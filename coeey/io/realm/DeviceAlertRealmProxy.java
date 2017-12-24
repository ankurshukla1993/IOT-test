package io.realm;

import android.annotation.TargetApi;
import android.util.JsonReader;
import android.util.JsonToken;
import com.cooey.common.vo.DeviceAlert;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.measurement.AppMeasurement$Param;
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

public class DeviceAlertRealmProxy extends DeviceAlert implements RealmObjectProxy, DeviceAlertRealmProxyInterface {
    private static final List<String> FIELD_NAMES;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private DeviceAlertColumnInfo columnInfo;
    private ProxyState<DeviceAlert> proxyState;

    static final class DeviceAlertColumnInfo extends ColumnInfo {
        long altitudeIndex;
        long altitudeUncertainityIndex;
        long assistedGPSIndex;
        long batteryStatusIndex;
        long commandExtensionIndex;
        long commandIdIndex;
        long commandIndex;
        long createdOnIndex;
        long deviceIdIndex;
        long fullMessageIndex;
        long gpsSpeedIndex;
        long headingIndex;
        long horizontalUncertainityAngleIndex;
        long horizontalUncertainityIndex;
        long idIndex;
        long latitudeIndex;
        long longitudeIndex;
        long payloadSizeIndex;
        long perpendicularUncertainityIndex;
        long temperatureIndex;
        long timestampIndex;

        DeviceAlertColumnInfo(OsSchemaInfo schemaInfo) {
            super(21);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("DeviceAlert");
            this.idIndex = addColumnDetails(ShareConstants.WEB_DIALOG_PARAM_ID, objectSchemaInfo);
            this.payloadSizeIndex = addColumnDetails("payloadSize", objectSchemaInfo);
            this.commandIdIndex = addColumnDetails("commandId", objectSchemaInfo);
            this.commandExtensionIndex = addColumnDetails("commandExtension", objectSchemaInfo);
            this.fullMessageIndex = addColumnDetails("fullMessage", objectSchemaInfo);
            this.deviceIdIndex = addColumnDetails("deviceId", objectSchemaInfo);
            this.createdOnIndex = addColumnDetails("createdOn", objectSchemaInfo);
            this.timestampIndex = addColumnDetails(AppMeasurement$Param.TIMESTAMP, objectSchemaInfo);
            this.latitudeIndex = addColumnDetails("latitude", objectSchemaInfo);
            this.longitudeIndex = addColumnDetails("longitude", objectSchemaInfo);
            this.headingIndex = addColumnDetails("heading", objectSchemaInfo);
            this.altitudeIndex = addColumnDetails("altitude", objectSchemaInfo);
            this.assistedGPSIndex = addColumnDetails("assistedGPS", objectSchemaInfo);
            this.gpsSpeedIndex = addColumnDetails("gpsSpeed", objectSchemaInfo);
            this.horizontalUncertainityIndex = addColumnDetails("horizontalUncertainity", objectSchemaInfo);
            this.perpendicularUncertainityIndex = addColumnDetails("perpendicularUncertainity", objectSchemaInfo);
            this.horizontalUncertainityAngleIndex = addColumnDetails("horizontalUncertainityAngle", objectSchemaInfo);
            this.altitudeUncertainityIndex = addColumnDetails("altitudeUncertainity", objectSchemaInfo);
            this.batteryStatusIndex = addColumnDetails("batteryStatus", objectSchemaInfo);
            this.temperatureIndex = addColumnDetails("temperature", objectSchemaInfo);
            this.commandIndex = addColumnDetails("command", objectSchemaInfo);
        }

        DeviceAlertColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        protected final ColumnInfo copy(boolean mutable) {
            return new DeviceAlertColumnInfo(this, mutable);
        }

        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            DeviceAlertColumnInfo src = (DeviceAlertColumnInfo) rawSrc;
            DeviceAlertColumnInfo dst = (DeviceAlertColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.payloadSizeIndex = src.payloadSizeIndex;
            dst.commandIdIndex = src.commandIdIndex;
            dst.commandExtensionIndex = src.commandExtensionIndex;
            dst.fullMessageIndex = src.fullMessageIndex;
            dst.deviceIdIndex = src.deviceIdIndex;
            dst.createdOnIndex = src.createdOnIndex;
            dst.timestampIndex = src.timestampIndex;
            dst.latitudeIndex = src.latitudeIndex;
            dst.longitudeIndex = src.longitudeIndex;
            dst.headingIndex = src.headingIndex;
            dst.altitudeIndex = src.altitudeIndex;
            dst.assistedGPSIndex = src.assistedGPSIndex;
            dst.gpsSpeedIndex = src.gpsSpeedIndex;
            dst.horizontalUncertainityIndex = src.horizontalUncertainityIndex;
            dst.perpendicularUncertainityIndex = src.perpendicularUncertainityIndex;
            dst.horizontalUncertainityAngleIndex = src.horizontalUncertainityAngleIndex;
            dst.altitudeUncertainityIndex = src.altitudeUncertainityIndex;
            dst.batteryStatusIndex = src.batteryStatusIndex;
            dst.temperatureIndex = src.temperatureIndex;
            dst.commandIndex = src.commandIndex;
        }
    }

