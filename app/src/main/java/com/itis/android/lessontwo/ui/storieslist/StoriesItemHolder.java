package com.itis.android.lessontwo.ui.storieslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.story.Story;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

/**
 * Created by User on 16.03.2018.
 */

public class StoriesItemHolder extends RecyclerView.ViewHolder {

    private static final int MAX_LENGTH = 80;
    private static final String MORE_TEXT = "...";

    private TextView name;
    private TextView description;
    private ImageView imageView;

    @NonNull
    public static StoriesItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.item_story, null);
        StoriesItemHolder holder = new StoriesItemHolder(view);
        return holder;
    }

    public StoriesItemHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name_story);
        description = itemView.findViewById(R.id.tv_description_story);
        imageView = itemView.findViewById(R.id.iv_cover_story);
    }

    public void bind(@NonNull Story item) {
        name.setText(item.getTitle());
        if (item.getDescription() != null) {
            description.setText(cutLongDescription(item.getDescription()));
        }
        if (item.getImage() != null) {
            ImageLoadHelper.loadPicture(imageView, String.format("%s.%s", item.getImage().getPath(),
                    item.getImage().getExtension()));
        } else {
            Log.d("image:", item.getId().toString());
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
