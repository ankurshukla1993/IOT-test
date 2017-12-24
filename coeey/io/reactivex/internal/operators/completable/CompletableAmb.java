package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

public final class CompletableAmb extends Completable {
    private final CompletableSource[] sources;
    private final Iterable<? extends CompletableSource> sourcesIterable;

    static final class Amb implements CompletableObserver {
        private final AtomicBoolean once;
        private final CompletableObserver f2489s;
        private final CompositeDisposable set;

        Amb(AtomicBoolean once, CompositeDisposable set, CompletableObserver s) {
            this.once = once;
            this.set = set;
            this.f2489s = s;
        }

        public void onComplete() {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.f2489s.onComplete();
            }
        }

        public void onError(Throwable e) {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.f2489s.onError(e);
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onSubscribe(Disposable d) {
            this.set.add(d);
        }
    }

    public CompletableAmb(CompletableSource[] sources, Iterable<? extends CompletableSource> sourcesIterable) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
    }

    public void subscribeActual(CompletableObserver s) {
        int i;
        Throwable e;
        CompletableSource[] sources = this.sources;
        if (sources == null) {
            sources = new CompletableSource[8];
            try {
                int count = 0;
                for (CompletableSource element : this.sourcesIterable) {
                    try {
                        if (element == null) {
                            EmptyDisposable.error(new NullPointerException("One of the sources is null"), s);
                            i = count;
                            return;
                        }
                        if (count == sources.length) {
                            CompletableSource[] b = new CompletableSource[((count >> 2) + count)];
                            System.arraycopy(sources, 0, b, 0, count);
                            sources = b;
                        }
                        i = count + 1;
                        sources[count] = element;
                        count = i;
                    } catch (Throwable th) {
                        e = th;
                        i = count;
                    }
                }
                i = count;
            } catch (Throwable th2) {
                e = th2;
            }
        } else {
            i = sources.length;
        }
        CompositeDisposable set = new CompositeDisposable();
        s.onSubscribe(set);
        AtomicBoolean once = new AtomicBoolean();
        CompletableObserver inner = new Amb(once, set, s);
        int i2 = 0;
        while (i2 < i) {
            CompletableSource c = sources[i2];
            if (!set.isDisposed()) {
                if (c == null) {
                    NullPointerException npe = new NullPointerException("One of the sources is null");
                    if (once.compareAndSet(false, true)) {
                        set.dispose();
                        s.onError(npe);
                        return;
                    }
                    RxJavaPlugins.onError(npe);
                    return;
                }
                c.subscribe(inner);
                i2++;
            } else {
                return;
            }
        }
        if (i == 0) {
            s.onComplete();
            return;
        }
        return;
        Exceptions.throwIfFatal(e);
        EmptyDisposable.error(e, s);
    }
}
