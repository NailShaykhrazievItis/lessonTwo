package com.itis.android.lessontwo.ui.general;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.itis.android.lessontwo.model.general.ListItem;
import com.itis.android.lessontwo.ui.base.BaseAdapter;

import java.util.List;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */
// maybe я упоролся
public class ListAdapter<T extends ListItem> extends BaseAdapter<T, ListItemHolder> {

    public ListAdapter(@NonNull List<T> items) {
        super(items);
    }

    @Override
    public ListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ListItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ListItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ListItem item = getItem(position);
        holder.bind(item);
    }
}