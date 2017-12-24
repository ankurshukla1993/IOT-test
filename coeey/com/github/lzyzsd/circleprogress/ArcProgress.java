package com.github.lzyzsd.circleprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class ArcProgress extends View {
    private static final String INSTANCE_ARC_ANGLE = "arc_angle";
    private static final String INSTANCE_BOTTOM_TEXT = "bottom_text";
    private static final String INSTANCE_BOTTOM_TEXT_SIZE = "bottom_text_size";
    private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PROGRESS = "progress";
    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_STROKE_WIDTH = "stroke_width";
    private static final String INSTANCE_SUFFIX = "suffix";
    private static final String INSTANCE_SUFFIX_TEXT_PADDING = "suffix_text_padding";
    private static final String INSTANCE_SUFFIX_TEXT_SIZE = "suffix_text_size";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_TEXT_SIZE = "text_size";
    private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
    private float arcAngle;
    private float arcBottomHeight;
    private String bottomText;
    private float bottomTextSize;
    private final float default_arc_angle;
    private final float default_bottom_text_size;
    private final int default_finished_color;
    private final int default_max;
    private final float default_stroke_width;
    private final float default_suffix_padding;
    private final String default_suffix_text;
    private final float default_suffix_text_size;
    private final int default_text_color;
    private float default_text_size;
    private final int default_unfinished_color;
    private int finishedStrokeColor;
    private int max;
    private final int min_size;
    private Paint paint;
    private int progress;
    private RectF rectF;
    private float strokeWidth;
    private String suffixText;
    private float suffixTextPadding;
    private float suffixTextSize;
    private int textColor;
    protected Paint textPaint;
    private float textSize;
    private int unfinishedStrokeColor;

    public ArcProgress(Context context) {
        this(context, null);
    }

    public ArcProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.rectF = new RectF();
        this.progress = 0;
        this.suffixText = "%";
        this.default_finished_color = -1;
        this.default_unfinished_color = Color.rgb(72, 106, 176);
        this.default_text_color = Color.rgb(66, 145, 241);
        this.default_max = 100;
        this.default_arc_angle = 288.0f;
        this.default_text_size = Utils.sp2px(getResources(), 18.0f);
        this.min_size = (int) Utils.dp2px(getResources(), 100.0f);
        this.default_text_size = Utils.sp2px(getResources(), 40.0f);
        this.default_suffix_text_size = Utils.sp2px(getResources(), 15.0f);
        this.default_suffix_padding = Utils.dp2px(getResources(), 4.0f);
        this.default_suffix_text = "%";
        this.default_bottom_text_size = Utils.sp2px(getResources(), 10.0f);
        this.default_stroke_width = Utils.dp2px(getResources(), 4.0f);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, C1480R.styleable.ArcProgress, defStyleAttr, 0);
        initByAttributes(attributes);
        attributes.recycle();
        initPainters();
    }

    protected void initByAttributes(TypedArray attributes) {
        this.finishedStrokeColor = attributes.getColor(C1480R.styleable.ArcProgress_arc_finished_color, -1);
        this.unfinishedStrokeColor = attributes.getColor(C1480R.styleable.ArcProgress_arc_unfinished_color, this.default_unfinished_color);
        this.textColor = attributes.getColor(C1480R.styleable.ArcProgress_arc_text_color, this.default_text_color);
        this.textSize = attributes.getDimension(C1480R.styleable.ArcProgress_arc_text_size, this.default_text_size);
        this.arcAngle = attributes.getFloat(C1480R.styleable.ArcProgress_arc_angle, 288.0f);
        setMax(attributes.getInt(C1480R.styleable.ArcProgress_arc_max, 100));
        setProgress(attributes.getInt(C1480R.styleable.ArcProgress_arc_progress, 0));
        this.strokeWidth = attributes.getDimension(C1480R.styleable.ArcProgress_arc_stroke_width, this.default_stroke_width);
        this.suffixTextSize = attributes.getDimension(C1480R.styleable.ArcProgress_arc_suffix_text_size, this.default_suffix_text_size);
        this.suffixText = TextUtils.isEmpty(attributes.getString(C1480R.styleable.ArcProgress_arc_suffix_text)) ? this.default_suffix_text : attributes.getString(C1480R.styleable.ArcProgress_arc_suffix_text);
        this.suffixTextPadding = attributes.getDimension(C1480R.styleable.ArcProgress_arc_suffix_text_padding, this.default_suffix_padding);
        this.bottomTextSize = attributes.getDimension(C1480R.styleable.ArcProgress_arc_bottom_text_size, this.default_bottom_text_size);
        this.bottomText = attributes.getString(C1480R.styleable.ArcProgress_arc_bottom_text);
    }

    protected void initPainters() {
        this.textPaint = new TextPaint();
        this.textPaint.setColor(this.textColor);
        this.textPaint.setTextSize(this.textSize);
        this.textPaint.setAntiAlias(true);
        this.paint = new Paint();
        this.paint.setColor(this.default_unfinished_color);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(this.strokeWidth);
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeCap(Cap.ROUND);
    }

    public void invalidate() {
        initPainters();
        super.invalidate();
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        invalidate();
    }

    public float getSuffixTextSize() {
        return this.suffixTextSize;
    }

    public void setSuffixTextSize(float suffixTextSize) {
        this.suffixTextSize = suffixTextSize;
        invalidate();
    }

    public String getBottomText() {
        return this.bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
        invalidate();
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

    public float getBottomTextSize() {
        return this.bottomTextSize;
    }

    public void setBottomTextSize(float bottomTextSize) {
        this.bottomTextSize = bottomTextSize;
        invalidate();
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

    public int getFinishedStrokeColor() {
        return this.finishedStrokeColor;
    }

    public void setFinishedStrokeColor(int finishedStrokeColor) {
        this.finishedStrokeColor = finishedStrokeColor;
        invalidate();
    }

    public int getUnfinishedStrokeColor() {
        return this.unfinishedStrokeColor;
    }

    public void setUnfinishedStrokeColor(int unfinishedStrokeColor) {
        this.unfinishedStrokeColor = unfinishedStrokeColor;
        invalidate();
    }

    public float getArcAngle() {
        return this.arcAngle;
    }

    public void setArcAngle(float arcAngle) {
        this.arcAngle = arcAngle;
        invalidate();
    }

    public String getSuffixText() {
        return this.suffixText;
    }

    public void setSuffixText(String suffixText) {
        this.suffixText = suffixText;
        invalidate();
    }

    public float getSuffixTextPadding() {
        return this.suffixTextPadding;
    }

    public void setSuffixTextPadding(float suffixTextPadding) {
        this.suffixTextPadding = suffixTextPadding;
        invalidate();
    }

    protected int getSuggestedMinimumHeight() {
        return this.min_size;
    }

    protected int getSuggestedMinimumWidth() {
        return this.min_size;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        this.rectF.set(this.strokeWidth / 2.0f, this.strokeWidth / 2.0f, ((float) width) - (this.strokeWidth / 2.0f), ((float) MeasureSpec.getSize(heightMeasureSpec)) - (this.strokeWidth / 2.0f));
        this.arcBottomHeight = ((float) (1.0d - Math.cos(((double) (((360.0f - this.arcAngle) / 2.0f) / 180.0f)) * 3.141592653589793d))) * (((float) width) / 2.0f);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float startAngle = 270.0f - (this.arcAngle / 2.0f);
        float finishedSweepAngle = (((float) this.progress) / ((float) getMax())) * this.arcAngle;
        float finishedStartAngle = startAngle;
        if (this.progress == 0) {
            finishedStartAngle = 0.01f;
        }
        this.paint.setColor(this.unfinishedStrokeColor);
        canvas.drawArc(this.rectF, startAngle, this.arcAngle, false, this.paint);
        this.paint.setColor(this.finishedStrokeColor);
        canvas.drawArc(this.rectF, finishedStartAngle, finishedSweepAngle, false, this.paint);
        String text = String.valueOf(getProgress());
        if (!TextUtils.isEmpty(text)) {
            this.textPaint.setColor(this.textColor);
            this.textPaint.setTextSize(this.textSize);
            float textHeight = this.textPaint.descent() + this.textPaint.ascent();
            float textBaseline = (((float) getHeight()) - textHeight) / 2.0f;
            canvas.drawText(text, (((float) getWidth()) - this.textPaint.measureText(text)) / 2.0f, textBaseline, this.textPaint);
            this.textPaint.setTextSize(this.suffixTextSize);
            float suffixHeight = this.textPaint.descent() + this.textPaint.ascent();
            canvas.drawText(this.suffixText, ((((float) getWidth()) / 2.0f) + this.textPaint.measureText(text)) + this.suffixTextPadding, (textBaseline + textHeight) - suffixHeight, this.textPaint);
        }
        if (this.arcBottomHeight == 0.0f) {
            this.arcBottomHeight = ((float) (1.0d - Math.cos(((double) (((360.0f - this.arcAngle) / 2.0f) / 180.0f)) * 3.141592653589793d))) * (((float) getWidth()) / 2.0f);
        }
        if (!TextUtils.isEmpty(getBottomText())) {
            this.textPaint.setTextSize(this.bottomTextSize);
            float bottomTextBaseline = (((float) getHeight()) - this.arcBottomHeight) - ((this.textPaint.descent() + this.textPaint.ascent()) / 2.0f);
            canvas.drawText(getBottomText(), (((float) getWidth()) - this.textPaint.measureText(getBottomText())) / 2.0f, bottomTextBaseline, this.textPaint);
        }
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putFloat(INSTANCE_STROKE_WIDTH, getStrokeWidth());
        bundle.putFloat(INSTANCE_SUFFIX_TEXT_SIZE, getSuffixTextSize());
        bundle.putFloat(INSTANCE_SUFFIX_TEXT_PADDING, getSuffixTextPadding());
        bundle.putFloat(INSTANCE_BOTTOM_TEXT_SIZE, getBottomTextSize());
        bundle.putString(INSTANCE_BOTTOM_TEXT, getBottomText());
        bundle.putFloat(INSTANCE_TEXT_SIZE, getTextSize());
        bundle.putInt(INSTANCE_TEXT_COLOR, getTextColor());
        bundle.putInt("progress", getProgress());
        bundle.putInt(INSTANCE_MAX, getMax());
        bundle.putInt(INSTANCE_FINISHED_STROKE_COLOR, getFinishedStrokeColor());
        bundle.putInt(INSTANCE_UNFINISHED_STROKE_COLOR, getUnfinishedStrokeColor());
        bundle.putFloat(INSTANCE_ARC_ANGLE, getArcAngle());
        bundle.putString(INSTANCE_SUFFIX, getSuffixText());
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.strokeWidth = bundle.getFloat(INSTANCE_STROKE_WIDTH);
            this.suffixTextSize = bundle.getFloat(INSTANCE_SUFFIX_TEXT_SIZE);
            this.suffixTextPadding = bundle.getFloat(INSTANCE_SUFFIX_TEXT_PADDING);
            this.bottomTextSize = bundle.getFloat(INSTANCE_BOTTOM_TEXT_SIZE);
            this.bottomText = bundle.getString(INSTANCE_BOTTOM_TEXT);
            this.textSize = bundle.getFloat(INSTANCE_TEXT_SIZE);
            this.textColor = bundle.getInt(INSTANCE_TEXT_COLOR);
            setMax(bundle.getInt(INSTANCE_MAX));
            setProgress(bundle.getInt("progress"));
            this.finishedStrokeColor = bundle.getInt(INSTANCE_FINISHED_STROKE_COLOR);
            this.unfinishedStrokeColor = bundle.getInt(INSTANCE_UNFINISHED_STROKE_COLOR);
            this.suffixText = bundle.getString(INSTANCE_SUFFIX);
            initPainters();
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
