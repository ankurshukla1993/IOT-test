package com.cooey.common.vitals;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.common.C0842R;
import java.util.List;

public class VitalsInputRecyclerAdapter extends Adapter<ViewHolder> {
    private final List<String> vitalsList;

    public VitalsInputRecyclerAdapter(List<String> vitalsList) {
        this.vitalsList = vitalsList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VitalsInputViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0842R.layout.layout_text_input, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        ((VitalsInputViewHolder) holder).bind((String) this.vitalsList.get(position));
    }

    public int getItemCount() {
        if (this.vitalsList == null) {
            return 0;
        }
        return this.vitalsList.size();
    }
}
