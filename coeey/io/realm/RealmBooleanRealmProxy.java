package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.RealmBoolean;
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

public class RealmBooleanRealmProxy extends RealmBoolean implements RealmObjectProxy, RealmBooleanRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RealmBooleanColumnInfo columnInfo;
    private ProxyState<RealmBoolean> proxyState;

    static final class RealmBooleanColumnInfo extends ColumnInfo {
        long valueIndex;

        RealmBooleanColumnInfo(OsSchemaInfo schemaInfo) {
            super(1);
            this.valueIndex = addColumnDetails("value", schemaInfo.getObjectSchemaInfo("RealmBoolean"));
        }

        RealmBooleanColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new RealmBooleanColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ((RealmBooleanColumnInfo) rawDst).valueIndex = ((RealmBooleanColumnInfo) rawSrc).valueIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("value");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    RealmBooleanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (RealmBooleanColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public boolean realmGet$value() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.valueIndex);
    }

    public void realmSet$value(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.valueIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.valueIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("RealmBoolean");
        builder.addPersistedProperty("value", RealmFieldType.BOOLEAN, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static RealmBooleanColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new RealmBooleanColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_RealmBoolean";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static RealmBoolean createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        RealmBoolean obj = (RealmBoolean) realm.createObjectInternal(RealmBoolean.class, true, Collections.emptyList());
        RealmBooleanRealmProxyInterface objProxy = obj;
        if (json.has("value")) {
            if (json.isNull("value")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'value' to null.");
            }
            objProxy.realmSet$value(json.getBoolean("value"));
        }
        return obj;
    }

    @TargetApi(11)
    public static RealmBoolean createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        RealmBoolean obj = new RealmBoolean();
        RealmBooleanRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            if (!reader.nextName().equals("value")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$value(reader.nextBoolean());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'value' to null.");
            }
        }
        reader.endObject();
        return (RealmBoolean) realm.copyToRealm(obj);
    }

    public static RealmBoolean copyOrUpdate(Realm realm, RealmBoolean object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (RealmBoolean) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static RealmBoolean copy(Realm realm, RealmBoolean newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (RealmBoolean) cachedRealmObject;
        }
        RealmBoolean realmObject = (RealmBoolean) realm.createObjectInternal(RealmBoolean.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.realmSet$value(newObject.realmGet$value());
        return realmObject;
    }

    public static long insert(Realm realm, RealmBoolean object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(RealmBoolean.class);
        long tableNativePtr = table.getNativePtr();
        RealmBooleanColumnInfo columnInfo = (RealmBooleanColumnInfo) realm.getSchema().getColumnInfo(RealmBoolean.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Table.nativeSetBoolean(tableNativePtr, columnInfo.valueIndex, rowIndex, object.realmGet$value(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(RealmBoolean.class);
        long tableNativePtr = table.getNativePtr();
        RealmBooleanColumnInfo columnInfo = (RealmBooleanColumnInfo) realm.getSchema().getColumnInfo(RealmBoolean.class);
        while (objects.hasNext()) {
            RealmBoolean object = (RealmBoolean) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.valueIndex, rowIndex, object.realmGet$value(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, RealmBoolean object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(RealmBoolean.class);
        long tableNativePtr = table.getNativePtr();
        RealmBooleanColumnInfo columnInfo = (RealmBooleanColumnInfo) realm.getSchema().getColumnInfo(RealmBoolean.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Table.nativeSetBoolean(tableNativePtr, columnInfo.valueIndex, rowIndex, object.realmGet$value(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(RealmBoolean.class);
        long tableNativePtr = table.getNativePtr();
        RealmBooleanColumnInfo columnInfo = (RealmBooleanColumnInfo) realm.getSchema().getColumnInfo(RealmBoolean.class);
        while (objects.hasNext()) {
            RealmBoolean object = (RealmBoolean) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.valueIndex, rowIndex, object.realmGet$value(), false);
                }
            }
        }
    }

    public static RealmBoolean createDetachedCopy(RealmBoolean realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        RealmBoolean unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new RealmBoolean();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (RealmBoolean) cachedObject.object;
        } else {
            unmanagedObject = (RealmBoolean) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        unmanagedObject.realmSet$value(realmObject.realmGet$value());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("RealmBoolean = proxy[");
        stringBuilder.append("{value:");
        stringBuilder.append(realmGet$value());
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
        RealmBooleanRealmProxy aRealmBoolean = (RealmBooleanRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aRealmBoolean.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aRealmBoolean.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aRealmBoolean.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
