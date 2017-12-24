package com.jakewharton.retrofit2.adapter.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

final class RxJava2CallAdapter implements CallAdapter<Object> {
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isFlowable;
    private final boolean isMaybe;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;
    private final Scheduler scheduler;

    RxJava2CallAdapter(Type responseType, Scheduler scheduler, boolean isResult, boolean isBody, boolean isFlowable, boolean isSingle, boolean isMaybe, boolean isCompletable) {
        this.responseType = responseType;
        this.scheduler = scheduler;
        this.isResult = isResult;
        this.isBody = isBody;
        this.isFlowable = isFlowable;
        this.isSingle = isSingle;
        this.isMaybe = isMaybe;
        this.isCompletable = isCompletable;
    }

    public Type responseType() {
        return this.responseType;
    }

    public <R> Object adapt(Call<R> call) {
        Observable<?> observable;
        Observable<Response<R>> responseObservable = new CallObservable(call);
        if (this.isResult) {
            observable = new ResultObservable(responseObservable);
        } else if (this.isBody) {
            observable = new BodyObservable(responseObservable);
        } else {
            observable = responseObservable;
        }
        if (this.scheduler != null) {
            observable = observable.subscribeOn(this.scheduler);
        }
        if (this.isFlowable) {
            return observable.toFlowable(BackpressureStrategy.LATEST);
        }
        if (this.isSingle) {
            return observable.singleOrError();
        }
        if (this.isMaybe) {
            return observable.singleElement();
        }
        if (this.isCompletable) {
            return observable.ignoreElements();
        }
        return observable;
    }
}
