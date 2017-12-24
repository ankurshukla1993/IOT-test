package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Monitor.Guard;
import com.google.common.util.concurrent.Service.Listener;
import com.google.common.util.concurrent.Service.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

@Beta
public abstract class AbstractService implements Service {
    private static final Callback<Listener> RUNNING_CALLBACK = new Callback<Listener>("running()") {
        void call(Listener listener) {
            listener.running();
        }
    };
    private static final Callback<Listener> STARTING_CALLBACK = new Callback<Listener>("starting()") {
        void call(Listener listener) {
            listener.starting();
        }
    };
    private static final Callback<Listener> STOPPING_FROM_RUNNING_CALLBACK = stoppingCallback(State.RUNNING);
    private static final Callback<Listener> STOPPING_FROM_STARTING_CALLBACK = stoppingCallback(State.STARTING);
    private static final Callback<Listener> TERMINATED_FROM_NEW_CALLBACK = terminatedCallback(State.NEW);
    private static final Callback<Listener> TERMINATED_FROM_RUNNING_CALLBACK = terminatedCallback(State.RUNNING);
    private static final Callback<Listener> TERMINATED_FROM_STOPPING_CALLBACK = terminatedCallback(State.STOPPING);
    private final Guard hasReachedRunning = new Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(State.RUNNING) >= 0;
        }
    };
    private final Guard isStartable = new Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state() == State.NEW;
        }
    };
    private final Guard isStoppable = new Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(State.RUNNING) <= 0;
        }
    };
    private final Guard isStopped = new Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().isTerminal();
        }
    };
    @GuardedBy("monitor")
    private final List<ListenerCallQueue<Listener>> listeners = Collections.synchronizedList(new ArrayList());
    private final Monitor monitor = new Monitor();
    @GuardedBy("monitor")
    private volatile StateSnapshot snapshot = new StateSnapshot(State.NEW);

    protected abstract void doStart();

    protected abstract void doStop();

    private static Callback<Listener> terminatedCallback(final State from) {
        String valueOf = String.valueOf(String.valueOf(from));
        return new Callback<Listener>(new StringBuilder(valueOf.length() + 21).append("terminated({from = ").append(valueOf).append("})").toString()) {
            void call(Listener listener) {
                listener.terminated(from);
            }
        };
    }

    private static Callback<Listener> stoppingCallback(final State from) {
        String valueOf = String.valueOf(String.valueOf(from));
        return new Callback<Listener>(new StringBuilder(valueOf.length() + 19).append("stopping({from = ").append(valueOf).append("})").toString()) {
            void call(Listener listener) {
                listener.stopping(from);
            }
        };
    }

    protected AbstractService() {
    }

    public final Service startAsync() {
        if (this.monitor.enterIf(this.isStartable)) {
            try {
                this.snapshot = new StateSnapshot(State.STARTING);
                starting();
                doStart();
            } catch (Throwable startupFailure) {
                notifyFailed(startupFailure);
            } finally {
                this.monitor.leave();
                executeListeners();
            }
            return this;
        }
        String valueOf = String.valueOf(String.valueOf(this));
        throw new IllegalStateException(new StringBuilder(valueOf.length() + 33).append("Service ").append(valueOf).append(" has already been started").toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.common.util.concurrent.Service stopAsync() {
        /*
        r6 = this;
        r2 = r6.monitor;
        r3 = r6.isStoppable;
        r2 = r2.enterIf(r3);
        if (r2 == 0) goto L_0x004c;
    L_0x000a:
        r0 = r6.state();	 Catch:{ Throwable -> 0x0040 }
        r2 = com.google.common.util.concurrent.AbstractService.AnonymousClass10.$SwitchMap$com$google$common$util$concurrent$Service$State;	 Catch:{ Throwable -> 0x0040 }
        r3 = r0.ordinal();	 Catch:{ Throwable -> 0x0040 }
        r2 = r2[r3];	 Catch:{ Throwable -> 0x0040 }
        switch(r2) {
            case 1: goto L_0x004d;
            case 2: goto L_0x0064;
            case 3: goto L_0x007f;
            case 4: goto L_0x0091;
            case 5: goto L_0x0091;
            case 6: goto L_0x0091;
            default: goto L_0x0019;
        };	 Catch:{ Throwable -> 0x0040 }
    L_0x0019:
        r2 = new java.lang.AssertionError;	 Catch:{ Throwable -> 0x0040 }
        r3 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0040 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Throwable -> 0x0040 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0040 }
        r5 = r3.length();	 Catch:{ Throwable -> 0x0040 }
        r5 = r5 + 18;
        r4.<init>(r5);	 Catch:{ Throwable -> 0x0040 }
        r5 = "Unexpected state: ";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x0040 }
        r3 = r4.append(r3);	 Catch:{ Throwable -> 0x0040 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0040 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0040 }
        throw r2;	 Catch:{ Throwable -> 0x0040 }
    L_0x0040:
        r1 = move-exception;
        r6.notifyFailed(r1);	 Catch:{ all -> 0x0075 }
        r2 = r6.monitor;
        r2.leave();
        r6.executeListeners();
    L_0x004c:
        return r6;
    L_0x004d:
        r2 = new com.google.common.util.concurrent.AbstractService$StateSnapshot;	 Catch:{ Throwable -> 0x0040 }
        r3 = com.google.common.util.concurrent.Service.State.TERMINATED;	 Catch:{ Throwable -> 0x0040 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0040 }
        r6.snapshot = r2;	 Catch:{ Throwable -> 0x0040 }
        r2 = com.google.common.util.concurrent.Service.State.NEW;	 Catch:{ Throwable -> 0x0040 }
        r6.terminated(r2);	 Catch:{ Throwable -> 0x0040 }
    L_0x005b:
        r2 = r6.monitor;
        r2.leave();
        r6.executeListeners();
        goto L_0x004c;
    L_0x0064:
        r2 = new com.google.common.util.concurrent.AbstractService$StateSnapshot;	 Catch:{ Throwable -> 0x0040 }
        r3 = com.google.common.util.concurrent.Service.State.STARTING;	 Catch:{ Throwable -> 0x0040 }
        r4 = 1;
        r5 = 0;
        r2.<init>(r3, r4, r5);	 Catch:{ Throwable -> 0x0040 }
        r6.snapshot = r2;	 Catch:{ Throwable -> 0x0040 }
        r2 = com.google.common.util.concurrent.Service.State.STARTING;	 Catch:{ Throwable -> 0x0040 }
        r6.stopping(r2);	 Catch:{ Throwable -> 0x0040 }
        goto L_0x005b;
    L_0x0075:
        r2 = move-exception;
        r3 = r6.monitor;
        r3.leave();
        r6.executeListeners();
        throw r2;
    L_0x007f:
        r2 = new com.google.common.util.concurrent.AbstractService$StateSnapshot;	 Catch:{ Throwable -> 0x0040 }
        r3 = com.google.common.util.concurrent.Service.State.STOPPING;	 Catch:{ Throwable -> 0x0040 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0040 }
        r6.snapshot = r2;	 Catch:{ Throwable -> 0x0040 }
        r2 = com.google.common.util.concurrent.Service.State.RUNNING;	 Catch:{ Throwable -> 0x0040 }
        r6.stopping(r2);	 Catch:{ Throwable -> 0x0040 }
        r6.doStop();	 Catch:{ Throwable -> 0x0040 }
        goto L_0x005b;
    L_0x0091:
        r2 = new java.lang.AssertionError;	 Catch:{ Throwable -> 0x0040 }
        r3 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0040 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Throwable -> 0x0040 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0040 }
        r5 = r3.length();	 Catch:{ Throwable -> 0x0040 }
        r5 = r5 + 45;
        r4.<init>(r5);	 Catch:{ Throwable -> 0x0040 }
        r5 = "isStoppable is incorrectly implemented, saw: ";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x0040 }
        r3 = r4.append(r3);	 Catch:{ Throwable -> 0x0040 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0040 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0040 }
        throw r2;	 Catch:{ Throwable -> 0x0040 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractService.stopAsync():com.google.common.util.concurrent.Service");
    }

    public final void awaitRunning() {
        this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
        try {
            checkCurrentState(State.RUNNING);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, timeout, unit)) {
            try {
                checkCurrentState(State.RUNNING);
            } finally {
                this.monitor.leave();
            }
        } else {
            String valueOf = String.valueOf(String.valueOf(this));
            String valueOf2 = String.valueOf(String.valueOf(state()));
            throw new TimeoutException(new StringBuilder((valueOf.length() + 66) + valueOf2.length()).append("Timed out waiting for ").append(valueOf).append(" to reach the RUNNING state. ").append("Current state: ").append(valueOf2).toString());
        }
    }

    public final void awaitTerminated() {
        this.monitor.enterWhenUninterruptibly(this.isStopped);
        try {
            checkCurrentState(State.TERMINATED);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.isStopped, timeout, unit)) {
            try {
                checkCurrentState(State.TERMINATED);
            } finally {
                this.monitor.leave();
            }
        } else {
            String valueOf = String.valueOf(String.valueOf(this));
            String valueOf2 = String.valueOf(String.valueOf(state()));
            throw new TimeoutException(new StringBuilder((valueOf.length() + 65) + valueOf2.length()).append("Timed out waiting for ").append(valueOf).append(" to reach a terminal state. ").append("Current state: ").append(valueOf2).toString());
        }
    }

    @GuardedBy("monitor")
    private void checkCurrentState(State expected) {
        State actual = state();
        if (actual == expected) {
            return;
        }
        if (actual == State.FAILED) {
            String valueOf = String.valueOf(String.valueOf(expected));
            throw new IllegalStateException(new StringBuilder(valueOf.length() + 55).append("Expected the service to be ").append(valueOf).append(", but the service has FAILED").toString(), failureCause());
        }
        valueOf = String.valueOf(String.valueOf(expected));
        String valueOf2 = String.valueOf(String.valueOf(actual));
        throw new IllegalStateException(new StringBuilder((valueOf.length() + 37) + valueOf2.length()).append("Expected the service to be ").append(valueOf).append(", but was ").append(valueOf2).toString());
    }

    protected final void notifyStarted() {
        this.monitor.enter();
        try {
            if (this.snapshot.state != State.STARTING) {
                String valueOf = String.valueOf(String.valueOf(this.snapshot.state));
                IllegalStateException failure = new IllegalStateException(new StringBuilder(valueOf.length() + 43).append("Cannot notifyStarted() when the service is ").append(valueOf).toString());
                notifyFailed(failure);
                throw failure;
            }
            if (this.snapshot.shutdownWhenStartupFinishes) {
                this.snapshot = new StateSnapshot(State.STOPPING);
                doStop();
            } else {
                this.snapshot = new StateSnapshot(State.RUNNING);
                running();
            }
            this.monitor.leave();
            executeListeners();
        } catch (Throwable th) {
            this.monitor.leave();
            executeListeners();
        }
    }

    protected final void notifyStopped() {
        this.monitor.enter();
        try {
            State previous = this.snapshot.state;
            if (previous == State.STOPPING || previous == State.RUNNING) {
                this.snapshot = new StateSnapshot(State.TERMINATED);
                terminated(previous);
                return;
            }
            String valueOf = String.valueOf(String.valueOf(previous));
            IllegalStateException failure = new IllegalStateException(new StringBuilder(valueOf.length() + 43).append("Cannot notifyStopped() when the service is ").append(valueOf).toString());
            notifyFailed(failure);
            throw failure;
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    protected final void notifyFailed(Throwable cause) {
        Preconditions.checkNotNull(cause);
        this.monitor.enter();
        try {
            State previous = state();
            String valueOf;
            switch (previous) {
                case NEW:
                case TERMINATED:
                    valueOf = String.valueOf(String.valueOf(previous));
                    throw new IllegalStateException(new StringBuilder(valueOf.length() + 22).append("Failed while in state:").append(valueOf).toString(), cause);
                case STARTING:
                case RUNNING:
                case STOPPING:
                    this.snapshot = new StateSnapshot(State.FAILED, false, cause);
                    failed(previous, cause);
                    break;
                case FAILED:
                    break;
                default:
                    valueOf = String.valueOf(String.valueOf(previous));
                    throw new AssertionError(new StringBuilder(valueOf.length() + 18).append("Unexpected state: ").append(valueOf).toString());
            }
            this.monitor.leave();
            executeListeners();
        } catch (Throwable th) {
            this.monitor.leave();
            executeListeners();
        }
    }

    public final boolean isRunning() {
        return state() == State.RUNNING;
    }

    public final State state() {
        return this.snapshot.externalState();
    }

    public final Throwable failureCause() {
        return this.snapshot.failureCause();
    }

    public final void addListener(Listener listener, Executor executor) {
        Preconditions.checkNotNull(listener, "listener");
        Preconditions.checkNotNull(executor, "executor");
        this.monitor.enter();
        try {
            if (!state().isTerminal()) {
                this.listeners.add(new ListenerCallQueue(listener, executor));
            }
            this.monitor.leave();
        } catch (Throwable th) {
            this.monitor.leave();
        }
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(getClass().getSimpleName()));
        String valueOf2 = String.valueOf(String.valueOf(state()));
        return new StringBuilder((valueOf.length() + 3) + valueOf2.length()).append(valueOf).append(" [").append(valueOf2).append("]").toString();
    }

    private void executeListeners() {
        if (!this.monitor.isOccupiedByCurrentThread()) {
            for (int i = 0; i < this.listeners.size(); i++) {
                ((ListenerCallQueue) this.listeners.get(i)).execute();
            }
        }
    }

    @GuardedBy("monitor")
    private void starting() {
        STARTING_CALLBACK.enqueueOn(this.listeners);
    }

    @GuardedBy("monitor")
    private void running() {
        RUNNING_CALLBACK.enqueueOn(this.listeners);
    }

    @GuardedBy("monitor")
    private void stopping(State from) {
        if (from == State.STARTING) {
            STOPPING_FROM_STARTING_CALLBACK.enqueueOn(this.listeners);
        } else if (from == State.RUNNING) {
            STOPPING_FROM_RUNNING_CALLBACK.enqueueOn(this.listeners);
        } else {
            throw new AssertionError();
        }
    }

    @GuardedBy("monitor")
    private void terminated(State from) {
        switch (from) {
            case NEW:
                TERMINATED_FROM_NEW_CALLBACK.enqueueOn(this.listeners);
                return;
            case RUNNING:
                TERMINATED_FROM_RUNNING_CALLBACK.enqueueOn(this.listeners);
                return;
            case STOPPING:
                TERMINATED_FROM_STOPPING_CALLBACK.enqueueOn(this.listeners);
                return;
            default:
                throw new AssertionError();
        }
    }

    @GuardedBy("monitor")
    private void failed(final State from, final Throwable cause) {
        String valueOf = String.valueOf(String.valueOf(from));
        String valueOf2 = String.valueOf(String.valueOf(cause));
        new Callback<Listener>(new StringBuilder((valueOf.length() + 27) + valueOf2.length()).append("failed({from = ").append(valueOf).append(", cause = ").append(valueOf2).append("})").toString()) {
            void call(Listener listener) {
                listener.failed(from, cause);
            }
        }.enqueueOn(this.listeners);
    }
}
