package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.ResumeSingleObserver;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleResumeNext<T> extends Single<T> {
    final Function<? super Throwable, ? extends SingleSource<? extends T>> nextFunction;
    final SingleSource<? extends T> source;

    static final class ResumeMainSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -5314538511045349925L;
        final SingleObserver<? super T> actual;
        final Function<? super Throwable, ? extends SingleSource<? extends T>> nextFunction;

        ResumeMainSingleObserver(SingleObserver<? super T> actual, Function<? super Throwable, ? extends SingleSource<? extends T>> nextFunction) {
            this.actual = actual;
            this.nextFunction = nextFunction;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.setOnce(this, d)) {
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.actual.onSuccess(value);
        }

        public void onError(Throwable e) {
            try {
                ((SingleSource) ObjectHelper.requireNonNull(this.nextFunction.apply(e), "The nextFunction returned a null SingleSource.")).subscribe(new ResumeSingleObserver(this, this.actual));
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(new CompositeException(e, ex));
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    public SingleResumeNext(SingleSource<? extends T> source, Function<? super Throwable, ? extends SingleSource<? extends T>> nextFunction) {
        this.source = source;
        this.nextFunction = nextFunction;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        this.source.subscribe(new ResumeMainSingleObserver(s, this.nextFunction));
    }
}
