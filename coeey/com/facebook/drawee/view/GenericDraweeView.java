package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchyInflater;
import javax.annotation.Nullable;

public class GenericDraweeView extends DraweeView<GenericDraweeHierarchy> {
    public GenericDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context);
        setHierarchy(hierarchy);
    }

    public GenericDraweeView(Context context) {
        super(context);
        inflateHierarchy(context, null);
    }

    public GenericDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateHierarchy(context, attrs);
    }

    public GenericDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflateHierarchy(context, attrs);
    }

    @TargetApi(21)
    public GenericDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateHierarchy(context, attrs);
    }

    protected void inflateHierarchy(Context context, @Nullable AttributeSet attrs) {
        GenericDraweeHierarchyBuilder builder = GenericDraweeHierarchyInflater.inflateBuilder(context, attrs);
        setAspectRatio(builder.getDesiredAspectRatio());
        setHierarchy(builder.build());
    }
}
