package android.arch.persistence.db.framework;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Factory;

public class FrameworkSQLiteOpenHelperFactory implements Factory {
    public SupportSQLiteOpenHelper create(Configuration configuration) {
        return new FrameworkSQLiteOpenHelper(configuration.context, configuration.name, configuration.version, configuration.errorHandler, configuration.callback);
    }
}
