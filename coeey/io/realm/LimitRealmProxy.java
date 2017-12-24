package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Limit;
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

public class LimitRealmProxy extends Limit implements RealmObjectProxy, LimitRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private LimitColumnInfo columnInfo;
    private ProxyState<Limit> proxyState;

    static final class LimitColumnInfo extends ColumnInfo {
        long higherLimitIndex;
        long limitTypeIndex;
        long lowerLimitIndex;

        LimitColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Limit");
            this.lowerLimitIndex = addColumnDetails("lowerLimit", objectSchemaInfo);
            this.higherLimitIndex = addColumnDetails("higherLimit", objectSchemaInfo);
            this.limitTypeIndex = addColumnDetails("limitType", objectSchemaInfo);
        }

        LimitColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new LimitColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            LimitColumnInfo src = (LimitColumnInfo) rawSrc;
            LimitColumnInfo dst = (LimitColumnInfo) rawDst;
            dst.lowerLimitIndex = src.lowerLimitIndex;
            dst.higherLimitIndex = src.higherLimitIndex;
            dst.limitTypeIndex = src.limitTypeIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("lowerLimit");
        fieldNames.add("higherLimit");
        fieldNames.add("limitType");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    LimitRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (LimitColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$lowerLimit() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.lowerLimitIndex);
    }

    public void realmSet$lowerLimit(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.lowerLimitIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.lowerLimitIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.lowerLimitIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.lowerLimitIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$higherLimit() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.higherLimitIndex);
    }

    public void realmSet$higherLimit(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.higherLimitIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.higherLimitIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.higherLimitIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.higherLimitIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$limitType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.limitTypeIndex);
    }

    public void realmSet$limitType(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.limitTypeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.limitTypeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.limitTypeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.limitTypeIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Limit");
        builder.addPersistedProperty("lowerLimit", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("higherLimit", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("limitType", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static LimitColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new LimitColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Limit";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Limit createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Limit obj = (Limit) realm.createObjectInternal(Limit.class, true, Collections.emptyList());
        LimitRealmProxyInterface objProxy = obj;
        if (json.has("lowerLimit")) {
            if (json.isNull("lowerLimit")) {
                objProxy.realmSet$lowerLimit(null);
            } else {
                objProxy.realmSet$lowerLimit(json.getString("lowerLimit"));
            }
        }
        if (json.has("higherLimit")) {
            if (json.isNull("higherLimit")) {
                objProxy.realmSet$higherLimit(null);
            } else {
                objProxy.realmSet$higherLimit(json.getString("higherLimit"));
            }
        }
        if (json.has("limitType")) {
            if (json.isNull("limitType")) {
                objProxy.realmSet$limitType(null);
            } else {
                objProxy.realmSet$limitType(json.getString("limitType"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Limit createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Limit obj = new Limit();
        LimitRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("lowerLimit")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$lowerLimit(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$lowerLimit(null);
                }
            } else if (name.equals("higherLimit")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$higherLimit(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$higherLimit(null);
                }
            } else if (!name.equals("limitType")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$limitType(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$limitType(null);
            }
        }
        reader.endObject();
        return (Limit) realm.copyToRealm(obj);
    }

    public static Limit copyOrUpdate(Realm realm, Limit object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Limit) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Limit copy(Realm realm, Limit newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Limit) cachedRealmObject;
        }
        Limit realmObject = (Limit) realm.createObjectInternal(Limit.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        LimitRealmProxyInterface realmObjectSource = newObject;
        LimitRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$lowerLimit(realmObjectSource.realmGet$lowerLimit());
        realmObjectCopy.realmSet$higherLimit(realmObjectSource.realmGet$higherLimit());
        realmObjectCopy.realmSet$limitType(realmObjectSource.realmGet$limitType());
        return realmObject;
    }

    public static long insert(Realm realm, Limit object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Limit.class);
        long tableNativePtr = table.getNativePtr();
        LimitColumnInfo columnInfo = (LimitColumnInfo) realm.getSchema().getColumnInfo(Limit.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$lowerLimit = object.realmGet$lowerLimit();
        if (realmGet$lowerLimit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.lowerLimitIndex, rowIndex, realmGet$lowerLimit, false);
        }
        String realmGet$higherLimit = object.realmGet$higherLimit();
        if (realmGet$higherLimit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.higherLimitIndex, rowIndex, realmGet$higherLimit, false);
        }
        String realmGet$limitType = object.realmGet$limitType();
        if (realmGet$limitType == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.limitTypeIndex, rowIndex, realmGet$limitType, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Limit.class);
        long tableNativePtr = table.getNativePtr();
        LimitColumnInfo columnInfo = (LimitColumnInfo) realm.getSchema().getColumnInfo(Limit.class);
        while (objects.hasNext()) {
            Limit object = (Limit) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$lowerLimit = object.realmGet$lowerLimit();
                    if (realmGet$lowerLimit != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.lowerLimitIndex, rowIndex, realmGet$lowerLimit, false);
                    }
                    String realmGet$higherLimit = object.realmGet$higherLimit();
                    if (realmGet$higherLimit != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.higherLimitIndex, rowIndex, realmGet$higherLimit, false);
                    }
                    String realmGet$limitType = object.realmGet$limitType();
                    if (realmGet$limitType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.limitTypeIndex, rowIndex, realmGet$limitType, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Limit object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Limit.class);
        long tableNativePtr = table.getNativePtr();
        LimitColumnInfo columnInfo = (LimitColumnInfo) realm.getSchema().getColumnInfo(Limit.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$lowerLimit = object.realmGet$lowerLimit();
        if (realmGet$lowerLimit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.lowerLimitIndex, rowIndex, realmGet$lowerLimit, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.lowerLimitIndex, rowIndex, false);
        }
        String realmGet$higherLimit = object.realmGet$higherLimit();
        if (realmGet$higherLimit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.higherLimitIndex, rowIndex, realmGet$higherLimit, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.higherLimitIndex, rowIndex, false);
        }
        String realmGet$limitType = object.realmGet$limitType();
        if (realmGet$limitType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.limitTypeIndex, rowIndex, realmGet$limitType, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.limitTypeIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Limit.class);
        long tableNativePtr = table.getNativePtr();
        LimitColumnInfo columnInfo = (LimitColumnInfo) realm.getSchema().getColumnInfo(Limit.class);
        while (objects.hasNext()) {
            Limit object = (Limit) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$lowerLimit = object.realmGet$lowerLimit();
                    if (realmGet$lowerLimit != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.lowerLimitIndex, rowIndex, realmGet$lowerLimit, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.lowerLimitIndex, rowIndex, false);
                    }
                    String realmGet$higherLimit = object.realmGet$higherLimit();
                    if (realmGet$higherLimit != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.higherLimitIndex, rowIndex, realmGet$higherLimit, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.higherLimitIndex, rowIndex, false);
                    }
                    String realmGet$limitType = object.realmGet$limitType();
                    if (realmGet$limitType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.limitTypeIndex, rowIndex, realmGet$limitType, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.limitTypeIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Limit createDetachedCopy(Limit realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Limit unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Limit();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Limit) cachedObject.object;
        } else {
            unmanagedObject = (Limit) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        LimitRealmProxyInterface unmanagedCopy = unmanagedObject;
        LimitRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$lowerLimit(realmSource.realmGet$lowerLimit());
        unmanagedCopy.realmSet$higherLimit(realmSource.realmGet$higherLimit());
        unmanagedCopy.realmSet$limitType(realmSource.realmGet$limitType());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Limit = proxy[");
        stringBuilder.append("{lowerLimit:");
        stringBuilder.append(realmGet$lowerLimit() != null ? realmGet$lowerLimit() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{higherLimit:");
        stringBuilder.append(realmGet$higherLimit() != null ? realmGet$higherLimit() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{limitType:");
        stringBuilder.append(realmGet$limitType() != null ? realmGet$limitType() : "null");
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
        LimitRealmProxy aLimit = (LimitRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aLimit.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aLimit.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aLimit.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
