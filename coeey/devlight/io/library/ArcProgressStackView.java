package devlight.io.library;

import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.annotation.FloatRange;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.google.android.flexbox.FlexItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@TargetApi(11)
public class ArcProgressStackView extends View {
    private static final int ANIMATE_ALL_INDEX = -2;
    private static final int DEFAULT_ACTION_MOVE_ANIMATION_DURATION = 150;
    private static final int DEFAULT_ANIMATION_DURATION = 350;
    private static final float DEFAULT_DRAW_WIDTH_FRACTION = 0.7f;
    private static final float DEFAULT_MODEL_OFFSET = 5.0f;
    private static final float DEFAULT_SHADOW_ANGLE = 90.0f;
    private static final int DEFAULT_SHADOW_COLOR = Color.parseColor("#8C000000");
    private static final float DEFAULT_SHADOW_DISTANCE = 15.0f;
    private static final float DEFAULT_SHADOW_RADIUS = 30.0f;
    private static final int DEFAULT_SLICE = 0;
    private static final float DEFAULT_START_ANGLE = 270.0f;
    private static final float DEFAULT_SWEEP_ANGLE = 360.0f;
    private static final int DISABLE_ANIMATE_INDEX = -1;
    private static final float MAX_ANGLE = 360.0f;
    private static final float MAX_FRACTION = 1.0f;
    private static final float MAX_PROGRESS = 100.0f;
    private static final float MIN_ANGLE = 0.0f;
    private static final float MIN_FRACTION = 0.0f;
    private static final float MIN_PROGRESS = 0.0f;
    private static final float MIN_SHADOW = 0.0f;
    private static final float NEGATIVE_ANGLE = 270.0f;
    private static final int NEGATIVE_SLICE = -1;
    private static final float POSITIVE_ANGLE = 90.0f;
    private static final int POSITIVE_SLICE = 1;
    private int mActionMoveLastSlice;
    private int mActionMoveModelIndex;
    private int mActionMoveSliceCounter;
    private float mAnimatedFraction;
    private int mAnimationDuration;
    private AnimatorListener mAnimatorListener;
    private AnimatorUpdateListener mAnimatorUpdateListener;
    private float mDrawWidthDimension;
    private float mDrawWidthFraction;
    private IndicatorOrientation mIndicatorOrientation;
    private Interpolator mInterpolator;
    private boolean mIsActionMoved;
    private boolean mIsAnimated;
    private boolean mIsDragged;
    private boolean mIsFeaturesAvailable;
    private boolean mIsLeveled;
    private boolean mIsModelBgEnabled;
    private boolean mIsRounded;
    private boolean mIsShadowed;
    private final Paint mLevelPaint;
    private List<Model> mModels;
    private int mPreviewModelBgColor;
    private final ValueAnimator mProgressAnimator;
    private float mProgressModelOffset;
    private float mProgressModelSize;
    private final Paint mProgressPaint;
    private float mShadowAngle;
    private int mShadowColor;
    private float mShadowDistance;
    private float mShadowRadius;
    private int mSize;
    private float mStartAngle;
    private float mSweepAngle;
    private int mTextColor;
    private final TextPaint mTextPaint;
    private Typeface mTypeface;

