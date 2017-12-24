package com.biz.health.cooey_app;

import android.app.ProgressDialog;
import android.widget.Toast;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J(\u0010\n\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"com/biz/health/cooey_app/RescheduleEventActivity$requestNewEvent$1", "Lretrofit2/Callback;", "Ljava/lang/Void;", "(Lcom/biz/health/cooey_app/RescheduleEventActivity;Lkotlin/jvm/internal/Ref$ObjectRef;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: RescheduleEventActivity.kt */
public final class RescheduleEventActivity$requestNewEvent$1 implements Callback<Void> {
    final /* synthetic */ ObjectRef $progressDialog;
    final /* synthetic */ RescheduleEventActivity this$0;

    RescheduleEventActivity$requestNewEvent$1(RescheduleEventActivity $outer, ObjectRef $captured_local_variable$1) {
        this.this$0 = $outer;
        this.$progressDialog = $captured_local_variable$1;
    }

    public void onResponse(@Nullable Call<Void> call, @Nullable Response<Void> response) {
        if (response != null && response.isSuccessful()) {
            ((ProgressDialog) this.$progressDialog.element).dismiss();
            Toast.makeText(this.this$0, "Request for event change has been sent", 0).show();
            this.this$0.onBackPressed();
        }
    }

    public void onFailure(@Nullable Call<Void> call, @Nullable Throwable t) {
        if (t != null) {
            t.printStackTrace();
        }
        Toast.makeText(this.this$0, "Something went wrong", 0).show();
        this.this$0.onBackPressed();
    }
}
