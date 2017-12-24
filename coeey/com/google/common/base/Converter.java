package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import javax.annotation.Nullable;

@GwtCompatible
@Beta
public abstract class Converter<A, B> implements Function<A, B> {
    private final boolean handleNullAutomatically;
    private transient Converter<B, A> reverse;

    private static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> first;
        final Converter<B, C> second;

        ConverterComposition(Converter<A, B> first, Converter<B, C> second) {
            this.first = first;
            this.second = second;
        }

        protected C doForward(A a) {
            throw new AssertionError();
        }

        protected A doBackward(C c) {
            throw new AssertionError();
        }

        @Nullable
        C correctedDoForward(@Nullable A a) {
            return this.second.correctedDoForward(this.first.correctedDoForward(a));
        }

        @Nullable
        A correctedDoBackward(@Nullable C c) {
            return this.first.correctedDoBackward(this.second.correctedDoBackward(c));
        }

        public boolean equals(@Nullable Object object) {
            if (!(object instanceof ConverterComposition)) {
                return false;
            }
            ConverterComposition<?, ?, ?> that = (ConverterComposition) object;
            if (this.first.equals(that.first) && this.second.equals(that.second)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.first.hashCode() * 31) + this.second.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.first));
            String valueOf2 = String.valueOf(String.valueOf(this.second));
            return new StringBuilder((valueOf.length() + 10) + valueOf2.length()).append(valueOf).append(".andThen(").append(valueOf2).append(")").toString();
        }
    }

    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> original;

        ReverseConverter(Converter<A, B> original) {
            this.original = original;
        }

        protected A doForward(B b) {
            throw new AssertionError();
        }

        protected B doBackward(A a) {
            throw new AssertionError();
        }

        @Nullable
        A correctedDoForward(@Nullable B b) {
            return this.original.correctedDoBackward(b);
        }

        @Nullable
        B correctedDoBackward(@Nullable A a) {
            return this.original.correctedDoForward(a);
        }

        public Converter<A, B> reverse() {
            return this.original;
        }

        public boolean equals(@Nullable Object object) {
            if (!(object instanceof ReverseConverter)) {
                return false;
            }
            return this.original.equals(((ReverseConverter) object).original);
        }

        public int hashCode() {
            return this.original.hashCode() ^ -1;
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.original));
            return new StringBuilder(valueOf.length() + 10).append(valueOf).append(".reverse()").toString();
        }
    }

    protected abstract A doBackward(B b);

    protected abstract B doForward(A a);

    protected Converter() {
        this(true);
    }

    Converter(boolean handleNullAutomatically) {
        this.handleNullAutomatically = handleNullAutomatically;
    }

    @Nullable
    public final B convert(@Nullable A a) {
        return correctedDoForward(a);
    }

    @Nullable
    B correctedDoForward(@Nullable A a) {
        if (this.handleNullAutomatically) {
            return a == null ? null : Preconditions.checkNotNull(doForward(a));
        } else {
            return doForward(a);
        }
    }

    @Nullable
    A correctedDoBackward(@Nullable B b) {
        if (this.handleNullAutomatically) {
            return b == null ? null : Preconditions.checkNotNull(doBackward(b));
        } else {
            return doBackward(b);
        }
    }

    public Iterable<B> convertAll(Iterable<? extends A> fromIterable) {
        Preconditions.checkNotNull(fromIterable, "fromIterable");
        return new 1(this, fromIterable);
    }

    public Converter<B, A> reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter != null) {
            return converter;
        }
        converter = new ReverseConverter(this);
        this.reverse = converter;
        return converter;
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> secondConverter) {
        return doAndThen(secondConverter);
    }

    <C> Converter<A, C> doAndThen(Converter<B, C> secondConverter) {
        return new ConverterComposition(this, (Converter) Preconditions.checkNotNull(secondConverter));
    }

    @Deprecated
    @Nullable
    public final B apply(@Nullable A a) {
        return convert(a);
    }

    public boolean equals(@Nullable Object object) {
        return super.equals(object);
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> forwardFunction, Function<? super B, ? extends A> backwardFunction) {
        return new FunctionBasedConverter(forwardFunction, backwardFunction, null);
    }

    public static <T> Converter<T, T> identity() {
        return IdentityConverter.INSTANCE;
    }
}
