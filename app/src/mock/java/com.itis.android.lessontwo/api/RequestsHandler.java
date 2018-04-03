package com.itis.android.lessontwo.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.itis.android.lessontwo.App;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Nail Shaykhraziev on 26.03.2018.
 */
public class RequestsHandler {

    private final Map<String, String> responsesMap = new HashMap<>();

    public RequestsHandler() {
        responsesMap.put("comics/59539", "comics.json");
        responsesMap.put("comics_test", "cList.json");

        responsesMap.put("series_test", "seriesList.json");
        responsesMap.put("series/18454", "series.json");
    }

    public boolean shouldIntercept(@NonNull String path) {
        Set<String> keys = responsesMap.keySet();
        for (String interceptUrl : keys) {
            if (path.contains(interceptUrl)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public Response proceed(@NonNull Request request, @NonNull String path) throws IOException {
//        if ("error".equals(token)) { // я не придумал пример
//            return OkHttpResponse.error(request, 400, "Error for path " + path);
//        }
        if (request.url().queryParameter("offset") != null &&
                Long.decode(request.url().queryParameter("offset")) < 0) {
            return OkHttpResponse.error(request, 400, "Error for path " + path);
        }
        Set<String> keys = responsesMap.keySet();
        for (String interceptUrl : keys) {
            if (path.contains(interceptUrl)) {
                String mockResponsePath = responsesMap.get(interceptUrl);
                return createResponseFromAssets(request, mockResponsePath);
            }
        }
        return OkHttpResponse.error(request, 500, "Incorrectly intercepted request");
    }

    @NonNull
    private Response createResponseFromAssets(@NonNull Request request, @NonNull String assetPath) {
        Context context = App.getsContext();
        try {
            final InputStream stream = context.getAssets().open(assetPath);
            try {
                return OkHttpResponse.success(request, stream);
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            return OkHttpResponse.error(request, 500, e.getMessage());
        }
    }
}
