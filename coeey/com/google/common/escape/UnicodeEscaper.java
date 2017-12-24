package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import kotlin.jvm.internal.CharCompanionObject;

@GwtCompatible
@Beta
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    protected abstract char[] escape(int i);

    protected UnicodeEscaper() {
    }

    protected int nextEscapeIndex(CharSequence csq, int start, int end) {
        int index = start;
        while (index < end) {
            int cp = codePointAt(csq, index, end);
            if (cp < 0 || escape(cp) != null) {
                break;
            }
            index += Character.isSupplementaryCodePoint(cp) ? 2 : 1;
        }
        return index;
    }

    public String escape(String string) {
        Preconditions.checkNotNull(string);
        int end = string.length();
        int index = nextEscapeIndex(string, 0, end);
        return index == end ? string : escapeSlow(string, index);
    }

    protected final String escapeSlow(String s, int index) {
        int charsSkipped;
        int end = s.length();
        char[] dest = Platform.charBufferFromThreadLocal();
        int destIndex = 0;
        int unescapedChunkStart = 0;
        while (index < end) {
            int cp = codePointAt(s, index, end);
            if (cp < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] escaped = escape(cp);
            int nextIndex = index + (Character.isSupplementaryCodePoint(cp) ? 2 : 1);
            if (escaped != null) {
                charsSkipped = index - unescapedChunkStart;
                int sizeNeeded = (destIndex + charsSkipped) + escaped.length;
                if (dest.length < sizeNeeded) {
                    dest = growBuffer(dest, destIndex, ((end - index) + sizeNeeded) + 32);
                }
                if (charsSkipped > 0) {
                    s.getChars(unescapedChunkStart, index, dest, destIndex);
                    destIndex += charsSkipped;
                }
                if (escaped.length > 0) {
                    System.arraycopy(escaped, 0, dest, destIndex, escaped.length);
                    destIndex += escaped.length;
                }
                unescapedChunkStart = nextIndex;
            }
            index = nextEscapeIndex(s, nextIndex, end);
        }
        charsSkipped = end - unescapedChunkStart;
        if (charsSkipped > 0) {
            int endIndex = destIndex + charsSkipped;
            if (dest.length < endIndex) {
                dest = growBuffer(dest, destIndex, endIndex);
            }
            s.getChars(unescapedChunkStart, end, dest, destIndex);
            destIndex = endIndex;
        }
        return new String(dest, 0, destIndex);
    }

    protected static int codePointAt(CharSequence seq, int index, int end) {
        Preconditions.checkNotNull(seq);
        if (index < end) {
            int index2 = index + 1;
            char c1 = seq.charAt(index);
            if (c1 < '?' || c1 > '?') {
                return c1;
            }
            if (c1 > CharCompanionObject.MAX_HIGH_SURROGATE) {
                int i = index2 - 1;
                String valueOf = String.valueOf(String.valueOf(seq));
                throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 88).append("Unexpected low surrogate character '").append(c1).append("' with value ").append(c1).append(" at index ").append(i).append(" in '").append(valueOf).append("'").toString());
            } else if (index2 == end) {
                return -c1;
            } else {
                char c2 = seq.charAt(index2);
                if (Character.isLowSurrogate(c2)) {
                    return Character.toCodePoint(c1, c2);
                }
                String valueOf2 = String.valueOf(String.valueOf(seq));
                throw new IllegalArgumentException(new StringBuilder(valueOf2.length() + 89).append("Expected low surrogate but got char '").append(c2).append("' with value ").append(c2).append(" at index ").append(index2).append(" in '").append(valueOf2).append("'").toString());
            }
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    private static char[] growBuffer(char[] dest, int index, int size) {
        char[] copy = new char[size];
        if (index > 0) {
            System.arraycopy(dest, 0, copy, 0, index);
        }
        return copy;
    }
}
