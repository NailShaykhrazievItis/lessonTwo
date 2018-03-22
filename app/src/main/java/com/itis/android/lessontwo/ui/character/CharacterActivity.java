package com.itis.android.lessontwo.ui.character;

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
    private TextView tvName;
    private TextView tvDescription;

    @InjectPresenter
    CharacterPresenter presenter;

    private long id;

    public static void start(@NonNull Activity activity, @NonNull Character character) {
        Intent intent = new Intent(activity, CharacterActivity.class);
        intent.putExtra(NAME_KEY, character.getName());
        intent.putExtra(ID_KEY, character.getId());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.activity_character, frameLayout);
        initViews();

        id = getIntent().getLongExtra(ID_KEY, 0);
    }

    @Override
    public void getCharacterId() {
        presenter.init(id);
    }

    @Override
    public void setCharacterImage(final Character character) {
        if (character.getImage() != null) {
            ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", character.getImage().getPath(),
                    character.getImage().getExtension()));
        } else {
            ImageLoadHelper.loadPictureByDrawable(ivCover, R.drawable.image_error_marvel_logo);
        }
    }

    @Override
    public void setCharacterName(final Character character) {
        tvName.setText(character.getName());
    }

    @Override
    public void setCharacterDescription(final Character character) {
        tvDescription.setText(character.getDescription());
    }


    @Override
    public void handleError(final Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void findViews() {
        collapsingToolbar = findViewById(R.id.ct_character);
        toolbar = findViewById(R.id.tb_character);
        ivCover = findViewById(R.id.iv_character);
        tvName = findViewById(R.id.tv_name);
        tvDescription = findViewById(R.id.tv_description);
    }

    private void initViews() {
        findViews();
        supportActionBar(toolbar);
        setBackArrow(toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra(NAME_KEY));
    }
}
