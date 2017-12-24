package com.cooey.common.vo.careplan;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.cooey.common.vo.Schedule;
import io.realm.InterventionRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class Intervention extends RealmObject implements Parcelable, InterventionRealmProxyInterface {
    public static final Creator<Intervention> CREATOR = new C09031();
    private long assignedOn;
    private boolean caretakerNotificationEnabled;
    private long createdOn;
    private boolean guaridanNotificationEnabled;
    @PrimaryKey
    private String id;
    private String name;
    private String ownerId;
    private String parameters;
    private String parentId;
    private String patientId;
    private boolean patientNotificationEnabled;
    private String permissions;
    private Schedule schedule;
    private String tenantId;
    private String type;
    private long updatedOn;

    static class C09031 implements Creator<Intervention> {
        C09031() {
        }

        public Intervention createFromParcel(Parcel in) {
            return new Intervention(in);
        }

        public Intervention[] newArray(int size) {
            return new Intervention[size];
        }
    }

    public long realmGet$assignedOn() {
        return this.assignedOn;
    }

    public boolean realmGet$caretakerNotificationEnabled() {
        return this.caretakerNotificationEnabled;
    }

    public long realmGet$createdOn() {
        return this.createdOn;
    }

    public boolean realmGet$guaridanNotificationEnabled() {
        return this.guaridanNotificationEnabled;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$ownerId() {
        return this.ownerId;
    }

    public String realmGet$parameters() {
        return this.parameters;
    }

    public String realmGet$parentId() {
        return this.parentId;
    }

    public String realmGet$patientId() {
        return this.patientId;
    }

    public boolean realmGet$patientNotificationEnabled() {
        return this.patientNotificationEnabled;
    }

    public String realmGet$permissions() {
        return this.permissions;
    }

    public Schedule realmGet$schedule() {
        return this.schedule;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public String realmGet$type() {
        return this.type;
    }

    public long realmGet$updatedOn() {
        return this.updatedOn;
    }

    public void realmSet$assignedOn(long j) {
        this.assignedOn = j;
    }

    public void realmSet$caretakerNotificationEnabled(boolean z) {
        this.caretakerNotificationEnabled = z;
    }

    public void realmSet$createdOn(long j) {
        this.createdOn = j;
    }

    public void realmSet$guaridanNotificationEnabled(boolean z) {
        this.guaridanNotificationEnabled = z;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$ownerId(String str) {
        this.ownerId = str;
    }

    public void realmSet$parameters(String str) {
        this.parameters = str;
    }

    public void realmSet$parentId(String str) {
        this.parentId = str;
    }

    public void realmSet$patientId(String str) {
        this.patientId = str;
    }

    public void realmSet$patientNotificationEnabled(boolean z) {
        this.patientNotificationEnabled = z;
    }

    public void realmSet$permissions(String str) {
        this.permissions = str;
    }

    public void realmSet$schedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public void realmSet$updatedOn(long j) {
        this.updatedOn = j;
    }

    protected Intervention(Parcel in) {
        boolean z;
        boolean z2 = true;
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$guaridanNotificationEnabled(in.readByte() != (byte) 0);
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        realmSet$caretakerNotificationEnabled(z);
        realmSet$patientId(in.readString());
        realmSet$assignedOn(in.readLong());
        realmSet$updatedOn(in.readLong());
        realmSet$type(in.readString());
        realmSet$ownerId(in.readString());
        realmSet$createdOn(in.readLong());
        realmSet$parentId(in.readString());
        realmSet$tenantId(in.readString());
        realmSet$name(in.readString());
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        realmSet$patientNotificationEnabled(z2);
        realmSet$id(in.readString());
        realmSet$parameters(in.readString());
    }

    public Intervention() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public void setGuaridanNotificationEnabled(boolean guaridanNotificationEnabled) {
        realmSet$guaridanNotificationEnabled(guaridanNotificationEnabled);
    }

    public boolean isGuaridanNotificationEnabled() {
        return realmGet$guaridanNotificationEnabled();
    }

    public void setCaretakerNotificationEnabled(boolean caretakerNotificationEnabled) {
        realmSet$caretakerNotificationEnabled(caretakerNotificationEnabled);
    }

    public boolean isCaretakerNotificationEnabled() {
        return realmGet$caretakerNotificationEnabled();
    }

    public void setPatientId(String patientId) {
        realmSet$patientId(patientId);
    }

    public String getPatientId() {
        return realmGet$patientId();
    }

    public void setAssignedOn(long assignedOn) {
        realmSet$assignedOn(assignedOn);
    }

    public long getAssignedOn() {
        return realmGet$assignedOn();
    }

    public void setUpdatedOn(long updatedOn) {
        realmSet$updatedOn(updatedOn);
    }

    public long getUpdatedOn() {
        return realmGet$updatedOn();
    }

    public void setType(String type) {
        realmSet$type(type);
    }

    public String getType() {
        return realmGet$type();
    }

    public void setOwnerId(String ownerId) {
        realmSet$ownerId(ownerId);
    }

    public String getOwnerId() {
        return realmGet$ownerId();
    }

    public void setCreatedOn(long createdOn) {
        realmSet$createdOn(createdOn);
    }

    public long getCreatedOn() {
        return realmGet$createdOn();
    }

    public void setParentId(String parentId) {
        realmSet$parentId(parentId);
    }

    public String getParentId() {
        return realmGet$parentId();
    }

    public void setSchedule(Schedule schedule) {
        realmSet$schedule(schedule);
    }

    public Schedule getSchedule() {
        return realmGet$schedule();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public Object getTenantId() {
        return realmGet$tenantId();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setPatientNotificationEnabled(boolean patientNotificationEnabled) {
        realmSet$patientNotificationEnabled(patientNotificationEnabled);
    }

    public boolean isPatientNotificationEnabled() {
        return realmGet$patientNotificationEnabled();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setParameters(String parameters) {
        realmSet$parameters(parameters);
    }

    public String getParameters() {
        return realmGet$parameters();
    }

    public String getPermissions() {
        return realmGet$permissions();
    }

    public void setPermissions(String permissions) {
        realmSet$permissions(permissions);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 1;
        dest.writeByte((byte) (realmGet$guaridanNotificationEnabled() ? 1 : 0));
        if (realmGet$caretakerNotificationEnabled()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        dest.writeString(realmGet$patientId());
        dest.writeLong(realmGet$assignedOn());
        dest.writeLong(realmGet$updatedOn());
        dest.writeString(realmGet$type());
        dest.writeString(realmGet$ownerId());
        dest.writeLong(realmGet$createdOn());
        dest.writeString(realmGet$parentId());
        dest.writeString(realmGet$tenantId());
        dest.writeString(realmGet$name());
        if (!realmGet$patientNotificationEnabled()) {
            i2 = 0;
        }
        dest.writeByte((byte) i2);
        dest.writeString(realmGet$id());
        dest.writeString(realmGet$parameters());
    }
}
