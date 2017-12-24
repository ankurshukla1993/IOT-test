package com.facebook.react.views.toolbar;

import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.toolbar.events.ToolbarClickEvent;

class ReactToolbarManager$2 implements OnMenuItemClickListener {
    final /* synthetic */ ReactToolbarManager this$0;
    final /* synthetic */ EventDispatcher val$mEventDispatcher;
    final /* synthetic */ ReactToolbar val$view;

    ReactToolbarManager$2(ReactToolbarManager this$0, EventDispatcher eventDispatcher, ReactToolbar reactToolbar) {
        this.this$0 = this$0;
        this.val$mEventDispatcher = eventDispatcher;
        this.val$view = reactToolbar;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        this.val$mEventDispatcher.dispatchEvent(new ToolbarClickEvent(this.val$view.getId(), menuItem.getOrder()));
        return true;
    }
}
