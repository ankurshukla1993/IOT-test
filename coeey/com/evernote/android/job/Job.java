package com.evernote.android.job;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.evernote.android.job.JobRequest.BackoffPolicy;
import com.evernote.android.job.JobRequest.NetworkType;
import com.evernote.android.job.util.Device;
import com.evernote.android.job.util.JobCat;
import com.evernote.android.job.util.support.PersistableBundleCompat;
import java.lang.ref.WeakReference;
import net.vrallev.android.cat.CatLog;

public abstract class Job {
    private static final CatLog CAT = new JobCat("Job");
    private Context mApplicationContext;
    private boolean mCanceled;
    private WeakReference<Context> mContextReference;
    private boolean mDeleted;
    private long mFinishedTimeStamp = -1;
    private Params mParams;
    private Result mResult = Result.FAILURE;

    public static final class Params {
        private PersistableBundleCompat mExtras;
        private final JobRequest mRequest;

        private Params(@NonNull JobRequest request) {
            this.mRequest = request;
        }

        public int getId() {
            return this.mRequest.getJobId();
        }

        public String getTag() {
            return this.mRequest.getTag();
        }

        public boolean isPeriodic() {
            return this.mRequest.isPeriodic();
        }

        public boolean isExact() {
            return this.mRequest.isExact();
        }

        public boolean isPersisted() {
            return this.mRequest.isPersisted();
        }

        public long getStartMs() {
            return this.mRequest.getStartMs();
        }

        public long getEndMs() {
            return this.mRequest.getEndMs();
        }

        public long getIntervalMs() {
            return this.mRequest.getIntervalMs();
        }

        public long getFlexMs() {
            return this.mRequest.getFlexMs();
        }

        public long getScheduledAt() {
            return this.mRequest.getScheduledAt();
        }

        public long getBackoffMs() {
            return this.mRequest.getBackoffMs();
        }

        public BackoffPolicy getBackoffPolicy() {
            return this.mRequest.getBackoffPolicy();
        }

        public boolean requiresCharging() {
            return this.mRequest.requiresCharging();
        }

        public boolean requiresDeviceIdle() {
            return this.mRequest.requiresDeviceIdle();
        }

        public NetworkType requiredNetworkType() {
            return this.mRequest.requiredNetworkType();
        }

        public boolean requirementsEnforced() {
            return this.mRequest.requirementsEnforced();
        }

        public int getFailureCount() {
            return this.mRequest.getFailureCount();
        }

        public long getLastRun() {
            return this.mRequest.getLastRun();
        }

        @NonNull
        public PersistableBundleCompat getExtras() {
            if (this.mExtras == null) {
                this.mExtras = this.mRequest.getExtras();
                if (this.mExtras == null) {
                    this.mExtras = new PersistableBundleCompat();
                }
            }
            return this.mExtras;
        }

        JobRequest getRequest() {
            return this.mRequest;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return this.mRequest.equals(((Params) o).mRequest);
        }

        public int hashCode() {
            return this.mRequest.hashCode();
        }
    }

    public enum Result {
        SUCCESS,
        FAILURE,
        RESCHEDULE
    }

    @WorkerThread
    @NonNull
    protected abstract Result onRunJob(Params params);

    final Result runJob() {
        try {
            if (meetsRequirements()) {
                this.mResult = onRunJob(getParams());
            } else {
                this.mResult = getParams().isPeriodic() ? Result.FAILURE : Result.RESCHEDULE;
            }
            Result result = this.mResult;
            return result;
        } finally {
            this.mFinishedTimeStamp = System.currentTimeMillis();
        }
    }

    @WorkerThread
    protected void onReschedule(int newJobId) {
    }

    private boolean meetsRequirements() {
        if (!getParams().getRequest().requirementsEnforced()) {
            return true;
        }
        if (!isRequirementChargingMet()) {
            CAT.w("Job requires charging, reschedule");
            return false;
        } else if (!isRequirementDeviceIdleMet()) {
            CAT.w("Job requires device to be idle, reschedule");
            return false;
        } else if (isRequirementNetworkTypeMet()) {
            return true;
        } else {
            CAT.w("Job requires network to be %s, but was %s", new Object[]{getParams().getRequest().requiredNetworkType(), Device.getNetworkType(getContext())});
            return false;
        }
    }

    protected boolean isRequirementChargingMet() {
        return !getParams().getRequest().requiresCharging() || Device.isCharging(getContext());
    }

    protected boolean isRequirementDeviceIdleMet() {
        return !getParams().getRequest().requiresDeviceIdle() || Device.isIdle(getContext());
    }

    protected boolean isRequirementNetworkTypeMet() {
        boolean z = false;
        NetworkType requirement = getParams().getRequest().requiredNetworkType();
        if (requirement == NetworkType.ANY) {
            return true;
        }
        NetworkType current = Device.getNetworkType(getContext());
        switch (requirement) {
            case CONNECTED:
                if (current == NetworkType.ANY) {
                    return false;
                }
                return true;
            case NOT_ROAMING:
                if (current == NetworkType.NOT_ROAMING || current == NetworkType.UNMETERED) {
                    z = true;
                }
                return z;
            case UNMETERED:
                if (current != NetworkType.UNMETERED) {
                    return false;
                }
                return true;
            default:
                throw new IllegalStateException("not implemented");
        }
    }

    final Job setRequest(JobRequest request) {
        this.mParams = new Params(request);
        return this;
    }

    @NonNull
    protected final Params getParams() {
        return this.mParams;
    }

    final Job setContext(Context context) {
        this.mContextReference = new WeakReference(context);
        this.mApplicationContext = context.getApplicationContext();
        return this;
    }

    @NonNull
    protected final Context getContext() {
        Context context = (Context) this.mContextReference.get();
        return context == null ? this.mApplicationContext : context;
    }

    public final void cancel() {
        cancel(false);
    }

    final void cancel(boolean deleted) {
        if (!isFinished()) {
            this.mCanceled = true;
            this.mDeleted = deleted;
        }
    }

    protected final boolean isCanceled() {
        return this.mCanceled;
    }

    public final boolean isFinished() {
        return this.mFinishedTimeStamp > 0;
    }

    final long getFinishedTimeStamp() {
        return this.mFinishedTimeStamp;
    }

    final Result getResult() {
        return this.mResult;
    }

    final boolean isDeleted() {
        return this.mDeleted;
    }

    protected ComponentName startWakefulService(@NonNull Intent intent) {
        return WakeLockUtil.startWakefulService(getContext(), intent);
    }

    public static boolean completeWakefulIntent(@NonNull Intent intent) {
        try {
            return WakeLockUtil.completeWakefulIntent(intent);
        } catch (Exception e) {
            return true;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mParams.equals(((Job) o).mParams);
    }

    public int hashCode() {
        return this.mParams.hashCode();
    }

    public String toString() {
        return "job{id=" + this.mParams.getId() + ", finished=" + isFinished() + ", result=" + this.mResult + ", canceled=" + this.mCanceled + ", periodic=" + this.mParams.isPeriodic() + ", class=" + getClass().getSimpleName() + ", tag=" + this.mParams.getTag() + '}';
    }
}
