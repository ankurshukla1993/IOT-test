package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.flexbox.FlexItem;

public class ProductUserInfo implements Parcelable {
    public static final Creator CREATOR = new C2246f();
    private int f2407a = 1;
    private String f2408b = "男";
    private byte f2409c = (byte) 1;
    private float f2410d = FlexItem.FLEX_SHRINK_DEFAULT;
    private int f2411e = 1;
    private String f2412f = "kg";
    private float f2413g = FlexItem.FLEX_SHRINK_DEFAULT;
    private float f2414h = FlexItem.FLEX_SHRINK_DEFAULT;

    public ProductUserInfo(Parcel parcel) {
        this.f2407a = parcel.readInt();
        this.f2408b = parcel.readString();
        this.f2409c = parcel.readByte();
        this.f2410d = parcel.readFloat();
        this.f2411e = parcel.readInt();
        this.f2412f = parcel.readString();
        this.f2413g = parcel.readFloat();
        this.f2414h = parcel.readFloat();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ProductUserInfo [productUserNumber=" + this.f2407a + ", sex=" + this.f2408b + ", age=" + this.f2409c + ", height=" + this.f2410d + ", athleteActivityLevel=" + this.f2411e + ", unit=" + this.f2412f + ", goalWeight=" + this.f2413g + ", waistline=" + this.f2414h + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2407a);
        parcel.writeString(this.f2408b);
        parcel.writeByte(this.f2409c);
        parcel.writeFloat(this.f2410d);
        parcel.writeInt(this.f2411e);
        parcel.writeString(this.f2412f);
        parcel.writeFloat(this.f2413g);
        parcel.writeFloat(this.f2414h);
    }
}
