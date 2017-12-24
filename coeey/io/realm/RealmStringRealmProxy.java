package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.RealmString;
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

public class RealmStringRealmProxy extends RealmString implements RealmObjectProxy, RealmStringRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RealmStringColumnInfo columnInfo;
    private ProxyState<RealmString> proxyState;

    static final class RealmStringColumnInfo extends ColumnInfo {
        long valIndex;

        RealmStringColumnInfo(OsSchemaInfo schemaInfo) {
            super(1);
            this.valIndex = addColumnDetails("val", schemaInfo.getObjectSchemaInfo("RealmString"));
        }

        RealmStringColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new RealmStringColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ((RealmStringColumnInfo) rawDst).valIndex = ((RealmStringColumnInfo) rawSrc).valIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("val");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    RealmStringRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (RealmStringColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$val() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.valIndex);
    }

    public void realmSet$val(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.valIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.valIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.valIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.valIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("RealmString");
        builder.addPersistedProperty("val", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static RealmStringColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new RealmStringColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_RealmString";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static RealmString createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        RealmString obj = (RealmString) realm.createObjectInternal(RealmString.class, true, Collections.emptyList());
        RealmStringRealmProxyInterface objProxy = obj;
        if (json.has("val")) {
            if (json.isNull("val")) {
                objProxy.realmSet$val(null);
            } else {
                objProxy.realmSet$val(json.getString("val"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static RealmString createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        RealmString obj = new RealmString();
        RealmStringRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            if (!reader.nextName().equals("val")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$val(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$val(null);
            }
        }
        reader.endObject();
        return (RealmString) realm.copyToRealm(obj);
    }

    public static RealmString copyOrUpdate(Realm realm, RealmString object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (RealmString) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static RealmString copy(Realm realm, RealmString newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (RealmString) cachedRealmObject;
        }
        RealmString realmObject = (RealmString) realm.createObjectInternal(RealmString.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.realmSet$val(newObject.realmGet$val());
        return realmObject;
    }

    public static long insert(Realm realm, RealmString object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(RealmString.class);
        long tableNativePtr = table.getNativePtr();
        RealmStringColumnInfo columnInfo = (RealmStringColumnInfo) realm.getSchema().getColumnInfo(RealmString.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$val = object.realmGet$val();
        if (realmGet$val == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.valIndex, rowIndex, realmGet$val, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(RealmString.class);
        long tableNativePtr = table.getNativePtr();
        RealmStringColumnInfo columnInfo = (RealmStringColumnInfo) realm.getSchema().getColumnInfo(RealmString.class);
        while (objects.hasNext()) {
            RealmString object = (RealmString) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$val = object.realmGet$val();
                    if (realmGet$val != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.valIndex, rowIndex, realmGet$val, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, RealmString object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(RealmString.class);
        long tableNativePtr = table.getNativePtr();
        RealmStringColumnInfo columnInfo = (RealmStringColumnInfo) realm.getSchema().getColumnInfo(RealmString.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$val = object.realmGet$val();
        if (realmGet$val != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.valIndex, rowIndex, realmGet$val, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.valIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(RealmString.class);
        long tableNativePtr = table.getNativePtr();
        RealmStringColumnInfo columnInfo = (RealmStringColumnInfo) realm.getSchema().getColumnInfo(RealmString.class);
        while (objects.hasNext()) {
            RealmString object = (RealmString) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$val = object.realmGet$val();
                    if (realmGet$val != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.valIndex, rowIndex, realmGet$val, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.valIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static RealmString createDetachedCopy(RealmString realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        RealmString unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new RealmString();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (RealmString) cachedObject.object;
        } else {
            unmanagedObject = (RealmString) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        unmanagedObject.realmSet$val(realmObject.realmGet$val());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("RealmString = proxy[");
        stringBuilder.append("{val:");
        stringBuilder.append(realmGet$val() != null ? realmGet$val() : "null");
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
        RealmStringRealmProxy aRealmString = (RealmStringRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aRealmString.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aRealmString.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aRealmString.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
