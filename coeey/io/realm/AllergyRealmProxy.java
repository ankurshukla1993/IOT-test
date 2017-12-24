package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Allergy;
import com.cooey.common.vo.RealmString;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AllergyRealmProxy extends Allergy implements RealmObjectProxy, AllergyRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private AllergyColumnInfo columnInfo;
    private ProxyState<Allergy> proxyState;
    private RealmList<RealmString> symptomsRealmList;

    static final class AllergyColumnInfo extends ColumnInfo {
        long levelValueIndex;
        long nameIndex;
        long symptomsIndex;

        AllergyColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Allergy");
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.levelValueIndex = addColumnDetails("levelValue", objectSchemaInfo);
            this.symptomsIndex = addColumnDetails("symptoms", objectSchemaInfo);
        }

        AllergyColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new AllergyColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            AllergyColumnInfo src = (AllergyColumnInfo) rawSrc;
            AllergyColumnInfo dst = (AllergyColumnInfo) rawDst;
            dst.nameIndex = src.nameIndex;
            dst.levelValueIndex = src.levelValueIndex;
            dst.symptomsIndex = src.symptomsIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("name");
        fieldNames.add("levelValue");
        fieldNames.add("symptoms");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    AllergyRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (AllergyColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$name() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.nameIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.nameIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.nameIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.nameIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$levelValue() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.levelValueIndex);
    }

    public void realmSet$levelValue(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.levelValueIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.levelValueIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.levelValueIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.levelValueIndex, row.getIndex(), value, true);
            }
        }
    }

    public RealmList<RealmString> realmGet$symptoms() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.symptomsRealmList != null) {
            return this.symptomsRealmList;
        }
        this.symptomsRealmList = new RealmList(RealmString.class, this.proxyState.getRow$realm().getLinkList(this.columnInfo.symptomsIndex), this.proxyState.getRealm$realm());
        return this.symptomsRealmList;
    }

    public void realmSet$symptoms(RealmList<RealmString> value) {
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("symptoms")) {
                return;
            }
            if (!(value == null || value.isManaged())) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<RealmString> original = value;
                value = new RealmList();
                Iterator it = original.iterator();
                while (it.hasNext()) {
                    RealmString item = (RealmString) it.next();
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList osList = this.proxyState.getRow$realm().getLinkList(this.columnInfo.symptomsIndex);
        osList.removeAll();
        if (value != null) {
            Iterator it2 = value.iterator();
            while (it2.hasNext()) {
                RealmModel linkedObject = (RealmModel) it2.next();
                if (!RealmObject.isManaged(linkedObject) || !RealmObject.isValid(linkedObject)) {
                    throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
                } else if (((RealmObjectProxy) linkedObject).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                    throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
                } else {
                    osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
                }
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Allergy");
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("levelValue", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("symptoms", RealmFieldType.LIST, "RealmString");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AllergyColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new AllergyColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Allergy";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Allergy createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        if (json.has("symptoms")) {
            excludeFields.add("symptoms");
        }
        Allergy obj = (Allergy) realm.createObjectInternal(Allergy.class, true, excludeFields);
        AllergyRealmProxyInterface objProxy = obj;
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name(json.getString("name"));
            }
        }
        if (json.has("levelValue")) {
            if (json.isNull("levelValue")) {
                objProxy.realmSet$levelValue(null);
            } else {
                objProxy.realmSet$levelValue(json.getString("levelValue"));
            }
        }
        if (json.has("symptoms")) {
            if (json.isNull("symptoms")) {
                objProxy.realmSet$symptoms(null);
            } else {
                objProxy.realmGet$symptoms().clear();
                JSONArray array = json.getJSONArray("symptoms");
                for (int i = 0; i < array.length(); i++) {
                    objProxy.realmGet$symptoms().add(RealmStringRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update));
                }
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Allergy createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Allergy obj = new Allergy();
        AllergyRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("levelValue")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$levelValue(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$levelValue(null);
                }
            } else if (!name.equals("symptoms")) {
                reader.skipValue();
            } else if (reader.peek() == JsonToken.NULL) {
                reader.skipValue();
                objProxy.realmSet$symptoms(null);
            } else {
                objProxy.realmSet$symptoms(new RealmList());
                reader.beginArray();
                while (reader.hasNext()) {
                    objProxy.realmGet$symptoms().add(RealmStringRealmProxy.createUsingJsonStream(realm, reader));
                }
                reader.endArray();
            }
        }
        reader.endObject();
        return (Allergy) realm.copyToRealm(obj);
    }

    public static Allergy copyOrUpdate(Realm realm, Allergy object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Allergy) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Allergy copy(Realm realm, Allergy newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Allergy) cachedRealmObject;
        }
        Allergy realmObject = (Allergy) realm.createObjectInternal(Allergy.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        AllergyRealmProxyInterface realmObjectSource = newObject;
        AllergyRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$levelValue(realmObjectSource.realmGet$levelValue());
        RealmList<RealmString> symptomsList = realmObjectSource.realmGet$symptoms();
        if (symptomsList != null) {
            RealmList<RealmString> symptomsRealmList = realmObjectCopy.realmGet$symptoms();
            symptomsRealmList.clear();
            for (int i = 0; i < symptomsList.size(); i++) {
                RealmString symptomsItem = (RealmString) symptomsList.get(i);
                RealmString cachesymptoms = (RealmString) cache.get(symptomsItem);
                if (cachesymptoms != null) {
                    symptomsRealmList.add(cachesymptoms);
                } else {
                    symptomsRealmList.add(RealmStringRealmProxy.copyOrUpdate(realm, symptomsItem, update, cache));
                }
            }
        }
        return realmObject;
    }

    public static long insert(Realm realm, Allergy object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Allergy.class);
        long tableNativePtr = table.getNativePtr();
        AllergyColumnInfo columnInfo = (AllergyColumnInfo) realm.getSchema().getColumnInfo(Allergy.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$levelValue = object.realmGet$levelValue();
        if (realmGet$levelValue != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.levelValueIndex, rowIndex, realmGet$levelValue, false);
        }
        RealmList<RealmString> symptomsList = object.realmGet$symptoms();
        if (symptomsList == null) {
            return rowIndex;
        }
        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.symptomsIndex);
        Iterator it = symptomsList.iterator();
        while (it.hasNext()) {
            RealmString symptomsItem = (RealmString) it.next();
            Long cacheItemIndexsymptoms = (Long) cache.get(symptomsItem);
            if (cacheItemIndexsymptoms == null) {
                cacheItemIndexsymptoms = Long.valueOf(RealmStringRealmProxy.insert(realm, symptomsItem, (Map) cache));
            }
            osList.addRow(cacheItemIndexsymptoms.longValue());
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Allergy.class);
        long tableNativePtr = table.getNativePtr();
        AllergyColumnInfo columnInfo = (AllergyColumnInfo) realm.getSchema().getColumnInfo(Allergy.class);
        while (objects.hasNext()) {
            Allergy object = (Allergy) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    String realmGet$levelValue = object.realmGet$levelValue();
                    if (realmGet$levelValue != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.levelValueIndex, rowIndex, realmGet$levelValue, false);
                    }
                    RealmList<RealmString> symptomsList = object.realmGet$symptoms();
                    if (symptomsList != null) {
                        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.symptomsIndex);
                        Iterator it = symptomsList.iterator();
                        while (it.hasNext()) {
                            RealmString symptomsItem = (RealmString) it.next();
                            Long cacheItemIndexsymptoms = (Long) cache.get(symptomsItem);
                            if (cacheItemIndexsymptoms == null) {
                                cacheItemIndexsymptoms = Long.valueOf(RealmStringRealmProxy.insert(realm, symptomsItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexsymptoms.longValue());
                        }
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Allergy object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Allergy.class);
        long tableNativePtr = table.getNativePtr();
        AllergyColumnInfo columnInfo = (AllergyColumnInfo) realm.getSchema().getColumnInfo(Allergy.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$levelValue = object.realmGet$levelValue();
        if (realmGet$levelValue != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.levelValueIndex, rowIndex, realmGet$levelValue, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.levelValueIndex, rowIndex, false);
        }
        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.symptomsIndex);
        osList.removeAll();
        RealmList<RealmString> symptomsList = object.realmGet$symptoms();
        if (symptomsList == null) {
            return rowIndex;
        }
        Iterator it = symptomsList.iterator();
        while (it.hasNext()) {
            RealmString symptomsItem = (RealmString) it.next();
            Long cacheItemIndexsymptoms = (Long) cache.get(symptomsItem);
            if (cacheItemIndexsymptoms == null) {
                cacheItemIndexsymptoms = Long.valueOf(RealmStringRealmProxy.insertOrUpdate(realm, symptomsItem, (Map) cache));
            }
            osList.addRow(cacheItemIndexsymptoms.longValue());
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Allergy.class);
        long tableNativePtr = table.getNativePtr();
        AllergyColumnInfo columnInfo = (AllergyColumnInfo) realm.getSchema().getColumnInfo(Allergy.class);
        while (objects.hasNext()) {
            Allergy object = (Allergy) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                    }
                    String realmGet$levelValue = object.realmGet$levelValue();
                    if (realmGet$levelValue != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.levelValueIndex, rowIndex, realmGet$levelValue, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.levelValueIndex, rowIndex, false);
                    }
                    OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.symptomsIndex);
                    osList.removeAll();
                    RealmList<RealmString> symptomsList = object.realmGet$symptoms();
                    if (symptomsList != null) {
                        Iterator it = symptomsList.iterator();
                        while (it.hasNext()) {
                            RealmString symptomsItem = (RealmString) it.next();
                            Long cacheItemIndexsymptoms = (Long) cache.get(symptomsItem);
                            if (cacheItemIndexsymptoms == null) {
                                cacheItemIndexsymptoms = Long.valueOf(RealmStringRealmProxy.insertOrUpdate(realm, symptomsItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexsymptoms.longValue());
                        }
                    }
                }
            }
        }
    }

    public static Allergy createDetachedCopy(Allergy realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Allergy unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Allergy();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Allergy) cachedObject.object;
        } else {
            unmanagedObject = (Allergy) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        AllergyRealmProxyInterface unmanagedCopy = unmanagedObject;
        AllergyRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$levelValue(realmSource.realmGet$levelValue());
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$symptoms(null);
        } else {
            RealmList<RealmString> managedsymptomsList = realmSource.realmGet$symptoms();
            RealmList<RealmString> unmanagedsymptomsList = new RealmList();
            unmanagedCopy.realmSet$symptoms(unmanagedsymptomsList);
            int nextDepth = currentDepth + 1;
            int size = managedsymptomsList.size();
            for (int i = 0; i < size; i++) {
                unmanagedsymptomsList.add(RealmStringRealmProxy.createDetachedCopy((RealmString) managedsymptomsList.get(i), nextDepth, maxDepth, cache));
            }
        }
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Allergy = proxy[");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{levelValue:");
        stringBuilder.append(realmGet$levelValue() != null ? realmGet$levelValue() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{symptoms:");
        stringBuilder.append("RealmList<RealmString>[").append(realmGet$symptoms().size()).append("]");
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
        AllergyRealmProxy aAllergy = (AllergyRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aAllergy.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aAllergy.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aAllergy.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
