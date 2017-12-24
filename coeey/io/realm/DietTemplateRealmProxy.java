package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.DietTemplate;
import com.cooey.common.vo.MealPlan;
import com.cooey.common.vo.diet.weekdays.Friday;
import com.cooey.common.vo.diet.weekdays.Monday;
import com.cooey.common.vo.diet.weekdays.Saturday;
import com.cooey.common.vo.diet.weekdays.Thursday;
import com.cooey.common.vo.diet.weekdays.Tuesday;
import com.cooey.common.vo.diet.weekdays.Wednesday;
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

public class DietTemplateRealmProxy extends DietTemplate implements RealmObjectProxy, DietTemplateRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private DietTemplateColumnInfo columnInfo;
    private RealmList<MealPlan> mealPlanRealmList;
    private ProxyState<DietTemplate> proxyState;

    static final class DietTemplateColumnInfo extends ColumnInfo {
        long dietTitleIndex;
        long fridayIndex;
        long fromDateIndex;
        long idIndex;
        long mealPlanIndex;
        long mondayIndex;
        long ownerIdIndex;
        long ownerTypeIndex;
        long saturdayIndex;
        long thursdayIndex;
        long toDateIndex;
        long tuesdayIndex;
        long wednesdayIndex;

        DietTemplateColumnInfo(OsSchemaInfo schemaInfo) {
            super(13);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("DietTemplate");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.ownerIdIndex = addColumnDetails("ownerId", objectSchemaInfo);
            this.ownerTypeIndex = addColumnDetails("ownerType", objectSchemaInfo);
            this.dietTitleIndex = addColumnDetails("dietTitle", objectSchemaInfo);
            this.mondayIndex = addColumnDetails("monday", objectSchemaInfo);
            this.tuesdayIndex = addColumnDetails("tuesday", objectSchemaInfo);
            this.wednesdayIndex = addColumnDetails("wednesday", objectSchemaInfo);
            this.thursdayIndex = addColumnDetails("thursday", objectSchemaInfo);
            this.fridayIndex = addColumnDetails("friday", objectSchemaInfo);
            this.saturdayIndex = addColumnDetails("saturday", objectSchemaInfo);
            this.mealPlanIndex = addColumnDetails("mealPlan", objectSchemaInfo);
            this.fromDateIndex = addColumnDetails("fromDate", objectSchemaInfo);
            this.toDateIndex = addColumnDetails("toDate", objectSchemaInfo);
        }

        DietTemplateColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new DietTemplateColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            DietTemplateColumnInfo src = (DietTemplateColumnInfo) rawSrc;
            DietTemplateColumnInfo dst = (DietTemplateColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.ownerIdIndex = src.ownerIdIndex;
            dst.ownerTypeIndex = src.ownerTypeIndex;
            dst.dietTitleIndex = src.dietTitleIndex;
            dst.mondayIndex = src.mondayIndex;
            dst.tuesdayIndex = src.tuesdayIndex;
            dst.wednesdayIndex = src.wednesdayIndex;
            dst.thursdayIndex = src.thursdayIndex;
            dst.fridayIndex = src.fridayIndex;
            dst.saturdayIndex = src.saturdayIndex;
            dst.mealPlanIndex = src.mealPlanIndex;
            dst.fromDateIndex = src.fromDateIndex;
            dst.toDateIndex = src.toDateIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("ownerId");
        fieldNames.add("ownerType");
        fieldNames.add("dietTitle");
        fieldNames.add("monday");
        fieldNames.add("tuesday");
        fieldNames.add("wednesday");
        fieldNames.add("thursday");
        fieldNames.add("friday");
        fieldNames.add("saturday");
        fieldNames.add("mealPlan");
        fieldNames.add("fromDate");
        fieldNames.add("toDate");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DietTemplateRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (DietTemplateColumnInfo) context.getColumnInfo();
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

    public String realmGet$ownerType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.ownerTypeIndex);
    }

    public void realmSet$ownerType(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.ownerTypeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.ownerTypeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.ownerTypeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.ownerTypeIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$dietTitle() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.dietTitleIndex);
    }

    public void realmSet$dietTitle(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.dietTitleIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.dietTitleIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.dietTitleIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.dietTitleIndex, row.getIndex(), value, true);
            }
        }
    }

    public Monday realmGet$monday() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.mondayIndex)) {
            return null;
        }
        return (Monday) this.proxyState.getRealm$realm().get(Monday.class, this.proxyState.getRow$realm().getLink(this.columnInfo.mondayIndex), false, Collections.emptyList());
    }

    public void realmSet$monday(Monday value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.mondayIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.mondayIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("monday")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Monday) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.mondayIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.mondayIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Tuesday realmGet$tuesday() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.tuesdayIndex)) {
            return null;
        }
        return (Tuesday) this.proxyState.getRealm$realm().get(Tuesday.class, this.proxyState.getRow$realm().getLink(this.columnInfo.tuesdayIndex), false, Collections.emptyList());
    }

    public void realmSet$tuesday(Tuesday value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.tuesdayIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.tuesdayIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("tuesday")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Tuesday) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.tuesdayIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.tuesdayIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Wednesday realmGet$wednesday() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.wednesdayIndex)) {
            return null;
        }
        return (Wednesday) this.proxyState.getRealm$realm().get(Wednesday.class, this.proxyState.getRow$realm().getLink(this.columnInfo.wednesdayIndex), false, Collections.emptyList());
    }

    public void realmSet$wednesday(Wednesday value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.wednesdayIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.wednesdayIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("wednesday")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Wednesday) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.wednesdayIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.wednesdayIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Thursday realmGet$thursday() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.thursdayIndex)) {
            return null;
        }
        return (Thursday) this.proxyState.getRealm$realm().get(Thursday.class, this.proxyState.getRow$realm().getLink(this.columnInfo.thursdayIndex), false, Collections.emptyList());
    }

    public void realmSet$thursday(Thursday value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.thursdayIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.thursdayIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("thursday")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Thursday) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.thursdayIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.thursdayIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Friday realmGet$friday() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.fridayIndex)) {
            return null;
        }
        return (Friday) this.proxyState.getRealm$realm().get(Friday.class, this.proxyState.getRow$realm().getLink(this.columnInfo.fridayIndex), false, Collections.emptyList());
    }

    public void realmSet$friday(Friday value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.fridayIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.fridayIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("friday")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Friday) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.fridayIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.fridayIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Saturday realmGet$saturday() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.saturdayIndex)) {
            return null;
        }
        return (Saturday) this.proxyState.getRealm$realm().get(Saturday.class, this.proxyState.getRow$realm().getLink(this.columnInfo.saturdayIndex), false, Collections.emptyList());
    }

    public void realmSet$saturday(Saturday value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.saturdayIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.saturdayIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("saturday")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Saturday) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.saturdayIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.saturdayIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public RealmList<MealPlan> realmGet$mealPlan() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.mealPlanRealmList != null) {
            return this.mealPlanRealmList;
        }
        this.mealPlanRealmList = new RealmList(MealPlan.class, this.proxyState.getRow$realm().getLinkList(this.columnInfo.mealPlanIndex), this.proxyState.getRealm$realm());
        return this.mealPlanRealmList;
    }

    public void realmSet$mealPlan(RealmList<MealPlan> value) {
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("mealPlan")) {
                return;
            }
            if (!(value == null || value.isManaged())) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<MealPlan> original = value;
                value = new RealmList();
                Iterator it = original.iterator();
                while (it.hasNext()) {
                    MealPlan item = (MealPlan) it.next();
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList osList = this.proxyState.getRow$realm().getLinkList(this.columnInfo.mealPlanIndex);
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

    public long realmGet$fromDate() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.fromDateIndex);
    }

    public void realmSet$fromDate(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.fromDateIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.fromDateIndex, row.getIndex(), value, true);
        }
    }

    public long realmGet$toDate() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.toDateIndex);
    }

    public void realmSet$toDate(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.toDateIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.toDateIndex, row.getIndex(), value, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("DietTemplate");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("ownerId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("ownerType", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("dietTitle", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("monday", RealmFieldType.OBJECT, "Monday");
        builder.addPersistedLinkProperty("tuesday", RealmFieldType.OBJECT, "Tuesday");
        builder.addPersistedLinkProperty("wednesday", RealmFieldType.OBJECT, "Wednesday");
        builder.addPersistedLinkProperty("thursday", RealmFieldType.OBJECT, "Thursday");
        builder.addPersistedLinkProperty("friday", RealmFieldType.OBJECT, "Friday");
        builder.addPersistedLinkProperty("saturday", RealmFieldType.OBJECT, "Saturday");
        builder.addPersistedLinkProperty("mealPlan", RealmFieldType.LIST, "MealPlan");
        builder.addPersistedProperty("fromDate", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("toDate", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static DietTemplateColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new DietTemplateColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_DietTemplate";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static DietTemplate createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(7);
        DietTemplate dietTemplate = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(DietTemplate.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(DietTemplate.class), false, Collections.emptyList());
                    dietTemplate = new DietTemplateRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (dietTemplate == null) {
            if (json.has("monday")) {
                excludeFields.add("monday");
            }
            if (json.has("tuesday")) {
                excludeFields.add("tuesday");
            }
            if (json.has("wednesday")) {
                excludeFields.add("wednesday");
            }
            if (json.has("thursday")) {
                excludeFields.add("thursday");
            }
            if (json.has("friday")) {
                excludeFields.add("friday");
            }
            if (json.has("saturday")) {
                excludeFields.add("saturday");
            }
            if (json.has("mealPlan")) {
                excludeFields.add("mealPlan");
            }
            if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                    dietTemplate = (DietTemplateRealmProxy) realm.createObjectInternal(DietTemplate.class, null, true, excludeFields);
                } else {
                    String string = json.getString(ShareConstants.WEB_DIALOG_PARAM_ID);
                    DietTemplateRealmProxy obj = (DietTemplateRealmProxy) realm.createObjectInternal(DietTemplate.class, string, true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        DietTemplateRealmProxyInterface objProxy = dietTemplate;
        if (json.has("ownerId")) {
            if (json.isNull("ownerId")) {
                objProxy.realmSet$ownerId(null);
            } else {
                objProxy.realmSet$ownerId(json.getString("ownerId"));
            }
        }
        if (json.has("ownerType")) {
            if (json.isNull("ownerType")) {
                objProxy.realmSet$ownerType(null);
            } else {
                objProxy.realmSet$ownerType(json.getString("ownerType"));
            }
        }
        if (json.has("dietTitle")) {
            if (json.isNull("dietTitle")) {
                objProxy.realmSet$dietTitle(null);
            } else {
                objProxy.realmSet$dietTitle(json.getString("dietTitle"));
            }
        }
        if (json.has("monday")) {
            if (json.isNull("monday")) {
                objProxy.realmSet$monday(null);
            } else {
                objProxy.realmSet$monday(MondayRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("monday"), update));
            }
        }
        if (json.has("tuesday")) {
            if (json.isNull("tuesday")) {
                objProxy.realmSet$tuesday(null);
            } else {
                objProxy.realmSet$tuesday(TuesdayRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("tuesday"), update));
            }
        }
        if (json.has("wednesday")) {
            if (json.isNull("wednesday")) {
                objProxy.realmSet$wednesday(null);
            } else {
                objProxy.realmSet$wednesday(WednesdayRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("wednesday"), update));
            }
        }
        if (json.has("thursday")) {
            if (json.isNull("thursday")) {
                objProxy.realmSet$thursday(null);
            } else {
                objProxy.realmSet$thursday(ThursdayRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("thursday"), update));
            }
        }
        if (json.has("friday")) {
            if (json.isNull("friday")) {
                objProxy.realmSet$friday(null);
            } else {
                objProxy.realmSet$friday(FridayRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("friday"), update));
            }
        }
        if (json.has("saturday")) {
            if (json.isNull("saturday")) {
                objProxy.realmSet$saturday(null);
            } else {
                objProxy.realmSet$saturday(SaturdayRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("saturday"), update));
            }
        }
        if (json.has("mealPlan")) {
            if (json.isNull("mealPlan")) {
                objProxy.realmSet$mealPlan(null);
            } else {
                objProxy.realmGet$mealPlan().clear();
                JSONArray array = json.getJSONArray("mealPlan");
                for (int i = 0; i < array.length(); i++) {
                    objProxy.realmGet$mealPlan().add(MealPlanRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update));
                }
            }
        }
        if (json.has("fromDate")) {
            if (json.isNull("fromDate")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'fromDate' to null.");
            }
            objProxy.realmSet$fromDate(json.getLong("fromDate"));
        }
        if (json.has("toDate")) {
            if (json.isNull("toDate")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'toDate' to null.");
            }
            objProxy.realmSet$toDate(json.getLong("toDate"));
        }
        return dietTemplate;
    }

    @TargetApi(11)
    public static DietTemplate createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        DietTemplate obj = new DietTemplate();
        DietTemplateRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("ownerId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$ownerId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$ownerId(null);
                }
            } else if (name.equals("ownerType")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$ownerType(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$ownerType(null);
                }
            } else if (name.equals("dietTitle")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$dietTitle(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$dietTitle(null);
                }
            } else if (name.equals("monday")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$monday(null);
                } else {
                    objProxy.realmSet$monday(MondayRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("tuesday")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$tuesday(null);
                } else {
                    objProxy.realmSet$tuesday(TuesdayRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("wednesday")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$wednesday(null);
                } else {
                    objProxy.realmSet$wednesday(WednesdayRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("thursday")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$thursday(null);
                } else {
                    objProxy.realmSet$thursday(ThursdayRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("friday")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$friday(null);
                } else {
                    objProxy.realmSet$friday(FridayRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("saturday")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$saturday(null);
                } else {
                    objProxy.realmSet$saturday(SaturdayRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("mealPlan")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$mealPlan(null);
                } else {
                    objProxy.realmSet$mealPlan(new RealmList());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        objProxy.realmGet$mealPlan().add(MealPlanRealmProxy.createUsingJsonStream(realm, reader));
                    }
                    reader.endArray();
                }
            } else if (name.equals("fromDate")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$fromDate(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'fromDate' to null.");
                }
            } else if (!name.equals("toDate")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$toDate(reader.nextLong());
            } else {
                reader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'toDate' to null.");
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (DietTemplate) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    public static DietTemplate copyOrUpdate(Realm realm, DietTemplate object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (DietTemplate) cachedRealmObject;
        }
        DietTemplate update2;
        DietTemplate realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(DietTemplate.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(DietTemplate.class), false, Collections.emptyList());
                    DietTemplate realmObject2 = new DietTemplateRealmProxy();
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

    public static DietTemplate copy(Realm realm, DietTemplate newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (DietTemplate) cachedRealmObject;
        }
        DietTemplate realmObject = (DietTemplate) realm.createObjectInternal(DietTemplate.class, newObject.realmGet$id(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        DietTemplateRealmProxyInterface realmObjectSource = newObject;
        DietTemplateRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$ownerId(realmObjectSource.realmGet$ownerId());
        realmObjectCopy.realmSet$ownerType(realmObjectSource.realmGet$ownerType());
        realmObjectCopy.realmSet$dietTitle(realmObjectSource.realmGet$dietTitle());
        Monday mondayObj = realmObjectSource.realmGet$monday();
        if (mondayObj == null) {
            realmObjectCopy.realmSet$monday(null);
        } else {
            Monday cachemonday = (Monday) cache.get(mondayObj);
            if (cachemonday != null) {
                realmObjectCopy.realmSet$monday(cachemonday);
            } else {
                realmObjectCopy.realmSet$monday(MondayRealmProxy.copyOrUpdate(realm, mondayObj, update, cache));
            }
        }
        Tuesday tuesdayObj = realmObjectSource.realmGet$tuesday();
        if (tuesdayObj == null) {
            realmObjectCopy.realmSet$tuesday(null);
        } else {
            Tuesday cachetuesday = (Tuesday) cache.get(tuesdayObj);
            if (cachetuesday != null) {
                realmObjectCopy.realmSet$tuesday(cachetuesday);
            } else {
                realmObjectCopy.realmSet$tuesday(TuesdayRealmProxy.copyOrUpdate(realm, tuesdayObj, update, cache));
            }
        }
        Wednesday wednesdayObj = realmObjectSource.realmGet$wednesday();
        if (wednesdayObj == null) {
            realmObjectCopy.realmSet$wednesday(null);
        } else {
            Wednesday cachewednesday = (Wednesday) cache.get(wednesdayObj);
            if (cachewednesday != null) {
                realmObjectCopy.realmSet$wednesday(cachewednesday);
            } else {
                realmObjectCopy.realmSet$wednesday(WednesdayRealmProxy.copyOrUpdate(realm, wednesdayObj, update, cache));
            }
        }
        Thursday thursdayObj = realmObjectSource.realmGet$thursday();
        if (thursdayObj == null) {
            realmObjectCopy.realmSet$thursday(null);
        } else {
            Thursday cachethursday = (Thursday) cache.get(thursdayObj);
            if (cachethursday != null) {
                realmObjectCopy.realmSet$thursday(cachethursday);
            } else {
                realmObjectCopy.realmSet$thursday(ThursdayRealmProxy.copyOrUpdate(realm, thursdayObj, update, cache));
            }
        }
        Friday fridayObj = realmObjectSource.realmGet$friday();
        if (fridayObj == null) {
            realmObjectCopy.realmSet$friday(null);
        } else {
            Friday cachefriday = (Friday) cache.get(fridayObj);
            if (cachefriday != null) {
                realmObjectCopy.realmSet$friday(cachefriday);
            } else {
                realmObjectCopy.realmSet$friday(FridayRealmProxy.copyOrUpdate(realm, fridayObj, update, cache));
            }
        }
        Saturday saturdayObj = realmObjectSource.realmGet$saturday();
        if (saturdayObj == null) {
            realmObjectCopy.realmSet$saturday(null);
        } else {
            Saturday cachesaturday = (Saturday) cache.get(saturdayObj);
            if (cachesaturday != null) {
                realmObjectCopy.realmSet$saturday(cachesaturday);
            } else {
                realmObjectCopy.realmSet$saturday(SaturdayRealmProxy.copyOrUpdate(realm, saturdayObj, update, cache));
            }
        }
        RealmList<MealPlan> mealPlanList = realmObjectSource.realmGet$mealPlan();
        if (mealPlanList != null) {
            RealmList<MealPlan> mealPlanRealmList = realmObjectCopy.realmGet$mealPlan();
            mealPlanRealmList.clear();
            for (int i = 0; i < mealPlanList.size(); i++) {
                MealPlan mealPlanItem = (MealPlan) mealPlanList.get(i);
                MealPlan cachemealPlan = (MealPlan) cache.get(mealPlanItem);
                if (cachemealPlan != null) {
                    mealPlanRealmList.add(cachemealPlan);
                } else {
                    mealPlanRealmList.add(MealPlanRealmProxy.copyOrUpdate(realm, mealPlanItem, update, cache));
                }
            }
        }
        realmObjectCopy.realmSet$fromDate(realmObjectSource.realmGet$fromDate());
        realmObjectCopy.realmSet$toDate(realmObjectSource.realmGet$toDate());
        return realmObject;
    }

    public static long insert(Realm realm, DietTemplate object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(DietTemplate.class);
        long tableNativePtr = table.getNativePtr();
        DietTemplateColumnInfo columnInfo = (DietTemplateColumnInfo) realm.getSchema().getColumnInfo(DietTemplate.class);
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
        String realmGet$ownerId = object.realmGet$ownerId();
        if (realmGet$ownerId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, realmGet$ownerId, false);
        }
        String realmGet$ownerType = object.realmGet$ownerType();
        if (realmGet$ownerType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownerTypeIndex, rowIndex, realmGet$ownerType, false);
        }
        String realmGet$dietTitle = object.realmGet$dietTitle();
        if (realmGet$dietTitle != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dietTitleIndex, rowIndex, realmGet$dietTitle, false);
        }
        Monday mondayObj = object.realmGet$monday();
        if (mondayObj != null) {
            Long cachemonday = (Long) cache.get(mondayObj);
            if (cachemonday == null) {
                cachemonday = Long.valueOf(MondayRealmProxy.insert(realm, mondayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.mondayIndex, rowIndex, cachemonday.longValue(), false);
        }
        Tuesday tuesdayObj = object.realmGet$tuesday();
        if (tuesdayObj != null) {
            Long cachetuesday = (Long) cache.get(tuesdayObj);
            if (cachetuesday == null) {
                cachetuesday = Long.valueOf(TuesdayRealmProxy.insert(realm, tuesdayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.tuesdayIndex, rowIndex, cachetuesday.longValue(), false);
        }
        Wednesday wednesdayObj = object.realmGet$wednesday();
        if (wednesdayObj != null) {
            Long cachewednesday = (Long) cache.get(wednesdayObj);
            if (cachewednesday == null) {
                cachewednesday = Long.valueOf(WednesdayRealmProxy.insert(realm, wednesdayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.wednesdayIndex, rowIndex, cachewednesday.longValue(), false);
        }
        Thursday thursdayObj = object.realmGet$thursday();
        if (thursdayObj != null) {
            Long cachethursday = (Long) cache.get(thursdayObj);
            if (cachethursday == null) {
                cachethursday = Long.valueOf(ThursdayRealmProxy.insert(realm, thursdayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.thursdayIndex, rowIndex, cachethursday.longValue(), false);
        }
        Friday fridayObj = object.realmGet$friday();
        if (fridayObj != null) {
            Long cachefriday = (Long) cache.get(fridayObj);
            if (cachefriday == null) {
                cachefriday = Long.valueOf(FridayRealmProxy.insert(realm, fridayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.fridayIndex, rowIndex, cachefriday.longValue(), false);
        }
        Saturday saturdayObj = object.realmGet$saturday();
        if (saturdayObj != null) {
            Long cachesaturday = (Long) cache.get(saturdayObj);
            if (cachesaturday == null) {
                cachesaturday = Long.valueOf(SaturdayRealmProxy.insert(realm, saturdayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.saturdayIndex, rowIndex, cachesaturday.longValue(), false);
        }
        RealmList<MealPlan> mealPlanList = object.realmGet$mealPlan();
        if (mealPlanList != null) {
            OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.mealPlanIndex);
            Iterator it = mealPlanList.iterator();
            while (it.hasNext()) {
                MealPlan mealPlanItem = (MealPlan) it.next();
                Long cacheItemIndexmealPlan = (Long) cache.get(mealPlanItem);
                if (cacheItemIndexmealPlan == null) {
                    cacheItemIndexmealPlan = Long.valueOf(MealPlanRealmProxy.insert(realm, mealPlanItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexmealPlan.longValue());
            }
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.fromDateIndex, rowIndex, object.realmGet$fromDate(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.toDateIndex, rowIndex, object.realmGet$toDate(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(DietTemplate.class);
        long tableNativePtr = table.getNativePtr();
        DietTemplateColumnInfo columnInfo = (DietTemplateColumnInfo) realm.getSchema().getColumnInfo(DietTemplate.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            DietTemplate object = (DietTemplate) objects.next();
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
                    String realmGet$ownerId = object.realmGet$ownerId();
                    if (realmGet$ownerId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, realmGet$ownerId, false);
                    }
                    String realmGet$ownerType = object.realmGet$ownerType();
                    if (realmGet$ownerType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.ownerTypeIndex, rowIndex, realmGet$ownerType, false);
                    }
                    String realmGet$dietTitle = object.realmGet$dietTitle();
                    if (realmGet$dietTitle != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.dietTitleIndex, rowIndex, realmGet$dietTitle, false);
                    }
                    Monday mondayObj = object.realmGet$monday();
                    if (mondayObj != null) {
                        Long cachemonday = (Long) cache.get(mondayObj);
                        if (cachemonday == null) {
                            cachemonday = Long.valueOf(MondayRealmProxy.insert(realm, mondayObj, (Map) cache));
                        }
                        table.setLink(columnInfo.mondayIndex, rowIndex, cachemonday.longValue(), false);
                    }
                    Tuesday tuesdayObj = object.realmGet$tuesday();
                    if (tuesdayObj != null) {
                        Long cachetuesday = (Long) cache.get(tuesdayObj);
                        if (cachetuesday == null) {
                            cachetuesday = Long.valueOf(TuesdayRealmProxy.insert(realm, tuesdayObj, (Map) cache));
                        }
                        table.setLink(columnInfo.tuesdayIndex, rowIndex, cachetuesday.longValue(), false);
                    }
                    Wednesday wednesdayObj = object.realmGet$wednesday();
                    if (wednesdayObj != null) {
                        Long cachewednesday = (Long) cache.get(wednesdayObj);
                        if (cachewednesday == null) {
                            cachewednesday = Long.valueOf(WednesdayRealmProxy.insert(realm, wednesdayObj, (Map) cache));
                        }
                        table.setLink(columnInfo.wednesdayIndex, rowIndex, cachewednesday.longValue(), false);
                    }
                    Thursday thursdayObj = object.realmGet$thursday();
                    if (thursdayObj != null) {
                        Long cachethursday = (Long) cache.get(thursdayObj);
                        if (cachethursday == null) {
                            cachethursday = Long.valueOf(ThursdayRealmProxy.insert(realm, thursdayObj, (Map) cache));
                        }
                        table.setLink(columnInfo.thursdayIndex, rowIndex, cachethursday.longValue(), false);
                    }
                    Friday fridayObj = object.realmGet$friday();
                    if (fridayObj != null) {
                        Long cachefriday = (Long) cache.get(fridayObj);
                        if (cachefriday == null) {
                            cachefriday = Long.valueOf(FridayRealmProxy.insert(realm, fridayObj, (Map) cache));
                        }
                        table.setLink(columnInfo.fridayIndex, rowIndex, cachefriday.longValue(), false);
                    }
                    Saturday saturdayObj = object.realmGet$saturday();
                    if (saturdayObj != null) {
                        Long cachesaturday = (Long) cache.get(saturdayObj);
                        if (cachesaturday == null) {
                            cachesaturday = Long.valueOf(SaturdayRealmProxy.insert(realm, saturdayObj, (Map) cache));
                        }
                        table.setLink(columnInfo.saturdayIndex, rowIndex, cachesaturday.longValue(), false);
                    }
                    RealmList<MealPlan> mealPlanList = object.realmGet$mealPlan();
                    if (mealPlanList != null) {
                        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.mealPlanIndex);
                        Iterator it = mealPlanList.iterator();
                        while (it.hasNext()) {
                            MealPlan mealPlanItem = (MealPlan) it.next();
                            Long cacheItemIndexmealPlan = (Long) cache.get(mealPlanItem);
                            if (cacheItemIndexmealPlan == null) {
                                cacheItemIndexmealPlan = Long.valueOf(MealPlanRealmProxy.insert(realm, mealPlanItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexmealPlan.longValue());
                        }
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.fromDateIndex, rowIndex, object.realmGet$fromDate(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.toDateIndex, rowIndex, object.realmGet$toDate(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, DietTemplate object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(DietTemplate.class);
        long tableNativePtr = table.getNativePtr();
        DietTemplateColumnInfo columnInfo = (DietTemplateColumnInfo) realm.getSchema().getColumnInfo(DietTemplate.class);
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
        String realmGet$ownerId = object.realmGet$ownerId();
        if (realmGet$ownerId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, realmGet$ownerId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, false);
        }
        String realmGet$ownerType = object.realmGet$ownerType();
        if (realmGet$ownerType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownerTypeIndex, rowIndex, realmGet$ownerType, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.ownerTypeIndex, rowIndex, false);
        }
        String realmGet$dietTitle = object.realmGet$dietTitle();
        if (realmGet$dietTitle != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dietTitleIndex, rowIndex, realmGet$dietTitle, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dietTitleIndex, rowIndex, false);
        }
        Monday mondayObj = object.realmGet$monday();
        if (mondayObj != null) {
            Long cachemonday = (Long) cache.get(mondayObj);
            if (cachemonday == null) {
                cachemonday = Long.valueOf(MondayRealmProxy.insertOrUpdate(realm, mondayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.mondayIndex, rowIndex, cachemonday.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.mondayIndex, rowIndex);
        }
        Tuesday tuesdayObj = object.realmGet$tuesday();
        if (tuesdayObj != null) {
            Long cachetuesday = (Long) cache.get(tuesdayObj);
            if (cachetuesday == null) {
                cachetuesday = Long.valueOf(TuesdayRealmProxy.insertOrUpdate(realm, tuesdayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.tuesdayIndex, rowIndex, cachetuesday.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.tuesdayIndex, rowIndex);
        }
        Wednesday wednesdayObj = object.realmGet$wednesday();
        if (wednesdayObj != null) {
            Long cachewednesday = (Long) cache.get(wednesdayObj);
            if (cachewednesday == null) {
                cachewednesday = Long.valueOf(WednesdayRealmProxy.insertOrUpdate(realm, wednesdayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.wednesdayIndex, rowIndex, cachewednesday.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.wednesdayIndex, rowIndex);
        }
        Thursday thursdayObj = object.realmGet$thursday();
        if (thursdayObj != null) {
            Long cachethursday = (Long) cache.get(thursdayObj);
            if (cachethursday == null) {
                cachethursday = Long.valueOf(ThursdayRealmProxy.insertOrUpdate(realm, thursdayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.thursdayIndex, rowIndex, cachethursday.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.thursdayIndex, rowIndex);
        }
        Friday fridayObj = object.realmGet$friday();
        if (fridayObj != null) {
            Long cachefriday = (Long) cache.get(fridayObj);
            if (cachefriday == null) {
                cachefriday = Long.valueOf(FridayRealmProxy.insertOrUpdate(realm, fridayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.fridayIndex, rowIndex, cachefriday.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.fridayIndex, rowIndex);
        }
        Saturday saturdayObj = object.realmGet$saturday();
        if (saturdayObj != null) {
            Long cachesaturday = (Long) cache.get(saturdayObj);
            if (cachesaturday == null) {
                cachesaturday = Long.valueOf(SaturdayRealmProxy.insertOrUpdate(realm, saturdayObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.saturdayIndex, rowIndex, cachesaturday.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.saturdayIndex, rowIndex);
        }
        OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.mealPlanIndex);
        osList.removeAll();
        RealmList<MealPlan> mealPlanList = object.realmGet$mealPlan();
        if (mealPlanList != null) {
            Iterator it = mealPlanList.iterator();
            while (it.hasNext()) {
                MealPlan mealPlanItem = (MealPlan) it.next();
                Long cacheItemIndexmealPlan = (Long) cache.get(mealPlanItem);
                if (cacheItemIndexmealPlan == null) {
                    cacheItemIndexmealPlan = Long.valueOf(MealPlanRealmProxy.insertOrUpdate(realm, mealPlanItem, (Map) cache));
                }
                osList.addRow(cacheItemIndexmealPlan.longValue());
            }
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.fromDateIndex, rowIndex, object.realmGet$fromDate(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.toDateIndex, rowIndex, object.realmGet$toDate(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(DietTemplate.class);
        long tableNativePtr = table.getNativePtr();
        DietTemplateColumnInfo columnInfo = (DietTemplateColumnInfo) realm.getSchema().getColumnInfo(DietTemplate.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            DietTemplate object = (DietTemplate) objects.next();
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
                    String realmGet$ownerId = object.realmGet$ownerId();
                    if (realmGet$ownerId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, realmGet$ownerId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.ownerIdIndex, rowIndex, false);
                    }
                    String realmGet$ownerType = object.realmGet$ownerType();
                    if (realmGet$ownerType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.ownerTypeIndex, rowIndex, realmGet$ownerType, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.ownerTypeIndex, rowIndex, false);
                    }
                    String realmGet$dietTitle = object.realmGet$dietTitle();
                    if (realmGet$dietTitle != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.dietTitleIndex, rowIndex, realmGet$dietTitle, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.dietTitleIndex, rowIndex, false);
                    }
                    Monday mondayObj = object.realmGet$monday();
                    if (mondayObj != null) {
                        Long cachemonday = (Long) cache.get(mondayObj);
                        if (cachemonday == null) {
                            cachemonday = Long.valueOf(MondayRealmProxy.insertOrUpdate(realm, mondayObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.mondayIndex, rowIndex, cachemonday.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.mondayIndex, rowIndex);
                    }
                    Tuesday tuesdayObj = object.realmGet$tuesday();
                    if (tuesdayObj != null) {
                        Long cachetuesday = (Long) cache.get(tuesdayObj);
                        if (cachetuesday == null) {
                            cachetuesday = Long.valueOf(TuesdayRealmProxy.insertOrUpdate(realm, tuesdayObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.tuesdayIndex, rowIndex, cachetuesday.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.tuesdayIndex, rowIndex);
                    }
                    Wednesday wednesdayObj = object.realmGet$wednesday();
                    if (wednesdayObj != null) {
                        Long cachewednesday = (Long) cache.get(wednesdayObj);
                        if (cachewednesday == null) {
                            cachewednesday = Long.valueOf(WednesdayRealmProxy.insertOrUpdate(realm, wednesdayObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.wednesdayIndex, rowIndex, cachewednesday.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.wednesdayIndex, rowIndex);
                    }
                    Thursday thursdayObj = object.realmGet$thursday();
                    if (thursdayObj != null) {
                        Long cachethursday = (Long) cache.get(thursdayObj);
                        if (cachethursday == null) {
                            cachethursday = Long.valueOf(ThursdayRealmProxy.insertOrUpdate(realm, thursdayObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.thursdayIndex, rowIndex, cachethursday.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.thursdayIndex, rowIndex);
                    }
                    Friday fridayObj = object.realmGet$friday();
                    if (fridayObj != null) {
                        Long cachefriday = (Long) cache.get(fridayObj);
                        if (cachefriday == null) {
                            cachefriday = Long.valueOf(FridayRealmProxy.insertOrUpdate(realm, fridayObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.fridayIndex, rowIndex, cachefriday.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.fridayIndex, rowIndex);
                    }
                    Saturday saturdayObj = object.realmGet$saturday();
                    if (saturdayObj != null) {
                        Long cachesaturday = (Long) cache.get(saturdayObj);
                        if (cachesaturday == null) {
                            cachesaturday = Long.valueOf(SaturdayRealmProxy.insertOrUpdate(realm, saturdayObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.saturdayIndex, rowIndex, cachesaturday.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.saturdayIndex, rowIndex);
                    }
                    OsList osList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.mealPlanIndex);
                    osList.removeAll();
                    RealmList<MealPlan> mealPlanList = object.realmGet$mealPlan();
                    if (mealPlanList != null) {
                        Iterator it = mealPlanList.iterator();
                        while (it.hasNext()) {
                            MealPlan mealPlanItem = (MealPlan) it.next();
                            Long cacheItemIndexmealPlan = (Long) cache.get(mealPlanItem);
                            if (cacheItemIndexmealPlan == null) {
                                cacheItemIndexmealPlan = Long.valueOf(MealPlanRealmProxy.insertOrUpdate(realm, mealPlanItem, (Map) cache));
                            }
                            osList.addRow(cacheItemIndexmealPlan.longValue());
                        }
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.fromDateIndex, rowIndex, object.realmGet$fromDate(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.toDateIndex, rowIndex, object.realmGet$toDate(), false);
                }
            }
        }
    }

    public static DietTemplate createDetachedCopy(DietTemplate realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        DietTemplate unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new DietTemplate();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (DietTemplate) cachedObject.object;
        } else {
            unmanagedObject = (DietTemplate) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        DietTemplateRealmProxyInterface unmanagedCopy = unmanagedObject;
        DietTemplateRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$ownerId(realmSource.realmGet$ownerId());
        unmanagedCopy.realmSet$ownerType(realmSource.realmGet$ownerType());
        unmanagedCopy.realmSet$dietTitle(realmSource.realmGet$dietTitle());
        unmanagedCopy.realmSet$monday(MondayRealmProxy.createDetachedCopy(realmSource.realmGet$monday(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$tuesday(TuesdayRealmProxy.createDetachedCopy(realmSource.realmGet$tuesday(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$wednesday(WednesdayRealmProxy.createDetachedCopy(realmSource.realmGet$wednesday(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$thursday(ThursdayRealmProxy.createDetachedCopy(realmSource.realmGet$thursday(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$friday(FridayRealmProxy.createDetachedCopy(realmSource.realmGet$friday(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$saturday(SaturdayRealmProxy.createDetachedCopy(realmSource.realmGet$saturday(), currentDepth + 1, maxDepth, cache));
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$mealPlan(null);
        } else {
            RealmList<MealPlan> managedmealPlanList = realmSource.realmGet$mealPlan();
            RealmList<MealPlan> unmanagedmealPlanList = new RealmList();
            unmanagedCopy.realmSet$mealPlan(unmanagedmealPlanList);
            int nextDepth = currentDepth + 1;
            int size = managedmealPlanList.size();
            for (int i = 0; i < size; i++) {
                unmanagedmealPlanList.add(MealPlanRealmProxy.createDetachedCopy((MealPlan) managedmealPlanList.get(i), nextDepth, maxDepth, cache));
            }
        }
        unmanagedCopy.realmSet$fromDate(realmSource.realmGet$fromDate());
        unmanagedCopy.realmSet$toDate(realmSource.realmGet$toDate());
        return unmanagedObject;
    }

    static DietTemplate update(Realm realm, DietTemplate realmObject, DietTemplate newObject, Map<RealmModel, RealmObjectProxy> cache) {
        DietTemplateRealmProxyInterface realmObjectTarget = realmObject;
        DietTemplateRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$ownerId(realmObjectSource.realmGet$ownerId());
        realmObjectTarget.realmSet$ownerType(realmObjectSource.realmGet$ownerType());
        realmObjectTarget.realmSet$dietTitle(realmObjectSource.realmGet$dietTitle());
        Monday mondayObj = realmObjectSource.realmGet$monday();
        if (mondayObj == null) {
            realmObjectTarget.realmSet$monday(null);
        } else {
            Monday cachemonday = (Monday) cache.get(mondayObj);
            if (cachemonday != null) {
                realmObjectTarget.realmSet$monday(cachemonday);
            } else {
                realmObjectTarget.realmSet$monday(MondayRealmProxy.copyOrUpdate(realm, mondayObj, true, cache));
            }
        }
        Tuesday tuesdayObj = realmObjectSource.realmGet$tuesday();
        if (tuesdayObj == null) {
            realmObjectTarget.realmSet$tuesday(null);
        } else {
            Tuesday cachetuesday = (Tuesday) cache.get(tuesdayObj);
            if (cachetuesday != null) {
                realmObjectTarget.realmSet$tuesday(cachetuesday);
            } else {
                realmObjectTarget.realmSet$tuesday(TuesdayRealmProxy.copyOrUpdate(realm, tuesdayObj, true, cache));
            }
        }
        Wednesday wednesdayObj = realmObjectSource.realmGet$wednesday();
        if (wednesdayObj == null) {
            realmObjectTarget.realmSet$wednesday(null);
        } else {
            Wednesday cachewednesday = (Wednesday) cache.get(wednesdayObj);
            if (cachewednesday != null) {
                realmObjectTarget.realmSet$wednesday(cachewednesday);
            } else {
                realmObjectTarget.realmSet$wednesday(WednesdayRealmProxy.copyOrUpdate(realm, wednesdayObj, true, cache));
            }
        }
        Thursday thursdayObj = realmObjectSource.realmGet$thursday();
        if (thursdayObj == null) {
            realmObjectTarget.realmSet$thursday(null);
        } else {
            Thursday cachethursday = (Thursday) cache.get(thursdayObj);
            if (cachethursday != null) {
                realmObjectTarget.realmSet$thursday(cachethursday);
            } else {
                realmObjectTarget.realmSet$thursday(ThursdayRealmProxy.copyOrUpdate(realm, thursdayObj, true, cache));
            }
        }
        Friday fridayObj = realmObjectSource.realmGet$friday();
        if (fridayObj == null) {
            realmObjectTarget.realmSet$friday(null);
        } else {
            Friday cachefriday = (Friday) cache.get(fridayObj);
            if (cachefriday != null) {
                realmObjectTarget.realmSet$friday(cachefriday);
            } else {
                realmObjectTarget.realmSet$friday(FridayRealmProxy.copyOrUpdate(realm, fridayObj, true, cache));
            }
        }
        Saturday saturdayObj = realmObjectSource.realmGet$saturday();
        if (saturdayObj == null) {
            realmObjectTarget.realmSet$saturday(null);
        } else {
            Saturday cachesaturday = (Saturday) cache.get(saturdayObj);
            if (cachesaturday != null) {
                realmObjectTarget.realmSet$saturday(cachesaturday);
            } else {
                realmObjectTarget.realmSet$saturday(SaturdayRealmProxy.copyOrUpdate(realm, saturdayObj, true, cache));
            }
        }
        RealmList<MealPlan> mealPlanList = realmObjectSource.realmGet$mealPlan();
        RealmList<MealPlan> mealPlanRealmList = realmObjectTarget.realmGet$mealPlan();
        mealPlanRealmList.clear();
        if (mealPlanList != null) {
            for (int i = 0; i < mealPlanList.size(); i++) {
                MealPlan mealPlanItem = (MealPlan) mealPlanList.get(i);
                MealPlan cachemealPlan = (MealPlan) cache.get(mealPlanItem);
                if (cachemealPlan != null) {
                    mealPlanRealmList.add(cachemealPlan);
                } else {
                    mealPlanRealmList.add(MealPlanRealmProxy.copyOrUpdate(realm, mealPlanItem, true, cache));
                }
            }
        }
        realmObjectTarget.realmSet$fromDate(realmObjectSource.realmGet$fromDate());
        realmObjectTarget.realmSet$toDate(realmObjectSource.realmGet$toDate());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DietTemplate = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{ownerId:");
        stringBuilder.append(realmGet$ownerId() != null ? realmGet$ownerId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{ownerType:");
        stringBuilder.append(realmGet$ownerType() != null ? realmGet$ownerType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dietTitle:");
        stringBuilder.append(realmGet$dietTitle() != null ? realmGet$dietTitle() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{monday:");
        stringBuilder.append(realmGet$monday() != null ? "Monday" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tuesday:");
        stringBuilder.append(realmGet$tuesday() != null ? "Tuesday" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{wednesday:");
        stringBuilder.append(realmGet$wednesday() != null ? "Wednesday" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{thursday:");
        stringBuilder.append(realmGet$thursday() != null ? "Thursday" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{friday:");
        stringBuilder.append(realmGet$friday() != null ? "Friday" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{saturday:");
        stringBuilder.append(realmGet$saturday() != null ? "Saturday" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mealPlan:");
        stringBuilder.append("RealmList<MealPlan>[").append(realmGet$mealPlan().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{fromDate:");
        stringBuilder.append(realmGet$fromDate());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{toDate:");
        stringBuilder.append(realmGet$toDate());
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
        DietTemplateRealmProxy aDietTemplate = (DietTemplateRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aDietTemplate.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDietTemplate.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aDietTemplate.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
