package com.db.chart.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.Shader.TileMode;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import com.db.chart.model.ChartSet;
import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.util.Tools;
import com.db.chart.view.ChartView.Orientation;
import com.db.williamchart.C1047R;
import java.util.ArrayList;
import java.util.Iterator;

public class LineChartView extends ChartView {
    private static final float SMOOTH_FACTOR = 0.15f;
    private float mClickableRadius;
    private final Style mStyle;

    class Style {
        static final int FULL_ALPHA = 255;
        private Paint mDotsPaint;
        private Paint mDotsStrokePaint;
        private Paint mFillPaint;
        private Paint mLinePaint;

        Style() {
        }

        Style(TypedArray attrs) {
        }

        private void init() {
            this.mDotsPaint = new Paint();
            this.mDotsPaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
            this.mDotsPaint.setAntiAlias(true);
            this.mDotsStrokePaint = new Paint();
            this.mDotsStrokePaint.setStyle(android.graphics.Paint.Style.STROKE);
            this.mDotsStrokePaint.setAntiAlias(true);
            this.mLinePaint = new Paint();
            this.mLinePaint.setStyle(android.graphics.Paint.Style.STROKE);
            this.mLinePaint.setAntiAlias(true);
            this.mFillPaint = new Paint();
            this.mFillPaint.setStyle(android.graphics.Paint.Style.FILL);
        }

        private void clean() {
            this.mLinePaint = null;
            this.mFillPaint = null;
            this.mDotsPaint = null;
        }
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(Orientation.VERTICAL);
        this.mStyle = new Style(context.getTheme().obtainStyledAttributes(attrs, C1047R.styleable.ChartAttrs, 0, 0));
        this.mClickableRadius = context.getResources().getDimension(C1047R.dimen.dot_region_radius);
    }

    public LineChartView(Context context) {
        super(context);
        setOrientation(Orientation.VERTICAL);
        this.mStyle = new Style();
        this.mClickableRadius = context.getResources().getDimension(C1047R.dimen.dot_region_radius);
    }

    private static int si(int setSize, int i) {
        if (i > setSize - 1) {
            return setSize - 1;
        }
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mStyle.init();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mStyle.clean();
    }

    public void onDrawChart(Canvas canvas, ArrayList<ChartSet> data) {
        Iterator it = data.iterator();
        while (it.hasNext()) {
            LineSet lineSet = (LineSet) ((ChartSet) it.next());
            if (lineSet.isVisible()) {
                Path linePath;
                this.mStyle.mLinePaint.setColor(lineSet.getColor());
                this.mStyle.mLinePaint.setStrokeWidth(lineSet.getThickness());
                applyShadow(this.mStyle.mLinePaint, lineSet.getAlpha(), lineSet.getShadowDx(), lineSet.getShadowDy(), lineSet.getShadowRadius(), lineSet.getShadowColor());
                if (lineSet.isDashed()) {
                    this.mStyle.mLinePaint.setPathEffect(new DashPathEffect(lineSet.getDashedIntervals(), (float) lineSet.getDashedPhase()));
                } else {
                    this.mStyle.mLinePaint.setPathEffect(null);
                }
                if (lineSet.isSmooth()) {
                    linePath = createSmoothLinePath(lineSet);
                } else {
                    linePath = createLinePath(lineSet);
                }
                if (lineSet.hasFill() || lineSet.hasGradientFill()) {
                    canvas.drawPath(createBackgroundPath(new Path(linePath), lineSet), this.mStyle.mFillPaint);
                }
                canvas.drawPath(linePath, this.mStyle.mLinePaint);
                drawPoints(canvas, lineSet);
            }
        }
    }

    void defineRegions(ArrayList<ArrayList<Region>> regions, ArrayList<ChartSet> data) {
        int dataSize = data.size();
        for (int i = 0; i < dataSize; i++) {
            int setSize = ((ChartSet) data.get(0)).size();
            for (int j = 0; j < setSize; j++) {
                float x = ((ChartSet) data.get(i)).getEntry(j).getX();
                float y = ((ChartSet) data.get(i)).getEntry(j).getY();
                ((Region) ((ArrayList) regions.get(i)).get(j)).set((int) (x - this.mClickableRadius), (int) (y - this.mClickableRadius), (int) (this.mClickableRadius + x), (int) (this.mClickableRadius + y));
            }
        }
    }

