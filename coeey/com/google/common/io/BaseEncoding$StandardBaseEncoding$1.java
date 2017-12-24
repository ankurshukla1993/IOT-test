package com.google.common.io;

import com.google.common.io.BaseEncoding.StandardBaseEncoding;
import java.io.IOException;

class BaseEncoding$StandardBaseEncoding$1 implements ByteOutput {
    int bitBuffer = 0;
    int bitBufferLength = 0;
    final /* synthetic */ StandardBaseEncoding this$0;
    final /* synthetic */ CharOutput val$out;
    int writtenChars = 0;

    BaseEncoding$StandardBaseEncoding$1(StandardBaseEncoding standardBaseEncoding, CharOutput charOutput) {
        this.this$0 = standardBaseEncoding;
        this.val$out = charOutput;
    }

    public void write(byte b) throws IOException {
        this.bitBuffer <<= 8;
        this.bitBuffer |= b & 255;
        this.bitBufferLength += 8;
        while (this.bitBufferLength >= StandardBaseEncoding.access$000(this.this$0).bitsPerChar) {
            this.val$out.write(StandardBaseEncoding.access$000(this.this$0).encode((this.bitBuffer >> (this.bitBufferLength - StandardBaseEncoding.access$000(this.this$0).bitsPerChar)) & StandardBaseEncoding.access$000(this.this$0).mask));
            this.writtenChars++;
            this.bitBufferLength -= StandardBaseEncoding.access$000(this.this$0).bitsPerChar;
        }
    }

    public void flush() throws IOException {
        this.val$out.flush();
    }

    public void close() throws IOException {
        if (this.bitBufferLength > 0) {
            this.val$out.write(StandardBaseEncoding.access$000(this.this$0).encode((this.bitBuffer << (StandardBaseEncoding.access$000(this.this$0).bitsPerChar - this.bitBufferLength)) & StandardBaseEncoding.access$000(this.this$0).mask));
            this.writtenChars++;
            if (StandardBaseEncoding.access$100(this.this$0) != null) {
                while (this.writtenChars % StandardBaseEncoding.access$000(this.this$0).charsPerChunk != 0) {
                    this.val$out.write(StandardBaseEncoding.access$100(this.this$0).charValue());
                    this.writtenChars++;
                }
            }
        }
        this.val$out.close();
    }
}
