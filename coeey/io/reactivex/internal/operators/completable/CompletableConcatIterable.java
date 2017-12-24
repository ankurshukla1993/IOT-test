package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class CompletableConcatIterable extends Completable {
    final Iterable<? extends CompletableSource> sources;

    static final class ConcatInnerObserver extends AtomicInteger implements CompletableObserver {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableObserver actual;
        final SequentialDisposable sd = new SequentialDisposable();
        final Iterator<? extends CompletableSource> sources;

        ConcatInnerObserver(CompletableObserver actual, Iterator<? extends CompletableSource> sources) {
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
                Iterator<? extends CompletableSource> a = this.sources;
                while (!this.sd.isDisposed()) {
                    try {
                        if (a.hasNext()) {
                            try {
                                ((CompletableSource) ObjectHelper.requireNonNull(a.next(), "The CompletableSource returned is null")).subscribe(this);
                                if (decrementAndGet() == 0) {
                                    return;
                                }
                            } catch (Throwable ex) {
                                Exceptions.throwIfFatal(ex);
                                this.actual.onError(ex);
                                return;
                            }
                        }
                        this.actual.onComplete();
                        return;
                    } catch (Throwable ex2) {
                        Exceptions.throwIfFatal(ex2);
                        this.actual.onError(ex2);
                        return;
                    }
                }
            }
        }
    }

    public CompletableConcatIterable(Iterable<? extends CompletableSource> sources) {
        this.sources = sources;
    }

    public void subscribeActual(CompletableObserver s) {
        try {
            ConcatInnerObserver inner = new ConcatInnerObserver(s, (Iterator) ObjectHelper.requireNonNull(this.sources.iterator(), "The iterator returned is null"));
            s.onSubscribe(inner.sd);
            inner.next();
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            EmptyDisposable.error(e, s);
        }
    }
}
