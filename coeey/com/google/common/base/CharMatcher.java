package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.Arrays;
import java.util.BitSet;
import javax.annotation.CheckReturnValue;

@GwtCompatible(emulated = true)
@Beta
public abstract class CharMatcher implements Predicate<Character> {
    public static final CharMatcher ANY = new 7("CharMatcher.ANY");
    public static final CharMatcher ASCII = inRange('\u0000', Ascii.MAX, "CharMatcher.ASCII");
    public static final CharMatcher BREAKING_WHITESPACE = new 1();
    public static final CharMatcher DIGIT = new RangesMatcher("CharMatcher.DIGIT", ZEROES.toCharArray(), NINES.toCharArray());
    private static final int DISTINCT_CHARS = 65536;
    public static final CharMatcher INVISIBLE = new RangesMatcher("CharMatcher.INVISIBLE", "\u0000­؀؜۝܏ ᠎   ⁦⁧⁨⁩⁪　?﻿￹￺".toCharArray(), "  ­؄؜۝܏ ᠎‏ ⁤⁦⁧⁨⁩⁯　﻿￹￻".toCharArray());
    public static final CharMatcher JAVA_DIGIT = new 2("CharMatcher.JAVA_DIGIT");
    public static final CharMatcher JAVA_ISO_CONTROL = inRange('\u0000', '\u001f').or(inRange(Ascii.MAX, '')).withToString("CharMatcher.JAVA_ISO_CONTROL");
    public static final CharMatcher JAVA_LETTER = new 3("CharMatcher.JAVA_LETTER");
    public static final CharMatcher JAVA_LETTER_OR_DIGIT = new 4("CharMatcher.JAVA_LETTER_OR_DIGIT");
    public static final CharMatcher JAVA_LOWER_CASE = new 6("CharMatcher.JAVA_LOWER_CASE");
    public static final CharMatcher JAVA_UPPER_CASE = new 5("CharMatcher.JAVA_UPPER_CASE");
    private static final String NINES;
    public static final CharMatcher NONE = new 8("CharMatcher.NONE");
    public static final CharMatcher SINGLE_WIDTH = new RangesMatcher("CharMatcher.SINGLE_WIDTH", "\u0000־א׳؀ݐ฀Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ๿₯℺﷿﻿ￜ".toCharArray());
    public static final CharMatcher WHITESPACE = new 15("WHITESPACE");
    static final int WHITESPACE_MULTIPLIER = 1682554634;
    static final int WHITESPACE_SHIFT = Integer.numberOfLeadingZeros(WHITESPACE_TABLE.length() - 1);
    static final String WHITESPACE_TABLE = " 　\r   　 \u000b　   　 \t     \f 　 　　 \n 　";
    private static final String ZEROES = "0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０";
    final String description;

    public abstract boolean matches(char c);

    static {
        StringBuilder builder = new StringBuilder(ZEROES.length());
        for (int i = 0; i < ZEROES.length(); i++) {
            builder.append((char) (ZEROES.charAt(i) + 9));
        }
        NINES = builder.toString();
    }

