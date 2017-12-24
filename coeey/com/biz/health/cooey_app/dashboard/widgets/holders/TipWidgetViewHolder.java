package com.biz.health.cooey_app.dashboard.widgets.holders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.databinding.TipWidgetBinding;
import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Content;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.User;
import com.thefinestartist.finestwebview.FinestWebView.Builder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipWidgetViewHolder extends WidgetViewHolder {
    private final Context context;
    TipWidgetBinding tipWidgetBinding;

    public TipWidgetViewHolder(Context context, TipWidgetBinding tipWidgetBinding) {
        super(tipWidgetBinding);
        this.tipWidgetBinding = tipWidgetBinding;
        this.context = context;
    }

    public void bind(final User user) {
        PreferenceStore preferenceStore = new PreferenceStore();
        final Session session = preferenceStore.getSession(this.context);
        final String userId = preferenceStore.getUser(this.context).getId();
        this.tipWidgetBinding.tipCard.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final ProgressDialog progressdialog = new ProgressDialog(TipWidgetViewHolder.this.context);
                progressdialog.setMessage("Loading Content for " + user.getFirstName());
                progressdialog.show();
                new ApiClient(TipWidgetViewHolder.this.context, "http://api.cooey.co.in/ehealth/").getContentService().getContentForUserId(session.getId(), userId).enqueue(new Callback<List<Content>>() {
                    public void onResponse(Call<List<Content>> call, Response<List<Content>> response) {
                        if (response.body() == null || ((List) response.body()).size() <= 0) {
                            Toast.makeText(TipWidgetViewHolder.this.context, C0644R.string.no_content, 0).show();
                        } else if (((Content) ((List) response.body()).get(0)).getType().contentEquals("BLOG")) {
                            new Builder(TipWidgetViewHolder.this.context).titleDefault("Default Title").toolbarScrollFlags(5).gradientDivider(false).dividerHeight(100).toolbarColorRes(C0644R.color.colorPrimary).dividerColorRes(C0644R.color.background).iconDefaultColorRes(C0644R.color.backgroundDark).iconDisabledColorRes(C0644R.color.gray).iconPressedColorRes(C0644R.color.black).progressBarColorRes(C0644R.color.colorSecondary).backPressToClose(false).setCustomAnimations(C0644R.anim.activity_open_enter, C0644R.anim.activity_open_exit, C0644R.anim.activity_close_enter, C0644R.anim.activity_close_exit).show(((Content) ((List) response.body()).get(0)).getUrl());
                        } else if (response.body() != null && ((List) response.body()).size() > 0) {
                            TipWidgetViewHolder.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((Content) ((List) response.body()).get(0)).getUrl())));
                        }
                        progressdialog.dismiss();
                    }

                    public void onFailure(Call<List<Content>> call, Throwable t) {
                        progressdialog.dismiss();
                    }
                });
            }
        });
    }
}
