package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;

public final class CompletableOnErrorComplete extends Completable {
    final Predicate<? super Throwable> predicate;
    final CompletableSource source;

    final class OnError implements CompletableObserver {
        private final CompletableObserver f2499s;

        OnError(CompletableObserver s) {
            this.f2499s = s;
        }

        public void onComplete() {
            this.f2499s.onComplete();
        }

        public void onError(Throwable e) {
            try {
                if (CompletableOnErrorComplete.this.predicate.test(e)) {
                    this.f2499s.onComplete();
                } else {
                    this.f2499s.onError(e);
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.f2499s.onError(new CompositeException(e, ex));
            }
        }

        public void onSubscribe(Disposable d) {
            this.f2499s.onSubscribe(d);
        }
    }

    public CompletableOnErrorComplete(CompletableSource source, Predicate<? super Throwable> predicate) {
        this.source = source;
        this.predicate = predicate;
    }

    protected void subscribeActual(CompletableObserver s) {
        this.source.subscribe(new OnError(s));
    }
}
