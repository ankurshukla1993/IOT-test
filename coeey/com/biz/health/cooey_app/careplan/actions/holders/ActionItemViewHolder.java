package com.biz.health.cooey_app.careplan.actions.holders;

import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.PatientApplication;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.utils.DateUtil;
import com.cooey.android.users.old.utils.GPSTracker;
import com.cooey.common.CommonDatabase;
import com.cooey.common.dao.ActionItemDao;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.ActionItem;
import com.cooey.common.vo.FeedBackInputItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0010\u0010.\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0002J&\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020-2\u0006\u00101\u001a\u00020-2\f\u00102\u001a\b\u0012\u0004\u0012\u00020403H\u0002J&\u00105\u001a\u00020'2\f\u00106\u001a\b\u0012\u0004\u0012\u000204032\u0006\u0010(\u001a\u00020)2\u0006\u00107\u001a\u00020\u0018H\u0002J*\u00108\u001a\n :*\u0004\u0018\u0001H9H9\"\u0006\b\u0000\u00109\u0018\u0001*\u00020;2\u0006\u0010<\u001a\u00020-H\b¢\u0006\u0002\u0010=R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"¨\u0006>"}, d2 = {"Lcom/biz/health/cooey_app/careplan/actions/holders/ActionItemViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "gpsTracker", "Lcom/cooey/android/users/old/utils/GPSTracker;", "(Landroid/view/View;Lcom/cooey/android/users/old/utils/GPSTracker;)V", "checkBox", "Landroid/widget/CheckBox;", "getCheckBox", "()Landroid/widget/CheckBox;", "setCheckBox", "(Landroid/widget/CheckBox;)V", "getGpsTracker", "()Lcom/cooey/android/users/old/utils/GPSTracker;", "setGpsTracker", "(Lcom/cooey/android/users/old/utils/GPSTracker;)V", "imgActionitemInfo", "Landroid/widget/ImageView;", "getImgActionitemInfo", "()Landroid/widget/ImageView;", "setImgActionitemInfo", "(Landroid/widget/ImageView;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "txtActionItem", "Landroid/widget/TextView;", "getTxtActionItem", "()Landroid/widget/TextView;", "setTxtActionItem", "(Landroid/widget/TextView;)V", "txtTime", "getTxtTime", "setTxtTime", "bind", "", "actionItem", "Lcom/cooey/common/vo/ActionItem;", "buildInfoDialog", "Landroid/support/v7/app/AlertDialog;", "message", "", "postData", "setParameterMap", "parameters", "key", "value", "", "Lcom/cooey/common/vo/FeedBackInputItem;", "showChangeLangDialog", "feedBackInputItems", "position", "fromJson", "T", "kotlin.jvm.PlatformType", "Lcom/google/gson/Gson;", "json", "(Lcom/google/gson/Gson;Ljava/lang/String;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: ActionItemViewHolder.kt */
public final class ActionItemViewHolder extends ViewHolder {
    @Nullable
    private CheckBox checkBox;
    @NotNull
    private GPSTracker gpsTracker;
    @Nullable
    private ImageView imgActionitemInfo;
    private int index;
    @Nullable
    private TextView txtActionItem;
    @Nullable
    private TextView txtTime;

