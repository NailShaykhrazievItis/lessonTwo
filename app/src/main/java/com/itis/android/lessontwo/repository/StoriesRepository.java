package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.model.story.Story;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by User on 16.03.2018.
 */

public interface StoriesRepository {

    @NonNull
    Single<List<Story>> stories(Long offset, Long limit);

    Single<Story> stories(Long id);

    Single<List<Story>> storiesTest(Long offset, Long limit);
}
