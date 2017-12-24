package io.reactivex.processors;

import io.reactivex.internal.functions.ObjectHelper;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.reactivestreams.Subscriber;

final class ReplayProcessor$UnboundedReplayBuffer<T> implements ReplayProcessor$ReplayBuffer<T> {
    final List<T> buffer;
    volatile boolean done;
    Throwable error;
    volatile int size;

    ReplayProcessor$UnboundedReplayBuffer(int capacityHint) {
        this.buffer = new ArrayList(ObjectHelper.verifyPositive(capacityHint, "capacityHint"));
    }

    public void next(T value) {
        this.buffer.add(value);
        this.size++;
    }

    public void error(Throwable ex) {
        this.error = ex;
        this.done = true;
    }

    public void complete() {
        this.done = true;
    }

    public T getValue() {
        int s = this.size;
        if (s == 0) {
            return null;
        }
        return this.buffer.get(s - 1);
    }

    public T[] getValues(T[] array) {
        int s = this.size;
        if (s == 0) {
            if (array.length != 0) {
                array[0] = null;
            }
            return array;
        }
        List<T> b = this.buffer;
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

    public void replay(ReplayProcessor$ReplaySubscription<T> rs) {
        if (rs.getAndIncrement() == 0) {
            int index;
            int missed = 1;
            List<T> b = this.buffer;
            Subscriber<? super T> a = rs.actual;
            Integer indexObject = rs.index;
            if (indexObject != null) {
                index = indexObject.intValue();
            } else {
                index = 0;
                rs.index = Integer.valueOf(0);
            }
            long e = rs.emitted;
            do {
                boolean d;
                int s;
                Throwable ex;
                long r = rs.requested.get();
                while (e != r) {
                    if (rs.cancelled) {
                        rs.index = null;
                        return;
                    }
                    d = this.done;
                    s = this.size;
                    if (d && index == s) {
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
                    } else if (index == s) {
                        break;
                    } else {
                        a.onNext(b.get(index));
                        index++;
                        e++;
                    }
                }
                if (e == r) {
                    if (rs.cancelled) {
                        rs.index = null;
                        return;
                    }
                    d = this.done;
                    s = this.size;
                    if (d && index == s) {
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
                rs.index = Integer.valueOf(index);
                rs.emitted = e;
                missed = rs.addAndGet(-missed);
            } while (missed != 0);
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isDone() {
        return this.done;
    }

    public Throwable getError() {
        return this.error;
    }
}
