package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Service.Listener;
import com.google.common.util.concurrent.Service.State;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

@Beta
public abstract class AbstractExecutionThreadService implements Service {
    private static final Logger logger = Logger.getLogger(AbstractExecutionThreadService.class.getName());
    private final Service delegate = new C18221();

    class C18221 extends AbstractService {

        class C18201 implements Supplier<String> {
            C18201() {
            }

            public String get() {
                return AbstractExecutionThreadService.this.serviceName();
            }
        }

        class C18212 implements Runnable {
            C18212() {
            }

            public void run() {
                try {
                    AbstractExecutionThreadService.this.startUp();
                    C18221.this.notifyStarted();
                    if (C18221.this.isRunning()) {
                        AbstractExecutionThreadService.this.run();
                    }
                    AbstractExecutionThreadService.this.shutDown();
                    C18221.this.notifyStopped();
                } catch (Throwable t) {
                    C18221.this.notifyFailed(t);
                    RuntimeException propagate = Throwables.propagate(t);
                }
            }
        }

        C18221() {
        }

        protected final void doStart() {
            MoreExecutors.renamingDecorator(AbstractExecutionThreadService.this.executor(), new C18201()).execute(new C18212());
        }

        protected void doStop() {
            AbstractExecutionThreadService.this.triggerShutdown();
        }
    }

    class C18232 implements Executor {
        C18232() {
        }

        public void execute(Runnable command) {
            MoreExecutors.newThread(AbstractExecutionThreadService.this.serviceName(), command).start();
        }
    }

    protected abstract void run() throws Exception;

    protected AbstractExecutionThreadService() {
    }

    protected void startUp() throws Exception {
    }

    protected void shutDown() throws Exception {
    }

    protected void triggerShutdown() {
    }

    protected Executor executor() {
        return new C18232();
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
