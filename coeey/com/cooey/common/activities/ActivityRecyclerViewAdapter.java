package com.cooey.common.activities;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.common.C0842R;
import com.cooey.common.adapters.BaseRealmAdapter;
import com.cooey.common.vo.careplan.ActivityItem;
import com.google.android.gms.measurement.AppMeasurement$Param;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ActivityRecyclerViewAdapter extends BaseRealmAdapter<ActivityItem, ActivityViewHolder> {
    public ActivityRecyclerViewAdapter(Realm realm) {
        super(realm);
    }

    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActivityViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0842R.layout.layout_activity_holder_view, parent, false));
    }

    public void onBindViewHolder(ActivityViewHolder holder, int position) {
        holder.bind((ActivityItem) this.mResults.get(position));
    }

    public boolean hasHeader() {
        return false;
    }

    public boolean hasFooter() {
        return false;
    }

    protected RealmResults<ActivityItem> loadData(Realm realm) {
        RealmResults<ActivityItem> activityItems = realm.where(ActivityItem.class).findAll();
        activityItems.sort(AppMeasurement$Param.TIMESTAMP, Sort.DESCENDING);
        return activityItems;
    }
}
