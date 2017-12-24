package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOperator;
import io.reactivex.CompletableSource;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

public final class CompletableLift extends Completable {
    final CompletableOperator onLift;
    final CompletableSource source;

    public CompletableLift(CompletableSource source, CompletableOperator onLift) {
        this.source = source;
        this.onLift = onLift;
    }

    protected void subscribeActual(CompletableObserver s) {
        try {
            this.source.subscribe(this.onLift.apply(s));
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Throwable ex2) {
            Exceptions.throwIfFatal(ex2);
            RxJavaPlugins.onError(ex2);
        }
    }
}
