package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;

public final class DisposableLambdaObserver<T> implements Observer<T>, Disposable {
    final Observer<? super T> actual;
    final Action onDispose;
    final Consumer<? super Disposable> onSubscribe;
    Disposable f2484s;

    public DisposableLambdaObserver(Observer<? super T> actual, Consumer<? super Disposable> onSubscribe, Action onDispose) {
        this.actual = actual;
        this.onSubscribe = onSubscribe;
        this.onDispose = onDispose;
    }

    public void onSubscribe(Disposable s) {
        try {
            this.onSubscribe.accept(s);
            if (DisposableHelper.validate(this.f2484s, s)) {
                this.f2484s = s;
                this.actual.onSubscribe(this);
            }
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            s.dispose();
            this.f2484s = DisposableHelper.DISPOSED;
            EmptyDisposable.error(e, this.actual);
        }
    }

    public void onNext(T t) {
        this.actual.onNext(t);
    }

    public void onError(Throwable t) {
        if (this.f2484s != DisposableHelper.DISPOSED) {
            this.actual.onError(t);
        } else {
            RxJavaPlugins.onError(t);
        }
    }

    public void onComplete() {
        if (this.f2484s != DisposableHelper.DISPOSED) {
            this.actual.onComplete();
        }
    }

    public void dispose() {
        try {
            this.onDispose.run();
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            RxJavaPlugins.onError(e);
        }
        this.f2484s.dispose();
    }

    public boolean isDisposed() {
        return this.f2484s.isDisposed();
    }
}
