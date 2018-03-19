package com.itis.android.lessontwo.ui.characterlist;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseAdapter;
import java.util.List;

public class CharacterAdapter extends BaseAdapter<Character, CharacterItemHolder> {

    public CharacterAdapter(@NonNull List<Character> items) {
        super(items);
    }

    @NonNull
    @Override
    public CharacterItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CharacterItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Character item = getItem(position);
        holder.bind(item);
    }
}
