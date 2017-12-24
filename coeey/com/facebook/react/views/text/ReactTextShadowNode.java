package com.facebook.react.views.text;

import android.os.Build.VERSION;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewDefaults;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaMeasureFunction;
import com.lifesense.ble.bean.DeviceTypeConstants;
import humanize.util.Constants;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class ReactTextShadowNode extends LayoutShadowNode {
    public static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String INLINE_IMAGE_PLACEHOLDER = "I";
    public static final String PROP_SHADOW_COLOR = "textShadowColor";
    public static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    public static final String PROP_SHADOW_OFFSET_HEIGHT = "height";
    public static final String PROP_SHADOW_OFFSET_WIDTH = "width";
    public static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    @VisibleForTesting
    public static final String PROP_TEXT = "text";
    public static final int UNSET = -1;
    private static final TextPaint sTextPaintInstance = new TextPaint();
    private boolean mAllowFontScaling = true;
    private int mBackgroundColor;
    private int mColor;
    protected boolean mContainsImages;
    @Nullable
    private String mFontFamily;
    protected int mFontSize = -1;
    protected float mFontSizeInput = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
    private int mFontStyle;
    private int mFontWeight;
    private float mHeightOfTallestInlineImage;
    private boolean mIsBackgroundColorSet = false;
    private boolean mIsColorSet = false;
    private boolean mIsLineThroughTextDecorationSet;
    private boolean mIsUnderlineTextDecorationSet;
    private float mLineHeight = YogaConstants.UNDEFINED;
    protected int mLineHeightInput = -1;
    protected int mNumberOfLines = -1;
    @Nullable
    private Spannable mPreparedSpannableText;
    @Nullable
    private String mText;
    protected int mTextAlign = 0;
    protected int mTextBreakStrategy;
    private final YogaMeasureFunction mTextMeasureFunction = new 1(this);
    private int mTextShadowColor;
    private float mTextShadowOffsetDx;
    private float mTextShadowOffsetDy;
    private float mTextShadowRadius;

    static {
        sTextPaintInstance.setFlags(1);
    }

    private static void buildSpannedFromTextCSSNode(ReactTextShadowNode textShadowNode, SpannableStringBuilder sb, List<SetSpanOperation> ops) {
        int start = sb.length();
        if (textShadowNode.mText != null) {
            sb.append(textShadowNode.mText);
        }
        int length = textShadowNode.getChildCount();
        for (int i = 0; i < length; i++) {
            ReactShadowNode child = textShadowNode.getChildAt(i);
            if (child instanceof ReactTextShadowNode) {
                buildSpannedFromTextCSSNode((ReactTextShadowNode) child, sb, ops);
            } else if (child instanceof ReactTextInlineImageShadowNode) {
                sb.append(INLINE_IMAGE_PLACEHOLDER);
                ops.add(new SetSpanOperation(sb.length() - INLINE_IMAGE_PLACEHOLDER.length(), sb.length(), ((ReactTextInlineImageShadowNode) child).buildInlineImageSpan()));
            } else {
                throw new IllegalViewOperationException("Unexpected view type nested under text node: " + child.getClass());
            }
            child.markUpdateSeen();
        }
        int end = sb.length();
        if (end >= start) {
            if (textShadowNode.mIsColorSet) {
                ops.add(new SetSpanOperation(start, end, new ForegroundColorSpan(textShadowNode.mColor)));
            }
            if (textShadowNode.mIsBackgroundColorSet) {
                ops.add(new SetSpanOperation(start, end, new BackgroundColorSpan(textShadowNode.mBackgroundColor)));
            }
            if (textShadowNode.mFontSize != -1) {
                ops.add(new SetSpanOperation(start, end, new AbsoluteSizeSpan(textShadowNode.mFontSize)));
            }
            if (!(textShadowNode.mFontStyle == -1 && textShadowNode.mFontWeight == -1 && textShadowNode.mFontFamily == null)) {
                ops.add(new SetSpanOperation(start, end, new CustomStyleSpan(textShadowNode.mFontStyle, textShadowNode.mFontWeight, textShadowNode.mFontFamily, textShadowNode.getThemedContext().getAssets())));
            }
            if (textShadowNode.mIsUnderlineTextDecorationSet) {
                ops.add(new SetSpanOperation(start, end, new UnderlineSpan()));
            }
            if (textShadowNode.mIsLineThroughTextDecorationSet) {
                ops.add(new SetSpanOperation(start, end, new StrikethroughSpan()));
            }
            if (!(textShadowNode.mTextShadowOffsetDx == 0.0f && textShadowNode.mTextShadowOffsetDy == 0.0f)) {
                ops.add(new SetSpanOperation(start, end, new ShadowStyleSpan(textShadowNode.mTextShadowOffsetDx, textShadowNode.mTextShadowOffsetDy, textShadowNode.mTextShadowRadius, textShadowNode.mTextShadowColor)));
            }
            if (!Float.isNaN(textShadowNode.getEffectiveLineHeight())) {
                ops.add(new SetSpanOperation(start, end, new CustomLineHeightSpan(textShadowNode.getEffectiveLineHeight())));
            }
            ops.add(new SetSpanOperation(start, end, new ReactTagSpan(textShadowNode.getReactTag())));
        }
    }

    protected static Spannable fromTextCSSNode(ReactTextShadowNode textCSSNode) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        List<SetSpanOperation> ops = new ArrayList();
        buildSpannedFromTextCSSNode(textCSSNode, sb, ops);
        if (textCSSNode.mFontSize == -1) {
            int ceil;
            if (textCSSNode.mAllowFontScaling) {
                ceil = (int) Math.ceil((double) PixelUtil.toPixelFromSP(ViewDefaults.FONT_SIZE_SP));
            } else {
                ceil = (int) Math.ceil((double) PixelUtil.toPixelFromDIP(ViewDefaults.FONT_SIZE_SP));
            }
            sb.setSpan(new AbsoluteSizeSpan(ceil), 0, sb.length(), 17);
        }
        textCSSNode.mContainsImages = false;
        textCSSNode.mHeightOfTallestInlineImage = YogaConstants.UNDEFINED;
        for (int i = ops.size() - 1; i >= 0; i--) {
            SetSpanOperation op = (SetSpanOperation) ops.get(i);
            if (op.what instanceof TextInlineImageSpan) {
                int height = ((TextInlineImageSpan) op.what).getHeight();
                textCSSNode.mContainsImages = true;
                if (Float.isNaN(textCSSNode.mHeightOfTallestInlineImage) || ((float) height) > textCSSNode.mHeightOfTallestInlineImage) {
                    textCSSNode.mHeightOfTallestInlineImage = (float) height;
                }
            }
            op.execute(sb);
        }
        return sb;
    }

    private static int parseNumericFontWeight(String fontWeightString) {
        return (fontWeightString.length() != 3 || !fontWeightString.endsWith(DeviceTypeConstants.UNKNOW) || fontWeightString.charAt(0) > '9' || fontWeightString.charAt(0) < '1') ? -1 : (fontWeightString.charAt(0) - 48) * 100;
    }

    public ReactTextShadowNode() {
        int i = 1;
        if (VERSION.SDK_INT < 23) {
            i = 0;
        }
        this.mTextBreakStrategy = i;
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        this.mTextShadowRadius = FlexItem.FLEX_SHRINK_DEFAULT;
        this.mTextShadowColor = DEFAULT_TEXT_SHADOW_COLOR;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        this.mFontStyle = -1;
        this.mFontWeight = -1;
        this.mFontFamily = null;
        this.mText = null;
        this.mContainsImages = false;
        this.mHeightOfTallestInlineImage = YogaConstants.UNDEFINED;
        if (!isVirtual()) {
            setMeasureFunction(this.mTextMeasureFunction);
        }
    }

    public float getEffectiveLineHeight() {
        boolean useInlineViewHeight = (Float.isNaN(this.mLineHeight) || Float.isNaN(this.mHeightOfTallestInlineImage) || this.mHeightOfTallestInlineImage <= this.mLineHeight) ? false : true;
        return useInlineViewHeight ? this.mHeightOfTallestInlineImage : this.mLineHeight;
    }

    private int getTextAlign() {
        int textAlign = this.mTextAlign;
        if (getLayoutDirection() != YogaDirection.RTL) {
            return textAlign;
        }
        if (textAlign == 5) {
            return 3;
        }
        if (textAlign == 3) {
            return 5;
        }
        return textAlign;
    }

    public void onBeforeLayout() {
        if (!isVirtual()) {
            this.mPreparedSpannableText = fromTextCSSNode(this);
            markUpdated();
        }
    }

    public void markUpdated() {
        super.markUpdated();
        if (!isVirtual()) {
            super.dirty();
        }
    }

    @ReactProp(name = "text")
    public void setText(@Nullable String text) {
        this.mText = text;
        markUpdated();
    }

    @ReactProp(defaultInt = -1, name = "numberOfLines")
    public void setNumberOfLines(int numberOfLines) {
        if (numberOfLines == 0) {
            numberOfLines = -1;
        }
        this.mNumberOfLines = numberOfLines;
        markUpdated();
    }

    @ReactProp(defaultInt = -1, name = "lineHeight")
    public void setLineHeight(int lineHeight) {
        this.mLineHeightInput = lineHeight;
        if (lineHeight == -1) {
            this.mLineHeight = YogaConstants.UNDEFINED;
        } else {
            this.mLineHeight = this.mAllowFontScaling ? PixelUtil.toPixelFromSP((float) lineHeight) : PixelUtil.toPixelFromDIP((float) lineHeight);
        }
        markUpdated();
    }

    @ReactProp(defaultBoolean = true, name = "allowFontScaling")
    public void setAllowFontScaling(boolean allowFontScaling) {
        if (allowFontScaling != this.mAllowFontScaling) {
            this.mAllowFontScaling = allowFontScaling;
            setFontSize(this.mFontSizeInput);
            setLineHeight(this.mLineHeightInput);
            markUpdated();
        }
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(@Nullable String textAlign) {
        if (textAlign == null || ReactScrollViewHelper.AUTO.equals(textAlign)) {
            this.mTextAlign = 0;
        } else if (ViewProps.LEFT.equals(textAlign)) {
            this.mTextAlign = 3;
        } else if (ViewProps.RIGHT.equals(textAlign)) {
            this.mTextAlign = 5;
        } else if ("center".equals(textAlign)) {
            this.mTextAlign = 1;
        } else if ("justify".equals(textAlign)) {
            this.mTextAlign = 3;
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + textAlign);
        }
        markUpdated();
    }

    @ReactProp(defaultFloat = -1.0f, name = "fontSize")
    public void setFontSize(float fontSize) {
        this.mFontSizeInput = fontSize;
        if (fontSize != FlexItem.FLEX_BASIS_PERCENT_DEFAULT) {
            if (this.mAllowFontScaling) {
                fontSize = (float) Math.ceil((double) PixelUtil.toPixelFromSP(fontSize));
            } else {
                fontSize = (float) Math.ceil((double) PixelUtil.toPixelFromDIP(fontSize));
            }
        }
        this.mFontSize = (int) fontSize;
        markUpdated();
    }

    @ReactProp(name = "color")
    public void setColor(@Nullable Integer color) {
        this.mIsColorSet = color != null;
        if (this.mIsColorSet) {
            this.mColor = color.intValue();
        }
        markUpdated();
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(Integer color) {
        if (!isVirtualAnchor()) {
            this.mIsBackgroundColorSet = color != null;
            if (this.mIsBackgroundColorSet) {
                this.mBackgroundColor = color.intValue();
            }
            markUpdated();
        }
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(@Nullable String fontFamily) {
        this.mFontFamily = fontFamily;
        markUpdated();
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(@Nullable String fontWeightString) {
        int fontWeightNumeric;
        if (fontWeightString != null) {
            fontWeightNumeric = parseNumericFontWeight(fontWeightString);
        } else {
            fontWeightNumeric = -1;
        }
        int fontWeight = -1;
        if (fontWeightNumeric >= 500 || "bold".equals(fontWeightString)) {
            fontWeight = 1;
        } else if ("normal".equals(fontWeightString) || (fontWeightNumeric != -1 && fontWeightNumeric < 500)) {
            fontWeight = 0;
        }
        if (fontWeight != this.mFontWeight) {
            this.mFontWeight = fontWeight;
            markUpdated();
        }
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(@Nullable String fontStyleString) {
        int fontStyle = -1;
        if ("italic".equals(fontStyleString)) {
            fontStyle = 2;
        } else if ("normal".equals(fontStyleString)) {
            fontStyle = 0;
        }
        if (fontStyle != this.mFontStyle) {
            this.mFontStyle = fontStyle;
            markUpdated();
        }
    }

    @ReactProp(name = "textDecorationLine")
    public void setTextDecorationLine(@Nullable String textDecorationLineString) {
        int i = 0;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (textDecorationLineString != null) {
            String[] split = textDecorationLineString.split(Constants.SPACE);
            int length = split.length;
            while (i < length) {
                String textDecorationLineSubString = split[i];
                if ("underline".equals(textDecorationLineSubString)) {
                    this.mIsUnderlineTextDecorationSet = true;
                } else if ("line-through".equals(textDecorationLineSubString)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
                i++;
            }
        }
        markUpdated();
    }

    @ReactProp(name = "textBreakStrategy")
    public void setTextBreakStrategy(@Nullable String textBreakStrategy) {
        if (VERSION.SDK_INT >= 23) {
            if (textBreakStrategy == null || "highQuality".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 1;
            } else if ("simple".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 0;
            } else if ("balanced".equals(textBreakStrategy)) {
                this.mTextBreakStrategy = 2;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + textBreakStrategy);
            }
            markUpdated();
        }
    }

    @ReactProp(name = "textShadowOffset")
    public void setTextShadowOffset(ReadableMap offsetMap) {
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (offsetMap != null) {
            if (offsetMap.hasKey("width") && !offsetMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(offsetMap.getDouble("width"));
            }
            if (offsetMap.hasKey("height") && !offsetMap.isNull("height")) {
                this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(offsetMap.getDouble("height"));
            }
        }
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "textShadowRadius")
    public void setTextShadowRadius(float textShadowRadius) {
        if (textShadowRadius != this.mTextShadowRadius) {
            this.mTextShadowRadius = textShadowRadius;
            markUpdated();
        }
    }

    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public void setTextShadowColor(int textShadowColor) {
        if (textShadowColor != this.mTextShadowColor) {
            this.mTextShadowColor = textShadowColor;
            markUpdated();
        }
    }

    public boolean isVirtualAnchor() {
        return !isVirtual();
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
        if (!isVirtual()) {
            super.onCollectExtraUpdates(uiViewOperationQueue);
            if (this.mPreparedSpannableText != null) {
                uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(this.mPreparedSpannableText, -1, this.mContainsImages, getPadding(4), getPadding(1), getPadding(5), getPadding(3), getTextAlign(), this.mTextBreakStrategy));
            }
        }
    }
}
