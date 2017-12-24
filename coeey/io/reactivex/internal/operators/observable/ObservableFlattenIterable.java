package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableFlattenIterable<T, R> extends AbstractObservableWithUpstream<T, R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;

    static final class FlattenIterableObserver<T, R> implements Observer<T>, Disposable {
        final Observer<? super R> actual;
        Disposable f2697d;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;

        FlattenIterableObserver(Observer<? super R> actual, Function<? super T, ? extends Iterable<? extends R>> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2697d, d)) {
                this.f2697d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T value) {
            if (this.f2697d != DisposableHelper.DISPOSED) {
                try {
                    Observer<? super R> a = this.actual;
                    for (Object requireNonNull : (Iterable) this.mapper.apply(value)) {
                        try {
                            try {
                                a.onNext(ObjectHelper.requireNonNull(requireNonNull, "The iterator returned a null value"));
                            } catch (Throwable ex) {
                                Exceptions.throwIfFatal(ex);
                                this.f2697d.dispose();
                                onError(ex);
                                return;
                            }
                        } catch (Throwable ex2) {
                            Exceptions.throwIfFatal(ex2);
                            this.f2697d.dispose();
                            onError(ex2);
                            return;
                        }
                    }
                } catch (Throwable ex22) {
                    Exceptions.throwIfFatal(ex22);
                    this.f2697d.dispose();
                    onError(ex22);
                }
            }
        }

        public void onError(Throwable e) {
            if (this.f2697d == DisposableHelper.DISPOSED) {
                RxJavaPlugins.onError(e);
                return;
            }
            this.f2697d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
        }

        public void onComplete() {
            if (this.f2697d != DisposableHelper.DISPOSED) {
                this.f2697d = DisposableHelper.DISPOSED;
                this.actual.onComplete();
            }
        }

        public boolean isDisposed() {
            return this.f2697d.isDisposed();
        }

        public void dispose() {
            this.f2697d.dispose();
            this.f2697d = DisposableHelper.DISPOSED;
        }
    }

    public ObservableFlattenIterable(ObservableSource<T> source, Function<? super T, ? extends Iterable<? extends R>> mapper) {
        super(source);
        this.mapper = mapper;
    }

    protected void subscribeActual(Observer<? super R> observer) {
        this.source.subscribe(new FlattenIterableObserver(observer, this.mapper));
    }
}
