package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.ArrayDeque;

public final class ObservableSkipLast<T> extends AbstractObservableWithUpstream<T, T> {
    final int skip;

    static final class SkipLastObserver<T> extends ArrayDeque<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -3807491841935125653L;
        final Observer<? super T> actual;
        Disposable f2724s;
        final int skip;

        SkipLastObserver(Observer<? super T> actual, int skip) {
            super(skip);
            this.actual = actual;
            this.skip = skip;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2724s, s)) {
                this.f2724s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2724s.dispose();
        }

        public boolean isDisposed() {
            return this.f2724s.isDisposed();
        }

        public void onNext(T t) {
            if (this.skip == size()) {
                this.actual.onNext(poll());
            }
            offer(t);
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    public ObservableSkipLast(ObservableSource<T> source, int skip) {
        super(source);
        this.skip = skip;
    }

    public void subscribeActual(Observer<? super T> s) {
        this.source.subscribe(new SkipLastObserver(s, this.skip));
    }
}
