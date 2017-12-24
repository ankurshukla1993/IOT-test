package com.cooey.android.medical_reports;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.cooey.android.medical_reports.databinding.ActivityMedicalReportBinding;
import java.io.FileNotFoundException;

public class MedicalReportActivity extends AppCompatActivity {
    public static final int FILE_CHOOSER_REQUEST_CODE = 8432;
    private String authToken;
    MedicalReportActivityViewModel medicalReportActivityViewModel;
    private String serverUrl;
    private String userId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userId = getIntent().getStringExtra("userId");
        this.authToken = getIntent().getStringExtra("authToken");
        this.serverUrl = getIntent().getStringExtra("serverurl");
        ActivityMedicalReportBinding activityMedicalReportBinding = (ActivityMedicalReportBinding) DataBindingUtil.setContentView(this, C0756R.layout.activity_medical_report);
        setSupportActionBar(activityMedicalReportBinding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MedicalReportPermissionHepler medicalReportPermissionHepler = new MedicalReportPermissionHepler();
        if (!medicalReportPermissionHepler.checkPermission(this)) {
            medicalReportPermissionHepler.requestPermission(this);
        }
        this.medicalReportActivityViewModel = new MedicalReportActivityViewModel(this, new MedicalReportFileUploadManager(this, this.userId, this.authToken, this.serverUrl), new MedicalReportAgent(this, this.userId, this.authToken, this.serverUrl));
        activityMedicalReportBinding.setMedicalReportActivityViewModel(this.medicalReportActivityViewModel);
        this.medicalReportActivityViewModel.getMedicalReports();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == FILE_CHOOSER_REQUEST_CODE) {
            if (resultCode == -1 && intent != null) {
                Uri uri = intent.getData();
                String fileName = FileHelper.getFileNameFromContentURI(this, uri);
                if (fileName == null) {
                    fileName = intent.getData().getLastPathSegment();
                }
                try {
                    this.medicalReportActivityViewModel.uploadFile(fileName, getContentResolver().openInputStream(uri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1224:
                if (grantResults.length <= 0 || grantResults[0] != 0) {
                    Toast.makeText(this, "You must allow access to storage to upload and download a document ", 0).show();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
