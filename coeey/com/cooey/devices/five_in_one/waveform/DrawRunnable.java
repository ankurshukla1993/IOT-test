package com.cooey.devices.five_in_one.waveform;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.cooey.devices.C0910R;
import java.util.concurrent.LinkedBlockingQueue;

public class DrawRunnable implements Runnable {
    private int STROKE_WIDTH = 3;
    private int WAVEFORM_PADDING = 20;
    private Context mContext;
    private Paint mPaint = new Paint();
    private LinkedBlockingQueue<Integer> mQueue;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private WaveFormParams mWaveParams;

    public DrawRunnable(Context context, LinkedBlockingQueue<Integer> queue, SurfaceView surfaceView, SurfaceHolder surfaceHolder, WaveFormParams waveParams) {
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeWidth((float) this.STROKE_WIDTH);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeCap(Cap.ROUND);
        this.mPaint.setStyle(Style.STROKE);
        this.mQueue = queue;
        this.mSurfaceHolder = surfaceHolder;
        this.mSurfaceView = surfaceView;
        this.mWaveParams = waveParams;
        this.mContext = context;
    }

    public void run() {
        int temp = 0;
        Point oldPoint = new Point(this.WAVEFORM_PADDING + 1, this.WAVEFORM_PADDING);
        Point newPoint = new Point(this.WAVEFORM_PADDING + 1, this.WAVEFORM_PADDING);
        Point prevOldPoint = new Point(this.WAVEFORM_PADDING + 1, this.WAVEFORM_PADDING);
        int[] tempArray = new int[5];
        Path mPath = new Path();
        float rangeCoef = (float) (1.0d / ((double) (this.mWaveParams.getValueRange()[1] - this.mWaveParams.getValueRange()[0])));
        while (true) {
            int counter;
            for (counter = 0; counter < this.mWaveParams.getBufferCounter(); counter++) {
                try {
                    temp = ((Integer) this.mQueue.take()).intValue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tempArray[counter] = temp;
            }
            synchronized (this) {
                Canvas mCanvas = this.mSurfaceHolder.lockCanvas(new Rect(oldPoint.x, this.WAVEFORM_PADDING, oldPoint.x + (this.STROKE_WIDTH * 8), (this.mSurfaceView.getHeight() - this.WAVEFORM_PADDING) + 1));
                if (mCanvas != null) {
                    mCanvas.drawColor(this.mContext.getResources().getColor(C0910R.color.colorPrimary));
                    mPath.reset();
                    for (counter = 0; counter < this.mWaveParams.getBufferCounter(); counter++) {
                        newPoint.x = oldPoint.x + this.mWaveParams.getxStep();
                        if (newPoint.x > this.mSurfaceView.getWidth() - this.WAVEFORM_PADDING) {
                            newPoint.x = this.WAVEFORM_PADDING;
                        }
                        newPoint.y = (this.mSurfaceView.getHeight() - this.WAVEFORM_PADDING) - ((int) ((((float) tempArray[counter]) * rangeCoef) * ((float) (this.mSurfaceView.getHeight() - (this.WAVEFORM_PADDING * 2)))));
                        mPath.moveTo((float) oldPoint.x, (float) oldPoint.y);
                        mPath.cubicTo((float) (oldPoint.x + ((oldPoint.x - prevOldPoint.x) / 2)), (float) (oldPoint.y + ((oldPoint.y - prevOldPoint.y) / 2)), (float) (oldPoint.x + ((newPoint.x - oldPoint.x) / 2)), (float) (oldPoint.y + ((newPoint.y - oldPoint.y) / 2)), (float) newPoint.x, (float) newPoint.y);
                        prevOldPoint.x = oldPoint.x;
                        prevOldPoint.y = oldPoint.y;
                        oldPoint.x = newPoint.x;
                        oldPoint.y = newPoint.y;
                    }
                    mCanvas.drawPath(mPath, this.mPaint);
                    mCanvas.save();
                    this.mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        }
    }
}
