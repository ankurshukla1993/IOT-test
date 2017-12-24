package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.ContentFeature;
import com.cooey.common.vo.careplan.Template;
import com.facebook.share.internal.ShareConstants;
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

public class ContentFeatureRealmProxy extends ContentFeature implements RealmObjectProxy, ContentFeatureRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ContentFeatureColumnInfo columnInfo;
    private ProxyState<ContentFeature> proxyState;

    static final class ContentFeatureColumnInfo extends ColumnInfo {
        long idIndex;
        long templateIndex;
        long tenantIdIndex;
        long typeIndex;

        ContentFeatureColumnInfo(OsSchemaInfo schemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("ContentFeature");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.templateIndex = addColumnDetails("template", objectSchemaInfo);
        }

        ContentFeatureColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new ContentFeatureColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ContentFeatureColumnInfo src = (ContentFeatureColumnInfo) rawSrc;
            ContentFeatureColumnInfo dst = (ContentFeatureColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.typeIndex = src.typeIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.templateIndex = src.templateIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("type");
        fieldNames.add("tenantId");
        fieldNames.add("template");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ContentFeatureRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ContentFeatureColumnInfo) context.getColumnInfo();
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
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.idIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.idIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.idIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.idIndex, row.getIndex(), value, true);
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

    public String realmGet$tenantId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.tenantIdIndex);
    }

    public void realmSet$tenantId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.tenantIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.tenantIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.tenantIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.tenantIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public Template realmGet$template() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.templateIndex)) {
            return null;
        }
        return (Template) this.proxyState.getRealm$realm().get(Template.class, this.proxyState.getRow$realm().getLink(this.columnInfo.templateIndex), false, Collections.emptyList());
    }

    public void realmSet$template(Template value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.templateIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.templateIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("template")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Template) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.templateIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.templateIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("ContentFeature");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("template", RealmFieldType.OBJECT, "Template");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ContentFeatureColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ContentFeatureColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_ContentFeature";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static ContentFeature createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        if (json.has("template")) {
            excludeFields.add("template");
        }
        ContentFeature obj = (ContentFeature) realm.createObjectInternal(ContentFeature.class, true, excludeFields);
        ContentFeatureRealmProxyInterface objProxy = obj;
        if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                objProxy.realmSet$id(null);
            } else {
                objProxy.realmSet$id(json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type(json.getString("type"));
            }
        }
        if (json.has("tenantId")) {
            if (json.isNull("tenantId")) {
                objProxy.realmSet$tenantId(null);
            } else {
                objProxy.realmSet$tenantId(json.getString("tenantId"));
            }
        }
        if (json.has("template")) {
            if (json.isNull("template")) {
                objProxy.realmSet$template(null);
            } else {
                objProxy.realmSet$template(TemplateRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("template"), update));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static ContentFeature createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        ContentFeature obj = new ContentFeature();
        ContentFeatureRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("tenantId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tenantId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tenantId(null);
                }
            } else if (!name.equals("template")) {
                reader.skipValue();
            } else if (reader.peek() == JsonToken.NULL) {
                reader.skipValue();
                objProxy.realmSet$template(null);
            } else {
                objProxy.realmSet$template(TemplateRealmProxy.createUsingJsonStream(realm, reader));
            }
        }
        reader.endObject();
        return (ContentFeature) realm.copyToRealm(obj);
    }

    public static ContentFeature copyOrUpdate(Realm realm, ContentFeature object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (ContentFeature) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static ContentFeature copy(Realm realm, ContentFeature newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (ContentFeature) cachedRealmObject;
        }
        ContentFeature realmObject = (ContentFeature) realm.createObjectInternal(ContentFeature.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ContentFeatureRealmProxyInterface realmObjectSource = newObject;
        ContentFeatureRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$id(realmObjectSource.realmGet$id());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        Template templateObj = realmObjectSource.realmGet$template();
        if (templateObj == null) {
            realmObjectCopy.realmSet$template(null);
        } else {
            Template cachetemplate = (Template) cache.get(templateObj);
            if (cachetemplate != null) {
                realmObjectCopy.realmSet$template(cachetemplate);
            } else {
                realmObjectCopy.realmSet$template(TemplateRealmProxy.copyOrUpdate(realm, templateObj, update, cache));
            }
        }
        return realmObject;
    }

    public static long insert(Realm realm, ContentFeature object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(ContentFeature.class);
        long tableNativePtr = table.getNativePtr();
        ContentFeatureColumnInfo columnInfo = (ContentFeatureColumnInfo) realm.getSchema().getColumnInfo(ContentFeature.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$id = object.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        }
        Template templateObj = object.realmGet$template();
        if (templateObj == null) {
            return rowIndex;
        }
        Long cachetemplate = (Long) cache.get(templateObj);
        if (cachetemplate == null) {
            cachetemplate = Long.valueOf(TemplateRealmProxy.insert(realm, templateObj, (Map) cache));
        }
        Table.nativeSetLink(tableNativePtr, columnInfo.templateIndex, rowIndex, cachetemplate.longValue(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(ContentFeature.class);
        long tableNativePtr = table.getNativePtr();
        ContentFeatureColumnInfo columnInfo = (ContentFeatureColumnInfo) realm.getSchema().getColumnInfo(ContentFeature.class);
        while (objects.hasNext()) {
            ContentFeature object = (ContentFeature) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$id = object.realmGet$id();
                    if (realmGet$id != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    }
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                    Template templateObj = object.realmGet$template();
                    if (templateObj != null) {
                        Long cachetemplate = (Long) cache.get(templateObj);
                        if (cachetemplate == null) {
                            cachetemplate = Long.valueOf(TemplateRealmProxy.insert(realm, templateObj, (Map) cache));
                        }
                        table.setLink(columnInfo.templateIndex, rowIndex, cachetemplate.longValue(), false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, ContentFeature object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(ContentFeature.class);
        long tableNativePtr = table.getNativePtr();
        ContentFeatureColumnInfo columnInfo = (ContentFeatureColumnInfo) realm.getSchema().getColumnInfo(ContentFeature.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$id = object.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        }
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
        }
        Template templateObj = object.realmGet$template();
        if (templateObj != null) {
            Long cachetemplate = (Long) cache.get(templateObj);
            if (cachetemplate == null) {
                cachetemplate = Long.valueOf(TemplateRealmProxy.insertOrUpdate(realm, templateObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.templateIndex, rowIndex, cachetemplate.longValue(), false);
            return rowIndex;
        }
        Table.nativeNullifyLink(tableNativePtr, columnInfo.templateIndex, rowIndex);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(ContentFeature.class);
        long tableNativePtr = table.getNativePtr();
        ContentFeatureColumnInfo columnInfo = (ContentFeatureColumnInfo) realm.getSchema().getColumnInfo(ContentFeature.class);
        while (objects.hasNext()) {
            ContentFeature object = (ContentFeature) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$id = object.realmGet$id();
                    if (realmGet$id != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
                    }
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
                    }
                    Template templateObj = object.realmGet$template();
                    if (templateObj != null) {
                        Long cachetemplate = (Long) cache.get(templateObj);
                        if (cachetemplate == null) {
                            cachetemplate = Long.valueOf(TemplateRealmProxy.insertOrUpdate(realm, templateObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.templateIndex, rowIndex, cachetemplate.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.templateIndex, rowIndex);
                    }
                }
            }
        }
    }

    public static ContentFeature createDetachedCopy(ContentFeature realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        ContentFeature unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new ContentFeature();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (ContentFeature) cachedObject.object;
        } else {
            unmanagedObject = (ContentFeature) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ContentFeatureRealmProxyInterface unmanagedCopy = unmanagedObject;
        ContentFeatureRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$template(TemplateRealmProxy.createDetachedCopy(realmSource.realmGet$template(), currentDepth + 1, maxDepth, cache));
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
        ContentFeatureRealmProxy aContentFeature = (ContentFeatureRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aContentFeature.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aContentFeature.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aContentFeature.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
