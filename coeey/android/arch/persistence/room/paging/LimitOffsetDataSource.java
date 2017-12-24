package android.arch.persistence.room.paging;

import android.arch.paging.TiledDataSource;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.List;
import java.util.Set;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class LimitOffsetDataSource<T> extends TiledDataSource<T> {
    private final String mCountQuery = ("SELECT COUNT(*) FROM ( " + this.mSourceQuery.getSql() + " )");
    private final RoomDatabase mDb;
    private final String mLimitOffsetQuery = ("SELECT * FROM ( " + this.mSourceQuery.getSql() + " ) LIMIT ? OFFSET ?");
    private final Observer mObserver;
    private final RoomSQLiteQuery mSourceQuery;

    protected abstract List<T> convertRows(Cursor cursor);

    protected LimitOffsetDataSource(RoomDatabase db, RoomSQLiteQuery query, String... tables) {
        this.mDb = db;
        this.mSourceQuery = query;
        this.mObserver = new Observer(tables) {
            public void onInvalidated(@NonNull Set<String> set) {
                LimitOffsetDataSource.this.invalidate();
            }
        };
        db.getInvalidationTracker().addWeakObserver(this.mObserver);
    }

    public int countItems() {
        int i = 0;
        RoomSQLiteQuery sqLiteQuery = RoomSQLiteQuery.acquire(this.mCountQuery, this.mSourceQuery.getArgCount());
        sqLiteQuery.copyArgumentsFrom(this.mSourceQuery);
        Cursor cursor = this.mDb.query(sqLiteQuery);
        try {
            if (cursor.moveToFirst()) {
                i = cursor.getInt(0);
            } else {
                cursor.close();
                sqLiteQuery.release();
            }
            return i;
        } finally {
            cursor.close();
            sqLiteQuery.release();
        }
    }

    public boolean isInvalid() {
        this.mDb.getInvalidationTracker().refreshVersionsSync();
        return super.isInvalid();
    }

    @Nullable
    public List<T> loadRange(int startPosition, int loadCount) {
        RoomSQLiteQuery sqLiteQuery = RoomSQLiteQuery.acquire(this.mLimitOffsetQuery, this.mSourceQuery.getArgCount() + 2);
        sqLiteQuery.copyArgumentsFrom(this.mSourceQuery);
        sqLiteQuery.bindLong(sqLiteQuery.getArgCount() - 1, (long) loadCount);
        sqLiteQuery.bindLong(sqLiteQuery.getArgCount(), (long) startPosition);
        Cursor cursor = this.mDb.query(sqLiteQuery);
        try {
            List<T> convertRows = convertRows(cursor);
            return convertRows;
        } finally {
            cursor.close();
            sqLiteQuery.release();
        }
    }
}
