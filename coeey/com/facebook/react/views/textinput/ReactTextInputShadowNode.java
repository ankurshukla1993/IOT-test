package com.facebook.react.views.textinput;

import android.os.Build.VERSION;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewDefaults;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextShadowNode;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.view.MeasureUtil;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNodeAPI;
import javax.annotation.Nullable;

@VisibleForTesting
public class ReactTextInputShadowNode extends ReactTextShadowNode implements YogaMeasureFunction {
    @Nullable
    private EditText mEditText;
    private int mJsEventCount = -1;

    public ReactTextInputShadowNode() {
        if (VERSION.SDK_INT < 23) {
            this.mTextBreakStrategy = 0;
            setMeasureFunction(this);
        } else {
            this.mTextBreakStrategy = 0;
            setMeasureFunction(this);
        }
    }

    public void setThemedContext(ThemedReactContext themedContext) {
        super.setThemedContext(themedContext);
        this.mEditText = new EditText(getThemedContext());
        this.mEditText.setLayoutParams(new LayoutParams(-2, -2));
        setDefaultPadding(4, (float) this.mEditText.getPaddingStart());
        setDefaultPadding(1, (float) this.mEditText.getPaddingTop());
        setDefaultPadding(5, (float) this.mEditText.getPaddingEnd());
        setDefaultPadding(3, (float) this.mEditText.getPaddingBottom());
        this.mEditText.setPadding(0, 0, 0, 0);
    }

    public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        EditText editText = (EditText) Assertions.assertNotNull(this.mEditText);
        editText.setTextSize(0, this.mFontSize == -1 ? (float) ((int) Math.ceil((double) PixelUtil.toPixelFromSP(ViewDefaults.FONT_SIZE_SP))) : (float) this.mFontSize);
        if (this.mNumberOfLines != -1) {
            editText.setLines(this.mNumberOfLines);
        }
        if (VERSION.SDK_INT >= 23 && editText.getBreakStrategy() != this.mTextBreakStrategy) {
            editText.setBreakStrategy(this.mTextBreakStrategy);
        }
        editText.measure(MeasureUtil.getMeasureSpec(width, widthMode), MeasureUtil.getMeasureSpec(height, heightMode));
        return YogaMeasureOutput.make(editText.getMeasuredWidth(), editText.getMeasuredHeight());
    }

    public void onBeforeLayout() {
    }

    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(int mostRecentEventCount) {
        this.mJsEventCount = mostRecentEventCount;
    }

    public void setTextBreakStrategy(@Nullable String textBreakStrategy) {
        if (VERSION.SDK_INT >= 23) {
            if (textBreakStrategy == null || "simple".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 0;
            } else if ("highQuality".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 1;
            } else if ("balanced".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 2;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + textBreakStrategy);
            }
        }
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
        super.onCollectExtraUpdates(uiViewOperationQueue);
        if (this.mJsEventCount != -1) {
            uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(ReactTextShadowNode.fromTextCSSNode(this), this.mJsEventCount, this.mContainsImages, getPadding(0), getPadding(1), getPadding(2), getPadding(3), this.mTextAlign, this.mTextBreakStrategy));
        }
    }

    public void setPadding(int spacingType, float padding) {
        super.setPadding(spacingType, padding);
        markUpdated();
    }
}
