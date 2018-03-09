package com.itis.android.lessontwo.utils;

import android.support.annotation.NonNull;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;
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

    @NonNull
    public static <T> SingleTransformer<T, T> asyncSingle() {
        return single -> single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    public static <T> MaybeTransformer<T, T> asyncMaybe() {
        return maybe -> maybe
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    public static CompletableTransformer asyncCompletable() {
        return completable -> completable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    public static <T>FlowableTransformer<T,T> asyncFlowable() {
        return flowable -> flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
