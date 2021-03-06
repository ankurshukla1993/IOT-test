package io.reactivex.internal.operators.flowable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Cancellable;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableCreate<T> extends Flowable<T> {
    final BackpressureStrategy backpressure;
    final FlowableOnSubscribe<T> source;

    static abstract class BaseEmitter<T> extends AtomicLong implements FlowableEmitter<T>, Subscription {
        private static final long serialVersionUID = 7326289992464377023L;
        final Subscriber<? super T> actual;
        final SequentialDisposable serial = new SequentialDisposable();

        BaseEmitter(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        public void onComplete() {
            complete();
        }

        protected void complete() {
            if (!isCancelled()) {
                try {
                    this.actual.onComplete();
                } finally {
                    this.serial.dispose();
                }
            }
        }

        public final void onError(Throwable e) {
            if (!tryOnError(e)) {
                RxJavaPlugins.onError(e);
            }
        }

        public boolean tryOnError(Throwable e) {
            return error(e);
        }

        protected boolean error(Throwable e) {
            if (e == null) {
                e = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (isCancelled()) {
                return false;
            }
            try {
                this.actual.onError(e);
                return true;
            } finally {
                this.serial.dispose();
            }
        }

        public final void cancel() {
            this.serial.dispose();
            onUnsubscribed();
        }

        void onUnsubscribed() {
        }

        public final boolean isCancelled() {
            return this.serial.isDisposed();
        }

        public final void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this, n);
                onRequested();
            }
        }

        void onRequested() {
        }

        public final void setDisposable(Disposable s) {
            this.serial.update(s);
        }

        public final void setCancellable(Cancellable c) {
            setDisposable(new CancellableDisposable(c));
        }

        public final long requested() {
            return get();
        }

        public final FlowableEmitter<T> serialize() {
            return new SerializedEmitter(this);
        }
    }

    static final class BufferAsyncEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 2427151001689639875L;
        volatile boolean done;
        Throwable error;
        final SpscLinkedArrayQueue<T> queue;
        final AtomicInteger wip = new AtomicInteger();

        BufferAsyncEmitter(Subscriber<? super T> actual, int capacityHint) {
            super(actual);
            this.queue = new SpscLinkedArrayQueue(capacityHint);
        }

        public void onNext(T t) {
            if (!this.done && !isCancelled()) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                this.queue.offer(t);
                drain();
            }
        }

        public boolean tryOnError(Throwable e) {
            if (this.done || isCancelled()) {
                return false;
            }
            if (e == null) {
                e = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.error = e;
            this.done = true;
            drain();
            return true;
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void onRequested() {
            drain();
        }

        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super T> a = this.actual;
                SpscLinkedArrayQueue<T> q = this.queue;
                do {
                    boolean d;
                    boolean empty;
                    Throwable ex;
                    long r = get();
                    long e = 0;
                    while (e != r) {
                        if (isCancelled()) {
                            q.clear();
                            return;
                        }
                        d = this.done;
                        T o = q.poll();
                        empty = o == null;
                        if (d && empty) {
                            ex = this.error;
                            if (ex != null) {
                                error(ex);
                                return;
                            } else {
                                complete();
                                return;
                            }
                        } else if (empty) {
                            break;
                        } else {
                            a.onNext(o);
                            e++;
                        }
                    }
                    if (e == r) {
                        if (isCancelled()) {
                            q.clear();
                            return;
                        }
                        d = this.done;
                        empty = q.isEmpty();
                        if (d && empty) {
                            ex = this.error;
                            if (ex != null) {
                                error(ex);
                                return;
                            } else {
                                complete();
                                return;
                            }
                        }
                    }
                    if (e != 0) {
                        BackpressureHelper.produced(this, e);
                    }
                    missed = this.wip.addAndGet(-missed);
                } while (missed != 0);
            }
        }
    }

    static abstract class NoOverflowBaseAsyncEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4127754106204442833L;

        abstract void onOverflow();

        NoOverflowBaseAsyncEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        public final void onNext(T t) {
            if (!isCancelled()) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                } else if (get() != 0) {
                    this.actual.onNext(t);
                    BackpressureHelper.produced(this, 1);
                } else {
                    onOverflow();
                }
            }
        }
    }

    static final class DropAsyncEmitter<T> extends NoOverflowBaseAsyncEmitter<T> {
        private static final long serialVersionUID = 8360058422307496563L;

        DropAsyncEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        void onOverflow() {
        }
    }

    static final class ErrorAsyncEmitter<T> extends NoOverflowBaseAsyncEmitter<T> {
        private static final long serialVersionUID = 338953216916120960L;

        ErrorAsyncEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        void onOverflow() {
            onError(new MissingBackpressureException("create: could not emit value due to lack of requests"));
        }
    }

    static final class LatestAsyncEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4023437720691792495L;
        volatile boolean done;
        Throwable error;
        final AtomicReference<T> queue = new AtomicReference();
        final AtomicInteger wip = new AtomicInteger();

        LatestAsyncEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        public void onNext(T t) {
            if (!this.done && !isCancelled()) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                this.queue.set(t);
                drain();
            }
        }

        public boolean tryOnError(Throwable e) {
            if (this.done || isCancelled()) {
                return false;
            }
            if (e == null) {
                onError(new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources."));
            }
            this.error = e;
            this.done = true;
            drain();
            return true;
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void onRequested() {
            drain();
        }

        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.lazySet(null);
            }
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super T> a = this.actual;
                AtomicReference<T> q = this.queue;
                do {
                    boolean d;
                    boolean empty;
                    Throwable ex;
                    long r = get();
                    long e = 0;
                    while (e != r) {
                        if (isCancelled()) {
                            q.lazySet(null);
                            return;
                        }
                        d = this.done;
                        T o = q.getAndSet(null);
                        empty = o == null;
                        if (d && empty) {
                            ex = this.error;
                            if (ex != null) {
                                error(ex);
                                return;
                            } else {
                                complete();
                                return;
                            }
                        } else if (empty) {
                            break;
                        } else {
                            a.onNext(o);
                            e++;
                        }
                    }
                    if (e == r) {
                        if (isCancelled()) {
                            q.lazySet(null);
                            return;
                        }
                        d = this.done;
                        empty = q.get() == null;
                        if (d && empty) {
                            ex = this.error;
                            if (ex != null) {
                                error(ex);
                                return;
                            } else {
                                complete();
                                return;
                            }
                        }
                    }
                    if (e != 0) {
                        BackpressureHelper.produced(this, e);
                    }
                    missed = this.wip.addAndGet(-missed);
                } while (missed != 0);
            }
        }
    }

    static final class MissingEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 3776720187248809713L;

        MissingEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        public void onNext(T t) {
            if (!isCancelled()) {
                if (t != null) {
                    this.actual.onNext(t);
                    long r;
                    do {
                        r = get();
                        if (r == 0) {
                            return;
                        }
                    } while (!compareAndSet(r, r - 1));
                    return;
                }
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            }
        }
    }

    static final class SerializedEmitter<T> extends AtomicInteger implements FlowableEmitter<T> {
        private static final long serialVersionUID = 4883307006032401862L;
        volatile boolean done;
        final BaseEmitter<T> emitter;
        final AtomicThrowable error = new AtomicThrowable();
        final SimplePlainQueue<T> queue = new SpscLinkedArrayQueue(16);

        SerializedEmitter(BaseEmitter<T> emitter) {
            this.emitter = emitter;
        }

        public void onNext(T t) {
            if (!this.emitter.isCancelled() && !this.done) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                if (get() == 0 && compareAndSet(0, 1)) {
                    this.emitter.onNext(t);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                SimplePlainQueue<T> q = this.queue;
                synchronized (q) {
                    q.offer(t);
                }
                if (getAndIncrement() != 0) {
                    return;
                }
                drainLoop();
            }
        }

        public void onError(Throwable t) {
            if (!tryOnError(t)) {
                RxJavaPlugins.onError(t);
            }
        }

        public boolean tryOnError(Throwable t) {
            if (this.emitter.isCancelled() || this.done) {
                return false;
            }
            if (t == null) {
                t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (!this.error.addThrowable(t)) {
                return false;
            }
            this.done = true;
            drain();
            return true;
        }

        public void onComplete() {
            if (!this.emitter.isCancelled() && !this.done) {
                this.done = true;
                drain();
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            BaseEmitter<T> e = this.emitter;
            SimplePlainQueue<T> q = this.queue;
            AtomicThrowable error = this.error;
            int missed = 1;
            while (!e.isCancelled()) {
                if (error.get() != null) {
                    q.clear();
                    e.onError(error.terminate());
                    return;
                }
                boolean d = this.done;
                T v = q.poll();
                boolean empty = v == null;
                if (d && empty) {
                    e.onComplete();
                    return;
                } else if (empty) {
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                } else {
                    e.onNext(v);
                }
            }
            q.clear();
        }

        public void setDisposable(Disposable s) {
            this.emitter.setDisposable(s);
        }

        public void setCancellable(Cancellable c) {
            this.emitter.setCancellable(c);
        }

        public long requested() {
            return this.emitter.requested();
        }

        public boolean isCancelled() {
            return this.emitter.isCancelled();
        }

        public FlowableEmitter<T> serialize() {
            return this;
        }
    }

    public FlowableCreate(FlowableOnSubscribe<T> source, BackpressureStrategy backpressure) {
        this.source = source;
        this.backpressure = backpressure;
    }

    public void subscribeActual(Subscriber<? super T> t) {
        BaseEmitter<T> emitter;
        switch (this.backpressure) {
            case MISSING:
                emitter = new MissingEmitter(t);
                break;
            case ERROR:
                emitter = new ErrorAsyncEmitter(t);
                break;
            case DROP:
                emitter = new DropAsyncEmitter(t);
                break;
            case LATEST:
                emitter = new LatestAsyncEmitter(t);
                break;
            default:
                emitter = new BufferAsyncEmitter(t, bufferSize());
                break;
        }
        t.onSubscribe(emitter);
        try {
            this.source.subscribe(emitter);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            emitter.onError(ex);
        }
    }
}
