package com.itis.android.lessontwo.repository.cache;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Nail Shaykhraziev on 05.03.2018.
 */
public class RewriteCache<T extends RealmObject> implements Function<List<T>, Single<List<T>>> {

    private final Class<T> mClass;

    public RewriteCache(@NonNull Class<T> tClass) {
        mClass = tClass;
    }

    @Override
    public Single<List<T>> apply(@io.reactivex.annotations.NonNull List<T> elements) throws Exception {
        try {
            Realm.getDefaultInstance().executeTransaction(realm -> {
                realm.delete(mClass);
                Log.d("TAG", "elements size = " + elements.size());
                realm.insert(elements);
                List<T> list = Realm.getDefaultInstance().where(mClass).findAll();
                Log.d("TAG", "list size = " + list.size());
            });
        } catch (Exception ex){
            Log.d("TAG","exception = " + ex.getMessage());
            throw ex;
        }
        return Single.just(elements);
    }
}
