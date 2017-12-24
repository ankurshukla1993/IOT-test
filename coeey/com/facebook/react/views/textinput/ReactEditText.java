package com.facebook.react.views.textinput;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.v4.view.GravityCompat;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.QwertyKeyListener;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.views.text.CustomStyleSpan;
import com.facebook.react.views.text.ReactTagSpan;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import io.reactivex.annotations.SchedulerSupport;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class ReactEditText extends EditText {
    private static final KeyListener sKeyListener = QwertyKeyListener.getInstanceForFullKeyboard();
    private boolean mBlurOnSubmit;
    private boolean mContainsImages;
    @Nullable
    private ContentSizeWatcher mContentSizeWatcher;
    private int mDefaultGravityHorizontal;
    private int mDefaultGravityVertical;
    private boolean mDetectScrollMovement = false;
    private boolean mDisableFullscreen;
    private final InputMethodManager mInputMethodManager;
    private boolean mIsJSSettingFocus;
    private boolean mIsSettingTextFromJS;
    private final InternalKeyListener mKeyListener;
    @Nullable
    private ArrayList<TextWatcher> mListeners;
    private int mMostRecentEventCount;
    private int mNativeEventCount;
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    @Nullable
    private String mReturnKeyType;
    @Nullable
    private SelectionWatcher mSelectionWatcher;
    private int mStagedInputType;
    @Nullable
    private TextWatcherDelegator mTextWatcherDelegator;

    public ReactEditText(Context context) {
        super(context);
        setFocusableInTouchMode(false);
        this.mInputMethodManager = (InputMethodManager) Assertions.assertNotNull(getContext().getSystemService("input_method"));
        this.mDefaultGravityHorizontal = getGravity() & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        this.mDefaultGravityVertical = getGravity() & 112;
        this.mNativeEventCount = 0;
        this.mMostRecentEventCount = 0;
        this.mIsSettingTextFromJS = false;
        this.mIsJSSettingFocus = false;
        this.mBlurOnSubmit = true;
        this.mDisableFullscreen = false;
        this.mListeners = null;
        this.mTextWatcherDelegator = null;
        this.mStagedInputType = getInputType();
        this.mKeyListener = new InternalKeyListener();
    }

    public boolean isLayoutRequested() {
        if (this.mContentSizeWatcher != null) {
            return isMultiline();
        }
        return false;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.mContentSizeWatcher != null) {
            this.mContentSizeWatcher.onLayout();
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                this.mDetectScrollMovement = true;
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 2:
                if (this.mDetectScrollMovement) {
                    if (!(canScrollVertically(-1) || canScrollVertically(1) || canScrollHorizontally(-1) || canScrollHorizontally(1))) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    this.mDetectScrollMovement = false;
                    break;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode != 66 || isMultiline()) {
            return super.onKeyUp(keyCode, event);
        }
        hideSoftKeyboard();
        return true;
    }

    public void clearFocus() {
        setFocusableInTouchMode(false);
        super.clearFocus();
        hideSoftKeyboard();
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (isFocused()) {
            return true;
        }
        if (!this.mIsJSSettingFocus) {
            return false;
        }
        setFocusableInTouchMode(true);
        boolean focused = super.requestFocus(direction, previouslyFocusedRect);
        showSoftKeyboard();
        return focused;
    }

    public void addTextChangedListener(TextWatcher watcher) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
            super.addTextChangedListener(getTextWatcherDelegator());
        }
        this.mListeners.add(watcher);
    }

    public void removeTextChangedListener(TextWatcher watcher) {
        if (this.mListeners != null) {
            this.mListeners.remove(watcher);
            if (this.mListeners.isEmpty()) {
                this.mListeners = null;
                super.removeTextChangedListener(getTextWatcherDelegator());
            }
        }
    }

    public void setContentSizeWatcher(ContentSizeWatcher contentSizeWatcher) {
        this.mContentSizeWatcher = contentSizeWatcher;
    }

    public void setSelection(int start, int end) {
        if (this.mMostRecentEventCount >= this.mNativeEventCount) {
            super.setSelection(start, end);
        }
    }

    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (this.mSelectionWatcher != null && hasFocus()) {
            this.mSelectionWatcher.onSelectionChanged(selStart, selEnd);
        }
    }

    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused && this.mSelectionWatcher != null) {
            this.mSelectionWatcher.onSelectionChanged(getSelectionStart(), getSelectionEnd());
        }
    }

    public void setSelectionWatcher(SelectionWatcher selectionWatcher) {
        this.mSelectionWatcher = selectionWatcher;
    }

    public void setBlurOnSubmit(boolean blurOnSubmit) {
        this.mBlurOnSubmit = blurOnSubmit;
    }

    public boolean getBlurOnSubmit() {
        return this.mBlurOnSubmit;
    }

    public void setDisableFullscreenUI(boolean disableFullscreenUI) {
        this.mDisableFullscreen = disableFullscreenUI;
        updateImeOptions();
    }

    public boolean getDisableFullscreenUI() {
        return this.mDisableFullscreen;
    }

    public void setReturnKeyType(String returnKeyType) {
        this.mReturnKeyType = returnKeyType;
        updateImeOptions();
    }

    public String getReturnKeyType() {
        return this.mReturnKeyType;
    }

    int getStagedInputType() {
        return this.mStagedInputType;
    }

    void setStagedInputType(int stagedInputType) {
        this.mStagedInputType = stagedInputType;
    }

    void commitStagedInputType() {
        if (getInputType() != this.mStagedInputType) {
            setInputType(this.mStagedInputType);
        }
    }

    public void setInputType(int type) {
        Typeface tf = super.getTypeface();
        super.setInputType(type);
        this.mStagedInputType = type;
        super.setTypeface(tf);
        this.mKeyListener.setInputType(type);
        setKeyListener(this.mKeyListener);
    }

    public void requestFocusFromJS() {
        this.mIsJSSettingFocus = true;
        requestFocus();
        this.mIsJSSettingFocus = false;
    }

    void clearFocusFromJS() {
        clearFocus();
    }

    public int incrementAndGetEventCounter() {
        int i = this.mNativeEventCount + 1;
        this.mNativeEventCount = i;
        return i;
    }

    public void maybeSetText(ReactTextUpdate reactTextUpdate) {
        this.mMostRecentEventCount = reactTextUpdate.getJsEventCounter();
        if (this.mMostRecentEventCount >= this.mNativeEventCount) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(reactTextUpdate.getText());
            manageSpans(spannableStringBuilder);
            this.mContainsImages = reactTextUpdate.containsImages();
            this.mIsSettingTextFromJS = true;
            getText().replace(0, length(), spannableStringBuilder);
            this.mIsSettingTextFromJS = false;
            if (VERSION.SDK_INT >= 23 && getBreakStrategy() != reactTextUpdate.getTextBreakStrategy()) {
                setBreakStrategy(reactTextUpdate.getTextBreakStrategy());
            }
        }
    }

    private void manageSpans(SpannableStringBuilder spannableStringBuilder) {
        Object[] spans = getText().getSpans(0, length(), Object.class);
        int spanIdx = 0;
        while (spanIdx < spans.length) {
            if (ForegroundColorSpan.class.isInstance(spans[spanIdx]) || BackgroundColorSpan.class.isInstance(spans[spanIdx]) || AbsoluteSizeSpan.class.isInstance(spans[spanIdx]) || CustomStyleSpan.class.isInstance(spans[spanIdx]) || ReactTagSpan.class.isInstance(spans[spanIdx])) {
                getText().removeSpan(spans[spanIdx]);
            }
            if ((getText().getSpanFlags(spans[spanIdx]) & 33) == 33) {
                Object span = spans[spanIdx];
                int spanStart = getText().getSpanStart(spans[spanIdx]);
                int spanEnd = getText().getSpanEnd(spans[spanIdx]);
                int spanFlags = getText().getSpanFlags(spans[spanIdx]);
                getText().removeSpan(spans[spanIdx]);
                if (sameTextForSpan(getText(), spannableStringBuilder, spanStart, spanEnd)) {
                    spannableStringBuilder.setSpan(span, spanStart, spanEnd, spanFlags);
                }
            }
            spanIdx++;
        }
    }

    private static boolean sameTextForSpan(Editable oldText, SpannableStringBuilder newText, int start, int end) {
        if (start > newText.length() || end > newText.length()) {
            return false;
        }
        for (int charIdx = start; charIdx < end; charIdx++) {
            if (oldText.charAt(charIdx) != newText.charAt(charIdx)) {
                return false;
            }
        }
        return true;
    }

    private boolean showSoftKeyboard() {
        return this.mInputMethodManager.showSoftInput(this, 0);
    }

    private void hideSoftKeyboard() {
        this.mInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    private TextWatcherDelegator getTextWatcherDelegator() {
        if (this.mTextWatcherDelegator == null) {
            this.mTextWatcherDelegator = new TextWatcherDelegator(this, null);
        }
        return this.mTextWatcherDelegator;
    }

    private boolean isMultiline() {
        return (getInputType() & 131072) != 0;
    }

    void setGravityHorizontal(int gravityHorizontal) {
        if (gravityHorizontal == 0) {
            gravityHorizontal = this.mDefaultGravityHorizontal;
        }
        setGravity(((getGravity() & -8) & -8388616) | gravityHorizontal);
    }

    void setGravityVertical(int gravityVertical) {
        if (gravityVertical == 0) {
            gravityVertical = this.mDefaultGravityVertical;
        }
        setGravity((getGravity() & -113) | gravityVertical);
    }

    private void updateImeOptions() {
        int returnKeyFlag = 6;
        if (this.mReturnKeyType != null) {
            String str = this.mReturnKeyType;
            Object obj = -1;
            switch (str.hashCode()) {
                case -1273775369:
                    if (str.equals("previous")) {
                        obj = 3;
                        break;
                    }
                    break;
                case -906336856:
                    if (str.equals(Event.SEARCH)) {
                        obj = 4;
                        break;
                    }
                    break;
                case 3304:
                    if (str.equals("go")) {
                        obj = null;
                        break;
                    }
                    break;
                case 3089282:
                    if (str.equals("done")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 3377907:
                    if (str.equals("next")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 3387192:
                    if (str.equals(SchedulerSupport.NONE)) {
                        obj = 2;
                        break;
                    }
                    break;
                case 3526536:
                    if (str.equals("send")) {
                        obj = 5;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    returnKeyFlag = 2;
                    break;
                case 1:
                    returnKeyFlag = 5;
                    break;
                case 2:
                    returnKeyFlag = 1;
                    break;
                case 3:
                    returnKeyFlag = 7;
                    break;
                case 4:
                    returnKeyFlag = 3;
                    break;
                case 5:
                    returnKeyFlag = 4;
                    break;
                case 6:
                    returnKeyFlag = 6;
                    break;
            }
        }
        if (this.mDisableFullscreen) {
            setImeOptions(33554432 | returnKeyFlag);
        } else {
            setImeOptions(returnKeyFlag);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        int i = 0;
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                if (spans[i].getDrawable() == drawable) {
                    return true;
                }
                i++;
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        int i = 0;
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                if (spans[i].getDrawable() == drawable) {
                    invalidate();
                }
                i++;
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        int i = 0;
        super.onDetachedFromWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                spans[i].onDetachedFromWindow();
                i++;
            }
        }
    }

    public void onStartTemporaryDetach() {
        int i = 0;
        super.onStartTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                spans[i].onStartTemporaryDetach();
                i++;
            }
        }
    }

    public void onAttachedToWindow() {
        int i = 0;
        super.onAttachedToWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                spans[i].onAttachedToWindow();
                i++;
            }
        }
    }

    public void onFinishTemporaryDetach() {
        int i = 0;
        super.onFinishTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                spans[i].onFinishTemporaryDetach();
                i++;
            }
        }
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.mReactBackgroundDrawable != null) {
            getOrCreateReactViewBackground().setColor(color);
        }
    }

    public void setBorderWidth(int position, float width) {
        getOrCreateReactViewBackground().setBorderWidth(position, width);
    }

    public void setBorderColor(int position, float color, float alpha) {
        getOrCreateReactViewBackground().setBorderColor(position, color, alpha);
    }

    public void setBorderRadius(float borderRadius) {
        getOrCreateReactViewBackground().setRadius(borderRadius);
    }

    public void setBorderRadius(float borderRadius, int position) {
        getOrCreateReactViewBackground().setRadius(borderRadius, position);
    }

    public void setBorderStyle(@Nullable String style) {
        getOrCreateReactViewBackground().setBorderStyle(style);
    }

    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable();
            Drawable backgroundDrawable = getBackground();
            super.setBackground(null);
            if (backgroundDrawable == null) {
                super.setBackground(this.mReactBackgroundDrawable);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, backgroundDrawable}));
            }
        }
        return this.mReactBackgroundDrawable;
    }
}
