package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableChar extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableChar> CREATOR = new C00421();
    static final long serialVersionUID = 1;
    private char mValue;

    static class C00421 implements Creator<ObservableChar> {
        C00421() {
        }

        public ObservableChar createFromParcel(Parcel source) {
            return new ObservableChar((char) source.readInt());
        }

        public ObservableChar[] newArray(int size) {
            return new ObservableChar[size];
        }
    }

    public ObservableChar(char value) {
        this.mValue = value;
    }

    public char get() {
        return this.mValue;
    }

    public void set(char value) {
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
