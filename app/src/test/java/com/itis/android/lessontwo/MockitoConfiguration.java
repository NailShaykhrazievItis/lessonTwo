package com.itis.android.lessontwo;

import android.support.annotation.NonNull;

import org.mockito.configuration.DefaultMockitoConfiguration;
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Nail Shaykhraziev on 18.03.2018.
 */

public class MockitoConfiguration extends DefaultMockitoConfiguration {
    public Answer<Object> getDefaultAnswer() {
        return new ReturnsEmptyValues() {
            @Override
            public Object answer(InvocationOnMock inv) {
                Class<?> type = inv.getMethod().getReturnType();
                if (type.isAssignableFrom(Observable.class)) {
                    return Observable.error(createException(inv));
                } else if (type.isAssignableFrom(Single.class)) {
                    return Single.error(createException(inv));
                } else {
                    return super.answer(inv);
                }
            }
        };
    }

    @NonNull
    private RuntimeException  createException(InvocationOnMock inv) {
        String s = inv.toString();
        return new RuntimeException("No mock defined for invocation " + s);
    }
}