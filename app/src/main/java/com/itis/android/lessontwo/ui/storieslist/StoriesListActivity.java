package com.itis.android.lessontwo.ui.storieslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.story.Story;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.ui.base.BaseAdapter;
import com.itis.android.lessontwo.widget.EmptyStateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by User on 16.03.2018.
 */

public class StoriesListActivity extends BaseActivity implements StoriesListContract.View,
        BaseAdapter.OnItemClickListener<Story>{

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private EmptyStateRecyclerView recyclerView;
    private TextView tvEmpty;

    private StoriesAdapter adapter;
    private StoriesListContract.Presenter presenter;

    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_stories_list, contentFrameLayout);
        initViews();
        initRecycler();
        new StoriesListPresenter(this);
        presenter.loadStories();
    }

    @Override
    public void setPresenter(StoriesListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showItems(@NonNull List<Story> items) {
        adapter.changeDataSet(items);
    }

    @Override
    public void showDetails(Story item) {

    }

    @Override
    public void addMoreItems(List<Story> items) {
        adapter.addAll(items);
    }

    @Override
    public void setNotLoading() {
        isLoading = false;
    }

    @Override
    public void showLoading(Disposable disposable) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(@NonNull Story item) {
        presenter.onItemClick(item);
    }

    private void initRecycler() {
        adapter = new StoriesAdapter(new ArrayList<>());
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
        toolbar = findViewById(R.id.tb_stories_list);
        progressBar = findViewById(R.id.pg_stories_list);
        recyclerView = findViewById(R.id.rv_stories_list);
        tvEmpty = findViewById(R.id.tv_empty_stories);
        supportActionBar(toolbar);
    }
}