    class C23564 implements AnimatorUpdateListener {
        C23564() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            ArcProgressStackView.this.mAnimatedFraction = ((Float) animation.getAnimatedValue()).floatValue();
            if (ArcProgressStackView.this.mAnimatorUpdateListener != null) {
                ArcProgressStackView.this.mAnimatorUpdateListener.onAnimationUpdate(animation);
            }
            ArcProgressStackView.this.postInvalidate();
        }
    }

    public enum IndicatorOrientation {
        HORIZONTAL,
        VERTICAL
    }

    public static class Model {
        private int mBgColor;
        private final RectF mBounds;
        private int mColor;
        private int[] mColors;
        private float mLastProgress;
        private final Path mPath;
        private final PathMeasure mPathMeasure;
        private final float[] mPos;
        private float mProgress;
        private SweepGradient mSweepGradient;
        private final float[] mTan;
        private final Rect mTextBounds;
        private String mTitle;

        public Model(String title, float progress, int color) {
            this.mBounds = new RectF();
            this.mTextBounds = new Rect();
            this.mPath = new Path();
            this.mPathMeasure = new PathMeasure();
            this.mPos = new float[2];
            this.mTan = new float[2];
            setTitle(title);
            setProgress(progress);
            setColor(color);
        }

        public Model(String title, float progress, int[] colors) {
            this.mBounds = new RectF();
            this.mTextBounds = new Rect();
            this.mPath = new Path();
            this.mPathMeasure = new PathMeasure();
            this.mPos = new float[2];
            this.mTan = new float[2];
            setTitle(title);
            setProgress(progress);
            setColors(colors);
        }

        public Model(String title, float progress, int bgColor, int color) {
            this.mBounds = new RectF();
            this.mTextBounds = new Rect();
            this.mPath = new Path();
            this.mPathMeasure = new PathMeasure();
            this.mPos = new float[2];
            this.mTan = new float[2];
            setTitle(title);
            setProgress(progress);
            setColor(color);
            setBgColor(bgColor);
        }

        public Model(String title, float progress, int bgColor, int[] colors) {
            this.mBounds = new RectF();
            this.mTextBounds = new Rect();
            this.mPath = new Path();
            this.mPathMeasure = new PathMeasure();
            this.mPos = new float[2];
            this.mTan = new float[2];
            setTitle(title);
            setProgress(progress);
            setColors(colors);
            setBgColor(bgColor);
        }

        public String getTitle() {
            return this.mTitle;
        }

        public void setTitle(String title) {
            this.mTitle = title;
        }

        public float getProgress() {
            return this.mProgress;
        }

        @FloatRange
        public void setProgress(@FloatRange(from = 0.0d, to = 100.0d) float progress) {
            this.mLastProgress = this.mProgress;
            this.mProgress = (float) ((int) Math.max(0.0f, Math.min(progress, ArcProgressStackView.MAX_PROGRESS)));
        }

        public int getColor() {
            return this.mColor;
        }

        public void setColor(int color) {
            this.mColor = color;
        }

        public int getBgColor() {
            return this.mBgColor;
        }

        public void setBgColor(int bgColor) {
            this.mBgColor = bgColor;
        }

        public int[] getColors() {
            return this.mColors;
        }

        public void setColors(int[] colors) {
            if (colors == null || colors.length < 2) {
                this.mColors = null;
            } else {
                this.mColors = colors;
            }
        }
    }

    public ArcProgressStackView(Context context) {
        this(context, null);
    }

    public ArcProgressStackView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcProgressStackView(Context context, AttributeSet attrs, int defStyleAttr) {
        Random random;
        super(context, attrs, defStyleAttr);
        this.mModels = new ArrayList();
        this.mProgressPaint = new Paint(1) {
        };
        this.mTextPaint = new TextPaint(1) {
        };
        this.mLevelPaint = new Paint(1) {
        };
        this.mProgressAnimator = new ValueAnimator();
        this.mActionMoveModelIndex = -1;
        this.mActionMoveLastSlice = 0;
        setWillNotDraw(false);
        setLayerType(1, null);
        ViewCompat.setLayerType(this, 1, null);
        this.mIsFeaturesAvailable = VERSION.SDK_INT >= 11;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, C2357R.styleable.ArcProgressStackView);
        try {
            Interpolator interpolator;
            setIsAnimated(typedArray.getBoolean(C2357R.styleable.ArcProgressStackView_apsv_animated, true));
            setIsShadowed(typedArray.getBoolean(C2357R.styleable.ArcProgressStackView_apsv_shadowed, true));
            setIsRounded(typedArray.getBoolean(C2357R.styleable.ArcProgressStackView_apsv_rounded, false));
            setIsDragged(typedArray.getBoolean(C2357R.styleable.ArcProgressStackView_apsv_dragged, false));
            setIsLeveled(typedArray.getBoolean(C2357R.styleable.ArcProgressStackView_apsv_leveled, false));
            setTypeface(typedArray.getString(C2357R.styleable.ArcProgressStackView_apsv_typeface));
            setTextColor(typedArray.getColor(C2357R.styleable.ArcProgressStackView_apsv_text_color, -1));
            setShadowRadius(typedArray.getDimension(C2357R.styleable.ArcProgressStackView_apsv_shadow_radius, DEFAULT_SHADOW_RADIUS));
            setShadowDistance(typedArray.getDimension(C2357R.styleable.ArcProgressStackView_apsv_shadow_distance, DEFAULT_SHADOW_DISTANCE));
            setShadowAngle((float) typedArray.getInteger(C2357R.styleable.ArcProgressStackView_apsv_shadow_angle, 90));
            setShadowColor(typedArray.getColor(C2357R.styleable.ArcProgressStackView_apsv_shadow_color, DEFAULT_SHADOW_COLOR));
            setAnimationDuration((long) typedArray.getInteger(C2357R.styleable.ArcProgressStackView_apsv_animation_duration, DEFAULT_ANIMATION_DURATION));
            setStartAngle((float) typedArray.getInteger(C2357R.styleable.ArcProgressStackView_apsv_start_angle, 270));
            setSweepAngle((float) typedArray.getInteger(C2357R.styleable.ArcProgressStackView_apsv_sweep_angle, 360));
            setProgressModelOffset(typedArray.getDimension(C2357R.styleable.ArcProgressStackView_apsv_model_offset, DEFAULT_MODEL_OFFSET));
            setModelBgEnabled(typedArray.getBoolean(C2357R.styleable.ArcProgressStackView_apsv_model_bg_enabled, false));
            setIndicatorOrientation(typedArray.getInt(C2357R.styleable.ArcProgressStackView_apsv_indicator_orientation, 0) == 0 ? IndicatorOrientation.VERTICAL : IndicatorOrientation.HORIZONTAL);
            int interpolatorId = typedArray.getResourceId(C2357R.styleable.ArcProgressStackView_apsv_interpolator, 0);
            if (interpolatorId == 0) {
                interpolator = null;
            } else {
                interpolator = AnimationUtils.loadInterpolator(context, interpolatorId);
            }
            setInterpolator(interpolator);
        } catch (NotFoundException exception) {
            exception.printStackTrace();
            setInterpolator(null);
        } catch (Throwable th) {
            typedArray.recycle();
        }
        if (this.mIsFeaturesAvailable) {
            this.mProgressAnimator.setFloatValues(new float[]{0.0f, 1.0f});
            this.mProgressAnimator.addUpdateListener(new C23564());
        }
        if (typedArray.hasValue(C2357R.styleable.ArcProgressStackView_apsv_draw_width)) {
            TypedValue drawWidth = new TypedValue();
            typedArray.getValue(C2357R.styleable.ArcProgressStackView_apsv_draw_width, drawWidth);
            if (drawWidth.type == 5) {
                setDrawWidthDimension(drawWidth.getDimension(context.getResources().getDisplayMetrics()));
            } else {
                setDrawWidthFraction(drawWidth.getFraction(1.0f, 1.0f));
            }
        } else {
            setDrawWidthFraction(0.7f);
        }
        if (isInEditMode()) {
            String[] preview;
            try {
                int previewId = typedArray.getResourceId(C2357R.styleable.ArcProgressStackView_apsv_preview_colors, 0);
                if (previewId == 0) {
                    preview = null;
                } else {
                    preview = typedArray.getResources().getStringArray(previewId);
                }
                if (preview == null) {
                    preview = typedArray.getResources().getStringArray(C2357R.array.default_preview);
                }
                random = new Random();
                for (String previewColor : preview) {
                    this.mModels.add(new Model("", (float) random.nextInt(100), Color.parseColor(previewColor)));
                }
                measure(this.mSize, this.mSize);
            } catch (Exception exception2) {
                preview = null;
                exception2.printStackTrace();
                if (null == null) {
                    preview = typedArray.getResources().getStringArray(C2357R.array.default_preview);
                }
                random = new Random();
                for (String previewColor2 : preview) {
                    this.mModels.add(new Model("", (float) random.nextInt(100), Color.parseColor(previewColor2)));
                }
                measure(this.mSize, this.mSize);
            } catch (Throwable th2) {
                if (null == null) {
                    preview = typedArray.getResources().getStringArray(C2357R.array.default_preview);
                }
                random = new Random();
                for (String previewColor22 : preview) {
                    this.mModels.add(new Model("", (float) random.nextInt(100), Color.parseColor(previewColor22)));
                }
                measure(this.mSize, this.mSize);
            }
            this.mPreviewModelBgColor = typedArray.getColor(C2357R.styleable.ArcProgressStackView_apsv_preview_bg, -3355444);
        }
        typedArray.recycle();
    }

    public ValueAnimator getProgressAnimator() {
        return this.mProgressAnimator;
    }

    public long getAnimationDuration() {
        return (long) this.mAnimationDuration;
    }

    @TargetApi(11)
    public void setAnimationDuration(long animationDuration) {
        this.mAnimationDuration = (int) animationDuration;
        this.mProgressAnimator.setDuration(animationDuration);
    }

    public AnimatorListener getAnimatorListener() {
        return this.mAnimatorListener;
    }

    public void setAnimatorListener(AnimatorListener animatorListener) {
        if (this.mAnimatorListener != null) {
            this.mProgressAnimator.removeListener(this.mAnimatorListener);
        }
        this.mAnimatorListener = animatorListener;
        this.mProgressAnimator.addListener(animatorListener);
    }

    public AnimatorUpdateListener getAnimatorUpdateListener() {
        return this.mAnimatorUpdateListener;
    }

    public void setAnimatorUpdateListener(AnimatorUpdateListener animatorUpdateListener) {
        this.mAnimatorUpdateListener = animatorUpdateListener;
    }

    public float getStartAngle() {
        return this.mStartAngle;
    }

    @SuppressLint({"SupportAnnotationUsage"})
    @FloatRange
    public void setStartAngle(@FloatRange(from = 0.0d, to = 360.0d) float startAngle) {
        this.mStartAngle = Math.max(0.0f, Math.min(startAngle, 360.0f));
        postInvalidate();
    }

    public float getSweepAngle() {
        return this.mSweepAngle;
    }

    @SuppressLint({"SupportAnnotationUsage"})
    @FloatRange
    public void setSweepAngle(@FloatRange(from = 0.0d, to = 360.0d) float sweepAngle) {
        this.mSweepAngle = Math.max(0.0f, Math.min(sweepAngle, 360.0f));
        postInvalidate();
    }

    public List<Model> getModels() {
        return this.mModels;
    }

    public void setModels(List<Model> models) {
        this.mModels.clear();
        this.mModels = models;
        requestLayout();
    }

    public int getSize() {
        return this.mSize;
    }

    public float getProgressModelSize() {
        return this.mProgressModelSize;
    }

    public boolean isAnimated() {
        return this.mIsAnimated;
    }

    @TargetApi(11)
    public void setIsAnimated(boolean isAnimated) {
        boolean z = this.mIsFeaturesAvailable && isAnimated;
        this.mIsAnimated = z;
    }

    public boolean isShadowed() {
        return this.mIsShadowed;
    }

    @TargetApi(11)
    public void setIsShadowed(boolean isShadowed) {
        boolean z = this.mIsFeaturesAvailable && isShadowed;
        this.mIsShadowed = z;
        resetShadowLayer();
        requestLayout();
    }

    public boolean isModelBgEnabled() {
        return this.mIsModelBgEnabled;
    }

    public void setModelBgEnabled(boolean modelBgEnabled) {
        this.mIsModelBgEnabled = modelBgEnabled;
        postInvalidate();
    }

    public boolean isRounded() {
        return this.mIsRounded;
    }

    public void setIsRounded(boolean isRounded) {
        this.mIsRounded = isRounded;
        if (this.mIsRounded) {
            this.mProgressPaint.setStrokeCap(Cap.ROUND);
            this.mProgressPaint.setStrokeJoin(Join.ROUND);
        } else {
            this.mProgressPaint.setStrokeCap(Cap.BUTT);
            this.mProgressPaint.setStrokeJoin(Join.MITER);
        }
        requestLayout();
    }

    public boolean isDragged() {
        return this.mIsDragged;
    }

    public void setIsDragged(boolean isDragged) {
        this.mIsDragged = isDragged;
    }

    public boolean isLeveled() {
        return this.mIsLeveled;
    }

    public void setIsLeveled(boolean isLeveled) {
        boolean z = this.mIsFeaturesAvailable && isLeveled;
        this.mIsLeveled = z;
        requestLayout();
    }

    public Interpolator getInterpolator() {
        return (Interpolator) this.mProgressAnimator.getInterpolator();
    }

    @TargetApi(11)
    public void setInterpolator(Interpolator interpolator) {
        if (interpolator == null) {
            interpolator = new AccelerateDecelerateInterpolator();
        }
        this.mInterpolator = interpolator;
        this.mProgressAnimator.setInterpolator(this.mInterpolator);
    }

    public float getProgressModelOffset() {
        return this.mProgressModelOffset;
    }

    public void setProgressModelOffset(float progressModelOffset) {
        this.mProgressModelOffset = progressModelOffset;
        requestLayout();
    }

    public float getDrawWidthFraction() {
        return this.mDrawWidthFraction;
    }

    @SuppressLint({"SupportAnnotationUsage"})
    @FloatRange
    public void setDrawWidthFraction(@FloatRange(from = 0.0d, to = 1.0d) float drawWidthFraction) {
        this.mDrawWidthFraction = Math.max(0.0f, Math.min(drawWidthFraction, 1.0f)) * 0.5f;
        this.mDrawWidthDimension = 0.0f;
        requestLayout();
    }

    public float getDrawWidthDimension() {
        return this.mDrawWidthDimension;
    }

    public void setDrawWidthDimension(float drawWidthDimension) {
        this.mDrawWidthFraction = 0.0f;
        this.mDrawWidthDimension = drawWidthDimension;
        requestLayout();
    }

    public float getShadowDistance() {
        return this.mShadowDistance;
    }

    public void setShadowDistance(float shadowDistance) {
        this.mShadowDistance = shadowDistance;
        resetShadowLayer();
        requestLayout();
    }

    public float getShadowAngle() {
        return this.mShadowAngle;
    }

    @SuppressLint({"SupportAnnotationUsage"})
    @FloatRange
    public void setShadowAngle(@FloatRange(from = 0.0d, to = 360.0d) float shadowAngle) {
        this.mShadowAngle = Math.max(0.0f, Math.min(shadowAngle, 360.0f));
        resetShadowLayer();
        requestLayout();
    }

    public float getShadowRadius() {
        return this.mShadowRadius;
    }

    public void setShadowRadius(float shadowRadius) {
        if (shadowRadius <= 0.0f) {
            shadowRadius = 0.0f;
        }
        this.mShadowRadius = shadowRadius;
        resetShadowLayer();
        requestLayout();
    }

    public int getShadowColor() {
        return this.mShadowColor;
    }

    public void setShadowColor(int shadowColor) {
        this.mShadowColor = shadowColor;
        resetShadowLayer();
        postInvalidate();
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
        this.mTextPaint.setColor(textColor);
        postInvalidate();
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public void setTypeface(String typeface) {
        Typeface tempTypeface;
        try {
            if (!isInEditMode()) {
                tempTypeface = Typeface.createFromAsset(getContext().getAssets(), typeface);
                setTypeface(tempTypeface);
            }
        } catch (Exception e) {
            tempTypeface = Typeface.create(Typeface.DEFAULT, 0);
            e.printStackTrace();
        }
    }

    public void setTypeface(Typeface typeface) {
        this.mTypeface = typeface;
        this.mTextPaint.setTypeface(typeface);
        postInvalidate();
    }

    public IndicatorOrientation getIndicatorOrientation() {
        return this.mIndicatorOrientation;
    }

    public void setIndicatorOrientation(IndicatorOrientation indicatorOrientation) {
        this.mIndicatorOrientation = indicatorOrientation;
    }

    private void resetShadowLayer() {
        if (!isInEditMode()) {
            float newDx = (float) (((double) this.mShadowDistance) * Math.cos(((double) ((this.mShadowAngle - this.mStartAngle) / 180.0f)) * 3.141592653589793d));
            float newDy = (float) (((double) this.mShadowDistance) * Math.sin(((double) ((this.mShadowAngle - this.mStartAngle) / 180.0f)) * 3.141592653589793d));
            if (this.mIsShadowed) {
                this.mProgressPaint.setShadowLayer(this.mShadowRadius, newDx, newDy, this.mShadowColor);
            } else {
                this.mProgressPaint.clearShadowLayer();
            }
        }
    }

    private void setLevelShadowLayer() {
        if (!isInEditMode()) {
            if (this.mIsShadowed || this.mIsLeveled) {
                float shadowOffset = this.mShadowRadius * 0.5f;
                this.mLevelPaint.setShadowLayer(shadowOffset, 0.0f, -shadowOffset, adjustColorAlpha(this.mShadowColor, 0.5f));
                return;
            }
            this.mLevelPaint.clearShadowLayer();
        }
    }

    private int adjustColorAlpha(int color, float factor) {
        return Color.argb(Math.round(((float) Color.alpha(color)) * factor), Color.red(color), Color.green(color), Color.blue(color));
    }

    public void animateProgress() {
        if (this.mIsAnimated && this.mProgressAnimator != null) {
            if (this.mProgressAnimator.isRunning()) {
                if (this.mAnimatorListener != null) {
                    this.mProgressAnimator.removeListener(this.mAnimatorListener);
                }
                this.mProgressAnimator.cancel();
            }
            this.mActionMoveModelIndex = -2;
            this.mProgressAnimator.setDuration((long) this.mAnimationDuration);
            this.mProgressAnimator.setInterpolator(this.mInterpolator);
            if (this.mAnimatorListener != null) {
                this.mProgressAnimator.removeListener(this.mAnimatorListener);
                this.mProgressAnimator.addListener(this.mAnimatorListener);
            }
            this.mProgressAnimator.start();
        }
    }

    private void animateActionMoveProgress() {
        if (this.mIsAnimated && this.mProgressAnimator != null && !this.mProgressAnimator.isRunning()) {
            this.mProgressAnimator.setDuration(150);
            this.mProgressAnimator.setInterpolator(null);
            if (this.mAnimatorListener != null) {
                this.mProgressAnimator.removeListener(this.mAnimatorListener);
            }
            this.mProgressAnimator.start();
        }
    }

    private float getActionMoveAngle(float x, float y) {
        float radius = ((float) this.mSize) * 0.5f;
        float degrees = (float) ((Math.toDegrees(Math.atan2((double) (y - radius), (double) (x - radius))) + 360.0d) % 360.0d);
        if (degrees < 0.0f) {
            degrees = (float) (((double) degrees) + 6.283185307179586d);
        }
        degrees = (float) ((Math.toDegrees(Math.atan2((double) ((float) (((double) radius) * Math.sin(((double) ((degrees - this.mStartAngle) / 180.0f)) * 3.141592653589793d))), (double) ((float) (((double) radius) * Math.cos(((double) ((degrees - this.mStartAngle) / 180.0f)) * 3.141592653589793d))))) + 360.0d) % 360.0d);
        if (degrees < 0.0f) {
            return (float) (((double) degrees) + 6.283185307179586d);
        }
        return degrees;
    }

    private void handleActionMoveModel(MotionEvent event) {
        if (this.mActionMoveModelIndex != -1) {
            int actionMoveCurrentSlice;
            float currentAngle = getActionMoveAngle(event.getX(), event.getY());
            if (currentAngle > 0.0f && currentAngle < 90.0f) {
                actionMoveCurrentSlice = 1;
            } else if (currentAngle <= 270.0f || currentAngle >= 360.0f) {
                actionMoveCurrentSlice = 0;
            } else {
                actionMoveCurrentSlice = -1;
            }
            if (actionMoveCurrentSlice != 0 && ((this.mActionMoveLastSlice == -1 && actionMoveCurrentSlice == 1) || (actionMoveCurrentSlice == -1 && this.mActionMoveLastSlice == 1))) {
                if (this.mActionMoveLastSlice == -1) {
                    this.mActionMoveSliceCounter++;
                } else {
                    this.mActionMoveSliceCounter--;
                }
                if (this.mActionMoveSliceCounter > 1) {
                    this.mActionMoveSliceCounter = 1;
                } else if (this.mActionMoveSliceCounter < -1) {
                    this.mActionMoveSliceCounter = -1;
                }
            }
            this.mActionMoveLastSlice = actionMoveCurrentSlice;
            float actionMoveTotalAngle = currentAngle + (((float) this.mActionMoveSliceCounter) * 360.0f);
            Model model = (Model) this.mModels.get(this.mActionMoveModelIndex);
            if (actionMoveTotalAngle < 0.0f || actionMoveTotalAngle > 360.0f) {
                currentAngle = actionMoveTotalAngle > 360.0f ? 361.0f : FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
            }
            model.setProgress((float) Math.round((MAX_PROGRESS / this.mSweepAngle) * currentAngle));
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!this.mIsDragged) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case 0:
                this.mActionMoveModelIndex = -1;
                float currentAngle = getActionMoveAngle(event.getX(), event.getY());
                if (currentAngle <= this.mSweepAngle || currentAngle >= 360.0f) {
                    for (int i = 0; i < this.mModels.size(); i++) {
                        Model model = (Model) this.mModels.get(i);
                        if (model.mBounds.contains(event.getX(), event.getY())) {
                            float modelRadius = model.mBounds.width() * 0.5f;
                            float modelOffset = this.mProgressModelSize * 0.5f;
                            float mainRadius = ((float) this.mSize) * 0.5f;
                            float distance = (float) Math.sqrt(Math.pow((double) (event.getX() - mainRadius), 2.0d) + Math.pow((double) (event.getY() - mainRadius), 2.0d));
                            if (distance > modelRadius - modelOffset && distance < modelRadius + modelOffset) {
                                this.mActionMoveModelIndex = i;
                                this.mIsActionMoved = true;
                                handleActionMoveModel(event);
                                animateActionMoveProgress();
                            }
                        }
                    }
                    break;
                }
            case 2:
                if ((this.mActionMoveModelIndex != -1 || this.mIsActionMoved) && !this.mProgressAnimator.isRunning()) {
                    handleActionMoveModel(event);
                    postInvalidate();
                    break;
                }
            default:
                this.mActionMoveLastSlice = 0;
                this.mActionMoveSliceCounter = 0;
                this.mIsActionMoved = false;
                break;
        }
        if (event.getAction() == 2 && getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float divider;
        float shadowOffset = 0.0f;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (width > height) {
            this.mSize = height;
        } else {
            this.mSize = width;
        }
        if (this.mDrawWidthFraction == 0.0f) {
            divider = this.mDrawWidthDimension;
        } else {
            divider = ((float) this.mSize) * this.mDrawWidthFraction;
        }
        this.mProgressModelSize = divider / ((float) this.mModels.size());
        float paintOffset = this.mProgressModelSize * 0.5f;
        if (this.mIsShadowed) {
            shadowOffset = this.mShadowRadius + this.mShadowDistance;
        }
        for (int i = 0; i < this.mModels.size(); i++) {
            Model model = (Model) this.mModels.get(i);
            float modelOffset = ((this.mProgressModelSize * ((float) i)) + (paintOffset + shadowOffset)) - (this.mProgressModelOffset * ((float) i));
            model.mBounds.set(modelOffset, modelOffset, ((float) this.mSize) - modelOffset, ((float) this.mSize) - modelOffset);
            if (model.getColors() != null) {
                model.mSweepGradient = new SweepGradient(model.mBounds.centerX(), model.mBounds.centerY(), model.getColors(), null);
            }
        }
        setMeasuredDimension(this.mSize, this.mSize);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        float radius = ((float) this.mSize) * 0.5f;
        canvas.rotate(this.mStartAngle, radius, radius);
        for (int i = 0; i < this.mModels.size(); i++) {
            float progress;
            Model model = (Model) this.mModels.get(i);
            if (!this.mIsAnimated || isInEditMode()) {
                progress = model.getProgress() / MAX_PROGRESS;
            } else {
                progress = (model.mLastProgress + (this.mAnimatedFraction * (model.getProgress() - model.mLastProgress))) / MAX_PROGRESS;
            }
            if (!(i == this.mActionMoveModelIndex || this.mActionMoveModelIndex == -2)) {
                progress = model.getProgress() / MAX_PROGRESS;
            }
            float progress2 = progress * this.mSweepAngle;
            boolean isGradient = model.getColors() != null;
            this.mProgressPaint.setStrokeWidth(this.mProgressModelSize);
            model.mPath.reset();
            model.mPath.addArc(model.mBounds, 0.0f, progress2);
            resetShadowLayer();
            this.mProgressPaint.setShader(null);
            this.mProgressPaint.setStyle(Style.STROKE);
            if (this.mIsModelBgEnabled) {
                this.mProgressPaint.setColor(isInEditMode() ? this.mPreviewModelBgColor : model.getBgColor());
                canvas.drawArc(model.mBounds, 0.0f, this.mSweepAngle, false, this.mProgressPaint);
                if (!isInEditMode()) {
                    this.mProgressPaint.clearShadowLayer();
                }
            }
            if (isGradient) {
                if (!this.mIsModelBgEnabled) {
                    canvas.drawPath(model.mPath, this.mProgressPaint);
                    if (!isInEditMode()) {
                        this.mProgressPaint.clearShadowLayer();
                    }
                }
                this.mProgressPaint.setShader(model.mSweepGradient);
            } else {
                this.mProgressPaint.setColor(model.getColor());
            }
            this.mProgressPaint.setAlpha(255);
            canvas.drawPath(model.mPath, this.mProgressPaint);
            if (!isInEditMode()) {
                float f;
                float[] access$900;
                this.mTextPaint.setTextSize(this.mProgressModelSize * 0.5f);
                this.mTextPaint.getTextBounds(model.getTitle(), 0, model.getTitle().length(), model.mTextBounds);
                float titleHorizontalOffset = ((float) model.mTextBounds.height()) * 0.5f;
                float progressLength = ((0.017453292f * progress2) * model.mBounds.width()) * 0.5f;
                String title = (String) TextUtils.ellipsize(model.getTitle(), this.mTextPaint, progressLength - (2.0f * titleHorizontalOffset), TruncateAt.END);
                Path access$500 = model.mPath;
                if (this.mIsRounded) {
                    f = 0.0f;
                } else {
                    f = titleHorizontalOffset;
                }
                canvas.drawTextOnPath(title, access$500, f, titleHorizontalOffset, this.mTextPaint);
                model.mPathMeasure.setPath(model.mPath, false);
                model.mPathMeasure.getPosTan(model.mPathMeasure.getLength(), model.mPos, model.mTan);
                float titleWidth = (float) model.mTextBounds.width();
                String percentProgress = String.format("%d%%", new Object[]{Integer.valueOf((int) model.getProgress())});
                this.mTextPaint.setTextSize(this.mProgressModelSize * 0.35f);
                this.mTextPaint.getTextBounds(percentProgress, 0, percentProgress.length(), model.mTextBounds);
                float progressHorizontalOffset = this.mIndicatorOrientation == IndicatorOrientation.VERTICAL ? ((float) model.mTextBounds.height()) * 0.5f : ((float) model.mTextBounds.width()) * 0.5f;
                if (!this.mIsRounded) {
                    progress = 1.0f;
                }
                float indicatorProgressOffset = progress * (((-progressHorizontalOffset) - titleHorizontalOffset) - (this.mIsRounded ? ((float) model.mTextBounds.height()) * 2.0f : 0.0f));
                PathMeasure access$700 = model.mPathMeasure;
                f = model.mPathMeasure.getLength() + indicatorProgressOffset;
                float[] access$800 = model.mPos;
                if (this.mIndicatorOrientation != IndicatorOrientation.VERTICAL || this.mIsRounded) {
                    access$900 = model.mTan;
                } else {
                    access$900 = new float[2];
                }
                access$700.getPosTan(f, access$800, access$900);
                if (((((float) model.mTextBounds.height()) + titleWidth) + (2.0f * titleHorizontalOffset)) - indicatorProgressOffset < progressLength) {
                    float indicatorProgressAngle = (float) (Math.atan2((double) model.mTan[1], (double) model.mTan[0]) * 57.29577951308232d);
                    float indicatorLengthProgressAngle = ((progressLength + indicatorProgressOffset) / (model.mBounds.width() * 0.5f)) * 57.29578f;
                    if (this.mIndicatorOrientation == IndicatorOrientation.VERTICAL) {
                        indicatorProgressAngle += ((float) (((double) (model.mBounds.width() * 0.5f)) * Math.cos((((double) (this.mStartAngle + indicatorLengthProgressAngle)) * 3.141592653589793d) / 180.0d))) + model.mBounds.centerX() > radius ? -90.0f : 90.0f;
                    } else {
                        indicatorProgressAngle += ((float) (((double) (model.mBounds.height() * 0.5f)) * Math.sin((((double) (this.mStartAngle + indicatorLengthProgressAngle)) * 3.141592653589793d) / 180.0d))) + model.mBounds.centerY() > radius ? 180.0f : 0.0f;
                    }
                    canvas.save();
                    canvas.rotate(indicatorProgressAngle, model.mPos[0], model.mPos[1]);
                    canvas.drawText(percentProgress, model.mPos[0] - model.mTextBounds.exactCenterX(), model.mPos[1] - model.mTextBounds.exactCenterY(), this.mTextPaint);
                    canvas.restore();
                }
                if ((isGradient || this.mIsLeveled) && this.mIsRounded && progress2 != 0.0f) {
                    model.mPathMeasure.getPosTan(0.0f, model.mPos, model.mTan);
                    setLevelShadowLayer();
                    this.mLevelPaint.setColor(isGradient ? model.getColors()[0] : model.getColor());
                    float halfSize = this.mProgressModelSize * 0.5f;
                    canvas.drawArc(new RectF(model.mPos[0] - halfSize, model.mPos[1] - halfSize, model.mPos[0] + halfSize, (model.mPos[1] + halfSize) + 2.0f), 0.0f, -180.0f, true, this.mLevelPaint);
                }
            }
        }
        canvas.restore();
    }
}
