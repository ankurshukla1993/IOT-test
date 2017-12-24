package io.reactivex.schedulers;

import io.reactivex.annotations.NonNull;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.TimeUnit;

public final class Timed<T> {
    final long time;
    final TimeUnit unit;
    final T value;

    public Timed(@NonNull T value, long time, @NonNull TimeUnit unit) {
        this.value = value;
        this.time = time;
        this.unit = (TimeUnit) ObjectHelper.requireNonNull(unit, "unit is null");
    }

    @NonNull
    public T value() {
        return this.value;
    }

    @NonNull
    public TimeUnit unit() {
        return this.unit;
    }

    public long time() {
        return this.time;
    }

    public long time(@NonNull TimeUnit unit) {
        return unit.convert(this.time, this.unit);
    }

    public boolean equals(Object other) {
        if (!(other instanceof Timed)) {
            return false;
        }
        Timed<?> o = (Timed) other;
        if (ObjectHelper.equals(this.value, o.value) && this.time == o.time && ObjectHelper.equals(this.unit, o.unit)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.value != null ? this.value.hashCode() : 0) * 31) + ((int) ((this.time >>> 31) ^ this.time))) * 31) + this.unit.hashCode();
    }

    public String toString() {
        return "Timed[time=" + this.time + ", unit=" + this.unit + ", value=" + this.value + "]";
    }
}
