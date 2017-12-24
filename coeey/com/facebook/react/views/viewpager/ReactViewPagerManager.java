package com.facebook.react.views.viewpager;

import android.view.View;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidViewPager")
public class ReactViewPagerManager extends ViewGroupManager<ReactViewPager> {
    public static final int COMMAND_SET_PAGE = 1;
    public static final int COMMAND_SET_PAGE_WITHOUT_ANIMATION = 2;
    protected static final String REACT_CLASS = "AndroidViewPager";

    public String getName() {
        return REACT_CLASS;
    }

    protected ReactViewPager createViewInstance(ThemedReactContext reactContext) {
        return new ReactViewPager(reactContext);
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactViewPager viewPager, boolean value) {
        viewPager.setScrollEnabled(value);
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(PageScrollEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageScroll"), PageScrollStateChangedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageScrollStateChanged"), PageSelectedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageSelected"));
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("setPage", Integer.valueOf(1), "setPageWithoutAnimation", Integer.valueOf(2));
    }

    public void receiveCommand(ReactViewPager viewPager, int commandType, @Nullable ReadableArray args) {
        Assertions.assertNotNull(viewPager);
        Assertions.assertNotNull(args);
        switch (commandType) {
            case 1:
                viewPager.setCurrentItemFromJs(args.getInt(0), true);
                return;
            case 2:
                viewPager.setCurrentItemFromJs(args.getInt(0), false);
                return;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(commandType), getClass().getSimpleName()}));
        }
    }

    public void addView(ReactViewPager parent, View child, int index) {
        parent.addViewToAdapter(child, index);
    }

    public int getChildCount(ReactViewPager parent) {
        return parent.getViewCountInAdapter();
    }

    public View getChildAt(ReactViewPager parent, int index) {
        return parent.getViewFromAdapter(index);
    }

    public void removeViewAt(ReactViewPager parent, int index) {
        parent.removeViewFromAdapter(index);
    }

    @ReactProp(defaultFloat = 0.0f, name = "pageMargin")
    public void setPageMargin(ReactViewPager pager, float margin) {
        pager.setPageMargin((int) PixelUtil.toPixelFromDIP(margin));
    }
}
