package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

public final class MaybeAmb<T> extends Maybe<T> {
    private final MaybeSource<? extends T>[] sources;
    private final Iterable<? extends MaybeSource<? extends T>> sourcesIterable;

    static final class AmbMaybeObserver<T> extends AtomicBoolean implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -7044685185359438206L;
        final MaybeObserver<? super T> actual;
        final CompositeDisposable set = new CompositeDisposable();

        AmbMaybeObserver(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.set.dispose();
            }
        }

        public boolean isDisposed() {
            return get();
        }

        public void onSubscribe(Disposable d) {
            this.set.add(d);
        }

        public void onSuccess(T value) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.actual.onSuccess(value);
            }
        }

        public void onError(Throwable e) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.actual.onError(e);
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.actual.onComplete();
            }
        }
    }

    public MaybeAmb(MaybeSource<? extends T>[] sources, Iterable<? extends MaybeSource<? extends T>> sourcesIterable) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        int i;
        Throwable e;
        MaybeSource<? extends T>[] sources = this.sources;
        if (sources == null) {
            sources = new MaybeSource[8];
            try {
                int count = 0;
                for (MaybeSource<? extends T> element : this.sourcesIterable) {
                    try {
                        if (element == null) {
                            EmptyDisposable.error(new NullPointerException("One of the sources is null"), (MaybeObserver) observer);
                            i = count;
                            return;
                        }
                        if (count == sources.length) {
                            MaybeSource<? extends T>[] b = new MaybeSource[((count >> 2) + count)];
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
        AmbMaybeObserver<T> parent = new AmbMaybeObserver(observer);
        observer.onSubscribe(parent);
        int i2 = 0;
        while (i2 < i) {
            MaybeSource<? extends T> s = sources[i2];
            if (!parent.isDisposed()) {
                if (s == null) {
                    parent.onError(new NullPointerException("One of the MaybeSources is null"));
                    return;
                } else {
                    s.subscribe(parent);
                    i2++;
                }
            } else {
                return;
            }
        }
        if (i == 0) {
            observer.onComplete();
            return;
        }
        return;
        Exceptions.throwIfFatal(e);
        EmptyDisposable.error(e, (MaybeObserver) observer);
    }
}
