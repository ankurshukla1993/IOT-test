package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;

public final class SingleEquals<T> extends Single<Boolean> {
    final SingleSource<? extends T> first;
    final SingleSource<? extends T> second;

    static class InnerObserver<T> implements SingleObserver<T> {
        final AtomicInteger count;
        final int index;
        final SingleObserver<? super Boolean> f2789s;
        final CompositeDisposable set;
        final Object[] values;

        InnerObserver(int index, CompositeDisposable set, Object[] values, SingleObserver<? super Boolean> s, AtomicInteger count) {
            this.index = index;
            this.set = set;
            this.values = values;
            this.f2789s = s;
            this.count = count;
        }

        public void onSubscribe(Disposable d) {
            this.set.add(d);
        }

        public void onSuccess(T value) {
            this.values[this.index] = value;
            if (this.count.incrementAndGet() == 2) {
                this.f2789s.onSuccess(Boolean.valueOf(ObjectHelper.equals(this.values[0], this.values[1])));
            }
        }

        public void onError(Throwable e) {
            int state;
            do {
                state = this.count.get();
                if (state >= 2) {
                    RxJavaPlugins.onError(e);
                    return;
                }
            } while (!this.count.compareAndSet(state, 2));
            this.set.dispose();
            this.f2789s.onError(e);
        }
    }

    public SingleEquals(SingleSource<? extends T> first, SingleSource<? extends T> second) {
        this.first = first;
        this.second = second;
    }

    protected void subscribeActual(SingleObserver<? super Boolean> s) {
        AtomicInteger count = new AtomicInteger();
        Object[] values = new Object[]{null, null};
        CompositeDisposable set = new CompositeDisposable();
        s.onSubscribe(set);
        this.first.subscribe(new InnerObserver(0, set, values, s, count));
        this.second.subscribe(new InnerObserver(1, set, values, s, count));
    }
}
