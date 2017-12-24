package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.Subjective;
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

public class SubjectiveRealmProxy extends Subjective implements RealmObjectProxy, SubjectiveRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private SubjectiveColumnInfo columnInfo;
    private ProxyState<Subjective> proxyState;

    static final class SubjectiveColumnInfo extends ColumnInfo {
        long noteIndex;

        SubjectiveColumnInfo(OsSchemaInfo schemaInfo) {
            super(1);
            this.noteIndex = addColumnDetails("note", schemaInfo.getObjectSchemaInfo("Subjective"));
        }

        SubjectiveColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new SubjectiveColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ((SubjectiveColumnInfo) rawDst).noteIndex = ((SubjectiveColumnInfo) rawSrc).noteIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("note");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    SubjectiveRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (SubjectiveColumnInfo) context.getColumnInfo();
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Subjective");
        builder.addPersistedProperty("note", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static SubjectiveColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new SubjectiveColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Subjective";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Subjective createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Subjective obj = (Subjective) realm.createObjectInternal(Subjective.class, true, Collections.emptyList());
        SubjectiveRealmProxyInterface objProxy = obj;
        if (json.has("note")) {
            if (json.isNull("note")) {
                objProxy.realmSet$note(null);
            } else {
                objProxy.realmSet$note(json.getString("note"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Subjective createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Subjective obj = new Subjective();
        SubjectiveRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            if (!reader.nextName().equals("note")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$note(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$note(null);
            }
        }
        reader.endObject();
        return (Subjective) realm.copyToRealm(obj);
    }

    public static Subjective copyOrUpdate(Realm realm, Subjective object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Subjective) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Subjective copy(Realm realm, Subjective newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Subjective) cachedRealmObject;
        }
        Subjective realmObject = (Subjective) realm.createObjectInternal(Subjective.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.realmSet$note(newObject.realmGet$note());
        return realmObject;
    }

    public static long insert(Realm realm, Subjective object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Subjective.class);
        long tableNativePtr = table.getNativePtr();
        SubjectiveColumnInfo columnInfo = (SubjectiveColumnInfo) realm.getSchema().getColumnInfo(Subjective.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$note = object.realmGet$note();
        if (realmGet$note == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Subjective.class);
        long tableNativePtr = table.getNativePtr();
        SubjectiveColumnInfo columnInfo = (SubjectiveColumnInfo) realm.getSchema().getColumnInfo(Subjective.class);
        while (objects.hasNext()) {
            Subjective object = (Subjective) objects.next();
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
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Subjective object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Subjective.class);
        long tableNativePtr = table.getNativePtr();
        SubjectiveColumnInfo columnInfo = (SubjectiveColumnInfo) realm.getSchema().getColumnInfo(Subjective.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$note = object.realmGet$note();
        if (realmGet$note != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.noteIndex, rowIndex, realmGet$note, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.noteIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Subjective.class);
        long tableNativePtr = table.getNativePtr();
        SubjectiveColumnInfo columnInfo = (SubjectiveColumnInfo) realm.getSchema().getColumnInfo(Subjective.class);
        while (objects.hasNext()) {
            Subjective object = (Subjective) objects.next();
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
                }
            }
        }
    }

    public static Subjective createDetachedCopy(Subjective realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Subjective unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Subjective();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Subjective) cachedObject.object;
        } else {
            unmanagedObject = (Subjective) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        unmanagedObject.realmSet$note(realmObject.realmGet$note());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Subjective = proxy[");
        stringBuilder.append("{note:");
        stringBuilder.append(realmGet$note() != null ? realmGet$note() : "null");
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
        SubjectiveRealmProxy aSubjective = (SubjectiveRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aSubjective.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSubjective.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aSubjective.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
