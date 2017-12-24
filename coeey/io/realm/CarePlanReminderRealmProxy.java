package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.CarePlanReminder;
import com.cooey.common.vo.careplan.Days;
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

public class CarePlanReminderRealmProxy extends CarePlanReminder implements RealmObjectProxy, CarePlanReminderRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private CarePlanReminderColumnInfo columnInfo;
    private ProxyState<CarePlanReminder> proxyState;

    static final class CarePlanReminderColumnInfo extends ColumnInfo {
        long dateIndex;
        long daysIndex;
        long endTimeIndex;
        long hourIndex;
        long minutesIndex;
        long startTimeIndex;

        CarePlanReminderColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("CarePlanReminder");
            this.startTimeIndex = addColumnDetails("startTime", objectSchemaInfo);
            this.endTimeIndex = addColumnDetails("endTime", objectSchemaInfo);
            this.daysIndex = addColumnDetails("days", objectSchemaInfo);
            this.hourIndex = addColumnDetails("hour", objectSchemaInfo);
            this.minutesIndex = addColumnDetails("minutes", objectSchemaInfo);
            this.dateIndex = addColumnDetails("date", objectSchemaInfo);
        }

        CarePlanReminderColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new CarePlanReminderColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            CarePlanReminderColumnInfo src = (CarePlanReminderColumnInfo) rawSrc;
            CarePlanReminderColumnInfo dst = (CarePlanReminderColumnInfo) rawDst;
            dst.startTimeIndex = src.startTimeIndex;
            dst.endTimeIndex = src.endTimeIndex;
            dst.daysIndex = src.daysIndex;
            dst.hourIndex = src.hourIndex;
            dst.minutesIndex = src.minutesIndex;
            dst.dateIndex = src.dateIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("startTime");
        fieldNames.add("endTime");
        fieldNames.add("days");
        fieldNames.add("hour");
        fieldNames.add("minutes");
        fieldNames.add("date");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CarePlanReminderRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (CarePlanReminderColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public long realmGet$startTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.startTimeIndex);
    }

    public void realmSet$startTime(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.startTimeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.startTimeIndex, row.getIndex(), value, true);
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

    public Days realmGet$days() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.daysIndex)) {
            return null;
        }
        return (Days) this.proxyState.getRealm$realm().get(Days.class, this.proxyState.getRow$realm().getLink(this.columnInfo.daysIndex), false, Collections.emptyList());
    }

    public void realmSet$days(Days value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.daysIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.daysIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("days")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Days) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.daysIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.daysIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public int realmGet$hour() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.hourIndex);
    }

    public void realmSet$hour(int value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.hourIndex, (long) value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.hourIndex, row.getIndex(), (long) value, true);
        }
    }

    public int realmGet$minutes() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.minutesIndex);
    }

    public void realmSet$minutes(int value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.minutesIndex, (long) value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.minutesIndex, row.getIndex(), (long) value, true);
        }
    }

    public int realmGet$date() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.dateIndex);
    }

    public void realmSet$date(int value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.dateIndex, (long) value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.dateIndex, row.getIndex(), (long) value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("CarePlanReminder");
        builder.addPersistedProperty("startTime", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("endTime", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("days", RealmFieldType.OBJECT, "Days");
        builder.addPersistedProperty("hour", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("minutes", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("date", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CarePlanReminderColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new CarePlanReminderColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_CarePlanReminder";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static CarePlanReminder createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        if (json.has("days")) {
            excludeFields.add("days");
        }
        CarePlanReminder obj = (CarePlanReminder) realm.createObjectInternal(CarePlanReminder.class, true, excludeFields);
        CarePlanReminderRealmProxyInterface objProxy = obj;
        if (json.has("startTime")) {
            if (json.isNull("startTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'startTime' to null.");
            }
            objProxy.realmSet$startTime(json.getLong("startTime"));
        }
        if (json.has("endTime")) {
            if (json.isNull("endTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'endTime' to null.");
            }
            objProxy.realmSet$endTime(json.getLong("endTime"));
        }
        if (json.has("days")) {
            if (json.isNull("days")) {
                objProxy.realmSet$days(null);
            } else {
                objProxy.realmSet$days(DaysRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("days"), update));
            }
        }
        if (json.has("hour")) {
            if (json.isNull("hour")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'hour' to null.");
            }
            objProxy.realmSet$hour(json.getInt("hour"));
        }
        if (json.has("minutes")) {
            if (json.isNull("minutes")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'minutes' to null.");
            }
            objProxy.realmSet$minutes(json.getInt("minutes"));
        }
        if (json.has("date")) {
            if (json.isNull("date")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'date' to null.");
            }
            objProxy.realmSet$date(json.getInt("date"));
        }
        return obj;
    }

    @TargetApi(11)
    public static CarePlanReminder createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        CarePlanReminder obj = new CarePlanReminder();
        CarePlanReminderRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("startTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$startTime(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'startTime' to null.");
                }
            } else if (name.equals("endTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$endTime(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'endTime' to null.");
                }
            } else if (name.equals("days")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$days(null);
                } else {
                    objProxy.realmSet$days(DaysRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("hour")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$hour(reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'hour' to null.");
                }
            } else if (name.equals("minutes")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$minutes(reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'minutes' to null.");
                }
            } else if (!name.equals("date")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$date(reader.nextInt());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'date' to null.");
            }
        }
        reader.endObject();
        return (CarePlanReminder) realm.copyToRealm(obj);
    }

    public static CarePlanReminder copyOrUpdate(Realm realm, CarePlanReminder object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (CarePlanReminder) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static CarePlanReminder copy(Realm realm, CarePlanReminder newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (CarePlanReminder) cachedRealmObject;
        }
        CarePlanReminder realmObject = (CarePlanReminder) realm.createObjectInternal(CarePlanReminder.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        CarePlanReminderRealmProxyInterface realmObjectSource = newObject;
        CarePlanReminderRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$startTime(realmObjectSource.realmGet$startTime());
        realmObjectCopy.realmSet$endTime(realmObjectSource.realmGet$endTime());
        Days daysObj = realmObjectSource.realmGet$days();
        if (daysObj == null) {
            realmObjectCopy.realmSet$days(null);
        } else {
            Days cachedays = (Days) cache.get(daysObj);
            if (cachedays != null) {
                realmObjectCopy.realmSet$days(cachedays);
            } else {
                realmObjectCopy.realmSet$days(DaysRealmProxy.copyOrUpdate(realm, daysObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$hour(realmObjectSource.realmGet$hour());
        realmObjectCopy.realmSet$minutes(realmObjectSource.realmGet$minutes());
        realmObjectCopy.realmSet$date(realmObjectSource.realmGet$date());
        return realmObject;
    }

    public static long insert(Realm realm, CarePlanReminder object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(CarePlanReminder.class);
        long tableNativePtr = table.getNativePtr();
        CarePlanReminderColumnInfo columnInfo = (CarePlanReminderColumnInfo) realm.getSchema().getColumnInfo(CarePlanReminder.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
        Days daysObj = object.realmGet$days();
        if (daysObj != null) {
            Long cachedays = (Long) cache.get(daysObj);
            if (cachedays == null) {
                cachedays = Long.valueOf(DaysRealmProxy.insert(realm, daysObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.daysIndex, rowIndex, cachedays.longValue(), false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.hourIndex, rowIndex, (long) object.realmGet$hour(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.minutesIndex, rowIndex, (long) object.realmGet$minutes(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.dateIndex, rowIndex, (long) object.realmGet$date(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(CarePlanReminder.class);
        long tableNativePtr = table.getNativePtr();
        CarePlanReminderColumnInfo columnInfo = (CarePlanReminderColumnInfo) realm.getSchema().getColumnInfo(CarePlanReminder.class);
        while (objects.hasNext()) {
            CarePlanReminder object = (CarePlanReminder) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
                    Days daysObj = object.realmGet$days();
                    if (daysObj != null) {
                        Long cachedays = (Long) cache.get(daysObj);
                        if (cachedays == null) {
                            cachedays = Long.valueOf(DaysRealmProxy.insert(realm, daysObj, (Map) cache));
                        }
                        table.setLink(columnInfo.daysIndex, rowIndex, cachedays.longValue(), false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.hourIndex, rowIndex, (long) object.realmGet$hour(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.minutesIndex, rowIndex, (long) object.realmGet$minutes(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.dateIndex, rowIndex, (long) object.realmGet$date(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, CarePlanReminder object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(CarePlanReminder.class);
        long tableNativePtr = table.getNativePtr();
        CarePlanReminderColumnInfo columnInfo = (CarePlanReminderColumnInfo) realm.getSchema().getColumnInfo(CarePlanReminder.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
        Days daysObj = object.realmGet$days();
        if (daysObj != null) {
            Long cachedays = (Long) cache.get(daysObj);
            if (cachedays == null) {
                cachedays = Long.valueOf(DaysRealmProxy.insertOrUpdate(realm, daysObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.daysIndex, rowIndex, cachedays.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.daysIndex, rowIndex);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.hourIndex, rowIndex, (long) object.realmGet$hour(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.minutesIndex, rowIndex, (long) object.realmGet$minutes(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.dateIndex, rowIndex, (long) object.realmGet$date(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(CarePlanReminder.class);
        long tableNativePtr = table.getNativePtr();
        CarePlanReminderColumnInfo columnInfo = (CarePlanReminderColumnInfo) realm.getSchema().getColumnInfo(CarePlanReminder.class);
        while (objects.hasNext()) {
            CarePlanReminder object = (CarePlanReminder) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
                    Days daysObj = object.realmGet$days();
                    if (daysObj != null) {
                        Long cachedays = (Long) cache.get(daysObj);
                        if (cachedays == null) {
                            cachedays = Long.valueOf(DaysRealmProxy.insertOrUpdate(realm, daysObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.daysIndex, rowIndex, cachedays.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.daysIndex, rowIndex);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.hourIndex, rowIndex, (long) object.realmGet$hour(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.minutesIndex, rowIndex, (long) object.realmGet$minutes(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.dateIndex, rowIndex, (long) object.realmGet$date(), false);
                }
            }
        }
    }

    public static CarePlanReminder createDetachedCopy(CarePlanReminder realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CarePlanReminder unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new CarePlanReminder();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (CarePlanReminder) cachedObject.object;
        } else {
            unmanagedObject = (CarePlanReminder) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        CarePlanReminderRealmProxyInterface unmanagedCopy = unmanagedObject;
        CarePlanReminderRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$startTime(realmSource.realmGet$startTime());
        unmanagedCopy.realmSet$endTime(realmSource.realmGet$endTime());
        unmanagedCopy.realmSet$days(DaysRealmProxy.createDetachedCopy(realmSource.realmGet$days(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$hour(realmSource.realmGet$hour());
        unmanagedCopy.realmSet$minutes(realmSource.realmGet$minutes());
        unmanagedCopy.realmSet$date(realmSource.realmGet$date());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("CarePlanReminder = proxy[");
        stringBuilder.append("{startTime:");
        stringBuilder.append(realmGet$startTime());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{endTime:");
        stringBuilder.append(realmGet$endTime());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{days:");
        stringBuilder.append(realmGet$days() != null ? "Days" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{hour:");
        stringBuilder.append(realmGet$hour());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{minutes:");
        stringBuilder.append(realmGet$minutes());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date:");
        stringBuilder.append(realmGet$date());
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
        CarePlanReminderRealmProxy aCarePlanReminder = (CarePlanReminderRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aCarePlanReminder.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCarePlanReminder.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aCarePlanReminder.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
