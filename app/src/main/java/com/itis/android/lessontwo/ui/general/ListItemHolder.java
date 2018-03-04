package com.itis.android.lessontwo.ui.general;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.general.ListItem;
import com.squareup.picasso.Picasso;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public class ListItemHolder extends RecyclerView.ViewHolder {

    private static final int MAX_LENGTH = 50;
    private static final String MORE_TEXT = "...";

    private TextView name;
    private TextView description;
    private ImageView imageView;

    @NonNull
    public static ListItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.item_comics, null);
        ListItemHolder holder = new ListItemHolder(view);
        return holder;
    }

    public ListItemHolder(View itemView) {
        super(itemView);
        //name = itemView.findViewById(R.id.tv_name);
        description = itemView.findViewById(R.id.tv_description);
        //imageView = itemView.findViewById(R.id.iv_cover);
    }

    public void bind(@NonNull ListItem item) {
        name.setText(item.getName());
        if (item.getDescription() != null) {
            description.setText(cutLongDescription(item.getDescription()));
        }
        if (item.getImage() != null) {
            loadPicture(imageView, String.format("%s.%s", item.getImage().getPath(),
                    item.getImage().getExtension()));
        }
    }

    private void loadPicture(@NonNull ImageView imageView, @NonNull String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
    }

    private String cutLongDescription(String description) {
        if (description.length() < MAX_LENGTH) {
            return description;
        } else {
            return description.substring(0, MAX_LENGTH - MORE_TEXT.length()) + MORE_TEXT;
        }
    }
}
