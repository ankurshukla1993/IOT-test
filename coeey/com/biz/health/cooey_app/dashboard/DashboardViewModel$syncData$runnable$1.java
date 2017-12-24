package com.biz.health.cooey_app.dashboard;

import com.cooey.common.realm_store.ActivityStore;
import com.cooey.common.realm_store.MedicineStore;
import com.cooey.common.vo.Activity;
import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.Reminder;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 7})
/* compiled from: DashboardViewModel.kt */
final class DashboardViewModel$syncData$runnable$1 implements Runnable {
    final /* synthetic */ DashboardViewModel this$0;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 7})
    /* compiled from: DashboardViewModel.kt */
    static final class C06452 implements Runnable {
        final /* synthetic */ List $medicines;
        final /* synthetic */ DashboardViewModel$syncData$runnable$1 this$0;

        C06452(DashboardViewModel$syncData$runnable$1 dashboardViewModel$syncData$runnable$1, List list) {
            this.this$0 = dashboardViewModel$syncData$runnable$1;
            this.$medicines = list;
        }

        public final void run() {
            for (Medicine medicine : this.$medicines) {
                if (!(medicine == null || medicine.getReminders() == null || medicine.getReminders().size() <= 0)) {
                    int size = medicine.realmGet$reminders().size();
                    for (int i = 0; i < size; i++) {
                        Reminder reminder = (Reminder) medicine.realmGet$reminders().get(i);
                        if (!(reminder.getTimeOfDay() == null || reminder.getId() == null || reminder.getItemId() == null)) {
                            MedicineStore.getInstance(this.this$0.this$0.getContext()).writeToMedicine(medicine);
                        }
                    }
                }
            }
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 7})
    /* compiled from: DashboardViewModel.kt */
    static final class C06473 implements Runnable {
        final /* synthetic */ List $noteList;
        final /* synthetic */ DashboardViewModel$syncData$runnable$1 this$0;

        C06473(DashboardViewModel$syncData$runnable$1 dashboardViewModel$syncData$runnable$1, List list) {
            this.this$0 = dashboardViewModel$syncData$runnable$1;
            this.$noteList = list;
        }

        public final void run() {
            ProtoRealm.getInstance(this.this$0.this$0.getContext()).getRealm().executeTransaction(new Transaction() {
                public final void execute(Realm realm1) {
                    realm1.copyToRealmOrUpdate((Iterable) this.$noteList);
                }
            });
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 7})
    /* compiled from: DashboardViewModel.kt */
    static final class C06484 implements Runnable {
        final /* synthetic */ Activity $event;
        final /* synthetic */ DashboardViewModel$syncData$runnable$1 this$0;

        C06484(DashboardViewModel$syncData$runnable$1 dashboardViewModel$syncData$runnable$1, Activity activity) {
            this.this$0 = dashboardViewModel$syncData$runnable$1;
            this.$event = activity;
        }

        public final void run() {
            ActivityStore.getInstance(this.this$0.this$0.getContext()).writeToActivity(this.$event);
        }
    }

    DashboardViewModel$syncData$runnable$1(DashboardViewModel dashboardViewModel) {
        this.this$0 = dashboardViewModel;
    }

    public final void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.biz.health.cooey_app.dashboard.DashboardViewModel$syncData$runnable$1.run():void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.addJump(MethodNode.java:370)
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:356)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 5 more
*/
        /*
        r0 = this;
        r0 = r22;
        r4 = r0.this$0;
        r20 = r4.getVitalsForUser();
        r0 = r22;
        r4 = r0.this$0;
        r15 = r4.getMedicinesForUser();
        r0 = r22;
        r4 = r0.this$0;
        r17 = r4.getAllNotesForUser();
        r0 = r22;
        r4 = r0.this$0;
        r14 = r4.getActivitiesForUser();
        if (r20 == 0) goto L_0x0073;
    L_0x0022:
        r0 = r22;
        r4 = r0.this$0;
        r4 = r4.getVitalRepository();
        r4 = r4.getVitalDao();
        r0 = r20;
        r4.insert(r0);
        r18 = new java.util.ArrayList;
        r18.<init>();
        r18 = (java.util.List) r18;
        r2 = r20;
        r2 = (java.lang.Iterable) r2;
        r4 = r2.iterator();
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x0061;
    L_0x0048:
        r12 = r4.next();
        r19 = r12;
        r19 = (com.cooey.android.vitals.Vital) r19;
        r0 = r22;
        r5 = r0.this$0;
        r0 = r19;
        r5 = r5.getTimelineItemForVital(r0);
        r0 = r18;
        r0.add(r5);
        goto L_0x0042;
        r0 = r22;
        r4 = r0.this$0;
        r4 = r4.getVitalRepository();
        r4 = r4.getTimelineItemDao();
        r0 = r18;
        r4.insert(r0);
    L_0x0073:
        if (r15 == 0) goto L_0x0095;
        r0 = r22;
        r4 = r0.this$0;
        r4 = r4.getContext();
        if (r4 != 0) goto L_0x0087;
        r4 = new kotlin.TypeCastException;
        r5 = "null cannot be cast to non-null type android.support.v7.app.AppCompatActivity";
        r4.<init>(r5);
        throw r4;
        r4 = (android.support.v7.app.AppCompatActivity) r4;
        r5 = new com.biz.health.cooey_app.dashboard.DashboardViewModel$syncData$runnable$1$2;
        r0 = r22;
        r5.<init>(r0, r15);
        r5 = (java.lang.Runnable) r5;
        r4.runOnUiThread(r5);
        if (r17 == 0) goto L_0x0112;
        r4 = r17.size();
        if (r4 <= 0) goto L_0x0112;
        r18 = new java.util.ArrayList;
        r18.<init>();
        r18 = (java.util.List) r18;
        r21 = r17.iterator();
        r4 = r21.hasNext();
        if (r4 == 0) goto L_0x00df;
        r16 = r21.next();
        r16 = (com.cooey.common.vo.Note) r16;
        r3 = new com.cooey.common.vo.timeline.TimelineItem;
        r4 = r16.getId();
        r5 = "note.id";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5);
        r5 = com.cooey.common.vo.timeline.TimelineItemType.INSTANCE;
        r5 = r5.getNOTE();
        r6 = r16.getCreatedOn();
        r8 = r16.getUserId();
        r9 = "note.userId";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r9);
        r9 = 0;
        r10 = 16;
        r11 = 0;
        r3.<init>(r4, r5, r6, r8, r9, r10, r11);
        r0 = r18;
        r0.add(r3);
        goto L_0x00a8;
        r0 = r22;
        r4 = r0.this$0;
        r4 = r4.getContext();
        if (r4 != 0) goto L_0x00f1;
        r4 = new kotlin.TypeCastException;
        r5 = "null cannot be cast to non-null type android.support.v7.app.AppCompatActivity";
        r4.<init>(r5);
        throw r4;
        r4 = (android.support.v7.app.AppCompatActivity) r4;
        r5 = new com.biz.health.cooey_app.dashboard.DashboardViewModel$syncData$runnable$1$3;
        r0 = r22;
        r1 = r17;
        r5.<init>(r0, r1);
        r5 = (java.lang.Runnable) r5;
        r4.runOnUiThread(r5);
        r0 = r22;
        r4 = r0.this$0;
        r4 = r4.getVitalRepository();
        r4 = r4.getTimelineItemDao();
        r0 = r18;
        r4.insert(r0);
        if (r14 == 0) goto L_0x0191;
        r4 = r14.size();
        if (r4 <= 0) goto L_0x0191;
        r18 = new java.util.ArrayList;
        r18.<init>();
        r18 = (java.util.List) r18;
        r21 = r14.iterator();
        r4 = r21.hasNext();
        if (r4 == 0) goto L_0x0180;
        r13 = r21.next();
        r13 = (com.cooey.common.vo.Activity) r13;
        r0 = r22;
        r4 = r0.this$0;
        r4 = r4.getContext();
        if (r4 != 0) goto L_0x0143;
        r4 = new kotlin.TypeCastException;
        r5 = "null cannot be cast to non-null type android.support.v7.app.AppCompatActivity";
        r4.<init>(r5);
        throw r4;
        r4 = (android.support.v7.app.AppCompatActivity) r4;
        r5 = new com.biz.health.cooey_app.dashboard.DashboardViewModel$syncData$runnable$1$4;
        r0 = r22;
        r5.<init>(r0, r13);
        r5 = (java.lang.Runnable) r5;
        r4.runOnUiThread(r5);
        r3 = new com.cooey.common.vo.timeline.TimelineItem;
        r4 = r13.getId();
        r5 = "event.id";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5);
        r5 = com.cooey.common.vo.timeline.TimelineItemType.INSTANCE;
        r5 = r5.getEVENT();
        r6 = r13.getStart();
        r6 = java.lang.Long.parseLong(r6);
        r8 = r13.getPatientId();
        r9 = "event.patientId";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r9);
        r9 = 0;
        r10 = 16;
        r11 = 0;
        r3.<init>(r4, r5, r6, r8, r9, r10, r11);
        r0 = r18;
        r0.add(r3);
        goto L_0x0125;
        r0 = r22;
        r4 = r0.this$0;
        r4 = r4.getVitalRepository();
        r4 = r4.getTimelineItemDao();
        r0 = r18;
        r4.insert(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.biz.health.cooey_app.dashboard.DashboardViewModel$syncData$runnable$1.run():void");
    }
}
