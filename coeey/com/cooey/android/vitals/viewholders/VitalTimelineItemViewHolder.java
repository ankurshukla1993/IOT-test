package com.cooey.android.vitals.viewholders;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.TextView;
import com.cooey.android.vitals.R;
import com.cooey.android.vitals.helpers.DateHelper;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.holders.TimelineItemViewHolder;
import com.cooey.common.views.TimelineItemView;
import com.cooey.common.vo.timeline.TimelineItem;
import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001c\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010>\u001a\u0004\u0018\u00010=H\u0016J\u001e\u0010?\u001a\u0004\u0018\u00010@2\b\u0010A\u001a\u0004\u0018\u00010B2\b\u0010C\u001a\u0004\u0018\u00010BH\u0002J\u0012\u0010D\u001a\u00020;2\b\u0010E\u001a\u0004\u0018\u00010BH\u0002J*\u0010F\u001a\n H*\u0004\u0018\u0001HGHG\"\u0006\b\u0000\u0010G\u0018\u0001*\u00020I2\u0006\u0010J\u001a\u00020BH\b¢\u0006\u0002\u0010KR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u000e\"\u0004\b%\u0010\u0010R\u0010\u0010&\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109¨\u0006L"}, d2 = {"Lcom/cooey/android/vitals/viewholders/VitalTimelineItemViewHolder;", "Lcom/cooey/common/holders/TimelineItemViewHolder;", "view", "Lcom/cooey/common/views/TimelineItemView;", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "showPrimary", "", "(Lcom/cooey/common/views/TimelineItemView;Landroid/arch/lifecycle/LifecycleOwner;Lcom/cooey/android/vitals/repositories/VitalRepository;Z)V", "col", "", "getCol", "()I", "setCol", "(I)V", "dateFormat", "Ljava/text/DateFormat;", "getDateFormat", "()Ljava/text/DateFormat;", "setDateFormat", "(Ljava/text/DateFormat;)V", "dateHelper", "Lcom/cooey/android/vitals/helpers/DateHelper;", "getDateHelper", "()Lcom/cooey/android/vitals/helpers/DateHelper;", "setDateHelper", "(Lcom/cooey/android/vitals/helpers/DateHelper;)V", "getLifecycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "notesTextView", "Landroid/widget/TextView;", "row", "getRow", "setRow", "timeTextView", "getView", "()Lcom/cooey/common/views/TimelineItemView;", "setView", "(Lcom/cooey/common/views/TimelineItemView;)V", "vitalGridLayout", "Landroid/widget/GridLayout;", "getVitalGridLayout", "()Landroid/widget/GridLayout;", "setVitalGridLayout", "(Landroid/widget/GridLayout;)V", "vitalNameTextView", "getVitalNameTextView", "()Landroid/widget/TextView;", "setVitalNameTextView", "(Landroid/widget/TextView;)V", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "bind", "", "currentTimelineItem", "Lcom/cooey/common/vo/timeline/TimelineItem;", "prevTimelineItem", "generateParameterView", "Landroid/view/View;", "key", "", "value", "generateParametersView", "parameters", "fromJson", "T", "kotlin.jvm.PlatformType", "Lcom/google/gson/Gson;", "json", "(Lcom/google/gson/Gson;Ljava/lang/String;)Ljava/lang/Object;", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalTimelineItemViewHolder.kt */
public final class VitalTimelineItemViewHolder extends TimelineItemViewHolder {
    private int col = 1;
    @NotNull
    private DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    @NotNull
    private DateHelper dateHelper = new DateHelper();
    @Nullable
    private LifecycleOwner lifecycleOwner;
    private TextView notesTextView;
    private int row = 1;
    private TextView timeTextView;
    @Nullable
    private TimelineItemView view;
    @Nullable
    private GridLayout vitalGridLayout;
    @Nullable
    private TextView vitalNameTextView;
    @NotNull
    private VitalRepository vitalRepository;

    private final void generateParametersView(java.lang.String r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.cooey.android.vitals.viewholders.VitalTimelineItemViewHolder.generateParametersView(java.lang.String):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.addJump(MethodNode.java:370)
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:356)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 7 more
*/
        /*
        r0 = this;
        r10 = 1;
        if (r12 == 0) goto L_0x00c7;
    L_0x0003:
        r8 = r12.length();
        if (r8 <= 0) goto L_0x00c7;
    L_0x0009:
        r8 = new com.google.gson.Gson;
        r8.<init>();
        r2 = r12;
        r9 = new com.cooey.android.vitals.viewholders.VitalTimelineItemViewHolder$generateParametersView$$inlined$fromJson$1;
        r9.<init>();
        r9 = r9.getType();
        r5 = r8.fromJson(r2, r9);
        r5 = (java.util.Map) r5;
        r11.col = r10;
        r11.row = r10;
        r8 = r11.vitalGridLayout;
        if (r8 == 0) goto L_0x0029;
    L_0x0026:
        r8.removeAllViewsInLayout();
    L_0x0029:
        r0 = r5.keySet();
        r0 = (java.lang.Iterable) r0;
        r9 = r0.iterator();
        r8 = r9.hasNext();
        if (r8 == 0) goto L_0x009f;
    L_0x0039:
        r1 = r9.next();
        r3 = r1;
        r3 = (java.lang.String) r3;
        r8 = "notes";
        r8 = (java.lang.CharSequence) r8;
        if (r3 != 0) goto L_0x004e;
        r8 = new kotlin.TypeCastException;
        r9 = "null cannot be cast to non-null type java.lang.String";
        r8.<init>(r9);
        throw r8;
        r8 = r3.contentEquals(r8);
        if (r8 != 0) goto L_0x009c;
        r8 = "mood";
        r8 = (java.lang.CharSequence) r8;
        if (r3 != 0) goto L_0x0062;
        r8 = new kotlin.TypeCastException;
        r9 = "null cannot be cast to non-null type java.lang.String";
        r8.<init>(r9);
        throw r8;
        r8 = r3.contentEquals(r8);
        if (r8 != 0) goto L_0x009c;
        r8 = "context";
        r8 = (java.lang.CharSequence) r8;
        if (r3 != 0) goto L_0x0076;
        r8 = new kotlin.TypeCastException;
        r9 = "null cannot be cast to non-null type java.lang.String";
        r8.<init>(r9);
        throw r8;
        r8 = r3.contentEquals(r8);
        if (r8 != 0) goto L_0x009c;
        r7 = r5.get(r3);
        r7 = (java.lang.String) r7;
        if (r7 == 0) goto L_0x008a;
        r8 = r7.length();
        if (r8 == 0) goto L_0x009c;
        r6 = r11.generateParameterView(r3, r7);
        r8 = r11.vitalGridLayout;
        if (r8 == 0) goto L_0x0095;
        r8.addView(r6);
        r8 = r11.vitalGridLayout;
        if (r8 == 0) goto L_0x009c;
        r8.invalidate();
        goto L_0x0033;
        r8 = "notes";
        r4 = r5.get(r8);
        r4 = (java.lang.String) r4;
        if (r4 == 0) goto L_0x00c8;
        r8 = r4.length();
        if (r8 <= 0) goto L_0x00c8;
        r9 = r11.notesTextView;
        if (r9 == 0) goto L_0x00bf;
        r8 = "notes";
        r8 = r5.get(r8);
        r8 = (java.lang.CharSequence) r8;
        r9.setText(r8);
        r8 = r11.notesTextView;
        if (r8 == 0) goto L_0x00c7;
        r9 = 0;
        r8.setVisibility(r9);
    L_0x00c7:
        return;
        r8 = r11.notesTextView;
        if (r8 == 0) goto L_0x00c7;
        r9 = 8;
        r8.setVisibility(r9);
        goto L_0x00c7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.vitals.viewholders.VitalTimelineItemViewHolder.generateParametersView(java.lang.String):void");
    }

    public VitalTimelineItemViewHolder(@Nullable TimelineItemView view, @Nullable LifecycleOwner lifecycleOwner, @NotNull VitalRepository vitalRepository, boolean showPrimary) {
        TextView textView;
        GridLayout gridLayout;
        Intrinsics.checkParameterIsNotNull(vitalRepository, "vitalRepository");
        super(view);
        this.view = view;
        this.lifecycleOwner = lifecycleOwner;
        this.vitalRepository = vitalRepository;
        TimelineItemView timelineItemView = this.view;
        if (timelineItemView != null) {
            textView = (TextView) timelineItemView.findViewById(R.id.vital_name);
        } else {
            textView = null;
        }
        this.vitalNameTextView = textView;
        timelineItemView = this.view;
        if (timelineItemView != null) {
            gridLayout = (GridLayout) timelineItemView.findViewById(R.id.field_container);
        } else {
            gridLayout = null;
        }
        this.vitalGridLayout = gridLayout;
        timelineItemView = this.view;
        if (timelineItemView != null) {
            textView = (TextView) timelineItemView.findViewById(R.id.notes_text);
        } else {
            textView = null;
        }
        this.notesTextView = textView;
        timelineItemView = this.view;
        if (timelineItemView != null) {
            textView = (TextView) timelineItemView.findViewById(R.id.time);
        } else {
            textView = null;
        }
        this.timeTextView = textView;
    }

    @Nullable
    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    @Nullable
    public final TimelineItemView getView() {
        return this.view;
    }

    @NotNull
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setLifecycleOwner(@Nullable LifecycleOwner <set-?>) {
        this.lifecycleOwner = <set-?>;
    }

    public final void setView(@Nullable TimelineItemView <set-?>) {
        this.view = <set-?>;
    }

    public final void setVitalRepository(@NotNull VitalRepository <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.vitalRepository = <set-?>;
    }

    @Nullable
    public final TextView getVitalNameTextView() {
        return this.vitalNameTextView;
    }

    public final void setVitalNameTextView(@Nullable TextView <set-?>) {
        this.vitalNameTextView = <set-?>;
    }

    @Nullable
    public final GridLayout getVitalGridLayout() {
        return this.vitalGridLayout;
    }

    public final void setVitalGridLayout(@Nullable GridLayout <set-?>) {
        this.vitalGridLayout = <set-?>;
    }

    @NotNull
    public final DateHelper getDateHelper() {
        return this.dateHelper;
    }

    public final void setDateHelper(@NotNull DateHelper <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.dateHelper = <set-?>;
    }

    @NotNull
    public final DateFormat getDateFormat() {
        return this.dateFormat;
    }

    public final void setDateFormat(@NotNull DateFormat <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.dateFormat = <set-?>;
    }

    private final <T> T fromJson(@NotNull Gson $receiver, String json) {
        Intrinsics.needClassReification();
        return $receiver.fromJson(json, new VitalTimelineItemViewHolder$fromJson$1().getType());
    }

    public final int getRow() {
        return this.row;
    }

    public final void setRow(int <set-?>) {
        this.row = <set-?>;
    }

    public final int getCol() {
        return this.col;
    }

    public final void setCol(int <set-?>) {
        this.col = <set-?>;
    }

    public void bind(@Nullable TimelineItem currentTimelineItem, @Nullable TimelineItem prevTimelineItem) {
        if (currentTimelineItem != null) {
            TextView textView = this.notesTextView;
            if (textView != null) {
                textView.setVisibility(8);
            }
            this.vitalRepository.getVitalDao().getVital(currentTimelineItem.getId()).observe(this.lifecycleOwner, new VitalTimelineItemViewHolder$bind$1(this, currentTimelineItem, prevTimelineItem));
        }
    }

    private final View generateParameterView(String key, String value) {
        Context context;
        Object[] objArr;
        Double d = null;
        TimelineItemView timelineItemView = this.view;
        if (timelineItemView != null) {
            context = timelineItemView.getContext();
        } else {
            context = null;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_vital_parameter, this.vitalGridLayout, false);
        TextView parameterValue = (TextView) view.findViewById(R.id.parameter_value);
        ((TextView) view.findViewById(R.id.parameter_key)).setText(key);
        String str = "%.2f";
        Object[] objArr2 = new Object[1];
        if (value != null) {
            d = Double.valueOf(Double.parseDouble(value));
            objArr = objArr2;
        } else {
            objArr = objArr2;
        }
        objArr2[0] = d;
        String format = String.format(str, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        parameterValue.setText(format);
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.columnSpec = GridLayout.spec(this.col);
        layoutParams.rowSpec = GridLayout.spec(this.row);
        this.col++;
        if (this.col == 3) {
            this.col = 1;
            this.row++;
        }
        layoutParams.setGravity(119);
        view.setLayoutParams(layoutParams);
        return view;
    }
}
