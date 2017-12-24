package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;

public final class CompletablePeek extends Completable {
    final Action onAfterTerminate;
    final Action onComplete;
    final Action onDispose;
    final Consumer<? super Throwable> onError;
    final Consumer<? super Disposable> onSubscribe;
    final Action onTerminate;
    final CompletableSource source;

    final class CompletableObserverImplementation implements CompletableObserver, Disposable {
        final CompletableObserver actual;
        Disposable f2500d;

        CompletableObserverImplementation(CompletableObserver actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable d) {
            try {
                CompletablePeek.this.onSubscribe.accept(d);
                if (DisposableHelper.validate(this.f2500d, d)) {
                    this.f2500d = d;
                    this.actual.onSubscribe(this);
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                d.dispose();
                this.f2500d = DisposableHelper.DISPOSED;
                EmptyDisposable.error(ex, this.actual);
            }
        }

        public void onError(Throwable e) {
            if (this.f2500d == DisposableHelper.DISPOSED) {
                RxJavaPlugins.onError(e);
                return;
            }
            try {
                CompletablePeek.this.onError.accept(e);
                CompletablePeek.this.onTerminate.run();
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                e = new CompositeException(e, ex);
            }
            this.actual.onError(e);
            doAfter();
        }

        public void onComplete() {
            if (this.f2500d != DisposableHelper.DISPOSED) {
                try {
                    CompletablePeek.this.onComplete.run();
                    CompletablePeek.this.onTerminate.run();
                    this.actual.onComplete();
                    doAfter();
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.actual.onError(e);
                }
            }
        }

        void doAfter() {
            try {
                CompletablePeek.this.onAfterTerminate.run();
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                RxJavaPlugins.onError(ex);
            }
        }

        public void dispose() {
            try {
                CompletablePeek.this.onDispose.run();
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                RxJavaPlugins.onError(e);
            }
            this.f2500d.dispose();
        }

        public boolean isDisposed() {
            return this.f2500d.isDisposed();
        }
    }

    public CompletablePeek(CompletableSource source, Consumer<? super Disposable> onSubscribe, Consumer<? super Throwable> onError, Action onComplete, Action onTerminate, Action onAfterTerminate, Action onDispose) {
        this.source = source;
        this.onSubscribe = onSubscribe;
        this.onError = onError;
        this.onComplete = onComplete;
        this.onTerminate = onTerminate;
        this.onAfterTerminate = onAfterTerminate;
        this.onDispose = onDispose;
    }

    protected void subscribeActual(CompletableObserver s) {
        this.source.subscribe(new CompletableObserverImplementation(s));
    }
}
