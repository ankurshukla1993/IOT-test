package com.cooey.android.users.old.careplan;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.utils.DateUtil;
import com.cooey.android.users.old.utils.GPSTracker;
import com.cooey.android.users.old.viewholders.ActionItemPatientHeaderViewHolder;
import com.cooey.android.users.old.viewholders.TodoViewHolder;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.ActionItem;
import com.cooey.common.vo.FeedBackInputItem;
import com.cooey.common.vo.ListItem;
import com.cooey.common.vo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActionItemAdapter extends Adapter<TodoViewHolder> {
    private List<ListItem> actionItemList;
    List<EditText> allEds;
    private GPSTracker gpsTracker;
    public int index;
    private Context mContext;
    private String serverUrl;
    User user;

    class C07711 implements OnClickListener {
        C07711() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    class C07755 implements Callback<ActionItem> {
        C07755() {
        }

        public void onResponse(Call<ActionItem> call, Response<ActionItem> response) {
        }

        public void onFailure(Call<ActionItem> call, Throwable t) {
            t.printStackTrace();
        }
    }

    public class ActionItemViewHolder extends TodoViewHolder {
        CheckBox checkBox;
        private Context context;
        ImageView imgActionitemInfo;
        TextView txtActionItem;
        TextView txtTime;

        public ActionItemViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            this.txtActionItem = (TextView) itemView.findViewById(C0757R.id.textActionItem);
            this.checkBox = (CheckBox) itemView.findViewById(C0757R.id.checkBox);
            this.txtTime = (TextView) itemView.findViewById(C0757R.id.txt_view_time);
            this.imgActionitemInfo = (ImageView) itemView.findViewById(C0757R.id.img_action_item_info);
        }

        public void bindType(ListItem listItem, final int position) {
            final ActionItem actionItem = (ActionItem) listItem;
            if (actionItem.getTitle() != null) {
                this.txtActionItem.setText(actionItem.getTitle());
            }
            if (actionItem == null || actionItem.getType().toString().equals(CTConstants.DIET_TYPE)) {
                this.txtTime.setVisibility(4);
            } else {
                this.txtTime.setVisibility(0);
                this.txtTime.setText(DateUtil.getReadableTimeFromDate(new Date(actionItem.getScheduledOn())));
                this.txtTime.setTag(actionItem);
            }
            if (actionItem.getPermissions() != null && actionItem.getPermissionFromType(1, 5)) {
                this.checkBox.setOnCheckedChangeListener(null);
                this.checkBox.setVisibility(0);
                if (actionItem.isCompleted()) {
                    this.checkBox.setChecked(true);
                    this.checkBox.setTag(actionItem);
                } else {
                    this.checkBox.setChecked(false);
                    this.checkBox.setTag(actionItem);
                }
                this.imgActionitemInfo.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        try {
                            if (actionItem == null || actionItem.getParameterMap() == null || actionItem.getParameterMap().get("instruction") == null || ((String) actionItem.getParameterMap().get("instruction")).isEmpty()) {
                                ActionItemAdapter.this.buildInfoDialog(ActionItemAdapter.this.mContext, ActionItemViewHolder.this.context.getString(C0757R.string.action_item_no_info));
                            } else {
                                ActionItemAdapter.this.buildInfoDialog(ActionItemAdapter.this.mContext, (String) actionItem.getParameterMap().get("instruction"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            ActionItemAdapter.this.buildInfoDialog(ActionItemAdapter.this.mContext, ActionItemViewHolder.this.context.getString(C0757R.string.action_item_no_info));
                        }
                    }
                });
                this.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    class C07771 extends TypeToken<ArrayList<FeedBackInputItem>> {
                        C07771() {
                        }
                    }

                    public void onCheckedChanged(android.widget.CompoundButton r14, boolean r15) {
                        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x014f in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                        /*
                        r13 = this;
                        if (r15 == 0) goto L_0x01db;
                    L_0x0002:
                        r7 = new com.google.gson.GsonBuilder;
                        r7.<init>();
                        r3 = r7.create();
                        r7 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.getParameters();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        if (r7 == 0) goto L_0x00bd;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                    L_0x0013:
                        r7 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.getParameterMap();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        if (r7 == 0) goto L_0x00bd;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                    L_0x001b:
                        r7 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.getParameterMap();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r10 = "feedBackInput";	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.get(r10);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        if (r7 == 0) goto L_0x00bd;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                    L_0x0029:
                        r7 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.getParameterMap();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r10 = "feedBackInput";	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.get(r10);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r2 = r3.toJson(r7);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = new com.cooey.android.users.old.careplan.ActionItemAdapter$ActionItemViewHolder$2$1;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7.<init>();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r6 = r7.getType();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r1 = r3.fromJson(r2, r6);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r1 = (java.util.ArrayList) r1;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7.setCompleted(r15);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        if (r1 == 0) goto L_0x0060;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                    L_0x004f:
                        r7 = r1.size();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        if (r7 <= 0) goto L_0x0060;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                    L_0x0055:
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r10 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r11 = r10;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7.showChangeLangDialog(r1, r10, r11);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                    L_0x0060:
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7 = r7.gpsTracker;
                        r7 = r7.canGetLocation();
                        if (r7 == 0) goto L_0x0090;
                    L_0x006e:
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7 = r7.gpsTracker;
                        r4 = r7.getLatitude();
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7 = r7.gpsTracker;
                        r8 = r7.getLongitude();
                        r7 = r0;
                        r7.setLatitude(r4);
                        r7 = r0;
                        r7.setLongitude(r8);
                    L_0x0090:
                        r7 = r0;
                        r7.setCompleted(r15);
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7 = r7.actionItemList;
                        r10 = r10;
                        r11 = r0;
                        r7.set(r10, r11);
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = r10.mContext;
                        r11 = r0;
                        r7.postData(r10, r11);
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7.notifyDataSetChanged();
                    L_0x00bc:
                        return;
                    L_0x00bd:
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.gpsTracker;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.canGetLocation();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        if (r7 == 0) goto L_0x00ed;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                    L_0x00cb:
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.gpsTracker;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r4 = r7.getLatitude();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.gpsTracker;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r8 = r7.getLongitude();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7.setLatitude(r4);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7.setLongitude(r8);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                    L_0x00ed:
                        r7 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7.setCompleted(r15);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = r7.actionItemList;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r10 = r10;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r11 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7.set(r10, r11);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r10 = r10.mContext;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r11 = r0;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7.postData(r10, r11);	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7.notifyDataSetChanged();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        goto L_0x0060;
                    L_0x011b:
                        r0 = move-exception;
                        r0.printStackTrace();	 Catch:{ Exception -> 0x011b, all -> 0x017d }
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7 = r7.gpsTracker;
                        r7 = r7.canGetLocation();
                        if (r7 == 0) goto L_0x014f;
                    L_0x012d:
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7 = r7.gpsTracker;
                        r4 = r7.getLatitude();
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7 = r7.gpsTracker;
                        r8 = r7.getLongitude();
                        r7 = r0;
                        r7.setLatitude(r4);
                        r7 = r0;
                        r7.setLongitude(r8);
                    L_0x014f:
                        r7 = r0;
                        r7.setCompleted(r15);
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7 = r7.actionItemList;
                        r10 = r10;
                        r11 = r0;
                        r7.set(r10, r11);
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = r10.mContext;
                        r11 = r0;
                        r7.postData(r10, r11);
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7.notifyDataSetChanged();
                        goto L_0x00bc;
                    L_0x017d:
                        r7 = move-exception;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = r10.gpsTracker;
                        r10 = r10.canGetLocation();
                        if (r10 == 0) goto L_0x01ae;
                    L_0x018c:
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = r10.gpsTracker;
                        r4 = r10.getLatitude();
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = r10.gpsTracker;
                        r8 = r10.getLongitude();
                        r10 = r0;
                        r10.setLatitude(r4);
                        r10 = r0;
                        r10.setLongitude(r8);
                    L_0x01ae:
                        r10 = r0;
                        r10.setCompleted(r15);
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = r10.actionItemList;
                        r11 = r10;
                        r12 = r0;
                        r10.set(r11, r12);
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r11 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r11 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r11 = r11.mContext;
                        r12 = r0;
                        r10.postData(r11, r12);
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10.notifyDataSetChanged();
                        throw r7;
                    L_0x01db:
                        r7 = r0;
                        r7.setCompleted(r15);
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r7 = r7.actionItemList;
                        r10 = r10;
                        r11 = r0;
                        r7.set(r10, r11);
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r10 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = r10.mContext;
                        r11 = r0;
                        r7.postData(r10, r11);
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.this;
                        r7 = com.cooey.android.users.old.careplan.ActionItemAdapter.this;
                        r10 = r10;
                        r7.notifyItemChanged(r10);
                        goto L_0x00bc;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.users.old.careplan.ActionItemAdapter.ActionItemViewHolder.2.onCheckedChanged(android.widget.CompoundButton, boolean):void");
                    }
                });
                this.txtActionItem.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (actionItem.getType().equalsIgnoreCase(CTConstants.VITAL_TYPE)) {
                            String type = (String) actionItem.getParameterMap().get("type");
                            Intent i = new Intent();
                            i.setAction("ActionItemVitalValues");
                            i.putExtra("userId", CTConstants.PATIENT_ID);
                            i.putExtra("type", type);
                            ActionItemViewHolder.this.context.sendBroadcast(i);
                        } else if (!actionItem.getType().equalsIgnoreCase("Diet")) {
                        }
                    }
                });
            }
        }
    }

    public ActionItemAdapter(List<ListItem> actionItemList, Context mContext, User user, String serverUrl) {
        this.actionItemList = actionItemList;
        this.mContext = mContext;
        this.gpsTracker = new GPSTracker(mContext);
        this.user = user;
        this.serverUrl = serverUrl;
    }

    public TodoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new ActionItemPatientHeaderViewHolder(this.mContext, LayoutInflater.from(viewGroup.getContext()).inflate(C0757R.layout.item_action_item_header_patient_user, viewGroup, false), this.user);
            case 2:
                return new ActionItemViewHolder(this.mContext, LayoutInflater.from(viewGroup.getContext()).inflate(C0757R.layout.item_action_item_user, viewGroup, false));
            default:
                try {
                    return new ActionItemViewHolder(this.mContext, null);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
        }
        e.printStackTrace();
        return null;
    }

    public void onBindViewHolder(TodoViewHolder todoViewHolder, int i) {
        todoViewHolder.bindType((ListItem) this.actionItemList.get(i), i);
    }

    public int getItemCount() {
        if (this.actionItemList.size() > 0) {
            return this.actionItemList.size();
        }
        return 0;
    }

    public void filterActionItems(List<ListItem> actionItemList) {
        this.actionItemList.clear();
        this.actionItemList.addAll(actionItemList);
        notifyDataSetChanged();
    }

    public int getItemViewType(int position) {
        return ((ListItem) this.actionItemList.get(position)).getListItemType();
    }

    public AlertDialog buildInfoDialog(Context context, String message) {
        Builder alertBuilder = new Builder(context);
        alertBuilder.setTitle(context.getResources().getString(C0757R.string.info_dialog_title));
        alertBuilder.setMessage(message);
        alertBuilder.setPositiveButton(context.getResources().getString(C0757R.string.done_dialog_btn), new C07711());
        return alertBuilder.show();
    }

    private void showChangeLangDialog(List<FeedBackInputItem> feedBackInputItems, ActionItem actionItem, int position) {
        String nextOrSave;
        if (feedBackInputItems.size() == this.index + 1) {
            nextOrSave = "Save";
        } else {
            nextOrSave = "Next";
        }
        if (feedBackInputItems.size() > 0) {
            Builder dialogBuilder = new Builder(this.mContext);
            View dialogView = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(C0757R.layout.custom_dialog, null);
            dialogBuilder.setView(dialogView);
            dialogBuilder.setTitle(this.mContext.getString(C0757R.string.feeback));
            TextView txtTitle = (TextView) dialogView.findViewById(C0757R.id.txt_view_feedback_title);
            TextView textView = txtTitle;
            textView.setText(((FeedBackInputItem) feedBackInputItems.get(this.index)).getLabel());
            final LinearLayout linearLayout = (LinearLayout) dialogView.findViewById(C0757R.id.linear_radio_response);
            final LinearLayout linearLayoutEdit = (LinearLayout) dialogView.findViewById(C0757R.id.linear_edit_response);
            final EditText editText = (EditText) dialogView.findViewById(C0757R.id.edt_feedback_input_response);
            final RadioButton rdbTrue = (RadioButton) dialogView.findViewById(C0757R.id.rdb_true);
            final RadioButton rdbFalse = (RadioButton) dialogView.findViewById(C0757R.id.rdb_false);
            if (((FeedBackInputItem) feedBackInputItems.get(this.index)).getType().contentEquals("Text")) {
                linearLayoutEdit.setVisibility(0);
            } else {
                linearLayout.setVisibility(0);
            }
            final List<FeedBackInputItem> list = feedBackInputItems;
            final ActionItem actionItem2 = actionItem;
            final int i = position;
            dialogBuilder.setPositiveButton(nextOrSave, new OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String trueOrFalse;
                    if (nextOrSave.contentEquals("Next")) {
                        if (((FeedBackInputItem) list.get(ActionItemAdapter.this.index)).getType().contentEquals("Text")) {
                            ((FeedBackInputItem) list.get(ActionItemAdapter.this.index)).setValue(editText.getText().toString());
                        } else if (((FeedBackInputItem) list.get(ActionItemAdapter.this.index)).getType().contentEquals("Boolean")) {
                            if (rdbTrue.isChecked()) {
                                trueOrFalse = "Yes";
                            } else if (rdbFalse.isChecked()) {
                                trueOrFalse = "No";
                            } else {
                                trueOrFalse = "No";
                            }
                            ((FeedBackInputItem) list.get(ActionItemAdapter.this.index)).setValue(trueOrFalse);
                        }
                        ActionItemAdapter actionItemAdapter = ActionItemAdapter.this;
                        actionItemAdapter.index++;
                        ActionItemAdapter.this.showChangeLangDialog(list, actionItem2, i);
                        return;
                    }
                    if (((FeedBackInputItem) list.get(ActionItemAdapter.this.index)).getType().contentEquals("Text")) {
                        linearLayoutEdit.setVisibility(0);
                        ((FeedBackInputItem) list.get(ActionItemAdapter.this.index)).setValue(editText.getText().toString());
                    } else if (((FeedBackInputItem) list.get(ActionItemAdapter.this.index)).getType().contentEquals("Boolean")) {
                        linearLayout.setVisibility(0);
                        if (rdbTrue.isChecked()) {
                            trueOrFalse = "Yes";
                        } else if (rdbFalse.isChecked()) {
                            trueOrFalse = "No";
                        } else {
                            trueOrFalse = "No";
                        }
                        ((FeedBackInputItem) list.get(ActionItemAdapter.this.index)).setValue(trueOrFalse);
                    }
                    if (ActionItemAdapter.this.gpsTracker.canGetLocation()) {
                        double latitude = ActionItemAdapter.this.gpsTracker.getLatitude();
                        double longitude = ActionItemAdapter.this.gpsTracker.getLongitude();
                        actionItem2.setLatitude(latitude);
                        actionItem2.setLongitude(longitude);
                    }
                    if (list.size() > 0) {
                        actionItem2.setParameters(ActionItemAdapter.this.setParameterMap(actionItem2.getParameters(), "feedBackInput", list));
                    }
                    actionItem2.setCompleted(true);
                    ActionItemAdapter.this.actionItemList.set(i, actionItem2);
                    ActionItemAdapter.this.postData(ActionItemAdapter.this.mContext, actionItem2);
                    ActionItemAdapter.this.notifyItemChanged(i);
                    ActionItemAdapter.this.index = 0;
                }
            });
            final ActionItem actionItem3 = actionItem;
            final int i2 = position;
            dialogBuilder.setNegativeButton(this.mContext.getString(C0757R.string.cancel), new OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    if (ActionItemAdapter.this.index > 0) {
                        ActionItemAdapter.this.index = 0;
                    }
                    actionItem3.setCompleted(false);
                    ActionItemAdapter.this.actionItemList.set(i2, actionItem3);
                    ActionItemAdapter.this.notifyItemChanged(i2);
                }
            });
            actionItem3 = actionItem;
            i2 = position;
            dialogBuilder.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    actionItem3.setCompleted(false);
                    ActionItemAdapter.this.actionItemList.set(i2, actionItem3);
                    ActionItemAdapter.this.notifyItemChanged(i2);
                }
            });
            dialogBuilder.create().show();
        }
    }

    private String setParameterMap(String parameters, String key, List<FeedBackInputItem> value) {
        Map<String, Object> map = new HashMap();
        Gson gson = new GsonBuilder().create();
        Object map2 = (Map) gson.fromJson(parameters, map.getClass());
        map2.put(key, value);
        return gson.toJson(map2);
    }

    private void postData(Context context, ActionItem actionItem) {
        new ApiClient(context, this.serverUrl).getActionItemService().isCompletedActionItem(actionItem).enqueue(new C07755());
    }
}
