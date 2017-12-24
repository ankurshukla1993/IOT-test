package com.cooey.android.vitals;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b3\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\t\u00104\u001a\u00020\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0002\u0010\u001cJ\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0006HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¢\u0001\u0010A\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u0010BJ\u0013\u0010C\u001a\u00020\u00112\b\u0010D\u001a\u0004\u0018\u00010EHÖ\u0003J\t\u0010F\u001a\u00020GHÖ\u0001J\t\u0010H\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0016R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0014\"\u0004\b#\u0010\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0014\"\u0004\b'\u0010\u0016R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0014\"\u0004\b)\u0010\u0016R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0014\"\u0004\b+\u0010\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0014\"\u0004\b1\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0014\"\u0004\b3\u0010\u0016¨\u0006I"}, d2 = {"Lcom/cooey/android/vitals/Vital;", "Lcom/cooey/android/vitals/BaseEntity;", "id", "", "vitalType", "takenOn", "", "parameters", "userId", "takenBy", "postAction", "deviceId", "deviceType", "platform", "contextType", "contextId", "deviceReading", "", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getContextId", "()Ljava/lang/String;", "setContextId", "(Ljava/lang/String;)V", "getContextType", "setContextType", "getDeviceId", "setDeviceId", "getDeviceReading", "()Ljava/lang/Boolean;", "setDeviceReading", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getDeviceType", "setDeviceType", "getId", "setId", "getParameters", "setParameters", "getPlatform", "setPlatform", "getPostAction", "setPostAction", "getTakenBy", "setTakenBy", "getTakenOn", "()J", "setTakenOn", "(J)V", "getUserId", "setUserId", "getVitalType", "setVitalType", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/cooey/android/vitals/Vital;", "equals", "other", "", "hashCode", "", "toString", "vitals_release"}, k = 1, mv = {1, 1, 7})
@Entity(tableName = "vitals")
/* compiled from: Vital.kt */
public final class Vital extends BaseEntity {
    @Nullable
    private String contextId;
    @Nullable
    private String contextType;
    @Nullable
    private String deviceId;
    @Nullable
    private Boolean deviceReading;
    @Nullable
    private String deviceType;
    @PrimaryKey
    @NotNull
    private String id;
    @Nullable
    private String parameters;
    @Nullable
    private String platform;
    @Nullable
    private String postAction;
    @Nullable
    private String takenBy;
    private long takenOn;
    @NotNull
    private String userId;
    @NotNull
    private String vitalType;

    @NotNull
    public static /* bridge */ /* synthetic */ Vital copy$default(Vital vital, String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, Boolean bool, int i, Object obj) {
        return vital.copy((i & 1) != 0 ? vital.id : str, (i & 2) != 0 ? vital.vitalType : str2, (i & 4) != 0 ? vital.takenOn : j, (i & 8) != 0 ? vital.parameters : str3, (i & 16) != 0 ? vital.userId : str4, (i & 32) != 0 ? vital.takenBy : str5, (i & 64) != 0 ? vital.postAction : str6, (i & 128) != 0 ? vital.deviceId : str7, (i & 256) != 0 ? vital.deviceType : str8, (i & 512) != 0 ? vital.platform : str9, (i & 1024) != 0 ? vital.contextType : str10, (i & 2048) != 0 ? vital.contextId : str11, (i & 4096) != 0 ? vital.deviceReading : bool);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @Nullable
    public final String component10() {
        return this.platform;
    }

    @Nullable
    public final String component11() {
        return this.contextType;
    }

    @Nullable
    public final String component12() {
        return this.contextId;
    }

    @Nullable
    public final Boolean component13() {
        return this.deviceReading;
    }

    @NotNull
    public final String component2() {
        return this.vitalType;
    }

    public final long component3() {
        return this.takenOn;
    }

    @Nullable
    public final String component4() {
        return this.parameters;
    }

    @NotNull
    public final String component5() {
        return this.userId;
    }

    @Nullable
    public final String component6() {
        return this.takenBy;
    }

    @Nullable
    public final String component7() {
        return this.postAction;
    }

    @Nullable
    public final String component8() {
        return this.deviceId;
    }

    @Nullable
    public final String component9() {
        return this.deviceType;
    }

    @NotNull
    public final Vital copy(@NotNull String id, @NotNull String vitalType, long takenOn, @Nullable String parameters, @NotNull String userId, @Nullable String takenBy, @Nullable String postAction, @Nullable String deviceId, @Nullable String deviceType, @Nullable String platform, @Nullable String contextType, @Nullable String contextId, @Nullable Boolean deviceReading) {
        Intrinsics.checkParameterIsNotNull(id, ShareConstants.WEB_DIALOG_PARAM_ID);
        Intrinsics.checkParameterIsNotNull(vitalType, "vitalType");
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        return new Vital(id, vitalType, takenOn, parameters, userId, takenBy, postAction, deviceId, deviceType, platform, contextType, contextId, deviceReading);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof Vital)) {
                return false;
            }
            Vital vital = (Vital) obj;
            if (!Intrinsics.areEqual(this.id, vital.id) || !Intrinsics.areEqual(this.vitalType, vital.vitalType)) {
                return false;
            }
            if (!((this.takenOn == vital.takenOn) && Intrinsics.areEqual(this.parameters, vital.parameters) && Intrinsics.areEqual(this.userId, vital.userId) && Intrinsics.areEqual(this.takenBy, vital.takenBy) && Intrinsics.areEqual(this.postAction, vital.postAction) && Intrinsics.areEqual(this.deviceId, vital.deviceId) && Intrinsics.areEqual(this.deviceType, vital.deviceType) && Intrinsics.areEqual(this.platform, vital.platform) && Intrinsics.areEqual(this.contextType, vital.contextType) && Intrinsics.areEqual(this.contextId, vital.contextId) && Intrinsics.areEqual(this.deviceReading, vital.deviceReading))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        String str = this.id;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        str = this.vitalType;
        int hashCode2 = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        long j = this.takenOn;
        hashCode = (hashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        str = this.parameters;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.userId;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.takenBy;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.postAction;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.deviceId;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.deviceType;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.platform;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.contextType;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.contextId;
        hashCode2 = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        Boolean bool = this.deviceReading;
        if (bool != null) {
            i = bool.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "Vital(id=" + this.id + ", vitalType=" + this.vitalType + ", takenOn=" + this.takenOn + ", parameters=" + this.parameters + ", userId=" + this.userId + ", takenBy=" + this.takenBy + ", postAction=" + this.postAction + ", deviceId=" + this.deviceId + ", deviceType=" + this.deviceType + ", platform=" + this.platform + ", contextType=" + this.contextType + ", contextId=" + this.contextId + ", deviceReading=" + this.deviceReading + ")";
    }

    public Vital(@NotNull String id, @NotNull String vitalType, long takenOn, @Nullable String parameters, @NotNull String userId, @Nullable String takenBy, @Nullable String postAction, @Nullable String deviceId, @Nullable String deviceType, @Nullable String platform, @Nullable String contextType, @Nullable String contextId, @Nullable Boolean deviceReading) {
        Intrinsics.checkParameterIsNotNull(id, ShareConstants.WEB_DIALOG_PARAM_ID);
        Intrinsics.checkParameterIsNotNull(vitalType, "vitalType");
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        super(0, 0, null, null, null, false, false, 127, null);
        this.id = id;
        this.vitalType = vitalType;
        this.takenOn = takenOn;
        this.parameters = parameters;
        this.userId = userId;
        this.takenBy = takenBy;
        this.postAction = postAction;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.platform = platform;
        this.contextType = contextType;
        this.contextId = contextId;
        this.deviceReading = deviceReading;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final long getTakenOn() {
        return this.takenOn;
    }

    @NotNull
    public final String getVitalType() {
        return this.vitalType;
    }

    public final void setId(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.id = <set-?>;
    }

    public final void setTakenOn(long <set-?>) {
        this.takenOn = <set-?>;
    }

    public final void setVitalType(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalType = <set-?>;
    }

    @Nullable
    public final String getParameters() {
        return this.parameters;
    }

    @Nullable
    public final String getTakenBy() {
        return this.takenBy;
    }

    @NotNull
    public final String getUserId() {
        return this.userId;
    }

    public final void setParameters(@Nullable String <set-?>) {
        this.parameters = <set-?>;
    }

    public final void setTakenBy(@Nullable String <set-?>) {
        this.takenBy = <set-?>;
    }

    public final void setUserId(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.userId = <set-?>;
    }

    @Nullable
    public final String getDeviceId() {
        return this.deviceId;
    }

    @Nullable
    public final String getDeviceType() {
        return this.deviceType;
    }

    @Nullable
    public final String getPostAction() {
        return this.postAction;
    }

    public final void setDeviceId(@Nullable String <set-?>) {
        this.deviceId = <set-?>;
    }

    public final void setDeviceType(@Nullable String <set-?>) {
        this.deviceType = <set-?>;
    }

    public final void setPostAction(@Nullable String <set-?>) {
        this.postAction = <set-?>;
    }

    @Nullable
    public final String getContextId() {
        return this.contextId;
    }

    @Nullable
    public final String getContextType() {
        return this.contextType;
    }

    @Nullable
    public final String getPlatform() {
        return this.platform;
    }

    public final void setContextId(@Nullable String <set-?>) {
        this.contextId = <set-?>;
    }

    public final void setContextType(@Nullable String <set-?>) {
        this.contextType = <set-?>;
    }

    public final void setPlatform(@Nullable String <set-?>) {
        this.platform = <set-?>;
    }

    @Nullable
    public final Boolean getDeviceReading() {
        return this.deviceReading;
    }

    public final void setDeviceReading(@Nullable Boolean <set-?>) {
        this.deviceReading = <set-?>;
    }
}
