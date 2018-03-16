package com.itis.android.lessontwo.ui.storieslist;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.itis.android.lessontwo.model.story.Story;
import com.itis.android.lessontwo.ui.base.BaseAdapter;

import java.util.List;

/**
 * Created by User on 16.03.2018.
 */

public class StoriesAdapter extends BaseAdapter<Story, StoriesItemHolder> {

    public StoriesAdapter(@NonNull List<Story> items) {
        super(items);
    }

    @NonNull
    @Override
    public StoriesItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return StoriesItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Story item = getItem(position);
        holder.bind(item);
    }
}
