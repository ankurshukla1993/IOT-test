package com.evernote.android.job;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.evernote.android.job.util.JobApi;
import com.evernote.android.job.util.JobCat;
import com.evernote.android.job.util.JobPreconditions;
import com.evernote.android.job.util.JobUtil;
import com.evernote.android.job.util.support.PersistableBundleCompat;
import java.util.concurrent.TimeUnit;
import net.vrallev.android.cat.CatLog;

public final class JobRequest {
    private static final CatLog CAT = new JobCat("JobRequest");
    public static final long DEFAULT_BACKOFF_MS = 30000;
    public static final BackoffPolicy DEFAULT_BACKOFF_POLICY = BackoffPolicy.EXPONENTIAL;
    public static final NetworkType DEFAULT_NETWORK_TYPE = NetworkType.ANY;
    public static final long MIN_FLEX = TimeUnit.MINUTES.toMillis(5);
    public static final long MIN_INTERVAL = TimeUnit.MINUTES.toMillis(15);
    private static final long WINDOW_THRESHOLD_MAX = 6148914691236517204L;
    private static final long WINDOW_THRESHOLD_WARNING = 3074457345618258602L;
    private final Builder mBuilder;
    private int mFailureCount;
    private boolean mFlexSupport;
    private final JobApi mJobApi;
    private long mLastRun;
    private long mScheduledAt;
    private boolean mTransient;

    public enum BackoffPolicy {
        LINEAR,
        EXPONENTIAL
    }

    public static final class Builder {
        private static final int CREATE_ID = -8765;
        private long mBackoffMs;
        private BackoffPolicy mBackoffPolicy;
        private long mEndMs;
        private boolean mExact;
        private PersistableBundleCompat mExtras;
        private String mExtrasXml;
        private long mFlexMs;
        private int mId;
        private long mIntervalMs;
        private NetworkType mNetworkType;
        private boolean mPersisted;
        private boolean mRequirementsEnforced;
        private boolean mRequiresCharging;
        private boolean mRequiresDeviceIdle;
        private long mStartMs;
        private final String mTag;
        private boolean mUpdateCurrent;

        public Builder(@NonNull String tag) {
            this.mTag = (String) JobPreconditions.checkNotEmpty(tag);
            this.mId = CREATE_ID;
            this.mStartMs = -1;
            this.mEndMs = -1;
            this.mBackoffMs = JobRequest.DEFAULT_BACKOFF_MS;
            this.mBackoffPolicy = JobRequest.DEFAULT_BACKOFF_POLICY;
            this.mNetworkType = JobRequest.DEFAULT_NETWORK_TYPE;
        }

