package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

final class PublishSubject$PublishDisposable<T> extends AtomicBoolean implements Disposable {
    private static final long serialVersionUID = 3562861878281475070L;
    final Observer<? super T> actual;
    final PublishSubject<T> parent;

    PublishSubject$PublishDisposable(Observer<? super T> actual, PublishSubject<T> parent) {
        this.actual = actual;
        this.parent = parent;
    }

    public void onNext(T t) {
        if (!get()) {
            this.actual.onNext(t);
        }
    }

    public void onError(Throwable t) {
        if (get()) {
            RxJavaPlugins.onError(t);
        } else {
            this.actual.onError(t);
        }
    }

    public void onComplete() {
        if (!get()) {
            this.actual.onComplete();
        }
    }

    public void dispose() {
        if (compareAndSet(false, true)) {
            this.parent.remove(this);
        }
    }

    public boolean isDisposed() {
        return get();
    }
}
