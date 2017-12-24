package com.google.common.io;

import java.io.IOException;

class BaseEncoding$4 implements CharOutput {
    int charsUntilSeparator = this.val$afterEveryChars;
    final /* synthetic */ int val$afterEveryChars;
    final /* synthetic */ CharOutput val$delegate;
    final /* synthetic */ String val$separator;

    BaseEncoding$4(int i, String str, CharOutput charOutput) {
        this.val$afterEveryChars = i;
        this.val$separator = str;
        this.val$delegate = charOutput;
    }

    public void write(char c) throws IOException {
        if (this.charsUntilSeparator == 0) {
            for (int i = 0; i < this.val$separator.length(); i++) {
                this.val$delegate.write(this.val$separator.charAt(i));
            }
            this.charsUntilSeparator = this.val$afterEveryChars;
        }
        this.val$delegate.write(c);
        this.charsUntilSeparator--;
    }

    public void flush() throws IOException {
        this.val$delegate.flush();
    }

    public void close() throws IOException {
        this.val$delegate.close();
    }
}
