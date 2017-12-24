package humanize.text;

import humanize.spi.FormatProvider;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;

public class MaskFormat extends Format implements FormatProvider {
    private static final char DEFAULT_PLACEHOLDER = '_';
    private static final long serialVersionUID = -2072270263539296713L;
    private String mask;
    private char placeholder;

    static class C23711 implements FormatFactory {
        C23711() {
        }

        public Format getFormat(String name, String args, Locale locale) {
            return new MaskFormat(args);
        }
    }

    public static FormatFactory factory() {
        return new C23711();
    }

    public static String format(String mask, String str) {
        return format(mask, str, (char) DEFAULT_PLACEHOLDER);
    }

    public static String format(String mask, String str, char placeholder) {
        return new MaskFormat(mask, placeholder).format(str);
    }

    public static String parse(String mask, String source) throws ParseException {
        return parse(mask, source, DEFAULT_PLACEHOLDER);
    }

    public static String parse(String mask, String source, char placeholder) throws ParseException {
        return new MaskFormat(mask, placeholder).parse(source);
    }

    public MaskFormat(String mask) {
        this(mask, DEFAULT_PLACEHOLDER);
    }

    public MaskFormat(String mask, char placeholder) {
        this.mask = mask;
        this.placeholder = placeholder;
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (obj == null) {
            return null;
        }
        return toAppendTo.append(format(obj.toString()));
    }

    public String format(String str) {
        if (isEmptyInput(this.mask, str)) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        int msgIndex = 0;
        int i = 0;
        while (i < this.mask.length()) {
            char maskChar = this.mask.charAt(i);
            boolean isPlaceHolder = isPlaceholder(maskChar);
            if (isPlaceHolder || isDeleteholder(maskChar)) {
                if (isPlaceHolder) {
                    result.append(str.charAt(msgIndex));
                }
                msgIndex++;
                if (msgIndex == str.length()) {
                    break;
                }
            } else {
                if (isEscapeChar(maskChar)) {
                    i++;
                    maskChar = this.mask.charAt(i);
                }
                result.append(maskChar);
            }
            i++;
        }
        while (true) {
            i++;
            if (i >= this.mask.length()) {
                return result.toString();
            }
            result.append(this.mask.charAt(i));
        }
    }

    public FormatFactory getFactory() {
        return factory();
    }

    public String getFormatName() {
        return "mask";
    }

    public String getMask() {
        return this.mask;
    }

    public char getPlaceholder() {
        return this.placeholder;
    }

    public String parse(String source) throws ParseException {
        return parse(source, (ParsePosition) null);
    }

    public String parse(String source, ParsePosition pos) throws ParseException {
        if (isEmptyInput(this.mask, source)) {
            return source;
        }
        StringBuilder sb = new StringBuilder(this.mask.length());
        int i = 0;
        while (i < this.mask.length() && i < source.length()) {
            char maskChar = this.mask.charAt(i);
            if (isPlaceholder(maskChar)) {
                sb.append(source.charAt(i));
            } else if (!(isEscapeChar(maskChar) || maskChar == source.charAt(i))) {
                throw new ParseException(String.format("Error parsing String: '%s' at %d", new Object[]{source, Integer.valueOf(i)}), i);
            }
            i++;
        }
        return sb.toString();
    }

    public Object parseObject(String source) throws ParseException {
        ParsePosition pos = new ParsePosition(0);
        Object result = parseObject(source, pos);
        if (pos.getErrorIndex() < 0) {
            return result;
        }
        throw new ParseException("Format.parseObject(String) failed", pos.getErrorIndex());
    }

    public Object parseObject(String source, ParsePosition pos) {
        try {
            return parse(source, pos);
        } catch (ParseException e) {
            pos.setIndex(0);
            pos.setErrorIndex(e.getErrorOffset());
            return null;
        }
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public void setPlaceholder(char placeholder) {
        this.placeholder = placeholder;
    }

    private boolean isDeleteholder(char c) {
        return c == '#';
    }

    private boolean isEmptyInput(String mask, String str) {
        return mask == null || mask.length() == 0 || str == null || str.length() == 0;
    }

    private boolean isEscapeChar(char c) {
        return c == '\\';
    }

    private boolean isPlaceholder(char c) {
        return c == this.placeholder;
    }
}
