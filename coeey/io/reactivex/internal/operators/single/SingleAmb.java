package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SingleAmb<T> extends Single<T> {
    private final SingleSource<? extends T>[] sources;
    private final Iterable<? extends SingleSource<? extends T>> sourcesIterable;

    static final class AmbSingleObserver<T> extends AtomicBoolean implements SingleObserver<T> {
        private static final long serialVersionUID = -1944085461036028108L;
        final SingleObserver<? super T> f2777s;
        final CompositeDisposable set;

        AmbSingleObserver(SingleObserver<? super T> s, CompositeDisposable set) {
            this.f2777s = s;
            this.set = set;
        }

        public void onSubscribe(Disposable d) {
            this.set.add(d);
        }

        public void onSuccess(T value) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.f2777s.onSuccess(value);
            }
        }

        public void onError(Throwable e) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.f2777s.onError(e);
                return;
            }
            RxJavaPlugins.onError(e);
        }
    }

    public SingleAmb(SingleSource<? extends T>[] sources, Iterable<? extends SingleSource<? extends T>> sourcesIterable) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        int i;
        Throwable e;
        SingleSource<? extends T>[] sources = this.sources;
        if (sources == null) {
            sources = new SingleSource[8];
            try {
                int count = 0;
                for (SingleSource<? extends T> element : this.sourcesIterable) {
                    try {
                        if (element == null) {
                            EmptyDisposable.error(new NullPointerException("One of the sources is null"), (SingleObserver) s);
                            i = count;
                            return;
                        }
                        if (count == sources.length) {
                            SingleSource<? extends T>[] b = new SingleSource[((count >> 2) + count)];
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
        AmbSingleObserver<T> shared = new AmbSingleObserver(s, set);
        s.onSubscribe(set);
        int i2 = 0;
        while (i2 < i) {
            SingleSource<? extends T> s1 = sources[i2];
            if (!shared.get()) {
                if (s1 == null) {
                    set.dispose();
                    e = new NullPointerException("One of the sources is null");
                    if (shared.compareAndSet(false, true)) {
                        s.onError(e);
                        return;
                    } else {
                        RxJavaPlugins.onError(e);
                        return;
                    }
                }
                s1.subscribe(shared);
                i2++;
            } else {
                return;
            }
        }
        return;
        Exceptions.throwIfFatal(e);
        EmptyDisposable.error(e, (SingleObserver) s);
    }
}
