package com.itis.android.lessontwo;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by Nail Shaykhraziev on 24.02.2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initStetho();
        initPicasso();
        initRealm();
        RepositoryProvider.init();
        ApiFactory.recreate();
    }

    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(this))
                .build();
        Picasso.setSingletonInstance(picasso);
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
