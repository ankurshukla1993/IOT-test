package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.ActivityFeature;
import com.cooey.common.vo.careplan.ActivityIntervention;
import com.cooey.common.vo.careplan.Goal;
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

public class ActivityInterventionRealmProxy extends ActivityIntervention implements RealmObjectProxy, ActivityInterventionRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ActivityInterventionColumnInfo columnInfo;
    private ProxyState<ActivityIntervention> proxyState;

    static final class ActivityInterventionColumnInfo extends ColumnInfo {
        long activityFeatureIndex;
        long goalIndex;

        ActivityInterventionColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("ActivityIntervention");
            this.activityFeatureIndex = addColumnDetails("activityFeature", objectSchemaInfo);
            this.goalIndex = addColumnDetails("goal", objectSchemaInfo);
        }

        ActivityInterventionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new ActivityInterventionColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ActivityInterventionColumnInfo src = (ActivityInterventionColumnInfo) rawSrc;
            ActivityInterventionColumnInfo dst = (ActivityInterventionColumnInfo) rawDst;
            dst.activityFeatureIndex = src.activityFeatureIndex;
            dst.goalIndex = src.goalIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("activityFeature");
        fieldNames.add("goal");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ActivityInterventionRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ActivityInterventionColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public ActivityFeature realmGet$activityFeature() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.activityFeatureIndex)) {
            return null;
        }
        return (ActivityFeature) this.proxyState.getRealm$realm().get(ActivityFeature.class, this.proxyState.getRow$realm().getLink(this.columnInfo.activityFeatureIndex), false, Collections.emptyList());
    }

    public void realmSet$activityFeature(ActivityFeature value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.activityFeatureIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.activityFeatureIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("activityFeature")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (ActivityFeature) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.activityFeatureIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.activityFeatureIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
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
        Builder builder = new Builder("ActivityIntervention");
        builder.addPersistedLinkProperty("activityFeature", RealmFieldType.OBJECT, "ActivityFeature");
        builder.addPersistedLinkProperty("goal", RealmFieldType.OBJECT, "Goal");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ActivityInterventionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ActivityInterventionColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_ActivityIntervention";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static ActivityIntervention createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(2);
        if (json.has("activityFeature")) {
            excludeFields.add("activityFeature");
        }
        if (json.has("goal")) {
            excludeFields.add("goal");
        }
        ActivityIntervention obj = (ActivityIntervention) realm.createObjectInternal(ActivityIntervention.class, true, excludeFields);
        ActivityInterventionRealmProxyInterface objProxy = obj;
        if (json.has("activityFeature")) {
            if (json.isNull("activityFeature")) {
                objProxy.realmSet$activityFeature(null);
            } else {
                objProxy.realmSet$activityFeature(ActivityFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("activityFeature"), update));
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
    public static ActivityIntervention createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        ActivityIntervention obj = new ActivityIntervention();
        ActivityInterventionRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("activityFeature")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$activityFeature(null);
                } else {
                    objProxy.realmSet$activityFeature(ActivityFeatureRealmProxy.createUsingJsonStream(realm, reader));
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
        return (ActivityIntervention) realm.copyToRealm(obj);
    }

    public static ActivityIntervention copyOrUpdate(Realm realm, ActivityIntervention object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (ActivityIntervention) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static ActivityIntervention copy(Realm realm, ActivityIntervention newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (ActivityIntervention) cachedRealmObject;
        }
        ActivityIntervention realmObject = (ActivityIntervention) realm.createObjectInternal(ActivityIntervention.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ActivityInterventionRealmProxyInterface realmObjectSource = newObject;
        ActivityInterventionRealmProxyInterface realmObjectCopy = realmObject;
        ActivityFeature activityFeatureObj = realmObjectSource.realmGet$activityFeature();
        if (activityFeatureObj == null) {
            realmObjectCopy.realmSet$activityFeature(null);
        } else {
            ActivityFeature cacheactivityFeature = (ActivityFeature) cache.get(activityFeatureObj);
            if (cacheactivityFeature != null) {
                realmObjectCopy.realmSet$activityFeature(cacheactivityFeature);
            } else {
                realmObjectCopy.realmSet$activityFeature(ActivityFeatureRealmProxy.copyOrUpdate(realm, activityFeatureObj, update, cache));
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

    public static long insert(Realm realm, ActivityIntervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(ActivityIntervention.class);
        long tableNativePtr = table.getNativePtr();
        ActivityInterventionColumnInfo columnInfo = (ActivityInterventionColumnInfo) realm.getSchema().getColumnInfo(ActivityIntervention.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        ActivityFeature activityFeatureObj = object.realmGet$activityFeature();
        if (activityFeatureObj != null) {
            Long cacheactivityFeature = (Long) cache.get(activityFeatureObj);
            if (cacheactivityFeature == null) {
                cacheactivityFeature = Long.valueOf(ActivityFeatureRealmProxy.insert(realm, activityFeatureObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.activityFeatureIndex, rowIndex, cacheactivityFeature.longValue(), false);
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
        Table table = realm.getTable(ActivityIntervention.class);
        long tableNativePtr = table.getNativePtr();
        ActivityInterventionColumnInfo columnInfo = (ActivityInterventionColumnInfo) realm.getSchema().getColumnInfo(ActivityIntervention.class);
        while (objects.hasNext()) {
            ActivityIntervention object = (ActivityIntervention) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    ActivityFeature activityFeatureObj = object.realmGet$activityFeature();
                    if (activityFeatureObj != null) {
                        Long cacheactivityFeature = (Long) cache.get(activityFeatureObj);
                        if (cacheactivityFeature == null) {
                            cacheactivityFeature = Long.valueOf(ActivityFeatureRealmProxy.insert(realm, activityFeatureObj, (Map) cache));
                        }
                        table.setLink(columnInfo.activityFeatureIndex, rowIndex, cacheactivityFeature.longValue(), false);
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

    public static long insertOrUpdate(Realm realm, ActivityIntervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(ActivityIntervention.class);
        long tableNativePtr = table.getNativePtr();
        ActivityInterventionColumnInfo columnInfo = (ActivityInterventionColumnInfo) realm.getSchema().getColumnInfo(ActivityIntervention.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        ActivityFeature activityFeatureObj = object.realmGet$activityFeature();
        if (activityFeatureObj != null) {
            Long cacheactivityFeature = (Long) cache.get(activityFeatureObj);
            if (cacheactivityFeature == null) {
                cacheactivityFeature = Long.valueOf(ActivityFeatureRealmProxy.insertOrUpdate(realm, activityFeatureObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.activityFeatureIndex, rowIndex, cacheactivityFeature.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.activityFeatureIndex, rowIndex);
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
        Table table = realm.getTable(ActivityIntervention.class);
        long tableNativePtr = table.getNativePtr();
        ActivityInterventionColumnInfo columnInfo = (ActivityInterventionColumnInfo) realm.getSchema().getColumnInfo(ActivityIntervention.class);
        while (objects.hasNext()) {
            ActivityIntervention object = (ActivityIntervention) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    ActivityFeature activityFeatureObj = object.realmGet$activityFeature();
                    if (activityFeatureObj != null) {
                        Long cacheactivityFeature = (Long) cache.get(activityFeatureObj);
                        if (cacheactivityFeature == null) {
                            cacheactivityFeature = Long.valueOf(ActivityFeatureRealmProxy.insertOrUpdate(realm, activityFeatureObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.activityFeatureIndex, rowIndex, cacheactivityFeature.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.activityFeatureIndex, rowIndex);
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

    public static ActivityIntervention createDetachedCopy(ActivityIntervention realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        ActivityIntervention unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new ActivityIntervention();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (ActivityIntervention) cachedObject.object;
        } else {
            unmanagedObject = (ActivityIntervention) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ActivityInterventionRealmProxyInterface unmanagedCopy = unmanagedObject;
        ActivityInterventionRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$activityFeature(ActivityFeatureRealmProxy.createDetachedCopy(realmSource.realmGet$activityFeature(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$goal(GoalRealmProxy.createDetachedCopy(realmSource.realmGet$goal(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("ActivityIntervention = proxy[");
        stringBuilder.append("{activityFeature:");
        stringBuilder.append(realmGet$activityFeature() != null ? "ActivityFeature" : "null");
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
        ActivityInterventionRealmProxy aActivityIntervention = (ActivityInterventionRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aActivityIntervention.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aActivityIntervention.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aActivityIntervention.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
