package com.hadiidbouk.charts;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.react.uimanager.ViewDefaults;
import com.google.android.flexbox.FlexItem;
import java.util.ArrayList;
import java.util.Iterator;

public class ChartProgressBar extends LinearLayout {
    static final /* synthetic */ boolean $assertionsDisabled = (!ChartProgressBar.class.desiredAssertionStatus());
    private OnClickListener barClickListener = new C20181();
    private boolean isBarCanBeClick;
    private boolean isBarsEmpty;
    private boolean isOldBarClicked;
    private int mBarHeight;
    private int mBarRadius;
    private int mBarTitleColor;
    private float mBarTitleTxtSize;
    private int mBarWidth;
    private Context mContext;
    private ArrayList<BarData> mDataList;
    private int mEmptyColor;
    private float mMaxValue;
    private DisplayMetrics mMetrics;
    private int mPinBackgroundColor;
    private int mPinMarginTop;
    private int mPinPadding;
    private int mPinTextColor;
    private float mPinTxtSize;
    private int mProgressClickColor;
    private int mProgressColor;
    private FrameLayout oldFrameLayout;

    class C20181 implements OnClickListener {
        C20181() {
        }

        public void onClick(View view) {
            if (!ChartProgressBar.this.isBarsEmpty) {
                FrameLayout frameLayout = (FrameLayout) view;
                if (ChartProgressBar.this.oldFrameLayout != frameLayout) {
                    if (ChartProgressBar.this.oldFrameLayout != null) {
                        ChartProgressBar.this.clickBarOff(ChartProgressBar.this.oldFrameLayout);
                    }
                    ChartProgressBar.this.clickBarOn(frameLayout);
                } else if (ChartProgressBar.this.isOldBarClicked) {
                    ChartProgressBar.this.clickBarOff(frameLayout);
                } else {
                    ChartProgressBar.this.clickBarOn(frameLayout);
                }
                ChartProgressBar.this.oldFrameLayout = frameLayout;
            }
        }
    }

