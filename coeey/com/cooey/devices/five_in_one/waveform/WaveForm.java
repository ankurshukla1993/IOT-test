package com.cooey.devices.five_in_one.waveform;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import java.util.concurrent.LinkedBlockingQueue;

public class WaveForm {
    private boolean IsStoped = false;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private LinkedBlockingQueue<Integer> mWaveQueue;
    private DrawRunnable mWaveRunnable;
    private Thread mWaveThread;

    class C09561 implements Callback {
        C09561() {
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            WaveForm.this.stop();
        }

        public void surfaceCreated(SurfaceHolder holder) {
            WaveForm.this.start();
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Canvas mCanvas = holder.lockCanvas();
            mCanvas.drawColor(0);
            holder.unlockCanvasAndPost(mCanvas);
        }
    }

    public WaveForm(Context context, SurfaceView surfaceView, WaveFormParams waveFormParams) {
        this.mSurfaceView = surfaceView;
        this.mSurfaceView.setZOrderOnTop(true);
        this.mSurfaceHolder = this.mSurfaceView.getHolder();
        this.mSurfaceHolder.setFormat(-3);
        this.mWaveQueue = new LinkedBlockingQueue(1024);
        this.mWaveRunnable = new DrawRunnable(context, this.mWaveQueue, this.mSurfaceView, this.mSurfaceHolder, waveFormParams);
        this.mWaveThread = new Thread(this.mWaveRunnable);
        this.mWaveThread.start();
        this.mSurfaceHolder.addCallback(new C09561());
    }

    protected void stop() {
        this.mWaveQueue.clear();
        this.IsStoped = true;
    }

    protected void start() {
        this.IsStoped = false;
    }

    public void add(Integer dat) {
        if (!this.IsStoped) {
            try {
                this.mWaveQueue.put(dat);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setVisibility(boolean b) {
        if (b) {
            this.mSurfaceView.setVisibility(0);
        } else {
            this.mSurfaceView.setVisibility(8);
        }
    }
}
