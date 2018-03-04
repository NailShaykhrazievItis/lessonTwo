package com.itis.android.lessontwo.ui.characterslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

/**
 * Created by Home on 04.03.2018.
 */

public class CharactersItemHolder extends RecyclerView.ViewHolder {

    private static final int MAX_LENGTH = 80;
    private static final String MORE_TEXT = "...";

    private TextView tvName;
    private TextView tvDescription;
    private ImageView imageView;

    @NonNull
    public static CharactersItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.item_comics, null);
        CharactersItemHolder holder = new CharactersItemHolder(view);
        return holder;
    }

    public CharactersItemHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        tvDescription = itemView.findViewById(R.id.tv_description);
        imageView = itemView.findViewById(R.id.iv_cover);
    }

    public void bind(@NonNull Character item) {
        tvName.setText(item.getName());
        if (item.getDescription() != null) {
            tvDescription.setText(cutLongDescription(item.getDescription()));
        }
        if (item.getImage() != null) {
            ImageLoadHelper.loadPicture(imageView, String.format("%s.%s", item.getImage().getPath(),
                    item.getImage().getExtension()));
        }
    }

    private String cutLongDescription(String description) {
        if (description.length() < MAX_LENGTH) {
            return description;
        } else {
            return description.substring(0, MAX_LENGTH - MORE_TEXT.length()) + MORE_TEXT;
        }
    }
}