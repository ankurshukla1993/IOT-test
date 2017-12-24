package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;

public final class CompletableDoOnEvent extends Completable {
    final Consumer<? super Throwable> onEvent;
    final CompletableSource source;

    final class DoOnEvent implements CompletableObserver {
        private final CompletableObserver observer;

        DoOnEvent(CompletableObserver observer) {
            this.observer = observer;
        }

        public void onComplete() {
            try {
                CompletableDoOnEvent.this.onEvent.accept(null);
                this.observer.onComplete();
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                this.observer.onError(e);
            }
        }

        public void onError(Throwable e) {
            try {
                CompletableDoOnEvent.this.onEvent.accept(e);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                e = new CompositeException(e, ex);
            }
            this.observer.onError(e);
        }

        public void onSubscribe(Disposable d) {
            this.observer.onSubscribe(d);
        }
    }

    public CompletableDoOnEvent(CompletableSource source, Consumer<? super Throwable> onEvent) {
        this.source = source;
        this.onEvent = onEvent;
    }

    protected void subscribeActual(CompletableObserver s) {
        this.source.subscribe(new DoOnEvent(s));
    }
}
