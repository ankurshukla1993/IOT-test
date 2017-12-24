package io.realm;

import io.realm.internal.Collection;
import io.realm.internal.OsList;
import io.realm.internal.PendingRow;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SortDescriptor;
import io.realm.internal.Table;
import io.realm.internal.TableQuery;
import io.realm.internal.fields.FieldDescriptor;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Nullable;

public class RealmQuery<E extends RealmModel> {
    private static final String ASYNC_QUERY_WRONG_THREAD_MESSAGE = "Async query cannot be created on current thread.";
    private static final String EMPTY_VALUES = "Non-empty 'values' must be provided.";
    private static final String TYPE_MISMATCH = "Field '%s': type mismatch - %s expected.";
    private String className;
    private Class<E> clazz;
    private final OsList osList;
    private final TableQuery query;
    private final BaseRealm realm;
    private final RealmObjectSchema schema;
    private final Table table;

    public static <E extends RealmModel> RealmQuery<E> createQuery(Realm realm, Class<E> clazz) {
        return new RealmQuery(realm, (Class) clazz);
    }

    public static <E extends RealmModel> RealmQuery<E> createDynamicQuery(DynamicRealm realm, String className) {
        return new RealmQuery((BaseRealm) realm, className);
    }

    public static <E extends RealmModel> RealmQuery<E> createQueryFromResult(RealmResults<E> queryResults) {
        if (queryResults.classSpec == null) {
            return new RealmQuery((RealmResults) queryResults, queryResults.className);
        }
        return new RealmQuery((RealmResults) queryResults, queryResults.classSpec);
    }

    public static <E extends RealmModel> RealmQuery<E> createQueryFromList(RealmList<E> list) {
        if (list.clazz == null) {
            return new RealmQuery(list.realm, list.osList, list.className);
        }
        return new RealmQuery(list.realm, list.osList, list.clazz);
    }

    private RealmQuery(Realm realm, Class<E> clazz) {
        this.realm = realm;
        this.clazz = clazz;
        this.schema = realm.getSchema().getSchemaForClass((Class) clazz);
        this.table = this.schema.getTable();
        this.osList = null;
        this.query = this.table.where();
    }

    private RealmQuery(RealmResults<E> queryResults, Class<E> clazz) {
        this.realm = queryResults.realm;
        this.clazz = clazz;
        this.schema = this.realm.getSchema().getSchemaForClass((Class) clazz);
        this.table = queryResults.getTable();
        this.osList = null;
        this.query = queryResults.getCollection().where();
    }

    private RealmQuery(BaseRealm realm, OsList osList, Class<E> clazz) {
        this.realm = realm;
        this.clazz = clazz;
        this.schema = realm.getSchema().getSchemaForClass((Class) clazz);
        this.table = this.schema.getTable();
        this.osList = osList;
        this.query = osList.getQuery();
    }

    private RealmQuery(BaseRealm realm, String className) {
        this.realm = realm;
        this.className = className;
        this.schema = realm.getSchema().getSchemaForClass(className);
        this.table = this.schema.getTable();
        this.query = this.table.where();
        this.osList = null;
    }

    private RealmQuery(RealmResults<DynamicRealmObject> queryResults, String className) {
        this.realm = queryResults.realm;
        this.className = className;
        this.schema = this.realm.getSchema().getSchemaForClass(className);
        this.table = this.schema.getTable();
        this.query = queryResults.getCollection().where();
        this.osList = null;
    }

    private RealmQuery(BaseRealm realm, OsList osList, String className) {
        this.realm = realm;
        this.className = className;
        this.schema = realm.getSchema().getSchemaForClass(className);
        this.table = this.schema.getTable();
        this.query = osList.getQuery();
        this.osList = osList;
    }

    public boolean isValid() {
        if (this.realm == null || this.realm.isClosed()) {
            return false;
        }
        if (this.osList != null) {
            return this.osList.isValid();
        }
        if (this.table == null || !this.table.isValid()) {
            return false;
        }
        return true;
    }

