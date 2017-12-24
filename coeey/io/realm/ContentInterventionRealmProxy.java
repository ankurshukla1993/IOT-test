package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.ContentFeature;
import com.cooey.common.vo.careplan.ContentIntervention;
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

public class ContentInterventionRealmProxy extends ContentIntervention implements RealmObjectProxy, ContentInterventionRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ContentInterventionColumnInfo columnInfo;
    private ProxyState<ContentIntervention> proxyState;

    static final class ContentInterventionColumnInfo extends ColumnInfo {
        long contentFeatureIndex;
        long goalIndex;

        ContentInterventionColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("ContentIntervention");
            this.contentFeatureIndex = addColumnDetails("contentFeature", objectSchemaInfo);
            this.goalIndex = addColumnDetails("goal", objectSchemaInfo);
        }

        ContentInterventionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new ContentInterventionColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ContentInterventionColumnInfo src = (ContentInterventionColumnInfo) rawSrc;
            ContentInterventionColumnInfo dst = (ContentInterventionColumnInfo) rawDst;
            dst.contentFeatureIndex = src.contentFeatureIndex;
            dst.goalIndex = src.goalIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("contentFeature");
        fieldNames.add("goal");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ContentInterventionRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ContentInterventionColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public ContentFeature realmGet$contentFeature() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.contentFeatureIndex)) {
            return null;
        }
        return (ContentFeature) this.proxyState.getRealm$realm().get(ContentFeature.class, this.proxyState.getRow$realm().getLink(this.columnInfo.contentFeatureIndex), false, Collections.emptyList());
    }

    public void realmSet$contentFeature(ContentFeature value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.contentFeatureIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.contentFeatureIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("contentFeature")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (ContentFeature) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.contentFeatureIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.contentFeatureIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
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
        Builder builder = new Builder("ContentIntervention");
        builder.addPersistedLinkProperty("contentFeature", RealmFieldType.OBJECT, "ContentFeature");
        builder.addPersistedLinkProperty("goal", RealmFieldType.OBJECT, "Goal");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ContentInterventionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ContentInterventionColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_ContentIntervention";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static ContentIntervention createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(2);
        if (json.has("contentFeature")) {
            excludeFields.add("contentFeature");
        }
        if (json.has("goal")) {
            excludeFields.add("goal");
        }
        ContentIntervention obj = (ContentIntervention) realm.createObjectInternal(ContentIntervention.class, true, excludeFields);
        ContentInterventionRealmProxyInterface objProxy = obj;
        if (json.has("contentFeature")) {
            if (json.isNull("contentFeature")) {
                objProxy.realmSet$contentFeature(null);
            } else {
                objProxy.realmSet$contentFeature(ContentFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("contentFeature"), update));
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
    public static ContentIntervention createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        ContentIntervention obj = new ContentIntervention();
        ContentInterventionRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("contentFeature")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$contentFeature(null);
                } else {
                    objProxy.realmSet$contentFeature(ContentFeatureRealmProxy.createUsingJsonStream(realm, reader));
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
        return (ContentIntervention) realm.copyToRealm(obj);
    }

    public static ContentIntervention copyOrUpdate(Realm realm, ContentIntervention object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (ContentIntervention) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static ContentIntervention copy(Realm realm, ContentIntervention newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (ContentIntervention) cachedRealmObject;
        }
        ContentIntervention realmObject = (ContentIntervention) realm.createObjectInternal(ContentIntervention.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ContentInterventionRealmProxyInterface realmObjectSource = newObject;
        ContentInterventionRealmProxyInterface realmObjectCopy = realmObject;
        ContentFeature contentFeatureObj = realmObjectSource.realmGet$contentFeature();
        if (contentFeatureObj == null) {
            realmObjectCopy.realmSet$contentFeature(null);
        } else {
            ContentFeature cachecontentFeature = (ContentFeature) cache.get(contentFeatureObj);
            if (cachecontentFeature != null) {
                realmObjectCopy.realmSet$contentFeature(cachecontentFeature);
            } else {
                realmObjectCopy.realmSet$contentFeature(ContentFeatureRealmProxy.copyOrUpdate(realm, contentFeatureObj, update, cache));
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

    public static long insert(Realm realm, ContentIntervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(ContentIntervention.class);
        long tableNativePtr = table.getNativePtr();
        ContentInterventionColumnInfo columnInfo = (ContentInterventionColumnInfo) realm.getSchema().getColumnInfo(ContentIntervention.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        ContentFeature contentFeatureObj = object.realmGet$contentFeature();
        if (contentFeatureObj != null) {
            Long cachecontentFeature = (Long) cache.get(contentFeatureObj);
            if (cachecontentFeature == null) {
                cachecontentFeature = Long.valueOf(ContentFeatureRealmProxy.insert(realm, contentFeatureObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.contentFeatureIndex, rowIndex, cachecontentFeature.longValue(), false);
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
        Table table = realm.getTable(ContentIntervention.class);
        long tableNativePtr = table.getNativePtr();
        ContentInterventionColumnInfo columnInfo = (ContentInterventionColumnInfo) realm.getSchema().getColumnInfo(ContentIntervention.class);
        while (objects.hasNext()) {
            ContentIntervention object = (ContentIntervention) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    ContentFeature contentFeatureObj = object.realmGet$contentFeature();
                    if (contentFeatureObj != null) {
                        Long cachecontentFeature = (Long) cache.get(contentFeatureObj);
                        if (cachecontentFeature == null) {
                            cachecontentFeature = Long.valueOf(ContentFeatureRealmProxy.insert(realm, contentFeatureObj, (Map) cache));
                        }
                        table.setLink(columnInfo.contentFeatureIndex, rowIndex, cachecontentFeature.longValue(), false);
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

    public static long insertOrUpdate(Realm realm, ContentIntervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(ContentIntervention.class);
        long tableNativePtr = table.getNativePtr();
        ContentInterventionColumnInfo columnInfo = (ContentInterventionColumnInfo) realm.getSchema().getColumnInfo(ContentIntervention.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        ContentFeature contentFeatureObj = object.realmGet$contentFeature();
        if (contentFeatureObj != null) {
            Long cachecontentFeature = (Long) cache.get(contentFeatureObj);
            if (cachecontentFeature == null) {
                cachecontentFeature = Long.valueOf(ContentFeatureRealmProxy.insertOrUpdate(realm, contentFeatureObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.contentFeatureIndex, rowIndex, cachecontentFeature.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.contentFeatureIndex, rowIndex);
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
        Table table = realm.getTable(ContentIntervention.class);
        long tableNativePtr = table.getNativePtr();
        ContentInterventionColumnInfo columnInfo = (ContentInterventionColumnInfo) realm.getSchema().getColumnInfo(ContentIntervention.class);
        while (objects.hasNext()) {
            ContentIntervention object = (ContentIntervention) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    ContentFeature contentFeatureObj = object.realmGet$contentFeature();
                    if (contentFeatureObj != null) {
                        Long cachecontentFeature = (Long) cache.get(contentFeatureObj);
                        if (cachecontentFeature == null) {
                            cachecontentFeature = Long.valueOf(ContentFeatureRealmProxy.insertOrUpdate(realm, contentFeatureObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.contentFeatureIndex, rowIndex, cachecontentFeature.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.contentFeatureIndex, rowIndex);
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

    public static ContentIntervention createDetachedCopy(ContentIntervention realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        ContentIntervention unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new ContentIntervention();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (ContentIntervention) cachedObject.object;
        } else {
            unmanagedObject = (ContentIntervention) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ContentInterventionRealmProxyInterface unmanagedCopy = unmanagedObject;
        ContentInterventionRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$contentFeature(ContentFeatureRealmProxy.createDetachedCopy(realmSource.realmGet$contentFeature(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$goal(GoalRealmProxy.createDetachedCopy(realmSource.realmGet$goal(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("ContentIntervention = proxy[");
        stringBuilder.append("{contentFeature:");
        stringBuilder.append(realmGet$contentFeature() != null ? "ContentFeature" : "null");
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
        ContentInterventionRealmProxy aContentIntervention = (ContentInterventionRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aContentIntervention.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aContentIntervention.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aContentIntervention.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
