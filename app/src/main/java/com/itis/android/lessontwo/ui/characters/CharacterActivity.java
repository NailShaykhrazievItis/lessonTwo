package com.itis.android.lessontwo.ui.characters;

import static com.itis.android.lessontwo.utils.Constants.ID_KEY_CHARACTER;
import static com.itis.android.lessontwo.utils.Constants.NAME_KEY_CHARACTER;

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
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseActivity;
import com.itis.android.lessontwo.utils.ImageLoadHelper;


public class CharacterActivity extends BaseActivity implements CharacterView {

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private ImageView ivCover;
    private TextView tvDescription;
    private TextView tvName;
    private ProgressBar progressBar;

    @InjectPresenter
    CharacterPresenter presenter;

    private Long id;

    public static void start(@NonNull Activity activity, @NonNull Character character) {
        Intent intent = new Intent(activity, CharacterActivity.class);
        intent.putExtra(NAME_KEY_CHARACTER, character.getName());
        intent.putExtra(ID_KEY_CHARACTER, character.getId());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_character, contentFrameLayout);
        initViews();
        id = getIntent().getLongExtra(ID_KEY_CHARACTER, 0);
    }

    @Override
    public void getCharacterId() {
        presenter.init(id);
    }

    @Override
    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDescription(Character character) {
        if (character.getDescription() != null && !character.getDescription().equals("")) {
            tvDescription.setText(character.getDescription());
        } else {
            tvDescription.setText(R.string.text_desc_not_found);
        }
    }

    @Override
    public void setImage(Character character) {
        ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", character.getImage().getPath(),
                character.getImage().getExtension()));
    }

    @Override
    public void setName(Character character) {
    }

    private void initViews() {
        findViews();
        supportActionBar(toolbar);
        setBackArrow(toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra(NAME_KEY_CHARACTER));
    }

    private void findViews() {
        collapsingToolbar = findViewById(R.id.ct_characters);
        toolbar = findViewById(R.id.tb_characters);
        ivCover = findViewById(R.id.iv_characters);
        tvDescription = findViewById(R.id.tv_description_details_character);
    }
}
