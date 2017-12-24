package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.DietFeature;
import com.cooey.common.vo.careplan.DietIntervention;
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

public class DietInterventionRealmProxy extends DietIntervention implements RealmObjectProxy, DietInterventionRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private DietInterventionColumnInfo columnInfo;
    private ProxyState<DietIntervention> proxyState;

    static final class DietInterventionColumnInfo extends ColumnInfo {
        long dietFeatureIndex;
        long goalIndex;

        DietInterventionColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("DietIntervention");
            this.dietFeatureIndex = addColumnDetails("dietFeature", objectSchemaInfo);
            this.goalIndex = addColumnDetails("goal", objectSchemaInfo);
        }

        DietInterventionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new DietInterventionColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            DietInterventionColumnInfo src = (DietInterventionColumnInfo) rawSrc;
            DietInterventionColumnInfo dst = (DietInterventionColumnInfo) rawDst;
            dst.dietFeatureIndex = src.dietFeatureIndex;
            dst.goalIndex = src.goalIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("dietFeature");
        fieldNames.add("goal");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DietInterventionRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (DietInterventionColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public DietFeature realmGet$dietFeature() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.dietFeatureIndex)) {
            return null;
        }
        return (DietFeature) this.proxyState.getRealm$realm().get(DietFeature.class, this.proxyState.getRow$realm().getLink(this.columnInfo.dietFeatureIndex), false, Collections.emptyList());
    }

    public void realmSet$dietFeature(DietFeature value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.dietFeatureIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.dietFeatureIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("dietFeature")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (DietFeature) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.dietFeatureIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.dietFeatureIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
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
        Builder builder = new Builder("DietIntervention");
        builder.addPersistedLinkProperty("dietFeature", RealmFieldType.OBJECT, "DietFeature");
        builder.addPersistedLinkProperty("goal", RealmFieldType.OBJECT, "Goal");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static DietInterventionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new DietInterventionColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_DietIntervention";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static DietIntervention createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(2);
        if (json.has("dietFeature")) {
            excludeFields.add("dietFeature");
        }
        if (json.has("goal")) {
            excludeFields.add("goal");
        }
        DietIntervention obj = (DietIntervention) realm.createObjectInternal(DietIntervention.class, true, excludeFields);
        DietInterventionRealmProxyInterface objProxy = obj;
        if (json.has("dietFeature")) {
            if (json.isNull("dietFeature")) {
                objProxy.realmSet$dietFeature(null);
            } else {
                objProxy.realmSet$dietFeature(DietFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("dietFeature"), update));
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
    public static DietIntervention createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        DietIntervention obj = new DietIntervention();
        DietInterventionRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("dietFeature")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$dietFeature(null);
                } else {
                    objProxy.realmSet$dietFeature(DietFeatureRealmProxy.createUsingJsonStream(realm, reader));
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
        return (DietIntervention) realm.copyToRealm(obj);
    }

    public static DietIntervention copyOrUpdate(Realm realm, DietIntervention object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (DietIntervention) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static DietIntervention copy(Realm realm, DietIntervention newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (DietIntervention) cachedRealmObject;
        }
        DietIntervention realmObject = (DietIntervention) realm.createObjectInternal(DietIntervention.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        DietInterventionRealmProxyInterface realmObjectSource = newObject;
        DietInterventionRealmProxyInterface realmObjectCopy = realmObject;
        DietFeature dietFeatureObj = realmObjectSource.realmGet$dietFeature();
        if (dietFeatureObj == null) {
            realmObjectCopy.realmSet$dietFeature(null);
        } else {
            DietFeature cachedietFeature = (DietFeature) cache.get(dietFeatureObj);
            if (cachedietFeature != null) {
                realmObjectCopy.realmSet$dietFeature(cachedietFeature);
            } else {
                realmObjectCopy.realmSet$dietFeature(DietFeatureRealmProxy.copyOrUpdate(realm, dietFeatureObj, update, cache));
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

    public static long insert(Realm realm, DietIntervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(DietIntervention.class);
        long tableNativePtr = table.getNativePtr();
        DietInterventionColumnInfo columnInfo = (DietInterventionColumnInfo) realm.getSchema().getColumnInfo(DietIntervention.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        DietFeature dietFeatureObj = object.realmGet$dietFeature();
        if (dietFeatureObj != null) {
            Long cachedietFeature = (Long) cache.get(dietFeatureObj);
            if (cachedietFeature == null) {
                cachedietFeature = Long.valueOf(DietFeatureRealmProxy.insert(realm, dietFeatureObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.dietFeatureIndex, rowIndex, cachedietFeature.longValue(), false);
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
        Table table = realm.getTable(DietIntervention.class);
        long tableNativePtr = table.getNativePtr();
        DietInterventionColumnInfo columnInfo = (DietInterventionColumnInfo) realm.getSchema().getColumnInfo(DietIntervention.class);
        while (objects.hasNext()) {
            DietIntervention object = (DietIntervention) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    DietFeature dietFeatureObj = object.realmGet$dietFeature();
                    if (dietFeatureObj != null) {
                        Long cachedietFeature = (Long) cache.get(dietFeatureObj);
                        if (cachedietFeature == null) {
                            cachedietFeature = Long.valueOf(DietFeatureRealmProxy.insert(realm, dietFeatureObj, (Map) cache));
                        }
                        table.setLink(columnInfo.dietFeatureIndex, rowIndex, cachedietFeature.longValue(), false);
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

    public static long insertOrUpdate(Realm realm, DietIntervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(DietIntervention.class);
        long tableNativePtr = table.getNativePtr();
        DietInterventionColumnInfo columnInfo = (DietInterventionColumnInfo) realm.getSchema().getColumnInfo(DietIntervention.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        DietFeature dietFeatureObj = object.realmGet$dietFeature();
        if (dietFeatureObj != null) {
            Long cachedietFeature = (Long) cache.get(dietFeatureObj);
            if (cachedietFeature == null) {
                cachedietFeature = Long.valueOf(DietFeatureRealmProxy.insertOrUpdate(realm, dietFeatureObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.dietFeatureIndex, rowIndex, cachedietFeature.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.dietFeatureIndex, rowIndex);
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
        Table table = realm.getTable(DietIntervention.class);
        long tableNativePtr = table.getNativePtr();
        DietInterventionColumnInfo columnInfo = (DietInterventionColumnInfo) realm.getSchema().getColumnInfo(DietIntervention.class);
        while (objects.hasNext()) {
            DietIntervention object = (DietIntervention) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    DietFeature dietFeatureObj = object.realmGet$dietFeature();
                    if (dietFeatureObj != null) {
                        Long cachedietFeature = (Long) cache.get(dietFeatureObj);
                        if (cachedietFeature == null) {
                            cachedietFeature = Long.valueOf(DietFeatureRealmProxy.insertOrUpdate(realm, dietFeatureObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.dietFeatureIndex, rowIndex, cachedietFeature.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.dietFeatureIndex, rowIndex);
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

    public static DietIntervention createDetachedCopy(DietIntervention realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        DietIntervention unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new DietIntervention();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (DietIntervention) cachedObject.object;
        } else {
            unmanagedObject = (DietIntervention) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        DietInterventionRealmProxyInterface unmanagedCopy = unmanagedObject;
        DietInterventionRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$dietFeature(DietFeatureRealmProxy.createDetachedCopy(realmSource.realmGet$dietFeature(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$goal(GoalRealmProxy.createDetachedCopy(realmSource.realmGet$goal(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DietIntervention = proxy[");
        stringBuilder.append("{dietFeature:");
        stringBuilder.append(realmGet$dietFeature() != null ? "DietFeature" : "null");
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
        DietInterventionRealmProxy aDietIntervention = (DietInterventionRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aDietIntervention.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDietIntervention.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aDietIntervention.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
