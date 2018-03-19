package com.itis.android.lessontwo.ui.comics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.model.comics.ComicsTextObject;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.ui.comics.ComicsContract.Presenter;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

import static com.itis.android.lessontwo.utils.Constants.ID_KEY;
import static com.itis.android.lessontwo.utils.Constants.NAME_KEY;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */
public class ComicsActivity extends BaseActivity implements ComicsContract.View {

    private CollapsingToolbarLayout collapsingToolbar;

    private Toolbar toolbar;

    private ImageView ivCover;

    private TextView tvDescription;

    private TextView tvPrice;

    private TextView tvPages;

    private ProgressBar progressBar;

    @InjectPresenter
    ComicsPresenter presenter;

    public static void start(@NonNull Activity activity, @NonNull Comics comics) {
        Intent intent = new Intent(activity, ComicsActivity.class);
        intent.putExtra(NAME_KEY, comics.getName());
        intent.putExtra(ID_KEY, comics.getId());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_comics, contentFrameLayout);
        initViews();

        new ComicsPresenter();
        long id = getIntent().getLongExtra(ID_KEY, 0);
        presenter.loadComics(id);
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

    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void showComics(@NonNull Comics comics) {
        if (comics.getImage() != null) {
            ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", comics.getImage().getPath(),
                    comics.getImage().getExtension()));
        } else {
            ImageLoadHelper.loadPictureByDrawable(ivCover, R.drawable.image_error_marvel_logo);
        }
        if (comics.getTextObjects() != null) {
            StringBuilder description = new StringBuilder();
            for (ComicsTextObject comicsTextObject : comics.getTextObjects()) {
                description.append(comicsTextObject.getText()).append("\n");
            }
            tvDescription.setText(description.length() > 0 ?
                    description.toString().trim() : getString(R.string.text_desc_not_found));
        }
        if (comics.getPrices() != null && !comics.getPrices().isEmpty()) {
            tvPrice.setText(getString(R.string.price_format, String.valueOf(comics.getPrices().get(0).getPrice())));
        }
        tvPages.setText(String.valueOf(comics.getPageCount()));
    }
}
