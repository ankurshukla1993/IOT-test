package com.db.chart.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.RelativeLayout;
import com.db.chart.animation.Animation;
import com.db.chart.animation.ChartAnimationListener;
import com.db.chart.listener.OnEntryClickListener;
import com.db.chart.model.ChartSet;
import com.db.chart.renderer.AxisRenderer;
import com.db.chart.renderer.AxisRenderer.LabelPosition;
import com.db.chart.renderer.XRenderer;
import com.db.chart.renderer.YRenderer;
import com.db.chart.tooltip.Tooltip;
import com.db.chart.util.Preconditions;
import com.db.williamchart.C1047R;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class ChartView extends RelativeLayout {
    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 200;
    private static final String TAG = "chart.view.ChartView";
    ArrayList<ChartSet> data;
    private final OnPreDrawListener drawListener = new C10431();
    private Animation mAnim;
    private ChartAnimationListener mAnimListener;
    private int mChartBottom;
    private int mChartLeft;
    private OnClickListener mChartListener;
    private int mChartRight;
    private int mChartTop;
    private OnEntryClickListener mEntryListener;
    private GestureDetector mGestureDetector;
    private boolean mIsDrawing;
    private Orientation mOrientation;
    private boolean mReadyToDraw;
    private ArrayList<ArrayList<Region>> mRegions;
    private ArrayList<Integer> mThresholdEndLabels;
    private ArrayList<Float> mThresholdEndValues;
    private ArrayList<Integer> mThresholdStartLabels;
    private ArrayList<Float> mThresholdStartValues;
    private Tooltip mTooltip;
    final Style style;
    final XRenderer xRndr;
    final YRenderer yRndr;

    class C10431 implements OnPreDrawListener {
        C10431() {
        }

        @SuppressLint({"NewApi"})
        public boolean onPreDraw() {
            int i;
            ChartView.this.getViewTreeObserver().removeOnPreDrawListener(this);
            ChartView.this.style.init();
            ChartView.this.yRndr.init(ChartView.this.data, ChartView.this.style);
            ChartView.this.xRndr.init(ChartView.this.data, ChartView.this.style);
            ChartView.this.mChartLeft = ChartView.this.getPaddingLeft();
            ChartView.this.mChartTop = ChartView.this.getPaddingTop() + (ChartView.this.style.fontMaxHeight / 2);
            ChartView.this.mChartRight = ChartView.this.getMeasuredWidth() - ChartView.this.getPaddingRight();
            ChartView.this.mChartBottom = ChartView.this.getMeasuredHeight() - ChartView.this.getPaddingBottom();
            ChartView.this.yRndr.measure(ChartView.this.mChartLeft, ChartView.this.mChartTop, ChartView.this.mChartRight, ChartView.this.mChartBottom);
            ChartView.this.xRndr.measure(ChartView.this.mChartLeft, ChartView.this.mChartTop, ChartView.this.mChartRight, ChartView.this.mChartBottom);
            float[] bounds = ChartView.this.negotiateInnerChartBounds(ChartView.this.yRndr.getInnerChartBounds(), ChartView.this.xRndr.getInnerChartBounds());
            ChartView.this.yRndr.setInnerChartBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
            ChartView.this.xRndr.setInnerChartBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
            ChartView.this.yRndr.dispose();
            ChartView.this.xRndr.dispose();
            if (!ChartView.this.mThresholdStartValues.isEmpty()) {
                for (i = 0; i < ChartView.this.mThresholdStartValues.size(); i++) {
                    ChartView.this.mThresholdStartValues.set(i, Float.valueOf(ChartView.this.yRndr.parsePos(0, (double) ((Float) ChartView.this.mThresholdStartValues.get(i)).floatValue())));
                    ChartView.this.mThresholdEndValues.set(i, Float.valueOf(ChartView.this.yRndr.parsePos(0, (double) ((Float) ChartView.this.mThresholdEndValues.get(i)).floatValue())));
                }
            }
            ChartView.this.digestData();
            ChartView.this.onPreDrawChart(ChartView.this.data);
            if (ChartView.this.mRegions.isEmpty()) {
                int dataSize = ChartView.this.data.size();
                ChartView.this.mRegions = new ArrayList(dataSize);
                for (i = 0; i < dataSize; i++) {
                    int setSize = ((ChartSet) ChartView.this.data.get(0)).size();
                    ArrayList<Region> regionSet = new ArrayList(setSize);
                    for (int j = 0; j < setSize; j++) {
                        regionSet.add(new Region());
                    }
                    ChartView.this.mRegions.add(regionSet);
                }
            }
            ChartView.this.defineRegions(ChartView.this.mRegions, ChartView.this.data);
            if (ChartView.this.mAnim != null) {
                ChartView.this.data = ChartView.this.mAnim.prepareEnterAnimation(ChartView.this);
            }
            if (VERSION.SDK_INT >= 11) {
                ChartView.this.setLayerType(1, null);
            }
            return ChartView.this.mReadyToDraw = true;
        }
    }

    class C10442 implements ChartAnimationListener {
        C10442() {
        }

        public boolean onAnimationUpdate(ArrayList<ChartSet> data) {
            if (ChartView.this.mIsDrawing) {
                return false;
            }
            ChartView.this.addData((ArrayList) data);
            ChartView.this.postInvalidate();
            return true;
        }
    }

    private class GestureListener extends SimpleOnGestureListener {
        private GestureListener() {
        }

        public boolean onSingleTapUp(MotionEvent ev) {
            if (!(ChartView.this.mEntryListener == null && ChartView.this.mTooltip == null)) {
                int nSets = ChartView.this.mRegions.size();
                int nEntries = ((ArrayList) ChartView.this.mRegions.get(0)).size();
                for (int i = 0; i < nSets; i++) {
                    for (int j = 0; j < nEntries; j++) {
                        if (((Region) ((ArrayList) ChartView.this.mRegions.get(i)).get(j)).contains((int) ev.getX(), (int) ev.getY())) {
                            if (ChartView.this.mEntryListener != null) {
                                ChartView.this.mEntryListener.onClick(i, j, ChartView.this.getEntryRect((Region) ((ArrayList) ChartView.this.mRegions.get(i)).get(j)));
                            }
                            if (ChartView.this.mTooltip != null) {
                                ChartView.this.toggleTooltip(ChartView.this.getEntryRect((Region) ((ArrayList) ChartView.this.mRegions.get(i)).get(j)), ((ChartSet) ChartView.this.data.get(i)).getValue(j));
                            }
                            return true;
                        }
                    }
                }
            }
            if (ChartView.this.mChartListener != null) {
                ChartView.this.mChartListener.onClick(ChartView.this);
            }
            if (ChartView.this.mTooltip != null && ChartView.this.mTooltip.on()) {
                ChartView.this.dismissTooltip(ChartView.this.mTooltip);
            }
            return true;
        }

        public boolean onDown(MotionEvent e) {
            return true;
        }
    }

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public class Style {
        private static final int DEFAULT_COLOR = -16777216;
        private static final int DEFAULT_GRID_OFF = 0;
        static final int FULL_ALPHA = 255;
        private int axisBorderSpacing;
        private int axisColor;
        private int axisLabelsSpacing;
        private float axisThickness;
        private int axisTopSpacing;
        private Paint chartPaint;
        private int fontMaxHeight;
        private float fontSize;
        private int gridColumns;
        private Paint gridPaint;
        private int gridRows;
        private boolean hasXAxis;
        private boolean hasYAxis;
        private Paint labelThresPaint;
        private int labelsColor;
        private DecimalFormat labelsFormat;
        private Paint labelsPaint;
        private Typeface typeface;
        private Paint valueThresPaint;
        private LabelPosition xLabelsPositioning;
        private LabelPosition yLabelsPositioning;

        Style(Context context) {
            this.axisColor = -16777216;
            this.axisThickness = context.getResources().getDimension(C1047R.dimen.grid_thickness);
            this.hasXAxis = true;
            this.hasYAxis = true;
            this.xLabelsPositioning = LabelPosition.OUTSIDE;
            this.yLabelsPositioning = LabelPosition.OUTSIDE;
            this.labelsColor = -16777216;
            this.fontSize = context.getResources().getDimension(C1047R.dimen.font_size);
            this.axisLabelsSpacing = context.getResources().getDimensionPixelSize(C1047R.dimen.axis_labels_spacing);
            this.axisBorderSpacing = context.getResources().getDimensionPixelSize(C1047R.dimen.axis_border_spacing);
            this.axisTopSpacing = context.getResources().getDimensionPixelSize(C1047R.dimen.axis_top_spacing);
            this.gridRows = 0;
            this.gridColumns = 0;
            this.labelsFormat = new DecimalFormat();
        }

        Style(Context context, AttributeSet attrs) {
            TypedArray arr = context.getTheme().obtainStyledAttributes(attrs, C1047R.styleable.ChartAttrs, 0, 0);
            this.hasXAxis = arr.getBoolean(C1047R.styleable.ChartAttrs_chart_axis, true);
            this.hasYAxis = arr.getBoolean(C1047R.styleable.ChartAttrs_chart_axis, true);
            this.axisColor = arr.getColor(C1047R.styleable.ChartAttrs_chart_axisColor, -16777216);
            this.axisThickness = arr.getDimension(C1047R.styleable.ChartAttrs_chart_axisThickness, context.getResources().getDimension(C1047R.dimen.axis_thickness));
            switch (arr.getInt(C1047R.styleable.ChartAttrs_chart_labels, 0)) {
                case 1:
                    this.xLabelsPositioning = LabelPosition.INSIDE;
                    this.yLabelsPositioning = LabelPosition.INSIDE;
                    break;
                case 2:
                    this.xLabelsPositioning = LabelPosition.NONE;
                    this.yLabelsPositioning = LabelPosition.NONE;
                    break;
                default:
                    this.xLabelsPositioning = LabelPosition.OUTSIDE;
                    this.yLabelsPositioning = LabelPosition.OUTSIDE;
                    break;
            }
            this.labelsColor = arr.getColor(C1047R.styleable.ChartAttrs_chart_labelColor, -16777216);
            this.fontSize = arr.getDimension(C1047R.styleable.ChartAttrs_chart_fontSize, context.getResources().getDimension(C1047R.dimen.font_size));
            String typefaceName = arr.getString(C1047R.styleable.ChartAttrs_chart_typeface);
            if (typefaceName != null) {
                this.typeface = Typeface.createFromAsset(ChartView.this.getResources().getAssets(), typefaceName);
            }
            this.axisLabelsSpacing = arr.getDimensionPixelSize(C1047R.styleable.ChartAttrs_chart_axisLabelsSpacing, context.getResources().getDimensionPixelSize(C1047R.dimen.axis_labels_spacing));
            this.axisBorderSpacing = arr.getDimensionPixelSize(C1047R.styleable.ChartAttrs_chart_axisBorderSpacing, context.getResources().getDimensionPixelSize(C1047R.dimen.axis_border_spacing));
            this.axisTopSpacing = arr.getDimensionPixelSize(C1047R.styleable.ChartAttrs_chart_axisTopSpacing, context.getResources().getDimensionPixelSize(C1047R.dimen.axis_top_spacing));
            this.gridRows = 0;
            this.gridColumns = 0;
            this.labelsFormat = new DecimalFormat();
        }

        private void init() {
            this.chartPaint = new Paint();
            this.chartPaint.setColor(this.axisColor);
            this.chartPaint.setStyle(android.graphics.Paint.Style.STROKE);
            this.chartPaint.setStrokeWidth(this.axisThickness);
            this.chartPaint.setAntiAlias(true);
            this.labelsPaint = new Paint();
            this.labelsPaint.setColor(this.labelsColor);
            this.labelsPaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
            this.labelsPaint.setAntiAlias(true);
            this.labelsPaint.setTextSize(this.fontSize);
            this.labelsPaint.setTypeface(this.typeface);
            this.fontMaxHeight = (int) (ChartView.this.style.labelsPaint.descent() - ChartView.this.style.labelsPaint.ascent());
        }

        private void clean() {
            this.chartPaint = null;
            this.labelsPaint = null;
        }

        public int getLabelHeight(String text) {
            Rect rect = new Rect();
            ChartView.this.style.labelsPaint.getTextBounds(text, 0, text.length(), rect);
            return rect.height();
        }

        public Paint getChartPaint() {
            return this.chartPaint;
        }

        public float getAxisThickness() {
            return this.axisThickness;
        }

        public boolean hasXAxis() {
            return this.hasXAxis;
        }

        public boolean hasYAxis() {
            return this.hasYAxis;
        }

        public Paint getLabelsPaint() {
            return this.labelsPaint;
        }

        public int getFontMaxHeight() {
            return this.fontMaxHeight;
        }

        public LabelPosition getXLabelsPositioning() {
            return this.xLabelsPositioning;
        }

        public LabelPosition getYLabelsPositioning() {
            return this.yLabelsPositioning;
        }

        public int getAxisLabelsSpacing() {
            return this.axisLabelsSpacing;
        }

        public int getAxisBorderSpacing() {
            return this.axisBorderSpacing;
        }

        public int getAxisTopSpacing() {
            return this.axisTopSpacing;
        }

        public DecimalFormat getLabelsFormat() {
            return this.labelsFormat;
        }

        private boolean hasHorizontalGrid() {
            return this.gridRows > 0;
        }

        private boolean hasVerticalGrid() {
            return this.gridColumns > 0;
        }
    }

    protected abstract void onDrawChart(Canvas canvas, ArrayList<ChartSet> arrayList);

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        this.mGestureDetector = new GestureDetector(context, new GestureListener());
        this.xRndr = new XRenderer();
        this.yRndr = new YRenderer();
        this.style = new Style(context, attrs);
    }

    public ChartView(Context context) {
        super(context);
        init();
        this.mGestureDetector = new GestureDetector(context, new GestureListener());
        this.xRndr = new XRenderer();
        this.yRndr = new YRenderer();
        this.style = new Style(context);
    }

    private void init() {
        this.mReadyToDraw = false;
        this.mThresholdStartValues = new ArrayList();
        this.mThresholdEndValues = new ArrayList();
        this.mThresholdStartLabels = new ArrayList();
        this.mThresholdEndLabels = new ArrayList();
        this.mIsDrawing = false;
        this.data = new ArrayList();
        this.mRegions = new ArrayList();
        this.mAnimListener = new C10442();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setWillNotDraw(false);
        this.style.init();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.style.clean();
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int tmpWidth = widthMeasureSpec;
        int tmpHeight = heightMeasureSpec;
        if (widthMode == Integer.MIN_VALUE) {
            tmpWidth = 200;
        }
        if (heightMode == Integer.MIN_VALUE) {
            tmpHeight = 100;
        }
        setMeasuredDimension(tmpWidth, tmpHeight);
    }

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        super.onTouchEvent(event);
        return (this.mAnim == null || !this.mAnim.isPlaying()) && (!(this.mEntryListener == null && this.mChartListener == null && this.mTooltip == null) && this.mGestureDetector.onTouchEvent(event));
    }

    protected void onDraw(Canvas canvas) {
        this.mIsDrawing = true;
        super.onDraw(canvas);
        if (this.mReadyToDraw) {
            int i;
            if (this.style.hasVerticalGrid()) {
                drawVerticalGrid(canvas);
            }
            if (this.style.hasHorizontalGrid()) {
                drawHorizontalGrid(canvas);
            }
            if (!this.mThresholdStartValues.isEmpty()) {
                for (i = 0; i < this.mThresholdStartValues.size(); i++) {
                    drawThreshold(canvas, getInnerChartLeft(), ((Float) this.mThresholdStartValues.get(i)).floatValue(), getInnerChartRight(), ((Float) this.mThresholdEndValues.get(i)).floatValue(), this.style.valueThresPaint);
                }
            }
            if (!this.mThresholdStartLabels.isEmpty()) {
                for (i = 0; i < this.mThresholdStartLabels.size(); i++) {
                    drawThreshold(canvas, ((ChartSet) this.data.get(0)).getEntry(((Integer) this.mThresholdStartLabels.get(i)).intValue()).getX(), getInnerChartTop(), ((ChartSet) this.data.get(0)).getEntry(((Integer) this.mThresholdEndLabels.get(i)).intValue()).getX(), getInnerChartBottom(), this.style.labelThresPaint);
                }
            }
            if (!this.data.isEmpty()) {
                onDrawChart(canvas, this.data);
            }
            this.yRndr.draw(canvas);
            this.xRndr.draw(canvas);
        }
        this.mIsDrawing = false;
    }

    private void digestData() {
        int nEntries = ((ChartSet) this.data.get(0)).size();
        Iterator it = this.data.iterator();
        while (it.hasNext()) {
            ChartSet set = (ChartSet) it.next();
            for (int i = 0; i < nEntries; i++) {
                set.getEntry(i).setCoordinates(this.xRndr.parsePos(i, (double) set.getValue(i)), this.yRndr.parsePos(i, (double) set.getValue(i)));
            }
        }
    }

    void onPreDrawChart(ArrayList<ChartSet> arrayList) {
    }

    void defineRegions(ArrayList<ArrayList<Region>> arrayList, ArrayList<ChartSet> arrayList2) {
    }

    public void addData(@NonNull ChartSet set) {
        Preconditions.checkNotNull(set);
        if (this.data.isEmpty() || set.size() == ((ChartSet) this.data.get(0)).size()) {
            this.data.add(set);
            return;
        }
        throw new IllegalArgumentException("The number of entries between sets doesn't match.");
    }

    public void addData(ArrayList<ChartSet> data) {
        this.data = data;
    }

    private void display() {
        getViewTreeObserver().addOnPreDrawListener(this.drawListener);
        postInvalidate();
    }

    public void show() {
        Iterator it = this.data.iterator();
        while (it.hasNext()) {
            ((ChartSet) it.next()).setVisible(true);
        }
        display();
    }

    public void show(int setIndex) {
        ((ChartSet) this.data.get(Preconditions.checkPositionIndex(setIndex, this.data.size()))).setVisible(true);
        display();
    }

    public void show(@NonNull Animation anim) {
        this.mAnim = (Animation) Preconditions.checkNotNull(anim);
        this.mAnim.setAnimationListener(this.mAnimListener);
        show();
    }

    public void dismiss() {
        dismiss(this.mAnim);
    }

    public void dismiss(int setIndex) {
        ((ChartSet) this.data.get(Preconditions.checkPositionIndex(setIndex, this.data.size()))).setVisible(false);
        invalidate();
    }

    public void dismiss(@NonNull Animation anim) {
        this.mAnim = (Animation) Preconditions.checkNotNull(anim);
        this.mAnim.setAnimationListener(this.mAnimListener);
        final Runnable endAction = this.mAnim.getEndAction();
        this.mAnim.withEndAction(new Runnable() {
            public void run() {
                if (endAction != null) {
                    endAction.run();
                }
                ChartView.this.data.clear();
                ChartView.this.invalidate();
            }
        });
        this.data = this.mAnim.prepareExitAnimation(this);
        invalidate();
    }

    public void reset() {
        if (this.mAnim != null && this.mAnim.isPlaying()) {
            this.mAnim.cancel();
        }
        init();
        this.xRndr.reset();
        this.yRndr.reset();
        setOrientation(this.mOrientation);
        this.style.labelThresPaint = null;
        this.style.valueThresPaint = null;
        this.style.gridPaint = null;
    }

    public ChartView updateValues(int setIndex, float[] values) {
        ((ChartSet) this.data.get(Preconditions.checkPositionIndex(setIndex, this.data.size()))).updateValues(values);
        return this;
    }

    public void notifyDataUpdate() {
        if ((this.mAnim == null || this.mAnim.isPlaying() || !this.mReadyToDraw) && !(this.mAnim == null && this.mReadyToDraw)) {
            Log.w(TAG, "Unexpected data update notification. Chart is still not displayed or still displaying.");
            return;
        }
        ArrayList<float[][]> oldCoords = new ArrayList(this.data.size());
        ArrayList<float[][]> newCoords = new ArrayList(this.data.size());
        Iterator it = this.data.iterator();
        while (it.hasNext()) {
            oldCoords.add(((ChartSet) it.next()).getScreenPoints());
        }
        digestData();
        it = this.data.iterator();
        while (it.hasNext()) {
            newCoords.add(((ChartSet) it.next()).getScreenPoints());
        }
        defineRegions(this.mRegions, this.data);
        if (this.mAnim != null) {
            this.mAnim.prepareUpdateAnimation(oldCoords, newCoords);
        } else {
            invalidate();
        }
    }

    private void toggleTooltip(@NonNull Rect rect, float value) {
        Preconditions.checkNotNull(rect);
        if (this.mTooltip.on()) {
            dismissTooltip(this.mTooltip, rect, value);
            return;
        }
        this.mTooltip.prepare(rect, value);
        showTooltip(this.mTooltip, true);
    }

    public void showTooltip(@NonNull Tooltip tooltip, boolean correctPos) {
        Preconditions.checkNotNull(tooltip);
        if (correctPos) {
            tooltip.correctPosition(this.mChartLeft, this.mChartTop, this.mChartRight, this.mChartBottom);
        }
        if (tooltip.hasEnterAnimation()) {
            tooltip.animateEnter();
        }
        addTooltip(tooltip);
    }

    private void addTooltip(@NonNull Tooltip tooltip) {
        Preconditions.checkNotNull(tooltip);
        addView(tooltip);
        tooltip.setOn(true);
    }

    private void removeTooltip(@NonNull Tooltip tooltip) {
        Preconditions.checkNotNull(tooltip);
        removeView(tooltip);
        tooltip.setOn(false);
    }

    private void dismissTooltip(@NonNull Tooltip tooltip) {
        dismissTooltip((Tooltip) Preconditions.checkNotNull(tooltip), null, 0.0f);
    }

    private void dismissTooltip(@NonNull final Tooltip tooltip, final Rect rect, final float value) {
        Preconditions.checkNotNull(tooltip);
        if (tooltip.hasExitAnimation()) {
            tooltip.animateExit(new Runnable() {
                public void run() {
                    ChartView.this.removeTooltip(tooltip);
                    if (rect != null) {
                        ChartView.this.toggleTooltip(rect, value);
                    }
                }
            });
            return;
        }
        removeTooltip(tooltip);
        if (rect != null) {
            toggleTooltip(rect, value);
        }
    }

    public void dismissAllTooltips() {
        removeAllViews();
        if (this.mTooltip != null) {
            this.mTooltip.setOn(false);
        }
    }

    public boolean canIPleaseAskYouToDraw() {
        return !this.mIsDrawing;
    }

    float[] negotiateInnerChartBounds(float[] innersA, float[] innersB) {
        float[] fArr = new float[4];
        fArr[0] = innersA[0] > innersB[0] ? innersA[0] : innersB[0];
        fArr[1] = innersA[1] > innersB[1] ? innersA[1] : innersB[1];
        fArr[2] = innersA[2] < innersB[2] ? innersA[2] : innersB[2];
        fArr[3] = innersA[3] < innersB[3] ? innersA[3] : innersB[3];
        return fArr;
    }

    private void drawThreshold(Canvas canvas, float left, float top, float right, float bottom, Paint paint) {
        if (left == right || top == bottom) {
            canvas.drawLine(left, top, right, bottom, paint);
        } else {
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }

    private void drawVerticalGrid(Canvas canvas) {
        float offset = (getInnerChartRight() - getInnerChartLeft()) / ((float) this.style.gridColumns);
        float marker = getInnerChartLeft();
        if (this.style.hasYAxis) {
            marker += offset;
        }
        while (marker < getInnerChartRight()) {
            canvas.drawLine(marker, getInnerChartTop(), marker, getInnerChartBottom(), this.style.gridPaint);
            marker += offset;
        }
        canvas.drawLine(getInnerChartRight(), getInnerChartTop(), getInnerChartRight(), getInnerChartBottom(), this.style.gridPaint);
    }

    private void drawHorizontalGrid(Canvas canvas) {
        float offset = (getInnerChartBottom() - getInnerChartTop()) / ((float) this.style.gridRows);
        for (float marker = getInnerChartTop(); marker < getInnerChartBottom(); marker += offset) {
            canvas.drawLine(getInnerChartLeft(), marker, getInnerChartRight(), marker, this.style.gridPaint);
        }
        if (!this.style.hasXAxis) {
            canvas.drawLine(getInnerChartLeft(), getInnerChartBottom(), getInnerChartRight(), getInnerChartBottom(), this.style.gridPaint);
        }
    }

    public Orientation getOrientation() {
        return this.mOrientation;
    }

    void setOrientation(@NonNull Orientation orien) {
        this.mOrientation = (Orientation) Preconditions.checkNotNull(orien);
        if (this.mOrientation == Orientation.VERTICAL) {
            this.yRndr.setHandleValues(true);
        } else {
            this.xRndr.setHandleValues(true);
        }
    }

    public float getInnerChartBottom() {
        return this.yRndr.getInnerChartBottom();
    }

    public float getInnerChartLeft() {
        return this.xRndr.getInnerChartLeft();
    }

    public float getInnerChartRight() {
        return this.xRndr.getInnerChartRight();
    }

    public float getInnerChartTop() {
        return this.yRndr.getInnerChartTop();
    }

    public float getZeroPosition() {
        AxisRenderer rndr;
        if (this.mOrientation == Orientation.VERTICAL) {
            rndr = this.yRndr;
        } else {
            rndr = this.xRndr;
        }
        if (rndr.getBorderMinimumValue() > 0.0f) {
            return rndr.parsePos(0, (double) rndr.getBorderMinimumValue());
        }
        if (rndr.getBorderMaximumValue() < 0.0f) {
            return rndr.parsePos(0, (double) rndr.getBorderMaximumValue());
        }
        return rndr.parsePos(0, 0.0d);
    }

    float getStep() {
        if (this.mOrientation == Orientation.VERTICAL) {
            return this.yRndr.getStep();
        }
        return this.xRndr.getStep();
    }

    public ChartView setStep(int step) {
        if (step <= 0) {
            throw new IllegalArgumentException("Step can't be lower or equal to 0");
        }
        if (this.mOrientation == Orientation.VERTICAL) {
            this.yRndr.setStep(step);
        } else {
            this.xRndr.setStep(step);
        }
        return this;
    }

    float getBorderSpacing() {
        return (float) this.style.axisBorderSpacing;
    }

    public ChartView setBorderSpacing(int spacing) {
        this.style.axisBorderSpacing = spacing;
        return this;
    }

    public ArrayList<ChartSet> getData() {
        return this.data;
    }

    public ArrayList<Rect> getEntriesArea(int index) {
        Preconditions.checkPositionIndex(index, this.mRegions.size());
        ArrayList<Rect> result = new ArrayList(((ArrayList) this.mRegions.get(index)).size());
        Iterator it = ((ArrayList) this.mRegions.get(index)).iterator();
        while (it.hasNext()) {
            result.add(getEntryRect((Region) it.next()));
        }
        return result;
    }

    Rect getEntryRect(@NonNull Region region) {
        Preconditions.checkNotNull(region);
        return new Rect(region.getBounds().left - getPaddingLeft(), region.getBounds().top - getPaddingTop(), region.getBounds().right - getPaddingLeft(), region.getBounds().bottom - getPaddingTop());
    }

    public Animation getChartAnimation() {
        return this.mAnim;
    }

    public ChartView setYLabels(@NonNull LabelPosition position) {
        this.style.yLabelsPositioning = (LabelPosition) Preconditions.checkNotNull(position);
        return this;
    }

    public ChartView setXLabels(@NonNull LabelPosition position) {
        this.style.xLabelsPositioning = (LabelPosition) Preconditions.checkNotNull(position);
        return this;
    }

    public ChartView setLabelsFormat(@NonNull DecimalFormat format) {
        this.style.labelsFormat = (DecimalFormat) Preconditions.checkNotNull(format);
        return this;
    }

    public ChartView setLabelsColor(@ColorInt int color) {
        this.style.labelsColor = color;
        return this;
    }

    public ChartView setFontSize(@IntRange(from = 0) int size) {
        this.style.fontSize = (float) size;
        return this;
    }

    public ChartView setTypeface(@NonNull Typeface typeface) {
        this.style.typeface = (Typeface) Preconditions.checkNotNull(typeface);
        return this;
    }

    public ChartView setXAxis(boolean bool) {
        this.style.hasXAxis = bool;
        return this;
    }

    public ChartView setYAxis(boolean bool) {
        this.style.hasYAxis = bool;
        return this;
    }

    public ChartView setAxisBorderValues(float minValue, float maxValue, float step) {
        if (this.mOrientation == Orientation.VERTICAL) {
            this.yRndr.setBorderValues(minValue, maxValue, step);
        } else {
            this.xRndr.setBorderValues(minValue, maxValue, step);
        }
        return this;
    }

    public ChartView setAxisBorderValues(float minValue, float maxValue) {
        if (this.mOrientation == Orientation.VERTICAL) {
            this.yRndr.setBorderValues(minValue, maxValue);
        } else {
            this.xRndr.setBorderValues(minValue, maxValue);
        }
        return this;
    }

    public ChartView setAxisThickness(@FloatRange(from = 0.0d) float thickness) {
        this.style.axisThickness = thickness;
        return this;
    }

    public ChartView setAxisColor(@ColorInt int color) {
        this.style.axisColor = color;
        return this;
    }

    public void setOnEntryClickListener(OnEntryClickListener listener) {
        this.mEntryListener = listener;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mChartListener = listener;
    }

    public ChartView setTopSpacing(int spacing) {
        this.style.axisTopSpacing = spacing;
        return this;
    }

    public ChartView setGrid(@IntRange(from = 0) int rows, @IntRange(from = 0) int columns, @NonNull Paint paint) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("Number of rows/columns can't be smaller than 0.");
        }
        this.style.gridRows = rows;
        this.style.gridColumns = columns;
        this.style.gridPaint = (Paint) Preconditions.checkNotNull(paint);
        return this;
    }

    public ChartView setValueThreshold(float startValue, float endValue, @NonNull Paint paint) {
        this.mThresholdStartValues.add(Float.valueOf(startValue));
        this.mThresholdEndValues.add(Float.valueOf(endValue));
        this.style.valueThresPaint = (Paint) Preconditions.checkNotNull(paint);
        return this;
    }

    public ChartView setValueThreshold(@NonNull float[] startValues, @NonNull float[] endValues, @NonNull Paint paint) {
        Preconditions.checkNotNull(startValues);
        Preconditions.checkNotNull(endValues);
        this.mThresholdStartValues.clear();
        this.mThresholdEndValues.clear();
        for (int i = 0; i < startValues.length; i++) {
            this.mThresholdStartValues.add(Float.valueOf(startValues[i]));
            this.mThresholdEndValues.add(Float.valueOf(endValues[i]));
        }
        this.style.valueThresPaint = (Paint) Preconditions.checkNotNull(paint);
        return this;
    }

    public ChartView setLabelThreshold(int startLabel, int endLabel, @NonNull Paint paint) {
        this.mThresholdStartLabels.add(Integer.valueOf(startLabel));
        this.mThresholdEndLabels.add(Integer.valueOf(endLabel));
        this.style.labelThresPaint = (Paint) Preconditions.checkNotNull(paint);
        return this;
    }

    public ChartView setLabelThreshold(@NonNull int[] startLabels, @NonNull int[] endLabels, @NonNull Paint paint) {
        Preconditions.checkNotNull(startLabels);
        Preconditions.checkNotNull(endLabels);
        this.mThresholdStartLabels.clear();
        this.mThresholdEndLabels.clear();
        for (int i = 0; i < startLabels.length; i++) {
            this.mThresholdStartLabels.add(Integer.valueOf(startLabels[i]));
            this.mThresholdEndLabels.add(Integer.valueOf(endLabels[i]));
        }
        this.style.labelThresPaint = (Paint) Preconditions.checkNotNull(paint);
        return this;
    }

    public ChartView setAxisLabelsSpacing(int spacing) {
        this.style.axisLabelsSpacing = spacing;
        return this;
    }

    void setMandatoryBorderSpacing() {
        if (this.mOrientation == Orientation.VERTICAL) {
            this.xRndr.setMandatoryBorderSpacing(true);
        } else {
            this.yRndr.setMandatoryBorderSpacing(true);
        }
    }

    public ChartView setTooltips(Tooltip tooltip) {
        this.mTooltip = tooltip;
        return this;
    }

    void setClickableRegions(ArrayList<ArrayList<Region>> regions) {
        this.mRegions = regions;
    }

    protected void applyShadow(Paint paint, float alpha, float dx, float dy, float radius, int[] color) {
        int i;
        Style style = this.style;
        paint.setAlpha((int) (alpha * 255.0f));
        style = this.style;
        if (((int) (alpha * 255.0f)) < color[0]) {
            style = this.style;
            i = (int) (alpha * 255.0f);
        } else {
            i = color[0];
        }
        paint.setShadowLayer(radius, dx, dy, Color.argb(i, color[1], color[2], color[3]));
    }
}
