package com.facebook.fbui.textlayoutbuilder.glyphwarmer;

import android.annotation.SuppressLint;
import android.graphics.Picture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.VisibleForTesting;
import android.text.Layout;
import com.facebook.fbui.textlayoutbuilder.GlyphWarmer;
import com.facebook.fbui.textlayoutbuilder.util.LayoutMeasureUtil;

public class GlyphWarmerImpl implements GlyphWarmer {
    private static WarmHandler sWarmHandler;

    private static class WarmHandler extends Handler {
        private static final int NO_OP = 1;
        private final Picture mPicture = new Picture();

        public WarmHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            Layout layout = msg.obj;
            try {
                layout.draw(this.mPicture.beginRecording(LayoutMeasureUtil.getWidth(layout), LayoutMeasureUtil.getHeight(layout)));
                this.mPicture.endRecording();
            } catch (Exception e) {
            }
        }
    }

    public void warmLayout(Layout layout) {
        WarmHandler handler = getWarmHandler();
        handler.sendMessage(handler.obtainMessage(1, layout));
    }

    @VisibleForTesting
    Looper getWarmHandlerLooper() {
        return getWarmHandler().getLooper();
    }

    @SuppressLint({"BadMethodUse-android.os.HandlerThread._Constructor", "BadMethodUse-java.lang.Thread.start"})
    private WarmHandler getWarmHandler() {
        if (sWarmHandler == null) {
            HandlerThread warmerThread = new HandlerThread("GlyphWarmer");
            warmerThread.start();
            sWarmHandler = new WarmHandler(warmerThread.getLooper());
        }
        return sWarmHandler;
    }
}
