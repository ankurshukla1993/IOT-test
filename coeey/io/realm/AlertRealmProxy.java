package io.realm;

import android.annotation.TargetApi;
import android.support.v4.app.NotificationCompat;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.Alert;
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

public class AlertRealmProxy extends Alert implements RealmObjectProxy, AlertRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private AlertColumnInfo columnInfo;
    private ProxyState<Alert> proxyState;

    static final class AlertColumnInfo extends ColumnInfo {
        long alertTypeIndex;
        long callIndex;
        long emailIndex;
        long notificationIndex;
        long smsIndex;

        AlertColumnInfo(OsSchemaInfo schemaInfo) {
            super(5);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Alert");
            this.alertTypeIndex = addColumnDetails("alertType", objectSchemaInfo);
            this.callIndex = addColumnDetails(NotificationCompat.CATEGORY_CALL, objectSchemaInfo);
            this.emailIndex = addColumnDetails("email", objectSchemaInfo);
            this.smsIndex = addColumnDetails("sms", objectSchemaInfo);
            this.notificationIndex = addColumnDetails("notification", objectSchemaInfo);
        }

        AlertColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new AlertColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            AlertColumnInfo src = (AlertColumnInfo) rawSrc;
            AlertColumnInfo dst = (AlertColumnInfo) rawDst;
            dst.alertTypeIndex = src.alertTypeIndex;
            dst.callIndex = src.callIndex;
            dst.emailIndex = src.emailIndex;
            dst.smsIndex = src.smsIndex;
            dst.notificationIndex = src.notificationIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("alertType");
        fieldNames.add(NotificationCompat.CATEGORY_CALL);
        fieldNames.add("email");
        fieldNames.add("sms");
        fieldNames.add("notification");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    AlertRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (AlertColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$alertType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.alertTypeIndex);
    }

    public void realmSet$alertType(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.alertTypeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.alertTypeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.alertTypeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.alertTypeIndex, row.getIndex(), value, true);
            }
        }
    }

    public boolean realmGet$call() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.callIndex);
    }

    public void realmSet$call(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.callIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.callIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$email() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.emailIndex);
    }

    public void realmSet$email(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.emailIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.emailIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$sms() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.smsIndex);
    }

    public void realmSet$sms(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.smsIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.smsIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$notification() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.notificationIndex);
    }

    public void realmSet$notification(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.notificationIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.notificationIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Alert");
        builder.addPersistedProperty("alertType", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(NotificationCompat.CATEGORY_CALL, RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("email", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("sms", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("notification", RealmFieldType.BOOLEAN, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AlertColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new AlertColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Alert";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Alert createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Alert obj = (Alert) realm.createObjectInternal(Alert.class, true, Collections.emptyList());
        AlertRealmProxyInterface objProxy = obj;
        if (json.has("alertType")) {
            if (json.isNull("alertType")) {
                objProxy.realmSet$alertType(null);
            } else {
                objProxy.realmSet$alertType(json.getString("alertType"));
            }
        }
        if (json.has(NotificationCompat.CATEGORY_CALL)) {
            if (json.isNull(NotificationCompat.CATEGORY_CALL)) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'call' to null.");
            }
            objProxy.realmSet$call(json.getBoolean(NotificationCompat.CATEGORY_CALL));
        }
        if (json.has("email")) {
            if (json.isNull("email")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'email' to null.");
            }
            objProxy.realmSet$email(json.getBoolean("email"));
        }
        if (json.has("sms")) {
            if (json.isNull("sms")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'sms' to null.");
            }
            objProxy.realmSet$sms(json.getBoolean("sms"));
        }
        if (json.has("notification")) {
            if (json.isNull("notification")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'notification' to null.");
            }
            objProxy.realmSet$notification(json.getBoolean("notification"));
        }
        return obj;
    }

    @TargetApi(11)
    public static Alert createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Alert obj = new Alert();
        AlertRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("alertType")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$alertType(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$alertType(null);
                }
            } else if (name.equals(NotificationCompat.CATEGORY_CALL)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$call(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'call' to null.");
                }
            } else if (name.equals("email")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$email(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'email' to null.");
                }
            } else if (name.equals("sms")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$sms(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'sms' to null.");
                }
            } else if (!name.equals("notification")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$notification(reader.nextBoolean());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'notification' to null.");
            }
        }
        reader.endObject();
        return (Alert) realm.copyToRealm(obj);
    }

    public static Alert copyOrUpdate(Realm realm, Alert object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Alert) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Alert copy(Realm realm, Alert newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Alert) cachedRealmObject;
        }
        Alert realmObject = (Alert) realm.createObjectInternal(Alert.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        AlertRealmProxyInterface realmObjectSource = newObject;
        AlertRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$alertType(realmObjectSource.realmGet$alertType());
        realmObjectCopy.realmSet$call(realmObjectSource.realmGet$call());
        realmObjectCopy.realmSet$email(realmObjectSource.realmGet$email());
        realmObjectCopy.realmSet$sms(realmObjectSource.realmGet$sms());
        realmObjectCopy.realmSet$notification(realmObjectSource.realmGet$notification());
        return realmObject;
    }

    public static long insert(Realm realm, Alert object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Alert.class);
        long tableNativePtr = table.getNativePtr();
        AlertColumnInfo columnInfo = (AlertColumnInfo) realm.getSchema().getColumnInfo(Alert.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$alertType = object.realmGet$alertType();
        if (realmGet$alertType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.alertTypeIndex, rowIndex, realmGet$alertType, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.callIndex, rowIndex, object.realmGet$call(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.emailIndex, rowIndex, object.realmGet$email(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.smsIndex, rowIndex, object.realmGet$sms(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.notificationIndex, rowIndex, object.realmGet$notification(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Alert.class);
        long tableNativePtr = table.getNativePtr();
        AlertColumnInfo columnInfo = (AlertColumnInfo) realm.getSchema().getColumnInfo(Alert.class);
        while (objects.hasNext()) {
            Alert object = (Alert) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$alertType = object.realmGet$alertType();
                    if (realmGet$alertType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.alertTypeIndex, rowIndex, realmGet$alertType, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.callIndex, rowIndex, object.realmGet$call(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.emailIndex, rowIndex, object.realmGet$email(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.smsIndex, rowIndex, object.realmGet$sms(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.notificationIndex, rowIndex, object.realmGet$notification(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Alert object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Alert.class);
        long tableNativePtr = table.getNativePtr();
        AlertColumnInfo columnInfo = (AlertColumnInfo) realm.getSchema().getColumnInfo(Alert.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$alertType = object.realmGet$alertType();
        if (realmGet$alertType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.alertTypeIndex, rowIndex, realmGet$alertType, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.alertTypeIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.callIndex, rowIndex, object.realmGet$call(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.emailIndex, rowIndex, object.realmGet$email(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.smsIndex, rowIndex, object.realmGet$sms(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.notificationIndex, rowIndex, object.realmGet$notification(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Alert.class);
        long tableNativePtr = table.getNativePtr();
        AlertColumnInfo columnInfo = (AlertColumnInfo) realm.getSchema().getColumnInfo(Alert.class);
        while (objects.hasNext()) {
            Alert object = (Alert) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$alertType = object.realmGet$alertType();
                    if (realmGet$alertType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.alertTypeIndex, rowIndex, realmGet$alertType, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.alertTypeIndex, rowIndex, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.callIndex, rowIndex, object.realmGet$call(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.emailIndex, rowIndex, object.realmGet$email(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.smsIndex, rowIndex, object.realmGet$sms(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.notificationIndex, rowIndex, object.realmGet$notification(), false);
                }
            }
        }
    }

    public static Alert createDetachedCopy(Alert realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Alert unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Alert();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Alert) cachedObject.object;
        } else {
            unmanagedObject = (Alert) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        AlertRealmProxyInterface unmanagedCopy = unmanagedObject;
        AlertRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$alertType(realmSource.realmGet$alertType());
        unmanagedCopy.realmSet$call(realmSource.realmGet$call());
        unmanagedCopy.realmSet$email(realmSource.realmGet$email());
        unmanagedCopy.realmSet$sms(realmSource.realmGet$sms());
        unmanagedCopy.realmSet$notification(realmSource.realmGet$notification());
        return unmanagedObject;
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
        AlertRealmProxy aAlert = (AlertRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aAlert.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aAlert.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aAlert.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
