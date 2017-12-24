package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableFloat extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableFloat> CREATOR = new C00441();
    static final long serialVersionUID = 1;
    private float mValue;

    static class C00441 implements Creator<ObservableFloat> {
        C00441() {
        }

        public ObservableFloat createFromParcel(Parcel source) {
            return new ObservableFloat(source.readFloat());
        }

        public ObservableFloat[] newArray(int size) {
            return new ObservableFloat[size];
        }
    }

    public ObservableFloat(float value) {
        this.mValue = value;
    }

    public float get() {
        return this.mValue;
    }

    public void set(float value) {
        if (value != this.mValue) {
            this.mValue = value;
            notifyChange();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.mValue);
    }
}
