package com.cooey.android.vitals.dao;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import android.support.annotation.NonNull;
import com.cooey.android.vitals.Vital;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class VitalDao_Impl implements VitalDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfVital;
    private final EntityInsertionAdapter __insertionAdapterOfVital;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfVital;

    public VitalDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfVital = new EntityInsertionAdapter<Vital>(__db) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `vitals`(`id`,`vitalType`,`takenOn`,`parameters`,`userId`,`takenBy`,`postAction`,`deviceId`,`deviceType`,`platform`,`contextType`,`contextId`,`deviceReading`,`createdOn`,`updatedOn`,`tenantId`,`applicationId`,`externalId`,`isActive`,`isArchived`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement stmt, Vital value) {
                Integer _tmp;
                int _tmp_1;
                int _tmp_2;
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getVitalType() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getVitalType());
                }
                stmt.bindLong(3, value.getTakenOn());
                if (value.getParameters() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getParameters());
                }
                if (value.getUserId() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getUserId());
                }
                if (value.getTakenBy() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getTakenBy());
                }
                if (value.getPostAction() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getPostAction());
                }
                if (value.getDeviceId() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getDeviceId());
                }
                if (value.getDeviceType() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getDeviceType());
                }
                if (value.getPlatform() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getPlatform());
                }
                if (value.getContextType() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getContextType());
                }
                if (value.getContextId() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getContextId());
                }
                if (value.getDeviceReading() == null) {
                    _tmp = null;
                } else {
                    _tmp = Integer.valueOf(value.getDeviceReading().booleanValue() ? 1 : 0);
                }
                if (_tmp == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindLong(13, (long) _tmp.intValue());
                }
                stmt.bindLong(14, value.getCreatedOn());
                stmt.bindLong(15, value.getUpdatedOn());
                if (value.getTenantId() == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindString(16, value.getTenantId());
                }
                if (value.getApplicationId() == null) {
                    stmt.bindNull(17);
                } else {
                    stmt.bindString(17, value.getApplicationId());
                }
                if (value.getExternalId() == null) {
                    stmt.bindNull(18);
                } else {
                    stmt.bindString(18, value.getExternalId());
                }
                if (value.isActive()) {
                    _tmp_1 = 1;
                } else {
                    _tmp_1 = 0;
                }
                stmt.bindLong(19, (long) _tmp_1);
                if (value.isArchived()) {
                    _tmp_2 = 1;
                } else {
                    _tmp_2 = 0;
                }
                stmt.bindLong(20, (long) _tmp_2);
            }
        };
        this.__deletionAdapterOfVital = new EntityDeletionOrUpdateAdapter<Vital>(__db) {
            public String createQuery() {
                return "DELETE FROM `vitals` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement stmt, Vital value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
            }
        };
        this.__updateAdapterOfVital = new EntityDeletionOrUpdateAdapter<Vital>(__db) {
            public String createQuery() {
                return "UPDATE OR ABORT `vitals` SET `id` = ?,`vitalType` = ?,`takenOn` = ?,`parameters` = ?,`userId` = ?,`takenBy` = ?,`postAction` = ?,`deviceId` = ?,`deviceType` = ?,`platform` = ?,`contextType` = ?,`contextId` = ?,`deviceReading` = ?,`createdOn` = ?,`updatedOn` = ?,`tenantId` = ?,`applicationId` = ?,`externalId` = ?,`isActive` = ?,`isArchived` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement stmt, Vital value) {
                Integer _tmp;
                int _tmp_1;
                int _tmp_2;
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getVitalType() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getVitalType());
                }
                stmt.bindLong(3, value.getTakenOn());
                if (value.getParameters() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getParameters());
                }
                if (value.getUserId() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getUserId());
                }
                if (value.getTakenBy() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getTakenBy());
                }
                if (value.getPostAction() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getPostAction());
                }
                if (value.getDeviceId() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getDeviceId());
                }
                if (value.getDeviceType() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getDeviceType());
                }
                if (value.getPlatform() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getPlatform());
                }
                if (value.getContextType() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getContextType());
                }
                if (value.getContextId() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getContextId());
                }
                if (value.getDeviceReading() == null) {
                    _tmp = null;
                } else {
                    _tmp = Integer.valueOf(value.getDeviceReading().booleanValue() ? 1 : 0);
                }
                if (_tmp == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindLong(13, (long) _tmp.intValue());
                }
                stmt.bindLong(14, value.getCreatedOn());
                stmt.bindLong(15, value.getUpdatedOn());
                if (value.getTenantId() == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindString(16, value.getTenantId());
                }
                if (value.getApplicationId() == null) {
                    stmt.bindNull(17);
                } else {
                    stmt.bindString(17, value.getApplicationId());
                }
                if (value.getExternalId() == null) {
                    stmt.bindNull(18);
                } else {
                    stmt.bindString(18, value.getExternalId());
                }
                if (value.isActive()) {
                    _tmp_1 = 1;
                } else {
                    _tmp_1 = 0;
                }
                stmt.bindLong(19, (long) _tmp_1);
                if (value.isArchived()) {
                    _tmp_2 = 1;
                } else {
                    _tmp_2 = 0;
                }
                stmt.bindLong(20, (long) _tmp_2);
                if (value.getId() == null) {
                    stmt.bindNull(21);
                } else {
                    stmt.bindString(21, value.getId());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
            public String createQuery() {
                String _query = "DELETE FROM vitals";
                return "DELETE FROM vitals";
            }
        };
    }

    public void insert(Vital vital) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfVital.insert(vital);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(List<Vital> vitals) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfVital.insert(vitals);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(Vital vital) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfVital.handle(vital);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(Vital vital) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfVital.handle(vital);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public int deleteAll() {
        SupportSQLiteStatement _stmt = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            int _result = _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return _result;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(_stmt);
        }
    }

    public List<VitalBlueprint> getAll() {
        String _sql = "SELECT * FROM vitals";
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitals", 0);
        Cursor _cursor = this.__db.query(_statement);
        try {
            int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
            List<VitalBlueprint> _result = new ArrayList(_cursor.getCount());
            while (_cursor.moveToNext()) {
                _result.add(new VitalBlueprint(_cursor.getString(_cursorIndexOfId), null, null, null, null, null));
            }
            return _result;
        } finally {
            _cursor.close();
            _statement.release();
        }
    }

    public LiveData<Vital> getVitalsForPatient(String userId) {
        String _sql = "SELECT * FROM vitals WHERE userId = ?";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitals WHERE userId = ?", 1);
        if (userId == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, userId);
        }
        return new ComputableLiveData<Vital>() {
            private Observer _observer;

            protected Vital compute() {
                if (this._observer == null) {
                    this._observer = new Observer("vitals", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08285.this.invalidate();
                        }
                    };
                    VitalDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = VitalDao_Impl.this.__db.query(_statement);
                try {
                    Vital _result;
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfVitalType = _cursor.getColumnIndexOrThrow("vitalType");
                    int _cursorIndexOfTakenOn = _cursor.getColumnIndexOrThrow("takenOn");
                    int _cursorIndexOfParameters = _cursor.getColumnIndexOrThrow("parameters");
                    int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("userId");
                    int _cursorIndexOfTakenBy = _cursor.getColumnIndexOrThrow("takenBy");
                    int _cursorIndexOfPostAction = _cursor.getColumnIndexOrThrow("postAction");
                    int _cursorIndexOfDeviceId = _cursor.getColumnIndexOrThrow("deviceId");
                    int _cursorIndexOfDeviceType = _cursor.getColumnIndexOrThrow("deviceType");
                    int _cursorIndexOfPlatform = _cursor.getColumnIndexOrThrow("platform");
                    int _cursorIndexOfContextType = _cursor.getColumnIndexOrThrow("contextType");
                    int _cursorIndexOfContextId = _cursor.getColumnIndexOrThrow("contextId");
                    int _cursorIndexOfDeviceReading = _cursor.getColumnIndexOrThrow("deviceReading");
                    int _cursorIndexOfCreatedOn = _cursor.getColumnIndexOrThrow("createdOn");
                    int _cursorIndexOfUpdatedOn = _cursor.getColumnIndexOrThrow("updatedOn");
                    int _cursorIndexOfTenantId = _cursor.getColumnIndexOrThrow("tenantId");
                    int _cursorIndexOfApplicationId = _cursor.getColumnIndexOrThrow("applicationId");
                    int _cursorIndexOfExternalId = _cursor.getColumnIndexOrThrow(CooeySQLHelper.COL_EXT_ID);
                    int _cursorIndexOfIsActive = _cursor.getColumnIndexOrThrow("isActive");
                    int _cursorIndexOfIsArchived = _cursor.getColumnIndexOrThrow("isArchived");
                    if (_cursor.moveToFirst()) {
                        Integer _tmp;
                        Boolean _tmpDeviceReading;
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpVitalType = _cursor.getString(_cursorIndexOfVitalType);
                        long _tmpTakenOn = _cursor.getLong(_cursorIndexOfTakenOn);
                        String _tmpParameters = _cursor.getString(_cursorIndexOfParameters);
                        String _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
                        String _tmpTakenBy = _cursor.getString(_cursorIndexOfTakenBy);
                        String _tmpPostAction = _cursor.getString(_cursorIndexOfPostAction);
                        String _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
                        String _tmpDeviceType = _cursor.getString(_cursorIndexOfDeviceType);
                        String _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
                        String _tmpContextType = _cursor.getString(_cursorIndexOfContextType);
                        String _tmpContextId = _cursor.getString(_cursorIndexOfContextId);
                        if (_cursor.isNull(_cursorIndexOfDeviceReading)) {
                            _tmp = null;
                        } else {
                            _tmp = Integer.valueOf(_cursor.getInt(_cursorIndexOfDeviceReading));
                        }
                        if (_tmp == null) {
                            _tmpDeviceReading = null;
                        } else {
                            _tmpDeviceReading = Boolean.valueOf(_tmp.intValue() != 0);
                        }
                        _result = new Vital(_tmpId, _tmpVitalType, _tmpTakenOn, _tmpParameters, _tmpUserId, _tmpTakenBy, _tmpPostAction, _tmpDeviceId, _tmpDeviceType, _tmpPlatform, _tmpContextType, _tmpContextId, _tmpDeviceReading);
                        _result.setCreatedOn(_cursor.getLong(_cursorIndexOfCreatedOn));
                        _result.setUpdatedOn(_cursor.getLong(_cursorIndexOfUpdatedOn));
                        _result.setTenantId(_cursor.getString(_cursorIndexOfTenantId));
                        _result.setApplicationId(_cursor.getString(_cursorIndexOfApplicationId));
                        _result.setExternalId(_cursor.getString(_cursorIndexOfExternalId));
                        _result.setActive(_cursor.getInt(_cursorIndexOfIsActive) != 0);
                        _result.setArchived(_cursor.getInt(_cursorIndexOfIsArchived) != 0);
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

    public LiveData<Vital> getVital(String id) {
        String _sql = "SELECT * FROM vitals WHERE id = ? LIMIT 1";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitals WHERE id = ? LIMIT 1", 1);
        if (id == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, id);
        }
        return new ComputableLiveData<Vital>() {
            private Observer _observer;

            protected Vital compute() {
                if (this._observer == null) {
                    this._observer = new Observer("vitals", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08306.this.invalidate();
                        }
                    };
                    VitalDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = VitalDao_Impl.this.__db.query(_statement);
                try {
                    Vital _result;
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfVitalType = _cursor.getColumnIndexOrThrow("vitalType");
                    int _cursorIndexOfTakenOn = _cursor.getColumnIndexOrThrow("takenOn");
                    int _cursorIndexOfParameters = _cursor.getColumnIndexOrThrow("parameters");
                    int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("userId");
                    int _cursorIndexOfTakenBy = _cursor.getColumnIndexOrThrow("takenBy");
                    int _cursorIndexOfPostAction = _cursor.getColumnIndexOrThrow("postAction");
                    int _cursorIndexOfDeviceId = _cursor.getColumnIndexOrThrow("deviceId");
                    int _cursorIndexOfDeviceType = _cursor.getColumnIndexOrThrow("deviceType");
                    int _cursorIndexOfPlatform = _cursor.getColumnIndexOrThrow("platform");
                    int _cursorIndexOfContextType = _cursor.getColumnIndexOrThrow("contextType");
                    int _cursorIndexOfContextId = _cursor.getColumnIndexOrThrow("contextId");
                    int _cursorIndexOfDeviceReading = _cursor.getColumnIndexOrThrow("deviceReading");
                    int _cursorIndexOfCreatedOn = _cursor.getColumnIndexOrThrow("createdOn");
                    int _cursorIndexOfUpdatedOn = _cursor.getColumnIndexOrThrow("updatedOn");
                    int _cursorIndexOfTenantId = _cursor.getColumnIndexOrThrow("tenantId");
                    int _cursorIndexOfApplicationId = _cursor.getColumnIndexOrThrow("applicationId");
                    int _cursorIndexOfExternalId = _cursor.getColumnIndexOrThrow(CooeySQLHelper.COL_EXT_ID);
                    int _cursorIndexOfIsActive = _cursor.getColumnIndexOrThrow("isActive");
                    int _cursorIndexOfIsArchived = _cursor.getColumnIndexOrThrow("isArchived");
                    if (_cursor.moveToFirst()) {
                        Integer _tmp;
                        Boolean _tmpDeviceReading;
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpVitalType = _cursor.getString(_cursorIndexOfVitalType);
                        long _tmpTakenOn = _cursor.getLong(_cursorIndexOfTakenOn);
                        String _tmpParameters = _cursor.getString(_cursorIndexOfParameters);
                        String _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
                        String _tmpTakenBy = _cursor.getString(_cursorIndexOfTakenBy);
                        String _tmpPostAction = _cursor.getString(_cursorIndexOfPostAction);
                        String _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
                        String _tmpDeviceType = _cursor.getString(_cursorIndexOfDeviceType);
                        String _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
                        String _tmpContextType = _cursor.getString(_cursorIndexOfContextType);
                        String _tmpContextId = _cursor.getString(_cursorIndexOfContextId);
                        if (_cursor.isNull(_cursorIndexOfDeviceReading)) {
                            _tmp = null;
                        } else {
                            _tmp = Integer.valueOf(_cursor.getInt(_cursorIndexOfDeviceReading));
                        }
                        if (_tmp == null) {
                            _tmpDeviceReading = null;
                        } else {
                            _tmpDeviceReading = Boolean.valueOf(_tmp.intValue() != 0);
                        }
                        _result = new Vital(_tmpId, _tmpVitalType, _tmpTakenOn, _tmpParameters, _tmpUserId, _tmpTakenBy, _tmpPostAction, _tmpDeviceId, _tmpDeviceType, _tmpPlatform, _tmpContextType, _tmpContextId, _tmpDeviceReading);
                        _result.setCreatedOn(_cursor.getLong(_cursorIndexOfCreatedOn));
                        _result.setUpdatedOn(_cursor.getLong(_cursorIndexOfUpdatedOn));
                        _result.setTenantId(_cursor.getString(_cursorIndexOfTenantId));
                        _result.setApplicationId(_cursor.getString(_cursorIndexOfApplicationId));
                        _result.setExternalId(_cursor.getString(_cursorIndexOfExternalId));
                        _result.setActive(_cursor.getInt(_cursorIndexOfIsActive) != 0);
                        _result.setArchived(_cursor.getInt(_cursorIndexOfIsArchived) != 0);
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

    public LiveData<Vital> getLatestVitalForTypeByTimestampDesc(String type, String userId) {
        String _sql = "SELECT * FROM vitals WHERE vitalType = ? AND userId = ? ORDER BY takenOn DESC LIMIT 1";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitals WHERE vitalType = ? AND userId = ? ORDER BY takenOn DESC LIMIT 1", 2);
        if (type == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, type);
        }
        if (userId == null) {
            _statement.bindNull(2);
        } else {
            _statement.bindString(2, userId);
        }
        return new ComputableLiveData<Vital>() {
            private Observer _observer;

            protected Vital compute() {
                if (this._observer == null) {
                    this._observer = new Observer("vitals", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08327.this.invalidate();
                        }
                    };
                    VitalDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = VitalDao_Impl.this.__db.query(_statement);
                try {
                    Vital _result;
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfVitalType = _cursor.getColumnIndexOrThrow("vitalType");
                    int _cursorIndexOfTakenOn = _cursor.getColumnIndexOrThrow("takenOn");
                    int _cursorIndexOfParameters = _cursor.getColumnIndexOrThrow("parameters");
                    int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("userId");
                    int _cursorIndexOfTakenBy = _cursor.getColumnIndexOrThrow("takenBy");
                    int _cursorIndexOfPostAction = _cursor.getColumnIndexOrThrow("postAction");
                    int _cursorIndexOfDeviceId = _cursor.getColumnIndexOrThrow("deviceId");
                    int _cursorIndexOfDeviceType = _cursor.getColumnIndexOrThrow("deviceType");
                    int _cursorIndexOfPlatform = _cursor.getColumnIndexOrThrow("platform");
                    int _cursorIndexOfContextType = _cursor.getColumnIndexOrThrow("contextType");
                    int _cursorIndexOfContextId = _cursor.getColumnIndexOrThrow("contextId");
                    int _cursorIndexOfDeviceReading = _cursor.getColumnIndexOrThrow("deviceReading");
                    int _cursorIndexOfCreatedOn = _cursor.getColumnIndexOrThrow("createdOn");
                    int _cursorIndexOfUpdatedOn = _cursor.getColumnIndexOrThrow("updatedOn");
                    int _cursorIndexOfTenantId = _cursor.getColumnIndexOrThrow("tenantId");
                    int _cursorIndexOfApplicationId = _cursor.getColumnIndexOrThrow("applicationId");
                    int _cursorIndexOfExternalId = _cursor.getColumnIndexOrThrow(CooeySQLHelper.COL_EXT_ID);
                    int _cursorIndexOfIsActive = _cursor.getColumnIndexOrThrow("isActive");
                    int _cursorIndexOfIsArchived = _cursor.getColumnIndexOrThrow("isArchived");
                    if (_cursor.moveToFirst()) {
                        Integer _tmp;
                        Boolean _tmpDeviceReading;
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpVitalType = _cursor.getString(_cursorIndexOfVitalType);
                        long _tmpTakenOn = _cursor.getLong(_cursorIndexOfTakenOn);
                        String _tmpParameters = _cursor.getString(_cursorIndexOfParameters);
                        String _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
                        String _tmpTakenBy = _cursor.getString(_cursorIndexOfTakenBy);
                        String _tmpPostAction = _cursor.getString(_cursorIndexOfPostAction);
                        String _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
                        String _tmpDeviceType = _cursor.getString(_cursorIndexOfDeviceType);
                        String _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
                        String _tmpContextType = _cursor.getString(_cursorIndexOfContextType);
                        String _tmpContextId = _cursor.getString(_cursorIndexOfContextId);
                        if (_cursor.isNull(_cursorIndexOfDeviceReading)) {
                            _tmp = null;
                        } else {
                            _tmp = Integer.valueOf(_cursor.getInt(_cursorIndexOfDeviceReading));
                        }
                        if (_tmp == null) {
                            _tmpDeviceReading = null;
                        } else {
                            _tmpDeviceReading = Boolean.valueOf(_tmp.intValue() != 0);
                        }
                        _result = new Vital(_tmpId, _tmpVitalType, _tmpTakenOn, _tmpParameters, _tmpUserId, _tmpTakenBy, _tmpPostAction, _tmpDeviceId, _tmpDeviceType, _tmpPlatform, _tmpContextType, _tmpContextId, _tmpDeviceReading);
                        _result.setCreatedOn(_cursor.getLong(_cursorIndexOfCreatedOn));
                        _result.setUpdatedOn(_cursor.getLong(_cursorIndexOfUpdatedOn));
                        _result.setTenantId(_cursor.getString(_cursorIndexOfTenantId));
                        _result.setApplicationId(_cursor.getString(_cursorIndexOfApplicationId));
                        _result.setExternalId(_cursor.getString(_cursorIndexOfExternalId));
                        _result.setActive(_cursor.getInt(_cursorIndexOfIsActive) != 0);
                        _result.setArchived(_cursor.getInt(_cursorIndexOfIsArchived) != 0);
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

    public LiveData<List<Vital>> getVitalsForTypeByTimestampDesc(String type, String userId) {
        String _sql = "SELECT * FROM vitals WHERE vitalType = ? AND userId = ? ORDER BY takenOn DESC";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitals WHERE vitalType = ? AND userId = ? ORDER BY takenOn DESC", 2);
        if (type == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, type);
        }
        if (userId == null) {
            _statement.bindNull(2);
        } else {
            _statement.bindString(2, userId);
        }
        return new ComputableLiveData<List<Vital>>() {
            private Observer _observer;

            protected List<Vital> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("vitals", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08348.this.invalidate();
                        }
                    };
                    VitalDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = VitalDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfVitalType = _cursor.getColumnIndexOrThrow("vitalType");
                    int _cursorIndexOfTakenOn = _cursor.getColumnIndexOrThrow("takenOn");
                    int _cursorIndexOfParameters = _cursor.getColumnIndexOrThrow("parameters");
                    int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("userId");
                    int _cursorIndexOfTakenBy = _cursor.getColumnIndexOrThrow("takenBy");
                    int _cursorIndexOfPostAction = _cursor.getColumnIndexOrThrow("postAction");
                    int _cursorIndexOfDeviceId = _cursor.getColumnIndexOrThrow("deviceId");
                    int _cursorIndexOfDeviceType = _cursor.getColumnIndexOrThrow("deviceType");
                    int _cursorIndexOfPlatform = _cursor.getColumnIndexOrThrow("platform");
                    int _cursorIndexOfContextType = _cursor.getColumnIndexOrThrow("contextType");
                    int _cursorIndexOfContextId = _cursor.getColumnIndexOrThrow("contextId");
                    int _cursorIndexOfDeviceReading = _cursor.getColumnIndexOrThrow("deviceReading");
                    int _cursorIndexOfCreatedOn = _cursor.getColumnIndexOrThrow("createdOn");
                    int _cursorIndexOfUpdatedOn = _cursor.getColumnIndexOrThrow("updatedOn");
                    int _cursorIndexOfTenantId = _cursor.getColumnIndexOrThrow("tenantId");
                    int _cursorIndexOfApplicationId = _cursor.getColumnIndexOrThrow("applicationId");
                    int _cursorIndexOfExternalId = _cursor.getColumnIndexOrThrow(CooeySQLHelper.COL_EXT_ID);
                    int _cursorIndexOfIsActive = _cursor.getColumnIndexOrThrow("isActive");
                    int _cursorIndexOfIsArchived = _cursor.getColumnIndexOrThrow("isArchived");
                    List<Vital> arrayList = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        Integer _tmp;
                        Boolean _tmpDeviceReading;
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpVitalType = _cursor.getString(_cursorIndexOfVitalType);
                        long _tmpTakenOn = _cursor.getLong(_cursorIndexOfTakenOn);
                        String _tmpParameters = _cursor.getString(_cursorIndexOfParameters);
                        String _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
                        String _tmpTakenBy = _cursor.getString(_cursorIndexOfTakenBy);
                        String _tmpPostAction = _cursor.getString(_cursorIndexOfPostAction);
                        String _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
                        String _tmpDeviceType = _cursor.getString(_cursorIndexOfDeviceType);
                        String _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
                        String _tmpContextType = _cursor.getString(_cursorIndexOfContextType);
                        String _tmpContextId = _cursor.getString(_cursorIndexOfContextId);
                        if (_cursor.isNull(_cursorIndexOfDeviceReading)) {
                            _tmp = null;
                        } else {
                            _tmp = Integer.valueOf(_cursor.getInt(_cursorIndexOfDeviceReading));
                        }
                        if (_tmp == null) {
                            _tmpDeviceReading = null;
                        } else {
                            _tmpDeviceReading = Boolean.valueOf(_tmp.intValue() != 0);
                        }
                        Vital _item = new Vital(_tmpId, _tmpVitalType, _tmpTakenOn, _tmpParameters, _tmpUserId, _tmpTakenBy, _tmpPostAction, _tmpDeviceId, _tmpDeviceType, _tmpPlatform, _tmpContextType, _tmpContextId, _tmpDeviceReading);
                        _item.setCreatedOn(_cursor.getLong(_cursorIndexOfCreatedOn));
                        _item.setUpdatedOn(_cursor.getLong(_cursorIndexOfUpdatedOn));
                        _item.setTenantId(_cursor.getString(_cursorIndexOfTenantId));
                        _item.setApplicationId(_cursor.getString(_cursorIndexOfApplicationId));
                        _item.setExternalId(_cursor.getString(_cursorIndexOfExternalId));
                        _item.setActive(_cursor.getInt(_cursorIndexOfIsActive) != 0);
                        _item.setArchived(_cursor.getInt(_cursorIndexOfIsArchived) != 0);
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

    public LiveData<List<Vital>> getVitalsForTypeByTimestampAsc(String type, String userId) {
        String _sql = "SELECT * FROM vitals WHERE vitalType = ? AND userId = ? ORDER BY takenOn ASC";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitals WHERE vitalType = ? AND userId = ? ORDER BY takenOn ASC", 2);
        if (type == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, type);
        }
        if (userId == null) {
            _statement.bindNull(2);
        } else {
            _statement.bindString(2, userId);
        }
        return new ComputableLiveData<List<Vital>>() {
            private Observer _observer;

            protected List<Vital> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("vitals", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08369.this.invalidate();
                        }
                    };
                    VitalDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = VitalDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfVitalType = _cursor.getColumnIndexOrThrow("vitalType");
                    int _cursorIndexOfTakenOn = _cursor.getColumnIndexOrThrow("takenOn");
                    int _cursorIndexOfParameters = _cursor.getColumnIndexOrThrow("parameters");
                    int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("userId");
                    int _cursorIndexOfTakenBy = _cursor.getColumnIndexOrThrow("takenBy");
                    int _cursorIndexOfPostAction = _cursor.getColumnIndexOrThrow("postAction");
                    int _cursorIndexOfDeviceId = _cursor.getColumnIndexOrThrow("deviceId");
                    int _cursorIndexOfDeviceType = _cursor.getColumnIndexOrThrow("deviceType");
                    int _cursorIndexOfPlatform = _cursor.getColumnIndexOrThrow("platform");
                    int _cursorIndexOfContextType = _cursor.getColumnIndexOrThrow("contextType");
                    int _cursorIndexOfContextId = _cursor.getColumnIndexOrThrow("contextId");
                    int _cursorIndexOfDeviceReading = _cursor.getColumnIndexOrThrow("deviceReading");
                    int _cursorIndexOfCreatedOn = _cursor.getColumnIndexOrThrow("createdOn");
                    int _cursorIndexOfUpdatedOn = _cursor.getColumnIndexOrThrow("updatedOn");
                    int _cursorIndexOfTenantId = _cursor.getColumnIndexOrThrow("tenantId");
                    int _cursorIndexOfApplicationId = _cursor.getColumnIndexOrThrow("applicationId");
                    int _cursorIndexOfExternalId = _cursor.getColumnIndexOrThrow(CooeySQLHelper.COL_EXT_ID);
                    int _cursorIndexOfIsActive = _cursor.getColumnIndexOrThrow("isActive");
                    int _cursorIndexOfIsArchived = _cursor.getColumnIndexOrThrow("isArchived");
                    List<Vital> arrayList = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        Integer _tmp;
                        Boolean _tmpDeviceReading;
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpVitalType = _cursor.getString(_cursorIndexOfVitalType);
                        long _tmpTakenOn = _cursor.getLong(_cursorIndexOfTakenOn);
                        String _tmpParameters = _cursor.getString(_cursorIndexOfParameters);
                        String _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
                        String _tmpTakenBy = _cursor.getString(_cursorIndexOfTakenBy);
                        String _tmpPostAction = _cursor.getString(_cursorIndexOfPostAction);
                        String _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
                        String _tmpDeviceType = _cursor.getString(_cursorIndexOfDeviceType);
                        String _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
                        String _tmpContextType = _cursor.getString(_cursorIndexOfContextType);
                        String _tmpContextId = _cursor.getString(_cursorIndexOfContextId);
                        if (_cursor.isNull(_cursorIndexOfDeviceReading)) {
                            _tmp = null;
                        } else {
                            _tmp = Integer.valueOf(_cursor.getInt(_cursorIndexOfDeviceReading));
                        }
                        if (_tmp == null) {
                            _tmpDeviceReading = null;
                        } else {
                            _tmpDeviceReading = Boolean.valueOf(_tmp.intValue() != 0);
                        }
                        Vital _item = new Vital(_tmpId, _tmpVitalType, _tmpTakenOn, _tmpParameters, _tmpUserId, _tmpTakenBy, _tmpPostAction, _tmpDeviceId, _tmpDeviceType, _tmpPlatform, _tmpContextType, _tmpContextId, _tmpDeviceReading);
                        _item.setCreatedOn(_cursor.getLong(_cursorIndexOfCreatedOn));
                        _item.setUpdatedOn(_cursor.getLong(_cursorIndexOfUpdatedOn));
                        _item.setTenantId(_cursor.getString(_cursorIndexOfTenantId));
                        _item.setApplicationId(_cursor.getString(_cursorIndexOfApplicationId));
                        _item.setExternalId(_cursor.getString(_cursorIndexOfExternalId));
                        _item.setActive(_cursor.getInt(_cursorIndexOfIsActive) != 0);
                        _item.setArchived(_cursor.getInt(_cursorIndexOfIsArchived) != 0);
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
