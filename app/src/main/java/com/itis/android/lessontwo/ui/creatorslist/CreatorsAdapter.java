package com.itis.android.lessontwo.ui.creatorslist;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.ui.base.BaseAdapter;

import java.util.List;

/**
 * Created by Tony on 3/5/2018.
 */

public class CreatorsAdapter extends BaseAdapter<Creators,CreatorsItemHolder> {

    public CreatorsAdapter(@NonNull List <Creators> items) {
        super(items);
    }

    @Override
    public CreatorsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CreatorsItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(CreatorsItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Creators item = getItem(position);
        holder.bind(item);
    }
}
