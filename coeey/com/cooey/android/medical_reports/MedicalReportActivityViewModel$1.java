package com.cooey.android.medical_reports;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class MedicalReportActivityViewModel$1 implements OnClickListener {
    final /* synthetic */ MedicalReportActivityViewModel this$0;

    MedicalReportActivityViewModel$1(MedicalReportActivityViewModel this$0) {
        this.this$0 = this$0;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("file/*");
        ((Activity) MedicalReportActivityViewModel.access$000(this.this$0)).startActivityForResult(intent, MedicalReportActivity.FILE_CHOOSER_REQUEST_CODE);
    }
}
