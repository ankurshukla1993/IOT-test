package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.vo.careplan.Assessment;
import com.cooey.common.vo.careplan.Careplan;
import com.cooey.common.vo.careplan.Diagnosis;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.facebook.react.uimanager.ViewProps;
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

public class CareplanRealmProxy extends Careplan implements RealmObjectProxy, CareplanRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private CareplanColumnInfo columnInfo;
    private ProxyState<Careplan> proxyState;

    static final class CareplanColumnInfo extends ColumnInfo {
        long assessmentIndex;
        long assignedOnIndex;
        long assignerIdIndex;
        long beginTimeIndex;
        long createdOnIndex;
        long descriptionIndex;
        long diagnosisIndex;
        long enabledIndex;
        long endTimeIndex;
        long evaluationIndex;
        long goalIndex;
        long historyIndex;
        long idIndex;
        long nameIndex;
        long numOfDaysIndex;
        long parentIdIndex;
        long patientIdIndex;
        long tenantIdIndex;
        long updatedOnIndex;

        CareplanColumnInfo(OsSchemaInfo schemaInfo) {
            super(19);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo(CTConstants.CAREPLAN);
            this.goalIndex = addColumnDetails("goal", objectSchemaInfo);
            this.patientIdIndex = addColumnDetails("patientId", objectSchemaInfo);
            this.descriptionIndex = addColumnDetails("description", objectSchemaInfo);
            this.diagnosisIndex = addColumnDetails("diagnosis", objectSchemaInfo);
            this.assignedOnIndex = addColumnDetails("assignedOn", objectSchemaInfo);
            this.updatedOnIndex = addColumnDetails("updatedOn", objectSchemaInfo);
            this.historyIndex = addColumnDetails("history", objectSchemaInfo);
            this.createdOnIndex = addColumnDetails("createdOn", objectSchemaInfo);
            this.parentIdIndex = addColumnDetails(CooeySQLHelper.COLUMN_PARENT, objectSchemaInfo);
            this.enabledIndex = addColumnDetails(ViewProps.ENABLED, objectSchemaInfo);
            this.evaluationIndex = addColumnDetails("evaluation", objectSchemaInfo);
            this.numOfDaysIndex = addColumnDetails("numOfDays", objectSchemaInfo);
            this.assessmentIndex = addColumnDetails("assessment", objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.beginTimeIndex = addColumnDetails("beginTime", objectSchemaInfo);
            this.endTimeIndex = addColumnDetails("endTime", objectSchemaInfo);
            this.assignerIdIndex = addColumnDetails("assignerId", objectSchemaInfo);
        }

        CareplanColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new CareplanColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            CareplanColumnInfo src = (CareplanColumnInfo) rawSrc;
            CareplanColumnInfo dst = (CareplanColumnInfo) rawDst;
            dst.goalIndex = src.goalIndex;
            dst.patientIdIndex = src.patientIdIndex;
            dst.descriptionIndex = src.descriptionIndex;
            dst.diagnosisIndex = src.diagnosisIndex;
            dst.assignedOnIndex = src.assignedOnIndex;
            dst.updatedOnIndex = src.updatedOnIndex;
            dst.historyIndex = src.historyIndex;
            dst.createdOnIndex = src.createdOnIndex;
            dst.parentIdIndex = src.parentIdIndex;
            dst.enabledIndex = src.enabledIndex;
            dst.evaluationIndex = src.evaluationIndex;
            dst.numOfDaysIndex = src.numOfDaysIndex;
            dst.assessmentIndex = src.assessmentIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.nameIndex = src.nameIndex;
            dst.idIndex = src.idIndex;
            dst.beginTimeIndex = src.beginTimeIndex;
            dst.endTimeIndex = src.endTimeIndex;
            dst.assignerIdIndex = src.assignerIdIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("goal");
        fieldNames.add("patientId");
        fieldNames.add("description");
        fieldNames.add("diagnosis");
        fieldNames.add("assignedOn");
        fieldNames.add("updatedOn");
        fieldNames.add("history");
        fieldNames.add("createdOn");
        fieldNames.add(CooeySQLHelper.COLUMN_PARENT);
        fieldNames.add(ViewProps.ENABLED);
        fieldNames.add("evaluation");
        fieldNames.add("numOfDays");
        fieldNames.add("assessment");
        fieldNames.add("tenantId");
        fieldNames.add("name");
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("beginTime");
        fieldNames.add("endTime");
        fieldNames.add("assignerId");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CareplanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (CareplanColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$goal() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.goalIndex);
    }

    public void realmSet$goal(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.goalIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.goalIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.goalIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.goalIndex, row.getIndex(), value, true);
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

    public Diagnosis realmGet$diagnosis() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.diagnosisIndex)) {
            return null;
        }
        return (Diagnosis) this.proxyState.getRealm$realm().get(Diagnosis.class, this.proxyState.getRow$realm().getLink(this.columnInfo.diagnosisIndex), false, Collections.emptyList());
    }

    public void realmSet$diagnosis(Diagnosis value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.diagnosisIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.diagnosisIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("diagnosis")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Diagnosis) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.diagnosisIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.diagnosisIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
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

    public String realmGet$history() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.historyIndex);
    }

    public void realmSet$history(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.historyIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.historyIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.historyIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.historyIndex, row.getIndex(), value, true);
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

    public boolean realmGet$enabled() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.enabledIndex);
    }

    public void realmSet$enabled(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.enabledIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.enabledIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$evaluation() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.evaluationIndex);
    }

    public void realmSet$evaluation(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.evaluationIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.evaluationIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.evaluationIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.evaluationIndex, row.getIndex(), value, true);
            }
        }
    }

    public int realmGet$numOfDays() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.numOfDaysIndex);
    }

    public void realmSet$numOfDays(int value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.numOfDaysIndex, (long) value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.numOfDaysIndex, row.getIndex(), (long) value, true);
        }
    }

    public Assessment realmGet$assessment() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.assessmentIndex)) {
            return null;
        }
        return (Assessment) this.proxyState.getRealm$realm().get(Assessment.class, this.proxyState.getRow$realm().getLink(this.columnInfo.assessmentIndex), false, Collections.emptyList());
    }

    public void realmSet$assessment(Assessment value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.assessmentIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.assessmentIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("assessment")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Assessment) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.assessmentIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.assessmentIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
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

    public long realmGet$beginTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.beginTimeIndex);
    }

    public void realmSet$beginTime(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.beginTimeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.beginTimeIndex, row.getIndex(), value, true);
        }
    }

    public long realmGet$endTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.endTimeIndex);
    }

    public void realmSet$endTime(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.endTimeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.endTimeIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$assignerId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.assignerIdIndex);
    }

    public void realmSet$assignerId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.assignerIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.assignerIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.assignerIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.assignerIdIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder(CTConstants.CAREPLAN);
        builder.addPersistedProperty("goal", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("patientId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("diagnosis", RealmFieldType.OBJECT, CTConstants.DIAGNOSIS);
        builder.addPersistedProperty("assignedOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("updatedOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("history", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("createdOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty(CooeySQLHelper.COLUMN_PARENT, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(ViewProps.ENABLED, RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("evaluation", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("numOfDays", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("assessment", RealmFieldType.OBJECT, CTConstants.ASSESSMENT);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("beginTime", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("endTime", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("assignerId", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CareplanColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new CareplanColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Careplan";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Careplan createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(2);
        Careplan careplan = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Careplan.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Careplan.class), false, Collections.emptyList());
                    careplan = new CareplanRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (careplan == null) {
            if (json.has("diagnosis")) {
                excludeFields.add("diagnosis");
            }
            if (json.has("assessment")) {
                excludeFields.add("assessment");
            }
            if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                    careplan = (CareplanRealmProxy) realm.createObjectInternal(Careplan.class, null, true, excludeFields);
                } else {
                    String string = json.getString(ShareConstants.WEB_DIALOG_PARAM_ID);
                    CareplanRealmProxy obj = (CareplanRealmProxy) realm.createObjectInternal(Careplan.class, string, true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        CareplanRealmProxyInterface objProxy = careplan;
        if (json.has("goal")) {
            if (json.isNull("goal")) {
                objProxy.realmSet$goal(null);
            } else {
                objProxy.realmSet$goal(json.getString("goal"));
            }
        }
        if (json.has("patientId")) {
            if (json.isNull("patientId")) {
                objProxy.realmSet$patientId(null);
            } else {
                objProxy.realmSet$patientId(json.getString("patientId"));
            }
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                objProxy.realmSet$description(null);
            } else {
                objProxy.realmSet$description(json.getString("description"));
            }
        }
        if (json.has("diagnosis")) {
            if (json.isNull("diagnosis")) {
                objProxy.realmSet$diagnosis(null);
            } else {
                objProxy.realmSet$diagnosis(DiagnosisRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("diagnosis"), update));
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
        if (json.has("history")) {
            if (json.isNull("history")) {
                objProxy.realmSet$history(null);
            } else {
                objProxy.realmSet$history(json.getString("history"));
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
        if (json.has(ViewProps.ENABLED)) {
            if (json.isNull(ViewProps.ENABLED)) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'enabled' to null.");
            }
            objProxy.realmSet$enabled(json.getBoolean(ViewProps.ENABLED));
        }
        if (json.has("evaluation")) {
            if (json.isNull("evaluation")) {
                objProxy.realmSet$evaluation(null);
            } else {
                objProxy.realmSet$evaluation(json.getString("evaluation"));
            }
        }
        if (json.has("numOfDays")) {
            if (json.isNull("numOfDays")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'numOfDays' to null.");
            }
            objProxy.realmSet$numOfDays(json.getInt("numOfDays"));
        }
        if (json.has("assessment")) {
            if (json.isNull("assessment")) {
                objProxy.realmSet$assessment(null);
            } else {
                objProxy.realmSet$assessment(AssessmentRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("assessment"), update));
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
        if (json.has("beginTime")) {
            if (json.isNull("beginTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'beginTime' to null.");
            }
            objProxy.realmSet$beginTime(json.getLong("beginTime"));
        }
        if (json.has("endTime")) {
            if (json.isNull("endTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'endTime' to null.");
            }
            objProxy.realmSet$endTime(json.getLong("endTime"));
        }
        if (json.has("assignerId")) {
            if (json.isNull("assignerId")) {
                objProxy.realmSet$assignerId(null);
            } else {
                objProxy.realmSet$assignerId(json.getString("assignerId"));
            }
        }
        return careplan;
    }

    @TargetApi(11)
    public static Careplan createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Careplan obj = new Careplan();
        CareplanRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("goal")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$goal(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$goal(null);
                }
            } else if (name.equals("patientId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$patientId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$patientId(null);
                }
            } else if (name.equals("description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$description(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$description(null);
                }
            } else if (name.equals("diagnosis")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$diagnosis(null);
                } else {
                    objProxy.realmSet$diagnosis(DiagnosisRealmProxy.createUsingJsonStream(realm, reader));
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
            } else if (name.equals("history")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$history(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$history(null);
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
            } else if (name.equals(ViewProps.ENABLED)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$enabled(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'enabled' to null.");
                }
            } else if (name.equals("evaluation")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$evaluation(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$evaluation(null);
                }
            } else if (name.equals("numOfDays")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$numOfDays(reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'numOfDays' to null.");
                }
            } else if (name.equals("assessment")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$assessment(null);
                } else {
                    objProxy.realmSet$assessment(AssessmentRealmProxy.createUsingJsonStream(realm, reader));
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
            } else if (name.equals(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("beginTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$beginTime(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'beginTime' to null.");
                }
            } else if (name.equals("endTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$endTime(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'endTime' to null.");
                }
            } else if (!name.equals("assignerId")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$assignerId(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$assignerId(null);
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Careplan) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Careplan copyOrUpdate(Realm realm, Careplan object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Careplan) cachedRealmObject;
        }
        Careplan update2;
        Careplan realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Careplan.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Careplan.class), false, Collections.emptyList());
                    Careplan realmObject2 = new CareplanRealmProxy();
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

    public static Careplan copy(Realm realm, Careplan newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Careplan) cachedRealmObject;
        }
        Careplan realmObject = (Careplan) realm.createObjectInternal(Careplan.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        CareplanRealmProxyInterface realmObjectSource = newObject;
        CareplanRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$goal(realmObjectSource.realmGet$goal());
        realmObjectCopy.realmSet$patientId(realmObjectSource.realmGet$patientId());
        realmObjectCopy.realmSet$description(realmObjectSource.realmGet$description());
        Diagnosis diagnosisObj = realmObjectSource.realmGet$diagnosis();
        if (diagnosisObj == null) {
            realmObjectCopy.realmSet$diagnosis(null);
        } else {
            Diagnosis cachediagnosis = (Diagnosis) cache.get(diagnosisObj);
            if (cachediagnosis != null) {
                realmObjectCopy.realmSet$diagnosis(cachediagnosis);
            } else {
                realmObjectCopy.realmSet$diagnosis(DiagnosisRealmProxy.copyOrUpdate(realm, diagnosisObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$assignedOn(realmObjectSource.realmGet$assignedOn());
        realmObjectCopy.realmSet$updatedOn(realmObjectSource.realmGet$updatedOn());
        realmObjectCopy.realmSet$history(realmObjectSource.realmGet$history());
        realmObjectCopy.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectCopy.realmSet$parentId(realmObjectSource.realmGet$parentId());
        realmObjectCopy.realmSet$enabled(realmObjectSource.realmGet$enabled());
        realmObjectCopy.realmSet$evaluation(realmObjectSource.realmGet$evaluation());
        realmObjectCopy.realmSet$numOfDays(realmObjectSource.realmGet$numOfDays());
        Assessment assessmentObj = realmObjectSource.realmGet$assessment();
        if (assessmentObj == null) {
            realmObjectCopy.realmSet$assessment(null);
        } else {
            Assessment cacheassessment = (Assessment) cache.get(assessmentObj);
            if (cacheassessment != null) {
                realmObjectCopy.realmSet$assessment(cacheassessment);
            } else {
                realmObjectCopy.realmSet$assessment(AssessmentRealmProxy.copyOrUpdate(realm, assessmentObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$beginTime(realmObjectSource.realmGet$beginTime());
        realmObjectCopy.realmSet$endTime(realmObjectSource.realmGet$endTime());
        realmObjectCopy.realmSet$assignerId(realmObjectSource.realmGet$assignerId());
        return realmObject;
    }

    public static long insert(Realm realm, Careplan object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Careplan.class);
        long tableNativePtr = table.getNativePtr();
        CareplanColumnInfo columnInfo = (CareplanColumnInfo) realm.getSchema().getColumnInfo(Careplan.class);
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
        String realmGet$goal = object.realmGet$goal();
        if (realmGet$goal != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.goalIndex, rowIndex, realmGet$goal, false);
        }
        String realmGet$patientId = object.realmGet$patientId();
        if (realmGet$patientId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
        }
        String realmGet$description = object.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        }
        Diagnosis diagnosisObj = object.realmGet$diagnosis();
        if (diagnosisObj != null) {
            Long cachediagnosis = (Long) cache.get(diagnosisObj);
            if (cachediagnosis == null) {
                cachediagnosis = Long.valueOf(DiagnosisRealmProxy.insert(realm, diagnosisObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.diagnosisIndex, rowIndex, cachediagnosis.longValue(), false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.assignedOnIndex, rowIndex, object.realmGet$assignedOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
        String realmGet$history = object.realmGet$history();
        if (realmGet$history != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.historyIndex, rowIndex, realmGet$history, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
        String realmGet$parentId = object.realmGet$parentId();
        if (realmGet$parentId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parentIdIndex, rowIndex, realmGet$parentId, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.enabledIndex, rowIndex, object.realmGet$enabled(), false);
        String realmGet$evaluation = object.realmGet$evaluation();
        if (realmGet$evaluation != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.evaluationIndex, rowIndex, realmGet$evaluation, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.numOfDaysIndex, rowIndex, (long) object.realmGet$numOfDays(), false);
        Assessment assessmentObj = object.realmGet$assessment();
        if (assessmentObj != null) {
            Long cacheassessment = (Long) cache.get(assessmentObj);
            if (cacheassessment == null) {
                cacheassessment = Long.valueOf(AssessmentRealmProxy.insert(realm, assessmentObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.assessmentIndex, rowIndex, cacheassessment.longValue(), false);
        }
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        }
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.beginTimeIndex, rowIndex, object.realmGet$beginTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
        String realmGet$assignerId = object.realmGet$assignerId();
        if (realmGet$assignerId == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.assignerIdIndex, rowIndex, realmGet$assignerId, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Careplan.class);
        long tableNativePtr = table.getNativePtr();
        CareplanColumnInfo columnInfo = (CareplanColumnInfo) realm.getSchema().getColumnInfo(Careplan.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Careplan object = (Careplan) objects.next();
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
                    String realmGet$goal = object.realmGet$goal();
                    if (realmGet$goal != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.goalIndex, rowIndex, realmGet$goal, false);
                    }
                    String realmGet$patientId = object.realmGet$patientId();
                    if (realmGet$patientId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
                    }
                    String realmGet$description = object.realmGet$description();
                    if (realmGet$description != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                    }
                    Diagnosis diagnosisObj = object.realmGet$diagnosis();
                    if (diagnosisObj != null) {
                        Long cachediagnosis = (Long) cache.get(diagnosisObj);
                        if (cachediagnosis == null) {
                            cachediagnosis = Long.valueOf(DiagnosisRealmProxy.insert(realm, diagnosisObj, (Map) cache));
                        }
                        table.setLink(columnInfo.diagnosisIndex, rowIndex, cachediagnosis.longValue(), false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.assignedOnIndex, rowIndex, object.realmGet$assignedOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
                    String realmGet$history = object.realmGet$history();
                    if (realmGet$history != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.historyIndex, rowIndex, realmGet$history, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
                    String realmGet$parentId = object.realmGet$parentId();
                    if (realmGet$parentId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parentIdIndex, rowIndex, realmGet$parentId, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.enabledIndex, rowIndex, object.realmGet$enabled(), false);
                    String realmGet$evaluation = object.realmGet$evaluation();
                    if (realmGet$evaluation != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.evaluationIndex, rowIndex, realmGet$evaluation, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.numOfDaysIndex, rowIndex, (long) object.realmGet$numOfDays(), false);
                    Assessment assessmentObj = object.realmGet$assessment();
                    if (assessmentObj != null) {
                        Long cacheassessment = (Long) cache.get(assessmentObj);
                        if (cacheassessment == null) {
                            cacheassessment = Long.valueOf(AssessmentRealmProxy.insert(realm, assessmentObj, (Map) cache));
                        }
                        table.setLink(columnInfo.assessmentIndex, rowIndex, cacheassessment.longValue(), false);
                    }
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.beginTimeIndex, rowIndex, object.realmGet$beginTime(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
                    String realmGet$assignerId = object.realmGet$assignerId();
                    if (realmGet$assignerId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.assignerIdIndex, rowIndex, realmGet$assignerId, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Careplan object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Careplan.class);
        long tableNativePtr = table.getNativePtr();
        CareplanColumnInfo columnInfo = (CareplanColumnInfo) realm.getSchema().getColumnInfo(Careplan.class);
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
        String realmGet$goal = object.realmGet$goal();
        if (realmGet$goal != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.goalIndex, rowIndex, realmGet$goal, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.goalIndex, rowIndex, false);
        }
        String realmGet$patientId = object.realmGet$patientId();
        if (realmGet$patientId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.patientIdIndex, rowIndex, false);
        }
        String realmGet$description = object.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
        }
        Diagnosis diagnosisObj = object.realmGet$diagnosis();
        if (diagnosisObj != null) {
            Long cachediagnosis = (Long) cache.get(diagnosisObj);
            if (cachediagnosis == null) {
                cachediagnosis = Long.valueOf(DiagnosisRealmProxy.insertOrUpdate(realm, diagnosisObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.diagnosisIndex, rowIndex, cachediagnosis.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.diagnosisIndex, rowIndex);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.assignedOnIndex, rowIndex, object.realmGet$assignedOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
        String realmGet$history = object.realmGet$history();
        if (realmGet$history != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.historyIndex, rowIndex, realmGet$history, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.historyIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
        String realmGet$parentId = object.realmGet$parentId();
        if (realmGet$parentId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parentIdIndex, rowIndex, realmGet$parentId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.parentIdIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.enabledIndex, rowIndex, object.realmGet$enabled(), false);
        String realmGet$evaluation = object.realmGet$evaluation();
        if (realmGet$evaluation != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.evaluationIndex, rowIndex, realmGet$evaluation, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.evaluationIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.numOfDaysIndex, rowIndex, (long) object.realmGet$numOfDays(), false);
        Assessment assessmentObj = object.realmGet$assessment();
        if (assessmentObj != null) {
            Long cacheassessment = (Long) cache.get(assessmentObj);
            if (cacheassessment == null) {
                cacheassessment = Long.valueOf(AssessmentRealmProxy.insertOrUpdate(realm, assessmentObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.assessmentIndex, rowIndex, cacheassessment.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.assessmentIndex, rowIndex);
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
        Table.nativeSetLong(tableNativePtr, columnInfo.beginTimeIndex, rowIndex, object.realmGet$beginTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
        String realmGet$assignerId = object.realmGet$assignerId();
        if (realmGet$assignerId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.assignerIdIndex, rowIndex, realmGet$assignerId, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.assignerIdIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Careplan.class);
        long tableNativePtr = table.getNativePtr();
        CareplanColumnInfo columnInfo = (CareplanColumnInfo) realm.getSchema().getColumnInfo(Careplan.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Careplan object = (Careplan) objects.next();
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
                    String realmGet$goal = object.realmGet$goal();
                    if (realmGet$goal != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.goalIndex, rowIndex, realmGet$goal, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.goalIndex, rowIndex, false);
                    }
                    String realmGet$patientId = object.realmGet$patientId();
                    if (realmGet$patientId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.patientIdIndex, rowIndex, false);
                    }
                    String realmGet$description = object.realmGet$description();
                    if (realmGet$description != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
                    }
                    Diagnosis diagnosisObj = object.realmGet$diagnosis();
                    if (diagnosisObj != null) {
                        Long cachediagnosis = (Long) cache.get(diagnosisObj);
                        if (cachediagnosis == null) {
                            cachediagnosis = Long.valueOf(DiagnosisRealmProxy.insertOrUpdate(realm, diagnosisObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.diagnosisIndex, rowIndex, cachediagnosis.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.diagnosisIndex, rowIndex);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.assignedOnIndex, rowIndex, object.realmGet$assignedOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.updatedOnIndex, rowIndex, object.realmGet$updatedOn(), false);
                    String realmGet$history = object.realmGet$history();
                    if (realmGet$history != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.historyIndex, rowIndex, realmGet$history, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.historyIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
                    String realmGet$parentId = object.realmGet$parentId();
                    if (realmGet$parentId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parentIdIndex, rowIndex, realmGet$parentId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.parentIdIndex, rowIndex, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.enabledIndex, rowIndex, object.realmGet$enabled(), false);
                    String realmGet$evaluation = object.realmGet$evaluation();
                    if (realmGet$evaluation != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.evaluationIndex, rowIndex, realmGet$evaluation, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.evaluationIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.numOfDaysIndex, rowIndex, (long) object.realmGet$numOfDays(), false);
                    Assessment assessmentObj = object.realmGet$assessment();
                    if (assessmentObj != null) {
                        Long cacheassessment = (Long) cache.get(assessmentObj);
                        if (cacheassessment == null) {
                            cacheassessment = Long.valueOf(AssessmentRealmProxy.insertOrUpdate(realm, assessmentObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.assessmentIndex, rowIndex, cacheassessment.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.assessmentIndex, rowIndex);
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
                    Table.nativeSetLong(tableNativePtr, columnInfo.beginTimeIndex, rowIndex, object.realmGet$beginTime(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
                    String realmGet$assignerId = object.realmGet$assignerId();
                    if (realmGet$assignerId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.assignerIdIndex, rowIndex, realmGet$assignerId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.assignerIdIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Careplan createDetachedCopy(Careplan realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Careplan unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Careplan();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Careplan) cachedObject.object;
        } else {
            unmanagedObject = (Careplan) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        CareplanRealmProxyInterface unmanagedCopy = unmanagedObject;
        CareplanRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$goal(realmSource.realmGet$goal());
        unmanagedCopy.realmSet$patientId(realmSource.realmGet$patientId());
        unmanagedCopy.realmSet$description(realmSource.realmGet$description());
        unmanagedCopy.realmSet$diagnosis(DiagnosisRealmProxy.createDetachedCopy(realmSource.realmGet$diagnosis(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$assignedOn(realmSource.realmGet$assignedOn());
        unmanagedCopy.realmSet$updatedOn(realmSource.realmGet$updatedOn());
        unmanagedCopy.realmSet$history(realmSource.realmGet$history());
        unmanagedCopy.realmSet$createdOn(realmSource.realmGet$createdOn());
        unmanagedCopy.realmSet$parentId(realmSource.realmGet$parentId());
        unmanagedCopy.realmSet$enabled(realmSource.realmGet$enabled());
        unmanagedCopy.realmSet$evaluation(realmSource.realmGet$evaluation());
        unmanagedCopy.realmSet$numOfDays(realmSource.realmGet$numOfDays());
        unmanagedCopy.realmSet$assessment(AssessmentRealmProxy.createDetachedCopy(realmSource.realmGet$assessment(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$beginTime(realmSource.realmGet$beginTime());
        unmanagedCopy.realmSet$endTime(realmSource.realmGet$endTime());
        unmanagedCopy.realmSet$assignerId(realmSource.realmGet$assignerId());
        return unmanagedObject;
    }

    static Careplan update(Realm realm, Careplan realmObject, Careplan newObject, Map<RealmModel, RealmObjectProxy> cache) {
        CareplanRealmProxyInterface realmObjectTarget = realmObject;
        CareplanRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$goal(realmObjectSource.realmGet$goal());
        realmObjectTarget.realmSet$patientId(realmObjectSource.realmGet$patientId());
        realmObjectTarget.realmSet$description(realmObjectSource.realmGet$description());
        Diagnosis diagnosisObj = realmObjectSource.realmGet$diagnosis();
        if (diagnosisObj == null) {
            realmObjectTarget.realmSet$diagnosis(null);
        } else {
            Diagnosis cachediagnosis = (Diagnosis) cache.get(diagnosisObj);
            if (cachediagnosis != null) {
                realmObjectTarget.realmSet$diagnosis(cachediagnosis);
            } else {
                realmObjectTarget.realmSet$diagnosis(DiagnosisRealmProxy.copyOrUpdate(realm, diagnosisObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$assignedOn(realmObjectSource.realmGet$assignedOn());
        realmObjectTarget.realmSet$updatedOn(realmObjectSource.realmGet$updatedOn());
        realmObjectTarget.realmSet$history(realmObjectSource.realmGet$history());
        realmObjectTarget.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectTarget.realmSet$parentId(realmObjectSource.realmGet$parentId());
        realmObjectTarget.realmSet$enabled(realmObjectSource.realmGet$enabled());
        realmObjectTarget.realmSet$evaluation(realmObjectSource.realmGet$evaluation());
        realmObjectTarget.realmSet$numOfDays(realmObjectSource.realmGet$numOfDays());
        Assessment assessmentObj = realmObjectSource.realmGet$assessment();
        if (assessmentObj == null) {
            realmObjectTarget.realmSet$assessment(null);
        } else {
            Assessment cacheassessment = (Assessment) cache.get(assessmentObj);
            if (cacheassessment != null) {
                realmObjectTarget.realmSet$assessment(cacheassessment);
            } else {
                realmObjectTarget.realmSet$assessment(AssessmentRealmProxy.copyOrUpdate(realm, assessmentObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$beginTime(realmObjectSource.realmGet$beginTime());
        realmObjectTarget.realmSet$endTime(realmObjectSource.realmGet$endTime());
        realmObjectTarget.realmSet$assignerId(realmObjectSource.realmGet$assignerId());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Careplan = proxy[");
        stringBuilder.append("{goal:");
        stringBuilder.append(realmGet$goal() != null ? realmGet$goal() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{patientId:");
        stringBuilder.append(realmGet$patientId() != null ? realmGet$patientId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{description:");
        stringBuilder.append(realmGet$description() != null ? realmGet$description() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{diagnosis:");
        stringBuilder.append(realmGet$diagnosis() != null ? CTConstants.DIAGNOSIS : "null");
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
        stringBuilder.append("{history:");
        stringBuilder.append(realmGet$history() != null ? realmGet$history() : "null");
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
        stringBuilder.append("{enabled:");
        stringBuilder.append(realmGet$enabled());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{evaluation:");
        stringBuilder.append(realmGet$evaluation() != null ? realmGet$evaluation() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{numOfDays:");
        stringBuilder.append(realmGet$numOfDays());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{assessment:");
        stringBuilder.append(realmGet$assessment() != null ? CTConstants.ASSESSMENT : "null");
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
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{beginTime:");
        stringBuilder.append(realmGet$beginTime());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{endTime:");
        stringBuilder.append(realmGet$endTime());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{assignerId:");
        stringBuilder.append(realmGet$assignerId() != null ? realmGet$assignerId() : "null");
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
        CareplanRealmProxy aCareplan = (CareplanRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aCareplan.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCareplan.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aCareplan.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
