package com.db.chart.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.animation.DecelerateInterpolator;
import com.db.chart.model.ChartEntry;
import com.db.chart.model.ChartSet;
import com.db.chart.util.Preconditions;
import com.db.chart.view.ChartView;
import com.db.chart.view.ChartView.Orientation;
import com.google.android.flexbox.FlexItem;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Animation {
    private static final int DEFAULT_DURATION = 1000;
    private static final String TAG = "animation.Animation";
    private int mAlpha;
    private float mAnimateOverlapFactor;
    private final AnimatorListener mAnimatorListener = new C10371();
    private ArrayList<ValueAnimator> mAnimators;
    private ChartAnimationListener mCallback;
    private int mColor;
    private ArrayList<ChartSet> mData;
    private long mDuration;
    private Runnable mEndAction;
    private TimeInterpolator mInterpolator;
    private boolean mIsEntering;
    private int[] mOrder;
    private float mStartXFactor;
    private float mStartYFactor;

    class C10371 implements AnimatorListener {
        C10371() {
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            Animation.this.cancel();
            Animation.this.mAnimators.clear();
            if (Animation.this.mEndAction != null) {
                Animation.this.mEndAction.run();
            }
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    class C10382 implements AnimatorUpdateListener {
        C10382() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            Animation.this.mCallback.onAnimationUpdate(Animation.this.mData);
        }
    }

    public Animation() {
        init(1000);
    }

    public Animation(int duration) {
        init(duration);
    }

    private void init(int duration) {
        this.mAnimators = new ArrayList();
        this.mDuration = (long) duration;
        this.mAlpha = 1;
        this.mColor = -1;
        this.mInterpolator = new DecelerateInterpolator();
        this.mStartXFactor = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
        this.mStartYFactor = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
        this.mIsEntering = true;
        this.mAnimateOverlapFactor = FlexItem.FLEX_SHRINK_DEFAULT;
    }

    private ArrayList<ChartSet> prepareAnimation(ChartView chartView) {
        this.mData = chartView.getData();
        int nSets = this.mData.size();
        int nEntries = ((ChartSet) this.mData.get(0)).size();
        ArrayList<float[][]> startValues = new ArrayList(nSets);
        ArrayList<float[][]> endValues = new ArrayList(nSets);
        for (int i = 0; i < nSets; i++) {
            float[][] startCoords = (float[][]) Array.newInstance(Float.TYPE, new int[]{nEntries, 2});
            float[][] endCoords = ((ChartSet) this.mData.get(i)).getScreenPoints();
            for (int j = 0; j < nEntries; j++) {
                float y;
                startCoords[j][0] = chartView.getOrientation() == Orientation.VERTICAL ? ((ChartSet) this.mData.get(i)).getEntry(j).getX() : chartView.getZeroPosition();
                float[] fArr = startCoords[j];
                if (chartView.getOrientation() == Orientation.HORIZONTAL) {
                    y = ((ChartSet) this.mData.get(i)).getEntry(j).getY();
                } else {
                    y = chartView.getZeroPosition();
                }
                fArr[1] = y;
            }
            startValues.add(startCoords);
            endValues.add(endCoords);
        }
        startValues = applyStartingPosition(startValues, new Rect((int) chartView.getInnerChartLeft(), (int) chartView.getInnerChartTop(), (int) chartView.getInnerChartRight(), (int) chartView.getInnerChartBottom()), this.mStartXFactor, this.mStartYFactor);
        if (this.mIsEntering) {
            return animate(startValues, endValues);
        }
        return animate(endValues, startValues);
    }

    public ArrayList<ChartSet> prepareEnterAnimation(ChartView chartView) {
        this.mIsEntering = true;
        return prepareAnimation(chartView);
    }

    public ArrayList<ChartSet> prepareUpdateAnimation(ArrayList<float[][]> start, ArrayList<float[][]> end) {
        return animate(start, end);
    }

    public ArrayList<ChartSet> prepareExitAnimation(ChartView chartView) {
        this.mIsEntering = false;
        return prepareAnimation(chartView);
    }

    ArrayList<float[][]> applyStartingPosition(ArrayList<float[][]> values, Rect area, float xStartFactor, float yStartFactor) {
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < ((float[][]) values.get(i)).length; j++) {
                if (xStartFactor != FlexItem.FLEX_BASIS_PERCENT_DEFAULT) {
                    ((float[][]) values.get(i))[j][0] = ((float) area.left) + (((float) (area.right - area.left)) * xStartFactor);
                }
                if (yStartFactor != FlexItem.FLEX_BASIS_PERCENT_DEFAULT) {
                    ((float[][]) values.get(i))[j][1] = ((float) area.bottom) - (((float) (area.bottom - area.top)) * yStartFactor);
                }
            }
        }
        return values;
    }

    private ArrayList<ChartSet> animate(ArrayList<float[][]> start, ArrayList<float[][]> end) {
        ValueAnimator animator;
        this.mAnimators.addAll(animateEntries(start, end));
        Iterator it = this.mData.iterator();
        while (it.hasNext()) {
            ChartSet set = (ChartSet) it.next();
            animator = set.animateAlpha((float) this.mAlpha, set.getAlpha());
            animator.setDuration(this.mDuration);
            animator.setInterpolator(this.mInterpolator);
            this.mAnimators.add(animator);
        }
        if (this.mColor != -1 && VERSION.SDK_INT >= 21) {
            it = this.mData.iterator();
            while (it.hasNext()) {
                Iterator it2 = ((ChartSet) it.next()).getEntries().iterator();
                while (it2.hasNext()) {
                    ChartEntry entry = (ChartEntry) it2.next();
                    animator = entry.animateColor(this.mColor, entry.getColor());
                    animator.setDuration(this.mDuration);
                    animator.setInterpolator(this.mInterpolator);
                    this.mAnimators.add(animator);
                }
            }
        }
        long maxDelay = 0;
        it = this.mAnimators.iterator();
        while (it.hasNext()) {
            ValueAnimator e = (ValueAnimator) it.next();
            if (maxDelay < e.getStartDelay()) {
                maxDelay = e.getStartDelay();
            }
            e.start();
        }
        animator = ValueAnimator.ofInt(new int[]{0, 1});
        animator.addUpdateListener(new C10382());
        animator.addListener(this.mAnimatorListener);
        animator.setDuration(this.mDuration + maxDelay);
        animator.start();
        return this.mData;
    }

    private ArrayList<ValueAnimator> animateEntries(ArrayList<float[][]> start, ArrayList<float[][]> end) {
        int nSets = start.size();
        int nEntries = ((float[][]) start.get(0)).length;
        ArrayList<ValueAnimator> result = new ArrayList(nSets * nEntries);
        long duration = calculateEntriesDuration(nEntries, this.mDuration, this.mAnimateOverlapFactor);
        long[] delays = calculateEntriesInitTime(nEntries, this.mDuration, this.mAnimateOverlapFactor, this.mOrder);
        for (int i = 0; i < nSets; i++) {
            for (int j = 0; j < nEntries; j++) {
                ValueAnimator animator = ((ChartSet) this.mData.get(i)).getEntry(j).animateXY(((float[][]) start.get(i))[j][0], ((float[][]) start.get(i))[j][1], ((float[][]) end.get(i))[j][0], ((float[][]) end.get(i))[j][1]);
                animator.setStartDelay(delays[j]);
                animator.setDuration(duration);
                animator.setInterpolator(this.mInterpolator);
                result.add(animator);
            }
        }
        return result;
    }

    long[] calculateEntriesInitTime(int size, long duration, float overlapFactor, int[] order) {
        int i;
        if (overlapFactor != FlexItem.FLEX_SHRINK_DEFAULT) {
            duration = (long) (((float) duration) + (((float) duration) * overlapFactor));
        }
        if (order == null) {
            order = new int[size];
            for (i = 0; i < size; i++) {
                order[i] = i;
            }
        }
        long[] result = new long[size];
        for (i = 0; i < size; i++) {
            long noOverlapInitTime = ((long) i) * (duration / ((long) size));
            result[order[i]] = noOverlapInitTime - ((long) (((float) noOverlapInitTime) * overlapFactor));
        }
        return result;
    }

    long calculateEntriesDuration(int size, long duration, float overlapFactor) {
        float noOverlapDuration = (float) (duration / ((long) size));
        return (long) (((((float) duration) - noOverlapDuration) * overlapFactor) + noOverlapDuration);
    }

    public boolean isPlaying() {
        Iterator it = this.mAnimators.iterator();
        while (it.hasNext()) {
            if (((ValueAnimator) it.next()).isRunning()) {
                return true;
            }
        }
        return false;
    }

    public void cancel() {
        Iterator it = this.mAnimators.iterator();
        while (it.hasNext()) {
            ((ValueAnimator) it.next()).cancel();
        }
    }

    public Runnable getEndAction() {
        return this.mEndAction;
    }

    public Animation setInterpolator(@NonNull TimeInterpolator interpolator) {
        this.mInterpolator = (TimeInterpolator) Preconditions.checkNotNull(interpolator);
        return this;
    }

    public Animation setDuration(int duration) {
        this.mDuration = (long) duration;
        return this;
    }

    public Animation inSequence(@FloatRange(from = 0.0d, to = 1.0d) float factor, int[] order) {
        this.mOrder = order;
        inSequence(factor);
        return this;
    }

    public Animation inSequence(@FloatRange(from = 0.0d, to = 1.0d) float factor) {
        this.mAnimateOverlapFactor = factor;
        return this;
    }

    public Animation withEndAction(Runnable endAction) {
        this.mEndAction = endAction;
        return this;
    }

    public Animation fromXY(@FloatRange(from = -1.0d, to = 1.0d) float xFactor, @FloatRange(from = -1.0d, to = 1.0d) float yFactor) {
        this.mStartXFactor = xFactor;
        this.mStartYFactor = yFactor;
        return this;
    }

    public Animation fromAlpha(int alpha) {
        this.mAlpha = alpha;
        return this;
    }

    public Animation setAnimationListener(@NonNull ChartAnimationListener callback) {
        this.mCallback = (ChartAnimationListener) Preconditions.checkNotNull(callback);
        return this;
    }

    @RequiresApi(api = 21)
    public Animation fromColor(int color) {
        this.mColor = color;
        return this;
    }
}