    static {
        List<String> fieldNames = new ArrayList();
        fieldNames.add(ShareConstants.WEB_DIALOG_PARAM_ID);
        fieldNames.add("payloadSize");
        fieldNames.add("commandId");
        fieldNames.add("commandExtension");
        fieldNames.add("fullMessage");
        fieldNames.add("deviceId");
        fieldNames.add("createdOn");
        fieldNames.add(AppMeasurement$Param.TIMESTAMP);
        fieldNames.add("latitude");
        fieldNames.add("longitude");
        fieldNames.add("heading");
        fieldNames.add("altitude");
        fieldNames.add("assistedGPS");
        fieldNames.add("gpsSpeed");
        fieldNames.add("horizontalUncertainity");
        fieldNames.add("perpendicularUncertainity");
        fieldNames.add("horizontalUncertainityAngle");
        fieldNames.add("altitudeUncertainity");
        fieldNames.add("batteryStatus");
        fieldNames.add("temperature");
        fieldNames.add("command");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DeviceAlertRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm$RealmObjectContext context = (BaseRealm$RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (DeviceAlertColumnInfo) context.getColumnInfo();
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

    public long realmGet$payloadSize() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.payloadSizeIndex);
    }

    public void realmSet$payloadSize(long value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.payloadSizeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setLong(this.columnInfo.payloadSizeIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$commandId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.commandIdIndex);
    }

