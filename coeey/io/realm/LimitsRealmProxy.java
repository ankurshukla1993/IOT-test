package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.Limits;
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

public class LimitsRealmProxy extends Limits implements RealmObjectProxy, LimitsRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private LimitsColumnInfo columnInfo;
    private ProxyState<Limits> proxyState;

    static final class LimitsColumnInfo extends ColumnInfo {
        long higherLimitIndex;
        long lowerLimitIndex;
        long tenantIdIndex;

        LimitsColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Limits");
            this.lowerLimitIndex = addColumnDetails("lowerLimit", objectSchemaInfo);
            this.higherLimitIndex = addColumnDetails("higherLimit", objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
        }

        LimitsColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new LimitsColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            LimitsColumnInfo src = (LimitsColumnInfo) rawSrc;
            LimitsColumnInfo dst = (LimitsColumnInfo) rawDst;
            dst.lowerLimitIndex = src.lowerLimitIndex;
            dst.higherLimitIndex = src.higherLimitIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("lowerLimit");
        fieldNames.add("higherLimit");
        fieldNames.add("tenantId");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    LimitsRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (LimitsColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public int realmGet$lowerLimit() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.lowerLimitIndex);
    }

    public void realmSet$lowerLimit(int value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.lowerLimitIndex, (long) value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.lowerLimitIndex, row.getIndex(), (long) value, true);
        }
    }

    public int realmGet$higherLimit() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.higherLimitIndex);
    }

    public void realmSet$higherLimit(int value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.higherLimitIndex, (long) value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.higherLimitIndex, row.getIndex(), (long) value, true);
        }
    }

    public String realmGet$tenantId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.tenantIdIndex);
    }

    public void realmSet$tenantId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.tenantIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.tenantIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.tenantIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.tenantIdIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Limits");
        builder.addPersistedProperty("lowerLimit", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("higherLimit", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static LimitsColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new LimitsColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Limits";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Limits createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Limits obj = (Limits) realm.createObjectInternal(Limits.class, true, Collections.emptyList());
        LimitsRealmProxyInterface objProxy = obj;
        if (json.has("lowerLimit")) {
            if (json.isNull("lowerLimit")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'lowerLimit' to null.");
            }
            objProxy.realmSet$lowerLimit(json.getInt("lowerLimit"));
        }
        if (json.has("higherLimit")) {
            if (json.isNull("higherLimit")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'higherLimit' to null.");
            }
            objProxy.realmSet$higherLimit(json.getInt("higherLimit"));
        }
        if (json.has("tenantId")) {
            if (json.isNull("tenantId")) {
                objProxy.realmSet$tenantId(null);
            } else {
                objProxy.realmSet$tenantId(json.getString("tenantId"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Limits createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Limits obj = new Limits();
        LimitsRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("lowerLimit")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$lowerLimit(reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'lowerLimit' to null.");
                }
            } else if (name.equals("higherLimit")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$higherLimit(reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'higherLimit' to null.");
                }
            } else if (!name.equals("tenantId")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$tenantId(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$tenantId(null);
            }
        }
        reader.endObject();
        return (Limits) realm.copyToRealm(obj);
    }

    public static Limits copyOrUpdate(Realm realm, Limits object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Limits) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Limits copy(Realm realm, Limits newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Limits) cachedRealmObject;
        }
        Limits realmObject = (Limits) realm.createObjectInternal(Limits.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        LimitsRealmProxyInterface realmObjectSource = newObject;
        LimitsRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$lowerLimit(realmObjectSource.realmGet$lowerLimit());
        realmObjectCopy.realmSet$higherLimit(realmObjectSource.realmGet$higherLimit());
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        return realmObject;
    }

    public static long insert(Realm realm, Limits object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Limits.class);
        long tableNativePtr = table.getNativePtr();
        LimitsColumnInfo columnInfo = (LimitsColumnInfo) realm.getSchema().getColumnInfo(Limits.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Table.nativeSetLong(tableNativePtr, columnInfo.lowerLimitIndex, rowIndex, (long) object.realmGet$lowerLimit(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.higherLimitIndex, rowIndex, (long) object.realmGet$higherLimit(), false);
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Limits.class);
        long tableNativePtr = table.getNativePtr();
        LimitsColumnInfo columnInfo = (LimitsColumnInfo) realm.getSchema().getColumnInfo(Limits.class);
        while (objects.hasNext()) {
            Limits object = (Limits) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Table.nativeSetLong(tableNativePtr, columnInfo.lowerLimitIndex, rowIndex, (long) object.realmGet$lowerLimit(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.higherLimitIndex, rowIndex, (long) object.realmGet$higherLimit(), false);
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Limits object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Limits.class);
        long tableNativePtr = table.getNativePtr();
        LimitsColumnInfo columnInfo = (LimitsColumnInfo) realm.getSchema().getColumnInfo(Limits.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Table.nativeSetLong(tableNativePtr, columnInfo.lowerLimitIndex, rowIndex, (long) object.realmGet$lowerLimit(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.higherLimitIndex, rowIndex, (long) object.realmGet$higherLimit(), false);
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Limits.class);
        long tableNativePtr = table.getNativePtr();
        LimitsColumnInfo columnInfo = (LimitsColumnInfo) realm.getSchema().getColumnInfo(Limits.class);
        while (objects.hasNext()) {
            Limits object = (Limits) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Table.nativeSetLong(tableNativePtr, columnInfo.lowerLimitIndex, rowIndex, (long) object.realmGet$lowerLimit(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.higherLimitIndex, rowIndex, (long) object.realmGet$higherLimit(), false);
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Limits createDetachedCopy(Limits realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Limits unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Limits();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Limits) cachedObject.object;
        } else {
            unmanagedObject = (Limits) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        LimitsRealmProxyInterface unmanagedCopy = unmanagedObject;
        LimitsRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$lowerLimit(realmSource.realmGet$lowerLimit());
        unmanagedCopy.realmSet$higherLimit(realmSource.realmGet$higherLimit());
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Limits = proxy[");
        stringBuilder.append("{lowerLimit:");
        stringBuilder.append(realmGet$lowerLimit());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{higherLimit:");
        stringBuilder.append(realmGet$higherLimit());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tenantId:");
        stringBuilder.append(realmGet$tenantId() != null ? realmGet$tenantId() : "null");
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
        LimitsRealmProxy aLimits = (LimitsRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aLimits.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aLimits.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aLimits.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
