package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.RealmBoolean;
import com.cooey.common.vo.UserFeature;
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

public class UserFeatureRealmProxy extends UserFeature implements RealmObjectProxy, UserFeatureRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private UserFeatureColumnInfo columnInfo;
    private ProxyState<UserFeature> proxyState;

    static final class UserFeatureColumnInfo extends ColumnInfo {
        long realmBooleanIndex;
        long settingNameIndex;

        UserFeatureColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("UserFeature");
            this.settingNameIndex = addColumnDetails("settingName", objectSchemaInfo);
            this.realmBooleanIndex = addColumnDetails("realmBoolean", objectSchemaInfo);
        }

        UserFeatureColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new UserFeatureColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            UserFeatureColumnInfo src = (UserFeatureColumnInfo) rawSrc;
            UserFeatureColumnInfo dst = (UserFeatureColumnInfo) rawDst;
            dst.settingNameIndex = src.settingNameIndex;
            dst.realmBooleanIndex = src.realmBooleanIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("settingName");
        fieldNames.add("realmBoolean");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    UserFeatureRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (UserFeatureColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$settingName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.settingNameIndex);
    }

    public void realmSet$settingName(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.settingNameIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.settingNameIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.settingNameIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.settingNameIndex, row.getIndex(), value, true);
            }
        }
    }

    public RealmBoolean realmGet$realmBoolean() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.realmBooleanIndex)) {
            return null;
        }
        return (RealmBoolean) this.proxyState.getRealm$realm().get(RealmBoolean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.realmBooleanIndex), false, Collections.emptyList());
    }

    public void realmSet$realmBoolean(RealmBoolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.realmBooleanIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.realmBooleanIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("realmBoolean")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (RealmBoolean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.realmBooleanIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.realmBooleanIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("UserFeature");
        builder.addPersistedProperty("settingName", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("realmBoolean", RealmFieldType.OBJECT, "RealmBoolean");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static UserFeatureColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new UserFeatureColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_UserFeature";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static UserFeature createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        if (json.has("realmBoolean")) {
            excludeFields.add("realmBoolean");
        }
        UserFeature obj = (UserFeature) realm.createObjectInternal(UserFeature.class, true, excludeFields);
        UserFeatureRealmProxyInterface objProxy = obj;
        if (json.has("settingName")) {
            if (json.isNull("settingName")) {
                objProxy.realmSet$settingName(null);
            } else {
                objProxy.realmSet$settingName(json.getString("settingName"));
            }
        }
        if (json.has("realmBoolean")) {
            if (json.isNull("realmBoolean")) {
                objProxy.realmSet$realmBoolean(null);
            } else {
                objProxy.realmSet$realmBoolean(RealmBooleanRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("realmBoolean"), update));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static UserFeature createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        UserFeature obj = new UserFeature();
        UserFeatureRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("settingName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$settingName(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$settingName(null);
                }
            } else if (!name.equals("realmBoolean")) {
                reader.skipValue();
            } else if (reader.peek() == JsonToken.NULL) {
                reader.skipValue();
                objProxy.realmSet$realmBoolean(null);
            } else {
                objProxy.realmSet$realmBoolean(RealmBooleanRealmProxy.createUsingJsonStream(realm, reader));
            }
        }
        reader.endObject();
        return (UserFeature) realm.copyToRealm(obj);
    }

    public static UserFeature copyOrUpdate(Realm realm, UserFeature object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (UserFeature) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static UserFeature copy(Realm realm, UserFeature newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (UserFeature) cachedRealmObject;
        }
        UserFeature realmObject = (UserFeature) realm.createObjectInternal(UserFeature.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        UserFeatureRealmProxyInterface realmObjectSource = newObject;
        UserFeatureRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$settingName(realmObjectSource.realmGet$settingName());
        RealmBoolean realmBooleanObj = realmObjectSource.realmGet$realmBoolean();
        if (realmBooleanObj == null) {
            realmObjectCopy.realmSet$realmBoolean(null);
        } else {
            RealmBoolean cacherealmBoolean = (RealmBoolean) cache.get(realmBooleanObj);
            if (cacherealmBoolean != null) {
                realmObjectCopy.realmSet$realmBoolean(cacherealmBoolean);
            } else {
                realmObjectCopy.realmSet$realmBoolean(RealmBooleanRealmProxy.copyOrUpdate(realm, realmBooleanObj, update, cache));
            }
        }
        return realmObject;
    }

    public static long insert(Realm realm, UserFeature object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(UserFeature.class);
        long tableNativePtr = table.getNativePtr();
        UserFeatureColumnInfo columnInfo = (UserFeatureColumnInfo) realm.getSchema().getColumnInfo(UserFeature.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$settingName = object.realmGet$settingName();
        if (realmGet$settingName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.settingNameIndex, rowIndex, realmGet$settingName, false);
        }
        RealmBoolean realmBooleanObj = object.realmGet$realmBoolean();
        if (realmBooleanObj == null) {
            return rowIndex;
        }
        Long cacherealmBoolean = (Long) cache.get(realmBooleanObj);
        if (cacherealmBoolean == null) {
            cacherealmBoolean = Long.valueOf(RealmBooleanRealmProxy.insert(realm, realmBooleanObj, (Map) cache));
        }
        Table.nativeSetLink(tableNativePtr, columnInfo.realmBooleanIndex, rowIndex, cacherealmBoolean.longValue(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(UserFeature.class);
        long tableNativePtr = table.getNativePtr();
        UserFeatureColumnInfo columnInfo = (UserFeatureColumnInfo) realm.getSchema().getColumnInfo(UserFeature.class);
        while (objects.hasNext()) {
            UserFeature object = (UserFeature) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$settingName = object.realmGet$settingName();
                    if (realmGet$settingName != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.settingNameIndex, rowIndex, realmGet$settingName, false);
                    }
                    RealmBoolean realmBooleanObj = object.realmGet$realmBoolean();
                    if (realmBooleanObj != null) {
                        Long cacherealmBoolean = (Long) cache.get(realmBooleanObj);
                        if (cacherealmBoolean == null) {
                            cacherealmBoolean = Long.valueOf(RealmBooleanRealmProxy.insert(realm, realmBooleanObj, (Map) cache));
                        }
                        table.setLink(columnInfo.realmBooleanIndex, rowIndex, cacherealmBoolean.longValue(), false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, UserFeature object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(UserFeature.class);
        long tableNativePtr = table.getNativePtr();
        UserFeatureColumnInfo columnInfo = (UserFeatureColumnInfo) realm.getSchema().getColumnInfo(UserFeature.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$settingName = object.realmGet$settingName();
        if (realmGet$settingName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.settingNameIndex, rowIndex, realmGet$settingName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.settingNameIndex, rowIndex, false);
        }
        RealmBoolean realmBooleanObj = object.realmGet$realmBoolean();
        if (realmBooleanObj != null) {
            Long cacherealmBoolean = (Long) cache.get(realmBooleanObj);
            if (cacherealmBoolean == null) {
                cacherealmBoolean = Long.valueOf(RealmBooleanRealmProxy.insertOrUpdate(realm, realmBooleanObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.realmBooleanIndex, rowIndex, cacherealmBoolean.longValue(), false);
            return rowIndex;
        }
        Table.nativeNullifyLink(tableNativePtr, columnInfo.realmBooleanIndex, rowIndex);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(UserFeature.class);
        long tableNativePtr = table.getNativePtr();
        UserFeatureColumnInfo columnInfo = (UserFeatureColumnInfo) realm.getSchema().getColumnInfo(UserFeature.class);
        while (objects.hasNext()) {
            UserFeature object = (UserFeature) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$settingName = object.realmGet$settingName();
                    if (realmGet$settingName != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.settingNameIndex, rowIndex, realmGet$settingName, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.settingNameIndex, rowIndex, false);
                    }
                    RealmBoolean realmBooleanObj = object.realmGet$realmBoolean();
                    if (realmBooleanObj != null) {
                        Long cacherealmBoolean = (Long) cache.get(realmBooleanObj);
                        if (cacherealmBoolean == null) {
                            cacherealmBoolean = Long.valueOf(RealmBooleanRealmProxy.insertOrUpdate(realm, realmBooleanObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.realmBooleanIndex, rowIndex, cacherealmBoolean.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.realmBooleanIndex, rowIndex);
                    }
                }
            }
        }
    }

    public static UserFeature createDetachedCopy(UserFeature realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        UserFeature unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new UserFeature();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (UserFeature) cachedObject.object;
        } else {
            unmanagedObject = (UserFeature) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        UserFeatureRealmProxyInterface unmanagedCopy = unmanagedObject;
        UserFeatureRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$settingName(realmSource.realmGet$settingName());
        unmanagedCopy.realmSet$realmBoolean(RealmBooleanRealmProxy.createDetachedCopy(realmSource.realmGet$realmBoolean(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("UserFeature = proxy[");
        stringBuilder.append("{settingName:");
        stringBuilder.append(realmGet$settingName() != null ? realmGet$settingName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{realmBoolean:");
        stringBuilder.append(realmGet$realmBoolean() != null ? "RealmBoolean" : "null");
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
        UserFeatureRealmProxy aUserFeature = (UserFeatureRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aUserFeature.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUserFeature.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aUserFeature.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
