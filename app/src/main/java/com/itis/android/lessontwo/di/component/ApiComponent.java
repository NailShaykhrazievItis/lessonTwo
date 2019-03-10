package com.itis.android.lessontwo.di.component;

import com.itis.android.lessontwo.api.ComicsService;
import com.itis.android.lessontwo.di.module.NetModule;
import com.itis.android.lessontwo.di.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Nail Shaykhraziev on 09.04.2018.
 */
@Singleton
@Component(modules = {
        NetModule.class,
        ServiceModule.class
})
public interface ApiComponent {

    ComicsService comicsService();

    Retrofit retrofit();

    OkHttpClient okHttpClient();
}
