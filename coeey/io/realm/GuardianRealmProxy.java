package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.vo.Allergy;
import com.cooey.common.vo.Guardian;
import com.cooey.common.vo.Height;
import com.cooey.common.vo.HipSize;
import com.cooey.common.vo.UserSettings;
import com.cooey.common.vo.WaistSize;
import com.cooey.devices.helpers.CooeySQLHelper;
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

public class GuardianRealmProxy extends Guardian implements RealmObjectProxy, GuardianRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RealmList<Allergy> allergiresRealmList;
    private GuardianColumnInfo columnInfo;
    private ProxyState<Guardian> proxyState;

    static final class GuardianColumnInfo extends ColumnInfo {
        long alarmIdIndex;
        long allergiresIndex;
        long caretakerIdIndex;
        long countryIndex;
        long dateOfBirthIndex;
        long emailIndex;
        long firstNameIndex;
        long genderIndex;
        long governmentIdIndex;
        long groupIdIndex;
        long heightIndex;
        long hipSizeIndex;
        long idIndex;
        long lastNameIndex;
        long mobileIndex;
        long oauthIdIndex;
        long oauthTokenIndex;
        long passwordIndex;
        long passwordResetEnabledIndex;
        long patientIdIndex;
        long publicIdIndex;
        long roomIndex;
        long tenantIdIndex;
        long typeIndex;
        long userSettingsIndex;
        long waistSizeIndex;

        GuardianColumnInfo(OsSchemaInfo schemaInfo) {
            super(26);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Guardian");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.firstNameIndex = addColumnDetails("firstName", objectSchemaInfo);
            this.lastNameIndex = addColumnDetails("lastName", objectSchemaInfo);
            this.dateOfBirthIndex = addColumnDetails("dateOfBirth", objectSchemaInfo);
            this.emailIndex = addColumnDetails("email", objectSchemaInfo);
            this.passwordIndex = addColumnDetails("password", objectSchemaInfo);
            this.roomIndex = addColumnDetails("room", objectSchemaInfo);
            this.governmentIdIndex = addColumnDetails("governmentId", objectSchemaInfo);
            this.heightIndex = addColumnDetails("height", objectSchemaInfo);
            this.waistSizeIndex = addColumnDetails("waistSize", objectSchemaInfo);
            this.hipSizeIndex = addColumnDetails("hipSize", objectSchemaInfo);
            this.patientIdIndex = addColumnDetails("patientId", objectSchemaInfo);
            this.countryIndex = addColumnDetails(CooeySQLHelper.COLUMN_COUNTRY, objectSchemaInfo);
            this.mobileIndex = addColumnDetails("mobile", objectSchemaInfo);
            this.genderIndex = addColumnDetails("gender", objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.userSettingsIndex = addColumnDetails("userSettings", objectSchemaInfo);
            this.publicIdIndex = addColumnDetails("publicId", objectSchemaInfo);
            this.groupIdIndex = addColumnDetails(CTConstants.GROUP_ID, objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.caretakerIdIndex = addColumnDetails("caretakerId", objectSchemaInfo);
            this.alarmIdIndex = addColumnDetails("alarmId", objectSchemaInfo);
            this.allergiresIndex = addColumnDetails("allergires", objectSchemaInfo);
            this.passwordResetEnabledIndex = addColumnDetails("passwordResetEnabled", objectSchemaInfo);
            this.oauthIdIndex = addColumnDetails("oauthId", objectSchemaInfo);
            this.oauthTokenIndex = addColumnDetails("oauthToken", objectSchemaInfo);
        }

        GuardianColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new GuardianColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            GuardianColumnInfo src = (GuardianColumnInfo) rawSrc;
            GuardianColumnInfo dst = (GuardianColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.firstNameIndex = src.firstNameIndex;
            dst.lastNameIndex = src.lastNameIndex;
            dst.dateOfBirthIndex = src.dateOfBirthIndex;
            dst.emailIndex = src.emailIndex;
            dst.passwordIndex = src.passwordIndex;
            dst.roomIndex = src.roomIndex;
            dst.governmentIdIndex = src.governmentIdIndex;
            dst.heightIndex = src.heightIndex;
            dst.waistSizeIndex = src.waistSizeIndex;
            dst.hipSizeIndex = src.hipSizeIndex;
            dst.patientIdIndex = src.patientIdIndex;
            dst.countryIndex = src.countryIndex;
            dst.mobileIndex = src.mobileIndex;
            dst.genderIndex = src.genderIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.userSettingsIndex = src.userSettingsIndex;
            dst.publicIdIndex = src.publicIdIndex;
            dst.groupIdIndex = src.groupIdIndex;
            dst.typeIndex = src.typeIndex;
            dst.caretakerIdIndex = src.caretakerIdIndex;
            dst.alarmIdIndex = src.alarmIdIndex;
            dst.allergiresIndex = src.allergiresIndex;
            dst.passwordResetEnabledIndex = src.passwordResetEnabledIndex;
            dst.oauthIdIndex = src.oauthIdIndex;
            dst.oauthTokenIndex = src.oauthTokenIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("firstName");
        fieldNames.add("lastName");
        fieldNames.add("dateOfBirth");
        fieldNames.add("email");
        fieldNames.add("password");
        fieldNames.add("room");
        fieldNames.add("governmentId");
        fieldNames.add("height");
        fieldNames.add("waistSize");
        fieldNames.add("hipSize");
        fieldNames.add("patientId");
        fieldNames.add(CooeySQLHelper.COLUMN_COUNTRY);
        fieldNames.add("mobile");
        fieldNames.add("gender");
        fieldNames.add("tenantId");
        fieldNames.add("userSettings");
        fieldNames.add("publicId");
        fieldNames.add(CTConstants.GROUP_ID);
        fieldNames.add("type");
        fieldNames.add("caretakerId");
        fieldNames.add("alarmId");
        fieldNames.add("allergires");
        fieldNames.add("passwordResetEnabled");
        fieldNames.add("oauthId");
        fieldNames.add("oauthToken");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    GuardianRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (GuardianColumnInfo) context.getColumnInfo();
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

    public String realmGet$firstName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.firstNameIndex);
    }

    public void realmSet$firstName(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.firstNameIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.firstNameIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.firstNameIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.firstNameIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$lastName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.lastNameIndex);
    }

    public void realmSet$lastName(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.lastNameIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.lastNameIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.lastNameIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.lastNameIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$dateOfBirth() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.dateOfBirthIndex);
    }

    public void realmSet$dateOfBirth(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.dateOfBirthIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.dateOfBirthIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.dateOfBirthIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.dateOfBirthIndex, row.getIndex(), value, true);
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

    public String realmGet$password() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.passwordIndex);
    }

    public void realmSet$password(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.passwordIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.passwordIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.passwordIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.passwordIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$room() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.roomIndex);
    }

    public void realmSet$room(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.roomIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.roomIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.roomIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.roomIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$governmentId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.governmentIdIndex);
    }

    public void realmSet$governmentId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.governmentIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.governmentIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.governmentIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.governmentIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public Height realmGet$height() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.heightIndex)) {
            return null;
        }
        return (Height) this.proxyState.getRealm$realm().get(Height.class, this.proxyState.getRow$realm().getLink(this.columnInfo.heightIndex), false, Collections.emptyList());
    }

    public void realmSet$height(Height value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.heightIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.heightIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("height")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Height) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.heightIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.heightIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public WaistSize realmGet$waistSize() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.waistSizeIndex)) {
            return null;
        }
        return (WaistSize) this.proxyState.getRealm$realm().get(WaistSize.class, this.proxyState.getRow$realm().getLink(this.columnInfo.waistSizeIndex), false, Collections.emptyList());
    }

    public void realmSet$waistSize(WaistSize value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.waistSizeIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.waistSizeIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("waistSize")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (WaistSize) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.waistSizeIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.waistSizeIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public HipSize realmGet$hipSize() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.hipSizeIndex)) {
            return null;
        }
        return (HipSize) this.proxyState.getRealm$realm().get(HipSize.class, this.proxyState.getRow$realm().getLink(this.columnInfo.hipSizeIndex), false, Collections.emptyList());
    }

    public void realmSet$hipSize(HipSize value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.hipSizeIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.hipSizeIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("hipSize")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (HipSize) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.hipSizeIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.hipSizeIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
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

    public String realmGet$country() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.countryIndex);
    }

    public void realmSet$country(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.countryIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.countryIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.countryIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.countryIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$mobile() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.mobileIndex);
    }

    public void realmSet$mobile(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.mobileIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.mobileIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.mobileIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.mobileIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$gender() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.genderIndex);
    }

    public void realmSet$gender(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.genderIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.genderIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.genderIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.genderIndex, row.getIndex(), value, true);
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

    public UserSettings realmGet$userSettings() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.userSettingsIndex)) {
            return null;
        }
        return (UserSettings) this.proxyState.getRealm$realm().get(UserSettings.class, this.proxyState.getRow$realm().getLink(this.columnInfo.userSettingsIndex), false, Collections.emptyList());
    }

    public void realmSet$userSettings(UserSettings value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.userSettingsIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.userSettingsIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("userSettings")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (UserSettings) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.userSettingsIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.userSettingsIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Long realmGet$publicId() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNull(this.columnInfo.publicIdIndex)) {
            return null;
        }
        return Long.valueOf(this.proxyState.getRow$realm().getLong(this.columnInfo.publicIdIndex));
    }

    public void realmSet$publicId(Long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.publicIdIndex);
            } else {
                this.proxyState.getRow$realm().setLong(this.columnInfo.publicIdIndex, value.longValue());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.publicIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setLong(this.columnInfo.publicIdIndex, row.getIndex(), value.longValue(), true);
            }
        }
    }

    public String realmGet$groupId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.groupIdIndex);
    }

    public void realmSet$groupId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.groupIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.groupIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.groupIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.groupIdIndex, row.getIndex(), value, true);
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

    public String realmGet$caretakerId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.caretakerIdIndex);
    }

    public void realmSet$caretakerId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.caretakerIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.caretakerIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.caretakerIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.caretakerIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$alarmId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.alarmIdIndex);
    }

    public void realmSet$alarmId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.alarmIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.alarmIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.alarmIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.alarmIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public RealmList<Allergy> realmGet$allergires() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.allergiresRealmList != null) {
            return this.allergiresRealmList;
        }
        this.allergiresRealmList = new RealmList(Allergy.class, this.proxyState.getRow$realm().getLinkList(this.columnInfo.allergiresIndex), this.proxyState.getRealm$realm());
        return this.allergiresRealmList;
    }

    public void realmSet$allergires(RealmList<Allergy> value) {
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("allergires")) {
                return;
            }
            if (!(value == null || value.isManaged())) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<Allergy> original = value;
                value = new RealmList();
                Iterator it = original.iterator();
                while (it.hasNext()) {
                    Allergy item = (Allergy) it.next();
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList osList = this.proxyState.getRow$realm().getLinkList(this.columnInfo.allergiresIndex);
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

    public Boolean realmGet$passwordResetEnabled() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNull(this.columnInfo.passwordResetEnabledIndex)) {
            return null;
        }
        return Boolean.valueOf(this.proxyState.getRow$realm().getBoolean(this.columnInfo.passwordResetEnabledIndex));
    }

    public void realmSet$passwordResetEnabled(Boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.passwordResetEnabledIndex);
            } else {
                this.proxyState.getRow$realm().setBoolean(this.columnInfo.passwordResetEnabledIndex, value.booleanValue());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.passwordResetEnabledIndex, row.getIndex(), true);
            } else {
                row.getTable().setBoolean(this.columnInfo.passwordResetEnabledIndex, row.getIndex(), value.booleanValue(), true);
            }
        }
    }

    public String realmGet$oauthId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.oauthIdIndex);
    }

    public void realmSet$oauthId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.oauthIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.oauthIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.oauthIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.oauthIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$oauthToken() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.oauthTokenIndex);
    }

    public void realmSet$oauthToken(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.oauthTokenIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.oauthTokenIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.oauthTokenIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.oauthTokenIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Guardian");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("firstName", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("lastName", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("dateOfBirth", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("email", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("password", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("room", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("governmentId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("height", RealmFieldType.OBJECT, "Height");
        builder.addPersistedLinkProperty("waistSize", RealmFieldType.OBJECT, "WaistSize");
        builder.addPersistedLinkProperty("hipSize", RealmFieldType.OBJECT, "HipSize");
        builder.addPersistedProperty("patientId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(CooeySQLHelper.COLUMN_COUNTRY, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("mobile", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("gender", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("userSettings", RealmFieldType.OBJECT, "UserSettings");
        builder.addPersistedProperty("publicId", RealmFieldType.INTEGER, false, false, false);
        builder.addPersistedProperty(CTConstants.GROUP_ID, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("caretakerId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("alarmId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("allergires", RealmFieldType.LIST, "Allergy");
        builder.addPersistedProperty("passwordResetEnabled", RealmFieldType.BOOLEAN, false, false, false);
        builder.addPersistedProperty("oauthId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("oauthToken", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static GuardianColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new GuardianColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Guardian";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Guardian createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(5);
        Guardian guardian = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Guardian.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Guardian.class), false, Collections.emptyList());
                    guardian = new GuardianRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (guardian == null) {
            if (json.has("height")) {
                excludeFields.add("height");
            }
            if (json.has("waistSize")) {
                excludeFields.add("waistSize");
            }
            if (json.has("hipSize")) {
                excludeFields.add("hipSize");
            }
            if (json.has("userSettings")) {
                excludeFields.add("userSettings");
            }
            if (json.has("allergires")) {
                excludeFields.add("allergires");
            }
            if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                    guardian = (GuardianRealmProxy) realm.createObjectInternal(Guardian.class, null, true, excludeFields);
                } else {
                    String string = json.getString(ShareConstants.WEB_DIALOG_PARAM_ID);
                    GuardianRealmProxy obj = (GuardianRealmProxy) realm.createObjectInternal(Guardian.class, string, true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        GuardianRealmProxyInterface objProxy = guardian;
        if (json.has("firstName")) {
            if (json.isNull("firstName")) {
                objProxy.realmSet$firstName(null);
            } else {
                objProxy.realmSet$firstName(json.getString("firstName"));
            }
        }
        if (json.has("lastName")) {
            if (json.isNull("lastName")) {
                objProxy.realmSet$lastName(null);
            } else {
                objProxy.realmSet$lastName(json.getString("lastName"));
            }
        }
        if (json.has("dateOfBirth")) {
            if (json.isNull("dateOfBirth")) {
                objProxy.realmSet$dateOfBirth(null);
            } else {
                objProxy.realmSet$dateOfBirth(json.getString("dateOfBirth"));
            }
        }
        if (json.has("email")) {
            if (json.isNull("email")) {
                objProxy.realmSet$email(null);
            } else {
                objProxy.realmSet$email(json.getString("email"));
            }
        }
        if (json.has("password")) {
            if (json.isNull("password")) {
                objProxy.realmSet$password(null);
            } else {
                objProxy.realmSet$password(json.getString("password"));
            }
        }
        if (json.has("room")) {
            if (json.isNull("room")) {
                objProxy.realmSet$room(null);
            } else {
                objProxy.realmSet$room(json.getString("room"));
            }
        }
        if (json.has("governmentId")) {
            if (json.isNull("governmentId")) {
                objProxy.realmSet$governmentId(null);
            } else {
                objProxy.realmSet$governmentId(json.getString("governmentId"));
            }
        }
        if (json.has("height")) {
            if (json.isNull("height")) {
                objProxy.realmSet$height(null);
            } else {
                objProxy.realmSet$height(HeightRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("height"), update));
            }
        }
        if (json.has("waistSize")) {
            if (json.isNull("waistSize")) {
                objProxy.realmSet$waistSize(null);
            } else {
                objProxy.realmSet$waistSize(WaistSizeRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("waistSize"), update));
            }
        }
        if (json.has("hipSize")) {
            if (json.isNull("hipSize")) {
                objProxy.realmSet$hipSize(null);
            } else {
                objProxy.realmSet$hipSize(HipSizeRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("hipSize"), update));
            }
        }
        if (json.has("patientId")) {
            if (json.isNull("patientId")) {
                objProxy.realmSet$patientId(null);
            } else {
                objProxy.realmSet$patientId(json.getString("patientId"));
            }
        }
        if (json.has(CooeySQLHelper.COLUMN_COUNTRY)) {
            if (json.isNull(CooeySQLHelper.COLUMN_COUNTRY)) {
                objProxy.realmSet$country(null);
            } else {
                objProxy.realmSet$country(json.getString(CooeySQLHelper.COLUMN_COUNTRY));
            }
        }
        if (json.has("mobile")) {
            if (json.isNull("mobile")) {
                objProxy.realmSet$mobile(null);
            } else {
                objProxy.realmSet$mobile(json.getString("mobile"));
            }
        }
        if (json.has("gender")) {
            if (json.isNull("gender")) {
                objProxy.realmSet$gender(null);
            } else {
                objProxy.realmSet$gender(json.getString("gender"));
            }
        }
        if (json.has("tenantId")) {
            if (json.isNull("tenantId")) {
                objProxy.realmSet$tenantId(null);
            } else {
                objProxy.realmSet$tenantId(json.getString("tenantId"));
            }
        }
        if (json.has("userSettings")) {
            if (json.isNull("userSettings")) {
                objProxy.realmSet$userSettings(null);
            } else {
                objProxy.realmSet$userSettings(UserSettingsRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("userSettings"), update));
            }
        }
        if (json.has("publicId")) {
            if (json.isNull("publicId")) {
                objProxy.realmSet$publicId(null);
            } else {
                GuardianRealmProxyInterface guardianRealmProxyInterface = objProxy;
                guardianRealmProxyInterface.realmSet$publicId(Long.valueOf(json.getLong("publicId")));
            }
        }
        if (json.has(CTConstants.GROUP_ID)) {
            if (json.isNull(CTConstants.GROUP_ID)) {
                objProxy.realmSet$groupId(null);
            } else {
                objProxy.realmSet$groupId(json.getString(CTConstants.GROUP_ID));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type(json.getString("type"));
            }
        }
        if (json.has("caretakerId")) {
            if (json.isNull("caretakerId")) {
                objProxy.realmSet$caretakerId(null);
            } else {
                objProxy.realmSet$caretakerId(json.getString("caretakerId"));
            }
        }
        if (json.has("alarmId")) {
            if (json.isNull("alarmId")) {
                objProxy.realmSet$alarmId(null);
            } else {
                objProxy.realmSet$alarmId(json.getString("alarmId"));
            }
        }
        if (json.has("allergires")) {
            if (json.isNull("allergires")) {
                objProxy.realmSet$allergires(null);
            } else {
                objProxy.realmGet$allergires().clear();
                JSONArray array = json.getJSONArray("allergires");
                for (int i = 0; i < array.length(); i++) {
                    objProxy.realmGet$allergires().add(AllergyRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update));
                }
            }
        }
        if (json.has("passwordResetEnabled")) {
            if (json.isNull("passwordResetEnabled")) {
                objProxy.realmSet$passwordResetEnabled(null);
            } else {
                guardianRealmProxyInterface = objProxy;
                guardianRealmProxyInterface.realmSet$passwordResetEnabled(Boolean.valueOf(json.getBoolean("passwordResetEnabled")));
            }
        }
        if (json.has("oauthId")) {
            if (json.isNull("oauthId")) {
                objProxy.realmSet$oauthId(null);
            } else {
                objProxy.realmSet$oauthId(json.getString("oauthId"));
            }
        }
        if (json.has("oauthToken")) {
            if (json.isNull("oauthToken")) {
                objProxy.realmSet$oauthToken(null);
            } else {
                objProxy.realmSet$oauthToken(json.getString("oauthToken"));
            }
        }
        return guardian;
    }

    @TargetApi(11)
    public static Guardian createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Guardian obj = new Guardian();
        GuardianRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("firstName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$firstName(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$firstName(null);
                }
            } else if (name.equals("lastName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$lastName(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$lastName(null);
                }
            } else if (name.equals("dateOfBirth")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$dateOfBirth(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$dateOfBirth(null);
                }
            } else if (name.equals("email")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$email(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$email(null);
                }
            } else if (name.equals("password")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$password(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$password(null);
                }
            } else if (name.equals("room")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$room(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$room(null);
                }
            } else if (name.equals("governmentId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$governmentId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$governmentId(null);
                }
            } else if (name.equals("height")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$height(null);
                } else {
                    objProxy.realmSet$height(HeightRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("waistSize")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$waistSize(null);
                } else {
                    objProxy.realmSet$waistSize(WaistSizeRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("hipSize")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$hipSize(null);
                } else {
                    objProxy.realmSet$hipSize(HipSizeRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("patientId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$patientId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$patientId(null);
                }
            } else if (name.equals(CooeySQLHelper.COLUMN_COUNTRY)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$country(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$country(null);
                }
            } else if (name.equals("mobile")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$mobile(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$mobile(null);
                }
            } else if (name.equals("gender")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$gender(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$gender(null);
                }
            } else if (name.equals("tenantId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tenantId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tenantId(null);
                }
            } else if (name.equals("userSettings")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$userSettings(null);
                } else {
                    objProxy.realmSet$userSettings(UserSettingsRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("publicId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$publicId(Long.valueOf(reader.nextLong()));
                } else {
                    reader.skipValue();
                    objProxy.realmSet$publicId(null);
                }
            } else if (name.equals(CTConstants.GROUP_ID)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$groupId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$groupId(null);
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("caretakerId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$caretakerId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$caretakerId(null);
                }
            } else if (name.equals("alarmId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$alarmId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$alarmId(null);
                }
            } else if (name.equals("allergires")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$allergires(null);
                } else {
                    objProxy.realmSet$allergires(new RealmList());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        objProxy.realmGet$allergires().add(AllergyRealmProxy.createUsingJsonStream(realm, reader));
                    }
                    reader.endArray();
                }
            } else if (name.equals("passwordResetEnabled")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$passwordResetEnabled(Boolean.valueOf(reader.nextBoolean()));
                } else {
                    reader.skipValue();
                    objProxy.realmSet$passwordResetEnabled(null);
                }
            } else if (name.equals("oauthId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$oauthId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$oauthId(null);
                }
            } else if (!name.equals("oauthToken")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$oauthToken(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$oauthToken(null);
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Guardian) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static Guardian copyOrUpdate(Realm realm, Guardian object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Guardian) cachedRealmObject;
        }
        Guardian update2;
        Guardian realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Guardian.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Guardian.class), false, Collections.emptyList());
                    Guardian realmObject2 = new GuardianRealmProxy();
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

    public static Guardian copy(Realm realm, Guardian newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Guardian) cachedRealmObject;
        }
        Guardian realmObject = (Guardian) realm.createObjectInternal(Guardian.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        GuardianRealmProxyInterface realmObjectSource = newObject;
        GuardianRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$firstName(realmObjectSource.realmGet$firstName());
        realmObjectCopy.realmSet$lastName(realmObjectSource.realmGet$lastName());
        realmObjectCopy.realmSet$dateOfBirth(realmObjectSource.realmGet$dateOfBirth());
        realmObjectCopy.realmSet$email(realmObjectSource.realmGet$email());
        realmObjectCopy.realmSet$password(realmObjectSource.realmGet$password());
        realmObjectCopy.realmSet$room(realmObjectSource.realmGet$room());
        realmObjectCopy.realmSet$governmentId(realmObjectSource.realmGet$governmentId());
        Height heightObj = realmObjectSource.realmGet$height();
        if (heightObj == null) {
            realmObjectCopy.realmSet$height(null);
        } else {
            Height cacheheight = (Height) cache.get(heightObj);
            if (cacheheight != null) {
                realmObjectCopy.realmSet$height(cacheheight);
            } else {
                realmObjectCopy.realmSet$height(HeightRealmProxy.copyOrUpdate(realm, heightObj, update, cache));
            }
        }
        WaistSize waistSizeObj = realmObjectSource.realmGet$waistSize();
        if (waistSizeObj == null) {
            realmObjectCopy.realmSet$waistSize(null);
        } else {
            WaistSize cachewaistSize = (WaistSize) cache.get(waistSizeObj);
            if (cachewaistSize != null) {
                realmObjectCopy.realmSet$waistSize(cachewaistSize);
            } else {
                realmObjectCopy.realmSet$waistSize(WaistSizeRealmProxy.copyOrUpdate(realm, waistSizeObj, update, cache));
            }
        }
        HipSize hipSizeObj = realmObjectSource.realmGet$hipSize();
        if (hipSizeObj == null) {
            realmObjectCopy.realmSet$hipSize(null);
        } else {
            HipSize cachehipSize = (HipSize) cache.get(hipSizeObj);
            if (cachehipSize != null) {
                realmObjectCopy.realmSet$hipSize(cachehipSize);
            } else {
                realmObjectCopy.realmSet$hipSize(HipSizeRealmProxy.copyOrUpdate(realm, hipSizeObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$patientId(realmObjectSource.realmGet$patientId());
        realmObjectCopy.realmSet$country(realmObjectSource.realmGet$country());
        realmObjectCopy.realmSet$mobile(realmObjectSource.realmGet$mobile());
        realmObjectCopy.realmSet$gender(realmObjectSource.realmGet$gender());
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        UserSettings userSettingsObj = realmObjectSource.realmGet$userSettings();
        if (userSettingsObj == null) {
            realmObjectCopy.realmSet$userSettings(null);
        } else {
            UserSettings cacheuserSettings = (UserSettings) cache.get(userSettingsObj);
            if (cacheuserSettings != null) {
                realmObjectCopy.realmSet$userSettings(cacheuserSettings);
            } else {
                realmObjectCopy.realmSet$userSettings(UserSettingsRealmProxy.copyOrUpdate(realm, userSettingsObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$publicId(realmObjectSource.realmGet$publicId());
        realmObjectCopy.realmSet$groupId(realmObjectSource.realmGet$groupId());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$caretakerId(realmObjectSource.realmGet$caretakerId());
        realmObjectCopy.realmSet$alarmId(realmObjectSource.realmGet$alarmId());
        RealmList<Allergy> allergiresList = realmObjectSource.realmGet$allergires();
        if (allergiresList != null) {
            RealmList<Allergy> allergiresRealmList = realmObjectCopy.realmGet$allergires();
            allergiresRealmList.clear();
            for (int i = 0; i < allergiresList.size(); i++) {
                Allergy allergiresItem = (Allergy) allergiresList.get(i);
                Allergy cacheallergires = (Allergy) cache.get(allergiresItem);
                if (cacheallergires != null) {
                    allergiresRealmList.add(cacheallergires);
                } else {
                    allergiresRealmList.add(AllergyRealmProxy.copyOrUpdate(realm, allergiresItem, update, cache));
                }
            }
        }
        realmObjectCopy.realmSet$passwordResetEnabled(realmObjectSource.realmGet$passwordResetEnabled());
        realmObjectCopy.realmSet$oauthId(realmObjectSource.realmGet$oauthId());
        realmObjectCopy.realmSet$oauthToken(realmObjectSource.realmGet$oauthToken());
        return realmObject;
    }

    public static long insert(Realm realm, Guardian object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Guardian.class);
        long tableNativePtr = table.getNativePtr();
        GuardianColumnInfo columnInfo = (GuardianColumnInfo) realm.getSchema().getColumnInfo(Guardian.class);
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
        String realmGet$firstName = object.realmGet$firstName();
        if (realmGet$firstName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.firstNameIndex, rowIndex, realmGet$firstName, false);
        }
        String realmGet$lastName = object.realmGet$lastName();
        if (realmGet$lastName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.lastNameIndex, rowIndex, realmGet$lastName, false);
        }
        String realmGet$dateOfBirth = object.realmGet$dateOfBirth();
        if (realmGet$dateOfBirth != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateOfBirthIndex, rowIndex, realmGet$dateOfBirth, false);
        }
        String realmGet$email = object.realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
        }
        String realmGet$password = object.realmGet$password();
        if (realmGet$password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password, false);
        }
        String realmGet$room = object.realmGet$room();
        if (realmGet$room != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.roomIndex, rowIndex, realmGet$room, false);
        }
        String realmGet$governmentId = object.realmGet$governmentId();
        if (realmGet$governmentId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.governmentIdIndex, rowIndex, realmGet$governmentId, false);
        }
        Height heightObj = object.realmGet$height();
        if (heightObj != null) {
            Long cacheheight = (Long) cache.get(heightObj);
            if (cacheheight == null) {
                cacheheight = Long.valueOf(HeightRealmProxy.insert(realm, heightObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.heightIndex, rowIndex, cacheheight.longValue(), false);
        }
        WaistSize waistSizeObj = object.realmGet$waistSize();
        if (waistSizeObj != null) {
            Long cachewaistSize = (Long) cache.get(waistSizeObj);
            if (cachewaistSize == null) {
                cachewaistSize = Long.valueOf(WaistSizeRealmProxy.insert(realm, waistSizeObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.waistSizeIndex, rowIndex, cachewaistSize.longValue(), false);
        }
        HipSize hipSizeObj = object.realmGet$hipSize();
        if (hipSizeObj != null) {
            Long cachehipSize = (Long) cache.get(hipSizeObj);
            if (cachehipSize == null) {
                cachehipSize = Long.valueOf(HipSizeRealmProxy.insert(realm, hipSizeObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.hipSizeIndex, rowIndex, cachehipSize.longValue(), false);
        }
        String realmGet$patientId = object.realmGet$patientId();
        if (realmGet$patientId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
        }
        String realmGet$country = object.realmGet$country();
        if (realmGet$country != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.countryIndex, rowIndex, realmGet$country, false);
        }
        String realmGet$mobile = object.realmGet$mobile();
        if (realmGet$mobile != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mobileIndex, rowIndex, realmGet$mobile, false);
        }
        String realmGet$gender = object.realmGet$gender();
        if (realmGet$gender != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
        }
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        }
        UserSettings userSettingsObj = object.realmGet$userSettings();
        if (userSettingsObj != null) {
            Long cacheuserSettings = (Long) cache.get(userSettingsObj);
            if (cacheuserSettings == null) {
                cacheuserSettings = Long.valueOf(UserSettingsRealmProxy.insert(realm, userSettingsObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.userSettingsIndex, rowIndex, cacheuserSettings.longValue(), false);
        }
        Number realmGet$publicId = object.realmGet$publicId();
        if (realmGet$publicId != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.publicIdIndex, rowIndex, realmGet$publicId.longValue(), false);
        }
        String realmGet$groupId = object.realmGet$groupId();
        if (realmGet$groupId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.groupIdIndex, rowIndex, realmGet$groupId, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }
        String realmGet$caretakerId = object.realmGet$caretakerId();
        if (realmGet$caretakerId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.caretakerIdIndex, rowIndex, realmGet$caretakerId, false);
        }
        String realmGet$alarmId = object.realmGet$alarmId();
        if (realmGet$alarmId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.alarmIdIndex, rowIndex, realmGet$alarmId, false);
        }
        RealmList<Allergy> allergiresList = object.realmGet$allergires();
        if (allergiresList != null) {
            OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.allergiresIndex);
            Iterator it = allergiresList.iterator();
            while (it.hasNext()) {
                Allergy allergiresItem = (Allergy) it.next();
                Long cacheItemIndexallergires = (Long) cache.get(allergiresItem);
                if (cacheItemIndexallergires == null) {
                    cacheItemIndexallergires = Long.valueOf(AllergyRealmProxy.insert(realm, allergiresItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexallergires.longValue());
            }
        }
        Boolean realmGet$passwordResetEnabled = object.realmGet$passwordResetEnabled();
        if (realmGet$passwordResetEnabled != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.passwordResetEnabledIndex, rowIndex, realmGet$passwordResetEnabled.booleanValue(), false);
        }
        String realmGet$oauthId = object.realmGet$oauthId();
        if (realmGet$oauthId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.oauthIdIndex, rowIndex, realmGet$oauthId, false);
        }
        String realmGet$oauthToken = object.realmGet$oauthToken();
        if (realmGet$oauthToken == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.oauthTokenIndex, rowIndex, realmGet$oauthToken, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Guardian.class);
        long tableNativePtr = table.getNativePtr();
        GuardianColumnInfo columnInfo = (GuardianColumnInfo) realm.getSchema().getColumnInfo(Guardian.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Guardian object = (Guardian) objects.next();
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
                    String realmGet$firstName = object.realmGet$firstName();
                    if (realmGet$firstName != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.firstNameIndex, rowIndex, realmGet$firstName, false);
                    }
                    String realmGet$lastName = object.realmGet$lastName();
                    if (realmGet$lastName != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.lastNameIndex, rowIndex, realmGet$lastName, false);
                    }
                    String realmGet$dateOfBirth = object.realmGet$dateOfBirth();
                    if (realmGet$dateOfBirth != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.dateOfBirthIndex, rowIndex, realmGet$dateOfBirth, false);
                    }
                    String realmGet$email = object.realmGet$email();
                    if (realmGet$email != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
                    }
                    String realmGet$password = object.realmGet$password();
                    if (realmGet$password != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password, false);
                    }
                    String realmGet$room = object.realmGet$room();
                    if (realmGet$room != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.roomIndex, rowIndex, realmGet$room, false);
                    }
                    String realmGet$governmentId = object.realmGet$governmentId();
                    if (realmGet$governmentId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.governmentIdIndex, rowIndex, realmGet$governmentId, false);
                    }
                    Height heightObj = object.realmGet$height();
                    if (heightObj != null) {
                        Long cacheheight = (Long) cache.get(heightObj);
                        if (cacheheight == null) {
                            cacheheight = Long.valueOf(HeightRealmProxy.insert(realm, heightObj, (Map) cache));
                        }
                        table.setLink(columnInfo.heightIndex, rowIndex, cacheheight.longValue(), false);
                    }
                    WaistSize waistSizeObj = object.realmGet$waistSize();
                    if (waistSizeObj != null) {
                        Long cachewaistSize = (Long) cache.get(waistSizeObj);
                        if (cachewaistSize == null) {
                            cachewaistSize = Long.valueOf(WaistSizeRealmProxy.insert(realm, waistSizeObj, (Map) cache));
                        }
                        table.setLink(columnInfo.waistSizeIndex, rowIndex, cachewaistSize.longValue(), false);
                    }
                    HipSize hipSizeObj = object.realmGet$hipSize();
                    if (hipSizeObj != null) {
                        Long cachehipSize = (Long) cache.get(hipSizeObj);
                        if (cachehipSize == null) {
                            cachehipSize = Long.valueOf(HipSizeRealmProxy.insert(realm, hipSizeObj, (Map) cache));
                        }
                        table.setLink(columnInfo.hipSizeIndex, rowIndex, cachehipSize.longValue(), false);
                    }
                    String realmGet$patientId = object.realmGet$patientId();
                    if (realmGet$patientId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
                    }
                    String realmGet$country = object.realmGet$country();
                    if (realmGet$country != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.countryIndex, rowIndex, realmGet$country, false);
                    }
                    String realmGet$mobile = object.realmGet$mobile();
                    if (realmGet$mobile != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.mobileIndex, rowIndex, realmGet$mobile, false);
                    }
                    String realmGet$gender = object.realmGet$gender();
                    if (realmGet$gender != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
                    }
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                    UserSettings userSettingsObj = object.realmGet$userSettings();
                    if (userSettingsObj != null) {
                        Long cacheuserSettings = (Long) cache.get(userSettingsObj);
                        if (cacheuserSettings == null) {
                            cacheuserSettings = Long.valueOf(UserSettingsRealmProxy.insert(realm, userSettingsObj, (Map) cache));
                        }
                        table.setLink(columnInfo.userSettingsIndex, rowIndex, cacheuserSettings.longValue(), false);
                    }
                    Number realmGet$publicId = object.realmGet$publicId();
                    if (realmGet$publicId != null) {
                        Table.nativeSetLong(tableNativePtr, columnInfo.publicIdIndex, rowIndex, realmGet$publicId.longValue(), false);
                    }
                    String realmGet$groupId = object.realmGet$groupId();
                    if (realmGet$groupId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.groupIdIndex, rowIndex, realmGet$groupId, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    }
                    String realmGet$caretakerId = object.realmGet$caretakerId();
                    if (realmGet$caretakerId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.caretakerIdIndex, rowIndex, realmGet$caretakerId, false);
                    }
                    String realmGet$alarmId = object.realmGet$alarmId();
                    if (realmGet$alarmId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.alarmIdIndex, rowIndex, realmGet$alarmId, false);
                    }
                    RealmList<Allergy> allergiresList = object.realmGet$allergires();
                    if (allergiresList != null) {
                        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.allergiresIndex);
                        Iterator it = allergiresList.iterator();
                        while (it.hasNext()) {
                            Allergy allergiresItem = (Allergy) it.next();
                            Long cacheItemIndexallergires = (Long) cache.get(allergiresItem);
                            if (cacheItemIndexallergires == null) {
                                cacheItemIndexallergires = Long.valueOf(AllergyRealmProxy.insert(realm, allergiresItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexallergires.longValue());
                        }
                    }
                    Boolean realmGet$passwordResetEnabled = object.realmGet$passwordResetEnabled();
                    if (realmGet$passwordResetEnabled != null) {
                        Table.nativeSetBoolean(tableNativePtr, columnInfo.passwordResetEnabledIndex, rowIndex, realmGet$passwordResetEnabled.booleanValue(), false);
                    }
                    String realmGet$oauthId = object.realmGet$oauthId();
                    if (realmGet$oauthId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.oauthIdIndex, rowIndex, realmGet$oauthId, false);
                    }
                    String realmGet$oauthToken = object.realmGet$oauthToken();
                    if (realmGet$oauthToken != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.oauthTokenIndex, rowIndex, realmGet$oauthToken, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Guardian object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Guardian.class);
        long tableNativePtr = table.getNativePtr();
        GuardianColumnInfo columnInfo = (GuardianColumnInfo) realm.getSchema().getColumnInfo(Guardian.class);
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
        String realmGet$firstName = object.realmGet$firstName();
        if (realmGet$firstName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.firstNameIndex, rowIndex, realmGet$firstName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.firstNameIndex, rowIndex, false);
        }
        String realmGet$lastName = object.realmGet$lastName();
        if (realmGet$lastName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.lastNameIndex, rowIndex, realmGet$lastName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.lastNameIndex, rowIndex, false);
        }
        String realmGet$dateOfBirth = object.realmGet$dateOfBirth();
        if (realmGet$dateOfBirth != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateOfBirthIndex, rowIndex, realmGet$dateOfBirth, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateOfBirthIndex, rowIndex, false);
        }
        String realmGet$email = object.realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.emailIndex, rowIndex, false);
        }
        String realmGet$password = object.realmGet$password();
        if (realmGet$password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.passwordIndex, rowIndex, false);
        }
        String realmGet$room = object.realmGet$room();
        if (realmGet$room != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.roomIndex, rowIndex, realmGet$room, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.roomIndex, rowIndex, false);
        }
        String realmGet$governmentId = object.realmGet$governmentId();
        if (realmGet$governmentId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.governmentIdIndex, rowIndex, realmGet$governmentId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.governmentIdIndex, rowIndex, false);
        }
        Height heightObj = object.realmGet$height();
        if (heightObj != null) {
            Long cacheheight = (Long) cache.get(heightObj);
            if (cacheheight == null) {
                cacheheight = Long.valueOf(HeightRealmProxy.insertOrUpdate(realm, heightObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.heightIndex, rowIndex, cacheheight.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.heightIndex, rowIndex);
        }
        WaistSize waistSizeObj = object.realmGet$waistSize();
        if (waistSizeObj != null) {
            Long cachewaistSize = (Long) cache.get(waistSizeObj);
            if (cachewaistSize == null) {
                cachewaistSize = Long.valueOf(WaistSizeRealmProxy.insertOrUpdate(realm, waistSizeObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.waistSizeIndex, rowIndex, cachewaistSize.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.waistSizeIndex, rowIndex);
        }
        HipSize hipSizeObj = object.realmGet$hipSize();
        if (hipSizeObj != null) {
            Long cachehipSize = (Long) cache.get(hipSizeObj);
            if (cachehipSize == null) {
                cachehipSize = Long.valueOf(HipSizeRealmProxy.insertOrUpdate(realm, hipSizeObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.hipSizeIndex, rowIndex, cachehipSize.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.hipSizeIndex, rowIndex);
        }
        String realmGet$patientId = object.realmGet$patientId();
        if (realmGet$patientId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.patientIdIndex, rowIndex, false);
        }
        String realmGet$country = object.realmGet$country();
        if (realmGet$country != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.countryIndex, rowIndex, realmGet$country, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.countryIndex, rowIndex, false);
        }
        String realmGet$mobile = object.realmGet$mobile();
        if (realmGet$mobile != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mobileIndex, rowIndex, realmGet$mobile, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mobileIndex, rowIndex, false);
        }
        String realmGet$gender = object.realmGet$gender();
        if (realmGet$gender != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.genderIndex, rowIndex, false);
        }
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
        }
        UserSettings userSettingsObj = object.realmGet$userSettings();
        if (userSettingsObj != null) {
            Long cacheuserSettings = (Long) cache.get(userSettingsObj);
            if (cacheuserSettings == null) {
                cacheuserSettings = Long.valueOf(UserSettingsRealmProxy.insertOrUpdate(realm, userSettingsObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.userSettingsIndex, rowIndex, cacheuserSettings.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.userSettingsIndex, rowIndex);
        }
        Number realmGet$publicId = object.realmGet$publicId();
        if (realmGet$publicId != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.publicIdIndex, rowIndex, realmGet$publicId.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.publicIdIndex, rowIndex, false);
        }
        String realmGet$groupId = object.realmGet$groupId();
        if (realmGet$groupId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.groupIdIndex, rowIndex, realmGet$groupId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.groupIdIndex, rowIndex, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        }
        String realmGet$caretakerId = object.realmGet$caretakerId();
        if (realmGet$caretakerId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.caretakerIdIndex, rowIndex, realmGet$caretakerId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.caretakerIdIndex, rowIndex, false);
        }
        String realmGet$alarmId = object.realmGet$alarmId();
        if (realmGet$alarmId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.alarmIdIndex, rowIndex, realmGet$alarmId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.alarmIdIndex, rowIndex, false);
        }
        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.allergiresIndex);
        osList.removeAll();
        RealmList<Allergy> allergiresList = object.realmGet$allergires();
        if (allergiresList != null) {
            Iterator it = allergiresList.iterator();
            while (it.hasNext()) {
                Allergy allergiresItem = (Allergy) it.next();
                Long cacheItemIndexallergires = (Long) cache.get(allergiresItem);
                if (cacheItemIndexallergires == null) {
                    cacheItemIndexallergires = Long.valueOf(AllergyRealmProxy.insertOrUpdate(realm, allergiresItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexallergires.longValue());
            }
        }
        Boolean realmGet$passwordResetEnabled = object.realmGet$passwordResetEnabled();
        if (realmGet$passwordResetEnabled != null) {
            Table.nativeSetBoolean(tableNativePtr, columnInfo.passwordResetEnabledIndex, rowIndex, realmGet$passwordResetEnabled.booleanValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.passwordResetEnabledIndex, rowIndex, false);
        }
        String realmGet$oauthId = object.realmGet$oauthId();
        if (realmGet$oauthId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.oauthIdIndex, rowIndex, realmGet$oauthId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.oauthIdIndex, rowIndex, false);
        }
        String realmGet$oauthToken = object.realmGet$oauthToken();
        if (realmGet$oauthToken != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.oauthTokenIndex, rowIndex, realmGet$oauthToken, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.oauthTokenIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Guardian.class);
        long tableNativePtr = table.getNativePtr();
        GuardianColumnInfo columnInfo = (GuardianColumnInfo) realm.getSchema().getColumnInfo(Guardian.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Guardian object = (Guardian) objects.next();
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
                    String realmGet$firstName = object.realmGet$firstName();
                    if (realmGet$firstName != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.firstNameIndex, rowIndex, realmGet$firstName, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.firstNameIndex, rowIndex, false);
                    }
                    String realmGet$lastName = object.realmGet$lastName();
                    if (realmGet$lastName != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.lastNameIndex, rowIndex, realmGet$lastName, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.lastNameIndex, rowIndex, false);
                    }
                    String realmGet$dateOfBirth = object.realmGet$dateOfBirth();
                    if (realmGet$dateOfBirth != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.dateOfBirthIndex, rowIndex, realmGet$dateOfBirth, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.dateOfBirthIndex, rowIndex, false);
                    }
                    String realmGet$email = object.realmGet$email();
                    if (realmGet$email != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.emailIndex, rowIndex, realmGet$email, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.emailIndex, rowIndex, false);
                    }
                    String realmGet$password = object.realmGet$password();
                    if (realmGet$password != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.passwordIndex, rowIndex, realmGet$password, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.passwordIndex, rowIndex, false);
                    }
                    String realmGet$room = object.realmGet$room();
                    if (realmGet$room != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.roomIndex, rowIndex, realmGet$room, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.roomIndex, rowIndex, false);
                    }
                    String realmGet$governmentId = object.realmGet$governmentId();
                    if (realmGet$governmentId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.governmentIdIndex, rowIndex, realmGet$governmentId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.governmentIdIndex, rowIndex, false);
                    }
                    Height heightObj = object.realmGet$height();
                    if (heightObj != null) {
                        Long cacheheight = (Long) cache.get(heightObj);
                        if (cacheheight == null) {
                            cacheheight = Long.valueOf(HeightRealmProxy.insertOrUpdate(realm, heightObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.heightIndex, rowIndex, cacheheight.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.heightIndex, rowIndex);
                    }
                    WaistSize waistSizeObj = object.realmGet$waistSize();
                    if (waistSizeObj != null) {
                        Long cachewaistSize = (Long) cache.get(waistSizeObj);
                        if (cachewaistSize == null) {
                            cachewaistSize = Long.valueOf(WaistSizeRealmProxy.insertOrUpdate(realm, waistSizeObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.waistSizeIndex, rowIndex, cachewaistSize.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.waistSizeIndex, rowIndex);
                    }
                    HipSize hipSizeObj = object.realmGet$hipSize();
                    if (hipSizeObj != null) {
                        Long cachehipSize = (Long) cache.get(hipSizeObj);
                        if (cachehipSize == null) {
                            cachehipSize = Long.valueOf(HipSizeRealmProxy.insertOrUpdate(realm, hipSizeObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.hipSizeIndex, rowIndex, cachehipSize.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.hipSizeIndex, rowIndex);
                    }
                    String realmGet$patientId = object.realmGet$patientId();
                    if (realmGet$patientId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.patientIdIndex, rowIndex, realmGet$patientId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.patientIdIndex, rowIndex, false);
                    }
                    String realmGet$country = object.realmGet$country();
                    if (realmGet$country != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.countryIndex, rowIndex, realmGet$country, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.countryIndex, rowIndex, false);
                    }
                    String realmGet$mobile = object.realmGet$mobile();
                    if (realmGet$mobile != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.mobileIndex, rowIndex, realmGet$mobile, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.mobileIndex, rowIndex, false);
                    }
                    String realmGet$gender = object.realmGet$gender();
                    if (realmGet$gender != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.genderIndex, rowIndex, false);
                    }
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
                    }
                    UserSettings userSettingsObj = object.realmGet$userSettings();
                    if (userSettingsObj != null) {
                        Long cacheuserSettings = (Long) cache.get(userSettingsObj);
                        if (cacheuserSettings == null) {
                            cacheuserSettings = Long.valueOf(UserSettingsRealmProxy.insertOrUpdate(realm, userSettingsObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.userSettingsIndex, rowIndex, cacheuserSettings.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.userSettingsIndex, rowIndex);
                    }
                    Number realmGet$publicId = object.realmGet$publicId();
                    if (realmGet$publicId != null) {
                        Table.nativeSetLong(tableNativePtr, columnInfo.publicIdIndex, rowIndex, realmGet$publicId.longValue(), false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.publicIdIndex, rowIndex, false);
                    }
                    String realmGet$groupId = object.realmGet$groupId();
                    if (realmGet$groupId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.groupIdIndex, rowIndex, realmGet$groupId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.groupIdIndex, rowIndex, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
                    }
                    String realmGet$caretakerId = object.realmGet$caretakerId();
                    if (realmGet$caretakerId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.caretakerIdIndex, rowIndex, realmGet$caretakerId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.caretakerIdIndex, rowIndex, false);
                    }
                    String realmGet$alarmId = object.realmGet$alarmId();
                    if (realmGet$alarmId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.alarmIdIndex, rowIndex, realmGet$alarmId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.alarmIdIndex, rowIndex, false);
                    }
                    OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.allergiresIndex);
                    osList.removeAll();
                    RealmList<Allergy> allergiresList = object.realmGet$allergires();
                    if (allergiresList != null) {
                        Iterator it = allergiresList.iterator();
                        while (it.hasNext()) {
                            Allergy allergiresItem = (Allergy) it.next();
                            Long cacheItemIndexallergires = (Long) cache.get(allergiresItem);
                            if (cacheItemIndexallergires == null) {
                                cacheItemIndexallergires = Long.valueOf(AllergyRealmProxy.insertOrUpdate(realm, allergiresItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexallergires.longValue());
                        }
                    }
                    Boolean realmGet$passwordResetEnabled = object.realmGet$passwordResetEnabled();
                    if (realmGet$passwordResetEnabled != null) {
                        Table.nativeSetBoolean(tableNativePtr, columnInfo.passwordResetEnabledIndex, rowIndex, realmGet$passwordResetEnabled.booleanValue(), false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.passwordResetEnabledIndex, rowIndex, false);
                    }
                    String realmGet$oauthId = object.realmGet$oauthId();
                    if (realmGet$oauthId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.oauthIdIndex, rowIndex, realmGet$oauthId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.oauthIdIndex, rowIndex, false);
                    }
                    String realmGet$oauthToken = object.realmGet$oauthToken();
                    if (realmGet$oauthToken != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.oauthTokenIndex, rowIndex, realmGet$oauthToken, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.oauthTokenIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Guardian createDetachedCopy(Guardian realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Guardian unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Guardian();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Guardian) cachedObject.object;
        } else {
            unmanagedObject = (Guardian) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        GuardianRealmProxyInterface unmanagedCopy = unmanagedObject;
        GuardianRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$firstName(realmSource.realmGet$firstName());
        unmanagedCopy.realmSet$lastName(realmSource.realmGet$lastName());
        unmanagedCopy.realmSet$dateOfBirth(realmSource.realmGet$dateOfBirth());
        unmanagedCopy.realmSet$email(realmSource.realmGet$email());
        unmanagedCopy.realmSet$password(realmSource.realmGet$password());
        unmanagedCopy.realmSet$room(realmSource.realmGet$room());
        unmanagedCopy.realmSet$governmentId(realmSource.realmGet$governmentId());
        unmanagedCopy.realmSet$height(HeightRealmProxy.createDetachedCopy(realmSource.realmGet$height(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$waistSize(WaistSizeRealmProxy.createDetachedCopy(realmSource.realmGet$waistSize(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$hipSize(HipSizeRealmProxy.createDetachedCopy(realmSource.realmGet$hipSize(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$patientId(realmSource.realmGet$patientId());
        unmanagedCopy.realmSet$country(realmSource.realmGet$country());
        unmanagedCopy.realmSet$mobile(realmSource.realmGet$mobile());
        unmanagedCopy.realmSet$gender(realmSource.realmGet$gender());
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$userSettings(UserSettingsRealmProxy.createDetachedCopy(realmSource.realmGet$userSettings(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$publicId(realmSource.realmGet$publicId());
        unmanagedCopy.realmSet$groupId(realmSource.realmGet$groupId());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$caretakerId(realmSource.realmGet$caretakerId());
        unmanagedCopy.realmSet$alarmId(realmSource.realmGet$alarmId());
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$allergires(null);
        } else {
            RealmList<Allergy> managedallergiresList = realmSource.realmGet$allergires();
            RealmList<Allergy> unmanagedallergiresList = new RealmList();
            unmanagedCopy.realmSet$allergires(unmanagedallergiresList);
            int nextDepth = currentDepth + 1;
            int size = managedallergiresList.size();
            for (int i = 0; i < size; i++) {
                unmanagedallergiresList.add(AllergyRealmProxy.createDetachedCopy((Allergy) managedallergiresList.get(i), nextDepth, maxDepth, cache));
            }
        }
        unmanagedCopy.realmSet$passwordResetEnabled(realmSource.realmGet$passwordResetEnabled());
        unmanagedCopy.realmSet$oauthId(realmSource.realmGet$oauthId());
        unmanagedCopy.realmSet$oauthToken(realmSource.realmGet$oauthToken());
        return unmanagedObject;
    }

    static Guardian update(Realm realm, Guardian realmObject, Guardian newObject, Map<RealmModel, RealmObjectProxy> cache) {
        GuardianRealmProxyInterface realmObjectTarget = realmObject;
        GuardianRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$firstName(realmObjectSource.realmGet$firstName());
        realmObjectTarget.realmSet$lastName(realmObjectSource.realmGet$lastName());
        realmObjectTarget.realmSet$dateOfBirth(realmObjectSource.realmGet$dateOfBirth());
        realmObjectTarget.realmSet$email(realmObjectSource.realmGet$email());
        realmObjectTarget.realmSet$password(realmObjectSource.realmGet$password());
        realmObjectTarget.realmSet$room(realmObjectSource.realmGet$room());
        realmObjectTarget.realmSet$governmentId(realmObjectSource.realmGet$governmentId());
        Height heightObj = realmObjectSource.realmGet$height();
        if (heightObj == null) {
            realmObjectTarget.realmSet$height(null);
        } else {
            Height cacheheight = (Height) cache.get(heightObj);
            if (cacheheight != null) {
                realmObjectTarget.realmSet$height(cacheheight);
            } else {
                realmObjectTarget.realmSet$height(HeightRealmProxy.copyOrUpdate(realm, heightObj, true, cache));
            }
        }
        WaistSize waistSizeObj = realmObjectSource.realmGet$waistSize();
        if (waistSizeObj == null) {
            realmObjectTarget.realmSet$waistSize(null);
        } else {
            WaistSize cachewaistSize = (WaistSize) cache.get(waistSizeObj);
            if (cachewaistSize != null) {
                realmObjectTarget.realmSet$waistSize(cachewaistSize);
            } else {
                realmObjectTarget.realmSet$waistSize(WaistSizeRealmProxy.copyOrUpdate(realm, waistSizeObj, true, cache));
            }
        }
        HipSize hipSizeObj = realmObjectSource.realmGet$hipSize();
        if (hipSizeObj == null) {
            realmObjectTarget.realmSet$hipSize(null);
        } else {
            HipSize cachehipSize = (HipSize) cache.get(hipSizeObj);
            if (cachehipSize != null) {
                realmObjectTarget.realmSet$hipSize(cachehipSize);
            } else {
                realmObjectTarget.realmSet$hipSize(HipSizeRealmProxy.copyOrUpdate(realm, hipSizeObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$patientId(realmObjectSource.realmGet$patientId());
        realmObjectTarget.realmSet$country(realmObjectSource.realmGet$country());
        realmObjectTarget.realmSet$mobile(realmObjectSource.realmGet$mobile());
        realmObjectTarget.realmSet$gender(realmObjectSource.realmGet$gender());
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        UserSettings userSettingsObj = realmObjectSource.realmGet$userSettings();
        if (userSettingsObj == null) {
            realmObjectTarget.realmSet$userSettings(null);
        } else {
            UserSettings cacheuserSettings = (UserSettings) cache.get(userSettingsObj);
            if (cacheuserSettings != null) {
                realmObjectTarget.realmSet$userSettings(cacheuserSettings);
            } else {
                realmObjectTarget.realmSet$userSettings(UserSettingsRealmProxy.copyOrUpdate(realm, userSettingsObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$publicId(realmObjectSource.realmGet$publicId());
        realmObjectTarget.realmSet$groupId(realmObjectSource.realmGet$groupId());
        realmObjectTarget.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectTarget.realmSet$caretakerId(realmObjectSource.realmGet$caretakerId());
        realmObjectTarget.realmSet$alarmId(realmObjectSource.realmGet$alarmId());
        RealmList<Allergy> allergiresList = realmObjectSource.realmGet$allergires();
        RealmList<Allergy> allergiresRealmList = realmObjectTarget.realmGet$allergires();
        allergiresRealmList.clear();
        if (allergiresList != null) {
            for (int i = 0; i < allergiresList.size(); i++) {
                Allergy allergiresItem = (Allergy) allergiresList.get(i);
                Allergy cacheallergires = (Allergy) cache.get(allergiresItem);
                if (cacheallergires != null) {
                    allergiresRealmList.add(cacheallergires);
                } else {
                    allergiresRealmList.add(AllergyRealmProxy.copyOrUpdate(realm, allergiresItem, true, cache));
                }
            }
        }
        realmObjectTarget.realmSet$passwordResetEnabled(realmObjectSource.realmGet$passwordResetEnabled());
        realmObjectTarget.realmSet$oauthId(realmObjectSource.realmGet$oauthId());
        realmObjectTarget.realmSet$oauthToken(realmObjectSource.realmGet$oauthToken());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Guardian = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{firstName:");
        stringBuilder.append(realmGet$firstName() != null ? realmGet$firstName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{lastName:");
        stringBuilder.append(realmGet$lastName() != null ? realmGet$lastName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dateOfBirth:");
        stringBuilder.append(realmGet$dateOfBirth() != null ? realmGet$dateOfBirth() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{email:");
        stringBuilder.append(realmGet$email() != null ? realmGet$email() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{password:");
        stringBuilder.append(realmGet$password() != null ? realmGet$password() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{room:");
        stringBuilder.append(realmGet$room() != null ? realmGet$room() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{governmentId:");
        stringBuilder.append(realmGet$governmentId() != null ? realmGet$governmentId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{height:");
        stringBuilder.append(realmGet$height() != null ? "Height" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{waistSize:");
        stringBuilder.append(realmGet$waistSize() != null ? "WaistSize" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{hipSize:");
        stringBuilder.append(realmGet$hipSize() != null ? "HipSize" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{patientId:");
        stringBuilder.append(realmGet$patientId() != null ? realmGet$patientId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{country:");
        stringBuilder.append(realmGet$country() != null ? realmGet$country() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mobile:");
        stringBuilder.append(realmGet$mobile() != null ? realmGet$mobile() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{gender:");
        stringBuilder.append(realmGet$gender() != null ? realmGet$gender() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tenantId:");
        stringBuilder.append(realmGet$tenantId() != null ? realmGet$tenantId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userSettings:");
        stringBuilder.append(realmGet$userSettings() != null ? "UserSettings" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{publicId:");
        stringBuilder.append(realmGet$publicId() != null ? realmGet$publicId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{groupId:");
        stringBuilder.append(realmGet$groupId() != null ? realmGet$groupId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{caretakerId:");
        stringBuilder.append(realmGet$caretakerId() != null ? realmGet$caretakerId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{alarmId:");
        stringBuilder.append(realmGet$alarmId() != null ? realmGet$alarmId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{allergires:");
        stringBuilder.append("RealmList<Allergy>[").append(realmGet$allergires().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{passwordResetEnabled:");
        stringBuilder.append(realmGet$passwordResetEnabled() != null ? realmGet$passwordResetEnabled() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{oauthId:");
        stringBuilder.append(realmGet$oauthId() != null ? realmGet$oauthId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{oauthToken:");
        stringBuilder.append(realmGet$oauthToken() != null ? realmGet$oauthToken() : "null");
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
        GuardianRealmProxy aGuardian = (GuardianRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aGuardian.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aGuardian.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aGuardian.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
