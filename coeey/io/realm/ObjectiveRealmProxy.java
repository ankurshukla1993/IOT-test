package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.Objective;
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

public class ObjectiveRealmProxy extends Objective implements RealmObjectProxy, ObjectiveRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ObjectiveColumnInfo columnInfo;
    private ProxyState<Objective> proxyState;

    static final class ObjectiveColumnInfo extends ColumnInfo {
        long noteIndex;
        long parameterIndex;

        ObjectiveColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Objective");
            this.noteIndex = addColumnDetails("note", objectSchemaInfo);
            this.parameterIndex = addColumnDetails("parameter", objectSchemaInfo);
        }

        ObjectiveColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new ObjectiveColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ObjectiveColumnInfo src = (ObjectiveColumnInfo) rawSrc;
            ObjectiveColumnInfo dst = (ObjectiveColumnInfo) rawDst;
            dst.noteIndex = src.noteIndex;
            dst.parameterIndex = src.parameterIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("note");
        fieldNames.add("parameter");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ObjectiveRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ObjectiveColumnInfo) context.getColumnInfo();
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

    public String realmGet$parameter() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.parameterIndex);
    }

    public void realmSet$parameter(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.parameterIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.parameterIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.parameterIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.parameterIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Objective");
        builder.addPersistedProperty("note", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("parameter", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ObjectiveColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ObjectiveColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Objective";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Objective createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Objective obj = (Objective) realm.createObjectInternal(Objective.class, true, Collections.emptyList());
        ObjectiveRealmProxyInterface objProxy = obj;
        if (json.has("note")) {
            if (json.isNull("note")) {
                objProxy.realmSet$note(null);
            } else {
                objProxy.realmSet$note(json.getString("note"));
            }
        }
        if (json.has("parameter")) {
            if (json.isNull("parameter")) {
                objProxy.realmSet$parameter(null);
            } else {
                objProxy.realmSet$parameter(json.getString("parameter"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Objective createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Objective obj = new Objective();
        ObjectiveRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("note")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$note(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$note(null);
                }
            } else if (!name.equals("parameter")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$parameter(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$parameter(null);
            }
        }
        reader.endObject();
        return (Objective) realm.copyToRealm(obj);
    }

    public static Objective copyOrUpdate(Realm realm, Objective object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Objective) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Objective copy(Realm realm, Objective newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Objective) cachedRealmObject;
        }
        Objective realmObject = (Objective) realm.createObjectInternal(Objective.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ObjectiveRealmProxyInterface realmObjectSource = newObject;
        ObjectiveRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$note(realmObjectSource.realmGet$note());
        realmObjectCopy.realmSet$parameter(realmObjectSource.realmGet$parameter());
        return realmObject;
    }

    public static long insert(Realm realm, Objective object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Objective.class);
        long tableNativePtr = table.getNativePtr();
        ObjectiveColumnInfo columnInfo = (ObjectiveColumnInfo) realm.getSchema().getColumnInfo(Objective.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$note = object.realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        }
        String realmGet$parameter = object.realmGet$parameter();
        if (realmGet$parameter == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.parameterIndex, rowIndex, realmGet$parameter, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Objective.class);
        long tableNativePtr = table.getNativePtr();
        ObjectiveColumnInfo columnInfo = (ObjectiveColumnInfo) realm.getSchema().getColumnInfo(Objective.class);
        while (objects.hasNext()) {
            Objective object = (Objective) objects.next();
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
                    String realmGet$parameter = object.realmGet$parameter();
                    if (realmGet$parameter != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parameterIndex, rowIndex, realmGet$parameter, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Objective object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Objective.class);
        long tableNativePtr = table.getNativePtr();
        ObjectiveColumnInfo columnInfo = (ObjectiveColumnInfo) realm.getSchema().getColumnInfo(Objective.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$note = object.realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
        }
        String realmGet$parameter = object.realmGet$parameter();
        if (realmGet$parameter != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parameterIndex, rowIndex, realmGet$parameter, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.parameterIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Objective.class);
        long tableNativePtr = table.getNativePtr();
        ObjectiveColumnInfo columnInfo = (ObjectiveColumnInfo) realm.getSchema().getColumnInfo(Objective.class);
        while (objects.hasNext()) {
            Objective object = (Objective) objects.next();
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
                    String realmGet$parameter = object.realmGet$parameter();
                    if (realmGet$parameter != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parameterIndex, rowIndex, realmGet$parameter, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.parameterIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Objective createDetachedCopy(Objective realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Objective unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Objective();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Objective) cachedObject.object;
        } else {
            unmanagedObject = (Objective) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ObjectiveRealmProxyInterface unmanagedCopy = unmanagedObject;
        ObjectiveRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$note(realmSource.realmGet$note());
        unmanagedCopy.realmSet$parameter(realmSource.realmGet$parameter());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Objective = proxy[");
        stringBuilder.append("{note:");
        stringBuilder.append(realmGet$note() != null ? realmGet$note() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parameter:");
        stringBuilder.append(realmGet$parameter() != null ? realmGet$parameter() : "null");
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
        ObjectiveRealmProxy aObjective = (ObjectiveRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aObjective.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aObjective.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aObjective.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
