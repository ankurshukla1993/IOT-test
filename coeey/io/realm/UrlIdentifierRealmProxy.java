package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.UrlIdentifier;
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

public class UrlIdentifierRealmProxy extends UrlIdentifier implements RealmObjectProxy, UrlIdentifierRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private UrlIdentifierColumnInfo columnInfo;
    private ProxyState<UrlIdentifier> proxyState;

    static final class UrlIdentifierColumnInfo extends ColumnInfo {
        long urlIndex;

        UrlIdentifierColumnInfo(OsSchemaInfo schemaInfo) {
            super(1);
            this.urlIndex = addColumnDetails("url", schemaInfo.getObjectSchemaInfo("UrlIdentifier"));
        }

        UrlIdentifierColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new UrlIdentifierColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ((UrlIdentifierColumnInfo) rawDst).urlIndex = ((UrlIdentifierColumnInfo) rawSrc).urlIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("url");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    UrlIdentifierRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (UrlIdentifierColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$url() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.urlIndex);
    }

    public void realmSet$url(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.urlIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.urlIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.urlIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.urlIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("UrlIdentifier");
        builder.addPersistedProperty("url", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static UrlIdentifierColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new UrlIdentifierColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_UrlIdentifier";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static UrlIdentifier createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        UrlIdentifier obj = (UrlIdentifier) realm.createObjectInternal(UrlIdentifier.class, true, Collections.emptyList());
        UrlIdentifierRealmProxyInterface objProxy = obj;
        if (json.has("url")) {
            if (json.isNull("url")) {
                objProxy.realmSet$url(null);
            } else {
                objProxy.realmSet$url(json.getString("url"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static UrlIdentifier createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        UrlIdentifier obj = new UrlIdentifier();
        UrlIdentifierRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            if (!reader.nextName().equals("url")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$url(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$url(null);
            }
        }
        reader.endObject();
        return (UrlIdentifier) realm.copyToRealm(obj);
    }

    public static UrlIdentifier copyOrUpdate(Realm realm, UrlIdentifier object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (UrlIdentifier) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static UrlIdentifier copy(Realm realm, UrlIdentifier newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (UrlIdentifier) cachedRealmObject;
        }
        UrlIdentifier realmObject = (UrlIdentifier) realm.createObjectInternal(UrlIdentifier.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.realmSet$url(newObject.realmGet$url());
        return realmObject;
    }

    public static long insert(Realm realm, UrlIdentifier object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(UrlIdentifier.class);
        long tableNativePtr = table.getNativePtr();
        UrlIdentifierColumnInfo columnInfo = (UrlIdentifierColumnInfo) realm.getSchema().getColumnInfo(UrlIdentifier.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$url = object.realmGet$url();
        if (realmGet$url == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(UrlIdentifier.class);
        long tableNativePtr = table.getNativePtr();
        UrlIdentifierColumnInfo columnInfo = (UrlIdentifierColumnInfo) realm.getSchema().getColumnInfo(UrlIdentifier.class);
        while (objects.hasNext()) {
            UrlIdentifier object = (UrlIdentifier) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$url = object.realmGet$url();
                    if (realmGet$url != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, UrlIdentifier object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(UrlIdentifier.class);
        long tableNativePtr = table.getNativePtr();
        UrlIdentifierColumnInfo columnInfo = (UrlIdentifierColumnInfo) realm.getSchema().getColumnInfo(UrlIdentifier.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$url = object.realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.urlIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(UrlIdentifier.class);
        long tableNativePtr = table.getNativePtr();
        UrlIdentifierColumnInfo columnInfo = (UrlIdentifierColumnInfo) realm.getSchema().getColumnInfo(UrlIdentifier.class);
        while (objects.hasNext()) {
            UrlIdentifier object = (UrlIdentifier) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$url = object.realmGet$url();
                    if (realmGet$url != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.urlIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static UrlIdentifier createDetachedCopy(UrlIdentifier realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        UrlIdentifier unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new UrlIdentifier();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (UrlIdentifier) cachedObject.object;
        } else {
            unmanagedObject = (UrlIdentifier) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        unmanagedObject.realmSet$url(realmObject.realmGet$url());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("UrlIdentifier = proxy[");
        stringBuilder.append("{url:");
        stringBuilder.append(realmGet$url() != null ? realmGet$url() : "null");
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
        UrlIdentifierRealmProxy aUrlIdentifier = (UrlIdentifierRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aUrlIdentifier.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUrlIdentifier.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aUrlIdentifier.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
