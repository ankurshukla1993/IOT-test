package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Action;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.measurement.AppMeasurement$Param;
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

public class ActionRealmProxy extends Action implements RealmObjectProxy, ActionRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ActionColumnInfo columnInfo;
    private ProxyState<Action> proxyState;

    static final class ActionColumnInfo extends ColumnInfo {
        long completedIndex;
        long completedOnIndex;
        long createdOnIndex;
        long idIndex;
        long interventionIdIndex;
        long latitudeIndex;
        long longitudeIndex;
        long notesIndex;
        long parametersIndex;
        long patientIdIndex;
        long patientNameIndex;
        long postActionIndex;
        long resolutionIdIndex;
        long scheduledOnIndex;
        long tenantIdIndex;
        long timestampIndex;
        long titleIndex;
        long typeIndex;
        long updatedOnIndex;

        ActionColumnInfo(OsSchemaInfo schemaInfo) {
            super(19);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Action");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.createdOnIndex = addColumnDetails("createdOn", objectSchemaInfo);
            this.updatedOnIndex = addColumnDetails("updatedOn", objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.titleIndex = addColumnDetails("title", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.parametersIndex = addColumnDetails("parameters", objectSchemaInfo);
            this.patientIdIndex = addColumnDetails("patientId", objectSchemaInfo);
            this.patientNameIndex = addColumnDetails("patientName", objectSchemaInfo);
            this.resolutionIdIndex = addColumnDetails("resolutionId", objectSchemaInfo);
            this.completedOnIndex = addColumnDetails("completedOn", objectSchemaInfo);
            this.scheduledOnIndex = addColumnDetails("scheduledOn", objectSchemaInfo);
            this.notesIndex = addColumnDetails("notes", objectSchemaInfo);
            this.interventionIdIndex = addColumnDetails("interventionId", objectSchemaInfo);
            this.latitudeIndex = addColumnDetails("latitude", objectSchemaInfo);
            this.longitudeIndex = addColumnDetails("longitude", objectSchemaInfo);
            this.completedIndex = addColumnDetails("completed", objectSchemaInfo);
            this.postActionIndex = addColumnDetails("postAction", objectSchemaInfo);
            this.timestampIndex = addColumnDetails(AppMeasurement$Param.TIMESTAMP, objectSchemaInfo);
        }

        ActionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new ActionColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            ActionColumnInfo src = (ActionColumnInfo) rawSrc;
            ActionColumnInfo dst = (ActionColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.createdOnIndex = src.createdOnIndex;
            dst.updatedOnIndex = src.updatedOnIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.titleIndex = src.titleIndex;
            dst.typeIndex = src.typeIndex;
            dst.parametersIndex = src.parametersIndex;
            dst.patientIdIndex = src.patientIdIndex;
            dst.patientNameIndex = src.patientNameIndex;
            dst.resolutionIdIndex = src.resolutionIdIndex;
            dst.completedOnIndex = src.completedOnIndex;
            dst.scheduledOnIndex = src.scheduledOnIndex;
            dst.notesIndex = src.notesIndex;
            dst.interventionIdIndex = src.interventionIdIndex;
            dst.latitudeIndex = src.latitudeIndex;
            dst.longitudeIndex = src.longitudeIndex;
            dst.completedIndex = src.completedIndex;
            dst.postActionIndex = src.postActionIndex;
            dst.timestampIndex = src.timestampIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("createdOn");
        fieldNames.add("updatedOn");
        fieldNames.add("tenantId");
        fieldNames.add("title");
        fieldNames.add("type");
        fieldNames.add("parameters");
        fieldNames.add("patientId");
        fieldNames.add("patientName");
        fieldNames.add("resolutionId");
        fieldNames.add("completedOn");
        fieldNames.add("scheduledOn");
        fieldNames.add("notes");
        fieldNames.add("interventionId");
        fieldNames.add("latitude");
        fieldNames.add("longitude");
        fieldNames.add("completed");
        fieldNames.add("postAction");
        fieldNames.add(AppMeasurement$Param.TIMESTAMP);
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ActionRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ActionColumnInfo) context.getColumnInfo();
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

    public String realmGet$title() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.titleIndex);
    }

    public void realmSet$title(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.titleIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.titleIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.titleIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.titleIndex, row.getIndex(), value, true);
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

    public String realmGet$patientName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.patientNameIndex);
    }

    public void realmSet$patientName(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.patientNameIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.patientNameIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.patientNameIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.patientNameIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$resolutionId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.resolutionIdIndex);
    }

    public void realmSet$resolutionId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.resolutionIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.resolutionIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.resolutionIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.resolutionIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public long realmGet$completedOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.completedOnIndex);
    }

    public void realmSet$completedOn(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.completedOnIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.completedOnIndex, row.getIndex(), value, true);
        }
    }

    public long realmGet$scheduledOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.scheduledOnIndex);
    }

    public void realmSet$scheduledOn(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.scheduledOnIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.scheduledOnIndex, row.getIndex(), value, true);
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

    public String realmGet$interventionId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.interventionIdIndex);
    }

    public void realmSet$interventionId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.interventionIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.interventionIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.interventionIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.interventionIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public long realmGet$latitude() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.latitudeIndex);
    }

    public void realmSet$latitude(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.latitudeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.latitudeIndex, row.getIndex(), value, true);
        }
    }

    public long realmGet$longitude() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.longitudeIndex);
    }

    public void realmSet$longitude(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.longitudeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.longitudeIndex, row.getIndex(), value, true);
        }
    }

    public boolean realmGet$completed() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.completedIndex);
    }

    public void realmSet$completed(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.completedIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.completedIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$postAction() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.postActionIndex);
    }

    public void realmSet$postAction(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.postActionIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.postActionIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.postActionIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.postActionIndex, row.getIndex(), value, true);
            }
        }
    }

    public long realmGet$timestamp() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.timestampIndex);
    }

    public void realmSet$timestamp(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.timestampIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.timestampIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Action");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("createdOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("updatedOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("title", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("parameters", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("patientId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("patientName", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("resolutionId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("completedOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("scheduledOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("notes", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("interventionId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("latitude", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("longitude", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("completed", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("postAction", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(AppMeasurement$Param.TIMESTAMP, RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ActionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ActionColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Action";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Action createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = Collections.emptyList();
        Action action = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Action.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Action.class), false, Collections.emptyList());
                    action = new ActionRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (action == null) {
            if (!json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            } else if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                action = (ActionRealmProxy) realm.createObjectInternal(Action.class, null, true, excludeFields);
            } else {
                ActionRealmProxy obj = (ActionRealmProxy) realm.createObjectInternal(Action.class, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID), true, excludeFields);
            }
        }
        ActionRealmProxyInterface objProxy = action;
        if (json.has("createdOn")) {
            if (json.isNull("createdOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'createdOn' to null.");
            }
            objProxy.realmSet$createdOn(json.getLong("createdOn"));
        }
        if (json.has("updatedOn")) {
            if (json.isNull("updatedOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'updatedOn' to null.");
            }
            objProxy.realmSet$updatedOn(json.getLong("updatedOn"));
        }
        if (json.has("tenantId")) {
            if (json.isNull("tenantId")) {
                objProxy.realmSet$tenantId(null);
            } else {
                objProxy.realmSet$tenantId(json.getString("tenantId"));
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                objProxy.realmSet$title(null);
            } else {
                objProxy.realmSet$title(json.getString("title"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type(json.getString("type"));
            }
        }
        if (json.has("parameters")) {
            if (json.isNull("parameters")) {
                objProxy.realmSet$parameters(null);
            } else {
                objProxy.realmSet$parameters(json.getString("parameters"));
            }
        }
        if (json.has("patientId")) {
            if (json.isNull("patientId")) {
                objProxy.realmSet$patientId(null);
            } else {
                objProxy.realmSet$patientId(json.getString("patientId"));
            }
        }
        if (json.has("patientName")) {
            if (json.isNull("patientName")) {
                objProxy.realmSet$patientName(null);
            } else {
                objProxy.realmSet$patientName(json.getString("patientName"));
            }
        }
        if (json.has("resolutionId")) {
            if (json.isNull("resolutionId")) {
                objProxy.realmSet$resolutionId(null);
            } else {
                objProxy.realmSet$resolutionId(json.getString("resolutionId"));
            }
        }
        if (json.has("completedOn")) {
            if (json.isNull("completedOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'completedOn' to null.");
            }
            objProxy.realmSet$completedOn(json.getLong("completedOn"));
        }
        if (json.has("scheduledOn")) {
            if (json.isNull("scheduledOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'scheduledOn' to null.");
            }
            objProxy.realmSet$scheduledOn(json.getLong("scheduledOn"));
        }
        if (json.has("notes")) {
            if (json.isNull("notes")) {
                objProxy.realmSet$notes(null);
            } else {
                objProxy.realmSet$notes(json.getString("notes"));
            }
        }
        if (json.has("interventionId")) {
            if (json.isNull("interventionId")) {
                objProxy.realmSet$interventionId(null);
            } else {
                objProxy.realmSet$interventionId(json.getString("interventionId"));
            }
        }
        if (json.has("latitude")) {
            if (json.isNull("latitude")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'latitude' to null.");
            }
            objProxy.realmSet$latitude(json.getLong("latitude"));
        }
        if (json.has("longitude")) {
            if (json.isNull("longitude")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'longitude' to null.");
            }
            objProxy.realmSet$longitude(json.getLong("longitude"));
        }
        if (json.has("completed")) {
            if (json.isNull("completed")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'completed' to null.");
            }
            objProxy.realmSet$completed(json.getBoolean("completed"));
        }
        if (json.has("postAction")) {
            if (json.isNull("postAction")) {
                objProxy.realmSet$postAction(null);
            } else {
                objProxy.realmSet$postAction(json.getString("postAction"));
            }
        }
        if (json.has(AppMeasurement$Param.TIMESTAMP)) {
            if (json.isNull(AppMeasurement$Param.TIMESTAMP)) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
            }
            objProxy.realmSet$timestamp(json.getLong(AppMeasurement$Param.TIMESTAMP));
        }
        return action;
    }

    @TargetApi(11)
    public static Action createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Action obj = new Action();
        ActionRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("createdOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$createdOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createdOn' to null.");
                }
            } else if (name.equals("updatedOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$updatedOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'updatedOn' to null.");
                }
            } else if (name.equals("tenantId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tenantId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tenantId(null);
                }
            } else if (name.equals("title")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$title(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$title(null);
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("parameters")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$parameters(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$parameters(null);
                }
            } else if (name.equals("patientId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$patientId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$patientId(null);
                }
            } else if (name.equals("patientName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$patientName(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$patientName(null);
                }
            } else if (name.equals("resolutionId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$resolutionId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$resolutionId(null);
                }
            } else if (name.equals("completedOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$completedOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'completedOn' to null.");
                }
            } else if (name.equals("scheduledOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$scheduledOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'scheduledOn' to null.");
                }
            } else if (name.equals("notes")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$notes(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$notes(null);
                }
            } else if (name.equals("interventionId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$interventionId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$interventionId(null);
                }
            } else if (name.equals("latitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$latitude(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'latitude' to null.");
                }
            } else if (name.equals("longitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$longitude(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'longitude' to null.");
                }
            } else if (name.equals("completed")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$completed(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'completed' to null.");
                }
            } else if (name.equals("postAction")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$postAction(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$postAction(null);
                }
            } else if (!name.equals(AppMeasurement$Param.TIMESTAMP)) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$timestamp(reader.nextLong());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Action) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Action copyOrUpdate(Realm realm, Action object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Action) cachedRealmObject;
        }
        Action update2;
        Action realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Action.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Action.class), false, Collections.emptyList());
                    Action realmObject2 = new ActionRealmProxy();
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

    public static Action copy(Realm realm, Action newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Action) cachedRealmObject;
        }
        Action realmObject = (Action) realm.createObjectInternal(Action.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ActionRealmProxyInterface realmObjectSource = newObject;
        ActionRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectCopy.realmSet$updatedOn(realmObjectSource.realmGet$updatedOn());
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectCopy.realmSet$title(realmObjectSource.realmGet$title());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$parameters(realmObjectSource.realmGet$parameters());
        realmObjectCopy.realmSet$patientId(realmObjectSource.realmGet$patientId());
        realmObjectCopy.realmSet$patientName(realmObjectSource.realmGet$patientName());
        realmObjectCopy.realmSet$resolutionId(realmObjectSource.realmGet$resolutionId());
        realmObjectCopy.realmSet$completedOn(realmObjectSource.realmGet$completedOn());
        realmObjectCopy.realmSet$scheduledOn(realmObjectSource.realmGet$scheduledOn());
        realmObjectCopy.realmSet$notes(realmObjectSource.realmGet$notes());
        realmObjectCopy.realmSet$interventionId(realmObjectSource.realmGet$interventionId());
        realmObjectCopy.realmSet$latitude(realmObjectSource.realmGet$latitude());
        realmObjectCopy.realmSet$longitude(realmObjectSource.realmGet$longitude());
        realmObjectCopy.realmSet$completed(realmObjectSource.realmGet$completed());
        realmObjectCopy.realmSet$postAction(realmObjectSource.realmGet$postAction());
        realmObjectCopy.realmSet$timestamp(realmObjectSource.realmGet$timestamp());
        return realmObject;
    }

    public static long insert(Realm realm, Action object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Action.class);
        long tableNativePtr = table.getNativePtr();
        ActionColumnInfo columnInfo = (ActionColumnInfo) realm.getSchema().getColumnInfo(Action.class);
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
        Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        }
        String realmGet$title = object.realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        }
        String realmGet$patientId = object.realmGet$patientId();
        if (realmGet$patientId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
        }
        String realmGet$patientName = object.realmGet$patientName();
        if (realmGet$patientName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientNameIndex, rowIndex, realmGet$patientName, false);
        }
        String realmGet$resolutionId = object.realmGet$resolutionId();
        if (realmGet$resolutionId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.resolutionIdIndex, rowIndex, realmGet$resolutionId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.completedOnIndex, rowIndex, object.realmGet$completedOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.scheduledOnIndex, rowIndex, object.realmGet$scheduledOn(), false);
        String realmGet$notes = object.realmGet$notes();
        if (realmGet$notes != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.notesIndex, rowIndex, realmGet$notes, false);
        }
        String realmGet$interventionId = object.realmGet$interventionId();
        if (realmGet$interventionId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.interventionIdIndex, rowIndex, realmGet$interventionId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.latitudeIndex, rowIndex, object.realmGet$latitude(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.longitudeIndex, rowIndex, object.realmGet$longitude(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.completedIndex, rowIndex, object.realmGet$completed(), false);
        String realmGet$postAction = object.realmGet$postAction();
        if (realmGet$postAction != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.postActionIndex, rowIndex, realmGet$postAction, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Action.class);
        long tableNativePtr = table.getNativePtr();
        ActionColumnInfo columnInfo = (ActionColumnInfo) realm.getSchema().getColumnInfo(Action.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Action object = (Action) objects.next();
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
                    Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                    String realmGet$title = object.realmGet$title();
                    if (realmGet$title != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    }
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    }
                    String realmGet$patientId = object.realmGet$patientId();
                    if (realmGet$patientId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
                    }
                    String realmGet$patientName = object.realmGet$patientName();
                    if (realmGet$patientName != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientNameIndex, rowIndex, realmGet$patientName, false);
                    }
                    String realmGet$resolutionId = object.realmGet$resolutionId();
                    if (realmGet$resolutionId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.resolutionIdIndex, rowIndex, realmGet$resolutionId, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.completedOnIndex, rowIndex, object.realmGet$completedOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.scheduledOnIndex, rowIndex, object.realmGet$scheduledOn(), false);
                    String realmGet$notes = object.realmGet$notes();
                    if (realmGet$notes != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.notesIndex, rowIndex, realmGet$notes, false);
                    }
                    String realmGet$interventionId = object.realmGet$interventionId();
                    if (realmGet$interventionId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.interventionIdIndex, rowIndex, realmGet$interventionId, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.latitudeIndex, rowIndex, object.realmGet$latitude(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.longitudeIndex, rowIndex, object.realmGet$longitude(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.completedIndex, rowIndex, object.realmGet$completed(), false);
                    String realmGet$postAction = object.realmGet$postAction();
                    if (realmGet$postAction != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.postActionIndex, rowIndex, realmGet$postAction, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Action object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Action.class);
        long tableNativePtr = table.getNativePtr();
        ActionColumnInfo columnInfo = (ActionColumnInfo) realm.getSchema().getColumnInfo(Action.class);
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
        Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
        }
        String realmGet$title = object.realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        }
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
        }
        String realmGet$patientId = object.realmGet$patientId();
        if (realmGet$patientId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.patientIdIndex, rowIndex, false);
        }
        String realmGet$patientName = object.realmGet$patientName();
        if (realmGet$patientName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientNameIndex, rowIndex, realmGet$patientName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.patientNameIndex, rowIndex, false);
        }
        String realmGet$resolutionId = object.realmGet$resolutionId();
        if (realmGet$resolutionId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.resolutionIdIndex, rowIndex, realmGet$resolutionId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.resolutionIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.completedOnIndex, rowIndex, object.realmGet$completedOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.scheduledOnIndex, rowIndex, object.realmGet$scheduledOn(), false);
        String realmGet$notes = object.realmGet$notes();
        if (realmGet$notes != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.notesIndex, rowIndex, realmGet$notes, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.notesIndex, rowIndex, false);
        }
        String realmGet$interventionId = object.realmGet$interventionId();
        if (realmGet$interventionId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.interventionIdIndex, rowIndex, realmGet$interventionId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.interventionIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.latitudeIndex, rowIndex, object.realmGet$latitude(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.longitudeIndex, rowIndex, object.realmGet$longitude(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.completedIndex, rowIndex, object.realmGet$completed(), false);
        String realmGet$postAction = object.realmGet$postAction();
        if (realmGet$postAction != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.postActionIndex, rowIndex, realmGet$postAction, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.postActionIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Action.class);
        long tableNativePtr = table.getNativePtr();
        ActionColumnInfo columnInfo = (ActionColumnInfo) realm.getSchema().getColumnInfo(Action.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Action object = (Action) objects.next();
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
                    Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
                    }
                    String realmGet$title = object.realmGet$title();
                    if (realmGet$title != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
                    }
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
                    }
                    String realmGet$patientId = object.realmGet$patientId();
                    if (realmGet$patientId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.patientIdIndex, rowIndex, false);
                    }
                    String realmGet$patientName = object.realmGet$patientName();
                    if (realmGet$patientName != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientNameIndex, rowIndex, realmGet$patientName, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.patientNameIndex, rowIndex, false);
                    }
                    String realmGet$resolutionId = object.realmGet$resolutionId();
                    if (realmGet$resolutionId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.resolutionIdIndex, rowIndex, realmGet$resolutionId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.resolutionIdIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.completedOnIndex, rowIndex, object.realmGet$completedOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.scheduledOnIndex, rowIndex, object.realmGet$scheduledOn(), false);
                    String realmGet$notes = object.realmGet$notes();
                    if (realmGet$notes != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.notesIndex, rowIndex, realmGet$notes, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.notesIndex, rowIndex, false);
                    }
                    String realmGet$interventionId = object.realmGet$interventionId();
                    if (realmGet$interventionId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.interventionIdIndex, rowIndex, realmGet$interventionId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.interventionIdIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.latitudeIndex, rowIndex, object.realmGet$latitude(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.longitudeIndex, rowIndex, object.realmGet$longitude(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.completedIndex, rowIndex, object.realmGet$completed(), false);
                    String realmGet$postAction = object.realmGet$postAction();
                    if (realmGet$postAction != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.postActionIndex, rowIndex, realmGet$postAction, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.postActionIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
                }
            }
        }
    }

    public static Action createDetachedCopy(Action realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Action unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Action();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Action) cachedObject.object;
        } else {
            unmanagedObject = (Action) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ActionRealmProxyInterface unmanagedCopy = unmanagedObject;
        ActionRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$createdOn(realmSource.realmGet$createdOn());
        unmanagedCopy.realmSet$updatedOn(realmSource.realmGet$updatedOn());
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$title(realmSource.realmGet$title());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$parameters(realmSource.realmGet$parameters());
        unmanagedCopy.realmSet$patientId(realmSource.realmGet$patientId());
        unmanagedCopy.realmSet$patientName(realmSource.realmGet$patientName());
        unmanagedCopy.realmSet$resolutionId(realmSource.realmGet$resolutionId());
        unmanagedCopy.realmSet$completedOn(realmSource.realmGet$completedOn());
        unmanagedCopy.realmSet$scheduledOn(realmSource.realmGet$scheduledOn());
        unmanagedCopy.realmSet$notes(realmSource.realmGet$notes());
        unmanagedCopy.realmSet$interventionId(realmSource.realmGet$interventionId());
        unmanagedCopy.realmSet$latitude(realmSource.realmGet$latitude());
        unmanagedCopy.realmSet$longitude(realmSource.realmGet$longitude());
        unmanagedCopy.realmSet$completed(realmSource.realmGet$completed());
        unmanagedCopy.realmSet$postAction(realmSource.realmGet$postAction());
        unmanagedCopy.realmSet$timestamp(realmSource.realmGet$timestamp());
        return unmanagedObject;
    }

    static Action update(Realm realm, Action realmObject, Action newObject, Map<RealmModel, RealmObjectProxy> map) {
        ActionRealmProxyInterface realmObjectTarget = realmObject;
        ActionRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectTarget.realmSet$updatedOn(realmObjectSource.realmGet$updatedOn());
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectTarget.realmSet$title(realmObjectSource.realmGet$title());
        realmObjectTarget.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectTarget.realmSet$parameters(realmObjectSource.realmGet$parameters());
        realmObjectTarget.realmSet$patientId(realmObjectSource.realmGet$patientId());
        realmObjectTarget.realmSet$patientName(realmObjectSource.realmGet$patientName());
        realmObjectTarget.realmSet$resolutionId(realmObjectSource.realmGet$resolutionId());
        realmObjectTarget.realmSet$completedOn(realmObjectSource.realmGet$completedOn());
        realmObjectTarget.realmSet$scheduledOn(realmObjectSource.realmGet$scheduledOn());
        realmObjectTarget.realmSet$notes(realmObjectSource.realmGet$notes());
        realmObjectTarget.realmSet$interventionId(realmObjectSource.realmGet$interventionId());
        realmObjectTarget.realmSet$latitude(realmObjectSource.realmGet$latitude());
        realmObjectTarget.realmSet$longitude(realmObjectSource.realmGet$longitude());
        realmObjectTarget.realmSet$completed(realmObjectSource.realmGet$completed());
        realmObjectTarget.realmSet$postAction(realmObjectSource.realmGet$postAction());
        realmObjectTarget.realmSet$timestamp(realmObjectSource.realmGet$timestamp());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Action = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{createdOn:");
        stringBuilder.append(realmGet$createdOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{updatedOn:");
        stringBuilder.append(realmGet$updatedOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tenantId:");
        stringBuilder.append(realmGet$tenantId() != null ? realmGet$tenantId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(realmGet$title() != null ? realmGet$title() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parameters:");
        stringBuilder.append(realmGet$parameters() != null ? realmGet$parameters() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{patientId:");
        stringBuilder.append(realmGet$patientId() != null ? realmGet$patientId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{patientName:");
        stringBuilder.append(realmGet$patientName() != null ? realmGet$patientName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{resolutionId:");
        stringBuilder.append(realmGet$resolutionId() != null ? realmGet$resolutionId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{completedOn:");
        stringBuilder.append(realmGet$completedOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{scheduledOn:");
        stringBuilder.append(realmGet$scheduledOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{notes:");
        stringBuilder.append(realmGet$notes() != null ? realmGet$notes() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{interventionId:");
        stringBuilder.append(realmGet$interventionId() != null ? realmGet$interventionId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{latitude:");
        stringBuilder.append(realmGet$latitude());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{longitude:");
        stringBuilder.append(realmGet$longitude());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{completed:");
        stringBuilder.append(realmGet$completed());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{postAction:");
        stringBuilder.append(realmGet$postAction() != null ? realmGet$postAction() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp());
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
        ActionRealmProxy aAction = (ActionRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aAction.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aAction.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aAction.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
