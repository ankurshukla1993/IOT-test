package com.evernote.android.job;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.LruCache;
import com.evernote.android.job.util.JobCat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import net.vrallev.android.cat.CatLog;

class JobStorage {
    private static final int CACHE_SIZE = 30;
    private static final CatLog CAT = new JobCat("JobStorage");
    public static final String COLUMN_BACKOFF_MS = "backoffMs";
    public static final String COLUMN_BACKOFF_POLICY = "backoffPolicy";
    public static final String COLUMN_END_MS = "endMs";
    public static final String COLUMN_EXACT = "exact";
    public static final String COLUMN_EXTRAS = "extras";
    public static final String COLUMN_FLEX_MS = "flexMs";
    public static final String COLUMN_FLEX_SUPPORT = "flexSupport";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_INTERVAL_MS = "intervalMs";
    public static final String COLUMN_LAST_RUN = "lastRun";
    public static final String COLUMN_NETWORK_TYPE = "networkType";
    public static final String COLUMN_NUM_FAILURES = "numFailures";
    public static final String COLUMN_PERSISTED = "persisted";
    public static final String COLUMN_REQUIREMENTS_ENFORCED = "requirementsEnforced";
    public static final String COLUMN_REQUIRES_CHARGING = "requiresCharging";
    public static final String COLUMN_REQUIRES_DEVICE_IDLE = "requiresDeviceIdle";
    public static final String COLUMN_SCHEDULED_AT = "scheduledAt";
    public static final String COLUMN_START_MS = "startMs";
    public static final String COLUMN_TAG = "tag";
    public static final String COLUMN_TRANSIENT = "isTransient";
    public static final String DATABASE_NAME = "evernote_jobs.db";
    public static final int DATABASE_VERSION = 4;
    private static final String FAILED_DELETE_IDS = "FAILED_DELETE_IDS";
    public static final String JOB_TABLE_NAME = "jobs";
    public static final String PREF_FILE_NAME = "evernote_jobs";
    private static final String WHERE_NOT_TRANSIENT = "ifnull(isTransient, 0)<=0";
    private final JobCacheId mCacheId;
    private final JobOpenHelper mDbHelper;
    private final Set<String> mFailedDeleteIds;
    private SQLiteDatabase mInjectedDatabase;
    private AtomicInteger mJobCounter;
    private final SharedPreferences mPreferences;

    private class JobCacheId extends LruCache<Integer, JobRequest> {
        public JobCacheId() {
            super(30);
        }

        protected JobRequest create(Integer id) {
            return JobStorage.this.load(id.intValue(), true);
        }
    }

    private static final class JobOpenHelper extends SQLiteOpenHelper {
        private JobOpenHelper(Context context, String databasePath) {
            super(context, databasePath, null, 4, new JobStorageDatabaseErrorHandler());
        }

        public void onCreate(SQLiteDatabase db) {
            createJobTable(db);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            while (oldVersion < newVersion) {
                switch (oldVersion) {
                    case 1:
                        upgradeFrom1To2(db);
                        oldVersion++;
                        break;
                    case 2:
                        upgradeFrom2To3(db);
                        oldVersion++;
                        break;
                    case 3:
                        upgradeFrom3to4(db);
                        oldVersion++;
                        break;
                    default:
                        throw new IllegalStateException("not implemented");
                }
            }
        }

        private void createJobTable(SQLiteDatabase db) {
            db.execSQL("create table jobs (_id integer primary key, tag text not null, startMs integer, endMs integer, backoffMs integer, backoffPolicy text not null, intervalMs integer, requirementsEnforced integer, requiresCharging integer, requiresDeviceIdle integer, exact integer, networkType text not null, extras text, persisted integer, numFailures integer, scheduledAt integer, isTransient integer, flexMs integer, flexSupport integer, lastRun integer);");
        }

        private void upgradeFrom1To2(SQLiteDatabase db) {
            db.execSQL("alter table jobs add column isTransient integer;");
        }

        private void upgradeFrom2To3(SQLiteDatabase db) {
            db.execSQL("alter table jobs add column flexMs integer;");
            db.execSQL("alter table jobs add column flexSupport integer;");
            ContentValues contentValues = new ContentValues();
            contentValues.put(JobStorage.COLUMN_INTERVAL_MS, Long.valueOf(JobRequest.MIN_INTERVAL));
            db.update(JobStorage.JOB_TABLE_NAME, contentValues, "intervalMs>0 AND intervalMs<" + JobRequest.MIN_INTERVAL, new String[0]);
            db.execSQL("update jobs set flexMs = intervalMs;");
        }

