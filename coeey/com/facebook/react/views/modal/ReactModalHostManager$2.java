package com.facebook.react.views.modal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import com.facebook.react.uimanager.events.EventDispatcher;

class ReactModalHostManager$2 implements OnShowListener {
    final /* synthetic */ ReactModalHostManager this$0;
    final /* synthetic */ EventDispatcher val$dispatcher;
    final /* synthetic */ ReactModalHostView val$view;

    ReactModalHostManager$2(ReactModalHostManager this$0, EventDispatcher eventDispatcher, ReactModalHostView reactModalHostView) {
        this.this$0 = this$0;
        this.val$dispatcher = eventDispatcher;
        this.val$view = reactModalHostView;
    }

    public void onShow(DialogInterface dialog) {
        this.val$dispatcher.dispatchEvent(new ShowEvent(this.val$view.getId()));
    }
}
