package com.github.lzyzsd.circleprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.common.primitives.Ints;
import com.ihealth.communication.manager.iHealthDevicesManager.ScanDevice;

public class DonutProgress extends View {
    private static final String INSTANCE_BACKGROUND_COLOR = "inner_background_color";
    private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
    private static final String INSTANCE_FINISHED_STROKE_WIDTH = "finished_stroke_width";
    private static final String INSTANCE_INNER_BOTTOM_TEXT = "inner_bottom_text";
    private static final String INSTANCE_INNER_BOTTOM_TEXT_COLOR = "inner_bottom_text_color";
    private static final String INSTANCE_INNER_BOTTOM_TEXT_SIZE = "inner_bottom_text_size";
    private static final String INSTANCE_INNER_DRAWABLE = "inner_drawable";
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PREFIX = "prefix";
    private static final String INSTANCE_PROGRESS = "progress";
    private static final String INSTANCE_STARTING_DEGREE = "starting_degree";
    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_SUFFIX = "suffix";
    private static final String INSTANCE_TEXT = "text";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_TEXT_SIZE = "text_size";
    private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
    private static final String INSTANCE_UNFINISHED_STROKE_WIDTH = "unfinished_stroke_width";
    private int attributeResourceId;
    private final int default_finished_color;
    private final int default_inner_background_color;
    private final int default_inner_bottom_text_color;
    private final float default_inner_bottom_text_size;
    private final int default_max;
    private final int default_startingDegree;
    private final float default_stroke_width;
    private final int default_text_color;
    private final float default_text_size;
    private final int default_unfinished_color;
    private RectF finishedOuterRect;
    private Paint finishedPaint;
    private int finishedStrokeColor;
    private float finishedStrokeWidth;
    private int innerBackgroundColor;
    private String innerBottomText;
    private int innerBottomTextColor;
    private float innerBottomTextHeight;
    protected Paint innerBottomTextPaint;
    private float innerBottomTextSize;
    private Paint innerCirclePaint;
    private int max;
    private final int min_size;
    private String prefixText;
    private float progress;
    private boolean showText;
    private int startingDegree;
    private String suffixText;
    private String text;
    private int textColor;
    protected Paint textPaint;
    private float textSize;
    private RectF unfinishedOuterRect;
    private Paint unfinishedPaint;
    private int unfinishedStrokeColor;
    private float unfinishedStrokeWidth;

    public DonutProgress(Context context) {
        this(context, null);
    }

