package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.Reminder;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.facebook.react.modules.appstate.AppStateModule;
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

public class MedicineRealmProxy extends Medicine implements RealmObjectProxy, MedicineRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private MedicineColumnInfo columnInfo;
    private ProxyState<Medicine> proxyState;
    private RealmList<Reminder> remindersRealmList;

    static final class MedicineColumnInfo extends ColumnInfo {
        long activeIndex;
        long addedOnIndex;
        long applicationIdIndex;
        long archivedIndex;
        long createdOnIndex;
        long descriptionIndex;
        long externalIdIndex;
        long idIndex;
        long medicineIdIndex;
        long nameIndex;
        long notesIndex;
        long remindersIndex;
        long tenantIdIndex;
        long toBeTakenTillIndex;
        long updatedOnIndex;
        long userIdIndex;

        MedicineColumnInfo(OsSchemaInfo schemaInfo) {
            super(16);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo(CTConstants.FAB_MEDICINES);
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.createdOnIndex = addColumnDetails("createdOn", objectSchemaInfo);
            this.updatedOnIndex = addColumnDetails("updatedOn", objectSchemaInfo);
            this.applicationIdIndex = addColumnDetails("applicationId", objectSchemaInfo);
            this.archivedIndex = addColumnDetails("archived", objectSchemaInfo);
            this.externalIdIndex = addColumnDetails(CooeySQLHelper.COL_EXT_ID, objectSchemaInfo);
            this.activeIndex = addColumnDetails(AppStateModule.APP_STATE_ACTIVE, objectSchemaInfo);
            this.remindersIndex = addColumnDetails(CooeySQLHelper.COL_REMINDER, objectSchemaInfo);
            this.medicineIdIndex = addColumnDetails(CooeySQLHelper.COL_MEDID, objectSchemaInfo);
            this.descriptionIndex = addColumnDetails("description", objectSchemaInfo);
            this.userIdIndex = addColumnDetails("userId", objectSchemaInfo);
            this.notesIndex = addColumnDetails("notes", objectSchemaInfo);
            this.addedOnIndex = addColumnDetails("addedOn", objectSchemaInfo);
            this.toBeTakenTillIndex = addColumnDetails("toBeTakenTill", objectSchemaInfo);
        }

        MedicineColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new MedicineColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            MedicineColumnInfo src = (MedicineColumnInfo) rawSrc;
            MedicineColumnInfo dst = (MedicineColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.nameIndex = src.nameIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.createdOnIndex = src.createdOnIndex;
            dst.updatedOnIndex = src.updatedOnIndex;
            dst.applicationIdIndex = src.applicationIdIndex;
            dst.archivedIndex = src.archivedIndex;
            dst.externalIdIndex = src.externalIdIndex;
            dst.activeIndex = src.activeIndex;
            dst.remindersIndex = src.remindersIndex;
            dst.medicineIdIndex = src.medicineIdIndex;
            dst.descriptionIndex = src.descriptionIndex;
            dst.userIdIndex = src.userIdIndex;
            dst.notesIndex = src.notesIndex;
            dst.addedOnIndex = src.addedOnIndex;
            dst.toBeTakenTillIndex = src.toBeTakenTillIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("name");
        fieldNames.add("tenantId");
        fieldNames.add("createdOn");
        fieldNames.add("updatedOn");
        fieldNames.add("applicationId");
        fieldNames.add("archived");
        fieldNames.add(CooeySQLHelper.COL_EXT_ID);
        fieldNames.add(AppStateModule.APP_STATE_ACTIVE);
        fieldNames.add(CooeySQLHelper.COL_REMINDER);
        fieldNames.add(CooeySQLHelper.COL_MEDID);
        fieldNames.add("description");
        fieldNames.add("userId");
        fieldNames.add("notes");
        fieldNames.add("addedOn");
        fieldNames.add("toBeTakenTill");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    MedicineRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (MedicineColumnInfo) context.getColumnInfo();
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

    public String realmGet$createdOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.createdOnIndex);
    }

    public void realmSet$createdOn(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.createdOnIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.createdOnIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.createdOnIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.createdOnIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$updatedOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.updatedOnIndex);
    }

    public void realmSet$updatedOn(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.updatedOnIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.updatedOnIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.updatedOnIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.updatedOnIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$applicationId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.applicationIdIndex);
    }

    public void realmSet$applicationId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.applicationIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.applicationIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.applicationIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.applicationIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public boolean realmGet$archived() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.archivedIndex);
    }

    public void realmSet$archived(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.archivedIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.archivedIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$externalId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.externalIdIndex);
    }

    public void realmSet$externalId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.externalIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.externalIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.externalIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.externalIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$active() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.activeIndex);
    }

    public void realmSet$active(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.activeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.activeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.activeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.activeIndex, row.getIndex(), value, true);
            }
        }
    }

    public RealmList<Reminder> realmGet$reminders() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.remindersRealmList != null) {
            return this.remindersRealmList;
        }
        this.remindersRealmList = new RealmList(Reminder.class, this.proxyState.getRow$realm().getLinkList(this.columnInfo.remindersIndex), this.proxyState.getRealm$realm());
        return this.remindersRealmList;
    }

    public void realmSet$reminders(RealmList<Reminder> value) {
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains(CooeySQLHelper.COL_REMINDER)) {
                return;
            }
            if (!(value == null || value.isManaged())) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<Reminder> original = value;
                value = new RealmList();
                Iterator it = original.iterator();
                while (it.hasNext()) {
                    Reminder item = (Reminder) it.next();
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList osList = this.proxyState.getRow$realm().getLinkList(this.columnInfo.remindersIndex);
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

    public String realmGet$medicineId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.medicineIdIndex);
    }

    public void realmSet$medicineId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.medicineIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.medicineIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.medicineIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.medicineIdIndex, row.getIndex(), value, true);
            }
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

    public String realmGet$notes() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.notesIndex);
    }

    public void realmSet$notes(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.notesIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.notesIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.notesIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.notesIndex, row.getIndex(), value, true);
            }
        }
    }

    public long realmGet$addedOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.addedOnIndex);
    }

    public void realmSet$addedOn(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.addedOnIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.addedOnIndex, row.getIndex(), value, true);
        }
    }

    public long realmGet$toBeTakenTill() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.toBeTakenTillIndex);
    }

    public void realmSet$toBeTakenTill(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.toBeTakenTillIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.toBeTakenTillIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder(CTConstants.FAB_MEDICINES);
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("createdOn", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("updatedOn", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("applicationId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("archived", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty(CooeySQLHelper.COL_EXT_ID, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(AppStateModule.APP_STATE_ACTIVE, RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty(CooeySQLHelper.COL_REMINDER, RealmFieldType.LIST, "Reminder");
        builder.addPersistedProperty(CooeySQLHelper.COL_MEDID, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("userId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("notes", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("addedOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("toBeTakenTill", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MedicineColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new MedicineColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Medicine";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Medicine createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        Medicine medicine = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Medicine.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Medicine.class), false, Collections.emptyList());
                    medicine = new MedicineRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (medicine == null) {
            if (json.has(CooeySQLHelper.COL_REMINDER)) {
                excludeFields.add(CooeySQLHelper.COL_REMINDER);
            }
            if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                    medicine = (MedicineRealmProxy) realm.createObjectInternal(Medicine.class, null, true, excludeFields);
                } else {
                    String string = json.getString(ShareConstants.WEB_DIALOG_PARAM_ID);
                    MedicineRealmProxy obj = (MedicineRealmProxy) realm.createObjectInternal(Medicine.class, string, true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        MedicineRealmProxyInterface objProxy = medicine;
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name(json.getString("name"));
            }
        }
        if (json.has("tenantId")) {
            if (json.isNull("tenantId")) {
                objProxy.realmSet$tenantId(null);
            } else {
                objProxy.realmSet$tenantId(json.getString("tenantId"));
            }
        }
        if (json.has("createdOn")) {
            if (json.isNull("createdOn")) {
                objProxy.realmSet$createdOn(null);
            } else {
                objProxy.realmSet$createdOn(json.getString("createdOn"));
            }
        }
        if (json.has("updatedOn")) {
            if (json.isNull("updatedOn")) {
                objProxy.realmSet$updatedOn(null);
            } else {
                objProxy.realmSet$updatedOn(json.getString("updatedOn"));
            }
        }
        if (json.has("applicationId")) {
            if (json.isNull("applicationId")) {
                objProxy.realmSet$applicationId(null);
            } else {
                objProxy.realmSet$applicationId(json.getString("applicationId"));
            }
        }
        if (json.has("archived")) {
            if (json.isNull("archived")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'archived' to null.");
            }
            objProxy.realmSet$archived(json.getBoolean("archived"));
        }
        if (json.has(CooeySQLHelper.COL_EXT_ID)) {
            if (json.isNull(CooeySQLHelper.COL_EXT_ID)) {
                objProxy.realmSet$externalId(null);
            } else {
                objProxy.realmSet$externalId(json.getString(CooeySQLHelper.COL_EXT_ID));
            }
        }
        if (json.has(AppStateModule.APP_STATE_ACTIVE)) {
            if (json.isNull(AppStateModule.APP_STATE_ACTIVE)) {
                objProxy.realmSet$active(null);
            } else {
                objProxy.realmSet$active(json.getString(AppStateModule.APP_STATE_ACTIVE));
            }
        }
        if (json.has(CooeySQLHelper.COL_REMINDER)) {
            if (json.isNull(CooeySQLHelper.COL_REMINDER)) {
                objProxy.realmSet$reminders(null);
            } else {
                objProxy.realmGet$reminders().clear();
                JSONArray array = json.getJSONArray(CooeySQLHelper.COL_REMINDER);
                for (int i = 0; i < array.length(); i++) {
                    objProxy.realmGet$reminders().add(ReminderRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update));
                }
            }
        }
        if (json.has(CooeySQLHelper.COL_MEDID)) {
            if (json.isNull(CooeySQLHelper.COL_MEDID)) {
                objProxy.realmSet$medicineId(null);
            } else {
                objProxy.realmSet$medicineId(json.getString(CooeySQLHelper.COL_MEDID));
            }
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                objProxy.realmSet$description(null);
            } else {
                objProxy.realmSet$description(json.getString("description"));
            }
        }
        if (json.has("userId")) {
            if (json.isNull("userId")) {
                objProxy.realmSet$userId(null);
            } else {
                objProxy.realmSet$userId(json.getString("userId"));
            }
        }
        if (json.has("notes")) {
            if (json.isNull("notes")) {
                objProxy.realmSet$notes(null);
            } else {
                objProxy.realmSet$notes(json.getString("notes"));
            }
        }
        if (json.has("addedOn")) {
            if (json.isNull("addedOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'addedOn' to null.");
            }
            objProxy.realmSet$addedOn(json.getLong("addedOn"));
        }
        if (json.has("toBeTakenTill")) {
            if (json.isNull("toBeTakenTill")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'toBeTakenTill' to null.");
            }
            objProxy.realmSet$toBeTakenTill(json.getLong("toBeTakenTill"));
        }
        return medicine;
    }

    @TargetApi(11)
    public static Medicine createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Medicine obj = new Medicine();
        MedicineRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("tenantId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tenantId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tenantId(null);
                }
            } else if (name.equals("createdOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$createdOn(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$createdOn(null);
                }
            } else if (name.equals("updatedOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$updatedOn(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$updatedOn(null);
                }
            } else if (name.equals("applicationId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$applicationId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$applicationId(null);
                }
            } else if (name.equals("archived")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$archived(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'archived' to null.");
                }
            } else if (name.equals(CooeySQLHelper.COL_EXT_ID)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$externalId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$externalId(null);
                }
            } else if (name.equals(AppStateModule.APP_STATE_ACTIVE)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$active(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$active(null);
                }
            } else if (name.equals(CooeySQLHelper.COL_REMINDER)) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$reminders(null);
                } else {
                    objProxy.realmSet$reminders(new RealmList());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        objProxy.realmGet$reminders().add(ReminderRealmProxy.createUsingJsonStream(realm, reader));
                    }
                    reader.endArray();
                }
            } else if (name.equals(CooeySQLHelper.COL_MEDID)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$medicineId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$medicineId(null);
                }
            } else if (name.equals("description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$description(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$description(null);
                }
            } else if (name.equals("userId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$userId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$userId(null);
                }
            } else if (name.equals("notes")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$notes(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$notes(null);
                }
            } else if (name.equals("addedOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$addedOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'addedOn' to null.");
                }
            } else if (!name.equals("toBeTakenTill")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$toBeTakenTill(reader.nextLong());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'toBeTakenTill' to null.");
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Medicine) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Medicine copyOrUpdate(Realm realm, Medicine object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Medicine) cachedRealmObject;
        }
        Medicine update2;
        Medicine realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Medicine.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Medicine.class), false, Collections.emptyList());
                    Medicine realmObject2 = new MedicineRealmProxy();
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

    public static Medicine copy(Realm realm, Medicine newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Medicine) cachedRealmObject;
        }
        Medicine realmObject = (Medicine) realm.createObjectInternal(Medicine.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        MedicineRealmProxyInterface realmObjectSource = newObject;
        MedicineRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectCopy.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectCopy.realmSet$updatedOn(realmObjectSource.realmGet$updatedOn());
        realmObjectCopy.realmSet$applicationId(realmObjectSource.realmGet$applicationId());
        realmObjectCopy.realmSet$archived(realmObjectSource.realmGet$archived());
        realmObjectCopy.realmSet$externalId(realmObjectSource.realmGet$externalId());
        realmObjectCopy.realmSet$active(realmObjectSource.realmGet$active());
        RealmList<Reminder> remindersList = realmObjectSource.realmGet$reminders();
        if (remindersList != null) {
            RealmList<Reminder> remindersRealmList = realmObjectCopy.realmGet$reminders();
            remindersRealmList.clear();
            for (int i = 0; i < remindersList.size(); i++) {
                Reminder remindersItem = (Reminder) remindersList.get(i);
                Reminder cachereminders = (Reminder) cache.get(remindersItem);
                if (cachereminders != null) {
                    remindersRealmList.add(cachereminders);
                } else {
                    remindersRealmList.add(ReminderRealmProxy.copyOrUpdate(realm, remindersItem, update, cache));
                }
            }
        }
        realmObjectCopy.realmSet$medicineId(realmObjectSource.realmGet$medicineId());
        realmObjectCopy.realmSet$description(realmObjectSource.realmGet$description());
        realmObjectCopy.realmSet$userId(realmObjectSource.realmGet$userId());
        realmObjectCopy.realmSet$notes(realmObjectSource.realmGet$notes());
        realmObjectCopy.realmSet$addedOn(realmObjectSource.realmGet$addedOn());
        realmObjectCopy.realmSet$toBeTakenTill(realmObjectSource.realmGet$toBeTakenTill());
        return realmObject;
    }

    public static long insert(Realm realm, Medicine object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Medicine.class);
        long tableNativePtr = table.getNativePtr();
        MedicineColumnInfo columnInfo = (MedicineColumnInfo) realm.getSchema().getColumnInfo(Medicine.class);
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
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        }
        String realmGet$createdOn = object.realmGet$createdOn();
        if (realmGet$createdOn != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.createdOnIndex, rowIndex, realmGet$createdOn, false);
        }
        String realmGet$updatedOn = object.realmGet$updatedOn();
        if (realmGet$updatedOn != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, realmGet$updatedOn, false);
        }
        String realmGet$applicationId = object.realmGet$applicationId();
        if (realmGet$applicationId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, realmGet$applicationId, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.archivedIndex, rowIndex, object.realmGet$archived(), false);
        String realmGet$externalId = object.realmGet$externalId();
        if (realmGet$externalId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.externalIdIndex, rowIndex, realmGet$externalId, false);
        }
        String realmGet$active = object.realmGet$active();
        if (realmGet$active != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.activeIndex, rowIndex, realmGet$active, false);
        }
        RealmList<Reminder> remindersList = object.realmGet$reminders();
        if (remindersList != null) {
            OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.remindersIndex);
            Iterator it = remindersList.iterator();
            while (it.hasNext()) {
                Reminder remindersItem = (Reminder) it.next();
                Long cacheItemIndexreminders = (Long) cache.get(remindersItem);
                if (cacheItemIndexreminders == null) {
                    cacheItemIndexreminders = Long.valueOf(ReminderRealmProxy.insert(realm, remindersItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexreminders.longValue());
            }
        }
        String realmGet$medicineId = object.realmGet$medicineId();
        if (realmGet$medicineId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.medicineIdIndex, rowIndex, realmGet$medicineId, false);
        }
        String realmGet$description = object.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        }
        String realmGet$userId = object.realmGet$userId();
        if (realmGet$userId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
        }
        String realmGet$notes = object.realmGet$notes();
        if (realmGet$notes != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.notesIndex, rowIndex, realmGet$notes, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.addedOnIndex, rowIndex, object.realmGet$addedOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.toBeTakenTillIndex, rowIndex, object.realmGet$toBeTakenTill(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Medicine.class);
        long tableNativePtr = table.getNativePtr();
        MedicineColumnInfo columnInfo = (MedicineColumnInfo) realm.getSchema().getColumnInfo(Medicine.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Medicine object = (Medicine) objects.next();
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
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                    String realmGet$createdOn = object.realmGet$createdOn();
                    if (realmGet$createdOn != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.createdOnIndex, rowIndex, realmGet$createdOn, false);
                    }
                    String realmGet$updatedOn = object.realmGet$updatedOn();
                    if (realmGet$updatedOn != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, realmGet$updatedOn, false);
                    }
                    String realmGet$applicationId = object.realmGet$applicationId();
                    if (realmGet$applicationId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, realmGet$applicationId, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.archivedIndex, rowIndex, object.realmGet$archived(), false);
                    String realmGet$externalId = object.realmGet$externalId();
                    if (realmGet$externalId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.externalIdIndex, rowIndex, realmGet$externalId, false);
                    }
                    String realmGet$active = object.realmGet$active();
                    if (realmGet$active != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.activeIndex, rowIndex, realmGet$active, false);
                    }
                    RealmList<Reminder> remindersList = object.realmGet$reminders();
                    if (remindersList != null) {
                        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.remindersIndex);
                        Iterator it = remindersList.iterator();
                        while (it.hasNext()) {
                            Reminder remindersItem = (Reminder) it.next();
                            Long cacheItemIndexreminders = (Long) cache.get(remindersItem);
                            if (cacheItemIndexreminders == null) {
                                cacheItemIndexreminders = Long.valueOf(ReminderRealmProxy.insert(realm, remindersItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexreminders.longValue());
                        }
                    }
                    String realmGet$medicineId = object.realmGet$medicineId();
                    if (realmGet$medicineId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.medicineIdIndex, rowIndex, realmGet$medicineId, false);
                    }
                    String realmGet$description = object.realmGet$description();
                    if (realmGet$description != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                    }
                    String realmGet$userId = object.realmGet$userId();
                    if (realmGet$userId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
                    }
                    String realmGet$notes = object.realmGet$notes();
                    if (realmGet$notes != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.notesIndex, rowIndex, realmGet$notes, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.addedOnIndex, rowIndex, object.realmGet$addedOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.toBeTakenTillIndex, rowIndex, object.realmGet$toBeTakenTill(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Medicine object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Medicine.class);
        long tableNativePtr = table.getNativePtr();
        MedicineColumnInfo columnInfo = (MedicineColumnInfo) realm.getSchema().getColumnInfo(Medicine.class);
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
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
        }
        String realmGet$createdOn = object.realmGet$createdOn();
        if (realmGet$createdOn != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.createdOnIndex, rowIndex, realmGet$createdOn, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.createdOnIndex, rowIndex, false);
        }
        String realmGet$updatedOn = object.realmGet$updatedOn();
        if (realmGet$updatedOn != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, realmGet$updatedOn, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, false);
        }
        String realmGet$applicationId = object.realmGet$applicationId();
        if (realmGet$applicationId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, realmGet$applicationId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.archivedIndex, rowIndex, object.realmGet$archived(), false);
        String realmGet$externalId = object.realmGet$externalId();
        if (realmGet$externalId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.externalIdIndex, rowIndex, realmGet$externalId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.externalIdIndex, rowIndex, false);
        }
        String realmGet$active = object.realmGet$active();
        if (realmGet$active != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.activeIndex, rowIndex, realmGet$active, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.activeIndex, rowIndex, false);
        }
        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.remindersIndex);
        osList.removeAll();
        RealmList<Reminder> remindersList = object.realmGet$reminders();
        if (remindersList != null) {
            Iterator it = remindersList.iterator();
            while (it.hasNext()) {
                Reminder remindersItem = (Reminder) it.next();
                Long cacheItemIndexreminders = (Long) cache.get(remindersItem);
                if (cacheItemIndexreminders == null) {
                    cacheItemIndexreminders = Long.valueOf(ReminderRealmProxy.insertOrUpdate(realm, remindersItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexreminders.longValue());
            }
        }
        String realmGet$medicineId = object.realmGet$medicineId();
        if (realmGet$medicineId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.medicineIdIndex, rowIndex, realmGet$medicineId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.medicineIdIndex, rowIndex, false);
        }
        String realmGet$description = object.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
        }
        String realmGet$userId = object.realmGet$userId();
        if (realmGet$userId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.userIdIndex, rowIndex, false);
        }
        String realmGet$notes = object.realmGet$notes();
        if (realmGet$notes != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.notesIndex, rowIndex, realmGet$notes, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.notesIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.addedOnIndex, rowIndex, object.realmGet$addedOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.toBeTakenTillIndex, rowIndex, object.realmGet$toBeTakenTill(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Medicine.class);
        long tableNativePtr = table.getNativePtr();
        MedicineColumnInfo columnInfo = (MedicineColumnInfo) realm.getSchema().getColumnInfo(Medicine.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Medicine object = (Medicine) objects.next();
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
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
                    }
                    String realmGet$createdOn = object.realmGet$createdOn();
                    if (realmGet$createdOn != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.createdOnIndex, rowIndex, realmGet$createdOn, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.createdOnIndex, rowIndex, false);
                    }
                    String realmGet$updatedOn = object.realmGet$updatedOn();
                    if (realmGet$updatedOn != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, realmGet$updatedOn, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, false);
                    }
                    String realmGet$applicationId = object.realmGet$applicationId();
                    if (realmGet$applicationId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, realmGet$applicationId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.applicationIdIndex, rowIndex, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.archivedIndex, rowIndex, object.realmGet$archived(), false);
                    String realmGet$externalId = object.realmGet$externalId();
                    if (realmGet$externalId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.externalIdIndex, rowIndex, realmGet$externalId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.externalIdIndex, rowIndex, false);
                    }
                    String realmGet$active = object.realmGet$active();
                    if (realmGet$active != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.activeIndex, rowIndex, realmGet$active, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.activeIndex, rowIndex, false);
                    }
                    OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.remindersIndex);
                    osList.removeAll();
                    RealmList<Reminder> remindersList = object.realmGet$reminders();
                    if (remindersList != null) {
                        Iterator it = remindersList.iterator();
                        while (it.hasNext()) {
                            Reminder remindersItem = (Reminder) it.next();
                            Long cacheItemIndexreminders = (Long) cache.get(remindersItem);
                            if (cacheItemIndexreminders == null) {
                                cacheItemIndexreminders = Long.valueOf(ReminderRealmProxy.insertOrUpdate(realm, remindersItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexreminders.longValue());
                        }
                    }
                    String realmGet$medicineId = object.realmGet$medicineId();
                    if (realmGet$medicineId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.medicineIdIndex, rowIndex, realmGet$medicineId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.medicineIdIndex, rowIndex, false);
                    }
                    String realmGet$description = object.realmGet$description();
                    if (realmGet$description != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
                    }
                    String realmGet$userId = object.realmGet$userId();
                    if (realmGet$userId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.userIdIndex, rowIndex, false);
                    }
                    String realmGet$notes = object.realmGet$notes();
                    if (realmGet$notes != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.notesIndex, rowIndex, realmGet$notes, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.notesIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.addedOnIndex, rowIndex, object.realmGet$addedOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.toBeTakenTillIndex, rowIndex, object.realmGet$toBeTakenTill(), false);
                }
            }
        }
    }

    public static Medicine createDetachedCopy(Medicine realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Medicine unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Medicine();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Medicine) cachedObject.object;
        } else {
            unmanagedObject = (Medicine) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        MedicineRealmProxyInterface unmanagedCopy = unmanagedObject;
        MedicineRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$createdOn(realmSource.realmGet$createdOn());
        unmanagedCopy.realmSet$updatedOn(realmSource.realmGet$updatedOn());
        unmanagedCopy.realmSet$applicationId(realmSource.realmGet$applicationId());
        unmanagedCopy.realmSet$archived(realmSource.realmGet$archived());
        unmanagedCopy.realmSet$externalId(realmSource.realmGet$externalId());
        unmanagedCopy.realmSet$active(realmSource.realmGet$active());
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$reminders(null);
        } else {
            RealmList<Reminder> managedremindersList = realmSource.realmGet$reminders();
            RealmList<Reminder> unmanagedremindersList = new RealmList();
            unmanagedCopy.realmSet$reminders(unmanagedremindersList);
            int nextDepth = currentDepth + 1;
            int size = managedremindersList.size();
            for (int i = 0; i < size; i++) {
                unmanagedremindersList.add(ReminderRealmProxy.createDetachedCopy((Reminder) managedremindersList.get(i), nextDepth, maxDepth, cache));
            }
        }
        unmanagedCopy.realmSet$medicineId(realmSource.realmGet$medicineId());
        unmanagedCopy.realmSet$description(realmSource.realmGet$description());
        unmanagedCopy.realmSet$userId(realmSource.realmGet$userId());
        unmanagedCopy.realmSet$notes(realmSource.realmGet$notes());
        unmanagedCopy.realmSet$addedOn(realmSource.realmGet$addedOn());
        unmanagedCopy.realmSet$toBeTakenTill(realmSource.realmGet$toBeTakenTill());
        return unmanagedObject;
    }

    static Medicine update(Realm realm, Medicine realmObject, Medicine newObject, Map<RealmModel, RealmObjectProxy> cache) {
        MedicineRealmProxyInterface realmObjectTarget = realmObject;
        MedicineRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectTarget.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectTarget.realmSet$updatedOn(realmObjectSource.realmGet$updatedOn());
        realmObjectTarget.realmSet$applicationId(realmObjectSource.realmGet$applicationId());
        realmObjectTarget.realmSet$archived(realmObjectSource.realmGet$archived());
        realmObjectTarget.realmSet$externalId(realmObjectSource.realmGet$externalId());
        realmObjectTarget.realmSet$active(realmObjectSource.realmGet$active());
        RealmList<Reminder> remindersList = realmObjectSource.realmGet$reminders();
        RealmList<Reminder> remindersRealmList = realmObjectTarget.realmGet$reminders();
        remindersRealmList.clear();
        if (remindersList != null) {
            for (int i = 0; i < remindersList.size(); i++) {
                Reminder remindersItem = (Reminder) remindersList.get(i);
                Reminder cachereminders = (Reminder) cache.get(remindersItem);
                if (cachereminders != null) {
                    remindersRealmList.add(cachereminders);
                } else {
                    remindersRealmList.add(ReminderRealmProxy.copyOrUpdate(realm, remindersItem, true, cache));
                }
            }
        }
        realmObjectTarget.realmSet$medicineId(realmObjectSource.realmGet$medicineId());
        realmObjectTarget.realmSet$description(realmObjectSource.realmGet$description());
        realmObjectTarget.realmSet$userId(realmObjectSource.realmGet$userId());
        realmObjectTarget.realmSet$notes(realmObjectSource.realmGet$notes());
        realmObjectTarget.realmSet$addedOn(realmObjectSource.realmGet$addedOn());
        realmObjectTarget.realmSet$toBeTakenTill(realmObjectSource.realmGet$toBeTakenTill());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Medicine = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tenantId:");
        stringBuilder.append(realmGet$tenantId() != null ? realmGet$tenantId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{createdOn:");
        stringBuilder.append(realmGet$createdOn() != null ? realmGet$createdOn() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{updatedOn:");
        stringBuilder.append(realmGet$updatedOn() != null ? realmGet$updatedOn() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{applicationId:");
        stringBuilder.append(realmGet$applicationId() != null ? realmGet$applicationId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{archived:");
        stringBuilder.append(realmGet$archived());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{externalId:");
        stringBuilder.append(realmGet$externalId() != null ? realmGet$externalId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{active:");
        stringBuilder.append(realmGet$active() != null ? realmGet$active() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{reminders:");
        stringBuilder.append("RealmList<Reminder>[").append(realmGet$reminders().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{medicineId:");
        stringBuilder.append(realmGet$medicineId() != null ? realmGet$medicineId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{description:");
        stringBuilder.append(realmGet$description() != null ? realmGet$description() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userId:");
        stringBuilder.append(realmGet$userId() != null ? realmGet$userId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{notes:");
        stringBuilder.append(realmGet$notes() != null ? realmGet$notes() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{addedOn:");
        stringBuilder.append(realmGet$addedOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{toBeTakenTill:");
        stringBuilder.append(realmGet$toBeTakenTill());
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
        MedicineRealmProxy aMedicine = (MedicineRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aMedicine.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aMedicine.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aMedicine.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
