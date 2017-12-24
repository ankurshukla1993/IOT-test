package com.cooey.android.users.old.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.R;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.StyleStore;
import com.cooey.common.vo.ChangePassword;
import com.cooey.common.vo.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText confirmPwd;
    TextInputLayout confirmPwdLayout;
    ConstraintLayout constraintLayout;
    EditText currentPwd;
    TextInputLayout currentPwdLayout;
    EditText newPwd;
    TextInputLayout newPwdLayout;
    String serverUrl;
    String strConfirmPwd;
    String strCurrentPwd;
    String strNewPwd;
    TextView title;
    String token;
    Toolbar toolbar;
    User user;

    class C07671 implements OnClickListener {
        C07671() {
        }

        public void onClick(DialogInterface dialog, int which) {
            ChangePasswordActivity.this.finish();
        }
    }

    class C07682 implements Callback<ResponseBody> {
        C07682() {
        }

        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                ChangePasswordActivity.this.showSnackBar("Password has been changed successfully");
                ChangePasswordActivity.this.delay();
                return;
            }
            ChangePasswordActivity.this.currentPwdLayout.setError("Current password is wrong");
        }

        public void onFailure(Call<ResponseBody> call, Throwable t) {
            ChangePasswordActivity.this.showSnackBar("Something went wrong. Please try again later");
        }
    }

    class C07693 implements Runnable {
        C07693() {
        }

        public void run() {
            ChangePasswordActivity.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0757R.layout.activity_change_password);
        if (getIntent().getExtras() != null) {
            this.user = (User) getIntent().getParcelableExtra("USER");
            this.token = getIntent().getStringExtra("SessionId");
            this.serverUrl = getIntent().getStringExtra("serverurl");
            initializeUI();
            return;
        }
        onBackPressed();
    }

    private void initializeUI() {
        this.currentPwd = (EditText) findViewById(C0757R.id.currentPwd);
        this.newPwd = (EditText) findViewById(C0757R.id.newPwd);
        this.confirmPwd = (EditText) findViewById(C0757R.id.confirmPwd);
        this.toolbar = (Toolbar) findViewById(C0757R.id.changePwdToolBar);
        this.currentPwdLayout = (TextInputLayout) findViewById(C0757R.id.currentPwdLayout);
        this.newPwdLayout = (TextInputLayout) findViewById(C0757R.id.newPwdLayout);
        this.confirmPwdLayout = (TextInputLayout) findViewById(C0757R.id.confirmPwdLayout);
        this.constraintLayout = (ConstraintLayout) findViewById(C0757R.id.constraintLayoutChangePwd);
        this.title = (TextView) findViewById(C0757R.id.changePwdTitile);
        this.title.setTypeface(new StyleStore(this).getFont("lato_black"));
        setSupportActionBar(this.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onSupportNavigateUp() {
        displayDiscardDialog("Discard Changes?", "You will lose the entered information");
        return true;
    }

    public void onBackPressed() {
        displayDiscardDialog("Discard Changes?", "You will lose the entered information");
        super.onBackPressed();
    }

    private void displayDiscardDialog(String title, String message) {
        if (isEmpty()) {
            finish();
        } else {
            new Builder(this).setTitle(title).setMessage(message).setPositiveButton("Discard", new C07671()).setNegativeButton("Cancel", null).setCancelable(false).show();
        }
    }

    private void getDataFromFields() {
        if (this.user != null) {
            this.strNewPwd = this.newPwd.getText().toString().trim();
            this.strConfirmPwd = this.confirmPwd.getText().toString().trim();
            this.strCurrentPwd = this.currentPwd.getText().toString();
            return;
        }
        this.strNewPwd = "";
        this.strConfirmPwd = "";
        this.strCurrentPwd = "";
    }

    private boolean isEmpty() {
        getDataFromFields();
        if (this.strNewPwd.isEmpty() && this.strConfirmPwd.isEmpty() && this.strCurrentPwd.isEmpty()) {
            return true;
        }
        return false;
    }

    private void validatePwd() {
        getDataFromFields();
        this.confirmPwdLayout.setErrorEnabled(false);
        this.currentPwdLayout.setErrorEnabled(false);
        this.newPwdLayout.setErrorEnabled(false);
        if (this.user == null) {
            return;
        }
        if (this.strNewPwd.isEmpty() || this.strConfirmPwd.isEmpty() || this.strCurrentPwd.isEmpty()) {
            if (this.strCurrentPwd.isEmpty()) {
                this.currentPwdLayout.setError("Enter current password");
            }
            if (this.strNewPwd.isEmpty()) {
                this.newPwdLayout.setError("Enter new password");
            }
            if (this.strConfirmPwd.isEmpty()) {
                this.confirmPwdLayout.setError("Enter confirm password");
            }
        } else if (this.strNewPwd.length() < 6) {
            this.newPwdLayout.setError("should have at least 6 characters");
        } else if (this.strConfirmPwd.length() < 6) {
            this.confirmPwdLayout.setError("should have at least 6 characters");
        } else if (this.strNewPwd.equals(this.strConfirmPwd)) {
            setNewPassword();
        } else {
            this.confirmPwdLayout.setError("New Password and Confirm Password should be same");
            this.newPwdLayout.setError("New Password and Confirm Password should be same");
        }
    }

    private void setNewPassword() {
        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPwd(this.strNewPwd);
        changePassword.setOldPwd(this.strCurrentPwd);
        changePassword.setEmail(this.user.getEmail());
        changePassword.setUserId(this.user.getId());
        changePassword.setType(this.user.getType());
        new ApiClient(this, this.serverUrl).getUsersService().changePasswordForUser(this.token, changePassword).enqueue(new C07682());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0757R.id.changePwd) {
            validatePwd();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0757R.menu.menu_change_pwd, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(C0757R.id.constraintLayoutChangePwd), message, 0);
        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(-1);
        snackbar.show();
    }

    private void delay() {
        try {
            new Handler().postDelayed(new C07693(), 3900);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
