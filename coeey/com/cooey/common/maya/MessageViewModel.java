package com.cooey.common.maya;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.cooey.common.BR;
import com.cooey.common.vo.Message;

public class MessageViewModel extends BaseObservable {
    private Message message;

    public void setMessage(Message message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public Message getMessage() {
        return this.message;
    }
}
