package com.google.common.io;

import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;

final class BaseEncoding$Alphabet extends CharMatcher {
    final int bitsPerChar;
    final int bytesPerChunk;
    private final char[] chars;
    final int charsPerChunk;
    private final byte[] decodabet;
    final int mask;
    private final String name;
    private final boolean[] validPadding;

    BaseEncoding$Alphabet(String name, char[] chars) {
        this.name = (String) Preconditions.checkNotNull(name);
        this.chars = (char[]) Preconditions.checkNotNull(chars);
        try {
            int i;
            this.bitsPerChar = IntMath.log2(chars.length, RoundingMode.UNNECESSARY);
            int gcd = Math.min(8, Integer.lowestOneBit(this.bitsPerChar));
            this.charsPerChunk = 8 / gcd;
            this.bytesPerChunk = this.bitsPerChar / gcd;
            this.mask = chars.length - 1;
            byte[] decodabet = new byte[128];
            Arrays.fill(decodabet, (byte) -1);
            for (i = 0; i < chars.length; i++) {
                boolean z;
                char c = chars[i];
                Preconditions.checkArgument(CharMatcher.ASCII.matches(c), "Non-ASCII character: %s", Character.valueOf(c));
                if (decodabet[c] == (byte) -1) {
                    z = true;
                } else {
                    z = false;
                }
                Preconditions.checkArgument(z, "Duplicate character: %s", Character.valueOf(c));
                decodabet[c] = (byte) i;
            }
            this.decodabet = decodabet;
            boolean[] validPadding = new boolean[this.charsPerChunk];
            for (i = 0; i < this.bytesPerChunk; i++) {
                validPadding[IntMath.divide(i * 8, this.bitsPerChar, RoundingMode.CEILING)] = true;
            }
            this.validPadding = validPadding;
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Illegal alphabet length " + chars.length, e);
        }
    }

    char encode(int bits) {
        return this.chars[bits];
    }

    boolean isValidPaddingStartPosition(int index) {
        return this.validPadding[index % this.charsPerChunk];
    }

    int decode(char ch) throws IOException {
        if (ch <= Ascii.MAX && this.decodabet[ch] != (byte) -1) {
            return this.decodabet[ch];
        }
        throw new BaseEncoding$DecodingException("Unrecognized character: " + ch);
    }

    private boolean hasLowerCase() {
        for (char c : this.chars) {
            if (Ascii.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasUpperCase() {
        for (char c : this.chars) {
            if (Ascii.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    BaseEncoding$Alphabet upperCase() {
        if (!hasLowerCase()) {
            return this;
        }
        Preconditions.checkState(!hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
        char[] upperCased = new char[this.chars.length];
        for (int i = 0; i < this.chars.length; i++) {
            upperCased[i] = Ascii.toUpperCase(this.chars[i]);
        }
        return new BaseEncoding$Alphabet(String.valueOf(this.name).concat(".upperCase()"), upperCased);
    }

    BaseEncoding$Alphabet lowerCase() {
        if (!hasUpperCase()) {
            return this;
        }
        Preconditions.checkState(!hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
        char[] lowerCased = new char[this.chars.length];
        for (int i = 0; i < this.chars.length; i++) {
            lowerCased[i] = Ascii.toLowerCase(this.chars[i]);
        }
        return new BaseEncoding$Alphabet(String.valueOf(this.name).concat(".lowerCase()"), lowerCased);
    }

    public boolean matches(char c) {
        return CharMatcher.ASCII.matches(c) && this.decodabet[c] != (byte) -1;
    }

    public String toString() {
        return this.name;
    }
}
