package com.cooey.maya;

import android.content.Context;
import chatkit.Message;
import chatkit.events.ActionSelectedEvent;
import chatkit.events.OptionSelectedEvent;
import chatkit.messages.MessagesListAdapter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ContentManager {
    public static String contextId;
    private final Context context;
    private final MessagesListAdapter<Message> messageListAdaper;
    private final WebSocketConnector webSocketConnector;

    public ContentManager(Context context, WebSocketConnector webSocketConnector, MessagesListAdapter<Message> messagesListAdapter) {
        this.webSocketConnector = webSocketConnector;
        this.messageListAdaper = messagesListAdapter;
        this.context = context;
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActionSelected(ActionSelectedEvent actionSelectedEvent) {
        MayaRequest mayaRequest = new MayaRequest();
        mayaRequest.setExpectedIntent(actionSelectedEvent.getAction().getIntent());
        mayaRequest.setContentId(actionSelectedEvent.getAction().getContentId());
        new Thread(new 1(this, mayaRequest)).start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOptionSelected(OptionSelectedEvent optionSelectedEvent) {
        new Thread(new 2(this, optionSelectedEvent)).start();
    }
}