        private void upgradeFrom3to4(SQLiteDatabase db) {
            db.execSQL("alter table jobs add column lastRun integer;");
        }
    }

    public JobStorage(Context context) {
        this(context, DATABASE_NAME);
    }

    public JobStorage(Context context, String databasePath) {
        this.mPreferences = context.getSharedPreferences(PREF_FILE_NAME, 0);
        this.mCacheId = new JobCacheId();
        this.mDbHelper = new JobOpenHelper(context, databasePath);
        this.mFailedDeleteIds = this.mPreferences.getStringSet(FAILED_DELETE_IDS, new HashSet());
        if (!this.mFailedDeleteIds.isEmpty()) {
            tryToCleanupFinishedJobs();
        }
    }

    public synchronized void put(JobRequest request) {
        store(request);
        updateRequestInCache(request);
    }

    public synchronized void update(JobRequest request, ContentValues contentValues) {
        updateRequestInCache(request);
        SQLiteDatabase database = null;
        try {
            database = getDatabase();
            database.update(JOB_TABLE_NAME, contentValues, "_id=?", new String[]{String.valueOf(request.getJobId())});
            closeDatabase(database);
        } catch (Exception e) {
            CAT.e(e, "could not update %s", new Object[]{request});
            closeDatabase(database);
        } catch (Throwable th) {
            closeDatabase(database);
        }
    }

    private void updateRequestInCache(JobRequest request) {
        this.mCacheId.put(Integer.valueOf(request.getJobId()), request);
    }

    public synchronized JobRequest get(int id) {
        return (JobRequest) this.mCacheId.get(Integer.valueOf(id));
    }

