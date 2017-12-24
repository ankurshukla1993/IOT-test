package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.converter.Tag;
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

public class TagRealmProxy extends Tag implements RealmObjectProxy, TagRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TagColumnInfo columnInfo;
    private ProxyState<Tag> proxyState;

    static final class TagColumnInfo extends ColumnInfo {
        long valueIndex;

        TagColumnInfo(OsSchemaInfo schemaInfo) {
            super(1);
            this.valueIndex = addColumnDetails("value", schemaInfo.getObjectSchemaInfo("Tag"));
        }

        TagColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new TagColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ((TagColumnInfo) rawDst).valueIndex = ((TagColumnInfo) rawSrc).valueIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("value");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TagRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TagColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Tag");
        builder.addPersistedProperty("value", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TagColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new TagColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Tag";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Tag createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        Tag obj = (Tag) realm.createObjectInternal(Tag.class, true, Collections.emptyList());
        TagRealmProxyInterface objProxy = obj;
        if (json.has("value")) {
            if (json.isNull("value")) {
                objProxy.realmSet$value(null);
            } else {
                objProxy.realmSet$value(json.getString("value"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Tag createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Tag obj = new Tag();
        TagRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            if (!reader.nextName().equals("value")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$value(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$value(null);
            }
        }
        reader.endObject();
        return (Tag) realm.copyToRealm(obj);
    }

    public static Tag copyOrUpdate(Realm realm, Tag object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Tag) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Tag copy(Realm realm, Tag newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Tag) cachedRealmObject;
        }
        Tag realmObject = (Tag) realm.createObjectInternal(Tag.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.realmSet$value(newObject.realmGet$value());
        return realmObject;
    }

    public static long insert(Realm realm, Tag object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Tag.class);
        long tableNativePtr = table.getNativePtr();
        TagColumnInfo columnInfo = (TagColumnInfo) realm.getSchema().getColumnInfo(Tag.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$value = object.realmGet$value();
        if (realmGet$value == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Tag.class);
        long tableNativePtr = table.getNativePtr();
        TagColumnInfo columnInfo = (TagColumnInfo) realm.getSchema().getColumnInfo(Tag.class);
        while (objects.hasNext()) {
            Tag object = (Tag) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$value = object.realmGet$value();
                    if (realmGet$value != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Tag object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Tag.class);
        long tableNativePtr = table.getNativePtr();
        TagColumnInfo columnInfo = (TagColumnInfo) realm.getSchema().getColumnInfo(Tag.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$value = object.realmGet$value();
        if (realmGet$value != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.valueIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Tag.class);
        long tableNativePtr = table.getNativePtr();
        TagColumnInfo columnInfo = (TagColumnInfo) realm.getSchema().getColumnInfo(Tag.class);
        while (objects.hasNext()) {
            Tag object = (Tag) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$value = object.realmGet$value();
                    if (realmGet$value != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.valueIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Tag createDetachedCopy(Tag realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Tag unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Tag();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Tag) cachedObject.object;
        } else {
            unmanagedObject = (Tag) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        unmanagedObject.realmSet$value(realmObject.realmGet$value());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Tag = proxy[");
        stringBuilder.append("{value:");
        stringBuilder.append(realmGet$value() != null ? realmGet$value() : "null");
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
        TagRealmProxy aTag = (TagRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aTag.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTag.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aTag.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
