package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
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

public class DaysRealmProxy extends Days implements RealmObjectProxy, DaysRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private DaysColumnInfo columnInfo;
    private ProxyState<Days> proxyState;

    static final class DaysColumnInfo extends ColumnInfo {
        long fridayIndex;
        long mondayIndex;
        long saturdayIndex;
        long sundayIndex;
        long thursdayIndex;
        long tuesdayIndex;
        long wednesdayIndex;

        DaysColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Days");
            this.mondayIndex = addColumnDetails("monday", objectSchemaInfo);
            this.tuesdayIndex = addColumnDetails("tuesday", objectSchemaInfo);
            this.wednesdayIndex = addColumnDetails("wednesday", objectSchemaInfo);
            this.thursdayIndex = addColumnDetails("thursday", objectSchemaInfo);
            this.fridayIndex = addColumnDetails("friday", objectSchemaInfo);
            this.saturdayIndex = addColumnDetails("saturday", objectSchemaInfo);
            this.sundayIndex = addColumnDetails("sunday", objectSchemaInfo);
        }

        DaysColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new DaysColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            DaysColumnInfo src = (DaysColumnInfo) rawSrc;
            DaysColumnInfo dst = (DaysColumnInfo) rawDst;
            dst.mondayIndex = src.mondayIndex;
            dst.tuesdayIndex = src.tuesdayIndex;
            dst.wednesdayIndex = src.wednesdayIndex;
            dst.thursdayIndex = src.thursdayIndex;
            dst.fridayIndex = src.fridayIndex;
            dst.saturdayIndex = src.saturdayIndex;
            dst.sundayIndex = src.sundayIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("monday");
        fieldNames.add("tuesday");
        fieldNames.add("wednesday");
        fieldNames.add("thursday");
        fieldNames.add("friday");
        fieldNames.add("saturday");
        fieldNames.add("sunday");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DaysRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (DaysColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public boolean realmGet$monday() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.mondayIndex);
    }

    public void realmSet$monday(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.mondayIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.mondayIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$tuesday() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.tuesdayIndex);
    }

    public void realmSet$tuesday(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.tuesdayIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.tuesdayIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$wednesday() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.wednesdayIndex);
    }

    public void realmSet$wednesday(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.wednesdayIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.wednesdayIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$thursday() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.thursdayIndex);
    }

    public void realmSet$thursday(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.thursdayIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.thursdayIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$friday() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.fridayIndex);
    }

    public void realmSet$friday(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.fridayIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.fridayIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$saturday() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.saturdayIndex);
    }

    public void realmSet$saturday(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.saturdayIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.saturdayIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$sunday() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.sundayIndex);
    }

    public void realmSet$sunday(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.sundayIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.sundayIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Days");
        builder.addPersistedProperty("monday", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("tuesday", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("wednesday", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("thursday", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("friday", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("saturday", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("sunday", RealmFieldType.BOOLEAN, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static DaysColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new DaysColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Days";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Days createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Days obj = (Days) realm.createObjectInternal(Days.class, true, Collections.emptyList());
        DaysRealmProxyInterface objProxy = obj;
        if (json.has("monday")) {
            if (json.isNull("monday")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'monday' to null.");
            }
            objProxy.realmSet$monday(json.getBoolean("monday"));
        }
        if (json.has("tuesday")) {
            if (json.isNull("tuesday")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'tuesday' to null.");
            }
            objProxy.realmSet$tuesday(json.getBoolean("tuesday"));
        }
        if (json.has("wednesday")) {
            if (json.isNull("wednesday")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'wednesday' to null.");
            }
            objProxy.realmSet$wednesday(json.getBoolean("wednesday"));
        }
        if (json.has("thursday")) {
            if (json.isNull("thursday")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'thursday' to null.");
            }
            objProxy.realmSet$thursday(json.getBoolean("thursday"));
        }
        if (json.has("friday")) {
            if (json.isNull("friday")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'friday' to null.");
            }
            objProxy.realmSet$friday(json.getBoolean("friday"));
        }
        if (json.has("saturday")) {
            if (json.isNull("saturday")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'saturday' to null.");
            }
            objProxy.realmSet$saturday(json.getBoolean("saturday"));
        }
        if (json.has("sunday")) {
            if (json.isNull("sunday")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'sunday' to null.");
            }
            objProxy.realmSet$sunday(json.getBoolean("sunday"));
        }
        return obj;
    }

    @TargetApi(11)
    public static Days createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Days obj = new Days();
        DaysRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("monday")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$monday(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'monday' to null.");
                }
            } else if (name.equals("tuesday")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tuesday(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'tuesday' to null.");
                }
            } else if (name.equals("wednesday")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$wednesday(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'wednesday' to null.");
                }
            } else if (name.equals("thursday")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$thursday(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'thursday' to null.");
                }
            } else if (name.equals("friday")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$friday(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'friday' to null.");
                }
            } else if (name.equals("saturday")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$saturday(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'saturday' to null.");
                }
            } else if (!name.equals("sunday")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$sunday(reader.nextBoolean());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'sunday' to null.");
            }
        }
        reader.endObject();
        return (Days) realm.copyToRealm(obj);
    }

    public static Days copyOrUpdate(Realm realm, Days object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Days) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Days copy(Realm realm, Days newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Days) cachedRealmObject;
        }
        Days realmObject = (Days) realm.createObjectInternal(Days.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        DaysRealmProxyInterface realmObjectSource = newObject;
        DaysRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$monday(realmObjectSource.realmGet$monday());
        realmObjectCopy.realmSet$tuesday(realmObjectSource.realmGet$tuesday());
        realmObjectCopy.realmSet$wednesday(realmObjectSource.realmGet$wednesday());
        realmObjectCopy.realmSet$thursday(realmObjectSource.realmGet$thursday());
        realmObjectCopy.realmSet$friday(realmObjectSource.realmGet$friday());
        realmObjectCopy.realmSet$saturday(realmObjectSource.realmGet$saturday());
        realmObjectCopy.realmSet$sunday(realmObjectSource.realmGet$sunday());
        return realmObject;
    }

    public static long insert(Realm realm, Days object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Days.class);
        long tableNativePtr = table.getNativePtr();
        DaysColumnInfo columnInfo = (DaysColumnInfo) realm.getSchema().getColumnInfo(Days.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Table.nativeSetBoolean(tableNativePtr, columnInfo.mondayIndex, rowIndex, object.realmGet$monday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.tuesdayIndex, rowIndex, object.realmGet$tuesday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.wednesdayIndex, rowIndex, object.realmGet$wednesday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.thursdayIndex, rowIndex, object.realmGet$thursday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.fridayIndex, rowIndex, object.realmGet$friday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.saturdayIndex, rowIndex, object.realmGet$saturday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.sundayIndex, rowIndex, object.realmGet$sunday(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Days.class);
        long tableNativePtr = table.getNativePtr();
        DaysColumnInfo columnInfo = (DaysColumnInfo) realm.getSchema().getColumnInfo(Days.class);
        while (objects.hasNext()) {
            Days object = (Days) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.mondayIndex, rowIndex, object.realmGet$monday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.tuesdayIndex, rowIndex, object.realmGet$tuesday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.wednesdayIndex, rowIndex, object.realmGet$wednesday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.thursdayIndex, rowIndex, object.realmGet$thursday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.fridayIndex, rowIndex, object.realmGet$friday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.saturdayIndex, rowIndex, object.realmGet$saturday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.sundayIndex, rowIndex, object.realmGet$sunday(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Days object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Days.class);
        long tableNativePtr = table.getNativePtr();
        DaysColumnInfo columnInfo = (DaysColumnInfo) realm.getSchema().getColumnInfo(Days.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Table.nativeSetBoolean(tableNativePtr, columnInfo.mondayIndex, rowIndex, object.realmGet$monday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.tuesdayIndex, rowIndex, object.realmGet$tuesday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.wednesdayIndex, rowIndex, object.realmGet$wednesday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.thursdayIndex, rowIndex, object.realmGet$thursday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.fridayIndex, rowIndex, object.realmGet$friday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.saturdayIndex, rowIndex, object.realmGet$saturday(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.sundayIndex, rowIndex, object.realmGet$sunday(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Days.class);
        long tableNativePtr = table.getNativePtr();
        DaysColumnInfo columnInfo = (DaysColumnInfo) realm.getSchema().getColumnInfo(Days.class);
        while (objects.hasNext()) {
            Days object = (Days) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.mondayIndex, rowIndex, object.realmGet$monday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.tuesdayIndex, rowIndex, object.realmGet$tuesday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.wednesdayIndex, rowIndex, object.realmGet$wednesday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.thursdayIndex, rowIndex, object.realmGet$thursday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.fridayIndex, rowIndex, object.realmGet$friday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.saturdayIndex, rowIndex, object.realmGet$saturday(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.sundayIndex, rowIndex, object.realmGet$sunday(), false);
                }
            }
        }
    }

    public static Days createDetachedCopy(Days realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Days unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Days();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Days) cachedObject.object;
        } else {
            unmanagedObject = (Days) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        DaysRealmProxyInterface unmanagedCopy = unmanagedObject;
        DaysRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$monday(realmSource.realmGet$monday());
        unmanagedCopy.realmSet$tuesday(realmSource.realmGet$tuesday());
        unmanagedCopy.realmSet$wednesday(realmSource.realmGet$wednesday());
        unmanagedCopy.realmSet$thursday(realmSource.realmGet$thursday());
        unmanagedCopy.realmSet$friday(realmSource.realmGet$friday());
        unmanagedCopy.realmSet$saturday(realmSource.realmGet$saturday());
        unmanagedCopy.realmSet$sunday(realmSource.realmGet$sunday());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Days = proxy[");
        stringBuilder.append("{monday:");
        stringBuilder.append(realmGet$monday());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tuesday:");
        stringBuilder.append(realmGet$tuesday());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{wednesday:");
        stringBuilder.append(realmGet$wednesday());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{thursday:");
        stringBuilder.append(realmGet$thursday());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{friday:");
        stringBuilder.append(realmGet$friday());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{saturday:");
        stringBuilder.append(realmGet$saturday());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sunday:");
        stringBuilder.append(realmGet$sunday());
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
        DaysRealmProxy aDays = (DaysRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aDays.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDays.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aDays.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
