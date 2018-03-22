package com.itis.android.lessontwo.ui.creatorslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.creator.Creator;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

/**
 * Created by Bulat Murtazin on 05.03.2018.
 * KPFU ITIS 11-601
 */

public class CreatorItemHolder extends RecyclerView.ViewHolder {

    private static final int MAX_LENGTH = 80;
    private static final String MORE_TEXT = "...";

    private TextView name;
    private ImageView imageView;

    @NonNull
    public static CreatorItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.item_creator, null);
        CreatorItemHolder holder = new CreatorItemHolder(view);
        return holder;
    }

    public CreatorItemHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_creator_name);
        imageView = itemView.findViewById(R.id.iv_creator_cover);
    }

    public void bind(@NonNull Creator item) {
        name.setText(item.getName());
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
