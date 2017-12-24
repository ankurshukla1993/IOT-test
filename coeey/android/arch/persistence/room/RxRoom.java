package android.arch.persistence.room;

import android.arch.core.executor.AppToolkitTaskExecutor;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class RxRoom {
    public static final Object NOTHING = new Object();
    private static Scheduler sAppToolkitIOScheduler = new C00355();

    static class C00312 implements Function<Optional<T>, T> {
        C00312() {
        }

        public T apply(@NonNull Optional<T> optional) throws Exception {
            return optional.mValue;
        }
    }

    static class C00323 implements Predicate<Optional<T>> {
        C00323() {
        }

        public boolean test(@NonNull Optional<T> optional) throws Exception {
            return optional.mValue != null;
        }
    }

    static class C00355 extends Scheduler {
        C00355() {
        }

        public Worker createWorker() {
            final AtomicBoolean mDisposed = new AtomicBoolean(false);
            return new Worker() {
                public Disposable schedule(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                    DisposableRunnable disposable = new DisposableRunnable(run, mDisposed);
                    AppToolkitTaskExecutor.getInstance().executeOnDiskIO(run);
                    return disposable;
                }

                public void dispose() {
                    mDisposed.set(true);
                }

                public boolean isDisposed() {
                    return mDisposed.get();
                }
            };
        }
    }

    private static class DisposableRunnable implements Disposable, Runnable {
        private final Runnable mActual;
        private volatile boolean mDisposed = false;
        private final AtomicBoolean mGlobalDisposed;

        DisposableRunnable(Runnable actual, AtomicBoolean globalDisposed) {
            this.mActual = actual;
            this.mGlobalDisposed = globalDisposed;
        }

        public void dispose() {
            this.mDisposed = true;
        }

        public boolean isDisposed() {
            return this.mDisposed || this.mGlobalDisposed.get();
        }

        public void run() {
            if (!isDisposed()) {
                this.mActual.run();
            }
        }
    }

    static class Optional<T> {
        @Nullable
        final T mValue;

        Optional(@Nullable T value) {
            this.mValue = value;
        }
    }

    public static Flowable<Object> createFlowable(final RoomDatabase database, final String... tableNames) {
        return Flowable.create(new FlowableOnSubscribe<Object>() {
            public void subscribe(final FlowableEmitter<Object> emitter) throws Exception {
                final Observer observer = new Observer(tableNames) {
                    public void onInvalidated(@android.support.annotation.NonNull Set<String> set) {
                        if (!emitter.isCancelled()) {
                            emitter.onNext(RxRoom.NOTHING);
                        }
                    }
                };
                if (!emitter.isCancelled()) {
                    database.getInvalidationTracker().addObserver(observer);
                    emitter.setDisposable(Disposables.fromAction(new Action() {
                        public void run() throws Exception {
                            database.getInvalidationTracker().removeObserver(observer);
                        }
                    }));
                }
                if (!emitter.isCancelled()) {
                    emitter.onNext(RxRoom.NOTHING);
                }
            }
        }, BackpressureStrategy.LATEST);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static <T> Flowable<T> createFlowable(RoomDatabase database, String[] tableNames, final Callable<T> callable) {
        return createFlowable(database, tableNames).observeOn(sAppToolkitIOScheduler).map(new Function<Object, Optional<T>>() {
            public Optional<T> apply(@NonNull Object o) throws Exception {
                return new Optional(callable.call());
            }
        }).filter(new C00323()).map(new C00312());
    }
}
