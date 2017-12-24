package com.facebook.react.devsupport;

import android.view.View;
import android.view.View.OnClickListener;

class RedBoxDialog$3 implements OnClickListener {
    final /* synthetic */ RedBoxDialog this$0;

    RedBoxDialog$3(RedBoxDialog this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        RedBoxDialog.access$600(this.this$0).handleReloadJS();
    }
}
