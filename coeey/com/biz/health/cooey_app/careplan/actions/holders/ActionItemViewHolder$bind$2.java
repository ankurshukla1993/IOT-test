package com.biz.health.cooey_app.careplan.actions.holders;

import android.widget.CompoundButton.OnCheckedChangeListener;
import com.cooey.common.vo.ActionItem;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "buttonView", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: ActionItemViewHolder.kt */
final class ActionItemViewHolder$bind$2 implements OnCheckedChangeListener {
    final /* synthetic */ ActionItem $actionItem;
    final /* synthetic */ ActionItemViewHolder this$0;

    ActionItemViewHolder$bind$2(ActionItemViewHolder actionItemViewHolder, ActionItem actionItem) {
        this.this$0 = actionItemViewHolder;
        this.$actionItem = actionItem;
    }

    public final void onCheckedChanged(android.widget.CompoundButton r13, boolean r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0101 in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r12 = this;
        if (r14 == 0) goto L_0x0146;
    L_0x0002:
        r7 = new com.google.gson.GsonBuilder;
        r7.<init>();
        r3 = r7.create();
        r7 = r12.$actionItem;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r7.getParameters();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        if (r7 == 0) goto L_0x009c;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
    L_0x0014:
        r7 = r12.$actionItem;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r7.getParameterMap();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        if (r7 == 0) goto L_0x009c;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
    L_0x001c:
        r7 = r12.$actionItem;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r7.getParameterMap();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r10 = "feedBackInput";	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r7.get(r10);	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        if (r7 == 0) goto L_0x009c;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
    L_0x002a:
        r7 = r12.$actionItem;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r7.getParameterMap();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r10 = "feedBackInput";	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r7.get(r10);	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r2 = r3.toJson(r7);	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = new com.biz.health.cooey_app.careplan.actions.holders.ActionItemViewHolder$bind$2$listType$1;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7.<init>();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r6 = r7.getType();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r1 = r3.fromJson(r2, r6);	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r1 = (java.util.ArrayList) r1;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r12.$actionItem;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7.setCompleted(r14);	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        if (r1 == 0) goto L_0x0065;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
    L_0x0050:
        r7 = r1.size();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        if (r7 <= 0) goto L_0x0065;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
    L_0x0056:
        r7 = r12.this$0;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r1 = (java.util.List) r1;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r10 = r12.$actionItem;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r11 = r12.this$0;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r11 = r11.getPosition();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7.showChangeLangDialog(r1, r10, r11);	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
    L_0x0065:
        r7 = r12.this$0;
        r7 = r7.getGpsTracker();
        r7 = r7.canGetLocation();
        if (r7 == 0) goto L_0x008f;
    L_0x0071:
        r7 = r12.this$0;
        r7 = r7.getGpsTracker();
        r4 = r7.getLatitude();
        r7 = r12.this$0;
        r7 = r7.getGpsTracker();
        r8 = r7.getLongitude();
        r7 = r12.$actionItem;
        r7.setLatitude(r4);
        r7 = r12.$actionItem;
        r7.setLongitude(r8);
    L_0x008f:
        r7 = r12.$actionItem;
        r7.setCompleted(r14);
        r7 = r12.this$0;
        r10 = r12.$actionItem;
        r7.postData(r10);
    L_0x009b:
        return;
    L_0x009c:
        r7 = r12.this$0;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r7.getGpsTracker();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r7.canGetLocation();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        if (r7 == 0) goto L_0x00c6;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
    L_0x00a8:
        r7 = r12.this$0;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r7.getGpsTracker();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r4 = r7.getLatitude();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r12.this$0;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r7.getGpsTracker();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r8 = r7.getLongitude();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r12.$actionItem;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7.setLatitude(r4);	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r12.$actionItem;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7.setLongitude(r8);	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
    L_0x00c6:
        r7 = r12.$actionItem;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7.setCompleted(r14);	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r12.this$0;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r10 = r12.$actionItem;	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7.postData(r10);	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        goto L_0x0065;
    L_0x00d3:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x00d3, all -> 0x010e }
        r7 = r12.this$0;
        r7 = r7.getGpsTracker();
        r7 = r7.canGetLocation();
        if (r7 == 0) goto L_0x0101;
    L_0x00e3:
        r7 = r12.this$0;
        r7 = r7.getGpsTracker();
        r4 = r7.getLatitude();
        r7 = r12.this$0;
        r7 = r7.getGpsTracker();
        r8 = r7.getLongitude();
        r7 = r12.$actionItem;
        r7.setLatitude(r4);
        r7 = r12.$actionItem;
        r7.setLongitude(r8);
    L_0x0101:
        r7 = r12.$actionItem;
        r7.setCompleted(r14);
        r7 = r12.this$0;
        r10 = r12.$actionItem;
        r7.postData(r10);
        goto L_0x009b;
    L_0x010e:
        r7 = move-exception;
        r10 = r12.this$0;
        r10 = r10.getGpsTracker();
        r10 = r10.canGetLocation();
        if (r10 == 0) goto L_0x0139;
    L_0x011b:
        r10 = r12.this$0;
        r10 = r10.getGpsTracker();
        r4 = r10.getLatitude();
        r10 = r12.this$0;
        r10 = r10.getGpsTracker();
        r8 = r10.getLongitude();
        r10 = r12.$actionItem;
        r10.setLatitude(r4);
        r10 = r12.$actionItem;
        r10.setLongitude(r8);
    L_0x0139:
        r10 = r12.$actionItem;
        r10.setCompleted(r14);
        r10 = r12.this$0;
        r11 = r12.$actionItem;
        r10.postData(r11);
        throw r7;
    L_0x0146:
        r7 = r12.$actionItem;
        r7.setCompleted(r14);
        r7 = r12.this$0;
        r10 = r12.$actionItem;
        r7.postData(r10);
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.biz.health.cooey_app.careplan.actions.holders.ActionItemViewHolder$bind$2.onCheckedChanged(android.widget.CompoundButton, boolean):void");
    }
}
