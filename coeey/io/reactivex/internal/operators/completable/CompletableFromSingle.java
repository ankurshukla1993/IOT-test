package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;

public final class CompletableFromSingle<T> extends Completable {
    final SingleSource<T> single;

    static final class CompletableFromSingleObserver<T> implements SingleObserver<T> {
        final CompletableObserver co;

        CompletableFromSingleObserver(CompletableObserver co) {
            this.co = co;
        }

        public void onError(Throwable e) {
            this.co.onError(e);
        }

        public void onSubscribe(Disposable d) {
            this.co.onSubscribe(d);
        }

        public void onSuccess(T t) {
            this.co.onComplete();
        }
    }

    public CompletableFromSingle(SingleSource<T> single) {
        this.single = single;
    }

    protected void subscribeActual(CompletableObserver s) {
        this.single.subscribe(new CompletableFromSingleObserver(s));
    }
}