    private void drawPoints(Canvas canvas, LineSet set) {
        int begin = set.getBegin();
        int end = set.getEnd();
        for (int i = begin; i < end; i++) {
            Point dot = (Point) set.getEntry(i);
            if (dot.isVisible()) {
                this.mStyle.mDotsPaint.setColor(dot.getColor());
                Paint access$400 = this.mStyle.mDotsPaint;
                float alpha = set.getAlpha();
                com.db.chart.view.ChartView.Style style = this.style;
                access$400.setAlpha((int) (alpha * 255.0f));
                applyShadow(this.mStyle.mDotsPaint, set.getAlpha(), dot.getShadowDx(), dot.getShadowDy(), dot.getShadowRadius(), dot.getShadowColor());
                canvas.drawCircle(dot.getX(), dot.getY(), dot.getRadius(), this.mStyle.mDotsPaint);
                if (dot.hasStroke()) {
                    this.mStyle.mDotsStrokePaint.setStrokeWidth(dot.getStrokeThickness());
                    this.mStyle.mDotsStrokePaint.setColor(dot.getStrokeColor());
                    access$400 = this.mStyle.mDotsStrokePaint;
                    alpha = set.getAlpha();
                    style = this.style;
                    access$400.setAlpha((int) (alpha * 255.0f));
                    applyShadow(this.mStyle.mDotsStrokePaint, set.getAlpha(), dot.getShadowDx(), dot.getShadowDy(), dot.getShadowRadius(), dot.getShadowColor());
                    canvas.drawCircle(dot.getX(), dot.getY(), dot.getRadius(), this.mStyle.mDotsStrokePaint);
                }
                if (dot.getDrawable() != null) {
                    Bitmap dotsBitmap = Tools.drawableToBitmap(dot.getDrawable());
                    canvas.drawBitmap(dotsBitmap, dot.getX() - ((float) (dotsBitmap.getWidth() / 2)), dot.getY() - ((float) (dotsBitmap.getHeight() / 2)), this.mStyle.mDotsPaint);
                }
            }
        }
    }

    Path createLinePath(LineSet set) {
        Path res = new Path();
        int begin = set.getBegin();
        int end = set.getEnd();
        for (int i = begin; i < end; i++) {
            if (i == begin) {
                res.moveTo(set.getEntry(i).getX(), set.getEntry(i).getY());
            } else {
                res.lineTo(set.getEntry(i).getX(), set.getEntry(i).getY());
            }
        }
        return res;
    }

    Path createSmoothLinePath(LineSet set) {
        Path res = new Path();
        res.moveTo(set.getEntry(set.getBegin()).getX(), set.getEntry(set.getBegin()).getY());
        int begin = set.getBegin();
        int end = set.getEnd();
        for (int i = begin; i < end - 1; i++) {
            float thisPointX = set.getEntry(i).getX();
            float thisPointY = set.getEntry(i).getY();
            float nextPointX = set.getEntry(i + 1).getX();
            float nextPointY = set.getEntry(i + 1).getY();
            res.cubicTo(thisPointX + (SMOOTH_FACTOR * (nextPointX - set.getEntry(si(set.size(), i - 1)).getX())), thisPointY + (SMOOTH_FACTOR * (nextPointY - set.getEntry(si(set.size(), i - 1)).getY())), nextPointX - (SMOOTH_FACTOR * (set.getEntry(si(set.size(), i + 2)).getX() - thisPointX)), nextPointY - (SMOOTH_FACTOR * (set.getEntry(si(set.size(), i + 2)).getY() - thisPointY)), nextPointX, nextPointY);
        }
        return res;
    }

    private Path createBackgroundPath(Path path, LineSet set) {
        Paint access$300 = this.mStyle.mFillPaint;
        float alpha = set.getAlpha();
        com.db.chart.view.ChartView.Style style = this.style;
        access$300.setAlpha((int) (alpha * 255.0f));
        if (set.hasFill()) {
            this.mStyle.mFillPaint.setColor(set.getFillColor());
        }
        if (set.hasGradientFill()) {
            this.mStyle.mFillPaint.setShader(new LinearGradient(super.getInnerChartLeft(), super.getInnerChartTop(), super.getInnerChartLeft(), super.getInnerChartBottom(), set.getGradientColors(), set.getGradientPositions(), TileMode.MIRROR));
        }
        path.lineTo(set.getEntry(set.getEnd() - 1).getX(), super.getInnerChartBottom());
        path.lineTo(set.getEntry(set.getBegin()).getX(), super.getInnerChartBottom());
        path.close();
        return path;
    }

    public LineChartView setClickablePointRadius(@FloatRange(from = 0.0d) float radius) {
        this.mClickableRadius = radius;
        return this;
    }
}
