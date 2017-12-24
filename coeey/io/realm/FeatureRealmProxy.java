package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.careplan.Alert;
import com.cooey.common.vo.careplan.CarePlanReminder;
import com.cooey.common.vo.careplan.CommonFeature;
import com.cooey.common.vo.careplan.Feature;
import com.facebook.share.internal.ShareConstants;
import com.ihealth.communication.control.AmProfile;
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

public class FeatureRealmProxy extends Feature implements RealmObjectProxy, FeatureRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private FeatureColumnInfo columnInfo;
    private ProxyState<Feature> proxyState;

    static final class FeatureColumnInfo extends ColumnInfo {
        long alertIndex;
        long carePlanReminderIndex;
        long endTimeIndex;
        long frequencyIndex;
        long idIndex;
        long nameIndex;
        long periodIndex;
        long propertiesIndex;
        long repeatIndex;
        long startTimeIndex;
        long tenantIdIndex;
        long typeIndex;
        long urlIndex;

        FeatureColumnInfo(OsSchemaInfo schemaInfo) {
            super(13);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Feature");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.propertiesIndex = addColumnDetails("properties", objectSchemaInfo);
            this.alertIndex = addColumnDetails("alert", objectSchemaInfo);
            this.urlIndex = addColumnDetails("url", objectSchemaInfo);
            this.frequencyIndex = addColumnDetails("frequency", objectSchemaInfo);
            this.startTimeIndex = addColumnDetails("startTime", objectSchemaInfo);
            this.endTimeIndex = addColumnDetails("endTime", objectSchemaInfo);
            this.periodIndex = addColumnDetails("period", objectSchemaInfo);
            this.repeatIndex = addColumnDetails(AmProfile.GET_ALARM_ISREPEAT_AM, objectSchemaInfo);
            this.carePlanReminderIndex = addColumnDetails("carePlanReminder", objectSchemaInfo);
        }

        FeatureColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new FeatureColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            FeatureColumnInfo src = (FeatureColumnInfo) rawSrc;
            FeatureColumnInfo dst = (FeatureColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.nameIndex = src.nameIndex;
            dst.typeIndex = src.typeIndex;
            dst.propertiesIndex = src.propertiesIndex;
            dst.alertIndex = src.alertIndex;
            dst.urlIndex = src.urlIndex;
            dst.frequencyIndex = src.frequencyIndex;
            dst.startTimeIndex = src.startTimeIndex;
            dst.endTimeIndex = src.endTimeIndex;
            dst.periodIndex = src.periodIndex;
            dst.repeatIndex = src.repeatIndex;
            dst.carePlanReminderIndex = src.carePlanReminderIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("tenantId");
        fieldNames.add("name");
        fieldNames.add("type");
        fieldNames.add("properties");
        fieldNames.add("alert");
        fieldNames.add("url");
        fieldNames.add("frequency");
        fieldNames.add("startTime");
        fieldNames.add("endTime");
        fieldNames.add("period");
        fieldNames.add(AmProfile.GET_ALARM_ISREPEAT_AM);
        fieldNames.add("carePlanReminder");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    FeatureRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (FeatureColumnInfo) context.getColumnInfo();
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

    public CommonFeature realmGet$properties() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.propertiesIndex)) {
            return null;
        }
        return (CommonFeature) this.proxyState.getRealm$realm().get(CommonFeature.class, this.proxyState.getRow$realm().getLink(this.columnInfo.propertiesIndex), false, Collections.emptyList());
    }

    public void realmSet$properties(CommonFeature value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.propertiesIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.propertiesIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("properties")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (CommonFeature) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.propertiesIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.propertiesIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Alert realmGet$alert() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.alertIndex)) {
            return null;
        }
        return (Alert) this.proxyState.getRealm$realm().get(Alert.class, this.proxyState.getRow$realm().getLink(this.columnInfo.alertIndex), false, Collections.emptyList());
    }

    public void realmSet$alert(Alert value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.alertIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.alertIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("alert")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Alert) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.alertIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.alertIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public String realmGet$url() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.urlIndex);
    }

    public void realmSet$url(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.urlIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.urlIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.urlIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.urlIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$frequency() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.frequencyIndex);
    }

    public void realmSet$frequency(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.frequencyIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.frequencyIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.frequencyIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.frequencyIndex, row.getIndex(), value, true);
            }
        }
    }

    public long realmGet$startTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.startTimeIndex);
    }

    public void realmSet$startTime(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.startTimeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.startTimeIndex, row.getIndex(), value, true);
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

    public String realmGet$period() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.periodIndex);
    }

    public void realmSet$period(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.periodIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.periodIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.periodIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.periodIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$repeat() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.repeatIndex);
    }

    public void realmSet$repeat(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.repeatIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.repeatIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.repeatIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.repeatIndex, row.getIndex(), value, true);
            }
        }
    }

    public CarePlanReminder realmGet$carePlanReminder() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.carePlanReminderIndex)) {
            return null;
        }
        return (CarePlanReminder) this.proxyState.getRealm$realm().get(CarePlanReminder.class, this.proxyState.getRow$realm().getLink(this.columnInfo.carePlanReminderIndex), false, Collections.emptyList());
    }

    public void realmSet$carePlanReminder(CarePlanReminder value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.carePlanReminderIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.carePlanReminderIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("carePlanReminder")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (CarePlanReminder) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.carePlanReminderIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.carePlanReminderIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Feature");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("properties", RealmFieldType.OBJECT, "CommonFeature");
        builder.addPersistedLinkProperty("alert", RealmFieldType.OBJECT, "Alert");
        builder.addPersistedProperty("url", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("frequency", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("startTime", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("endTime", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("period", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(AmProfile.GET_ALARM_ISREPEAT_AM, RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("carePlanReminder", RealmFieldType.OBJECT, "CarePlanReminder");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static FeatureColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new FeatureColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Feature";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Feature createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(3);
        Feature feature = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Feature.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Feature.class), false, Collections.emptyList());
                    feature = new FeatureRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (feature == null) {
            if (json.has("properties")) {
                excludeFields.add("properties");
            }
            if (json.has("alert")) {
                excludeFields.add("alert");
            }
            if (json.has("carePlanReminder")) {
                excludeFields.add("carePlanReminder");
            }
            if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                    feature = (FeatureRealmProxy) realm.createObjectInternal(Feature.class, null, true, excludeFields);
                } else {
                    String string = json.getString(ShareConstants.WEB_DIALOG_PARAM_ID);
                    FeatureRealmProxy obj = (FeatureRealmProxy) realm.createObjectInternal(Feature.class, string, true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        FeatureRealmProxyInterface objProxy = feature;
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
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type(json.getString("type"));
            }
        }
        if (json.has("properties")) {
            if (json.isNull("properties")) {
                objProxy.realmSet$properties(null);
            } else {
                objProxy.realmSet$properties(CommonFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("properties"), update));
            }
        }
        if (json.has("alert")) {
            if (json.isNull("alert")) {
                objProxy.realmSet$alert(null);
            } else {
                objProxy.realmSet$alert(AlertRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("alert"), update));
            }
        }
        if (json.has("url")) {
            if (json.isNull("url")) {
                objProxy.realmSet$url(null);
            } else {
                objProxy.realmSet$url(json.getString("url"));
            }
        }
        if (json.has("frequency")) {
            if (json.isNull("frequency")) {
                objProxy.realmSet$frequency(null);
            } else {
                objProxy.realmSet$frequency(json.getString("frequency"));
            }
        }
        if (json.has("startTime")) {
            if (json.isNull("startTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'startTime' to null.");
            }
            objProxy.realmSet$startTime(json.getLong("startTime"));
        }
        if (json.has("endTime")) {
            if (json.isNull("endTime")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'endTime' to null.");
            }
            objProxy.realmSet$endTime(json.getLong("endTime"));
        }
        if (json.has("period")) {
            if (json.isNull("period")) {
                objProxy.realmSet$period(null);
            } else {
                objProxy.realmSet$period(json.getString("period"));
            }
        }
        if (json.has(AmProfile.GET_ALARM_ISREPEAT_AM)) {
            if (json.isNull(AmProfile.GET_ALARM_ISREPEAT_AM)) {
                objProxy.realmSet$repeat(null);
            } else {
                objProxy.realmSet$repeat(json.getString(AmProfile.GET_ALARM_ISREPEAT_AM));
            }
        }
        if (json.has("carePlanReminder")) {
            if (json.isNull("carePlanReminder")) {
                objProxy.realmSet$carePlanReminder(null);
            } else {
                objProxy.realmSet$carePlanReminder(CarePlanReminderRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("carePlanReminder"), update));
            }
        }
        return feature;
    }

    @TargetApi(11)
    public static Feature createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Feature obj = new Feature();
        FeatureRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("properties")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$properties(null);
                } else {
                    objProxy.realmSet$properties(CommonFeatureRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("alert")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$alert(null);
                } else {
                    objProxy.realmSet$alert(AlertRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("url")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$url(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$url(null);
                }
            } else if (name.equals("frequency")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$frequency(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$frequency(null);
                }
            } else if (name.equals("startTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$startTime(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'startTime' to null.");
                }
            } else if (name.equals("endTime")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$endTime(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'endTime' to null.");
                }
            } else if (name.equals("period")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$period(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$period(null);
                }
            } else if (name.equals(AmProfile.GET_ALARM_ISREPEAT_AM)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$repeat(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$repeat(null);
                }
            } else if (!name.equals("carePlanReminder")) {
                reader.skipValue();
            } else if (reader.peek() == JsonToken.NULL) {
                reader.skipValue();
                objProxy.realmSet$carePlanReminder(null);
            } else {
                objProxy.realmSet$carePlanReminder(CarePlanReminderRealmProxy.createUsingJsonStream(realm, reader));
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Feature) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Feature copyOrUpdate(Realm realm, Feature object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Feature) cachedRealmObject;
        }
        Feature update2;
        Feature realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Feature.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Feature.class), false, Collections.emptyList());
                    Feature realmObject2 = new FeatureRealmProxy();
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

    public static Feature copy(Realm realm, Feature newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Feature) cachedRealmObject;
        }
        Feature realmObject = (Feature) realm.createObjectInternal(Feature.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        FeatureRealmProxyInterface realmObjectSource = newObject;
        FeatureRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        CommonFeature propertiesObj = realmObjectSource.realmGet$properties();
        if (propertiesObj == null) {
            realmObjectCopy.realmSet$properties(null);
        } else {
            CommonFeature cacheproperties = (CommonFeature) cache.get(propertiesObj);
            if (cacheproperties != null) {
                realmObjectCopy.realmSet$properties(cacheproperties);
            } else {
                realmObjectCopy.realmSet$properties(CommonFeatureRealmProxy.copyOrUpdate(realm, propertiesObj, update, cache));
            }
        }
        Alert alertObj = realmObjectSource.realmGet$alert();
        if (alertObj == null) {
            realmObjectCopy.realmSet$alert(null);
        } else {
            Alert cachealert = (Alert) cache.get(alertObj);
            if (cachealert != null) {
                realmObjectCopy.realmSet$alert(cachealert);
            } else {
                realmObjectCopy.realmSet$alert(AlertRealmProxy.copyOrUpdate(realm, alertObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$url(realmObjectSource.realmGet$url());
        realmObjectCopy.realmSet$frequency(realmObjectSource.realmGet$frequency());
        realmObjectCopy.realmSet$startTime(realmObjectSource.realmGet$startTime());
        realmObjectCopy.realmSet$endTime(realmObjectSource.realmGet$endTime());
        realmObjectCopy.realmSet$period(realmObjectSource.realmGet$period());
        realmObjectCopy.realmSet$repeat(realmObjectSource.realmGet$repeat());
        CarePlanReminder carePlanReminderObj = realmObjectSource.realmGet$carePlanReminder();
        if (carePlanReminderObj == null) {
            realmObjectCopy.realmSet$carePlanReminder(null);
        } else {
            CarePlanReminder cachecarePlanReminder = (CarePlanReminder) cache.get(carePlanReminderObj);
            if (cachecarePlanReminder != null) {
                realmObjectCopy.realmSet$carePlanReminder(cachecarePlanReminder);
            } else {
                realmObjectCopy.realmSet$carePlanReminder(CarePlanReminderRealmProxy.copyOrUpdate(realm, carePlanReminderObj, update, cache));
            }
        }
        return realmObject;
    }

    public static long insert(Realm realm, Feature object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Feature.class);
        long tableNativePtr = table.getNativePtr();
        FeatureColumnInfo columnInfo = (FeatureColumnInfo) realm.getSchema().getColumnInfo(Feature.class);
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
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }
        CommonFeature propertiesObj = object.realmGet$properties();
        if (propertiesObj != null) {
            Long cacheproperties = (Long) cache.get(propertiesObj);
            if (cacheproperties == null) {
                cacheproperties = Long.valueOf(CommonFeatureRealmProxy.insert(realm, propertiesObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.propertiesIndex, rowIndex, cacheproperties.longValue(), false);
        }
        Alert alertObj = object.realmGet$alert();
        if (alertObj != null) {
            Long cachealert = (Long) cache.get(alertObj);
            if (cachealert == null) {
                cachealert = Long.valueOf(AlertRealmProxy.insert(realm, alertObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.alertIndex, rowIndex, cachealert.longValue(), false);
        }
        String realmGet$url = object.realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
        }
        String realmGet$frequency = object.realmGet$frequency();
        if (realmGet$frequency != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.frequencyIndex, rowIndex, realmGet$frequency, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
        String realmGet$period = object.realmGet$period();
        if (realmGet$period != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.periodIndex, rowIndex, realmGet$period, false);
        }
        String realmGet$repeat = object.realmGet$repeat();
        if (realmGet$repeat != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.repeatIndex, rowIndex, realmGet$repeat, false);
        }
        CarePlanReminder carePlanReminderObj = object.realmGet$carePlanReminder();
        if (carePlanReminderObj == null) {
            return rowIndex;
        }
        Long cachecarePlanReminder = (Long) cache.get(carePlanReminderObj);
        if (cachecarePlanReminder == null) {
            cachecarePlanReminder = Long.valueOf(CarePlanReminderRealmProxy.insert(realm, carePlanReminderObj, (Map) cache));
        }
        Table.nativeSetLink(tableNativePtr, columnInfo.carePlanReminderIndex, rowIndex, cachecarePlanReminder.longValue(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Feature.class);
        long tableNativePtr = table.getNativePtr();
        FeatureColumnInfo columnInfo = (FeatureColumnInfo) realm.getSchema().getColumnInfo(Feature.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Feature object = (Feature) objects.next();
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
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    }
                    CommonFeature propertiesObj = object.realmGet$properties();
                    if (propertiesObj != null) {
                        Long cacheproperties = (Long) cache.get(propertiesObj);
                        if (cacheproperties == null) {
                            cacheproperties = Long.valueOf(CommonFeatureRealmProxy.insert(realm, propertiesObj, (Map) cache));
                        }
                        table.setLink(columnInfo.propertiesIndex, rowIndex, cacheproperties.longValue(), false);
                    }
                    Alert alertObj = object.realmGet$alert();
                    if (alertObj != null) {
                        Long cachealert = (Long) cache.get(alertObj);
                        if (cachealert == null) {
                            cachealert = Long.valueOf(AlertRealmProxy.insert(realm, alertObj, (Map) cache));
                        }
                        table.setLink(columnInfo.alertIndex, rowIndex, cachealert.longValue(), false);
                    }
                    String realmGet$url = object.realmGet$url();
                    if (realmGet$url != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
                    }
                    String realmGet$frequency = object.realmGet$frequency();
                    if (realmGet$frequency != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.frequencyIndex, rowIndex, realmGet$frequency, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
                    String realmGet$period = object.realmGet$period();
                    if (realmGet$period != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.periodIndex, rowIndex, realmGet$period, false);
                    }
                    String realmGet$repeat = object.realmGet$repeat();
                    if (realmGet$repeat != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.repeatIndex, rowIndex, realmGet$repeat, false);
                    }
                    CarePlanReminder carePlanReminderObj = object.realmGet$carePlanReminder();
                    if (carePlanReminderObj != null) {
                        Long cachecarePlanReminder = (Long) cache.get(carePlanReminderObj);
                        if (cachecarePlanReminder == null) {
                            cachecarePlanReminder = Long.valueOf(CarePlanReminderRealmProxy.insert(realm, carePlanReminderObj, (Map) cache));
                        }
                        table.setLink(columnInfo.carePlanReminderIndex, rowIndex, cachecarePlanReminder.longValue(), false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Feature object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Feature.class);
        long tableNativePtr = table.getNativePtr();
        FeatureColumnInfo columnInfo = (FeatureColumnInfo) realm.getSchema().getColumnInfo(Feature.class);
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
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        }
        CommonFeature propertiesObj = object.realmGet$properties();
        if (propertiesObj != null) {
            Long cacheproperties = (Long) cache.get(propertiesObj);
            if (cacheproperties == null) {
                cacheproperties = Long.valueOf(CommonFeatureRealmProxy.insertOrUpdate(realm, propertiesObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.propertiesIndex, rowIndex, cacheproperties.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.propertiesIndex, rowIndex);
        }
        Alert alertObj = object.realmGet$alert();
        if (alertObj != null) {
            Long cachealert = (Long) cache.get(alertObj);
            if (cachealert == null) {
                cachealert = Long.valueOf(AlertRealmProxy.insertOrUpdate(realm, alertObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.alertIndex, rowIndex, cachealert.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.alertIndex, rowIndex);
        }
        String realmGet$url = object.realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.urlIndex, rowIndex, false);
        }
        String realmGet$frequency = object.realmGet$frequency();
        if (realmGet$frequency != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.frequencyIndex, rowIndex, realmGet$frequency, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.frequencyIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
        String realmGet$period = object.realmGet$period();
        if (realmGet$period != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.periodIndex, rowIndex, realmGet$period, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.periodIndex, rowIndex, false);
        }
        String realmGet$repeat = object.realmGet$repeat();
        if (realmGet$repeat != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.repeatIndex, rowIndex, realmGet$repeat, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.repeatIndex, rowIndex, false);
        }
        CarePlanReminder carePlanReminderObj = object.realmGet$carePlanReminder();
        if (carePlanReminderObj != null) {
            Long cachecarePlanReminder = (Long) cache.get(carePlanReminderObj);
            if (cachecarePlanReminder == null) {
                cachecarePlanReminder = Long.valueOf(CarePlanReminderRealmProxy.insertOrUpdate(realm, carePlanReminderObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.carePlanReminderIndex, rowIndex, cachecarePlanReminder.longValue(), false);
            return rowIndex;
        }
        Table.nativeNullifyLink(tableNativePtr, columnInfo.carePlanReminderIndex, rowIndex);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Feature.class);
        long tableNativePtr = table.getNativePtr();
        FeatureColumnInfo columnInfo = (FeatureColumnInfo) realm.getSchema().getColumnInfo(Feature.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Feature object = (Feature) objects.next();
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
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
                    }
                    CommonFeature propertiesObj = object.realmGet$properties();
                    if (propertiesObj != null) {
                        Long cacheproperties = (Long) cache.get(propertiesObj);
                        if (cacheproperties == null) {
                            cacheproperties = Long.valueOf(CommonFeatureRealmProxy.insertOrUpdate(realm, propertiesObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.propertiesIndex, rowIndex, cacheproperties.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.propertiesIndex, rowIndex);
                    }
                    Alert alertObj = object.realmGet$alert();
                    if (alertObj != null) {
                        Long cachealert = (Long) cache.get(alertObj);
                        if (cachealert == null) {
                            cachealert = Long.valueOf(AlertRealmProxy.insertOrUpdate(realm, alertObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.alertIndex, rowIndex, cachealert.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.alertIndex, rowIndex);
                    }
                    String realmGet$url = object.realmGet$url();
                    if (realmGet$url != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.urlIndex, rowIndex, false);
                    }
                    String realmGet$frequency = object.realmGet$frequency();
                    if (realmGet$frequency != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.frequencyIndex, rowIndex, realmGet$frequency, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.frequencyIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.startTimeIndex, rowIndex, object.realmGet$startTime(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.endTimeIndex, rowIndex, object.realmGet$endTime(), false);
                    String realmGet$period = object.realmGet$period();
                    if (realmGet$period != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.periodIndex, rowIndex, realmGet$period, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.periodIndex, rowIndex, false);
                    }
                    String realmGet$repeat = object.realmGet$repeat();
                    if (realmGet$repeat != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.repeatIndex, rowIndex, realmGet$repeat, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.repeatIndex, rowIndex, false);
                    }
                    CarePlanReminder carePlanReminderObj = object.realmGet$carePlanReminder();
                    if (carePlanReminderObj != null) {
                        Long cachecarePlanReminder = (Long) cache.get(carePlanReminderObj);
                        if (cachecarePlanReminder == null) {
                            cachecarePlanReminder = Long.valueOf(CarePlanReminderRealmProxy.insertOrUpdate(realm, carePlanReminderObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.carePlanReminderIndex, rowIndex, cachecarePlanReminder.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.carePlanReminderIndex, rowIndex);
                    }
                }
            }
        }
    }

    public static Feature createDetachedCopy(Feature realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Feature unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Feature();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Feature) cachedObject.object;
        } else {
            unmanagedObject = (Feature) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        FeatureRealmProxyInterface unmanagedCopy = unmanagedObject;
        FeatureRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$properties(CommonFeatureRealmProxy.createDetachedCopy(realmSource.realmGet$properties(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$alert(AlertRealmProxy.createDetachedCopy(realmSource.realmGet$alert(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$url(realmSource.realmGet$url());
        unmanagedCopy.realmSet$frequency(realmSource.realmGet$frequency());
        unmanagedCopy.realmSet$startTime(realmSource.realmGet$startTime());
        unmanagedCopy.realmSet$endTime(realmSource.realmGet$endTime());
        unmanagedCopy.realmSet$period(realmSource.realmGet$period());
        unmanagedCopy.realmSet$repeat(realmSource.realmGet$repeat());
        unmanagedCopy.realmSet$carePlanReminder(CarePlanReminderRealmProxy.createDetachedCopy(realmSource.realmGet$carePlanReminder(), currentDepth + 1, maxDepth, cache));
        return unmanagedObject;
    }

    static Feature update(Realm realm, Feature realmObject, Feature newObject, Map<RealmModel, RealmObjectProxy> cache) {
        FeatureRealmProxyInterface realmObjectTarget = realmObject;
        FeatureRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$type(realmObjectSource.realmGet$type());
        CommonFeature propertiesObj = realmObjectSource.realmGet$properties();
        if (propertiesObj == null) {
            realmObjectTarget.realmSet$properties(null);
        } else {
            CommonFeature cacheproperties = (CommonFeature) cache.get(propertiesObj);
            if (cacheproperties != null) {
                realmObjectTarget.realmSet$properties(cacheproperties);
            } else {
                realmObjectTarget.realmSet$properties(CommonFeatureRealmProxy.copyOrUpdate(realm, propertiesObj, true, cache));
            }
        }
        Alert alertObj = realmObjectSource.realmGet$alert();
        if (alertObj == null) {
            realmObjectTarget.realmSet$alert(null);
        } else {
            Alert cachealert = (Alert) cache.get(alertObj);
            if (cachealert != null) {
                realmObjectTarget.realmSet$alert(cachealert);
            } else {
                realmObjectTarget.realmSet$alert(AlertRealmProxy.copyOrUpdate(realm, alertObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$url(realmObjectSource.realmGet$url());
        realmObjectTarget.realmSet$frequency(realmObjectSource.realmGet$frequency());
        realmObjectTarget.realmSet$startTime(realmObjectSource.realmGet$startTime());
        realmObjectTarget.realmSet$endTime(realmObjectSource.realmGet$endTime());
        realmObjectTarget.realmSet$period(realmObjectSource.realmGet$period());
        realmObjectTarget.realmSet$repeat(realmObjectSource.realmGet$repeat());
        CarePlanReminder carePlanReminderObj = realmObjectSource.realmGet$carePlanReminder();
        if (carePlanReminderObj == null) {
            realmObjectTarget.realmSet$carePlanReminder(null);
        } else {
            CarePlanReminder cachecarePlanReminder = (CarePlanReminder) cache.get(carePlanReminderObj);
            if (cachecarePlanReminder != null) {
                realmObjectTarget.realmSet$carePlanReminder(cachecarePlanReminder);
            } else {
                realmObjectTarget.realmSet$carePlanReminder(CarePlanReminderRealmProxy.copyOrUpdate(realm, carePlanReminderObj, true, cache));
            }
        }
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Feature = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
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
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{properties:");
        stringBuilder.append(realmGet$properties() != null ? "CommonFeature" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{alert:");
        stringBuilder.append(realmGet$alert() != null ? "Alert" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{url:");
        stringBuilder.append(realmGet$url() != null ? realmGet$url() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{frequency:");
        stringBuilder.append(realmGet$frequency() != null ? realmGet$frequency() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{startTime:");
        stringBuilder.append(realmGet$startTime());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{endTime:");
        stringBuilder.append(realmGet$endTime());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{period:");
        stringBuilder.append(realmGet$period() != null ? realmGet$period() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{repeat:");
        stringBuilder.append(realmGet$repeat() != null ? realmGet$repeat() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{carePlanReminder:");
        stringBuilder.append(realmGet$carePlanReminder() != null ? "CarePlanReminder" : "null");
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
        FeatureRealmProxy aFeature = (FeatureRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aFeature.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFeature.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aFeature.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
