package org.webrtc;

import java.util.LinkedList;

public class VideoTrack extends MediaStreamTrack {
    private final LinkedList<VideoRenderer> renderers = new LinkedList();

    private static native void free(long j);

    private static native void nativeAddRenderer(long j, long j2);

    private static native void nativeRemoveRenderer(long j, long j2);

    public VideoTrack(long nativeTrack) {
        super(nativeTrack);
    }

    public void addRenderer(VideoRenderer renderer) {
        this.renderers.add(renderer);
        nativeAddRenderer(this.nativeTrack, renderer.nativeVideoRenderer);
    }

    public void removeRenderer(VideoRenderer renderer) {
        if (this.renderers.remove(renderer)) {
            nativeRemoveRenderer(this.nativeTrack, renderer.nativeVideoRenderer);
            renderer.dispose();
        }
    }

    public void dispose() {
        while (!this.renderers.isEmpty()) {
            removeRenderer((VideoRenderer) this.renderers.getFirst());
        }
        super.dispose();
    }
}
