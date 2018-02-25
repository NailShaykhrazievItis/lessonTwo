package com.itis.android.lessontwo.api;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Nail Shaykhraziev on 24.02.2018.
 */

public final class LoggingInterceptor implements Interceptor {

    private final Interceptor loggingInterceptor;

    private LoggingInterceptor() {
        loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    @NonNull
    public static Interceptor create() {
        return new LoggingInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return loggingInterceptor.intercept(chain);
    }
}
