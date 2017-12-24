package io.reactivex.subjects;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

final class MaybeSubject$MaybeDisposable<T> extends AtomicReference<MaybeSubject<T>> implements Disposable {
    private static final long serialVersionUID = -7650903191002190468L;
    final MaybeObserver<? super T> actual;

    MaybeSubject$MaybeDisposable(MaybeObserver<? super T> actual, MaybeSubject<T> parent) {
        this.actual = actual;
        lazySet(parent);
    }

    public void dispose() {
        MaybeSubject<T> parent = (MaybeSubject) getAndSet(null);
        if (parent != null) {
            parent.remove(this);
        }
    }

    public boolean isDisposed() {
        return get() == null;
    }
}