    public ActionItemViewHolder(@NotNull View view, @NotNull GPSTracker gpsTracker) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(gpsTracker, "gpsTracker");
        super(view);
        this.gpsTracker = gpsTracker;
        View findViewById = this.itemView.findViewById(C0644R.id.textActionItem);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.txtActionItem = (TextView) findViewById;
        findViewById = this.itemView.findViewById(C0644R.id.checkBox);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
        }
        this.checkBox = (CheckBox) findViewById;
        findViewById = this.itemView.findViewById(C0644R.id.txt_view_time);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.txtTime = (TextView) findViewById;
        findViewById = this.itemView.findViewById(C0644R.id.img_action_item_info);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.imgActionitemInfo = (ImageView) findViewById;
    }

    @NotNull
    public final GPSTracker getGpsTracker() {
        return this.gpsTracker;
    }

    public final void setGpsTracker(@NotNull GPSTracker <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.gpsTracker = <set-?>;
    }

    @Nullable
    public final TextView getTxtActionItem() {
        return this.txtActionItem;
    }

    public final void setTxtActionItem(@Nullable TextView <set-?>) {
        this.txtActionItem = <set-?>;
    }

    @Nullable
    public final CheckBox getCheckBox() {
        return this.checkBox;
    }

    public final void setCheckBox(@Nullable CheckBox <set-?>) {
        this.checkBox = <set-?>;
    }

    @Nullable
    public final TextView getTxtTime() {
        return this.txtTime;
    }

    public final void setTxtTime(@Nullable TextView <set-?>) {
        this.txtTime = <set-?>;
    }

    @Nullable
    public final ImageView getImgActionitemInfo() {
        return this.imgActionitemInfo;
    }

    public final void setImgActionitemInfo(@Nullable ImageView <set-?>) {
        this.imgActionitemInfo = <set-?>;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int <set-?>) {
        this.index = <set-?>;
    }

    private final <T> T fromJson(@NotNull Gson $receiver, String json) {
        Intrinsics.needClassReification();
        return $receiver.fromJson(json, new ActionItemViewHolder$fromJson$1().getType());
    }

    public final void bind(@NotNull ActionItem actionItem) {
        TextView textView;
        Intrinsics.checkParameterIsNotNull(actionItem, "actionItem");
        if (actionItem.getTitle() != null) {
            textView = this.txtActionItem;
            if (textView != null) {
                textView.setText(actionItem.getTitle());
            }
        }
        TextView textView2;
        if ((Intrinsics.areEqual(actionItem.getType().toString(), CTConstants.DIET_TYPE) ^ 1) != 0) {
            textView2 = this.txtTime;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            String timeString = DateUtil.getReadableTimeFromDate(new Date(actionItem.getScheduledOn()));
            textView2 = this.txtTime;
            if (textView2 != null) {
                textView2.setText(timeString);
            }
            textView2 = this.txtTime;
            if (textView2 != null) {
                textView2.setTag(actionItem);
            }
        } else {
            textView2 = this.txtTime;
            if (textView2 != null) {
                textView2.setVisibility(4);
            }
        }
        if (actionItem.getPermissions() != null && actionItem.getPermissionFromType(1, 5)) {
            CheckBox checkBox = this.checkBox;
            if (checkBox != null) {
                checkBox.setOnCheckedChangeListener(null);
            }
            checkBox = this.checkBox;
            if (checkBox != null) {
                checkBox.setVisibility(0);
            }
            if (actionItem.isCompleted()) {
                checkBox = this.checkBox;
                if (checkBox != null) {
                    checkBox.setChecked(true);
                }
                checkBox = this.checkBox;
                if (checkBox != null) {
                    checkBox.setTag(actionItem);
                }
            } else {
                checkBox = this.checkBox;
                if (checkBox != null) {
                    checkBox.setChecked(false);
                }
                checkBox = this.checkBox;
                if (checkBox != null) {
                    checkBox.setTag(actionItem);
                }
            }
            ImageView imageView = this.imgActionitemInfo;
            if (imageView != null) {
                imageView.setOnClickListener(new ActionItemViewHolder$bind$1(this, actionItem));
            }
            CheckBox checkBox2 = this.checkBox;
            if (checkBox2 != null) {
                checkBox2.setOnCheckedChangeListener(new ActionItemViewHolder$bind$2(this, actionItem));
            }
            textView = this.txtActionItem;
            if (textView != null) {
                textView.setOnClickListener(new ActionItemViewHolder$bind$3(this, actionItem));
            }
        }
    }

    @NotNull
    public final AlertDialog buildInfoDialog(@NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Builder alertBuilder = new Builder(this.itemView.getContext());
        alertBuilder.setTitle((CharSequence) this.itemView.getContext().getResources().getString(C0644R.string.info_dialog_title));
        alertBuilder.setMessage((CharSequence) message);
        alertBuilder.setPositiveButton((CharSequence) this.itemView.getContext().getResources().getString(C0644R.string.done_dialog_btn), (OnClickListener) ActionItemViewHolder$buildInfoDialog$1.INSTANCE);
        AlertDialog show = alertBuilder.show();
        Intrinsics.checkExpressionValueIsNotNull(show, "alertBuilder.show()");
        return show;
    }

    private final void showChangeLangDialog(List<? extends FeedBackInputItem> feedBackInputItems, ActionItem actionItem, int position) {
        String nextOrSave;
        if (feedBackInputItems.size() == this.index + 1) {
            nextOrSave = "Save";
        } else {
            nextOrSave = "Next";
        }
        if (feedBackInputItems.size() > 0) {
            Builder dialogBuilder = new Builder(this.itemView.getContext());
            LayoutInflater inflater = this.itemView.getContext().getSystemService("layout_inflater");
            if (inflater == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
            }
            View dialogView = inflater.inflate(C0644R.layout.custom_dialog, null);
            dialogBuilder.setView(dialogView);
            dialogBuilder.setTitle((CharSequence) this.itemView.getContext().getString(C0644R.string.feeback));
            TextView txtTitle = (TextView) dialogView.findViewById(C0644R.id.txt_view_feedback_title);
            TextView textView = txtTitle;
            textView.setText(((FeedBackInputItem) feedBackInputItems.get(this.index)).getLabel());
            LinearLayout linearLayout = (LinearLayout) dialogView.findViewById(C0644R.id.linear_radio_response);
            LinearLayout linearLayoutEdit = (LinearLayout) dialogView.findViewById(C0644R.id.linear_edit_response);
            EditText editText = (EditText) dialogView.findViewById(C0644R.id.edt_feedback_input_response);
            RadioButton rdbTrue = (RadioButton) dialogView.findViewById(C0644R.id.rdb_true);
            RadioButton rdbFalse = (RadioButton) dialogView.findViewById(C0644R.id.rdb_false);
            String type = ((FeedBackInputItem) feedBackInputItems.get(this.index)).getType();
            CharSequence charSequence = "Text";
            if (type == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            if (type.contentEquals(charSequence)) {
                linearLayoutEdit.setVisibility(0);
            } else {
                linearLayout.setVisibility(0);
            }
            dialogBuilder.setPositiveButton((CharSequence) nextOrSave, (OnClickListener) new ActionItemViewHolder$showChangeLangDialog$1(this, nextOrSave, feedBackInputItems, editText, rdbTrue, rdbFalse, actionItem, position, linearLayoutEdit, linearLayout));
            dialogBuilder.setNegativeButton((CharSequence) this.itemView.getContext().getString(C0644R.string.cancel), (OnClickListener) new ActionItemViewHolder$showChangeLangDialog$2(this, actionItem));
            dialogBuilder.setOnCancelListener(new ActionItemViewHolder$showChangeLangDialog$3(actionItem));
            dialogBuilder.create().show();
        }
    }

    private final void postData(ActionItem actionItem) {
        new ApiClient(this.itemView.getContext(), PatientApplication.Companion.getServerUrl()).getActionItemService().isCompletedActionItem(actionItem).enqueue(new ActionItemViewHolder$postData$1());
        CommonDatabase commonDatabase = PatientApplication.Companion.getCommonDatabase();
        if (commonDatabase != null) {
            ActionItemDao ActionItemDao = commonDatabase.ActionItemDao();
            if (ActionItemDao != null) {
                ActionItemDao.insert(actionItem);
            }
        }
    }

    private final String setParameterMap(String parameters, String key, List<? extends FeedBackInputItem> value) {
        Map map = new HashMap();
        Gson gson = new GsonBuilder().create();
        ActionItemViewHolder this_$iv = this;
        Object fromJson = new Gson().fromJson(parameters, new ActionItemViewHolder$setParameterMap$$inlined$fromJson$1().getType());
        Intrinsics.checkExpressionValueIsNotNull(fromJson, "Gson().fromJson<HashMap<…ing, Object>>(parameters)");
        map = (Map) fromJson;
        if (value == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
        }
        map.put(key, value);
        String toJson = gson.toJson(map);
        Intrinsics.checkExpressionValueIsNotNull(toJson, "gson.toJson(map)");
        return toJson;
    }
}
