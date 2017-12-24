package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class UnicastSubject<T> extends Subject<T> {
    final AtomicReference<Observer<? super T>> actual;
    final boolean delayError;
    volatile boolean disposed;
    volatile boolean done;
    boolean enableOperatorFusion;
    Throwable error;
    final AtomicReference<Runnable> onTerminate;
    final AtomicBoolean once;
    final SpscLinkedArrayQueue<T> queue;
    final BasicIntQueueDisposable<T> wip;

    @CheckReturnValue
    public static <T> UnicastSubject<T> create() {
        return new UnicastSubject(bufferSize(), true);
    }

    @CheckReturnValue
    public static <T> UnicastSubject<T> create(int capacityHint) {
        return new UnicastSubject(capacityHint, true);
    }

    @CheckReturnValue
    public static <T> UnicastSubject<T> create(int capacityHint, Runnable onTerminate) {
        return new UnicastSubject(capacityHint, onTerminate, true);
    }

    @CheckReturnValue
    @Experimental
    public static <T> UnicastSubject<T> create(int capacityHint, Runnable onTerminate, boolean delayError) {
        return new UnicastSubject(capacityHint, onTerminate, delayError);
    }

    @CheckReturnValue
    @Experimental
    public static <T> UnicastSubject<T> create(boolean delayError) {
        return new UnicastSubject(bufferSize(), delayError);
    }

    UnicastSubject(int capacityHint, boolean delayError) {
        this.queue = new SpscLinkedArrayQueue(ObjectHelper.verifyPositive(capacityHint, "capacityHint"));
        this.onTerminate = new AtomicReference();
        this.delayError = delayError;
        this.actual = new AtomicReference();
        this.once = new AtomicBoolean();
        this.wip = new UnicastQueueDisposable(this);
    }

    UnicastSubject(int capacityHint, Runnable onTerminate) {
        this(capacityHint, onTerminate, true);
    }

    UnicastSubject(int capacityHint, Runnable onTerminate, boolean delayError) {
        this.queue = new SpscLinkedArrayQueue(ObjectHelper.verifyPositive(capacityHint, "capacityHint"));
        this.onTerminate = new AtomicReference(ObjectHelper.requireNonNull(onTerminate, "onTerminate"));
        this.delayError = delayError;
        this.actual = new AtomicReference();
        this.once = new AtomicBoolean();
        this.wip = new UnicastQueueDisposable(this);
    }

    protected void subscribeActual(Observer<? super T> observer) {
        if (this.once.get() || !this.once.compareAndSet(false, true)) {
            EmptyDisposable.error(new IllegalStateException("Only a single observer allowed."), observer);
            return;
        }
        observer.onSubscribe(this.wip);
        this.actual.lazySet(observer);
        if (this.disposed) {
            this.actual.lazySet(null);
        } else {
            drain();
        }
    }

    void doTerminate() {
        Runnable r = (Runnable) this.onTerminate.get();
        if (r != null && this.onTerminate.compareAndSet(r, null)) {
            r.run();
        }
    }

    public void onSubscribe(Disposable s) {
        if (this.done || this.disposed) {
            s.dispose();
        }
    }

    public void onNext(T t) {
        if (!this.done && !this.disposed) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            this.queue.offer(t);
            drain();
        }
    }

    public void onError(Throwable t) {
        if (this.done || this.disposed) {
            RxJavaPlugins.onError(t);
            return;
        }
        if (t == null) {
            t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        this.error = t;
        this.done = true;
        doTerminate();
        drain();
    }

    public void onComplete() {
        if (!this.done && !this.disposed) {
            this.done = true;
            doTerminate();
            drain();
        }
    }

    void drainNormal(Observer<? super T> a) {
        boolean failFast;
        int missed = 1;
        SimpleQueue<T> q = this.queue;
        if (this.delayError) {
            failFast = false;
        } else {
            failFast = true;
        }
        boolean canBeError = true;
        while (!this.disposed) {
            boolean empty;
            boolean d = this.done;
            T v = this.queue.poll();
            if (v == null) {
                empty = true;
            } else {
                empty = false;
            }
            if (d) {
                if (failFast && canBeError) {
                    if (!failedFast(q, a)) {
                        canBeError = false;
                    } else {
                        return;
                    }
                }
                if (empty) {
                    errorOrComplete(a);
                    return;
                }
            }
            if (empty) {
                missed = this.wip.addAndGet(-missed);
                if (missed == 0) {
                    return;
                }
            } else {
                a.onNext(v);
            }
        }
        this.actual.lazySet(null);
        q.clear();
    }

    void drainFused(Observer<? super T> a) {
        int missed = 1;
        SpscLinkedArrayQueue<T> q = this.queue;
        boolean failFast = !this.delayError;
        while (!this.disposed) {
            boolean d = this.done;
            if (!failFast || !d || !failedFast(q, a)) {
                a.onNext(null);
                if (d) {
                    errorOrComplete(a);
                    return;
                }
                missed = this.wip.addAndGet(-missed);
                if (missed == 0) {
                    return;
                }
            }
            return;
        }
        this.actual.lazySet(null);
        q.clear();
    }

    void errorOrComplete(Observer<? super T> a) {
        this.actual.lazySet(null);
        Throwable ex = this.error;
        if (ex != null) {
            a.onError(ex);
        } else {
            a.onComplete();
        }
    }

    boolean failedFast(SimpleQueue<T> q, Observer<? super T> a) {
        Throwable ex = this.error;
        if (ex == null) {
            return false;
        }
        this.actual.lazySet(null);
        q.clear();
        a.onError(ex);
        return true;
    }

    void drain() {
        if (this.wip.getAndIncrement() == 0) {
            Observer<? super T> a = (Observer) this.actual.get();
            int missed = 1;
            while (a == null) {
                missed = this.wip.addAndGet(-missed);
                if (missed != 0) {
                    a = (Observer) this.actual.get();
                } else {
                    return;
                }
            }
            if (this.enableOperatorFusion) {
                drainFused(a);
            } else {
                drainNormal(a);
            }
        }
    }

    public boolean hasObservers() {
        return this.actual.get() != null;
    }

    public Throwable getThrowable() {
        if (this.done) {
            return this.error;
        }
        return null;
    }

    public boolean hasThrowable() {
        return this.done && this.error != null;
    }

    public boolean hasComplete() {
        return this.done && this.error == null;
    }
}
