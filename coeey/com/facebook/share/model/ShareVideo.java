package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareVideo implements ShareModel {
    public static final Creator<ShareVideo> CREATOR = new C14041();
    private final Uri localUrl;

    static class C14041 implements Creator<ShareVideo> {
        C14041() {
        }

        public ShareVideo createFromParcel(Parcel in) {
            return new ShareVideo(in);
        }

        public ShareVideo[] newArray(int size) {
            return new ShareVideo[size];
        }
    }

    public static final class Builder implements ShareModelBuilder<ShareVideo, Builder> {
        private Uri localUrl;

        public Builder setLocalUrl(@Nullable Uri localUrl) {
            this.localUrl = localUrl;
            return this;
        }

        public ShareVideo build() {
            return new ShareVideo();
        }

        public Builder readFrom(ShareVideo model) {
            return model == null ? this : setLocalUrl(model.getLocalUrl());
        }

        public Builder readFrom(Parcel parcel) {
            return readFrom((ShareVideo) parcel.readParcelable(ShareVideo.class.getClassLoader()));
        }
    }

    private ShareVideo(Builder builder) {
        this.localUrl = builder.localUrl;
    }

    ShareVideo(Parcel in) {
        this.localUrl = (Uri) in.readParcelable(Uri.class.getClassLoader());
    }

    @Nullable
    public Uri getLocalUrl() {
        return this.localUrl;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.localUrl, 0);
    }
}
