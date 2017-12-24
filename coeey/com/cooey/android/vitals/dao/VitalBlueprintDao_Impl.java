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
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.converters.Converter;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class VitalBlueprintDao_Impl implements VitalBlueprintDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfVitalBlueprint;
    private final EntityInsertionAdapter __insertionAdapterOfVitalBlueprint;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public VitalBlueprintDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfVitalBlueprint = new EntityInsertionAdapter<VitalBlueprint>(__db) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `vitalBlueprints`(`iconURL`,`fields`,`id`,`name`,`type`,`isGraphRequired`,`isPrimary`,`isSynced`) VALUES (?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement stmt, VitalBlueprint value) {
                Integer _tmp_1;
                Integer _tmp_2;
                Integer _tmp_3 = null;
                int i = 1;
                if (value.getIconURL() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIconURL());
                }
                String _tmp = Converter.convertFieldListToString(value.getFields());
                if (_tmp == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, _tmp);
                }
                if (value.getId() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getId());
                }
                if (value.getName() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getName());
                }
                if (value.getType() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getType());
                }
                if (value.isGraphRequired() == null) {
                    _tmp_1 = null;
                } else {
                    _tmp_1 = Integer.valueOf(value.isGraphRequired().booleanValue() ? 1 : 0);
                }
                if (_tmp_1 == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindLong(6, (long) _tmp_1.intValue());
                }
                if (value.isPrimary() == null) {
                    _tmp_2 = null;
                } else {
                    _tmp_2 = Integer.valueOf(value.isPrimary().booleanValue() ? 1 : 0);
                }
                if (_tmp_2 == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindLong(7, (long) _tmp_2.intValue());
                }
                if (value.isSynced() != null) {
                    if (!value.isSynced().booleanValue()) {
                        i = 0;
                    }
                    _tmp_3 = Integer.valueOf(i);
                }
                if (_tmp_3 == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindLong(8, (long) _tmp_3.intValue());
                }
            }
        };
        this.__deletionAdapterOfVitalBlueprint = new EntityDeletionOrUpdateAdapter<VitalBlueprint>(__db) {
            public String createQuery() {
                return "DELETE FROM `vitalBlueprints` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement stmt, VitalBlueprint value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
            public String createQuery() {
                String _query = "DELETE FROM vitalBlueprints";
                return "DELETE FROM vitalBlueprints";
            }
        };
    }

    public void insert(VitalBlueprint vitalBlueprint) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfVitalBlueprint.insert(vitalBlueprint);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(List<VitalBlueprint> vitalBlueprints) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfVitalBlueprint.insert(vitalBlueprints);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(VitalBlueprint vitalBlueprint) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfVitalBlueprint.handle(vitalBlueprint);
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

    public LiveData<List<VitalBlueprint>> getAll() {
        String _sql = "SELECT * FROM vitalBlueprints";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitalBlueprints", 0);
        return new ComputableLiveData<List<VitalBlueprint>>() {
            private Observer _observer;

            protected List<VitalBlueprint> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("vitalBlueprints", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08204.this.invalidate();
                        }
                    };
                    VitalBlueprintDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = VitalBlueprintDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfIconURL = _cursor.getColumnIndexOrThrow("iconURL");
                    int _cursorIndexOfFields = _cursor.getColumnIndexOrThrow("fields");
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
                    int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
                    int _cursorIndexOfIsGraphRequired = _cursor.getColumnIndexOrThrow("isGraphRequired");
                    int _cursorIndexOfIsPrimary = _cursor.getColumnIndexOrThrow("isPrimary");
                    int _cursorIndexOfIsSynced = _cursor.getColumnIndexOrThrow("isSynced");
                    List<VitalBlueprint> arrayList = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        Integer _tmp;
                        Boolean _tmpIsGraphRequired;
                        Integer _tmp_1;
                        Boolean _tmpIsPrimary;
                        Integer _tmp_2;
                        Boolean _tmpIsSynced;
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpName = _cursor.getString(_cursorIndexOfName);
                        String _tmpType = _cursor.getString(_cursorIndexOfType);
                        if (_cursor.isNull(_cursorIndexOfIsGraphRequired)) {
                            _tmp = null;
                        } else {
                            _tmp = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsGraphRequired));
                        }
                        if (_tmp == null) {
                            _tmpIsGraphRequired = null;
                        } else {
                            _tmpIsGraphRequired = Boolean.valueOf(_tmp.intValue() != 0);
                        }
                        if (_cursor.isNull(_cursorIndexOfIsPrimary)) {
                            _tmp_1 = null;
                        } else {
                            _tmp_1 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsPrimary));
                        }
                        if (_tmp_1 == null) {
                            _tmpIsPrimary = null;
                        } else {
                            _tmpIsPrimary = Boolean.valueOf(_tmp_1.intValue() != 0);
                        }
                        if (_cursor.isNull(_cursorIndexOfIsSynced)) {
                            _tmp_2 = null;
                        } else {
                            _tmp_2 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsSynced));
                        }
                        if (_tmp_2 == null) {
                            _tmpIsSynced = null;
                        } else {
                            _tmpIsSynced = Boolean.valueOf(_tmp_2.intValue() != 0);
                        }
                        VitalBlueprint _item = new VitalBlueprint(_tmpId, _tmpName, _tmpType, _tmpIsGraphRequired, _tmpIsPrimary, _tmpIsSynced);
                        _item.setIconURL(_cursor.getString(_cursorIndexOfIconURL));
                        _item.setFields(Converter.convertStringToFieldList(_cursor.getString(_cursorIndexOfFields)));
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

    public List<VitalBlueprint> getAllSync() {
        String _sql = "SELECT * FROM vitalBlueprints";
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitalBlueprints", 0);
        Cursor _cursor = this.__db.query(_statement);
        try {
            int _cursorIndexOfIconURL = _cursor.getColumnIndexOrThrow("iconURL");
            int _cursorIndexOfFields = _cursor.getColumnIndexOrThrow("fields");
            int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
            int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
            int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
            int _cursorIndexOfIsGraphRequired = _cursor.getColumnIndexOrThrow("isGraphRequired");
            int _cursorIndexOfIsPrimary = _cursor.getColumnIndexOrThrow("isPrimary");
            int _cursorIndexOfIsSynced = _cursor.getColumnIndexOrThrow("isSynced");
            List<VitalBlueprint> arrayList = new ArrayList(_cursor.getCount());
            while (_cursor.moveToNext()) {
                Integer _tmp;
                Boolean _tmpIsGraphRequired;
                Integer _tmp_1;
                Boolean _tmpIsPrimary;
                Integer _tmp_2;
                Boolean _tmpIsSynced;
                String _tmpId = _cursor.getString(_cursorIndexOfId);
                String _tmpName = _cursor.getString(_cursorIndexOfName);
                String _tmpType = _cursor.getString(_cursorIndexOfType);
                if (_cursor.isNull(_cursorIndexOfIsGraphRequired)) {
                    _tmp = null;
                } else {
                    _tmp = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsGraphRequired));
                }
                if (_tmp == null) {
                    _tmpIsGraphRequired = null;
                } else {
                    _tmpIsGraphRequired = Boolean.valueOf(_tmp.intValue() != 0);
                }
                if (_cursor.isNull(_cursorIndexOfIsPrimary)) {
                    _tmp_1 = null;
                } else {
                    _tmp_1 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsPrimary));
                }
                if (_tmp_1 == null) {
                    _tmpIsPrimary = null;
                } else {
                    _tmpIsPrimary = Boolean.valueOf(_tmp_1.intValue() != 0);
                }
                if (_cursor.isNull(_cursorIndexOfIsSynced)) {
                    _tmp_2 = null;
                } else {
                    _tmp_2 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsSynced));
                }
                if (_tmp_2 == null) {
                    _tmpIsSynced = null;
                } else {
                    _tmpIsSynced = Boolean.valueOf(_tmp_2.intValue() != 0);
                }
                VitalBlueprint _item = new VitalBlueprint(_tmpId, _tmpName, _tmpType, _tmpIsGraphRequired, _tmpIsPrimary, _tmpIsSynced);
                _item.setIconURL(_cursor.getString(_cursorIndexOfIconURL));
                _item.setFields(Converter.convertStringToFieldList(_cursor.getString(_cursorIndexOfFields)));
                arrayList.add(_item);
            }
            return arrayList;
        } finally {
            _cursor.close();
            _statement.release();
        }
    }

    public LiveData<List<VitalBlueprint>> getAll(boolean isPrimary) {
        int _tmp = 1;
        String _sql = "SELECT * FROM vitalBlueprints WHERE isPrimary = ?";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitalBlueprints WHERE isPrimary = ?", 1);
        if (!isPrimary) {
            _tmp = 0;
        }
        _statement.bindLong(1, (long) _tmp);
        return new ComputableLiveData<List<VitalBlueprint>>() {
            private Observer _observer;

            protected List<VitalBlueprint> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("vitalBlueprints", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08225.this.invalidate();
                        }
                    };
                    VitalBlueprintDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = VitalBlueprintDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfIconURL = _cursor.getColumnIndexOrThrow("iconURL");
                    int _cursorIndexOfFields = _cursor.getColumnIndexOrThrow("fields");
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
                    int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
                    int _cursorIndexOfIsGraphRequired = _cursor.getColumnIndexOrThrow("isGraphRequired");
                    int _cursorIndexOfIsPrimary = _cursor.getColumnIndexOrThrow("isPrimary");
                    int _cursorIndexOfIsSynced = _cursor.getColumnIndexOrThrow("isSynced");
                    List<VitalBlueprint> arrayList = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        Integer _tmp_1;
                        Boolean _tmpIsGraphRequired;
                        Integer _tmp_2;
                        Boolean _tmpIsPrimary;
                        Integer _tmp_3;
                        Boolean _tmpIsSynced;
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpName = _cursor.getString(_cursorIndexOfName);
                        String _tmpType = _cursor.getString(_cursorIndexOfType);
                        if (_cursor.isNull(_cursorIndexOfIsGraphRequired)) {
                            _tmp_1 = null;
                        } else {
                            _tmp_1 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsGraphRequired));
                        }
                        if (_tmp_1 == null) {
                            _tmpIsGraphRequired = null;
                        } else {
                            _tmpIsGraphRequired = Boolean.valueOf(_tmp_1.intValue() != 0);
                        }
                        if (_cursor.isNull(_cursorIndexOfIsPrimary)) {
                            _tmp_2 = null;
                        } else {
                            _tmp_2 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsPrimary));
                        }
                        if (_tmp_2 == null) {
                            _tmpIsPrimary = null;
                        } else {
                            _tmpIsPrimary = Boolean.valueOf(_tmp_2.intValue() != 0);
                        }
                        if (_cursor.isNull(_cursorIndexOfIsSynced)) {
                            _tmp_3 = null;
                        } else {
                            _tmp_3 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsSynced));
                        }
                        if (_tmp_3 == null) {
                            _tmpIsSynced = null;
                        } else {
                            _tmpIsSynced = Boolean.valueOf(_tmp_3.intValue() != 0);
                        }
                        VitalBlueprint _item = new VitalBlueprint(_tmpId, _tmpName, _tmpType, _tmpIsGraphRequired, _tmpIsPrimary, _tmpIsSynced);
                        _item.setIconURL(_cursor.getString(_cursorIndexOfIconURL));
                        _item.setFields(Converter.convertStringToFieldList(_cursor.getString(_cursorIndexOfFields)));
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

    public List<VitalBlueprint> getAllSync(boolean isPrimary) {
        String _sql = "SELECT * FROM vitalBlueprints WHERE isPrimary = ?";
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitalBlueprints WHERE isPrimary = ?", 1);
        _statement.bindLong(1, (long) (isPrimary ? 1 : 0));
        Cursor _cursor = this.__db.query(_statement);
        try {
            int _cursorIndexOfIconURL = _cursor.getColumnIndexOrThrow("iconURL");
            int _cursorIndexOfFields = _cursor.getColumnIndexOrThrow("fields");
            int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
            int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
            int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
            int _cursorIndexOfIsGraphRequired = _cursor.getColumnIndexOrThrow("isGraphRequired");
            int _cursorIndexOfIsPrimary = _cursor.getColumnIndexOrThrow("isPrimary");
            int _cursorIndexOfIsSynced = _cursor.getColumnIndexOrThrow("isSynced");
            List<VitalBlueprint> arrayList = new ArrayList(_cursor.getCount());
            while (_cursor.moveToNext()) {
                Integer _tmp_1;
                Boolean _tmpIsGraphRequired;
                Integer _tmp_2;
                Boolean _tmpIsPrimary;
                Integer _tmp_3;
                Boolean _tmpIsSynced;
                String _tmpId = _cursor.getString(_cursorIndexOfId);
                String _tmpName = _cursor.getString(_cursorIndexOfName);
                String _tmpType = _cursor.getString(_cursorIndexOfType);
                if (_cursor.isNull(_cursorIndexOfIsGraphRequired)) {
                    _tmp_1 = null;
                } else {
                    _tmp_1 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsGraphRequired));
                }
                if (_tmp_1 == null) {
                    _tmpIsGraphRequired = null;
                } else {
                    _tmpIsGraphRequired = Boolean.valueOf(_tmp_1.intValue() != 0);
                }
                if (_cursor.isNull(_cursorIndexOfIsPrimary)) {
                    _tmp_2 = null;
                } else {
                    _tmp_2 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsPrimary));
                }
                if (_tmp_2 == null) {
                    _tmpIsPrimary = null;
                } else {
                    _tmpIsPrimary = Boolean.valueOf(_tmp_2.intValue() != 0);
                }
                if (_cursor.isNull(_cursorIndexOfIsSynced)) {
                    _tmp_3 = null;
                } else {
                    _tmp_3 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsSynced));
                }
                if (_tmp_3 == null) {
                    _tmpIsSynced = null;
                } else {
                    _tmpIsSynced = Boolean.valueOf(_tmp_3.intValue() != 0);
                }
                VitalBlueprint _item = new VitalBlueprint(_tmpId, _tmpName, _tmpType, _tmpIsGraphRequired, _tmpIsPrimary, _tmpIsSynced);
                _item.setIconURL(_cursor.getString(_cursorIndexOfIconURL));
                _item.setFields(Converter.convertStringToFieldList(_cursor.getString(_cursorIndexOfFields)));
                arrayList.add(_item);
            }
            return arrayList;
        } finally {
            _cursor.close();
            _statement.release();
        }
    }

    public VitalBlueprint getVitalBlueprintForType(String type) {
        String _sql = "SELECT * FROM vitalBlueprints WHERE type = ? LIMIT 1";
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM vitalBlueprints WHERE type = ? LIMIT 1", 1);
        if (type == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, type);
        }
        Cursor _cursor = this.__db.query(_statement);
        try {
            VitalBlueprint _result;
            int _cursorIndexOfIconURL = _cursor.getColumnIndexOrThrow("iconURL");
            int _cursorIndexOfFields = _cursor.getColumnIndexOrThrow("fields");
            int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
            int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
            int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
            int _cursorIndexOfIsGraphRequired = _cursor.getColumnIndexOrThrow("isGraphRequired");
            int _cursorIndexOfIsPrimary = _cursor.getColumnIndexOrThrow("isPrimary");
            int _cursorIndexOfIsSynced = _cursor.getColumnIndexOrThrow("isSynced");
            if (_cursor.moveToFirst()) {
                Integer _tmp;
                Boolean _tmpIsGraphRequired;
                Integer _tmp_1;
                Boolean _tmpIsPrimary;
                Integer _tmp_2;
                Boolean _tmpIsSynced;
                String _tmpId = _cursor.getString(_cursorIndexOfId);
                String _tmpName = _cursor.getString(_cursorIndexOfName);
                String _tmpType = _cursor.getString(_cursorIndexOfType);
                if (_cursor.isNull(_cursorIndexOfIsGraphRequired)) {
                    _tmp = null;
                } else {
                    _tmp = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsGraphRequired));
                }
                if (_tmp == null) {
                    _tmpIsGraphRequired = null;
                } else {
                    _tmpIsGraphRequired = Boolean.valueOf(_tmp.intValue() != 0);
                }
                if (_cursor.isNull(_cursorIndexOfIsPrimary)) {
                    _tmp_1 = null;
                } else {
                    _tmp_1 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsPrimary));
                }
                if (_tmp_1 == null) {
                    _tmpIsPrimary = null;
                } else {
                    _tmpIsPrimary = Boolean.valueOf(_tmp_1.intValue() != 0);
                }
                if (_cursor.isNull(_cursorIndexOfIsSynced)) {
                    _tmp_2 = null;
                } else {
                    _tmp_2 = Integer.valueOf(_cursor.getInt(_cursorIndexOfIsSynced));
                }
                if (_tmp_2 == null) {
                    _tmpIsSynced = null;
                } else {
                    _tmpIsSynced = Boolean.valueOf(_tmp_2.intValue() != 0);
                }
                _result = new VitalBlueprint(_tmpId, _tmpName, _tmpType, _tmpIsGraphRequired, _tmpIsPrimary, _tmpIsSynced);
                _result.setIconURL(_cursor.getString(_cursorIndexOfIconURL));
                _result.setFields(Converter.convertStringToFieldList(_cursor.getString(_cursorIndexOfFields)));
            } else {
                _result = null;
            }
            _cursor.close();
            _statement.release();
            return _result;
        } catch (Throwable th) {
            _cursor.close();
            _statement.release();
        }
    }
}
