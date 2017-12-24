package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.MealPlan;
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

public class MealPlanRealmProxy extends MealPlan implements RealmObjectProxy, MealPlanRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private MealPlanColumnInfo columnInfo;
    private ProxyState<MealPlan> proxyState;

    static final class MealPlanColumnInfo extends ColumnInfo {
        long caloriesIndex;
        long carbsIndex;
        long dietIdIndex;
        long dietRecomendationIndex;
        long fiberIndex;
        long nameIndex;
        long tagIdIndex;

        MealPlanColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("MealPlan");
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.dietIdIndex = addColumnDetails("dietId", objectSchemaInfo);
            this.tagIdIndex = addColumnDetails("tagId", objectSchemaInfo);
            this.dietRecomendationIndex = addColumnDetails("dietRecomendation", objectSchemaInfo);
            this.caloriesIndex = addColumnDetails("calories", objectSchemaInfo);
            this.carbsIndex = addColumnDetails("carbs", objectSchemaInfo);
            this.fiberIndex = addColumnDetails("fiber", objectSchemaInfo);
        }

        MealPlanColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new MealPlanColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            MealPlanColumnInfo src = (MealPlanColumnInfo) rawSrc;
            MealPlanColumnInfo dst = (MealPlanColumnInfo) rawDst;
            dst.nameIndex = src.nameIndex;
            dst.dietIdIndex = src.dietIdIndex;
            dst.tagIdIndex = src.tagIdIndex;
            dst.dietRecomendationIndex = src.dietRecomendationIndex;
            dst.caloriesIndex = src.caloriesIndex;
            dst.carbsIndex = src.carbsIndex;
            dst.fiberIndex = src.fiberIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("name");
        fieldNames.add("dietId");
        fieldNames.add("tagId");
        fieldNames.add("dietRecomendation");
        fieldNames.add("calories");
        fieldNames.add("carbs");
        fieldNames.add("fiber");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    MealPlanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (MealPlanColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
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

    public String realmGet$dietId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.dietIdIndex);
    }

    public void realmSet$dietId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.dietIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.dietIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.dietIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.dietIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$tagId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.tagIdIndex);
    }

    public void realmSet$tagId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.tagIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.tagIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.tagIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.tagIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$dietRecomendation() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.dietRecomendationIndex);
    }

    public void realmSet$dietRecomendation(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.dietRecomendationIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.dietRecomendationIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.dietRecomendationIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.dietRecomendationIndex, row.getIndex(), value, true);
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

    public String realmGet$fiber() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.fiberIndex);
    }

    public void realmSet$fiber(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.fiberIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.fiberIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.fiberIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.fiberIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("MealPlan");
        builder.addPersistedProperty("name", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("dietId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("tagId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("dietRecomendation", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("calories", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("carbs", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("fiber", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MealPlanColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new MealPlanColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_MealPlan";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static MealPlan createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        MealPlan obj = (MealPlan) realm.createObjectInternal(MealPlan.class, true, Collections.emptyList());
        MealPlanRealmProxyInterface objProxy = obj;
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name(json.getString("name"));
            }
        }
        if (json.has("dietId")) {
            if (json.isNull("dietId")) {
                objProxy.realmSet$dietId(null);
            } else {
                objProxy.realmSet$dietId(json.getString("dietId"));
            }
        }
        if (json.has("tagId")) {
            if (json.isNull("tagId")) {
                objProxy.realmSet$tagId(null);
            } else {
                objProxy.realmSet$tagId(json.getString("tagId"));
            }
        }
        if (json.has("dietRecomendation")) {
            if (json.isNull("dietRecomendation")) {
                objProxy.realmSet$dietRecomendation(null);
            } else {
                objProxy.realmSet$dietRecomendation(json.getString("dietRecomendation"));
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
        if (json.has("fiber")) {
            if (json.isNull("fiber")) {
                objProxy.realmSet$fiber(null);
            } else {
                objProxy.realmSet$fiber(json.getString("fiber"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static MealPlan createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        MealPlan obj = new MealPlan();
        MealPlanRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("dietId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$dietId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$dietId(null);
                }
            } else if (name.equals("tagId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tagId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tagId(null);
                }
            } else if (name.equals("dietRecomendation")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$dietRecomendation(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$dietRecomendation(null);
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
            } else if (!name.equals("fiber")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$fiber(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$fiber(null);
            }
        }
        reader.endObject();
        return (MealPlan) realm.copyToRealm(obj);
    }

    public static MealPlan copyOrUpdate(Realm realm, MealPlan object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (MealPlan) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static MealPlan copy(Realm realm, MealPlan newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (MealPlan) cachedRealmObject;
        }
        MealPlan realmObject = (MealPlan) realm.createObjectInternal(MealPlan.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        MealPlanRealmProxyInterface realmObjectSource = newObject;
        MealPlanRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$dietId(realmObjectSource.realmGet$dietId());
        realmObjectCopy.realmSet$tagId(realmObjectSource.realmGet$tagId());
        realmObjectCopy.realmSet$dietRecomendation(realmObjectSource.realmGet$dietRecomendation());
        realmObjectCopy.realmSet$calories(realmObjectSource.realmGet$calories());
        realmObjectCopy.realmSet$carbs(realmObjectSource.realmGet$carbs());
        realmObjectCopy.realmSet$fiber(realmObjectSource.realmGet$fiber());
        return realmObject;
    }

    public static long insert(Realm realm, MealPlan object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(MealPlan.class);
        long tableNativePtr = table.getNativePtr();
        MealPlanColumnInfo columnInfo = (MealPlanColumnInfo) realm.getSchema().getColumnInfo(MealPlan.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$dietId = object.realmGet$dietId();
        if (realmGet$dietId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dietIdIndex, rowIndex, realmGet$dietId, false);
        }
        String realmGet$tagId = object.realmGet$tagId();
        if (realmGet$tagId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tagIdIndex, rowIndex, realmGet$tagId, false);
        }
        String realmGet$dietRecomendation = object.realmGet$dietRecomendation();
        if (realmGet$dietRecomendation != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dietRecomendationIndex, rowIndex, realmGet$dietRecomendation, false);
        }
        String realmGet$calories = object.realmGet$calories();
        if (realmGet$calories != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.caloriesIndex, rowIndex, realmGet$calories, false);
        }
        String realmGet$carbs = object.realmGet$carbs();
        if (realmGet$carbs != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.carbsIndex, rowIndex, realmGet$carbs, false);
        }
        String realmGet$fiber = object.realmGet$fiber();
        if (realmGet$fiber == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.fiberIndex, rowIndex, realmGet$fiber, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(MealPlan.class);
        long tableNativePtr = table.getNativePtr();
        MealPlanColumnInfo columnInfo = (MealPlanColumnInfo) realm.getSchema().getColumnInfo(MealPlan.class);
        while (objects.hasNext()) {
            MealPlan object = (MealPlan) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    }
                    String realmGet$dietId = object.realmGet$dietId();
                    if (realmGet$dietId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.dietIdIndex, rowIndex, realmGet$dietId, false);
                    }
                    String realmGet$tagId = object.realmGet$tagId();
                    if (realmGet$tagId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tagIdIndex, rowIndex, realmGet$tagId, false);
                    }
                    String realmGet$dietRecomendation = object.realmGet$dietRecomendation();
                    if (realmGet$dietRecomendation != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.dietRecomendationIndex, rowIndex, realmGet$dietRecomendation, false);
                    }
                    String realmGet$calories = object.realmGet$calories();
                    if (realmGet$calories != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.caloriesIndex, rowIndex, realmGet$calories, false);
                    }
                    String realmGet$carbs = object.realmGet$carbs();
                    if (realmGet$carbs != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.carbsIndex, rowIndex, realmGet$carbs, false);
                    }
                    String realmGet$fiber = object.realmGet$fiber();
                    if (realmGet$fiber != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.fiberIndex, rowIndex, realmGet$fiber, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, MealPlan object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(MealPlan.class);
        long tableNativePtr = table.getNativePtr();
        MealPlanColumnInfo columnInfo = (MealPlanColumnInfo) realm.getSchema().getColumnInfo(MealPlan.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$name = object.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$dietId = object.realmGet$dietId();
        if (realmGet$dietId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dietIdIndex, rowIndex, realmGet$dietId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dietIdIndex, rowIndex, false);
        }
        String realmGet$tagId = object.realmGet$tagId();
        if (realmGet$tagId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tagIdIndex, rowIndex, realmGet$tagId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tagIdIndex, rowIndex, false);
        }
        String realmGet$dietRecomendation = object.realmGet$dietRecomendation();
        if (realmGet$dietRecomendation != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dietRecomendationIndex, rowIndex, realmGet$dietRecomendation, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dietRecomendationIndex, rowIndex, false);
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
        String realmGet$fiber = object.realmGet$fiber();
        if (realmGet$fiber != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fiberIndex, rowIndex, realmGet$fiber, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.fiberIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(MealPlan.class);
        long tableNativePtr = table.getNativePtr();
        MealPlanColumnInfo columnInfo = (MealPlanColumnInfo) realm.getSchema().getColumnInfo(MealPlan.class);
        while (objects.hasNext()) {
            MealPlan object = (MealPlan) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$name = object.realmGet$name();
                    if (realmGet$name != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                    }
                    String realmGet$dietId = object.realmGet$dietId();
                    if (realmGet$dietId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.dietIdIndex, rowIndex, realmGet$dietId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.dietIdIndex, rowIndex, false);
                    }
                    String realmGet$tagId = object.realmGet$tagId();
                    if (realmGet$tagId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tagIdIndex, rowIndex, realmGet$tagId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.tagIdIndex, rowIndex, false);
                    }
                    String realmGet$dietRecomendation = object.realmGet$dietRecomendation();
                    if (realmGet$dietRecomendation != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.dietRecomendationIndex, rowIndex, realmGet$dietRecomendation, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.dietRecomendationIndex, rowIndex, false);
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
                    String realmGet$fiber = object.realmGet$fiber();
                    if (realmGet$fiber != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.fiberIndex, rowIndex, realmGet$fiber, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.fiberIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static MealPlan createDetachedCopy(MealPlan realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        MealPlan unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new MealPlan();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (MealPlan) cachedObject.object;
        } else {
            unmanagedObject = (MealPlan) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        MealPlanRealmProxyInterface unmanagedCopy = unmanagedObject;
        MealPlanRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$dietId(realmSource.realmGet$dietId());
        unmanagedCopy.realmSet$tagId(realmSource.realmGet$tagId());
        unmanagedCopy.realmSet$dietRecomendation(realmSource.realmGet$dietRecomendation());
        unmanagedCopy.realmSet$calories(realmSource.realmGet$calories());
        unmanagedCopy.realmSet$carbs(realmSource.realmGet$carbs());
        unmanagedCopy.realmSet$fiber(realmSource.realmGet$fiber());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("MealPlan = proxy[");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dietId:");
        stringBuilder.append(realmGet$dietId() != null ? realmGet$dietId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tagId:");
        stringBuilder.append(realmGet$tagId() != null ? realmGet$tagId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dietRecomendation:");
        stringBuilder.append(realmGet$dietRecomendation() != null ? realmGet$dietRecomendation() : "null");
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
        stringBuilder.append("{fiber:");
        stringBuilder.append(realmGet$fiber() != null ? realmGet$fiber() : "null");
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
        MealPlanRealmProxy aMealPlan = (MealPlanRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aMealPlan.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aMealPlan.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aMealPlan.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
