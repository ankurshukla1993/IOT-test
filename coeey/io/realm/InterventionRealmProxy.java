package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.vo.Schedule;
import com.cooey.common.vo.careplan.Intervention;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.facebook.internal.NativeProtocol;
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

public class InterventionRealmProxy extends Intervention implements RealmObjectProxy, InterventionRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private InterventionColumnInfo columnInfo;
    private ProxyState<Intervention> proxyState;

    static final class InterventionColumnInfo extends ColumnInfo {
        long assignedOnIndex;
        long caretakerNotificationEnabledIndex;
        long createdOnIndex;
        long guaridanNotificationEnabledIndex;
        long idIndex;
        long nameIndex;
        long ownerIdIndex;
        long parametersIndex;
        long parentIdIndex;
        long patientIdIndex;
        long patientNotificationEnabledIndex;
        long permissionsIndex;
        long scheduleIndex;
        long tenantIdIndex;
        long typeIndex;
        long updatedOnIndex;

        InterventionColumnInfo(OsSchemaInfo schemaInfo) {
            super(16);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo(CTConstants.INTERVENTION);
            this.guaridanNotificationEnabledIndex = addColumnDetails("guaridanNotificationEnabled", objectSchemaInfo);
            this.caretakerNotificationEnabledIndex = addColumnDetails("caretakerNotificationEnabled", objectSchemaInfo);
            this.patientIdIndex = addColumnDetails("patientId", objectSchemaInfo);
            this.assignedOnIndex = addColumnDetails("assignedOn", objectSchemaInfo);
            this.updatedOnIndex = addColumnDetails("updatedOn", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.ownerIdIndex = addColumnDetails("ownerId", objectSchemaInfo);
            this.createdOnIndex = addColumnDetails("createdOn", objectSchemaInfo);
            this.parentIdIndex = addColumnDetails(CooeySQLHelper.COLUMN_PARENT, objectSchemaInfo);
            this.scheduleIndex = addColumnDetails("schedule", objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.patientNotificationEnabledIndex = addColumnDetails("patientNotificationEnabled", objectSchemaInfo);
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.parametersIndex = addColumnDetails("parameters", objectSchemaInfo);
            this.permissionsIndex = addColumnDetails(NativeProtocol.RESULT_ARGS_PERMISSIONS, objectSchemaInfo);
        }

        InterventionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new InterventionColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            InterventionColumnInfo src = (InterventionColumnInfo) rawSrc;
            InterventionColumnInfo dst = (InterventionColumnInfo) rawDst;
            dst.guaridanNotificationEnabledIndex = src.guaridanNotificationEnabledIndex;
            dst.caretakerNotificationEnabledIndex = src.caretakerNotificationEnabledIndex;
            dst.patientIdIndex = src.patientIdIndex;
            dst.assignedOnIndex = src.assignedOnIndex;
            dst.updatedOnIndex = src.updatedOnIndex;
            dst.typeIndex = src.typeIndex;
            dst.ownerIdIndex = src.ownerIdIndex;
            dst.createdOnIndex = src.createdOnIndex;
            dst.parentIdIndex = src.parentIdIndex;
            dst.scheduleIndex = src.scheduleIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.nameIndex = src.nameIndex;
            dst.patientNotificationEnabledIndex = src.patientNotificationEnabledIndex;
            dst.idIndex = src.idIndex;
            dst.parametersIndex = src.parametersIndex;
            dst.permissionsIndex = src.permissionsIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("guaridanNotificationEnabled");
        fieldNames.add("caretakerNotificationEnabled");
        fieldNames.add("patientId");
        fieldNames.add("assignedOn");
        fieldNames.add("updatedOn");
        fieldNames.add("type");
        fieldNames.add("ownerId");
        fieldNames.add("createdOn");
        fieldNames.add(CooeySQLHelper.COLUMN_PARENT);
        fieldNames.add("schedule");
        fieldNames.add("tenantId");
        fieldNames.add("name");
        fieldNames.add("patientNotificationEnabled");
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("parameters");
        fieldNames.add(NativeProtocol.RESULT_ARGS_PERMISSIONS);
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    InterventionRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (InterventionColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public boolean realmGet$guaridanNotificationEnabled() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.guaridanNotificationEnabledIndex);
    }

    public void realmSet$guaridanNotificationEnabled(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.guaridanNotificationEnabledIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.guaridanNotificationEnabledIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$caretakerNotificationEnabled() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.caretakerNotificationEnabledIndex);
    }

    public void realmSet$caretakerNotificationEnabled(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.caretakerNotificationEnabledIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.caretakerNotificationEnabledIndex, row.getIndex(), value, true);
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

    public long realmGet$assignedOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.assignedOnIndex);
    }

    public void realmSet$assignedOn(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.assignedOnIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.assignedOnIndex, row.getIndex(), value, true);
        }
    }

    public long realmGet$updatedOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.updatedOnIndex);
    }

    public void realmSet$updatedOn(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.updatedOnIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.updatedOnIndex, row.getIndex(), value, true);
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

    public String realmGet$ownerId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.ownerIdIndex);
    }

    public void realmSet$ownerId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.ownerIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.ownerIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.ownerIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.ownerIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public long realmGet$createdOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.createdOnIndex);
    }

    public void realmSet$createdOn(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.createdOnIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.createdOnIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$parentId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.parentIdIndex);
    }

    public void realmSet$parentId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.parentIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.parentIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.parentIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.parentIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public Schedule realmGet$schedule() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.scheduleIndex)) {
            return null;
        }
        return (Schedule) this.proxyState.getRealm$realm().get(Schedule.class, this.proxyState.getRow$realm().getLink(this.columnInfo.scheduleIndex), false, Collections.emptyList());
    }

    public void realmSet$schedule(Schedule value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.scheduleIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.scheduleIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("schedule")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Schedule) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.scheduleIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.scheduleIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
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

    public boolean realmGet$patientNotificationEnabled() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.patientNotificationEnabledIndex);
    }

    public void realmSet$patientNotificationEnabled(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.patientNotificationEnabledIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.patientNotificationEnabledIndex, row.getIndex(), value, true);
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

    public String realmGet$permissions() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.permissionsIndex);
    }

    public void realmSet$permissions(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.permissionsIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.permissionsIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.permissionsIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.permissionsIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder(CTConstants.INTERVENTION);
        builder.addPersistedProperty("guaridanNotificationEnabled", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("caretakerNotificationEnabled", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("patientId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("assignedOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("updatedOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("ownerId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("createdOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty(CooeySQLHelper.COLUMN_PARENT, RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("schedule", RealmFieldType.OBJECT, "Schedule");
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("patientNotificationEnabled", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("parameters", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(NativeProtocol.RESULT_ARGS_PERMISSIONS, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static InterventionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new InterventionColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Intervention";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Intervention createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(1);
        Intervention intervention = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Intervention.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Intervention.class), false, Collections.emptyList());
                    intervention = new InterventionRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (intervention == null) {
            if (json.has("schedule")) {
                excludeFields.add("schedule");
            }
            if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                    intervention = (InterventionRealmProxy) realm.createObjectInternal(Intervention.class, null, true, excludeFields);
                } else {
                    String string = json.getString(ShareConstants.WEB_DIALOG_PARAM_ID);
                    InterventionRealmProxy obj = (InterventionRealmProxy) realm.createObjectInternal(Intervention.class, string, true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        InterventionRealmProxyInterface objProxy = intervention;
        if (json.has("guaridanNotificationEnabled")) {
            if (json.isNull("guaridanNotificationEnabled")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'guaridanNotificationEnabled' to null.");
            }
            objProxy.realmSet$guaridanNotificationEnabled(json.getBoolean("guaridanNotificationEnabled"));
        }
        if (json.has("caretakerNotificationEnabled")) {
            if (json.isNull("caretakerNotificationEnabled")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'caretakerNotificationEnabled' to null.");
            }
            objProxy.realmSet$caretakerNotificationEnabled(json.getBoolean("caretakerNotificationEnabled"));
        }
        if (json.has("patientId")) {
            if (json.isNull("patientId")) {
                objProxy.realmSet$patientId(null);
            } else {
                objProxy.realmSet$patientId(json.getString("patientId"));
            }
        }
        if (json.has("assignedOn")) {
            if (json.isNull("assignedOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'assignedOn' to null.");
            }
            objProxy.realmSet$assignedOn(json.getLong("assignedOn"));
        }
        if (json.has("updatedOn")) {
            if (json.isNull("updatedOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'updatedOn' to null.");
            }
            objProxy.realmSet$updatedOn(json.getLong("updatedOn"));
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type(json.getString("type"));
            }
        }
        if (json.has("ownerId")) {
            if (json.isNull("ownerId")) {
                objProxy.realmSet$ownerId(null);
            } else {
                objProxy.realmSet$ownerId(json.getString("ownerId"));
            }
        }
        if (json.has("createdOn")) {
            if (json.isNull("createdOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'createdOn' to null.");
            }
            objProxy.realmSet$createdOn(json.getLong("createdOn"));
        }
        if (json.has(CooeySQLHelper.COLUMN_PARENT)) {
            if (json.isNull(CooeySQLHelper.COLUMN_PARENT)) {
                objProxy.realmSet$parentId(null);
            } else {
                objProxy.realmSet$parentId(json.getString(CooeySQLHelper.COLUMN_PARENT));
            }
        }
        if (json.has("schedule")) {
            if (json.isNull("schedule")) {
                objProxy.realmSet$schedule(null);
            } else {
                objProxy.realmSet$schedule(ScheduleRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("schedule"), update));
            }
        }
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
        if (json.has("patientNotificationEnabled")) {
            if (json.isNull("patientNotificationEnabled")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'patientNotificationEnabled' to null.");
            }
            objProxy.realmSet$patientNotificationEnabled(json.getBoolean("patientNotificationEnabled"));
        }
        if (json.has("parameters")) {
            if (json.isNull("parameters")) {
                objProxy.realmSet$parameters(null);
            } else {
                objProxy.realmSet$parameters(json.getString("parameters"));
            }
        }
        if (json.has(NativeProtocol.RESULT_ARGS_PERMISSIONS)) {
            if (json.isNull(NativeProtocol.RESULT_ARGS_PERMISSIONS)) {
                objProxy.realmSet$permissions(null);
            } else {
                objProxy.realmSet$permissions(json.getString(NativeProtocol.RESULT_ARGS_PERMISSIONS));
            }
        }
        return intervention;
    }

    @TargetApi(11)
    public static Intervention createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Intervention obj = new Intervention();
        InterventionRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("guaridanNotificationEnabled")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$guaridanNotificationEnabled(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'guaridanNotificationEnabled' to null.");
                }
            } else if (name.equals("caretakerNotificationEnabled")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$caretakerNotificationEnabled(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'caretakerNotificationEnabled' to null.");
                }
            } else if (name.equals("patientId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$patientId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$patientId(null);
                }
            } else if (name.equals("assignedOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$assignedOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'assignedOn' to null.");
                }
            } else if (name.equals("updatedOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$updatedOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'updatedOn' to null.");
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("ownerId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$ownerId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$ownerId(null);
                }
            } else if (name.equals("createdOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$createdOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createdOn' to null.");
                }
            } else if (name.equals(CooeySQLHelper.COLUMN_PARENT)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$parentId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$parentId(null);
                }
            } else if (name.equals("schedule")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$schedule(null);
                } else {
                    objProxy.realmSet$schedule(ScheduleRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("tenantId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tenantId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tenantId(null);
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("patientNotificationEnabled")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$patientNotificationEnabled(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'patientNotificationEnabled' to null.");
                }
            } else if (name.equals(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("parameters")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$parameters(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$parameters(null);
                }
            } else if (!name.equals(NativeProtocol.RESULT_ARGS_PERMISSIONS)) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$permissions(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$permissions(null);
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Intervention) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Intervention copyOrUpdate(Realm realm, Intervention object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Intervention) cachedRealmObject;
        }
        Intervention update2;
        Intervention realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Intervention.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Intervention.class), false, Collections.emptyList());
                    Intervention realmObject2 = new InterventionRealmProxy();
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

    public static Intervention copy(Realm realm, Intervention newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Intervention) cachedRealmObject;
        }
        Intervention realmObject = (Intervention) realm.createObjectInternal(Intervention.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        InterventionRealmProxyInterface realmObjectSource = newObject;
        InterventionRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$guaridanNotificationEnabled(realmObjectSource.realmGet$guaridanNotificationEnabled());
        realmObjectCopy.realmSet$caretakerNotificationEnabled(realmObjectSource.realmGet$caretakerNotificationEnabled());
        realmObjectCopy.realmSet$patientId(realmObjectSource.realmGet$patientId());
        realmObjectCopy.realmSet$assignedOn(realmObjectSource.realmGet$assignedOn());
        realmObjectCopy.realmSet$updatedOn(realmObjectSource.realmGet$updatedOn());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$ownerId(realmObjectSource.realmGet$ownerId());
        realmObjectCopy.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectCopy.realmSet$parentId(realmObjectSource.realmGet$parentId());
        Schedule scheduleObj = realmObjectSource.realmGet$schedule();
        if (scheduleObj == null) {
            realmObjectCopy.realmSet$schedule(null);
        } else {
            Schedule cacheschedule = (Schedule) cache.get(scheduleObj);
            if (cacheschedule != null) {
                realmObjectCopy.realmSet$schedule(cacheschedule);
            } else {
                realmObjectCopy.realmSet$schedule(ScheduleRealmProxy.copyOrUpdate(realm, scheduleObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$patientNotificationEnabled(realmObjectSource.realmGet$patientNotificationEnabled());
        realmObjectCopy.realmSet$parameters(realmObjectSource.realmGet$parameters());
        realmObjectCopy.realmSet$permissions(realmObjectSource.realmGet$permissions());
        return realmObject;
    }

    public static long insert(Realm realm, Intervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Intervention.class);
        long tableNativePtr = table.getNativePtr();
        InterventionColumnInfo columnInfo = (InterventionColumnInfo) realm.getSchema().getColumnInfo(Intervention.class);
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
        Table.nativeSetBoolean(tableNativePtr, columnInfo.guaridanNotificationEnabledIndex, rowIndex, object.realmGet$guaridanNotificationEnabled(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.caretakerNotificationEnabledIndex, rowIndex, object.realmGet$caretakerNotificationEnabled(), false);
        String realmGet$patientId = object.realmGet$patientId();
        if (realmGet$patientId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.assignedOnIndex, rowIndex, object.realmGet$assignedOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }
        String realmGet$ownerId = object.realmGet$ownerId();
        if (realmGet$ownerId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, realmGet$ownerId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
        String realmGet$parentId = object.realmGet$parentId();
        if (realmGet$parentId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parentIdIndex, rowIndex, realmGet$parentId, false);
        }
        Schedule scheduleObj = object.realmGet$schedule();
        if (scheduleObj != null) {
            Long cacheschedule = (Long) cache.get(scheduleObj);
            if (cacheschedule == null) {
                cacheschedule = Long.valueOf(ScheduleRealmProxy.insert(realm, scheduleObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.scheduleIndex, rowIndex, cacheschedule.longValue(), false);
        }
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        }
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.patientNotificationEnabledIndex, rowIndex, object.realmGet$patientNotificationEnabled(), false);
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        }
        String realmGet$permissions = object.realmGet$permissions();
        if (realmGet$permissions == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.permissionsIndex, rowIndex, realmGet$permissions, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Intervention.class);
        long tableNativePtr = table.getNativePtr();
        InterventionColumnInfo columnInfo = (InterventionColumnInfo) realm.getSchema().getColumnInfo(Intervention.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Intervention object = (Intervention) objects.next();
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
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.guaridanNotificationEnabledIndex, rowIndex, object.realmGet$guaridanNotificationEnabled(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.caretakerNotificationEnabledIndex, rowIndex, object.realmGet$caretakerNotificationEnabled(), false);
                    String realmGet$patientId = object.realmGet$patientId();
                    if (realmGet$patientId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.assignedOnIndex, rowIndex, object.realmGet$assignedOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    }
                    String realmGet$ownerId = object.realmGet$ownerId();
                    if (realmGet$ownerId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, realmGet$ownerId, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
                    String realmGet$parentId = object.realmGet$parentId();
                    if (realmGet$parentId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parentIdIndex, rowIndex, realmGet$parentId, false);
                    }
                    Schedule scheduleObj = object.realmGet$schedule();
                    if (scheduleObj != null) {
                        Long cacheschedule = (Long) cache.get(scheduleObj);
                        if (cacheschedule == null) {
                            cacheschedule = Long.valueOf(ScheduleRealmProxy.insert(realm, scheduleObj, (Map) cache));
                        }
                        table.setLink(columnInfo.scheduleIndex, rowIndex, cacheschedule.longValue(), false);
                    }
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.patientNotificationEnabledIndex, rowIndex, object.realmGet$patientNotificationEnabled(), false);
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    }
                    String realmGet$permissions = object.realmGet$permissions();
                    if (realmGet$permissions != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.permissionsIndex, rowIndex, realmGet$permissions, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Intervention object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Intervention.class);
        long tableNativePtr = table.getNativePtr();
        InterventionColumnInfo columnInfo = (InterventionColumnInfo) realm.getSchema().getColumnInfo(Intervention.class);
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
        Table.nativeSetBoolean(tableNativePtr, columnInfo.guaridanNotificationEnabledIndex, rowIndex, object.realmGet$guaridanNotificationEnabled(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.caretakerNotificationEnabledIndex, rowIndex, object.realmGet$caretakerNotificationEnabled(), false);
        String realmGet$patientId = object.realmGet$patientId();
        if (realmGet$patientId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.patientIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.assignedOnIndex, rowIndex, object.realmGet$assignedOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        }
        String realmGet$ownerId = object.realmGet$ownerId();
        if (realmGet$ownerId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, realmGet$ownerId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
        String realmGet$parentId = object.realmGet$parentId();
        if (realmGet$parentId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parentIdIndex, rowIndex, realmGet$parentId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.parentIdIndex, rowIndex, false);
        }
        Schedule scheduleObj = object.realmGet$schedule();
        if (scheduleObj != null) {
            Long cacheschedule = (Long) cache.get(scheduleObj);
            if (cacheschedule == null) {
                cacheschedule = Long.valueOf(ScheduleRealmProxy.insertOrUpdate(realm, scheduleObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.scheduleIndex, rowIndex, cacheschedule.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.scheduleIndex, rowIndex);
        }
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
        Table.nativeSetBoolean(tableNativePtr, columnInfo.patientNotificationEnabledIndex, rowIndex, object.realmGet$patientNotificationEnabled(), false);
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
        }
        String realmGet$permissions = object.realmGet$permissions();
        if (realmGet$permissions != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.permissionsIndex, rowIndex, realmGet$permissions, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.permissionsIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Intervention.class);
        long tableNativePtr = table.getNativePtr();
        InterventionColumnInfo columnInfo = (InterventionColumnInfo) realm.getSchema().getColumnInfo(Intervention.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Intervention object = (Intervention) objects.next();
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
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.guaridanNotificationEnabledIndex, rowIndex, object.realmGet$guaridanNotificationEnabled(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.caretakerNotificationEnabledIndex, rowIndex, object.realmGet$caretakerNotificationEnabled(), false);
                    String realmGet$patientId = object.realmGet$patientId();
                    if (realmGet$patientId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.patientIdIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.assignedOnIndex, rowIndex, object.realmGet$assignedOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
                    }
                    String realmGet$ownerId = object.realmGet$ownerId();
                    if (realmGet$ownerId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, realmGet$ownerId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
                    String realmGet$parentId = object.realmGet$parentId();
                    if (realmGet$parentId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parentIdIndex, rowIndex, realmGet$parentId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.parentIdIndex, rowIndex, false);
                    }
                    Schedule scheduleObj = object.realmGet$schedule();
                    if (scheduleObj != null) {
                        Long cacheschedule = (Long) cache.get(scheduleObj);
                        if (cacheschedule == null) {
                            cacheschedule = Long.valueOf(ScheduleRealmProxy.insertOrUpdate(realm, scheduleObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.scheduleIndex, rowIndex, cacheschedule.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.scheduleIndex, rowIndex);
                    }
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
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.patientNotificationEnabledIndex, rowIndex, object.realmGet$patientNotificationEnabled(), false);
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
                    }
                    String realmGet$permissions = object.realmGet$permissions();
                    if (realmGet$permissions != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.permissionsIndex, rowIndex, realmGet$permissions, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.permissionsIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Intervention createDetachedCopy(Intervention realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Intervention unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Intervention();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Intervention) cachedObject.object;
        } else {
            unmanagedObject = (Intervention) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        InterventionRealmProxyInterface unmanagedCopy = unmanagedObject;
        InterventionRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$guaridanNotificationEnabled(realmSource.realmGet$guaridanNotificationEnabled());
        unmanagedCopy.realmSet$caretakerNotificationEnabled(realmSource.realmGet$caretakerNotificationEnabled());
        unmanagedCopy.realmSet$patientId(realmSource.realmGet$patientId());
        unmanagedCopy.realmSet$assignedOn(realmSource.realmGet$assignedOn());
        unmanagedCopy.realmSet$updatedOn(realmSource.realmGet$updatedOn());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$ownerId(realmSource.realmGet$ownerId());
        unmanagedCopy.realmSet$createdOn(realmSource.realmGet$createdOn());
        unmanagedCopy.realmSet$parentId(realmSource.realmGet$parentId());
        unmanagedCopy.realmSet$schedule(ScheduleRealmProxy.createDetachedCopy(realmSource.realmGet$schedule(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$patientNotificationEnabled(realmSource.realmGet$patientNotificationEnabled());
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$parameters(realmSource.realmGet$parameters());
        unmanagedCopy.realmSet$permissions(realmSource.realmGet$permissions());
        return unmanagedObject;
    }

    static Intervention update(Realm realm, Intervention realmObject, Intervention newObject, Map<RealmModel, RealmObjectProxy> cache) {
        InterventionRealmProxyInterface realmObjectTarget = realmObject;
        InterventionRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$guaridanNotificationEnabled(realmObjectSource.realmGet$guaridanNotificationEnabled());
        realmObjectTarget.realmSet$caretakerNotificationEnabled(realmObjectSource.realmGet$caretakerNotificationEnabled());
        realmObjectTarget.realmSet$patientId(realmObjectSource.realmGet$patientId());
        realmObjectTarget.realmSet$assignedOn(realmObjectSource.realmGet$assignedOn());
        realmObjectTarget.realmSet$updatedOn(realmObjectSource.realmGet$updatedOn());
        realmObjectTarget.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectTarget.realmSet$ownerId(realmObjectSource.realmGet$ownerId());
        realmObjectTarget.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectTarget.realmSet$parentId(realmObjectSource.realmGet$parentId());
        Schedule scheduleObj = realmObjectSource.realmGet$schedule();
        if (scheduleObj == null) {
            realmObjectTarget.realmSet$schedule(null);
        } else {
            Schedule cacheschedule = (Schedule) cache.get(scheduleObj);
            if (cacheschedule != null) {
                realmObjectTarget.realmSet$schedule(cacheschedule);
            } else {
                realmObjectTarget.realmSet$schedule(ScheduleRealmProxy.copyOrUpdate(realm, scheduleObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$patientNotificationEnabled(realmObjectSource.realmGet$patientNotificationEnabled());
        realmObjectTarget.realmSet$parameters(realmObjectSource.realmGet$parameters());
        realmObjectTarget.realmSet$permissions(realmObjectSource.realmGet$permissions());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Intervention = proxy[");
        stringBuilder.append("{guaridanNotificationEnabled:");
        stringBuilder.append(realmGet$guaridanNotificationEnabled());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{caretakerNotificationEnabled:");
        stringBuilder.append(realmGet$caretakerNotificationEnabled());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{patientId:");
        stringBuilder.append(realmGet$patientId() != null ? realmGet$patientId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{assignedOn:");
        stringBuilder.append(realmGet$assignedOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{updatedOn:");
        stringBuilder.append(realmGet$updatedOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{ownerId:");
        stringBuilder.append(realmGet$ownerId() != null ? realmGet$ownerId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{createdOn:");
        stringBuilder.append(realmGet$createdOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parentId:");
        stringBuilder.append(realmGet$parentId() != null ? realmGet$parentId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{schedule:");
        stringBuilder.append(realmGet$schedule() != null ? "Schedule" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tenantId:");
        stringBuilder.append(realmGet$tenantId() != null ? realmGet$tenantId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{patientNotificationEnabled:");
        stringBuilder.append(realmGet$patientNotificationEnabled());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parameters:");
        stringBuilder.append(realmGet$parameters() != null ? realmGet$parameters() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{permissions:");
        stringBuilder.append(realmGet$permissions() != null ? realmGet$permissions() : "null");
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
        InterventionRealmProxy aIntervention = (InterventionRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aIntervention.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aIntervention.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aIntervention.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
