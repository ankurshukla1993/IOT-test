package io.reactivex.internal.schedulers;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.annotations.Experimental;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.UnicastProcessor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Experimental
public class SchedulerWhen extends Scheduler implements Disposable {
    static final Disposable DISPOSED = Disposables.disposed();
    static final Disposable SUBSCRIBED = new SubscribedDisposable();
    private final Scheduler actualScheduler;
    private Disposable disposable;
    private final FlowableProcessor<Flowable<Completable>> workerProcessor = UnicastProcessor.create().toSerialized();

    static final class CreateWorkerFunction implements Function<ScheduledAction, Completable> {
        final Worker actualWorker;

        final class WorkerCompletable extends Completable {
            final ScheduledAction action;

            WorkerCompletable(ScheduledAction action) {
                this.action = action;
            }

            protected void subscribeActual(CompletableObserver actionCompletable) {
                actionCompletable.onSubscribe(this.action);
                this.action.call(CreateWorkerFunction.this.actualWorker, actionCompletable);
            }
        }

        CreateWorkerFunction(Worker actualWorker) {
            this.actualWorker = actualWorker;
        }

        public Completable apply(ScheduledAction action) {
            return new WorkerCompletable(action);
        }
    }

    static abstract class ScheduledAction extends AtomicReference<Disposable> implements Disposable {
        protected abstract Disposable callActual(Worker worker, CompletableObserver completableObserver);

        ScheduledAction() {
            super(SchedulerWhen.SUBSCRIBED);
        }

        void call(Worker actualWorker, CompletableObserver actionCompletable) {
            Disposable oldState = (Disposable) get();
            if (oldState != SchedulerWhen.DISPOSED && oldState == SchedulerWhen.SUBSCRIBED) {
                Disposable newState = callActual(actualWorker, actionCompletable);
                if (!compareAndSet(SchedulerWhen.SUBSCRIBED, newState)) {
                    newState.dispose();
                }
            }
        }

        public boolean isDisposed() {
            return ((Disposable) get()).isDisposed();
        }

        public void dispose() {
            Disposable newState = SchedulerWhen.DISPOSED;
            Disposable oldState;
            do {
                oldState = (Disposable) get();
                if (oldState == SchedulerWhen.DISPOSED) {
                    return;
                }
            } while (!compareAndSet(oldState, newState));
            if (oldState != SchedulerWhen.SUBSCRIBED) {
                oldState.dispose();
            }
        }
    }

    static class DelayedAction extends ScheduledAction {
        private final Runnable action;
        private final long delayTime;
        private final TimeUnit unit;

        DelayedAction(Runnable action, long delayTime, TimeUnit unit) {
            this.action = action;
            this.delayTime = delayTime;
            this.unit = unit;
        }

        protected Disposable callActual(Worker actualWorker, CompletableObserver actionCompletable) {
            return actualWorker.schedule(new OnCompletedAction(this.action, actionCompletable), this.delayTime, this.unit);
        }
    }

    static class ImmediateAction extends ScheduledAction {
        private final Runnable action;

        ImmediateAction(Runnable action) {
            this.action = action;
        }

        protected Disposable callActual(Worker actualWorker, CompletableObserver actionCompletable) {
            return actualWorker.schedule(new OnCompletedAction(this.action, actionCompletable));
        }
    }

    static class OnCompletedAction implements Runnable {
        final Runnable action;
        final CompletableObserver actionCompletable;

        OnCompletedAction(Runnable action, CompletableObserver actionCompletable) {
            this.action = action;
            this.actionCompletable = actionCompletable;
        }

        public void run() {
            try {
                this.action.run();
            } finally {
                this.actionCompletable.onComplete();
            }
        }
    }

    static final class QueueWorker extends Worker {
        private final FlowableProcessor<ScheduledAction> actionProcessor;
        private final Worker actualWorker;
        private final AtomicBoolean unsubscribed = new AtomicBoolean();

        QueueWorker(FlowableProcessor<ScheduledAction> actionProcessor, Worker actualWorker) {
            this.actionProcessor = actionProcessor;
            this.actualWorker = actualWorker;
        }

        public void dispose() {
            if (this.unsubscribed.compareAndSet(false, true)) {
                this.actionProcessor.onComplete();
                this.actualWorker.dispose();
            }
        }

        public boolean isDisposed() {
            return this.unsubscribed.get();
        }

        @NonNull
        public Disposable schedule(@NonNull Runnable action, long delayTime, @NonNull TimeUnit unit) {
            DelayedAction delayedAction = new DelayedAction(action, delayTime, unit);
            this.actionProcessor.onNext(delayedAction);
            return delayedAction;
        }

        @NonNull
        public Disposable schedule(@NonNull Runnable action) {
            ImmediateAction immediateAction = new ImmediateAction(action);
            this.actionProcessor.onNext(immediateAction);
            return immediateAction;
        }
    }

    static final class SubscribedDisposable implements Disposable {
        SubscribedDisposable() {
        }

        public void dispose() {
        }

        public boolean isDisposed() {
            return false;
        }
    }

    public SchedulerWhen(Function<Flowable<Flowable<Completable>>, Completable> combine, Scheduler actualScheduler) {
        this.actualScheduler = actualScheduler;
        try {
            this.disposable = ((Completable) combine.apply(this.workerProcessor)).subscribe();
        } catch (Throwable e) {
            Exceptions.propagate(e);
        }
    }

    public void dispose() {
        this.disposable.dispose();
    }

    public boolean isDisposed() {
        return this.disposable.isDisposed();
    }

    @NonNull
    public Worker createWorker() {
        Worker actualWorker = this.actualScheduler.createWorker();
        FlowableProcessor<ScheduledAction> actionProcessor = UnicastProcessor.create().toSerialized();
        Flowable<Completable> actions = actionProcessor.map(new CreateWorkerFunction(actualWorker));
        Worker worker = new QueueWorker(actionProcessor, actualWorker);
        this.workerProcessor.onNext(actions);
        return worker;
    }
}
