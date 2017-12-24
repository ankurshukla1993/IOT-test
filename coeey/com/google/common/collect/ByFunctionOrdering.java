package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
final class ByFunctionOrdering<F, T> extends Ordering<F> implements Serializable {
    private static final long serialVersionUID = 0;
    final Function<F, ? extends T> function;
    final Ordering<T> ordering;

    ByFunctionOrdering(Function<F, ? extends T> function, Ordering<T> ordering) {
        this.function = (Function) Preconditions.checkNotNull(function);
        this.ordering = (Ordering) Preconditions.checkNotNull(ordering);
    }

    public int compare(F left, F right) {
        return this.ordering.compare(this.function.apply(left), this.function.apply(right));
    }

    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ByFunctionOrdering)) {
            return false;
        }
        ByFunctionOrdering<?, ?> that = (ByFunctionOrdering) object;
        if (this.function.equals(that.function) && this.ordering.equals(that.ordering)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.function, this.ordering});
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.ordering));
        String valueOf2 = String.valueOf(String.valueOf(this.function));
        return new StringBuilder((valueOf.length() + 13) + valueOf2.length()).append(valueOf).append(".onResultOf(").append(valueOf2).append(")").toString();
    }
}
