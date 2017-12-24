package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;

public final class FlowableRangeLong extends Flowable<Long> {
    final long end;
    final long start;

    static abstract class BaseRangeSubscription extends BasicQueueSubscription<Long> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final long end;
        long index;

        abstract void fastPath();

        abstract void slowPath(long j);

        BaseRangeSubscription(long index, long end) {
            this.index = index;
            this.end = end;
        }

        public final int requestFusion(int mode) {
            return mode & 1;
        }

        @Nullable
        public final Long poll() {
            long i = this.index;
            if (i == this.end) {
                return null;
            }
            this.index = 1 + i;
            return Long.valueOf(i);
        }

        public final boolean isEmpty() {
            return this.index == this.end;
        }

        public final void clear() {
            this.index = this.end;
        }

        public final void request(long n) {
            if (!SubscriptionHelper.validate(n) || BackpressureHelper.add(this, n) != 0) {
                return;
            }
            if (n == Long.MAX_VALUE) {
                fastPath();
            } else {
                slowPath(n);
            }
        }

        public final void cancel() {
            this.cancelled = true;
        }
    }

    static final class RangeConditionalSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super Long> actual;

        RangeConditionalSubscription(ConditionalSubscriber<? super Long> actual, long index, long end) {
            super(index, end);
            this.actual = actual;
        }

        void fastPath() {
            long f = this.end;
            ConditionalSubscriber<? super Long> a = this.actual;
            long i = this.index;
            while (i != f) {
                if (!this.cancelled) {
                    a.tryOnNext(Long.valueOf(i));
                    i++;
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                a.onComplete();
            }
        }

        void slowPath(long r) {
            long e = 0;
            long f = this.end;
            long i = this.index;
            ConditionalSubscriber<? super Long> a = this.actual;
            while (true) {
                if (e == r || i == f) {
                    if (i == f) {
                        break;
                    }
                    r = get();
                    if (e == r) {
                        this.index = i;
                        r = addAndGet(-e);
                        if (r != 0) {
                            e = 0;
                        } else {
                            return;
                        }
                    }
                    continue;
                } else if (!this.cancelled) {
                    if (a.tryOnNext(Long.valueOf(i))) {
                        e++;
                    }
                    i++;
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                a.onComplete();
            }
        }
    }

    static final class RangeSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super Long> actual;

        RangeSubscription(Subscriber<? super Long> actual, long index, long end) {
            super(index, end);
            this.actual = actual;
        }

        void fastPath() {
            long f = this.end;
            Subscriber<? super Long> a = this.actual;
            long i = this.index;
            while (i != f) {
                if (!this.cancelled) {
                    a.onNext(Long.valueOf(i));
                    i++;
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                a.onComplete();
            }
        }

        void slowPath(long r) {
            long e = 0;
            long f = this.end;
            long i = this.index;
            Subscriber<? super Long> a = this.actual;
            while (true) {
                if (e == r || i == f) {
                    if (i == f) {
                        break;
                    }
                    r = get();
                    if (e == r) {
                        this.index = i;
                        r = addAndGet(-e);
                        if (r != 0) {
                            e = 0;
                        } else {
                            return;
                        }
                    }
                    continue;
                } else if (!this.cancelled) {
                    a.onNext(Long.valueOf(i));
                    e++;
                    i++;
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                a.onComplete();
            }
        }
    }

    public FlowableRangeLong(long start, long count) {
        this.start = start;
        this.end = start + count;
    }

    public void subscribeActual(Subscriber<? super Long> s) {
        if (s instanceof ConditionalSubscriber) {
            s.onSubscribe(new RangeConditionalSubscription((ConditionalSubscriber) s, this.start, this.end));
            return;
        }
        s.onSubscribe(new RangeSubscription(s, this.start, this.end));
    }
}
