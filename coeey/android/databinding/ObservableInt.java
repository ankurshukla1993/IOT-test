package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableInt extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableInt> CREATOR = new C00451();
    static final long serialVersionUID = 1;
    private int mValue;

    static class C00451 implements Creator<ObservableInt> {
        C00451() {
        }

        public ObservableInt createFromParcel(Parcel source) {
            return new ObservableInt(source.readInt());
        }

        public ObservableInt[] newArray(int size) {
            return new ObservableInt[size];
        }
    }

    public ObservableInt(int value) {
        this.mValue = value;
    }

    public int get() {
        return this.mValue;
    }

    public void set(int value) {
        if (value != this.mValue) {
            this.mValue = value;
            notifyChange();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mValue);
    }
}
