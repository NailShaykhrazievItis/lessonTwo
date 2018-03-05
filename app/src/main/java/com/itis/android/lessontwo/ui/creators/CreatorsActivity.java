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
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.ui.comics.ComicsActivity;
import com.itis.android.lessontwo.ui.comics.ComicsContract;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

import static com.itis.android.lessontwo.utils.Constants.ID_KEY;
import static com.itis.android.lessontwo.utils.Constants.NAME_KEY;

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

    public static void start(@NonNull Activity activity, @NonNull Creator creator) {
        Intent intent = new Intent(activity, ComicsActivity.class);
        intent.putExtra(NAME_KEY, creator.getName());
        intent.putExtra(ID_KEY, creator.getId());
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_comics, contentFrameLayout);
        initViews();

        new ComicsPresenter(this);

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
        collapsingToolbar = findViewById(R.id.ct_comics);
        toolbar = findViewById(R.id.tb_comics);
        ivCover = findViewById(R.id.iv_comics);
        tvDescription = findViewById(R.id.tv_description);
        tvPrice = findViewById(R.id.tv_price);
        tvPages = findViewById(R.id.tv_pages);
    }

    @Override
    public void setPresenter(ComicsContract.Presenter presenter) {

    }

    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void showCreators(Creators creators) {
        ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", creators.getImage().getPath(),
                creators.getImage().getExtension()));
        if (creators.getTextObjects() != null){
            StringBuilder description = new StringBuilder();
            for (CreatorsTextObject creatorsTextObject : creators.getTextObjects()) {
                description.append(creatorsTextObject.getText()).append("\n");
            }
            tvDescription.setText(description.toString().trim());
        }
        if (creators.getPrices() != null && !creators.getPrices().isEmpty()){
            tvPrice.setText(getString(R.string.price_format, String.valueOf(creators.getPrices().get(0).getPrice())));
        }
        tvPages.setText(String.valueOf(creators.getPageCount()));
    }
}

