package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.plugins.RxJavaPlugins;

public abstract class BasicFuseableObserver<T, R> implements Observer<T>, QueueDisposable<R> {
    protected final Observer<? super R> actual;
    protected boolean done;
    protected QueueDisposable<T> qs;
    protected Disposable f2480s;
    protected int sourceMode;

    public BasicFuseableObserver(Observer<? super R> actual) {
        this.actual = actual;
    }

    public final void onSubscribe(Disposable s) {
        if (DisposableHelper.validate(this.f2480s, s)) {
            this.f2480s = s;
            if (s instanceof QueueDisposable) {
                this.qs = (QueueDisposable) s;
            }
            if (beforeDownstream()) {
                this.actual.onSubscribe(this);
                afterDownstream();
            }
        }
    }

    protected boolean beforeDownstream() {
        return true;
    }

    protected void afterDownstream() {
    }

    public void onError(Throwable t) {
        if (this.done) {
            RxJavaPlugins.onError(t);
            return;
        }
        this.done = true;
        this.actual.onError(t);
    }

    protected final void fail(Throwable t) {
        Exceptions.throwIfFatal(t);
        this.f2480s.dispose();
        onError(t);
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            this.actual.onComplete();
        }
    }

    protected final int transitiveBoundaryFusion(int mode) {
        QueueDisposable<T> qs = this.qs;
        if (qs == null || (mode & 4) != 0) {
            return 0;
        }
        int m = qs.requestFusion(mode);
        if (m == 0) {
            return m;
        }
        this.sourceMode = m;
        return m;
    }

    public void dispose() {
        this.f2480s.dispose();
    }

    public boolean isDisposed() {
        return this.f2480s.isDisposed();
    }

    public boolean isEmpty() {
        return this.qs.isEmpty();
    }

    public void clear() {
        this.qs.clear();
    }

    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public final boolean offer(R r, R r2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