    public synchronized Set<JobRequest> getAllJobRequests(@Nullable String tag, boolean includeTransient) {
        Set<JobRequest> result;
        String where = null;
        synchronized (this) {
            result = new HashSet();
            try {
                String[] args;
                if (TextUtils.isEmpty(tag)) {
                    if (!includeTransient) {
                        where = WHERE_NOT_TRANSIENT;
                    }
                    args = null;
                } else {
                    where = (includeTransient ? "" : "ifnull(isTransient, 0)<=0 AND ") + "tag=?";
                    args = new String[]{tag};
                }
                SQLiteDatabase database = getDatabase();
                Cursor cursor = database.query(JOB_TABLE_NAME, null, where, args, null, null, null);
                HashMap<Integer, JobRequest> cachedRequests = new HashMap(this.mCacheId.snapshot());
                while (cursor != null && cursor.moveToNext()) {
                    Integer id = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("_id")));
                    if (!didFailToDelete(id.intValue())) {
                        if (cachedRequests.containsKey(id)) {
                            result.add(cachedRequests.get(id));
                        } else {
                            result.add(JobRequest.fromCursor(cursor));
                        }
                    }
                }
                closeCursor(cursor);
                closeDatabase(database);
            } catch (Exception e) {
                CAT.e(e, "could not load all jobs", new Object[0]);
                closeCursor(null);
                closeDatabase(null);
            } catch (Throwable th) {
                closeCursor(null);
                closeDatabase(null);
            }
        }
        return result;
    }

    public synchronized void remove(JobRequest request) {
        remove(request, request.getJobId());
    }

    private synchronized boolean remove(@Nullable JobRequest request, int jobId) {
        boolean z = true;
        synchronized (this) {
            this.mCacheId.remove(Integer.valueOf(jobId));
            SQLiteDatabase database = null;
            try {
                database = getDatabase();
                database.delete(JOB_TABLE_NAME, "_id=?", new String[]{String.valueOf(jobId)});
                closeDatabase(database);
            } catch (Exception e) {
                CAT.e(e, "could not delete %d %s", new Object[]{Integer.valueOf(jobId), request});
                addFailedDeleteId(jobId);
                closeDatabase(database);
                z = false;
            } catch (Throwable th) {
                closeDatabase(database);
            }
        }
        return z;
    }

    public synchronized int nextJobId() {
        int id;
        if (this.mJobCounter == null) {
            this.mJobCounter = new AtomicInteger(getMaxJobId());
        }
        id = this.mJobCounter.incrementAndGet();
        if (id < 0) {
            id = 1;
            this.mJobCounter.set(1);
        }
        return id;
    }

    private void store(JobRequest request) {
        ContentValues contentValues = request.toContentValues();
        SQLiteDatabase database = null;
        try {
            database = getDatabase();
            if (database.insertWithOnConflict(JOB_TABLE_NAME, null, contentValues, 5) < 0) {
                throw new SQLException("Couldn't insert job request into database");
            }
        } finally {
            closeDatabase(database);
        }
    }

    private JobRequest load(int id, boolean includeTransient) {
        if (didFailToDelete(id)) {
            return null;
        }
        SQLiteDatabase database = null;
        Cursor cursor = null;
        JobRequest fromCursor;
        try {
            String where = "_id=?";
            if (!includeTransient) {
                where = where + " AND isTransient<=0";
            }
            database = getDatabase();
            cursor = database.query(JOB_TABLE_NAME, null, where, new String[]{String.valueOf(id)}, null, null, null);
            if (cursor == null || !cursor.moveToFirst()) {
                closeCursor(cursor);
                closeDatabase(database);
                return null;
            }
            fromCursor = JobRequest.fromCursor(cursor);
            return fromCursor;
        } catch (Exception e) {
            fromCursor = CAT;
            fromCursor.e(e, "could not load id %d", new Object[]{Integer.valueOf(id)});
        } finally {
            closeCursor(cursor);
            closeDatabase(database);
        }
    }

    @VisibleForTesting
    @NonNull
    SQLiteDatabase getDatabase() {
        if (this.mInjectedDatabase != null) {
            return this.mInjectedDatabase;
        }
        try {
            return this.mDbHelper.getWritableDatabase();
        } catch (SQLiteCantOpenDatabaseException e) {
            CAT.e(e);
            new JobStorageDatabaseErrorHandler().deleteDatabaseFile(DATABASE_NAME);
            return this.mDbHelper.getWritableDatabase();
        }
    }

    @VisibleForTesting
    void injectDatabase(SQLiteDatabase database) {
        this.mInjectedDatabase = database;
    }

    @VisibleForTesting
    Set<String> getFailedDeleteIds() {
        return this.mFailedDeleteIds;
    }

    @VisibleForTesting
    int getMaxJobId() {
        int i = 0;
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {
            database = getDatabase();
            cursor = database.rawQuery("SELECT MAX(_id) FROM jobs", null);
            if (cursor == null || !cursor.moveToFirst()) {
                closeCursor(cursor);
                closeDatabase(database);
                return i;
            }
            i = cursor.getInt(0);
            return i;
        } catch (Exception e) {
            CAT.e(e);
        } finally {
            closeCursor(cursor);
            closeDatabase(database);
        }
    }

    private void addFailedDeleteId(int id) {
        synchronized (this.mFailedDeleteIds) {
            this.mFailedDeleteIds.add(String.valueOf(id));
            this.mPreferences.edit().putStringSet(FAILED_DELETE_IDS, this.mFailedDeleteIds).apply();
        }
    }

    private boolean didFailToDelete(int id) {
        boolean z;
        synchronized (this.mFailedDeleteIds) {
            z = !this.mFailedDeleteIds.isEmpty() && this.mFailedDeleteIds.contains(String.valueOf(id));
        }
        return z;
    }

    private void tryToCleanupFinishedJobs() {
        new Thread("CleanupFinishedJobsThread") {
            public void run() {
                Set<String> ids;
                synchronized (JobStorage.this.mFailedDeleteIds) {
                    ids = new HashSet(JobStorage.this.mFailedDeleteIds);
                }
                Iterator<String> iterator = ids.iterator();
                while (iterator.hasNext()) {
                    try {
                        if (JobStorage.this.remove(null, Integer.parseInt((String) iterator.next()))) {
                            iterator.remove();
                            JobStorage.CAT.i("Deleted job %d which failed to delete earlier", new Object[]{Integer.valueOf(jobId)});
                        } else {
                            JobStorage.CAT.e("Couldn't delete job %d which failed to delete earlier", new Object[]{Integer.valueOf(Integer.parseInt((String) iterator.next()))});
                        }
                    } catch (NumberFormatException e) {
                        iterator.remove();
                    }
                }
                synchronized (JobStorage.this.mFailedDeleteIds) {
                    JobStorage.this.mFailedDeleteIds.clear();
                    if (ids.size() > 50) {
                        int counter;
                        int counter2 = 0;
                        for (String id : ids) {
                            counter = counter2 + 1;
                            if (counter2 > 50) {
                                break;
                            }
                            JobStorage.this.mFailedDeleteIds.add(id);
                            counter2 = counter;
                        }
                        counter = counter2;
                    } else {
                        JobStorage.this.mFailedDeleteIds.addAll(ids);
                    }
                }
            }
        }.start();
    }

    private static void closeCursor(@Nullable Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e) {
            }
        }
    }

    private static void closeDatabase(@Nullable SQLiteDatabase database) {
        if (database != null) {
            try {
                database.close();
            } catch (Exception e) {
            }
        }
    }
}
