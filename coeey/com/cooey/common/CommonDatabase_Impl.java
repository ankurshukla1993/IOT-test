package com.cooey.common;

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
import com.cooey.common.dao.ActionItemDao;
import com.cooey.common.dao.ActionItemDao_Impl;
import com.cooey.common.dao.TimelineItemDao;
import com.cooey.common.dao.TimelineItemDao_Impl;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.facebook.internal.NativeProtocol;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.measurement.AppMeasurement$Param;
import java.util.HashMap;
import java.util.HashSet;

public class CommonDatabase_Impl extends CommonDatabase {
    private volatile ActionItemDao _actionItemDao;
    private volatile TimelineItemDao _timelineItemDao;

    class C08411 extends Delegate {
        C08411() {
        }

        public void createAllTables(SupportSQLiteDatabase _db) {
            _db.execSQL("CREATE TABLE IF NOT EXISTS `timelineItems` (`id` TEXT NOT NULL, `type` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `userId` TEXT NOT NULL, `subType` TEXT, PRIMARY KEY(`id`))");
            _db.execSQL("CREATE TABLE IF NOT EXISTS `actionItems` (`patientName` TEXT, `patientId` TEXT, `tenantId` TEXT, `updatedOn` INTEGER NOT NULL, `id` TEXT, `completed` INTEGER NOT NULL, `title` TEXT, `type` TEXT, `createdOn` INTEGER NOT NULL, `parameters` TEXT, `scheduledOn` INTEGER NOT NULL, `completedOn` INTEGER NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `postAction` TEXT, `permissions` TEXT, `parentType` TEXT, `contextType` TEXT, `contextId` TEXT, `interventionId` TEXT, `completedBy` TEXT, `ownerId` TEXT, `visitId` TEXT, `assignerId` TEXT, `notes` TEXT, `applicationId` TEXT, `archived` TEXT, `externalId` TEXT, `active` TEXT, PRIMARY KEY(`id`))");
            _db.execSQL(RoomMasterTable.CREATE_QUERY);
            _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"e3e5dd8507df97dab47d80821f79f44b\")");
        }

        public void dropAllTables(SupportSQLiteDatabase _db) {
            _db.execSQL("DROP TABLE IF EXISTS `timelineItems`");
            _db.execSQL("DROP TABLE IF EXISTS `actionItems`");
        }

        protected void onCreate(SupportSQLiteDatabase _db) {
            if (CommonDatabase_Impl.this.mCallbacks != null) {
                int _size = CommonDatabase_Impl.this.mCallbacks.size();
                for (int _i = 0; _i < _size; _i++) {
                    ((Callback) CommonDatabase_Impl.this.mCallbacks.get(_i)).onCreate(_db);
                }
            }
        }

        public void onOpen(SupportSQLiteDatabase _db) {
            CommonDatabase_Impl.this.mDatabase = _db;
            CommonDatabase_Impl.this.internalInitInvalidationTracker(_db);
            if (CommonDatabase_Impl.this.mCallbacks != null) {
                int _size = CommonDatabase_Impl.this.mCallbacks.size();
                for (int _i = 0; _i < _size; _i++) {
                    ((Callback) CommonDatabase_Impl.this.mCallbacks.get(_i)).onOpen(_db);
                }
            }
        }