    public RealmQuery<E> isNull(String fieldName) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, new RealmFieldType[0]);
        this.query.isNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        return this;
    }

    public RealmQuery<E> isNotNull(String fieldName) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, new RealmFieldType[0]);
        this.query.isNotNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        return this;
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable String value) {
        return equalTo(fieldName, value, Case.SENSITIVE);
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable String value, Case casing) {
        this.realm.checkIfValid();
        return equalToWithoutThreadValidation(fieldName, value, casing);
    }

    private RealmQuery<E> equalToWithoutThreadValidation(String fieldName, @Nullable String value, Case casing) {
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.STRING);
        this.query.equalTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value, casing);
        return this;
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable Byte value) {
        this.realm.checkIfValid();
        return equalToWithoutThreadValidation(fieldName, value);
    }

    private RealmQuery<E> equalToWithoutThreadValidation(String fieldName, @Nullable Byte value) {
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        if (value == null) {
            this.query.isNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.equalTo(fd.getColumnIndices(), fd.getNativeTablePointers(), (long) value.byteValue());
        }
        return this;
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable byte[] value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.BINARY);
        if (value == null) {
            this.query.isNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.equalTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        }
        return this;
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable Short value) {
        this.realm.checkIfValid();
        return equalToWithoutThreadValidation(fieldName, value);
    }

    private RealmQuery<E> equalToWithoutThreadValidation(String fieldName, @Nullable Short value) {
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        if (value == null) {
            this.query.isNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.equalTo(fd.getColumnIndices(), fd.getNativeTablePointers(), (long) value.shortValue());
        }
        return this;
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable Integer value) {
        this.realm.checkIfValid();
        return equalToWithoutThreadValidation(fieldName, value);
    }

    private RealmQuery<E> equalToWithoutThreadValidation(String fieldName, @Nullable Integer value) {
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        if (value == null) {
            this.query.isNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.equalTo(fd.getColumnIndices(), fd.getNativeTablePointers(), (long) value.intValue());
        }
        return this;
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable Long value) {
        this.realm.checkIfValid();
        return equalToWithoutThreadValidation(fieldName, value);
    }

    private RealmQuery<E> equalToWithoutThreadValidation(String fieldName, @Nullable Long value) {
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        if (value == null) {
            this.query.isNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.equalTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value.longValue());
        }
        return this;
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable Double value) {
        this.realm.checkIfValid();
        return equalToWithoutThreadValidation(fieldName, value);
    }

    private RealmQuery<E> equalToWithoutThreadValidation(String fieldName, @Nullable Double value) {
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DOUBLE);
        if (value == null) {
            this.query.isNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.equalTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value.doubleValue());
        }
        return this;
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable Float value) {
        this.realm.checkIfValid();
        return equalToWithoutThreadValidation(fieldName, value);
    }

    private RealmQuery<E> equalToWithoutThreadValidation(String fieldName, @Nullable Float value) {
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.FLOAT);
        if (value == null) {
            this.query.isNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.equalTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value.floatValue());
        }
        return this;
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable Boolean value) {
        this.realm.checkIfValid();
        return equalToWithoutThreadValidation(fieldName, value);
    }

    private RealmQuery<E> equalToWithoutThreadValidation(String fieldName, @Nullable Boolean value) {
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.BOOLEAN);
        if (value == null) {
            this.query.isNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.equalTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value.booleanValue());
        }
        return this;
    }

    public RealmQuery<E> equalTo(String fieldName, @Nullable Date value) {
        this.realm.checkIfValid();
        return equalToWithoutThreadValidation(fieldName, value);
    }

    private RealmQuery<E> equalToWithoutThreadValidation(String fieldName, @Nullable Date value) {
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DATE);
        this.query.equalTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> in(String fieldName, String[] values) {
        return in(fieldName, values, Case.SENSITIVE);
    }

    public RealmQuery<E> in(String fieldName, String[] values, Case casing) {
        this.realm.checkIfValid();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(EMPTY_VALUES);
        }
        beginGroupWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[0], casing);
        for (int i = 1; i < values.length; i++) {
            orWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[i], casing);
        }
        return endGroupWithoutThreadValidation();
    }

    public RealmQuery<E> in(String fieldName, Byte[] values) {
        this.realm.checkIfValid();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(EMPTY_VALUES);
        }
        beginGroupWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[0]);
        for (int i = 1; i < values.length; i++) {
            orWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[i]);
        }
        return endGroupWithoutThreadValidation();
    }

    public RealmQuery<E> in(String fieldName, Short[] values) {
        this.realm.checkIfValid();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(EMPTY_VALUES);
        }
        beginGroupWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[0]);
        for (int i = 1; i < values.length; i++) {
            orWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[i]);
        }
        return endGroupWithoutThreadValidation();
    }

    public RealmQuery<E> in(String fieldName, Integer[] values) {
        this.realm.checkIfValid();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(EMPTY_VALUES);
        }
        beginGroupWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[0]);
        for (int i = 1; i < values.length; i++) {
            orWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[i]);
        }
        return endGroupWithoutThreadValidation();
    }

    public RealmQuery<E> in(String fieldName, Long[] values) {
        this.realm.checkIfValid();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(EMPTY_VALUES);
        }
        beginGroupWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[0]);
        for (int i = 1; i < values.length; i++) {
            orWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[i]);
        }
        return endGroupWithoutThreadValidation();
    }

    public RealmQuery<E> in(String fieldName, Double[] values) {
        this.realm.checkIfValid();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(EMPTY_VALUES);
        }
        beginGroupWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[0]);
        for (int i = 1; i < values.length; i++) {
            orWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[i]);
        }
        return endGroupWithoutThreadValidation();
    }

    public RealmQuery<E> in(String fieldName, Float[] values) {
        this.realm.checkIfValid();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(EMPTY_VALUES);
        }
        beginGroupWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[0]);
        for (int i = 1; i < values.length; i++) {
            orWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[i]);
        }
        return endGroupWithoutThreadValidation();
    }

    public RealmQuery<E> in(String fieldName, Boolean[] values) {
        this.realm.checkIfValid();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(EMPTY_VALUES);
        }
        beginGroupWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[0]);
        for (int i = 1; i < values.length; i++) {
            orWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[i]);
        }
        return endGroupWithoutThreadValidation();
    }

    public RealmQuery<E> in(String fieldName, Date[] values) {
        this.realm.checkIfValid();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(EMPTY_VALUES);
        }
        beginGroupWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[0]);
        for (int i = 1; i < values.length; i++) {
            orWithoutThreadValidation().equalToWithoutThreadValidation(fieldName, values[i]);
        }
        return endGroupWithoutThreadValidation();
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable String value) {
        return notEqualTo(fieldName, value, Case.SENSITIVE);
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable String value, Case casing) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.STRING);
        if (fd.length() <= 1 || casing.getValue()) {
            this.query.notEqualTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value, casing);
            return this;
        }
        throw new IllegalArgumentException("Link queries cannot be case insensitive - coming soon.");
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable Byte value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        if (value == null) {
            this.query.isNotNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.notEqualTo(fd.getColumnIndices(), fd.getNativeTablePointers(), (long) value.byteValue());
        }
        return this;
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable byte[] value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.BINARY);
        if (value == null) {
            this.query.isNotNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.notEqualTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        }
        return this;
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable Short value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        if (value == null) {
            this.query.isNotNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.notEqualTo(fd.getColumnIndices(), fd.getNativeTablePointers(), (long) value.shortValue());
        }
        return this;
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable Integer value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        if (value == null) {
            this.query.isNotNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.notEqualTo(fd.getColumnIndices(), fd.getNativeTablePointers(), (long) value.intValue());
        }
        return this;
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable Long value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        if (value == null) {
            this.query.isNotNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.notEqualTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value.longValue());
        }
        return this;
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable Double value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DOUBLE);
        if (value == null) {
            this.query.isNotNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.notEqualTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value.doubleValue());
        }
        return this;
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable Float value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.FLOAT);
        if (value == null) {
            this.query.isNotNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.notEqualTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value.floatValue());
        }
        return this;
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable Boolean value) {
        boolean z = true;
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.BOOLEAN);
        if (value == null) {
            this.query.isNotNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            TableQuery tableQuery = this.query;
            long[] columnIndices = fd.getColumnIndices();
            long[] nativeTablePointers = fd.getNativeTablePointers();
            if (value.booleanValue()) {
                z = false;
            }
            tableQuery.equalTo(columnIndices, nativeTablePointers, z);
        }
        return this;
    }

    public RealmQuery<E> notEqualTo(String fieldName, @Nullable Date value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DATE);
        if (value == null) {
            this.query.isNotNull(fd.getColumnIndices(), fd.getNativeTablePointers());
        } else {
            this.query.notEqualTo(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        }
        return this;
    }

    public RealmQuery<E> greaterThan(String fieldName, int value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        this.query.greaterThan(fd.getColumnIndices(), fd.getNativeTablePointers(), (long) value);
        return this;
    }

    public RealmQuery<E> greaterThan(String fieldName, long value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        this.query.greaterThan(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> greaterThan(String fieldName, double value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DOUBLE);
        this.query.greaterThan(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> greaterThan(String fieldName, float value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.FLOAT);
        this.query.greaterThan(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> greaterThan(String fieldName, Date value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DATE);
        this.query.greaterThan(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> greaterThanOrEqualTo(String fieldName, int value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        this.query.greaterThanOrEqual(fd.getColumnIndices(), fd.getNativeTablePointers(), (long) value);
        return this;
    }

    public RealmQuery<E> greaterThanOrEqualTo(String fieldName, long value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        this.query.greaterThanOrEqual(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> greaterThanOrEqualTo(String fieldName, double value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DOUBLE);
        this.query.greaterThanOrEqual(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> greaterThanOrEqualTo(String fieldName, float value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.FLOAT);
        this.query.greaterThanOrEqual(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> greaterThanOrEqualTo(String fieldName, Date value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DATE);
        this.query.greaterThanOrEqual(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> lessThan(String fieldName, int value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        this.query.lessThan(fd.getColumnIndices(), fd.getNativeTablePointers(), (long) value);
        return this;
    }

    public RealmQuery<E> lessThan(String fieldName, long value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        this.query.lessThan(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> lessThan(String fieldName, double value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DOUBLE);
        this.query.lessThan(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> lessThan(String fieldName, float value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.FLOAT);
        this.query.lessThan(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> lessThan(String fieldName, Date value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DATE);
        this.query.lessThan(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> lessThanOrEqualTo(String fieldName, int value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        this.query.lessThanOrEqual(fd.getColumnIndices(), fd.getNativeTablePointers(), (long) value);
        return this;
    }

    public RealmQuery<E> lessThanOrEqualTo(String fieldName, long value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER);
        this.query.lessThanOrEqual(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> lessThanOrEqualTo(String fieldName, double value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DOUBLE);
        this.query.lessThanOrEqual(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> lessThanOrEqualTo(String fieldName, float value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.FLOAT);
        this.query.lessThanOrEqual(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> lessThanOrEqualTo(String fieldName, Date value) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.DATE);
        this.query.lessThanOrEqual(fd.getColumnIndices(), fd.getNativeTablePointers(), value);
        return this;
    }

    public RealmQuery<E> between(String fieldName, int from, int to) {
        this.realm.checkIfValid();
        this.query.between(this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER).getColumnIndices(), (long) from, (long) to);
        return this;
    }

    public RealmQuery<E> between(String fieldName, long from, long to) {
        this.realm.checkIfValid();
        this.query.between(this.schema.getColumnIndices(fieldName, RealmFieldType.INTEGER).getColumnIndices(), from, to);
        return this;
    }

    public RealmQuery<E> between(String fieldName, double from, double to) {
        this.realm.checkIfValid();
        this.query.between(this.schema.getColumnIndices(fieldName, RealmFieldType.DOUBLE).getColumnIndices(), from, to);
        return this;
    }

    public RealmQuery<E> between(String fieldName, float from, float to) {
        this.realm.checkIfValid();
        this.query.between(this.schema.getColumnIndices(fieldName, RealmFieldType.FLOAT).getColumnIndices(), from, to);
        return this;
    }

    public RealmQuery<E> between(String fieldName, Date from, Date to) {
        this.realm.checkIfValid();
        this.query.between(this.schema.getColumnIndices(fieldName, RealmFieldType.DATE).getColumnIndices(), from, to);
        return this;
    }

    public RealmQuery<E> contains(String fieldName, String value) {
        return contains(fieldName, value, Case.SENSITIVE);
    }

    public RealmQuery<E> contains(String fieldName, String value, Case casing) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.STRING);
        this.query.contains(fd.getColumnIndices(), fd.getNativeTablePointers(), value, casing);
        return this;
    }

    public RealmQuery<E> beginsWith(String fieldName, String value) {
        return beginsWith(fieldName, value, Case.SENSITIVE);
    }

    public RealmQuery<E> beginsWith(String fieldName, String value, Case casing) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.STRING);
        this.query.beginsWith(fd.getColumnIndices(), fd.getNativeTablePointers(), value, casing);
        return this;
    }

    public RealmQuery<E> endsWith(String fieldName, String value) {
        return endsWith(fieldName, value, Case.SENSITIVE);
    }

    public RealmQuery<E> endsWith(String fieldName, String value, Case casing) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.STRING);
        this.query.endsWith(fd.getColumnIndices(), fd.getNativeTablePointers(), value, casing);
        return this;
    }

    public RealmQuery<E> like(String fieldName, String value) {
        return like(fieldName, value, Case.SENSITIVE);
    }

    public RealmQuery<E> like(String fieldName, String value, Case casing) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.STRING);
        this.query.like(fd.getColumnIndices(), fd.getNativeTablePointers(), value, casing);
        return this;
    }

    public RealmQuery<E> beginGroup() {
        this.realm.checkIfValid();
        return beginGroupWithoutThreadValidation();
    }

    private RealmQuery<E> beginGroupWithoutThreadValidation() {
        this.query.group();
        return this;
    }

    public RealmQuery<E> endGroup() {
        this.realm.checkIfValid();
        return endGroupWithoutThreadValidation();
    }

    private RealmQuery<E> endGroupWithoutThreadValidation() {
        this.query.endGroup();
        return this;
    }

    public RealmQuery<E> or() {
        this.realm.checkIfValid();
        return orWithoutThreadValidation();
    }

    private RealmQuery<E> orWithoutThreadValidation() {
        this.query.or();
        return this;
    }

    public RealmQuery<E> not() {
        this.realm.checkIfValid();
        this.query.not();
        return this;
    }

    public RealmQuery<E> isEmpty(String fieldName) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.STRING, RealmFieldType.BINARY, RealmFieldType.LIST, RealmFieldType.LINKING_OBJECTS);
        this.query.isEmpty(fd.getColumnIndices(), fd.getNativeTablePointers());
        return this;
    }

    public RealmQuery<E> isNotEmpty(String fieldName) {
        this.realm.checkIfValid();
        FieldDescriptor fd = this.schema.getColumnIndices(fieldName, RealmFieldType.STRING, RealmFieldType.BINARY, RealmFieldType.LIST, RealmFieldType.LINKING_OBJECTS);
        this.query.isNotEmpty(fd.getColumnIndices(), fd.getNativeTablePointers());
        return this;
    }

    public RealmResults<E> distinct(String fieldName) {
        this.realm.checkIfValid();
        return createRealmResults(this.query, null, SortDescriptor.getInstanceForDistinct(getSchemaConnector(), this.query.getTable(), fieldName), true);
    }

    public RealmResults<E> distinctAsync(String fieldName) {
        this.realm.checkIfValid();
        this.realm.sharedRealm.capabilities.checkCanDeliverNotification(ASYNC_QUERY_WRONG_THREAD_MESSAGE);
        return createRealmResults(this.query, null, SortDescriptor.getInstanceForDistinct(getSchemaConnector(), this.query.getTable(), fieldName), false);
    }

    public RealmResults<E> distinct(String firstFieldName, String... remainingFieldNames) {
        this.realm.checkIfValid();
        String[] fieldNames = new String[(remainingFieldNames.length + 1)];
        fieldNames[0] = firstFieldName;
        System.arraycopy(remainingFieldNames, 0, fieldNames, 1, remainingFieldNames.length);
        return createRealmResults(this.query, null, SortDescriptor.getInstanceForDistinct(getSchemaConnector(), this.table, fieldNames), true);
    }

    public Number sum(String fieldName) {
        this.realm.checkIfValid();
        long columnIndex = this.schema.getAndCheckFieldIndex(fieldName);
        switch (1.$SwitchMap$io$realm$RealmFieldType[this.table.getColumnType(columnIndex).ordinal()]) {
            case 1:
                return Long.valueOf(this.query.sumInt(columnIndex));
            case 2:
                return Double.valueOf(this.query.sumFloat(columnIndex));
            case 3:
                return Double.valueOf(this.query.sumDouble(columnIndex));
            default:
                throw new IllegalArgumentException(String.format(Locale.US, TYPE_MISMATCH, new Object[]{fieldName, "int, float or double"}));
        }
    }

    public double average(String fieldName) {
        this.realm.checkIfValid();
        long columnIndex = this.schema.getAndCheckFieldIndex(fieldName);
        switch (1.$SwitchMap$io$realm$RealmFieldType[this.table.getColumnType(columnIndex).ordinal()]) {
            case 1:
                return this.query.averageInt(columnIndex);
            case 2:
                return this.query.averageFloat(columnIndex);
            case 3:
                return this.query.averageDouble(columnIndex);
            default:
                throw new IllegalArgumentException(String.format(Locale.US, TYPE_MISMATCH, new Object[]{fieldName, "int, float or double"}));
        }
    }

    @Nullable
    public Number min(String fieldName) {
        this.realm.checkIfValid();
        long columnIndex = this.schema.getAndCheckFieldIndex(fieldName);
        switch (1.$SwitchMap$io$realm$RealmFieldType[this.table.getColumnType(columnIndex).ordinal()]) {
            case 1:
                return this.query.minimumInt(columnIndex);
            case 2:
                return this.query.minimumFloat(columnIndex);
            case 3:
                return this.query.minimumDouble(columnIndex);
            default:
                throw new IllegalArgumentException(String.format(Locale.US, TYPE_MISMATCH, new Object[]{fieldName, "int, float or double"}));
        }
    }

    @Nullable
    public Date minimumDate(String fieldName) {
        this.realm.checkIfValid();
        return this.query.minimumDate(this.schema.getAndCheckFieldIndex(fieldName));
    }

    @Nullable
    public Number max(String fieldName) {
        this.realm.checkIfValid();
        long columnIndex = this.schema.getAndCheckFieldIndex(fieldName);
        switch (1.$SwitchMap$io$realm$RealmFieldType[this.table.getColumnType(columnIndex).ordinal()]) {
            case 1:
                return this.query.maximumInt(columnIndex);
            case 2:
                return this.query.maximumFloat(columnIndex);
            case 3:
                return this.query.maximumDouble(columnIndex);
            default:
                throw new IllegalArgumentException(String.format(Locale.US, TYPE_MISMATCH, new Object[]{fieldName, "int, float or double"}));
        }
    }

    @Nullable
    public Date maximumDate(String fieldName) {
        this.realm.checkIfValid();
        return this.query.maximumDate(this.schema.getAndCheckFieldIndex(fieldName));
    }

    public long count() {
        this.realm.checkIfValid();
        return this.query.count();
    }

    public RealmResults<E> findAll() {
        this.realm.checkIfValid();
        return createRealmResults(this.query, null, null, true);
    }

    public RealmResults<E> findAllAsync() {
        this.realm.checkIfValid();
        this.realm.sharedRealm.capabilities.checkCanDeliverNotification(ASYNC_QUERY_WRONG_THREAD_MESSAGE);
        return createRealmResults(this.query, null, null, false);
    }

    public RealmResults<E> findAllSorted(String fieldName, Sort sortOrder) {
        this.realm.checkIfValid();
        return createRealmResults(this.query, SortDescriptor.getInstanceForSort(getSchemaConnector(), this.query.getTable(), fieldName, sortOrder), null, true);
    }

    public RealmResults<E> findAllSortedAsync(String fieldName, Sort sortOrder) {
        this.realm.checkIfValid();
        this.realm.sharedRealm.capabilities.checkCanDeliverNotification(ASYNC_QUERY_WRONG_THREAD_MESSAGE);
        return createRealmResults(this.query, SortDescriptor.getInstanceForSort(getSchemaConnector(), this.query.getTable(), fieldName, sortOrder), null, false);
    }

    public RealmResults<E> findAllSorted(String fieldName) {
        return findAllSorted(fieldName, Sort.ASCENDING);
    }

    public RealmResults<E> findAllSortedAsync(String fieldName) {
        return findAllSortedAsync(fieldName, Sort.ASCENDING);
    }

    public RealmResults<E> findAllSorted(String[] fieldNames, Sort[] sortOrders) {
        this.realm.checkIfValid();
        return createRealmResults(this.query, SortDescriptor.getInstanceForSort(getSchemaConnector(), this.query.getTable(), fieldNames, sortOrders), null, true);
    }

    private boolean isDynamicQuery() {
        return this.className != null;
    }

    public RealmResults<E> findAllSortedAsync(String[] fieldNames, Sort[] sortOrders) {
        this.realm.checkIfValid();
        this.realm.sharedRealm.capabilities.checkCanDeliverNotification(ASYNC_QUERY_WRONG_THREAD_MESSAGE);
        return createRealmResults(this.query, SortDescriptor.getInstanceForSort(getSchemaConnector(), this.query.getTable(), fieldNames, sortOrders), null, false);
    }

    public RealmResults<E> findAllSorted(String fieldName1, Sort sortOrder1, String fieldName2, Sort sortOrder2) {
        return findAllSorted(new String[]{fieldName1, fieldName2}, new Sort[]{sortOrder1, sortOrder2});
    }

    public RealmResults<E> findAllSortedAsync(String fieldName1, Sort sortOrder1, String fieldName2, Sort sortOrder2) {
        return findAllSortedAsync(new String[]{fieldName1, fieldName2}, new Sort[]{sortOrder1, sortOrder2});
    }

    @Nullable
    public E findFirst() {
        this.realm.checkIfValid();
        long tableRowIndex = getSourceRowIndexForFirstObject();
        return tableRowIndex < 0 ? null : this.realm.get(this.clazz, this.className, tableRowIndex);
    }

    public E findFirstAsync() {
        Row row;
        E result;
        this.realm.checkIfValid();
        this.realm.sharedRealm.capabilities.checkCanDeliverNotification(ASYNC_QUERY_WRONG_THREAD_MESSAGE);
        if (this.realm.isInTransaction()) {
            row = new Collection(this.realm.sharedRealm, this.query).firstUncheckedRow();
        } else {
            row = new PendingRow(this.realm.sharedRealm, this.query, null, isDynamicQuery());
        }
        if (isDynamicQuery()) {
            result = new DynamicRealmObject(this.realm, row);
        } else {
            result = this.realm.getConfiguration().getSchemaMediator().newInstance(this.clazz, this.realm, row, this.realm.getSchema().getColumnInfo(this.clazz), false, Collections.emptyList());
        }
        if (row instanceof PendingRow) {
            ((PendingRow) row).setFrontEnd(((RealmObjectProxy) result).realmGet$proxyState());
        }
        return result;
    }

    private RealmResults<E> createRealmResults(TableQuery query, @Nullable SortDescriptor sortDescriptor, @Nullable SortDescriptor distinctDescriptor, boolean loadResults) {
        RealmResults<E> results;
        Collection collection = new Collection(this.realm.sharedRealm, query, sortDescriptor, distinctDescriptor);
        if (isDynamicQuery()) {
            results = new RealmResults(this.realm, collection, this.className);
        } else {
            results = new RealmResults(this.realm, collection, this.clazz);
        }
        if (loadResults) {
            results.load();
        }
        return results;
    }

    private long getSourceRowIndexForFirstObject() {
        return this.query.find();
    }

    private SchemaConnector getSchemaConnector() {
        return new SchemaConnector(this.realm.getSchema());
    }
}
