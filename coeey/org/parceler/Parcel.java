package org.parceler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.parceler.ParcelConverter.EmptyConverter;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Parcel {

    public enum Serialization {
        FIELD,
        BEAN,
        VALUE
    }

    Class[] analyze() default {};

    Class<? extends TypeRangeParcelConverter> converter() default EmptyConverter.class;

    Class[] implementations() default {};

    Serialization value() default Serialization.FIELD;
}
