package io.reactivex.processors;

import io.reactivex.Scheduler;
import io.reactivex.internal.functions.ObjectHelper;
import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;

final class ReplayProcessor$SizeAndTimeBoundReplayBuffer<T> implements ReplayProcessor$ReplayBuffer<T> {
    volatile boolean done;
    Throwable error;
    volatile ReplayProcessor$TimedNode<T> head;
    final long maxAge;
    final int maxSize;
    final Scheduler scheduler;
    int size;
    ReplayProcessor$TimedNode<T> tail;
    final TimeUnit unit;

    ReplayProcessor$SizeAndTimeBoundReplayBuffer(int maxSize, long maxAge, TimeUnit unit, Scheduler scheduler) {
        this.maxSize = ObjectHelper.verifyPositive(maxSize, "maxSize");
        this.maxAge = ObjectHelper.verifyPositive(maxAge, "maxAge");
        this.unit = (TimeUnit) ObjectHelper.requireNonNull(unit, "unit is null");
        this.scheduler = (Scheduler) ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ReplayProcessor$TimedNode<T> h = new ReplayProcessor$TimedNode(null, 0);
        this.tail = h;
        this.head = h;
    }

    void trim() {
        if (this.size > this.maxSize) {
            this.size--;
            this.head = (ReplayProcessor$TimedNode) this.head.get();
        }
        long limit = this.scheduler.now(this.unit) - this.maxAge;
        ReplayProcessor$TimedNode<T> h = this.head;
        while (true) {
            ReplayProcessor$TimedNode<T> next = (ReplayProcessor$TimedNode) h.get();
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
        ReplayProcessor$TimedNode<T> h = this.head;
        while (true) {
            ReplayProcessor$TimedNode<T> next = (ReplayProcessor$TimedNode) h.get();
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

    public void next(T value) {
        ReplayProcessor$TimedNode<T> n = new ReplayProcessor$TimedNode(value, this.scheduler.now(this.unit));
        ReplayProcessor$TimedNode<T> t = this.tail;
        this.tail = n;
        this.size++;
        t.set(n);
        trim();
    }

    public void error(Throwable ex) {
        trimFinal();
        this.error = ex;
        this.done = true;
    }

    public void complete() {
        trimFinal();
        this.done = true;
    }

    public T getValue() {
        ReplayProcessor$TimedNode<T> h = this.head;
        while (true) {
            ReplayProcessor$TimedNode<T> next = (ReplayProcessor$TimedNode) h.get();
            if (next == null) {
                break;
            }
            h = next;
        }
        if (h.time < this.scheduler.now(this.unit) - this.maxAge) {
            return null;
        }
        return h.value;
    }

    public T[] getValues(T[] array) {
        ReplayProcessor$TimedNode<T> h = getHead();
        int s = size(h);
        if (s != 0) {
            if (array.length < s) {
                array = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), s));
            }
            int i = 0;
            while (i != s) {
                ReplayProcessor$TimedNode<T> next = (ReplayProcessor$TimedNode) h.get();
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

    ReplayProcessor$TimedNode<T> getHead() {
        ReplayProcessor$TimedNode<T> index = this.head;
        long limit = this.scheduler.now(this.unit) - this.maxAge;
        ReplayProcessor$TimedNode<T> next = (ReplayProcessor$TimedNode) index.get();
        while (next != null && next.time <= limit) {
            index = next;
            next = (ReplayProcessor$TimedNode) index.get();
        }
        return index;
    }

    public void replay(ReplayProcessor$ReplaySubscription<T> rs) {
        if (rs.getAndIncrement() == 0) {
            int missed = 1;
            Subscriber<? super T> a = rs.actual;
            ReplayProcessor$TimedNode<T> index = rs.index;
            if (index == null) {
                index = getHead();
            }
            long e = rs.emitted;
            do {
                Throwable ex;
                long r = rs.requested.get();
                while (e != r) {
                    if (rs.cancelled) {
                        rs.index = null;
                        return;
                    }
                    boolean d = this.done;
                    ReplayProcessor$TimedNode<T> next = (ReplayProcessor$TimedNode) index.get();
                    boolean empty = next == null;
                    if (d && empty) {
                        rs.index = null;
                        rs.cancelled = true;
                        ex = this.error;
                        if (ex == null) {
                            a.onComplete();
                            return;
                        } else {
                            a.onError(ex);
                            return;
                        }
                    } else if (empty) {
                        break;
                    } else {
                        a.onNext(next.value);
                        e++;
                        index = next;
                    }
                }
                if (e == r) {
                    if (rs.cancelled) {
                        rs.index = null;
                        return;
                    } else if (this.done && index.get() == null) {
                        rs.index = null;
                        rs.cancelled = true;
                        ex = this.error;
                        if (ex == null) {
                            a.onComplete();
                            return;
                        } else {
                            a.onError(ex);
                            return;
                        }
                    }
                }
                rs.index = index;
                rs.emitted = e;
                missed = rs.addAndGet(-missed);
            } while (missed != 0);
        }
    }

    public int size() {
        return size(getHead());
    }

    int size(ReplayProcessor$TimedNode<T> h) {
        int s = 0;
        while (s != Integer.MAX_VALUE) {
            ReplayProcessor$TimedNode<T> next = (ReplayProcessor$TimedNode) h.get();
            if (next == null) {
                break;
            }
            s++;
            h = next;
        }
        return s;
    }

    public Throwable getError() {
        return this.error;
    }

    public boolean isDone() {
        return this.done;
    }
}
