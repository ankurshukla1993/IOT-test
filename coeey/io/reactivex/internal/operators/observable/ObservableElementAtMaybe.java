package io.reactivex.internal.operators.observable;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableElementAtMaybe<T> extends Maybe<T> implements FuseToObservable<T> {
    final long index;
    final ObservableSource<T> source;

    static final class ElementAtObserver<T> implements Observer<T>, Disposable {
        final MaybeObserver<? super T> actual;
        long count;
        boolean done;
        final long index;
        Disposable f2690s;

        ElementAtObserver(MaybeObserver<? super T> actual, long index) {
            this.actual = actual;
            this.index = index;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2690s, s)) {
                this.f2690s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2690s.dispose();
        }

        public boolean isDisposed() {
            return this.f2690s.isDisposed();
        }

        public void onNext(T t) {
            if (!this.done) {
                long c = this.count;
                if (c == this.index) {
                    this.done = true;
                    this.f2690s.dispose();
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
                this.actual.onComplete();
            }
        }
    }

    public ObservableElementAtMaybe(ObservableSource<T> source, long index) {
        this.source = source;
        this.index = index;
    }

    public void subscribeActual(MaybeObserver<? super T> t) {
        this.source.subscribe(new ElementAtObserver(t, this.index));
    }

    public Observable<T> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableElementAt(this.source, this.index, null, false));
    }
}
