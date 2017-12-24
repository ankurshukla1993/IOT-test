package io.reactivex.processors;

import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.Experimental;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class PublishProcessor<T> extends FlowableProcessor<T> {
    static final PublishSubscription[] EMPTY = new PublishSubscription[0];
    static final PublishSubscription[] TERMINATED = new PublishSubscription[0];
    Throwable error;
    final AtomicReference<PublishSubscription<T>[]> subscribers = new AtomicReference(EMPTY);

    @CheckReturnValue
    public static <T> PublishProcessor<T> create() {
        return new PublishProcessor();
    }

    PublishProcessor() {
    }

    public void subscribeActual(Subscriber<? super T> t) {
        PublishSubscription<T> ps = new PublishSubscription(t, this);
        t.onSubscribe(ps);
        if (!add(ps)) {
            Throwable ex = this.error;
            if (ex != null) {
                t.onError(ex);
            } else {
                t.onComplete();
            }
        } else if (ps.isCancelled()) {
            remove(ps);
        }
    }

    boolean add(PublishSubscription<T> ps) {
        PublishSubscription[] a;
        PublishSubscription<T>[] b;
        do {
            a = (PublishSubscription[]) this.subscribers.get();
            if (a == TERMINATED) {
                return false;
            }
            int n = a.length;
            b = new PublishSubscription[(n + 1)];
            System.arraycopy(a, 0, b, 0, n);
            b[n] = ps;
        } while (!this.subscribers.compareAndSet(a, b));
        return true;
    }

    void remove(PublishSubscription<T> ps) {
        PublishSubscription[] a;
        PublishSubscription<T>[] b;
        do {
            a = (PublishSubscription[]) this.subscribers.get();
            if (a != TERMINATED && a != EMPTY) {
                int n = a.length;
                int j = -1;
                for (int i = 0; i < n; i++) {
                    if (a[i] == ps) {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                    return;
                }
                if (n == 1) {
                    b = EMPTY;
                } else {
                    b = new PublishSubscription[(n - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(a, b));
    }

    public void onSubscribe(Subscription s) {
        if (this.subscribers.get() == TERMINATED) {
            s.cancel();
        } else {
            s.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        if (this.subscribers.get() != TERMINATED) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            for (PublishSubscription<T> s : (PublishSubscription[]) this.subscribers.get()) {
                s.onNext(t);
            }
        }
    }

    public void onError(Throwable t) {
        if (this.subscribers.get() == TERMINATED) {
            RxJavaPlugins.onError(t);
            return;
        }
        if (t == null) {
            t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        this.error = t;
        for (PublishSubscription<T> s : (PublishSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
            s.onError(t);
        }
    }

    public void onComplete() {
        if (this.subscribers.get() != TERMINATED) {
            for (PublishSubscription<T> s : (PublishSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                s.onComplete();
            }
        }
    }

    @Experimental
    public boolean offer(T t) {
        int i = 0;
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return true;
        }
        PublishSubscription[] array = (PublishSubscription[]) this.subscribers.get();
        for (PublishSubscription<T> s : array) {
            if (s.isFull()) {
                return false;
            }
        }
        int length = array.length;
        while (i < length) {
            array[i].onNext(t);
            i++;
        }
        return true;
    }

    public boolean hasSubscribers() {
        return ((PublishSubscription[]) this.subscribers.get()).length != 0;
    }

    public Throwable getThrowable() {
        if (this.subscribers.get() == TERMINATED) {
            return this.error;
        }
        return null;
    }

    public boolean hasThrowable() {
        return this.subscribers.get() == TERMINATED && this.error != null;
    }

    public boolean hasComplete() {
        return this.subscribers.get() == TERMINATED && this.error == null;
    }
}
