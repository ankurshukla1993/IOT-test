package com.cooey.android.medical_reports;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.os.Environment;
import android.support.design.C0086R;
import android.support.design.widget.Snackbar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.common.net.HttpHeaders;
import humanize.Humanize;
import java.util.Date;

public class MedicalReportItemViewModel extends BaseObservable {
    private final Context context;
    private MedicalReport medicalReport;
    private String timeString;

    MedicalReportItemViewModel(Context context) {
        this.context = context;
    }

    public void setMedicalReport(MedicalReport medicalReport) {
        this.medicalReport = medicalReport;
        notifyPropertyChanged(BR.medicalReport);
        notifyPropertyChanged(BR.timeString);
    }

    @Bindable
    public MedicalReport getMedicalReport() {
        return this.medicalReport;
    }

    @Bindable
    public String getTimeString() {
        if (this.medicalReport != null) {
            this.timeString = Humanize.naturalTime(new Date(this.medicalReport.getCreatedOn()));
        }
        return this.timeString;
    }

    public void downloadReport() {
        MedicalReportPermissionHepler medicalReportPermissionHepler = new MedicalReportPermissionHepler();
        if (medicalReportPermissionHepler.checkPermission(this.context)) {
            Uri uri = Uri.parse(this.medicalReport.getUrl());
            Request r = new Request(uri);
            String extension = FileHelper.getExtensionForFilePath(uri.getPath());
            r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, this.medicalReport.getName() + extension);
            if (extension.contentEquals(".pdf")) {
                r.setMimeType("application/pdf");
            }
            if (extension.contentEquals(".jpg") || extension.contentEquals(".jpeg")) {
                r.setMimeType("image/jpeg");
            }
            if (extension.contentEquals(".bmp")) {
                r.setMimeType("image/bmp");
            }
            if (extension.contentEquals(".png")) {
                r.setMimeType("image/png");
            }
            r.allowScanningByMediaScanner();
            r.setNotificationVisibility(1);
            ((DownloadManager) this.context.getSystemService("download")).enqueue(r);
            Toast.makeText(this.context, "Downloading...", 0).show();
            return;
        }
        Snackbar snackbar = Snackbar.make(((Activity) this.context).findViewById(16908290), (CharSequence) "Allow the app to access the storage and try again", 0);
        snackbar.setAction(HttpHeaders.ALLOW, new 1(this, medicalReportPermissionHepler));
        ((TextView) snackbar.getView().findViewById(C0086R.id.snackbar_text)).setTextColor(-1);
        snackbar.show();
    }
}
