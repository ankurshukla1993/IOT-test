package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;

public final class ObservableElementAtSingle<T> extends Single<T> implements FuseToObservable<T> {
    final T defaultValue;
    final long index;
    final ObservableSource<T> source;

    static final class ElementAtObserver<T> implements Observer<T>, Disposable {
        final SingleObserver<? super T> actual;
        long count;
        final T defaultValue;
        boolean done;
        final long index;
        Disposable f2691s;

        ElementAtObserver(SingleObserver<? super T> actual, long index, T defaultValue) {
            this.actual = actual;
            this.index = index;
            this.defaultValue = defaultValue;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2691s, s)) {
                this.f2691s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2691s.dispose();
        }

        public boolean isDisposed() {
            return this.f2691s.isDisposed();
        }

        public void onNext(T t) {
            if (!this.done) {
                long c = this.count;
                if (c == this.index) {
                    this.done = true;
                    this.f2691s.dispose();
                    this.actual.onSuccess(t);
                    return;
                }
                this.count = 1 + c;
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                T v = this.defaultValue;
                if (v != null) {
                    this.actual.onSuccess(v);
                } else {
                    this.actual.onError(new NoSuchElementException());
                }
            }
        }
    }

    public ObservableElementAtSingle(ObservableSource<T> source, long index, T defaultValue) {
        this.source = source;
        this.index = index;
        this.defaultValue = defaultValue;
    }

    public void subscribeActual(SingleObserver<? super T> t) {
        this.source.subscribe(new ElementAtObserver(t, this.index, this.defaultValue));
    }

    public Observable<T> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableElementAt(this.source, this.index, this.defaultValue, true));
    }
}
