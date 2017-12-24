package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.SequentialDisposable;

public final class CompletableResumeNext extends Completable {
    final Function<? super Throwable, ? extends CompletableSource> errorMapper;
    final CompletableSource source;

    final class ResumeNext implements CompletableObserver {
        final CompletableObserver f2501s;
        final SequentialDisposable sd;

        final class OnErrorObserver implements CompletableObserver {
            OnErrorObserver() {
            }

            public void onComplete() {
                ResumeNext.this.f2501s.onComplete();
            }

            public void onError(Throwable e) {
                ResumeNext.this.f2501s.onError(e);
            }

            public void onSubscribe(Disposable d) {
                ResumeNext.this.sd.update(d);
            }
        }

        ResumeNext(CompletableObserver s, SequentialDisposable sd) {
            this.f2501s = s;
            this.sd = sd;
        }

        public void onComplete() {
            this.f2501s.onComplete();
        }

        public void onError(Throwable e) {
            try {
                CompletableSource c = (CompletableSource) CompletableResumeNext.this.errorMapper.apply(e);
                if (c == null) {
                    NullPointerException npe = new NullPointerException("The CompletableConsumable returned is null");
                    npe.initCause(e);
                    this.f2501s.onError(npe);
                    return;
                }
                c.subscribe(new OnErrorObserver());
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.f2501s.onError(new CompositeException(ex, e));
            }
        }

        public void onSubscribe(Disposable d) {
            this.sd.update(d);
        }
    }

    public CompletableResumeNext(CompletableSource source, Function<? super Throwable, ? extends CompletableSource> errorMapper) {
        this.source = source;
        this.errorMapper = errorMapper;
    }

    protected void subscribeActual(CompletableObserver s) {
        SequentialDisposable sd = new SequentialDisposable();
        s.onSubscribe(sd);
        this.source.subscribe(new ResumeNext(s, sd));
    }
}
