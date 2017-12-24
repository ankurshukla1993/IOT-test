package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.HealthCondition;
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

public class HealthConditionRealmProxy extends HealthCondition implements RealmObjectProxy, HealthConditionRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private HealthConditionColumnInfo columnInfo;
    private ProxyState<HealthCondition> proxyState;

    static final class HealthConditionColumnInfo extends ColumnInfo {
        long noteIndex;

        HealthConditionColumnInfo(OsSchemaInfo schemaInfo) {
            super(1);
            this.noteIndex = addColumnDetails("note", schemaInfo.getObjectSchemaInfo("HealthCondition"));
        }

        HealthConditionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new HealthConditionColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ((HealthConditionColumnInfo) rawDst).noteIndex = ((HealthConditionColumnInfo) rawSrc).noteIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("note");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    HealthConditionRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (HealthConditionColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$note() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.noteIndex);
    }

    public void realmSet$note(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.noteIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.noteIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.noteIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.noteIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("HealthCondition");
        builder.addPersistedProperty("note", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static HealthConditionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new HealthConditionColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_HealthCondition";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static HealthCondition createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        HealthCondition obj = (HealthCondition) realm.createObjectInternal(HealthCondition.class, true, Collections.emptyList());
        HealthConditionRealmProxyInterface objProxy = obj;
        if (json.has("note")) {
            if (json.isNull("note")) {
                objProxy.realmSet$note(null);
            } else {
                objProxy.realmSet$note(json.getString("note"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static HealthCondition createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        HealthCondition obj = new HealthCondition();
        HealthConditionRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            if (!reader.nextName().equals("note")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$note(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$note(null);
            }
        }
        reader.endObject();
        return (HealthCondition) realm.copyToRealm(obj);
    }

    public static HealthCondition copyOrUpdate(Realm realm, HealthCondition object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (HealthCondition) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static HealthCondition copy(Realm realm, HealthCondition newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (HealthCondition) cachedRealmObject;
        }
        HealthCondition realmObject = (HealthCondition) realm.createObjectInternal(HealthCondition.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.realmSet$note(newObject.realmGet$note());
        return realmObject;
    }

    public static long insert(Realm realm, HealthCondition object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(HealthCondition.class);
        long tableNativePtr = table.getNativePtr();
        HealthConditionColumnInfo columnInfo = (HealthConditionColumnInfo) realm.getSchema().getColumnInfo(HealthCondition.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$note = object.realmGet$note();
        if (realmGet$note == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(HealthCondition.class);
        long tableNativePtr = table.getNativePtr();
        HealthConditionColumnInfo columnInfo = (HealthConditionColumnInfo) realm.getSchema().getColumnInfo(HealthCondition.class);
        while (objects.hasNext()) {
            HealthCondition object = (HealthCondition) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$note = object.realmGet$note();
                    if (realmGet$note != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, HealthCondition object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(HealthCondition.class);
        long tableNativePtr = table.getNativePtr();
        HealthConditionColumnInfo columnInfo = (HealthConditionColumnInfo) realm.getSchema().getColumnInfo(HealthCondition.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$note = object.realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(HealthCondition.class);
        long tableNativePtr = table.getNativePtr();
        HealthConditionColumnInfo columnInfo = (HealthConditionColumnInfo) realm.getSchema().getColumnInfo(HealthCondition.class);
        while (objects.hasNext()) {
            HealthCondition object = (HealthCondition) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$note = object.realmGet$note();
                    if (realmGet$note != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static HealthCondition createDetachedCopy(HealthCondition realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        HealthCondition unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new HealthCondition();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (HealthCondition) cachedObject.object;
        } else {
            unmanagedObject = (HealthCondition) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        unmanagedObject.realmSet$note(realmObject.realmGet$note());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("HealthCondition = proxy[");
        stringBuilder.append("{note:");
        stringBuilder.append(realmGet$note() != null ? realmGet$note() : "null");
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
        HealthConditionRealmProxy aHealthCondition = (HealthConditionRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aHealthCondition.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aHealthCondition.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aHealthCondition.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
