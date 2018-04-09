package com.itis.android.lessontwo;

import android.app.Application;
import android.support.annotation.NonNull;

import com.facebook.stetho.Stetho;
import com.itis.android.lessontwo.di.ClientModule;
import com.itis.android.lessontwo.di.DaggerAppComponent;
import com.itis.android.lessontwo.di.component.AppComponent;
import com.itis.android.lessontwo.di.module.AppModule;
import com.itis.android.lessontwo.di.module.DataModule;
import com.itis.android.lessontwo.di.module.PresenterModule;
import com.itis.android.lessontwo.di.module.ServiceModule;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by Nail Shaykhraziev on 24.02.2018.
 */
public class App extends Application {

    private static AppComponent sAppComponent;
//    private static ApiComponent sApiComponent;
//    private static ComicsComponent sComicsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initStetho();
        initPicasso();
        initRealm();
        sAppComponent = DaggerAppComponent.builder()
                .dataModule(new DataModule())
                .presenterModule(new PresenterModule())
                .appModule(new AppModule(this))
                .clientModule(new ClientModule())
                .serviceModule(new ServiceModule())
                .build();

//        sApiComponent = DaggerAppComponent.builder()
//                .clientModule(new ClientModule())
//                .serviceModule(new ServiceModule())
//                .build();
//
//        sComicsComponent = DaggerAppComponent.builder()
//                .apiComponent(sApiComponent)
//                .appComponent(sAppComponent)
//                .comicsModule(new ComicsModule())
//                .build(();
    }

    @NonNull
    public static AppComponent getAppComponent() {
        return sAppComponent;
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
