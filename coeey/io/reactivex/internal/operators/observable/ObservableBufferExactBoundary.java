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
import java.util.Collection;
import java.util.concurrent.Callable;

public final class ObservableBufferExactBoundary<T, U extends Collection<? super T>, B> extends AbstractObservableWithUpstream<T, U> {
    final ObservableSource<B> boundary;
    final Callable<U> bufferSupplier;

    static final class BufferBoundaryObserver<T, U extends Collection<? super T>, B> extends DisposableObserver<B> {
        final BufferExactBoundaryObserver<T, U, B> parent;

        BufferBoundaryObserver(BufferExactBoundaryObserver<T, U, B> parent) {
            this.parent = parent;
        }

        public void onNext(B b) {
            this.parent.next();
        }

        public void onError(Throwable t) {
            this.parent.onError(t);
        }

        public void onComplete() {
            this.parent.onComplete();
        }
    }

    static final class BufferExactBoundaryObserver<T, U extends Collection<? super T>, B> extends QueueDrainObserver<T, U, U> implements Observer<T>, Disposable {
        final ObservableSource<B> boundary;
        U buffer;
        final Callable<U> bufferSupplier;
        Disposable other;
        Disposable f2663s;

        BufferExactBoundaryObserver(Observer<? super U> actual, Callable<U> bufferSupplier, ObservableSource<B> boundary) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier;
            this.boundary = boundary;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2663s, s)) {
                this.f2663s = s;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    BufferBoundaryObserver<T, U, B> bs = new BufferBoundaryObserver(this);
                    this.other = bs;
                    this.actual.onSubscribe(this);
                    if (!this.cancelled) {
                        this.boundary.subscribe(bs);
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.cancelled = true;
                    s.dispose();
                    EmptyDisposable.error(e, this.actual);
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
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableBufferExactBoundary.BufferExactBoundaryObserver.onComplete():void");
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.other.dispose();
                this.f2663s.dispose();
                if (enter()) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void next() {
            try {
                Collection next = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                synchronized (this) {
                    U b = this.buffer;
                    if (b == null) {
                        return;
                    }
                    this.buffer = next;
                    fastPathEmit(b, false, this);
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

    public ObservableBufferExactBoundary(ObservableSource<T> source, ObservableSource<B> boundary, Callable<U> bufferSupplier) {
        super(source);
        this.boundary = boundary;
        this.bufferSupplier = bufferSupplier;
    }

    protected void subscribeActual(Observer<? super U> t) {
        this.source.subscribe(new BufferExactBoundaryObserver(new SerializedObserver(t), this.bufferSupplier, this.boundary));
    }
}
