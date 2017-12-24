package com.cooey.common.vitals;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.common.C0842R;
import com.cooey.common.adapters.BaseRealmAdapter;
import com.cooey.common.vo.Vital;
import io.realm.Realm;
import io.realm.RealmResults;

public class VitalListRecyclerAdapter extends BaseRealmAdapter<Vital, VitalViewHolder> {
    private String name;

    public VitalListRecyclerAdapter(Realm realm) {
        super(realm);
    }

    public VitalListRecyclerAdapter(Realm realm, String name) {
        super(realm);
        this.name = name;
    }

    public boolean hasHeader() {
        return false;
    }

    public boolean hasFooter() {
        return false;
    }

    protected RealmResults<Vital> loadData(Realm realm) {
        if (this.name != null) {
            return realm.where(Vital.class).contains("parameters", this.name).findAll();
        }
        return realm.where(Vital.class).findAll();
    }

    public VitalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VitalViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0842R.layout.layout_vital_view, parent, false));
    }

    public void onBindViewHolder(VitalViewHolder holder, int position) {
        holder.bind((Vital) this.mResults.get(position));
    }
}
