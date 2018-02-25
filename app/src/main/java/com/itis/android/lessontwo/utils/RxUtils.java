package com.itis.android.lessontwo.utils;

import android.support.annotation.NonNull;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public final class RxUtils {

    private RxUtils() {
    }

    @NonNull
    public static <T> ObservableTransformer<T, T> async() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    public static <T> ObservableTransformer<T, T> async(@NonNull final Scheduler background,
                                                        @NonNull final Scheduler main) {
        return observable -> observable
                .subscribeOn(background)
                .observeOn(main);
    }
}
