package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.List;

/**
 * Created by Nail Shaykhraziev on 05.03.2018.
 */

public class RewriteCache <T> implements Function<List<T>, Observable<List<T>>> {

    private final Class<T> mClass;

    public RewriteCache(@NonNull Class<T> tClass) {
        mClass = tClass;
    }

    @Override
    public Observable<List<T>> apply(@io.reactivex.annotations.NonNull List<T> elements) throws Exception {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.delete(mClass);
            realm.insert(elements);
        });
        return Observable.just(elements);
    }
}
