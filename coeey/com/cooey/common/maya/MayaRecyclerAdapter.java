package com.cooey.common.maya;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.common.C0842R;
import com.cooey.common.adapters.BaseMutableRealmAdapter;
import com.cooey.common.databinding.LayoutMessageBinding;
import com.cooey.common.vo.Message;
import com.google.android.gms.measurement.AppMeasurement$Param;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MayaRecyclerAdapter extends BaseMutableRealmAdapter<Message, MessageViewHolder> {
    private final Context context;

    public MayaRecyclerAdapter(Context context, Realm realm) {
        super(realm);
        this.context = context;
    }

    public boolean hasHeader() {
        return false;
    }

    public boolean hasFooter() {
        return false;
    }

    protected RealmResults<Message> loadData(Realm realm) {
        return realm.where(Message.class).findAllSorted(AppMeasurement$Param.TIMESTAMP, Sort.DESCENDING);
    }

    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder((LayoutMessageBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), C0842R.layout.layout_message, parent, false));
    }

    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.bind((Message) this.mResults.get(position));
    }

    public void add(Message message) {
        this.realm.copyToRealm(message);
        notifyDataSetChanged();
    }
}
