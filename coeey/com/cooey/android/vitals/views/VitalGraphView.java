package com.cooey.android.vitals.views;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.cooey.android.vitals.Field;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.db.chart.view.LineChartView;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\b\u0010/\u001a\u00020,H\u0002J,\u00100\u001a\u00020,2\f\u00101\u001a\b\u0012\u0004\u0012\u000203022\u0016\u00104\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rJ*\u00105\u001a\n 7*\u0004\u0018\u0001H6H6\"\u0006\b\u0000\u00106\u0018\u0001*\u0002082\u0006\u00109\u001a\u00020\fH\b¢\u0006\u0002\u0010:R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u0006;"}, d2 = {"Lcom/cooey/android/vitals/views/VitalGraphView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "chart", "Lcom/db/chart/view/LineChartView;", "colors", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getColors", "()Ljava/util/ArrayList;", "setColors", "(Ljava/util/ArrayList;)V", "fieldGraphView", "Landroid/widget/RelativeLayout;", "formatter", "Ljava/text/SimpleDateFormat;", "getFormatter", "()Ljava/text/SimpleDateFormat;", "setFormatter", "(Ljava/text/SimpleDateFormat;)V", "lifeCycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "getLifeCycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifeCycleOwner", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "userId", "getUserId", "()Ljava/lang/String;", "setUserId", "(Ljava/lang/String;)V", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "loadVitals", "", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "setUpView", "setupChart", "vitals", "", "Lcom/cooey/android/vitals/Vital;", "fieldKeys", "fromJson", "T", "kotlin.jvm.PlatformType", "Lcom/google/gson/Gson;", "json", "(Lcom/google/gson/Gson;Ljava/lang/String;)Ljava/lang/Object;", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalGraphView.kt */
public final class VitalGraphView extends FrameLayout {
    private HashMap _$_findViewCache;
    private LineChartView chart;
    @NotNull
    private ArrayList<String> colors;
    private RelativeLayout fieldGraphView;
    @NotNull
    private SimpleDateFormat formatter;
    @Nullable
    private LifecycleOwner lifeCycleOwner;
    @Nullable
    private String userId;
    @Nullable
    private VitalRepository vitalRepository;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public final void setupChart(@org.jetbrains.annotations.NotNull java.util.List<com.cooey.android.vitals.Vital> r1, @org.jetbrains.annotations.NotNull java.util.ArrayList<java.lang.String> r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.cooey.android.vitals.views.VitalGraphView.setupChart(java.util.List, java.util.ArrayList):void
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
        r27 = "vitals";
        r0 = r35;
        r1 = r27;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r1);
        r27 = "fieldKeys";
        r0 = r36;
        r1 = r27;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r1);
        r17 = 0;
        r0 = r34;
        r0 = r0.fieldGraphView;
        r27 = r0;
        if (r27 == 0) goto L_0x00e7;
    L_0x001d:
        r28 = com.cooey.android.vitals.R.id.vertical_container;
        r27 = r27.findViewById(r28);
        r27 = (android.widget.LinearLayout) r27;
        r24 = r27;
        r15 = new java.util.HashMap;
        r15.<init>();
        r23 = new java.util.HashMap;
        r23.<init>();
        r4 = r35;
        r4 = (java.lang.Iterable) r4;
        r27 = r4.iterator();
        r28 = r27.hasNext();
        if (r28 == 0) goto L_0x00f2;
    L_0x003f:
        r9 = r27.next();
        r26 = r9;
        r26 = (com.cooey.android.vitals.Vital) r26;
        r28 = new com.google.gson.Gson;
        r28.<init>();
        r12 = r26.getParameters();
        if (r12 != 0) goto L_0x0055;
        kotlin.jvm.internal.Intrinsics.throwNpe();
        r29 = new com.cooey.android.vitals.views.VitalGraphView$$special$$inlined$fromJson$1;
        r29.<init>();
        r29 = r29.getType();
        r0 = r28;
        r1 = r29;
        r18 = r0.fromJson(r12, r1);
        r18 = (java.util.Map) r18;
        r4 = r36;
        r4 = (java.lang.Iterable) r4;
        r28 = r4.iterator();
        r29 = r28.hasNext();
        if (r29 == 0) goto L_0x00ed;
        r9 = r28.next();
        r13 = r9;
        r13 = (java.lang.String) r13;
        r0 = r18;
        r21 = r0.get(r13);
        r21 = (java.lang.String) r21;
        if (r21 == 0) goto L_0x00eb;
        r11 = kotlin.text.StringsKt__StringNumberConversionsKt.toFloatOrNull(r21);
        if (r11 != 0) goto L_0x0093;
        r29 = 0;
        r11 = java.lang.Float.valueOf(r29);
        r14 = r15.get(r13);
        r14 = (java.util.ArrayList) r14;
        if (r14 != 0) goto L_0x00a0;
        r14 = new java.util.ArrayList;
        r14.<init>();
        r0 = r34;
        r0 = r0.formatter;
        r29 = r0;
        r30 = new java.util.Date;
        r32 = r26.getTakenOn();
        r0 = r30;
        r1 = r32;
        r0.<init>(r1);
        r8 = r29.format(r30);
        r14.add(r8);
        r0 = r23;
        r22 = r0.get(r13);
        r22 = (java.util.ArrayList) r22;
        if (r22 != 0) goto L_0x00c9;
        r22 = new java.util.ArrayList;
        r22.<init>();
        r0 = r22;
        r0.add(r11);
        r0 = r23;
        r1 = r22;
        r0.put(r13, r1);
        r15.put(r13, r14);
        r29 = r11.floatValue();
        r29 = (r29 > r17 ? 1 : (r29 == r17 ? 0 : -1));
        if (r29 <= 0) goto L_0x00e4;
        r17 = r11.floatValue();
        goto L_0x0070;
    L_0x00e7:
        r24 = 0;
        goto L_0x0027;
        r11 = 0;
        goto L_0x008b;
        goto L_0x0039;
        r6 = 0;
        r4 = r36;
        r4 = (java.lang.Iterable) r4;
        r30 = r4.iterator();
        r27 = r30.hasNext();
        if (r27 == 0) goto L_0x020a;
        r9 = r30.next();
        r13 = r9;
        r13 = (java.lang.String) r13;
        r0 = r23;
        r22 = r0.get(r13);
        r22 = (java.util.ArrayList) r22;
        r14 = r15.get(r13);
        r14 = (java.util.ArrayList) r14;
        r27 = r34.getContext();
        r28 = android.view.LayoutInflater.from(r27);
        r29 = com.cooey.android.vitals.R.layout.layout_graph_field;
        r27 = r24;
        r27 = (android.view.ViewGroup) r27;
        r31 = 0;
        r0 = r28;
        r1 = r29;
        r2 = r27;
        r3 = r31;
        r25 = r0.inflate(r1, r2, r3);
        r27 = com.cooey.android.vitals.R.id.color;
        r0 = r25;
        r1 = r27;
        r5 = r0.findViewById(r1);
        r5 = (android.widget.FrameLayout) r5;
        r0 = r34;
        r0 = r0.colors;
        r27 = r0;
        r28 = r6 % 7;
        r27 = r27.get(r28);
        r27 = (java.lang.String) r27;
        r27 = android.graphics.Color.parseColor(r27);
        r0 = r27;
        r5.setBackgroundColor(r0);
        r27 = com.cooey.android.vitals.R.id.field_name;
        r0 = r25;
        r1 = r27;
        r10 = r0.findViewById(r1);
        r10 = (android.widget.TextView) r10;
        r13 = (java.lang.CharSequence) r13;
        r10.setText(r13);
        if (r24 == 0) goto L_0x016c;
        r24.addView(r25);
        if (r24 == 0) goto L_0x0171;
        r24.invalidate();
        r28 = new com.db.chart.model.LineSet;
        if (r14 == 0) goto L_0x01fe;
        r20 = r14;
        r20 = (java.util.Collection) r20;
        if (r20 != 0) goto L_0x0183;
        r27 = new kotlin.TypeCastException;
        r28 = "null cannot be cast to non-null type java.util.Collection<T>";
        r27.<init>(r28);
        throw r27;
        r27 = r20.size();
        r0 = r27;
        r0 = new java.lang.String[r0];
        r27 = r0;
        r0 = r20;
        r1 = r27;
        r27 = r0.toArray(r1);
        if (r27 != 0) goto L_0x019f;
        r27 = new kotlin.TypeCastException;
        r28 = "null cannot be cast to non-null type kotlin.Array<T>";
        r27.<init>(r28);
        throw r27;
        r27 = (java.lang.String[]) r27;
        r29 = r28;
        r7 = r28;
        r28 = r27;
        if (r28 != 0) goto L_0x01ac;
        kotlin.jvm.internal.Intrinsics.throwNpe();
        if (r22 == 0) goto L_0x0207;
        r22 = (java.util.Collection) r22;
        r27 = kotlin.collections.CollectionsKt___CollectionsKt.toFloatArray(r22);
        if (r27 != 0) goto L_0x01b9;
        kotlin.jvm.internal.Intrinsics.throwNpe();
        r0 = r29;
        r1 = r28;
        r2 = r27;
        r0.<init>(r1, r2);
        r0 = r34;
        r0 = r0.colors;
        r27 = r0;
        r28 = r6 % 7;
        r27 = r27.get(r28);
        r27 = (java.lang.String) r27;
        r27 = android.graphics.Color.parseColor(r27);
        r0 = r27;
        r27 = r7.setColor(r0);
        r28 = "#758cbb";
        r28 = android.graphics.Color.parseColor(r28);
        r27 = r27.setDotsColor(r28);
        r28 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r27.setThickness(r28);
        r6 = r6 + 1;
        r0 = r34;
        r0 = r0.chart;
        r27 = r0;
        if (r27 == 0) goto L_0x01fa;
        r7 = (com.db.chart.model.ChartSet) r7;
        r0 = r27;
        r0.addData(r7);
        goto L_0x00fc;
        r27 = 0;
        r29 = r28;
        r7 = r28;
        r28 = r27;
        goto L_0x01a7;
        r27 = 0;
        goto L_0x01b4;
        r16 = new com.db.chart.tooltip.Tooltip;
        r27 = r34.getContext();
        r28 = com.cooey.android.vitals.R.layout.linechart_three_tooltip;
        r29 = com.cooey.android.vitals.R.id.value;
        r0 = r16;
        r1 = r27;
        r2 = r28;
        r3 = r29;
        r0.<init>(r1, r2, r3);
        r27 = com.db.chart.tooltip.Tooltip.Alignment.BOTTOM_TOP;
        r0 = r16;
        r1 = r27;
        r0.setVerticalAlignment(r1);
        r27 = 1114112000; // 0x42680000 float:58.0 double:5.50444465E-315;
        r27 = com.db.chart.util.Tools.fromDpToPx(r27);
        r0 = r27;
        r0 = (int) r0;
        r27 = r0;
        r28 = 1103626240; // 0x41c80000 float:25.0 double:5.45263811E-315;
        r28 = com.db.chart.util.Tools.fromDpToPx(r28);
        r0 = r28;
        r0 = (int) r0;
        r28 = r0;
        r0 = r16;
        r1 = r27;
        r2 = r28;
        r0.setDimensions(r1, r2);
        r27 = android.os.Build.VERSION.SDK_INT;
        r28 = 14;
        r0 = r27;
        r1 = r28;
        if (r0 < r1) goto L_0x032f;
        r27 = 3;
        r0 = r27;
        r0 = new android.animation.PropertyValuesHolder[r0];
        r27 = r0;
        r28 = 0;
        r29 = android.view.View.ALPHA;
        r30 = 1;
        r0 = r30;
        r0 = new float[r0];
        r30 = r0;
        r31 = 0;
        r32 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r30[r31] = r32;
        r29 = android.animation.PropertyValuesHolder.ofFloat(r29, r30);
        r27[r28] = r29;
        r28 = 1;
        r29 = android.view.View.SCALE_Y;
        r30 = 1;
        r0 = r30;
        r0 = new float[r0];
        r30 = r0;
        r31 = 0;
        r32 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r30[r31] = r32;
        r29 = android.animation.PropertyValuesHolder.ofFloat(r29, r30);
        r27[r28] = r29;
        r28 = 2;
        r29 = android.view.View.SCALE_X;
        r30 = 1;
        r0 = r30;
        r0 = new float[r0];
        r30 = r0;
        r31 = 0;
        r32 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r30[r31] = r32;
        r29 = android.animation.PropertyValuesHolder.ofFloat(r29, r30);
        r27[r28] = r29;
        r0 = r16;
        r1 = r27;
        r27 = r0.setEnterAnimation(r1);
        r28 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r27.setDuration(r28);
        r27 = 3;
        r0 = r27;
        r0 = new android.animation.PropertyValuesHolder[r0];
        r27 = r0;
        r28 = 0;
        r29 = android.view.View.ALPHA;
        r30 = 1;
        r0 = r30;
        r0 = new float[r0];
        r30 = r0;
        r31 = 0;
        r32 = 0;
        r30[r31] = r32;
        r29 = android.animation.PropertyValuesHolder.ofFloat(r29, r30);
        r27[r28] = r29;
        r28 = 1;
        r29 = android.view.View.SCALE_Y;
        r30 = 1;
        r0 = r30;
        r0 = new float[r0];
        r30 = r0;
        r31 = 0;
        r32 = 0;
        r30[r31] = r32;
        r29 = android.animation.PropertyValuesHolder.ofFloat(r29, r30);
        r27[r28] = r29;
        r28 = 2;
        r29 = android.view.View.SCALE_X;
        r30 = 1;
        r0 = r30;
        r0 = new float[r0];
        r30 = r0;
        r31 = 0;
        r32 = 0;
        r30[r31] = r32;
        r29 = android.animation.PropertyValuesHolder.ofFloat(r29, r30);
        r27[r28] = r29;
        r0 = r16;
        r1 = r27;
        r27 = r0.setExitAnimation(r1);
        r28 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r27.setDuration(r28);
        r27 = 1115815936; // 0x42820000 float:65.0 double:5.51286321E-315;
        r27 = com.db.chart.util.Tools.fromDpToPx(r27);
        r28 = 2;
        r0 = r28;
        r0 = (float) r0;
        r28 = r0;
        r27 = r27 / r28;
        r0 = r16;
        r1 = r27;
        r0.setPivotX(r1);
        r27 = 1103626240; // 0x41c80000 float:25.0 double:5.45263811E-315;
        r27 = com.db.chart.util.Tools.fromDpToPx(r27);
        r0 = r16;
        r1 = r27;
        r0.setPivotY(r1);
        r19 = 10;
        r27 = 10;
        r0 = r27;
        r0 = (float) r0;
        r27 = r0;
        r27 = r17 % r27;
        r27 = r17 - r27;
        r28 = 0;
        r0 = r28;
        r0 = (float) r0;
        r28 = r0;
        r27 = (r27 > r28 ? 1 : (r27 == r28 ? 0 : -1));
        if (r27 <= 0) goto L_0x0360;
        r27 = 10;
        r0 = r27;
        r0 = (float) r0;
        r27 = r0;
        r27 = r17 % r27;
        r27 = r17 - r27;
        r28 = 10;
        r0 = r28;
        r0 = (float) r0;
        r28 = r0;
        r27 = r27 / r28;
        r0 = r27;
        r0 = (int) r0;
        r19 = r0;
        r0 = r34;
        r0 = r0.chart;
        r27 = r0;
        if (r27 == 0) goto L_0x03b5;
        r28 = 0;
        r29 = 1101004800; // 0x41a00000 float:20.0 double:5.439686476E-315;
        r29 = r29 + r17;
        r27 = r27.setAxisBorderValues(r28, r29);
        if (r27 == 0) goto L_0x03b5;
        r28 = com.db.chart.renderer.AxisRenderer.LabelPosition.OUTSIDE;
        r27 = r27.setYLabels(r28);
        if (r27 == 0) goto L_0x03b5;
        r0 = r27;
        r1 = r19;
        r27 = r0.setStep(r1);
        if (r27 == 0) goto L_0x03b5;
        r0 = r27;
        r1 = r16;
        r28 = r0.setTooltips(r1);
        if (r28 == 0) goto L_0x03b5;
        r29 = new com.db.chart.animation.Animation;
        r29.<init>();
        r27 = new android.view.animation.BounceInterpolator;
        r27.<init>();
        r27 = (android.animation.TimeInterpolator) r27;
        r0 = r29;
        r1 = r27;
        r27 = r0.setInterpolator(r1);
        r29 = 0;
        r0 = r27;
        r1 = r29;
        r27 = r0.fromAlpha(r1);
        r0 = r28;
        r1 = r27;
        r0.show(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.vitals.views.VitalGraphView.setupChart(java.util.List, java.util.ArrayList):void");
    }

    @Nullable
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setVitalRepository(@Nullable VitalRepository <set-?>) {
        this.vitalRepository = <set-?>;
    }

    @Nullable
    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(@Nullable String <set-?>) {
        this.userId = <set-?>;
    }

    @Nullable
    public final LifecycleOwner getLifeCycleOwner() {
        return this.lifeCycleOwner;
    }

    public final void setLifeCycleOwner(@Nullable LifecycleOwner <set-?>) {
        this.lifeCycleOwner = <set-?>;
    }

    @NotNull
    public final SimpleDateFormat getFormatter() {
        return this.formatter;
    }

    public final void setFormatter(@NotNull SimpleDateFormat <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.formatter = <set-?>;
    }

    @NotNull
    public final ArrayList<String> getColors() {
        return this.colors;
    }

    public final void setColors(@NotNull ArrayList<String> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.colors = <set-?>;
    }

    private final <T> T fromJson(@NotNull Gson $receiver, String json) {
        Intrinsics.needClassReification();
        return $receiver.fromJson(json, new VitalGraphView$fromJson$1().getType());
    }

    public VitalGraphView(@Nullable Context context, @NotNull AttributeSet attrs) {
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        super(context, attrs);
        this.formatter = new SimpleDateFormat("dd MMM");
        this.colors = CollectionsKt__CollectionsKt.arrayListOf("#8CBEB2", "#F2EBBF", "#F3B562", "#F06060", "#428C5C", "#4EA64B", "#ADD96C");
        setUpView();
    }

    public VitalGraphView(@Nullable Context context) {
        super(context);
        this.formatter = new SimpleDateFormat("dd MMM");
        this.colors = CollectionsKt__CollectionsKt.arrayListOf("#8CBEB2", "#F2EBBF", "#F3B562", "#F06060", "#428C5C", "#4EA64B", "#ADD96C");
        setUpView();
    }

    private final void setUpView() {
    }

    public final void loadVitals(@NotNull VitalBlueprint vitalBlueprint) {
        LiveData liveVitalData;
        Intrinsics.checkParameterIsNotNull(vitalBlueprint, "vitalBlueprint");
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = new ArrayList();
        Iterable<Field> $receiver$iv = vitalBlueprint.getFields();
        if ($receiver$iv != null) {
            for (Field field : $receiver$iv) {
                if (field.getKey() == null) {
                    String label = field.getLabel();
                    if (label == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    label = label.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(label, "(this as java.lang.String).toLowerCase()");
                    field.setKey(label);
                }
                ((ArrayList) objectRef.element).add(field.getKey());
            }
        }
        VitalRepository vitalRepository = this.vitalRepository;
        if (vitalRepository != null) {
            String type = vitalBlueprint.getType();
            if (type == null) {
                Intrinsics.throwNpe();
            }
            String str = this.userId;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            liveVitalData = vitalRepository.getVitalsForType(type, str);
        } else {
            liveVitalData = null;
        }
        if (liveVitalData != null) {
            LifecycleOwner lifecycleOwner = this.lifeCycleOwner;
            if (lifecycleOwner == null) {
                Intrinsics.throwNpe();
            }
            liveVitalData.observe(lifecycleOwner, new VitalGraphView$loadVitals$2(this, objectRef));
        }
    }
}
