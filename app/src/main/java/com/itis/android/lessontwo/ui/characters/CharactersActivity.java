package com.itis.android.lessontwo.ui.characters;

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
import com.itis.android.lessontwo.model.entity.character.Character;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

import static com.itis.android.lessontwo.utils.Constants.ID_KEY;
import static com.itis.android.lessontwo.utils.Constants.NAME_KEY;

/**
 * Created by Ruslan on 02.03.2018.
 */

public class CharactersActivity extends BaseActivity implements BaseContract.View<Character>{

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private ImageView ivCover;
    private TextView tvDescription;
    private TextView tvName;

    private BaseContract.Presenter presenter;

    public static void start(@NonNull Activity activity, @NonNull Character character) {
        Intent intent = new Intent(activity, CharactersActivity.class);
        intent.putExtra(NAME_KEY, character.getName());
        intent.putExtra(ID_KEY, character.getId());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_characters, contentFrameLayout);
        initViews();

        long id = getIntent().getLongExtra(ID_KEY, 0);
        new CharactersPresenter(this);
        presenter.load(id);
    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show(@NonNull Character character) {
        tvName.setText(character.getName());
        if (character.getImage() != null) {
            ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", character.getImage().getPath(),
                    character.getImage().getExtension()));
        } else {
            ImageLoadHelper.loadPictureByDrawable(ivCover, R.drawable.image_error_marvel_logo);
        }
        if (character.getDescription() != null) {
            tvDescription.setText(character.getDescription().length() > 0 ?
                    character.getDescription().trim() : getString(R.string.text_desc_not_found));
        }
    }

    private void initViews() {
        findViews();
        supportActionBar(toolbar);
        setBackArrow(toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra(NAME_KEY));
    }

    private void findViews() {
        collapsingToolbar = findViewById(R.id.ct_character);
        toolbar = findViewById(R.id.tb_character);
        ivCover = findViewById(R.id.iv_character);
        tvDescription = findViewById(R.id.tv_description);
        tvName = findViewById(R.id.tv_name);
    }
}
