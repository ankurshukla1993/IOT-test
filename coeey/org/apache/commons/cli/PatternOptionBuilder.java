package org.apache.commons.cli;

import kotlin.text.Typography;

public class PatternOptionBuilder {
    public static final Class CLASS_VALUE;
    public static final Class DATE_VALUE;
    public static final Class EXISTING_FILE_VALUE;
    public static final Class FILES_VALUE;
    public static final Class FILE_VALUE;
    public static final Class NUMBER_VALUE;
    public static final Class OBJECT_VALUE;
    public static final Class STRING_VALUE;
    public static final Class URL_VALUE;
    static Class array$Ljava$io$File;
    static Class class$java$io$File;
    static Class class$java$io$FileInputStream;
    static Class class$java$lang$Class;
    static Class class$java$lang$Number;
    static Class class$java$lang$Object;
    static Class class$java$lang$String;
    static Class class$java$net$URL;
    static Class class$java$util$Date;

    static {
        Class class$;
        if (class$java$lang$String == null) {
            class$ = class$("java.lang.String");
            class$java$lang$String = class$;
        } else {
            class$ = class$java$lang$String;
        }
        STRING_VALUE = class$;
        if (class$java$lang$Object == null) {
            class$ = class$("java.lang.Object");
            class$java$lang$Object = class$;
        } else {
            class$ = class$java$lang$Object;
        }
        OBJECT_VALUE = class$;
        if (class$java$lang$Number == null) {
            class$ = class$("java.lang.Number");
            class$java$lang$Number = class$;
        } else {
            class$ = class$java$lang$Number;
        }
        NUMBER_VALUE = class$;
        if (class$java$util$Date == null) {
            class$ = class$("java.util.Date");
            class$java$util$Date = class$;
        } else {
            class$ = class$java$util$Date;
        }
        DATE_VALUE = class$;
        if (class$java$lang$Class == null) {
            class$ = class$("java.lang.Class");
            class$java$lang$Class = class$;
        } else {
            class$ = class$java$lang$Class;
        }
        CLASS_VALUE = class$;
        if (class$java$io$FileInputStream == null) {
            class$ = class$("java.io.FileInputStream");
            class$java$io$FileInputStream = class$;
        } else {
            class$ = class$java$io$FileInputStream;
        }
        EXISTING_FILE_VALUE = class$;
        if (class$java$io$File == null) {
            class$ = class$("java.io.File");
            class$java$io$File = class$;
        } else {
            class$ = class$java$io$File;
        }
        FILE_VALUE = class$;
        if (array$Ljava$io$File == null) {
            class$ = class$("[Ljava.io.File;");
            array$Ljava$io$File = class$;
        } else {
            class$ = array$Ljava$io$File;
        }
        FILES_VALUE = class$;
        if (class$java$net$URL == null) {
            class$ = class$("java.net.URL");
            class$java$net$URL = class$;
        } else {
            class$ = class$java$net$URL;
        }
        URL_VALUE = class$;
    }

    static Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError().initCause(x1);
        }
    }

    public static Object getValueClass(char ch) {
        switch (ch) {
            case '#':
                return DATE_VALUE;
            case '%':
                return NUMBER_VALUE;
            case '*':
                return FILES_VALUE;
            case '+':
                return CLASS_VALUE;
            case '/':
                return URL_VALUE;
            case ':':
                return STRING_VALUE;
            case '<':
                return EXISTING_FILE_VALUE;
            case '>':
                return FILE_VALUE;
            case '@':
                return OBJECT_VALUE;
            default:
                return null;
        }
    }

    public static boolean isValueCode(char ch) {
        return ch == '@' || ch == ':' || ch == '%' || ch == '+' || ch == '#' || ch == Typography.less || ch == Typography.greater || ch == '*' || ch == '/' || ch == '!';
    }

    public static Options parsePattern(String pattern) {
        boolean z = true;
        char opt = ' ';
        boolean required = false;
        Object type = null;
        Options options = new Options();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (!isValueCode(ch)) {
                if (opt != ' ') {
                    boolean z2;
                    if (type != null) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    OptionBuilder.hasArg(z2);
                    OptionBuilder.isRequired(required);
                    OptionBuilder.withType(type);
                    options.addOption(OptionBuilder.create(opt));
                    required = false;
                    type = null;
                }
                opt = ch;
            } else if (ch == '!') {
                required = true;
            } else {
                type = getValueClass(ch);
            }
        }
        if (opt != ' ') {
            if (type == null) {
                z = false;
            }
            OptionBuilder.hasArg(z);
            OptionBuilder.isRequired(required);
            OptionBuilder.withType(type);
            options.addOption(OptionBuilder.create(opt));
        }
        return options;
    }
}
