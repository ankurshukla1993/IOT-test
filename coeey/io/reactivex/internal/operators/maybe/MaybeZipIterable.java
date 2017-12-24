package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.Arrays;

public final class MaybeZipIterable<T, R> extends Maybe<R> {
    final Iterable<? extends MaybeSource<? extends T>> sources;
    final Function<? super Object[], ? extends R> zipper;

    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        public R apply(T t) throws Exception {
            return ObjectHelper.requireNonNull(MaybeZipIterable.this.zipper.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }

    public MaybeZipIterable(Iterable<? extends MaybeSource<? extends T>> sources, Function<? super Object[], ? extends R> zipper) {
        this.sources = sources;
        this.zipper = zipper;
    }

    protected void subscribeActual(MaybeObserver<? super R> observer) {
        Throwable ex;
        MaybeSource<? extends T>[] a = new MaybeSource[8];
        try {
            int i;
            int n = 0;
            for (MaybeSource<? extends T> source : this.sources) {
                try {
                    if (source == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), (MaybeObserver) observer);
                        i = n;
                        return;
                    }
                    if (n == a.length) {
                        a = (MaybeSource[]) Arrays.copyOf(a, (n >> 2) + n);
                    }
                    i = n + 1;
                    a[n] = source;
                    n = i;
                } catch (Throwable th) {
                    ex = th;
                    i = n;
                }
            }
            if (n == 0) {
                EmptyDisposable.complete((MaybeObserver) observer);
                i = n;
                return;
            } else if (n == 1) {
                a[0].subscribe(new MapMaybeObserver(observer, new SingletonArrayFunc()));
                i = n;
                return;
            } else {
                ZipCoordinator<T, R> parent = new ZipCoordinator(observer, n, this.zipper);
                observer.onSubscribe(parent);
                int i2 = 0;
                while (i2 < n) {
                    if (parent.isDisposed()) {
                        i = n;
                        return;
                    } else {
                        a[i2].subscribe(parent.observers[i2]);
                        i2++;
                    }
                }
                i = n;
                return;
            }
        } catch (Throwable th2) {
            ex = th2;
        }
        Exceptions.throwIfFatal(ex);
        EmptyDisposable.error(ex, (MaybeObserver) observer);
    }
}
