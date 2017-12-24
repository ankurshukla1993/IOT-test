package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleUsing<T, U> extends Single<T> {
    final Consumer<? super U> disposer;
    final boolean eager;
    final Callable<U> resourceSupplier;
    final Function<? super U, ? extends SingleSource<? extends T>> singleFunction;

    static final class UsingSingleObserver<T, U> extends AtomicReference<Object> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -5331524057054083935L;
        final SingleObserver<? super T> actual;
        Disposable f2799d;
        final Consumer<? super U> disposer;
        final boolean eager;

        UsingSingleObserver(SingleObserver<? super T> actual, U resource, boolean eager, Consumer<? super U> disposer) {
            super(resource);
            this.actual = actual;
            this.eager = eager;
            this.disposer = disposer;
        }

        public void dispose() {
            this.f2799d.dispose();
            this.f2799d = DisposableHelper.DISPOSED;
            disposeAfter();
        }

        public boolean isDisposed() {
            return this.f2799d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2799d, d)) {
                this.f2799d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.f2799d = DisposableHelper.DISPOSED;
            if (this.eager) {
                UsingSingleObserver u = getAndSet(this);
                if (u != this) {
                    try {
                        this.disposer.accept(u);
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        this.actual.onError(ex);
                        return;
                    }
                }
                return;
            }
            this.actual.onSuccess(value);
            if (!this.eager) {
                disposeAfter();
            }
        }

        public void onError(Throwable e) {
            this.f2799d = DisposableHelper.DISPOSED;
            if (this.eager) {
                UsingSingleObserver u = getAndSet(this);
                if (u != this) {
                    try {
                        this.disposer.accept(u);
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        e = new CompositeException(e, ex);
                    }
                } else {
                    return;
                }
            }
            this.actual.onError(e);
            if (!this.eager) {
                disposeAfter();
            }
        }

        void disposeAfter() {
            UsingSingleObserver u = getAndSet(this);
            if (u != this) {
                try {
                    this.disposer.accept(u);
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    RxJavaPlugins.onError(ex);
                }
            }
        }
    }

    public SingleUsing(Callable<U> resourceSupplier, Function<? super U, ? extends SingleSource<? extends T>> singleFunction, Consumer<? super U> disposer, boolean eager) {
        this.resourceSupplier = resourceSupplier;
        this.singleFunction = singleFunction;
        this.disposer = disposer;
        this.eager = eager;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        Throwable ex;
        try {
            U resource = this.resourceSupplier.call();
            try {
                ((SingleSource) ObjectHelper.requireNonNull(this.singleFunction.apply(resource), "The singleFunction returned a null SingleSource")).subscribe(new UsingSingleObserver(s, resource, this.eager, this.disposer));
                return;
            } catch (Throwable exc) {
                Exceptions.throwIfFatal(exc);
                RxJavaPlugins.onError(exc);
                return;
            }
            EmptyDisposable.error(ex, (SingleObserver) s);
            if (!this.eager) {
                this.disposer.accept(resource);
            }
        } catch (Throwable ex2) {
            Exceptions.throwIfFatal(ex2);
            EmptyDisposable.error(ex2, (SingleObserver) s);
        }
    }
}
