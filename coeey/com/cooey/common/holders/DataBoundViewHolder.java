package com.cooey.common.holders;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class DataBoundViewHolder<T extends ViewDataBinding> extends ViewHolder {
    public final T binding;

    public DataBoundViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public static <T extends ViewDataBinding> DataBoundViewHolder<T> create(ViewGroup parent, @LayoutRes int layoutId) {
        return new DataBoundViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false));
    }
}
