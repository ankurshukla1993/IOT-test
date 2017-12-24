package com.jakewharton.retrofit2.adapter.rxjava2;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import retrofit2.CallAdapter;
import retrofit2.CallAdapter.Factory;
import retrofit2.Response;
import retrofit2.Retrofit;

public final class RxJava2CallAdapterFactory extends Factory {
    private final Scheduler scheduler;

    public static RxJava2CallAdapterFactory create() {
        return new RxJava2CallAdapterFactory(null);
    }

    public static RxJava2CallAdapterFactory createWithScheduler(Scheduler scheduler) {
        if (scheduler != null) {
            return new RxJava2CallAdapterFactory(scheduler);
        }
        throw new NullPointerException("scheduler == null");
    }

    private RxJava2CallAdapterFactory(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        if (rawType == Completable.class) {
            return new RxJava2CallAdapter(Void.class, this.scheduler, false, true, false, false, false, true);
        }
        boolean isFlowable = rawType == Flowable.class;
        boolean isSingle = rawType == Single.class;
        boolean isMaybe = rawType == Maybe.class;
        if (rawType != Observable.class && !isFlowable && !isSingle && !isMaybe) {
            return null;
        }
        boolean isResult = false;
        boolean isBody = false;
        if (returnType instanceof ParameterizedType) {
            Type responseType;
            Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
            Class<?> rawObservableType = getRawType(observableType);
            if (rawObservableType == Response.class) {
                if (observableType instanceof ParameterizedType) {
                    responseType = getParameterUpperBound(0, (ParameterizedType) observableType);
                } else {
                    throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
                }
            } else if (rawObservableType != Result.class) {
                responseType = observableType;
                isBody = true;
            } else if (observableType instanceof ParameterizedType) {
                responseType = getParameterUpperBound(0, (ParameterizedType) observableType);
                isResult = true;
            } else {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            return new RxJava2CallAdapter(responseType, this.scheduler, isResult, isBody, isFlowable, isSingle, isMaybe, false);
        }
        String name = isFlowable ? "Flowable" : isSingle ? "Single" : "Observable";
        throw new IllegalStateException(name + " return type must be parameterized as " + name + "<Foo> or " + name + "<? extends Foo>");
    }
}
