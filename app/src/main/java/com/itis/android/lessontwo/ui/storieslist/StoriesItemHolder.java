package com.itis.android.lessontwo.ui.storieslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.story.Story;

/**
 * Created by User on 16.03.2018.
 */

public class StoriesItemHolder extends RecyclerView.ViewHolder {

    private TextView name;

    @NonNull
    public static StoriesItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.item_story, null);
        StoriesItemHolder holder = new StoriesItemHolder(view);
        return holder;
    }

    public StoriesItemHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name_story);
    }

    public void bind(@NonNull Story item) {
        name.setText(item.getTitle());
    }
}
