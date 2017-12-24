package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.ActivityItem;
import com.facebook.share.internal.ShareConstants;
import io.realm.exceptions.RealmException;
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

public class ActivityItemRealmProxy extends ActivityItem implements RealmObjectProxy, ActivityItemRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ActivityItemColumnInfo columnInfo;
    private ProxyState<ActivityItem> proxyState;

    static final class ActivityItemColumnInfo extends ColumnInfo {
        long idIndex;
        long nameIndex;
        long parametersIndex;
        long timeStampIndex;

        ActivityItemColumnInfo(OsSchemaInfo schemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("ActivityItem");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.parametersIndex = addColumnDetails("parameters", objectSchemaInfo);
            this.timeStampIndex = addColumnDetails("timeStamp", objectSchemaInfo);
        }

        ActivityItemColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new ActivityItemColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ActivityItemColumnInfo src = (ActivityItemColumnInfo) rawSrc;
            ActivityItemColumnInfo dst = (ActivityItemColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.nameIndex = src.nameIndex;
            dst.parametersIndex = src.parametersIndex;
            dst.timeStampIndex = src.timeStampIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("name");
        fieldNames.add("parameters");
        fieldNames.add("timeStamp");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ActivityItemRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ActivityItemColumnInfo) context.getColumnInfo();
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
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
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

    public long realmGet$timeStamp() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.timeStampIndex);
    }

    public void realmSet$timeStamp(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.timeStampIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.timeStampIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("ActivityItem");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("parameters", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("timeStamp", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ActivityItemColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ActivityItemColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_ActivityItem";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static ActivityItem createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = Collections.emptyList();
        ActivityItem activityItem = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(ActivityItem.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(ActivityItem.class), false, Collections.emptyList());
                    activityItem = new ActivityItemRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (activityItem == null) {
            if (!json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            } else if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                activityItem = (ActivityItemRealmProxy) realm.createObjectInternal(ActivityItem.class, null, true, excludeFields);
            } else {
                ActivityItemRealmProxy obj = (ActivityItemRealmProxy) realm.createObjectInternal(ActivityItem.class, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID), true, excludeFields);
            }
        }
        ActivityItemRealmProxyInterface objProxy = activityItem;
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
        if (json.has("timeStamp")) {
            if (json.isNull("timeStamp")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timeStamp' to null.");
            }
            objProxy.realmSet$timeStamp(json.getLong("timeStamp"));
        }
        return activityItem;
    }

    @TargetApi(11)
    public static ActivityItem createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        ActivityItem obj = new ActivityItem();
        ActivityItemRealmProxyInterface objProxy = obj;
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
                jsonHasPrimaryKey = true;
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("parameters")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$parameters(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$parameters(null);
                }
            } else if (!name.equals("timeStamp")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$timeStamp(reader.nextLong());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'timeStamp' to null.");
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (ActivityItem) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static ActivityItem copyOrUpdate(Realm realm, ActivityItem object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        Throwable th;
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
            return (ActivityItem) cachedRealmObject;
        }
        ActivityItem update2;
        ActivityItem realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(ActivityItem.class);
            long pkColumnIndex = table.getPrimaryKey();
            String value = object.realmGet$id();
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, value);
            }
            if (rowIndex == -1) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(ActivityItem.class), false, Collections.emptyList());
                    ActivityItem realmObject2 = new ActivityItemRealmProxy();
                    try {
                        cache.put(object, (RealmObjectProxy) realmObject2);
                        objectContext.clear();
                        realmObject = realmObject2;
                    } catch (Throwable th2) {
                        th = th2;
                        realmObject = realmObject2;
                        objectContext.clear();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectContext.clear();
                    throw th;
                }
            }
        }
        if (canUpdate) {
            update2 = update(realm, realmObject, object, cache);
        } else {
            update2 = copy(realm, object, update, cache);
        }
        return update2;
    }

    public static ActivityItem copy(Realm realm, ActivityItem newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (ActivityItem) cachedRealmObject;
        }
        ActivityItem realmObject = (ActivityItem) realm.createObjectInternal(ActivityItem.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ActivityItemRealmProxyInterface realmObjectSource = newObject;
        ActivityItemRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$parameters(realmObjectSource.realmGet$parameters());
        realmObjectCopy.realmSet$timeStamp(realmObjectSource.realmGet$timeStamp());
        return realmObject;
    }

    public static long insert(Realm realm, ActivityItem object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(ActivityItem.class);
        long tableNativePtr = table.getNativePtr();
        ActivityItemColumnInfo columnInfo = (ActivityItemColumnInfo) realm.getSchema().getColumnInfo(ActivityItem.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = object.realmGet$id();
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == -1) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timeStampIndex, rowIndex, object.realmGet$timeStamp(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(ActivityItem.class);
        long tableNativePtr = table.getNativePtr();
        ActivityItemColumnInfo columnInfo = (ActivityItemColumnInfo) realm.getSchema().getColumnInfo(ActivityItem.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            ActivityItem object = (ActivityItem) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex;
                    String primaryKeyValue = object.realmGet$id();
                    if (primaryKeyValue == null) {
                        rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                    } else {
                        rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                    }
                    if (rowIndex == -1) {
                        rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
                    } else {
                        Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                    }
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.timeStampIndex, rowIndex, object.realmGet$timeStamp(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, ActivityItem object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(ActivityItem.class);
        long tableNativePtr = table.getNativePtr();
        ActivityItemColumnInfo columnInfo = (ActivityItemColumnInfo) realm.getSchema().getColumnInfo(ActivityItem.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = object.realmGet$id();
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == -1) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
        }
        cache.put(object, Long.valueOf(rowIndex));
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
        Table.nativeSetLong(tableNativePtr, columnInfo.timeStampIndex, rowIndex, object.realmGet$timeStamp(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(ActivityItem.class);
        long tableNativePtr = table.getNativePtr();
        ActivityItemColumnInfo columnInfo = (ActivityItemColumnInfo) realm.getSchema().getColumnInfo(ActivityItem.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            ActivityItem object = (ActivityItem) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex;
                    String primaryKeyValue = object.realmGet$id();
                    if (primaryKeyValue == null) {
                        rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                    } else {
                        rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                    }
                    if (rowIndex == -1) {
                        rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
                    }
                    cache.put(object, Long.valueOf(rowIndex));
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
                    Table.nativeSetLong(tableNativePtr, columnInfo.timeStampIndex, rowIndex, object.realmGet$timeStamp(), false);
                }
            }
        }
    }

    public static ActivityItem createDetachedCopy(ActivityItem realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        ActivityItem unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new ActivityItem();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (ActivityItem) cachedObject.object;
        } else {
            unmanagedObject = (ActivityItem) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ActivityItemRealmProxyInterface unmanagedCopy = unmanagedObject;
        ActivityItemRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$parameters(realmSource.realmGet$parameters());
        unmanagedCopy.realmSet$timeStamp(realmSource.realmGet$timeStamp());
        return unmanagedObject;
    }

    static ActivityItem update(Realm realm, ActivityItem realmObject, ActivityItem newObject, Map<RealmModel, RealmObjectProxy> map) {
        ActivityItemRealmProxyInterface realmObjectTarget = realmObject;
        ActivityItemRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$parameters(realmObjectSource.realmGet$parameters());
        realmObjectTarget.realmSet$timeStamp(realmObjectSource.realmGet$timeStamp());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("ActivityItem = proxy[");
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
        stringBuilder.append(",");
        stringBuilder.append("{timeStamp:");
        stringBuilder.append(realmGet$timeStamp());
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
        ActivityItemRealmProxy aActivityItem = (ActivityItemRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aActivityItem.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aActivityItem.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aActivityItem.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
