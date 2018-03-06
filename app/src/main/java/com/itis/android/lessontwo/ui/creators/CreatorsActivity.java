package com.itis.android.lessontwo.ui.creators;

import static com.itis.android.lessontwo.utils.Constants.ID_KEY;
import static com.itis.android.lessontwo.utils.Constants.NAME_KEY;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.creator.Creator;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.ui.creators.CreatorContract.Presenter;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

public class CreatorsActivity extends BaseActivity implements CreatorContract.View {

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private ImageView ivCover;
    private Presenter presenter;
    private ProgressBar progressBar;
    private TextView tvName;
    private TextView tvStories;

    public static void start(@NonNull Activity activity, @NonNull Creator creator) {
        Intent intent = new Intent(activity, CreatorsActivity.class);
        intent.putExtra(NAME_KEY, creator.getName());
        intent.putExtra(ID_KEY, creator.getId());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_creator, frameLayout);
        initViews();

        new CreatorPresenter(this);
        long id = getIntent().getLongExtra(ID_KEY, 0);
        presenter.loadCreator(id);
    }

    @Override
    public void handleError(final Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(final CreatorContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showCreator(@NonNull final Creator creator) {
        ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", creator.getImage().getPath(),
                creator.getImage().getExtension()));
        tvName.setText(creator.getName());
        tvStories.setText(creator.getDescription());
    }

    private void findViews() {
        collapsingToolbar = findViewById(R.id.ct_comics);
        toolbar = findViewById(R.id.tb_comics);
        ivCover = findViewById(R.id.iv_comics);
        tvName = findViewById(R.id.tv_name);
        tvStories = findViewById(R.id.tv_stories);
    }

    private void initViews() {
        findViews();
        supportActionBar(toolbar);
        setBackArrow(toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra(NAME_KEY));
    }
}