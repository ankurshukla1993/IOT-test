package com.facebook.react.module.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReactModule {
    boolean canOverrideExistingModule() default false;

    String name();

    boolean needsEagerInit() default false;

    boolean supportsWebWorkers() default false;
}
