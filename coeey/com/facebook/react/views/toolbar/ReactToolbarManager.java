package com.facebook.react.views.toolbar;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import com.facebook.react.R;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import java.util.Map;
import javax.annotation.Nullable;

public class ReactToolbarManager extends ViewGroupManager<ReactToolbar> {
    private static final String REACT_CLASS = "ToolbarAndroid";

    public String getName() {
        return REACT_CLASS;
    }

    protected ReactToolbar createViewInstance(ThemedReactContext reactContext) {
        return new ReactToolbar(reactContext);
    }

    @ReactProp(name = "logo")
    public void setLogo(ReactToolbar view, @Nullable ReadableMap logo) {
        view.setLogoSource(logo);
    }

    @ReactProp(name = "navIcon")
    public void setNavIcon(ReactToolbar view, @Nullable ReadableMap navIcon) {
        view.setNavIconSource(navIcon);
    }

    @ReactProp(name = "overflowIcon")
    public void setOverflowIcon(ReactToolbar view, @Nullable ReadableMap overflowIcon) {
        view.setOverflowIconSource(overflowIcon);
    }

    @ReactProp(name = "rtl")
    public void setRtl(ReactToolbar view, boolean rtl) {
        view.setLayoutDirection(rtl ? 1 : 0);
    }

    @ReactProp(name = "subtitle")
    public void setSubtitle(ReactToolbar view, @Nullable String subtitle) {
        view.setSubtitle(subtitle);
    }

    @ReactProp(customType = "Color", name = "subtitleColor")
    public void setSubtitleColor(ReactToolbar view, @Nullable Integer subtitleColor) {
        int[] defaultColors = getDefaultColors(view.getContext());
        if (subtitleColor != null) {
            view.setSubtitleTextColor(subtitleColor.intValue());
        } else {
            view.setSubtitleTextColor(defaultColors[1]);
        }
    }

    @ReactProp(name = "title")
    public void setTitle(ReactToolbar view, @Nullable String title) {
        view.setTitle(title);
    }

    @ReactProp(customType = "Color", name = "titleColor")
    public void setTitleColor(ReactToolbar view, @Nullable Integer titleColor) {
        int[] defaultColors = getDefaultColors(view.getContext());
        if (titleColor != null) {
            view.setTitleTextColor(titleColor.intValue());
        } else {
            view.setTitleTextColor(defaultColors[0]);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "contentInsetStart")
    public void setContentInsetStart(ReactToolbar view, float insetStart) {
        int inset;
        if (Float.isNaN(insetStart)) {
            inset = getDefaultContentInsets(view.getContext())[0];
        } else {
            inset = Math.round(PixelUtil.toPixelFromDIP(insetStart));
        }
        view.setContentInsetsRelative(inset, view.getContentInsetEnd());
    }

    @ReactProp(defaultFloat = Float.NaN, name = "contentInsetEnd")
    public void setContentInsetEnd(ReactToolbar view, float insetEnd) {
        int inset;
        if (Float.isNaN(insetEnd)) {
            inset = getDefaultContentInsets(view.getContext())[1];
        } else {
            inset = Math.round(PixelUtil.toPixelFromDIP(insetEnd));
        }
        view.setContentInsetsRelative(view.getContentInsetStart(), inset);
    }

    @ReactProp(name = "nativeActions")
    public void setActions(ReactToolbar view, @Nullable ReadableArray actions) {
        view.setActions(actions);
    }

    protected void addEventEmitters(ThemedReactContext reactContext, ReactToolbar view) {
        EventDispatcher mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        view.setNavigationOnClickListener(new 1(this, mEventDispatcher, view));
        view.setOnMenuItemClickListener(new 2(this, mEventDispatcher, view));
    }

    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.of("ShowAsAction", MapBuilder.of(ReactScrollViewHelper.OVER_SCROLL_NEVER, Integer.valueOf(0), ReactScrollViewHelper.OVER_SCROLL_ALWAYS, Integer.valueOf(2), "ifRoom", Integer.valueOf(1)));
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    private int[] getDefaultContentInsets(Context context) {
        Theme theme = context.getTheme();
        TypedArray toolbarStyle = null;
        TypedArray contentInsets = null;
        try {
            toolbarStyle = theme.obtainStyledAttributes(new int[]{R.attr.toolbarStyle});
            contentInsets = theme.obtainStyledAttributes(toolbarStyle.getResourceId(0, 0), new int[]{R.attr.contentInsetStart, R.attr.contentInsetEnd});
            int contentInsetStart = contentInsets.getDimensionPixelSize(0, 0);
            int contentInsetEnd = contentInsets.getDimensionPixelSize(1, 0);
            int[] iArr = new int[]{contentInsetStart, contentInsetEnd};
            return iArr;
        } finally {
            recycleQuietly(toolbarStyle);
            recycleQuietly(contentInsets);
        }
    }

    private static int[] getDefaultColors(Context context) {
        Theme theme = context.getTheme();
        TypedArray toolbarStyle = null;
        TypedArray textAppearances = null;
        TypedArray titleTextAppearance = null;
        TypedArray subtitleTextAppearance = null;
        try {
            toolbarStyle = theme.obtainStyledAttributes(new int[]{R.attr.toolbarStyle});
            textAppearances = theme.obtainStyledAttributes(toolbarStyle.getResourceId(0, 0), new int[]{R.attr.titleTextAppearance, R.attr.subtitleTextAppearance});
            int titleTextAppearanceResId = textAppearances.getResourceId(0, 0);
            int subtitleTextAppearanceResId = textAppearances.getResourceId(1, 0);
            titleTextAppearance = theme.obtainStyledAttributes(titleTextAppearanceResId, new int[]{16842904});
            subtitleTextAppearance = theme.obtainStyledAttributes(subtitleTextAppearanceResId, new int[]{16842904});
            int titleTextColor = titleTextAppearance.getColor(0, ViewCompat.MEASURED_STATE_MASK);
            int subtitleTextColor = subtitleTextAppearance.getColor(0, ViewCompat.MEASURED_STATE_MASK);
            int[] iArr = new int[]{titleTextColor, subtitleTextColor};
            return iArr;
        } finally {
            recycleQuietly(toolbarStyle);
            recycleQuietly(textAppearances);
            recycleQuietly(titleTextAppearance);
            recycleQuietly(subtitleTextAppearance);
        }
    }

    private static void recycleQuietly(@Nullable TypedArray style) {
        if (style != null) {
            style.recycle();
        }
    }
}
