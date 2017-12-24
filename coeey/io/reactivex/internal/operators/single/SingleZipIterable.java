package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.Arrays;
import java.util.NoSuchElementException;

public final class SingleZipIterable<T, R> extends Single<R> {
    final Iterable<? extends SingleSource<? extends T>> sources;
    final Function<? super Object[], ? extends R> zipper;

    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        public R apply(T t) throws Exception {
            return ObjectHelper.requireNonNull(SingleZipIterable.this.zipper.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }

    public SingleZipIterable(Iterable<? extends SingleSource<? extends T>> sources, Function<? super Object[], ? extends R> zipper) {
        this.sources = sources;
        this.zipper = zipper;
    }

    protected void subscribeActual(SingleObserver<? super R> observer) {
        Throwable ex;
        SingleSource<? extends T>[] a = new SingleSource[8];
        try {
            int i;
            int n = 0;
            for (SingleSource<? extends T> source : this.sources) {
                try {
                    if (source == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), (SingleObserver) observer);
                        i = n;
                        return;
                    }
                    if (n == a.length) {
                        a = (SingleSource[]) Arrays.copyOf(a, (n >> 2) + n);
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
                EmptyDisposable.error(new NoSuchElementException(), (SingleObserver) observer);
                i = n;
                return;
            } else if (n == 1) {
                a[0].subscribe(new MapSingleObserver(observer, new SingletonArrayFunc()));
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
        EmptyDisposable.error(ex, (SingleObserver) observer);
    }
}
