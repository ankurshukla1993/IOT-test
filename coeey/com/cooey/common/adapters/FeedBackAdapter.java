package com.cooey.common.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cooey.common.C0842R;
import com.cooey.common.vo.FeedBackInputItem;
import java.util.List;

public class FeedBackAdapter extends Adapter<ViewHolder> {
    private Context context;
    private List<FeedBackInputItem> feedBackInputItems;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private TextView txtValue;
        private TextView txtViewLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtViewLabel = (TextView) itemView.findViewById(C0842R.id.feedback_label);
            this.txtValue = (TextView) itemView.findViewById(C0842R.id.feedback_value);
        }
    }

    public FeedBackAdapter(Context context, List<FeedBackInputItem> feedBackInputItems) {
        this.context = context;
        this.feedBackInputItems = feedBackInputItems;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0842R.layout.feedback_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        FeedBackInputItem feedBackInputItem = (FeedBackInputItem) this.feedBackInputItems.get(position);
        holder.txtViewLabel.setText(feedBackInputItem.getLabel());
        holder.txtValue.setText(feedBackInputItem.getValue());
    }

    public int getItemCount() {
        return this.feedBackInputItems.size();
    }
}
