package com.jakewharton.retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Response;

final class CallObservable<T> extends Observable<Response<T>> {
    private final Call<T> originalCall;

    private static final class CallDisposable implements Disposable {
        private final Call<?> call;

        CallDisposable(Call<?> call) {
            this.call = call;
        }

        public void dispose() {
            this.call.cancel();
        }

        public boolean isDisposed() {
            return this.call.isCanceled();
        }
    }

    CallObservable(Call<T> originalCall) {
        this.originalCall = originalCall;
    }

    protected void subscribeActual(Observer<? super Response<T>> observer) {
        Call<T> call = this.originalCall.clone();
        observer.onSubscribe(new CallDisposable(call));
        try {
            Response<T> response = call.execute();
            if (!call.isCanceled()) {
                observer.onNext(response);
            }
            if (!call.isCanceled()) {
                observer.onComplete();
            }
        } catch (Throwable inner) {
            Exceptions.throwIfFatal(inner);
            RxJavaPlugins.onError(new CompositeException(t, inner));
        }
    }
}
