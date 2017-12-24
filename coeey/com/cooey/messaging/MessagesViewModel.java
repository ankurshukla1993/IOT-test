package com.cooey.messaging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import chatkit.Message;
import java.util.List;

public class MessagesViewModel extends ViewModel {
    private MutableLiveData<List<Message>> messages = new MutableLiveData();

    public MutableLiveData<List<Message>> getMessages() {
        return this.messages;
    }

    public void setMessages(MutableLiveData<List<Message>> messages) {
        this.messages = messages;
    }
}
