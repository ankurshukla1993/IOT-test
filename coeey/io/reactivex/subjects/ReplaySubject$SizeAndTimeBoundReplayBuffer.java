package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

final class ReplaySubject$SizeAndTimeBoundReplayBuffer<T> extends AtomicReference<Object> implements ReplaySubject$ReplayBuffer<T> {
    private static final long serialVersionUID = -8056260896137901749L;
    volatile boolean done;
    volatile ReplaySubject$TimedNode<Object> head;
    final long maxAge;
    final int maxSize;
    final Scheduler scheduler;
    int size;
    ReplaySubject$TimedNode<Object> tail;
    final TimeUnit unit;

    ReplaySubject$SizeAndTimeBoundReplayBuffer(int maxSize, long maxAge, TimeUnit unit, Scheduler scheduler) {
        this.maxSize = ObjectHelper.verifyPositive(maxSize, "maxSize");
        this.maxAge = ObjectHelper.verifyPositive(maxAge, "maxAge");
        this.unit = (TimeUnit) ObjectHelper.requireNonNull(unit, "unit is null");
        this.scheduler = (Scheduler) ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ReplaySubject$TimedNode<Object> h = new ReplaySubject$TimedNode(null, 0);
        this.tail = h;
        this.head = h;
    }

    void trim() {
        if (this.size > this.maxSize) {
            this.size--;
            this.head = (ReplaySubject$TimedNode) this.head.get();
        }
        long limit = this.scheduler.now(this.unit) - this.maxAge;
        ReplaySubject$TimedNode<Object> h = this.head;
        while (true) {
            ReplaySubject$TimedNode<Object> next = (ReplaySubject$TimedNode) h.get();
            if (next == null) {
                this.head = h;
                return;
            } else if (next.time > limit) {
                this.head = h;
                return;
            } else {
                h = next;
            }
        }
    }

    void trimFinal() {
        long limit = this.scheduler.now(this.unit) - this.maxAge;
        ReplaySubject$TimedNode<Object> h = this.head;
        while (true) {
            ReplaySubject$TimedNode<Object> next = (ReplaySubject$TimedNode) h.get();
            if (next.get() == null) {
                this.head = h;
                return;
            } else if (next.time > limit) {
                this.head = h;
                return;
            } else {
                h = next;
            }
        }
    }

    public void add(T value) {
        ReplaySubject$TimedNode<Object> n = new ReplaySubject$TimedNode(value, this.scheduler.now(this.unit));
        ReplaySubject$TimedNode<Object> t = this.tail;
        this.tail = n;
        this.size++;
        t.set(n);
        trim();
    }

    public void addFinal(Object notificationLite) {
        ReplaySubject$TimedNode<Object> n = new ReplaySubject$TimedNode(notificationLite, Long.MAX_VALUE);
        ReplaySubject$TimedNode<Object> t = this.tail;
        this.tail = n;
        this.size++;
        t.lazySet(n);
        trimFinal();
        this.done = true;
    }

    public T getValue() {
        ReplaySubject$TimedNode<Object> prev = null;
        ReplaySubject$TimedNode<Object> h = this.head;
        while (true) {
            ReplaySubject$TimedNode<Object> next = (ReplaySubject$TimedNode) h.get();
            if (next == null) {
                break;
            }
            prev = h;
            h = next;
        }
        if (h.time < this.scheduler.now(this.unit) - this.maxAge) {
            return null;
        }
        T v = h.value;
        if (v == null) {
            return null;
        }
        if (NotificationLite.isComplete(v) || NotificationLite.isError(v)) {
            return prev.value;
        }
        return v;
    }

    ReplaySubject$TimedNode<Object> getHead() {
        ReplaySubject$TimedNode<Object> index = this.head;
        long limit = this.scheduler.now(this.unit) - this.maxAge;
        ReplaySubject$TimedNode<Object> next = (ReplaySubject$TimedNode) index.get();
        while (next != null && next.time <= limit) {
            index = next;
            next = (ReplaySubject$TimedNode) index.get();
        }
        return index;
    }

    public T[] getValues(T[] array) {
        ReplaySubject$TimedNode<Object> h = getHead();
        int s = size(h);
        if (s != 0) {
            if (array.length < s) {
                array = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), s));
            }
            int i = 0;
            while (i != s) {
                ReplaySubject$TimedNode<Object> next = (ReplaySubject$TimedNode) h.get();
                array[i] = next.value;
                i++;
                h = next;
            }
            if (array.length > s) {
                array[s] = null;
            }
        } else if (array.length != 0) {
            array[0] = null;
        }
        return array;
    }

    public void replay(ReplaySubject$ReplayDisposable<T> rs) {
        if (rs.getAndIncrement() == 0) {
            int missed = 1;
            Observer<? super T> a = rs.actual;
            ReplaySubject$TimedNode<Object> index = rs.index;
            if (index == null) {
                index = getHead();
            }
            while (!rs.cancelled) {
                while (!rs.cancelled) {
                    ReplaySubject$TimedNode<Object> n = (ReplaySubject$TimedNode) index.get();
                    if (n != null) {
                        Object o = n.value;
                        if (this.done && n.get() == null) {
                            if (NotificationLite.isComplete(o)) {
                                a.onComplete();
                            } else {
                                a.onError(NotificationLite.getError(o));
                            }
                            rs.index = null;
                            rs.cancelled = true;
                            return;
                        }
                        a.onNext(o);
                        index = n;
                    } else if (index.get() == null) {
                        rs.index = index;
                        missed = rs.addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    }
                }
                rs.index = null;
                return;
            }
            rs.index = null;
        }
    }

    public int size() {
        return size(getHead());
    }

    int size(ReplaySubject$TimedNode<Object> h) {
        int s = 0;
        while (s != Integer.MAX_VALUE) {
            ReplaySubject$TimedNode<Object> next = (ReplaySubject$TimedNode) h.get();
            if (next == null) {
                Object o = h.value;
                if (NotificationLite.isComplete(o) || NotificationLite.isError(o)) {
                    return s - 1;
                }
                return s;
            }
            s++;
            h = next;
        }
        return s;
    }
}
