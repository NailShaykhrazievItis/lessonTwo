package com.itis.android.lessontwo;

import android.app.Application;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpFacade;
import com.facebook.stetho.Stetho;
import com.itis.android.lessontwo.di.component.AppComponent;
import com.itis.android.lessontwo.di.component.ComicsComponent;
import com.itis.android.lessontwo.di.component.ComicsDetailsSComponent;
import com.itis.android.lessontwo.di.component.ComicsListSComponent;
import com.itis.android.lessontwo.di.component.DaggerAppComponent;
import com.itis.android.lessontwo.di.module.AppModule;
import com.itis.android.lessontwo.di.module.ComicsDetailsScModule;
import com.itis.android.lessontwo.di.module.ComicsListScModule;
import com.itis.android.lessontwo.di.module.ComicsModule;
import com.itis.android.lessontwo.di.module.NetModule;
import com.itis.android.lessontwo.di.module.ServiceModule;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;
import timber.log.Timber;

/**
 * Created by Nail Shaykhraziev on 24.02.2018.
 */
public class App extends Application {

    private static AppComponent sAppComponent;
    private static ComicsComponent comicsComponent;
    private static ComicsDetailsSComponent comicsDetailsSComponent;
    private static ComicsListSComponent comicsListSComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        MvpFacade.init();
        Timber.plant(new Timber.DebugTree());
        initStetho();
        initPicasso();
        initRealm();
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .serviceModule(new ServiceModule())
                .build();
    }

    @NonNull
    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static ComicsComponent plusComicsComponent() {
        if (comicsComponent == null) {
            comicsComponent = getAppComponent().plusComicsComponent(new ComicsModule());
        }
        return comicsComponent;
    }

    public static void clearComicsComponent() {
        comicsComponent = null;
    }

    public static ComicsDetailsSComponent plusComicsDetailsSComponent() {
        if (comicsDetailsSComponent == null) {
            comicsDetailsSComponent = plusComicsComponent().plusComicsDetailsSComponent(new ComicsDetailsScModule());
        }
        return comicsDetailsSComponent;
    }

    public static void clearComicsDetailsSComponent() {
        comicsDetailsSComponent = null;
    }

    public static ComicsListSComponent plusComicsListSComponent() {
        if (comicsListSComponent == null) {
            comicsListSComponent = plusComicsComponent().plusComicsListSComponent(new ComicsListScModule());
        }
        return comicsListSComponent;
    }

    public static void clearComicsListSComponent() {
        comicsListSComponent = null;
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
