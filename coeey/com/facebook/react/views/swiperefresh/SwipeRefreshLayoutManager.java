package com.facebook.react.views.swiperefresh;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidSwipeRefreshLayout")
public class SwipeRefreshLayoutManager extends ViewGroupManager<ReactSwipeRefreshLayout> {
    protected static final String REACT_CLASS = "AndroidSwipeRefreshLayout";

    protected ReactSwipeRefreshLayout createViewInstance(ThemedReactContext reactContext) {
        return new ReactSwipeRefreshLayout(reactContext);
    }

    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSwipeRefreshLayout view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @ReactProp(customType = "ColorArray", name = "colors")
    public void setColors(ReactSwipeRefreshLayout view, @Nullable ReadableArray colors) {
        if (colors != null) {
            int[] colorValues = new int[colors.size()];
            for (int i = 0; i < colors.size(); i++) {
                colorValues[i] = colors.getInt(i);
            }
            view.setColorSchemeColors(colorValues);
            return;
        }
        view.setColorSchemeColors(new int[0]);
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "progressBackgroundColor")
    public void setProgressBackgroundColor(ReactSwipeRefreshLayout view, int color) {
        view.setProgressBackgroundColorSchemeColor(color);
    }

    @ReactProp(defaultInt = 1, name = "size")
    public void setSize(ReactSwipeRefreshLayout view, int size) {
        view.setSize(size);
    }

    @ReactProp(name = "refreshing")
    public void setRefreshing(ReactSwipeRefreshLayout view, boolean refreshing) {
        view.setRefreshing(refreshing);
    }

    @ReactProp(defaultFloat = 0.0f, name = "progressViewOffset")
    public void setProgressViewOffset(ReactSwipeRefreshLayout view, float offset) {
        view.setProgressViewOffset(offset);
    }

    protected void addEventEmitters(ThemedReactContext reactContext, ReactSwipeRefreshLayout view) {
        view.setOnRefreshListener(new 1(this, reactContext, view));
    }

    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.of("SIZE", MapBuilder.of("DEFAULT", Integer.valueOf(1), "LARGE", Integer.valueOf(0)));
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("topRefresh", MapBuilder.of("registrationName", "onRefresh")).build();
    }
}
