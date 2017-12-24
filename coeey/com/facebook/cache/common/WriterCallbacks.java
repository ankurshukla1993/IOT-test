package com.facebook.cache.common;

import com.facebook.common.internal.ByteStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WriterCallbacks {
    public static WriterCallback from(final InputStream is) {
        return new WriterCallback() {
            public void write(OutputStream os) throws IOException {
                ByteStreams.copy(is, os);
            }
        };
    }

    public static WriterCallback from(final byte[] data) {
        return new WriterCallback() {
            public void write(OutputStream os) throws IOException {
                os.write(data);
            }
        };
    }
}
