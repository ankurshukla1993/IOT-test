package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public final class zzr extends zzl {
    private Fragment zzgtv;

    private zzr(Fragment fragment) {
        this.zzgtv = fragment;
    }

    public static zzr zza(Fragment fragment) {
        return fragment != null ? new zzr(fragment) : null;
    }

    public final Bundle getArguments() {
        return this.zzgtv.getArguments();
    }

    public final int getId() {
        return this.zzgtv.getId();
    }

    public final boolean getRetainInstance() {
        return this.zzgtv.getRetainInstance();
    }

    public final String getTag() {
        return this.zzgtv.getTag();
    }

    public final int getTargetRequestCode() {
        return this.zzgtv.getTargetRequestCode();
    }

    public final boolean getUserVisibleHint() {
        return this.zzgtv.getUserVisibleHint();
    }

    public final IObjectWrapper getView() {
        return zzn.zzy(this.zzgtv.getView());
    }

    public final boolean isAdded() {
        return this.zzgtv.isAdded();
    }

    public final boolean isDetached() {
        return this.zzgtv.isDetached();
    }

    public final boolean isHidden() {
        return this.zzgtv.isHidden();
    }

    public final boolean isInLayout() {
        return this.zzgtv.isInLayout();
    }

    public final boolean isRemoving() {
        return this.zzgtv.isRemoving();
    }

    public final boolean isResumed() {
        return this.zzgtv.isResumed();
    }

    public final boolean isVisible() {
        return this.zzgtv.isVisible();
    }

    public final void setHasOptionsMenu(boolean z) {
        this.zzgtv.setHasOptionsMenu(z);
    }

    public final void setMenuVisibility(boolean z) {
        this.zzgtv.setMenuVisibility(z);
    }

    public final void setRetainInstance(boolean z) {
        this.zzgtv.setRetainInstance(z);
    }

    public final void setUserVisibleHint(boolean z) {
        this.zzgtv.setUserVisibleHint(z);
    }

    public final void startActivity(Intent intent) {
        this.zzgtv.startActivity(intent);
    }

    public final void startActivityForResult(Intent intent, int i) {
        this.zzgtv.startActivityForResult(intent, i);
    }

    public final IObjectWrapper zzapl() {
        return zzn.zzy(this.zzgtv.getActivity());
    }

    public final zzk zzapm() {
        return zza(this.zzgtv.getParentFragment());
    }

    public final IObjectWrapper zzapn() {
        return zzn.zzy(this.zzgtv.getResources());
    }

    public final zzk zzapo() {
        return zza(this.zzgtv.getTargetFragment());
    }

    public final void zzv(IObjectWrapper iObjectWrapper) {
        this.zzgtv.registerForContextMenu((View) zzn.zzx(iObjectWrapper));
    }

    public final void zzw(IObjectWrapper iObjectWrapper) {
        this.zzgtv.unregisterForContextMenu((View) zzn.zzx(iObjectWrapper));
    }
}
