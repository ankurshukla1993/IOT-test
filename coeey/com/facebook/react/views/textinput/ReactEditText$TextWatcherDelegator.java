package com.facebook.react.views.textinput;

import android.text.Editable;
import android.text.TextWatcher;
import java.util.Iterator;

class ReactEditText$TextWatcherDelegator implements TextWatcher {
    final /* synthetic */ ReactEditText this$0;

    private ReactEditText$TextWatcherDelegator(ReactEditText reactEditText) {
        this.this$0 = reactEditText;
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!ReactEditText.access$100(this.this$0) && ReactEditText.access$200(this.this$0) != null) {
            Iterator it = ReactEditText.access$200(this.this$0).iterator();
            while (it.hasNext()) {
                ((TextWatcher) it.next()).beforeTextChanged(s, start, count, after);
            }
        }
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!ReactEditText.access$100(this.this$0) && ReactEditText.access$200(this.this$0) != null) {
            Iterator it = ReactEditText.access$200(this.this$0).iterator();
            while (it.hasNext()) {
                ((TextWatcher) it.next()).onTextChanged(s, start, before, count);
            }
        }
    }

    public void afterTextChanged(Editable s) {
        if (!ReactEditText.access$100(this.this$0) && ReactEditText.access$200(this.this$0) != null) {
            Iterator it = ReactEditText.access$200(this.this$0).iterator();
            while (it.hasNext()) {
                ((TextWatcher) it.next()).afterTextChanged(s);
            }
        }
    }
}
