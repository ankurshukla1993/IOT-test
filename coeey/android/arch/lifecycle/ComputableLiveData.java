package android.arch.lifecycle;

import android.arch.core.executor.AppToolkitTaskExecutor;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class ComputableLiveData<T> {
    private AtomicBoolean mComputing = new AtomicBoolean(false);
    private AtomicBoolean mInvalid = new AtomicBoolean(true);
    @VisibleForTesting
    final Runnable mInvalidationRunnable = new C00063();
    private final LiveData<T> mLiveData = new C00041();
    @VisibleForTesting
    final Runnable mRefreshRunnable = new C00052();

    class C00041 extends LiveData<T> {
        C00041() {
        }

        protected void onActive() {
            AppToolkitTaskExecutor.getInstance().executeOnDiskIO(ComputableLiveData.this.mRefreshRunnable);
        }
    }

    class C00052 implements Runnable {
        C00052() {
        }

        @WorkerThread
        public void run() {
            do {
                boolean computed = false;
                if (ComputableLiveData.this.mComputing.compareAndSet(false, true)) {
                    Object obj = null;
                    while (ComputableLiveData.this.mInvalid.compareAndSet(true, false)) {
                        try {
                            computed = true;
                            obj = ComputableLiveData.this.compute();
                        } catch (Throwable th) {
                            ComputableLiveData.this.mComputing.set(false);
                        }
                    }
                    if (computed) {
                        ComputableLiveData.this.mLiveData.postValue(obj);
                    }
                    ComputableLiveData.this.mComputing.set(false);
                }
                if (!computed) {
                    return;
                }
            } while (ComputableLiveData.this.mInvalid.get());
        }
    }

    class C00063 implements Runnable {
        C00063() {
        }

        @MainThread
        public void run() {
            boolean isActive = ComputableLiveData.this.mLiveData.hasActiveObservers();
            if (ComputableLiveData.this.mInvalid.compareAndSet(false, true) && isActive) {
                AppToolkitTaskExecutor.getInstance().executeOnDiskIO(ComputableLiveData.this.mRefreshRunnable);
            }
        }
    }

    @WorkerThread
    protected abstract T compute();

    @NonNull
    public LiveData<T> getLiveData() {
        return this.mLiveData;
    }

    public void invalidate() {
        AppToolkitTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
    }
}
