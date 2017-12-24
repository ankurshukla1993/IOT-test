package org.jetbrains.anko;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001ag\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b2\u001e\u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000eH\b\u001a\u001d\u0010\u000f\u001a\u00020\u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0010H\b\u001a \u0010\u000f\u001a\u00020\u0001*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a\u001d\u0010\u0011\u001a\u00020\u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0010H\b\u001a \u0010\u0011\u001a\u00020\u0001*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a\n\u0010\u0012\u001a\u00020\f*\u00020\u0007\u001a\u001d\u0010\u0013\u001a\u00020\u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0010H\b\u001a \u0010\u0013\u001a\u00020\u0001*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a\u001d\u0010\u0014\u001a\u00020\u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0010H\b\u001a \u0010\u0014\u001a\u00020\u0001*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a\u001d\u0010\u0015\u001a\u00020\u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0010H\b\u001a \u0010\u0015\u001a\u00020\u0001*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a \u0010\u0016\u001a\u00020\u0001*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\u0017"}, d2 = {"log", "", "logger", "Lorg/jetbrains/anko/AnkoLogger;", "message", "", "thr", "", "level", "", "f", "Lkotlin/Function2;", "", "fThrowable", "Lkotlin/Function3;", "debug", "Lkotlin/Function0;", "error", "getStackTraceString", "info", "verbose", "warn", "wtf", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: Logger.kt */
public final class LoggerKt {
    public static /* bridge */ /* synthetic */ void verbose$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: verbose");
        }
        verbose(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void verbose(org.jetbrains.anko.AnkoLogger r3, @org.jetbrains.annotations.Nullable java.lang.Object r4, @org.jetbrains.annotations.Nullable java.lang.Throwable r5) {
        /*
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r2);
        r2 = 2;
        r1 = r3.getLoggerTag();
        r2 = android.util.Log.isLoggable(r1, r2);
        if (r2 == 0) goto L_0x0027;
    L_0x0011:
        if (r5 == 0) goto L_0x002b;
    L_0x0013:
        if (r4 == 0) goto L_0x0028;
    L_0x0015:
        r2 = r4.toString();
        if (r2 == 0) goto L_0x0028;
    L_0x001b:
        r5 = (java.lang.Throwable) r5;
        r2 = (java.lang.String) r2;
        r0 = r1;
        r0 = (java.lang.String) r0;
        android.util.Log.v(r0, r2, r5);
        r2 = kotlin.Unit.INSTANCE;
    L_0x0027:
        return;
    L_0x0028:
        r2 = "null";
        goto L_0x001b;
    L_0x002b:
        if (r4 == 0) goto L_0x003e;
    L_0x002d:
        r2 = r4.toString();
        if (r2 == 0) goto L_0x003e;
    L_0x0033:
        r2 = (java.lang.String) r2;
        r0 = r1;
        r0 = (java.lang.String) r0;
        android.util.Log.v(r0, r2);
        r2 = kotlin.Unit.INSTANCE;
        goto L_0x0027;
    L_0x003e:
        r2 = "null";
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.verbose(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    public static /* bridge */ /* synthetic */ void debug$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: debug");
        }
        debug(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void debug(org.jetbrains.anko.AnkoLogger r3, @org.jetbrains.annotations.Nullable java.lang.Object r4, @org.jetbrains.annotations.Nullable java.lang.Throwable r5) {
        /*
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r2);
        r2 = 3;
        r1 = r3.getLoggerTag();
        r2 = android.util.Log.isLoggable(r1, r2);
        if (r2 == 0) goto L_0x0027;
    L_0x0011:
        if (r5 == 0) goto L_0x002b;
    L_0x0013:
        if (r4 == 0) goto L_0x0028;
    L_0x0015:
        r2 = r4.toString();
        if (r2 == 0) goto L_0x0028;
    L_0x001b:
        r5 = (java.lang.Throwable) r5;
        r2 = (java.lang.String) r2;
        r0 = r1;
        r0 = (java.lang.String) r0;
        android.util.Log.d(r0, r2, r5);
        r2 = kotlin.Unit.INSTANCE;
    L_0x0027:
        return;
    L_0x0028:
        r2 = "null";
        goto L_0x001b;
    L_0x002b:
        if (r4 == 0) goto L_0x003e;
    L_0x002d:
        r2 = r4.toString();
        if (r2 == 0) goto L_0x003e;
    L_0x0033:
        r2 = (java.lang.String) r2;
        r0 = r1;
        r0 = (java.lang.String) r0;
        android.util.Log.d(r0, r2);
        r2 = kotlin.Unit.INSTANCE;
        goto L_0x0027;
    L_0x003e:
        r2 = "null";
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.debug(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    public static /* bridge */ /* synthetic */ void info$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: info");
        }
        info(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void info(org.jetbrains.anko.AnkoLogger r3, @org.jetbrains.annotations.Nullable java.lang.Object r4, @org.jetbrains.annotations.Nullable java.lang.Throwable r5) {
        /*
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r2);
        r2 = 4;
        r1 = r3.getLoggerTag();
        r2 = android.util.Log.isLoggable(r1, r2);
        if (r2 == 0) goto L_0x0027;
    L_0x0011:
        if (r5 == 0) goto L_0x002b;
    L_0x0013:
        if (r4 == 0) goto L_0x0028;
    L_0x0015:
        r2 = r4.toString();
        if (r2 == 0) goto L_0x0028;
    L_0x001b:
        r5 = (java.lang.Throwable) r5;
        r2 = (java.lang.String) r2;
        r0 = r1;
        r0 = (java.lang.String) r0;
        android.util.Log.i(r0, r2, r5);
        r2 = kotlin.Unit.INSTANCE;
    L_0x0027:
        return;
    L_0x0028:
        r2 = "null";
        goto L_0x001b;
    L_0x002b:
        if (r4 == 0) goto L_0x003e;
    L_0x002d:
        r2 = r4.toString();
        if (r2 == 0) goto L_0x003e;
    L_0x0033:
        r2 = (java.lang.String) r2;
        r0 = r1;
        r0 = (java.lang.String) r0;
        android.util.Log.i(r0, r2);
        r2 = kotlin.Unit.INSTANCE;
        goto L_0x0027;
    L_0x003e:
        r2 = "null";
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.info(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    public static /* bridge */ /* synthetic */ void warn$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: warn");
        }
        warn(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void warn(org.jetbrains.anko.AnkoLogger r3, @org.jetbrains.annotations.Nullable java.lang.Object r4, @org.jetbrains.annotations.Nullable java.lang.Throwable r5) {
        /*
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r2);
        r2 = 5;
        r1 = r3.getLoggerTag();
        r2 = android.util.Log.isLoggable(r1, r2);
        if (r2 == 0) goto L_0x0027;
    L_0x0011:
        if (r5 == 0) goto L_0x002b;
    L_0x0013:
        if (r4 == 0) goto L_0x0028;
    L_0x0015:
        r2 = r4.toString();
        if (r2 == 0) goto L_0x0028;
    L_0x001b:
        r5 = (java.lang.Throwable) r5;
        r2 = (java.lang.String) r2;
        r0 = r1;
        r0 = (java.lang.String) r0;
        android.util.Log.w(r0, r2, r5);
        r2 = kotlin.Unit.INSTANCE;
    L_0x0027:
        return;
    L_0x0028:
        r2 = "null";
        goto L_0x001b;
    L_0x002b:
        if (r4 == 0) goto L_0x003e;
    L_0x002d:
        r2 = r4.toString();
        if (r2 == 0) goto L_0x003e;
    L_0x0033:
        r2 = (java.lang.String) r2;
        r0 = r1;
        r0 = (java.lang.String) r0;
        android.util.Log.w(r0, r2);
        r2 = kotlin.Unit.INSTANCE;
        goto L_0x0027;
    L_0x003e:
        r2 = "null";
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.warn(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    public static /* bridge */ /* synthetic */ void error$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: error");
        }
        error(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void error(org.jetbrains.anko.AnkoLogger r3, @org.jetbrains.annotations.Nullable java.lang.Object r4, @org.jetbrains.annotations.Nullable java.lang.Throwable r5) {
        /*
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r2);
        r2 = 6;
        r1 = r3.getLoggerTag();
        r2 = android.util.Log.isLoggable(r1, r2);
        if (r2 == 0) goto L_0x0027;
    L_0x0011:
        if (r5 == 0) goto L_0x002b;
    L_0x0013:
        if (r4 == 0) goto L_0x0028;
    L_0x0015:
        r2 = r4.toString();
        if (r2 == 0) goto L_0x0028;
    L_0x001b:
        r5 = (java.lang.Throwable) r5;
        r2 = (java.lang.String) r2;
        r0 = r1;
        r0 = (java.lang.String) r0;
        android.util.Log.e(r0, r2, r5);
        r2 = kotlin.Unit.INSTANCE;
    L_0x0027:
        return;
    L_0x0028:
        r2 = "null";
        goto L_0x001b;
    L_0x002b:
        if (r4 == 0) goto L_0x003e;
    L_0x002d:
        r2 = r4.toString();
        if (r2 == 0) goto L_0x003e;
    L_0x0033:
        r2 = (java.lang.String) r2;
        r0 = r1;
        r0 = (java.lang.String) r0;
        android.util.Log.e(r0, r2);
        r2 = kotlin.Unit.INSTANCE;
        goto L_0x0027;
    L_0x003e:
        r2 = "null";
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.error(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    public static /* bridge */ /* synthetic */ void wtf$default(AnkoLogger ankoLogger, Object obj, Throwable th, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: wtf");
        }
        wtf(ankoLogger, obj, (i & 2) != 0 ? (Throwable) null : th);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void wtf(org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.Nullable java.lang.Object r3, @org.jetbrains.annotations.Nullable java.lang.Throwable r4) {
        /*
        r0 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0);
        if (r4 == 0) goto L_0x001a;
    L_0x0007:
        r1 = r2.getLoggerTag();
        if (r3 == 0) goto L_0x0017;
    L_0x000d:
        r0 = r3.toString();
        if (r0 == 0) goto L_0x0017;
    L_0x0013:
        android.util.Log.wtf(r1, r0, r4);
    L_0x0016:
        return;
    L_0x0017:
        r0 = "null";
        goto L_0x0013;
    L_0x001a:
        r1 = r2.getLoggerTag();
        if (r3 == 0) goto L_0x002a;
    L_0x0020:
        r0 = r3.toString();
        if (r0 == 0) goto L_0x002a;
    L_0x0026:
        android.util.Log.wtf(r1, r0);
        goto L_0x0016;
    L_0x002a:
        r0 = "null";
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.wtf(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void verbose(org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.lang.Object> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1);
        r1 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r0 = r2.getLoggerTag();
        r1 = 2;
        r1 = android.util.Log.isLoggable(r0, r1);
        if (r1 == 0) goto L_0x0024;
    L_0x0015:
        r1 = r3.invoke();
        if (r1 == 0) goto L_0x0025;
    L_0x001b:
        r1 = r1.toString();
        if (r1 == 0) goto L_0x0025;
    L_0x0021:
        android.util.Log.v(r0, r1);
    L_0x0024:
        return;
    L_0x0025:
        r1 = "null";
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.verbose(org.jetbrains.anko.AnkoLogger, kotlin.jvm.functions.Function0):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void debug(org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.lang.Object> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1);
        r1 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r0 = r2.getLoggerTag();
        r1 = 3;
        r1 = android.util.Log.isLoggable(r0, r1);
        if (r1 == 0) goto L_0x0024;
    L_0x0015:
        r1 = r3.invoke();
        if (r1 == 0) goto L_0x0025;
    L_0x001b:
        r1 = r1.toString();
        if (r1 == 0) goto L_0x0025;
    L_0x0021:
        android.util.Log.d(r0, r1);
    L_0x0024:
        return;
    L_0x0025:
        r1 = "null";
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.debug(org.jetbrains.anko.AnkoLogger, kotlin.jvm.functions.Function0):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void info(org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.lang.Object> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1);
        r1 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r0 = r2.getLoggerTag();
        r1 = 4;
        r1 = android.util.Log.isLoggable(r0, r1);
        if (r1 == 0) goto L_0x0024;
    L_0x0015:
        r1 = r3.invoke();
        if (r1 == 0) goto L_0x0025;
    L_0x001b:
        r1 = r1.toString();
        if (r1 == 0) goto L_0x0025;
    L_0x0021:
        android.util.Log.i(r0, r1);
    L_0x0024:
        return;
    L_0x0025:
        r1 = "null";
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.info(org.jetbrains.anko.AnkoLogger, kotlin.jvm.functions.Function0):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void warn(org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.lang.Object> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1);
        r1 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r0 = r2.getLoggerTag();
        r1 = 5;
        r1 = android.util.Log.isLoggable(r0, r1);
        if (r1 == 0) goto L_0x0024;
    L_0x0015:
        r1 = r3.invoke();
        if (r1 == 0) goto L_0x0025;
    L_0x001b:
        r1 = r1.toString();
        if (r1 == 0) goto L_0x0025;
    L_0x0021:
        android.util.Log.w(r0, r1);
    L_0x0024:
        return;
    L_0x0025:
        r1 = "null";
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.warn(org.jetbrains.anko.AnkoLogger, kotlin.jvm.functions.Function0):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void error(org.jetbrains.anko.AnkoLogger r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.lang.Object> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1);
        r1 = "message";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r0 = r2.getLoggerTag();
        r1 = 6;
        r1 = android.util.Log.isLoggable(r0, r1);
        if (r1 == 0) goto L_0x0024;
    L_0x0015:
        r1 = r3.invoke();
        if (r1 == 0) goto L_0x0025;
    L_0x001b:
        r1 = r1.toString();
        if (r1 == 0) goto L_0x0025;
    L_0x0021:
        android.util.Log.e(r0, r1);
    L_0x0024:
        return;
    L_0x0025:
        r1 = "null";
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.error(org.jetbrains.anko.AnkoLogger, kotlin.jvm.functions.Function0):void");
    }

    @NotNull
    public static final String getStackTraceString(Throwable $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        String stackTraceString = Log.getStackTraceString($receiver);
        Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(this)");
        return stackTraceString;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final void log(org.jetbrains.anko.AnkoLogger r2, java.lang.Object r3, java.lang.Throwable r4, int r5, kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> r6, kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.String, ? super java.lang.Throwable, kotlin.Unit> r7) {
        /*
        r0 = r2.getLoggerTag();
        r1 = android.util.Log.isLoggable(r0, r5);
        if (r1 == 0) goto L_0x0017;
    L_0x000a:
        if (r4 == 0) goto L_0x001b;
    L_0x000c:
        if (r3 == 0) goto L_0x0018;
    L_0x000e:
        r1 = r3.toString();
        if (r1 == 0) goto L_0x0018;
    L_0x0014:
        r7.invoke(r0, r1, r4);
    L_0x0017:
        return;
    L_0x0018:
        r1 = "null";
        goto L_0x0014;
    L_0x001b:
        if (r3 == 0) goto L_0x0027;
    L_0x001d:
        r1 = r3.toString();
        if (r1 == 0) goto L_0x0027;
    L_0x0023:
        r6.invoke(r0, r1);
        goto L_0x0017;
    L_0x0027:
        r1 = "null";
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.LoggerKt.log(org.jetbrains.anko.AnkoLogger, java.lang.Object, java.lang.Throwable, int, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3):void");
    }
}
