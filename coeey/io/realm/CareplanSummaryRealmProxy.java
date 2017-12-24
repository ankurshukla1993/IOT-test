package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.CareplanSummary;
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

public class CareplanSummaryRealmProxy extends CareplanSummary implements RealmObjectProxy, CareplanSummaryRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private CareplanSummaryColumnInfo columnInfo;
    private ProxyState<CareplanSummary> proxyState;

    static final class CareplanSummaryColumnInfo extends ColumnInfo {
        long itemTypeIndex;
        long messageIndex;
        long rateIndicatorIndex;

        CareplanSummaryColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("CareplanSummary");
            this.messageIndex = addColumnDetails("message", objectSchemaInfo);
            this.rateIndicatorIndex = addColumnDetails("rateIndicator", objectSchemaInfo);
            this.itemTypeIndex = addColumnDetails("itemType", objectSchemaInfo);
        }

        CareplanSummaryColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new CareplanSummaryColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            CareplanSummaryColumnInfo src = (CareplanSummaryColumnInfo) rawSrc;
            CareplanSummaryColumnInfo dst = (CareplanSummaryColumnInfo) rawDst;
            dst.messageIndex = src.messageIndex;
            dst.rateIndicatorIndex = src.rateIndicatorIndex;
            dst.itemTypeIndex = src.itemTypeIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("message");
        fieldNames.add("rateIndicator");
        fieldNames.add("itemType");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CareplanSummaryRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (CareplanSummaryColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$message() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.messageIndex);
    }

    public void realmSet$message(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.messageIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.messageIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.messageIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.messageIndex, row.getIndex(), value, true);
            }
        }
    }

    public double realmGet$rateIndicator() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getDouble(this.columnInfo.rateIndicatorIndex);
    }

    public void realmSet$rateIndicator(double value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setDouble(this.columnInfo.rateIndicatorIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setDouble(this.columnInfo.rateIndicatorIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$itemType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.itemTypeIndex);
    }

    public void realmSet$itemType(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.itemTypeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.itemTypeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.itemTypeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.itemTypeIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("CareplanSummary");
        builder.addPersistedProperty("message", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("rateIndicator", RealmFieldType.DOUBLE, false, false, true);
        builder.addPersistedProperty("itemType", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CareplanSummaryColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new CareplanSummaryColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_CareplanSummary";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static CareplanSummary createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        CareplanSummary obj = (CareplanSummary) realm.createObjectInternal(CareplanSummary.class, true, Collections.emptyList());
        CareplanSummaryRealmProxyInterface objProxy = obj;
        if (json.has("message")) {
            if (json.isNull("message")) {
                objProxy.realmSet$message(null);
            } else {
                objProxy.realmSet$message(json.getString("message"));
            }
        }
        if (json.has("rateIndicator")) {
            if (json.isNull("rateIndicator")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'rateIndicator' to null.");
            }
            objProxy.realmSet$rateIndicator(json.getDouble("rateIndicator"));
        }
        if (json.has("itemType")) {
            if (json.isNull("itemType")) {
                objProxy.realmSet$itemType(null);
            } else {
                objProxy.realmSet$itemType(json.getString("itemType"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static CareplanSummary createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        CareplanSummary obj = new CareplanSummary();
        CareplanSummaryRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("message")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$message(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$message(null);
                }
            } else if (name.equals("rateIndicator")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$rateIndicator(reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'rateIndicator' to null.");
                }
            } else if (!name.equals("itemType")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$itemType(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$itemType(null);
            }
        }
        reader.endObject();
        return (CareplanSummary) realm.copyToRealm(obj);
    }

    public static CareplanSummary copyOrUpdate(Realm realm, CareplanSummary object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (CareplanSummary) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static CareplanSummary copy(Realm realm, CareplanSummary newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (CareplanSummary) cachedRealmObject;
        }
        CareplanSummary realmObject = (CareplanSummary) realm.createObjectInternal(CareplanSummary.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        CareplanSummaryRealmProxyInterface realmObjectSource = newObject;
        CareplanSummaryRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$message(realmObjectSource.realmGet$message());
        realmObjectCopy.realmSet$rateIndicator(realmObjectSource.realmGet$rateIndicator());
        realmObjectCopy.realmSet$itemType(realmObjectSource.realmGet$itemType());
        return realmObject;
    }

    public static long insert(Realm realm, CareplanSummary object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(CareplanSummary.class);
        long tableNativePtr = table.getNativePtr();
        CareplanSummaryColumnInfo columnInfo = (CareplanSummaryColumnInfo) realm.getSchema().getColumnInfo(CareplanSummary.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$message = object.realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.rateIndicatorIndex, rowIndex, object.realmGet$rateIndicator(), false);
        String realmGet$itemType = object.realmGet$itemType();
        if (realmGet$itemType == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.itemTypeIndex, rowIndex, realmGet$itemType, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(CareplanSummary.class);
        long tableNativePtr = table.getNativePtr();
        CareplanSummaryColumnInfo columnInfo = (CareplanSummaryColumnInfo) realm.getSchema().getColumnInfo(CareplanSummary.class);
        while (objects.hasNext()) {
            CareplanSummary object = (CareplanSummary) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$message = object.realmGet$message();
                    if (realmGet$message != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
                    }
                    Table.nativeSetDouble(tableNativePtr, columnInfo.rateIndicatorIndex, rowIndex, object.realmGet$rateIndicator(), false);
                    String realmGet$itemType = object.realmGet$itemType();
                    if (realmGet$itemType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.itemTypeIndex, rowIndex, realmGet$itemType, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, CareplanSummary object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(CareplanSummary.class);
        long tableNativePtr = table.getNativePtr();
        CareplanSummaryColumnInfo columnInfo = (CareplanSummaryColumnInfo) realm.getSchema().getColumnInfo(CareplanSummary.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$message = object.realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.messageIndex, rowIndex, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.rateIndicatorIndex, rowIndex, object.realmGet$rateIndicator(), false);
        String realmGet$itemType = object.realmGet$itemType();
        if (realmGet$itemType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.itemTypeIndex, rowIndex, realmGet$itemType, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.itemTypeIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(CareplanSummary.class);
        long tableNativePtr = table.getNativePtr();
        CareplanSummaryColumnInfo columnInfo = (CareplanSummaryColumnInfo) realm.getSchema().getColumnInfo(CareplanSummary.class);
        while (objects.hasNext()) {
            CareplanSummary object = (CareplanSummary) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$message = object.realmGet$message();
                    if (realmGet$message != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.messageIndex, rowIndex, false);
                    }
                    Table.nativeSetDouble(tableNativePtr, columnInfo.rateIndicatorIndex, rowIndex, object.realmGet$rateIndicator(), false);
                    String realmGet$itemType = object.realmGet$itemType();
                    if (realmGet$itemType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.itemTypeIndex, rowIndex, realmGet$itemType, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.itemTypeIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static CareplanSummary createDetachedCopy(CareplanSummary realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CareplanSummary unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new CareplanSummary();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (CareplanSummary) cachedObject.object;
        } else {
            unmanagedObject = (CareplanSummary) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        CareplanSummaryRealmProxyInterface unmanagedCopy = unmanagedObject;
        CareplanSummaryRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$message(realmSource.realmGet$message());
        unmanagedCopy.realmSet$rateIndicator(realmSource.realmGet$rateIndicator());
        unmanagedCopy.realmSet$itemType(realmSource.realmGet$itemType());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("CareplanSummary = proxy[");
        stringBuilder.append("{message:");
        stringBuilder.append(realmGet$message() != null ? realmGet$message() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{rateIndicator:");
        stringBuilder.append(realmGet$rateIndicator());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemType:");
        stringBuilder.append(realmGet$itemType() != null ? realmGet$itemType() : "null");
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
        CareplanSummaryRealmProxy aCareplanSummary = (CareplanSummaryRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aCareplanSummary.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCareplanSummary.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aCareplanSummary.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
