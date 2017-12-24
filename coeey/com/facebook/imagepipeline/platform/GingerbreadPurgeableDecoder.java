package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.MemoryFile;
import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public class GingerbreadPurgeableDecoder extends DalvikPurgeableDecoder {
    private static Method sGetFileDescriptorMethod;
    private final boolean mWebpSupportEnabled;

    public /* bridge */ /* synthetic */ CloseableReference decodeFromEncodedImage(EncodedImage encodedImage, Config config) {
        return super.decodeFromEncodedImage(encodedImage, config);
    }

    public /* bridge */ /* synthetic */ CloseableReference decodeJPEGFromEncodedImage(EncodedImage encodedImage, Config config, int i) {
        return super.decodeJPEGFromEncodedImage(encodedImage, config, i);
    }

    public /* bridge */ /* synthetic */ CloseableReference pinBitmap(Bitmap bitmap) {
        return super.pinBitmap(bitmap);
    }

    public GingerbreadPurgeableDecoder(boolean webpSupportEnabled) {
        this.mWebpSupportEnabled = webpSupportEnabled;
    }

    protected Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, Options options) {
        return decodeFileDescriptorAsPurgeable(bytesRef, ((PooledByteBuffer) bytesRef.get()).size(), null, options);
    }

    protected Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int length, Options options) {
        return decodeFileDescriptorAsPurgeable(bytesRef, length, DalvikPurgeableDecoder.endsWithEOI(bytesRef, length) ? null : EOI, options);
    }

    private static MemoryFile copyToMemoryFile(CloseableReference<PooledByteBuffer> bytesRef, int inputLength, @Nullable byte[] suffix) throws IOException {
        Throwable th;
        MemoryFile memoryFile = new MemoryFile(null, inputLength + (suffix == null ? 0 : suffix.length));
        memoryFile.allowPurging(false);
        InputStream pbbIs = null;
        InputStream is = null;
        try {
            InputStream pbbIs2 = new PooledByteBufferInputStream((PooledByteBuffer) bytesRef.get());
            try {
                InputStream is2 = new LimitedInputStream(pbbIs2, inputLength);
                try {
                    OutputStream os = memoryFile.getOutputStream();
                    ByteStreams.copy(is2, os);
                    if (suffix != null) {
                        memoryFile.writeBytes(suffix, 0, inputLength, suffix.length);
                    }
                    CloseableReference.closeSafely(bytesRef);
                    Closeables.closeQuietly(pbbIs2);
                    Closeables.closeQuietly(is2);
                    Closeables.close(os, true);
                    return memoryFile;
                } catch (Throwable th2) {
                    th = th2;
                    is = is2;
                    pbbIs = pbbIs2;
                    CloseableReference.closeSafely(bytesRef);
                    Closeables.closeQuietly(pbbIs);
                    Closeables.closeQuietly(is);
                    Closeables.close(null, true);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                pbbIs = pbbIs2;
                CloseableReference.closeSafely(bytesRef);
                Closeables.closeQuietly(pbbIs);
                Closeables.closeQuietly(is);
                Closeables.close(null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            CloseableReference.closeSafely(bytesRef);
            Closeables.closeQuietly(pbbIs);
            Closeables.closeQuietly(is);
            Closeables.close(null, true);
            throw th;
        }
    }

    private synchronized Method getFileDescriptorMethod() {
        if (sGetFileDescriptorMethod == null) {
            try {
                sGetFileDescriptorMethod = MemoryFile.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
            } catch (Exception e) {
                throw Throwables.propagate(e);
            }
        }
        return sGetFileDescriptorMethod;
    }

    private FileDescriptor getMemoryFileDescriptor(MemoryFile memoryFile) {
        try {
            return (FileDescriptor) getFileDescriptorMethod().invoke(memoryFile, new Object[0]);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    protected Bitmap decodeFileDescriptorAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int inputLength, byte[] suffix, Options options) {
        MemoryFile memoryFile = null;
        try {
            Bitmap bitmap;
            memoryFile = copyToMemoryFile(bytesRef, inputLength, suffix);
            FileDescriptor fd = getMemoryFileDescriptor(memoryFile);
            if (this.mWebpSupportEnabled) {
                bitmap = WebpSupportStatus.sWebpBitmapFactory.decodeFileDescriptor(fd, null, options);
            } else {
                bitmap = BitmapFactory.decodeFileDescriptor(fd, null, options);
            }
            Bitmap bitmap2 = (Bitmap) Preconditions.checkNotNull(bitmap, "BitmapFactory returned null");
            if (memoryFile != null) {
                memoryFile.close();
            }
            return bitmap2;
        } catch (IOException e) {
            throw Throwables.propagate(e);
        } catch (Throwable th) {
            if (memoryFile != null) {
                memoryFile.close();
            }
        }
    }
}
