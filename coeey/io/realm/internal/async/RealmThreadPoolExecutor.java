package io.realm.internal.async;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public class RealmThreadPoolExecutor extends ThreadPoolExecutor {
    private static final int CORE_POOL_SIZE = calculateCorePoolSize();
    private static final int QUEUE_SIZE = 100;
    private static final String SYS_CPU_DIR = "/sys/devices/system/cpu/";
    private boolean isPaused;
    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unpaused = this.pauseLock.newCondition();

    class C24421 implements FileFilter {
        final /* synthetic */ Pattern val$filePattern;

        C24421(Pattern pattern) {
            this.val$filePattern = pattern;
        }

        public boolean accept(File file) {
            return this.val$filePattern.matcher(file.getName()).matches();
        }
    }

    public static RealmThreadPoolExecutor newDefaultExecutor() {
        return new RealmThreadPoolExecutor(CORE_POOL_SIZE, CORE_POOL_SIZE);
    }

    public static RealmThreadPoolExecutor newSingleThreadExecutor() {
        return new RealmThreadPoolExecutor(1, 1);
    }

    @SuppressFBWarnings({"DMI_HARDCODED_ABSOLUTE_FILENAME"})
    private static int calculateCorePoolSize() {
        int cpus = countFilesInDir(SYS_CPU_DIR, "cpu[0-9]+");
        if (cpus <= 0) {
            cpus = Runtime.getRuntime().availableProcessors();
        }
        return cpus <= 0 ? 1 : (cpus * 2) + 1;
    }

    private static int countFilesInDir(String dirPath, String pattern) {
        int i = 0;
        try {
            File[] files = new File(dirPath).listFiles(new C24421(Pattern.compile(pattern)));
            if (files != null) {
                i = files.length;
            }
        } catch (SecurityException e) {
        }
        return i;
    }

    private RealmThreadPoolExecutor(int corePoolSize, int maxPoolSize) {
        super(corePoolSize, maxPoolSize, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(100));
    }

    public Future<?> submitTransaction(Runnable task) {
        return super.submit(new BgPriorityRunnable(task));
    }

    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        this.pauseLock.lock();
        while (this.isPaused) {
            try {
                this.unpaused.await();
            } catch (InterruptedException e) {
                t.interrupt();
            } finally {
                this.pauseLock.unlock();
            }
        }
    }

    public void pause() {
        this.pauseLock.lock();
        try {
            this.isPaused = true;
        } finally {
            this.pauseLock.unlock();
        }
    }

    public void resume() {
        this.pauseLock.lock();
        try {
            this.isPaused = false;
            this.unpaused.signalAll();
        } finally {
            this.pauseLock.unlock();
        }
    }
}
