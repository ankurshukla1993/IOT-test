package com.google.common.io;

import com.google.common.base.CharMatcher;
import com.google.common.io.BaseEncoding.StandardBaseEncoding;
import java.io.IOException;

class BaseEncoding$StandardBaseEncoding$2 implements ByteInput {
    int bitBuffer = 0;
    int bitBufferLength = 0;
    boolean hitPadding = false;
    final CharMatcher paddingMatcher = this.this$0.padding();
    int readChars = 0;
    final /* synthetic */ StandardBaseEncoding this$0;
    final /* synthetic */ CharInput val$reader;

    BaseEncoding$StandardBaseEncoding$2(StandardBaseEncoding standardBaseEncoding, CharInput charInput) {
        this.this$0 = standardBaseEncoding;
        this.val$reader = charInput;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read() throws java.io.IOException {
        /*
        r6 = this;
        r5 = 1;
        r2 = -1;
    L_0x0002:
        r3 = r6.val$reader;
        r1 = r3.read();
        if (r1 != r2) goto L_0x0039;
    L_0x000a:
        r3 = r6.hitPadding;
        if (r3 != 0) goto L_0x00e7;
    L_0x000e:
        r3 = r6.this$0;
        r3 = com.google.common.io.BaseEncoding.StandardBaseEncoding.access$000(r3);
        r4 = r6.readChars;
        r3 = r3.isValidPaddingStartPosition(r4);
        if (r3 != 0) goto L_0x00e7;
    L_0x001c:
        r2 = new com.google.common.io.BaseEncoding$DecodingException;
        r3 = r6.readChars;
        r4 = new java.lang.StringBuilder;
        r5 = 32;
        r4.<init>(r5);
        r5 = "Invalid input length ";
        r4 = r4.append(r5);
        r3 = r4.append(r3);
        r3 = r3.toString();
        r2.<init>(r3);
        throw r2;
    L_0x0039:
        r3 = r6.readChars;
        r3 = r3 + 1;
        r6.readChars = r3;
        r0 = (char) r1;
        r3 = r6.paddingMatcher;
        r3 = r3.matches(r0);
        if (r3 == 0) goto L_0x0080;
    L_0x0048:
        r3 = r6.hitPadding;
        if (r3 != 0) goto L_0x007d;
    L_0x004c:
        r3 = r6.readChars;
        if (r3 == r5) goto L_0x0060;
    L_0x0050:
        r3 = r6.this$0;
        r3 = com.google.common.io.BaseEncoding.StandardBaseEncoding.access$000(r3);
        r4 = r6.readChars;
        r4 = r4 + -1;
        r3 = r3.isValidPaddingStartPosition(r4);
        if (r3 != 0) goto L_0x007d;
    L_0x0060:
        r2 = new com.google.common.io.BaseEncoding$DecodingException;
        r3 = r6.readChars;
        r4 = new java.lang.StringBuilder;
        r5 = 41;
        r4.<init>(r5);
        r5 = "Padding cannot start at index ";
        r4 = r4.append(r5);
        r3 = r4.append(r3);
        r3 = r3.toString();
        r2.<init>(r3);
        throw r2;
    L_0x007d:
        r6.hitPadding = r5;
        goto L_0x0002;
    L_0x0080:
        r3 = r6.hitPadding;
        if (r3 == 0) goto L_0x00ab;
    L_0x0084:
        r2 = new com.google.common.io.BaseEncoding$DecodingException;
        r3 = r6.readChars;
        r4 = new java.lang.StringBuilder;
        r5 = 61;
        r4.<init>(r5);
        r5 = "Expected padding character but found '";
        r4 = r4.append(r5);
        r4 = r4.append(r0);
        r5 = "' at index ";
        r4 = r4.append(r5);
        r3 = r4.append(r3);
        r3 = r3.toString();
        r2.<init>(r3);
        throw r2;
    L_0x00ab:
        r3 = r6.bitBuffer;
        r4 = r6.this$0;
        r4 = com.google.common.io.BaseEncoding.StandardBaseEncoding.access$000(r4);
        r4 = r4.bitsPerChar;
        r3 = r3 << r4;
        r6.bitBuffer = r3;
        r3 = r6.bitBuffer;
        r4 = r6.this$0;
        r4 = com.google.common.io.BaseEncoding.StandardBaseEncoding.access$000(r4);
        r4 = r4.decode(r0);
        r3 = r3 | r4;
        r6.bitBuffer = r3;
        r3 = r6.bitBufferLength;
        r4 = r6.this$0;
        r4 = com.google.common.io.BaseEncoding.StandardBaseEncoding.access$000(r4);
        r4 = r4.bitsPerChar;
        r3 = r3 + r4;
        r6.bitBufferLength = r3;
        r3 = r6.bitBufferLength;
        r4 = 8;
        if (r3 < r4) goto L_0x0002;
    L_0x00da:
        r2 = r6.bitBufferLength;
        r2 = r2 + -8;
        r6.bitBufferLength = r2;
        r2 = r6.bitBuffer;
        r3 = r6.bitBufferLength;
        r2 = r2 >> r3;
        r2 = r2 & 255;
    L_0x00e7:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.BaseEncoding$StandardBaseEncoding$2.read():int");
    }

    public void close() throws IOException {
        this.val$reader.close();
    }
}
