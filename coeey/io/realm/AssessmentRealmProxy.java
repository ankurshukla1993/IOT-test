package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.vo.careplan.Assessment;
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

public class AssessmentRealmProxy extends Assessment implements RealmObjectProxy, AssessmentRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private AssessmentColumnInfo columnInfo;
    private ProxyState<Assessment> proxyState;

    static final class AssessmentColumnInfo extends ColumnInfo {
        long objectiveIndex;
        long subjectiveIndex;

        AssessmentColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo(CTConstants.ASSESSMENT);
            this.subjectiveIndex = addColumnDetails("subjective", objectSchemaInfo);
            this.objectiveIndex = addColumnDetails("objective", objectSchemaInfo);
        }

        AssessmentColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new AssessmentColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            AssessmentColumnInfo src = (AssessmentColumnInfo) rawSrc;
            AssessmentColumnInfo dst = (AssessmentColumnInfo) rawDst;
            dst.subjectiveIndex = src.subjectiveIndex;
            dst.objectiveIndex = src.objectiveIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("subjective");
        fieldNames.add("objective");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    AssessmentRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (AssessmentColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$subjective() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.subjectiveIndex);
    }

    public void realmSet$subjective(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.subjectiveIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.subjectiveIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.subjectiveIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.subjectiveIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$objective() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.objectiveIndex);
    }

    public void realmSet$objective(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.objectiveIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.objectiveIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.objectiveIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.objectiveIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder(CTConstants.ASSESSMENT);
        builder.addPersistedProperty("subjective", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("objective", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AssessmentColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new AssessmentColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Assessment";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Assessment createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Assessment obj = (Assessment) realm.createObjectInternal(Assessment.class, true, Collections.emptyList());
        AssessmentRealmProxyInterface objProxy = obj;
        if (json.has("subjective")) {
            if (json.isNull("subjective")) {
                objProxy.realmSet$subjective(null);
            } else {
                objProxy.realmSet$subjective(json.getString("subjective"));
            }
        }
        if (json.has("objective")) {
            if (json.isNull("objective")) {
                objProxy.realmSet$objective(null);
            } else {
                objProxy.realmSet$objective(json.getString("objective"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Assessment createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Assessment obj = new Assessment();
        AssessmentRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("subjective")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$subjective(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$subjective(null);
                }
            } else if (!name.equals("objective")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$objective(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$objective(null);
            }
        }
        reader.endObject();
        return (Assessment) realm.copyToRealm(obj);
    }

    public static Assessment copyOrUpdate(Realm realm, Assessment object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Assessment) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Assessment copy(Realm realm, Assessment newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Assessment) cachedRealmObject;
        }
        Assessment realmObject = (Assessment) realm.createObjectInternal(Assessment.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        AssessmentRealmProxyInterface realmObjectSource = newObject;
        AssessmentRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$subjective(realmObjectSource.realmGet$subjective());
        realmObjectCopy.realmSet$objective(realmObjectSource.realmGet$objective());
        return realmObject;
    }

    public static long insert(Realm realm, Assessment object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Assessment.class);
        long tableNativePtr = table.getNativePtr();
        AssessmentColumnInfo columnInfo = (AssessmentColumnInfo) realm.getSchema().getColumnInfo(Assessment.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$subjective = object.realmGet$subjective();
        if (realmGet$subjective != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.subjectiveIndex, rowIndex, realmGet$subjective, false);
        }
        String realmGet$objective = object.realmGet$objective();
        if (realmGet$objective == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.objectiveIndex, rowIndex, realmGet$objective, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Assessment.class);
        long tableNativePtr = table.getNativePtr();
        AssessmentColumnInfo columnInfo = (AssessmentColumnInfo) realm.getSchema().getColumnInfo(Assessment.class);
        while (objects.hasNext()) {
            Assessment object = (Assessment) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$subjective = object.realmGet$subjective();
                    if (realmGet$subjective != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.subjectiveIndex, rowIndex, realmGet$subjective, false);
                    }
                    String realmGet$objective = object.realmGet$objective();
                    if (realmGet$objective != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.objectiveIndex, rowIndex, realmGet$objective, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Assessment object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Assessment.class);
        long tableNativePtr = table.getNativePtr();
        AssessmentColumnInfo columnInfo = (AssessmentColumnInfo) realm.getSchema().getColumnInfo(Assessment.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$subjective = object.realmGet$subjective();
        if (realmGet$subjective != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.subjectiveIndex, rowIndex, realmGet$subjective, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.subjectiveIndex, rowIndex, false);
        }
        String realmGet$objective = object.realmGet$objective();
        if (realmGet$objective != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.objectiveIndex, rowIndex, realmGet$objective, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.objectiveIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Assessment.class);
        long tableNativePtr = table.getNativePtr();
        AssessmentColumnInfo columnInfo = (AssessmentColumnInfo) realm.getSchema().getColumnInfo(Assessment.class);
        while (objects.hasNext()) {
            Assessment object = (Assessment) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$subjective = object.realmGet$subjective();
                    if (realmGet$subjective != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.subjectiveIndex, rowIndex, realmGet$subjective, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.subjectiveIndex, rowIndex, false);
                    }
                    String realmGet$objective = object.realmGet$objective();
                    if (realmGet$objective != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.objectiveIndex, rowIndex, realmGet$objective, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.objectiveIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Assessment createDetachedCopy(Assessment realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Assessment unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Assessment();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Assessment) cachedObject.object;
        } else {
            unmanagedObject = (Assessment) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        AssessmentRealmProxyInterface unmanagedCopy = unmanagedObject;
        AssessmentRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$subjective(realmSource.realmGet$subjective());
        unmanagedCopy.realmSet$objective(realmSource.realmGet$objective());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Assessment = proxy[");
        stringBuilder.append("{subjective:");
        stringBuilder.append(realmGet$subjective() != null ? realmGet$subjective() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{objective:");
        stringBuilder.append(realmGet$objective() != null ? realmGet$objective() : "null");
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
        AssessmentRealmProxy aAssessment = (AssessmentRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aAssessment.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aAssessment.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aAssessment.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
