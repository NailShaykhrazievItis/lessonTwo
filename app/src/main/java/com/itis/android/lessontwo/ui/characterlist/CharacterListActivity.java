package com.itis.android.lessontwo.ui.characterlist;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.ui.base.BaseAdapter;
import com.itis.android.lessontwo.ui.character.CharacterActivity;
import com.itis.android.lessontwo.widget.EmptyStateRecyclerView;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

public class CharacterListActivity extends BaseActivity implements CharacterListView,
        BaseAdapter.OnItemClickListener<Character> {

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private EmptyStateRecyclerView recyclerView;
    private TextView tvEmpty;

    @InjectPresenter
    CharacterListPresenter presenter;

    private CharacterAdapter adapter;

    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_character_list, contentFrameLayout);
        initViews();
        initRecycler();
    }

    @Override
    public void onItemClick(@NonNull final Character item) {
        presenter.onItemClick(item);
    }

    @Override
    public void handleError(final Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showItems(@NonNull final List<Character> items) {
        adapter.changeDataSet(items);
    }

    @Override
    public void showDetails(final Character item) {
        CharacterActivity.start(this, item);
    }

    @Override
    public void addMoreItems(final List<Character> items) {
        adapter.addAll(items);
    }

    @Override
    public void setNotLoading() {
        isLoading = false;
    }

    @Override
    public void showLoading(final Disposable disposable) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private void initRecycler() {
        adapter = new CharacterAdapter(new ArrayList<>());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setEmptyView(tvEmpty);
        adapter.attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int currentPage = 0;
            // обычно бывает флаг последней страницы, но я че т его не нашел, если не найдется, то можно удалить, всегда тру
            private boolean isLastPage = false;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = manager.getChildCount();
                int totalItemCount = manager.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= 20) {
                        isLoading = true;
                        presenter.loadNextElements(++currentPage);
                    }
                }
            }
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.tb_character_list);
        progressBar = findViewById(R.id.pg_character_list);
        recyclerView = findViewById(R.id.rv_character_list);
        tvEmpty = findViewById(R.id.tv_character_empty);
        supportActionBar(toolbar);
    }
}
