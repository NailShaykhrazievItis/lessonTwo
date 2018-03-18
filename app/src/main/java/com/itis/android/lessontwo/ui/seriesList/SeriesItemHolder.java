package com.itis.android.lessontwo.ui.seriesList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.series.Series;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

/**
 * Created by a9 on 18.03.18.
 */

public class SeriesItemHolder extends RecyclerView.ViewHolder {

    private static final int MAX_LENGTH = 80;
    private static final String MORE_TEXT = "...";

    private TextView name;
    private TextView description;
    private ImageView imageView;

    @NonNull
    public static SeriesItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.item_comics, null);
        SeriesItemHolder holder = new SeriesItemHolder(view);
        return holder;
    }

    public SeriesItemHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name);
        description = itemView.findViewById(R.id.tv_description);
        imageView = itemView.findViewById(R.id.iv_cover);
    }

    public void bind(@NonNull Series item) {
        name.setText(item.getName());
        if (item.getDescription() != null) {
            description.setText(cutLongDescription(item.getDescription()));
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
