package android.arch.persistence.room;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface ColumnInfo {
    public static final int BLOB = 5;
    public static final String INHERIT_FIELD_NAME = "[field-name]";
    public static final int INTEGER = 3;
    public static final int REAL = 4;
    public static final int TEXT = 2;
    public static final int UNDEFINED = 1;

    public @interface SQLiteTypeAffinity {
    }

    boolean index() default false;

    String name() default "[field-name]";

    @SQLiteTypeAffinity
    int typeAffinity() default 1;
}
