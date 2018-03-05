package com.itis.android.lessontwo.ui.comicslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.model.general.ListItem;
import com.itis.android.lessontwo.ui.base.BaseAdapter;
import com.itis.android.lessontwo.ui.general.ListItemHolder;

import java.util.List;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public class ComicsAdapter extends BaseAdapter<Comics, ComicsItemHolder> {

    public ComicsAdapter(@NonNull List<Comics> items) {
        super(items);
    }

    @Override
    public ComicsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ComicsItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ComicsItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Comics item = getItem(position);
        holder.bind(item);
    }
}
