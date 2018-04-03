package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.model.creator.Creator;
import io.reactivex.Single;
import java.util.List;

public interface CreatorRepository {

    @NonNull
    Single<List<Creator>> creators(Long offset, Long limit);

    Single<List<Creator>> creatorsTest(Long offset, Long limit);

    Single<Creator> creator(Long id);
}
