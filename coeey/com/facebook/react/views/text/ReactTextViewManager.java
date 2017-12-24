package com.facebook.react.views.text;

import android.text.TextUtils.TruncateAt;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.yoga.YogaConstants;
import javax.annotation.Nullable;

@ReactModule(name = "RCTText")
public class ReactTextViewManager extends BaseViewManager<ReactTextView, ReactTextShadowNode> {
    @VisibleForTesting
    public static final String REACT_CLASS = "RCTText";
    private static final int[] SPACING_TYPES = new int[]{8, 0, 2, 1, 3};

    public String getName() {
        return REACT_CLASS;
    }

    public ReactTextView createViewInstance(ThemedReactContext context) {
        return new ReactTextView(context);
    }

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(ReactTextView view, int numberOfLines) {
        view.setNumberOfLines(numberOfLines);
    }

    @ReactProp(name = "ellipsizeMode")
    public void setEllipsizeMode(ReactTextView view, @Nullable String ellipsizeMode) {
        if (ellipsizeMode == null || ellipsizeMode.equals("tail")) {
            view.setEllipsizeLocation(TruncateAt.END);
        } else if (ellipsizeMode.equals("head")) {
            view.setEllipsizeLocation(TruncateAt.START);
        } else if (ellipsizeMode.equals("middle")) {
            view.setEllipsizeLocation(TruncateAt.MIDDLE);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid ellipsizeMode: " + ellipsizeMode);
        }
    }

    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(ReactTextView view, @Nullable String textAlignVertical) {
        if (textAlignVertical == null || ReactScrollViewHelper.AUTO.equals(textAlignVertical)) {
            view.setGravityVertical(0);
        } else if (ViewProps.TOP.equals(textAlignVertical)) {
            view.setGravityVertical(48);
        } else if (ViewProps.BOTTOM.equals(textAlignVertical)) {
            view.setGravityVertical(80);
        } else if ("center".equals(textAlignVertical)) {
            view.setGravityVertical(16);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlignVertical: " + textAlignVertical);
        }
    }

    @ReactProp(name = "selectable")
    public void setSelectable(ReactTextView view, boolean isSelectable) {
        view.setTextIsSelectable(isSelectable);
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactTextView view, @Nullable Integer color) {
        if (color == null) {
            view.setHighlightColor(DefaultStyleValuesUtil.getDefaultTextColorHighlight(view.getContext()));
        } else {
            view.setHighlightColor(color.intValue());
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactTextView view, int index, float borderRadius) {
        if (!YogaConstants.isUndefined(borderRadius)) {
            borderRadius = PixelUtil.toPixelFromDIP(borderRadius);
        }
        if (index == 0) {
            view.setBorderRadius(borderRadius);
        } else {
            view.setBorderRadius(borderRadius, index - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactTextView view, @Nullable String borderStyle) {
        view.setBorderStyle(borderStyle);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactTextView view, int index, float width) {
        if (!YogaConstants.isUndefined(width)) {
            width = PixelUtil.toPixelFromDIP(width);
        }
        view.setBorderWidth(SPACING_TYPES[index], width);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactTextView view, int index, Integer color) {
        float alphaComponent = YogaConstants.UNDEFINED;
        float rgbComponent = color == null ? YogaConstants.UNDEFINED : (float) (color.intValue() & 16777215);
        if (color != null) {
            alphaComponent = (float) (color.intValue() >>> 24);
        }
        view.setBorderColor(SPACING_TYPES[index], rgbComponent, alphaComponent);
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(ReactTextView view, boolean includepad) {
        view.setIncludeFontPadding(includepad);
    }

    public void updateExtraData(ReactTextView view, Object extraData) {
        ReactTextUpdate update = (ReactTextUpdate) extraData;
        if (update.containsImages()) {
            TextInlineImageSpan.possiblyUpdateInlineImageSpans(update.getText(), view);
        }
        view.setText(update);
    }

    public ReactTextShadowNode createShadowNodeInstance() {
        return new ReactTextShadowNode();
    }

    public Class<ReactTextShadowNode> getShadowNodeClass() {
        return ReactTextShadowNode.class;
    }

    protected void onAfterUpdateTransaction(ReactTextView view) {
        super.onAfterUpdateTransaction(view);
        view.updateView();
    }
}
