package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DefaultExecutorSupplier implements ExecutorSupplier {
    private static final int NUM_IO_BOUND_THREADS = 2;
    private static final int NUM_LIGHTWEIGHT_BACKGROUND_THREADS = 1;
    private final Executor mBackgroundExecutor;
    private final Executor mDecodeExecutor;
    private final Executor mIoBoundExecutor = Executors.newFixedThreadPool(2);
    private final Executor mLightWeightBackgroundExecutor;

    public DefaultExecutorSupplier(int numCpuBoundThreads) {
        ThreadFactory backgroundPriorityThreadFactory = new PriorityThreadFactory(10);
        this.mDecodeExecutor = Executors.newFixedThreadPool(numCpuBoundThreads, backgroundPriorityThreadFactory);
        this.mBackgroundExecutor = Executors.newFixedThreadPool(numCpuBoundThreads, backgroundPriorityThreadFactory);
        this.mLightWeightBackgroundExecutor = Executors.newFixedThreadPool(1, backgroundPriorityThreadFactory);
    }

    public Executor forLocalStorageRead() {
        return this.mIoBoundExecutor;
    }

    public Executor forLocalStorageWrite() {
        return this.mIoBoundExecutor;
    }

    public Executor forDecode() {
        return this.mDecodeExecutor;
    }

    public Executor forBackgroundTasks() {
        return this.mBackgroundExecutor;
    }

    public Executor forLightweightBackgroundTasks() {
        return this.mLightWeightBackgroundExecutor;
    }
}
