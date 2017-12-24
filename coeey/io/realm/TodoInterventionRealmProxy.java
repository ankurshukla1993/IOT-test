package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.Goal;
import com.cooey.common.vo.careplan.Todo;
import com.cooey.common.vo.careplan.TodoIntervention;
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

public class TodoInterventionRealmProxy extends TodoIntervention implements RealmObjectProxy, TodoInterventionRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TodoInterventionColumnInfo columnInfo;
    private ProxyState<TodoIntervention> proxyState;

    static final class TodoInterventionColumnInfo extends ColumnInfo {
        long goalIndex;
        long todoIndex;

        TodoInterventionColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("TodoIntervention");
            this.todoIndex = addColumnDetails("todo", objectSchemaInfo);
            this.goalIndex = addColumnDetails("goal", objectSchemaInfo);
        }

        TodoInterventionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new TodoInterventionColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            TodoInterventionColumnInfo src = (TodoInterventionColumnInfo) rawSrc;
            TodoInterventionColumnInfo dst = (TodoInterventionColumnInfo) rawDst;
            dst.todoIndex = src.todoIndex;
            dst.goalIndex = src.goalIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("todo");
        fieldNames.add("goal");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TodoInterventionRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TodoInterventionColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public Todo realmGet$todo() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.todoIndex)) {
            return null;
        }
        return (Todo) this.proxyState.getRealm$realm().get(Todo.class, this.proxyState.getRow$realm().getLink(this.columnInfo.todoIndex), false, Collections.emptyList());
    }

    public void realmSet$todo(Todo value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.todoIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.todoIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("todo")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Todo) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.todoIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.todoIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Goal realmGet$goal() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.goalIndex)) {
            return null;
        }
        return (Goal) this.proxyState.getRealm$realm().get(Goal.class, this.proxyState.getRow$realm().getLink(this.columnInfo.goalIndex), false, Collections.emptyList());
    }

    public void realmSet$goal(Goal value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.goalIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.goalIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("goal")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Goal) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.goalIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.goalIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("TodoIntervention");
        builder.addPersistedLinkProperty("todo", RealmFieldType.OBJECT, "Todo");
        builder.addPersistedLinkProperty("goal", RealmFieldType.OBJECT, "Goal");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TodoInterventionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new TodoInterventionColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_TodoIntervention";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static TodoIntervention createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(2);
        if (json.has("todo")) {
            excludeFields.add("todo");
        }
        if (json.has("goal")) {
            excludeFields.add("goal");
        }
        TodoIntervention obj = (TodoIntervention) realm.createObjectInternal(TodoIntervention.class, true, excludeFields);
        TodoInterventionRealmProxyInterface objProxy = obj;
        if (json.has("todo")) {
            if (json.isNull("todo")) {
                objProxy.realmSet$todo(null);
            } else {
                objProxy.realmSet$todo(TodoRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("todo"), update));
            }
        }
        if (json.has("goal")) {
            if (json.isNull("goal")) {
                objProxy.realmSet$goal(null);
            } else {
                objProxy.realmSet$goal(GoalRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("goal"), update));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static TodoIntervention createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        TodoIntervention obj = new TodoIntervention();
        TodoInterventionRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("todo")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$todo(null);
                } else {
                    objProxy.realmSet$todo(TodoRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (!name.equals("goal")) {
                reader.skipValue();
            } else if (reader.peek() == JsonToken.NULL) {
                reader.skipValue();
                objProxy.realmSet$goal(null);
            } else {
                objProxy.realmSet$goal(GoalRealmProxy.createUsingJsonStream(realm, reader));
            }
        }
        reader.endObject();
        return (TodoIntervention) realm.copyToRealm(obj);
    }

    public static TodoIntervention copyOrUpdate(Realm realm, TodoIntervention object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (TodoIntervention) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static TodoIntervention copy(Realm realm, TodoIntervention newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (TodoIntervention) cachedRealmObject;
        }
        TodoIntervention realmObject = (TodoIntervention) realm.createObjectInternal(TodoIntervention.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        TodoInterventionRealmProxyInterface realmObjectSource = newObject;
        TodoInterventionRealmProxyInterface realmObjectCopy = realmObject;
        Todo todoObj = realmObjectSource.realmGet$todo();
        if (todoObj == null) {
            realmObjectCopy.realmSet$todo(null);
        } else {
            Todo cachetodo = (Todo) cache.get(todoObj);
            if (cachetodo != null) {
                realmObjectCopy.realmSet$todo(cachetodo);
            } else {
                realmObjectCopy.realmSet$todo(TodoRealmProxy.copyOrUpdate(realm, todoObj, update, cache));
            }
        }
        Goal goalObj = realmObjectSource.realmGet$goal();
        if (goalObj == null) {
            realmObjectCopy.realmSet$goal(null);
        } else {
            Goal cachegoal = (Goal) cache.get(goalObj);
            if (cachegoal != null) {
                realmObjectCopy.realmSet$goal(cachegoal);
            } else {
                realmObjectCopy.realmSet$goal(GoalRealmProxy.copyOrUpdate(realm, goalObj, update, cache));
            }
        }
        return realmObject;
    }

    public static long insert(Realm realm, TodoIntervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(TodoIntervention.class);
        long tableNativePtr = table.getNativePtr();
        TodoInterventionColumnInfo columnInfo = (TodoInterventionColumnInfo) realm.getSchema().getColumnInfo(TodoIntervention.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Todo todoObj = object.realmGet$todo();
        if (todoObj != null) {
            Long cachetodo = (Long) cache.get(todoObj);
            if (cachetodo == null) {
                cachetodo = Long.valueOf(TodoRealmProxy.insert(realm, todoObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.todoIndex, rowIndex, cachetodo.longValue(), false);
        }
        Goal goalObj = object.realmGet$goal();
        if (goalObj == null) {
            return rowIndex;
        }
        Long cachegoal = (Long) cache.get(goalObj);
        if (cachegoal == null) {
            cachegoal = Long.valueOf(GoalRealmProxy.insert(realm, goalObj, (Map) cache));
        }
        Table.nativeSetLink(tableNativePtr, columnInfo.goalIndex, rowIndex, cachegoal.longValue(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(TodoIntervention.class);
        long tableNativePtr = table.getNativePtr();
        TodoInterventionColumnInfo columnInfo = (TodoInterventionColumnInfo) realm.getSchema().getColumnInfo(TodoIntervention.class);
        while (objects.hasNext()) {
            TodoIntervention object = (TodoIntervention) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Todo todoObj = object.realmGet$todo();
                    if (todoObj != null) {
                        Long cachetodo = (Long) cache.get(todoObj);
                        if (cachetodo == null) {
                            cachetodo = Long.valueOf(TodoRealmProxy.insert(realm, todoObj, (Map) cache));
                        }
                        table.setLink(columnInfo.todoIndex, rowIndex, cachetodo.longValue(), false);
                    }
                    Goal goalObj = object.realmGet$goal();
                    if (goalObj != null) {
                        Long cachegoal = (Long) cache.get(goalObj);
                        if (cachegoal == null) {
                            cachegoal = Long.valueOf(GoalRealmProxy.insert(realm, goalObj, (Map) cache));
                        }
                        table.setLink(columnInfo.goalIndex, rowIndex, cachegoal.longValue(), false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, TodoIntervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(TodoIntervention.class);
        long tableNativePtr = table.getNativePtr();
        TodoInterventionColumnInfo columnInfo = (TodoInterventionColumnInfo) realm.getSchema().getColumnInfo(TodoIntervention.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Todo todoObj = object.realmGet$todo();
        if (todoObj != null) {
            Long cachetodo = (Long) cache.get(todoObj);
            if (cachetodo == null) {
                cachetodo = Long.valueOf(TodoRealmProxy.insertOrUpdate(realm, todoObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.todoIndex, rowIndex, cachetodo.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.todoIndex, rowIndex);
        }
        Goal goalObj = object.realmGet$goal();
        if (goalObj != null) {
            Long cachegoal = (Long) cache.get(goalObj);
            if (cachegoal == null) {
                cachegoal = Long.valueOf(GoalRealmProxy.insertOrUpdate(realm, goalObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.goalIndex, rowIndex, cachegoal.longValue(), false);
            return rowIndex;
        }
        Table.nativeNullifyLink(tableNativePtr, columnInfo.goalIndex, rowIndex);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(TodoIntervention.class);
        long tableNativePtr = table.getNativePtr();
        TodoInterventionColumnInfo columnInfo = (TodoInterventionColumnInfo) realm.getSchema().getColumnInfo(TodoIntervention.class);
        while (objects.hasNext()) {
            TodoIntervention object = (TodoIntervention) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Todo todoObj = object.realmGet$todo();
                    if (todoObj != null) {
                        Long cachetodo = (Long) cache.get(todoObj);
                        if (cachetodo == null) {
                            cachetodo = Long.valueOf(TodoRealmProxy.insertOrUpdate(realm, todoObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.todoIndex, rowIndex, cachetodo.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.todoIndex, rowIndex);
                    }
                    Goal goalObj = object.realmGet$goal();
                    if (goalObj != null) {
                        Long cachegoal = (Long) cache.get(goalObj);
                        if (cachegoal == null) {
                            cachegoal = Long.valueOf(GoalRealmProxy.insertOrUpdate(realm, goalObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.goalIndex, rowIndex, cachegoal.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.goalIndex, rowIndex);
                    }
                }
            }
        }
    }

    public static TodoIntervention createDetachedCopy(TodoIntervention realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        TodoIntervention unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new TodoIntervention();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (TodoIntervention) cachedObject.object;
        } else {
            unmanagedObject = (TodoIntervention) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        TodoInterventionRealmProxyInterface unmanagedCopy = unmanagedObject;
        TodoInterventionRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$todo(TodoRealmProxy.createDetachedCopy(realmSource.realmGet$todo(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$goal(GoalRealmProxy.createDetachedCopy(realmSource.realmGet$goal(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TodoIntervention = proxy[");
        stringBuilder.append("{todo:");
        stringBuilder.append(realmGet$todo() != null ? "Todo" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{goal:");
        stringBuilder.append(realmGet$goal() != null ? "Goal" : "null");
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
        TodoInterventionRealmProxy aTodoIntervention = (TodoInterventionRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aTodoIntervention.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTodoIntervention.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aTodoIntervention.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
