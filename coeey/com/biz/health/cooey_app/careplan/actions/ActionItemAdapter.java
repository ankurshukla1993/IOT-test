package com.biz.health.cooey_app.careplan.actions;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.careplan.actions.holders.ActionItemViewHolder;
import com.cooey.android.users.old.utils.GPSTracker;
import com.cooey.common.vo.ActionItem;
import com.cooey.common.vo.User;
import java.util.ArrayList;
import java.util.List;

public class ActionItemAdapter extends Adapter<ActionItemViewHolder> {
    public List<ActionItem> actionItems = new ArrayList();
    private GPSTracker gpsTracker;
    private Context mContext;
    User user;

    public ActionItemAdapter(Context mContext, User user) {
        this.mContext = mContext;
        this.gpsTracker = new GPSTracker(mContext);
        this.user = user;
    }

    public ActionItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        try {
            return new ActionItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0644R.layout.item_action_item_user, viewGroup, false), this.gpsTracker);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onBindViewHolder(ActionItemViewHolder actionItemViewHolder, int i) {
        actionItemViewHolder.bind((ActionItem) this.actionItems.get(i));
    }

    public int getItemCount() {
        if (this.actionItems.size() > 0) {
            return this.actionItems.size();
        }
        return 0;
    }

    public int getItemViewType(int position) {
        return 1;
    }
}