        private Builder(Cursor cursor) throws Exception {
            boolean z;
            boolean z2 = true;
            this.mId = cursor.getInt(cursor.getColumnIndex("_id"));
            this.mTag = cursor.getString(cursor.getColumnIndex(JobStorage.COLUMN_TAG));
            this.mStartMs = cursor.getLong(cursor.getColumnIndex(JobStorage.COLUMN_START_MS));
            this.mEndMs = cursor.getLong(cursor.getColumnIndex(JobStorage.COLUMN_END_MS));
            this.mBackoffMs = cursor.getLong(cursor.getColumnIndex(JobStorage.COLUMN_BACKOFF_MS));
            try {
                this.mBackoffPolicy = BackoffPolicy.valueOf(cursor.getString(cursor.getColumnIndex(JobStorage.COLUMN_BACKOFF_POLICY)));
            } catch (Throwable t) {
                JobRequest.CAT.e(t);
                this.mBackoffPolicy = JobRequest.DEFAULT_BACKOFF_POLICY;
            }
            this.mIntervalMs = cursor.getLong(cursor.getColumnIndex(JobStorage.COLUMN_INTERVAL_MS));
            this.mFlexMs = cursor.getLong(cursor.getColumnIndex(JobStorage.COLUMN_FLEX_MS));
            if (cursor.getInt(cursor.getColumnIndex(JobStorage.COLUMN_REQUIREMENTS_ENFORCED)) > 0) {
                z = true;
            } else {
                z = false;
            }
            this.mRequirementsEnforced = z;
            if (cursor.getInt(cursor.getColumnIndex(JobStorage.COLUMN_REQUIRES_CHARGING)) > 0) {
                z = true;
            } else {
                z = false;
            }
            this.mRequiresCharging = z;
            if (cursor.getInt(cursor.getColumnIndex(JobStorage.COLUMN_REQUIRES_DEVICE_IDLE)) > 0) {
                z = true;
            } else {
                z = false;
            }
            this.mRequiresDeviceIdle = z;
            if (cursor.getInt(cursor.getColumnIndex(JobStorage.COLUMN_EXACT)) > 0) {
                z = true;
            } else {
                z = false;
            }
            this.mExact = z;
            try {
                this.mNetworkType = NetworkType.valueOf(cursor.getString(cursor.getColumnIndex(JobStorage.COLUMN_NETWORK_TYPE)));
            } catch (Throwable t2) {
                JobRequest.CAT.e(t2);
                this.mNetworkType = JobRequest.DEFAULT_NETWORK_TYPE;
            }
            this.mExtrasXml = cursor.getString(cursor.getColumnIndex(JobStorage.COLUMN_EXTRAS));
            if (cursor.getInt(cursor.getColumnIndex(JobStorage.COLUMN_PERSISTED)) <= 0) {
                z2 = false;
            }
            this.mPersisted = z2;
        }

        private Builder(@NonNull Builder builder) {
            this(builder, false);
        }

        private Builder(@NonNull Builder builder, boolean createId) {
            this.mId = createId ? CREATE_ID : builder.mId;
            this.mTag = builder.mTag;
            this.mStartMs = builder.mStartMs;
            this.mEndMs = builder.mEndMs;
            this.mBackoffMs = builder.mBackoffMs;
            this.mBackoffPolicy = builder.mBackoffPolicy;
            this.mIntervalMs = builder.mIntervalMs;
            this.mFlexMs = builder.mFlexMs;
            this.mRequirementsEnforced = builder.mRequirementsEnforced;
            this.mRequiresCharging = builder.mRequiresCharging;
            this.mRequiresDeviceIdle = builder.mRequiresDeviceIdle;
            this.mExact = builder.mExact;
            this.mNetworkType = builder.mNetworkType;
            this.mExtras = builder.mExtras;
            this.mExtrasXml = builder.mExtrasXml;
            this.mPersisted = builder.mPersisted;
            this.mUpdateCurrent = builder.mUpdateCurrent;
        }

        private void fillContentValues(ContentValues contentValues) {
            contentValues.put("_id", Integer.valueOf(this.mId));
            contentValues.put(JobStorage.COLUMN_TAG, this.mTag);
            contentValues.put(JobStorage.COLUMN_START_MS, Long.valueOf(this.mStartMs));
            contentValues.put(JobStorage.COLUMN_END_MS, Long.valueOf(this.mEndMs));
            contentValues.put(JobStorage.COLUMN_BACKOFF_MS, Long.valueOf(this.mBackoffMs));
            contentValues.put(JobStorage.COLUMN_BACKOFF_POLICY, this.mBackoffPolicy.toString());
            contentValues.put(JobStorage.COLUMN_INTERVAL_MS, Long.valueOf(this.mIntervalMs));
            contentValues.put(JobStorage.COLUMN_FLEX_MS, Long.valueOf(this.mFlexMs));
            contentValues.put(JobStorage.COLUMN_REQUIREMENTS_ENFORCED, Boolean.valueOf(this.mRequirementsEnforced));
            contentValues.put(JobStorage.COLUMN_REQUIRES_CHARGING, Boolean.valueOf(this.mRequiresCharging));
            contentValues.put(JobStorage.COLUMN_REQUIRES_DEVICE_IDLE, Boolean.valueOf(this.mRequiresDeviceIdle));
            contentValues.put(JobStorage.COLUMN_EXACT, Boolean.valueOf(this.mExact));
            contentValues.put(JobStorage.COLUMN_NETWORK_TYPE, this.mNetworkType.toString());
            if (this.mExtras != null) {
                contentValues.put(JobStorage.COLUMN_EXTRAS, this.mExtras.saveToXml());
            } else if (!TextUtils.isEmpty(this.mExtrasXml)) {
                contentValues.put(JobStorage.COLUMN_EXTRAS, this.mExtrasXml);
            }
            contentValues.put(JobStorage.COLUMN_PERSISTED, Boolean.valueOf(this.mPersisted));
        }

