package humanize.text;

import com.google.common.base.Preconditions;
import humanize.Humanize;
import humanize.spi.Expose;
import humanize.spi.FormatProvider;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HumanizeFormatProvider implements FormatProvider {
    private static final Map<String, Method> humanizeMethods = getStaticMethods(Humanize.class);

    static class C23701 implements FormatFactory {
        C23701() {
        }

        public Format getFormat(String name, String args, Locale locale) {
            String camelized = Humanize.camelize(args);
            if (HumanizeFormatProvider.humanizeMethods.containsKey(camelized)) {
                return new HumanizeFormat((Method) HumanizeFormatProvider.humanizeMethods.get(camelized), locale);
            }
            return null;
        }
    }

    public static class HumanizeFormat extends Format {
        private static final long serialVersionUID = -3261072590121741805L;
        private final Locale locale;
        private final Method method;

        public HumanizeFormat(Method method, Locale locale) {
            this.method = method;
            this.locale = locale;
        }

        public StringBuffer format(Object paramObject, StringBuffer toAppendTo, FieldPosition position) {
            Object retval;
            Preconditions.checkNotNull(this.method);
            boolean withLocale = false;
            for (Class<?> type : this.method.getParameterTypes()) {
                if (Locale.class.equals(type)) {
                    withLocale = true;
                    break;
                }
            }
            if (withLocale) {
                try {
                    retval = this.method.invoke(null, new Object[]{paramObject, this.locale});
                } catch (Exception e) {
                    retval = String.format("[invalid call: '%s']", new Object[]{e.getMessage()});
                }
            } else {
                retval = this.method.invoke(null, new Object[]{paramObject});
            }
            return toAppendTo.append(retval);
        }

        public Object parseObject(String paramString, ParsePosition paramParsePosition) {
            throw new UnsupportedOperationException();
        }
    }

    public static FormatFactory factory() {
        return new C23701();
    }

    private static Map<String, Method> getStaticMethods(Class<?> clazz) {
        Map<String, Method> methods = new HashMap();
        for (Method method : clazz.getMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && method.getAnnotation(Expose.class) != null) {
                methods.put(method.getName(), method);
            }
        }
        return Collections.unmodifiableMap(methods);
    }

    public FormatFactory getFactory() {
        return factory();
    }

    public String getFormatName() {
        return "humanize";
    }
}
