package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nail Shaykhraziev on 05.03.2018.
 */

public class ErrorReadFromCache<T> implements Function<Throwable, Observable<List<T>>> {

    private final Class<T> mClass;

    public ErrorReadFromCache(@NonNull Class<T> tClass) {
        mClass = tClass;
    }

    @Override
    public Observable<List<T>> apply(@io.reactivex.annotations.NonNull Throwable throwable) {
        return Observable.just(new ArrayList<T>());
    }
}
