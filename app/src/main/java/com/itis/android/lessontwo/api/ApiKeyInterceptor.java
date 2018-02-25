package com.itis.android.lessontwo.api;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.BuildConfig;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Date;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.itis.android.lessontwo.utils.Constants.API_KEY_PARAM;
import static com.itis.android.lessontwo.utils.Constants.HASH_PARAM;
import static com.itis.android.lessontwo.utils.Constants.TIMESTAMP_PARAM;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */

public final class ApiKeyInterceptor implements Interceptor {

    private ApiKeyInterceptor() {
    }

    @NonNull
    public static Interceptor create() {
        return new ApiKeyInterceptor();
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        return chain.proceed(original.newBuilder()
                .url(getUrl(original))
                .build());
    }

    /**
     * From documentation: Params: {"apikey": "your api key", "ts": "a timestamp","hash": "your hash"}
     */
    private HttpUrl getUrl(Request request) {
        String key = BuildConfig.PUBLIC_KEY;
        String privateKey = BuildConfig.PRIVATE_KEY;
        Long timestamp = new Date().getTime();
        String hash = getHash(key, privateKey, timestamp);
        return request.url().newBuilder()
                .addQueryParameter(API_KEY_PARAM, key)
                .addQueryParameter(TIMESTAMP_PARAM, String.valueOf(timestamp))
                .addQueryParameter(HASH_PARAM, hash)
                .build();
    }

    /**
     * @param ts         a timestamp (or other long string which can change on a request-by-request basis)
     * @param key        public key from your account
     * @param privateKey private key from your account
     * @return String md5 hash
     */
    private String getHash(String key, String privateKey, Long ts) {
        return new String(Hex.encodeHex(DigestUtils.md5(ts + privateKey + key)));
    }
}
