package com.facebook.react.devsupport;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class DevSupportManagerImpl$16 implements OnClickListener {
    final /* synthetic */ DevSupportManagerImpl this$0;
    final /* synthetic */ DevOptionHandler[] val$optionHandlers;

    DevSupportManagerImpl$16(DevSupportManagerImpl this$0, DevOptionHandler[] devOptionHandlerArr) {
        this.this$0 = this$0;
        this.val$optionHandlers = devOptionHandlerArr;
    }

    public void onClick(DialogInterface dialog, int which) {
        this.val$optionHandlers[which].onOptionSelected();
        DevSupportManagerImpl.access$1002(this.this$0, null);
    }
}
