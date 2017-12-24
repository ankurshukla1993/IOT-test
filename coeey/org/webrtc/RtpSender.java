package org.webrtc;

public class RtpSender {
    private MediaStreamTrack cachedTrack;
    private final DtmfSender dtmfSender;
    final long nativeRtpSender;
    private boolean ownsTrack = true;

    private static native void free(long j);

    private static native long nativeGetDtmfSender(long j);

    private static native RtpParameters nativeGetParameters(long j);

    private static native long nativeGetTrack(long j);

    private static native String nativeId(long j);

    private static native boolean nativeSetParameters(long j, RtpParameters rtpParameters);

    private static native boolean nativeSetTrack(long j, long j2);

    public RtpSender(long nativeRtpSender) {
        MediaStreamTrack mediaStreamTrack;
        DtmfSender dtmfSender = null;
        this.nativeRtpSender = nativeRtpSender;
        long track = nativeGetTrack(nativeRtpSender);
        if (track != 0) {
            mediaStreamTrack = new MediaStreamTrack(track);
        } else {
            mediaStreamTrack = null;
        }
        this.cachedTrack = mediaStreamTrack;
        long nativeDtmfSender = nativeGetDtmfSender(nativeRtpSender);
        if (nativeDtmfSender != 0) {
            dtmfSender = new DtmfSender(nativeDtmfSender);
        }
        this.dtmfSender = dtmfSender;
    }

    public boolean setTrack(MediaStreamTrack track, boolean takeOwnership) {
        if (!nativeSetTrack(this.nativeRtpSender, track == null ? 0 : track.nativeTrack)) {
            return false;
        }
        if (this.cachedTrack != null && this.ownsTrack) {
            this.cachedTrack.dispose();
        }
        this.cachedTrack = track;
        this.ownsTrack = takeOwnership;
        return true;
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }

    public boolean setParameters(RtpParameters parameters) {
        return nativeSetParameters(this.nativeRtpSender, parameters);
    }

    public RtpParameters getParameters() {
        return nativeGetParameters(this.nativeRtpSender);
    }

    public String id() {
        return nativeId(this.nativeRtpSender);
    }

    public DtmfSender dtmf() {
        return this.dtmfSender;
    }

    public void dispose() {
        if (this.dtmfSender != null) {
            this.dtmfSender.dispose();
        }
        if (this.cachedTrack != null && this.ownsTrack) {
            this.cachedTrack.dispose();
        }
        free(this.nativeRtpSender);
    }
}
