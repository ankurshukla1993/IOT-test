package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@SuppressLint({"NewApi"})
public final class zzj extends zzl {
    private Fragment zzgts;

    private zzj(Fragment fragment) {
        this.zzgts = fragment;
    }

    public static zzj zza(Fragment fragment) {
        return fragment != null ? new zzj(fragment) : null;
    }

    public final Bundle getArguments() {
        return this.zzgts.getArguments();
    }

    public final int getId() {
        return this.zzgts.getId();
    }

    public final boolean getRetainInstance() {
        return this.zzgts.getRetainInstance();
    }

    public final String getTag() {
        return this.zzgts.getTag();
    }

    public final int getTargetRequestCode() {
        return this.zzgts.getTargetRequestCode();
    }

    public final boolean getUserVisibleHint() {
        return this.zzgts.getUserVisibleHint();
    }

    public final IObjectWrapper getView() {
        return zzn.zzy(this.zzgts.getView());
    }

    public final boolean isAdded() {
        return this.zzgts.isAdded();
    }

    public final boolean isDetached() {
        return this.zzgts.isDetached();
    }

    public final boolean isHidden() {
        return this.zzgts.isHidden();
    }

    public final boolean isInLayout() {
        return this.zzgts.isInLayout();
    }

    public final boolean isRemoving() {
        return this.zzgts.isRemoving();
    }

    public final boolean isResumed() {
        return this.zzgts.isResumed();
    }

    public final boolean isVisible() {
        return this.zzgts.isVisible();
    }

    public final void setHasOptionsMenu(boolean z) {
        this.zzgts.setHasOptionsMenu(z);
    }

    public final void setMenuVisibility(boolean z) {
        this.zzgts.setMenuVisibility(z);
    }

    public final void setRetainInstance(boolean z) {
        this.zzgts.setRetainInstance(z);
    }

    public final void setUserVisibleHint(boolean z) {
        this.zzgts.setUserVisibleHint(z);
    }

    public final void startActivity(Intent intent) {
        this.zzgts.startActivity(intent);
    }

    public final void startActivityForResult(Intent intent, int i) {
        this.zzgts.startActivityForResult(intent, i);
    }

    public final IObjectWrapper zzapl() {
        return zzn.zzy(this.zzgts.getActivity());
    }

    public final zzk zzapm() {
        return zza(this.zzgts.getParentFragment());
    }

    public final IObjectWrapper zzapn() {
        return zzn.zzy(this.zzgts.getResources());
    }

    public final zzk zzapo() {
        return zza(this.zzgts.getTargetFragment());
    }

    public final void zzv(IObjectWrapper iObjectWrapper) {
        this.zzgts.registerForContextMenu((View) zzn.zzx(iObjectWrapper));
    }

    public final void zzw(IObjectWrapper iObjectWrapper) {
        this.zzgts.unregisterForContextMenu((View) zzn.zzx(iObjectWrapper));
    }
}
