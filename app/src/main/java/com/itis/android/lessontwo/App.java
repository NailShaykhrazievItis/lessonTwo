package com.itis.android.lessontwo;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.itis.android.lessontwo.api.ApiFactory;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

/**
 * Created by Nail Shaykhraziev on 24.02.2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initStetho();
        initPicasso();
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
}
