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

public class RequestsHandler {

    private final Map<String, String> responsesMap = new HashMap<>();

    public RequestsHandler() {
        //comics
        responsesMap.put("comics/27649", "comics.json");
        responsesMap.put("comics_test", "comicsList.json");
        //characters
        responsesMap.put("character/1011334", "character.json");
        responsesMap.put("characters_test", "charactersList.json");
        //stories
        responsesMap.put("stories_test","storiesList.json");
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
    public Response proceed(@NonNull Request request, @NonNull String path) {

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
