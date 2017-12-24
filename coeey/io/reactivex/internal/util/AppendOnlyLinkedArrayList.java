package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Predicate;
import org.reactivestreams.Subscriber;

public class AppendOnlyLinkedArrayList<T> {
    final int capacity;
    final Object[] head;
    int offset;
    Object[] tail = this.head;

    public interface NonThrowingPredicate<T> extends Predicate<T> {
        boolean test(T t);
    }

    public AppendOnlyLinkedArrayList(int capacity) {
        this.capacity = capacity;
        this.head = new Object[(capacity + 1)];
    }

    public void add(T value) {
        int c = this.capacity;
        int o = this.offset;
        if (o == c) {
            Object[] next = new Object[(c + 1)];
            this.tail[c] = next;
            this.tail = next;
            o = 0;
        }
        this.tail[o] = value;
        this.offset = o + 1;
    }

    public void setFirst(T value) {
        this.head[0] = value;
    }

    public void forEachWhile(NonThrowingPredicate<? super T> consumer) {
        int c = this.capacity;
        for (Object[] a = this.head; a != null; a = (Object[]) a[c]) {
            for (int i = 0; i < c; i++) {
                Object o = a[i];
                if (o == null || consumer.test(o)) {
                    break;
                }
            }
        }
    }

    public <U> boolean accept(Subscriber<? super U> subscriber) {
        int c = this.capacity;
        for (Object[] a = this.head; a != null; a = (Object[]) a[c]) {
            int i = 0;
            while (i < c) {
                Object o = a[i];
                if (o == null) {
                    continue;
                    break;
                } else if (NotificationLite.acceptFull(o, (Subscriber) subscriber)) {
                    return true;
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    public <U> boolean accept(Observer<? super U> observer) {
        int c = this.capacity;
        for (Object[] a = this.head; a != null; a = (Object[]) a[c]) {
            int i = 0;
            while (i < c) {
                Object o = a[i];
                if (o == null) {
                    continue;
                    break;
                } else if (NotificationLite.acceptFull(o, (Observer) observer)) {
                    return true;
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    public <S> void forEachWhile(S state, BiPredicate<? super S, ? super T> consumer) throws Exception {
        Object[] a = this.head;
        int c = this.capacity;
        while (true) {
            int i = 0;
            while (i < c) {
                Object o = a[i];
                if (o != null && !consumer.test(state, o)) {
                    i++;
                } else {
                    return;
                }
            }
            a = (Object[]) a[c];
        }
    }
}
