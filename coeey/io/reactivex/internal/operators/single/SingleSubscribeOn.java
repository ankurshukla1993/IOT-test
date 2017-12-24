package io.reactivex.internal.operators.single;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleSubscribeOn<T> extends Single<T> {
    final Scheduler scheduler;
    final SingleSource<? extends T> source;

    static final class SubscribeOnObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable, Runnable {
        private static final long serialVersionUID = 7000911171163930287L;
        final SingleObserver<? super T> actual;
        final SingleSource<? extends T> source;
        final SequentialDisposable task = new SequentialDisposable();

        SubscribeOnObserver(SingleObserver<? super T> actual, SingleSource<? extends T> source) {
            this.actual = actual;
            this.source = source;
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        public void onSuccess(T value) {
            this.actual.onSuccess(value);
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void run() {
            this.source.subscribe(this);
        }
    }

    public SingleSubscribeOn(SingleSource<? extends T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        SubscribeOnObserver<T> parent = new SubscribeOnObserver(s, this.source);
        s.onSubscribe(parent);
        parent.task.replace(this.scheduler.scheduleDirect(parent));
    }
}
