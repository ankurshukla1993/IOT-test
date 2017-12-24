package com.jakewharton.retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Response;

final class ResultObservable<T> extends Observable<Result<T>> {
    private final Observable<Response<T>> upstream;

    private static class ResultObserver<R> implements Observer<Response<R>> {
        private final Observer<? super Result<R>> observer;

        ResultObserver(Observer<? super Result<R>> observer) {
            this.observer = observer;
        }

        public void onSubscribe(Disposable disposable) {
            this.observer.onSubscribe(disposable);
        }

        public void onNext(Response<R> response) {
            this.observer.onNext(Result.response(response));
        }

        public void onError(Throwable throwable) {
            try {
                this.observer.onNext(Result.error(throwable));
                this.observer.onComplete();
            } catch (Throwable inner) {
                Exceptions.throwIfFatal(inner);
                RxJavaPlugins.onError(new CompositeException(t, inner));
            }
        }

        public void onComplete() {
            this.observer.onComplete();
        }
    }

    ResultObservable(Observable<Response<T>> upstream) {
        this.upstream = upstream;
    }

    protected void subscribeActual(Observer<? super Result<T>> observer) {
        this.upstream.subscribe(new ResultObserver(observer));
    }
}
