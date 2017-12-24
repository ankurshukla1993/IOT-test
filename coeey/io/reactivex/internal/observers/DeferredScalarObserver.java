package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public abstract class DeferredScalarObserver<T, R> extends DeferredScalarDisposable<R> implements Observer<T> {
    private static final long serialVersionUID = -266195175408988651L;
    protected Disposable f2483s;

    public DeferredScalarObserver(Observer<? super R> actual) {
        super(actual);
    }

    public void onSubscribe(Disposable s) {
        if (DisposableHelper.validate(this.f2483s, s)) {
            this.f2483s = s;
            this.actual.onSubscribe(this);
        }
    }

    public void onError(Throwable t) {
        this.value = null;
        error(t);
    }

    public void onComplete() {
        R v = this.value;
        if (v != null) {
            this.value = null;
            complete(v);
            return;
        }
        complete();
    }

    public void dispose() {
        super.dispose();
        this.f2483s.dispose();
    }
}
