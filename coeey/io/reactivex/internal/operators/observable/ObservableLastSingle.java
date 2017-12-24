package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.NoSuchElementException;

public final class ObservableLastSingle<T> extends Single<T> {
    final T defaultItem;
    final ObservableSource<T> source;

    static final class LastObserver<T> implements Observer<T>, Disposable {
        final SingleObserver<? super T> actual;
        final T defaultItem;
        T item;
        Disposable f2705s;

        LastObserver(SingleObserver<? super T> actual, T defaultItem) {
            this.actual = actual;
            this.defaultItem = defaultItem;
        }

        public void dispose() {
            this.f2705s.dispose();
            this.f2705s = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2705s == DisposableHelper.DISPOSED;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2705s, s)) {
                this.f2705s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.item = t;
        }

        public void onError(Throwable t) {
            this.f2705s = DisposableHelper.DISPOSED;
            this.item = null;
            this.actual.onError(t);
        }

        public void onComplete() {
            this.f2705s = DisposableHelper.DISPOSED;
            T v = this.item;
            if (v != null) {
                this.item = null;
                this.actual.onSuccess(v);
                return;
            }
            v = this.defaultItem;
            if (v != null) {
                this.actual.onSuccess(v);
            } else {
                this.actual.onError(new NoSuchElementException());
            }
        }
    }

    public ObservableLastSingle(ObservableSource<T> source, T defaultItem) {
        this.source = source;
        this.defaultItem = defaultItem;
    }

    protected void subscribeActual(SingleObserver<? super T> observer) {
        this.source.subscribe(new LastObserver(observer, this.defaultItem));
    }
}
