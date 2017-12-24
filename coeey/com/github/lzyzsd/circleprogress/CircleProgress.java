package com.github.lzyzsd.circleprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.ihealth.communication.manager.iHealthDevicesManager.ScanDevice;

public class CircleProgress extends View {
    private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PREFIX = "prefix";
    private static final String INSTANCE_PROGRESS = "progress";
    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_SUFFIX = "suffix";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_TEXT_SIZE = "text_size";
    private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
    private final int default_finished_color;
    private final int default_max;
    private final int default_text_color;
    private final float default_text_size;
    private final int default_unfinished_color;
    private int finishedColor;
    private int max;
    private final int min_size;
    private Paint paint;
    private String prefixText;
    private int progress;
    private RectF rectF;
    private String suffixText;
    private int textColor;
    private Paint textPaint;
    private float textSize;
    private int unfinishedColor;

    public CircleProgress(Context context) {
        this(context, null);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.rectF = new RectF();
        this.progress = 0;
        this.prefixText = "";
        this.suffixText = "%";
        this.default_finished_color = Color.rgb(66, 145, 241);
        this.default_unfinished_color = Color.rgb(ScanDevice.LINK_USB, ScanDevice.LINK_USB, ScanDevice.LINK_USB);
        this.default_text_color = -1;
        this.default_max = 100;
        this.paint = new Paint();
        this.default_text_size = Utils.sp2px(getResources(), 18.0f);
        this.min_size = (int) Utils.dp2px(getResources(), 100.0f);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, C1480R.styleable.CircleProgress, defStyleAttr, 0);
        initByAttributes(attributes);
        attributes.recycle();
        initPainters();
    }

    protected void initByAttributes(TypedArray attributes) {
        this.finishedColor = attributes.getColor(C1480R.styleable.CircleProgress_circle_finished_color, this.default_finished_color);
        this.unfinishedColor = attributes.getColor(C1480R.styleable.CircleProgress_circle_unfinished_color, this.default_unfinished_color);
        this.textColor = attributes.getColor(C1480R.styleable.CircleProgress_circle_text_color, -1);
        this.textSize = attributes.getDimension(C1480R.styleable.CircleProgress_circle_text_size, this.default_text_size);
        setMax(attributes.getInt(C1480R.styleable.CircleProgress_circle_max, 100));
        setProgress(attributes.getInt(C1480R.styleable.CircleProgress_circle_progress, 0));
        if (attributes.getString(C1480R.styleable.CircleProgress_circle_prefix_text) != null) {
            setPrefixText(attributes.getString(C1480R.styleable.CircleProgress_circle_prefix_text));
        }
        if (attributes.getString(C1480R.styleable.CircleProgress_circle_suffix_text) != null) {
            setSuffixText(attributes.getString(C1480R.styleable.CircleProgress_circle_suffix_text));
        }
    }

    protected void initPainters() {
        this.textPaint = new TextPaint();
        this.textPaint.setColor(this.textColor);
        this.textPaint.setTextSize(this.textSize);
        this.textPaint.setAntiAlias(true);
        this.paint.setAntiAlias(true);
    }

    public void invalidate() {
        initPainters();
        super.invalidate();
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        if (this.progress > getMax()) {
            this.progress %= getMax();
        }
        invalidate();
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        if (max > 0) {
            this.max = max;
            invalidate();
        }
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        invalidate();
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        invalidate();
    }

    public int getFinishedColor() {
        return this.finishedColor;
    }

    public void setFinishedColor(int finishedColor) {
        this.finishedColor = finishedColor;
        invalidate();
    }

    public int getUnfinishedColor() {
        return this.unfinishedColor;
    }

    public void setUnfinishedColor(int unfinishedColor) {
        this.unfinishedColor = unfinishedColor;
        invalidate();
    }

    public String getPrefixText() {
        return this.prefixText;
    }

    public void setPrefixText(String prefixText) {
        this.prefixText = prefixText;
        invalidate();
    }

    public String getSuffixText() {
        return this.suffixText;
    }

    public void setSuffixText(String suffixText) {
        this.suffixText = suffixText;
        invalidate();
    }

    public String getDrawText() {
        return getPrefixText() + getProgress() + getSuffixText();
    }

    protected int getSuggestedMinimumHeight() {
        return this.min_size;
    }

    protected int getSuggestedMinimumWidth() {
        return this.min_size;
    }

    public float getProgressPercentage() {
        return ((float) getProgress()) / ((float) getMax());
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.rectF.set(0.0f, 0.0f, (float) MeasureSpec.getSize(widthMeasureSpec), (float) MeasureSpec.getSize(heightMeasureSpec));
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        float radius = ((float) getWidth()) / 2.0f;
        float angle = (float) ((Math.acos((double) ((radius - ((((float) getProgress()) / ((float) getMax())) * ((float) getHeight()))) / radius)) * 180.0d) / 3.141592653589793d);
        float startAngle = 90.0f + angle;
        float sweepAngle = 360.0f - (2.0f * angle);
        this.paint.setColor(getUnfinishedColor());
        canvas.drawArc(this.rectF, startAngle, sweepAngle, false, this.paint);
        canvas.save();
        canvas.rotate(180.0f, (float) (getWidth() / 2), (float) (getHeight() / 2));
        this.paint.setColor(getFinishedColor());
        canvas.drawArc(this.rectF, 270.0f - angle, angle * 2.0f, false, this.paint);
        canvas.restore();
        String text = getDrawText();
        if (!TextUtils.isEmpty(text)) {
            canvas.drawText(text, (((float) getWidth()) - this.textPaint.measureText(text)) / 2.0f, (((float) getWidth()) - (this.textPaint.descent() + this.textPaint.ascent())) / 2.0f, this.textPaint);
        }
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(INSTANCE_TEXT_COLOR, getTextColor());
        bundle.putFloat(INSTANCE_TEXT_SIZE, getTextSize());
        bundle.putInt(INSTANCE_FINISHED_STROKE_COLOR, getFinishedColor());
        bundle.putInt(INSTANCE_UNFINISHED_STROKE_COLOR, getUnfinishedColor());
        bundle.putInt(INSTANCE_MAX, getMax());
        bundle.putInt("progress", getProgress());
        bundle.putString(INSTANCE_SUFFIX, getSuffixText());
        bundle.putString(INSTANCE_PREFIX, getPrefixText());
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.textColor = bundle.getInt(INSTANCE_TEXT_COLOR);
            this.textSize = bundle.getFloat(INSTANCE_TEXT_SIZE);
            this.finishedColor = bundle.getInt(INSTANCE_FINISHED_STROKE_COLOR);
            this.unfinishedColor = bundle.getInt(INSTANCE_UNFINISHED_STROKE_COLOR);
            initPainters();
            setMax(bundle.getInt(INSTANCE_MAX));
            setProgress(bundle.getInt("progress"));
            this.prefixText = bundle.getString(INSTANCE_PREFIX);
            this.suffixText = bundle.getString(INSTANCE_SUFFIX);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
