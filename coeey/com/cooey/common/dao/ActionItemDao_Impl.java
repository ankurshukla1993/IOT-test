package com.cooey.common.dao;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import android.support.annotation.NonNull;
import com.cooey.common.vo.ActionItem;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.facebook.internal.NativeProtocol;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ActionItemDao_Impl implements ActionItemDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfActionItem;

    public ActionItemDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfActionItem = new EntityInsertionAdapter<ActionItem>(__db) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `actionItems`(`patientName`,`patientId`,`tenantId`,`updatedOn`,`id`,`completed`,`title`,`type`,`createdOn`,`parameters`,`scheduledOn`,`completedOn`,`latitude`,`longitude`,`postAction`,`permissions`,`parentType`,`contextType`,`contextId`,`interventionId`,`completedBy`,`ownerId`,`visitId`,`assignerId`,`notes`,`applicationId`,`archived`,`externalId`,`active`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement stmt, ActionItem value) {
                int _tmp = 1;
                if (value.getPatientName() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getPatientName());
                }
                if (value.getPatientId() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getPatientId());
                }
                if (value.getTenantId() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getTenantId());
                }
                stmt.bindLong(4, value.getUpdatedOn());
                if (value.getId() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getId());
                }
                if (!value.isCompleted()) {
                    _tmp = 0;
                }
                stmt.bindLong(6, (long) _tmp);
                if (value.getTitle() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getTitle());
                }
                if (value.getType() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getType());
                }
                stmt.bindLong(9, value.getCreatedOn());
                if (value.getParameters() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getParameters());
                }
                stmt.bindLong(11, value.getScheduledOn());
                stmt.bindLong(12, value.getCompletedOn());
                stmt.bindDouble(13, value.getLatitude());
                stmt.bindDouble(14, value.getLongitude());
                if (value.getPostAction() == null) {
                    stmt.bindNull(15);
                } else {
                    stmt.bindString(15, value.getPostAction());
                }
                if (value.getPermissions() == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindString(16, value.getPermissions());
                }
                if (value.getParentType() == null) {
                    stmt.bindNull(17);
                } else {
                    stmt.bindString(17, value.getParentType());
                }
                if (value.getContextType() == null) {
                    stmt.bindNull(18);
                } else {
                    stmt.bindString(18, value.getContextType());
                }
                if (value.getContextId() == null) {
                    stmt.bindNull(19);
                } else {
                    stmt.bindString(19, value.getContextId());
                }
                if (value.getInterventionId() == null) {
                    stmt.bindNull(20);
                } else {
                    stmt.bindString(20, value.getInterventionId());
                }
                if (value.getCompletedBy() == null) {
                    stmt.bindNull(21);
                } else {
                    stmt.bindString(21, value.getCompletedBy());
                }
                if (value.getOwnerId() == null) {
                    stmt.bindNull(22);
                } else {
                    stmt.bindString(22, value.getOwnerId());
                }
                if (value.getVisitId() == null) {
                    stmt.bindNull(23);
                } else {
                    stmt.bindString(23, value.getVisitId());
                }
                if (value.getAssignerId() == null) {
                    stmt.bindNull(24);
                } else {
                    stmt.bindString(24, value.getAssignerId());
                }
                if (value.getNotes() == null) {
                    stmt.bindNull(25);
                } else {
                    stmt.bindString(25, value.getNotes());
                }
                if (value.getApplicationId() == null) {
                    stmt.bindNull(26);
                } else {
                    stmt.bindString(26, value.getApplicationId());
                }
                if (value.getArchived() == null) {
                    stmt.bindNull(27);
                } else {
                    stmt.bindString(27, value.getArchived());
                }
                if (value.getExternalId() == null) {
                    stmt.bindNull(28);
                } else {
                    stmt.bindString(28, value.getExternalId());
                }
                if (value.getActive() == null) {
                    stmt.bindNull(29);
                } else {
                    stmt.bindString(29, value.getActive());
                }
            }
        };
    }

    public void insert(ActionItem[] actionItems) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfActionItem.insert(actionItems);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(ActionItem actionItem) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfActionItem.insert(actionItem);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public LiveData<ActionItem> getActionItem(String id) {
        String _sql = "SELECT * FROM actionItems WHERE id = ? LIMIT 1";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM actionItems WHERE id = ? LIMIT 1", 1);
        if (id == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, id);
        }
        return new ComputableLiveData<ActionItem>() {
            private Observer _observer;

            protected ActionItem compute() {
                if (this._observer == null) {
                    this._observer = new Observer("actionItems", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08472.this.invalidate();
                        }
                    };
                    ActionItemDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = ActionItemDao_Impl.this.__db.query(_statement);
                try {
                    ActionItem _result;
                    int _cursorIndexOfPatientName = _cursor.getColumnIndexOrThrow("patientName");
                    int _cursorIndexOfPatientId = _cursor.getColumnIndexOrThrow("patientId");
                    int _cursorIndexOfTenantId = _cursor.getColumnIndexOrThrow("tenantId");
                    int _cursorIndexOfUpdatedOn = _cursor.getColumnIndexOrThrow("updatedOn");
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfCompleted = _cursor.getColumnIndexOrThrow("completed");
                    int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
                    int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
                    int _cursorIndexOfCreatedOn = _cursor.getColumnIndexOrThrow("createdOn");
                    int _cursorIndexOfParameters = _cursor.getColumnIndexOrThrow("parameters");
                    int _cursorIndexOfScheduledOn = _cursor.getColumnIndexOrThrow("scheduledOn");
                    int _cursorIndexOfCompletedOn = _cursor.getColumnIndexOrThrow("completedOn");
                    int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
                    int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
                    int _cursorIndexOfPostAction = _cursor.getColumnIndexOrThrow("postAction");
                    int _cursorIndexOfPermissions = _cursor.getColumnIndexOrThrow(NativeProtocol.RESULT_ARGS_PERMISSIONS);
                    int _cursorIndexOfParentType = _cursor.getColumnIndexOrThrow("parentType");
                    int _cursorIndexOfContextType = _cursor.getColumnIndexOrThrow("contextType");
                    int _cursorIndexOfContextId = _cursor.getColumnIndexOrThrow("contextId");
                    int _cursorIndexOfInterventionId = _cursor.getColumnIndexOrThrow("interventionId");
                    int _cursorIndexOfCompletedBy = _cursor.getColumnIndexOrThrow("completedBy");
                    int _cursorIndexOfOwnerId = _cursor.getColumnIndexOrThrow("ownerId");
                    int _cursorIndexOfVisitId = _cursor.getColumnIndexOrThrow("visitId");
                    int _cursorIndexOfAssignerId = _cursor.getColumnIndexOrThrow("assignerId");
                    int _cursorIndexOfNotes = _cursor.getColumnIndexOrThrow("notes");
                    int _cursorIndexOfApplicationId = _cursor.getColumnIndexOrThrow("applicationId");
                    int _cursorIndexOfArchived = _cursor.getColumnIndexOrThrow("archived");
                    int _cursorIndexOfExternalId = _cursor.getColumnIndexOrThrow(CooeySQLHelper.COL_EXT_ID);
                    int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow(AppStateModule.APP_STATE_ACTIVE);
                    if (_cursor.moveToFirst()) {
                        _result = new ActionItem();
                        _result.setPatientName(_cursor.getString(_cursorIndexOfPatientName));
                        _result.setPatientId(_cursor.getString(_cursorIndexOfPatientId));
                        _result.setTenantId(_cursor.getString(_cursorIndexOfTenantId));
                        _result.setUpdatedOn(_cursor.getLong(_cursorIndexOfUpdatedOn));
                        _result.setId(_cursor.getString(_cursorIndexOfId));
                        _result.setCompleted(_cursor.getInt(_cursorIndexOfCompleted) != 0);
                        _result.setTitle(_cursor.getString(_cursorIndexOfTitle));
                        _result.setType(_cursor.getString(_cursorIndexOfType));
                        _result.setCreatedOn(_cursor.getLong(_cursorIndexOfCreatedOn));
                        _result.setParameters(_cursor.getString(_cursorIndexOfParameters));
                        _result.setScheduledOn(_cursor.getLong(_cursorIndexOfScheduledOn));
                        _result.setCompletedOn(_cursor.getLong(_cursorIndexOfCompletedOn));
                        _result.setLatitude(_cursor.getDouble(_cursorIndexOfLatitude));
                        _result.setLongitude(_cursor.getDouble(_cursorIndexOfLongitude));
                        _result.setPostAction(_cursor.getString(_cursorIndexOfPostAction));
                        _result.setPermissions(_cursor.getString(_cursorIndexOfPermissions));
                        _result.setParentType(_cursor.getString(_cursorIndexOfParentType));
                        _result.setContextType(_cursor.getString(_cursorIndexOfContextType));
                        _result.setContextId(_cursor.getString(_cursorIndexOfContextId));
                        _result.setInterventionId(_cursor.getString(_cursorIndexOfInterventionId));
                        _result.setCompletedBy(_cursor.getString(_cursorIndexOfCompletedBy));
                        _result.setOwnerId(_cursor.getString(_cursorIndexOfOwnerId));
                        _result.setVisitId(_cursor.getString(_cursorIndexOfVisitId));
                        _result.setAssignerId(_cursor.getString(_cursorIndexOfAssignerId));
                        _result.setNotes(_cursor.getString(_cursorIndexOfNotes));
                        _result.setApplicationId(_cursor.getString(_cursorIndexOfApplicationId));
                        _result.setArchived(_cursor.getString(_cursorIndexOfArchived));
                        _result.setExternalId(_cursor.getString(_cursorIndexOfExternalId));
                        _result.setActive(_cursor.getString(_cursorIndexOfActive));
                    } else {
                        _result = null;
                    }
                    _cursor.close();
                    return _result;
                } catch (Throwable th) {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }

    public LiveData<List<ActionItem>> getActionItemsForUser(String patientId) {
        String _sql = "SELECT * FROM actionItems WHERE patientId = ? ORDER BY scheduledOn DESC";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM actionItems WHERE patientId = ? ORDER BY scheduledOn DESC", 1);
        if (patientId == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, patientId);
        }
        return new ComputableLiveData<List<ActionItem>>() {
            private Observer _observer;

            protected List<ActionItem> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("actionItems", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08493.this.invalidate();
                        }
                    };
                    ActionItemDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = ActionItemDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfPatientName = _cursor.getColumnIndexOrThrow("patientName");
                    int _cursorIndexOfPatientId = _cursor.getColumnIndexOrThrow("patientId");
                    int _cursorIndexOfTenantId = _cursor.getColumnIndexOrThrow("tenantId");
                    int _cursorIndexOfUpdatedOn = _cursor.getColumnIndexOrThrow("updatedOn");
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfCompleted = _cursor.getColumnIndexOrThrow("completed");
                    int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
                    int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
                    int _cursorIndexOfCreatedOn = _cursor.getColumnIndexOrThrow("createdOn");
                    int _cursorIndexOfParameters = _cursor.getColumnIndexOrThrow("parameters");
                    int _cursorIndexOfScheduledOn = _cursor.getColumnIndexOrThrow("scheduledOn");
                    int _cursorIndexOfCompletedOn = _cursor.getColumnIndexOrThrow("completedOn");
                    int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
                    int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
                    int _cursorIndexOfPostAction = _cursor.getColumnIndexOrThrow("postAction");
                    int _cursorIndexOfPermissions = _cursor.getColumnIndexOrThrow(NativeProtocol.RESULT_ARGS_PERMISSIONS);
                    int _cursorIndexOfParentType = _cursor.getColumnIndexOrThrow("parentType");
                    int _cursorIndexOfContextType = _cursor.getColumnIndexOrThrow("contextType");
                    int _cursorIndexOfContextId = _cursor.getColumnIndexOrThrow("contextId");
                    int _cursorIndexOfInterventionId = _cursor.getColumnIndexOrThrow("interventionId");
                    int _cursorIndexOfCompletedBy = _cursor.getColumnIndexOrThrow("completedBy");
                    int _cursorIndexOfOwnerId = _cursor.getColumnIndexOrThrow("ownerId");
                    int _cursorIndexOfVisitId = _cursor.getColumnIndexOrThrow("visitId");
                    int _cursorIndexOfAssignerId = _cursor.getColumnIndexOrThrow("assignerId");
                    int _cursorIndexOfNotes = _cursor.getColumnIndexOrThrow("notes");
                    int _cursorIndexOfApplicationId = _cursor.getColumnIndexOrThrow("applicationId");
                    int _cursorIndexOfArchived = _cursor.getColumnIndexOrThrow("archived");
                    int _cursorIndexOfExternalId = _cursor.getColumnIndexOrThrow(CooeySQLHelper.COL_EXT_ID);
                    int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow(AppStateModule.APP_STATE_ACTIVE);
                    List<ActionItem> arrayList = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        ActionItem _item = new ActionItem();
                        _item.setPatientName(_cursor.getString(_cursorIndexOfPatientName));
                        _item.setPatientId(_cursor.getString(_cursorIndexOfPatientId));
                        _item.setTenantId(_cursor.getString(_cursorIndexOfTenantId));
                        _item.setUpdatedOn(_cursor.getLong(_cursorIndexOfUpdatedOn));
                        _item.setId(_cursor.getString(_cursorIndexOfId));
                        _item.setCompleted(_cursor.getInt(_cursorIndexOfCompleted) != 0);
                        _item.setTitle(_cursor.getString(_cursorIndexOfTitle));
                        _item.setType(_cursor.getString(_cursorIndexOfType));
                        _item.setCreatedOn(_cursor.getLong(_cursorIndexOfCreatedOn));
                        _item.setParameters(_cursor.getString(_cursorIndexOfParameters));
                        _item.setScheduledOn(_cursor.getLong(_cursorIndexOfScheduledOn));
                        _item.setCompletedOn(_cursor.getLong(_cursorIndexOfCompletedOn));
                        _item.setLatitude(_cursor.getDouble(_cursorIndexOfLatitude));
                        _item.setLongitude(_cursor.getDouble(_cursorIndexOfLongitude));
                        _item.setPostAction(_cursor.getString(_cursorIndexOfPostAction));
                        _item.setPermissions(_cursor.getString(_cursorIndexOfPermissions));
                        _item.setParentType(_cursor.getString(_cursorIndexOfParentType));
                        _item.setContextType(_cursor.getString(_cursorIndexOfContextType));
                        _item.setContextId(_cursor.getString(_cursorIndexOfContextId));
                        _item.setInterventionId(_cursor.getString(_cursorIndexOfInterventionId));
                        _item.setCompletedBy(_cursor.getString(_cursorIndexOfCompletedBy));
                        _item.setOwnerId(_cursor.getString(_cursorIndexOfOwnerId));
                        _item.setVisitId(_cursor.getString(_cursorIndexOfVisitId));
                        _item.setAssignerId(_cursor.getString(_cursorIndexOfAssignerId));
                        _item.setNotes(_cursor.getString(_cursorIndexOfNotes));
                        _item.setApplicationId(_cursor.getString(_cursorIndexOfApplicationId));
                        _item.setArchived(_cursor.getString(_cursorIndexOfArchived));
                        _item.setExternalId(_cursor.getString(_cursorIndexOfExternalId));
                        _item.setActive(_cursor.getString(_cursorIndexOfActive));
                        arrayList.add(_item);
                    }
                    return arrayList;
                } finally {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }

    public LiveData<List<ActionItem>> getActionItemsBetween(String patientId, long startTime, long endTime) {
        String _sql = "SELECT * FROM actionItems WHERE patientId = ? AND scheduledOn > ? AND scheduledOn < ? ORDER BY scheduledOn DESC ";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM actionItems WHERE patientId = ? AND scheduledOn > ? AND scheduledOn < ? ORDER BY scheduledOn DESC ", 3);
        if (patientId == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, patientId);
        }
        _statement.bindLong(2, startTime);
        _statement.bindLong(3, endTime);
        return new ComputableLiveData<List<ActionItem>>() {
            private Observer _observer;

            protected List<ActionItem> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("actionItems", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08514.this.invalidate();
                        }
                    };
                    ActionItemDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = ActionItemDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfPatientName = _cursor.getColumnIndexOrThrow("patientName");
                    int _cursorIndexOfPatientId = _cursor.getColumnIndexOrThrow("patientId");
                    int _cursorIndexOfTenantId = _cursor.getColumnIndexOrThrow("tenantId");
                    int _cursorIndexOfUpdatedOn = _cursor.getColumnIndexOrThrow("updatedOn");
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfCompleted = _cursor.getColumnIndexOrThrow("completed");
                    int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
                    int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
                    int _cursorIndexOfCreatedOn = _cursor.getColumnIndexOrThrow("createdOn");
                    int _cursorIndexOfParameters = _cursor.getColumnIndexOrThrow("parameters");
                    int _cursorIndexOfScheduledOn = _cursor.getColumnIndexOrThrow("scheduledOn");
                    int _cursorIndexOfCompletedOn = _cursor.getColumnIndexOrThrow("completedOn");
                    int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
                    int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
                    int _cursorIndexOfPostAction = _cursor.getColumnIndexOrThrow("postAction");
                    int _cursorIndexOfPermissions = _cursor.getColumnIndexOrThrow(NativeProtocol.RESULT_ARGS_PERMISSIONS);
                    int _cursorIndexOfParentType = _cursor.getColumnIndexOrThrow("parentType");
                    int _cursorIndexOfContextType = _cursor.getColumnIndexOrThrow("contextType");
                    int _cursorIndexOfContextId = _cursor.getColumnIndexOrThrow("contextId");
                    int _cursorIndexOfInterventionId = _cursor.getColumnIndexOrThrow("interventionId");
                    int _cursorIndexOfCompletedBy = _cursor.getColumnIndexOrThrow("completedBy");
                    int _cursorIndexOfOwnerId = _cursor.getColumnIndexOrThrow("ownerId");
                    int _cursorIndexOfVisitId = _cursor.getColumnIndexOrThrow("visitId");
                    int _cursorIndexOfAssignerId = _cursor.getColumnIndexOrThrow("assignerId");
                    int _cursorIndexOfNotes = _cursor.getColumnIndexOrThrow("notes");
                    int _cursorIndexOfApplicationId = _cursor.getColumnIndexOrThrow("applicationId");
                    int _cursorIndexOfArchived = _cursor.getColumnIndexOrThrow("archived");
                    int _cursorIndexOfExternalId = _cursor.getColumnIndexOrThrow(CooeySQLHelper.COL_EXT_ID);
                    int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow(AppStateModule.APP_STATE_ACTIVE);
                    List<ActionItem> arrayList = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        ActionItem _item = new ActionItem();
                        _item.setPatientName(_cursor.getString(_cursorIndexOfPatientName));
                        _item.setPatientId(_cursor.getString(_cursorIndexOfPatientId));
                        _item.setTenantId(_cursor.getString(_cursorIndexOfTenantId));
                        _item.setUpdatedOn(_cursor.getLong(_cursorIndexOfUpdatedOn));
                        _item.setId(_cursor.getString(_cursorIndexOfId));
                        _item.setCompleted(_cursor.getInt(_cursorIndexOfCompleted) != 0);
                        _item.setTitle(_cursor.getString(_cursorIndexOfTitle));
                        _item.setType(_cursor.getString(_cursorIndexOfType));
                        _item.setCreatedOn(_cursor.getLong(_cursorIndexOfCreatedOn));
                        _item.setParameters(_cursor.getString(_cursorIndexOfParameters));
                        _item.setScheduledOn(_cursor.getLong(_cursorIndexOfScheduledOn));
                        _item.setCompletedOn(_cursor.getLong(_cursorIndexOfCompletedOn));
                        _item.setLatitude(_cursor.getDouble(_cursorIndexOfLatitude));
                        _item.setLongitude(_cursor.getDouble(_cursorIndexOfLongitude));
                        _item.setPostAction(_cursor.getString(_cursorIndexOfPostAction));
                        _item.setPermissions(_cursor.getString(_cursorIndexOfPermissions));
                        _item.setParentType(_cursor.getString(_cursorIndexOfParentType));
                        _item.setContextType(_cursor.getString(_cursorIndexOfContextType));
                        _item.setContextId(_cursor.getString(_cursorIndexOfContextId));
                        _item.setInterventionId(_cursor.getString(_cursorIndexOfInterventionId));
                        _item.setCompletedBy(_cursor.getString(_cursorIndexOfCompletedBy));
                        _item.setOwnerId(_cursor.getString(_cursorIndexOfOwnerId));
                        _item.setVisitId(_cursor.getString(_cursorIndexOfVisitId));
                        _item.setAssignerId(_cursor.getString(_cursorIndexOfAssignerId));
                        _item.setNotes(_cursor.getString(_cursorIndexOfNotes));
                        _item.setApplicationId(_cursor.getString(_cursorIndexOfApplicationId));
                        _item.setArchived(_cursor.getString(_cursorIndexOfArchived));
                        _item.setExternalId(_cursor.getString(_cursorIndexOfExternalId));
                        _item.setActive(_cursor.getString(_cursorIndexOfActive));
                        arrayList.add(_item);
                    }
                    return arrayList;
                } finally {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }
}
