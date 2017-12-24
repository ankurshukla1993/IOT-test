package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;

@Experimental
public final class SingleDoFinally<T> extends Single<T> {
    final Action onFinally;
    final SingleSource<T> source;

    static final class DoFinallyObserver<T> extends AtomicInteger implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = 4109457741734051389L;
        final SingleObserver<? super T> actual;
        Disposable f2784d;
        final Action onFinally;

        DoFinallyObserver(SingleObserver<? super T> actual, Action onFinally) {
            this.actual = actual;
            this.onFinally = onFinally;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2784d, d)) {
                this.f2784d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(t);
            runFinally();
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
            runFinally();
        }

        public void dispose() {
            this.f2784d.dispose();
            runFinally();
        }

        public boolean isDisposed() {
            return this.f2784d.isDisposed();
        }

        void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.run();
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    RxJavaPlugins.onError(ex);
                }
            }
        }
    }

    public SingleDoFinally(SingleSource<T> source, Action onFinally) {
        this.source = source;
        this.onFinally = onFinally;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        this.source.subscribe(new DoFinallyObserver(s, this.onFinally));
    }
}
