package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.CarePlanReminder;
import com.cooey.common.vo.careplan.Todo;
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

public class TodoRealmProxy extends Todo implements RealmObjectProxy, TodoRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TodoColumnInfo columnInfo;
    private ProxyState<Todo> proxyState;

    static final class TodoColumnInfo extends ColumnInfo {
        long carePlanReminderIndex;
        long noteIndex;

        TodoColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Todo");
            this.noteIndex = addColumnDetails("note", objectSchemaInfo);
            this.carePlanReminderIndex = addColumnDetails("carePlanReminder", objectSchemaInfo);
        }

        TodoColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new TodoColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            TodoColumnInfo src = (TodoColumnInfo) rawSrc;
            TodoColumnInfo dst = (TodoColumnInfo) rawDst;
            dst.noteIndex = src.noteIndex;
            dst.carePlanReminderIndex = src.carePlanReminderIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("note");
        fieldNames.add("carePlanReminder");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TodoRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TodoColumnInfo) context.getColumnInfo();
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

    public CarePlanReminder realmGet$carePlanReminder() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.carePlanReminderIndex)) {
            return null;
        }
        return (CarePlanReminder) this.proxyState.getRealm$realm().get(CarePlanReminder.class, this.proxyState.getRow$realm().getLink(this.columnInfo.carePlanReminderIndex), false, Collections.emptyList());
    }

    public void realmSet$carePlanReminder(CarePlanReminder value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.carePlanReminderIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.carePlanReminderIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("carePlanReminder")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (CarePlanReminder) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.carePlanReminderIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.carePlanReminderIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Todo");
        builder.addPersistedProperty("note", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("carePlanReminder", RealmFieldType.OBJECT, "CarePlanReminder");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TodoColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new TodoColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Todo";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Todo createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        if (json.has("carePlanReminder")) {
            excludeFields.add("carePlanReminder");
        }
        Todo obj = (Todo) realm.createObjectInternal(Todo.class, true, excludeFields);
        TodoRealmProxyInterface objProxy = obj;
        if (json.has("note")) {
            if (json.isNull("note")) {
                objProxy.realmSet$note(null);
            } else {
                objProxy.realmSet$note(json.getString("note"));
            }
        }
        if (json.has("carePlanReminder")) {
            if (json.isNull("carePlanReminder")) {
                objProxy.realmSet$carePlanReminder(null);
            } else {
                objProxy.realmSet$carePlanReminder(CarePlanReminderRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("carePlanReminder"), update));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Todo createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Todo obj = new Todo();
        TodoRealmProxyInterface objProxy = obj;
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
            } else if (!name.equals("carePlanReminder")) {
                reader.skipValue();
            } else if (reader.peek() == JsonToken.NULL) {
                reader.skipValue();
                objProxy.realmSet$carePlanReminder(null);
            } else {
                objProxy.realmSet$carePlanReminder(CarePlanReminderRealmProxy.createUsingJsonStream(realm, reader));
            }
        }
        reader.endObject();
        return (Todo) realm.copyToRealm(obj);
    }

    public static Todo copyOrUpdate(Realm realm, Todo object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Todo) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Todo copy(Realm realm, Todo newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Todo) cachedRealmObject;
        }
        Todo realmObject = (Todo) realm.createObjectInternal(Todo.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        TodoRealmProxyInterface realmObjectSource = newObject;
        TodoRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$note(realmObjectSource.realmGet$note());
        CarePlanReminder carePlanReminderObj = realmObjectSource.realmGet$carePlanReminder();
        if (carePlanReminderObj == null) {
            realmObjectCopy.realmSet$carePlanReminder(null);
        } else {
            CarePlanReminder cachecarePlanReminder = (CarePlanReminder) cache.get(carePlanReminderObj);
            if (cachecarePlanReminder != null) {
                realmObjectCopy.realmSet$carePlanReminder(cachecarePlanReminder);
            } else {
                realmObjectCopy.realmSet$carePlanReminder(CarePlanReminderRealmProxy.copyOrUpdate(realm, carePlanReminderObj, update, cache));
            }
        }
        return realmObject;
    }

    public static long insert(Realm realm, Todo object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Todo.class);
        long tableNativePtr = table.getNativePtr();
        TodoColumnInfo columnInfo = (TodoColumnInfo) realm.getSchema().getColumnInfo(Todo.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$note = object.realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        }
        CarePlanReminder carePlanReminderObj = object.realmGet$carePlanReminder();
        if (carePlanReminderObj == null) {
            return rowIndex;
        }
        Long cachecarePlanReminder = (Long) cache.get(carePlanReminderObj);
        if (cachecarePlanReminder == null) {
            cachecarePlanReminder = Long.valueOf(CarePlanReminderRealmProxy.insert(realm, carePlanReminderObj, (Map) cache));
        }
        Table.nativeSetLink(tableNativePtr, columnInfo.carePlanReminderIndex, rowIndex, cachecarePlanReminder.longValue(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Todo.class);
        long tableNativePtr = table.getNativePtr();
        TodoColumnInfo columnInfo = (TodoColumnInfo) realm.getSchema().getColumnInfo(Todo.class);
        while (objects.hasNext()) {
            Todo object = (Todo) objects.next();
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
                    CarePlanReminder carePlanReminderObj = object.realmGet$carePlanReminder();
                    if (carePlanReminderObj != null) {
                        Long cachecarePlanReminder = (Long) cache.get(carePlanReminderObj);
                        if (cachecarePlanReminder == null) {
                            cachecarePlanReminder = Long.valueOf(CarePlanReminderRealmProxy.insert(realm, carePlanReminderObj, (Map) cache));
                        }
                        table.setLink(columnInfo.carePlanReminderIndex, rowIndex, cachecarePlanReminder.longValue(), false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Todo object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Todo.class);
        long tableNativePtr = table.getNativePtr();
        TodoColumnInfo columnInfo = (TodoColumnInfo) realm.getSchema().getColumnInfo(Todo.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$note = object.realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
        }
        CarePlanReminder carePlanReminderObj = object.realmGet$carePlanReminder();
        if (carePlanReminderObj != null) {
            Long cachecarePlanReminder = (Long) cache.get(carePlanReminderObj);
            if (cachecarePlanReminder == null) {
                cachecarePlanReminder = Long.valueOf(CarePlanReminderRealmProxy.insertOrUpdate(realm, carePlanReminderObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.carePlanReminderIndex, rowIndex, cachecarePlanReminder.longValue(), false);
            return rowIndex;
        }
        Table.nativeNullifyLink(tableNativePtr, columnInfo.carePlanReminderIndex, rowIndex);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Todo.class);
        long tableNativePtr = table.getNativePtr();
        TodoColumnInfo columnInfo = (TodoColumnInfo) realm.getSchema().getColumnInfo(Todo.class);
        while (objects.hasNext()) {
            Todo object = (Todo) objects.next();
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
                    CarePlanReminder carePlanReminderObj = object.realmGet$carePlanReminder();
                    if (carePlanReminderObj != null) {
                        Long cachecarePlanReminder = (Long) cache.get(carePlanReminderObj);
                        if (cachecarePlanReminder == null) {
                            cachecarePlanReminder = Long.valueOf(CarePlanReminderRealmProxy.insertOrUpdate(realm, carePlanReminderObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.carePlanReminderIndex, rowIndex, cachecarePlanReminder.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.carePlanReminderIndex, rowIndex);
                    }
                }
            }
        }
    }

    public static Todo createDetachedCopy(Todo realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Todo unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Todo();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Todo) cachedObject.object;
        } else {
            unmanagedObject = (Todo) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        TodoRealmProxyInterface unmanagedCopy = unmanagedObject;
        TodoRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$note(realmSource.realmGet$note());
        unmanagedCopy.realmSet$carePlanReminder(CarePlanReminderRealmProxy.createDetachedCopy(realmSource.realmGet$carePlanReminder(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Todo = proxy[");
        stringBuilder.append("{note:");
        stringBuilder.append(realmGet$note() != null ? realmGet$note() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{carePlanReminder:");
        stringBuilder.append(realmGet$carePlanReminder() != null ? "CarePlanReminder" : "null");
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
        TodoRealmProxy aTodo = (TodoRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aTodo.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTodo.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aTodo.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
