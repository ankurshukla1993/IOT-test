package humanize.text.util;

import com.google.common.collect.Range;
import com.google.common.escape.UnicodeEscaper;
import java.util.ArrayList;
import java.util.Collection;

public class UnicodeInterpolator extends UnicodeEscaper {
    private final Collection<Range<Integer>> ranges = new ArrayList();
    private final Replacer replacer;

    public UnicodeInterpolator(Replacer replacer) {
        this.replacer = replacer;
    }

    public void addRange(int lower, int upper) {
        addRange(Range.closed(Integer.valueOf(lower), Integer.valueOf(upper)));
    }

    public void addRange(Range<Integer> range) {
        this.ranges.add(range);
    }

    protected char[] escape(int codePoint) {
        for (Range<Integer> range : this.ranges) {
            if (range.contains(Integer.valueOf(codePoint))) {
                return this.replacer.replace(Integer.toHexString(codePoint)).toCharArray();
            }
        }
        return Character.toChars(codePoint);
    }
}
