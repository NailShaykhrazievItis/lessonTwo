package com.itis.android.lessontwo.ui.creatorslist;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.itis.android.lessontwo.model.creator.Creator;
import com.itis.android.lessontwo.ui.base.BaseAdapter;

import java.util.List;

/**
 * Created by Bulat Murtazin on 05.03.2018.
 * KPFU ITIS 11-601
 */

public class CreatorAdapter extends BaseAdapter<Creator, CreatorItemHolder> {

    public CreatorAdapter(@NonNull List<Creator> items) {
        super(items);
    }

    @Override
    public CreatorItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CreatorItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(CreatorItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Creator item = getItem(position);
        holder.bind(item);
    }
}
