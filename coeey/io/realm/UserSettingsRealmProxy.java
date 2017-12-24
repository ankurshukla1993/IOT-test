package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Limit;
import com.cooey.common.vo.UserSettings;
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

public class UserSettingsRealmProxy extends UserSettings implements RealmObjectProxy, UserSettingsRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private UserSettingsColumnInfo columnInfo;
    private ProxyState<UserSettings> proxyState;
    private RealmList<Limit> vitalLimitsRealmList;

    static final class UserSettingsColumnInfo extends ColumnInfo {
        long defaultNotificationModeValueIndex;
        long eventNotificationEnabledIndex;
        long notificationEnabledIndex;
        long vitalLimitNotificationEnabledIndex;
        long vitalLimitsIndex;
        long vitalNotificationEnabledIndex;

        UserSettingsColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("UserSettings");
            this.vitalLimitsIndex = addColumnDetails("vitalLimits", objectSchemaInfo);
            this.defaultNotificationModeValueIndex = addColumnDetails("defaultNotificationModeValue", objectSchemaInfo);
            this.notificationEnabledIndex = addColumnDetails("notificationEnabled", objectSchemaInfo);
            this.vitalNotificationEnabledIndex = addColumnDetails("vitalNotificationEnabled", objectSchemaInfo);
            this.vitalLimitNotificationEnabledIndex = addColumnDetails("vitalLimitNotificationEnabled", objectSchemaInfo);
            this.eventNotificationEnabledIndex = addColumnDetails("eventNotificationEnabled", objectSchemaInfo);
        }

        UserSettingsColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new UserSettingsColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            UserSettingsColumnInfo src = (UserSettingsColumnInfo) rawSrc;
            UserSettingsColumnInfo dst = (UserSettingsColumnInfo) rawDst;
            dst.vitalLimitsIndex = src.vitalLimitsIndex;
            dst.defaultNotificationModeValueIndex = src.defaultNotificationModeValueIndex;
            dst.notificationEnabledIndex = src.notificationEnabledIndex;
            dst.vitalNotificationEnabledIndex = src.vitalNotificationEnabledIndex;
            dst.vitalLimitNotificationEnabledIndex = src.vitalLimitNotificationEnabledIndex;
            dst.eventNotificationEnabledIndex = src.eventNotificationEnabledIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("vitalLimits");
        fieldNames.add("defaultNotificationModeValue");
        fieldNames.add("notificationEnabled");
        fieldNames.add("vitalNotificationEnabled");
        fieldNames.add("vitalLimitNotificationEnabled");
        fieldNames.add("eventNotificationEnabled");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    UserSettingsRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (UserSettingsColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public RealmList<Limit> realmGet$vitalLimits() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.vitalLimitsRealmList != null) {
            return this.vitalLimitsRealmList;
        }
        this.vitalLimitsRealmList = new RealmList(Limit.class, this.proxyState.getRow$realm().getLinkList(this.columnInfo.vitalLimitsIndex), this.proxyState.getRealm$realm());
        return this.vitalLimitsRealmList;
    }

    public void realmSet$vitalLimits(RealmList<Limit> value) {
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("vitalLimits")) {
                return;
            }
            if (!(value == null || value.isManaged())) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<Limit> original = value;
                value = new RealmList();
                Iterator it = original.iterator();
                while (it.hasNext()) {
                    Limit item = (Limit) it.next();
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList osList = this.proxyState.getRow$realm().getLinkList(this.columnInfo.vitalLimitsIndex);
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

    public String realmGet$defaultNotificationModeValue() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.defaultNotificationModeValueIndex);
    }

    public void realmSet$defaultNotificationModeValue(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.defaultNotificationModeValueIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.defaultNotificationModeValueIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.defaultNotificationModeValueIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.defaultNotificationModeValueIndex, row.getIndex(), value, true);
            }
        }
    }

    public Boolean realmGet$notificationEnabled() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNull(this.columnInfo.notificationEnabledIndex)) {
            return null;
        }
        return Boolean.valueOf(this.proxyState.getRow$realm().getBoolean(this.columnInfo.notificationEnabledIndex));
    }

    public void realmSet$notificationEnabled(Boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.notificationEnabledIndex);
            } else {
                this.proxyState.getRow$realm().setBoolean(this.columnInfo.notificationEnabledIndex, value.booleanValue());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.notificationEnabledIndex, row.getIndex(), true);
            } else {
                row.getTable().setBoolean(this.columnInfo.notificationEnabledIndex, row.getIndex(), value.booleanValue(), true);
            }
        }
    }

    public boolean realmGet$vitalNotificationEnabled() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.vitalNotificationEnabledIndex);
    }

    public void realmSet$vitalNotificationEnabled(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.vitalNotificationEnabledIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.vitalNotificationEnabledIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$vitalLimitNotificationEnabled() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.vitalLimitNotificationEnabledIndex);
    }

    public void realmSet$vitalLimitNotificationEnabled(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.vitalLimitNotificationEnabledIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.vitalLimitNotificationEnabledIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$eventNotificationEnabled() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.eventNotificationEnabledIndex);
    }

    public void realmSet$eventNotificationEnabled(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.eventNotificationEnabledIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.eventNotificationEnabledIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("UserSettings");
        builder.addPersistedLinkProperty("vitalLimits", RealmFieldType.LIST, "Limit");
        builder.addPersistedProperty("defaultNotificationModeValue", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("notificationEnabled", RealmFieldType.BOOLEAN, false, false, false);
        builder.addPersistedProperty("vitalNotificationEnabled", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("vitalLimitNotificationEnabled", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("eventNotificationEnabled", RealmFieldType.BOOLEAN, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static UserSettingsColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new UserSettingsColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_UserSettings";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static UserSettings createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        if (json.has("vitalLimits")) {
            excludeFields.add("vitalLimits");
        }
        UserSettings obj = (UserSettings) realm.createObjectInternal(UserSettings.class, true, excludeFields);
        UserSettingsRealmProxyInterface objProxy = obj;
        if (json.has("vitalLimits")) {
            if (json.isNull("vitalLimits")) {
                objProxy.realmSet$vitalLimits(null);
            } else {
                objProxy.realmGet$vitalLimits().clear();
                JSONArray array = json.getJSONArray("vitalLimits");
                for (int i = 0; i < array.length(); i++) {
                    objProxy.realmGet$vitalLimits().add(LimitRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update));
                }
            }
        }
        if (json.has("defaultNotificationModeValue")) {
            if (json.isNull("defaultNotificationModeValue")) {
                objProxy.realmSet$defaultNotificationModeValue(null);
            } else {
                objProxy.realmSet$defaultNotificationModeValue(json.getString("defaultNotificationModeValue"));
            }
        }
        if (json.has("notificationEnabled")) {
            if (json.isNull("notificationEnabled")) {
                objProxy.realmSet$notificationEnabled(null);
            } else {
                objProxy.realmSet$notificationEnabled(Boolean.valueOf(json.getBoolean("notificationEnabled")));
            }
        }
        if (json.has("vitalNotificationEnabled")) {
            if (json.isNull("vitalNotificationEnabled")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'vitalNotificationEnabled' to null.");
            }
            objProxy.realmSet$vitalNotificationEnabled(json.getBoolean("vitalNotificationEnabled"));
        }
        if (json.has("vitalLimitNotificationEnabled")) {
            if (json.isNull("vitalLimitNotificationEnabled")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'vitalLimitNotificationEnabled' to null.");
            }
            objProxy.realmSet$vitalLimitNotificationEnabled(json.getBoolean("vitalLimitNotificationEnabled"));
        }
        if (json.has("eventNotificationEnabled")) {
            if (json.isNull("eventNotificationEnabled")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'eventNotificationEnabled' to null.");
            }
            objProxy.realmSet$eventNotificationEnabled(json.getBoolean("eventNotificationEnabled"));
        }
        return obj;
    }

    @TargetApi(11)
    public static UserSettings createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        UserSettings obj = new UserSettings();
        UserSettingsRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("vitalLimits")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$vitalLimits(null);
                } else {
                    objProxy.realmSet$vitalLimits(new RealmList());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        objProxy.realmGet$vitalLimits().add(LimitRealmProxy.createUsingJsonStream(realm, reader));
                    }
                    reader.endArray();
                }
            } else if (name.equals("defaultNotificationModeValue")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$defaultNotificationModeValue(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$defaultNotificationModeValue(null);
                }
            } else if (name.equals("notificationEnabled")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$notificationEnabled(Boolean.valueOf(reader.nextBoolean()));
                } else {
                    reader.skipValue();
                    objProxy.realmSet$notificationEnabled(null);
                }
            } else if (name.equals("vitalNotificationEnabled")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$vitalNotificationEnabled(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'vitalNotificationEnabled' to null.");
                }
            } else if (name.equals("vitalLimitNotificationEnabled")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$vitalLimitNotificationEnabled(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'vitalLimitNotificationEnabled' to null.");
                }
            } else if (!name.equals("eventNotificationEnabled")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$eventNotificationEnabled(reader.nextBoolean());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'eventNotificationEnabled' to null.");
            }
        }
        reader.endObject();
        return (UserSettings) realm.copyToRealm(obj);
    }

    public static UserSettings copyOrUpdate(Realm realm, UserSettings object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (UserSettings) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static UserSettings copy(Realm realm, UserSettings newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (UserSettings) cachedRealmObject;
        }
        UserSettings realmObject = (UserSettings) realm.createObjectInternal(UserSettings.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        UserSettingsRealmProxyInterface realmObjectSource = newObject;
        UserSettingsRealmProxyInterface realmObjectCopy = realmObject;
        RealmList<Limit> vitalLimitsList = realmObjectSource.realmGet$vitalLimits();
        if (vitalLimitsList != null) {
            RealmList<Limit> vitalLimitsRealmList = realmObjectCopy.realmGet$vitalLimits();
            vitalLimitsRealmList.clear();
            for (int i = 0; i < vitalLimitsList.size(); i++) {
                Limit vitalLimitsItem = (Limit) vitalLimitsList.get(i);
                Limit cachevitalLimits = (Limit) cache.get(vitalLimitsItem);
                if (cachevitalLimits != null) {
                    vitalLimitsRealmList.add(cachevitalLimits);
                } else {
                    vitalLimitsRealmList.add(LimitRealmProxy.copyOrUpdate(realm, vitalLimitsItem, update, cache));
                }
            }
        }
        realmObjectCopy.realmSet$defaultNotificationModeValue(realmObjectSource.realmGet$defaultNotificationModeValue());
        realmObjectCopy.realmSet$notificationEnabled(realmObjectSource.realmGet$notificationEnabled());
        realmObjectCopy.realmSet$vitalNotificationEnabled(realmObjectSource.realmGet$vitalNotificationEnabled());
        realmObjectCopy.realmSet$vitalLimitNotificationEnabled(realmObjectSource.realmGet$vitalLimitNotificationEnabled());
        realmObjectCopy.realmSet$eventNotificationEnabled(realmObjectSource.realmGet$eventNotificationEnabled());
        return realmObject;
    }

    public static long insert(Realm realm, UserSettings object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(UserSettings.class);
        long tableNativePtr = table.getNativePtr();
        UserSettingsColumnInfo columnInfo = (UserSettingsColumnInfo) realm.getSchema().getColumnInfo(UserSettings.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        RealmList<Limit> vitalLimitsList = object.realmGet$vitalLimits();
        if (vitalLimitsList != null) {
            OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.vitalLimitsIndex);
            Iterator it = vitalLimitsList.iterator();
            while (it.hasNext()) {
                Limit vitalLimitsItem = (Limit) it.next();
                Long cacheItemIndexvitalLimits = (Long) cache.get(vitalLimitsItem);
                if (cacheItemIndexvitalLimits == null) {
                    cacheItemIndexvitalLimits = Long.valueOf(LimitRealmProxy.insert(realm, vitalLimitsItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexvitalLimits.longValue());
            }
        }
        String realmGet$defaultNotificationModeValue = object.realmGet$defaultNotificationModeValue();
        if (realmGet$defaultNotificationModeValue != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.defaultNotificationModeValueIndex, rowIndex, realmGet$defaultNotificationModeValue, false);
        }
        Boolean realmGet$notificationEnabled = object.realmGet$notificationEnabled();
        if (realmGet$notificationEnabled != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.notificationEnabledIndex, rowIndex, realmGet$notificationEnabled.booleanValue(), false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.vitalNotificationEnabledIndex, rowIndex, object.realmGet$vitalNotificationEnabled(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.vitalLimitNotificationEnabledIndex, rowIndex, object.realmGet$vitalLimitNotificationEnabled(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.eventNotificationEnabledIndex, rowIndex, object.realmGet$eventNotificationEnabled(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(UserSettings.class);
        long tableNativePtr = table.getNativePtr();
        UserSettingsColumnInfo columnInfo = (UserSettingsColumnInfo) realm.getSchema().getColumnInfo(UserSettings.class);
        while (objects.hasNext()) {
            UserSettings object = (UserSettings) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    RealmList<Limit> vitalLimitsList = object.realmGet$vitalLimits();
                    if (vitalLimitsList != null) {
                        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.vitalLimitsIndex);
                        Iterator it = vitalLimitsList.iterator();
                        while (it.hasNext()) {
                            Limit vitalLimitsItem = (Limit) it.next();
                            Long cacheItemIndexvitalLimits = (Long) cache.get(vitalLimitsItem);
                            if (cacheItemIndexvitalLimits == null) {
                                cacheItemIndexvitalLimits = Long.valueOf(LimitRealmProxy.insert(realm, vitalLimitsItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexvitalLimits.longValue());
                        }
                    }
                    String realmGet$defaultNotificationModeValue = object.realmGet$defaultNotificationModeValue();
                    if (realmGet$defaultNotificationModeValue != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.defaultNotificationModeValueIndex, rowIndex, realmGet$defaultNotificationModeValue, false);
                    }
                    Boolean realmGet$notificationEnabled = object.realmGet$notificationEnabled();
                    if (realmGet$notificationEnabled != null) {
                        Table.nativeSetBoolean(tableNativePtr, columnInfo.notificationEnabledIndex, rowIndex, realmGet$notificationEnabled.booleanValue(), false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.vitalNotificationEnabledIndex, rowIndex, object.realmGet$vitalNotificationEnabled(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.vitalLimitNotificationEnabledIndex, rowIndex, object.realmGet$vitalLimitNotificationEnabled(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.eventNotificationEnabledIndex, rowIndex, object.realmGet$eventNotificationEnabled(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, UserSettings object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(UserSettings.class);
        long tableNativePtr = table.getNativePtr();
        UserSettingsColumnInfo columnInfo = (UserSettingsColumnInfo) realm.getSchema().getColumnInfo(UserSettings.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.vitalLimitsIndex);
        osList.removeAll();
        RealmList<Limit> vitalLimitsList = object.realmGet$vitalLimits();
        if (vitalLimitsList != null) {
            Iterator it = vitalLimitsList.iterator();
            while (it.hasNext()) {
                Limit vitalLimitsItem = (Limit) it.next();
                Long cacheItemIndexvitalLimits = (Long) cache.get(vitalLimitsItem);
                if (cacheItemIndexvitalLimits == null) {
                    cacheItemIndexvitalLimits = Long.valueOf(LimitRealmProxy.insertOrUpdate(realm, vitalLimitsItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexvitalLimits.longValue());
            }
        }
        String realmGet$defaultNotificationModeValue = object.realmGet$defaultNotificationModeValue();
        if (realmGet$defaultNotificationModeValue != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.defaultNotificationModeValueIndex, rowIndex, realmGet$defaultNotificationModeValue, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.defaultNotificationModeValueIndex, rowIndex, false);
        }
        Boolean realmGet$notificationEnabled = object.realmGet$notificationEnabled();
        if (realmGet$notificationEnabled != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.notificationEnabledIndex, rowIndex, realmGet$notificationEnabled.booleanValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.notificationEnabledIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.vitalNotificationEnabledIndex, rowIndex, object.realmGet$vitalNotificationEnabled(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.vitalLimitNotificationEnabledIndex, rowIndex, object.realmGet$vitalLimitNotificationEnabled(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.eventNotificationEnabledIndex, rowIndex, object.realmGet$eventNotificationEnabled(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(UserSettings.class);
        long tableNativePtr = table.getNativePtr();
        UserSettingsColumnInfo columnInfo = (UserSettingsColumnInfo) realm.getSchema().getColumnInfo(UserSettings.class);
        while (objects.hasNext()) {
            UserSettings object = (UserSettings) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.vitalLimitsIndex);
                    osList.removeAll();
                    RealmList<Limit> vitalLimitsList = object.realmGet$vitalLimits();
                    if (vitalLimitsList != null) {
                        Iterator it = vitalLimitsList.iterator();
                        while (it.hasNext()) {
                            Limit vitalLimitsItem = (Limit) it.next();
                            Long cacheItemIndexvitalLimits = (Long) cache.get(vitalLimitsItem);
                            if (cacheItemIndexvitalLimits == null) {
                                cacheItemIndexvitalLimits = Long.valueOf(LimitRealmProxy.insertOrUpdate(realm, vitalLimitsItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexvitalLimits.longValue());
                        }
                    }
                    String realmGet$defaultNotificationModeValue = object.realmGet$defaultNotificationModeValue();
                    if (realmGet$defaultNotificationModeValue != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.defaultNotificationModeValueIndex, rowIndex, realmGet$defaultNotificationModeValue, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.defaultNotificationModeValueIndex, rowIndex, false);
                    }
                    Boolean realmGet$notificationEnabled = object.realmGet$notificationEnabled();
                    if (realmGet$notificationEnabled != null) {
                        Table.nativeSetBoolean(tableNativePtr, columnInfo.notificationEnabledIndex, rowIndex, realmGet$notificationEnabled.booleanValue(), false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.notificationEnabledIndex, rowIndex, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.vitalNotificationEnabledIndex, rowIndex, object.realmGet$vitalNotificationEnabled(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.vitalLimitNotificationEnabledIndex, rowIndex, object.realmGet$vitalLimitNotificationEnabled(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.eventNotificationEnabledIndex, rowIndex, object.realmGet$eventNotificationEnabled(), false);
                }
            }
        }
    }

    public static UserSettings createDetachedCopy(UserSettings realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        UserSettings unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new UserSettings();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (UserSettings) cachedObject.object;
        } else {
            unmanagedObject = (UserSettings) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        UserSettingsRealmProxyInterface unmanagedCopy = unmanagedObject;
        UserSettingsRealmProxyInterface realmSource = realmObject;
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$vitalLimits(null);
        } else {
            RealmList<Limit> managedvitalLimitsList = realmSource.realmGet$vitalLimits();
            RealmList<Limit> unmanagedvitalLimitsList = new RealmList();
            unmanagedCopy.realmSet$vitalLimits(unmanagedvitalLimitsList);
            int nextDepth = currentDepth + 1;
            int size = managedvitalLimitsList.size();
            for (int i = 0; i < size; i++) {
                unmanagedvitalLimitsList.add(LimitRealmProxy.createDetachedCopy((Limit) managedvitalLimitsList.get(i), nextDepth, maxDepth, cache));
            }
        }
        unmanagedCopy.realmSet$defaultNotificationModeValue(realmSource.realmGet$defaultNotificationModeValue());
        unmanagedCopy.realmSet$notificationEnabled(realmSource.realmGet$notificationEnabled());
        unmanagedCopy.realmSet$vitalNotificationEnabled(realmSource.realmGet$vitalNotificationEnabled());
        unmanagedCopy.realmSet$vitalLimitNotificationEnabled(realmSource.realmGet$vitalLimitNotificationEnabled());
        unmanagedCopy.realmSet$eventNotificationEnabled(realmSource.realmGet$eventNotificationEnabled());
        return unmanagedObject;
    }

    public ProxyState<?> realmGet$proxyState() {
        return this.proxyState;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSettingsRealmProxy aUserSettings = (UserSettingsRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aUserSettings.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUserSettings.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aUserSettings.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
