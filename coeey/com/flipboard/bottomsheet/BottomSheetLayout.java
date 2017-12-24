package com.flipboard.bottomsheet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import flipboard.bottomsheet.R;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class BottomSheetLayout extends FrameLayout {
    private static final long ANIMATION_DURATION = 300;
    private static final Property<BottomSheetLayout, Float> SHEET_TRANSLATION = new Property<BottomSheetLayout, Float>(Float.class, "sheetTranslation") {
        public Float get(BottomSheetLayout object) {
            return Float.valueOf(object.sheetTranslation);
        }

        public void set(BottomSheetLayout object, Float value) {
            object.setSheetTranslation(value.floatValue());
        }
    };
    private TimeInterpolator animationInterpolator;
    public boolean bottomSheetOwnsTouch;
    private Rect contentClipRect;
    private Animator currentAnimator;
    private int currentSheetViewHeight;
    private final int defaultSheetWidth;
    private ViewTransformer defaultViewTransformer;
    private View dimView;
    private float downSheetTranslation;
    private State downState;
    private float downX;
    private float downY;
    private boolean hasIntercepted;
    private boolean interceptContentTouch;
    private final boolean isTablet;
    private float minFlingVelocity;
    private CopyOnWriteArraySet<OnSheetDismissedListener> onSheetDismissedListeners;
    private CopyOnWriteArraySet<OnSheetStateChangeListener> onSheetStateChangeListeners;
    private float peek;
    private float peekKeyline;
    private boolean peekOnDismiss;
    private Runnable runAfterDismiss;
    private int screenWidth;
    private int sheetEndX;
    private int sheetStartX;
    private float sheetTranslation;
    private OnLayoutChangeListener sheetViewOnLayoutChangeListener;
    private boolean sheetViewOwnsTouch;
    private boolean shouldDimContentView;
    private State state;
    private float touchSlop;
    private boolean useHardwareLayerWhileAnimating;
    private VelocityTracker velocityTracker;
    private ViewTransformer viewTransformer;

    private static class CancelDetectionAnimationListener extends AnimatorListenerAdapter {
        protected boolean canceled;

        private CancelDetectionAnimationListener() {
        }

        public void onAnimationCancel(Animator animation) {
            this.canceled = true;
        }
    }

    class C14652 extends CancelDetectionAnimationListener {
        C14652() {
            super();
        }

        public void onAnimationEnd(@NonNull Animator animation) {
            if (!this.canceled) {
                BottomSheetLayout.this.currentAnimator = null;
            }
        }
    }

    class C14663 extends CancelDetectionAnimationListener {
        C14663() {
            super();
        }

        public void onAnimationEnd(@NonNull Animator animation) {
            if (!this.canceled) {
                BottomSheetLayout.this.currentAnimator = null;
            }
        }
    }

    class C14695 implements OnPreDrawListener {

        class C14681 implements Runnable {
            C14681() {
            }

            public void run() {
                if (BottomSheetLayout.this.getSheetView() != null) {
                    BottomSheetLayout.this.peekSheet();
                }
            }
        }

        C14695() {
        }

        public boolean onPreDraw() {
            BottomSheetLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
            BottomSheetLayout.this.post(new C14681());
            return true;
        }
    }

    class C14706 implements OnLayoutChangeListener {
        C14706() {
        }

        public void onLayoutChange(View sheetView, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            int newSheetViewHeight = sheetView.getMeasuredHeight();
            if (BottomSheetLayout.this.state != State.HIDDEN && newSheetViewHeight < BottomSheetLayout.this.currentSheetViewHeight) {
                if (BottomSheetLayout.this.state == State.EXPANDED) {
                    BottomSheetLayout.this.setState(State.PEEKED);
                }
                BottomSheetLayout.this.setSheetTranslation((float) newSheetViewHeight);
            }
            BottomSheetLayout.this.currentSheetViewHeight = newSheetViewHeight;
        }
    }

    private static class IdentityViewTransformer extends BaseViewTransformer {
        private IdentityViewTransformer() {
        }

        public void transformView(float translation, float maxTranslation, float peekedTranslation, BottomSheetLayout parent, View view) {
        }
    }

    public interface OnSheetStateChangeListener {
        void onSheetStateChanged(State state);
    }

    public enum State {
        HIDDEN,
        PREPARING,
        PEEKED,
        EXPANDED
    }

    public BottomSheetLayout(Context context) {
        super(context);
        this.contentClipRect = new Rect();
        this.state = State.HIDDEN;
        this.peekOnDismiss = false;
        this.animationInterpolator = new DecelerateInterpolator(1.6f);
        this.defaultViewTransformer = new IdentityViewTransformer();
        this.shouldDimContentView = true;
        this.useHardwareLayerWhileAnimating = true;
        this.onSheetDismissedListeners = new CopyOnWriteArraySet();
        this.onSheetStateChangeListeners = new CopyOnWriteArraySet();
        this.interceptContentTouch = true;
        this.screenWidth = 0;
        this.isTablet = getResources().getBoolean(R.bool.bottomsheet_is_tablet);
        this.defaultSheetWidth = getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        this.sheetStartX = 0;
        this.sheetEndX = 0;
        init();
    }

    public BottomSheetLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomSheetLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.contentClipRect = new Rect();
        this.state = State.HIDDEN;
        this.peekOnDismiss = false;
        this.animationInterpolator = new DecelerateInterpolator(1.6f);
        this.defaultViewTransformer = new IdentityViewTransformer();
        this.shouldDimContentView = true;
        this.useHardwareLayerWhileAnimating = true;
        this.onSheetDismissedListeners = new CopyOnWriteArraySet();
        this.onSheetStateChangeListeners = new CopyOnWriteArraySet();
        this.interceptContentTouch = true;
        this.screenWidth = 0;
        this.isTablet = getResources().getBoolean(R.bool.bottomsheet_is_tablet);
        this.defaultSheetWidth = getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        this.sheetStartX = 0;
        this.sheetEndX = 0;
        init();
    }

    @TargetApi(21)
    public BottomSheetLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.contentClipRect = new Rect();
        this.state = State.HIDDEN;
        this.peekOnDismiss = false;
        this.animationInterpolator = new DecelerateInterpolator(1.6f);
        this.defaultViewTransformer = new IdentityViewTransformer();
        this.shouldDimContentView = true;
        this.useHardwareLayerWhileAnimating = true;
        this.onSheetDismissedListeners = new CopyOnWriteArraySet();
        this.onSheetStateChangeListeners = new CopyOnWriteArraySet();
        this.interceptContentTouch = true;
        this.screenWidth = 0;
        this.isTablet = getResources().getBoolean(R.bool.bottomsheet_is_tablet);
        this.defaultSheetWidth = getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        this.sheetStartX = 0;
        this.sheetEndX = 0;
        init();
    }

    private void init() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.minFlingVelocity = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.touchSlop = (float) viewConfiguration.getScaledTouchSlop();
        this.dimView = new View(getContext());
        this.dimView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.dimView.setAlpha(0.0f);
        this.dimView.setVisibility(4);
        setFocusableInTouchMode(true);
        Point point = new Point();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
        this.screenWidth = point.x;
        this.sheetEndX = this.screenWidth;
        this.peek = 0.0f;
        this.peekKeyline = ((float) point.y) - (((float) this.screenWidth) / 1.7777778f);
    }

    public void addView(@NonNull View child) {
        if (getChildCount() > 0) {
            throw new IllegalArgumentException("You may not declare more then one child of bottom sheet. The sheet view must be added dynamically with showWithSheetView()");
        }
        setContentView(child);
    }

    public void addView(@NonNull View child, int index) {
        addView(child);
    }

    public void addView(@NonNull View child, int index, @NonNull LayoutParams params) {
        addView(child);
    }

    public void addView(@NonNull View child, @NonNull LayoutParams params) {
        addView(child);
    }

    public void addView(@NonNull View child, int width, int height) {
        addView(child);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.velocityTracker = VelocityTracker.obtain();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.velocityTracker.clear();
        cancelCurrentAnimation();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.contentClipRect.set(0, 0, getWidth(), (int) (((double) getHeight()) - Math.ceil((double) this.sheetTranslation)));
    }

    public boolean onKeyPreIme(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == 4 && isSheetShowing()) {
            if (event.getAction() == 0 && event.getRepeatCount() == 0) {
                DispatcherState state = getKeyDispatcherState();
                if (state == null) {
                    return true;
                }
                state.startTracking(event, this);
                return true;
            } else if (event.getAction() == 1) {
                DispatcherState dispatcherState = getKeyDispatcherState();
                if (dispatcherState != null) {
                    dispatcherState.handleUpEvent(event);
                }
                if (isSheetShowing() && event.isTracking() && !event.isCanceled()) {
                    if (this.state == State.EXPANDED && this.peekOnDismiss) {
                        peekSheet();
                        return true;
                    }
                    dismissSheet();
                    return true;
                }
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }

    private void setSheetTranslation(float newTranslation) {
        int i = 0;
        this.sheetTranslation = Math.min(newTranslation, getMaxSheetTranslation());
        this.contentClipRect.set(0, 0, getWidth(), (int) (((double) getHeight()) - Math.ceil((double) this.sheetTranslation)));
        getSheetView().setTranslationY(((float) getHeight()) - this.sheetTranslation);
        transformView(this.sheetTranslation);
        if (this.shouldDimContentView) {
            float dimAlpha = getDimAlpha(this.sheetTranslation);
            this.dimView.setAlpha(dimAlpha);
            View view = this.dimView;
            if (dimAlpha <= 0.0f) {
                i = 4;
            }
            view.setVisibility(i);
        }
    }

    private void transformView(float sheetTranslation) {
        if (this.viewTransformer != null) {
            this.viewTransformer.transformView(sheetTranslation, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        } else if (this.defaultViewTransformer != null) {
            this.defaultViewTransformer.transformView(sheetTranslation, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        }
    }

    private float getDimAlpha(float sheetTranslation) {
        if (this.viewTransformer != null) {
            return this.viewTransformer.getDimAlpha(sheetTranslation, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        } else if (this.defaultViewTransformer == null) {
            return 0.0f;
        } else {
            return this.defaultViewTransformer.getDimAlpha(sheetTranslation, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        }
    }

    public boolean onInterceptTouchEvent(@NonNull MotionEvent ev) {
        boolean downAction;
        boolean z = true;
        if (ev.getActionMasked() == 0) {
            downAction = true;
        } else {
            downAction = false;
        }
        if (downAction) {
            this.hasIntercepted = false;
        }
        if (this.interceptContentTouch || (ev.getY() > ((float) getHeight()) - this.sheetTranslation && isXInSheet(ev.getX()))) {
            if (!(downAction && isSheetShowing())) {
                z = false;
            }
            this.hasIntercepted = z;
        } else {
            this.hasIntercepted = false;
        }
        return this.hasIntercepted;
    }

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (!isSheetShowing()) {
            return false;
        }
        if (isAnimating()) {
            return false;
        }
        if (!this.hasIntercepted) {
            return onInterceptTouchEvent(event);
        }
        if (event.getAction() == 0) {
            this.bottomSheetOwnsTouch = false;
            this.sheetViewOwnsTouch = false;
            this.downY = event.getY();
            this.downX = event.getX();
            this.downSheetTranslation = this.sheetTranslation;
            this.downState = this.state;
            this.velocityTracker.clear();
        }
        this.velocityTracker.addMovement(event);
        float maxSheetTranslation = getMaxSheetTranslation();
        float peekSheetTranslation = getPeekSheetTranslation();
        float deltaY = this.downY - event.getY();
        float deltaX = this.downX - event.getX();
        if (!(this.bottomSheetOwnsTouch || this.sheetViewOwnsTouch)) {
            this.bottomSheetOwnsTouch = Math.abs(deltaY) > this.touchSlop;
            this.sheetViewOwnsTouch = Math.abs(deltaX) > this.touchSlop;
            if (this.bottomSheetOwnsTouch) {
                if (this.state == State.PEEKED) {
                    MotionEvent cancelEvent = MotionEvent.obtain(event);
                    cancelEvent.offsetLocation(0.0f, this.sheetTranslation - ((float) getHeight()));
                    cancelEvent.setAction(3);
                    getSheetView().dispatchTouchEvent(cancelEvent);
                    cancelEvent.recycle();
                }
                this.sheetViewOwnsTouch = false;
                this.downY = event.getY();
                this.downX = event.getX();
                deltaY = 0.0f;
            }
        }
        float newSheetTranslation = this.downSheetTranslation + deltaY;
        if (this.bottomSheetOwnsTouch) {
            boolean scrollingDown = deltaY < 0.0f;
            boolean canScrollUp = canScrollUp(getSheetView(), event.getX(), event.getY() + (this.sheetTranslation - ((float) getHeight())));
            if (this.state == State.EXPANDED && scrollingDown && !canScrollUp) {
                this.downY = event.getY();
                this.downSheetTranslation = this.sheetTranslation;
                this.velocityTracker.clear();
                setState(State.PEEKED);
                setSheetLayerTypeIfEnabled(2);
                newSheetTranslation = this.sheetTranslation;
                cancelEvent = MotionEvent.obtain(event);
                cancelEvent.setAction(3);
                getSheetView().dispatchTouchEvent(cancelEvent);
                cancelEvent.recycle();
            }
            if (this.state == State.PEEKED && newSheetTranslation > maxSheetTranslation) {
                setSheetTranslation(maxSheetTranslation);
                newSheetTranslation = Math.min(maxSheetTranslation, newSheetTranslation);
                MotionEvent downEvent = MotionEvent.obtain(event);
                downEvent.setAction(0);
                getSheetView().dispatchTouchEvent(downEvent);
                downEvent.recycle();
                setState(State.EXPANDED);
                setSheetLayerTypeIfEnabled(0);
            }
            if (this.state == State.EXPANDED) {
                event.offsetLocation(0.0f, this.sheetTranslation - ((float) getHeight()));
                getSheetView().dispatchTouchEvent(event);
            } else {
                if (newSheetTranslation < peekSheetTranslation) {
                    newSheetTranslation = peekSheetTranslation - ((peekSheetTranslation - newSheetTranslation) / 4.0f);
                }
                setSheetTranslation(newSheetTranslation);
                if (event.getAction() == 3) {
                    if (this.downState == State.EXPANDED) {
                        expandSheet();
                    } else {
                        peekSheet();
                    }
                }
                if (event.getAction() == 1) {
                    if (newSheetTranslation < peekSheetTranslation) {
                        dismissSheet();
                    } else {
                        this.velocityTracker.computeCurrentVelocity(1000);
                        float velocityY = this.velocityTracker.getYVelocity();
                        if (Math.abs(velocityY) < this.minFlingVelocity) {
                            if (this.sheetTranslation > ((float) (getHeight() / 2))) {
                                expandSheet();
                            } else {
                                peekSheet();
                            }
                        } else if (velocityY < 0.0f) {
                            expandSheet();
                        } else {
                            peekSheet();
                        }
                    }
                }
            }
        } else {
            boolean touchOutsideBottomSheet = event.getY() < ((float) getHeight()) - this.sheetTranslation || !isXInSheet(event.getX());
            if (event.getAction() == 1 && touchOutsideBottomSheet && this.interceptContentTouch) {
                dismissSheet();
                return true;
            }
            event.offsetLocation(this.isTablet ? getX() - ((float) this.sheetStartX) : 0.0f, this.sheetTranslation - ((float) getHeight()));
            getSheetView().dispatchTouchEvent(event);
        }
        return true;
    }

    private boolean isXInSheet(float x) {
        return !this.isTablet || (x >= ((float) this.sheetStartX) && x <= ((float) this.sheetEndX));
    }

    private boolean isAnimating() {
        return this.currentAnimator != null;
    }

    private void cancelCurrentAnimation() {
        if (this.currentAnimator != null) {
            this.currentAnimator.cancel();
        }
    }

    private boolean canScrollUp(View view, float x, float y) {
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                int childLeft = child.getLeft() - view.getScrollX();
                int childTop = child.getTop() - view.getScrollY();
                boolean intersects = x > ((float) childLeft) && x < ((float) (child.getRight() - view.getScrollX())) && y > ((float) childTop) && y < ((float) (child.getBottom() - view.getScrollY()));
                if (intersects && canScrollUp(child, x - ((float) childLeft), y - ((float) childTop))) {
                    return true;
                }
            }
        }
        return view.canScrollVertically(-1);
    }

    private void setSheetLayerTypeIfEnabled(int layerType) {
        if (this.useHardwareLayerWhileAnimating) {
            getSheetView().setLayerType(layerType, null);
        }
    }

    private void setState(State state) {
        if (state != this.state) {
            this.state = state;
            Iterator it = this.onSheetStateChangeListeners.iterator();
            while (it.hasNext()) {
                ((OnSheetStateChangeListener) it.next()).onSheetStateChanged(state);
            }
        }
    }

    private boolean hasTallerKeylineHeightSheet() {
        return getSheetView() == null || ((float) getSheetView().getHeight()) > this.peekKeyline;
    }

    private boolean hasFullHeightSheet() {
        return getSheetView() == null || getSheetView().getHeight() == getHeight();
    }

    private void initializeSheetValues() {
        this.sheetTranslation = 0.0f;
        this.contentClipRect.set(0, 0, getWidth(), getHeight());
        getSheetView().setTranslationY((float) getHeight());
        this.dimView.setAlpha(0.0f);
        this.dimView.setVisibility(4);
    }

    public void expandSheet() {
        cancelCurrentAnimation();
        setSheetLayerTypeIfEnabled(0);
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, SHEET_TRANSLATION, new float[]{getMaxSheetTranslation()});
        anim.setDuration(ANIMATION_DURATION);
        anim.setInterpolator(this.animationInterpolator);
        anim.addListener(new C14652());
        anim.start();
        this.currentAnimator = anim;
        setState(State.EXPANDED);
    }

    public void peekSheet() {
        cancelCurrentAnimation();
        setSheetLayerTypeIfEnabled(2);
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, SHEET_TRANSLATION, new float[]{getPeekSheetTranslation()});
        anim.setDuration(ANIMATION_DURATION);
        anim.setInterpolator(this.animationInterpolator);
        anim.addListener(new C14663());
        anim.start();
        this.currentAnimator = anim;
        setState(State.PEEKED);
    }

    public float getPeekSheetTranslation() {
        return this.peek == 0.0f ? getDefaultPeekTranslation() : this.peek;
    }

    private float getDefaultPeekTranslation() {
        return hasTallerKeylineHeightSheet() ? this.peekKeyline : (float) getSheetView().getHeight();
    }

    public void setPeekSheetTranslation(float peek) {
        this.peek = peek;
    }

    public float getMaxSheetTranslation() {
        return hasFullHeightSheet() ? (float) (getHeight() - getPaddingTop()) : (float) getSheetView().getHeight();
    }

    public View getContentView() {
        return getChildCount() > 0 ? getChildAt(0) : null;
    }

    public View getSheetView() {
        return getChildCount() > 2 ? getChildAt(2) : null;
    }

    public void setContentView(View contentView) {
        super.addView(contentView, -1, generateDefaultLayoutParams());
        super.addView(this.dimView, -1, generateDefaultLayoutParams());
    }

    public void showWithSheetView(View sheetView) {
        showWithSheetView(sheetView, null);
    }

    public void showWithSheetView(final View sheetView, final ViewTransformer viewTransformer) {
        if (this.state != State.HIDDEN) {
            dismissSheet(new Runnable() {
                public void run() {
                    BottomSheetLayout.this.showWithSheetView(sheetView, viewTransformer);
                }
            });
            return;
        }
        setState(State.PREPARING);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) sheetView.getLayoutParams();
        if (params == null) {
            int i;
            if (this.isTablet) {
                i = -2;
            } else {
                i = -1;
            }
            params = new FrameLayout.LayoutParams(i, -2, 1);
        }
        if (this.isTablet && params.width == -2) {
            if (params.gravity == -1) {
                params.gravity = 1;
            }
            params.width = this.defaultSheetWidth;
            this.sheetStartX = (this.screenWidth - this.defaultSheetWidth) / 2;
            this.sheetEndX = this.screenWidth - this.sheetStartX;
        }
        super.addView(sheetView, -1, params);
        initializeSheetValues();
        this.viewTransformer = viewTransformer;
        getViewTreeObserver().addOnPreDrawListener(new C14695());
        this.currentSheetViewHeight = sheetView.getMeasuredHeight();
        this.sheetViewOnLayoutChangeListener = new C14706();
        sheetView.addOnLayoutChangeListener(this.sheetViewOnLayoutChangeListener);
    }

    public void dismissSheet() {
        dismissSheet(null);
    }

    private void dismissSheet(Runnable runAfterDismissThis) {
        if (this.state == State.HIDDEN) {
            this.runAfterDismiss = null;
            return;
        }
        this.runAfterDismiss = runAfterDismissThis;
        final View sheetView = getSheetView();
        sheetView.removeOnLayoutChangeListener(this.sheetViewOnLayoutChangeListener);
        cancelCurrentAnimation();
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, SHEET_TRANSLATION, new float[]{0.0f});
        anim.setDuration(ANIMATION_DURATION);
        anim.setInterpolator(this.animationInterpolator);
        anim.addListener(new CancelDetectionAnimationListener() {
            public void onAnimationEnd(Animator animation) {
                if (!this.canceled) {
                    BottomSheetLayout.this.currentAnimator = null;
                    BottomSheetLayout.this.setState(State.HIDDEN);
                    BottomSheetLayout.this.setSheetLayerTypeIfEnabled(0);
                    BottomSheetLayout.this.removeView(sheetView);
                    Iterator it = BottomSheetLayout.this.onSheetDismissedListeners.iterator();
                    while (it.hasNext()) {
                        ((OnSheetDismissedListener) it.next()).onDismissed(BottomSheetLayout.this);
                    }
                    BottomSheetLayout.this.viewTransformer = null;
                    if (BottomSheetLayout.this.runAfterDismiss != null) {
                        BottomSheetLayout.this.runAfterDismiss.run();
                        BottomSheetLayout.this.runAfterDismiss = null;
                    }
                }
            }
        });
        anim.start();
        this.currentAnimator = anim;
        this.sheetStartX = 0;
        this.sheetEndX = this.screenWidth;
    }

    public void setPeekOnDismiss(boolean peekOnDismiss) {
        this.peekOnDismiss = peekOnDismiss;
    }

    public boolean getPeekOnDismiss() {
        return this.peekOnDismiss;
    }

    public void setInterceptContentTouch(boolean interceptContentTouch) {
        this.interceptContentTouch = interceptContentTouch;
    }

    public boolean getInterceptContentTouch() {
        return this.interceptContentTouch;
    }

    public State getState() {
        return this.state;
    }

    public boolean isSheetShowing() {
        return this.state != State.HIDDEN;
    }

    public void setDefaultViewTransformer(ViewTransformer defaultViewTransformer) {
        this.defaultViewTransformer = defaultViewTransformer;
    }

    public void setShouldDimContentView(boolean shouldDimContentView) {
        this.shouldDimContentView = shouldDimContentView;
    }

    public boolean shouldDimContentView() {
        return this.shouldDimContentView;
    }

    public void setUseHardwareLayerWhileAnimating(boolean useHardwareLayerWhileAnimating) {
        this.useHardwareLayerWhileAnimating = useHardwareLayerWhileAnimating;
    }

    public void addOnSheetStateChangeListener(@NonNull OnSheetStateChangeListener onSheetStateChangeListener) {
        checkNotNull(onSheetStateChangeListener, "onSheetStateChangeListener == null");
        this.onSheetStateChangeListeners.add(onSheetStateChangeListener);
    }

    public void addOnSheetDismissedListener(@NonNull OnSheetDismissedListener onSheetDismissedListener) {
        checkNotNull(onSheetDismissedListener, "onSheetDismissedListener == null");
        this.onSheetDismissedListeners.add(onSheetDismissedListener);
    }

    public void removeOnSheetStateChangeListener(@NonNull OnSheetStateChangeListener onSheetStateChangeListener) {
        checkNotNull(onSheetStateChangeListener, "onSheetStateChangeListener == null");
        this.onSheetStateChangeListeners.remove(onSheetStateChangeListener);
    }

    public void removeOnSheetDismissedListener(@NonNull OnSheetDismissedListener onSheetDismissedListener) {
        checkNotNull(onSheetDismissedListener, "onSheetDismissedListener == null");
        this.onSheetDismissedListeners.remove(onSheetDismissedListener);
    }

    public static boolean isTablet(Context context) {
        return context.getResources().getBoolean(R.bool.bottomsheet_is_tablet);
    }

    public static int predictedDefaultWidth(Context context) {
        if (isTablet(context)) {
            return context.getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        }
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    private static <T> T checkNotNull(T value, String message) {
        if (value != null) {
            return value;
        }
        throw new NullPointerException(message);
    }
}
