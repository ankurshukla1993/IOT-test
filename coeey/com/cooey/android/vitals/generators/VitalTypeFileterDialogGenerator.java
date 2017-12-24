package com.cooey.android.vitals.generators;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/cooey/android/vitals/generators/VitalTypeFileterDialogGenerator;", "", "()V", "Companion", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalTypeFileterDialogGenerator.kt */
public final class VitalTypeFileterDialogGenerator {
    public static final Companion Companion = new Companion();
    @NotNull
    private static List<String> options = new ArrayList();
    @NotNull
    private static List<String> selectedTypes = new ArrayList();

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J@\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0018\u0010\u0016\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0018\u0012\u0004\u0012\u00020\u00190\u0017R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/cooey/android/vitals/generators/VitalTypeFileterDialogGenerator$Companion;", "", "()V", "options", "", "", "getOptions", "()Ljava/util/List;", "setOptions", "(Ljava/util/List;)V", "selectedTypes", "getSelectedTypes", "setSelectedTypes", "generate", "Landroid/support/v7/app/AlertDialog;", "context", "Landroid/content/Context;", "vitalBlueprintsRepository", "Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "showPrimary", "", "showSecondary", "filterChangeCallback", "Lkotlin/Function1;", "", "", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalTypeFileterDialogGenerator.kt */
    public static final class Companion {
        @org.jetbrains.annotations.NotNull
        public final android.support.v7.app.AlertDialog generate(@org.jetbrains.annotations.NotNull android.content.Context r1, @org.jetbrains.annotations.NotNull com.cooey.android.vitals.repositories.VitalBlueprintsRepository r2, boolean r3, boolean r4, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.util.List<java.lang.String>, kotlin.Unit> r5) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.cooey.android.vitals.generators.VitalTypeFileterDialogGenerator.Companion.generate(android.content.Context, com.cooey.android.vitals.repositories.VitalBlueprintsRepository, boolean, boolean, kotlin.jvm.functions.Function1):android.support.v7.app.AlertDialog
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:256)
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
	... 8 more
*/
            /*
            r0 = this;
            r7 = "context";
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r7);
            r7 = "vitalBlueprintsRepository";
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r7);
            r7 = "filterChangeCallback";
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r7);
            r2 = r12.getVitalBlueprintsFromDatabaseSync();
            r0 = r2;
            r0 = (java.lang.Iterable) r0;
            r7 = r0.iterator();
            r8 = r7.hasNext();
            if (r8 == 0) goto L_0x0058;
        L_0x0021:
            r5 = r7.next();
            r1 = r5;
            r1 = (com.cooey.android.vitals.VitalBlueprint) r1;
            r8 = r1.isPrimary();
            if (r8 != 0) goto L_0x0031;
            kotlin.jvm.internal.Intrinsics.throwNpe();
            r8 = r8.booleanValue();
            if (r8 == 0) goto L_0x0048;
            if (r13 == 0) goto L_0x0046;
            r8 = com.cooey.android.vitals.generators.VitalTypeFileterDialogGenerator.Companion;
            r8 = r8.getOptions();
            r9 = r1.getName();
            r8.add(r9);
            goto L_0x001b;
            if (r14 == 0) goto L_0x0046;
            r8 = com.cooey.android.vitals.generators.VitalTypeFileterDialogGenerator.Companion;
            r8 = r8.getOptions();
            r9 = r1.getName();
            r8.add(r9);
            goto L_0x0046;
            r3 = new android.support.v7.app.AlertDialog$Builder;
            r3.<init>(r11);
            r10 = (com.cooey.android.vitals.generators.VitalTypeFileterDialogGenerator.Companion) r10;
            r6 = r10.getOptions();
            r6 = (java.util.Collection) r6;
            if (r6 != 0) goto L_0x0070;
            r7 = new kotlin.TypeCastException;
            r8 = "null cannot be cast to non-null type java.util.Collection<T>";
            r7.<init>(r8);
            throw r7;
            r7 = r6.size();
            r7 = new java.lang.String[r7];
            r7 = r6.toArray(r7);
            if (r7 != 0) goto L_0x0084;
            r7 = new kotlin.TypeCastException;
            r8 = "null cannot be cast to non-null type kotlin.Array<T>";
            r7.<init>(r8);
            throw r7;
            r7 = (java.lang.CharSequence[]) r7;
            r8 = com.cooey.common.generators.TimelineItemTypeSelectionDialogGenerator.Companion;
            r9 = r8.getCheckedFeatures();
            r8 = com.cooey.android.vitals.generators.VitalTypeFileterDialogGenerator$Companion$generate$2.INSTANCE;
            r8 = (android.content.DialogInterface.OnMultiChoiceClickListener) r8;
            r3.setMultiChoiceItems(r7, r9, r8);
            r7 = 0;
            r3.setCancelable(r7);
            r7 = "Filter By: ";
            r7 = (java.lang.CharSequence) r7;
            r3.setTitle(r7);
            r7 = "OK";
            r7 = (java.lang.CharSequence) r7;
            r8 = new com.cooey.android.vitals.generators.VitalTypeFileterDialogGenerator$Companion$generate$3;
            r8.<init>(r15);
            r8 = (android.content.DialogInterface.OnClickListener) r8;
            r3.setPositiveButton(r7, r8);
            r7 = "CANCEL";
            r7 = (java.lang.CharSequence) r7;
            r8 = com.cooey.android.vitals.generators.VitalTypeFileterDialogGenerator$Companion$generate$4.INSTANCE;
            r8 = (android.content.DialogInterface.OnClickListener) r8;
            r3.setNegativeButton(r7, r8);
            r4 = r3.create();
            r7 = "dialog";
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r7);
            return r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.vitals.generators.VitalTypeFileterDialogGenerator.Companion.generate(android.content.Context, com.cooey.android.vitals.repositories.VitalBlueprintsRepository, boolean, boolean, kotlin.jvm.functions.Function1):android.support.v7.app.AlertDialog");
        }

        private Companion() {
        }

        @NotNull
        public final List<String> getSelectedTypes() {
            return VitalTypeFileterDialogGenerator.selectedTypes;
        }

        public final void setSelectedTypes(@NotNull List<String> <set-?>) {
            Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
            VitalTypeFileterDialogGenerator.selectedTypes = <set-?>;
        }

        @NotNull
        public final List<String> getOptions() {
            return VitalTypeFileterDialogGenerator.options;
        }

        public final void setOptions(@NotNull List<String> <set-?>) {
            Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
            VitalTypeFileterDialogGenerator.options = <set-?>;
        }
    }
}
