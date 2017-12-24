package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.Goal;
import com.cooey.common.vo.careplan.VitalFeature;
import com.cooey.common.vo.careplan.VitalIntervention;
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

public class VitalInterventionRealmProxy extends VitalIntervention implements RealmObjectProxy, VitalInterventionRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private VitalInterventionColumnInfo columnInfo;
    private ProxyState<VitalIntervention> proxyState;

    static final class VitalInterventionColumnInfo extends ColumnInfo {
        long goalIndex;
        long vitalFeatureIndex;

        VitalInterventionColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("VitalIntervention");
            this.vitalFeatureIndex = addColumnDetails("vitalFeature", objectSchemaInfo);
            this.goalIndex = addColumnDetails("goal", objectSchemaInfo);
        }

        VitalInterventionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new VitalInterventionColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            VitalInterventionColumnInfo src = (VitalInterventionColumnInfo) rawSrc;
            VitalInterventionColumnInfo dst = (VitalInterventionColumnInfo) rawDst;
            dst.vitalFeatureIndex = src.vitalFeatureIndex;
            dst.goalIndex = src.goalIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("vitalFeature");
        fieldNames.add("goal");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    VitalInterventionRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (VitalInterventionColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public VitalFeature realmGet$vitalFeature() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.vitalFeatureIndex)) {
            return null;
        }
        return (VitalFeature) this.proxyState.getRealm$realm().get(VitalFeature.class, this.proxyState.getRow$realm().getLink(this.columnInfo.vitalFeatureIndex), false, Collections.emptyList());
    }

    public void realmSet$vitalFeature(VitalFeature value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.vitalFeatureIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.vitalFeatureIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("vitalFeature")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (VitalFeature) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.vitalFeatureIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.vitalFeatureIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
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
        Builder builder = new Builder("VitalIntervention");
        builder.addPersistedLinkProperty("vitalFeature", RealmFieldType.OBJECT, "VitalFeature");
        builder.addPersistedLinkProperty("goal", RealmFieldType.OBJECT, "Goal");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static VitalInterventionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new VitalInterventionColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_VitalIntervention";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static VitalIntervention createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(2);
        if (json.has("vitalFeature")) {
            excludeFields.add("vitalFeature");
        }
        if (json.has("goal")) {
            excludeFields.add("goal");
        }
        VitalIntervention obj = (VitalIntervention) realm.createObjectInternal(VitalIntervention.class, true, excludeFields);
        VitalInterventionRealmProxyInterface objProxy = obj;
        if (json.has("vitalFeature")) {
            if (json.isNull("vitalFeature")) {
                objProxy.realmSet$vitalFeature(null);
            } else {
                objProxy.realmSet$vitalFeature(VitalFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("vitalFeature"), update));
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
    public static VitalIntervention createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        VitalIntervention obj = new VitalIntervention();
        VitalInterventionRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("vitalFeature")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$vitalFeature(null);
                } else {
                    objProxy.realmSet$vitalFeature(VitalFeatureRealmProxy.createUsingJsonStream(realm, reader));
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
        return (VitalIntervention) realm.copyToRealm(obj);
    }

    public static VitalIntervention copyOrUpdate(Realm realm, VitalIntervention object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (VitalIntervention) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static VitalIntervention copy(Realm realm, VitalIntervention newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (VitalIntervention) cachedRealmObject;
        }
        VitalIntervention realmObject = (VitalIntervention) realm.createObjectInternal(VitalIntervention.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        VitalInterventionRealmProxyInterface realmObjectSource = newObject;
        VitalInterventionRealmProxyInterface realmObjectCopy = realmObject;
        VitalFeature vitalFeatureObj = realmObjectSource.realmGet$vitalFeature();
        if (vitalFeatureObj == null) {
            realmObjectCopy.realmSet$vitalFeature(null);
        } else {
            VitalFeature cachevitalFeature = (VitalFeature) cache.get(vitalFeatureObj);
            if (cachevitalFeature != null) {
                realmObjectCopy.realmSet$vitalFeature(cachevitalFeature);
            } else {
                realmObjectCopy.realmSet$vitalFeature(VitalFeatureRealmProxy.copyOrUpdate(realm, vitalFeatureObj, update, cache));
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

    public static long insert(Realm realm, VitalIntervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(VitalIntervention.class);
        long tableNativePtr = table.getNativePtr();
        VitalInterventionColumnInfo columnInfo = (VitalInterventionColumnInfo) realm.getSchema().getColumnInfo(VitalIntervention.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        VitalFeature vitalFeatureObj = object.realmGet$vitalFeature();
        if (vitalFeatureObj != null) {
            Long cachevitalFeature = (Long) cache.get(vitalFeatureObj);
            if (cachevitalFeature == null) {
                cachevitalFeature = Long.valueOf(VitalFeatureRealmProxy.insert(realm, vitalFeatureObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.vitalFeatureIndex, rowIndex, cachevitalFeature.longValue(), false);
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
        Table table = realm.getTable(VitalIntervention.class);
        long tableNativePtr = table.getNativePtr();
        VitalInterventionColumnInfo columnInfo = (VitalInterventionColumnInfo) realm.getSchema().getColumnInfo(VitalIntervention.class);
        while (objects.hasNext()) {
            VitalIntervention object = (VitalIntervention) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    VitalFeature vitalFeatureObj = object.realmGet$vitalFeature();
                    if (vitalFeatureObj != null) {
                        Long cachevitalFeature = (Long) cache.get(vitalFeatureObj);
                        if (cachevitalFeature == null) {
                            cachevitalFeature = Long.valueOf(VitalFeatureRealmProxy.insert(realm, vitalFeatureObj, (Map) cache));
                        }
                        table.setLink(columnInfo.vitalFeatureIndex, rowIndex, cachevitalFeature.longValue(), false);
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

    public static long insertOrUpdate(Realm realm, VitalIntervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(VitalIntervention.class);
        long tableNativePtr = table.getNativePtr();
        VitalInterventionColumnInfo columnInfo = (VitalInterventionColumnInfo) realm.getSchema().getColumnInfo(VitalIntervention.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        VitalFeature vitalFeatureObj = object.realmGet$vitalFeature();
        if (vitalFeatureObj != null) {
            Long cachevitalFeature = (Long) cache.get(vitalFeatureObj);
            if (cachevitalFeature == null) {
                cachevitalFeature = Long.valueOf(VitalFeatureRealmProxy.insertOrUpdate(realm, vitalFeatureObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.vitalFeatureIndex, rowIndex, cachevitalFeature.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.vitalFeatureIndex, rowIndex);
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
        Table table = realm.getTable(VitalIntervention.class);
        long tableNativePtr = table.getNativePtr();
        VitalInterventionColumnInfo columnInfo = (VitalInterventionColumnInfo) realm.getSchema().getColumnInfo(VitalIntervention.class);
        while (objects.hasNext()) {
            VitalIntervention object = (VitalIntervention) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    VitalFeature vitalFeatureObj = object.realmGet$vitalFeature();
                    if (vitalFeatureObj != null) {
                        Long cachevitalFeature = (Long) cache.get(vitalFeatureObj);
                        if (cachevitalFeature == null) {
                            cachevitalFeature = Long.valueOf(VitalFeatureRealmProxy.insertOrUpdate(realm, vitalFeatureObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.vitalFeatureIndex, rowIndex, cachevitalFeature.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.vitalFeatureIndex, rowIndex);
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

    public static VitalIntervention createDetachedCopy(VitalIntervention realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        VitalIntervention unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new VitalIntervention();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (VitalIntervention) cachedObject.object;
        } else {
            unmanagedObject = (VitalIntervention) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        VitalInterventionRealmProxyInterface unmanagedCopy = unmanagedObject;
        VitalInterventionRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$vitalFeature(VitalFeatureRealmProxy.createDetachedCopy(realmSource.realmGet$vitalFeature(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$goal(GoalRealmProxy.createDetachedCopy(realmSource.realmGet$goal(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("VitalIntervention = proxy[");
        stringBuilder.append("{vitalFeature:");
        stringBuilder.append(realmGet$vitalFeature() != null ? "VitalFeature" : "null");
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
        VitalInterventionRealmProxy aVitalIntervention = (VitalInterventionRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aVitalIntervention.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aVitalIntervention.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aVitalIntervention.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
