package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.diet.time.BreakFast;
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

public class BreakFastRealmProxy extends BreakFast implements RealmObjectProxy, BreakFastRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private BreakFastColumnInfo columnInfo;
    private ProxyState<BreakFast> proxyState;

    static final class BreakFastColumnInfo extends ColumnInfo {
        long dietIdIndex;
        long nameIndex;

        BreakFastColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("BreakFast");
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.dietIdIndex = addColumnDetails("dietId", objectSchemaInfo);
        }

        BreakFastColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new BreakFastColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            BreakFastColumnInfo src = (BreakFastColumnInfo) rawSrc;
            BreakFastColumnInfo dst = (BreakFastColumnInfo) rawDst;
            dst.nameIndex = src.nameIndex;
            dst.dietIdIndex = src.dietIdIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("name");
        fieldNames.add("dietId");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    BreakFastRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (BreakFastColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$name() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.nameIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.nameIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.nameIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.nameIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$dietId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.dietIdIndex);
    }

    public void realmSet$dietId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.dietIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.dietIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.dietIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.dietIdIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("BreakFast");
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("dietId", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static BreakFastColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new BreakFastColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_BreakFast";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static BreakFast createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        BreakFast obj = (BreakFast) realm.createObjectInternal(BreakFast.class, true, Collections.emptyList());
        BreakFastRealmProxyInterface objProxy = obj;
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name(json.getString("name"));
            }
        }
        if (json.has("dietId")) {
            if (json.isNull("dietId")) {
                objProxy.realmSet$dietId(null);
            } else {
                objProxy.realmSet$dietId(json.getString("dietId"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static BreakFast createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        BreakFast obj = new BreakFast();
        BreakFastRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (!name.equals("dietId")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$dietId(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$dietId(null);
            }
        }
        reader.endObject();
        return (BreakFast) realm.copyToRealm(obj);
    }

    public static BreakFast copyOrUpdate(Realm realm, BreakFast object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (BreakFast) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static BreakFast copy(Realm realm, BreakFast newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (BreakFast) cachedRealmObject;
        }
        BreakFast realmObject = (BreakFast) realm.createObjectInternal(BreakFast.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        BreakFastRealmProxyInterface realmObjectSource = newObject;
        BreakFastRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$dietId(realmObjectSource.realmGet$dietId());
        return realmObject;
    }

    public static long insert(Realm realm, BreakFast object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(BreakFast.class);
        long tableNativePtr = table.getNativePtr();
        BreakFastColumnInfo columnInfo = (BreakFastColumnInfo) realm.getSchema().getColumnInfo(BreakFast.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$dietId = object.realmGet$dietId();
        if (realmGet$dietId == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.dietIdIndex, rowIndex, realmGet$dietId, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(BreakFast.class);
        long tableNativePtr = table.getNativePtr();
        BreakFastColumnInfo columnInfo = (BreakFastColumnInfo) realm.getSchema().getColumnInfo(BreakFast.class);
        while (objects.hasNext()) {
            BreakFast object = (BreakFast) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    String realmGet$dietId = object.realmGet$dietId();
                    if (realmGet$dietId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.dietIdIndex, rowIndex, realmGet$dietId, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, BreakFast object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(BreakFast.class);
        long tableNativePtr = table.getNativePtr();
        BreakFastColumnInfo columnInfo = (BreakFastColumnInfo) realm.getSchema().getColumnInfo(BreakFast.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$dietId = object.realmGet$dietId();
        if (realmGet$dietId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dietIdIndex, rowIndex, realmGet$dietId, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.dietIdIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(BreakFast.class);
        long tableNativePtr = table.getNativePtr();
        BreakFastColumnInfo columnInfo = (BreakFastColumnInfo) realm.getSchema().getColumnInfo(BreakFast.class);
        while (objects.hasNext()) {
            BreakFast object = (BreakFast) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                    }
                    String realmGet$dietId = object.realmGet$dietId();
                    if (realmGet$dietId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.dietIdIndex, rowIndex, realmGet$dietId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.dietIdIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static BreakFast createDetachedCopy(BreakFast realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        BreakFast unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new BreakFast();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (BreakFast) cachedObject.object;
        } else {
            unmanagedObject = (BreakFast) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        BreakFastRealmProxyInterface unmanagedCopy = unmanagedObject;
        BreakFastRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$dietId(realmSource.realmGet$dietId());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("BreakFast = proxy[");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dietId:");
        stringBuilder.append(realmGet$dietId() != null ? realmGet$dietId() : "null");
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
        BreakFastRealmProxy aBreakFast = (BreakFastRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aBreakFast.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aBreakFast.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aBreakFast.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
