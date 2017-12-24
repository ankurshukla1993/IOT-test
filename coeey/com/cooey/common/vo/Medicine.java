package com.cooey.common.vo;

import com.cooey.common.converter.RealmListParcelConverter;
import com.google.gson.annotations.SerializedName;
import io.realm.MedicineRealmProxyInterface;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import org.parceler.Parcel;
import org.parceler.Parcel.Serialization;
import org.parceler.ParcelProperty;
import org.parceler.ParcelPropertyConverter;

@Parcel(analyze = {Medicine.class}, value = Serialization.BEAN)
public class Medicine extends RealmObject implements MedicineRealmProxyInterface {
    @SerializedName("active")
    private String active;
    @SerializedName("addedOn")
    private long addedOn;
    @SerializedName("applicationId")
    private String applicationId;
    @SerializedName("archived")
    private boolean archived;
    @SerializedName("createdOn")
    private String createdOn;
    @SerializedName("description")
    private String description;
    @SerializedName("externalId")
    private String externalId;
    @SerializedName("id")
    @PrimaryKey
    private String id;
    @SerializedName("medicineId")
    private String medicineId;
    @SerializedName("name")
    private String name;
    @SerializedName("notes")
    private String notes;
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    @SerializedName("reminders")
    @ParcelProperty("reminders")
    public RealmList<Reminder> reminders;
    @SerializedName("tenantId")
    private String tenantId;
    @SerializedName("toBeTakenTill")
    private long toBeTakenTill;
    @SerializedName("updatedOn")
    private String updatedOn;
    @SerializedName("userId")
    private String userId;

    public String realmGet$active() {
        return this.active;
    }

    public long realmGet$addedOn() {
        return this.addedOn;
    }

    public String realmGet$applicationId() {
        return this.applicationId;
    }

    public boolean realmGet$archived() {
        return this.archived;
    }

    public String realmGet$createdOn() {
        return this.createdOn;
    }

    public String realmGet$description() {
        return this.description;
    }

    public String realmGet$externalId() {
        return this.externalId;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$medicineId() {
        return this.medicineId;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$notes() {
        return this.notes;
    }

    public RealmList realmGet$reminders() {
        return this.reminders;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public long realmGet$toBeTakenTill() {
        return this.toBeTakenTill;
    }

    public String realmGet$updatedOn() {
        return this.updatedOn;
    }

    public String realmGet$userId() {
        return this.userId;
    }

    public void realmSet$active(String str) {
        this.active = str;
    }

    public void realmSet$addedOn(long j) {
        this.addedOn = j;
    }

    public void realmSet$applicationId(String str) {
        this.applicationId = str;
    }

    public void realmSet$archived(boolean z) {
        this.archived = z;
    }

    public void realmSet$createdOn(String str) {
        this.createdOn = str;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$externalId(String str) {
        this.externalId = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$medicineId(String str) {
        this.medicineId = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$notes(String str) {
        this.notes = str;
    }

    public void realmSet$reminders(RealmList realmList) {
        this.reminders = realmList;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$toBeTakenTill(long j) {
        this.toBeTakenTill = j;
    }

    public void realmSet$updatedOn(String str) {
        this.updatedOn = str;
    }

    public void realmSet$userId(String str) {
        this.userId = str;
    }

    public Medicine(String id, String name, RealmList<Reminder> reminders, String description, String userId, String notes) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(id);
        realmSet$name(name);
        realmSet$reminders(reminders);
        realmSet$description(description);
        realmSet$userId(userId);
        realmSet$notes(notes);
    }

    public Medicine() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public RealmList<Reminder> getReminders() {
        return realmGet$reminders();
    }

    public void setReminders(RealmList<Reminder> reminders) {
        realmSet$reminders(reminders);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String description) {
        realmSet$description(description);
    }

    public String getUserId() {
        return realmGet$userId();
    }

    public void setUserId(String userId) {
        realmSet$userId(userId);
    }

    public String getNotes() {
        return realmGet$notes();
    }

    public void setNotes(String notes) {
        realmSet$notes(notes);
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public String getCreatedOn() {
        return realmGet$createdOn();
    }

    public void setCreatedOn(String createdOn) {
        realmSet$createdOn(createdOn);
    }

    public String getUpdatedOn() {
        return realmGet$updatedOn();
    }

    public void setUpdatedOn(String updatedOn) {
        realmSet$updatedOn(updatedOn);
    }

    public String getMedicineId() {
        return realmGet$medicineId();
    }

    public void setMedicineId(String medicineId) {
        realmSet$medicineId(medicineId);
    }

    public long getAddedOn() {
        return realmGet$addedOn();
    }

    public void setAddedOn(long addedOn) {
        realmSet$addedOn(addedOn);
    }

    public long getToBeTakenTill() {
        return realmGet$toBeTakenTill();
    }

    public void setToBeTakenTill(long toBeTakenTill) {
        realmSet$toBeTakenTill(toBeTakenTill);
    }

    public String getApplicationId() {
        return realmGet$applicationId();
    }

    public void setApplicationId(String applicationId) {
        realmSet$applicationId(applicationId);
    }

    public boolean isArchived() {
        return realmGet$archived();
    }

    public void setArchived(boolean archived) {
        realmSet$archived(archived);
    }

    public String getExternalId() {
        return realmGet$externalId();
    }

    public void setExternalId(String externalId) {
        realmSet$externalId(externalId);
    }

    public String getActive() {
        return realmGet$active();
    }

    public void setActive(String active) {
        realmSet$active(active);
    }
}
