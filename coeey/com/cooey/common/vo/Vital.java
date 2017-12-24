package com.cooey.common.vo;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.Observable.OnPropertyChangedCallback;
import android.databinding.PropertyChangeRegistry;
import com.cooey.common.BR;
import com.cooey.common.config.FieldConfig;
import com.cooey.common.config.VitalTemplatesConfigListItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.VitalRealmProxyInterface;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vital extends RealmObject implements Observable, VitalItem, Serializable, VitalRealmProxyInterface {
    public static final String BLOOD_PRESSURE = "BLOOD_PRESSURE";
    public static final String BLOOD_SUGAR = "BLOOD_GLUCOSE";
    @Expose
    @Ignore
    private String alert;
    @SerializedName("contextId")
    private String contextId;
    @SerializedName("contextType")
    private String contextType;
    @SerializedName("deviceReading")
    private boolean deviceReading;
    @SerializedName("deviceType")
    private String deviceType;
    @Expose
    private boolean isSync;
    @Ignore
    private transient PropertyChangeRegistry mCallbacks;
    @SerializedName("parameters")
    private String parameters;
    @SerializedName("platform")
    private String platform;
    private String postAction;
    private String takenBy;
    private long takenOn;
    @SerializedName("tenantId")
    private String tenantId;
    private long timestamp;
    @SerializedName("userId")
    private String userId;
    @SerializedName("id")
    @PrimaryKey
    private String vitalId;
    @SerializedName("vitalType")
    private String vitalType;

    public String realmGet$contextId() {
        return this.contextId;
    }

    public String realmGet$contextType() {
        return this.contextType;
    }

    public boolean realmGet$deviceReading() {
        return this.deviceReading;
    }

    public String realmGet$deviceType() {
        return this.deviceType;
    }

    public boolean realmGet$isSync() {
        return this.isSync;
    }

    public String realmGet$parameters() {
        return this.parameters;
    }

    public String realmGet$platform() {
        return this.platform;
    }

    public String realmGet$postAction() {
        return this.postAction;
    }

    public String realmGet$takenBy() {
        return this.takenBy;
    }

    public long realmGet$takenOn() {
        return this.takenOn;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public long realmGet$timestamp() {
        return this.timestamp;
    }

    public String realmGet$userId() {
        return this.userId;
    }

    public String realmGet$vitalId() {
        return this.vitalId;
    }

    public String realmGet$vitalType() {
        return this.vitalType;
    }

    public void realmSet$contextId(String str) {
        this.contextId = str;
    }

    public void realmSet$contextType(String str) {
        this.contextType = str;
    }

    public void realmSet$deviceReading(boolean z) {
        this.deviceReading = z;
    }

    public void realmSet$deviceType(String str) {
        this.deviceType = str;
    }

    public void realmSet$isSync(boolean z) {
        this.isSync = z;
    }

    public void realmSet$parameters(String str) {
        this.parameters = str;
    }

    public void realmSet$platform(String str) {
        this.platform = str;
    }

    public void realmSet$postAction(String str) {
        this.postAction = str;
    }

    public void realmSet$takenBy(String str) {
        this.takenBy = str;
    }

    public void realmSet$takenOn(long j) {
        this.takenOn = j;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$timestamp(long j) {
        this.timestamp = j;
    }

    public void realmSet$userId(String str) {
        this.userId = str;
    }

    public void realmSet$vitalId(String str) {
        this.vitalId = str;
    }

    public void realmSet$vitalType(String str) {
        this.vitalType = str;
    }

    public long getTimestamp() {
        return realmGet$timestamp();
    }

    public void setTimestamp(long timestamp) {
        realmSet$timestamp(timestamp);
    }

    public Vital(String vitalId, String vitalType, long takenOn, String parameters, String userId, String tenantId) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$vitalId(vitalId);
        realmSet$vitalType(vitalType);
        realmSet$takenOn(takenOn);
        realmSet$parameters(parameters);
        realmSet$userId(userId);
        realmSet$tenantId(tenantId);
    }

    public String getVitalId() {
        return realmGet$vitalId();
    }

    public void setVitalId(String vitalId) {
        realmSet$vitalId(vitalId);
    }

    public Vital() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getVitalType() {
        return realmGet$vitalType();
    }

    public void setVitalType(String vitalType) {
        realmSet$vitalType(vitalType);
    }

    public long getTakenOn() {
        return realmGet$takenOn();
    }

    public void setTakenOn(long takenOn) {
        realmSet$takenOn(takenOn);
    }

    public String getPostAction() {
        return realmGet$postAction();
    }

    public void setPostAction(String postAction) {
        realmSet$postAction(postAction);
    }

    public String getContextType() {
        return realmGet$contextType();
    }

    public void setContextType(String contextType) {
        realmSet$contextType(contextType);
    }

    public String getContextId() {
        return realmGet$contextId();
    }

    public void setContextId(String contextId) {
        realmSet$contextId(contextId);
    }

    @Bindable
    public String getParameters() {
        return realmGet$parameters();
    }

    public void setParameters(String parameters) {
        realmSet$parameters(parameters);
        notifyPropertyChanged(BR.parameters);
    }

    public boolean isSync() {
        return realmGet$isSync();
    }

    public void setSync(boolean sync) {
        realmSet$isSync(sync);
    }

    public String getUserId() {
        return realmGet$userId();
    }

    public void setUserId(String userId) {
        realmSet$userId(userId);
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public String getAlert() {
        return this.alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Map<String, String> getParameterMap() {
        return (Map) new GsonBuilder().create().fromJson(realmGet$parameters(), new HashMap().getClass());
    }

    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (this.mCallbacks == null) {
            this.mCallbacks = new PropertyChangeRegistry();
        }
        this.mCallbacks.add(callback);
    }

    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (this.mCallbacks != null) {
            this.mCallbacks.remove(callback);
        }
    }

    public void notifyPropertyChanged(int fieldId) {
        if (this.mCallbacks != null) {
            this.mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }

    public int getListItemType() {
        if (realmGet$vitalType().equals("BLOOD_PRESSURE")) {
            return 1;
        }
        if (realmGet$vitalType().equals("WEIGHT")) {
            return 3;
        }
        return 2;
    }

    public String getVitalUnit(String label, List<FieldConfig> fieldConfigList) {
        for (FieldConfig fieldConfig : fieldConfigList) {
            if (fieldConfig.getLabel().equalsIgnoreCase(label)) {
                return fieldConfig.getUnit();
            }
        }
        return null;
    }

    public String getDeviceType() {
        return realmGet$deviceType();
    }

    public void setDeviceType(String deviceType) {
        realmSet$deviceType(deviceType);
    }

    public String getTakenBy() {
        return realmGet$takenBy();
    }

    public void setTakenBy(String takenBy) {
        realmSet$takenBy(takenBy);
    }

    public String getPlatform() {
        return realmGet$platform();
    }

    public void setPlatform(String platform) {
        realmSet$platform(platform);
    }

    public boolean isDeviceReading() {
        return realmGet$deviceReading();
    }

    public void setDeviceReading(boolean deviceReading) {
        realmSet$deviceReading(deviceReading);
    }

    public List<FeedBackInput> getFeedBackInputs() {
        try {
            return Arrays.asList((FeedBackInput[]) new Gson().fromJson(new Gson().toJson(((Map) new GsonBuilder().create().fromJson(realmGet$postAction(), new HashMap().getClass())).get("feedBackInput")), FeedBackInput[].class));
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public List<FieldConfig> getVitalParameters(List<VitalTemplatesConfigListItem> vitalTemplatesConfigListItems, String vitalName) {
        Map<String, List<FieldConfig>> fieldConfigMap = new HashMap();
        if (vitalTemplatesConfigListItems != null && vitalTemplatesConfigListItems.size() > 0) {
            for (VitalTemplatesConfigListItem vitalTemplatesConfigListItem : vitalTemplatesConfigListItems) {
                fieldConfigMap.put(vitalTemplatesConfigListItem.getType(), vitalTemplatesConfigListItem.getFieldConfigList());
            }
        }
        return (List) fieldConfigMap.get(vitalName);
    }
}
