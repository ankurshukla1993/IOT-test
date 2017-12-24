package com.cooey.common.maya;

import android.support.v7.widget.RecyclerView.ViewHolder;
import com.cooey.common.databinding.LayoutMessageBinding;
import com.cooey.common.vo.Message;

public class MessageViewHolder extends ViewHolder {
    private final LayoutMessageBinding layoutMessageBinding;

    public MessageViewHolder(LayoutMessageBinding layoutMessageBinding) {
        super(layoutMessageBinding.getRoot());
        this.layoutMessageBinding = layoutMessageBinding;
    }

    public void bind(Message message) {
        MessageViewModel messageViewModel = new MessageViewModel();
        messageViewModel.setMessage(message);
        this.layoutMessageBinding.setMessageViewModel(messageViewModel);
    }
}
