package com.cooey.common.adapters;

import android.support.v7.widget.RecyclerView.ViewHolder;
import io.realm.Realm;
import io.realm.RealmObject;

public abstract class BaseMutableRealmAdapter<T extends RealmObject, VH extends ViewHolder> extends BaseRealmAdapter<T, VH> {
    protected Realm realm;

    public BaseMutableRealmAdapter(Realm realm) {
        super(realm);
        this.realm = realm;
    }

    public void add(T t, boolean update) {
        notifyItemRangeChanged(0, this.mResults.size());
    }

    public void remove(int position) {
        notifyItemRemoved(position);
    }

    public void update(T item, int position) {
        notifyItemChanged(position, item);
    }
}
