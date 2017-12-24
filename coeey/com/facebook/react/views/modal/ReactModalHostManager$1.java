package com.facebook.react.views.modal;

import android.content.DialogInterface;
import com.facebook.react.uimanager.events.EventDispatcher;

class ReactModalHostManager$1 implements ReactModalHostView$OnRequestCloseListener {
    final /* synthetic */ ReactModalHostManager this$0;
    final /* synthetic */ EventDispatcher val$dispatcher;
    final /* synthetic */ ReactModalHostView val$view;

    ReactModalHostManager$1(ReactModalHostManager this$0, EventDispatcher eventDispatcher, ReactModalHostView reactModalHostView) {
        this.this$0 = this$0;
        this.val$dispatcher = eventDispatcher;
        this.val$view = reactModalHostView;
    }

    public void onRequestClose(DialogInterface dialog) {
        this.val$dispatcher.dispatchEvent(new RequestCloseEvent(this.val$view.getId()));
    }
}
