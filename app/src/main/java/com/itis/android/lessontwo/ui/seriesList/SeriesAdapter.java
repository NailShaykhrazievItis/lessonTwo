package com.itis.android.lessontwo.ui.seriesList;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.itis.android.lessontwo.model.series.Series;
import com.itis.android.lessontwo.ui.base.BaseAdapter;

import java.util.List;

/**
 * Created by a9 on 18.03.18.
 */

public class SeriesAdapter extends BaseAdapter<Series, SeriesItemHolder> {

    public SeriesAdapter(@NonNull List<Series> items) {
        super(items);
    }

    @Override
    public SeriesItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SeriesItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(SeriesItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Series item = getItem(position);
        holder.bind(item);
    }
}