    public ChartProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setAttrs(attrs, 0);
        this.mMetrics = Resources.getSystem().getDisplayMetrics();
    }

    public ChartProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setAttrs(attrs, defStyleAttr);
        this.mMetrics = Resources.getSystem().getDisplayMetrics();
    }

    private void setAttrs(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = this.mContext.obtainStyledAttributes(attrs, C2019R.styleable.ChartProgressBar, defStyleAttr, 0);
        this.mBarWidth = typedArray.getDimensionPixelSize(C2019R.styleable.ChartProgressBar_hdBarWidth, 0);
        this.mBarHeight = typedArray.getDimensionPixelSize(C2019R.styleable.ChartProgressBar_hdBarHeight, 0);
        this.mBarRadius = typedArray.getDimensionPixelSize(C2019R.styleable.ChartProgressBar_hdBarRadius, 0);
        this.mEmptyColor = typedArray.getResourceId(C2019R.styleable.ChartProgressBar_hdEmptyColor, ContextCompat.getColor(this.mContext, C2019R.color.empty));
        this.mProgressColor = typedArray.getResourceId(C2019R.styleable.ChartProgressBar_hdProgressColor, ContextCompat.getColor(this.mContext, C2019R.color.progress));
        this.mProgressClickColor = typedArray.getResourceId(C2019R.styleable.ChartProgressBar_hdProgressClickColor, ContextCompat.getColor(this.mContext, C2019R.color.progress_click));
        this.mPinTextColor = typedArray.getResourceId(C2019R.styleable.ChartProgressBar_hdPinTextColor, ContextCompat.getColor(this.mContext, C2019R.color.pin_text));
        this.mPinBackgroundColor = typedArray.getResourceId(C2019R.styleable.ChartProgressBar_hdPinBackgroundColor, ContextCompat.getColor(this.mContext, C2019R.color.pin_background));
        this.mPinPadding = typedArray.getDimensionPixelSize(C2019R.styleable.ChartProgressBar_hdPinPadding, 3);
        this.isBarCanBeClick = typedArray.getBoolean(C2019R.styleable.ChartProgressBar_hdBarCanBeClick, false);
        this.mBarTitleColor = typedArray.getResourceId(C2019R.styleable.ChartProgressBar_hdBarTitleColor, ContextCompat.getColor(this.mContext, C2019R.color.bar_title_color));
        this.mMaxValue = typedArray.getFloat(C2019R.styleable.ChartProgressBar_hdMaxValue, FlexItem.FLEX_SHRINK_DEFAULT);
        this.mBarTitleTxtSize = typedArray.getDimension(C2019R.styleable.ChartProgressBar_hdBarTitleTxtSize, ViewDefaults.FONT_SIZE_SP);
        this.mPinTxtSize = typedArray.getDimension(C2019R.styleable.ChartProgressBar_hdPinTxtSize, ViewDefaults.FONT_SIZE_SP);
        this.mPinMarginTop = typedArray.getDimensionPixelSize(C2019R.styleable.ChartProgressBar_hdPinMarginTop, 0);
        typedArray.recycle();
    }

    public void setDataList(ArrayList<BarData> dataList) {
        this.mDataList = dataList;
    }

    public void build() {
        removeAllViews();
        Iterator it = this.mDataList.iterator();
        while (it.hasNext()) {
            BarData data = (BarData) it.next();
            addView(getBar(data.getBarTitle(), (int) (data.getBarValue() * 100.0f), data.getPinText()));
        }
    }

    private FrameLayout getBar(String title, int value, String pinTxt) {
        int maxValue = (int) (this.mMaxValue * 100.0f);
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        Bar bar = new Bar(this.mContext, null, 16842872);
        bar.setProgress(value);
        bar.setVisibility(0);
        bar.setIndeterminate(false);
        bar.setMax(maxValue);
        bar.setProgressDrawable(ContextCompat.getDrawable(this.mContext, C2019R.drawable.progress_bar_shape));
        layoutParams = new LinearLayout.LayoutParams(this.mBarWidth, this.mBarHeight);
        layoutParams.gravity = 17;
        bar.setLayoutParams(layoutParams);
        BarAnimation anim = new BarAnimation(bar, 0.0f, (float) value);
        anim.setDuration(250);
        bar.startAnimation(anim);
        LayerDrawable layerDrawable = (LayerDrawable) bar.getProgressDrawable();
        layerDrawable.mutate();
        GradientDrawable emptyLayer = (GradientDrawable) layerDrawable.getDrawable(0);
        ScaleDrawable scaleDrawable = (ScaleDrawable) layerDrawable.getDrawable(1);
        emptyLayer.setColor(ContextCompat.getColor(this.mContext, this.mEmptyColor));
        emptyLayer.setCornerRadius((float) this.mBarRadius);
        GradientDrawable progressLayer = (GradientDrawable) scaleDrawable.getDrawable();
        if ($assertionsDisabled || progressLayer != null) {
            progressLayer.setColor(ContextCompat.getColor(this.mContext, this.mProgressColor));
            progressLayer.setCornerRadius((float) this.mBarRadius);
            linearLayout.addView(bar);
            View textView = new TextView(this.mContext);
            new LinearLayout.LayoutParams(-2, -2).setMargins(0, getDPI(15), 0, 0);
            textView.setTextSize(getSP(this.mBarTitleTxtSize));
            textView.setText(title);
            textView.setGravity(17);
            textView.setTextColor(ContextCompat.getColor(this.mContext, this.mBarTitleColor));
            linearLayout.addView(textView);
            FrameLayout frameLayout = new FrameLayout(this.mContext);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1, FlexItem.FLEX_SHRINK_DEFAULT);
            layoutParams2.gravity = 17;
            frameLayout.setLayoutParams(layoutParams2);
            frameLayout.addView(linearLayout);
            textView = new TextView(this.mContext);
            LayoutParams valueParams = new FrameLayout.LayoutParams(-2, -2);
            valueParams.gravity = 81;
            textView.setBackgroundResource(C2019R.drawable.pin_shape);
            int padding = getDPI(3);
            textView.setPadding(padding * 2, padding, padding * 2, padding * 2);
            if (this.mPinPadding != 0) {
                int pinPadding = this.mPinPadding;
                textView.setPadding(pinPadding * 2, pinPadding, pinPadding * 2, pinPadding * 2);
            }
            Rect bounds = new Rect();
            textView.setText(pinTxt);
            textView.setMaxLines(1);
            textView.setTextSize(getSP(this.mPinTxtSize));
            textView.setGravity(17);
            textView.getPaint().getTextBounds(pinTxt, 0, pinTxt.length(), bounds);
            layoutParams = valueParams;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            layoutParams.setMargins(i, i2, i3, (((this.mBarHeight * value) / maxValue) + getDPI(bounds.height() / 2)) - this.mPinMarginTop);
            textView.setLayoutParams(valueParams);
            textView.setVisibility(4);
            textView.setTextColor(ContextCompat.getColor(this.mContext, 17170443));
            int color = this.mPinTextColor;
            if (color != 0) {
                textView.setTextColor(ContextCompat.getColor(this.mContext, color));
            }
            int backgroundColor = this.mPinBackgroundColor;
            if (backgroundColor != 0) {
                textView.getBackground().setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(this.mContext, backgroundColor), Mode.SRC_ATOP));
            }
            frameLayout.addView(textView);
            if (this.isBarCanBeClick) {
                frameLayout.setOnClickListener(this.barClickListener);
            }
            return frameLayout;
        }
        throw new AssertionError();
    }

    public void setMaxValue(float mMaxValue) {
        this.mMaxValue = mMaxValue;
    }

    private int getDPI(int size) {
        return (this.mMetrics.densityDpi * size) / 160;
    }

    private float getSP(float size) {
        return size / this.mMetrics.scaledDensity;
    }

    public boolean isBarsEmpty() {
        return this.isBarsEmpty;
    }

    private void clickBarOn(FrameLayout frameLayout) {
        this.isOldBarClicked = true;
        int childCount = frameLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = frameLayout.getChildAt(i);
            if (childView instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) childView;
                TextView titleTxtView = (TextView) linearLayout.getChildAt(1);
                LayerDrawable layerDrawable = (LayerDrawable) ((Bar) linearLayout.getChildAt(0)).getProgressDrawable();
                layerDrawable.mutate();
                GradientDrawable progressLayer = (GradientDrawable) ((ScaleDrawable) layerDrawable.getDrawable(1)).getDrawable();
                if (!$assertionsDisabled && progressLayer == null) {
                    throw new AssertionError();
                } else if (this.mPinBackgroundColor != 0) {
                    progressLayer.setColor(ContextCompat.getColor(this.mContext, this.mProgressClickColor));
                    titleTxtView.setTextColor(ContextCompat.getColor(this.mContext, this.mProgressClickColor));
                } else {
                    progressLayer.setColor(ContextCompat.getColor(this.mContext, 17170453));
                    titleTxtView.setTextColor(ContextCompat.getColor(this.mContext, 17170453));
                }
            } else {
                ((TextView) childView).setVisibility(0);
            }
        }
    }

    private void clickBarOff(FrameLayout frameLayout) {
        this.isOldBarClicked = false;
        int childCount = frameLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = frameLayout.getChildAt(i);
            if (childView instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) childView;
                TextView titleTxtView = (TextView) linearLayout.getChildAt(1);
                LayerDrawable layerDrawable = (LayerDrawable) ((Bar) linearLayout.getChildAt(0)).getProgressDrawable();
                layerDrawable.mutate();
                GradientDrawable progressLayer = (GradientDrawable) ((ScaleDrawable) layerDrawable.getDrawable(1)).getDrawable();
                if ($assertionsDisabled || progressLayer != null) {
                    progressLayer.setColor(ContextCompat.getColor(this.mContext, this.mProgressColor));
                    titleTxtView.setTextColor(ContextCompat.getColor(this.mContext, this.mBarTitleColor));
                } else {
                    throw new AssertionError();
                }
            }
            ((TextView) childView).setVisibility(4);
        }
    }

    public ArrayList<BarData> getData() {
        return this.mDataList;
    }

    public void removeBarValues() {
        if (this.oldFrameLayout != null) {
            removeClickedBar();
        }
        int barsCount = getChildCount();
        for (int i = 0; i < barsCount; i++) {
            FrameLayout rootFrame = (FrameLayout) getChildAt(i);
            int rootChildCount = rootFrame.getChildCount();
            for (int j = 0; j < rootChildCount; j++) {
                View childView = rootFrame.getChildAt(j);
                if (childView instanceof LinearLayout) {
                    LinearLayout barContainerLinear = (LinearLayout) childView;
                    int barContainerCount = barContainerLinear.getChildCount();
                    for (int k = 0; k < barContainerCount; k++) {
                        View view = barContainerLinear.getChildAt(j);
                        if (view instanceof Bar) {
                            ((Bar) view).setProgress(0);
                        }
                    }
                }
            }
        }
        this.isBarsEmpty = true;
    }

    public void removeClickedBar() {
        clickBarOff(this.oldFrameLayout);
    }

    public void resetBarValues() {
        if (this.oldFrameLayout != null) {
            removeClickedBar();
        }
        int barsCount = getChildCount();
        for (int i = 0; i < barsCount; i++) {
            FrameLayout rootFrame = (FrameLayout) getChildAt(i);
            int rootChildCount = rootFrame.getChildCount();
            for (int j = 0; j < rootChildCount; j++) {
                View childView = rootFrame.getChildAt(j);
                if (childView instanceof LinearLayout) {
                    LinearLayout barContainerLinear = (LinearLayout) childView;
                    int barContainerCount = barContainerLinear.getChildCount();
                    for (int k = 0; k < barContainerCount; k++) {
                        View view = barContainerLinear.getChildAt(j);
                        if (view instanceof Bar) {
                            ((Bar) view).setProgress((int) (((BarData) this.mDataList.get(i)).getBarValue() * 100.0f));
                        }
                    }
                }
            }
        }
        this.isBarsEmpty = false;
    }
}
