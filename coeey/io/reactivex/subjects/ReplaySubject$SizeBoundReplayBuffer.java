package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicReference;

final class ReplaySubject$SizeBoundReplayBuffer<T> extends AtomicReference<Object> implements ReplaySubject$ReplayBuffer<T> {
    private static final long serialVersionUID = 1107649250281456395L;
    volatile boolean done;
    volatile ReplaySubject$Node<Object> head;
    final int maxSize;
    int size;
    ReplaySubject$Node<Object> tail;

    ReplaySubject$SizeBoundReplayBuffer(int maxSize) {
        this.maxSize = ObjectHelper.verifyPositive(maxSize, "maxSize");
        ReplaySubject$Node<Object> h = new ReplaySubject$Node(null);
        this.tail = h;
        this.head = h;
    }

    void trim() {
        if (this.size > this.maxSize) {
            this.size--;
            this.head = (ReplaySubject$Node) this.head.get();
        }
    }

    public void add(T value) {
        ReplaySubject$Node<Object> n = new ReplaySubject$Node(value);
        ReplaySubject$Node<Object> t = this.tail;
        this.tail = n;
        this.size++;
        t.set(n);
        trim();
    }

    public void addFinal(Object notificationLite) {
        ReplaySubject$Node<Object> n = new ReplaySubject$Node(notificationLite);
        ReplaySubject$Node<Object> t = this.tail;
        this.tail = n;
        this.size++;
        t.lazySet(n);
        this.done = true;
    }

    public T getValue() {
        ReplaySubject$Node<Object> prev = null;
        ReplaySubject$Node<Object> h = this.head;
        while (true) {
            ReplaySubject$Node<Object> next = (ReplaySubject$Node) h.get();
            if (next == null) {
                break;
            }
            prev = h;
            h = next;
        }
        Object v = h.value;
        if (v == null) {
            return null;
        }
        if (NotificationLite.isComplete(v) || NotificationLite.isError(v)) {
            return prev.value;
        }
        return v;
    }

    public T[] getValues(T[] array) {
        ReplaySubject$Node<Object> h = this.head;
        int s = size();
        if (s != 0) {
            if (array.length < s) {
                array = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), s));
            }
            int i = 0;
            while (i != s) {
                ReplaySubject$Node<Object> next = (ReplaySubject$Node) h.get();
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
            ReplaySubject$Node<Object> index = rs.index;
            if (index == null) {
                index = this.head;
            }
            while (!rs.cancelled) {
                ReplaySubject$Node<Object> n = (ReplaySubject$Node) index.get();
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
                } else {
                    continue;
                }
            }
            rs.index = null;
        }
    }

    public int size() {
        int s = 0;
        ReplaySubject$Node<Object> h = this.head;
        while (s != Integer.MAX_VALUE) {
            ReplaySubject$Node<Object> next = (ReplaySubject$Node) h.get();
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
