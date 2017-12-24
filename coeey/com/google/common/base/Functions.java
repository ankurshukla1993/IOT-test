package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible
public final class Functions {

    private static class ConstantFunction<E> implements Function<Object, E>, Serializable {
        private static final long serialVersionUID = 0;
        private final E value;

        public ConstantFunction(@Nullable E value) {
            this.value = value;
        }

        public E apply(@Nullable Object from) {
            return this.value;
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof ConstantFunction)) {
                return false;
            }
            return Objects.equal(this.value, ((ConstantFunction) obj).value);
        }

        public int hashCode() {
            return this.value == null ? 0 : this.value.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.value));
            return new StringBuilder(valueOf.length() + 10).append("constant(").append(valueOf).append(")").toString();
        }
    }

    private static class ForMapWithDefault<K, V> implements Function<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final V defaultValue;
        final Map<K, ? extends V> map;

        ForMapWithDefault(Map<K, ? extends V> map, @Nullable V defaultValue) {
            this.map = (Map) Preconditions.checkNotNull(map);
            this.defaultValue = defaultValue;
        }

        public V apply(@Nullable K key) {
            V result = this.map.get(key);
            return (result != null || this.map.containsKey(key)) ? result : this.defaultValue;
        }

        public boolean equals(@Nullable Object o) {
            if (!(o instanceof ForMapWithDefault)) {
                return false;
            }
            ForMapWithDefault<?, ?> that = (ForMapWithDefault) o;
            if (this.map.equals(that.map) && Objects.equal(this.defaultValue, that.defaultValue)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{this.map, this.defaultValue});
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.map));
            String valueOf2 = String.valueOf(String.valueOf(this.defaultValue));
            return new StringBuilder((valueOf.length() + 23) + valueOf2.length()).append("forMap(").append(valueOf).append(", defaultValue=").append(valueOf2).append(")").toString();
        }
    }

    private static class FunctionComposition<A, B, C> implements Function<A, C>, Serializable {
        private static final long serialVersionUID = 0;
        private final Function<A, ? extends B> f192f;
        private final Function<B, C> f193g;

        public FunctionComposition(Function<B, C> g, Function<A, ? extends B> f) {
            this.f193g = (Function) Preconditions.checkNotNull(g);
            this.f192f = (Function) Preconditions.checkNotNull(f);
        }

        public C apply(@Nullable A a) {
            return this.f193g.apply(this.f192f.apply(a));
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof FunctionComposition)) {
                return false;
            }
            FunctionComposition<?, ?, ?> that = (FunctionComposition) obj;
            if (this.f192f.equals(that.f192f) && this.f193g.equals(that.f193g)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.f192f.hashCode() ^ this.f193g.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.f193g));
            String valueOf2 = String.valueOf(String.valueOf(this.f192f));
            return new StringBuilder((valueOf.length() + 2) + valueOf2.length()).append(valueOf).append("(").append(valueOf2).append(")").toString();
        }
    }

    private static class FunctionForMapNoDefault<K, V> implements Function<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final Map<K, V> map;

        FunctionForMapNoDefault(Map<K, V> map) {
            this.map = (Map) Preconditions.checkNotNull(map);
        }

        public V apply(@Nullable K key) {
            boolean z;
            V result = this.map.get(key);
            if (result != null || this.map.containsKey(key)) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "Key '%s' not present in map", key);
            return result;
        }

        public boolean equals(@Nullable Object o) {
            if (!(o instanceof FunctionForMapNoDefault)) {
                return false;
            }
            return this.map.equals(((FunctionForMapNoDefault) o).map);
        }

        public int hashCode() {
            return this.map.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.map));
            return new StringBuilder(valueOf.length() + 8).append("forMap(").append(valueOf).append(")").toString();
        }
    }

    private static class PredicateFunction<T> implements Function<T, Boolean>, Serializable {
        private static final long serialVersionUID = 0;
        private final Predicate<T> predicate;

        private PredicateFunction(Predicate<T> predicate) {
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
        }

        public Boolean apply(@Nullable T t) {
            return Boolean.valueOf(this.predicate.apply(t));
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof PredicateFunction)) {
                return false;
            }
            return this.predicate.equals(((PredicateFunction) obj).predicate);
        }

        public int hashCode() {
            return this.predicate.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.predicate));
            return new StringBuilder(valueOf.length() + 14).append("forPredicate(").append(valueOf).append(")").toString();
        }
    }

    private static class SupplierFunction<T> implements Function<Object, T>, Serializable {
        private static final long serialVersionUID = 0;
        private final Supplier<T> supplier;

        private SupplierFunction(Supplier<T> supplier) {
            this.supplier = (Supplier) Preconditions.checkNotNull(supplier);
        }

        public T apply(@Nullable Object input) {
            return this.supplier.get();
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof SupplierFunction)) {
                return false;
            }
            return this.supplier.equals(((SupplierFunction) obj).supplier);
        }

        public int hashCode() {
            return this.supplier.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.supplier));
            return new StringBuilder(valueOf.length() + 13).append("forSupplier(").append(valueOf).append(")").toString();
        }
    }

    private enum ToStringFunction implements Function<Object, String> {
        INSTANCE;

        public String apply(Object o) {
            Preconditions.checkNotNull(o);
            return o.toString();
        }

        public String toString() {
            return "toString";
        }
    }

    private Functions() {
    }

    public static Function<Object, String> toStringFunction() {
        return ToStringFunction.INSTANCE;
    }

    public static <E> Function<E, E> identity() {
        return IdentityFunction.INSTANCE;
    }

    public static <K, V> Function<K, V> forMap(Map<K, V> map) {
        return new FunctionForMapNoDefault(map);
    }

    public static <K, V> Function<K, V> forMap(Map<K, ? extends V> map, @Nullable V defaultValue) {
        return new ForMapWithDefault(map, defaultValue);
    }

    public static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, ? extends B> f) {
        return new FunctionComposition(g, f);
    }

    public static <T> Function<T, Boolean> forPredicate(Predicate<T> predicate) {
        return new PredicateFunction(predicate);
    }

    public static <E> Function<Object, E> constant(@Nullable E value) {
        return new ConstantFunction(value);
    }

    @Beta
    public static <T> Function<Object, T> forSupplier(Supplier<T> supplier) {
        return new SupplierFunction(supplier);
    }
}
