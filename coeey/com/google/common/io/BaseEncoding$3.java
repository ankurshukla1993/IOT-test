package com.google.common.io;

import com.google.common.base.CharMatcher;
import java.io.IOException;

class BaseEncoding$3 implements CharInput {
    final /* synthetic */ CharInput val$delegate;
    final /* synthetic */ CharMatcher val$toIgnore;

    BaseEncoding$3(CharInput charInput, CharMatcher charMatcher) {
        this.val$delegate = charInput;
        this.val$toIgnore = charMatcher;
    }

    public int read() throws IOException {
        int readChar;
        do {
            readChar = this.val$delegate.read();
            if (readChar == -1) {
                break;
            }
        } while (this.val$toIgnore.matches((char) readChar));
        return readChar;
    }

    public void close() throws IOException {
        this.val$delegate.close();
    }
}
