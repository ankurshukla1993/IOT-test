package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.ActivityFeature;
import com.facebook.share.internal.ShareConstants;
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

public class ActivityFeatureRealmProxy extends ActivityFeature implements RealmObjectProxy, ActivityFeatureRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ActivityFeatureColumnInfo columnInfo;
    private ProxyState<ActivityFeature> proxyState;

    static final class ActivityFeatureColumnInfo extends ColumnInfo {
        long idIndex;
        long nameIndex;
        long parametersIndex;

        ActivityFeatureColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("ActivityFeature");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.parametersIndex = addColumnDetails("parameters", objectSchemaInfo);
        }

        ActivityFeatureColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new ActivityFeatureColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ActivityFeatureColumnInfo src = (ActivityFeatureColumnInfo) rawSrc;
            ActivityFeatureColumnInfo dst = (ActivityFeatureColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.nameIndex = src.nameIndex;
            dst.parametersIndex = src.parametersIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("name");
        fieldNames.add("parameters");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ActivityFeatureRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ActivityFeatureColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idIndex);
    }

    public void realmSet$id(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.idIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.idIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.idIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.idIndex, row.getIndex(), value, true);
            }
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

    public String realmGet$parameters() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.parametersIndex);
    }

    public void realmSet$parameters(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.parametersIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.parametersIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.parametersIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.parametersIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("ActivityFeature");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("parameters", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ActivityFeatureColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ActivityFeatureColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_ActivityFeature";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static ActivityFeature createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        ActivityFeature obj = (ActivityFeature) realm.createObjectInternal(ActivityFeature.class, true, Collections.emptyList());
        ActivityFeatureRealmProxyInterface objProxy = obj;
        if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                objProxy.realmSet$id(null);
            } else {
                objProxy.realmSet$id(json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name(json.getString("name"));
            }
        }
        if (json.has("parameters")) {
            if (json.isNull("parameters")) {
                objProxy.realmSet$parameters(null);
            } else {
                objProxy.realmSet$parameters(json.getString("parameters"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static ActivityFeature createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        ActivityFeature obj = new ActivityFeature();
        ActivityFeatureRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (!name.equals("parameters")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$parameters(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$parameters(null);
            }
        }
        reader.endObject();
        return (ActivityFeature) realm.copyToRealm(obj);
    }

    public static ActivityFeature copyOrUpdate(Realm realm, ActivityFeature object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (ActivityFeature) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static ActivityFeature copy(Realm realm, ActivityFeature newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (ActivityFeature) cachedRealmObject;
        }
        ActivityFeature realmObject = (ActivityFeature) realm.createObjectInternal(ActivityFeature.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ActivityFeatureRealmProxyInterface realmObjectSource = newObject;
        ActivityFeatureRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$id(realmObjectSource.realmGet$id());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$parameters(realmObjectSource.realmGet$parameters());
        return realmObject;
    }

    public static long insert(Realm realm, ActivityFeature object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(ActivityFeature.class);
        long tableNativePtr = table.getNativePtr();
        ActivityFeatureColumnInfo columnInfo = (ActivityFeatureColumnInfo) realm.getSchema().getColumnInfo(ActivityFeature.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$id = object.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        }
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(ActivityFeature.class);
        long tableNativePtr = table.getNativePtr();
        ActivityFeatureColumnInfo columnInfo = (ActivityFeatureColumnInfo) realm.getSchema().getColumnInfo(ActivityFeature.class);
        while (objects.hasNext()) {
            ActivityFeature object = (ActivityFeature) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$id = object.realmGet$id();
                    if (realmGet$id != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                    }
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, ActivityFeature object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(ActivityFeature.class);
        long tableNativePtr = table.getNativePtr();
        ActivityFeatureColumnInfo columnInfo = (ActivityFeatureColumnInfo) realm.getSchema().getColumnInfo(ActivityFeature.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$id = object.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
        }
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(ActivityFeature.class);
        long tableNativePtr = table.getNativePtr();
        ActivityFeatureColumnInfo columnInfo = (ActivityFeatureColumnInfo) realm.getSchema().getColumnInfo(ActivityFeature.class);
        while (objects.hasNext()) {
            ActivityFeature object = (ActivityFeature) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$id = object.realmGet$id();
                    if (realmGet$id != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
                    }
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                    }
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static ActivityFeature createDetachedCopy(ActivityFeature realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        ActivityFeature unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new ActivityFeature();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (ActivityFeature) cachedObject.object;
        } else {
            unmanagedObject = (ActivityFeature) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ActivityFeatureRealmProxyInterface unmanagedCopy = unmanagedObject;
        ActivityFeatureRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$parameters(realmSource.realmGet$parameters());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("ActivityFeature = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parameters:");
        stringBuilder.append(realmGet$parameters() != null ? realmGet$parameters() : "null");
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
        ActivityFeatureRealmProxy aActivityFeature = (ActivityFeatureRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aActivityFeature.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aActivityFeature.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aActivityFeature.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
