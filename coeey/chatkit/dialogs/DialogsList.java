package chatkit.dialogs;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.AttributeSet;
import chatkit.commons.models.IDialog;

public class DialogsList extends RecyclerView {
    private DialogListStyle dialogStyle;

    public DialogsList(Context context) {
        super(context);
    }

    public DialogsList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parseStyle(context, attrs);
    }

    public DialogsList(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseStyle(context, attrs);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), 1, false);
        SimpleItemAnimator animator = new DefaultItemAnimator();
        setLayoutManager(layout);
        setItemAnimator(animator);
    }

    public void setAdapter(Adapter adapter) {
        throw new IllegalArgumentException("You can't set adapter to DialogsList. Use #setAdapter(DialogsListAdapter) instead.");
    }

    public <DIALOG extends IDialog> void setAdapter(DialogsListAdapter<DIALOG> adapter) {
        SimpleItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setSupportsChangeAnimations(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), 1, true);
        setItemAnimator(itemAnimator);
        setLayoutManager(layoutManager);
        adapter.setStyle(this.dialogStyle);
        super.setAdapter(adapter);
    }

    private void parseStyle(Context context, AttributeSet attrs) {
        this.dialogStyle = DialogListStyle.parse(context, attrs);
    }
}
