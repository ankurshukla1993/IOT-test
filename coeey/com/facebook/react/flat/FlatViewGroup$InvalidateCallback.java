package com.facebook.react.flat;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.image.ImageLoadEvent;
import java.lang.ref.WeakReference;

final class FlatViewGroup$InvalidateCallback extends WeakReference<FlatViewGroup> {
    private FlatViewGroup$InvalidateCallback(FlatViewGroup view) {
        super(view);
    }

    public void invalidate() {
        FlatViewGroup view = (FlatViewGroup) get();
        if (view != null) {
            view.invalidate();
        }
    }

    public void dispatchImageLoadEvent(int reactTag, int imageLoadEvent) {
        FlatViewGroup view = (FlatViewGroup) get();
        if (view != null) {
            ((UIManagerModule) ((ReactContext) view.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ImageLoadEvent(reactTag, imageLoadEvent));
        }
    }
}
