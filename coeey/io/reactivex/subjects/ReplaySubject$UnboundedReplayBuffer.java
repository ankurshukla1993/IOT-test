package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class ReplaySubject$UnboundedReplayBuffer<T> extends AtomicReference<Object> implements ReplaySubject$ReplayBuffer<T> {
    private static final long serialVersionUID = -733876083048047795L;
    final List<Object> buffer;
    volatile boolean done;
    volatile int size;

    ReplaySubject$UnboundedReplayBuffer(int capacityHint) {
        this.buffer = new ArrayList(ObjectHelper.verifyPositive(capacityHint, "capacityHint"));
    }

    public void add(T value) {
        this.buffer.add(value);
        this.size++;
    }

    public void addFinal(Object notificationLite) {
        this.buffer.add(notificationLite);
        this.size++;
        this.done = true;
    }

    public T getValue() {
        int s = this.size;
        if (s == 0) {
            return null;
        }
        List<Object> b = this.buffer;
        Object o = b.get(s - 1);
        if (!NotificationLite.isComplete(o) && !NotificationLite.isError(o)) {
            return o;
        }
        if (s == 1) {
            return null;
        }
        return b.get(s - 2);
    }

    public T[] getValues(T[] array) {
        int s = this.size;
        if (s == 0) {
            if (array.length != 0) {
                array[0] = null;
            }
            return array;
        }
        List<Object> b = this.buffer;
        Object o = b.get(s - 1);
        if (NotificationLite.isComplete(o) || NotificationLite.isError(o)) {
            s--;
            if (s == 0) {
                if (array.length != 0) {
                    array[0] = null;
                }
                return array;
            }
        }
        if (array.length < s) {
            array = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), s));
        }
        for (int i = 0; i < s; i++) {
            array[i] = b.get(i);
        }
        if (array.length > s) {
            array[s] = null;
        }
        return array;
    }

    public void replay(ReplaySubject$ReplayDisposable<T> rs) {
        if (rs.getAndIncrement() == 0) {
            int index;
            int missed = 1;
            List<Object> b = this.buffer;
            Observer<? super T> a = rs.actual;
            Integer indexObject = rs.index;
            if (indexObject != null) {
                index = indexObject.intValue();
            } else {
                index = 0;
                rs.index = Integer.valueOf(0);
            }
            while (!rs.cancelled) {
                int s = this.size;
                while (s != index) {
                    if (rs.cancelled) {
                        rs.index = null;
                        return;
                    }
                    Object o = b.get(index);
                    if (this.done && index + 1 == s) {
                        s = this.size;
                        if (index + 1 == s) {
                            if (NotificationLite.isComplete(o)) {
                                a.onComplete();
                            } else {
                                a.onError(NotificationLite.getError(o));
                            }
                            rs.index = null;
                            rs.cancelled = true;
                            return;
                        }
                    }
                    a.onNext(o);
                    index++;
                }
                if (index == this.size) {
                    rs.index = Integer.valueOf(index);
                    missed = rs.addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
            rs.index = null;
        }
    }

    public int size() {
        int s = this.size;
        if (s == 0) {
            return 0;
        }
        Object o = this.buffer.get(s - 1);
        if (NotificationLite.isComplete(o) || NotificationLite.isError(o)) {
            return s - 1;
        }
        return s;
    }
}
