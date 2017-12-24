package com.facebook.share.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class SharePhoto implements ShareModel {
    public static final Creator<SharePhoto> CREATOR = new C14021();
    private final Bitmap bitmap;
    private final String caption;
    private final Uri imageUrl;
    private final boolean userGenerated;

    static class C14021 implements Creator<SharePhoto> {
        C14021() {
        }

        public SharePhoto createFromParcel(Parcel in) {
            return new SharePhoto(in);
        }

        public SharePhoto[] newArray(int size) {
            return new SharePhoto[size];
        }
    }

    public static final class Builder implements ShareModelBuilder<SharePhoto, Builder> {
        private Bitmap bitmap;
        private String caption;
        private Uri imageUrl;
        private boolean userGenerated;

        public Builder setBitmap(@Nullable Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Builder setImageUrl(@Nullable Uri imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setUserGenerated(boolean userGenerated) {
            this.userGenerated = userGenerated;
            return this;
        }

        public Builder setCaption(@Nullable String caption) {
            this.caption = caption;
            return this;
        }

        Uri getImageUrl() {
            return this.imageUrl;
        }

        Bitmap getBitmap() {
            return this.bitmap;
        }

        public SharePhoto build() {
            return new SharePhoto();
        }

        public Builder readFrom(SharePhoto model) {
            return model == null ? this : setBitmap(model.getBitmap()).setImageUrl(model.getImageUrl()).setUserGenerated(model.getUserGenerated()).setCaption(model.getCaption());
        }

        public Builder readFrom(Parcel parcel) {
            return readFrom((SharePhoto) parcel.readParcelable(SharePhoto.class.getClassLoader()));
        }

        public static void writeListTo(Parcel out, List<SharePhoto> photos) {
            List<SharePhoto> list = new ArrayList();
            for (SharePhoto photo : photos) {
                list.add(photo);
            }
            out.writeTypedList(list);
        }

        public static List<SharePhoto> readListFrom(Parcel in) {
            List<SharePhoto> list = new ArrayList();
            in.readTypedList(list, SharePhoto.CREATOR);
            return list;
        }
    }

    private SharePhoto(Builder builder) {
        this.bitmap = builder.bitmap;
        this.imageUrl = builder.imageUrl;
        this.userGenerated = builder.userGenerated;
        this.caption = builder.caption;
    }

    SharePhoto(Parcel in) {
        this.bitmap = (Bitmap) in.readParcelable(Bitmap.class.getClassLoader());
        this.imageUrl = (Uri) in.readParcelable(Uri.class.getClassLoader());
        this.userGenerated = in.readByte() != (byte) 0;
        this.caption = in.readString();
    }

    @Nullable
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    @Nullable
    public Uri getImageUrl() {
        return this.imageUrl;
    }

    public boolean getUserGenerated() {
        return this.userGenerated;
    }

    public String getCaption() {
        return this.caption;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        int i = 0;
        out.writeParcelable(this.bitmap, 0);
        out.writeParcelable(this.imageUrl, 0);
        if (this.userGenerated) {
            i = 1;
        }
        out.writeByte((byte) i);
        out.writeString(this.caption);
    }
}
