package humanize.text;

import com.google.common.base.Preconditions;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class ExtendedMessageFormat extends MessageFormat {
    private static final String DUMMY_PATTERN = "";
    private static final char END_FE = '}';
    private static final String ESCAPED_QUOTE = "''";
    private static final char QUOTE = '\'';
    private static final char[] SPLIT_CHARS = " \t\n\r\f".toCharArray();
    private static final char START_FE = '{';
    private static final char START_FMT = ',';
    private static final long serialVersionUID = -2362048321261811743L;
    private final Map<String, ? extends FormatFactory> registry;
    private String toPattern;

    static {
        Arrays.sort(SPLIT_CHARS);
    }

    public ExtendedMessageFormat(String pattern) {
        this(pattern, Locale.getDefault());
    }

    public ExtendedMessageFormat(String pattern, Locale locale) {
        this(pattern, locale, null);
    }

    public ExtendedMessageFormat(String pattern, Locale locale, Map<String, ? extends FormatFactory> registry) {
        super("");
        setLocale(locale);
        this.registry = registry;
        applyPattern(pattern);
    }

    public ExtendedMessageFormat(String pattern, Map<String, ? extends FormatFactory> registry) {
        this(pattern, Locale.getDefault(), registry);
    }

    public final void applyPattern(String pattern) {
        if (hasRegistry()) {
            super.applyPattern(pattern);
            this.toPattern = super.toPattern();
            return;
        }
        ArrayList<Format> foundFormats = new ArrayList();
        ArrayList<String> foundDescriptions = new ArrayList();
        StringBuilder stripCustom = new StringBuilder(pattern.length());
        ParsePosition parsePosition = new ParsePosition(0);
        char[] c = pattern.toCharArray();
        int fmtCount = 0;
        while (parsePosition.getIndex() < pattern.length()) {
            char charType = c[parsePosition.getIndex()];
            if ('\'' == charType) {
                appendQuotedString(pattern, parsePosition, stripCustom, true);
            } else {
                if ('{' == charType) {
                    fmtCount++;
                    seekNonWs(pattern, parsePosition);
                    int start = parsePosition.getIndex();
                    stripCustom.append(START_FE).append(readArgumentIndex(pattern, next(parsePosition)));
                    seekNonWs(pattern, parsePosition);
                    Format format = null;
                    String formatDescription = null;
                    if (c[parsePosition.getIndex()] == ',') {
                        formatDescription = parseFormatDescription(pattern, next(parsePosition));
                        format = getFormat(formatDescription);
                        if (format == null) {
                            stripCustom.append(START_FMT).append(formatDescription);
                        }
                    }
                    foundFormats.add(format);
                    if (format == null) {
                        formatDescription = null;
                    }
                    foundDescriptions.add(formatDescription);
                    Preconditions.checkState(foundFormats.size() == fmtCount);
                    Preconditions.checkState(foundDescriptions.size() == fmtCount);
                    if (c[parsePosition.getIndex()] != '}') {
                        throw new IllegalArgumentException("Unreadable format element at position " + start);
                    }
                }
                stripCustom.append(c[parsePosition.getIndex()]);
                next(parsePosition);
            }
        }
        super.applyPattern(stripCustom.toString());
        this.toPattern = insertFormats(super.toPattern(), foundDescriptions);
        if (containsElements(foundFormats)) {
            Format[] origFormats = getFormats();
            Iterator<Format> it = foundFormats.iterator();
            int i = 0;
            while (it.hasNext()) {
                Format f = (Format) it.next();
                if (f != null) {
                    origFormats[i] = f;
                }
                i++;
            }
            super.setFormats(origFormats);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ExtendedMessageFormat other = (ExtendedMessageFormat) obj;
        if (this.registry == null) {
            if (other.registry != null) {
                return false;
            }
        } else if (!this.registry.equals(other.registry)) {
            return false;
        }
        if (this.toPattern == null) {
            if (other.toPattern != null) {
                return false;
            }
            return true;
        } else if (this.toPattern.equals(other.toPattern)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((super.hashCode() * 31) + (this.registry == null ? 0 : this.registry.hashCode())) * 31;
        if (this.toPattern != null) {
            i = this.toPattern.hashCode();
        }
        return hashCode + i;
    }

    public void setFormat(int formatElementIndex, Format newFormat) {
        super.setFormat(formatElementIndex, newFormat);
    }

    public void setFormatByArgumentIndex(int argumentIndex, Format newFormat) {
        super.setFormatByArgumentIndex(argumentIndex, newFormat);
    }

    public void setFormats(Format[] newFormats) {
        super.setFormats(newFormats);
    }

    public void setFormatsByArgumentIndex(Format[] newFormats) {
        super.setFormatsByArgumentIndex(newFormats);
    }

    public String toPattern() {
        return this.toPattern;
    }

    private StringBuilder appendQuotedString(String pattern, ParsePosition pos, StringBuilder appendTo, boolean escapingOn) {
        int start = pos.getIndex();
        char[] c = pattern.toCharArray();
        if (escapingOn && c[start] == QUOTE) {
            next(pos);
            if (appendTo == null) {
                return null;
            }
            return appendTo.append(QUOTE);
        }
        int lastHold = start;
        int i = pos.getIndex();
        while (i < pattern.length()) {
            if (!escapingOn || !pattern.substring(i).startsWith(ESCAPED_QUOTE)) {
                switch (c[pos.getIndex()]) {
                    case '\'':
                        next(pos);
                        if (appendTo != null) {
                            return appendTo.append(c, lastHold, pos.getIndex() - lastHold);
                        }
                        return null;
                    default:
                        next(pos);
                        break;
                }
            }
            appendTo.append(c, lastHold, pos.getIndex() - lastHold).append(QUOTE);
            pos.setIndex(ESCAPED_QUOTE.length() + i);
            lastHold = pos.getIndex();
            i++;
        }
        throw new IllegalArgumentException("Unterminated quoted string at position " + start);
    }

    private boolean containsElements(Collection<?> coll) {
        return (coll == null || coll.isEmpty()) ? false : true;
    }

    private Format getFormat(String desc) {
        if (this.registry != null) {
            String name = desc;
            String args = null;
            int i = desc.indexOf(44);
            if (i > 0) {
                name = desc.substring(0, i).trim();
                args = desc.substring(i + 1).trim();
            }
            FormatFactory factory = (FormatFactory) this.registry.get(name);
            if (factory != null) {
                return factory.getFormat(name, args, getLocale());
            }
        }
        return null;
    }

    private void getQuotedString(String pattern, ParsePosition pos, boolean escapingOn) {
        appendQuotedString(pattern, pos, null, escapingOn);
    }

    private boolean hasRegistry() {
        return this.registry == null || this.registry.isEmpty();
    }

    private String insertFormats(String pattern, ArrayList<String> customPatterns) {
        if (!containsElements(customPatterns)) {
            return pattern;
        }
        StringBuilder sb = new StringBuilder(pattern.length() * 2);
        ParsePosition pos = new ParsePosition(0);
        int fe = -1;
        int depth = 0;
        do {
            char c = pattern.charAt(pos.getIndex());
            if (QUOTE == c) {
                appendQuotedString(pattern, pos, sb, false);
            } else if (START_FE == c) {
                depth++;
                if (depth == 1) {
                    fe++;
                    sb.append(START_FE).append(readArgumentIndex(pattern, next(pos)));
                    String customPattern = (String) customPatterns.get(fe);
                    if (customPattern != null) {
                        sb.append(START_FMT).append(customPattern);
                    }
                }
            } else {
                if (END_FE == c) {
                    depth--;
                }
                sb.append(c);
                next(pos);
            }
        } while (pos.getIndex() < pattern.length());
        return sb.toString();
    }

    private ParsePosition next(ParsePosition pos) {
        pos.setIndex(pos.getIndex() + 1);
        return pos;
    }

    private String parseFormatDescription(String pattern, ParsePosition pos) {
        int start = pos.getIndex();
        seekNonWs(pattern, pos);
        int text = pos.getIndex();
        int depth = 1;
        while (pos.getIndex() < pattern.length()) {
            char charAt = pattern.charAt(pos.getIndex());
            if (START_FE == charAt) {
                depth++;
            } else if (END_FE == charAt) {
                depth--;
                if (depth == 0) {
                    return pattern.substring(text, pos.getIndex());
                }
            } else if (QUOTE == charAt) {
                getQuotedString(pattern, pos, false);
            }
            next(pos);
        }
        throw new IllegalArgumentException("Unterminated format element at position " + start);
    }

    private int readArgumentIndex(String pattern, ParsePosition pos) {
        int start = pos.getIndex();
        seekNonWs(pattern, pos);
        StringBuffer result = new StringBuffer();
        boolean error = false;
        while (!error && pos.getIndex() < pattern.length()) {
            char c = pattern.charAt(pos.getIndex());
            if (Character.isWhitespace(c)) {
                seekNonWs(pattern, pos);
                c = pattern.charAt(pos.getIndex());
                if (!(c == START_FMT || c == END_FE)) {
                    error = true;
                    next(pos);
                }
            }
            if ((c == START_FMT || c == END_FE) && result.length() > 0) {
                try {
                    return Integer.parseInt(result.toString());
                } catch (NumberFormatException e) {
                }
            }
            error = !Character.isDigit(c);
            result.append(c);
            next(pos);
        }
        if (error) {
            throw new IllegalArgumentException("Invalid format argument index at position " + start + ": " + pattern.substring(start, pos.getIndex()));
        }
        throw new IllegalArgumentException("Unterminated format element at position " + start);
    }

    private void seekNonWs(String pattern, ParsePosition pos) {
        char[] buffer = pattern.toCharArray();
        do {
            int len = Arrays.binarySearch(SPLIT_CHARS, buffer[pos.getIndex()]) >= 0 ? 1 : 0;
            pos.setIndex(pos.getIndex() + len);
            if (len <= 0) {
                return;
            }
        } while (pos.getIndex() < pattern.length());
    }
}
