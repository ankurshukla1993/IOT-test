package com.facebook.react.uimanager;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaWrap;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Locale;
import javax.annotation.Nullable;

public class LayoutShadowNode extends ReactShadowNode {
    private static boolean dynamicIsPercent(Dynamic dynamic) {
        return dynamic.getType() == ReadableType.String && dynamic.asString().endsWith("%");
    }

    private static float getDynamicAsPercent(Dynamic dynamic) {
        String value = dynamic.asString();
        return Float.parseFloat(value.substring(0, value.length() - 1));
    }

    private static float getDynamicAsFloat(Dynamic dynamic) {
        return PixelUtil.toPixelFromDIP(dynamic.asDouble());
    }

    private static boolean isNull(Dynamic d) {
        return d == null || d.isNull();
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic width) {
        if (!isVirtual()) {
            if (isNull(width) || !dynamicIsPercent(width)) {
                setStyleWidth(isNull(width) ? YogaConstants.UNDEFINED : getDynamicAsFloat(width));
            } else {
                setStyleWidthPercent(getDynamicAsPercent(width));
            }
            width.recycle();
        }
    }

    @ReactProp(name = "minWidth")
    public void setMinWidth(Dynamic minWidth) {
        if (!isVirtual()) {
            if (isNull(minWidth) || !dynamicIsPercent(minWidth)) {
                setStyleMinWidth(isNull(minWidth) ? YogaConstants.UNDEFINED : getDynamicAsFloat(minWidth));
            } else {
                setStyleMinWidthPercent(getDynamicAsPercent(minWidth));
            }
            minWidth.recycle();
        }
    }

    @ReactProp(name = "maxWidth")
    public void setMaxWidth(Dynamic maxWidth) {
        if (!isVirtual()) {
            if (isNull(maxWidth) || !dynamicIsPercent(maxWidth)) {
                setStyleMaxWidth(isNull(maxWidth) ? YogaConstants.UNDEFINED : getDynamicAsFloat(maxWidth));
            } else {
                setStyleMaxWidthPercent(getDynamicAsPercent(maxWidth));
            }
            maxWidth.recycle();
        }
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic height) {
        if (!isVirtual()) {
            if (isNull(height) || !dynamicIsPercent(height)) {
                setStyleHeight(isNull(height) ? YogaConstants.UNDEFINED : getDynamicAsFloat(height));
            } else {
                setStyleHeightPercent(getDynamicAsPercent(height));
            }
            height.recycle();
        }
    }

    @ReactProp(name = "minHeight")
    public void setMinHeight(Dynamic minHeight) {
        if (!isVirtual()) {
            if (isNull(minHeight) || !dynamicIsPercent(minHeight)) {
                setStyleMinHeight(isNull(minHeight) ? YogaConstants.UNDEFINED : getDynamicAsFloat(minHeight));
            } else {
                setStyleMinHeightPercent(getDynamicAsPercent(minHeight));
            }
            minHeight.recycle();
        }
    }

