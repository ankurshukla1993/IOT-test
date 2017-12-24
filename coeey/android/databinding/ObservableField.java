package android.databinding;

import java.io.Serializable;

public class ObservableField<T> extends BaseObservable implements Serializable {
    static final long serialVersionUID = 1;
    private T mValue;

    public ObservableField(T value) {
        this.mValue = value;
    }

    public T get() {
        return this.mValue;
    }

    public void set(T value) {
        if (value != this.mValue) {
            this.mValue = value;
            notifyChange();
        }
    }
}
