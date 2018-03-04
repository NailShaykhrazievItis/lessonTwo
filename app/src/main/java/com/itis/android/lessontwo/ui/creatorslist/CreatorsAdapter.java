package com.itis.android.lessontwo.ui.creatorslist;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.model.creators.Creator;
import com.itis.android.lessontwo.ui.base.BaseAdapter;

import java.util.List;

/**
 * Created by valera071998@gmail.com on 02.03.2018.
 */

public class CreatorsAdapter extends BaseAdapter<Creator, CreatorsItemHolder> {

    public CreatorsAdapter(@NonNull List<Creator> items) {
        super(items);
    }

    @Override
    public CreatorsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CreatorsItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(CreatorsItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Creator item = getItem(position);
        holder.bind(item);
    }
}
