package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.FeedBackInput;
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

public class FeedBackInputRealmProxy extends FeedBackInput implements RealmObjectProxy, FeedBackInputRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private FeedBackInputColumnInfo columnInfo;
    private ProxyState<FeedBackInput> proxyState;

    static final class FeedBackInputColumnInfo extends ColumnInfo {
        long caretakerAlertIndex;
        long guardianAlertIndex;
        long labelIndex;
        long patientAlertIndex;
        long typeIndex;
        long valueIndex;

        FeedBackInputColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("FeedBackInput");
            this.labelIndex = addColumnDetails("label", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.valueIndex = addColumnDetails("value", objectSchemaInfo);
            this.caretakerAlertIndex = addColumnDetails("caretakerAlert", objectSchemaInfo);
            this.guardianAlertIndex = addColumnDetails("guardianAlert", objectSchemaInfo);
            this.patientAlertIndex = addColumnDetails("patientAlert", objectSchemaInfo);
        }

        FeedBackInputColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new FeedBackInputColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            FeedBackInputColumnInfo src = (FeedBackInputColumnInfo) rawSrc;
            FeedBackInputColumnInfo dst = (FeedBackInputColumnInfo) rawDst;
            dst.labelIndex = src.labelIndex;
            dst.typeIndex = src.typeIndex;
            dst.valueIndex = src.valueIndex;
            dst.caretakerAlertIndex = src.caretakerAlertIndex;
            dst.guardianAlertIndex = src.guardianAlertIndex;
            dst.patientAlertIndex = src.patientAlertIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("label");
        fieldNames.add("type");
        fieldNames.add("value");
        fieldNames.add("caretakerAlert");
        fieldNames.add("guardianAlert");
        fieldNames.add("patientAlert");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    FeedBackInputRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (FeedBackInputColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$label() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.labelIndex);
    }

    public void realmSet$label(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.labelIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.labelIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.labelIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.labelIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.typeIndex);
    }

    public void realmSet$type(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.typeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.typeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.typeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.typeIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$value() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.valueIndex);
    }

    public void realmSet$value(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.valueIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.valueIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.valueIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.valueIndex, row.getIndex(), value, true);
            }
        }
    }

    public boolean realmGet$caretakerAlert() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.caretakerAlertIndex);
    }

    public void realmSet$caretakerAlert(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.caretakerAlertIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.caretakerAlertIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$guardianAlert() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.guardianAlertIndex);
    }

    public void realmSet$guardianAlert(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.guardianAlertIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.guardianAlertIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$patientAlert() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.patientAlertIndex);
    }

    public void realmSet$patientAlert(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.patientAlertIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.patientAlertIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("FeedBackInput");
        builder.addPersistedProperty("label", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("value", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("caretakerAlert", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("guardianAlert", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("patientAlert", RealmFieldType.BOOLEAN, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static FeedBackInputColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new FeedBackInputColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_FeedBackInput";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static FeedBackInput createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        FeedBackInput obj = (FeedBackInput) realm.createObjectInternal(FeedBackInput.class, true, Collections.emptyList());
        FeedBackInputRealmProxyInterface objProxy = obj;
        if (json.has("label")) {
            if (json.isNull("label")) {
                objProxy.realmSet$label(null);
            } else {
                objProxy.realmSet$label(json.getString("label"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type(json.getString("type"));
            }
        }
        if (json.has("value")) {
            if (json.isNull("value")) {
                objProxy.realmSet$value(null);
            } else {
                objProxy.realmSet$value(json.getString("value"));
            }
        }
        if (json.has("caretakerAlert")) {
            if (json.isNull("caretakerAlert")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'caretakerAlert' to null.");
            }
            objProxy.realmSet$caretakerAlert(json.getBoolean("caretakerAlert"));
        }
        if (json.has("guardianAlert")) {
            if (json.isNull("guardianAlert")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'guardianAlert' to null.");
            }
            objProxy.realmSet$guardianAlert(json.getBoolean("guardianAlert"));
        }
        if (json.has("patientAlert")) {
            if (json.isNull("patientAlert")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'patientAlert' to null.");
            }
            objProxy.realmSet$patientAlert(json.getBoolean("patientAlert"));
        }
        return obj;
    }

    @TargetApi(11)
    public static FeedBackInput createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        FeedBackInput obj = new FeedBackInput();
        FeedBackInputRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("label")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$label(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$label(null);
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("value")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$value(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$value(null);
                }
            } else if (name.equals("caretakerAlert")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$caretakerAlert(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'caretakerAlert' to null.");
                }
            } else if (name.equals("guardianAlert")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$guardianAlert(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'guardianAlert' to null.");
                }
            } else if (!name.equals("patientAlert")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$patientAlert(reader.nextBoolean());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'patientAlert' to null.");
            }
        }
        reader.endObject();
        return (FeedBackInput) realm.copyToRealm(obj);
    }

    public static FeedBackInput copyOrUpdate(Realm realm, FeedBackInput object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (FeedBackInput) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static FeedBackInput copy(Realm realm, FeedBackInput newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (FeedBackInput) cachedRealmObject;
        }
        FeedBackInput realmObject = (FeedBackInput) realm.createObjectInternal(FeedBackInput.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        FeedBackInputRealmProxyInterface realmObjectSource = newObject;
        FeedBackInputRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$label(realmObjectSource.realmGet$label());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$value(realmObjectSource.realmGet$value());
        realmObjectCopy.realmSet$caretakerAlert(realmObjectSource.realmGet$caretakerAlert());
        realmObjectCopy.realmSet$guardianAlert(realmObjectSource.realmGet$guardianAlert());
        realmObjectCopy.realmSet$patientAlert(realmObjectSource.realmGet$patientAlert());
        return realmObject;
    }

    public static long insert(Realm realm, FeedBackInput object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(FeedBackInput.class);
        long tableNativePtr = table.getNativePtr();
        FeedBackInputColumnInfo columnInfo = (FeedBackInputColumnInfo) realm.getSchema().getColumnInfo(FeedBackInput.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$label = object.realmGet$label();
        if (realmGet$label != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }
        String realmGet$value = object.realmGet$value();
        if (realmGet$value != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.caretakerAlertIndex, rowIndex, object.realmGet$caretakerAlert(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.guardianAlertIndex, rowIndex, object.realmGet$guardianAlert(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.patientAlertIndex, rowIndex, object.realmGet$patientAlert(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(FeedBackInput.class);
        long tableNativePtr = table.getNativePtr();
        FeedBackInputColumnInfo columnInfo = (FeedBackInputColumnInfo) realm.getSchema().getColumnInfo(FeedBackInput.class);
        while (objects.hasNext()) {
            FeedBackInput object = (FeedBackInput) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$label = object.realmGet$label();
                    if (realmGet$label != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    }
                    String realmGet$value = object.realmGet$value();
                    if (realmGet$value != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.caretakerAlertIndex, rowIndex, object.realmGet$caretakerAlert(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.guardianAlertIndex, rowIndex, object.realmGet$guardianAlert(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.patientAlertIndex, rowIndex, object.realmGet$patientAlert(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, FeedBackInput object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(FeedBackInput.class);
        long tableNativePtr = table.getNativePtr();
        FeedBackInputColumnInfo columnInfo = (FeedBackInputColumnInfo) realm.getSchema().getColumnInfo(FeedBackInput.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$label = object.realmGet$label();
        if (realmGet$label != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.labelIndex, rowIndex, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        }
        String realmGet$value = object.realmGet$value();
        if (realmGet$value != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.valueIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.caretakerAlertIndex, rowIndex, object.realmGet$caretakerAlert(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.guardianAlertIndex, rowIndex, object.realmGet$guardianAlert(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.patientAlertIndex, rowIndex, object.realmGet$patientAlert(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(FeedBackInput.class);
        long tableNativePtr = table.getNativePtr();
        FeedBackInputColumnInfo columnInfo = (FeedBackInputColumnInfo) realm.getSchema().getColumnInfo(FeedBackInput.class);
        while (objects.hasNext()) {
            FeedBackInput object = (FeedBackInput) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$label = object.realmGet$label();
                    if (realmGet$label != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.labelIndex, rowIndex, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
                    }
                    String realmGet$value = object.realmGet$value();
                    if (realmGet$value != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.valueIndex, rowIndex, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.caretakerAlertIndex, rowIndex, object.realmGet$caretakerAlert(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.guardianAlertIndex, rowIndex, object.realmGet$guardianAlert(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.patientAlertIndex, rowIndex, object.realmGet$patientAlert(), false);
                }
            }
        }
    }

    public static FeedBackInput createDetachedCopy(FeedBackInput realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        FeedBackInput unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new FeedBackInput();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (FeedBackInput) cachedObject.object;
        } else {
            unmanagedObject = (FeedBackInput) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        FeedBackInputRealmProxyInterface unmanagedCopy = unmanagedObject;
        FeedBackInputRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$label(realmSource.realmGet$label());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$value(realmSource.realmGet$value());
        unmanagedCopy.realmSet$caretakerAlert(realmSource.realmGet$caretakerAlert());
        unmanagedCopy.realmSet$guardianAlert(realmSource.realmGet$guardianAlert());
        unmanagedCopy.realmSet$patientAlert(realmSource.realmGet$patientAlert());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("FeedBackInput = proxy[");
        stringBuilder.append("{label:");
        stringBuilder.append(realmGet$label() != null ? realmGet$label() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{value:");
        stringBuilder.append(realmGet$value() != null ? realmGet$value() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{caretakerAlert:");
        stringBuilder.append(realmGet$caretakerAlert());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{guardianAlert:");
        stringBuilder.append(realmGet$guardianAlert());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{patientAlert:");
        stringBuilder.append(realmGet$patientAlert());
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
        FeedBackInputRealmProxy aFeedBackInput = (FeedBackInputRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aFeedBackInput.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFeedBackInput.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aFeedBackInput.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
