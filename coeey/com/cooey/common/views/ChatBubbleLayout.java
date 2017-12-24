package com.cooey.common.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.NinePatchDrawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.cooey.common.C0842R;
import com.google.android.flexbox.FlexItem;

public class ChatBubbleLayout extends FrameLayout {
    private static final PorterDuffXfermode DST_IN = new PorterDuffXfermode(Mode.DST_IN);
    private NinePatchDrawable mMask;
    private NinePatchDrawable mNoTailMask;
    private final Paint mPaint;
    private boolean mReverseLayout;
    private boolean mShowTail;
    private NinePatchDrawable mTailMask;

    public ChatBubbleLayout(Context context) {
        this(context, null);
    }

    public ChatBubbleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatBubbleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mPaint = new Paint(1);
        this.mShowTail = true;
        init();
    }

    @TargetApi(21)
    public ChatBubbleLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mPaint = new Paint(1);
        this.mShowTail = true;
        init();
    }

    private void init() {
        setWillNotDraw(false);
        setLayerType(2, this.mPaint);
        NinePatchDrawable createMask = createMask(C0842R.drawable.ic_mess_bubble_right);
        this.mTailMask = createMask;
        this.mMask = createMask;
        this.mNoTailMask = createMask(C0842R.drawable.ic_mess_bubble_circle);
    }

    private NinePatchDrawable createMask(@DrawableRes int res) {
        Bitmap maskBitmap = BitmapFactory.decodeResource(getResources(), res);
        return new NinePatchDrawable(getResources(), new NinePatch(maskBitmap, maskBitmap.getNinePatchChunk(), "BubbleMask"));
    }

    public void reverseLayout(boolean reverseLayout) {
        if (this.mReverseLayout != reverseLayout) {
            this.mReverseLayout = reverseLayout;
            invalidate();
        }
    }

    public void showTail(boolean showTail) {
        if (this.mShowTail != showTail) {
            this.mShowTail = showTail;
            this.mMask = this.mShowTail ? this.mTailMask : this.mNoTailMask;
            invalidate();
        }
    }

    protected int getSuggestedMinimumWidth() {
        return Math.max(this.mMask.getMinimumWidth(), super.getSuggestedMinimumWidth());
    }

    protected int getSuggestedMinimumHeight() {
        return Math.max(this.mMask.getMinimumHeight(), super.getSuggestedMinimumHeight());
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            this.mTailMask.setBounds(0, 0, w, h);
            this.mNoTailMask.setBounds(0, 0, w, h);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.mMask.getPaint().setXfermode(DST_IN);
        if (this.mReverseLayout && this.mShowTail) {
            canvas.save();
            canvas.scale(FlexItem.FLEX_BASIS_PERCENT_DEFAULT, FlexItem.FLEX_SHRINK_DEFAULT);
            canvas.translate((float) (-getMeasuredWidth()), 0.0f);
        }
        this.mMask.draw(canvas);
        if (this.mReverseLayout && this.mShowTail) {
            canvas.restore();
        }
        this.mMask.getPaint().setXfermode(null);
    }
}
