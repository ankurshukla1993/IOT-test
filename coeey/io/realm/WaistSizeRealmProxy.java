package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.WaistSize;
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

public class WaistSizeRealmProxy extends WaistSize implements RealmObjectProxy, WaistSizeRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private WaistSizeColumnInfo columnInfo;
    private ProxyState<WaistSize> proxyState;

    static final class WaistSizeColumnInfo extends ColumnInfo {
        long unitIndex;
        long valueIndex;

        WaistSizeColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("WaistSize");
            this.valueIndex = addColumnDetails("value", objectSchemaInfo);
            this.unitIndex = addColumnDetails(AmProfile.GET_USER_UNIT_AM, objectSchemaInfo);
        }

        WaistSizeColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new WaistSizeColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            WaistSizeColumnInfo src = (WaistSizeColumnInfo) rawSrc;
            WaistSizeColumnInfo dst = (WaistSizeColumnInfo) rawDst;
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

    WaistSizeRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (WaistSizeColumnInfo) context.getColumnInfo();
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
        Builder builder = new Builder("WaistSize");
        builder.addPersistedProperty("value", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(AmProfile.GET_USER_UNIT_AM, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static WaistSizeColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new WaistSizeColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_WaistSize";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static WaistSize createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        WaistSize obj = (WaistSize) realm.createObjectInternal(WaistSize.class, true, Collections.emptyList());
        WaistSizeRealmProxyInterface objProxy = obj;
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
    public static WaistSize createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        WaistSize obj = new WaistSize();
        WaistSizeRealmProxyInterface objProxy = obj;
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
        return (WaistSize) realm.copyToRealm(obj);
    }

    public static WaistSize copyOrUpdate(Realm realm, WaistSize object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (WaistSize) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static WaistSize copy(Realm realm, WaistSize newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (WaistSize) cachedRealmObject;
        }
        WaistSize realmObject = (WaistSize) realm.createObjectInternal(WaistSize.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        WaistSizeRealmProxyInterface realmObjectSource = newObject;
        WaistSizeRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$value(realmObjectSource.realmGet$value());
        realmObjectCopy.realmSet$unit(realmObjectSource.realmGet$unit());
        return realmObject;
    }

    public static long insert(Realm realm, WaistSize object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(WaistSize.class);
        long tableNativePtr = table.getNativePtr();
        WaistSizeColumnInfo columnInfo = (WaistSizeColumnInfo) realm.getSchema().getColumnInfo(WaistSize.class);
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
        Table table = realm.getTable(WaistSize.class);
        long tableNativePtr = table.getNativePtr();
        WaistSizeColumnInfo columnInfo = (WaistSizeColumnInfo) realm.getSchema().getColumnInfo(WaistSize.class);
        while (objects.hasNext()) {
            WaistSize object = (WaistSize) objects.next();
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

    public static long insertOrUpdate(Realm realm, WaistSize object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(WaistSize.class);
        long tableNativePtr = table.getNativePtr();
        WaistSizeColumnInfo columnInfo = (WaistSizeColumnInfo) realm.getSchema().getColumnInfo(WaistSize.class);
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
        Table table = realm.getTable(WaistSize.class);
        long tableNativePtr = table.getNativePtr();
        WaistSizeColumnInfo columnInfo = (WaistSizeColumnInfo) realm.getSchema().getColumnInfo(WaistSize.class);
        while (objects.hasNext()) {
            WaistSize object = (WaistSize) objects.next();
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

    public static WaistSize createDetachedCopy(WaistSize realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        WaistSize unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new WaistSize();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (WaistSize) cachedObject.object;
        } else {
            unmanagedObject = (WaistSize) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        WaistSizeRealmProxyInterface unmanagedCopy = unmanagedObject;
        WaistSizeRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$value(realmSource.realmGet$value());
        unmanagedCopy.realmSet$unit(realmSource.realmGet$unit());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("WaistSize = proxy[");
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
        WaistSizeRealmProxy aWaistSize = (WaistSizeRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aWaistSize.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aWaistSize.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aWaistSize.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
