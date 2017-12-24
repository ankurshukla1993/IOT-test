package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;

public final class ObservableZipIterable<T, U, V> extends Observable<V> {
    final Iterable<U> other;
    final Observable<? extends T> source;
    final BiFunction<? super T, ? super U, ? extends V> zipper;

    static final class ZipIterableObserver<T, U, V> implements Observer<T>, Disposable {
        final Observer<? super V> actual;
        boolean done;
        final Iterator<U> iterator;
        Disposable f2762s;
        final BiFunction<? super T, ? super U, ? extends V> zipper;

        ZipIterableObserver(Observer<? super V> actual, Iterator<U> iterator, BiFunction<? super T, ? super U, ? extends V> zipper) {
            this.actual = actual;
            this.iterator = iterator;
            this.zipper = zipper;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2762s, s)) {
                this.f2762s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2762s.dispose();
        }

        public boolean isDisposed() {
            return this.f2762s.isDisposed();
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    try {
                        this.actual.onNext(ObjectHelper.requireNonNull(this.zipper.apply(t, ObjectHelper.requireNonNull(this.iterator.next(), "The iterator returned a null value")), "The zipper function returned a null value"));
                        try {
                            if (!this.iterator.hasNext()) {
                                this.done = true;
                                this.f2762s.dispose();
                                this.actual.onComplete();
                            }
                        } catch (Throwable e) {
                            Exceptions.throwIfFatal(e);
                            error(e);
                        }
                    } catch (Throwable e2) {
                        Exceptions.throwIfFatal(e2);
                        error(e2);
                    }
                } catch (Throwable e22) {
                    Exceptions.throwIfFatal(e22);
                    error(e22);
                }
            }
        }

        void error(Throwable e) {
            this.done = true;
            this.f2762s.dispose();
            this.actual.onError(e);
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

    public ObservableZipIterable(Observable<? extends T> source, Iterable<U> other, BiFunction<? super T, ? super U, ? extends V> zipper) {
        this.source = source;
        this.other = other;
        this.zipper = zipper;
    }

    public void subscribeActual(Observer<? super V> t) {
        try {
            Iterator<U> it = (Iterator) ObjectHelper.requireNonNull(this.other.iterator(), "The iterator returned by other is null");
            try {
                if (it.hasNext()) {
                    this.source.subscribe(new ZipIterableObserver(t, it, this.zipper));
                } else {
                    EmptyDisposable.complete((Observer) t);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                EmptyDisposable.error(e, (Observer) t);
            }
        } catch (Throwable e2) {
            Exceptions.throwIfFatal(e2);
            EmptyDisposable.error(e2, (Observer) t);
        }
    }
}
