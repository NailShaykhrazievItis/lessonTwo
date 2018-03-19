package com.itis.android.lessontwo.ui.series;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.series.Series;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

import static com.itis.android.lessontwo.utils.Constants.ID_KEY;
import static com.itis.android.lessontwo.utils.Constants.NAME_KEY;

public class SeriesActivity extends BaseActivity implements SeriesView{

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private ImageView ivCover;
    private TextView tvDescription;
    private ProgressBar progressBar;

    @InjectPresenter(type = PresenterType.WEAK)
    SeriesPresenter presenter;

    private Long id;

    public static void start(@NonNull Activity activity,@NonNull Series series){
        Intent intent = new Intent(activity,SeriesActivity.class);
        intent.putExtra(NAME_KEY,series.getName());
        intent.putExtra(ID_KEY,series.getId());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_series, contentFrameLayout);
        initViews();
        id = getIntent().getLongExtra(ID_KEY,0);
    }

    @Override
    public void getSeriesId() {
        presenter.init(id);
    }

    @Override
    public void setDescription(@NonNull Series series) {
        if(series.getDescription() != null){
            tvDescription.setText(series.getDescription().trim());
        }else{
            tvDescription.setText(R.string.text_desc_not_found);
        }
    }

    @Override
    public void setImage(@NonNull Series series) {
        if(series.getImage() != null){
            ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", series.getImage().getPath(),
                    series.getImage().getExtension()));
        }else{
            ImageLoadHelper.loadPictureByDrawable(ivCover, R.drawable.image_error_marvel_logo);
        }
    }

    @Override
    public void handleError(@NonNull Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("Alm", throwable.getMessage());
        throwable.printStackTrace();
    }

    private void initViews() {
        findViews();
        supportActionBar(toolbar);
        setBackArrow(toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra(NAME_KEY));
    }

    private void findViews() {
        collapsingToolbar = findViewById(R.id.ct_series);
        toolbar = findViewById(R.id.tb_series);
        ivCover = findViewById(R.id.iv_series);
        tvDescription = findViewById(R.id.tv_description_series);
        progressBar = findViewById(R.id.progress_loader_series);
    }
}
