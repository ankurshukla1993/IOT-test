package com.facebook.react.views.text.frescosupport;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextInlineImageShadowNode;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.yoga.YogaConstants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Locale;
import javax.annotation.Nullable;

public class FrescoBasedReactTextInlineImageShadowNode extends ReactTextInlineImageShadowNode {
    @Nullable
    private final Object mCallerContext;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private float mHeight = YogaConstants.UNDEFINED;
    @Nullable
    private Uri mUri;
    private float mWidth = YogaConstants.UNDEFINED;

    public FrescoBasedReactTextInlineImageShadowNode(AbstractDraweeControllerBuilder draweeControllerBuilder, @Nullable Object callerContext) {
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mCallerContext = callerContext;
    }

    @ReactProp(name = "src")
    public void setSource(@Nullable ReadableArray sources) {
        String source = (sources == null || sources.size() == 0) ? null : sources.getMap(0).getString(RNFetchBlobConst.DATA_ENCODE_URI);
        Uri uri = null;
        if (source != null) {
            try {
                uri = Uri.parse(source);
                if (uri.getScheme() == null) {
                    uri = null;
                }
            } catch (Exception e) {
            }
            if (uri == null) {
                uri = getResourceDrawableUri(getThemedContext(), source);
            }
        }
        if (uri != this.mUri) {
            markUpdated();
        }
        this.mUri = uri;
    }

    public void setWidth(Dynamic width) {
        if (width.getType() == ReadableType.Number) {
            this.mWidth = (float) width.asDouble();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Inline images must not have percentage based width");
    }

    public void setHeight(Dynamic height) {
        if (height.getType() == ReadableType.Number) {
            this.mHeight = (float) height.asDouble();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Inline images must not have percentage based height");
    }

    @Nullable
    public Uri getUri() {
        return this.mUri;
    }

    @Nullable
    private static Uri getResourceDrawableUri(Context context, @Nullable String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(context.getResources().getIdentifier(name.toLowerCase(Locale.getDefault()).replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR), "drawable", context.getPackageName()))).build();
    }

    public boolean isVirtual() {
        return true;
    }

    public TextInlineImageSpan buildInlineImageSpan() {
        return new FrescoBasedReactTextInlineImageSpan(getThemedContext().getResources(), (int) Math.ceil((double) this.mWidth), (int) Math.ceil((double) this.mHeight), getUri(), getDraweeControllerBuilder(), getCallerContext());
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        return this.mDraweeControllerBuilder;
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }
}
