package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;

public final class FlowableRange extends Flowable<Integer> {
    final int end;
    final int start;

    static abstract class BaseRangeSubscription extends BasicQueueSubscription<Integer> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final int end;
        int index;

        abstract void fastPath();

        abstract void slowPath(long j);

        BaseRangeSubscription(int index, int end) {
            this.index = index;
            this.end = end;
        }

        public final int requestFusion(int mode) {
            return mode & 1;
        }

        @Nullable
        public final Integer poll() {
            int i = this.index;
            if (i == this.end) {
                return null;
            }
            this.index = i + 1;
            return Integer.valueOf(i);
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
        final ConditionalSubscriber<? super Integer> actual;

        RangeConditionalSubscription(ConditionalSubscriber<? super Integer> actual, int index, int end) {
            super(index, end);
            this.actual = actual;
        }

        void fastPath() {
            int f = this.end;
            ConditionalSubscriber<? super Integer> a = this.actual;
            int i = this.index;
            while (i != f) {
                if (!this.cancelled) {
                    a.tryOnNext(Integer.valueOf(i));
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
            int f = this.end;
            int i = this.index;
            ConditionalSubscriber<? super Integer> a = this.actual;
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
                    if (a.tryOnNext(Integer.valueOf(i))) {
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
        final Subscriber<? super Integer> actual;

        RangeSubscription(Subscriber<? super Integer> actual, int index, int end) {
            super(index, end);
            this.actual = actual;
        }

        void fastPath() {
            int f = this.end;
            Subscriber<? super Integer> a = this.actual;
            int i = this.index;
            while (i != f) {
                if (!this.cancelled) {
                    a.onNext(Integer.valueOf(i));
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
            int f = this.end;
            int i = this.index;
            Subscriber<? super Integer> a = this.actual;
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
                    a.onNext(Integer.valueOf(i));
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

    public FlowableRange(int start, int count) {
        this.start = start;
        this.end = start + count;
    }

    public void subscribeActual(Subscriber<? super Integer> s) {
        if (s instanceof ConditionalSubscriber) {
            s.onSubscribe(new RangeConditionalSubscription((ConditionalSubscriber) s, this.start, this.end));
        } else {
            s.onSubscribe(new RangeSubscription(s, this.start, this.end));
        }
    }
}
