package chatkit.holders;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import chatkit.Content;

public abstract class ContentViewHolder extends ViewHolder {
    protected View itemView;

    public abstract void onBind(Content content);

    public ContentViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }
}
