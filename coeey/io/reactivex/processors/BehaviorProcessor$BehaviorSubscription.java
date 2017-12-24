package io.reactivex.processors;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList.NonThrowingPredicate;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

final class BehaviorProcessor$BehaviorSubscription<T> extends AtomicLong implements Subscription, NonThrowingPredicate<Object> {
    private static final long serialVersionUID = 3293175281126227086L;
    final Subscriber<? super T> actual;
    volatile boolean cancelled;
    boolean emitting;
    boolean fastPath;
    long index;
    boolean next;
    AppendOnlyLinkedArrayList<Object> queue;
    final BehaviorProcessor<T> state;

    BehaviorProcessor$BehaviorSubscription(Subscriber<? super T> actual, BehaviorProcessor<T> state) {
        this.actual = actual;
        this.state = state;
    }

    public void request(long n) {
        if (SubscriptionHelper.validate(n)) {
            BackpressureHelper.add(this, n);
        }
    }

    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            this.state.remove(this);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void emitFirst() {
        /*
        r6 = this;
        r3 = 1;
        r4 = r6.cancelled;
        if (r4 == 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        monitor-enter(r6);
        r4 = r6.cancelled;	 Catch:{ all -> 0x000d }
        if (r4 == 0) goto L_0x0010;
    L_0x000b:
        monitor-exit(r6);	 Catch:{ all -> 0x000d }
        goto L_0x0005;
    L_0x000d:
        r3 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x000d }
        throw r3;
    L_0x0010:
        r4 = r6.next;	 Catch:{ all -> 0x000d }
        if (r4 == 0) goto L_0x0016;
    L_0x0014:
        monitor-exit(r6);	 Catch:{ all -> 0x000d }
        goto L_0x0005;
    L_0x0016:
        r2 = r6.state;	 Catch:{ all -> 0x000d }
        r1 = r2.readLock;	 Catch:{ all -> 0x000d }
        r1.lock();	 Catch:{ all -> 0x000d }
        r4 = r2.index;	 Catch:{ all -> 0x000d }
        r6.index = r4;	 Catch:{ all -> 0x000d }
        r4 = r2.value;	 Catch:{ all -> 0x000d }
        r0 = r4.get();	 Catch:{ all -> 0x000d }
        r1.unlock();	 Catch:{ all -> 0x000d }
        if (r0 == 0) goto L_0x003e;
    L_0x002c:
        r6.emitting = r3;	 Catch:{ all -> 0x000d }
        r3 = 1;
        r6.next = r3;	 Catch:{ all -> 0x000d }
        monitor-exit(r6);	 Catch:{ all -> 0x000d }
        if (r0 == 0) goto L_0x0005;
    L_0x0034:
        r3 = r6.test(r0);
        if (r3 != 0) goto L_0x0005;
    L_0x003a:
        r6.emitLoop();
        goto L_0x0005;
    L_0x003e:
        r3 = 0;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.BehaviorProcessor$BehaviorSubscription.emitFirst():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void emitNext(java.lang.Object r7, long r8) {
        /*
        r6 = this;
        r4 = 1;
        r1 = r6.cancelled;
        if (r1 == 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r1 = r6.fastPath;
        if (r1 != 0) goto L_0x0037;
    L_0x000a:
        monitor-enter(r6);
        r1 = r6.cancelled;	 Catch:{ all -> 0x0011 }
        if (r1 == 0) goto L_0x0014;
    L_0x000f:
        monitor-exit(r6);	 Catch:{ all -> 0x0011 }
        goto L_0x0005;
    L_0x0011:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0011 }
        throw r1;
    L_0x0014:
        r2 = r6.index;	 Catch:{ all -> 0x0011 }
        r1 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r1 != 0) goto L_0x001c;
    L_0x001a:
        monitor-exit(r6);	 Catch:{ all -> 0x0011 }
        goto L_0x0005;
    L_0x001c:
        r1 = r6.emitting;	 Catch:{ all -> 0x0011 }
        if (r1 == 0) goto L_0x0031;
    L_0x0020:
        r0 = r6.queue;	 Catch:{ all -> 0x0011 }
        if (r0 != 0) goto L_0x002c;
    L_0x0024:
        r0 = new io.reactivex.internal.util.AppendOnlyLinkedArrayList;	 Catch:{ all -> 0x0011 }
        r1 = 4;
        r0.<init>(r1);	 Catch:{ all -> 0x0011 }
        r6.queue = r0;	 Catch:{ all -> 0x0011 }
    L_0x002c:
        r0.add(r7);	 Catch:{ all -> 0x0011 }
        monitor-exit(r6);	 Catch:{ all -> 0x0011 }
        goto L_0x0005;
    L_0x0031:
        r1 = 1;
        r6.next = r1;	 Catch:{ all -> 0x0011 }
        monitor-exit(r6);	 Catch:{ all -> 0x0011 }
        r6.fastPath = r4;
    L_0x0037:
        r6.test(r7);
        goto L_0x0005;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.BehaviorProcessor$BehaviorSubscription.emitNext(java.lang.Object, long):void");
    }

    public boolean test(Object o) {
        if (this.cancelled) {
            return true;
        }
        if (NotificationLite.isComplete(o)) {
            this.actual.onComplete();
            return true;
        } else if (NotificationLite.isError(o)) {
            this.actual.onError(NotificationLite.getError(o));
            return true;
        } else {
            long r = get();
            if (r != 0) {
                this.actual.onNext(NotificationLite.getValue(o));
                if (r != Long.MAX_VALUE) {
                    decrementAndGet();
                }
                return false;
            }
            cancel();
            this.actual.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void emitLoop() {
        /*
        r2 = this;
    L_0x0000:
        r1 = r2.cancelled;
        if (r1 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        monitor-enter(r2);
        r0 = r2.queue;	 Catch:{ all -> 0x000f }
        if (r0 != 0) goto L_0x0012;
    L_0x000a:
        r1 = 0;
        r2.emitting = r1;	 Catch:{ all -> 0x000f }
        monitor-exit(r2);	 Catch:{ all -> 0x000f }
        goto L_0x0004;
    L_0x000f:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x000f }
        throw r1;
    L_0x0012:
        r1 = 0;
        r2.queue = r1;	 Catch:{ all -> 0x000f }
        monitor-exit(r2);	 Catch:{ all -> 0x000f }
        r0.forEachWhile(r2);
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.BehaviorProcessor$BehaviorSubscription.emitLoop():void");
    }

    public boolean isFull() {
        return get() == 0;
    }
}
