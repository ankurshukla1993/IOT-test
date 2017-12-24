package com.cooey.android.vitals;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.arch.persistence.room.RoomMasterTable;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import com.cooey.android.vitals.dao.VitalBlueprintDao;
import com.cooey.android.vitals.dao.VitalBlueprintDao_Impl;
import com.cooey.android.vitals.dao.VitalDao;
import com.cooey.android.vitals.dao.VitalDao_Impl;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.facebook.share.internal.ShareConstants;
import java.util.HashMap;
import java.util.HashSet;

public class VitalsDatabase_Impl extends VitalsDatabase {
    private volatile VitalBlueprintDao _vitalBlueprintDao;
    private volatile VitalDao _vitalDao;

    class C08141 extends Delegate {
        C08141() {
        }

        public void createAllTables(SupportSQLiteDatabase _db) {
            _db.execSQL("CREATE TABLE IF NOT EXISTS `vitalBlueprints` (`iconURL` TEXT, `fields` TEXT, `id` TEXT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `isGraphRequired` INTEGER, `isPrimary` INTEGER, `isSynced` INTEGER, PRIMARY KEY(`id`))");
            _db.execSQL("CREATE TABLE IF NOT EXISTS `vitals` (`id` TEXT NOT NULL, `vitalType` TEXT NOT NULL, `takenOn` INTEGER NOT NULL, `parameters` TEXT, `userId` TEXT NOT NULL, `takenBy` TEXT, `postAction` TEXT, `deviceId` TEXT, `deviceType` TEXT, `platform` TEXT, `contextType` TEXT, `contextId` TEXT, `deviceReading` INTEGER, `createdOn` INTEGER NOT NULL, `updatedOn` INTEGER NOT NULL, `tenantId` TEXT, `applicationId` TEXT, `externalId` TEXT, `isActive` INTEGER NOT NULL, `isArchived` INTEGER NOT NULL, PRIMARY KEY(`id`))");
            _db.execSQL(RoomMasterTable.CREATE_QUERY);
            _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"94628d6d55e07d858e7461f9c78b3cea\")");
        }

        public void dropAllTables(SupportSQLiteDatabase _db) {
            _db.execSQL("DROP TABLE IF EXISTS `vitalBlueprints`");
            _db.execSQL("DROP TABLE IF EXISTS `vitals`");
        }

        protected void onCreate(SupportSQLiteDatabase _db) {
            if (VitalsDatabase_Impl.this.mCallbacks != null) {
                int _size = VitalsDatabase_Impl.this.mCallbacks.size();
                for (int _i = 0; _i < _size; _i++) {
                    ((Callback) VitalsDatabase_Impl.this.mCallbacks.get(_i)).onCreate(_db);
                }
            }
        }

        public void onOpen(SupportSQLiteDatabase _db) {
            VitalsDatabase_Impl.this.mDatabase = _db;
            VitalsDatabase_Impl.this.internalInitInvalidationTracker(_db);
            if (VitalsDatabase_Impl.this.mCallbacks != null) {
                int _size = VitalsDatabase_Impl.this.mCallbacks.size();
                for (int _i = 0; _i < _size; _i++) {
                    ((Callback) VitalsDatabase_Impl.this.mCallbacks.get(_i)).onOpen(_db);
                }
            }
        }

