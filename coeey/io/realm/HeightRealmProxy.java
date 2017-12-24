package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Height;
import com.ihealth.communication.control.AmProfile;
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

public class HeightRealmProxy extends Height implements RealmObjectProxy, HeightRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private HeightColumnInfo columnInfo;
    private ProxyState<Height> proxyState;

    static final class HeightColumnInfo extends ColumnInfo {
        long unitIndex;
        long valueIndex;

        HeightColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Height");
            this.valueIndex = addColumnDetails("value", objectSchemaInfo);
            this.unitIndex = addColumnDetails(AmProfile.GET_USER_UNIT_AM, objectSchemaInfo);
        }

        HeightColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new HeightColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            HeightColumnInfo src = (HeightColumnInfo) rawSrc;
            HeightColumnInfo dst = (HeightColumnInfo) rawDst;
            dst.valueIndex = src.valueIndex;
            dst.unitIndex = src.unitIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("value");
        fieldNames.add(AmProfile.GET_USER_UNIT_AM);
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    HeightRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (HeightColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$value() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.valueIndex);
    }

    public void realmSet$value(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.valueIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.valueIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.valueIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.valueIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$unit() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.unitIndex);
    }

    public void realmSet$unit(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.unitIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.unitIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.unitIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.unitIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Height");
        builder.addPersistedProperty("value", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(AmProfile.GET_USER_UNIT_AM, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static HeightColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new HeightColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Height";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Height createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Height obj = (Height) realm.createObjectInternal(Height.class, true, Collections.emptyList());
        HeightRealmProxyInterface objProxy = obj;
        if (json.has("value")) {
            if (json.isNull("value")) {
                objProxy.realmSet$value(null);
            } else {
                objProxy.realmSet$value(json.getString("value"));
            }
        }
        if (json.has(AmProfile.GET_USER_UNIT_AM)) {
            if (json.isNull(AmProfile.GET_USER_UNIT_AM)) {
                objProxy.realmSet$unit(null);
            } else {
                objProxy.realmSet$unit(json.getString(AmProfile.GET_USER_UNIT_AM));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Height createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Height obj = new Height();
        HeightRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("value")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$value(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$value(null);
                }
            } else if (!name.equals(AmProfile.GET_USER_UNIT_AM)) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$unit(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$unit(null);
            }
        }
        reader.endObject();
        return (Height) realm.copyToRealm(obj);
    }

    public static Height copyOrUpdate(Realm realm, Height object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Height) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Height copy(Realm realm, Height newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Height) cachedRealmObject;
        }
        Height realmObject = (Height) realm.createObjectInternal(Height.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        HeightRealmProxyInterface realmObjectSource = newObject;
        HeightRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$value(realmObjectSource.realmGet$value());
        realmObjectCopy.realmSet$unit(realmObjectSource.realmGet$unit());
        return realmObject;
    }

    public static long insert(Realm realm, Height object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Height.class);
        long tableNativePtr = table.getNativePtr();
        HeightColumnInfo columnInfo = (HeightColumnInfo) realm.getSchema().getColumnInfo(Height.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$value = object.realmGet$value();
        if (realmGet$value != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
        }
        String realmGet$unit = object.realmGet$unit();
        if (realmGet$unit == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.unitIndex, rowIndex, realmGet$unit, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Height.class);
        long tableNativePtr = table.getNativePtr();
        HeightColumnInfo columnInfo = (HeightColumnInfo) realm.getSchema().getColumnInfo(Height.class);
        while (objects.hasNext()) {
            Height object = (Height) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$value = object.realmGet$value();
                    if (realmGet$value != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
                    }
                    String realmGet$unit = object.realmGet$unit();
                    if (realmGet$unit != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.unitIndex, rowIndex, realmGet$unit, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Height object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Height.class);
        long tableNativePtr = table.getNativePtr();
        HeightColumnInfo columnInfo = (HeightColumnInfo) realm.getSchema().getColumnInfo(Height.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$value = object.realmGet$value();
        if (realmGet$value != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.valueIndex, rowIndex, false);
        }
        String realmGet$unit = object.realmGet$unit();
        if (realmGet$unit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.unitIndex, rowIndex, realmGet$unit, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.unitIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Height.class);
        long tableNativePtr = table.getNativePtr();
        HeightColumnInfo columnInfo = (HeightColumnInfo) realm.getSchema().getColumnInfo(Height.class);
        while (objects.hasNext()) {
            Height object = (Height) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$value = object.realmGet$value();
                    if (realmGet$value != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.valueIndex, rowIndex, false);
                    }
                    String realmGet$unit = object.realmGet$unit();
                    if (realmGet$unit != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.unitIndex, rowIndex, realmGet$unit, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.unitIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Height createDetachedCopy(Height realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Height unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Height();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Height) cachedObject.object;
        } else {
            unmanagedObject = (Height) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        HeightRealmProxyInterface unmanagedCopy = unmanagedObject;
        HeightRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$value(realmSource.realmGet$value());
        unmanagedCopy.realmSet$unit(realmSource.realmGet$unit());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Height = proxy[");
        stringBuilder.append("{value:");
        stringBuilder.append(realmGet$value() != null ? realmGet$value() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{unit:");
        stringBuilder.append(realmGet$unit() != null ? realmGet$unit() : "null");
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
        HeightRealmProxy aHeight = (HeightRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aHeight.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aHeight.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aHeight.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
