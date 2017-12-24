package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeCallbackObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
    private static final long serialVersionUID = -6076952298809384986L;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onSuccess;

    public MaybeCallbackObserver(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError, Action onComplete) {
        this.onSuccess = onSuccess;
        this.onError = onError;
        this.onComplete = onComplete;
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed((Disposable) get());
    }

    public void onSubscribe(Disposable d) {
        DisposableHelper.setOnce(this, d);
    }

    public void onSuccess(T value) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onSuccess.accept(value);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            RxJavaPlugins.onError(ex);
        }
    }

    public void onError(Throwable e) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onError.accept(e);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            RxJavaPlugins.onError(new CompositeException(e, ex));
        }
    }

    public void onComplete() {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onComplete.run();
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            RxJavaPlugins.onError(ex);
        }
    }
}
