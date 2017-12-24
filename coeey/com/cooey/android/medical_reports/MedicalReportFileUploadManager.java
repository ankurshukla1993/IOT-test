package com.cooey.android.medical_reports;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.cooey.common.services.ApiClient;
import com.cooey.common.services.MediaService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalReportFileUploadManager {
    ApiClient apiClient;
    private final String authToken;
    private final Context context;
    MedicalReportAgent medicalReportAgent;
    private final ProgressDialog progressDialog;
    private String reportUrl;
    private final String userId;

    class C07543 implements OnClickListener {
        C07543() {
        }

        public void onClick(DialogInterface dialog, int whichButton) {
        }
    }

    class C07554 implements OnClickListener {
        C07554() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("file/*");
            ((Activity) MedicalReportFileUploadManager.this.context).startActivityForResult(intent, MedicalReportActivity.FILE_CHOOSER_REQUEST_CODE);
        }
    }

    public MedicalReportFileUploadManager(Context context, String userId, String authToken, String serverUrl) {
        this.context = context;
        this.userId = userId;
        this.authToken = authToken;
        this.apiClient = new ApiClient(context, serverUrl);
        this.medicalReportAgent = new MedicalReportAgent(context, userId, authToken, serverUrl);
        this.progressDialog = new ProgressDialog(context);
    }

    public void upload(String fileName, InputStream inputStream, final MedicalReporttUploadCallback medicalReporttUploadCallback) {
        this.progressDialog.setMessage("Uploading file..");
        this.progressDialog.show();
        try {
            byte[] buf = new byte[inputStream.available()];
            do {
            } while (inputStream.read(buf) != -1);
            ((MediaService) this.apiClient.create(MediaService.class)).uploadFile(fileName, RequestBody.create(MediaType.parse("application/octet-stream"), buf)).enqueue(new Callback<String>() {
                public void onResponse(Call<String> call, Response<String> response) {
                    MedicalReportFileUploadManager.this.reportUrl = "http://api.cooey.co.in/ehealth/v2/media/" + ((String) response.body());
                    MedicalReportFileUploadManager.this.showReportNameDialog(medicalReporttUploadCallback);
                    MedicalReportFileUploadManager.this.progressDialog.dismiss();
                }

                public void onFailure(Call<String> call, Throwable t) {
                    MedicalReportFileUploadManager.this.progressDialog.dismiss();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
            this.progressDialog.dismiss();
        }
    }

    public void showReportNameDialog(final MedicalReporttUploadCallback medicalReporttUploadCallback) {
        Builder dialogBuilder = new Builder(this.context);
        View dialogView = LayoutInflater.from(this.context).inflate(C0756R.layout.layout_report_name_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText reportNameEditText = (EditText) dialogView.findViewById(C0756R.id.report_name);
        dialogBuilder.setTitle("Report Name");
        dialogBuilder.setMessage("Enter the report name below");
        dialogBuilder.setPositiveButton("Done", new OnClickListener() {

            class C07521 implements Callback<MedicalReport> {
                C07521() {
                }

                public void onResponse(Call<MedicalReport> call, Response<MedicalReport> response) {
                    medicalReporttUploadCallback.onMedicalReportFileUploadComplete((MedicalReport) response.body());
                }

                public void onFailure(Call<MedicalReport> call, Throwable t) {
                    medicalReporttUploadCallback.onUploadError();
                }
            }

            public void onClick(DialogInterface dialog, int whichButton) {
                MedicalReport medicalReport = new MedicalReport();
                medicalReport.setUserId(MedicalReportFileUploadManager.this.userId);
                medicalReport.setUrl(MedicalReportFileUploadManager.this.reportUrl);
                medicalReport.setName(reportNameEditText.getText().toString());
                medicalReport.setCreatedOn(Calendar.getInstance().getTimeInMillis());
                medicalReport.setUpdatedOn(Calendar.getInstance().getTimeInMillis());
                medicalReport.setArchived(false);
                MedicalReportFileUploadManager.this.medicalReportAgent.create(medicalReport, new C07521());
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new C07543());
        dialogBuilder.create().show();
    }

    public void showFileSelector() {
        Builder builder = new Builder(this.context, C0756R.style.Theme_AppCompat_Dialog_Alert);
        builder.setTitle("File Upload");
        builder.setMessage("Please select a file to upload");
        builder.setPositiveButton("OK", new C07554());
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
