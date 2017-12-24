package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Cancellable;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeCreate<T> extends Maybe<T> {
    final MaybeOnSubscribe<T> source;

    static final class Emitter<T> extends AtomicReference<Disposable> implements MaybeEmitter<T>, Disposable {
        private static final long serialVersionUID = -2467358622224974244L;
        final MaybeObserver<? super T> actual;

        Emitter(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void onSuccess(T value) {
            if (get() != DisposableHelper.DISPOSED) {
                Disposable d = (Disposable) getAndSet(DisposableHelper.DISPOSED);
                if (d != DisposableHelper.DISPOSED) {
                    if (value == null) {
                        try {
                            this.actual.onError(new NullPointerException("onSuccess called with null. Null values are generally not allowed in 2.x operators and sources."));
                        } catch (Throwable th) {
                            if (d != null) {
                                d.dispose();
                            }
                        }
                    } else {
                        this.actual.onSuccess(value);
                    }
                    if (d != null) {
                        d.dispose();
                    }
                }
            }
        }

        public void onError(Throwable t) {
            if (!tryOnError(t)) {
                RxJavaPlugins.onError(t);
            }
        }

        public boolean tryOnError(Throwable t) {
            if (t == null) {
                t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (get() != DisposableHelper.DISPOSED) {
                Disposable d = (Disposable) getAndSet(DisposableHelper.DISPOSED);
                if (d != DisposableHelper.DISPOSED) {
                    try {
                        this.actual.onError(t);
                        return true;
                    } finally {
                        if (d != null) {
                            d.dispose();
                        }
                    }
                }
            }
            return false;
        }

        public void onComplete() {
            if (get() != DisposableHelper.DISPOSED) {
                Disposable d = (Disposable) getAndSet(DisposableHelper.DISPOSED);
                if (d != DisposableHelper.DISPOSED) {
                    try {
                        this.actual.onComplete();
                    } finally {
                        if (d != null) {
                            d.dispose();
                        }
                    }
                }
            }
        }

        public void setDisposable(Disposable d) {
            DisposableHelper.set(this, d);
        }

        public void setCancellable(Cancellable c) {
            setDisposable(new CancellableDisposable(c));
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    public MaybeCreate(MaybeOnSubscribe<T> source) {
        this.source = source;
    }

    protected void subscribeActual(MaybeObserver<? super T> s) {
        Emitter<T> parent = new Emitter(s);
        s.onSubscribe(parent);
        try {
            this.source.subscribe(parent);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            parent.onError(ex);
        }
    }
}
