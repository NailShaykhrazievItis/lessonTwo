package com.itis.android.lessontwo.ui.characterslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.itis.android.lessontwo.model.entity.character.Character;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.ui.base.BaseAdapter;
import com.itis.android.lessontwo.ui.characters.CharactersActivity;
import com.itis.android.lessontwo.widget.EmptyStateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Ruslan on 02.03.2018.
 */

public class CharactersListActivity extends BaseActivity implements CharactersListView,
        BaseAdapter.OnItemClickListener<Character> {

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private EmptyStateRecyclerView recyclerView;
    private TextView tvEmpty;

    private CharactersAdapter adapter;

    @InjectPresenter
    CharactersListPresenter presenter;

    private boolean isLoading = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_characters_list, contentFrameLayout);
        initViews();
        initRecycler();
    }

    @Override
    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showItems(@NonNull List<Character> items) {
        adapter.changeDataSet(items);
    }

    @Override
    public void showDetails(Character item) {
        CharactersActivity.start(this, item);
    }

    @Override
    public void addMoreItems(List<Character> items) {
        adapter.addAll(items);
    }

    @Override
    public void setNotLoading() {
        isLoading = false;
    }

    public void showLoading(Disposable disposable) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(@NonNull Character item) {
        presenter.onItemClick(item);
    }

    private void initRecycler() {
        adapter = new CharactersAdapter(new ArrayList<>());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setEmptyView(tvEmpty);
        adapter.attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int currentPage = 0;
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
        toolbar = findViewById(R.id.tb_characters_list);
        progressBar = findViewById(R.id.pg_comics_list);
        recyclerView = findViewById(R.id.rv_comics_list);
        tvEmpty = findViewById(R.id.tv_empty);
        supportActionBar(toolbar);
    }
}

