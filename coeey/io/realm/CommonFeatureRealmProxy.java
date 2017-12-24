package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Vital;
import com.cooey.common.vo.careplan.CommonFeature;
import com.cooey.common.vo.careplan.Limits;
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

public class CommonFeatureRealmProxy extends CommonFeature implements RealmObjectProxy, CommonFeatureRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private CommonFeatureColumnInfo columnInfo;
    private ProxyState<CommonFeature> proxyState;

    static final class CommonFeatureColumnInfo extends ColumnInfo {
        long idIndex;
        long limitsIndex;
        long nameIndex;
        long parametersIndex;
        long tenantIdIndex;
        long typeIndex;
        long vitalParametersIndex;

        CommonFeatureColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("CommonFeature");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.limitsIndex = addColumnDetails("limits", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.parametersIndex = addColumnDetails("parameters", objectSchemaInfo);
            this.vitalParametersIndex = addColumnDetails("vitalParameters", objectSchemaInfo);
        }

        CommonFeatureColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new CommonFeatureColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            CommonFeatureColumnInfo src = (CommonFeatureColumnInfo) rawSrc;
            CommonFeatureColumnInfo dst = (CommonFeatureColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.typeIndex = src.typeIndex;
            dst.limitsIndex = src.limitsIndex;
            dst.nameIndex = src.nameIndex;
            dst.parametersIndex = src.parametersIndex;
            dst.vitalParametersIndex = src.vitalParametersIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("tenantId");
        fieldNames.add("type");
        fieldNames.add("limits");
        fieldNames.add("name");
        fieldNames.add("parameters");
        fieldNames.add("vitalParameters");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CommonFeatureRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (CommonFeatureColumnInfo) context.getColumnInfo();
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

    public Limits realmGet$limits() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.limitsIndex)) {
            return null;
        }
        return (Limits) this.proxyState.getRealm$realm().get(Limits.class, this.proxyState.getRow$realm().getLink(this.columnInfo.limitsIndex), false, Collections.emptyList());
    }

    public void realmSet$limits(Limits value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.limitsIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.limitsIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("limits")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Limits) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.limitsIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.limitsIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
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

    public String realmGet$parameters() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.parametersIndex);
    }

    public void realmSet$parameters(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.parametersIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.parametersIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.parametersIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.parametersIndex, row.getIndex(), value, true);
            }
        }
    }

    public Vital realmGet$vitalParameters() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.vitalParametersIndex)) {
            return null;
        }
        return (Vital) this.proxyState.getRealm$realm().get(Vital.class, this.proxyState.getRow$realm().getLink(this.columnInfo.vitalParametersIndex), false, Collections.emptyList());
    }

    public void realmSet$vitalParameters(Vital value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.vitalParametersIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.vitalParametersIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("vitalParameters")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Vital) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.vitalParametersIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.vitalParametersIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("CommonFeature");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("limits", RealmFieldType.OBJECT, "Limits");
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("parameters", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("vitalParameters", RealmFieldType.OBJECT, "Vital");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CommonFeatureColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new CommonFeatureColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_CommonFeature";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static CommonFeature createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(2);
        CommonFeature commonFeature = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(CommonFeature.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(CommonFeature.class), false, Collections.emptyList());
                    commonFeature = new CommonFeatureRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (commonFeature == null) {
            if (json.has("limits")) {
                excludeFields.add("limits");
            }
            if (json.has("vitalParameters")) {
                excludeFields.add("vitalParameters");
            }
            if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                    commonFeature = (CommonFeatureRealmProxy) realm.createObjectInternal(CommonFeature.class, null, true, excludeFields);
                } else {
                    String string = json.getString(ShareConstants.WEB_DIALOG_PARAM_ID);
                    CommonFeatureRealmProxy obj = (CommonFeatureRealmProxy) realm.createObjectInternal(CommonFeature.class, string, true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        CommonFeatureRealmProxyInterface objProxy = commonFeature;
        if (json.has("tenantId")) {
            if (json.isNull("tenantId")) {
                objProxy.realmSet$tenantId(null);
            } else {
                objProxy.realmSet$tenantId(json.getString("tenantId"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type(json.getString("type"));
            }
        }
        if (json.has("limits")) {
            if (json.isNull("limits")) {
                objProxy.realmSet$limits(null);
            } else {
                objProxy.realmSet$limits(LimitsRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("limits"), update));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name(json.getString("name"));
            }
        }
        if (json.has("parameters")) {
            if (json.isNull("parameters")) {
                objProxy.realmSet$parameters(null);
            } else {
                objProxy.realmSet$parameters(json.getString("parameters"));
            }
        }
        if (json.has("vitalParameters")) {
            if (json.isNull("vitalParameters")) {
                objProxy.realmSet$vitalParameters(null);
            } else {
                objProxy.realmSet$vitalParameters(VitalRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("vitalParameters"), update));
            }
        }
        return commonFeature;
    }

    @TargetApi(11)
    public static CommonFeature createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        CommonFeature obj = new CommonFeature();
        CommonFeatureRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("tenantId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tenantId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tenantId(null);
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("limits")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$limits(null);
                } else {
                    objProxy.realmSet$limits(LimitsRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("parameters")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$parameters(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$parameters(null);
                }
            } else if (!name.equals("vitalParameters")) {
                reader.skipValue();
            } else if (reader.peek() == JsonToken.NULL) {
                reader.skipValue();
                objProxy.realmSet$vitalParameters(null);
            } else {
                objProxy.realmSet$vitalParameters(VitalRealmProxy.createUsingJsonStream(realm, reader));
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (CommonFeature) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static CommonFeature copyOrUpdate(Realm realm, CommonFeature object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (CommonFeature) cachedRealmObject;
        }
        CommonFeature update2;
        CommonFeature realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(CommonFeature.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(CommonFeature.class), false, Collections.emptyList());
                    CommonFeature realmObject2 = new CommonFeatureRealmProxy();
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

    public static CommonFeature copy(Realm realm, CommonFeature newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (CommonFeature) cachedRealmObject;
        }
        CommonFeature realmObject = (CommonFeature) realm.createObjectInternal(CommonFeature.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        CommonFeatureRealmProxyInterface realmObjectSource = newObject;
        CommonFeatureRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        Limits limitsObj = realmObjectSource.realmGet$limits();
        if (limitsObj == null) {
            realmObjectCopy.realmSet$limits(null);
        } else {
            Limits cachelimits = (Limits) cache.get(limitsObj);
            if (cachelimits != null) {
                realmObjectCopy.realmSet$limits(cachelimits);
            } else {
                realmObjectCopy.realmSet$limits(LimitsRealmProxy.copyOrUpdate(realm, limitsObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$parameters(realmObjectSource.realmGet$parameters());
        Vital vitalParametersObj = realmObjectSource.realmGet$vitalParameters();
        if (vitalParametersObj == null) {
            realmObjectCopy.realmSet$vitalParameters(null);
        } else {
            Vital cachevitalParameters = (Vital) cache.get(vitalParametersObj);
            if (cachevitalParameters != null) {
                realmObjectCopy.realmSet$vitalParameters(cachevitalParameters);
            } else {
                realmObjectCopy.realmSet$vitalParameters(VitalRealmProxy.copyOrUpdate(realm, vitalParametersObj, update, cache));
            }
        }
        return realmObject;
    }

    public static long insert(Realm realm, CommonFeature object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(CommonFeature.class);
        long tableNativePtr = table.getNativePtr();
        CommonFeatureColumnInfo columnInfo = (CommonFeatureColumnInfo) realm.getSchema().getColumnInfo(CommonFeature.class);
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
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }
        Limits limitsObj = object.realmGet$limits();
        if (limitsObj != null) {
            Long cachelimits = (Long) cache.get(limitsObj);
            if (cachelimits == null) {
                cachelimits = Long.valueOf(LimitsRealmProxy.insert(realm, limitsObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.limitsIndex, rowIndex, cachelimits.longValue(), false);
        }
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        }
        Vital vitalParametersObj = object.realmGet$vitalParameters();
        if (vitalParametersObj == null) {
            return rowIndex;
        }
        Long cachevitalParameters = (Long) cache.get(vitalParametersObj);
        if (cachevitalParameters == null) {
            cachevitalParameters = Long.valueOf(VitalRealmProxy.insert(realm, vitalParametersObj, (Map) cache));
        }
        Table.nativeSetLink(tableNativePtr, columnInfo.vitalParametersIndex, rowIndex, cachevitalParameters.longValue(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(CommonFeature.class);
        long tableNativePtr = table.getNativePtr();
        CommonFeatureColumnInfo columnInfo = (CommonFeatureColumnInfo) realm.getSchema().getColumnInfo(CommonFeature.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            CommonFeature object = (CommonFeature) objects.next();
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
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    }
                    Limits limitsObj = object.realmGet$limits();
                    if (limitsObj != null) {
                        Long cachelimits = (Long) cache.get(limitsObj);
                        if (cachelimits == null) {
                            cachelimits = Long.valueOf(LimitsRealmProxy.insert(realm, limitsObj, (Map) cache));
                        }
                        table.setLink(columnInfo.limitsIndex, rowIndex, cachelimits.longValue(), false);
                    }
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    }
                    Vital vitalParametersObj = object.realmGet$vitalParameters();
                    if (vitalParametersObj != null) {
                        Long cachevitalParameters = (Long) cache.get(vitalParametersObj);
                        if (cachevitalParameters == null) {
                            cachevitalParameters = Long.valueOf(VitalRealmProxy.insert(realm, vitalParametersObj, (Map) cache));
                        }
                        table.setLink(columnInfo.vitalParametersIndex, rowIndex, cachevitalParameters.longValue(), false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, CommonFeature object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(CommonFeature.class);
        long tableNativePtr = table.getNativePtr();
        CommonFeatureColumnInfo columnInfo = (CommonFeatureColumnInfo) realm.getSchema().getColumnInfo(CommonFeature.class);
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
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        }
        Limits limitsObj = object.realmGet$limits();
        if (limitsObj != null) {
            Long cachelimits = (Long) cache.get(limitsObj);
            if (cachelimits == null) {
                cachelimits = Long.valueOf(LimitsRealmProxy.insertOrUpdate(realm, limitsObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.limitsIndex, rowIndex, cachelimits.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.limitsIndex, rowIndex);
        }
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
        }
        Vital vitalParametersObj = object.realmGet$vitalParameters();
        if (vitalParametersObj != null) {
            Long cachevitalParameters = (Long) cache.get(vitalParametersObj);
            if (cachevitalParameters == null) {
                cachevitalParameters = Long.valueOf(VitalRealmProxy.insertOrUpdate(realm, vitalParametersObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.vitalParametersIndex, rowIndex, cachevitalParameters.longValue(), false);
            return rowIndex;
        }
        Table.nativeNullifyLink(tableNativePtr, columnInfo.vitalParametersIndex, rowIndex);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(CommonFeature.class);
        long tableNativePtr = table.getNativePtr();
        CommonFeatureColumnInfo columnInfo = (CommonFeatureColumnInfo) realm.getSchema().getColumnInfo(CommonFeature.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            CommonFeature object = (CommonFeature) objects.next();
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
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
                    }
                    Limits limitsObj = object.realmGet$limits();
                    if (limitsObj != null) {
                        Long cachelimits = (Long) cache.get(limitsObj);
                        if (cachelimits == null) {
                            cachelimits = Long.valueOf(LimitsRealmProxy.insertOrUpdate(realm, limitsObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.limitsIndex, rowIndex, cachelimits.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.limitsIndex, rowIndex);
                    }
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                    }
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
                    }
                    Vital vitalParametersObj = object.realmGet$vitalParameters();
                    if (vitalParametersObj != null) {
                        Long cachevitalParameters = (Long) cache.get(vitalParametersObj);
                        if (cachevitalParameters == null) {
                            cachevitalParameters = Long.valueOf(VitalRealmProxy.insertOrUpdate(realm, vitalParametersObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.vitalParametersIndex, rowIndex, cachevitalParameters.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.vitalParametersIndex, rowIndex);
                    }
                }
            }
        }
    }

    public static CommonFeature createDetachedCopy(CommonFeature realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CommonFeature unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new CommonFeature();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (CommonFeature) cachedObject.object;
        } else {
            unmanagedObject = (CommonFeature) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        CommonFeatureRealmProxyInterface unmanagedCopy = unmanagedObject;
        CommonFeatureRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$limits(LimitsRealmProxy.createDetachedCopy(realmSource.realmGet$limits(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$parameters(realmSource.realmGet$parameters());
        unmanagedCopy.realmSet$vitalParameters(VitalRealmProxy.createDetachedCopy(realmSource.realmGet$vitalParameters(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    static CommonFeature update(Realm realm, CommonFeature realmObject, CommonFeature newObject, Map<RealmModel, RealmObjectProxy> cache) {
        CommonFeatureRealmProxyInterface realmObjectTarget = realmObject;
        CommonFeatureRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectTarget.realmSet$type(realmObjectSource.realmGet$type());
        Limits limitsObj = realmObjectSource.realmGet$limits();
        if (limitsObj == null) {
            realmObjectTarget.realmSet$limits(null);
        } else {
            Limits cachelimits = (Limits) cache.get(limitsObj);
            if (cachelimits != null) {
                realmObjectTarget.realmSet$limits(cachelimits);
            } else {
                realmObjectTarget.realmSet$limits(LimitsRealmProxy.copyOrUpdate(realm, limitsObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$parameters(realmObjectSource.realmGet$parameters());
        Vital vitalParametersObj = realmObjectSource.realmGet$vitalParameters();
        if (vitalParametersObj == null) {
            realmObjectTarget.realmSet$vitalParameters(null);
        } else {
            Vital cachevitalParameters = (Vital) cache.get(vitalParametersObj);
            if (cachevitalParameters != null) {
                realmObjectTarget.realmSet$vitalParameters(cachevitalParameters);
            } else {
                realmObjectTarget.realmSet$vitalParameters(VitalRealmProxy.copyOrUpdate(realm, vitalParametersObj, true, cache));
            }
        }
        return realmObject;
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
        CommonFeatureRealmProxy aCommonFeature = (CommonFeatureRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aCommonFeature.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCommonFeature.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aCommonFeature.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
