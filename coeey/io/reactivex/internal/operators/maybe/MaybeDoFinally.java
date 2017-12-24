package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;

@Experimental
public final class MaybeDoFinally<T> extends AbstractMaybeWithUpstream<T, T> {
    final Action onFinally;

    static final class DoFinallyObserver<T> extends AtomicInteger implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4109457741734051389L;
        final MaybeObserver<? super T> actual;
        Disposable f2629d;
        final Action onFinally;

        DoFinallyObserver(MaybeObserver<? super T> actual, Action onFinally) {
            this.actual = actual;
            this.onFinally = onFinally;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2629d, d)) {
                this.f2629d = d;
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

        public void onComplete() {
            this.actual.onComplete();
            runFinally();
        }

        public void dispose() {
            this.f2629d.dispose();
            runFinally();
        }

        public boolean isDisposed() {
            return this.f2629d.isDisposed();
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

    public MaybeDoFinally(MaybeSource<T> source, Action onFinally) {
        super(source);
        this.onFinally = onFinally;
    }

    protected void subscribeActual(MaybeObserver<? super T> s) {
        this.source.subscribe(new DoFinallyObserver(s, this.onFinally));
    }
}