        public Builder setExecutionWindow(long startInMs, long endInMs) {
            this.mStartMs = JobPreconditions.checkArgumentPositive(startInMs, "startInMs must be greater than 0");
            this.mEndMs = JobPreconditions.checkArgumentInRange(endInMs, startInMs, Long.MAX_VALUE, "endInMs");
            if (this.mStartMs > JobRequest.WINDOW_THRESHOLD_MAX) {
                JobRequest.CAT.i("startInMs reduced from %d days to %d days", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toDays(this.mStartMs)), Long.valueOf(TimeUnit.MILLISECONDS.toDays(JobRequest.WINDOW_THRESHOLD_MAX))});
                this.mStartMs = JobRequest.WINDOW_THRESHOLD_MAX;
            }
            if (this.mEndMs > JobRequest.WINDOW_THRESHOLD_MAX) {
                JobRequest.CAT.i("endInMs reduced from %d days to %d days", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toDays(this.mEndMs)), Long.valueOf(TimeUnit.MILLISECONDS.toDays(JobRequest.WINDOW_THRESHOLD_MAX))});
                this.mEndMs = JobRequest.WINDOW_THRESHOLD_MAX;
            }
            return this;
        }

        public Builder setExtras(@Nullable PersistableBundleCompat extras) {
            if (extras == null) {
                this.mExtras = null;
                this.mExtrasXml = null;
            } else {
                this.mExtras = new PersistableBundleCompat(extras);
            }
            return this;
        }

        public Builder setRequirementsEnforced(boolean enforced) {
            this.mRequirementsEnforced = enforced;
            return this;
        }

        public Builder setRequiredNetworkType(@Nullable NetworkType networkType) {
            this.mNetworkType = networkType;
            return this;
        }

        public Builder setRequiresCharging(boolean requiresCharging) {
            this.mRequiresCharging = requiresCharging;
            return this;
        }

        public Builder setRequiresDeviceIdle(boolean requiresDeviceIdle) {
            this.mRequiresDeviceIdle = requiresDeviceIdle;
            return this;
        }

        public Builder setExact(long exactInMs) {
            this.mExact = true;
            if (exactInMs > JobRequest.WINDOW_THRESHOLD_MAX) {
                JobRequest.CAT.i("exactInMs clamped from %d days to %d days", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toDays(exactInMs)), Long.valueOf(TimeUnit.MILLISECONDS.toDays(JobRequest.WINDOW_THRESHOLD_MAX))});
                exactInMs = JobRequest.WINDOW_THRESHOLD_MAX;
            }
            return setExecutionWindow(exactInMs, exactInMs);
        }

        public Builder setPeriodic(long intervalMs) {
            return setPeriodic(intervalMs, intervalMs);
        }

        public Builder setPeriodic(long intervalMs, long flexMs) {
            this.mIntervalMs = JobPreconditions.checkArgumentInRange(intervalMs, JobRequest.getMinInterval(), Long.MAX_VALUE, JobStorage.COLUMN_INTERVAL_MS);
            this.mFlexMs = JobPreconditions.checkArgumentInRange(flexMs, JobRequest.getMinFlex(), this.mIntervalMs, JobStorage.COLUMN_FLEX_MS);
            return this;
        }

        public Builder setBackoffCriteria(long backoffMs, @NonNull BackoffPolicy backoffPolicy) {
            this.mBackoffMs = JobPreconditions.checkArgumentPositive(backoffMs, "backoffMs must be > 0");
            this.mBackoffPolicy = (BackoffPolicy) JobPreconditions.checkNotNull(backoffPolicy);
            return this;
        }

        public Builder setPersisted(boolean persisted) {
            if (!persisted || JobUtil.hasBootPermission(JobManager.instance().getContext())) {
                this.mPersisted = persisted;
                return this;
            }
            throw new IllegalStateException("Does not have RECEIVE_BOOT_COMPLETED permission, which is mandatory for this feature");
        }

        public Builder setUpdateCurrent(boolean updateCurrent) {
            this.mUpdateCurrent = updateCurrent;
            return this;
        }

        public JobRequest build() {
            JobPreconditions.checkNotEmpty(this.mTag);
            JobPreconditions.checkArgumentPositive(this.mBackoffMs, "backoffMs must be > 0");
            JobPreconditions.checkNotNull(this.mBackoffPolicy);
            JobPreconditions.checkNotNull(this.mNetworkType);
            if (this.mIntervalMs > 0) {
                JobPreconditions.checkArgumentInRange(this.mIntervalMs, JobRequest.getMinInterval(), Long.MAX_VALUE, JobStorage.COLUMN_INTERVAL_MS);
                JobPreconditions.checkArgumentInRange(this.mFlexMs, JobRequest.getMinFlex(), this.mIntervalMs, JobStorage.COLUMN_FLEX_MS);
                if (this.mIntervalMs < JobRequest.MIN_INTERVAL || this.mFlexMs < JobRequest.MIN_FLEX) {
                    JobRequest.CAT.w("AllowSmallerIntervals enabled, this will crash on Android N and later, interval %d (minimum is %d), flex %d (minimum is %d)", new Object[]{Long.valueOf(this.mIntervalMs), Long.valueOf(JobRequest.MIN_INTERVAL), Long.valueOf(this.mFlexMs), Long.valueOf(JobRequest.MIN_FLEX)});
                }
            }
            if (this.mExact && this.mIntervalMs > 0) {
                throw new IllegalArgumentException("Can't call setExact() on a periodic job.");
            } else if (this.mExact && this.mStartMs != this.mEndMs) {
                throw new IllegalArgumentException("Can't call setExecutionWindow() for an exact job.");
            } else if (this.mExact && (this.mRequirementsEnforced || this.mRequiresDeviceIdle || this.mRequiresCharging || !JobRequest.DEFAULT_NETWORK_TYPE.equals(this.mNetworkType))) {
                throw new IllegalArgumentException("Can't require any condition for an exact job.");
            } else if (this.mIntervalMs <= 0 && (this.mStartMs == -1 || this.mEndMs == -1)) {
                throw new IllegalArgumentException("You're trying to build a job with no constraints, this is not allowed.");
            } else if (this.mIntervalMs > 0 && (this.mStartMs != -1 || this.mEndMs != -1)) {
                throw new IllegalArgumentException("Can't call setExecutionWindow() on a periodic job.");
            } else if (this.mIntervalMs <= 0 || (this.mBackoffMs == JobRequest.DEFAULT_BACKOFF_MS && JobRequest.DEFAULT_BACKOFF_POLICY.equals(this.mBackoffPolicy))) {
                if (this.mIntervalMs <= 0 && (this.mStartMs > JobRequest.WINDOW_THRESHOLD_WARNING || this.mEndMs > JobRequest.WINDOW_THRESHOLD_WARNING)) {
                    JobRequest.CAT.w("Attention: your execution window is too large. This could result in undesired behavior, see https://github.com/evernote/android-job/blob/master/FAQ.md");
                }
                if (this.mIntervalMs <= 0 && this.mStartMs > TimeUnit.DAYS.toMillis(365)) {
                    JobRequest.CAT.w("Warning: job with tag %s scheduled over a year in the future", new Object[]{this.mTag});
                }
                if (this.mId != CREATE_ID) {
                    JobPreconditions.checkArgumentNonnegative(this.mId, "id can't be negative");
                }
                Builder builder = new Builder(this);
                if (this.mId == CREATE_ID) {
                    builder.mId = JobManager.instance().getJobStorage().nextJobId();
                    JobPreconditions.checkArgumentNonnegative(builder.mId, "id can't be negative");
                }
                return new JobRequest(builder);
            } else {
                throw new IllegalArgumentException("A periodic job will not respect any back-off policy, so calling setBackoffCriteria() with setPeriodic() is an error.");
            }
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (this.mId != ((Builder) o).mId) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.mId;
        }
    }

    public enum NetworkType {
        ANY,
        CONNECTED,
        UNMETERED,
        NOT_ROAMING
    }

    static long getMinInterval() {
        return JobManager.instance().getConfig().isAllowSmallerIntervalsForMarshmallow() ? TimeUnit.MINUTES.toMillis(1) : MIN_INTERVAL;
    }

    static long getMinFlex() {
        return JobManager.instance().getConfig().isAllowSmallerIntervalsForMarshmallow() ? TimeUnit.SECONDS.toMillis(30) : MIN_FLEX;
    }

    private JobRequest(Builder builder) {
        this.mBuilder = builder;
        this.mJobApi = builder.mExact ? JobApi.V_14 : JobManager.instance().getApi();
    }

    public int getJobId() {
        return this.mBuilder.mId;
    }

    @NonNull
    public String getTag() {
        return this.mBuilder.mTag;
    }

    public long getStartMs() {
        return this.mBuilder.mStartMs;
    }

    public long getEndMs() {
        return this.mBuilder.mEndMs;
    }

    public BackoffPolicy getBackoffPolicy() {
        return this.mBuilder.mBackoffPolicy;
    }

    public long getBackoffMs() {
        return this.mBuilder.mBackoffMs;
    }

    public boolean isPeriodic() {
        return getIntervalMs() > 0;
    }

    public long getIntervalMs() {
        return this.mBuilder.mIntervalMs;
    }

    public long getFlexMs() {
        return this.mBuilder.mFlexMs;
    }

    public boolean requirementsEnforced() {
        return this.mBuilder.mRequirementsEnforced;
    }

    public boolean requiresCharging() {
        return this.mBuilder.mRequiresCharging;
    }

    public boolean requiresDeviceIdle() {
        return this.mBuilder.mRequiresDeviceIdle;
    }

    public NetworkType requiredNetworkType() {
        return this.mBuilder.mNetworkType;
    }

    public PersistableBundleCompat getExtras() {
        if (this.mBuilder.mExtras == null && !TextUtils.isEmpty(this.mBuilder.mExtrasXml)) {
            this.mBuilder.mExtras = PersistableBundleCompat.fromXml(this.mBuilder.mExtrasXml);
        }
        return this.mBuilder.mExtras;
    }

    public boolean isPersisted() {
        return this.mBuilder.mPersisted;
    }

    public boolean isUpdateCurrent() {
        return this.mBuilder.mUpdateCurrent;
    }

    public boolean isExact() {
        return this.mBuilder.mExact;
    }

    long getBackoffOffset() {
        if (isPeriodic()) {
            return 0;
        }
        long offset;
        switch (getBackoffPolicy()) {
            case LINEAR:
                offset = ((long) this.mFailureCount) * getBackoffMs();
                break;
            case EXPONENTIAL:
                if (this.mFailureCount != 0) {
                    offset = (long) (((double) getBackoffMs()) * Math.pow(2.0d, (double) (this.mFailureCount - 1)));
                    break;
                }
                offset = 0;
                break;
            default:
                throw new IllegalStateException("not implemented");
        }
        return Math.min(offset, TimeUnit.HOURS.toMillis(5));
    }

    JobApi getJobApi() {
        return this.mJobApi;
    }

    void setScheduledAt(long timeStamp) {
        this.mScheduledAt = timeStamp;
    }

    public long getScheduledAt() {
        return this.mScheduledAt;
    }

    public int getFailureCount() {
        return this.mFailureCount;
    }

    boolean isTransient() {
        return this.mTransient;
    }

    boolean isFlexSupport() {
        return this.mFlexSupport;
    }

    void setFlexSupport(boolean flexSupport) {
        this.mFlexSupport = flexSupport;
    }

    public long getLastRun() {
        return this.mLastRun;
    }

    public int schedule() {
        JobManager.instance().schedule(this);
        return getJobId();
    }

    public Builder cancelAndEdit() {
        JobManager.instance().cancel(getJobId());
        Builder builder = new Builder(this.mBuilder);
        this.mTransient = false;
        if (!isPeriodic()) {
            long offset = System.currentTimeMillis() - this.mScheduledAt;
            builder.setExecutionWindow(Math.max(1, getStartMs() - offset), Math.max(1, getEndMs() - offset));
        }
        return builder;
    }

    JobRequest reschedule(boolean failure, boolean newJob) {
        JobRequest newRequest = new Builder(this.mBuilder, newJob).build();
        if (failure) {
            newRequest.mFailureCount = this.mFailureCount + 1;
        }
        try {
            newRequest.schedule();
        } catch (Exception e) {
            CAT.e(e);
        }
        return newRequest;
    }

    void updateStats(boolean incFailureCount, boolean updateLastRun) {
        ContentValues contentValues = new ContentValues();
        if (incFailureCount) {
            this.mFailureCount++;
            contentValues.put(JobStorage.COLUMN_NUM_FAILURES, Integer.valueOf(this.mFailureCount));
        }
        if (updateLastRun) {
            this.mLastRun = System.currentTimeMillis();
            contentValues.put(JobStorage.COLUMN_LAST_RUN, Long.valueOf(this.mLastRun));
        }
        JobManager.instance().getJobStorage().update(this, contentValues);
    }

    void setTransient(boolean isTransient) {
        this.mTransient = isTransient;
        ContentValues contentValues = new ContentValues();
        contentValues.put(JobStorage.COLUMN_TRANSIENT, Boolean.valueOf(this.mTransient));
        JobManager.instance().getJobStorage().update(this, contentValues);
    }

    ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        this.mBuilder.fillContentValues(contentValues);
        contentValues.put(JobStorage.COLUMN_NUM_FAILURES, Integer.valueOf(this.mFailureCount));
        contentValues.put(JobStorage.COLUMN_SCHEDULED_AT, Long.valueOf(this.mScheduledAt));
        contentValues.put(JobStorage.COLUMN_TRANSIENT, Boolean.valueOf(this.mTransient));
        contentValues.put(JobStorage.COLUMN_FLEX_SUPPORT, Boolean.valueOf(this.mFlexSupport));
        contentValues.put(JobStorage.COLUMN_LAST_RUN, Long.valueOf(this.mLastRun));
        return contentValues;
    }

    static JobRequest fromCursor(Cursor cursor) throws Exception {
        boolean z = true;
        JobRequest request = new Builder(cursor).build();
        request.mFailureCount = cursor.getInt(cursor.getColumnIndex(JobStorage.COLUMN_NUM_FAILURES));
        request.mScheduledAt = cursor.getLong(cursor.getColumnIndex(JobStorage.COLUMN_SCHEDULED_AT));
        request.mTransient = cursor.getInt(cursor.getColumnIndex(JobStorage.COLUMN_TRANSIENT)) > 0;
        if (cursor.getInt(cursor.getColumnIndex(JobStorage.COLUMN_FLEX_SUPPORT)) <= 0) {
            z = false;
        }
        request.mFlexSupport = z;
        request.mLastRun = cursor.getLong(cursor.getColumnIndex(JobStorage.COLUMN_LAST_RUN));
        JobPreconditions.checkArgumentNonnegative(request.mFailureCount, "failure count can't be negative");
        JobPreconditions.checkArgumentNonnegative(request.mScheduledAt, "scheduled at can't be negative");
        return request;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mBuilder.equals(((JobRequest) o).mBuilder);
    }

    public int hashCode() {
        return this.mBuilder.hashCode();
    }

    public String toString() {
        return "request{id=" + getJobId() + ", tag=" + getTag() + '}';
    }
}
