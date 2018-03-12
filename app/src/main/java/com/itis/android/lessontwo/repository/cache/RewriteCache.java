package com.itis.android.lessontwo.repository.cache;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Aizat on 12.03.2018.
 */

public class RewriteCache<T extends RealmObject> implements Function<List<T>, Single<List<T>>> {

    private final Class<T> mClass;

    public RewriteCache(@NonNull Class<T> tClass) {
        mClass = tClass;
    }

    @Override
    public Single<List<T>> apply(@io.reactivex.annotations.NonNull List<T> elements) throws Exception {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.delete(mClass);
            realm.insert(elements);
        });
        return Single.just(elements);
    }
}
