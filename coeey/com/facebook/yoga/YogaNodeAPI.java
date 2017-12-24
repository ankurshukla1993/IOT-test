package com.facebook.yoga;

public interface YogaNodeAPI<YogaNodeType extends YogaNodeAPI> {
    void addChildAt(YogaNodeType yogaNodeType, int i);

    void calculateLayout();

    void copyStyle(YogaNodeType yogaNodeType);

    void dirty();

    YogaAlign getAlignContent();

    YogaAlign getAlignItems();

    YogaAlign getAlignSelf();

    float getBorder(YogaEdge yogaEdge);

    YogaNodeType getChildAt(int i);

    int getChildCount();

    Object getData();

    YogaValue getFlexBasis();

    YogaFlexDirection getFlexDirection();

    float getFlexGrow();

    float getFlexShrink();

    YogaValue getHeight();

    YogaJustify getJustifyContent();

    YogaDirection getLayoutDirection();

    float getLayoutHeight();

    float getLayoutMargin(YogaEdge yogaEdge);

    float getLayoutPadding(YogaEdge yogaEdge);

    float getLayoutWidth();

    float getLayoutX();

    float getLayoutY();

    YogaValue getMargin(YogaEdge yogaEdge);

    YogaValue getMaxHeight();

    YogaValue getMaxWidth();

    YogaValue getMinHeight();

    YogaValue getMinWidth();

    YogaOverflow getOverflow();

    YogaValue getPadding(YogaEdge yogaEdge);

    YogaNodeType getParent();

    YogaValue getPosition(YogaEdge yogaEdge);

    YogaPositionType getPositionType();

    YogaDirection getStyleDirection();

    YogaValue getWidth();

    boolean hasNewLayout();

    int indexOf(YogaNodeType yogaNodeType);

    boolean isDirty();

    boolean isMeasureDefined();

    void markLayoutSeen();

    YogaNodeType removeChildAt(int i);

    void reset();

    void setAlignContent(YogaAlign yogaAlign);

    void setAlignItems(YogaAlign yogaAlign);

    void setAlignSelf(YogaAlign yogaAlign);

    void setBaselineFunction(YogaBaselineFunction yogaBaselineFunction);

    void setBorder(YogaEdge yogaEdge, float f);

    void setData(Object obj);

    void setDirection(YogaDirection yogaDirection);

    void setFlex(float f);

    void setFlexBasis(float f);

    void setFlexBasisPercent(float f);

    void setFlexDirection(YogaFlexDirection yogaFlexDirection);

    void setFlexGrow(float f);

    void setFlexShrink(float f);

    void setHeight(float f);

    void setHeightPercent(float f);

    void setJustifyContent(YogaJustify yogaJustify);

    void setMargin(YogaEdge yogaEdge, float f);

    void setMarginPercent(YogaEdge yogaEdge, float f);

    void setMaxHeight(float f);

    void setMaxHeightPercent(float f);

    void setMaxWidth(float f);

    void setMaxWidthPercent(float f);

    void setMeasureFunction(YogaMeasureFunction yogaMeasureFunction);

    void setMinHeight(float f);

    void setMinHeightPercent(float f);

    void setMinWidth(float f);

    void setMinWidthPercent(float f);

    void setOverflow(YogaOverflow yogaOverflow);

    void setPadding(YogaEdge yogaEdge, float f);

    void setPaddingPercent(YogaEdge yogaEdge, float f);

    void setPosition(YogaEdge yogaEdge, float f);

    void setPositionPercent(YogaEdge yogaEdge, float f);

    void setPositionType(YogaPositionType yogaPositionType);

    void setWidth(float f);

    void setWidthPercent(float f);

    void setWrap(YogaWrap yogaWrap);
}
