package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableBufferBoundarySupplier<T, U extends Collection<? super T>, B> extends AbstractObservableWithUpstream<T, U> {
    final Callable<? extends ObservableSource<B>> boundarySupplier;
    final Callable<U> bufferSupplier;

    static final class BufferBoundaryObserver<T, U extends Collection<? super T>, B> extends DisposableObserver<B> {
        boolean once;
        final BufferBoundarySupplierObserver<T, U, B> parent;

        BufferBoundaryObserver(BufferBoundarySupplierObserver<T, U, B> parent) {
            this.parent = parent;
        }

        public void onNext(B b) {
            if (!this.once) {
                this.once = true;
                dispose();
                this.parent.next();
            }
        }

        public void onError(Throwable t) {
            if (this.once) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.once = true;
            this.parent.onError(t);
        }

        public void onComplete() {
            if (!this.once) {
                this.once = true;
                this.parent.next();
            }
        }
    }

    static final class BufferBoundarySupplierObserver<T, U extends Collection<? super T>, B> extends QueueDrainObserver<T, U, U> implements Observer<T>, Disposable {
        final Callable<? extends ObservableSource<B>> boundarySupplier;
        U buffer;
        final Callable<U> bufferSupplier;
        final AtomicReference<Disposable> other = new AtomicReference();
        Disposable f2662s;

        BufferBoundarySupplierObserver(Observer<? super U> actual, Callable<U> bufferSupplier, Callable<? extends ObservableSource<B>> boundarySupplier) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier;
            this.boundarySupplier = boundarySupplier;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2662s, s)) {
                this.f2662s = s;
                Observer actual = this.actual;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    try {
                        ObservableSource<B> boundary = (ObservableSource) ObjectHelper.requireNonNull(this.boundarySupplier.call(), "The boundary ObservableSource supplied is null");
                        BufferBoundaryObserver<T, U, B> bs = new BufferBoundaryObserver(this);
                        this.other.set(bs);
                        actual.onSubscribe(this);
                        if (!this.cancelled) {
                            boundary.subscribe(bs);
                        }
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        this.cancelled = true;
                        s.dispose();
                        EmptyDisposable.error(ex, actual);
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.cancelled = true;
                    s.dispose();
                    EmptyDisposable.error(e, actual);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                U b = this.buffer;
                if (b == null) {
                    return;
                }
                b.add(t);
            }
        }

        public void onError(Throwable t) {
            dispose();
            this.actual.onError(t);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onComplete() {
            /*
            r4 = this;
            monitor-enter(r4);
            r0 = r4.buffer;	 Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x0007;
        L_0x0005:
            monitor-exit(r4);	 Catch:{ all -> 0x0022 }
        L_0x0006:
            return;
        L_0x0007:
            r1 = 0;
            r4.buffer = r1;	 Catch:{ all -> 0x0022 }
            monitor-exit(r4);	 Catch:{ all -> 0x0022 }
            r1 = r4.queue;
            r1.offer(r0);
            r1 = 1;
            r4.done = r1;
            r1 = r4.enter();
            if (r1 == 0) goto L_0x0006;
        L_0x0019:
            r1 = r4.queue;
            r2 = r4.actual;
            r3 = 0;
            io.reactivex.internal.util.QueueDrainHelper.drainLoop(r1, r2, r3, r4, r4);
            goto L_0x0006;
        L_0x0022:
            r1 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0022 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableBufferBoundarySupplier.BufferBoundarySupplierObserver.onComplete():void");
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2662s.dispose();
                disposeOther();
                if (enter()) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void disposeOther() {
            DisposableHelper.dispose(this.other);
        }

        void next() {
            try {
                Collection next = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                try {
                    ObservableSource<B> boundary = (ObservableSource) ObjectHelper.requireNonNull(this.boundarySupplier.call(), "The boundary ObservableSource supplied is null");
                    BufferBoundaryObserver<T, U, B> bs = new BufferBoundaryObserver(this);
                    if (this.other.compareAndSet((Disposable) this.other.get(), bs)) {
                        synchronized (this) {
                            U b = this.buffer;
                            if (b == null) {
                                return;
                            }
                            this.buffer = next;
                            boundary.subscribe(bs);
                            fastPathEmit(b, false, this);
                        }
                    }
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    this.cancelled = true;
                    this.f2662s.dispose();
                    this.actual.onError(ex);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                dispose();
                this.actual.onError(e);
            }
        }

        public void accept(Observer<? super U> observer, U v) {
            this.actual.onNext(v);
        }
    }

    public ObservableBufferBoundarySupplier(ObservableSource<T> source, Callable<? extends ObservableSource<B>> boundarySupplier, Callable<U> bufferSupplier) {
        super(source);
        this.boundarySupplier = boundarySupplier;
        this.bufferSupplier = bufferSupplier;
    }

    protected void subscribeActual(Observer<? super U> t) {
        this.source.subscribe(new BufferBoundarySupplierObserver(new SerializedObserver(t), this.bufferSupplier, this.boundarySupplier));
    }
}
