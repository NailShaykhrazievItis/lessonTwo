package com.itis.android.lessontwo.utils;

import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.itis.android.lessontwo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public final class ImageLoadHelper {

    private ImageLoadHelper() {
    }

    public static void loadPicture(@NonNull ImageView imageView, @NonNull String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_marvel_launcher)
                .noFade()
                .into(imageView);
    }
}
