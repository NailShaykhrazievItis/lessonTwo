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

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.utils.ImageLoadHelper;
import com.squareup.picasso.Picasso;

import static com.itis.android.lessontwo.utils.Constants.ID_KEY;
import static com.itis.android.lessontwo.utils.Constants.NAME_KEY;

/**
 * Created by Tony on 3/5/2018.
 */

public class CreatorsActivity extends BaseActivity implements CreatorContract.View {

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private ImageView ivCover;
    private TextView tvName;

    CreatorContract.Presenter presenter;

    public static void start(@NonNull Activity activity, @NonNull Creators creator) {
        Intent intent = new Intent(activity, CreatorsActivity.class);
        intent.putExtra(NAME_KEY, creator.getFullName());
        intent.putExtra(ID_KEY, creator.getId());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_creators, contentFrameLayout);
        initViews();

        new CreatorPresenter(this);

        long id = getIntent().getLongExtra(ID_KEY, 0);
        presenter.loadCreators(id);
    }

    private void initViews() {
        findViews();
        supportActionBar(toolbar);
        setBackArrow(toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra(NAME_KEY));
    }

    private void findViews() {
        collapsingToolbar = findViewById(R.id.ct_creators);
        toolbar = findViewById(R.id.tb_creators);
        ivCover = findViewById(R.id.iv_creators);
        tvName = findViewById(R.id.tv_name);
    }

    @Override
    public void setPresenter(CreatorContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void showCreators(Creators creators) {
        if(creators.getFullName() != null){
           tvName.setText(creators.getFullName().trim());
        }
        if (creators.getImage() != null) {
//            ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", creators.getImage().getPath(),
//                    creators.getImage().getExtension()));
            Picasso.with(this).load(R.drawable.the_homak).into(ivCover);
        } else {
            ImageLoadHelper.loadPictureByDrawable(ivCover, R.drawable.image_error_marvel_logo);
        }
    }
}

