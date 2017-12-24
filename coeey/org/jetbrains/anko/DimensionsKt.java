package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0015\u0010\u0012\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0001H\b\u001a\u0012\u0010\u0012\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0001\u001a\u0015\u0010\u0012\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u0001H\b\u001a\u0019\u0010\u0012\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0014\u001a\u00020\u0001H\b\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\b\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0001H\b\u001a\u0012\u0010\u0018\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a\u001a\u0012\u0010\u0018\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0001\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\b\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0001H\b\u001a\u0019\u0010\u0018\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\b\u001a\u0019\u0010\u0018\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0019\u001a\u00020\u0001H\b\u001a\u0015\u0010\u001b\u001a\u00020\u001a*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0001H\b\u001a\u0012\u0010\u001b\u001a\u00020\u001a*\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0001\u001a\u0015\u0010\u001b\u001a\u00020\u001a*\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0001H\b\u001a\u0019\u0010\u001b\u001a\u00020\u001a*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u001c\u001a\u00020\u0001H\b\u001a\u0015\u0010\u001d\u001a\u00020\u001a*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0001H\b\u001a\u0012\u0010\u001d\u001a\u00020\u001a*\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0001\u001a\u0015\u0010\u001d\u001a\u00020\u001a*\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0001H\b\u001a\u0019\u0010\u001d\u001a\u00020\u001a*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u001c\u001a\u00020\u0001H\b\u001a\u0015\u0010\u001e\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\b\u001a\u0015\u0010\u001e\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0001H\b\u001a\u0012\u0010\u001e\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a\u001a\u0012\u0010\u001e\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0001\u001a\u0015\u0010\u001e\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\b\u001a\u0015\u0010\u001e\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0001H\b\u001a\u0019\u0010\u001e\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\b\u001a\u0019\u0010\u001e\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0019\u001a\u00020\u0001H\b\"\u0014\u0010\u0000\u001a\u00020\u0001XD¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0014\u0010\u0006\u001a\u00020\u0001XD¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0014\u0010\b\u001a\u00020\u0001XD¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u0014\u0010\n\u001a\u00020\u0001XD¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0003\"\u0014\u0010\f\u001a\u00020\u0001XD¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0003\"\u0014\u0010\u000e\u001a\u00020\u0001XD¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0003\"\u0014\u0010\u0010\u001a\u00020\u0001XD¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0003¨\u0006\u001f"}, d2 = {"HDPI", "", "getHDPI", "()I", "LDPI", "getLDPI", "MAXDPI", "getMAXDPI", "MDPI", "getMDPI", "TVDPI", "getTVDPI", "XHDPI", "getXHDPI", "XXHDPI", "getXXHDPI", "XXXHDPI", "getXXXHDPI", "dimen", "Landroid/app/Fragment;", "resource", "Landroid/content/Context;", "Landroid/view/View;", "Lorg/jetbrains/anko/AnkoContext;", "dip", "value", "", "px2dip", "px", "px2sp", "sp", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: Dimensions.kt */
public final class DimensionsKt {
    private static final int HDPI = HDPI;
    private static final int LDPI = LDPI;
    private static final int MAXDPI = MAXDPI;
    private static final int MDPI = MDPI;
    private static final int TVDPI = TVDPI;
    private static final int XHDPI = XHDPI;
    private static final int XXHDPI = XXHDPI;
    private static final int XXXHDPI = XXXHDPI;

    public static final int getLDPI() {
        return LDPI;
    }

    public static final int getMDPI() {
        return MDPI;
    }

    public static final int getHDPI() {
        return HDPI;
    }

    public static final int getTVDPI() {
        return TVDPI;
    }

    public static final int getXHDPI() {
        return XHDPI;
    }

    public static final int getXXHDPI() {
        return XXHDPI;
    }

    public static final int getXXXHDPI() {
        return XXXHDPI;
    }

    public static final int getMAXDPI() {
        return MAXDPI;
    }

    public static final int dip(Context $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return (int) (((float) value) * $receiver.getResources().getDisplayMetrics().density);
    }

    public static final int dip(Context $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return (int) ($receiver.getResources().getDisplayMetrics().density * value);
    }

    public static final int sp(Context $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return (int) (((float) value) * $receiver.getResources().getDisplayMetrics().scaledDensity);
    }

    public static final int sp(Context $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return (int) ($receiver.getResources().getDisplayMetrics().scaledDensity * value);
    }

    public static final float px2dip(Context $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return ((float) px) / $receiver.getResources().getDisplayMetrics().density;
    }

    public static final float px2sp(Context $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return ((float) px) / $receiver.getResources().getDisplayMetrics().scaledDensity;
    }

    public static final int dimen(Context $receiver, int resource) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.getResources().getDimensionPixelSize(resource);
    }

    public static final int dip(AnkoContext<?> $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return dip($receiver.getCtx(), value);
    }

    public static final int dip(AnkoContext<?> $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return dip($receiver.getCtx(), value);
    }

    public static final int sp(AnkoContext<?> $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return sp($receiver.getCtx(), value);
    }

    public static final int sp(AnkoContext<?> $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return sp($receiver.getCtx(), value);
    }

    public static final float px2dip(AnkoContext<?> $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return px2dip($receiver.getCtx(), px);
    }

    public static final float px2sp(AnkoContext<?> $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return px2sp($receiver.getCtx(), px);
    }

    public static final int dimen(AnkoContext<?> $receiver, int resource) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return dimen($receiver.getCtx(), resource);
    }

    public static final int dip(View $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return dip($receiver.getContext(), value);
    }

    public static final int dip(View $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return dip($receiver.getContext(), value);
    }

    public static final int sp(View $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return sp($receiver.getContext(), value);
    }

    public static final int sp(View $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return sp($receiver.getContext(), value);
    }

    public static final float px2dip(View $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return px2dip($receiver.getContext(), px);
    }

    public static final float px2sp(View $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return px2sp($receiver.getContext(), px);
    }

    public static final int dimen(View $receiver, int resource) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return dimen($receiver.getContext(), resource);
    }

    public static final int dip(Fragment $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return dip((Context) $receiver.getActivity(), value);
    }

    public static final int dip(Fragment $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return dip((Context) $receiver.getActivity(), value);
    }

    public static final int sp(Fragment $receiver, int value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return sp((Context) $receiver.getActivity(), value);
    }

    public static final int sp(Fragment $receiver, float value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return sp((Context) $receiver.getActivity(), value);
    }

    public static final float px2dip(Fragment $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return px2dip((Context) $receiver.getActivity(), px);
    }

    public static final float px2sp(Fragment $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return px2sp((Context) $receiver.getActivity(), px);
    }

    public static final int dimen(Fragment $receiver, int resource) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return dimen((Context) $receiver.getActivity(), resource);
    }
}
