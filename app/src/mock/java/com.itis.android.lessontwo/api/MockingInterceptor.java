package com.itis.android.lessontwo.api;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Nail Shaykhraziev on 26.03.2018.
 */
public class MockingInterceptor implements Interceptor {

    private final RequestsHandler handler;
    private final Random random;

    private MockingInterceptor() {
        handler = new RequestsHandler();
        random = new SecureRandom();
    }

    @NonNull
    public static Interceptor create() {
        return new MockingInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String path = request.url().encodedPath();
        if (handler.shouldIntercept(path)) {
            Response response = handler.proceed(request, path);
            int stubDelay = 500 + random.nextInt(2500);
            SystemClock.sleep(stubDelay);
            return response;
        }
        return chain.proceed(request);
    }
}
