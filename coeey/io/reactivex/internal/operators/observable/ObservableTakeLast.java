package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.ArrayDeque;

public final class ObservableTakeLast<T> extends AbstractObservableWithUpstream<T, T> {
    final int count;

    static final class TakeLastObserver<T> extends ArrayDeque<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = 7240042530241604978L;
        final Observer<? super T> actual;
        volatile boolean cancelled;
        final int count;
        Disposable f2731s;

        TakeLastObserver(Observer<? super T> actual, int count) {
            this.actual = actual;
            this.count = count;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2731s, s)) {
                this.f2731s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.count == size()) {
                poll();
            }
            offer(t);
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            Observer<? super T> a = this.actual;
            while (!this.cancelled) {
                T v = poll();
                if (v != null) {
                    a.onNext(v);
                } else if (!this.cancelled) {
                    a.onComplete();
                    return;
                } else {
                    return;
                }
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2731s.dispose();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }
    }

    public ObservableTakeLast(ObservableSource<T> source, int count) {
        super(source);
        this.count = count;
    }

    public void subscribeActual(Observer<? super T> t) {
        this.source.subscribe(new TakeLastObserver(t, this.count));
    }
}
