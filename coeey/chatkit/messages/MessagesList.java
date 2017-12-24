package chatkit.messages;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.AttributeSet;
import chatkit.commons.models.IMessage;

public class MessagesList extends RecyclerView {
    private MessagesListStyle messagesListStyle;

    public MessagesList(Context context) {
        super(context);
    }

    public MessagesList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parseStyle(context, attrs);
    }

    public MessagesList(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseStyle(context, attrs);
    }

    public void setAdapter(Adapter adapter) {
        throw new IllegalArgumentException("You can't set adapter to MessagesList. Use #setAdapter(MessagesListAdapter) instead.");
    }

    public <MESSAGE extends IMessage> void setAdapter(MessagesListAdapter<MESSAGE> adapter) {
        SimpleItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setSupportsChangeAnimations(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), 1, true);
        setItemAnimator(itemAnimator);
        setLayoutManager(layoutManager);
        adapter.setLayoutManager(layoutManager);
        adapter.setStyle(this.messagesListStyle);
        addOnScrollListener(new RecyclerScrollMoreListener(layoutManager, adapter));
        super.setAdapter(adapter);
    }

    private void parseStyle(Context context, AttributeSet attrs) {
        this.messagesListStyle = MessagesListStyle.parse(context, attrs);
    }
}
