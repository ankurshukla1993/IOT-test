package com.biz.health.cooey_app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.biz.health.cooey_app.account.CooeyLoginActivity;
import com.biz.health.cooey_app.account.LoginMainActivity;
import com.cooey.common.stores.PreferenceStore;
import io.realm.Realm;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialogInterface", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "i", "", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: MainActivity.kt */
final class MainActivity$onOptionsItemSelected$1 implements OnClickListener {
    final /* synthetic */ MainActivity this$0;

    MainActivity$onOptionsItemSelected$1(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        new Thread(new Runnable() {
            public final void run() {
                PreferenceStore.clearSharedPreference(this.this$0);
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.deleteAll();
                realm.commitTransaction();
                if (Intrinsics.areEqual(this.this$0.getString(C0644R.string.tenant_id), (Object) "59103fd758e21e3dcc022760")) {
                    this.this$0.startActivity(new Intent(this.this$0, CooeyLoginActivity.class));
                    this.this$0.finish();
                    return;
                }
                this.this$0.startActivity(new Intent(this.this$0, LoginMainActivity.class));
                this.this$0.finish();
            }
        }).start();
    }
}
