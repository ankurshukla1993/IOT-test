package com.facebook.react.devsupport;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.devsupport.RedBoxHandler.ReportCompletedListener;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;

class RedBoxDialog$2 implements OnClickListener {
    final /* synthetic */ RedBoxDialog this$0;

    RedBoxDialog$2(RedBoxDialog this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View view) {
        if (RedBoxDialog.access$400(this.this$0) != null && RedBoxDialog.access$400(this.this$0).isReportEnabled() && !RedBoxDialog.access$000(this.this$0)) {
            RedBoxDialog.access$002(this.this$0, true);
            ((TextView) Assertions.assertNotNull(RedBoxDialog.access$300(this.this$0))).setText("Reporting...");
            ((TextView) Assertions.assertNotNull(RedBoxDialog.access$300(this.this$0))).setVisibility(0);
            ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.access$200(this.this$0))).setVisibility(0);
            ((View) Assertions.assertNotNull(RedBoxDialog.access$500(this.this$0))).setVisibility(0);
            ((Button) Assertions.assertNotNull(RedBoxDialog.access$100(this.this$0))).setEnabled(false);
            RedBoxDialog.access$400(this.this$0).reportRedbox((String) Assertions.assertNotNull(RedBoxDialog.access$600(this.this$0).getLastErrorTitle()), (StackFrame[]) Assertions.assertNotNull(RedBoxDialog.access$600(this.this$0).getLastErrorStack()), RedBoxDialog.access$600(this.this$0).getSourceUrl(), (ReportCompletedListener) Assertions.assertNotNull(RedBoxDialog.access$700(this.this$0)));
        }
    }
}
