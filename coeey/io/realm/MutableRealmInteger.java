package io.realm;

import io.realm.internal.ManagableObject;
import javax.annotation.Nullable;

public abstract class MutableRealmInteger implements Comparable<MutableRealmInteger>, ManagableObject {

    private static final class Unmanaged extends MutableRealmInteger {
        @Nullable
        private Long value;

        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return super.compareTo((MutableRealmInteger) obj);
        }

        Unmanaged(@Nullable Long value) {
            this.value = value;
        }

        public boolean isManaged() {
            return false;
        }

        public boolean isValid() {
            return true;
        }

        public void set(@Nullable Long newValue) {
            this.value = newValue;
        }

        @Nullable
        public Long get() {
            return this.value;
        }

        public void increment(long inc) {
            if (this.value == null) {
                throw new IllegalStateException("Cannot increment a MutableRealmInteger whose value is null. Set its value first.");
            }
            this.value = Long.valueOf(this.value.longValue() + inc);
        }

        public void decrement(long dec) {
            increment(-dec);
        }
    }

    public abstract void decrement(long j);

    @Nullable
    public abstract Long get();

    public abstract void increment(long j);

    public abstract void set(@Nullable Long l);

    public static MutableRealmInteger valueOf(Long value) {
        return new Unmanaged(value);
    }

    public static MutableRealmInteger ofNull() {
        return new Unmanaged(null);
    }

    public static MutableRealmInteger valueOf(long value) {
        return valueOf(Long.valueOf(value));
    }

    public static MutableRealmInteger valueOf(String value) {
        return valueOf(Long.parseLong(value));
    }

    MutableRealmInteger() {
    }

    public final void set(long newValue) {
        set(Long.valueOf(newValue));
    }

    public final boolean isNull() {
        return get() == null;
    }

    public final int compareTo(MutableRealmInteger o) {
        Long thisValue = get();
        Long otherValue = o.get();
        return thisValue == null ? otherValue == null ? 0 : -1 : otherValue == null ? 1 : thisValue.compareTo(otherValue);
    }

    public final int hashCode() {
        Long thisValue = get();
        return thisValue == null ? 0 : thisValue.hashCode();
    }

    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MutableRealmInteger)) {
            return false;
        }
        Long thisValue = get();
        Long otherValue = ((MutableRealmInteger) o).get();
        if (thisValue != null) {
            return thisValue.equals(otherValue);
        }
        if (otherValue != null) {
            return false;
        }
        return true;
    }
}
