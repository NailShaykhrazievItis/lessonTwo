package com.itis.android.lessontwo.ui.creatorslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

/**
 * Created by Tony on 3/5/2018.
 */

public class CreatorsItemHolder extends RecyclerView.ViewHolder {

    private TextView fullName;
    private ImageView imageView;

    @NonNull
    public static CreatorsItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.item_creators, null);
        CreatorsItemHolder holder = new CreatorsItemHolder(view);
        return holder;
    }

    public CreatorsItemHolder(View itemView) {
        super(itemView);
        fullName = itemView.findViewById(R.id.tv_name);
        imageView = itemView.findViewById(R.id.iv_cover);
    }

    public void bind(@NonNull Creators item) {
        fullName.setText(item.getFullName());
        if (item.getImage() != null) {
            ImageLoadHelper.loadPicture(imageView, String.format("%s.%s", item.getImage().getPath(),
                    item.getImage().getExtension()));
        }
    }
}
