package com.cooey.common.vitals;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.cooey.common.C0842R;

public class VitalsInputViewHolder extends ViewHolder {
    public VitalsInputViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(String hint) {
        TextInputEditText textInputEditText = (TextInputEditText) this.itemView.findViewById(C0842R.id.text_input_edit_text);
        textInputEditText.setTag(hint);
        textInputEditText.setHint(hint);
    }
}
