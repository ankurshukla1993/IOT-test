package com.facebook.react.devsupport;

import android.text.SpannedString;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.devsupport.RedBoxHandler.ReportCompletedListener;

class RedBoxDialog$1 implements ReportCompletedListener {
    final /* synthetic */ RedBoxDialog this$0;

    RedBoxDialog$1(RedBoxDialog this$0) {
        this.this$0 = this$0;
    }

    public void onReportSuccess(SpannedString spannedString) {
        RedBoxDialog.access$002(this.this$0, false);
        ((Button) Assertions.assertNotNull(RedBoxDialog.access$100(this.this$0))).setEnabled(true);
        ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.access$200(this.this$0))).setVisibility(8);
        ((TextView) Assertions.assertNotNull(RedBoxDialog.access$300(this.this$0))).setText(spannedString);
    }

    public void onReportError(SpannedString spannedString) {
        RedBoxDialog.access$002(this.this$0, false);
        ((Button) Assertions.assertNotNull(RedBoxDialog.access$100(this.this$0))).setEnabled(true);
        ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.access$200(this.this$0))).setVisibility(8);
        ((TextView) Assertions.assertNotNull(RedBoxDialog.access$300(this.this$0))).setText(spannedString);
    }
}
