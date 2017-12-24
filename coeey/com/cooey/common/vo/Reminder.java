package com.cooey.common.vo;

import com.cooey.common.converter.RealmListParcelConverter;
import com.cooey.common.utils.TimeProcessor;
import com.evernote.android.job.JobRequest.Builder;
import com.evernote.android.job.util.support.PersistableBundleCompat;
import com.facebook.share.internal.ShareConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.ReminderRealmProxyInterface;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import org.parceler.Parcel;
import org.parceler.Parcel.Serialization;
import org.parceler.ParcelProperty;
import org.parceler.ParcelPropertyConverter;

@Parcel(analyze = {Reminder.class}, value = Serialization.BEAN)
public class Reminder extends RealmObject implements ReminderRealmProxyInterface {
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    @SerializedName("activeDays")
    @ParcelProperty("activeDays")
    public RealmList<RealmBoolean> activeDays;
    @PrimaryKey
    @Expose
    private String id;
    @Expose
    private transient String itemId;
    @Expose
    private transient int jobId;
    @SerializedName("timeOfDay")
    private String timeOfDay;

    public RealmList realmGet$activeDays() {
        return this.activeDays;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$timeOfDay() {
        return this.timeOfDay;
    }

    public void realmSet$activeDays(RealmList realmList) {
        this.activeDays = realmList;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$timeOfDay(String str) {
        this.timeOfDay = str;
    }

    public Reminder() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public void setActiveDays(RealmList<RealmBoolean> activeDays) {
        realmSet$activeDays(activeDays);
    }

    public RealmList<RealmBoolean> getActiveDays() {
        return realmGet$activeDays();
    }

    public void setTimeOfDay(String timeOfDay) {
        realmSet$timeOfDay(timeOfDay);
    }

    public String getTimeOfDay() {
        return realmGet$timeOfDay();
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public int getJobId() {
        return this.jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int scheduleMedicineJob() {
        PersistableBundleCompat extras = new PersistableBundleCompat();
        extras.putString(ShareConstants.WEB_DIALOG_PARAM_ID, realmGet$id());
        extras.putString("itemId", this.itemId);
        TimeProcessor timeProcessor = new TimeProcessor();
        String[] result = realmGet$timeOfDay().split(":");
        return new Builder("MEDICINE").setExact(timeProcessor.getTimeRemaining(Integer.parseInt(result[0]), Integer.parseInt(result[1]))).setPersisted(true).setExtras(extras).setRequiresCharging(false).setRequiresDeviceIdle(false).build().schedule();
    }
}
