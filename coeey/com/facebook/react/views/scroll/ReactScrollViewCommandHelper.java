package com.facebook.react.views.scroll;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import java.util.Map;
import javax.annotation.Nullable;

public class ReactScrollViewCommandHelper {
    public static final int COMMAND_SCROLL_TO = 1;
    public static final int COMMAND_SCROLL_TO_END = 2;

    public interface ScrollCommandHandler<T> {
        void scrollTo(T t, ScrollToCommandData scrollToCommandData);

        void scrollToEnd(T t, ScrollToEndCommandData scrollToEndCommandData);
    }

    public static class ScrollToCommandData {
        public final boolean mAnimated;
        public final int mDestX;
        public final int mDestY;

        ScrollToCommandData(int destX, int destY, boolean animated) {
            this.mDestX = destX;
            this.mDestY = destY;
            this.mAnimated = animated;
        }
    }

    public static class ScrollToEndCommandData {
        public final boolean mAnimated;

        ScrollToEndCommandData(boolean animated) {
            this.mAnimated = animated;
        }
    }

    public static Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("scrollTo", Integer.valueOf(1), "scrollToEnd", Integer.valueOf(2));
    }

    public static <T> void receiveCommand(ScrollCommandHandler<T> viewManager, T scrollView, int commandType, @Nullable ReadableArray args) {
        Assertions.assertNotNull(viewManager);
        Assertions.assertNotNull(scrollView);
        Assertions.assertNotNull(args);
        switch (commandType) {
            case 1:
                viewManager.scrollTo(scrollView, new ScrollToCommandData(Math.round(PixelUtil.toPixelFromDIP(args.getDouble(0))), Math.round(PixelUtil.toPixelFromDIP(args.getDouble(1))), args.getBoolean(2)));
                return;
            case 2:
                viewManager.scrollToEnd(scrollView, new ScrollToEndCommandData(args.getBoolean(0)));
                return;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(commandType), viewManager.getClass().getSimpleName()}));
        }
    }
}
