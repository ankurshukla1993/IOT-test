package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Activity;
import com.facebook.share.internal.ShareConstants;
import io.realm.exceptions.RealmException;
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

public class ActivityRealmProxy extends Activity implements RealmObjectProxy, ActivityRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ActivityColumnInfo columnInfo;
    private ProxyState<Activity> proxyState;

    static final class ActivityColumnInfo extends ColumnInfo {
        long categoryIndex;
        long enableNotificationIndex;
        long endIndex;
        long idIndex;
        long nameIndex;
        long notificationBeforeIndex;
        long patientIdIndex;
        long startIndex;
        long userIdIndex;

        ActivityColumnInfo(OsSchemaInfo schemaInfo) {
            super(9);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Activity");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.startIndex = addColumnDetails("start", objectSchemaInfo);
            this.endIndex = addColumnDetails("end", objectSchemaInfo);
            this.enableNotificationIndex = addColumnDetails("enableNotification", objectSchemaInfo);
            this.notificationBeforeIndex = addColumnDetails("notificationBefore", objectSchemaInfo);
            this.patientIdIndex = addColumnDetails("patientId", objectSchemaInfo);
            this.categoryIndex = addColumnDetails("category", objectSchemaInfo);
            this.userIdIndex = addColumnDetails("userId", objectSchemaInfo);
        }

        ActivityColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new ActivityColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ActivityColumnInfo src = (ActivityColumnInfo) rawSrc;
            ActivityColumnInfo dst = (ActivityColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.nameIndex = src.nameIndex;
            dst.startIndex = src.startIndex;
            dst.endIndex = src.endIndex;
            dst.enableNotificationIndex = src.enableNotificationIndex;
            dst.notificationBeforeIndex = src.notificationBeforeIndex;
            dst.patientIdIndex = src.patientIdIndex;
            dst.categoryIndex = src.categoryIndex;
            dst.userIdIndex = src.userIdIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("name");
        fieldNames.add("start");
        fieldNames.add("end");
        fieldNames.add("enableNotification");
        fieldNames.add("notificationBefore");
        fieldNames.add("patientId");
        fieldNames.add("category");
        fieldNames.add("userId");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ActivityRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ActivityColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idIndex);
    }

    public void realmSet$id(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
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

    public String realmGet$start() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.startIndex);
    }

    public void realmSet$start(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.startIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.startIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.startIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.startIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$end() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.endIndex);
    }

    public void realmSet$end(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.endIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.endIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.endIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.endIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$enableNotification() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.enableNotificationIndex);
    }

    public void realmSet$enableNotification(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.enableNotificationIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.enableNotificationIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.enableNotificationIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.enableNotificationIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$notificationBefore() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.notificationBeforeIndex);
    }

    public void realmSet$notificationBefore(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.notificationBeforeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.notificationBeforeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.notificationBeforeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.notificationBeforeIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$patientId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.patientIdIndex);
    }

    public void realmSet$patientId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.patientIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.patientIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.patientIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.patientIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$category() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.categoryIndex);
    }

    public void realmSet$category(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.categoryIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.categoryIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.categoryIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.categoryIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$userId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.userIdIndex);
    }

    public void realmSet$userId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.userIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.userIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.userIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.userIdIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Activity");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("start", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("end", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("enableNotification", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("notificationBefore", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("patientId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("category", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("userId", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ActivityColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ActivityColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Activity";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Activity createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = Collections.emptyList();
        Activity activity = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Activity.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Activity.class), false, Collections.emptyList());
                    activity = new ActivityRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (activity == null) {
            if (!json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            } else if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                activity = (ActivityRealmProxy) realm.createObjectInternal(Activity.class, null, true, excludeFields);
            } else {
                ActivityRealmProxy obj = (ActivityRealmProxy) realm.createObjectInternal(Activity.class, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID), true, excludeFields);
            }
        }
        ActivityRealmProxyInterface objProxy = activity;
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name(json.getString("name"));
            }
        }
        if (json.has("start")) {
            if (json.isNull("start")) {
                objProxy.realmSet$start(null);
            } else {
                objProxy.realmSet$start(json.getString("start"));
            }
        }
        if (json.has("end")) {
            if (json.isNull("end")) {
                objProxy.realmSet$end(null);
            } else {
                objProxy.realmSet$end(json.getString("end"));
            }
        }
        if (json.has("enableNotification")) {
            if (json.isNull("enableNotification")) {
                objProxy.realmSet$enableNotification(null);
            } else {
                objProxy.realmSet$enableNotification(json.getString("enableNotification"));
            }
        }
        if (json.has("notificationBefore")) {
            if (json.isNull("notificationBefore")) {
                objProxy.realmSet$notificationBefore(null);
            } else {
                objProxy.realmSet$notificationBefore(json.getString("notificationBefore"));
            }
        }
        if (json.has("patientId")) {
            if (json.isNull("patientId")) {
                objProxy.realmSet$patientId(null);
            } else {
                objProxy.realmSet$patientId(json.getString("patientId"));
            }
        }
        if (json.has("category")) {
            if (json.isNull("category")) {
                objProxy.realmSet$category(null);
            } else {
                objProxy.realmSet$category(json.getString("category"));
            }
        }
        if (json.has("userId")) {
            if (json.isNull("userId")) {
                objProxy.realmSet$userId(null);
            } else {
                objProxy.realmSet$userId(json.getString("userId"));
            }
        }
        return activity;
    }

    @TargetApi(11)
    public static Activity createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Activity obj = new Activity();
        ActivityRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("start")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$start(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$start(null);
                }
            } else if (name.equals("end")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$end(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$end(null);
                }
            } else if (name.equals("enableNotification")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$enableNotification(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$enableNotification(null);
                }
            } else if (name.equals("notificationBefore")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$notificationBefore(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$notificationBefore(null);
                }
            } else if (name.equals("patientId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$patientId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$patientId(null);
                }
            } else if (name.equals("category")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$category(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$category(null);
                }
            } else if (!name.equals("userId")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$userId(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$userId(null);
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Activity) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Activity copyOrUpdate(Realm realm, Activity object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        Throwable th;
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
            return (Activity) cachedRealmObject;
        }
        Activity update2;
        Activity realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Activity.class);
            long pkColumnIndex = table.getPrimaryKey();
            String value = object.realmGet$id();
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, value);
            }
            if (rowIndex == -1) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Activity.class), false, Collections.emptyList());
                    Activity realmObject2 = new ActivityRealmProxy();
                    try {
                        cache.put(object, (RealmObjectProxy) realmObject2);
                        objectContext.clear();
                        realmObject = realmObject2;
                    } catch (Throwable th2) {
                        th = th2;
                        realmObject = realmObject2;
                        objectContext.clear();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectContext.clear();
                    throw th;
                }
            }
        }
        if (canUpdate) {
            update2 = update(realm, realmObject, object, cache);
        } else {
            update2 = copy(realm, object, update, cache);
        }
        return update2;
    }

    public static Activity copy(Realm realm, Activity newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Activity) cachedRealmObject;
        }
        Activity realmObject = (Activity) realm.createObjectInternal(Activity.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ActivityRealmProxyInterface realmObjectSource = newObject;
        ActivityRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$start(realmObjectSource.realmGet$start());
        realmObjectCopy.realmSet$end(realmObjectSource.realmGet$end());
        realmObjectCopy.realmSet$enableNotification(realmObjectSource.realmGet$enableNotification());
        realmObjectCopy.realmSet$notificationBefore(realmObjectSource.realmGet$notificationBefore());
        realmObjectCopy.realmSet$patientId(realmObjectSource.realmGet$patientId());
        realmObjectCopy.realmSet$category(realmObjectSource.realmGet$category());
        realmObjectCopy.realmSet$userId(realmObjectSource.realmGet$userId());
        return realmObject;
    }

    public static long insert(Realm realm, Activity object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Activity.class);
        long tableNativePtr = table.getNativePtr();
        ActivityColumnInfo columnInfo = (ActivityColumnInfo) realm.getSchema().getColumnInfo(Activity.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = object.realmGet$id();
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == -1) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$start = object.realmGet$start();
        if (realmGet$start != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.startIndex, rowIndex, realmGet$start, false);
        }
        String realmGet$end = object.realmGet$end();
        if (realmGet$end != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.endIndex, rowIndex, realmGet$end, false);
        }
        String realmGet$enableNotification = object.realmGet$enableNotification();
        if (realmGet$enableNotification != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.enableNotificationIndex, rowIndex, realmGet$enableNotification, false);
        }
        String realmGet$notificationBefore = object.realmGet$notificationBefore();
        if (realmGet$notificationBefore != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.notificationBeforeIndex, rowIndex, realmGet$notificationBefore, false);
        }
        String realmGet$patientId = object.realmGet$patientId();
        if (realmGet$patientId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
        }
        String realmGet$category = object.realmGet$category();
        if (realmGet$category != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.categoryIndex, rowIndex, realmGet$category, false);
        }
        String realmGet$userId = object.realmGet$userId();
        if (realmGet$userId == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Activity.class);
        long tableNativePtr = table.getNativePtr();
        ActivityColumnInfo columnInfo = (ActivityColumnInfo) realm.getSchema().getColumnInfo(Activity.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Activity object = (Activity) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex;
                    String primaryKeyValue = object.realmGet$id();
                    if (primaryKeyValue == null) {
                        rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                    } else {
                        rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                    }
                    if (rowIndex == -1) {
                        rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
                    } else {
                        Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                    }
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    String realmGet$start = object.realmGet$start();
                    if (realmGet$start != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.startIndex, rowIndex, realmGet$start, false);
                    }
                    String realmGet$end = object.realmGet$end();
                    if (realmGet$end != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.endIndex, rowIndex, realmGet$end, false);
                    }
                    String realmGet$enableNotification = object.realmGet$enableNotification();
                    if (realmGet$enableNotification != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.enableNotificationIndex, rowIndex, realmGet$enableNotification, false);
                    }
                    String realmGet$notificationBefore = object.realmGet$notificationBefore();
                    if (realmGet$notificationBefore != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.notificationBeforeIndex, rowIndex, realmGet$notificationBefore, false);
                    }
                    String realmGet$patientId = object.realmGet$patientId();
                    if (realmGet$patientId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
                    }
                    String realmGet$category = object.realmGet$category();
                    if (realmGet$category != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.categoryIndex, rowIndex, realmGet$category, false);
                    }
                    String realmGet$userId = object.realmGet$userId();
                    if (realmGet$userId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Activity object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Activity.class);
        long tableNativePtr = table.getNativePtr();
        ActivityColumnInfo columnInfo = (ActivityColumnInfo) realm.getSchema().getColumnInfo(Activity.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = object.realmGet$id();
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == -1) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
        }
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$start = object.realmGet$start();
        if (realmGet$start != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.startIndex, rowIndex, realmGet$start, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.startIndex, rowIndex, false);
        }
        String realmGet$end = object.realmGet$end();
        if (realmGet$end != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.endIndex, rowIndex, realmGet$end, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.endIndex, rowIndex, false);
        }
        String realmGet$enableNotification = object.realmGet$enableNotification();
        if (realmGet$enableNotification != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.enableNotificationIndex, rowIndex, realmGet$enableNotification, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.enableNotificationIndex, rowIndex, false);
        }
        String realmGet$notificationBefore = object.realmGet$notificationBefore();
        if (realmGet$notificationBefore != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.notificationBeforeIndex, rowIndex, realmGet$notificationBefore, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.notificationBeforeIndex, rowIndex, false);
        }
        String realmGet$patientId = object.realmGet$patientId();
        if (realmGet$patientId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.patientIdIndex, rowIndex, false);
        }
        String realmGet$category = object.realmGet$category();
        if (realmGet$category != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.categoryIndex, rowIndex, realmGet$category, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.categoryIndex, rowIndex, false);
        }
        String realmGet$userId = object.realmGet$userId();
        if (realmGet$userId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.userIdIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Activity.class);
        long tableNativePtr = table.getNativePtr();
        ActivityColumnInfo columnInfo = (ActivityColumnInfo) realm.getSchema().getColumnInfo(Activity.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Activity object = (Activity) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex;
                    String primaryKeyValue = object.realmGet$id();
                    if (primaryKeyValue == null) {
                        rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                    } else {
                        rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                    }
                    if (rowIndex == -1) {
                        rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
                    }
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                    }
                    String realmGet$start = object.realmGet$start();
                    if (realmGet$start != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.startIndex, rowIndex, realmGet$start, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.startIndex, rowIndex, false);
                    }
                    String realmGet$end = object.realmGet$end();
                    if (realmGet$end != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.endIndex, rowIndex, realmGet$end, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.endIndex, rowIndex, false);
                    }
                    String realmGet$enableNotification = object.realmGet$enableNotification();
                    if (realmGet$enableNotification != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.enableNotificationIndex, rowIndex, realmGet$enableNotification, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.enableNotificationIndex, rowIndex, false);
                    }
                    String realmGet$notificationBefore = object.realmGet$notificationBefore();
                    if (realmGet$notificationBefore != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.notificationBeforeIndex, rowIndex, realmGet$notificationBefore, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.notificationBeforeIndex, rowIndex, false);
                    }
                    String realmGet$patientId = object.realmGet$patientId();
                    if (realmGet$patientId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.patientIdIndex, rowIndex, false);
                    }
                    String realmGet$category = object.realmGet$category();
                    if (realmGet$category != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.categoryIndex, rowIndex, realmGet$category, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.categoryIndex, rowIndex, false);
                    }
                    String realmGet$userId = object.realmGet$userId();
                    if (realmGet$userId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.userIdIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Activity createDetachedCopy(Activity realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Activity unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Activity();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Activity) cachedObject.object;
        } else {
            unmanagedObject = (Activity) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ActivityRealmProxyInterface unmanagedCopy = unmanagedObject;
        ActivityRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$start(realmSource.realmGet$start());
        unmanagedCopy.realmSet$end(realmSource.realmGet$end());
        unmanagedCopy.realmSet$enableNotification(realmSource.realmGet$enableNotification());
        unmanagedCopy.realmSet$notificationBefore(realmSource.realmGet$notificationBefore());
        unmanagedCopy.realmSet$patientId(realmSource.realmGet$patientId());
        unmanagedCopy.realmSet$category(realmSource.realmGet$category());
        unmanagedCopy.realmSet$userId(realmSource.realmGet$userId());
        return unmanagedObject;
    }

    static Activity update(Realm realm, Activity realmObject, Activity newObject, Map<RealmModel, RealmObjectProxy> map) {
        ActivityRealmProxyInterface realmObjectTarget = realmObject;
        ActivityRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$start(realmObjectSource.realmGet$start());
        realmObjectTarget.realmSet$end(realmObjectSource.realmGet$end());
        realmObjectTarget.realmSet$enableNotification(realmObjectSource.realmGet$enableNotification());
        realmObjectTarget.realmSet$notificationBefore(realmObjectSource.realmGet$notificationBefore());
        realmObjectTarget.realmSet$patientId(realmObjectSource.realmGet$patientId());
        realmObjectTarget.realmSet$category(realmObjectSource.realmGet$category());
        realmObjectTarget.realmSet$userId(realmObjectSource.realmGet$userId());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Activity = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{start:");
        stringBuilder.append(realmGet$start() != null ? realmGet$start() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{end:");
        stringBuilder.append(realmGet$end() != null ? realmGet$end() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{enableNotification:");
        stringBuilder.append(realmGet$enableNotification() != null ? realmGet$enableNotification() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{notificationBefore:");
        stringBuilder.append(realmGet$notificationBefore() != null ? realmGet$notificationBefore() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{patientId:");
        stringBuilder.append(realmGet$patientId() != null ? realmGet$patientId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{category:");
        stringBuilder.append(realmGet$category() != null ? realmGet$category() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userId:");
        stringBuilder.append(realmGet$userId() != null ? realmGet$userId() : "null");
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
        ActivityRealmProxy aActivity = (ActivityRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aActivity.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aActivity.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aActivity.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
