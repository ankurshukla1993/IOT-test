package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.observers.BasicIntQueueDisposable;

public final class ObservableRange extends Observable<Integer> {
    private final long end;
    private final int start;

    static final class RangeDisposable extends BasicIntQueueDisposable<Integer> {
        private static final long serialVersionUID = 396518478098735504L;
        final Observer<? super Integer> actual;
        final long end;
        boolean fused;
        long index;

        RangeDisposable(Observer<? super Integer> actual, long start, long end) {
            this.actual = actual;
            this.index = start;
            this.end = end;
        }

        void run() {
            if (!this.fused) {
                Observer<? super Integer> actual = this.actual;
                long e = this.end;
                for (long i = this.index; i != e && get() == 0; i++) {
                    actual.onNext(Integer.valueOf((int) i));
                }
                if (get() == 0) {
                    lazySet(1);
                    actual.onComplete();
                }
            }
        }

        @Nullable
        public Integer poll() throws Exception {
            long i = this.index;
            if (i != this.end) {
                this.index = 1 + i;
                return Integer.valueOf((int) i);
            }
            lazySet(1);
            return null;
        }

        public boolean isEmpty() {
            return this.index == this.end;
        }

        public void clear() {
            this.index = this.end;
            lazySet(1);
        }

        public void dispose() {
            set(1);
        }

        public boolean isDisposed() {
            return get() != 0;
        }

        public int requestFusion(int mode) {
            if ((mode & 1) == 0) {
                return 0;
            }
            this.fused = true;
            return 1;
        }
    }

    public ObservableRange(int start, int count) {
        this.start = start;
        this.end = ((long) start) + ((long) count);
    }

    protected void subscribeActual(Observer<? super Integer> o) {
        RangeDisposable parent = new RangeDisposable(o, (long) this.start, this.end);
        o.onSubscribe(parent);
        parent.run();
    }
}
