package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Schedule;
import com.cooey.common.vo.Timings;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsObjectSchemaInfo.Builder;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmObjectProxy.CacheData;
import io.realm.internal.Row;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ScheduleRealmProxy extends Schedule implements RealmObjectProxy, ScheduleRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ScheduleColumnInfo columnInfo;
    private ProxyState<Schedule> proxyState;

    static final class ScheduleColumnInfo extends ColumnInfo {
        long beginTimeIndex;
        long endTimeIndex;
        long numOfDaysIndex;
        long timingsIndex;

        ScheduleColumnInfo(OsSchemaInfo schemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Schedule");
            this.numOfDaysIndex = addColumnDetails("numOfDays", objectSchemaInfo);
            this.timingsIndex = addColumnDetails("timings", objectSchemaInfo);
            this.beginTimeIndex = addColumnDetails("beginTime", objectSchemaInfo);
            this.endTimeIndex = addColumnDetails("endTime", objectSchemaInfo);
        }

        ScheduleColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new ScheduleColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ScheduleColumnInfo src = (ScheduleColumnInfo) rawSrc;
            ScheduleColumnInfo dst = (ScheduleColumnInfo) rawDst;
            dst.numOfDaysIndex = src.numOfDaysIndex;
            dst.timingsIndex = src.timingsIndex;
            dst.beginTimeIndex = src.beginTimeIndex;
            dst.endTimeIndex = src.endTimeIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("numOfDays");
        fieldNames.add("timings");
        fieldNames.add("beginTime");
        fieldNames.add("endTime");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ScheduleRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ScheduleColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public int realmGet$numOfDays() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.numOfDaysIndex);
    }

    public void realmSet$numOfDays(int value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.numOfDaysIndex, (long) value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.numOfDaysIndex, row.getIndex(), (long) value, true);
        }
    }

    public Timings realmGet$timings() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.timingsIndex)) {
            return null;
        }
        return (Timings) this.proxyState.getRealm$realm().get(Timings.class, this.proxyState.getRow$realm().getLink(this.columnInfo.timingsIndex), false, Collections.emptyList());
    }

    public void realmSet$timings(Timings value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.timingsIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.timingsIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("timings")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Timings) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.timingsIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.timingsIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public long realmGet$beginTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.beginTimeIndex);
    }

    public void realmSet$beginTime(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.beginTimeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.beginTimeIndex, row.getIndex(), value, true);
        }
    }

    public long realmGet$endTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.endTimeIndex);
    }

    public void realmSet$endTime(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.endTimeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.endTimeIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Schedule");
        builder.addPersistedProperty("numOfDays", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("timings", RealmFieldType.OBJECT, "Timings");
        builder.addPersistedProperty("beginTime", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("endTime", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ScheduleColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ScheduleColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Schedule";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Schedule createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        if (json.has("timings")) {
            excludeFields.add("timings");
        }
        Schedule obj = (Schedule) realm.createObjectInternal(Schedule.class, true, excludeFields);
        ScheduleRealmProxyInterface objProxy = obj;
        if (json.has("numOfDays")) {
            if (json.isNull("numOfDays")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'numOfDays' to null.");
            }
            objProxy.realmSet$numOfDays(json.getInt("numOfDays"));
        }
        if (json.has("timings")) {
            if (json.isNull("timings")) {
                objProxy.realmSet$timings(null);
            } else {
                objProxy.realmSet$timings(TimingsRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("timings"), update));
            }
        }
        if (json.has("beginTime")) {
            if (json.isNull("beginTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'beginTime' to null.");
            }
            objProxy.realmSet$beginTime(json.getLong("beginTime"));
        }
        if (json.has("endTime")) {
            if (json.isNull("endTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'endTime' to null.");
            }
            objProxy.realmSet$endTime(json.getLong("endTime"));
        }
        return obj;
    }

    @TargetApi(11)
    public static Schedule createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Schedule obj = new Schedule();
        ScheduleRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("numOfDays")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$numOfDays(reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'numOfDays' to null.");
                }
            } else if (name.equals("timings")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$timings(null);
                } else {
                    objProxy.realmSet$timings(TimingsRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("beginTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$beginTime(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'beginTime' to null.");
                }
            } else if (!name.equals("endTime")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$endTime(reader.nextLong());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'endTime' to null.");
            }
        }
        reader.endObject();
        return (Schedule) realm.copyToRealm(obj);
    }

    public static Schedule copyOrUpdate(Realm realm, Schedule object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            } else if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(object);
        if (cachedRealmObject != null) {
            return (Schedule) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Schedule copy(Realm realm, Schedule newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Schedule) cachedRealmObject;
        }
        Schedule realmObject = (Schedule) realm.createObjectInternal(Schedule.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ScheduleRealmProxyInterface realmObjectSource = newObject;
        ScheduleRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$numOfDays(realmObjectSource.realmGet$numOfDays());
        Timings timingsObj = realmObjectSource.realmGet$timings();
        if (timingsObj == null) {
            realmObjectCopy.realmSet$timings(null);
        } else {
            Timings cachetimings = (Timings) cache.get(timingsObj);
            if (cachetimings != null) {
                realmObjectCopy.realmSet$timings(cachetimings);
            } else {
                realmObjectCopy.realmSet$timings(TimingsRealmProxy.copyOrUpdate(realm, timingsObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$beginTime(realmObjectSource.realmGet$beginTime());
        realmObjectCopy.realmSet$endTime(realmObjectSource.realmGet$endTime());
        return realmObject;
    }

    public static long insert(Realm realm, Schedule object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Schedule.class);
        long tableNativePtr = table.getNativePtr();
        ScheduleColumnInfo columnInfo = (ScheduleColumnInfo) realm.getSchema().getColumnInfo(Schedule.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Table.nativeSetLong(tableNativePtr, columnInfo.numOfDaysIndex, rowIndex, (long) object.realmGet$numOfDays(), false);
        Timings timingsObj = object.realmGet$timings();
        if (timingsObj != null) {
            Long cachetimings = (Long) cache.get(timingsObj);
            if (cachetimings == null) {
                cachetimings = Long.valueOf(TimingsRealmProxy.insert(realm, timingsObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.timingsIndex, rowIndex, cachetimings.longValue(), false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.beginTimeIndex, rowIndex, object.realmGet$beginTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Schedule.class);
        long tableNativePtr = table.getNativePtr();
        ScheduleColumnInfo columnInfo = (ScheduleColumnInfo) realm.getSchema().getColumnInfo(Schedule.class);
        while (objects.hasNext()) {
            Schedule object = (Schedule) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Table.nativeSetLong(tableNativePtr, columnInfo.numOfDaysIndex, rowIndex, (long) object.realmGet$numOfDays(), false);
                    Timings timingsObj = object.realmGet$timings();
                    if (timingsObj != null) {
                        Long cachetimings = (Long) cache.get(timingsObj);
                        if (cachetimings == null) {
                            cachetimings = Long.valueOf(TimingsRealmProxy.insert(realm, timingsObj, (Map) cache));
                        }
                        table.setLink(columnInfo.timingsIndex, rowIndex, cachetimings.longValue(), false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.beginTimeIndex, rowIndex, object.realmGet$beginTime(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Schedule object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Schedule.class);
        long tableNativePtr = table.getNativePtr();
        ScheduleColumnInfo columnInfo = (ScheduleColumnInfo) realm.getSchema().getColumnInfo(Schedule.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Table.nativeSetLong(tableNativePtr, columnInfo.numOfDaysIndex, rowIndex, (long) object.realmGet$numOfDays(), false);
        Timings timingsObj = object.realmGet$timings();
        if (timingsObj != null) {
            Long cachetimings = (Long) cache.get(timingsObj);
            if (cachetimings == null) {
                cachetimings = Long.valueOf(TimingsRealmProxy.insertOrUpdate(realm, timingsObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.timingsIndex, rowIndex, cachetimings.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.timingsIndex, rowIndex);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.beginTimeIndex, rowIndex, object.realmGet$beginTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Schedule.class);
        long tableNativePtr = table.getNativePtr();
        ScheduleColumnInfo columnInfo = (ScheduleColumnInfo) realm.getSchema().getColumnInfo(Schedule.class);
        while (objects.hasNext()) {
            Schedule object = (Schedule) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Table.nativeSetLong(tableNativePtr, columnInfo.numOfDaysIndex, rowIndex, (long) object.realmGet$numOfDays(), false);
                    Timings timingsObj = object.realmGet$timings();
                    if (timingsObj != null) {
                        Long cachetimings = (Long) cache.get(timingsObj);
                        if (cachetimings == null) {
                            cachetimings = Long.valueOf(TimingsRealmProxy.insertOrUpdate(realm, timingsObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.timingsIndex, rowIndex, cachetimings.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.timingsIndex, rowIndex);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.beginTimeIndex, rowIndex, object.realmGet$beginTime(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
                }
            }
        }
    }

    public static Schedule createDetachedCopy(Schedule realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Schedule unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Schedule();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Schedule) cachedObject.object;
        } else {
            unmanagedObject = (Schedule) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ScheduleRealmProxyInterface unmanagedCopy = unmanagedObject;
        ScheduleRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$numOfDays(realmSource.realmGet$numOfDays());
        unmanagedCopy.realmSet$timings(TimingsRealmProxy.createDetachedCopy(realmSource.realmGet$timings(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$beginTime(realmSource.realmGet$beginTime());
        unmanagedCopy.realmSet$endTime(realmSource.realmGet$endTime());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Schedule = proxy[");
        stringBuilder.append("{numOfDays:");
        stringBuilder.append(realmGet$numOfDays());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timings:");
        stringBuilder.append(realmGet$timings() != null ? "Timings" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{beginTime:");
        stringBuilder.append(realmGet$beginTime());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{endTime:");
        stringBuilder.append(realmGet$endTime());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public ProxyState<?> realmGet$proxyState() {
        return this.proxyState;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        String realmName = this.proxyState.getRealm$realm().getPath();
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        long rowIndex = this.proxyState.getRow$realm().getIndex();
        if (realmName != null) {
            hashCode = realmName.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + 527) * 31;
        if (tableName != null) {
            i = tableName.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) ((rowIndex >>> 32) ^ rowIndex));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScheduleRealmProxy aSchedule = (ScheduleRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aSchedule.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSchedule.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aSchedule.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
