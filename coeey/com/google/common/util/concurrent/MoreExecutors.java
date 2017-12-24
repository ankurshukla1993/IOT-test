package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class MoreExecutors {
    private MoreExecutors() {
    }

    @Beta
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
        return new Application().getExitingExecutorService(executor, terminationTimeout, timeUnit);
    }

    @Beta
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor, long terminationTimeout, TimeUnit timeUnit) {
        return new Application().getExitingScheduledExecutorService(executor, terminationTimeout, timeUnit);
    }

    @Beta
    public static void addDelayedShutdownHook(ExecutorService service, long terminationTimeout, TimeUnit timeUnit) {
        new Application().addDelayedShutdownHook(service, terminationTimeout, timeUnit);
    }

    @Beta
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor executor) {
        return new Application().getExitingExecutorService(executor);
    }

    @Beta
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor executor) {
        return new Application().getExitingScheduledExecutorService(executor);
    }

    private static void useDaemonThreadFactory(ThreadPoolExecutor executor) {
        executor.setThreadFactory(new ThreadFactoryBuilder().setDaemon(true).setThreadFactory(executor.getThreadFactory()).build());
    }

    @Deprecated
    public static ListeningExecutorService sameThreadExecutor() {
        return new DirectExecutorService(null);
    }

    public static ListeningExecutorService newDirectExecutorService() {
        return new DirectExecutorService(null);
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    public static ListeningExecutorService listeningDecorator(ExecutorService delegate) {
        if (delegate instanceof ListeningExecutorService) {
            return (ListeningExecutorService) delegate;
        }
        return delegate instanceof ScheduledExecutorService ? new ScheduledListeningDecorator((ScheduledExecutorService) delegate) : new ListeningDecorator(delegate);
    }

    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService delegate) {
        return delegate instanceof ListeningScheduledExecutorService ? (ListeningScheduledExecutorService) delegate : new ScheduledListeningDecorator(delegate);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> T invokeAnyImpl(com.google.common.util.concurrent.ListeningExecutorService r23, java.util.Collection<? extends java.util.concurrent.Callable<T>> r24, boolean r25, long r26) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {
        /*
        com.google.common.base.Preconditions.checkNotNull(r23);
        r13 = r24.size();
        if (r13 <= 0) goto L_0x0079;
    L_0x0009:
        r19 = 1;
    L_0x000b:
        com.google.common.base.Preconditions.checkArgument(r19);
        r10 = com.google.common.collect.Lists.newArrayListWithCapacity(r13);
        r9 = com.google.common.collect.Queues.newLinkedBlockingQueue();
        r5 = 0;
        if (r25 == 0) goto L_0x007c;
    L_0x0019:
        r14 = java.lang.System.nanoTime();	 Catch:{ all -> 0x008d }
    L_0x001d:
        r12 = r24.iterator();	 Catch:{ all -> 0x008d }
        r19 = r12.next();	 Catch:{ all -> 0x008d }
        r19 = (java.util.concurrent.Callable) r19;	 Catch:{ all -> 0x008d }
        r0 = r23;
        r1 = r19;
        r19 = submitAndAddQueueListener(r0, r1, r9);	 Catch:{ all -> 0x008d }
        r0 = r19;
        r10.add(r0);	 Catch:{ all -> 0x008d }
        r13 = r13 + -1;
        r4 = 1;
        r6 = r5;
    L_0x0038:
        r8 = r9.poll();	 Catch:{ all -> 0x00bc }
        r8 = (java.util.concurrent.Future) r8;	 Catch:{ all -> 0x00bc }
        if (r8 != 0) goto L_0x0059;
    L_0x0040:
        if (r13 <= 0) goto L_0x007f;
    L_0x0042:
        r13 = r13 + -1;
        r19 = r12.next();	 Catch:{ all -> 0x00bc }
        r19 = (java.util.concurrent.Callable) r19;	 Catch:{ all -> 0x00bc }
        r0 = r23;
        r1 = r19;
        r19 = submitAndAddQueueListener(r0, r1, r9);	 Catch:{ all -> 0x00bc }
        r0 = r19;
        r10.add(r0);	 Catch:{ all -> 0x00bc }
        r4 = r4 + 1;
    L_0x0059:
        if (r8 == 0) goto L_0x00e3;
    L_0x005b:
        r4 = r4 + -1;
        r19 = r8.get();	 Catch:{ ExecutionException -> 0x00d1, RuntimeException -> 0x00d6 }
        r11 = r10.iterator();
    L_0x0065:
        r20 = r11.hasNext();
        if (r20 == 0) goto L_0x00e0;
    L_0x006b:
        r8 = r11.next();
        r8 = (java.util.concurrent.Future) r8;
        r20 = 1;
        r0 = r20;
        r8.cancel(r0);
        goto L_0x0065;
    L_0x0079:
        r19 = 0;
        goto L_0x000b;
    L_0x007c:
        r14 = 0;
        goto L_0x001d;
    L_0x007f:
        if (r4 != 0) goto L_0x00a6;
    L_0x0081:
        if (r6 != 0) goto L_0x00e1;
    L_0x0083:
        r5 = new java.util.concurrent.ExecutionException;	 Catch:{ all -> 0x00bc }
        r19 = 0;
        r0 = r19;
        r5.<init>(r0);	 Catch:{ all -> 0x00bc }
    L_0x008c:
        throw r5;	 Catch:{ all -> 0x008d }
    L_0x008d:
        r19 = move-exception;
    L_0x008e:
        r11 = r10.iterator();
    L_0x0092:
        r20 = r11.hasNext();
        if (r20 == 0) goto L_0x00df;
    L_0x0098:
        r8 = r11.next();
        r8 = (java.util.concurrent.Future) r8;
        r20 = 1;
        r0 = r20;
        r8.cancel(r0);
        goto L_0x0092;
    L_0x00a6:
        if (r25 == 0) goto L_0x00ca;
    L_0x00a8:
        r19 = java.util.concurrent.TimeUnit.NANOSECONDS;	 Catch:{ all -> 0x00bc }
        r0 = r26;
        r2 = r19;
        r8 = r9.poll(r0, r2);	 Catch:{ all -> 0x00bc }
        r8 = (java.util.concurrent.Future) r8;	 Catch:{ all -> 0x00bc }
        if (r8 != 0) goto L_0x00bf;
    L_0x00b6:
        r19 = new java.util.concurrent.TimeoutException;	 Catch:{ all -> 0x00bc }
        r19.<init>();	 Catch:{ all -> 0x00bc }
        throw r19;	 Catch:{ all -> 0x00bc }
    L_0x00bc:
        r19 = move-exception;
        r5 = r6;
        goto L_0x008e;
    L_0x00bf:
        r16 = java.lang.System.nanoTime();	 Catch:{ all -> 0x00bc }
        r20 = r16 - r14;
        r26 = r26 - r20;
        r14 = r16;
        goto L_0x0059;
    L_0x00ca:
        r8 = r9.take();	 Catch:{ all -> 0x00bc }
        r8 = (java.util.concurrent.Future) r8;	 Catch:{ all -> 0x00bc }
        goto L_0x0059;
    L_0x00d1:
        r7 = move-exception;
        r5 = r7;
    L_0x00d3:
        r6 = r5;
        goto L_0x0038;
    L_0x00d6:
        r18 = move-exception;
        r5 = new java.util.concurrent.ExecutionException;	 Catch:{ all -> 0x00bc }
        r0 = r18;
        r5.<init>(r0);	 Catch:{ all -> 0x00bc }
        goto L_0x00d3;
    L_0x00df:
        throw r19;
    L_0x00e0:
        return r19;
    L_0x00e1:
        r5 = r6;
        goto L_0x008c;
    L_0x00e3:
        r5 = r6;
        goto L_0x00d3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.MoreExecutors.invokeAnyImpl(com.google.common.util.concurrent.ListeningExecutorService, java.util.Collection, boolean, long):T");
    }

    private static <T> ListenableFuture<T> submitAndAddQueueListener(ListeningExecutorService executorService, Callable<T> task, BlockingQueue<Future<T>> queue) {
        ListenableFuture<T> future = executorService.submit(task);
        future.addListener(new 1(queue, future), directExecutor());
        return future;
    }

    @Beta
    public static ThreadFactory platformThreadFactory() {
        if (!isAppEngine()) {
            return Executors.defaultThreadFactory();
        }
        try {
            return (ThreadFactory) Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", new Class[0]).invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e3);
        } catch (InvocationTargetException e4) {
            throw Throwables.propagate(e4.getCause());
        }
    }

    private static boolean isAppEngine() {
        if (System.getProperty("com.google.appengine.runtime.environment") == null) {
            return false;
        }
        try {
            if (Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", new Class[0]).invoke(null, new Object[0]) != null) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (InvocationTargetException e2) {
            return false;
        } catch (IllegalAccessException e3) {
            return false;
        } catch (NoSuchMethodException e4) {
            return false;
        }
    }

    static Thread newThread(String name, Runnable runnable) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(runnable);
        Thread result = platformThreadFactory().newThread(runnable);
        try {
            result.setName(name);
        } catch (SecurityException e) {
        }
        return result;
    }

    static Executor renamingDecorator(Executor executor, Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(nameSupplier);
        return isAppEngine() ? executor : new 2(executor, nameSupplier);
    }

    static ExecutorService renamingDecorator(ExecutorService service, Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(service);
        Preconditions.checkNotNull(nameSupplier);
        return isAppEngine() ? service : new 3(service, nameSupplier);
    }

    static ScheduledExecutorService renamingDecorator(ScheduledExecutorService service, Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(service);
        Preconditions.checkNotNull(nameSupplier);
        return isAppEngine() ? service : new 4(service, nameSupplier);
    }

    @Beta
    public static boolean shutdownAndAwaitTermination(ExecutorService service, long timeout, TimeUnit unit) {
        Preconditions.checkNotNull(unit);
        service.shutdown();
        try {
            long halfTimeoutNanos = TimeUnit.NANOSECONDS.convert(timeout, unit) / 2;
            if (!service.awaitTermination(halfTimeoutNanos, TimeUnit.NANOSECONDS)) {
                service.shutdownNow();
                service.awaitTermination(halfTimeoutNanos, TimeUnit.NANOSECONDS);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            service.shutdownNow();
        }
        return service.isTerminated();
    }
}
