package chatkit.commons;

import android.view.View;

public abstract class ViewHolder<DATA> extends android.support.v7.widget.RecyclerView.ViewHolder {
    public abstract void onBind(DATA data);

    public ViewHolder(View itemView) {
        super(itemView);
    }
}
