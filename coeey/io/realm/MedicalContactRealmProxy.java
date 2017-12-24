package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.MedicalContact;
import com.cooey.common.vo.RealmString;
import com.facebook.share.internal.ShareConstants;
import io.realm.exceptions.RealmException;
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

public class MedicalContactRealmProxy extends MedicalContact implements RealmObjectProxy, MedicalContactRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private MedicalContactColumnInfo columnInfo;
    private RealmList<RealmString> mobileNumbersRealmList;
    private ProxyState<MedicalContact> proxyState;

    static final class MedicalContactColumnInfo extends ColumnInfo {
        long emailIndex;
        long idIndex;
        long mobileNumbersIndex;
        long nameIndex;
        long tenantIdIndex;
        long typeIndex;
        long userIdIndex;

        MedicalContactColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("MedicalContact");
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.mobileNumbersIndex = addColumnDetails("mobileNumbers", objectSchemaInfo);
            this.emailIndex = addColumnDetails("email", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.userIdIndex = addColumnDetails("userId", objectSchemaInfo);
        }

        MedicalContactColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new MedicalContactColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            MedicalContactColumnInfo src = (MedicalContactColumnInfo) rawSrc;
            MedicalContactColumnInfo dst = (MedicalContactColumnInfo) rawDst;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.idIndex = src.idIndex;
            dst.nameIndex = src.nameIndex;
            dst.mobileNumbersIndex = src.mobileNumbersIndex;
            dst.emailIndex = src.emailIndex;
            dst.typeIndex = src.typeIndex;
            dst.userIdIndex = src.userIdIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("tenantId");
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("name");
        fieldNames.add("mobileNumbers");
        fieldNames.add("email");
        fieldNames.add("type");
        fieldNames.add("userId");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    MedicalContactRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (MedicalContactColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
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

    public RealmList<RealmString> realmGet$mobileNumbers() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.mobileNumbersRealmList != null) {
            return this.mobileNumbersRealmList;
        }
        this.mobileNumbersRealmList = new RealmList(RealmString.class, this.proxyState.getRow$realm().getLinkList(this.columnInfo.mobileNumbersIndex), this.proxyState.getRealm$realm());
        return this.mobileNumbersRealmList;
    }

    public void realmSet$mobileNumbers(RealmList<RealmString> value) {
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("mobileNumbers")) {
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
        OsList osList = this.proxyState.getRow$realm().getLinkList(this.columnInfo.mobileNumbersIndex);
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

    public String realmGet$email() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.emailIndex);
    }

    public void realmSet$email(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.emailIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.emailIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.emailIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.emailIndex, row.getIndex(), value, true);
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
        Builder builder = new Builder("MedicalContact");
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("mobileNumbers", RealmFieldType.LIST, "RealmString");
        builder.addPersistedProperty("email", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("userId", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MedicalContactColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new MedicalContactColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_MedicalContact";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static MedicalContact createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        MedicalContact medicalContact = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(MedicalContact.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(MedicalContact.class), false, Collections.emptyList());
                    medicalContact = new MedicalContactRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (medicalContact == null) {
            if (json.has("mobileNumbers")) {
                excludeFields.add("mobileNumbers");
            }
            if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                    medicalContact = (MedicalContactRealmProxy) realm.createObjectInternal(MedicalContact.class, null, true, excludeFields);
                } else {
                    String string = json.getString(ShareConstants.WEB_DIALOG_PARAM_ID);
                    MedicalContactRealmProxy obj = (MedicalContactRealmProxy) realm.createObjectInternal(MedicalContact.class, string, true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        MedicalContactRealmProxyInterface objProxy = medicalContact;
        if (json.has("tenantId")) {
            if (json.isNull("tenantId")) {
                objProxy.realmSet$tenantId(null);
            } else {
                objProxy.realmSet$tenantId(json.getString("tenantId"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name(json.getString("name"));
            }
        }
        if (json.has("mobileNumbers")) {
            if (json.isNull("mobileNumbers")) {
                objProxy.realmSet$mobileNumbers(null);
            } else {
                objProxy.realmGet$mobileNumbers().clear();
                JSONArray array = json.getJSONArray("mobileNumbers");
                for (int i = 0; i < array.length(); i++) {
                    objProxy.realmGet$mobileNumbers().add(RealmStringRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update));
                }
            }
        }
        if (json.has("email")) {
            if (json.isNull("email")) {
                objProxy.realmSet$email(null);
            } else {
                objProxy.realmSet$email(json.getString("email"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type(json.getString("type"));
            }
        }
        if (json.has("userId")) {
            if (json.isNull("userId")) {
                objProxy.realmSet$userId(null);
            } else {
                objProxy.realmSet$userId(json.getString("userId"));
            }
        }
        return medicalContact;
    }

    @TargetApi(11)
    public static MedicalContact createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        MedicalContact obj = new MedicalContact();
        MedicalContactRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("tenantId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tenantId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tenantId(null);
                }
            } else if (name.equals(ShareConstants.WEB_DIALOG_PARAM_ID)) {
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
            } else if (name.equals("mobileNumbers")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$mobileNumbers(null);
                } else {
                    objProxy.realmSet$mobileNumbers(new RealmList());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        objProxy.realmGet$mobileNumbers().add(RealmStringRealmProxy.createUsingJsonStream(realm, reader));
                    }
                    reader.endArray();
                }
            } else if (name.equals("email")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$email(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$email(null);
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
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
            return (MedicalContact) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static MedicalContact copyOrUpdate(Realm realm, MedicalContact object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (MedicalContact) cachedRealmObject;
        }
        MedicalContact update2;
        MedicalContact realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(MedicalContact.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(MedicalContact.class), false, Collections.emptyList());
                    MedicalContact realmObject2 = new MedicalContactRealmProxy();
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

    public static MedicalContact copy(Realm realm, MedicalContact newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (MedicalContact) cachedRealmObject;
        }
        MedicalContact realmObject = (MedicalContact) realm.createObjectInternal(MedicalContact.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        MedicalContactRealmProxyInterface realmObjectSource = newObject;
        MedicalContactRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        RealmList<RealmString> mobileNumbersList = realmObjectSource.realmGet$mobileNumbers();
        if (mobileNumbersList != null) {
            RealmList<RealmString> mobileNumbersRealmList = realmObjectCopy.realmGet$mobileNumbers();
            mobileNumbersRealmList.clear();
            for (int i = 0; i < mobileNumbersList.size(); i++) {
                RealmString mobileNumbersItem = (RealmString) mobileNumbersList.get(i);
                RealmString cachemobileNumbers = (RealmString) cache.get(mobileNumbersItem);
                if (cachemobileNumbers != null) {
                    mobileNumbersRealmList.add(cachemobileNumbers);
                } else {
                    mobileNumbersRealmList.add(RealmStringRealmProxy.copyOrUpdate(realm, mobileNumbersItem, update, cache));
                }
            }
        }
        realmObjectCopy.realmSet$email(realmObjectSource.realmGet$email());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$userId(realmObjectSource.realmGet$userId());
        return realmObject;
    }

    public static long insert(Realm realm, MedicalContact object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(MedicalContact.class);
        long tableNativePtr = table.getNativePtr();
        MedicalContactColumnInfo columnInfo = (MedicalContactColumnInfo) realm.getSchema().getColumnInfo(MedicalContact.class);
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
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        RealmList<RealmString> mobileNumbersList = object.realmGet$mobileNumbers();
        if (mobileNumbersList != null) {
            OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.mobileNumbersIndex);
            Iterator it = mobileNumbersList.iterator();
            while (it.hasNext()) {
                RealmString mobileNumbersItem = (RealmString) it.next();
                Long cacheItemIndexmobileNumbers = (Long) cache.get(mobileNumbersItem);
                if (cacheItemIndexmobileNumbers == null) {
                    cacheItemIndexmobileNumbers = Long.valueOf(RealmStringRealmProxy.insert(realm, mobileNumbersItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexmobileNumbers.longValue());
            }
        }
        String realmGet$email = object.realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }
        String realmGet$userId = object.realmGet$userId();
        if (realmGet$userId == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(MedicalContact.class);
        long tableNativePtr = table.getNativePtr();
        MedicalContactColumnInfo columnInfo = (MedicalContactColumnInfo) realm.getSchema().getColumnInfo(MedicalContact.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            MedicalContact object = (MedicalContact) objects.next();
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
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    RealmList<RealmString> mobileNumbersList = object.realmGet$mobileNumbers();
                    if (mobileNumbersList != null) {
                        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.mobileNumbersIndex);
                        Iterator it = mobileNumbersList.iterator();
                        while (it.hasNext()) {
                            RealmString mobileNumbersItem = (RealmString) it.next();
                            Long cacheItemIndexmobileNumbers = (Long) cache.get(mobileNumbersItem);
                            if (cacheItemIndexmobileNumbers == null) {
                                cacheItemIndexmobileNumbers = Long.valueOf(RealmStringRealmProxy.insert(realm, mobileNumbersItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexmobileNumbers.longValue());
                        }
                    }
                    String realmGet$email = object.realmGet$email();
                    if (realmGet$email != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    }
                    String realmGet$userId = object.realmGet$userId();
                    if (realmGet$userId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, MedicalContact object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(MedicalContact.class);
        long tableNativePtr = table.getNativePtr();
        MedicalContactColumnInfo columnInfo = (MedicalContactColumnInfo) realm.getSchema().getColumnInfo(MedicalContact.class);
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
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.mobileNumbersIndex);
        osList.removeAll();
        RealmList<RealmString> mobileNumbersList = object.realmGet$mobileNumbers();
        if (mobileNumbersList != null) {
            Iterator it = mobileNumbersList.iterator();
            while (it.hasNext()) {
                RealmString mobileNumbersItem = (RealmString) it.next();
                Long cacheItemIndexmobileNumbers = (Long) cache.get(mobileNumbersItem);
                if (cacheItemIndexmobileNumbers == null) {
                    cacheItemIndexmobileNumbers = Long.valueOf(RealmStringRealmProxy.insertOrUpdate(realm, mobileNumbersItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexmobileNumbers.longValue());
            }
        }
        String realmGet$email = object.realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.emailIndex, rowIndex, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
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
        Table table = realm.getTable(MedicalContact.class);
        long tableNativePtr = table.getNativePtr();
        MedicalContactColumnInfo columnInfo = (MedicalContactColumnInfo) realm.getSchema().getColumnInfo(MedicalContact.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            MedicalContact object = (MedicalContact) objects.next();
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
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                    }
                    OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.mobileNumbersIndex);
                    osList.removeAll();
                    RealmList<RealmString> mobileNumbersList = object.realmGet$mobileNumbers();
                    if (mobileNumbersList != null) {
                        Iterator it = mobileNumbersList.iterator();
                        while (it.hasNext()) {
                            RealmString mobileNumbersItem = (RealmString) it.next();
                            Long cacheItemIndexmobileNumbers = (Long) cache.get(mobileNumbersItem);
                            if (cacheItemIndexmobileNumbers == null) {
                                cacheItemIndexmobileNumbers = Long.valueOf(RealmStringRealmProxy.insertOrUpdate(realm, mobileNumbersItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexmobileNumbers.longValue());
                        }
                    }
                    String realmGet$email = object.realmGet$email();
                    if (realmGet$email != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.emailIndex, rowIndex, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
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

    public static MedicalContact createDetachedCopy(MedicalContact realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        MedicalContact unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new MedicalContact();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (MedicalContact) cachedObject.object;
        } else {
            unmanagedObject = (MedicalContact) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        MedicalContactRealmProxyInterface unmanagedCopy = unmanagedObject;
        MedicalContactRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$mobileNumbers(null);
        } else {
            RealmList<RealmString> managedmobileNumbersList = realmSource.realmGet$mobileNumbers();
            RealmList<RealmString> unmanagedmobileNumbersList = new RealmList();
            unmanagedCopy.realmSet$mobileNumbers(unmanagedmobileNumbersList);
            int nextDepth = currentDepth + 1;
            int size = managedmobileNumbersList.size();
            for (int i = 0; i < size; i++) {
                unmanagedmobileNumbersList.add(RealmStringRealmProxy.createDetachedCopy((RealmString) managedmobileNumbersList.get(i), nextDepth, maxDepth, cache));
            }
        }
        unmanagedCopy.realmSet$email(realmSource.realmGet$email());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$userId(realmSource.realmGet$userId());
        return unmanagedObject;
    }

    static MedicalContact update(Realm realm, MedicalContact realmObject, MedicalContact newObject, Map<RealmModel, RealmObjectProxy> cache) {
        MedicalContactRealmProxyInterface realmObjectTarget = realmObject;
        MedicalContactRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        RealmList<RealmString> mobileNumbersList = realmObjectSource.realmGet$mobileNumbers();
        RealmList<RealmString> mobileNumbersRealmList = realmObjectTarget.realmGet$mobileNumbers();
        mobileNumbersRealmList.clear();
        if (mobileNumbersList != null) {
            for (int i = 0; i < mobileNumbersList.size(); i++) {
                RealmString mobileNumbersItem = (RealmString) mobileNumbersList.get(i);
                RealmString cachemobileNumbers = (RealmString) cache.get(mobileNumbersItem);
                if (cachemobileNumbers != null) {
                    mobileNumbersRealmList.add(cachemobileNumbers);
                } else {
                    mobileNumbersRealmList.add(RealmStringRealmProxy.copyOrUpdate(realm, mobileNumbersItem, true, cache));
                }
            }
        }
        realmObjectTarget.realmSet$email(realmObjectSource.realmGet$email());
        realmObjectTarget.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectTarget.realmSet$userId(realmObjectSource.realmGet$userId());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("MedicalContact = proxy[");
        stringBuilder.append("{tenantId:");
        stringBuilder.append(realmGet$tenantId() != null ? realmGet$tenantId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mobileNumbers:");
        stringBuilder.append("RealmList<RealmString>[").append(realmGet$mobileNumbers().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{email:");
        stringBuilder.append(realmGet$email() != null ? realmGet$email() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
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
        MedicalContactRealmProxy aMedicalContact = (MedicalContactRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aMedicalContact.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aMedicalContact.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aMedicalContact.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
