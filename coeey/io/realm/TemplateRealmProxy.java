package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.Template;
import com.cooey.common.vo.careplan.UrlIdentifier;
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

public class TemplateRealmProxy extends Template implements RealmObjectProxy, TemplateRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TemplateColumnInfo columnInfo;
    private ProxyState<Template> proxyState;
    private RealmList<UrlIdentifier> urlIdentifiersRealmList;

    static final class TemplateColumnInfo extends ColumnInfo {
        long descriptionIndex;
        long urlIdentifiersIndex;

        TemplateColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Template");
            this.descriptionIndex = addColumnDetails("description", objectSchemaInfo);
            this.urlIdentifiersIndex = addColumnDetails("urlIdentifiers", objectSchemaInfo);
        }

        TemplateColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new TemplateColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            TemplateColumnInfo src = (TemplateColumnInfo) rawSrc;
            TemplateColumnInfo dst = (TemplateColumnInfo) rawDst;
            dst.descriptionIndex = src.descriptionIndex;
            dst.urlIdentifiersIndex = src.urlIdentifiersIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("description");
        fieldNames.add("urlIdentifiers");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TemplateRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TemplateColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$description() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.descriptionIndex);
    }

    public void realmSet$description(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.descriptionIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.descriptionIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.descriptionIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.descriptionIndex, row.getIndex(), value, true);
            }
        }
    }

    public RealmList<UrlIdentifier> realmGet$urlIdentifiers() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.urlIdentifiersRealmList != null) {
            return this.urlIdentifiersRealmList;
        }
        this.urlIdentifiersRealmList = new RealmList(UrlIdentifier.class, this.proxyState.getRow$realm().getLinkList(this.columnInfo.urlIdentifiersIndex), this.proxyState.getRealm$realm());
        return this.urlIdentifiersRealmList;
    }

    public void realmSet$urlIdentifiers(RealmList<UrlIdentifier> value) {
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("urlIdentifiers")) {
                return;
            }
            if (!(value == null || value.isManaged())) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<UrlIdentifier> original = value;
                value = new RealmList();
                Iterator it = original.iterator();
                while (it.hasNext()) {
                    UrlIdentifier item = (UrlIdentifier) it.next();
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList osList = this.proxyState.getRow$realm().getLinkList(this.columnInfo.urlIdentifiersIndex);
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
        Builder builder = new Builder("Template");
        builder.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("urlIdentifiers", RealmFieldType.LIST, "UrlIdentifier");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TemplateColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new TemplateColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Template";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Template createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        if (json.has("urlIdentifiers")) {
            excludeFields.add("urlIdentifiers");
        }
        Template obj = (Template) realm.createObjectInternal(Template.class, true, excludeFields);
        TemplateRealmProxyInterface objProxy = obj;
        if (json.has("description")) {
            if (json.isNull("description")) {
                objProxy.realmSet$description(null);
            } else {
                objProxy.realmSet$description(json.getString("description"));
            }
        }
        if (json.has("urlIdentifiers")) {
            if (json.isNull("urlIdentifiers")) {
                objProxy.realmSet$urlIdentifiers(null);
            } else {
                objProxy.realmGet$urlIdentifiers().clear();
                JSONArray array = json.getJSONArray("urlIdentifiers");
                for (int i = 0; i < array.length(); i++) {
                    objProxy.realmGet$urlIdentifiers().add(UrlIdentifierRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update));
                }
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static Template createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        Template obj = new Template();
        TemplateRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$description(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$description(null);
                }
            } else if (!name.equals("urlIdentifiers")) {
                reader.skipValue();
            } else if (reader.peek() == JsonToken.NULL) {
                reader.skipValue();
                objProxy.realmSet$urlIdentifiers(null);
            } else {
                objProxy.realmSet$urlIdentifiers(new RealmList());
                reader.beginArray();
                while (reader.hasNext()) {
                    objProxy.realmGet$urlIdentifiers().add(UrlIdentifierRealmProxy.createUsingJsonStream(realm, reader));
                }
                reader.endArray();
            }
        }
        reader.endObject();
        return (Template) realm.copyToRealm(obj);
    }

    public static Template copyOrUpdate(Realm realm, Template object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Template) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static Template copy(Realm realm, Template newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Template) cachedRealmObject;
        }
        Template realmObject = (Template) realm.createObjectInternal(Template.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        TemplateRealmProxyInterface realmObjectSource = newObject;
        TemplateRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$description(realmObjectSource.realmGet$description());
        RealmList<UrlIdentifier> urlIdentifiersList = realmObjectSource.realmGet$urlIdentifiers();
        if (urlIdentifiersList != null) {
            RealmList<UrlIdentifier> urlIdentifiersRealmList = realmObjectCopy.realmGet$urlIdentifiers();
            urlIdentifiersRealmList.clear();
            for (int i = 0; i < urlIdentifiersList.size(); i++) {
                UrlIdentifier urlIdentifiersItem = (UrlIdentifier) urlIdentifiersList.get(i);
                UrlIdentifier cacheurlIdentifiers = (UrlIdentifier) cache.get(urlIdentifiersItem);
                if (cacheurlIdentifiers != null) {
                    urlIdentifiersRealmList.add(cacheurlIdentifiers);
                } else {
                    urlIdentifiersRealmList.add(UrlIdentifierRealmProxy.copyOrUpdate(realm, urlIdentifiersItem, update, cache));
                }
            }
        }
        return realmObject;
    }

    public static long insert(Realm realm, Template object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Template.class);
        long tableNativePtr = table.getNativePtr();
        TemplateColumnInfo columnInfo = (TemplateColumnInfo) realm.getSchema().getColumnInfo(Template.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$description = object.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        }
        RealmList<UrlIdentifier> urlIdentifiersList = object.realmGet$urlIdentifiers();
        if (urlIdentifiersList == null) {
            return rowIndex;
        }
        OsList urlIdentifiersOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.urlIdentifiersIndex);
        Iterator it = urlIdentifiersList.iterator();
        while (it.hasNext()) {
            UrlIdentifier urlIdentifiersItem = (UrlIdentifier) it.next();
            Long cacheItemIndexurlIdentifiers = (Long) cache.get(urlIdentifiersItem);
            if (cacheItemIndexurlIdentifiers == null) {
                cacheItemIndexurlIdentifiers = Long.valueOf(UrlIdentifierRealmProxy.insert(realm, urlIdentifiersItem, (Map) cache));
            }
            urlIdentifiersOsList.addRow(cacheItemIndexurlIdentifiers.longValue());
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Template.class);
        long tableNativePtr = table.getNativePtr();
        TemplateColumnInfo columnInfo = (TemplateColumnInfo) realm.getSchema().getColumnInfo(Template.class);
        while (objects.hasNext()) {
            Template object = (Template) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$description = object.realmGet$description();
                    if (realmGet$description != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                    }
                    RealmList<UrlIdentifier> urlIdentifiersList = object.realmGet$urlIdentifiers();
                    if (urlIdentifiersList != null) {
                        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.urlIdentifiersIndex);
                        Iterator it = urlIdentifiersList.iterator();
                        while (it.hasNext()) {
                            UrlIdentifier urlIdentifiersItem = (UrlIdentifier) it.next();
                            Long cacheItemIndexurlIdentifiers = (Long) cache.get(urlIdentifiersItem);
                            if (cacheItemIndexurlIdentifiers == null) {
                                cacheItemIndexurlIdentifiers = Long.valueOf(UrlIdentifierRealmProxy.insert(realm, urlIdentifiersItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexurlIdentifiers.longValue());
                        }
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Template object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(Template.class);
        long tableNativePtr = table.getNativePtr();
        TemplateColumnInfo columnInfo = (TemplateColumnInfo) realm.getSchema().getColumnInfo(Template.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$description = object.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
        }
        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.urlIdentifiersIndex);
        osList.removeAll();
        RealmList<UrlIdentifier> urlIdentifiersList = object.realmGet$urlIdentifiers();
        if (urlIdentifiersList == null) {
            return rowIndex;
        }
        Iterator it = urlIdentifiersList.iterator();
        while (it.hasNext()) {
            UrlIdentifier urlIdentifiersItem = (UrlIdentifier) it.next();
            Long cacheItemIndexurlIdentifiers = (Long) cache.get(urlIdentifiersItem);
            if (cacheItemIndexurlIdentifiers == null) {
                cacheItemIndexurlIdentifiers = Long.valueOf(UrlIdentifierRealmProxy.insertOrUpdate(realm, urlIdentifiersItem, (Map) cache));
            }
            osList.addRow(cacheItemIndexurlIdentifiers.longValue());
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Template.class);
        long tableNativePtr = table.getNativePtr();
        TemplateColumnInfo columnInfo = (TemplateColumnInfo) realm.getSchema().getColumnInfo(Template.class);
        while (objects.hasNext()) {
            Template object = (Template) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$description = object.realmGet$description();
                    if (realmGet$description != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
                    }
                    OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.urlIdentifiersIndex);
                    osList.removeAll();
                    RealmList<UrlIdentifier> urlIdentifiersList = object.realmGet$urlIdentifiers();
                    if (urlIdentifiersList != null) {
                        Iterator it = urlIdentifiersList.iterator();
                        while (it.hasNext()) {
                            UrlIdentifier urlIdentifiersItem = (UrlIdentifier) it.next();
                            Long cacheItemIndexurlIdentifiers = (Long) cache.get(urlIdentifiersItem);
                            if (cacheItemIndexurlIdentifiers == null) {
                                cacheItemIndexurlIdentifiers = Long.valueOf(UrlIdentifierRealmProxy.insertOrUpdate(realm, urlIdentifiersItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexurlIdentifiers.longValue());
                        }
                    }
                }
            }
        }
    }

    public static Template createDetachedCopy(Template realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Template unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Template();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Template) cachedObject.object;
        } else {
            unmanagedObject = (Template) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        TemplateRealmProxyInterface unmanagedCopy = unmanagedObject;
        TemplateRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$description(realmSource.realmGet$description());
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$urlIdentifiers(null);
        } else {
            RealmList<UrlIdentifier> managedurlIdentifiersList = realmSource.realmGet$urlIdentifiers();
            RealmList<UrlIdentifier> unmanagedurlIdentifiersList = new RealmList();
            unmanagedCopy.realmSet$urlIdentifiers(unmanagedurlIdentifiersList);
            int nextDepth = currentDepth + 1;
            int size = managedurlIdentifiersList.size();
            for (int i = 0; i < size; i++) {
                unmanagedurlIdentifiersList.add(UrlIdentifierRealmProxy.createDetachedCopy((UrlIdentifier) managedurlIdentifiersList.get(i), nextDepth, maxDepth, cache));
            }
        }
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
        TemplateRealmProxy aTemplate = (TemplateRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aTemplate.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTemplate.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aTemplate.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
