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
import com.itis.android.lessontwo.model.entity.creators.Creator;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.ui.base.BaseContract;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

import static com.itis.android.lessontwo.utils.Constants.ID_KEY;
import static com.itis.android.lessontwo.utils.Constants.NAME_KEY;

/**
 * Created by valera071998@gmail.com on 25.02.2018.
 */
public class CreatorsActivity extends BaseActivity  implements BaseContract.View<Creator>{

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private ImageView ivCover;
    private TextView tvName;
    private TextView tvDescription;

    private BaseContract.Presenter presenter;

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

        long id = getIntent().getLongExtra(ID_KEY, 0);
        new CreatorsPresenter(this);  // странный способ связывания view и presenter
        presenter.load(id);
    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;
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

    @Override
    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show(@NonNull Creator creator) {
        ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", creator.getImage().getPath(),
                creator.getImage().getExtension()));

        tvName.setText(creator.getName());
        tvDescription.setText(creator.getDescription().trim());
    }
}