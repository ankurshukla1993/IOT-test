package com.facebook.react.views.picker;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class ReactPicker$2 implements OnItemSelectedListener {
    final /* synthetic */ ReactPicker this$0;

    ReactPicker$2(ReactPicker this$0) {
        this.this$0 = this$0;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (!(ReactPicker.access$000(this.this$0) || ReactPicker.access$100(this.this$0) == null)) {
            ReactPicker.access$100(this.this$0).onItemSelected(position);
        }
        ReactPicker.access$002(this.this$0, false);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        if (!(ReactPicker.access$000(this.this$0) || ReactPicker.access$100(this.this$0) == null)) {
            ReactPicker.access$100(this.this$0).onItemSelected(-1);
        }
        ReactPicker.access$002(this.this$0, false);
    }
}
