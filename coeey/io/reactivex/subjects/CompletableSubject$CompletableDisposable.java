package io.reactivex.subjects;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

final class CompletableSubject$CompletableDisposable extends AtomicReference<CompletableSubject> implements Disposable {
    private static final long serialVersionUID = -7650903191002190468L;
    final CompletableObserver actual;

    CompletableSubject$CompletableDisposable(CompletableObserver actual, CompletableSubject parent) {
        this.actual = actual;
        lazySet(parent);
    }

    public void dispose() {
        CompletableSubject parent = (CompletableSubject) getAndSet(null);
        if (parent != null) {
            parent.remove(this);
        }
    }

    public boolean isDisposed() {
        return get() == null;
    }
}
