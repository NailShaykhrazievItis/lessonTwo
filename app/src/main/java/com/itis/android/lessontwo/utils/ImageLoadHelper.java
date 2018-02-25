package com.itis.android.lessontwo.utils;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public final class ImageLoadHelper {

    private ImageLoadHelper(){
    }

    public static void loadPicture(@NonNull ImageView imageView, @NonNull String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
    }
}
