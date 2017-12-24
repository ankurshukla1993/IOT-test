package com.facebook.imagepipeline.decoder;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.util.StreamUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.memory.PooledByteArrayBufferedInputStream;
import com.facebook.imageutils.JfifUtil;
import java.io.IOException;
import java.io.InputStream;

public class ProgressiveJpegParser {
    private static final int BUFFER_SIZE = 16384;
    private static final int NOT_A_JPEG = 6;
    private static final int READ_FIRST_JPEG_BYTE = 0;
    private static final int READ_MARKER_FIRST_BYTE_OR_ENTROPY_DATA = 2;
    private static final int READ_MARKER_SECOND_BYTE = 3;
    private static final int READ_SECOND_JPEG_BYTE = 1;
    private static final int READ_SIZE_FIRST_BYTE = 4;
    private static final int READ_SIZE_SECOND_BYTE = 5;
    private int mBestScanEndOffset = 0;
    private int mBestScanNumber = 0;
    private final ByteArrayPool mByteArrayPool;
    private int mBytesParsed = 0;
    private int mLastByteRead = 0;
    private int mNextFullScanNumber = 0;
    private int mParserState = 0;

    public ProgressiveJpegParser(ByteArrayPool byteArrayPool) {
        this.mByteArrayPool = (ByteArrayPool) Preconditions.checkNotNull(byteArrayPool);
    }

    public boolean parseMoreData(EncodedImage encodedImage) {
        if (this.mParserState == 6) {
            return false;
        }
        if (encodedImage.getSize() <= this.mBytesParsed) {
            return false;
        }
        boolean z = (byte[]) this.mByteArrayPool.get(16384);
        InputStream bufferedDataStream = new PooledByteArrayBufferedInputStream(encodedImage.getInputStream(), z, this.mByteArrayPool);
        try {
            StreamUtil.skip(bufferedDataStream, (long) this.mBytesParsed);
            z = doParseMoreData(bufferedDataStream);
            return z;
        } catch (IOException ioe) {
            Throwables.propagate(ioe);
            return false;
        } finally {
            Closeables.closeQuietly(bufferedDataStream);
        }
    }

    private boolean doParseMoreData(InputStream inputStream) {
        int oldBestScanNumber = this.mBestScanNumber;
        while (this.mParserState != 6) {
            int nextByte = inputStream.read();
            if (nextByte != -1) {
                this.mBytesParsed++;
                switch (this.mParserState) {
                    case 0:
                        if (nextByte != 255) {
                            this.mParserState = 6;
                            break;
                        }
                        try {
                            this.mParserState = 1;
                            break;
                        } catch (IOException ioe) {
                            Throwables.propagate(ioe);
                            break;
                        }
                    case 1:
                        if (nextByte != JfifUtil.MARKER_SOI) {
                            this.mParserState = 6;
                            break;
                        }
                        this.mParserState = 2;
                        break;
                    case 2:
                        if (nextByte == 255) {
                            this.mParserState = 3;
                            break;
                        }
                        break;
                    case 3:
                        if (nextByte != 255) {
                            if (nextByte != 0) {
                                if (nextByte == JfifUtil.MARKER_SOS || nextByte == JfifUtil.MARKER_EOI) {
                                    newScanOrImageEndFound(this.mBytesParsed - 2);
                                }
                                if (!doesMarkerStartSegment(nextByte)) {
                                    this.mParserState = 2;
                                    break;
                                }
                                this.mParserState = 4;
                                break;
                            }
                            this.mParserState = 2;
                            break;
                        }
                        this.mParserState = 3;
                        break;
                    case 4:
                        this.mParserState = 5;
                        break;
                    case 5:
                        int bytesToSkip = ((this.mLastByteRead << 8) + nextByte) - 2;
                        StreamUtil.skip(inputStream, (long) bytesToSkip);
                        this.mBytesParsed += bytesToSkip;
                        this.mParserState = 2;
                        break;
                    default:
                        Preconditions.checkState(false);
                        break;
                }
                this.mLastByteRead = nextByte;
            } else if (this.mParserState != 6 || this.mBestScanNumber == oldBestScanNumber) {
                return false;
            } else {
                return true;
            }
        }
        if (this.mParserState != 6) {
        }
        return false;
    }

    private static boolean doesMarkerStartSegment(int markerSecondByte) {
        boolean z = true;
        if (markerSecondByte == 1) {
            return false;
        }
        if (markerSecondByte >= JfifUtil.MARKER_RST0 && markerSecondByte <= JfifUtil.MARKER_RST7) {
            return false;
        }
        if (markerSecondByte == JfifUtil.MARKER_EOI || markerSecondByte == JfifUtil.MARKER_SOI) {
            z = false;
        }
        return z;
    }

    private void newScanOrImageEndFound(int offset) {
        if (this.mNextFullScanNumber > 0) {
            this.mBestScanEndOffset = offset;
        }
        int i = this.mNextFullScanNumber;
        this.mNextFullScanNumber = i + 1;
        this.mBestScanNumber = i;
    }

    public boolean isJpeg() {
        return this.mBytesParsed > 1 && this.mParserState != 6;
    }

    public int getBestScanEndOffset() {
        return this.mBestScanEndOffset;
    }

    public int getBestScanNumber() {
        return this.mBestScanNumber;
    }
}
