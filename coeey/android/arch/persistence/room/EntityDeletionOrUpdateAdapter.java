package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class EntityDeletionOrUpdateAdapter<T> extends SharedSQLiteStatement {
    protected abstract void bind(SupportSQLiteStatement supportSQLiteStatement, T t);

    protected abstract String createQuery();

    public EntityDeletionOrUpdateAdapter(RoomDatabase database) {
        super(database);
    }

    public final int handle(T entity) {
        SupportSQLiteStatement stmt = acquire();
        try {
            bind(stmt, entity);
            int executeUpdateDelete = stmt.executeUpdateDelete();
            return executeUpdateDelete;
        } finally {
            release(stmt);
        }
    }

    public final int handleMultiple(Iterable<T> entities) {
        SupportSQLiteStatement stmt = acquire();
        int total = 0;
        try {
            for (T entity : entities) {
                bind(stmt, entity);
                total += stmt.executeUpdateDelete();
            }
            return total;
        } finally {
            release(stmt);
        }
    }

    public final int handleMultiple(T[] entities) {
        SupportSQLiteStatement stmt = acquire();
        int total = 0;
        try {
            for (T entity : entities) {
                bind(stmt, entity);
                total += stmt.executeUpdateDelete();
            }
            return total;
        } finally {
            release(stmt);
        }
    }
}
