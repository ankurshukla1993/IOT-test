package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableParcelable<T extends Parcelable> extends ObservableField<T> implements Parcelable, Serializable {
    public static final Creator<ObservableParcelable> CREATOR = new C00471();
    static final long serialVersionUID = 1;

    static class C00471 implements Creator<ObservableParcelable> {
        C00471() {
        }

        public ObservableParcelable createFromParcel(Parcel source) {
            return new ObservableParcelable(source.readParcelable(getClass().getClassLoader()));
        }

        public ObservableParcelable[] newArray(int size) {
            return new ObservableParcelable[size];
        }
    }

    public ObservableParcelable(T value) {
        super(value);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) get(), 0);
    }
}