    public DonutProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DonutProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.finishedOuterRect = new RectF();
        this.unfinishedOuterRect = new RectF();
        this.attributeResourceId = 0;
        this.progress = 0.0f;
        this.prefixText = "";
        this.suffixText = "%";
        this.text = null;
        this.default_finished_color = Color.rgb(66, 145, 241);
        this.default_unfinished_color = Color.rgb(ScanDevice.LINK_USB, ScanDevice.LINK_USB, ScanDevice.LINK_USB);
        this.default_text_color = Color.rgb(66, 145, 241);
        this.default_inner_bottom_text_color = Color.rgb(66, 145, 241);
        this.default_inner_background_color = 0;
        this.default_max = 100;
        this.default_startingDegree = 0;
        this.default_text_size = Utils.sp2px(getResources(), 18.0f);
        this.min_size = (int) Utils.dp2px(getResources(), 100.0f);
        this.default_stroke_width = Utils.dp2px(getResources(), 10.0f);
        this.default_inner_bottom_text_size = Utils.sp2px(getResources(), 18.0f);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, C1480R.styleable.DonutProgress, defStyleAttr, 0);
        initByAttributes(attributes);
        attributes.recycle();
        initPainters();
    }

    protected void initPainters() {
        if (this.showText) {
            this.textPaint = new TextPaint();
            this.textPaint.setColor(this.textColor);
            this.textPaint.setTextSize(this.textSize);
            this.textPaint.setAntiAlias(true);
            this.innerBottomTextPaint = new TextPaint();
            this.innerBottomTextPaint.setColor(this.innerBottomTextColor);
            this.innerBottomTextPaint.setTextSize(this.innerBottomTextSize);
            this.innerBottomTextPaint.setAntiAlias(true);
        }
        this.finishedPaint = new Paint();
        this.finishedPaint.setColor(this.finishedStrokeColor);
        this.finishedPaint.setStyle(Style.STROKE);
        this.finishedPaint.setAntiAlias(true);
        this.finishedPaint.setStrokeWidth(this.finishedStrokeWidth);
        this.unfinishedPaint = new Paint();
        this.unfinishedPaint.setColor(this.unfinishedStrokeColor);
        this.unfinishedPaint.setStyle(Style.STROKE);
        this.unfinishedPaint.setAntiAlias(true);
        this.unfinishedPaint.setStrokeWidth(this.unfinishedStrokeWidth);
        this.innerCirclePaint = new Paint();
        this.innerCirclePaint.setColor(this.innerBackgroundColor);
        this.innerCirclePaint.setAntiAlias(true);
    }

    protected void initByAttributes(TypedArray attributes) {
        this.finishedStrokeColor = attributes.getColor(C1480R.styleable.DonutProgress_donut_finished_color, this.default_finished_color);
        this.unfinishedStrokeColor = attributes.getColor(C1480R.styleable.DonutProgress_donut_unfinished_color, this.default_unfinished_color);
        this.showText = attributes.getBoolean(C1480R.styleable.DonutProgress_donut_show_text, true);
        this.attributeResourceId = attributes.getResourceId(C1480R.styleable.DonutProgress_donut_inner_drawable, 0);
        setMax(attributes.getInt(C1480R.styleable.DonutProgress_donut_max, 100));
        setProgress(attributes.getFloat(C1480R.styleable.DonutProgress_donut_progress, 0.0f));
        this.finishedStrokeWidth = attributes.getDimension(C1480R.styleable.DonutProgress_donut_finished_stroke_width, this.default_stroke_width);
        this.unfinishedStrokeWidth = attributes.getDimension(C1480R.styleable.DonutProgress_donut_unfinished_stroke_width, this.default_stroke_width);
        if (this.showText) {
            if (attributes.getString(C1480R.styleable.DonutProgress_donut_prefix_text) != null) {
                this.prefixText = attributes.getString(C1480R.styleable.DonutProgress_donut_prefix_text);
            }
            if (attributes.getString(C1480R.styleable.DonutProgress_donut_suffix_text) != null) {
                this.suffixText = attributes.getString(C1480R.styleable.DonutProgress_donut_suffix_text);
            }
            if (attributes.getString(C1480R.styleable.DonutProgress_donut_text) != null) {
                this.text = attributes.getString(C1480R.styleable.DonutProgress_donut_text);
            }
            this.textColor = attributes.getColor(C1480R.styleable.DonutProgress_donut_text_color, this.default_text_color);
            this.textSize = attributes.getDimension(C1480R.styleable.DonutProgress_donut_text_size, this.default_text_size);
            this.innerBottomTextSize = attributes.getDimension(C1480R.styleable.DonutProgress_donut_inner_bottom_text_size, this.default_inner_bottom_text_size);
            this.innerBottomTextColor = attributes.getColor(C1480R.styleable.DonutProgress_donut_inner_bottom_text_color, this.default_inner_bottom_text_color);
            this.innerBottomText = attributes.getString(C1480R.styleable.DonutProgress_donut_inner_bottom_text);
        }
        this.innerBottomTextSize = attributes.getDimension(C1480R.styleable.DonutProgress_donut_inner_bottom_text_size, this.default_inner_bottom_text_size);
        this.innerBottomTextColor = attributes.getColor(C1480R.styleable.DonutProgress_donut_inner_bottom_text_color, this.default_inner_bottom_text_color);
        this.innerBottomText = attributes.getString(C1480R.styleable.DonutProgress_donut_inner_bottom_text);
        this.startingDegree = attributes.getInt(C1480R.styleable.DonutProgress_donut_circle_starting_degree, 0);
        this.innerBackgroundColor = attributes.getColor(C1480R.styleable.DonutProgress_donut_background_color, 0);
    }

    public void invalidate() {
        initPainters();
        super.invalidate();
    }

    public boolean isShowText() {
        return this.showText;
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
    }

    public float getFinishedStrokeWidth() {
        return this.finishedStrokeWidth;
    }

    public void setFinishedStrokeWidth(float finishedStrokeWidth) {
        this.finishedStrokeWidth = finishedStrokeWidth;
        invalidate();
    }

    public float getUnfinishedStrokeWidth() {
        return this.unfinishedStrokeWidth;
    }

    public void setUnfinishedStrokeWidth(float unfinishedStrokeWidth) {
        this.unfinishedStrokeWidth = unfinishedStrokeWidth;
        invalidate();
    }

    private float getProgressAngle() {
        return (getProgress() / ((float) this.max)) * 360.0f;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        if (this.progress > ((float) getMax())) {
            this.progress %= (float) getMax();
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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    public String getSuffixText() {
        return this.suffixText;
    }

    public void setSuffixText(String suffixText) {
        this.suffixText = suffixText;
        invalidate();
    }

    public String getPrefixText() {
        return this.prefixText;
    }

    public void setPrefixText(String prefixText) {
        this.prefixText = prefixText;
        invalidate();
    }

    public int getInnerBackgroundColor() {
        return this.innerBackgroundColor;
    }

    public void setInnerBackgroundColor(int innerBackgroundColor) {
        this.innerBackgroundColor = innerBackgroundColor;
        invalidate();
    }

    public String getInnerBottomText() {
        return this.innerBottomText;
    }

    public void setInnerBottomText(String innerBottomText) {
        this.innerBottomText = innerBottomText;
        invalidate();
    }

    public float getInnerBottomTextSize() {
        return this.innerBottomTextSize;
    }

    public void setInnerBottomTextSize(float innerBottomTextSize) {
        this.innerBottomTextSize = innerBottomTextSize;
        invalidate();
    }

    public int getInnerBottomTextColor() {
        return this.innerBottomTextColor;
    }

    public void setInnerBottomTextColor(int innerBottomTextColor) {
        this.innerBottomTextColor = innerBottomTextColor;
        invalidate();
    }

    public int getStartingDegree() {
        return this.startingDegree;
    }

    public void setStartingDegree(int startingDegree) {
        this.startingDegree = startingDegree;
        invalidate();
    }

    public int getAttributeResourceId() {
        return this.attributeResourceId;
    }

    public void setAttributeResourceId(int attributeResourceId) {
        this.attributeResourceId = attributeResourceId;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
        this.innerBottomTextHeight = (float) (getHeight() - ((getHeight() * 3) / 4));
    }

    private int measure(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == Ints.MAX_POWER_OF_TWO) {
            return size;
        }
        int result = this.min_size;
        if (mode == Integer.MIN_VALUE) {
            return Math.min(result, size);
        }
        return result;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float delta = Math.max(this.finishedStrokeWidth, this.unfinishedStrokeWidth);
        this.finishedOuterRect.set(delta, delta, ((float) getWidth()) - delta, ((float) getHeight()) - delta);
        this.unfinishedOuterRect.set(delta, delta, ((float) getWidth()) - delta, ((float) getHeight()) - delta);
        canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, ((((float) getWidth()) - Math.min(this.finishedStrokeWidth, this.unfinishedStrokeWidth)) + Math.abs(this.finishedStrokeWidth - this.unfinishedStrokeWidth)) / 2.0f, this.innerCirclePaint);
        canvas.drawArc(this.finishedOuterRect, (float) getStartingDegree(), getProgressAngle(), false, this.finishedPaint);
        canvas.drawArc(this.unfinishedOuterRect, getProgressAngle() + ((float) getStartingDegree()), 360.0f - getProgressAngle(), false, this.unfinishedPaint);
        if (this.showText) {
            String text = this.text != null ? this.text : this.prefixText + this.progress + this.suffixText;
            if (!TextUtils.isEmpty(text)) {
                canvas.drawText(text, (((float) getWidth()) - this.textPaint.measureText(text)) / 2.0f, (((float) getWidth()) - (this.textPaint.descent() + this.textPaint.ascent())) / 2.0f, this.textPaint);
            }
            if (!TextUtils.isEmpty(getInnerBottomText())) {
                this.innerBottomTextPaint.setTextSize(this.innerBottomTextSize);
                canvas.drawText(getInnerBottomText(), (((float) getWidth()) - this.innerBottomTextPaint.measureText(getInnerBottomText())) / 2.0f, (((float) getHeight()) - this.innerBottomTextHeight) - ((this.textPaint.descent() + this.textPaint.ascent()) / 2.0f), this.innerBottomTextPaint);
            }
        }
        if (this.attributeResourceId != 0) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), this.attributeResourceId);
            canvas.drawBitmap(bitmap, ((float) (getWidth() - bitmap.getWidth())) / 2.0f, ((float) (getHeight() - bitmap.getHeight())) / 2.0f, null);
        }
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(INSTANCE_TEXT_COLOR, getTextColor());
        bundle.putFloat(INSTANCE_TEXT_SIZE, getTextSize());
        bundle.putFloat(INSTANCE_INNER_BOTTOM_TEXT_SIZE, getInnerBottomTextSize());
        bundle.putFloat(INSTANCE_INNER_BOTTOM_TEXT_COLOR, (float) getInnerBottomTextColor());
        bundle.putString(INSTANCE_INNER_BOTTOM_TEXT, getInnerBottomText());
        bundle.putInt(INSTANCE_INNER_BOTTOM_TEXT_COLOR, getInnerBottomTextColor());
        bundle.putInt(INSTANCE_FINISHED_STROKE_COLOR, getFinishedStrokeColor());
        bundle.putInt(INSTANCE_UNFINISHED_STROKE_COLOR, getUnfinishedStrokeColor());
        bundle.putInt(INSTANCE_MAX, getMax());
        bundle.putInt(INSTANCE_STARTING_DEGREE, getStartingDegree());
        bundle.putFloat("progress", getProgress());
        bundle.putString(INSTANCE_SUFFIX, getSuffixText());
        bundle.putString(INSTANCE_PREFIX, getPrefixText());
        bundle.putString("text", getText());
        bundle.putFloat(INSTANCE_FINISHED_STROKE_WIDTH, getFinishedStrokeWidth());
        bundle.putFloat(INSTANCE_UNFINISHED_STROKE_WIDTH, getUnfinishedStrokeWidth());
        bundle.putInt(INSTANCE_BACKGROUND_COLOR, getInnerBackgroundColor());
        bundle.putInt(INSTANCE_INNER_DRAWABLE, getAttributeResourceId());
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.textColor = bundle.getInt(INSTANCE_TEXT_COLOR);
            this.textSize = bundle.getFloat(INSTANCE_TEXT_SIZE);
            this.innerBottomTextSize = bundle.getFloat(INSTANCE_INNER_BOTTOM_TEXT_SIZE);
            this.innerBottomText = bundle.getString(INSTANCE_INNER_BOTTOM_TEXT);
            this.innerBottomTextColor = bundle.getInt(INSTANCE_INNER_BOTTOM_TEXT_COLOR);
            this.finishedStrokeColor = bundle.getInt(INSTANCE_FINISHED_STROKE_COLOR);
            this.unfinishedStrokeColor = bundle.getInt(INSTANCE_UNFINISHED_STROKE_COLOR);
            this.finishedStrokeWidth = bundle.getFloat(INSTANCE_FINISHED_STROKE_WIDTH);
            this.unfinishedStrokeWidth = bundle.getFloat(INSTANCE_UNFINISHED_STROKE_WIDTH);
            this.innerBackgroundColor = bundle.getInt(INSTANCE_BACKGROUND_COLOR);
            this.attributeResourceId = bundle.getInt(INSTANCE_INNER_DRAWABLE);
            initPainters();
            setMax(bundle.getInt(INSTANCE_MAX));
            setStartingDegree(bundle.getInt(INSTANCE_STARTING_DEGREE));
            setProgress(bundle.getFloat("progress"));
            this.prefixText = bundle.getString(INSTANCE_PREFIX);
            this.suffixText = bundle.getString(INSTANCE_SUFFIX);
            this.text = bundle.getString("text");
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void setDonut_progress(String percent) {
        if (!TextUtils.isEmpty(percent)) {
            setProgress((float) Integer.parseInt(percent));
        }
    }
}
