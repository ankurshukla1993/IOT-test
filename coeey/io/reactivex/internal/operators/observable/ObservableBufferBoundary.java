package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableBufferBoundary<T, U extends Collection<? super T>, Open, Close> extends AbstractObservableWithUpstream<T, U> {
    final Function<? super Open, ? extends ObservableSource<? extends Close>> bufferClose;
    final ObservableSource<? extends Open> bufferOpen;
    final Callable<U> bufferSupplier;

    static final class BufferBoundaryObserver<T, U extends Collection<? super T>, Open, Close> extends QueueDrainObserver<T, U, U> implements Disposable {
        final Function<? super Open, ? extends ObservableSource<? extends Close>> bufferClose;
        final ObservableSource<? extends Open> bufferOpen;
        final Callable<U> bufferSupplier;
        final List<U> buffers;
        final CompositeDisposable resources;
        Disposable f2661s;
        final AtomicInteger windows = new AtomicInteger();

        BufferBoundaryObserver(Observer<? super U> actual, ObservableSource<? extends Open> bufferOpen, Function<? super Open, ? extends ObservableSource<? extends Close>> bufferClose, Callable<U> bufferSupplier) {
            super(actual, new MpscLinkedQueue());
            this.bufferOpen = bufferOpen;
            this.bufferClose = bufferClose;
            this.bufferSupplier = bufferSupplier;
            this.buffers = new LinkedList();
            this.resources = new CompositeDisposable();
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2661s, s)) {
                this.f2661s = s;
                BufferOpenObserver<T, U, Open, Close> bos = new BufferOpenObserver(this);
                this.resources.add(bos);
                this.actual.onSubscribe(this);
                this.windows.lazySet(1);
                this.bufferOpen.subscribe(bos);
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                for (Collection b : this.buffers) {
                    b.add(t);
                }
            }
        }

        public void onError(Throwable t) {
            dispose();
            this.cancelled = true;
            synchronized (this) {
                this.buffers.clear();
            }
            this.actual.onError(t);
        }

        public void onComplete() {
            if (this.windows.decrementAndGet() == 0) {
                complete();
            }
        }

        void complete() {
            synchronized (this) {
                List<U> list = new ArrayList(this.buffers);
                this.buffers.clear();
            }
            SimplePlainQueue<U> q = this.queue;
            for (U u : list) {
                q.offer(u);
            }
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainLoop(q, this.actual, false, this, this);
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.resources.dispose();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void accept(Observer<? super U> a, U v) {
            a.onNext(v);
        }

        void open(Open window) {
            if (!this.cancelled) {
                try {
                    Collection b = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    try {
                        ObservableSource<? extends Close> p = (ObservableSource) ObjectHelper.requireNonNull(this.bufferClose.apply(window), "The buffer closing Observable is null");
                        if (!this.cancelled) {
                            synchronized (this) {
                                if (this.cancelled) {
                                    return;
                                }
                                this.buffers.add(b);
                                BufferCloseObserver<T, U, Open, Close> bcs = new BufferCloseObserver(b, this);
                                this.resources.add(bcs);
                                this.windows.getAndIncrement();
                                p.subscribe(bcs);
                            }
                        }
                    } catch (Throwable e) {
                        Exceptions.throwIfFatal(e);
                        onError(e);
                    }
                } catch (Throwable e2) {
                    Exceptions.throwIfFatal(e2);
                    onError(e2);
                }
            }
        }

        void openFinished(Disposable d) {
            if (this.resources.remove(d) && this.windows.decrementAndGet() == 0) {
                complete();
            }
        }

        void close(U b, Disposable d) {
            synchronized (this) {
                boolean e = this.buffers.remove(b);
            }
            if (e) {
                fastPathOrderedEmit(b, false, this);
            }
            if (this.resources.remove(d) && this.windows.decrementAndGet() == 0) {
                complete();
            }
        }
    }

    static final class BufferCloseObserver<T, U extends Collection<? super T>, Open, Close> extends DisposableObserver<Close> {
        boolean done;
        final BufferBoundaryObserver<T, U, Open, Close> parent;
        final U value;

        BufferCloseObserver(U value, BufferBoundaryObserver<T, U, Open, Close> parent) {
            this.parent = parent;
            this.value = value;
        }

        public void onNext(Close close) {
            onComplete();
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
            } else {
                this.parent.onError(t);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.parent.close(this.value, this);
            }
        }
    }

    static final class BufferOpenObserver<T, U extends Collection<? super T>, Open, Close> extends DisposableObserver<Open> {
        boolean done;
        final BufferBoundaryObserver<T, U, Open, Close> parent;

        BufferOpenObserver(BufferBoundaryObserver<T, U, Open, Close> parent) {
            this.parent = parent;
        }

        public void onNext(Open t) {
            if (!this.done) {
                this.parent.open(t);
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.parent.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.parent.openFinished(this);
            }
        }
    }

    public ObservableBufferBoundary(ObservableSource<T> source, ObservableSource<? extends Open> bufferOpen, Function<? super Open, ? extends ObservableSource<? extends Close>> bufferClose, Callable<U> bufferSupplier) {
        super(source);
        this.bufferOpen = bufferOpen;
        this.bufferClose = bufferClose;
        this.bufferSupplier = bufferSupplier;
    }

    protected void subscribeActual(Observer<? super U> t) {
        this.source.subscribe(new BufferBoundaryObserver(new SerializedObserver(t), this.bufferOpen, this.bufferClose, this.bufferSupplier));
    }
}
