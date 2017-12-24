package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

@Experimental
public final class MaybeDoAfterSuccess<T> extends AbstractMaybeWithUpstream<T, T> {
    final Consumer<? super T> onAfterSuccess;

    static final class DoAfterObserver<T> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2628d;
        final Consumer<? super T> onAfterSuccess;

        DoAfterObserver(MaybeObserver<? super T> actual, Consumer<? super T> onAfterSuccess) {
            this.actual = actual;
            this.onAfterSuccess = onAfterSuccess;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2628d, d)) {
                this.f2628d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(t);
            try {
                this.onAfterSuccess.accept(t);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                RxJavaPlugins.onError(ex);
            }
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void dispose() {
            this.f2628d.dispose();
        }

        public boolean isDisposed() {
            return this.f2628d.isDisposed();
        }
    }

    public MaybeDoAfterSuccess(MaybeSource<T> source, Consumer<? super T> onAfterSuccess) {
        super(source);
        this.onAfterSuccess = onAfterSuccess;
    }

    protected void subscribeActual(MaybeObserver<? super T> s) {
        this.source.subscribe(new DoAfterObserver(s, this.onAfterSuccess));
    }
}
