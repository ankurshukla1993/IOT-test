package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.C1132R;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import javax.annotation.Nullable;

public class SimpleDraweeView extends GenericDraweeView {
    private static Supplier<? extends SimpleDraweeControllerBuilder> sDraweeControllerBuilderSupplier;
    private SimpleDraweeControllerBuilder mSimpleDraweeControllerBuilder;

    public static void initialize(Supplier<? extends SimpleDraweeControllerBuilder> draweeControllerBuilderSupplier) {
        sDraweeControllerBuilderSupplier = draweeControllerBuilderSupplier;
    }

    public static void shutDown() {
        sDraweeControllerBuilderSupplier = null;
    }

    public SimpleDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init(context, null);
    }

    public SimpleDraweeView(Context context) {
        super(context);
        init(context, null);
    }

    public SimpleDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SimpleDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(21)
    public SimpleDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        if (!isInEditMode()) {
            Preconditions.checkNotNull(sDraweeControllerBuilderSupplier, "SimpleDraweeView was not initialized!");
            this.mSimpleDraweeControllerBuilder = (SimpleDraweeControllerBuilder) sDraweeControllerBuilderSupplier.get();
            if (attrs != null) {
                TypedArray gdhAttrs = context.obtainStyledAttributes(attrs, C1132R.styleable.SimpleDraweeView);
                try {
                    if (gdhAttrs.hasValue(C1132R.styleable.SimpleDraweeView_actualImageUri)) {
                        setImageURI(Uri.parse(gdhAttrs.getString(C1132R.styleable.SimpleDraweeView_actualImageUri)), null);
                    }
                    gdhAttrs.recycle();
                } catch (Throwable th) {
                    gdhAttrs.recycle();
                }
            }
        }
    }

    protected SimpleDraweeControllerBuilder getControllerBuilder() {
        return this.mSimpleDraweeControllerBuilder;
    }

    public void setImageURI(Uri uri) {
        setImageURI(uri, null);
    }

    public void setImageURI(@Nullable String uriString) {
        setImageURI(uriString, null);
    }

    public void setImageURI(Uri uri, @Nullable Object callerContext) {
        setController(this.mSimpleDraweeControllerBuilder.setCallerContext(callerContext).setUri(uri).setOldController(getController()).build());
    }

    public void setImageURI(@Nullable String uriString, @Nullable Object callerContext) {
        setImageURI(uriString != null ? Uri.parse(uriString) : null, callerContext);
    }
}