    @ReactProp(name = "maxHeight")
    public void setMaxHeight(Dynamic maxHeight) {
        if (!isVirtual()) {
            if (isNull(maxHeight) || !dynamicIsPercent(maxHeight)) {
                setStyleMaxHeight(isNull(maxHeight) ? YogaConstants.UNDEFINED : getDynamicAsFloat(maxHeight));
            } else {
                setStyleMaxHeightPercent(getDynamicAsPercent(maxHeight));
            }
            maxHeight.recycle();
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flex")
    public void setFlex(float flex) {
        if (!isVirtual()) {
            super.setFlex(flex);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flexGrow")
    public void setFlexGrow(float flexGrow) {
        if (!isVirtual()) {
            super.setFlexGrow(flexGrow);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flexShrink")
    public void setFlexShrink(float flexShrink) {
        if (!isVirtual()) {
            super.setFlexShrink(flexShrink);
        }
    }

    @ReactProp(name = "flexBasis")
    public void setFlexBasis(Dynamic flexBasis) {
        if (!isVirtual()) {
            if (isNull(flexBasis) || !dynamicIsPercent(flexBasis)) {
                setFlexBasis(isNull(flexBasis) ? 0.0f : getDynamicAsFloat(flexBasis));
            } else {
                setFlexBasisPercent(getDynamicAsPercent(flexBasis));
            }
            flexBasis.recycle();
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "aspectRatio")
    public void setAspectRatio(float aspectRatio) {
        setStyleAspectRatio(aspectRatio);
    }

    @ReactProp(name = "flexDirection")
    public void setFlexDirection(@Nullable String flexDirection) {
        if (!isVirtual()) {
            YogaFlexDirection yogaFlexDirection;
            if (flexDirection == null) {
                yogaFlexDirection = YogaFlexDirection.COLUMN;
            } else {
                yogaFlexDirection = YogaFlexDirection.valueOf(flexDirection.toUpperCase(Locale.US).replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR));
            }
            setFlexDirection(yogaFlexDirection);
        }
    }

    @ReactProp(name = "flexWrap")
    public void setFlexWrap(@Nullable String flexWrap) {
        if (!isVirtual()) {
            if (flexWrap == null || flexWrap.equals("nowrap")) {
                setFlexWrap(YogaWrap.NO_WRAP);
            } else if (flexWrap.equals("wrap")) {
                setFlexWrap(YogaWrap.WRAP);
            } else {
                throw new IllegalArgumentException("Unknown flexWrap value: " + flexWrap);
            }
        }
    }

    @ReactProp(name = "alignSelf")
    public void setAlignSelf(@Nullable String alignSelf) {
        if (!isVirtual()) {
            setAlignSelf(alignSelf == null ? YogaAlign.AUTO : YogaAlign.valueOf(alignSelf.toUpperCase(Locale.US).replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)));
        }
    }

    @ReactProp(name = "alignItems")
    public void setAlignItems(@Nullable String alignItems) {
        if (!isVirtual()) {
            YogaAlign yogaAlign;
            if (alignItems == null) {
                yogaAlign = YogaAlign.STRETCH;
            } else {
                yogaAlign = YogaAlign.valueOf(alignItems.toUpperCase(Locale.US).replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR));
            }
            setAlignItems(yogaAlign);
        }
    }

    @ReactProp(name = "justifyContent")
    public void setJustifyContent(@Nullable String justifyContent) {
        if (!isVirtual()) {
            setJustifyContent(justifyContent == null ? YogaJustify.FLEX_START : YogaJustify.valueOf(justifyContent.toUpperCase(Locale.US).replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)));
        }
    }

    @ReactProp(name = "overflow")
    public void setOverflow(@Nullable String overflow) {
        if (!isVirtual()) {
            setOverflow(overflow == null ? YogaOverflow.VISIBLE : YogaOverflow.valueOf(overflow.toUpperCase(Locale.US).replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)));
        }
    }

    @ReactPropGroup(names = {"margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom"})
    public void setMargins(int index, Dynamic margin) {
        if (!isVirtual()) {
            if (isNull(margin) || !dynamicIsPercent(margin)) {
                setMargin(ViewProps.PADDING_MARGIN_SPACING_TYPES[index], isNull(margin) ? YogaConstants.UNDEFINED : getDynamicAsFloat(margin));
            } else {
                setMarginPercent(ViewProps.PADDING_MARGIN_SPACING_TYPES[index], getDynamicAsPercent(margin));
            }
            margin.recycle();
        }
    }

    @ReactPropGroup(names = {"padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom"})
    public void setPaddings(int index, Dynamic padding) {
        if (!isVirtual()) {
            if (isNull(padding) || !dynamicIsPercent(padding)) {
                setPadding(ViewProps.PADDING_MARGIN_SPACING_TYPES[index], isNull(padding) ? YogaConstants.UNDEFINED : getDynamicAsFloat(padding));
            } else {
                setPaddingPercent(ViewProps.PADDING_MARGIN_SPACING_TYPES[index], getDynamicAsPercent(padding));
            }
            padding.recycle();
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidths(int index, float borderWidth) {
        if (!isVirtual()) {
            setBorder(ViewProps.BORDER_SPACING_TYPES[index], PixelUtil.toPixelFromDIP(borderWidth));
        }
    }

    @ReactPropGroup(names = {"left", "right", "top", "bottom"})
    public void setPositionValues(int index, Dynamic position) {
        if (!isVirtual()) {
            if (isNull(position) || !dynamicIsPercent(position)) {
                setPosition(ViewProps.POSITION_SPACING_TYPES[index], isNull(position) ? YogaConstants.UNDEFINED : getDynamicAsFloat(position));
            } else {
                setPositionPercent(ViewProps.POSITION_SPACING_TYPES[index], getDynamicAsPercent(position));
            }
            position.recycle();
        }
    }

    @ReactProp(name = "position")
    public void setPosition(@Nullable String position) {
        if (!isVirtual()) {
            YogaPositionType positionType;
            if (position == null) {
                positionType = YogaPositionType.RELATIVE;
            } else {
                positionType = YogaPositionType.valueOf(position.toUpperCase(Locale.US));
            }
            setPositionType(positionType);
        }
    }

    @ReactProp(name = "onLayout")
    public void setShouldNotifyOnLayout(boolean shouldNotifyOnLayout) {
        super.setShouldNotifyOnLayout(shouldNotifyOnLayout);
    }
}
