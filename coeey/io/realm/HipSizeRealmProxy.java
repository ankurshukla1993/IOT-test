package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.HipSize;
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

public class HipSizeRealmProxy extends HipSize implements RealmObjectProxy, HipSizeRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private HipSizeColumnInfo columnInfo;
    private ProxyState<HipSize> proxyState;

    static final class HipSizeColumnInfo extends ColumnInfo {
        long unitIndex;
        long valueIndex;

        HipSizeColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("HipSize");
            this.valueIndex = addColumnDetails("value", objectSchemaInfo);
            this.unitIndex = addColumnDetails(AmProfile.GET_USER_UNIT_AM, objectSchemaInfo);
        }

        HipSizeColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new HipSizeColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            HipSizeColumnInfo src = (HipSizeColumnInfo) rawSrc;
            HipSizeColumnInfo dst = (HipSizeColumnInfo) rawDst;
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

    HipSizeRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (HipSizeColumnInfo) context.getColumnInfo();
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
        Builder builder = new Builder("HipSize");
        builder.addPersistedProperty("value", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(AmProfile.GET_USER_UNIT_AM, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static HipSizeColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new HipSizeColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_HipSize";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static HipSize createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        HipSize obj = (HipSize) realm.createObjectInternal(HipSize.class, true, Collections.emptyList());
        HipSizeRealmProxyInterface objProxy = obj;
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
    public static HipSize createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        HipSize obj = new HipSize();
        HipSizeRealmProxyInterface objProxy = obj;
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
        return (HipSize) realm.copyToRealm(obj);
    }

    public static HipSize copyOrUpdate(Realm realm, HipSize object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (HipSize) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static HipSize copy(Realm realm, HipSize newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (HipSize) cachedRealmObject;
        }
        HipSize realmObject = (HipSize) realm.createObjectInternal(HipSize.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        HipSizeRealmProxyInterface realmObjectSource = newObject;
        HipSizeRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$value(realmObjectSource.realmGet$value());
        realmObjectCopy.realmSet$unit(realmObjectSource.realmGet$unit());
        return realmObject;
    }

    public static long insert(Realm realm, HipSize object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(HipSize.class);
        long tableNativePtr = table.getNativePtr();
        HipSizeColumnInfo columnInfo = (HipSizeColumnInfo) realm.getSchema().getColumnInfo(HipSize.class);
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
        Table table = realm.getTable(HipSize.class);
        long tableNativePtr = table.getNativePtr();
        HipSizeColumnInfo columnInfo = (HipSizeColumnInfo) realm.getSchema().getColumnInfo(HipSize.class);
        while (objects.hasNext()) {
            HipSize object = (HipSize) objects.next();
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

    public static long insertOrUpdate(Realm realm, HipSize object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(HipSize.class);
        long tableNativePtr = table.getNativePtr();
        HipSizeColumnInfo columnInfo = (HipSizeColumnInfo) realm.getSchema().getColumnInfo(HipSize.class);
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
        Table table = realm.getTable(HipSize.class);
        long tableNativePtr = table.getNativePtr();
        HipSizeColumnInfo columnInfo = (HipSizeColumnInfo) realm.getSchema().getColumnInfo(HipSize.class);
        while (objects.hasNext()) {
            HipSize object = (HipSize) objects.next();
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

    public static HipSize createDetachedCopy(HipSize realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        HipSize unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new HipSize();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (HipSize) cachedObject.object;
        } else {
            unmanagedObject = (HipSize) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        HipSizeRealmProxyInterface unmanagedCopy = unmanagedObject;
        HipSizeRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$value(realmSource.realmGet$value());
        unmanagedCopy.realmSet$unit(realmSource.realmGet$unit());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("HipSize = proxy[");
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
        HipSizeRealmProxy aHipSize = (HipSizeRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aHipSize.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aHipSize.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aHipSize.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
