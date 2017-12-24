package com.thoughtbot.expandablerecyclerview.viewholders;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;

public abstract class GroupViewHolder extends ViewHolder implements OnClickListener {
    private OnGroupClickListener listener;

    public GroupViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (this.listener == null) {
            return;
        }
        if (this.listener.onGroupClick(getAdapterPosition())) {
            collapse();
        } else {
            expand();
        }
    }

    public void setOnGroupClickListener(OnGroupClickListener listener) {
        this.listener = listener;
    }

    public void expand() {
    }

    public void collapse() {
    }
}
