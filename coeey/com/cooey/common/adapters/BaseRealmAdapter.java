package com.cooey.common.adapters;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObject;
import io.realm.RealmResults;

public abstract class BaseRealmAdapter<T extends RealmObject, VH extends ViewHolder> extends Adapter<VH> {
    public static final int FOOTER_COUNT = 0;
    public static final int HEADER_COUNT = 0;
    private RealmChangeListener<T> listener = new C08431();
    protected RealmResults<T> mResults;

    class C08431 implements RealmChangeListener<T> {
        C08431() {
        }

        public void onChange(T t) {
            BaseRealmAdapter.this.notifyDataSetChanged();
        }
    }

    public enum ItemType {
        HEADER,
        ITEM,
        FOOTER
    }

    public abstract boolean hasFooter();

    public abstract boolean hasHeader();

    protected abstract RealmResults<T> loadData(Realm realm);

    public BaseRealmAdapter(Realm realm) {
        this.mResults = loadData(realm);
        notifyDataSetChanged();
    }

    public int getHeaderCount() {
        return hasHeader() ? 0 : 0;
    }

    public int getFooterCount() {
        return hasFooter() ? 0 : 0;
    }

    public boolean isHeader(int position) {
        if (!hasHeader() || position >= 0) {
            return false;
        }
        return true;
    }

    public boolean isFooter(int position) {
        if (!hasFooter() || position < getCount() + getHeaderCount()) {
            return false;
        }
        return true;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return ItemType.HEADER.ordinal();
        }
        if (isFooter(position)) {
            return ItemType.FOOTER.ordinal();
        }
        return ItemType.ITEM.ordinal();
    }

    public T getItem(int position) {
        if (isHeader(position) || isFooter(position) || this.mResults.isEmpty()) {
            return null;
        }
        return (RealmObject) this.mResults.get(position - getHeaderCount());
    }

    public int getItemCount() {
        return (getHeaderCount() + getCount()) + getFooterCount();
    }

    public final int getCount() {
        return this.mResults.size();
    }

    public void setData(RealmResults<T> results) {
        this.mResults = results;
        notifyDataSetChanged();
    }
}
