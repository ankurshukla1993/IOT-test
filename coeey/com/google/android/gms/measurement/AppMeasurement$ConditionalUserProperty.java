package com.google.android.gms.measurement;

import android.os.Bundle;
import android.support.annotation.Keep;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzckn;

public class AppMeasurement$ConditionalUserProperty {
    @Keep
    public boolean mActive;
    @Keep
    public String mAppId;
    @Keep
    public long mCreationTimestamp;
    @Keep
    public String mExpiredEventName;
    @Keep
    public Bundle mExpiredEventParams;
    @Keep
    public String mName;
    @Keep
    public String mOrigin;
    @Keep
    public long mTimeToLive;
    @Keep
    public String mTimedOutEventName;
    @Keep
    public Bundle mTimedOutEventParams;
    @Keep
    public String mTriggerEventName;
    @Keep
    public long mTriggerTimeout;
    @Keep
    public String mTriggeredEventName;
    @Keep
    public Bundle mTriggeredEventParams;
    @Keep
    public long mTriggeredTimestamp;
    @Keep
    public Object mValue;

    public AppMeasurement$ConditionalUserProperty(AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        zzbq.checkNotNull(appMeasurement$ConditionalUserProperty);
        this.mAppId = appMeasurement$ConditionalUserProperty.mAppId;
        this.mOrigin = appMeasurement$ConditionalUserProperty.mOrigin;
        this.mCreationTimestamp = appMeasurement$ConditionalUserProperty.mCreationTimestamp;
        this.mName = appMeasurement$ConditionalUserProperty.mName;
        if (appMeasurement$ConditionalUserProperty.mValue != null) {
            this.mValue = zzckn.zzaf(appMeasurement$ConditionalUserProperty.mValue);
            if (this.mValue == null) {
                this.mValue = appMeasurement$ConditionalUserProperty.mValue;
            }
        }
        this.mValue = appMeasurement$ConditionalUserProperty.mValue;
        this.mActive = appMeasurement$ConditionalUserProperty.mActive;
        this.mTriggerEventName = appMeasurement$ConditionalUserProperty.mTriggerEventName;
        this.mTriggerTimeout = appMeasurement$ConditionalUserProperty.mTriggerTimeout;
        this.mTimedOutEventName = appMeasurement$ConditionalUserProperty.mTimedOutEventName;
        if (appMeasurement$ConditionalUserProperty.mTimedOutEventParams != null) {
            this.mTimedOutEventParams = new Bundle(appMeasurement$ConditionalUserProperty.mTimedOutEventParams);
        }
        this.mTriggeredEventName = appMeasurement$ConditionalUserProperty.mTriggeredEventName;
        if (appMeasurement$ConditionalUserProperty.mTriggeredEventParams != null) {
            this.mTriggeredEventParams = new Bundle(appMeasurement$ConditionalUserProperty.mTriggeredEventParams);
        }
        this.mTriggeredTimestamp = appMeasurement$ConditionalUserProperty.mTriggeredTimestamp;
        this.mTimeToLive = appMeasurement$ConditionalUserProperty.mTimeToLive;
        this.mExpiredEventName = appMeasurement$ConditionalUserProperty.mExpiredEventName;
        if (appMeasurement$ConditionalUserProperty.mExpiredEventParams != null) {
            this.mExpiredEventParams = new Bundle(appMeasurement$ConditionalUserProperty.mExpiredEventParams);
        }
    }
}
