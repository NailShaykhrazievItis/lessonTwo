package com.itis.android.lessontwo.repository.cache;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Aizat on 12.03.2018.
 */

public class ErrorSingleReadFromCache<T extends RealmObject> implements Function<Throwable, Single<T>> {

    private final Class<T> mClass;
    private final Long id;

    public ErrorSingleReadFromCache(@NonNull Class<T> tClass, Long id) {
        mClass = tClass;
        this.id = id;
    }

    @Override
    public Single<T> apply(@io.reactivex.annotations.NonNull Throwable throwable) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<T> results =  realm.where(mClass).equalTo("id", id).findAll();
        return Single.just(realm.copyFromRealm(results.first()));
    }
}
