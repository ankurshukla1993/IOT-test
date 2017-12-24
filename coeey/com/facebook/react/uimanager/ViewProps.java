package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import java.util.Arrays;
import java.util.HashSet;

public class ViewProps {
    public static final String ALIGN_ITEMS = "alignItems";
    public static final String ALIGN_SELF = "alignSelf";
    public static final String ALLOW_FONT_SCALING = "allowFontScaling";
    public static final String ASPECT_RATIO = "aspectRatio";
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final String BORDER_BOTTOM_LEFT_RADIUS = "borderBottomLeftRadius";
    public static final String BORDER_BOTTOM_RIGHT_RADIUS = "borderBottomRightRadius";
    public static final String BORDER_BOTTOM_WIDTH = "borderBottomWidth";
    public static final String BORDER_LEFT_WIDTH = "borderLeftWidth";
    public static final String BORDER_RADIUS = "borderRadius";
    public static final String BORDER_RIGHT_WIDTH = "borderRightWidth";
    public static final int[] BORDER_SPACING_TYPES = new int[]{8, 4, 5, 1, 3};
    public static final String BORDER_TOP_LEFT_RADIUS = "borderTopLeftRadius";
    public static final String BORDER_TOP_RIGHT_RADIUS = "borderTopRightRadius";
    public static final String BORDER_TOP_WIDTH = "borderTopWidth";
    public static final String BORDER_WIDTH = "borderWidth";
    public static final String BOTTOM = "bottom";
    public static final String COLLAPSABLE = "collapsable";
    public static final String COLOR = "color";
    public static final String ELLIPSIZE_MODE = "ellipsizeMode";
    public static final String ENABLED = "enabled";
    public static final String FLEX = "flex";
    public static final String FLEX_BASIS = "flexBasis";
    public static final String FLEX_DIRECTION = "flexDirection";
    public static final String FLEX_GROW = "flexGrow";
    public static final String FLEX_SHRINK = "flexShrink";
    public static final String FLEX_WRAP = "flexWrap";
    public static final String FONT_FAMILY = "fontFamily";
    public static final String FONT_SIZE = "fontSize";
    public static final String FONT_STYLE = "fontStyle";
    public static final String FONT_WEIGHT = "fontWeight";
    public static final String HEIGHT = "height";
    public static final String JUSTIFY_CONTENT = "justifyContent";
    private static final HashSet<String> LAYOUT_ONLY_PROPS = new HashSet(Arrays.asList(new String[]{ALIGN_SELF, ALIGN_ITEMS, COLLAPSABLE, FLEX, FLEX_DIRECTION, FLEX_WRAP, JUSTIFY_CONTENT, OVERFLOW, "position", RIGHT, TOP, BOTTOM, LEFT, "width", "height", MIN_WIDTH, MAX_WIDTH, MIN_HEIGHT, MAX_HEIGHT, MARGIN, MARGIN_VERTICAL, MARGIN_HORIZONTAL, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM, PADDING, PADDING_VERTICAL, PADDING_HORIZONTAL, PADDING_LEFT, PADDING_RIGHT, PADDING_TOP, PADDING_BOTTOM}));
    public static final String LEFT = "left";
    public static final String LINE_HEIGHT = "lineHeight";
    public static final String MARGIN = "margin";
    public static final String MARGIN_BOTTOM = "marginBottom";
    public static final String MARGIN_HORIZONTAL = "marginHorizontal";
    public static final String MARGIN_LEFT = "marginLeft";
    public static final String MARGIN_RIGHT = "marginRight";
    public static final String MARGIN_TOP = "marginTop";
    public static final String MARGIN_VERTICAL = "marginVertical";
    public static final String MAX_HEIGHT = "maxHeight";
    public static final String MAX_WIDTH = "maxWidth";
    public static final String MIN_HEIGHT = "minHeight";
    public static final String MIN_WIDTH = "minWidth";
    public static final String NEEDS_OFFSCREEN_ALPHA_COMPOSITING = "needsOffscreenAlphaCompositing";
    public static final String NUMBER_OF_LINES = "numberOfLines";
    public static final String ON = "on";
    public static final String OVERFLOW = "overflow";
    public static final String PADDING = "padding";
    public static final String PADDING_BOTTOM = "paddingBottom";
    public static final String PADDING_HORIZONTAL = "paddingHorizontal";
    public static final String PADDING_LEFT = "paddingLeft";
    public static final int[] PADDING_MARGIN_SPACING_TYPES = new int[]{8, 7, 6, 4, 5, 1, 3};
    public static final String PADDING_RIGHT = "paddingRight";
    public static final String PADDING_TOP = "paddingTop";
    public static final String PADDING_VERTICAL = "paddingVertical";
    public static final String POINTER_EVENTS = "pointerEvents";
    public static final String POSITION = "position";
    public static final int[] POSITION_SPACING_TYPES = new int[]{4, 5, 1, 3};
    public static final String RESIZE_METHOD = "resizeMethod";
    public static final String RESIZE_MODE = "resizeMode";
    public static final String RIGHT = "right";
    public static final String TEXT_ALIGN = "textAlign";
    public static final String TEXT_ALIGN_VERTICAL = "textAlignVertical";
    public static final String TEXT_BREAK_STRATEGY = "textBreakStrategy";
    public static final String TEXT_DECORATION_LINE = "textDecorationLine";
    public static final String TOP = "top";
    public static final String VIEW_CLASS_NAME = "RCTView";
    public static final String WIDTH = "width";

    public static boolean isLayoutOnly(ReadableMap map, String prop) {
        if (LAYOUT_ONLY_PROPS.contains(prop)) {
            return true;
        }
        if (!POINTER_EVENTS.equals(prop)) {
            return false;
        }
        String value = map.getString(prop);
        if (ReactScrollViewHelper.AUTO.equals(value) || "box-none".equals(value)) {
            return true;
        }
        return false;
    }
}
