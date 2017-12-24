package com.cooey.common.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedCollectionChangeSet.Range;
import io.realm.OrderedRealmCollection;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;

public abstract class RealmRecyclerViewAdapter<T extends RealmModel, S extends ViewHolder> extends Adapter<S> {
    @Nullable
    private OrderedRealmCollection<T> adapterData;
    private final boolean hasAutoUpdates;
    private final OrderedRealmCollectionChangeListener listener;
    private final boolean updateOnModification;

    class C08441 implements OrderedRealmCollectionChangeListener {
        C08441() {
        }

        public void onChange(Object collection, OrderedCollectionChangeSet changeSet) {
            int i = 0;
            if (changeSet == null) {
                RealmRecyclerViewAdapter.this.notifyDataSetChanged();
                return;
            }
            int length;
            Range[] deletions = changeSet.getDeletionRanges();
            for (int i2 = deletions.length - 1; i2 >= 0; i2--) {
                Range range = deletions[i2];
                RealmRecyclerViewAdapter.this.notifyItemRangeRemoved(range.startIndex, range.length);
            }
            for (Range range2 : changeSet.getInsertionRanges()) {
                RealmRecyclerViewAdapter.this.notifyItemRangeInserted(range2.startIndex, range2.length);
            }
            if (RealmRecyclerViewAdapter.this.updateOnModification) {
                Range[] modifications = changeSet.getChangeRanges();
                length = modifications.length;
                while (i < length) {
                    range2 = modifications[i];
                    RealmRecyclerViewAdapter.this.notifyItemRangeChanged(range2.startIndex, range2.length);
                    i++;
                }
            }
        }
    }

    private OrderedRealmCollectionChangeListener createListener() {
        return new C08441();
    }

    public RealmRecyclerViewAdapter(@Nullable OrderedRealmCollection<T> data, boolean autoUpdate) {
        this(data, autoUpdate, true);
    }

    public RealmRecyclerViewAdapter(@Nullable OrderedRealmCollection<T> data, boolean autoUpdate, boolean updateOnModification) {
        if (data == null || data.isManaged()) {
            this.adapterData = data;
            this.hasAutoUpdates = autoUpdate;
            this.listener = this.hasAutoUpdates ? createListener() : null;
            this.updateOnModification = updateOnModification;
            return;
        }
        throw new IllegalStateException("Only use this adapter with managed RealmCollection, for un-managed lists you can just use the BaseRecyclerViewAdapter");
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (this.hasAutoUpdates && isDataValid()) {
            addListener(this.adapterData);
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (this.hasAutoUpdates && isDataValid()) {
            removeListener(this.adapterData);
        }
    }

    public long getItemId(int index) {
        return (long) index;
    }

    public int getItemCount() {
        return isDataValid() ? this.adapterData.size() : 0;
    }

    @Nullable
    public T getItem(int index) {
        return isDataValid() ? (RealmModel) this.adapterData.get(index) : null;
    }

    @Nullable
    public OrderedRealmCollection<T> getData() {
        return this.adapterData;
    }

    public void updateData(@Nullable OrderedRealmCollection<T> data) {
        if (this.hasAutoUpdates) {
            if (isDataValid()) {
                removeListener(this.adapterData);
            }
            if (data != null) {
                addListener(data);
            }
        }
        this.adapterData = data;
        notifyDataSetChanged();
    }

    private void addListener(@NonNull OrderedRealmCollection<T> data) {
        if (data instanceof RealmResults) {
            ((RealmResults) data).addChangeListener(this.listener);
        } else if (data instanceof RealmList) {
            ((RealmList) data).addChangeListener(this.listener);
        } else {
            throw new IllegalArgumentException("RealmCollection not supported: " + data.getClass());
        }
    }

    private void removeListener(@NonNull OrderedRealmCollection<T> data) {
        if (data instanceof RealmResults) {
            ((RealmResults) data).removeChangeListener(this.listener);
        } else if (data instanceof RealmList) {
            ((RealmList) data).removeChangeListener(this.listener);
        } else {
            throw new IllegalArgumentException("RealmCollection not supported: " + data.getClass());
        }
    }

    private boolean isDataValid() {
        return this.adapterData != null && this.adapterData.isValid();
    }
}