        protected void validateMigration(SupportSQLiteDatabase _db) {
            HashMap<String, Column> _columnsVitalBlueprints = new HashMap(8);
            _columnsVitalBlueprints.put("iconURL", new Column("iconURL", "TEXT", false, 0));
            _columnsVitalBlueprints.put("fields", new Column("fields", "TEXT", false, 0));
            _columnsVitalBlueprints.put(ShareConstants.WEB_DIALOG_PARAM_ID, new Column(ShareConstants.WEB_DIALOG_PARAM_ID, "TEXT", true, 1));
            _columnsVitalBlueprints.put("name", new Column("name", "TEXT", true, 0));
            _columnsVitalBlueprints.put("type", new Column("type", "TEXT", true, 0));
            _columnsVitalBlueprints.put("isGraphRequired", new Column("isGraphRequired", "INTEGER", false, 0));
            _columnsVitalBlueprints.put("isPrimary", new Column("isPrimary", "INTEGER", false, 0));
            _columnsVitalBlueprints.put("isSynced", new Column("isSynced", "INTEGER", false, 0));
            TableInfo _infoVitalBlueprints = new TableInfo("vitalBlueprints", _columnsVitalBlueprints, new HashSet(0));
            TableInfo _existingVitalBlueprints = TableInfo.read(_db, "vitalBlueprints");
            if (_infoVitalBlueprints.equals(_existingVitalBlueprints)) {
                HashMap<String, Column> _columnsVitals = new HashMap(20);
                _columnsVitals.put(ShareConstants.WEB_DIALOG_PARAM_ID, new Column(ShareConstants.WEB_DIALOG_PARAM_ID, "TEXT", true, 1));
                _columnsVitals.put("vitalType", new Column("vitalType", "TEXT", true, 0));
                _columnsVitals.put("takenOn", new Column("takenOn", "INTEGER", true, 0));
                _columnsVitals.put("parameters", new Column("parameters", "TEXT", false, 0));
                _columnsVitals.put("userId", new Column("userId", "TEXT", true, 0));
                _columnsVitals.put("takenBy", new Column("takenBy", "TEXT", false, 0));
                _columnsVitals.put("postAction", new Column("postAction", "TEXT", false, 0));
                _columnsVitals.put("deviceId", new Column("deviceId", "TEXT", false, 0));
                _columnsVitals.put("deviceType", new Column("deviceType", "TEXT", false, 0));
                _columnsVitals.put("platform", new Column("platform", "TEXT", false, 0));
                _columnsVitals.put("contextType", new Column("contextType", "TEXT", false, 0));
                _columnsVitals.put("contextId", new Column("contextId", "TEXT", false, 0));
                _columnsVitals.put("deviceReading", new Column("deviceReading", "INTEGER", false, 0));
                _columnsVitals.put("createdOn", new Column("createdOn", "INTEGER", true, 0));
                _columnsVitals.put("updatedOn", new Column("updatedOn", "INTEGER", true, 0));
                _columnsVitals.put("tenantId", new Column("tenantId", "TEXT", false, 0));
                _columnsVitals.put("applicationId", new Column("applicationId", "TEXT", false, 0));
                _columnsVitals.put(CooeySQLHelper.COL_EXT_ID, new Column(CooeySQLHelper.COL_EXT_ID, "TEXT", false, 0));
                _columnsVitals.put("isActive", new Column("isActive", "INTEGER", true, 0));
                _columnsVitals.put("isArchived", new Column("isArchived", "INTEGER", true, 0));
                TableInfo _infoVitals = new TableInfo("vitals", _columnsVitals, new HashSet(0));
                TableInfo _existingVitals = TableInfo.read(_db, "vitals");
                if (!_infoVitals.equals(_existingVitals)) {
                    throw new IllegalStateException("Migration didn't properly handle vitals(com.cooey.android.vitals.Vital).\n Expected:\n" + _infoVitals + "\n Found:\n" + _existingVitals);
                }
                return;
            }
            throw new IllegalStateException("Migration didn't properly handle vitalBlueprints(com.cooey.android.vitals.VitalBlueprint).\n Expected:\n" + _infoVitalBlueprints + "\n Found:\n" + _existingVitalBlueprints);
        }
    }

    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(Configuration.builder(configuration.context).name(configuration.name).version(1).callback(new RoomOpenHelper(configuration, new C08141(), "94628d6d55e07d858e7461f9c78b3cea")).build());
    }

    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new String[]{"vitalBlueprints", "vitals"});
    }

    public VitalBlueprintDao VitalBlueprintDao() {
        if (this._vitalBlueprintDao != null) {
            return this._vitalBlueprintDao;
        }
        VitalBlueprintDao vitalBlueprintDao;
        synchronized (this) {
            if (this._vitalBlueprintDao == null) {
                this._vitalBlueprintDao = new VitalBlueprintDao_Impl(this);
            }
            vitalBlueprintDao = this._vitalBlueprintDao;
        }
        return vitalBlueprintDao;
    }

    public VitalDao VitalDao() {
        if (this._vitalDao != null) {
            return this._vitalDao;
        }
        VitalDao vitalDao;
        synchronized (this) {
            if (this._vitalDao == null) {
                this._vitalDao = new VitalDao_Impl(this);
            }
            vitalDao = this._vitalDao;
        }
        return vitalDao;
    }
}
