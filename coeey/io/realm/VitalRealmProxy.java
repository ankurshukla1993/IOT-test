package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.Vital;
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

public class VitalRealmProxy extends Vital implements RealmObjectProxy, VitalRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private VitalColumnInfo columnInfo;
    private ProxyState<Vital> proxyState;

    static final class VitalColumnInfo extends ColumnInfo {
        long contextIdIndex;
        long contextTypeIndex;
        long deviceReadingIndex;
        long deviceTypeIndex;
        long isSyncIndex;
        long parametersIndex;
        long platformIndex;
        long postActionIndex;
        long takenByIndex;
        long takenOnIndex;
        long tenantIdIndex;
        long timestampIndex;
        long userIdIndex;
        long vitalIdIndex;
        long vitalTypeIndex;

        VitalColumnInfo(OsSchemaInfo schemaInfo) {
            super(15);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Vital");
            this.vitalIdIndex = addColumnDetails("vitalId", objectSchemaInfo);
            this.vitalTypeIndex = addColumnDetails("vitalType", objectSchemaInfo);
            this.takenOnIndex = addColumnDetails("takenOn", objectSchemaInfo);
            this.parametersIndex = addColumnDetails("parameters", objectSchemaInfo);
            this.userIdIndex = addColumnDetails("userId", objectSchemaInfo);
            this.tenantIdIndex = addColumnDetails("tenantId", objectSchemaInfo);
            this.timestampIndex = addColumnDetails(AppMeasurement$Param.TIMESTAMP, objectSchemaInfo);
            this.isSyncIndex = addColumnDetails("isSync", objectSchemaInfo);
            this.postActionIndex = addColumnDetails("postAction", objectSchemaInfo);
            this.takenByIndex = addColumnDetails("takenBy", objectSchemaInfo);
            this.deviceTypeIndex = addColumnDetails("deviceType", objectSchemaInfo);
            this.platformIndex = addColumnDetails("platform", objectSchemaInfo);
            this.deviceReadingIndex = addColumnDetails("deviceReading", objectSchemaInfo);
            this.contextTypeIndex = addColumnDetails("contextType", objectSchemaInfo);
            this.contextIdIndex = addColumnDetails("contextId", objectSchemaInfo);
        }

        VitalColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new VitalColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            VitalColumnInfo src = (VitalColumnInfo) rawSrc;
            VitalColumnInfo dst = (VitalColumnInfo) rawDst;
            dst.vitalIdIndex = src.vitalIdIndex;
            dst.vitalTypeIndex = src.vitalTypeIndex;
            dst.takenOnIndex = src.takenOnIndex;
            dst.parametersIndex = src.parametersIndex;
            dst.userIdIndex = src.userIdIndex;
            dst.tenantIdIndex = src.tenantIdIndex;
            dst.timestampIndex = src.timestampIndex;
            dst.isSyncIndex = src.isSyncIndex;
            dst.postActionIndex = src.postActionIndex;
            dst.takenByIndex = src.takenByIndex;
            dst.deviceTypeIndex = src.deviceTypeIndex;
            dst.platformIndex = src.platformIndex;
            dst.deviceReadingIndex = src.deviceReadingIndex;
            dst.contextTypeIndex = src.contextTypeIndex;
            dst.contextIdIndex = src.contextIdIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add("vitalId");
        fieldNames.add("vitalType");
        fieldNames.add("takenOn");
        fieldNames.add("parameters");
        fieldNames.add("userId");
        fieldNames.add("tenantId");
        fieldNames.add(AppMeasurement$Param.TIMESTAMP);
        fieldNames.add("isSync");
        fieldNames.add("postAction");
        fieldNames.add("takenBy");
        fieldNames.add("deviceType");
        fieldNames.add("platform");
        fieldNames.add("deviceReading");
        fieldNames.add("contextType");
        fieldNames.add("contextId");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    VitalRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (VitalColumnInfo) context.getColumnInfo();
            this.proxyState = new ProxyState(this);
            this.proxyState.setRealm$realm(context.getRealm());
            this.proxyState.setRow$realm(context.getRow());
            this.proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(context.getExcludeFields());
        }
    }

    public String realmGet$vitalId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.vitalIdIndex);
    }

    public void realmSet$vitalId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'vitalId' cannot be changed after object was created.");
        }
    }

    public String realmGet$vitalType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.vitalTypeIndex);
    }

    public void realmSet$vitalType(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.vitalTypeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.vitalTypeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.vitalTypeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.vitalTypeIndex, row.getIndex(), value, true);
            }
        }
    }

    public long realmGet$takenOn() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.takenOnIndex);
    }

    public void realmSet$takenOn(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.takenOnIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.takenOnIndex, row.getIndex(), value, true);
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

    public boolean realmGet$isSync() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isSyncIndex);
    }

    public void realmSet$isSync(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isSyncIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.isSyncIndex, row.getIndex(), value, true);
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

    public String realmGet$takenBy() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.takenByIndex);
    }

    public void realmSet$takenBy(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.takenByIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.takenByIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.takenByIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.takenByIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$deviceType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.deviceTypeIndex);
    }

    public void realmSet$deviceType(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.deviceTypeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.deviceTypeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.deviceTypeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.deviceTypeIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$platform() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.platformIndex);
    }

    public void realmSet$platform(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.platformIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.platformIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.platformIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.platformIndex, row.getIndex(), value, true);
            }
        }
    }

    public boolean realmGet$deviceReading() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.deviceReadingIndex);
    }

    public void realmSet$deviceReading(boolean value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.deviceReadingIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setBoolean(this.columnInfo.deviceReadingIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$contextType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.contextTypeIndex);
    }

    public void realmSet$contextType(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.contextTypeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.contextTypeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.contextTypeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.contextTypeIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$contextId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.contextIdIndex);
    }

    public void realmSet$contextId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.contextIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.contextIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.contextIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.contextIdIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("Vital");
        builder.addPersistedProperty("vitalId", RealmFieldType.STRING, true, true, false);
        builder.addPersistedProperty("vitalType", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("takenOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("parameters", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("userId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("tenantId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty(AppMeasurement$Param.TIMESTAMP, RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("isSync", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("postAction", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("takenBy", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("deviceType", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("platform", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("deviceReading", RealmFieldType.BOOLEAN, false, false, true);
        builder.addPersistedProperty("contextType", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("contextId", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static VitalColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new VitalColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_Vital";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Vital createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        List<String> excludeFields = Collections.emptyList();
        Vital vital = null;
        if (update) {
            long rowIndex;
            Table table = realm.getTable(Vital.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (json.isNull("vitalId")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("vitalId"));
            }
            if (rowIndex != -1) {
                BaseRealm$RealmObjectContext objectContext = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Vital.class), false, Collections.emptyList());
                    vital = new VitalRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (vital == null) {
            if (!json.has("vitalId")) {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'vitalId'.");
            } else if (json.isNull("vitalId")) {
                vital = (VitalRealmProxy) realm.createObjectInternal(Vital.class, null, true, excludeFields);
            } else {
                VitalRealmProxy obj = (VitalRealmProxy) realm.createObjectInternal(Vital.class, json.getString("vitalId"), true, excludeFields);
            }
        }
        VitalRealmProxyInterface objProxy = vital;
        if (json.has("vitalType")) {
            if (json.isNull("vitalType")) {
                objProxy.realmSet$vitalType(null);
            } else {
                objProxy.realmSet$vitalType(json.getString("vitalType"));
            }
        }
        if (json.has("takenOn")) {
            if (json.isNull("takenOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'takenOn' to null.");
            }
            objProxy.realmSet$takenOn(json.getLong("takenOn"));
        }
        if (json.has("parameters")) {
            if (json.isNull("parameters")) {
                objProxy.realmSet$parameters(null);
            } else {
                objProxy.realmSet$parameters(json.getString("parameters"));
            }
        }
        if (json.has("userId")) {
            if (json.isNull("userId")) {
                objProxy.realmSet$userId(null);
            } else {
                objProxy.realmSet$userId(json.getString("userId"));
            }
        }
        if (json.has("tenantId")) {
            if (json.isNull("tenantId")) {
                objProxy.realmSet$tenantId(null);
            } else {
                objProxy.realmSet$tenantId(json.getString("tenantId"));
            }
        }
        if (json.has(AppMeasurement$Param.TIMESTAMP)) {
            if (json.isNull(AppMeasurement$Param.TIMESTAMP)) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
            }
            objProxy.realmSet$timestamp(json.getLong(AppMeasurement$Param.TIMESTAMP));
        }
        if (json.has("isSync")) {
            if (json.isNull("isSync")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isSync' to null.");
            }
            objProxy.realmSet$isSync(json.getBoolean("isSync"));
        }
        if (json.has("postAction")) {
            if (json.isNull("postAction")) {
                objProxy.realmSet$postAction(null);
            } else {
                objProxy.realmSet$postAction(json.getString("postAction"));
            }
        }
        if (json.has("takenBy")) {
            if (json.isNull("takenBy")) {
                objProxy.realmSet$takenBy(null);
            } else {
                objProxy.realmSet$takenBy(json.getString("takenBy"));
            }
        }
        if (json.has("deviceType")) {
            if (json.isNull("deviceType")) {
                objProxy.realmSet$deviceType(null);
            } else {
                objProxy.realmSet$deviceType(json.getString("deviceType"));
            }
        }
        if (json.has("platform")) {
            if (json.isNull("platform")) {
                objProxy.realmSet$platform(null);
            } else {
                objProxy.realmSet$platform(json.getString("platform"));
            }
        }
        if (json.has("deviceReading")) {
            if (json.isNull("deviceReading")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'deviceReading' to null.");
            }
            objProxy.realmSet$deviceReading(json.getBoolean("deviceReading"));
        }
        if (json.has("contextType")) {
            if (json.isNull("contextType")) {
                objProxy.realmSet$contextType(null);
            } else {
                objProxy.realmSet$contextType(json.getString("contextType"));
            }
        }
        if (json.has("contextId")) {
            if (json.isNull("contextId")) {
                objProxy.realmSet$contextId(null);
            } else {
                objProxy.realmSet$contextId(json.getString("contextId"));
            }
        }
        return vital;
    }

    @TargetApi(11)
    public static Vital createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        boolean jsonHasPrimaryKey = false;
        Vital obj = new Vital();
        VitalRealmProxyInterface objProxy = obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("vitalId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$vitalId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$vitalId(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("vitalType")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$vitalType(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$vitalType(null);
                }
            } else if (name.equals("takenOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$takenOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'takenOn' to null.");
                }
            } else if (name.equals("parameters")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$parameters(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$parameters(null);
                }
            } else if (name.equals("userId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$userId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$userId(null);
                }
            } else if (name.equals("tenantId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tenantId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tenantId(null);
                }
            } else if (name.equals(AppMeasurement$Param.TIMESTAMP)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$timestamp(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
                }
            } else if (name.equals("isSync")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$isSync(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isSync' to null.");
                }
            } else if (name.equals("postAction")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$postAction(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$postAction(null);
                }
            } else if (name.equals("takenBy")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$takenBy(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$takenBy(null);
                }
            } else if (name.equals("deviceType")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$deviceType(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$deviceType(null);
                }
            } else if (name.equals("platform")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$platform(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$platform(null);
                }
            } else if (name.equals("deviceReading")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$deviceReading(reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'deviceReading' to null.");
                }
            } else if (name.equals("contextType")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$contextType(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$contextType(null);
                }
            } else if (!name.equals("contextId")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$contextId(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$contextId(null);
            }
        }
        reader.endObject();
        if (jsonHasPrimaryKey) {
            return (Vital) realm.copyToRealm(obj);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'vitalId'.");
    }

    public static Vital copyOrUpdate(Realm realm, Vital object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (Vital) cachedRealmObject;
        }
        Vital update2;
        Vital realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            long rowIndex;
            Table table = realm.getTable(Vital.class);
            long pkColumnIndex = table.getPrimaryKey();
            String value = object.realmGet$vitalId();
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, value);
            }
            if (rowIndex == -1) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(Vital.class), false, Collections.emptyList());
                    Vital realmObject2 = new VitalRealmProxy();
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

    public static Vital copy(Realm realm, Vital newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (Vital) cachedRealmObject;
        }
        Vital realmObject = (Vital) realm.createObjectInternal(Vital.class, newObject.realmGet$vitalId(), false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        VitalRealmProxyInterface realmObjectSource = newObject;
        VitalRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$vitalType(realmObjectSource.realmGet$vitalType());
        realmObjectCopy.realmSet$takenOn(realmObjectSource.realmGet$takenOn());
        realmObjectCopy.realmSet$parameters(realmObjectSource.realmGet$parameters());
        realmObjectCopy.realmSet$userId(realmObjectSource.realmGet$userId());
        realmObjectCopy.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectCopy.realmSet$timestamp(realmObjectSource.realmGet$timestamp());
        realmObjectCopy.realmSet$isSync(realmObjectSource.realmGet$isSync());
        realmObjectCopy.realmSet$postAction(realmObjectSource.realmGet$postAction());
        realmObjectCopy.realmSet$takenBy(realmObjectSource.realmGet$takenBy());
        realmObjectCopy.realmSet$deviceType(realmObjectSource.realmGet$deviceType());
        realmObjectCopy.realmSet$platform(realmObjectSource.realmGet$platform());
        realmObjectCopy.realmSet$deviceReading(realmObjectSource.realmGet$deviceReading());
        realmObjectCopy.realmSet$contextType(realmObjectSource.realmGet$contextType());
        realmObjectCopy.realmSet$contextId(realmObjectSource.realmGet$contextId());
        return realmObject;
    }

    public static long insert(Realm realm, Vital object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Vital.class);
        long tableNativePtr = table.getNativePtr();
        VitalColumnInfo columnInfo = (VitalColumnInfo) realm.getSchema().getColumnInfo(Vital.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = object.realmGet$vitalId();
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
        String realmGet$vitalType = object.realmGet$vitalType();
        if (realmGet$vitalType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.vitalTypeIndex, rowIndex, realmGet$vitalType, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.takenOnIndex, rowIndex, object.realmGet$takenOn(), false);
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        }
        String realmGet$userId = object.realmGet$userId();
        if (realmGet$userId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
        }
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isSyncIndex, rowIndex, object.realmGet$isSync(), false);
        String realmGet$postAction = object.realmGet$postAction();
        if (realmGet$postAction != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.postActionIndex, rowIndex, realmGet$postAction, false);
        }
        String realmGet$takenBy = object.realmGet$takenBy();
        if (realmGet$takenBy != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.takenByIndex, rowIndex, realmGet$takenBy, false);
        }
        String realmGet$deviceType = object.realmGet$deviceType();
        if (realmGet$deviceType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.deviceTypeIndex, rowIndex, realmGet$deviceType, false);
        }
        String realmGet$platform = object.realmGet$platform();
        if (realmGet$platform != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.platformIndex, rowIndex, realmGet$platform, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.deviceReadingIndex, rowIndex, object.realmGet$deviceReading(), false);
        String realmGet$contextType = object.realmGet$contextType();
        if (realmGet$contextType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contextTypeIndex, rowIndex, realmGet$contextType, false);
        }
        String realmGet$contextId = object.realmGet$contextId();
        if (realmGet$contextId == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.contextIdIndex, rowIndex, realmGet$contextId, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Vital.class);
        long tableNativePtr = table.getNativePtr();
        VitalColumnInfo columnInfo = (VitalColumnInfo) realm.getSchema().getColumnInfo(Vital.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Vital object = (Vital) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex;
                    String primaryKeyValue = object.realmGet$vitalId();
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
                    String realmGet$vitalType = object.realmGet$vitalType();
                    if (realmGet$vitalType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.vitalTypeIndex, rowIndex, realmGet$vitalType, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.takenOnIndex, rowIndex, object.realmGet$takenOn(), false);
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    }
                    String realmGet$userId = object.realmGet$userId();
                    if (realmGet$userId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
                    }
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.isSyncIndex, rowIndex, object.realmGet$isSync(), false);
                    String realmGet$postAction = object.realmGet$postAction();
                    if (realmGet$postAction != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.postActionIndex, rowIndex, realmGet$postAction, false);
                    }
                    String realmGet$takenBy = object.realmGet$takenBy();
                    if (realmGet$takenBy != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.takenByIndex, rowIndex, realmGet$takenBy, false);
                    }
                    String realmGet$deviceType = object.realmGet$deviceType();
                    if (realmGet$deviceType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.deviceTypeIndex, rowIndex, realmGet$deviceType, false);
                    }
                    String realmGet$platform = object.realmGet$platform();
                    if (realmGet$platform != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.platformIndex, rowIndex, realmGet$platform, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.deviceReadingIndex, rowIndex, object.realmGet$deviceReading(), false);
                    String realmGet$contextType = object.realmGet$contextType();
                    if (realmGet$contextType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.contextTypeIndex, rowIndex, realmGet$contextType, false);
                    }
                    String realmGet$contextId = object.realmGet$contextId();
                    if (realmGet$contextId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.contextIdIndex, rowIndex, realmGet$contextId, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Vital object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        long rowIndex;
        Table table = realm.getTable(Vital.class);
        long tableNativePtr = table.getNativePtr();
        VitalColumnInfo columnInfo = (VitalColumnInfo) realm.getSchema().getColumnInfo(Vital.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = object.realmGet$vitalId();
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == -1) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
        }
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$vitalType = object.realmGet$vitalType();
        if (realmGet$vitalType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.vitalTypeIndex, rowIndex, realmGet$vitalType, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.vitalTypeIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.takenOnIndex, rowIndex, object.realmGet$takenOn(), false);
        String realmGet$parameters = object.realmGet$parameters();
        if (realmGet$parameters != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
        }
        String realmGet$userId = object.realmGet$userId();
        if (realmGet$userId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.userIdIndex, rowIndex, false);
        }
        String realmGet$tenantId = object.realmGet$tenantId();
        if (realmGet$tenantId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isSyncIndex, rowIndex, object.realmGet$isSync(), false);
        String realmGet$postAction = object.realmGet$postAction();
        if (realmGet$postAction != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.postActionIndex, rowIndex, realmGet$postAction, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.postActionIndex, rowIndex, false);
        }
        String realmGet$takenBy = object.realmGet$takenBy();
        if (realmGet$takenBy != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.takenByIndex, rowIndex, realmGet$takenBy, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.takenByIndex, rowIndex, false);
        }
        String realmGet$deviceType = object.realmGet$deviceType();
        if (realmGet$deviceType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.deviceTypeIndex, rowIndex, realmGet$deviceType, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.deviceTypeIndex, rowIndex, false);
        }
        String realmGet$platform = object.realmGet$platform();
        if (realmGet$platform != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.platformIndex, rowIndex, realmGet$platform, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.platformIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.deviceReadingIndex, rowIndex, object.realmGet$deviceReading(), false);
        String realmGet$contextType = object.realmGet$contextType();
        if (realmGet$contextType != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contextTypeIndex, rowIndex, realmGet$contextType, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.contextTypeIndex, rowIndex, false);
        }
        String realmGet$contextId = object.realmGet$contextId();
        if (realmGet$contextId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contextIdIndex, rowIndex, realmGet$contextId, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.contextIdIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(Vital.class);
        long tableNativePtr = table.getNativePtr();
        VitalColumnInfo columnInfo = (VitalColumnInfo) realm.getSchema().getColumnInfo(Vital.class);
        long pkColumnIndex = table.getPrimaryKey();
        while (objects.hasNext()) {
            Vital object = (Vital) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex;
                    String primaryKeyValue = object.realmGet$vitalId();
                    if (primaryKeyValue == null) {
                        rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                    } else {
                        rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                    }
                    if (rowIndex == -1) {
                        rowIndex = OsObject.createRowWithPrimaryKey(table, primaryKeyValue);
                    }
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$vitalType = object.realmGet$vitalType();
                    if (realmGet$vitalType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.vitalTypeIndex, rowIndex, realmGet$vitalType, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.vitalTypeIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.takenOnIndex, rowIndex, object.realmGet$takenOn(), false);
                    String realmGet$parameters = object.realmGet$parameters();
                    if (realmGet$parameters != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.parametersIndex, rowIndex, realmGet$parameters, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.parametersIndex, rowIndex, false);
                    }
                    String realmGet$userId = object.realmGet$userId();
                    if (realmGet$userId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.userIdIndex, rowIndex, realmGet$userId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.userIdIndex, rowIndex, false);
                    }
                    String realmGet$tenantId = object.realmGet$tenantId();
                    if (realmGet$tenantId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, realmGet$tenantId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.tenantIdIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.isSyncIndex, rowIndex, object.realmGet$isSync(), false);
                    String realmGet$postAction = object.realmGet$postAction();
                    if (realmGet$postAction != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.postActionIndex, rowIndex, realmGet$postAction, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.postActionIndex, rowIndex, false);
                    }
                    String realmGet$takenBy = object.realmGet$takenBy();
                    if (realmGet$takenBy != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.takenByIndex, rowIndex, realmGet$takenBy, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.takenByIndex, rowIndex, false);
                    }
                    String realmGet$deviceType = object.realmGet$deviceType();
                    if (realmGet$deviceType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.deviceTypeIndex, rowIndex, realmGet$deviceType, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.deviceTypeIndex, rowIndex, false);
                    }
                    String realmGet$platform = object.realmGet$platform();
                    if (realmGet$platform != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.platformIndex, rowIndex, realmGet$platform, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.platformIndex, rowIndex, false);
                    }
                    Table.nativeSetBoolean(tableNativePtr, columnInfo.deviceReadingIndex, rowIndex, object.realmGet$deviceReading(), false);
                    String realmGet$contextType = object.realmGet$contextType();
                    if (realmGet$contextType != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.contextTypeIndex, rowIndex, realmGet$contextType, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.contextTypeIndex, rowIndex, false);
                    }
                    String realmGet$contextId = object.realmGet$contextId();
                    if (realmGet$contextId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.contextIdIndex, rowIndex, realmGet$contextId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.contextIdIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static Vital createDetachedCopy(Vital realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        Vital unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new Vital();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (Vital) cachedObject.object;
        } else {
            unmanagedObject = (Vital) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        VitalRealmProxyInterface unmanagedCopy = unmanagedObject;
        VitalRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$vitalId(realmSource.realmGet$vitalId());
        unmanagedCopy.realmSet$vitalType(realmSource.realmGet$vitalType());
        unmanagedCopy.realmSet$takenOn(realmSource.realmGet$takenOn());
        unmanagedCopy.realmSet$parameters(realmSource.realmGet$parameters());
        unmanagedCopy.realmSet$userId(realmSource.realmGet$userId());
        unmanagedCopy.realmSet$tenantId(realmSource.realmGet$tenantId());
        unmanagedCopy.realmSet$timestamp(realmSource.realmGet$timestamp());
        unmanagedCopy.realmSet$isSync(realmSource.realmGet$isSync());
        unmanagedCopy.realmSet$postAction(realmSource.realmGet$postAction());
        unmanagedCopy.realmSet$takenBy(realmSource.realmGet$takenBy());
        unmanagedCopy.realmSet$deviceType(realmSource.realmGet$deviceType());
        unmanagedCopy.realmSet$platform(realmSource.realmGet$platform());
        unmanagedCopy.realmSet$deviceReading(realmSource.realmGet$deviceReading());
        unmanagedCopy.realmSet$contextType(realmSource.realmGet$contextType());
        unmanagedCopy.realmSet$contextId(realmSource.realmGet$contextId());
        return unmanagedObject;
    }

    static Vital update(Realm realm, Vital realmObject, Vital newObject, Map<RealmModel, RealmObjectProxy> map) {
        VitalRealmProxyInterface realmObjectTarget = realmObject;
        VitalRealmProxyInterface realmObjectSource = newObject;
        realmObjectTarget.realmSet$vitalType(realmObjectSource.realmGet$vitalType());
        realmObjectTarget.realmSet$takenOn(realmObjectSource.realmGet$takenOn());
        realmObjectTarget.realmSet$parameters(realmObjectSource.realmGet$parameters());
        realmObjectTarget.realmSet$userId(realmObjectSource.realmGet$userId());
        realmObjectTarget.realmSet$tenantId(realmObjectSource.realmGet$tenantId());
        realmObjectTarget.realmSet$timestamp(realmObjectSource.realmGet$timestamp());
        realmObjectTarget.realmSet$isSync(realmObjectSource.realmGet$isSync());
        realmObjectTarget.realmSet$postAction(realmObjectSource.realmGet$postAction());
        realmObjectTarget.realmSet$takenBy(realmObjectSource.realmGet$takenBy());
        realmObjectTarget.realmSet$deviceType(realmObjectSource.realmGet$deviceType());
        realmObjectTarget.realmSet$platform(realmObjectSource.realmGet$platform());
        realmObjectTarget.realmSet$deviceReading(realmObjectSource.realmGet$deviceReading());
        realmObjectTarget.realmSet$contextType(realmObjectSource.realmGet$contextType());
        realmObjectTarget.realmSet$contextId(realmObjectSource.realmGet$contextId());
        return realmObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Vital = proxy[");
        stringBuilder.append("{vitalId:");
        stringBuilder.append(realmGet$vitalId() != null ? realmGet$vitalId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{vitalType:");
        stringBuilder.append(realmGet$vitalType() != null ? realmGet$vitalType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{takenOn:");
        stringBuilder.append(realmGet$takenOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parameters:");
        stringBuilder.append(realmGet$parameters() != null ? realmGet$parameters() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userId:");
        stringBuilder.append(realmGet$userId() != null ? realmGet$userId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tenantId:");
        stringBuilder.append(realmGet$tenantId() != null ? realmGet$tenantId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isSync:");
        stringBuilder.append(realmGet$isSync());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{postAction:");
        stringBuilder.append(realmGet$postAction() != null ? realmGet$postAction() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{takenBy:");
        stringBuilder.append(realmGet$takenBy() != null ? realmGet$takenBy() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{deviceType:");
        stringBuilder.append(realmGet$deviceType() != null ? realmGet$deviceType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{platform:");
        stringBuilder.append(realmGet$platform() != null ? realmGet$platform() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{deviceReading:");
        stringBuilder.append(realmGet$deviceReading());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{contextType:");
        stringBuilder.append(realmGet$contextType() != null ? realmGet$contextType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{contextId:");
        stringBuilder.append(realmGet$contextId() != null ? realmGet$contextId() : "null");
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
        VitalRealmProxy aVital = (VitalRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aVital.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aVital.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aVital.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
