package com.cooey.maya;

import android.view.View;
import chatkit.Message;
import chatkit.messages.MessagesListAdapter.IncomingMessageViewHolder;

public class CustomIncomingMessageViewHolder extends IncomingMessageViewHolder<Message> {
    private View onlineView;

    public CustomIncomingMessageViewHolder(View itemView) {
        super(itemView);
        this.onlineView = itemView.findViewById(C0981R.id.online);
    }

    public void onBind(Message message) {
        super.onBind(message);
        if (true) {
            this.onlineView.setBackgroundResource(C0981R.drawable.shape_bubble_online);
        } else {
            this.onlineView.setBackgroundResource(C0981R.drawable.shape_bubble_offline);
        }
    }
}
