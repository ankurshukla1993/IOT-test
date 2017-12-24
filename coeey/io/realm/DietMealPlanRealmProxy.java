package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.diet.DietMealPlan;
import com.cooey.common.vo.diet.time.BreakFast;
import com.cooey.common.vo.diet.time.Dinner;
import com.cooey.common.vo.diet.time.Lunch;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.ihealth.communication.control.HsProfile;
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

public class DietMealPlanRealmProxy extends DietMealPlan implements RealmObjectProxy, DietMealPlanRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private DietMealPlanColumnInfo columnInfo;
    private ProxyState<DietMealPlan> proxyState;

    static final class DietMealPlanColumnInfo extends ColumnInfo {
        long breakFastIndex;
        long caloriesIndex;
        long carbsIndex;
        long dinnerIndex;
        long fatIndex;
        long idIndex;
        long lunchIndex;
        long nameIndex;
        long proteinsIndex;
        long quantityIndex;
        long servingSizeIndex;
        long typeIndex;

        DietMealPlanColumnInfo(OsSchemaInfo schemaInfo) {
            super(12);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("DietMealPlan");
            this.breakFastIndex = addColumnDetails("breakFast", objectSchemaInfo);
            this.lunchIndex = addColumnDetails("lunch", objectSchemaInfo);
            this.dinnerIndex = addColumnDetails("dinner", objectSchemaInfo);
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.caloriesIndex = addColumnDetails("calories", objectSchemaInfo);
            this.carbsIndex = addColumnDetails("carbs", objectSchemaInfo);
            this.fatIndex = addColumnDetails(HsProfile.FAT_HS, objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.proteinsIndex = addColumnDetails("proteins", objectSchemaInfo);
            this.quantityIndex = addColumnDetails(Param.QUANTITY, objectSchemaInfo);
            this.servingSizeIndex = addColumnDetails("servingSize", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
        }

        DietMealPlanColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new DietMealPlanColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            DietMealPlanColumnInfo src = (DietMealPlanColumnInfo) rawSrc;
            DietMealPlanColumnInfo dst = (DietMealPlanColumnInfo) rawDst;
            dst.breakFastIndex = src.breakFastIndex;
            dst.lunchIndex = src.lunchIndex;
            dst.dinnerIndex = src.dinnerIndex;
            dst.idIndex = src.idIndex;
            dst.caloriesIndex = src.caloriesIndex;
            dst.carbsIndex = src.carbsIndex;
            dst.fatIndex = src.fatIndex;
            dst.nameIndex = src.nameIndex;
            dst.proteinsIndex = src.proteinsIndex;
            dst.quantityIndex = src.quantityIndex;
            dst.servingSizeIndex = src.servingSizeIndex;
            dst.typeIndex = src.typeIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("breakFast");
        fieldNames.add("lunch");
        fieldNames.add("dinner");
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("calories");
        fieldNames.add("carbs");
        fieldNames.add(HsProfile.FAT_HS);
        fieldNames.add("name");
        fieldNames.add("proteins");
        fieldNames.add(Param.QUANTITY);
        fieldNames.add("servingSize");
        fieldNames.add("type");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DietMealPlanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (DietMealPlanColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public BreakFast realmGet$breakFast() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.breakFastIndex)) {
            return null;
        }
        return (BreakFast) this.proxyState.getRealm$realm().get(BreakFast.class, this.proxyState.getRow$realm().getLink(this.columnInfo.breakFastIndex), false, Collections.emptyList());
    }

    public void realmSet$breakFast(BreakFast value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.breakFastIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.breakFastIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("breakFast")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (BreakFast) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.breakFastIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.breakFastIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Lunch realmGet$lunch() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.lunchIndex)) {
            return null;
        }
        return (Lunch) this.proxyState.getRealm$realm().get(Lunch.class, this.proxyState.getRow$realm().getLink(this.columnInfo.lunchIndex), false, Collections.emptyList());
    }

    public void realmSet$lunch(Lunch value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.lunchIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.lunchIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("lunch")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Lunch) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.lunchIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.lunchIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            }
        }
    }

    public Dinner realmGet$dinner() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.dinnerIndex)) {
            return null;
        }
        return (Dinner) this.proxyState.getRealm$realm().get(Dinner.class, this.proxyState.getRow$realm().getLink(this.columnInfo.dinnerIndex), false, Collections.emptyList());
    }

    public void realmSet$dinner(Dinner value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.dinnerIndex);
            } else if (!RealmObject.isManaged(value) || !RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                this.proxyState.getRow$realm().setLink(this.columnInfo.dinnerIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("dinner")) {
            if (!(value == null || RealmObject.isManaged(value))) {
                value = (Dinner) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(value);
            }
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.nullifyLink(this.columnInfo.dinnerIndex);
            } else if (!RealmObject.isValid(value)) {
                throw new IllegalArgumentException("'value' is not a valid managed object.");
            } else if (((RealmObjectProxy) value).realmGet$proxyState().getRealm$realm() != this.proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("'value' belongs to a different Realm.");
            } else {
                row.getTable().setLink(this.columnInfo.dinnerIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
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
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.idIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.idIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.idIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.idIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$calories() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.caloriesIndex);
    }

    public void realmSet$calories(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.caloriesIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.caloriesIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.caloriesIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.caloriesIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$carbs() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.carbsIndex);
    }

    public void realmSet$carbs(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.carbsIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.carbsIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.carbsIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.carbsIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$fat() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.fatIndex);
    }

    public void realmSet$fat(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.fatIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.fatIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.fatIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.fatIndex, row.getIndex(), value, true);
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

    public String realmGet$proteins() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.proteinsIndex);
    }

    public void realmSet$proteins(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.proteinsIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.proteinsIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.proteinsIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.proteinsIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$quantity() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.quantityIndex);
    }

    public void realmSet$quantity(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.quantityIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.quantityIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.quantityIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.quantityIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$servingSize() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.servingSizeIndex);
    }

    public void realmSet$servingSize(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.servingSizeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.servingSizeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.servingSizeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.servingSizeIndex, row.getIndex(), value, true);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("DietMealPlan");
        builder.addPersistedLinkProperty("breakFast", RealmFieldType.OBJECT, "BreakFast");
        builder.addPersistedLinkProperty("lunch", RealmFieldType.OBJECT, "Lunch");
        builder.addPersistedLinkProperty("dinner", RealmFieldType.OBJECT, "Dinner");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("calories", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("carbs", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(HsProfile.FAT_HS, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("proteins", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(Param.QUANTITY, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("servingSize", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static DietMealPlanColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new DietMealPlanColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_DietMealPlan";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static DietMealPlan createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = new ArrayList(3);
        if (json.has("breakFast")) {
            excludeFields.add("breakFast");
        }
        if (json.has("lunch")) {
            excludeFields.add("lunch");
        }
        if (json.has("dinner")) {
            excludeFields.add("dinner");
        }
        DietMealPlan obj = (DietMealPlan) realm.createObjectInternal(DietMealPlan.class, true, excludeFields);
        DietMealPlanRealmProxyInterface objProxy = obj;
        if (json.has("breakFast")) {
            if (json.isNull("breakFast")) {
                objProxy.realmSet$breakFast(null);
            } else {
                objProxy.realmSet$breakFast(BreakFastRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("breakFast"), update));
            }
        }
        if (json.has("lunch")) {
            if (json.isNull("lunch")) {
                objProxy.realmSet$lunch(null);
            } else {
                objProxy.realmSet$lunch(LunchRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("lunch"), update));
            }
        }
        if (json.has("dinner")) {
            if (json.isNull("dinner")) {
                objProxy.realmSet$dinner(null);
            } else {
                objProxy.realmSet$dinner(DinnerRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("dinner"), update));
            }
        }
        if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                objProxy.realmSet$id(null);
            } else {
                objProxy.realmSet$id(json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
        }
        if (json.has("calories")) {
            if (json.isNull("calories")) {
                objProxy.realmSet$calories(null);
            } else {
                objProxy.realmSet$calories(json.getString("calories"));
            }
        }
        if (json.has("carbs")) {
            if (json.isNull("carbs")) {
                objProxy.realmSet$carbs(null);
            } else {
                objProxy.realmSet$carbs(json.getString("carbs"));
            }
        }
        if (json.has(HsProfile.FAT_HS)) {
            if (json.isNull(HsProfile.FAT_HS)) {
                objProxy.realmSet$fat(null);
            } else {
                objProxy.realmSet$fat(json.getString(HsProfile.FAT_HS));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name(json.getString("name"));
            }
        }
        if (json.has("proteins")) {
            if (json.isNull("proteins")) {
                objProxy.realmSet$proteins(null);
            } else {
                objProxy.realmSet$proteins(json.getString("proteins"));
            }
        }
        if (json.has(Param.QUANTITY)) {
            if (json.isNull(Param.QUANTITY)) {
                objProxy.realmSet$quantity(null);
            } else {
                objProxy.realmSet$quantity(json.getString(Param.QUANTITY));
            }
        }
        if (json.has("servingSize")) {
            if (json.isNull("servingSize")) {
                objProxy.realmSet$servingSize(null);
            } else {
                objProxy.realmSet$servingSize(json.getString("servingSize"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type(json.getString("type"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static DietMealPlan createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        DietMealPlan obj = new DietMealPlan();
        DietMealPlanRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("breakFast")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$breakFast(null);
                } else {
                    objProxy.realmSet$breakFast(BreakFastRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("lunch")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$lunch(null);
                } else {
                    objProxy.realmSet$lunch(LunchRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals("dinner")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$dinner(null);
                } else {
                    objProxy.realmSet$dinner(DinnerRealmProxy.createUsingJsonStream(realm, reader));
                }
            } else if (name.equals(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
            } else if (name.equals("calories")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$calories(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$calories(null);
                }
            } else if (name.equals("carbs")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$carbs(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$carbs(null);
                }
            } else if (name.equals(HsProfile.FAT_HS)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$fat(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$fat(null);
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("proteins")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$proteins(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$proteins(null);
                }
            } else if (name.equals(Param.QUANTITY)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$quantity(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$quantity(null);
                }
            } else if (name.equals("servingSize")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$servingSize(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$servingSize(null);
                }
            } else if (!name.equals("type")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$type(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$type(null);
            }
        }
        reader.endObject();
        return (DietMealPlan) realm.copyToRealm(obj);
    }

    public static DietMealPlan copyOrUpdate(Realm realm, DietMealPlan object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (DietMealPlan) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static DietMealPlan copy(Realm realm, DietMealPlan newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (DietMealPlan) cachedRealmObject;
        }
        DietMealPlan realmObject = (DietMealPlan) realm.createObjectInternal(DietMealPlan.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        DietMealPlanRealmProxyInterface realmObjectSource = newObject;
        DietMealPlanRealmProxyInterface realmObjectCopy = realmObject;
        BreakFast breakFastObj = realmObjectSource.realmGet$breakFast();
        if (breakFastObj == null) {
            realmObjectCopy.realmSet$breakFast(null);
        } else {
            BreakFast cachebreakFast = (BreakFast) cache.get(breakFastObj);
            if (cachebreakFast != null) {
                realmObjectCopy.realmSet$breakFast(cachebreakFast);
            } else {
                realmObjectCopy.realmSet$breakFast(BreakFastRealmProxy.copyOrUpdate(realm, breakFastObj, update, cache));
            }
        }
        Lunch lunchObj = realmObjectSource.realmGet$lunch();
        if (lunchObj == null) {
            realmObjectCopy.realmSet$lunch(null);
        } else {
            Lunch cachelunch = (Lunch) cache.get(lunchObj);
            if (cachelunch != null) {
                realmObjectCopy.realmSet$lunch(cachelunch);
            } else {
                realmObjectCopy.realmSet$lunch(LunchRealmProxy.copyOrUpdate(realm, lunchObj, update, cache));
            }
        }
        Dinner dinnerObj = realmObjectSource.realmGet$dinner();
        if (dinnerObj == null) {
            realmObjectCopy.realmSet$dinner(null);
        } else {
            Dinner cachedinner = (Dinner) cache.get(dinnerObj);
            if (cachedinner != null) {
                realmObjectCopy.realmSet$dinner(cachedinner);
            } else {
                realmObjectCopy.realmSet$dinner(DinnerRealmProxy.copyOrUpdate(realm, dinnerObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$id(realmObjectSource.realmGet$id());
        realmObjectCopy.realmSet$calories(realmObjectSource.realmGet$calories());
        realmObjectCopy.realmSet$carbs(realmObjectSource.realmGet$carbs());
        realmObjectCopy.realmSet$fat(realmObjectSource.realmGet$fat());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$proteins(realmObjectSource.realmGet$proteins());
        realmObjectCopy.realmSet$quantity(realmObjectSource.realmGet$quantity());
        realmObjectCopy.realmSet$servingSize(realmObjectSource.realmGet$servingSize());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        return realmObject;
    }

    public static long insert(Realm realm, DietMealPlan object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(DietMealPlan.class);
        long tableNativePtr = table.getNativePtr();
        DietMealPlanColumnInfo columnInfo = (DietMealPlanColumnInfo) realm.getSchema().getColumnInfo(DietMealPlan.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        BreakFast breakFastObj = object.realmGet$breakFast();
        if (breakFastObj != null) {
            Long cachebreakFast = (Long) cache.get(breakFastObj);
            if (cachebreakFast == null) {
                cachebreakFast = Long.valueOf(BreakFastRealmProxy.insert(realm, breakFastObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.breakFastIndex, rowIndex, cachebreakFast.longValue(), false);
        }
        Lunch lunchObj = object.realmGet$lunch();
        if (lunchObj != null) {
            Long cachelunch = (Long) cache.get(lunchObj);
            if (cachelunch == null) {
                cachelunch = Long.valueOf(LunchRealmProxy.insert(realm, lunchObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.lunchIndex, rowIndex, cachelunch.longValue(), false);
        }
        Dinner dinnerObj = object.realmGet$dinner();
        if (dinnerObj != null) {
            Long cachedinner = (Long) cache.get(dinnerObj);
            if (cachedinner == null) {
                cachedinner = Long.valueOf(DinnerRealmProxy.insert(realm, dinnerObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.dinnerIndex, rowIndex, cachedinner.longValue(), false);
        }
        String realmGet$id = object.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        }
        String realmGet$calories = object.realmGet$calories();
        if (realmGet$calories != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.caloriesIndex, rowIndex, realmGet$calories, false);
        }
        String realmGet$carbs = object.realmGet$carbs();
        if (realmGet$carbs != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.carbsIndex, rowIndex, realmGet$carbs, false);
        }
        String realmGet$fat = object.realmGet$fat();
        if (realmGet$fat != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fatIndex, rowIndex, realmGet$fat, false);
        }
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$proteins = object.realmGet$proteins();
        if (realmGet$proteins != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.proteinsIndex, rowIndex, realmGet$proteins, false);
        }
        String realmGet$quantity = object.realmGet$quantity();
        if (realmGet$quantity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.quantityIndex, rowIndex, realmGet$quantity, false);
        }
        String realmGet$servingSize = object.realmGet$servingSize();
        if (realmGet$servingSize != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.servingSizeIndex, rowIndex, realmGet$servingSize, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(DietMealPlan.class);
        long tableNativePtr = table.getNativePtr();
        DietMealPlanColumnInfo columnInfo = (DietMealPlanColumnInfo) realm.getSchema().getColumnInfo(DietMealPlan.class);
        while (objects.hasNext()) {
            DietMealPlan object = (DietMealPlan) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    BreakFast breakFastObj = object.realmGet$breakFast();
                    if (breakFastObj != null) {
                        Long cachebreakFast = (Long) cache.get(breakFastObj);
                        if (cachebreakFast == null) {
                            cachebreakFast = Long.valueOf(BreakFastRealmProxy.insert(realm, breakFastObj, (Map) cache));
                        }
                        table.setLink(columnInfo.breakFastIndex, rowIndex, cachebreakFast.longValue(), false);
                    }
                    Lunch lunchObj = object.realmGet$lunch();
                    if (lunchObj != null) {
                        Long cachelunch = (Long) cache.get(lunchObj);
                        if (cachelunch == null) {
                            cachelunch = Long.valueOf(LunchRealmProxy.insert(realm, lunchObj, (Map) cache));
                        }
                        table.setLink(columnInfo.lunchIndex, rowIndex, cachelunch.longValue(), false);
                    }
                    Dinner dinnerObj = object.realmGet$dinner();
                    if (dinnerObj != null) {
                        Long cachedinner = (Long) cache.get(dinnerObj);
                        if (cachedinner == null) {
                            cachedinner = Long.valueOf(DinnerRealmProxy.insert(realm, dinnerObj, (Map) cache));
                        }
                        table.setLink(columnInfo.dinnerIndex, rowIndex, cachedinner.longValue(), false);
                    }
                    String realmGet$id = object.realmGet$id();
                    if (realmGet$id != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                    }
                    String realmGet$calories = object.realmGet$calories();
                    if (realmGet$calories != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.caloriesIndex, rowIndex, realmGet$calories, false);
                    }
                    String realmGet$carbs = object.realmGet$carbs();
                    if (realmGet$carbs != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.carbsIndex, rowIndex, realmGet$carbs, false);
                    }
                    String realmGet$fat = object.realmGet$fat();
                    if (realmGet$fat != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.fatIndex, rowIndex, realmGet$fat, false);
                    }
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    String realmGet$proteins = object.realmGet$proteins();
                    if (realmGet$proteins != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.proteinsIndex, rowIndex, realmGet$proteins, false);
                    }
                    String realmGet$quantity = object.realmGet$quantity();
                    if (realmGet$quantity != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.quantityIndex, rowIndex, realmGet$quantity, false);
                    }
                    String realmGet$servingSize = object.realmGet$servingSize();
                    if (realmGet$servingSize != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.servingSizeIndex, rowIndex, realmGet$servingSize, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, DietMealPlan object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(DietMealPlan.class);
        long tableNativePtr = table.getNativePtr();
        DietMealPlanColumnInfo columnInfo = (DietMealPlanColumnInfo) realm.getSchema().getColumnInfo(DietMealPlan.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        BreakFast breakFastObj = object.realmGet$breakFast();
        if (breakFastObj != null) {
            Long cachebreakFast = (Long) cache.get(breakFastObj);
            if (cachebreakFast == null) {
                cachebreakFast = Long.valueOf(BreakFastRealmProxy.insertOrUpdate(realm, breakFastObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.breakFastIndex, rowIndex, cachebreakFast.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.breakFastIndex, rowIndex);
        }
        Lunch lunchObj = object.realmGet$lunch();
        if (lunchObj != null) {
            Long cachelunch = (Long) cache.get(lunchObj);
            if (cachelunch == null) {
                cachelunch = Long.valueOf(LunchRealmProxy.insertOrUpdate(realm, lunchObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.lunchIndex, rowIndex, cachelunch.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.lunchIndex, rowIndex);
        }
        Dinner dinnerObj = object.realmGet$dinner();
        if (dinnerObj != null) {
            Long cachedinner = (Long) cache.get(dinnerObj);
            if (cachedinner == null) {
                cachedinner = Long.valueOf(DinnerRealmProxy.insertOrUpdate(realm, dinnerObj, (Map) cache));
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.dinnerIndex, rowIndex, cachedinner.longValue(), false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.dinnerIndex, rowIndex);
        }
        String realmGet$id = object.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
        }
        String realmGet$calories = object.realmGet$calories();
        if (realmGet$calories != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.caloriesIndex, rowIndex, realmGet$calories, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.caloriesIndex, rowIndex, false);
        }
        String realmGet$carbs = object.realmGet$carbs();
        if (realmGet$carbs != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.carbsIndex, rowIndex, realmGet$carbs, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.carbsIndex, rowIndex, false);
        }
        String realmGet$fat = object.realmGet$fat();
        if (realmGet$fat != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fatIndex, rowIndex, realmGet$fat, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fatIndex, rowIndex, false);
        }
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$proteins = object.realmGet$proteins();
        if (realmGet$proteins != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.proteinsIndex, rowIndex, realmGet$proteins, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.proteinsIndex, rowIndex, false);
        }
        String realmGet$quantity = object.realmGet$quantity();
        if (realmGet$quantity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.quantityIndex, rowIndex, realmGet$quantity, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.quantityIndex, rowIndex, false);
        }
        String realmGet$servingSize = object.realmGet$servingSize();
        if (realmGet$servingSize != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.servingSizeIndex, rowIndex, realmGet$servingSize, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.servingSizeIndex, rowIndex, false);
        }
        String realmGet$type = object.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(DietMealPlan.class);
        long tableNativePtr = table.getNativePtr();
        DietMealPlanColumnInfo columnInfo = (DietMealPlanColumnInfo) realm.getSchema().getColumnInfo(DietMealPlan.class);
        while (objects.hasNext()) {
            DietMealPlan object = (DietMealPlan) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    BreakFast breakFastObj = object.realmGet$breakFast();
                    if (breakFastObj != null) {
                        Long cachebreakFast = (Long) cache.get(breakFastObj);
                        if (cachebreakFast == null) {
                            cachebreakFast = Long.valueOf(BreakFastRealmProxy.insertOrUpdate(realm, breakFastObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.breakFastIndex, rowIndex, cachebreakFast.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.breakFastIndex, rowIndex);
                    }
                    Lunch lunchObj = object.realmGet$lunch();
                    if (lunchObj != null) {
                        Long cachelunch = (Long) cache.get(lunchObj);
                        if (cachelunch == null) {
                            cachelunch = Long.valueOf(LunchRealmProxy.insertOrUpdate(realm, lunchObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.lunchIndex, rowIndex, cachelunch.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.lunchIndex, rowIndex);
                    }
                    Dinner dinnerObj = object.realmGet$dinner();
                    if (dinnerObj != null) {
                        Long cachedinner = (Long) cache.get(dinnerObj);
                        if (cachedinner == null) {
                            cachedinner = Long.valueOf(DinnerRealmProxy.insertOrUpdate(realm, dinnerObj, (Map) cache));
                        }
                        Table.nativeSetLink(tableNativePtr, columnInfo.dinnerIndex, rowIndex, cachedinner.longValue(), false);
                    } else {
                        Table.nativeNullifyLink(tableNativePtr, columnInfo.dinnerIndex, rowIndex);
                    }
                    String realmGet$id = object.realmGet$id();
                    if (realmGet$id != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
                    }
                    String realmGet$calories = object.realmGet$calories();
                    if (realmGet$calories != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.caloriesIndex, rowIndex, realmGet$calories, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.caloriesIndex, rowIndex, false);
                    }
                    String realmGet$carbs = object.realmGet$carbs();
                    if (realmGet$carbs != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.carbsIndex, rowIndex, realmGet$carbs, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.carbsIndex, rowIndex, false);
                    }
                    String realmGet$fat = object.realmGet$fat();
                    if (realmGet$fat != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.fatIndex, rowIndex, realmGet$fat, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.fatIndex, rowIndex, false);
                    }
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                    }
                    String realmGet$proteins = object.realmGet$proteins();
                    if (realmGet$proteins != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.proteinsIndex, rowIndex, realmGet$proteins, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.proteinsIndex, rowIndex, false);
                    }
                    String realmGet$quantity = object.realmGet$quantity();
                    if (realmGet$quantity != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.quantityIndex, rowIndex, realmGet$quantity, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.quantityIndex, rowIndex, false);
                    }
                    String realmGet$servingSize = object.realmGet$servingSize();
                    if (realmGet$servingSize != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.servingSizeIndex, rowIndex, realmGet$servingSize, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.servingSizeIndex, rowIndex, false);
                    }
                    String realmGet$type = object.realmGet$type();
                    if (realmGet$type != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static DietMealPlan createDetachedCopy(DietMealPlan realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        DietMealPlan unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new DietMealPlan();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (DietMealPlan) cachedObject.object;
        } else {
            unmanagedObject = (DietMealPlan) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        DietMealPlanRealmProxyInterface unmanagedCopy = unmanagedObject;
        DietMealPlanRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$breakFast(BreakFastRealmProxy.createDetachedCopy(realmSource.realmGet$breakFast(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$lunch(LunchRealmProxy.createDetachedCopy(realmSource.realmGet$lunch(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$dinner(DinnerRealmProxy.createDetachedCopy(realmSource.realmGet$dinner(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$calories(realmSource.realmGet$calories());
        unmanagedCopy.realmSet$carbs(realmSource.realmGet$carbs());
        unmanagedCopy.realmSet$fat(realmSource.realmGet$fat());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$proteins(realmSource.realmGet$proteins());
        unmanagedCopy.realmSet$quantity(realmSource.realmGet$quantity());
        unmanagedCopy.realmSet$servingSize(realmSource.realmGet$servingSize());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DietMealPlan = proxy[");
        stringBuilder.append("{breakFast:");
        stringBuilder.append(realmGet$breakFast() != null ? "BreakFast" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{lunch:");
        stringBuilder.append(realmGet$lunch() != null ? "Lunch" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dinner:");
        stringBuilder.append(realmGet$dinner() != null ? "Dinner" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{calories:");
        stringBuilder.append(realmGet$calories() != null ? realmGet$calories() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{carbs:");
        stringBuilder.append(realmGet$carbs() != null ? realmGet$carbs() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{fat:");
        stringBuilder.append(realmGet$fat() != null ? realmGet$fat() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{proteins:");
        stringBuilder.append(realmGet$proteins() != null ? realmGet$proteins() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{quantity:");
        stringBuilder.append(realmGet$quantity() != null ? realmGet$quantity() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{servingSize:");
        stringBuilder.append(realmGet$servingSize() != null ? realmGet$servingSize() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
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
        DietMealPlanRealmProxy aDietMealPlan = (DietMealPlanRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aDietMealPlan.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDietMealPlan.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aDietMealPlan.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
