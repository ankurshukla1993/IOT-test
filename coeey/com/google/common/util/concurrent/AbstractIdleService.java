package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Service.Listener;
import com.google.common.util.concurrent.Service.State;
import humanize.util.Constants;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public abstract class AbstractIdleService implements Service {
    private final Service delegate = new C18272();
    private final Supplier<String> threadNameSupplier = new C18241();

    class C18241 implements Supplier<String> {
        C18241() {
        }

        public String get() {
            String valueOf = String.valueOf(String.valueOf(AbstractIdleService.this.serviceName()));
            String valueOf2 = String.valueOf(String.valueOf(AbstractIdleService.this.state()));
            return new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(Constants.SPACE).append(valueOf2).toString();
        }
    }

    class C18272 extends AbstractService {

        class C18251 implements Runnable {
            C18251() {
            }

            public void run() {
                try {
                    AbstractIdleService.this.startUp();
                    C18272.this.notifyStarted();
                } catch (Throwable t) {
                    C18272.this.notifyFailed(t);
                    RuntimeException propagate = Throwables.propagate(t);
                }
            }
        }

        class C18262 implements Runnable {
            C18262() {
            }

            public void run() {
                try {
                    AbstractIdleService.this.shutDown();
                    C18272.this.notifyStopped();
                } catch (Throwable t) {
                    C18272.this.notifyFailed(t);
                    RuntimeException propagate = Throwables.propagate(t);
                }
            }
        }

        C18272() {
        }

        protected final void doStart() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), AbstractIdleService.this.threadNameSupplier).execute(new C18251());
        }

        protected final void doStop() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), AbstractIdleService.this.threadNameSupplier).execute(new C18262());
        }
    }

    class C18283 implements Executor {
        C18283() {
        }

        public void execute(Runnable command) {
            MoreExecutors.newThread((String) AbstractIdleService.this.threadNameSupplier.get(), command).start();
        }
    }

    protected abstract void shutDown() throws Exception;

    protected abstract void startUp() throws Exception;

    protected AbstractIdleService() {
    }

    protected Executor executor() {
        return new C18283();
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(serviceName()));
        String valueOf2 = String.valueOf(String.valueOf(state()));
        return new StringBuilder((valueOf.length() + 3) + valueOf2.length()).append(valueOf).append(" [").append(valueOf2).append("]").toString();
    }

    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    public final State state() {
        return this.delegate.state();
    }

    public final void addListener(Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitRunning(timeout, unit);
    }

    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitTerminated(timeout, unit);
    }

    protected String serviceName() {
        return getClass().getSimpleName();
    }
}
