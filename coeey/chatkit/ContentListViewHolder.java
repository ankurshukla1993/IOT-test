package chatkit;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import chatkit.commons.ViewHolder;
import com.cooey.maya.R;

public class ContentListViewHolder extends ViewHolder<Message> {
    public ContentListViewHolder(View itemView) {
        super(itemView);
    }

    public void onBind(Message message) {
        RecyclerView recyclerView = (RecyclerView) this.itemView.findViewById(R.id.content_recycler_view);
        ContentRecyclerAdapter contentRecyclerAdapter = new ContentRecyclerAdapter(message.getContents());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.itemView.getContext(), 0, false));
        recyclerView.setAdapter(contentRecyclerAdapter);
    }
}