    private static String showCharacter(char c) {
        String hex = "0123456789ABCDEF";
        char[] tmp = new char[]{'\\', 'u', '\u0000', '\u0000', '\u0000', '\u0000'};
        for (int i = 0; i < 4; i++) {
            tmp[5 - i] = hex.charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(tmp);
    }

    public static CharMatcher is(char match) {
        String valueOf = String.valueOf(String.valueOf(showCharacter(match)));
        return new 9(new StringBuilder(valueOf.length() + 18).append("CharMatcher.is('").append(valueOf).append("')").toString(), match);
    }

    public static CharMatcher isNot(char match) {
        String valueOf = String.valueOf(String.valueOf(showCharacter(match)));
        return new 10(new StringBuilder(valueOf.length() + 21).append("CharMatcher.isNot('").append(valueOf).append("')").toString(), match);
    }

    public static CharMatcher anyOf(CharSequence sequence) {
        switch (sequence.length()) {
            case 0:
                return NONE;
            case 1:
                return is(sequence.charAt(0));
            case 2:
                return isEither(sequence.charAt(0), sequence.charAt(1));
            default:
                char[] chars = sequence.toString().toCharArray();
                Arrays.sort(chars);
                StringBuilder description = new StringBuilder("CharMatcher.anyOf(\"");
                for (char c : chars) {
                    description.append(showCharacter(c));
                }
                description.append("\")");
                return new 11(description.toString(), chars);
        }
    }

    private static CharMatcher isEither(char match1, char match2) {
        String valueOf = String.valueOf(String.valueOf(showCharacter(match1)));
        String valueOf2 = String.valueOf(String.valueOf(showCharacter(match2)));
        return new 12(new StringBuilder((valueOf.length() + 21) + valueOf2.length()).append("CharMatcher.anyOf(\"").append(valueOf).append(valueOf2).append("\")").toString(), match1, match2);
    }

    public static CharMatcher noneOf(CharSequence sequence) {
        return anyOf(sequence).negate();
    }

    public static CharMatcher inRange(char startInclusive, char endInclusive) {
        Preconditions.checkArgument(endInclusive >= startInclusive);
        String valueOf = String.valueOf(String.valueOf(showCharacter(startInclusive)));
        String valueOf2 = String.valueOf(String.valueOf(showCharacter(endInclusive)));
        return inRange(startInclusive, endInclusive, new StringBuilder((valueOf.length() + 27) + valueOf2.length()).append("CharMatcher.inRange('").append(valueOf).append("', '").append(valueOf2).append("')").toString());
    }

    static CharMatcher inRange(char startInclusive, char endInclusive, String description) {
        return new 13(description, startInclusive, endInclusive);
    }

    public static CharMatcher forPredicate(Predicate<? super Character> predicate) {
        Preconditions.checkNotNull(predicate);
        if (predicate instanceof CharMatcher) {
            return (CharMatcher) predicate;
        }
        String valueOf = String.valueOf(String.valueOf(predicate));
        return new 14(new StringBuilder(valueOf.length() + 26).append("CharMatcher.forPredicate(").append(valueOf).append(")").toString(), predicate);
    }

    CharMatcher(String description) {
        this.description = description;
    }

    protected CharMatcher() {
        this.description = super.toString();
    }

    public CharMatcher negate() {
        return new NegatedMatcher(this);
    }

    public CharMatcher and(CharMatcher other) {
        return new And(this, (CharMatcher) Preconditions.checkNotNull(other));
    }

    public CharMatcher or(CharMatcher other) {
        return new Or(this, (CharMatcher) Preconditions.checkNotNull(other));
    }

    public CharMatcher precomputed() {
        return Platform.precomputeCharMatcher(this);
    }

    CharMatcher withToString(String description) {
        throw new UnsupportedOperationException();
    }

    @GwtIncompatible("java.util.BitSet")
    CharMatcher precomputedInternal() {
        BitSet table = new BitSet();
        setBits(table);
        int totalCharacters = table.cardinality();
        if (totalCharacters * 2 <= 65536) {
            return precomputedPositive(totalCharacters, table, this.description);
        }
        String negatedDescription;
        table.flip(0, 65536);
        int negatedCharacters = 65536 - totalCharacters;
        String suffix = ".negate()";
        if (this.description.endsWith(suffix)) {
            negatedDescription = this.description.substring(0, this.description.length() - suffix.length());
        } else {
            String valueOf = String.valueOf(this.description);
            String valueOf2 = String.valueOf(suffix);
            negatedDescription = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        return new NegatedFastMatcher(toString(), precomputedPositive(negatedCharacters, table, negatedDescription));
    }

    @GwtIncompatible("java.util.BitSet")
    private static CharMatcher precomputedPositive(int totalCharacters, BitSet table, String description) {
        switch (totalCharacters) {
            case 0:
                return NONE;
            case 1:
                return is((char) table.nextSetBit(0));
            case 2:
                char c1 = (char) table.nextSetBit(0);
                return isEither(c1, (char) table.nextSetBit(c1 + 1));
            default:
                if (isSmall(totalCharacters, table.length())) {
                    return SmallCharMatcher.from(table, description);
                }
                return new BitSetMatcher(table, description, null);
        }
    }

    @GwtIncompatible("SmallCharMatcher")
    private static boolean isSmall(int totalCharacters, int tableLength) {
        return totalCharacters <= 1023 && tableLength > (totalCharacters * 4) * 16;
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet table) {
        for (int c = 65535; c >= 0; c--) {
            if (matches((char) c)) {
                table.set(c);
            }
        }
    }

    public boolean matchesAnyOf(CharSequence sequence) {
        return !matchesNoneOf(sequence);
    }

    public boolean matchesAllOf(CharSequence sequence) {
        for (int i = sequence.length() - 1; i >= 0; i--) {
            if (!matches(sequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesNoneOf(CharSequence sequence) {
        return indexIn(sequence) == -1;
    }

    public int indexIn(CharSequence sequence) {
        int length = sequence.length();
        for (int i = 0; i < length; i++) {
            if (matches(sequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int indexIn(CharSequence sequence, int start) {
        int length = sequence.length();
        Preconditions.checkPositionIndex(start, length);
        for (int i = start; i < length; i++) {
            if (matches(sequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexIn(CharSequence sequence) {
        for (int i = sequence.length() - 1; i >= 0; i--) {
            if (matches(sequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int countIn(CharSequence sequence) {
        int count = 0;
        for (int i = 0; i < sequence.length(); i++) {
            if (matches(sequence.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    @CheckReturnValue
    public String removeFrom(CharSequence sequence) {
        String string = sequence.toString();
        int pos = indexIn(string);
        if (pos == -1) {
            return string;
        }
        char[] chars = string.toCharArray();
        int spread = 1;
        while (true) {
            pos++;
            while (pos != chars.length) {
                if (matches(chars[pos])) {
                    spread++;
                } else {
                    chars[pos - spread] = chars[pos];
                    pos++;
                }
            }
            return new String(chars, 0, pos - spread);
        }
    }

    @CheckReturnValue
    public String retainFrom(CharSequence sequence) {
        return negate().removeFrom(sequence);
    }

    @CheckReturnValue
    public String replaceFrom(CharSequence sequence, char replacement) {
        String string = sequence.toString();
        int pos = indexIn(string);
        if (pos == -1) {
            return string;
        }
        char[] chars = string.toCharArray();
        chars[pos] = replacement;
        for (int i = pos + 1; i < chars.length; i++) {
            if (matches(chars[i])) {
                chars[i] = replacement;
            }
        }
        return new String(chars);
    }

    @CheckReturnValue
    public String replaceFrom(CharSequence sequence, CharSequence replacement) {
        int replacementLen = replacement.length();
        if (replacementLen == 0) {
            return removeFrom(sequence);
        }
        if (replacementLen == 1) {
            return replaceFrom(sequence, replacement.charAt(0));
        }
        String string = sequence.toString();
        int pos = indexIn(string);
        if (pos == -1) {
            return string;
        }
        int len = string.length();
        StringBuilder buf = new StringBuilder(((len * 3) / 2) + 16);
        int oldpos = 0;
        do {
            buf.append(string, oldpos, pos);
            buf.append(replacement);
            oldpos = pos + 1;
            pos = indexIn(string, oldpos);
        } while (pos != -1);
        buf.append(string, oldpos, len);
        return buf.toString();
    }

    @CheckReturnValue
    public String trimFrom(CharSequence sequence) {
        int len = sequence.length();
        int first = 0;
        while (first < len && matches(sequence.charAt(first))) {
            first++;
        }
        int last = len - 1;
        while (last > first && matches(sequence.charAt(last))) {
            last--;
        }
        return sequence.subSequence(first, last + 1).toString();
    }

    @CheckReturnValue
    public String trimLeadingFrom(CharSequence sequence) {
        int len = sequence.length();
        for (int first = 0; first < len; first++) {
            if (!matches(sequence.charAt(first))) {
                return sequence.subSequence(first, len).toString();
            }
        }
        return "";
    }

    @CheckReturnValue
    public String trimTrailingFrom(CharSequence sequence) {
        for (int last = sequence.length() - 1; last >= 0; last--) {
            if (!matches(sequence.charAt(last))) {
                return sequence.subSequence(0, last + 1).toString();
            }
        }
        return "";
    }

    @CheckReturnValue
    public String collapseFrom(CharSequence sequence, char replacement) {
        int len = sequence.length();
        int i = 0;
        while (i < len) {
            char c = sequence.charAt(i);
            if (matches(c)) {
                if (c != replacement || (i != len - 1 && matches(sequence.charAt(i + 1)))) {
                    return finishCollapseFrom(sequence, i + 1, len, replacement, new StringBuilder(len).append(sequence.subSequence(0, i)).append(replacement), true);
                }
                i++;
            }
            i++;
        }
        return sequence.toString();
    }

    @CheckReturnValue
    public String trimAndCollapseFrom(CharSequence sequence, char replacement) {
        int len = sequence.length();
        int first = 0;
        while (first < len && matches(sequence.charAt(first))) {
            first++;
        }
        int last = len - 1;
        while (last > first && matches(sequence.charAt(last))) {
            last--;
        }
        if (first == 0 && last == len - 1) {
            return collapseFrom(sequence, replacement);
        }
        return finishCollapseFrom(sequence, first, last + 1, replacement, new StringBuilder((last + 1) - first), false);
    }

    private String finishCollapseFrom(CharSequence sequence, int start, int end, char replacement, StringBuilder builder, boolean inMatchingGroup) {
        for (int i = start; i < end; i++) {
            char c = sequence.charAt(i);
            if (!matches(c)) {
                builder.append(c);
                inMatchingGroup = false;
            } else if (!inMatchingGroup) {
                builder.append(replacement);
                inMatchingGroup = true;
            }
        }
        return builder.toString();
    }

    @Deprecated
    public boolean apply(Character character) {
        return matches(character.charValue());
    }

    public String toString() {
        return this.description;
    }
}
