package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.CarePlanReminder;
import com.cooey.common.vo.careplan.Goal;
import com.cooey.common.vo.careplan.Instructions;
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

public class GoalRealmProxy extends Goal implements RealmObjectProxy, GoalRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private GoalColumnInfo columnInfo;
    private ProxyState<Goal> proxyState;

    static final class GoalColumnInfo extends ColumnInfo {
        long carePlanReminderIndex;
        long instructionsIndex;

        GoalColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Goal");
            this.instructionsIndex = addColumnDetails("instructions", objectSchemaInfo);
            this.carePlanReminderIndex = addColumnDetails("carePlanReminder", objectSchemaInfo);
        }

        GoalColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new GoalColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            GoalColumnInfo src = (GoalColumnInfo) rawSrc;
            GoalColumnInfo dst = (GoalColumnInfo) rawDst;
            dst.instructionsIndex = src.instructionsIndex;
            dst.carePlanReminderIndex = src.carePlanReminderIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("instructions");
        fieldNames.add("carePlanReminder");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    GoalRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (GoalColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public Instructions realmGet$instructions() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.instructionsIndex)) {
            return null;
        }
        return (Instructions) this.proxyState.getRealm$realm().get(Instructions.class, this.proxyState.getRow$realm().getLink(this.columnInfo.instructionsIndex), false, Collections.emptyList());
    }

    public void realmSet$instructions(Instructions value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.instructionsIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.instructionsIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("instructions")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Instructions) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.instructionsIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.instructionsIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
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
        Builder builder = new Builder("Goal");
        builder.addPersistedLinkProperty("instructions", RealmFieldType.OBJECT, "Instructions");
        builder.addPersistedLinkProperty("carePlanReminder", RealmFieldType.OBJECT, "CarePlanReminder");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static GoalColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new GoalColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Goal";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Goal createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(2);
        if (json.has("instructions")) {
            excludeFields.add("instructions");
        }
        if (json.has("carePlanReminder")) {
            excludeFields.add("carePlanReminder");
        }
        Goal obj = (Goal) realm.createObjectInternal(Goal.class, true, excludeFields);
        GoalRealmProxyInterface objProxy = obj;
        if (json.has("instructions")) {
            if (json.isNull("instructions")) {
                objProxy.realmSet$instructions(null);
            } else {
                objProxy.realmSet$instructions(InstructionsRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("instructions"), update));
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
    public static Goal createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Goal obj = new Goal();
        GoalRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("instructions")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$instructions(null);
                } else {
                    objProxy.realmSet$instructions(InstructionsRealmProxy.createUsingJsonStream(realm, reader));
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
        return (Goal) realm.copyToRealm(obj);
    }

    public static Goal copyOrUpdate(Realm realm, Goal object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Goal) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Goal copy(Realm realm, Goal newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Goal) cachedRealmObject;
        }
        Goal realmObject = (Goal) realm.createObjectInternal(Goal.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        GoalRealmProxyInterface realmObjectSource = newObject;
        GoalRealmProxyInterface realmObjectCopy = realmObject;
        Instructions instructionsObj = realmObjectSource.realmGet$instructions();
        if (instructionsObj == null) {
            realmObjectCopy.realmSet$instructions(null);
        } else {
            Instructions cacheinstructions = (Instructions) cache.get(instructionsObj);
            if (cacheinstructions != null) {
                realmObjectCopy.realmSet$instructions(cacheinstructions);
            } else {
                realmObjectCopy.realmSet$instructions(InstructionsRealmProxy.copyOrUpdate(realm, instructionsObj, update, cache));
            }
        }
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

    public static long insert(Realm realm, Goal object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Goal.class);
        long tableNativePtr = table.getNativePtr();
        GoalColumnInfo columnInfo = (GoalColumnInfo) realm.getSchema().getColumnInfo(Goal.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Instructions instructionsObj = object.realmGet$instructions();
        if (instructionsObj != null) {
            Long cacheinstructions = (Long) cache.get(instructionsObj);
            if (cacheinstructions == null) {
                cacheinstructions = Long.valueOf(InstructionsRealmProxy.insert(realm, instructionsObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.instructionsIndex, rowIndex, cacheinstructions.longValue(), false);
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
        Table table = realm.getTable(Goal.class);
        long tableNativePtr = table.getNativePtr();
        GoalColumnInfo columnInfo = (GoalColumnInfo) realm.getSchema().getColumnInfo(Goal.class);
        while (objects.hasNext()) {
            Goal object = (Goal) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Instructions instructionsObj = object.realmGet$instructions();
                    if (instructionsObj != null) {
                        Long cacheinstructions = (Long) cache.get(instructionsObj);
                        if (cacheinstructions == null) {
                            cacheinstructions = Long.valueOf(InstructionsRealmProxy.insert(realm, instructionsObj, (Map) cache));
                        }
                        table.setLink(columnInfo.instructionsIndex, rowIndex, cacheinstructions.longValue(), false);
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

    public static long insertOrUpdate(Realm realm, Goal object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Goal.class);
        long tableNativePtr = table.getNativePtr();
        GoalColumnInfo columnInfo = (GoalColumnInfo) realm.getSchema().getColumnInfo(Goal.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        Instructions instructionsObj = object.realmGet$instructions();
        if (instructionsObj != null) {
            Long cacheinstructions = (Long) cache.get(instructionsObj);
            if (cacheinstructions == null) {
                cacheinstructions = Long.valueOf(InstructionsRealmProxy.insertOrUpdate(realm, instructionsObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.instructionsIndex, rowIndex, cacheinstructions.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.instructionsIndex, rowIndex);
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
        Table table = realm.getTable(Goal.class);
        long tableNativePtr = table.getNativePtr();
        GoalColumnInfo columnInfo = (GoalColumnInfo) realm.getSchema().getColumnInfo(Goal.class);
        while (objects.hasNext()) {
            Goal object = (Goal) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    Instructions instructionsObj = object.realmGet$instructions();
                    if (instructionsObj != null) {
                        Long cacheinstructions = (Long) cache.get(instructionsObj);
                        if (cacheinstructions == null) {
                            cacheinstructions = Long.valueOf(InstructionsRealmProxy.insertOrUpdate(realm, instructionsObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.instructionsIndex, rowIndex, cacheinstructions.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.instructionsIndex, rowIndex);
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

    public static Goal createDetachedCopy(Goal realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Goal unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Goal();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Goal) cachedObject.object;
        } else {
            unmanagedObject = (Goal) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        GoalRealmProxyInterface unmanagedCopy = unmanagedObject;
        GoalRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$instructions(InstructionsRealmProxy.createDetachedCopy(realmSource.realmGet$instructions(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$carePlanReminder(CarePlanReminderRealmProxy.createDetachedCopy(realmSource.realmGet$carePlanReminder(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Goal = proxy[");
        stringBuilder.append("{instructions:");
        stringBuilder.append(realmGet$instructions() != null ? "Instructions" : "null");
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
        GoalRealmProxy aGoal = (GoalRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aGoal.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aGoal.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aGoal.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
