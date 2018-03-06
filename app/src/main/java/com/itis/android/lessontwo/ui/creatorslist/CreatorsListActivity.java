package com.itis.android.lessontwo.ui.creatorslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.ui.base.BaseAdapter;
import com.itis.android.lessontwo.ui.creators.CreatorsActivity;
import com.itis.android.lessontwo.widget.EmptyStateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Tony on 3/5/2018.
 */

public class CreatorsListActivity extends BaseActivity implements CreatorsListContract
        .View,BaseAdapter.OnItemClickListener<Creators> {

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private EmptyStateRecyclerView recyclerView;
    private TextView tvEmpty;

    private CreatorsAdapter adapter;
    private CreatorsListContract.Presenter presenter;

    private boolean isLoading = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_creators_list, contentFrameLayout);
        initViews();
        initRecycler();
        new CreatorsListPresenter(this);
        presenter.loadCreators();
    }

    @Override
    public void setPresenter(CreatorsListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("Alm", error.getMessage());
        error.printStackTrace();
    }

    @Override
    public void showItems(@NonNull List<Creators> items) {
        adapter.changeDataSet(items);
    }

    @Override
    public void showDetails(Creators item) {
        CreatorsActivity.start(this, item);
    }

    @Override
    public void addMoreItems(List<Creators> items) {
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
    public void onItemClick(@NonNull Creators item) {
        presenter.onItemClick(item);
    }

    private void initRecycler() {
        adapter = new CreatorsAdapter(new ArrayList<>());
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
        toolbar = findViewById(R.id.tb_creators_list);
        progressBar = findViewById(R.id.pg_creators_list);
        recyclerView = findViewById(R.id.rv_creators_list);
        tvEmpty = findViewById(R.id.tv_empty);
        supportActionBar(toolbar);
    }
}
