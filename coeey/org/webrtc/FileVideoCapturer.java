package org.webrtc;

import android.content.Context;
import android.os.SystemClock;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.webrtc.VideoCapturer.CapturerObserver;

public class FileVideoCapturer implements VideoCapturer {
    private static final String TAG = "FileVideoCapturer";
    private CapturerObserver capturerObserver;
    private final TimerTask tickTask = new C26101();
    private final Timer timer = new Timer();
    private final VideoReader videoReader;

    class C26101 extends TimerTask {
        C26101() {
        }

        public void run() {
            FileVideoCapturer.this.tick();
        }
    }

    private interface VideoReader {
        void close();

        int getFrameHeight();

        int getFrameWidth();

        byte[] getNextFrame();
    }

    private static class VideoReaderY4M implements VideoReader {
        private static final String TAG = "VideoReaderY4M";
        private static final String Y4M_FRAME_DELIMETER = "FRAME";
        private final int frameHeight;
        private final int frameSize;
        private final int frameWidth;
        private final RandomAccessFile mediaFileStream;
        private final long videoStart;

        public int getFrameWidth() {
            return this.frameWidth;
        }

        public int getFrameHeight() {
            return this.frameHeight;
        }

        public VideoReaderY4M(String file) throws IOException {
            this.mediaFileStream = new RandomAccessFile(file, "r");
            StringBuilder builder = new StringBuilder();
            while (true) {
                char c = this.mediaFileStream.read();
                if (c == '￿') {
                    throw new RuntimeException("Found end of file before end of header for file: " + file);
                } else if (c == '\n') {
                    break;
                } else {
                    builder.append((char) c);
                }
            }
            this.videoStart = this.mediaFileStream.getFilePointer();
            int w = 0;
            int h = 0;
            String colorSpace = "";
            for (String tok : builder.toString().split("[ ]")) {
                switch (tok.charAt(0)) {
                    case 'C':
                        colorSpace = tok.substring(1);
                        break;
                    case 'H':
                        h = Integer.parseInt(tok.substring(1));
                        break;
                    case 'W':
                        w = Integer.parseInt(tok.substring(1));
                        break;
                    default:
                        break;
                }
            }
            Logging.m2187d(TAG, "Color space: " + colorSpace);
            if (!colorSpace.equals("420") && !colorSpace.equals("420mpeg2")) {
                throw new IllegalArgumentException("Does not support any other color space than I420 or I420mpeg2");
            } else if (w % 2 == 1 || h % 2 == 1) {
                throw new IllegalArgumentException("Does not support odd width or height");
            } else {
                this.frameWidth = w;
                this.frameHeight = h;
                this.frameSize = ((w * h) * 3) / 2;
                Logging.m2187d(TAG, "frame dim: (" + w + ", " + h + ") frameSize: " + this.frameSize);
            }
        }

        public byte[] getNextFrame() {
            byte[] frame = new byte[this.frameSize];
            try {
                byte[] frameDelim = new byte[(Y4M_FRAME_DELIMETER.length() + 1)];
                if (this.mediaFileStream.read(frameDelim) < frameDelim.length) {
                    this.mediaFileStream.seek(this.videoStart);
                    if (this.mediaFileStream.read(frameDelim) < frameDelim.length) {
                        throw new RuntimeException("Error looping video");
                    }
                }
                String frameDelimStr = new String(frameDelim);
                if (frameDelimStr.equals("FRAME\n")) {
                    this.mediaFileStream.readFully(frame);
                    byte[] nv21Frame = new byte[this.frameSize];
                    FileVideoCapturer.nativeI420ToNV21(frame, this.frameWidth, this.frameHeight, nv21Frame);
                    return nv21Frame;
                }
                throw new RuntimeException("Frames should be delimited by FRAME plus newline, found delimter was: '" + frameDelimStr + "'");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void close() {
            try {
                this.mediaFileStream.close();
            } catch (IOException e) {
                Logging.m2189e(TAG, "Problem closing file", e);
            }
        }
    }

    public static native void nativeI420ToNV21(byte[] bArr, int i, int i2, byte[] bArr2);

    private int getFrameWidth() {
        return this.videoReader.getFrameWidth();
    }

    private int getFrameHeight() {
        return this.videoReader.getFrameHeight();
    }

    public FileVideoCapturer(String inputFile) throws IOException {
        try {
            this.videoReader = new VideoReaderY4M(inputFile);
        } catch (IOException e) {
            Logging.m2187d(TAG, "Could not open video file: " + inputFile);
            throw e;
        }
    }

    private byte[] getNextFrame() {
        return this.videoReader.getNextFrame();
    }

    public void tick() {
        long captureTimeNs = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
        this.capturerObserver.onByteBufferFrameCaptured(getNextFrame(), getFrameWidth(), getFrameHeight(), 0, captureTimeNs);
    }

    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context applicationContext, CapturerObserver capturerObserver) {
        this.capturerObserver = capturerObserver;
    }

    public void startCapture(int width, int height, int framerate) {
        this.timer.schedule(this.tickTask, 0, (long) (1000 / framerate));
    }

    public void stopCapture() throws InterruptedException {
        this.timer.cancel();
    }

    public void changeCaptureFormat(int width, int height, int framerate) {
    }

    public void dispose() {
        this.videoReader.close();
    }

    public boolean isScreencast() {
        return false;
    }
}
