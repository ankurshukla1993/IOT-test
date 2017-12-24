package com.github.vivchar.viewpagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.google.android.flexbox.FlexItem;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerIndicator extends LinearLayoutCompat {
    private static final int DEF_VALUE = 10;
    private static final int NO_SCALE = 1;
    private static final float SCALE = 1.6f;
    private int mDelimiterSize;
    @NonNull
    private final List<ImageView> mIndexImages;
    private int mItemSize;
    @Nullable
    private android.support.v4.view.ViewPager.OnPageChangeListener mListener;
    private int mPageCount;
    private int mSelectedIndex;

    private class OnPageChangeListener implements android.support.v4.view.ViewPager.OnPageChangeListener {
        private OnPageChangeListener() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (ViewPagerIndicator.this.mListener != null) {
                ViewPagerIndicator.this.mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        public void onPageSelected(int position) {
            ViewPagerIndicator.this.setSelectedIndex(position);
            if (ViewPagerIndicator.this.mListener != null) {
                ViewPagerIndicator.this.mListener.onPageSelected(position);
            }
        }

        public void onPageScrollStateChanged(int state) {
            if (ViewPagerIndicator.this.mListener != null) {
                ViewPagerIndicator.this.mListener.onPageScrollStateChanged(state);
            }
        }
    }

    public ViewPagerIndicator(@NonNull Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mItemSize = 10;
        this.mDelimiterSize = 10;
        this.mIndexImages = new ArrayList();
        setOrientation(0);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, C1508R.styleable.ViewPagerIndicator, 0, 0);
        try {
            this.mItemSize = attributes.getDimensionPixelSize(C1508R.styleable.ViewPagerIndicator_itemSize, 10);
            this.mDelimiterSize = attributes.getDimensionPixelSize(C1508R.styleable.ViewPagerIndicator_delimiterSize, 10);
            if (isInEditMode()) {
                createEditModeLayout();
            }
        } finally {
            attributes.recycle();
        }
    }

    private void createEditModeLayout() {
        for (int i = 0; i < 5; i++) {
            FrameLayout boxedItem = createBoxedItem(i);
            addView(boxedItem);
            if (i == 1) {
                View item = boxedItem.getChildAt(0);
                LayoutParams layoutParams = item.getLayoutParams();
                layoutParams.height = (int) (((float) layoutParams.height) * SCALE);
                layoutParams.width = (int) (((float) layoutParams.width) * SCALE);
                item.setLayoutParams(layoutParams);
            }
        }
    }

    public void setupWithViewPager(@NonNull ViewPager viewPager) {
        setPageCount(viewPager.getAdapter().getCount());
        viewPager.addOnPageChangeListener(new OnPageChangeListener());
    }

    public void addOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener listener) {
        this.mListener = listener;
    }

    private void setSelectedIndex(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex <= this.mPageCount - 1) {
            ((ImageView) this.mIndexImages.get(this.mSelectedIndex)).animate().scaleX(FlexItem.FLEX_SHRINK_DEFAULT).scaleY(FlexItem.FLEX_SHRINK_DEFAULT).setDuration(300).start();
            ((ImageView) this.mIndexImages.get(selectedIndex)).animate().scaleX(SCALE).scaleY(SCALE).setDuration(300).start();
            this.mSelectedIndex = selectedIndex;
        }
    }

    private void setPageCount(int pageCount) {
        this.mPageCount = pageCount;
        this.mSelectedIndex = 0;
        removeAllViews();
        this.mIndexImages.clear();
        for (int i = 0; i < pageCount; i++) {
            addView(createBoxedItem(i));
        }
        setSelectedIndex(this.mSelectedIndex);
    }

    @NonNull
    private FrameLayout createBoxedItem(int position) {
        FrameLayout box = new FrameLayout(getContext());
        ImageView item = createItem();
        box.addView(item);
        this.mIndexImages.add(item);
        LinearLayoutCompat.LayoutParams boxParams = new LinearLayoutCompat.LayoutParams((int) (((float) this.mItemSize) * SCALE), (int) (((float) this.mItemSize) * SCALE));
        if (position > 0) {
            boxParams.setMargins(this.mDelimiterSize, 0, 0, 0);
        }
        box.setLayoutParams(boxParams);
        return box;
    }

    @NonNull
    private ImageView createItem() {
        ImageView index = new ImageView(getContext());
        FrameLayout.LayoutParams indexParams = new FrameLayout.LayoutParams(this.mItemSize, this.mItemSize);
        indexParams.gravity = 17;
        index.setLayoutParams(indexParams);
        index.setImageResource(C1508R.drawable.white_circle);
        index.setScaleType(ScaleType.FIT_CENTER);
        return index;
    }
}
