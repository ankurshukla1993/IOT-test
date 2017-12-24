package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;

public final class ObservableOnErrorReturn<T> extends AbstractObservableWithUpstream<T, T> {
    final Function<? super Throwable, ? extends T> valueSupplier;

    static final class OnErrorReturnObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        Disposable f2709s;
        final Function<? super Throwable, ? extends T> valueSupplier;

        OnErrorReturnObserver(Observer<? super T> actual, Function<? super Throwable, ? extends T> valueSupplier) {
            this.actual = actual;
            this.valueSupplier = valueSupplier;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2709s, s)) {
                this.f2709s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2709s.dispose();
        }

        public boolean isDisposed() {
            return this.f2709s.isDisposed();
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable t) {
            try {
                T v = this.valueSupplier.apply(t);
                if (v == null) {
                    NullPointerException e = new NullPointerException("The supplied value is null");
                    e.initCause(t);
                    this.actual.onError(e);
                    return;
                }
                this.actual.onNext(v);
                this.actual.onComplete();
            } catch (Throwable e2) {
                Exceptions.throwIfFatal(e2);
                this.actual.onError(new CompositeException(t, e2));
            }
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    public ObservableOnErrorReturn(ObservableSource<T> source, Function<? super Throwable, ? extends T> valueSupplier) {
        super(source);
        this.valueSupplier = valueSupplier;
    }

    public void subscribeActual(Observer<? super T> t) {
        this.source.subscribe(new OnErrorReturnObserver(t, this.valueSupplier));
    }
}
