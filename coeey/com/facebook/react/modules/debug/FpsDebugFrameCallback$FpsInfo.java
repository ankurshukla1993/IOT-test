package com.facebook.react.modules.debug;

public class FpsDebugFrameCallback$FpsInfo {
    public final double fps;
    public final double jsFps;
    public final int total4PlusFrameStutters;
    public final int totalExpectedFrames;
    public final int totalFrames;
    public final int totalJsFrames;
    public final int totalTimeMs;

    public FpsDebugFrameCallback$FpsInfo(int totalFrames, int totalJsFrames, int totalExpectedFrames, int total4PlusFrameStutters, double fps, double jsFps, int totalTimeMs) {
        this.totalFrames = totalFrames;
        this.totalJsFrames = totalJsFrames;
        this.totalExpectedFrames = totalExpectedFrames;
        this.total4PlusFrameStutters = total4PlusFrameStutters;
        this.fps = fps;
        this.jsFps = jsFps;
        this.totalTimeMs = totalTimeMs;
    }
}
