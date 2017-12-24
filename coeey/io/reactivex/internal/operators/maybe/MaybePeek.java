package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;

public final class MaybePeek<T> extends AbstractMaybeWithUpstream<T, T> {
    final Action onAfterTerminate;
    final Action onCompleteCall;
    final Action onDisposeCall;
    final Consumer<? super Throwable> onErrorCall;
    final Consumer<? super Disposable> onSubscribeCall;
    final Consumer<? super T> onSuccessCall;

    static final class MaybePeekObserver<T> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2648d;
        final MaybePeek<T> parent;

        MaybePeekObserver(MaybeObserver<? super T> actual, MaybePeek<T> parent) {
            this.actual = actual;
            this.parent = parent;
        }

        public void dispose() {
            try {
                this.parent.onDisposeCall.run();
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                RxJavaPlugins.onError(ex);
            }
            this.f2648d.dispose();
            this.f2648d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2648d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2648d, d)) {
                try {
                    this.parent.onSubscribeCall.accept(d);
                    this.f2648d = d;
                    this.actual.onSubscribe(this);
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    d.dispose();
                    this.f2648d = DisposableHelper.DISPOSED;
                    EmptyDisposable.error(ex, this.actual);
                }
            }
        }

        public void onSuccess(T value) {
            if (this.f2648d != DisposableHelper.DISPOSED) {
                try {
                    this.parent.onSuccessCall.accept(value);
                    this.f2648d = DisposableHelper.DISPOSED;
                    this.actual.onSuccess(value);
                    onAfterTerminate();
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    onErrorInner(ex);
                }
            }
        }

        public void onError(Throwable e) {
            if (this.f2648d == DisposableHelper.DISPOSED) {
                RxJavaPlugins.onError(e);
            } else {
                onErrorInner(e);
            }
        }

        void onErrorInner(Throwable e) {
            try {
                this.parent.onErrorCall.accept(e);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                e = new CompositeException(e, ex);
            }
            this.f2648d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
            onAfterTerminate();
        }

        public void onComplete() {
            if (this.f2648d != DisposableHelper.DISPOSED) {
                try {
                    this.parent.onCompleteCall.run();
                    this.f2648d = DisposableHelper.DISPOSED;
                    this.actual.onComplete();
                    onAfterTerminate();
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    onErrorInner(ex);
                }
            }
        }

        void onAfterTerminate() {
            try {
                this.parent.onAfterTerminate.run();
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                RxJavaPlugins.onError(ex);
            }
        }
    }

    public MaybePeek(MaybeSource<T> source, Consumer<? super Disposable> onSubscribeCall, Consumer<? super T> onSuccessCall, Consumer<? super Throwable> onErrorCall, Action onCompleteCall, Action onAfterTerminate, Action onDispose) {
        super(source);
        this.onSubscribeCall = onSubscribeCall;
        this.onSuccessCall = onSuccessCall;
        this.onErrorCall = onErrorCall;
        this.onCompleteCall = onCompleteCall;
        this.onAfterTerminate = onAfterTerminate;
        this.onDisposeCall = onDispose;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new MaybePeekObserver(observer, this));
    }
}
