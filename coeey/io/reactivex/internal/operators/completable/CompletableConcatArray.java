package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.atomic.AtomicInteger;

public final class CompletableConcatArray extends Completable {
    final CompletableSource[] sources;

    static final class ConcatInnerObserver extends AtomicInteger implements CompletableObserver {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableObserver actual;
        int index;
        final SequentialDisposable sd = new SequentialDisposable();
        final CompletableSource[] sources;

        ConcatInnerObserver(CompletableObserver actual, CompletableSource[] sources) {
            this.actual = actual;
            this.sources = sources;
        }

        public void onSubscribe(Disposable d) {
            this.sd.update(d);
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void onComplete() {
            next();
        }

        void next() {
            if (!this.sd.isDisposed() && getAndIncrement() == 0) {
                CompletableSource[] a = this.sources;
                while (!this.sd.isDisposed()) {
                    int idx = this.index;
                    this.index = idx + 1;
                    if (idx == a.length) {
                        this.actual.onComplete();
                        return;
                    }
                    a[idx].subscribe(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    public CompletableConcatArray(CompletableSource[] sources) {
        this.sources = sources;
    }

    public void subscribeActual(CompletableObserver s) {
        ConcatInnerObserver inner = new ConcatInnerObserver(s, this.sources);
        s.onSubscribe(inner.sd);
        inner.next();
    }
}
