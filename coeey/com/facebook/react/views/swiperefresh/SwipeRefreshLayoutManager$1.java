package com.facebook.react.views.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;

class SwipeRefreshLayoutManager$1 implements OnRefreshListener {
    final /* synthetic */ SwipeRefreshLayoutManager this$0;
    final /* synthetic */ ThemedReactContext val$reactContext;
    final /* synthetic */ ReactSwipeRefreshLayout val$view;

    SwipeRefreshLayoutManager$1(SwipeRefreshLayoutManager this$0, ThemedReactContext themedReactContext, ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
        this.this$0 = this$0;
        this.val$reactContext = themedReactContext;
        this.val$view = reactSwipeRefreshLayout;
    }

    public void onRefresh() {
        ((UIManagerModule) this.val$reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new RefreshEvent(this.val$view.getId()));
    }
}
