package com.itis.android.lessontwo.ui.characterslist;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseAdapter;

import java.util.List;

/**
 * Created by Home on 04.03.2018.
 */

public class CharactersAdapter extends BaseAdapter<Character, CharactersItemHolder> {

    public CharactersAdapter(@NonNull List<Character> items) {
        super(items);
    }

    @Override
    public CharactersItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CharactersItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(CharactersItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Character item = getItem(position);
        holder.bind(item);
    }
}
