package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class QueueDrainHelper {
    static final long COMPLETED_MASK = Long.MIN_VALUE;
    static final long REQUESTED_MASK = Long.MAX_VALUE;

    private QueueDrainHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T, U> void drainMaxLoop(io.reactivex.internal.fuseable.SimplePlainQueue<T> r10, org.reactivestreams.Subscriber<? super U> r11, boolean r12, io.reactivex.disposables.Disposable r13, io.reactivex.internal.util.QueueDrain<T, U> r14) {
        /*
        r6 = 1;
    L_0x0001:
        r0 = r14.done();
        r7 = r10.poll();
        if (r7 != 0) goto L_0x001c;
    L_0x000b:
        r1 = 1;
    L_0x000c:
        r2 = r11;
        r3 = r12;
        r4 = r10;
        r5 = r14;
        r2 = checkTerminated(r0, r1, r2, r3, r4, r5);
        if (r2 == 0) goto L_0x001e;
    L_0x0016:
        if (r13 == 0) goto L_0x001b;
    L_0x0018:
        r13.dispose();
    L_0x001b:
        return;
    L_0x001c:
        r1 = 0;
        goto L_0x000c;
    L_0x001e:
        if (r1 == 0) goto L_0x0028;
    L_0x0020:
        r2 = -r6;
        r6 = r14.leave(r2);
        if (r6 != 0) goto L_0x0001;
    L_0x0027:
        goto L_0x001b;
    L_0x0028:
        r8 = r14.requested();
        r2 = 0;
        r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x0047;
    L_0x0032:
        r2 = r14.accept(r11, r7);
        if (r2 == 0) goto L_0x0001;
    L_0x0038:
        r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x0001;
    L_0x0041:
        r2 = 1;
        r14.produced(r2);
        goto L_0x0001;
    L_0x0047:
        r10.clear();
        if (r13 == 0) goto L_0x004f;
    L_0x004c:
        r13.dispose();
    L_0x004f:
        r2 = new io.reactivex.exceptions.MissingBackpressureException;
        r3 = "Could not emit value due to lack of requests.";
        r2.<init>(r3);
        r11.onError(r2);
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.util.QueueDrainHelper.drainMaxLoop(io.reactivex.internal.fuseable.SimplePlainQueue, org.reactivestreams.Subscriber, boolean, io.reactivex.disposables.Disposable, io.reactivex.internal.util.QueueDrain):void");
    }

    public static <T, U> boolean checkTerminated(boolean d, boolean empty, Subscriber<?> s, boolean delayError, SimpleQueue<?> q, QueueDrain<T, U> qd) {
        if (qd.cancelled()) {
            q.clear();
            return true;
        }
        if (d) {
            Throwable err;
            if (!delayError) {
                err = qd.error();
                if (err != null) {
                    q.clear();
                    s.onError(err);
                    return true;
                } else if (empty) {
                    s.onComplete();
                    return true;
                }
            } else if (empty) {
                err = qd.error();
                if (err != null) {
                    s.onError(err);
                    return true;
                }
                s.onComplete();
                return true;
            }
        }
        return false;
    }

    public static <T, U> void drainLoop(SimplePlainQueue<T> q, Observer<? super U> a, boolean delayError, Disposable dispose, ObservableQueueDrain<T, U> qd) {
        int missed = 1;
        while (!checkTerminated(qd.done(), q.isEmpty(), a, delayError, q, dispose, qd)) {
            while (true) {
                boolean d = qd.done();
                T v = q.poll();
                boolean empty = v == null;
                if (!checkTerminated(d, empty, a, delayError, q, dispose, qd)) {
                    if (empty) {
                        missed = qd.leave(-missed);
                        if (missed == 0) {
                            return;
                        }
                    }
                    qd.accept(a, v);
                } else {
                    return;
                }
            }
        }
    }

    public static <T, U> boolean checkTerminated(boolean d, boolean empty, Observer<?> s, boolean delayError, SimpleQueue<?> q, Disposable disposable, ObservableQueueDrain<T, U> qd) {
        if (qd.cancelled()) {
            q.clear();
            disposable.dispose();
            return true;
        }
        if (d) {
            Throwable err;
            if (!delayError) {
                err = qd.error();
                if (err != null) {
                    q.clear();
                    disposable.dispose();
                    s.onError(err);
                    return true;
                } else if (empty) {
                    disposable.dispose();
                    s.onComplete();
                    return true;
                }
            } else if (empty) {
                disposable.dispose();
                err = qd.error();
                if (err != null) {
                    s.onError(err);
                    return true;
                }
                s.onComplete();
                return true;
            }
        }
        return false;
    }

    public static <T> SimpleQueue<T> createQueue(int capacityHint) {
        if (capacityHint < 0) {
            return new SpscLinkedArrayQueue(-capacityHint);
        }
        return new SpscArrayQueue(capacityHint);
    }

    public static void request(Subscription s, int prefetch) {
        s.request(prefetch < 0 ? Long.MAX_VALUE : (long) prefetch);
    }

    public static <T> boolean postCompleteRequest(long n, Subscriber<? super T> actual, Queue<T> queue, AtomicLong state, BooleanSupplier isCancelled) {
        long r;
        do {
            r = state.get();
        } while (!state.compareAndSet(r, (Long.MIN_VALUE & r) | BackpressureHelper.addCap(r & Long.MAX_VALUE, n)));
        if (r != Long.MIN_VALUE) {
            return false;
        }
        postCompleteDrain(Long.MIN_VALUE | n, actual, queue, state, isCancelled);
        return true;
    }

    static boolean isCancelled(BooleanSupplier cancelled) {
        try {
            return cancelled.getAsBoolean();
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            return true;
        }
    }

    static <T> boolean postCompleteDrain(long n, Subscriber<? super T> actual, Queue<T> queue, AtomicLong state, BooleanSupplier isCancelled) {
        long e = n & Long.MIN_VALUE;
        while (true) {
            if (e != n) {
                if (isCancelled(isCancelled)) {
                    return true;
                }
                T t = queue.poll();
                if (t == null) {
                    actual.onComplete();
                    return true;
                }
                actual.onNext(t);
                e++;
            } else if (isCancelled(isCancelled)) {
                return true;
            } else {
                if (queue.isEmpty()) {
                    actual.onComplete();
                    return true;
                }
                n = state.get();
                if (n == e) {
                    n = state.addAndGet(-(Long.MAX_VALUE & e));
                    if ((Long.MAX_VALUE & n) == 0) {
                        return false;
                    }
                    e = n & Long.MIN_VALUE;
                } else {
                    continue;
                }
            }
        }
    }

    public static <T> void postComplete(Subscriber<? super T> actual, Queue<T> queue, AtomicLong state, BooleanSupplier isCancelled) {
        if (queue.isEmpty()) {
            actual.onComplete();
        } else if (!postCompleteDrain(state.get(), actual, queue, state, isCancelled)) {
            long r;
            long u;
            do {
                r = state.get();
                if ((r & Long.MIN_VALUE) == 0) {
                    u = r | Long.MIN_VALUE;
                } else {
                    return;
                }
            } while (!state.compareAndSet(r, u));
            if (r != 0) {
                postCompleteDrain(u, actual, queue, state, isCancelled);
            }
        }
    }
}
