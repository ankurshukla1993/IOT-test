package com.facebook.react.views.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.util.TypedValue;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.uimanager.ViewProps;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

public class ReactDrawableHelper {
    private static final TypedValue sResolveOutValue = new TypedValue();

    public static Drawable createDrawableFromJSDescription(Context context, ReadableMap drawableDescriptionDict) {
        String type = drawableDescriptionDict.getString("type");
        if ("ThemeAttrAndroid".equals(type)) {
            String attr = drawableDescriptionDict.getString("attribute");
            SoftAssertions.assertNotNull(attr);
            int attrID = context.getResources().getIdentifier(attr, "attr", AbstractSpiCall.ANDROID_CLIENT_TYPE);
            if (attrID == 0) {
                throw new JSApplicationIllegalArgumentException("Attribute " + attr + " couldn't be found in the resource list");
            } else if (!context.getTheme().resolveAttribute(attrID, sResolveOutValue, true)) {
                throw new JSApplicationIllegalArgumentException("Attribute " + attr + " couldn't be resolved into a drawable");
            } else if (VERSION.SDK_INT >= 21) {
                return context.getResources().getDrawable(sResolveOutValue.resourceId, context.getTheme());
            } else {
                return context.getResources().getDrawable(sResolveOutValue.resourceId);
            }
        } else if (!"RippleAndroid".equals(type)) {
            throw new JSApplicationIllegalArgumentException("Invalid type for android drawable: " + type);
        } else if (VERSION.SDK_INT < 21) {
            throw new JSApplicationIllegalArgumentException("Ripple drawable is not available on android API <21");
        } else {
            int color;
            if (drawableDescriptionDict.hasKey(ViewProps.COLOR) && !drawableDescriptionDict.isNull(ViewProps.COLOR)) {
                color = drawableDescriptionDict.getInt(ViewProps.COLOR);
            } else if (context.getTheme().resolveAttribute(16843820, sResolveOutValue, true)) {
                color = context.getResources().getColor(sResolveOutValue.resourceId);
            } else {
                throw new JSApplicationIllegalArgumentException("Attribute colorControlHighlight couldn't be resolved into a drawable");
            }
            Drawable mask = null;
            if (!(drawableDescriptionDict.hasKey("borderless") && !drawableDescriptionDict.isNull("borderless") && drawableDescriptionDict.getBoolean("borderless"))) {
                mask = new ColorDrawable(-1);
            }
            return new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{color}), null, mask);
        }
    }
}
