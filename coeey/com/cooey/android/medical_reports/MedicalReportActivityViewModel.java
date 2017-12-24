package com.cooey.android.medical_reports;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.design.C0086R;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.TextView;
import com.google.common.net.HttpHeaders;
import java.io.InputStream;
import java.util.List;

public class MedicalReportActivityViewModel extends BaseObservable {
    private final Context context;
    private boolean isProgess;
    private final MedicalReportAgent medicalReportAgent;
    private final MedicalReportFileUploadManager medicalReportFileUploadManager;
    private MedicalReportRecyclerViewAdapter medicalReportRecyclerViewAdapter;
    private List<MedicalReport> medicalReports;

    @Bindable
    public boolean isProgess() {
        return this.isProgess;
    }

    public void setProgess(boolean progess) {
        this.isProgess = progess;
        notifyPropertyChanged(BR.progess);
    }

    public MedicalReportActivityViewModel(Context context, MedicalReportFileUploadManager medicalReportFileUploadManager, MedicalReportAgent medicalReportAgent) {
        this.context = context;
        this.medicalReportFileUploadManager = medicalReportFileUploadManager;
        this.medicalReportAgent = medicalReportAgent;
        this.medicalReportRecyclerViewAdapter = new MedicalReportRecyclerViewAdapter(context);
    }

    @Bindable
    public MedicalReportRecyclerViewAdapter getMedicalReportRecyclerViewAdapter() {
        return this.medicalReportRecyclerViewAdapter;
    }

    public void showFileSelector() {
        MedicalReportPermissionHepler medicalReportPermissionHepler = new MedicalReportPermissionHepler();
        if (medicalReportPermissionHepler.checkPermission(this.context)) {
            Builder builder = new Builder(this.context, R.style.Theme_AppCompat_Dialog_Alert);
            builder.setTitle((CharSequence) "File Upload");
            builder.setMessage((CharSequence) "Please select a file to upload");
            builder.setPositiveButton((CharSequence) "OK", new 1(this));
            builder.setNegativeButton((CharSequence) "Cancel", null);
            builder.show();
            return;
        }
        Snackbar snackbar = Snackbar.make(((Activity) this.context).findViewById(16908290), (CharSequence) "Allow the app to access the storage and try again", 0);
        snackbar.setAction(HttpHeaders.ALLOW, new 2(this, medicalReportPermissionHepler));
        ((TextView) snackbar.getView().findViewById(C0086R.id.snackbar_text)).setTextColor(-1);
        snackbar.show();
    }

    public void uploadFile(String fileName, InputStream inputStream) {
        this.medicalReportFileUploadManager.upload(fileName, inputStream, new 3(this));
    }

    public void getMedicalReports() {
        setProgess(true);
        this.medicalReportAgent.getMedicalReports(new 4(this));
    }
}
