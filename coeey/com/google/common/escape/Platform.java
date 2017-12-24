package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
final class Platform {
    private static final ThreadLocal<char[]> DEST_TL = new C17661();

    static class C17661 extends ThreadLocal<char[]> {
        C17661() {
        }

        protected char[] initialValue() {
            return new char[1024];
        }
    }

    private Platform() {
    }

    static char[] charBufferFromThreadLocal() {
        return (char[]) DEST_TL.get();
    }
}