        protected void validateMigration(SupportSQLiteDatabase _db) {
            HashMap<String, Column> _columnsTimelineItems = new HashMap(5);
            _columnsTimelineItems.put(ShareConstants.WEB_DIALOG_PARAM_ID, new Column(ShareConstants.WEB_DIALOG_PARAM_ID, "TEXT", true, 1));
            _columnsTimelineItems.put("type", new Column("type", "INTEGER", true, 0));
            _columnsTimelineItems.put(AppMeasurement$Param.TIMESTAMP, new Column(AppMeasurement$Param.TIMESTAMP, "INTEGER", true, 0));
            _columnsTimelineItems.put("userId", new Column("userId", "TEXT", true, 0));
            _columnsTimelineItems.put("subType", new Column("subType", "TEXT", false, 0));
            TableInfo _infoTimelineItems = new TableInfo("timelineItems", _columnsTimelineItems, new HashSet(0));
            TableInfo _existingTimelineItems = TableInfo.read(_db, "timelineItems");
            if (_infoTimelineItems.equals(_existingTimelineItems)) {
                HashMap<String, Column> _columnsActionItems = new HashMap(29);
                _columnsActionItems.put("patientName", new Column("patientName", "TEXT", false, 0));
                _columnsActionItems.put("patientId", new Column("patientId", "TEXT", false, 0));
                _columnsActionItems.put("tenantId", new Column("tenantId", "TEXT", false, 0));
                _columnsActionItems.put("updatedOn", new Column("updatedOn", "INTEGER", true, 0));
                _columnsActionItems.put(ShareConstants.WEB_DIALOG_PARAM_ID, new Column(ShareConstants.WEB_DIALOG_PARAM_ID, "TEXT", false, 1));
                _columnsActionItems.put("completed", new Column("completed", "INTEGER", true, 0));
                _columnsActionItems.put("title", new Column("title", "TEXT", false, 0));
                _columnsActionItems.put("type", new Column("type", "TEXT", false, 0));
                _columnsActionItems.put("createdOn", new Column("createdOn", "INTEGER", true, 0));
                _columnsActionItems.put("parameters", new Column("parameters", "TEXT", false, 0));
                _columnsActionItems.put("scheduledOn", new Column("scheduledOn", "INTEGER", true, 0));
                _columnsActionItems.put("completedOn", new Column("completedOn", "INTEGER", true, 0));
                _columnsActionItems.put("latitude", new Column("latitude", "REAL", true, 0));
                _columnsActionItems.put("longitude", new Column("longitude", "REAL", true, 0));
                _columnsActionItems.put("postAction", new Column("postAction", "TEXT", false, 0));
                _columnsActionItems.put(NativeProtocol.RESULT_ARGS_PERMISSIONS, new Column(NativeProtocol.RESULT_ARGS_PERMISSIONS, "TEXT", false, 0));
                _columnsActionItems.put("parentType", new Column("parentType", "TEXT", false, 0));
                _columnsActionItems.put("contextType", new Column("contextType", "TEXT", false, 0));
                _columnsActionItems.put("contextId", new Column("contextId", "TEXT", false, 0));
                _columnsActionItems.put("interventionId", new Column("interventionId", "TEXT", false, 0));
                _columnsActionItems.put("completedBy", new Column("completedBy", "TEXT", false, 0));
                _columnsActionItems.put("ownerId", new Column("ownerId", "TEXT", false, 0));
                _columnsActionItems.put("visitId", new Column("visitId", "TEXT", false, 0));
                _columnsActionItems.put("assignerId", new Column("assignerId", "TEXT", false, 0));
                _columnsActionItems.put("notes", new Column("notes", "TEXT", false, 0));
                _columnsActionItems.put("applicationId", new Column("applicationId", "TEXT", false, 0));
                _columnsActionItems.put("archived", new Column("archived", "TEXT", false, 0));
                _columnsActionItems.put(CooeySQLHelper.COL_EXT_ID, new Column(CooeySQLHelper.COL_EXT_ID, "TEXT", false, 0));
                _columnsActionItems.put(AppStateModule.APP_STATE_ACTIVE, new Column(AppStateModule.APP_STATE_ACTIVE, "TEXT", false, 0));
                TableInfo _infoActionItems = new TableInfo("actionItems", _columnsActionItems, new HashSet(0));
                TableInfo _existingActionItems = TableInfo.read(_db, "actionItems");
                if (!_infoActionItems.equals(_existingActionItems)) {
                    throw new IllegalStateException("Migration didn't properly handle actionItems(com.cooey.common.vo.ActionItem).\n Expected:\n" + _infoActionItems + "\n Found:\n" + _existingActionItems);
                }
                return;
            }
            throw new IllegalStateException("Migration didn't properly handle timelineItems(com.cooey.common.vo.timeline.TimelineItem).\n Expected:\n" + _infoTimelineItems + "\n Found:\n" + _existingTimelineItems);
        }
    }

    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(Configuration.builder(configuration.context).name(configuration.name).version(1).callback(new RoomOpenHelper(configuration, new C08411(), "e3e5dd8507df97dab47d80821f79f44b")).build());
    }

    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new String[]{"timelineItems", "actionItems"});
    }

    public TimelineItemDao TimelineItemDao() {
        if (this._timelineItemDao != null) {
            return this._timelineItemDao;
        }
        TimelineItemDao timelineItemDao;
        synchronized (this) {
            if (this._timelineItemDao == null) {
                this._timelineItemDao = new TimelineItemDao_Impl(this);
            }
            timelineItemDao = this._timelineItemDao;
        }
        return timelineItemDao;
    }

    public ActionItemDao ActionItemDao() {
        if (this._actionItemDao != null) {
            return this._actionItemDao;
        }
        ActionItemDao actionItemDao;
        synchronized (this) {
            if (this._actionItemDao == null) {
                this._actionItemDao = new ActionItemDao_Impl(this);
            }
            actionItemDao = this._actionItemDao;
        }
        return actionItemDao;
    }
}
