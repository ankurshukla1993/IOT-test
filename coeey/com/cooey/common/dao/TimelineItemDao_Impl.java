package com.cooey.common.dao;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.util.StringUtil;
import android.database.Cursor;
import android.support.annotation.NonNull;
import com.cooey.common.vo.timeline.TimelineItem;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.measurement.AppMeasurement$Param;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TimelineItemDao_Impl implements TimelineItemDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfTimelineItem;

    public TimelineItemDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfTimelineItem = new EntityInsertionAdapter<TimelineItem>(__db) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `timelineItems`(`id`,`type`,`timestamp`,`userId`,`subType`) VALUES (?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement stmt, TimelineItem value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                stmt.bindLong(2, (long) value.getType());
                stmt.bindLong(3, value.getTimestamp());
                if (value.getUserId() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getUserId());
                }
                if (value.getSubType() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getSubType());
                }
            }
        };
    }

    public void insert(TimelineItem timelineItem) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTimelineItem.insert(timelineItem);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(List<TimelineItem> timelineItems) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTimelineItem.insert(timelineItems);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public LiveData<List<TimelineItem>> getTimelineItemsForUser(String userId) {
        String _sql = "SELECT * FROM timelineItems WHERE userId = ? ORDER BY timestamp DESC";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM timelineItems WHERE userId = ? ORDER BY timestamp DESC", 1);
        if (userId == null) {
            _statement.bindNull(1);
        } else {
            _statement.bindString(1, userId);
        }
        return new ComputableLiveData<List<TimelineItem>>() {
            private Observer _observer;

            protected List<TimelineItem> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("timelineItems", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08542.this.invalidate();
                        }
                    };
                    TimelineItemDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = TimelineItemDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
                    int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow(AppMeasurement$Param.TIMESTAMP);
                    int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("userId");
                    int _cursorIndexOfSubType = _cursor.getColumnIndexOrThrow("subType");
                    List<TimelineItem> _result = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        _result.add(new TimelineItem(_cursor.getString(_cursorIndexOfId), _cursor.getInt(_cursorIndexOfType), _cursor.getLong(_cursorIndexOfTimestamp), _cursor.getString(_cursorIndexOfUserId), _cursor.getString(_cursorIndexOfSubType)));
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }

    public LiveData<List<TimelineItem>> getTimelineItemsByType(int type, String userId) {
        String _sql = "SELECT * FROM timelineItems WHERE type = ? AND userId = ? ORDER by timestamp DESC";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM timelineItems WHERE type = ? AND userId = ? ORDER by timestamp DESC", 2);
        _statement.bindLong(1, (long) type);
        if (userId == null) {
            _statement.bindNull(2);
        } else {
            _statement.bindString(2, userId);
        }
        return new ComputableLiveData<List<TimelineItem>>() {
            private Observer _observer;

            protected List<TimelineItem> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("timelineItems", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08563.this.invalidate();
                        }
                    };
                    TimelineItemDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = TimelineItemDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
                    int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow(AppMeasurement$Param.TIMESTAMP);
                    int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("userId");
                    int _cursorIndexOfSubType = _cursor.getColumnIndexOrThrow("subType");
                    List<TimelineItem> _result = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        _result.add(new TimelineItem(_cursor.getString(_cursorIndexOfId), _cursor.getInt(_cursorIndexOfType), _cursor.getLong(_cursorIndexOfTimestamp), _cursor.getString(_cursorIndexOfUserId), _cursor.getString(_cursorIndexOfSubType)));
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }

    public LiveData<List<TimelineItem>> getTimelineItemsByTypeAndSubType(int type, String subType, String userId) {
        String _sql = "SELECT * FROM timelineItems WHERE type = ? AND subType = ? AND userId = ? ORDER by timestamp DESC";
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM timelineItems WHERE type = ? AND subType = ? AND userId = ? ORDER by timestamp DESC", 3);
        _statement.bindLong(1, (long) type);
        if (subType == null) {
            _statement.bindNull(2);
        } else {
            _statement.bindString(2, subType);
        }
        if (userId == null) {
            _statement.bindNull(3);
        } else {
            _statement.bindString(3, userId);
        }
        return new ComputableLiveData<List<TimelineItem>>() {
            private Observer _observer;

            protected List<TimelineItem> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("timelineItems", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08584.this.invalidate();
                        }
                    };
                    TimelineItemDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = TimelineItemDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
                    int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow(AppMeasurement$Param.TIMESTAMP);
                    int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("userId");
                    int _cursorIndexOfSubType = _cursor.getColumnIndexOrThrow("subType");
                    List<TimelineItem> _result = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        _result.add(new TimelineItem(_cursor.getString(_cursorIndexOfId), _cursor.getInt(_cursorIndexOfType), _cursor.getLong(_cursorIndexOfTimestamp), _cursor.getString(_cursorIndexOfUserId), _cursor.getString(_cursorIndexOfSubType)));
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }

    public LiveData<List<TimelineItem>> getTimelineItemsByTypesAndSubTypes(Integer[] types, String[] subTypes, String userId) {
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT * FROM timelineItems WHERE type IN (");
        int _inputSize = types.length;
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(") AND subType IN (");
        int _inputSize_1 = subTypes.length;
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize_1);
        _stringBuilder.append(") AND userId = ");
        _stringBuilder.append("?");
        _stringBuilder.append(" ORDER by timestamp DESC");
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_stringBuilder.toString(), (_inputSize + 1) + _inputSize_1);
        int _argIndex = 1;
        for (Integer _item : types) {
            if (_item == null) {
                _statement.bindNull(_argIndex);
            } else {
                _statement.bindLong(_argIndex, (long) _item.intValue());
            }
            _argIndex++;
        }
        _argIndex = _inputSize + 1;
        for (String _item_1 : subTypes) {
            if (_item_1 == null) {
                _statement.bindNull(_argIndex);
            } else {
                _statement.bindString(_argIndex, _item_1);
            }
            _argIndex++;
        }
        _argIndex = (_inputSize + 1) + _inputSize_1;
        if (userId == null) {
            _statement.bindNull(_argIndex);
        } else {
            _statement.bindString(_argIndex, userId);
        }
        return new ComputableLiveData<List<TimelineItem>>() {
            private Observer _observer;

            protected List<TimelineItem> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("timelineItems", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08605.this.invalidate();
                        }
                    };
                    TimelineItemDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = TimelineItemDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
                    int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow(AppMeasurement$Param.TIMESTAMP);
                    int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("userId");
                    int _cursorIndexOfSubType = _cursor.getColumnIndexOrThrow("subType");
                    List<TimelineItem> _result = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        _result.add(new TimelineItem(_cursor.getString(_cursorIndexOfId), _cursor.getInt(_cursorIndexOfType), _cursor.getLong(_cursorIndexOfTimestamp), _cursor.getString(_cursorIndexOfUserId), _cursor.getString(_cursorIndexOfSubType)));
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        }.getLiveData();
    }

    public LiveData<List<TimelineItem>> getTimelineItemsByTypes(Integer[] types, String userId) {
        StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("SELECT * FROM timelineItems WHERE type IN (");
        int _inputSize = types.length;
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(") AND userId = ");
        _stringBuilder.append("?");
        _stringBuilder.append(" ORDER by timestamp DESC");
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_stringBuilder.toString(), _inputSize + 1);
        int _argIndex = 1;
        for (Integer _item : types) {
            if (_item == null) {
                _statement.bindNull(_argIndex);
            } else {
                _statement.bindLong(_argIndex, (long) _item.intValue());
            }
            _argIndex++;
        }
        _argIndex = _inputSize + 1;
        if (userId == null) {
            _statement.bindNull(_argIndex);
        } else {
            _statement.bindString(_argIndex, userId);
        }
        return new ComputableLiveData<List<TimelineItem>>() {
            private Observer _observer;

            protected List<TimelineItem> compute() {
                if (this._observer == null) {
                    this._observer = new Observer("timelineItems", new String[0]) {
                        public void onInvalidated(@NonNull Set<String> set) {
                            C08626.this.invalidate();
                        }
                    };
                    TimelineItemDao_Impl.this.__db.getInvalidationTracker().addWeakObserver(this._observer);
                }
                Cursor _cursor = TimelineItemDao_Impl.this.__db.query(_statement);
                try {
                    int _cursorIndexOfId = _cursor.getColumnIndexOrThrow(ShareConstants.WEB_DIALOG_PARAM_ID);
                    int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
                    int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow(AppMeasurement$Param.TIMESTAMP);
                    int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("userId");
                    int _cursorIndexOfSubType = _cursor.getColumnIndexOrThrow("subType");
                    List<TimelineItem> _result = new ArrayList(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        _result.add(new TimelineItem(_cursor.getString(_cursorIndexOfId), _cursor.getInt(_cursorIndexOfType), _cursor.getLong(_cursorIndexOfTimestamp), _cursor.getString(_cursorIndexOfUserId), _cursor.getString(_cursorIndexOfSubType)));
                    }
                    return _result;
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
