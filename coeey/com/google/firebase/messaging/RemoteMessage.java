package com.google.firebase.messaging;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.Map;
import java.util.Map.Entry;

public final class RemoteMessage extends zzbej {
    public static final Creator<RemoteMessage> CREATOR = new zzf();
    Bundle mBundle;
    private Map<String, String> zzdms;
    private Notification zznvp;

    public static class Builder {
        private final Bundle mBundle = new Bundle();
        private final Map<String, String> zzdms = new ArrayMap();

        public Builder(String str) {
            if (TextUtils.isEmpty(str)) {
                String str2 = "Invalid to: ";
                String valueOf = String.valueOf(str);
                throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            this.mBundle.putString("google.to", str);
        }

        public Builder addData(String str, String str2) {
            this.zzdms.put(str, str2);
            return this;
        }

        public RemoteMessage build() {
            Bundle bundle = new Bundle();
            for (Entry entry : this.zzdms.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
            bundle.putAll(this.mBundle);
            String token = FirebaseInstanceId.getInstance().getToken();
            if (token != null) {
                this.mBundle.putString("from", token);
            } else {
                this.mBundle.remove("from");
            }
            return new RemoteMessage(bundle);
        }

        public Builder clearData() {
            this.zzdms.clear();
            return this;
        }

        public Builder setCollapseKey(String str) {
            this.mBundle.putString("collapse_key", str);
            return this;
        }

        public Builder setData(Map<String, String> map) {
            this.zzdms.clear();
            this.zzdms.putAll(map);
            return this;
        }

        public Builder setMessageId(String str) {
            this.mBundle.putString("google.message_id", str);
            return this;
        }

        public Builder setMessageType(String str) {
            this.mBundle.putString("message_type", str);
            return this;
        }

        public Builder setTtl(@IntRange(from = 0, to = 86400) int i) {
            this.mBundle.putString("google.ttl", String.valueOf(i));
            return this;
        }
    }

    public static class Notification {
        private final String mTag;
        private final String zzbrz;
        private final String zzejt;
        private final String zznvq;
        private final String[] zznvr;
        private final String zznvs;
        private final String[] zznvt;
        private final String zznvu;
        private final String zznvv;
        private final String zznvw;
        private final String zznvx;
        private final Uri zznvy;

        private Notification(Bundle bundle) {
            this.zzejt = zza.zze(bundle, "gcm.n.title");
            this.zznvq = zza.zzh(bundle, "gcm.n.title");
            this.zznvr = zzk(bundle, "gcm.n.title");
            this.zzbrz = zza.zze(bundle, "gcm.n.body");
            this.zznvs = zza.zzh(bundle, "gcm.n.body");
            this.zznvt = zzk(bundle, "gcm.n.body");
            this.zznvu = zza.zze(bundle, "gcm.n.icon");
            this.zznvv = zza.zzaj(bundle);
            this.mTag = zza.zze(bundle, "gcm.n.tag");
            this.zznvw = zza.zze(bundle, "gcm.n.color");
            this.zznvx = zza.zze(bundle, "gcm.n.click_action");
            this.zznvy = zza.zzai(bundle);
        }

        private static String[] zzk(Bundle bundle, String str) {
            Object[] zzi = zza.zzi(bundle, str);
            if (zzi == null) {
                return null;
            }
            String[] strArr = new String[zzi.length];
            for (int i = 0; i < zzi.length; i++) {
                strArr[i] = String.valueOf(zzi[i]);
            }
            return strArr;
        }

        @Nullable
        public String getBody() {
            return this.zzbrz;
        }

        @Nullable
        public String[] getBodyLocalizationArgs() {
            return this.zznvt;
        }

        @Nullable
        public String getBodyLocalizationKey() {
            return this.zznvs;
        }

        @Nullable
        public String getClickAction() {
            return this.zznvx;
        }

        @Nullable
        public String getColor() {
            return this.zznvw;
        }

        @Nullable
        public String getIcon() {
            return this.zznvu;
        }

        @Nullable
        public Uri getLink() {
            return this.zznvy;
        }

        @Nullable
        public String getSound() {
            return this.zznvv;
        }

        @Nullable
        public String getTag() {
            return this.mTag;
        }

        @Nullable
        public String getTitle() {
            return this.zzejt;
        }

        @Nullable
        public String[] getTitleLocalizationArgs() {
            return this.zznvr;
        }

        @Nullable
        public String getTitleLocalizationKey() {
            return this.zznvq;
        }
    }

    RemoteMessage(Bundle bundle) {
        this.mBundle = bundle;
    }

    @Nullable
    public final String getCollapseKey() {
        return this.mBundle.getString("collapse_key");
    }

    public final Map<String, String> getData() {
        if (this.zzdms == null) {
            this.zzdms = new ArrayMap();
            for (String str : this.mBundle.keySet()) {
                Object obj = this.mBundle.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!(str.startsWith("google.") || str.startsWith("gcm.") || str.equals("from") || str.equals("message_type") || str.equals("collapse_key"))) {
                        this.zzdms.put(str, str2);
                    }
                }
            }
        }
        return this.zzdms;
    }

    @Nullable
    public final String getFrom() {
        return this.mBundle.getString("from");
    }

    @Nullable
    public final String getMessageId() {
        String string = this.mBundle.getString("google.message_id");
        return string == null ? this.mBundle.getString("message_id") : string;
    }

    @Nullable
    public final String getMessageType() {
        return this.mBundle.getString("message_type");
    }

    @Nullable
    public final Notification getNotification() {
        if (this.zznvp == null && zza.zzah(this.mBundle)) {
            this.zznvp = new Notification(this.mBundle);
        }
        return this.zznvp;
    }

    public final long getSentTime() {
        Object obj = this.mBundle.get("google.sent_time");
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (obj instanceof String) {
            try {
                return Long.parseLong((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(obj);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(valueOf).length() + 19).append("Invalid sent time: ").append(valueOf).toString());
            }
        }
        return 0;
    }

    @Nullable
    public final String getTo() {
        return this.mBundle.getString("google.to");
    }

    public final int getTtl() {
        Object obj = this.mBundle.get("google.ttl");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(obj);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(valueOf).length() + 13).append("Invalid TTL: ").append(valueOf).toString());
            }
        }
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.mBundle, false);
        zzbem.zzai(parcel, zze);
    }
}
