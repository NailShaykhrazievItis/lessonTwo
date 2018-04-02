package com.itis.android.lessontwo.ui.creators;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.entity.creators.Creator;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

import static com.itis.android.lessontwo.utils.Constants.ID_KEY;
import static com.itis.android.lessontwo.utils.Constants.NAME_KEY;

/**
 * Created by valera071998@gmail.com on 25.02.2018.
 */
public class CreatorsActivity extends BaseActivity implements CreatorsView {

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private ImageView ivCover;
    private TextView tvName;
    private TextView tvDescription;

    @InjectPresenter
    CreatorsPresenter presenter;

    private Long id;

    public static void start(@NonNull Activity activity, @NonNull Creator creator) {
        Intent intent = new Intent(activity, CreatorsActivity.class);
        intent.putExtra(NAME_KEY, creator.getName());
        intent.putExtra(ID_KEY, creator.getId());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_creators, contentFrameLayout);
        initViews();

        id = getIntent().getLongExtra(ID_KEY, 0);
    }

    @Override
    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCreatorById() {
        presenter.load(id);
    }

    public void setDescription(Creator creator) {
        if (creator.getDescription() != null) {
            tvDescription.setText(creator.getDescription().length() > 0 ?
                    creator.getDescription().trim() : getString(R.string.text_desc_not_found));
        }
    }

    public void setImage(Creator creator) {
        if (creator.getImage() != null) {
            ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", creator.getImage().getPath(),
                    creator.getImage().getExtension()));
        } else {
            ImageLoadHelper.loadPictureByDrawable(ivCover, R.drawable.image_error_marvel_logo);
        }
    }

    public void setName(@NonNull Creator creator) {
        tvName.setText(creator.getName());
    }

    private void initViews() {
        findViews();
        supportActionBar(toolbar);
        setBackArrow(toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra(NAME_KEY));
    }

    private void findViews() {
        collapsingToolbar = findViewById(R.id.ct_creator);
        toolbar = findViewById(R.id.tb_creator);
        ivCover = findViewById(R.id.iv_creator);
        tvName = findViewById(R.id.tv_name);
        tvDescription = findViewById(R.id.tv_description);
    }
}