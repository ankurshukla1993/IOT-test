package com.cooey.maya;

import android.view.View;
import chatkit.Message;
import chatkit.messages.MessagesListAdapter.OutcomingMessageViewHolder;

public class CustomOutcomingMessageViewHolder extends OutcomingMessageViewHolder<Message> {
    public CustomOutcomingMessageViewHolder(View itemView) {
        super(itemView);
    }

    public void onBind(Message message) {
        super.onBind(message);
        this.time.setText(this.time.getText());
    }
}
