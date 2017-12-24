package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.vo.careplan.Diagnosis;
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

public class DiagnosisRealmProxy extends Diagnosis implements RealmObjectProxy, DiagnosisRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private DiagnosisColumnInfo columnInfo;
    private ProxyState<Diagnosis> proxyState;

    static final class DiagnosisColumnInfo extends ColumnInfo {
        long healthConditionsIndex;
        long symoptomsIndex;

        DiagnosisColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo(CTConstants.DIAGNOSIS);
            this.healthConditionsIndex = addColumnDetails("healthConditions", objectSchemaInfo);
            this.symoptomsIndex = addColumnDetails("symoptoms", objectSchemaInfo);
        }

        DiagnosisColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new DiagnosisColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            DiagnosisColumnInfo src = (DiagnosisColumnInfo) rawSrc;
            DiagnosisColumnInfo dst = (DiagnosisColumnInfo) rawDst;
            dst.healthConditionsIndex = src.healthConditionsIndex;
            dst.symoptomsIndex = src.symoptomsIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("healthConditions");
        fieldNames.add("symoptoms");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DiagnosisRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (DiagnosisColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$healthConditions() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.healthConditionsIndex);
    }

    public void realmSet$healthConditions(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.healthConditionsIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.healthConditionsIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.healthConditionsIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.healthConditionsIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$symoptoms() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.symoptomsIndex);
    }

    public void realmSet$symoptoms(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.symoptomsIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.symoptomsIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.symoptomsIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.symoptomsIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder(CTConstants.DIAGNOSIS);
        builder.addPersistedProperty("healthConditions", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("symoptoms", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static DiagnosisColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new DiagnosisColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Diagnosis";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Diagnosis createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Diagnosis obj = (Diagnosis) realm.createObjectInternal(Diagnosis.class, true, Collections.emptyList());
        DiagnosisRealmProxyInterface objProxy = obj;
        if (json.has("healthConditions")) {
            if (json.isNull("healthConditions")) {
                objProxy.realmSet$healthConditions(null);
            } else {
                objProxy.realmSet$healthConditions(json.getString("healthConditions"));
            }
        }
        if (json.has("symoptoms")) {
            if (json.isNull("symoptoms")) {
                objProxy.realmSet$symoptoms(null);
            } else {
                objProxy.realmSet$symoptoms(json.getString("symoptoms"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Diagnosis createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Diagnosis obj = new Diagnosis();
        DiagnosisRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("healthConditions")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$healthConditions(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$healthConditions(null);
                }
            } else if (!name.equals("symoptoms")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$symoptoms(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$symoptoms(null);
            }
        }
        reader.endObject();
        return (Diagnosis) realm.copyToRealm(obj);
    }

    public static Diagnosis copyOrUpdate(Realm realm, Diagnosis object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Diagnosis) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Diagnosis copy(Realm realm, Diagnosis newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Diagnosis) cachedRealmObject;
        }
        Diagnosis realmObject = (Diagnosis) realm.createObjectInternal(Diagnosis.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        DiagnosisRealmProxyInterface realmObjectSource = newObject;
        DiagnosisRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$healthConditions(realmObjectSource.realmGet$healthConditions());
        realmObjectCopy.realmSet$symoptoms(realmObjectSource.realmGet$symoptoms());
        return realmObject;
    }

    public static long insert(Realm realm, Diagnosis object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Diagnosis.class);
        long tableNativePtr = table.getNativePtr();
        DiagnosisColumnInfo columnInfo = (DiagnosisColumnInfo) realm.getSchema().getColumnInfo(Diagnosis.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$healthConditions = object.realmGet$healthConditions();
        if (realmGet$healthConditions != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.healthConditionsIndex, rowIndex, realmGet$healthConditions, false);
        }
        String realmGet$symoptoms = object.realmGet$symoptoms();
        if (realmGet$symoptoms == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.symoptomsIndex, rowIndex, realmGet$symoptoms, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Diagnosis.class);
        long tableNativePtr = table.getNativePtr();
        DiagnosisColumnInfo columnInfo = (DiagnosisColumnInfo) realm.getSchema().getColumnInfo(Diagnosis.class);
        while (objects.hasNext()) {
            Diagnosis object = (Diagnosis) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$healthConditions = object.realmGet$healthConditions();
                    if (realmGet$healthConditions != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.healthConditionsIndex, rowIndex, realmGet$healthConditions, false);
                    }
                    String realmGet$symoptoms = object.realmGet$symoptoms();
                    if (realmGet$symoptoms != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.symoptomsIndex, rowIndex, realmGet$symoptoms, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Diagnosis object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Diagnosis.class);
        long tableNativePtr = table.getNativePtr();
        DiagnosisColumnInfo columnInfo = (DiagnosisColumnInfo) realm.getSchema().getColumnInfo(Diagnosis.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$healthConditions = object.realmGet$healthConditions();
        if (realmGet$healthConditions != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.healthConditionsIndex, rowIndex, realmGet$healthConditions, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.healthConditionsIndex, rowIndex, false);
        }
        String realmGet$symoptoms = object.realmGet$symoptoms();
        if (realmGet$symoptoms != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.symoptomsIndex, rowIndex, realmGet$symoptoms, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.symoptomsIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Diagnosis.class);
        long tableNativePtr = table.getNativePtr();
        DiagnosisColumnInfo columnInfo = (DiagnosisColumnInfo) realm.getSchema().getColumnInfo(Diagnosis.class);
        while (objects.hasNext()) {
            Diagnosis object = (Diagnosis) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$healthConditions = object.realmGet$healthConditions();
                    if (realmGet$healthConditions != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.healthConditionsIndex, rowIndex, realmGet$healthConditions, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.healthConditionsIndex, rowIndex, false);
                    }
                    String realmGet$symoptoms = object.realmGet$symoptoms();
                    if (realmGet$symoptoms != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.symoptomsIndex, rowIndex, realmGet$symoptoms, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.symoptomsIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Diagnosis createDetachedCopy(Diagnosis realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Diagnosis unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Diagnosis();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Diagnosis) cachedObject.object;
        } else {
            unmanagedObject = (Diagnosis) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        DiagnosisRealmProxyInterface unmanagedCopy = unmanagedObject;
        DiagnosisRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$healthConditions(realmSource.realmGet$healthConditions());
        unmanagedCopy.realmSet$symoptoms(realmSource.realmGet$symoptoms());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Diagnosis = proxy[");
        stringBuilder.append("{healthConditions:");
        stringBuilder.append(realmGet$healthConditions() != null ? realmGet$healthConditions() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{symoptoms:");
        stringBuilder.append(realmGet$symoptoms() != null ? realmGet$symoptoms() : "null");
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
        DiagnosisRealmProxy aDiagnosis = (DiagnosisRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aDiagnosis.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDiagnosis.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aDiagnosis.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
