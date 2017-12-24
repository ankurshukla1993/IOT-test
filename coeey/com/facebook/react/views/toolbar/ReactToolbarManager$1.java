package com.facebook.react.views.toolbar;

import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.toolbar.events.ToolbarClickEvent;

class ReactToolbarManager$1 implements OnClickListener {
    final /* synthetic */ ReactToolbarManager this$0;
    final /* synthetic */ EventDispatcher val$mEventDispatcher;
    final /* synthetic */ ReactToolbar val$view;

    ReactToolbarManager$1(ReactToolbarManager this$0, EventDispatcher eventDispatcher, ReactToolbar reactToolbar) {
        this.this$0 = this$0;
        this.val$mEventDispatcher = eventDispatcher;
        this.val$view = reactToolbar;
    }

    public void onClick(View v) {
        this.val$mEventDispatcher.dispatchEvent(new ToolbarClickEvent(this.val$view.getId(), -1));
    }
}
