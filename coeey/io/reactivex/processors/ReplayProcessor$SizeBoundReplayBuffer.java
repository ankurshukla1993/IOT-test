package io.reactivex.processors;

import io.reactivex.internal.functions.ObjectHelper;
import java.lang.reflect.Array;
import org.reactivestreams.Subscriber;

final class ReplayProcessor$SizeBoundReplayBuffer<T> implements ReplayProcessor$ReplayBuffer<T> {
    volatile boolean done;
    Throwable error;
    volatile ReplayProcessor$Node<T> head;
    final int maxSize;
    int size;
    ReplayProcessor$Node<T> tail;

    ReplayProcessor$SizeBoundReplayBuffer(int maxSize) {
        this.maxSize = ObjectHelper.verifyPositive(maxSize, "maxSize");
        ReplayProcessor$Node<T> h = new ReplayProcessor$Node(null);
        this.tail = h;
        this.head = h;
    }

    void trim() {
        if (this.size > this.maxSize) {
            this.size--;
            this.head = (ReplayProcessor$Node) this.head.get();
        }
    }

    public void next(T value) {
        ReplayProcessor$Node<T> n = new ReplayProcessor$Node(value);
        ReplayProcessor$Node<T> t = this.tail;
        this.tail = n;
        this.size++;
        t.set(n);
        trim();
    }

    public void error(Throwable ex) {
        this.error = ex;
        this.done = true;
    }

    public void complete() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    public Throwable getError() {
        return this.error;
    }

    public T getValue() {
        ReplayProcessor$Node<T> h = this.head;
        while (true) {
            ReplayProcessor$Node<T> n = (ReplayProcessor$Node) h.get();
            if (n == null) {
                return h.value;
            }
            h = n;
        }
    }

    public T[] getValues(T[] array) {
        int s = 0;
        ReplayProcessor$Node<T> h = this.head;
        ReplayProcessor$Node<T> h0 = h;
        while (true) {
            ReplayProcessor$Node<T> next = (ReplayProcessor$Node) h0.get();
            if (next == null) {
                break;
            }
            s++;
            h0 = next;
        }
        if (array.length < s) {
            array = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), s));
        }
        for (int j = 0; j < s; j++) {
            h = (ReplayProcessor$Node) h.get();
            array[j] = h.value;
        }
        if (array.length > s) {
            array[s] = null;
        }
        return array;
    }

    public void replay(ReplayProcessor$ReplaySubscription<T> rs) {
        if (rs.getAndIncrement() == 0) {
            int missed = 1;
            Subscriber<? super T> a = rs.actual;
            ReplayProcessor$Node<T> index = rs.index;
            if (index == null) {
                index = this.head;
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
                    ReplayProcessor$Node<T> next = (ReplayProcessor$Node) index.get();
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
        int s = 0;
        ReplayProcessor$Node<T> h = this.head;
        while (s != Integer.MAX_VALUE) {
            ReplayProcessor$Node<T> next = (ReplayProcessor$Node) h.get();
            if (next == null) {
                break;
            }
            s++;
            h = next;
        }
        return s;
    }
}