    public void realmSet$commandId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.commandIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.commandIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.commandIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.commandIdIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$commandExtension() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.commandExtensionIndex);
    }

    public void realmSet$commandExtension(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.commandExtensionIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.commandExtensionIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.commandExtensionIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.commandExtensionIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$fullMessage() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.fullMessageIndex);
    }

    public void realmSet$fullMessage(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.fullMessageIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.fullMessageIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.fullMessageIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.fullMessageIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$deviceId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.deviceIdIndex);
    }

    public void realmSet$deviceId(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.deviceIdIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.deviceIdIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.deviceIdIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.deviceIdIndex, row.getIndex(), value, true);
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

    public double realmGet$latitude() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getDouble(this.columnInfo.latitudeIndex);
    }

    public void realmSet$latitude(double value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setDouble(this.columnInfo.latitudeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setDouble(this.columnInfo.latitudeIndex, row.getIndex(), value, true);
        }
    }

    public double realmGet$longitude() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getDouble(this.columnInfo.longitudeIndex);
    }

    public void realmSet$longitude(double value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setDouble(this.columnInfo.longitudeIndex, value);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            row.getTable().setDouble(this.columnInfo.longitudeIndex, row.getIndex(), value, true);
        }
    }

    public String realmGet$heading() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.headingIndex);
    }

    public void realmSet$heading(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.headingIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.headingIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.headingIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.headingIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$altitude() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.altitudeIndex);
    }

    public void realmSet$altitude(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.altitudeIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.altitudeIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.altitudeIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.altitudeIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$assistedGPS() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.assistedGPSIndex);
    }

    public void realmSet$assistedGPS(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.assistedGPSIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.assistedGPSIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.assistedGPSIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.assistedGPSIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$gpsSpeed() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.gpsSpeedIndex);
    }

    public void realmSet$gpsSpeed(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.gpsSpeedIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.gpsSpeedIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.gpsSpeedIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.gpsSpeedIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$horizontalUncertainity() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.horizontalUncertainityIndex);
    }

    public void realmSet$horizontalUncertainity(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.horizontalUncertainityIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.horizontalUncertainityIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.horizontalUncertainityIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.horizontalUncertainityIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$perpendicularUncertainity() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.perpendicularUncertainityIndex);
    }

    public void realmSet$perpendicularUncertainity(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.perpendicularUncertainityIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.perpendicularUncertainityIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.perpendicularUncertainityIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.perpendicularUncertainityIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$horizontalUncertainityAngle() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.horizontalUncertainityAngleIndex);
    }

    public void realmSet$horizontalUncertainityAngle(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.horizontalUncertainityAngleIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.horizontalUncertainityAngleIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.horizontalUncertainityAngleIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.horizontalUncertainityAngleIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$altitudeUncertainity() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.altitudeUncertainityIndex);
    }

    public void realmSet$altitudeUncertainity(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.altitudeUncertainityIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.altitudeUncertainityIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.altitudeUncertainityIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.altitudeUncertainityIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$batteryStatus() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.batteryStatusIndex);
    }

    public void realmSet$batteryStatus(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.batteryStatusIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.batteryStatusIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.batteryStatusIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.batteryStatusIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$temperature() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.temperatureIndex);
    }

    public void realmSet$temperature(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.temperatureIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.temperatureIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.temperatureIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.temperatureIndex, row.getIndex(), value, true);
            }
        }
    }

    public String realmGet$command() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.commandIndex);
    }

    public void realmSet$command(String value) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (value == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.commandIndex);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.commandIndex, value);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row = this.proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(this.columnInfo.commandIndex, row.getIndex(), true);
            } else {
                row.getTable().setString(this.columnInfo.commandIndex, row.getIndex(), value, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        Builder builder = new Builder("DeviceAlert");
        builder.addPersistedProperty(ShareConstants.WEB_DIALOG_PARAM_ID, RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("payloadSize", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("commandId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("commandExtension", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("fullMessage", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("deviceId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("createdOn", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty(AppMeasurement$Param.TIMESTAMP, RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedProperty("latitude", RealmFieldType.DOUBLE, false, false, true);
        builder.addPersistedProperty("longitude", RealmFieldType.DOUBLE, false, false, true);
        builder.addPersistedProperty("heading", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("altitude", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("assistedGPS", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("gpsSpeed", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("horizontalUncertainity", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("perpendicularUncertainity", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("horizontalUncertainityAngle", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("altitudeUncertainity", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("batteryStatus", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("temperature", RealmFieldType.STRING, false, false, false);
        builder.addPersistedProperty("command", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static DeviceAlertColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new DeviceAlertColumnInfo(schemaInfo);
    }

    public static String getTableName() {
        return "class_DeviceAlert";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static DeviceAlert createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update) throws JSONException {
        DeviceAlert obj = (DeviceAlert) realm.createObjectInternal(DeviceAlert.class, true, Collections.emptyList());
        DeviceAlertRealmProxyInterface objProxy = obj;
        if (json.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
            if (json.isNull(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                objProxy.realmSet$id(null);
            } else {
                objProxy.realmSet$id(json.getString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
        }
        if (json.has("payloadSize")) {
            if (json.isNull("payloadSize")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'payloadSize' to null.");
            }
            objProxy.realmSet$payloadSize(json.getLong("payloadSize"));
        }
        if (json.has("commandId")) {
            if (json.isNull("commandId")) {
                objProxy.realmSet$commandId(null);
            } else {
                objProxy.realmSet$commandId(json.getString("commandId"));
            }
        }
        if (json.has("commandExtension")) {
            if (json.isNull("commandExtension")) {
                objProxy.realmSet$commandExtension(null);
            } else {
                objProxy.realmSet$commandExtension(json.getString("commandExtension"));
            }
        }
        if (json.has("fullMessage")) {
            if (json.isNull("fullMessage")) {
                objProxy.realmSet$fullMessage(null);
            } else {
                objProxy.realmSet$fullMessage(json.getString("fullMessage"));
            }
        }
        if (json.has("deviceId")) {
            if (json.isNull("deviceId")) {
                objProxy.realmSet$deviceId(null);
            } else {
                objProxy.realmSet$deviceId(json.getString("deviceId"));
            }
        }
        if (json.has("createdOn")) {
            if (json.isNull("createdOn")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'createdOn' to null.");
            }
            objProxy.realmSet$createdOn(json.getLong("createdOn"));
        }
        if (json.has(AppMeasurement$Param.TIMESTAMP)) {
            if (json.isNull(AppMeasurement$Param.TIMESTAMP)) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
            }
            objProxy.realmSet$timestamp(json.getLong(AppMeasurement$Param.TIMESTAMP));
        }
        if (json.has("latitude")) {
            if (json.isNull("latitude")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'latitude' to null.");
            }
            objProxy.realmSet$latitude(json.getDouble("latitude"));
        }
        if (json.has("longitude")) {
            if (json.isNull("longitude")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'longitude' to null.");
            }
            objProxy.realmSet$longitude(json.getDouble("longitude"));
        }
        if (json.has("heading")) {
            if (json.isNull("heading")) {
                objProxy.realmSet$heading(null);
            } else {
                objProxy.realmSet$heading(json.getString("heading"));
            }
        }
        if (json.has("altitude")) {
            if (json.isNull("altitude")) {
                objProxy.realmSet$altitude(null);
            } else {
                objProxy.realmSet$altitude(json.getString("altitude"));
            }
        }
        if (json.has("assistedGPS")) {
            if (json.isNull("assistedGPS")) {
                objProxy.realmSet$assistedGPS(null);
            } else {
                objProxy.realmSet$assistedGPS(json.getString("assistedGPS"));
            }
        }
        if (json.has("gpsSpeed")) {
            if (json.isNull("gpsSpeed")) {
                objProxy.realmSet$gpsSpeed(null);
            } else {
                objProxy.realmSet$gpsSpeed(json.getString("gpsSpeed"));
            }
        }
        if (json.has("horizontalUncertainity")) {
            if (json.isNull("horizontalUncertainity")) {
                objProxy.realmSet$horizontalUncertainity(null);
            } else {
                objProxy.realmSet$horizontalUncertainity(json.getString("horizontalUncertainity"));
            }
        }
        if (json.has("perpendicularUncertainity")) {
            if (json.isNull("perpendicularUncertainity")) {
                objProxy.realmSet$perpendicularUncertainity(null);
            } else {
                objProxy.realmSet$perpendicularUncertainity(json.getString("perpendicularUncertainity"));
            }
        }
        if (json.has("horizontalUncertainityAngle")) {
            if (json.isNull("horizontalUncertainityAngle")) {
                objProxy.realmSet$horizontalUncertainityAngle(null);
            } else {
                objProxy.realmSet$horizontalUncertainityAngle(json.getString("horizontalUncertainityAngle"));
            }
        }
        if (json.has("altitudeUncertainity")) {
            if (json.isNull("altitudeUncertainity")) {
                objProxy.realmSet$altitudeUncertainity(null);
            } else {
                objProxy.realmSet$altitudeUncertainity(json.getString("altitudeUncertainity"));
            }
        }
        if (json.has("batteryStatus")) {
            if (json.isNull("batteryStatus")) {
                objProxy.realmSet$batteryStatus(null);
            } else {
                objProxy.realmSet$batteryStatus(json.getString("batteryStatus"));
            }
        }
        if (json.has("temperature")) {
            if (json.isNull("temperature")) {
                objProxy.realmSet$temperature(null);
            } else {
                objProxy.realmSet$temperature(json.getString("temperature"));
            }
        }
        if (json.has("command")) {
            if (json.isNull("command")) {
                objProxy.realmSet$command(null);
            } else {
                objProxy.realmSet$command(json.getString("command"));
            }
        }
        return obj;
    }

    @TargetApi(11)
    public static DeviceAlert createUsingJsonStream(Realm realm, JsonReader reader) throws IOException {
        DeviceAlert obj = new DeviceAlert();
        DeviceAlertRealmProxyInterface objProxy = obj;
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
            } else if (name.equals("payloadSize")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$payloadSize(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'payloadSize' to null.");
                }
            } else if (name.equals("commandId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$commandId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$commandId(null);
                }
            } else if (name.equals("commandExtension")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$commandExtension(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$commandExtension(null);
                }
            } else if (name.equals("fullMessage")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$fullMessage(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$fullMessage(null);
                }
            } else if (name.equals("deviceId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$deviceId(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$deviceId(null);
                }
            } else if (name.equals("createdOn")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$createdOn(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createdOn' to null.");
                }
            } else if (name.equals(AppMeasurement$Param.TIMESTAMP)) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$timestamp(reader.nextLong());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
                }
            } else if (name.equals("latitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$latitude(reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'latitude' to null.");
                }
            } else if (name.equals("longitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$longitude(reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'longitude' to null.");
                }
            } else if (name.equals("heading")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$heading(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$heading(null);
                }
            } else if (name.equals("altitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$altitude(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$altitude(null);
                }
            } else if (name.equals("assistedGPS")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$assistedGPS(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$assistedGPS(null);
                }
            } else if (name.equals("gpsSpeed")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$gpsSpeed(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$gpsSpeed(null);
                }
            } else if (name.equals("horizontalUncertainity")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$horizontalUncertainity(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$horizontalUncertainity(null);
                }
            } else if (name.equals("perpendicularUncertainity")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$perpendicularUncertainity(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$perpendicularUncertainity(null);
                }
            } else if (name.equals("horizontalUncertainityAngle")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$horizontalUncertainityAngle(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$horizontalUncertainityAngle(null);
                }
            } else if (name.equals("altitudeUncertainity")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$altitudeUncertainity(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$altitudeUncertainity(null);
                }
            } else if (name.equals("batteryStatus")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$batteryStatus(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$batteryStatus(null);
                }
            } else if (name.equals("temperature")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$temperature(reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$temperature(null);
                }
            } else if (!name.equals("command")) {
                reader.skipValue();
            } else if (reader.peek() != JsonToken.NULL) {
                objProxy.realmSet$command(reader.nextString());
            } else {
                reader.skipValue();
                objProxy.realmSet$command(null);
            }
        }
        reader.endObject();
        return (DeviceAlert) realm.copyToRealm(obj);
    }

    public static DeviceAlert copyOrUpdate(Realm realm, DeviceAlert object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
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
            return (DeviceAlert) cachedRealmObject;
        }
        return copy(realm, object, update, cache);
    }

    public static DeviceAlert copy(Realm realm, DeviceAlert newObject, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = (RealmObjectProxy) cache.get(newObject);
        if (cachedRealmObject != null) {
            return (DeviceAlert) cachedRealmObject;
        }
        DeviceAlert realmObject = (DeviceAlert) realm.createObjectInternal(DeviceAlert.class, false, Collections.emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        DeviceAlertRealmProxyInterface realmObjectSource = newObject;
        DeviceAlertRealmProxyInterface realmObjectCopy = realmObject;
        realmObjectCopy.realmSet$id(realmObjectSource.realmGet$id());
        realmObjectCopy.realmSet$payloadSize(realmObjectSource.realmGet$payloadSize());
        realmObjectCopy.realmSet$commandId(realmObjectSource.realmGet$commandId());
        realmObjectCopy.realmSet$commandExtension(realmObjectSource.realmGet$commandExtension());
        realmObjectCopy.realmSet$fullMessage(realmObjectSource.realmGet$fullMessage());
        realmObjectCopy.realmSet$deviceId(realmObjectSource.realmGet$deviceId());
        realmObjectCopy.realmSet$createdOn(realmObjectSource.realmGet$createdOn());
        realmObjectCopy.realmSet$timestamp(realmObjectSource.realmGet$timestamp());
        realmObjectCopy.realmSet$latitude(realmObjectSource.realmGet$latitude());
        realmObjectCopy.realmSet$longitude(realmObjectSource.realmGet$longitude());
        realmObjectCopy.realmSet$heading(realmObjectSource.realmGet$heading());
        realmObjectCopy.realmSet$altitude(realmObjectSource.realmGet$altitude());
        realmObjectCopy.realmSet$assistedGPS(realmObjectSource.realmGet$assistedGPS());
        realmObjectCopy.realmSet$gpsSpeed(realmObjectSource.realmGet$gpsSpeed());
        realmObjectCopy.realmSet$horizontalUncertainity(realmObjectSource.realmGet$horizontalUncertainity());
        realmObjectCopy.realmSet$perpendicularUncertainity(realmObjectSource.realmGet$perpendicularUncertainity());
        realmObjectCopy.realmSet$horizontalUncertainityAngle(realmObjectSource.realmGet$horizontalUncertainityAngle());
        realmObjectCopy.realmSet$altitudeUncertainity(realmObjectSource.realmGet$altitudeUncertainity());
        realmObjectCopy.realmSet$batteryStatus(realmObjectSource.realmGet$batteryStatus());
        realmObjectCopy.realmSet$temperature(realmObjectSource.realmGet$temperature());
        realmObjectCopy.realmSet$command(realmObjectSource.realmGet$command());
        return realmObject;
    }

    public static long insert(Realm realm, DeviceAlert object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(DeviceAlert.class);
        long tableNativePtr = table.getNativePtr();
        DeviceAlertColumnInfo columnInfo = (DeviceAlertColumnInfo) realm.getSchema().getColumnInfo(DeviceAlert.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$id = object.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.payloadSizeIndex, rowIndex, object.realmGet$payloadSize(), false);
        String realmGet$commandId = object.realmGet$commandId();
        if (realmGet$commandId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.commandIdIndex, rowIndex, realmGet$commandId, false);
        }
        String realmGet$commandExtension = object.realmGet$commandExtension();
        if (realmGet$commandExtension != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.commandExtensionIndex, rowIndex, realmGet$commandExtension, false);
        }
        String realmGet$fullMessage = object.realmGet$fullMessage();
        if (realmGet$fullMessage != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fullMessageIndex, rowIndex, realmGet$fullMessage, false);
        }
        String realmGet$deviceId = object.realmGet$deviceId();
        if (realmGet$deviceId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.deviceIdIndex, rowIndex, realmGet$deviceId, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeIndex, rowIndex, object.realmGet$latitude(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeIndex, rowIndex, object.realmGet$longitude(), false);
        String realmGet$heading = object.realmGet$heading();
        if (realmGet$heading != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.headingIndex, rowIndex, realmGet$heading, false);
        }
        String realmGet$altitude = object.realmGet$altitude();
        if (realmGet$altitude != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.altitudeIndex, rowIndex, realmGet$altitude, false);
        }
        String realmGet$assistedGPS = object.realmGet$assistedGPS();
        if (realmGet$assistedGPS != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.assistedGPSIndex, rowIndex, realmGet$assistedGPS, false);
        }
        String realmGet$gpsSpeed = object.realmGet$gpsSpeed();
        if (realmGet$gpsSpeed != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.gpsSpeedIndex, rowIndex, realmGet$gpsSpeed, false);
        }
        String realmGet$horizontalUncertainity = object.realmGet$horizontalUncertainity();
        if (realmGet$horizontalUncertainity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.horizontalUncertainityIndex, rowIndex, realmGet$horizontalUncertainity, false);
        }
        String realmGet$perpendicularUncertainity = object.realmGet$perpendicularUncertainity();
        if (realmGet$perpendicularUncertainity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.perpendicularUncertainityIndex, rowIndex, realmGet$perpendicularUncertainity, false);
        }
        String realmGet$horizontalUncertainityAngle = object.realmGet$horizontalUncertainityAngle();
        if (realmGet$horizontalUncertainityAngle != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.horizontalUncertainityAngleIndex, rowIndex, realmGet$horizontalUncertainityAngle, false);
        }
        String realmGet$altitudeUncertainity = object.realmGet$altitudeUncertainity();
        if (realmGet$altitudeUncertainity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.altitudeUncertainityIndex, rowIndex, realmGet$altitudeUncertainity, false);
        }
        String realmGet$batteryStatus = object.realmGet$batteryStatus();
        if (realmGet$batteryStatus != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.batteryStatusIndex, rowIndex, realmGet$batteryStatus, false);
        }
        String realmGet$temperature = object.realmGet$temperature();
        if (realmGet$temperature != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.temperatureIndex, rowIndex, realmGet$temperature, false);
        }
        String realmGet$command = object.realmGet$command();
        if (realmGet$command == null) {
            return rowIndex;
        }
        Table.nativeSetString(tableNativePtr, columnInfo.commandIndex, rowIndex, realmGet$command, false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(DeviceAlert.class);
        long tableNativePtr = table.getNativePtr();
        DeviceAlertColumnInfo columnInfo = (DeviceAlertColumnInfo) realm.getSchema().getColumnInfo(DeviceAlert.class);
        while (objects.hasNext()) {
            DeviceAlert object = (DeviceAlert) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$id = object.realmGet$id();
                    if (realmGet$id != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.payloadSizeIndex, rowIndex, object.realmGet$payloadSize(), false);
                    String realmGet$commandId = object.realmGet$commandId();
                    if (realmGet$commandId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.commandIdIndex, rowIndex, realmGet$commandId, false);
                    }
                    String realmGet$commandExtension = object.realmGet$commandExtension();
                    if (realmGet$commandExtension != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.commandExtensionIndex, rowIndex, realmGet$commandExtension, false);
                    }
                    String realmGet$fullMessage = object.realmGet$fullMessage();
                    if (realmGet$fullMessage != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.fullMessageIndex, rowIndex, realmGet$fullMessage, false);
                    }
                    String realmGet$deviceId = object.realmGet$deviceId();
                    if (realmGet$deviceId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.deviceIdIndex, rowIndex, realmGet$deviceId, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
                    Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeIndex, rowIndex, object.realmGet$latitude(), false);
                    Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeIndex, rowIndex, object.realmGet$longitude(), false);
                    String realmGet$heading = object.realmGet$heading();
                    if (realmGet$heading != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.headingIndex, rowIndex, realmGet$heading, false);
                    }
                    String realmGet$altitude = object.realmGet$altitude();
                    if (realmGet$altitude != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.altitudeIndex, rowIndex, realmGet$altitude, false);
                    }
                    String realmGet$assistedGPS = object.realmGet$assistedGPS();
                    if (realmGet$assistedGPS != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.assistedGPSIndex, rowIndex, realmGet$assistedGPS, false);
                    }
                    String realmGet$gpsSpeed = object.realmGet$gpsSpeed();
                    if (realmGet$gpsSpeed != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.gpsSpeedIndex, rowIndex, realmGet$gpsSpeed, false);
                    }
                    String realmGet$horizontalUncertainity = object.realmGet$horizontalUncertainity();
                    if (realmGet$horizontalUncertainity != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.horizontalUncertainityIndex, rowIndex, realmGet$horizontalUncertainity, false);
                    }
                    String realmGet$perpendicularUncertainity = object.realmGet$perpendicularUncertainity();
                    if (realmGet$perpendicularUncertainity != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.perpendicularUncertainityIndex, rowIndex, realmGet$perpendicularUncertainity, false);
                    }
                    String realmGet$horizontalUncertainityAngle = object.realmGet$horizontalUncertainityAngle();
                    if (realmGet$horizontalUncertainityAngle != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.horizontalUncertainityAngleIndex, rowIndex, realmGet$horizontalUncertainityAngle, false);
                    }
                    String realmGet$altitudeUncertainity = object.realmGet$altitudeUncertainity();
                    if (realmGet$altitudeUncertainity != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.altitudeUncertainityIndex, rowIndex, realmGet$altitudeUncertainity, false);
                    }
                    String realmGet$batteryStatus = object.realmGet$batteryStatus();
                    if (realmGet$batteryStatus != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.batteryStatusIndex, rowIndex, realmGet$batteryStatus, false);
                    }
                    String realmGet$temperature = object.realmGet$temperature();
                    if (realmGet$temperature != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.temperatureIndex, rowIndex, realmGet$temperature, false);
                    }
                    String realmGet$command = object.realmGet$command();
                    if (realmGet$command != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.commandIndex, rowIndex, realmGet$command, false);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, DeviceAlert object, Map<RealmModel, Long> cache) {
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(DeviceAlert.class);
        long tableNativePtr = table.getNativePtr();
        DeviceAlertColumnInfo columnInfo = (DeviceAlertColumnInfo) realm.getSchema().getColumnInfo(DeviceAlert.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, Long.valueOf(rowIndex));
        String realmGet$id = object.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.payloadSizeIndex, rowIndex, object.realmGet$payloadSize(), false);
        String realmGet$commandId = object.realmGet$commandId();
        if (realmGet$commandId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.commandIdIndex, rowIndex, realmGet$commandId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.commandIdIndex, rowIndex, false);
        }
        String realmGet$commandExtension = object.realmGet$commandExtension();
        if (realmGet$commandExtension != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.commandExtensionIndex, rowIndex, realmGet$commandExtension, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.commandExtensionIndex, rowIndex, false);
        }
        String realmGet$fullMessage = object.realmGet$fullMessage();
        if (realmGet$fullMessage != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fullMessageIndex, rowIndex, realmGet$fullMessage, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fullMessageIndex, rowIndex, false);
        }
        String realmGet$deviceId = object.realmGet$deviceId();
        if (realmGet$deviceId != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.deviceIdIndex, rowIndex, realmGet$deviceId, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.deviceIdIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeIndex, rowIndex, object.realmGet$latitude(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeIndex, rowIndex, object.realmGet$longitude(), false);
        String realmGet$heading = object.realmGet$heading();
        if (realmGet$heading != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.headingIndex, rowIndex, realmGet$heading, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.headingIndex, rowIndex, false);
        }
        String realmGet$altitude = object.realmGet$altitude();
        if (realmGet$altitude != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.altitudeIndex, rowIndex, realmGet$altitude, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.altitudeIndex, rowIndex, false);
        }
        String realmGet$assistedGPS = object.realmGet$assistedGPS();
        if (realmGet$assistedGPS != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.assistedGPSIndex, rowIndex, realmGet$assistedGPS, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.assistedGPSIndex, rowIndex, false);
        }
        String realmGet$gpsSpeed = object.realmGet$gpsSpeed();
        if (realmGet$gpsSpeed != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.gpsSpeedIndex, rowIndex, realmGet$gpsSpeed, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.gpsSpeedIndex, rowIndex, false);
        }
        String realmGet$horizontalUncertainity = object.realmGet$horizontalUncertainity();
        if (realmGet$horizontalUncertainity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.horizontalUncertainityIndex, rowIndex, realmGet$horizontalUncertainity, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.horizontalUncertainityIndex, rowIndex, false);
        }
        String realmGet$perpendicularUncertainity = object.realmGet$perpendicularUncertainity();
        if (realmGet$perpendicularUncertainity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.perpendicularUncertainityIndex, rowIndex, realmGet$perpendicularUncertainity, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.perpendicularUncertainityIndex, rowIndex, false);
        }
        String realmGet$horizontalUncertainityAngle = object.realmGet$horizontalUncertainityAngle();
        if (realmGet$horizontalUncertainityAngle != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.horizontalUncertainityAngleIndex, rowIndex, realmGet$horizontalUncertainityAngle, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.horizontalUncertainityAngleIndex, rowIndex, false);
        }
        String realmGet$altitudeUncertainity = object.realmGet$altitudeUncertainity();
        if (realmGet$altitudeUncertainity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.altitudeUncertainityIndex, rowIndex, realmGet$altitudeUncertainity, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.altitudeUncertainityIndex, rowIndex, false);
        }
        String realmGet$batteryStatus = object.realmGet$batteryStatus();
        if (realmGet$batteryStatus != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.batteryStatusIndex, rowIndex, realmGet$batteryStatus, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.batteryStatusIndex, rowIndex, false);
        }
        String realmGet$temperature = object.realmGet$temperature();
        if (realmGet$temperature != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.temperatureIndex, rowIndex, realmGet$temperature, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.temperatureIndex, rowIndex, false);
        }
        String realmGet$command = object.realmGet$command();
        if (realmGet$command != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.commandIndex, rowIndex, realmGet$command, false);
            return rowIndex;
        }
        Table.nativeSetNull(tableNativePtr, columnInfo.commandIndex, rowIndex, false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel, Long> cache) {
        Table table = realm.getTable(DeviceAlert.class);
        long tableNativePtr = table.getNativePtr();
        DeviceAlertColumnInfo columnInfo = (DeviceAlertColumnInfo) realm.getSchema().getColumnInfo(DeviceAlert.class);
        while (objects.hasNext()) {
            DeviceAlert object = (DeviceAlert) objects.next();
            if (!cache.containsKey(object)) {
                if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, Long.valueOf(((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex()));
                } else {
                    long rowIndex = OsObject.createRow(table);
                    cache.put(object, Long.valueOf(rowIndex));
                    String realmGet$id = object.realmGet$id();
                    if (realmGet$id != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.payloadSizeIndex, rowIndex, object.realmGet$payloadSize(), false);
                    String realmGet$commandId = object.realmGet$commandId();
                    if (realmGet$commandId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.commandIdIndex, rowIndex, realmGet$commandId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.commandIdIndex, rowIndex, false);
                    }
                    String realmGet$commandExtension = object.realmGet$commandExtension();
                    if (realmGet$commandExtension != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.commandExtensionIndex, rowIndex, realmGet$commandExtension, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.commandExtensionIndex, rowIndex, false);
                    }
                    String realmGet$fullMessage = object.realmGet$fullMessage();
                    if (realmGet$fullMessage != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.fullMessageIndex, rowIndex, realmGet$fullMessage, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.fullMessageIndex, rowIndex, false);
                    }
                    String realmGet$deviceId = object.realmGet$deviceId();
                    if (realmGet$deviceId != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.deviceIdIndex, rowIndex, realmGet$deviceId, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.deviceIdIndex, rowIndex, false);
                    }
                    Table.nativeSetLong(tableNativePtr, columnInfo.createdOnIndex, rowIndex, object.realmGet$createdOn(), false);
                    Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, object.realmGet$timestamp(), false);
                    Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeIndex, rowIndex, object.realmGet$latitude(), false);
                    Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeIndex, rowIndex, object.realmGet$longitude(), false);
                    String realmGet$heading = object.realmGet$heading();
                    if (realmGet$heading != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.headingIndex, rowIndex, realmGet$heading, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.headingIndex, rowIndex, false);
                    }
                    String realmGet$altitude = object.realmGet$altitude();
                    if (realmGet$altitude != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.altitudeIndex, rowIndex, realmGet$altitude, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.altitudeIndex, rowIndex, false);
                    }
                    String realmGet$assistedGPS = object.realmGet$assistedGPS();
                    if (realmGet$assistedGPS != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.assistedGPSIndex, rowIndex, realmGet$assistedGPS, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.assistedGPSIndex, rowIndex, false);
                    }
                    String realmGet$gpsSpeed = object.realmGet$gpsSpeed();
                    if (realmGet$gpsSpeed != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.gpsSpeedIndex, rowIndex, realmGet$gpsSpeed, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.gpsSpeedIndex, rowIndex, false);
                    }
                    String realmGet$horizontalUncertainity = object.realmGet$horizontalUncertainity();
                    if (realmGet$horizontalUncertainity != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.horizontalUncertainityIndex, rowIndex, realmGet$horizontalUncertainity, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.horizontalUncertainityIndex, rowIndex, false);
                    }
                    String realmGet$perpendicularUncertainity = object.realmGet$perpendicularUncertainity();
                    if (realmGet$perpendicularUncertainity != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.perpendicularUncertainityIndex, rowIndex, realmGet$perpendicularUncertainity, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.perpendicularUncertainityIndex, rowIndex, false);
                    }
                    String realmGet$horizontalUncertainityAngle = object.realmGet$horizontalUncertainityAngle();
                    if (realmGet$horizontalUncertainityAngle != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.horizontalUncertainityAngleIndex, rowIndex, realmGet$horizontalUncertainityAngle, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.horizontalUncertainityAngleIndex, rowIndex, false);
                    }
                    String realmGet$altitudeUncertainity = object.realmGet$altitudeUncertainity();
                    if (realmGet$altitudeUncertainity != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.altitudeUncertainityIndex, rowIndex, realmGet$altitudeUncertainity, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.altitudeUncertainityIndex, rowIndex, false);
                    }
                    String realmGet$batteryStatus = object.realmGet$batteryStatus();
                    if (realmGet$batteryStatus != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.batteryStatusIndex, rowIndex, realmGet$batteryStatus, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.batteryStatusIndex, rowIndex, false);
                    }
                    String realmGet$temperature = object.realmGet$temperature();
                    if (realmGet$temperature != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.temperatureIndex, rowIndex, realmGet$temperature, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.temperatureIndex, rowIndex, false);
                    }
                    String realmGet$command = object.realmGet$command();
                    if (realmGet$command != null) {
                        Table.nativeSetString(tableNativePtr, columnInfo.commandIndex, rowIndex, realmGet$command, false);
                    } else {
                        Table.nativeSetNull(tableNativePtr, columnInfo.commandIndex, rowIndex, false);
                    }
                }
            }
        }
    }

    public static DeviceAlert createDetachedCopy(DeviceAlert realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        DeviceAlert unmanagedObject;
        CacheData<RealmModel> cachedObject = (CacheData) cache.get(realmObject);
        if (cachedObject == null) {
            unmanagedObject = new DeviceAlert();
            cache.put(realmObject, new CacheData(currentDepth, unmanagedObject));
        } else if (currentDepth >= cachedObject.minDepth) {
            return (DeviceAlert) cachedObject.object;
        } else {
            unmanagedObject = (DeviceAlert) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        DeviceAlertRealmProxyInterface unmanagedCopy = unmanagedObject;
        DeviceAlertRealmProxyInterface realmSource = realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$payloadSize(realmSource.realmGet$payloadSize());
        unmanagedCopy.realmSet$commandId(realmSource.realmGet$commandId());
        unmanagedCopy.realmSet$commandExtension(realmSource.realmGet$commandExtension());
        unmanagedCopy.realmSet$fullMessage(realmSource.realmGet$fullMessage());
        unmanagedCopy.realmSet$deviceId(realmSource.realmGet$deviceId());
        unmanagedCopy.realmSet$createdOn(realmSource.realmGet$createdOn());
        unmanagedCopy.realmSet$timestamp(realmSource.realmGet$timestamp());
        unmanagedCopy.realmSet$latitude(realmSource.realmGet$latitude());
        unmanagedCopy.realmSet$longitude(realmSource.realmGet$longitude());
        unmanagedCopy.realmSet$heading(realmSource.realmGet$heading());
        unmanagedCopy.realmSet$altitude(realmSource.realmGet$altitude());
        unmanagedCopy.realmSet$assistedGPS(realmSource.realmGet$assistedGPS());
        unmanagedCopy.realmSet$gpsSpeed(realmSource.realmGet$gpsSpeed());
        unmanagedCopy.realmSet$horizontalUncertainity(realmSource.realmGet$horizontalUncertainity());
        unmanagedCopy.realmSet$perpendicularUncertainity(realmSource.realmGet$perpendicularUncertainity());
        unmanagedCopy.realmSet$horizontalUncertainityAngle(realmSource.realmGet$horizontalUncertainityAngle());
        unmanagedCopy.realmSet$altitudeUncertainity(realmSource.realmGet$altitudeUncertainity());
        unmanagedCopy.realmSet$batteryStatus(realmSource.realmGet$batteryStatus());
        unmanagedCopy.realmSet$temperature(realmSource.realmGet$temperature());
        unmanagedCopy.realmSet$command(realmSource.realmGet$command());
        return unmanagedObject;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DeviceAlert = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{payloadSize:");
        stringBuilder.append(realmGet$payloadSize());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{commandId:");
        stringBuilder.append(realmGet$commandId() != null ? realmGet$commandId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{commandExtension:");
        stringBuilder.append(realmGet$commandExtension() != null ? realmGet$commandExtension() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{fullMessage:");
        stringBuilder.append(realmGet$fullMessage() != null ? realmGet$fullMessage() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{deviceId:");
        stringBuilder.append(realmGet$deviceId() != null ? realmGet$deviceId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{createdOn:");
        stringBuilder.append(realmGet$createdOn());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp());
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
        stringBuilder.append("{heading:");
        stringBuilder.append(realmGet$heading() != null ? realmGet$heading() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{altitude:");
        stringBuilder.append(realmGet$altitude() != null ? realmGet$altitude() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{assistedGPS:");
        stringBuilder.append(realmGet$assistedGPS() != null ? realmGet$assistedGPS() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{gpsSpeed:");
        stringBuilder.append(realmGet$gpsSpeed() != null ? realmGet$gpsSpeed() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{horizontalUncertainity:");
        stringBuilder.append(realmGet$horizontalUncertainity() != null ? realmGet$horizontalUncertainity() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{perpendicularUncertainity:");
        stringBuilder.append(realmGet$perpendicularUncertainity() != null ? realmGet$perpendicularUncertainity() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{horizontalUncertainityAngle:");
        stringBuilder.append(realmGet$horizontalUncertainityAngle() != null ? realmGet$horizontalUncertainityAngle() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{altitudeUncertainity:");
        stringBuilder.append(realmGet$altitudeUncertainity() != null ? realmGet$altitudeUncertainity() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{batteryStatus:");
        stringBuilder.append(realmGet$batteryStatus() != null ? realmGet$batteryStatus() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{temperature:");
        stringBuilder.append(realmGet$temperature() != null ? realmGet$temperature() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{command:");
        stringBuilder.append(realmGet$command() != null ? realmGet$command() : "null");
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
        DeviceAlertRealmProxy aDeviceAlert = (DeviceAlertRealmProxy) o;
        String path = this.proxyState.getRealm$realm().getPath();
        String otherPath = aDeviceAlert.proxyState.getRealm$realm().getPath();
        if (path == null ? otherPath != null : !path.equals(otherPath)) {
            return false;
        }
        String tableName = this.proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDeviceAlert.proxyState.getRow$realm().getTable().getName();
        if (tableName == null ? otherTableName != null : !tableName.equals(otherTableName)) {
            return false;
        }
        if (this.proxyState.getRow$realm().getIndex() != aDeviceAlert.proxyState.getRow$realm().getIndex()) {
            return false;
        }
        return true;
    }
}
