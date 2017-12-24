package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.io.GwtWorkarounds.ByteInput;
import com.google.common.io.GwtWorkarounds.ByteOutput;
import com.google.common.io.GwtWorkarounds.CharInput;
import com.google.common.io.GwtWorkarounds.CharOutput;
import com.google.common.math.IntMath;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
@Beta
public abstract class BaseEncoding {
    private static final BaseEncoding BASE16 = new StandardBaseEncoding("base16()", "0123456789ABCDEF", null);
    private static final BaseEncoding BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", Character.valueOf('='));
    private static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", Character.valueOf('='));
    private static final BaseEncoding BASE64 = new StandardBaseEncoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", Character.valueOf('='));
    private static final BaseEncoding BASE64_URL = new StandardBaseEncoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", Character.valueOf('='));

    static final class StandardBaseEncoding extends BaseEncoding {
        private final Alphabet alphabet;
        private transient BaseEncoding lowerCase;
        @Nullable
        private final Character paddingChar;
        private transient BaseEncoding upperCase;

        StandardBaseEncoding(String name, String alphabetChars, @Nullable Character paddingChar) {
            this(new Alphabet(name, alphabetChars.toCharArray()), paddingChar);
        }

        StandardBaseEncoding(Alphabet alphabet, @Nullable Character paddingChar) {
            boolean z;
            this.alphabet = (Alphabet) Preconditions.checkNotNull(alphabet);
            if (paddingChar == null || !alphabet.matches(paddingChar.charValue())) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "Padding character %s was already in alphabet", new Object[]{paddingChar});
            this.paddingChar = paddingChar;
        }

        CharMatcher padding() {
            return this.paddingChar == null ? CharMatcher.NONE : CharMatcher.is(this.paddingChar.charValue());
        }

        int maxEncodedSize(int bytes) {
            return this.alphabet.charsPerChunk * IntMath.divide(bytes, this.alphabet.bytesPerChunk, RoundingMode.CEILING);
        }

        ByteOutput encodingStream(CharOutput out) {
            Preconditions.checkNotNull(out);
            return new 1(this, out);
        }

        int maxDecodedSize(int chars) {
            return (int) (((((long) this.alphabet.bitsPerChar) * ((long) chars)) + 7) / 8);
        }

        ByteInput decodingStream(CharInput reader) {
            Preconditions.checkNotNull(reader);
            return new 2(this, reader);
        }

        public BaseEncoding omitPadding() {
            return this.paddingChar == null ? this : new StandardBaseEncoding(this.alphabet, null);
        }

        public BaseEncoding withPadChar(char padChar) {
            if (8 % this.alphabet.bitsPerChar != 0) {
                return (this.paddingChar == null || this.paddingChar.charValue() != padChar) ? new StandardBaseEncoding(this.alphabet, Character.valueOf(padChar)) : this;
            } else {
                return this;
            }
        }

        public BaseEncoding withSeparator(String separator, int afterEveryChars) {
            Preconditions.checkNotNull(separator);
            Preconditions.checkArgument(padding().or(this.alphabet).matchesNoneOf(separator), "Separator cannot contain alphabet or padding characters");
            return new SeparatedBaseEncoding(this, separator, afterEveryChars);
        }

        public BaseEncoding upperCase() {
            BaseEncoding result = this.upperCase;
            if (result == null) {
                Alphabet upper = this.alphabet.upperCase();
                result = upper == this.alphabet ? this : new StandardBaseEncoding(upper, this.paddingChar);
                this.upperCase = result;
            }
            return result;
        }

