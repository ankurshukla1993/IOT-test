package com.facebook.react.devsupport;

import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;

class RedBoxDialog$5 implements OnClickListener {
    final /* synthetic */ RedBoxDialog this$0;

    RedBoxDialog$5(RedBoxDialog this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        String title = RedBoxDialog.access$600(this.this$0).getLastErrorTitle();
        StackFrame[] stack = RedBoxDialog.access$600(this.this$0).getLastErrorStack();
        Assertions.assertNotNull(title);
        Assertions.assertNotNull(stack);
        new RedBoxDialog$CopyToHostClipBoardTask(RedBoxDialog.access$600(this.this$0)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{StackTraceHelper.formatStackTrace(title, stack)});
    }
}
