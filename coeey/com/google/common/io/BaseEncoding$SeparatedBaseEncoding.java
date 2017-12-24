package com.google.common.io;

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.math.RoundingMode;

final class BaseEncoding$SeparatedBaseEncoding extends BaseEncoding {
    private final int afterEveryChars;
    private final BaseEncoding delegate;
    private final String separator;
    private final CharMatcher separatorChars;

    BaseEncoding$SeparatedBaseEncoding(BaseEncoding delegate, String separator, int afterEveryChars) {
        boolean z;
        this.delegate = (BaseEncoding) Preconditions.checkNotNull(delegate);
        this.separator = (String) Preconditions.checkNotNull(separator);
        this.afterEveryChars = afterEveryChars;
        if (afterEveryChars > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Cannot add a separator after every %s chars", Integer.valueOf(afterEveryChars));
        this.separatorChars = CharMatcher.anyOf(separator).precomputed();
    }

    CharMatcher padding() {
        return this.delegate.padding();
    }

    int maxEncodedSize(int bytes) {
        int unseparatedSize = this.delegate.maxEncodedSize(bytes);
        return (this.separator.length() * IntMath.divide(Math.max(0, unseparatedSize - 1), this.afterEveryChars, RoundingMode.FLOOR)) + unseparatedSize;
    }

    ByteOutput encodingStream(CharOutput output) {
        return this.delegate.encodingStream(separatingOutput(output, this.separator, this.afterEveryChars));
    }

    int maxDecodedSize(int chars) {
        return this.delegate.maxDecodedSize(chars);
    }

    ByteInput decodingStream(CharInput input) {
        return this.delegate.decodingStream(ignoringInput(input, this.separatorChars));
    }

    public BaseEncoding omitPadding() {
        return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
    }

    public BaseEncoding withPadChar(char padChar) {
        return this.delegate.withPadChar(padChar).withSeparator(this.separator, this.afterEveryChars);
    }

    public BaseEncoding withSeparator(String separator, int afterEveryChars) {
        throw new UnsupportedOperationException("Already have a separator");
    }

    public BaseEncoding upperCase() {
        return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
    }

    public BaseEncoding lowerCase() {
        return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.delegate.toString()));
        String valueOf2 = String.valueOf(String.valueOf(this.separator));
        return new StringBuilder((valueOf.length() + 31) + valueOf2.length()).append(valueOf).append(".withSeparator(\"").append(valueOf2).append("\", ").append(this.afterEveryChars).append(")").toString();
    }
}