        public BaseEncoding lowerCase() {
            BaseEncoding result = this.lowerCase;
            if (result == null) {
                Alphabet lower = this.alphabet.lowerCase();
                result = lower == this.alphabet ? this : new StandardBaseEncoding(lower, this.paddingChar);
                this.lowerCase = result;
            }
            return result;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder("BaseEncoding.");
            builder.append(this.alphabet.toString());
            if (8 % this.alphabet.bitsPerChar != 0) {
                if (this.paddingChar == null) {
                    builder.append(".omitPadding()");
                } else {
                    builder.append(".withPadChar(").append(this.paddingChar).append(')');
                }
            }
            return builder.toString();
        }
    }

    abstract ByteInput decodingStream(CharInput charInput);

    abstract ByteOutput encodingStream(CharOutput charOutput);

    @CheckReturnValue
    public abstract BaseEncoding lowerCase();

    abstract int maxDecodedSize(int i);

    abstract int maxEncodedSize(int i);

    @CheckReturnValue
    public abstract BaseEncoding omitPadding();

    abstract CharMatcher padding();

    @CheckReturnValue
    public abstract BaseEncoding upperCase();

    @CheckReturnValue
    public abstract BaseEncoding withPadChar(char c);

    @CheckReturnValue
    public abstract BaseEncoding withSeparator(String str, int i);

    BaseEncoding() {
    }

    public String encode(byte[] bytes) {
        return encode((byte[]) Preconditions.checkNotNull(bytes), 0, bytes.length);
    }

    public final String encode(byte[] bytes, int off, int len) {
        Preconditions.checkNotNull(bytes);
        Preconditions.checkPositionIndexes(off, off + len, bytes.length);
        CharOutput result = GwtWorkarounds.stringBuilderOutput(maxEncodedSize(len));
        ByteOutput byteOutput = encodingStream(result);
        int i = 0;
        while (i < len) {
            try {
                byteOutput.write(bytes[off + i]);
                i++;
            } catch (IOException e) {
                throw new AssertionError("impossible");
            }
        }
        byteOutput.close();
        return result.toString();
    }

    @GwtIncompatible("Writer,OutputStream")
    public final OutputStream encodingStream(Writer writer) {
        return GwtWorkarounds.asOutputStream(encodingStream(GwtWorkarounds.asCharOutput(writer)));
    }

    @GwtIncompatible("ByteSink,CharSink")
    public final ByteSink encodingSink(CharSink encodedSink) {
        Preconditions.checkNotNull(encodedSink);
        return new 1(this, encodedSink);
    }

    private static byte[] extract(byte[] result, int length) {
        if (length == result.length) {
            return result;
        }
        byte[] trunc = new byte[length];
        System.arraycopy(result, 0, trunc, 0, length);
        return trunc;
    }

    public final byte[] decode(CharSequence chars) {
        try {
            return decodeChecked(chars);
        } catch (DecodingException badInput) {
            throw new IllegalArgumentException(badInput);
        }
    }

    final byte[] decodeChecked(CharSequence chars) throws DecodingException {
        chars = padding().trimTrailingFrom(chars);
        ByteInput decodedInput = decodingStream(GwtWorkarounds.asCharInput(chars));
        byte[] tmp = new byte[maxDecodedSize(chars.length())];
        try {
            int i = decodedInput.read();
            int index = 0;
            while (i != -1) {
                int index2 = index + 1;
                tmp[index] = (byte) i;
                i = decodedInput.read();
                index = index2;
            }
            return extract(tmp, index);
        } catch (DecodingException badInput) {
            throw badInput;
        } catch (IOException impossible) {
            throw new AssertionError(impossible);
        }
    }

    @GwtIncompatible("Reader,InputStream")
    public final InputStream decodingStream(Reader reader) {
        return GwtWorkarounds.asInputStream(decodingStream(GwtWorkarounds.asCharInput(reader)));
    }

    @GwtIncompatible("ByteSource,CharSource")
    public final ByteSource decodingSource(CharSource encodedSource) {
        Preconditions.checkNotNull(encodedSource);
        return new 2(this, encodedSource);
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    static CharInput ignoringInput(CharInput delegate, CharMatcher toIgnore) {
        Preconditions.checkNotNull(delegate);
        Preconditions.checkNotNull(toIgnore);
        return new 3(delegate, toIgnore);
    }

    static CharOutput separatingOutput(CharOutput delegate, String separator, int afterEveryChars) {
        Preconditions.checkNotNull(delegate);
        Preconditions.checkNotNull(separator);
        Preconditions.checkArgument(afterEveryChars > 0);
        return new 4(afterEveryChars, separator, delegate);
    }
}
