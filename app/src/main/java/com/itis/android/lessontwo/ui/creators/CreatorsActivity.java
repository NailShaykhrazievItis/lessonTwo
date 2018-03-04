package com.itis.android.lessontwo.ui.creators;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.ui.comics.ComicsContract;

/**
 * Created by Tony on 3/5/2018.
 */

public class CreatorsActivity extends BaseActivity implements CreatorContract {
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private ImageView ivCover;
    private TextView tvDescription;
    private TextView tvPrice;
    private TextView tvPages;

    CreatorContract.Presenter presenter;

    
}
