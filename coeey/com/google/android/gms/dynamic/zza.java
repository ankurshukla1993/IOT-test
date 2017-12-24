package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.zze;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    private T zzgtj;
    private Bundle zzgtk;
    private LinkedList<zzi> zzgtl;
    private final zzo<T> zzgtm = new zzb(this);

    private final void zza(Bundle bundle, zzi com_google_android_gms_dynamic_zzi) {
        if (this.zzgtj != null) {
            com_google_android_gms_dynamic_zzi.zzb(this.zzgtj);
            return;
        }
        if (this.zzgtl == null) {
            this.zzgtl = new LinkedList();
        }
        this.zzgtl.add(com_google_android_gms_dynamic_zzi);
        if (bundle != null) {
            if (this.zzgtk == null) {
                this.zzgtk = (Bundle) bundle.clone();
            } else {
                this.zzgtk.putAll(bundle);
            }
        }
        zza(this.zzgtm);
    }

    public static void zzb(FrameLayout frameLayout) {
        zze instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        CharSequence zzi = zzu.zzi(context, isGooglePlayServicesAvailable);
        CharSequence zzk = zzu.zzk(context, isGooglePlayServicesAvailable);
        View linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        View textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(zzi);
        linearLayout.addView(textView);
        Intent zza = zze.zza(context, isGooglePlayServicesAvailable, null);
        if (zza != null) {
            View button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(zzk);
            linearLayout.addView(button);
            button.setOnClickListener(new zzf(context, zza));
        }
    }

    private final void zzcz(int i) {
        while (!this.zzgtl.isEmpty() && ((zzi) this.zzgtl.getLast()).getState() >= i) {
            this.zzgtl.removeLast();
        }
    }

    public final void onCreate(Bundle bundle) {
        zza(bundle, new zzd(this, bundle));
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        zza(bundle, new zze(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.zzgtj == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public final void onDestroy() {
        if (this.zzgtj != null) {
            this.zzgtj.onDestroy();
        } else {
            zzcz(1);
        }
    }

    public final void onDestroyView() {
        if (this.zzgtj != null) {
            this.zzgtj.onDestroyView();
        } else {
            zzcz(2);
        }
    }

    public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        zza(bundle2, new zzc(this, activity, bundle, bundle2));
    }

    public final void onLowMemory() {
        if (this.zzgtj != null) {
            this.zzgtj.onLowMemory();
        }
    }

    public final void onPause() {
        if (this.zzgtj != null) {
            this.zzgtj.onPause();
        } else {
            zzcz(5);
        }
    }

    public final void onResume() {
        zza(null, new zzh(this));
    }

    public final void onSaveInstanceState(Bundle bundle) {
        if (this.zzgtj != null) {
            this.zzgtj.onSaveInstanceState(bundle);
        } else if (this.zzgtk != null) {
            bundle.putAll(this.zzgtk);
        }
    }

    public final void onStart() {
        zza(null, new zzg(this));
    }

    public final void onStop() {
        if (this.zzgtj != null) {
            this.zzgtj.onStop();
        } else {
            zzcz(4);
        }
    }

    protected void zza(FrameLayout frameLayout) {
        zze instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        CharSequence zzi = zzu.zzi(context, isGooglePlayServicesAvailable);
        CharSequence zzk = zzu.zzk(context, isGooglePlayServicesAvailable);
        View linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        View textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(zzi);
        linearLayout.addView(textView);
        Intent zza = zze.zza(context, isGooglePlayServicesAvailable, null);
        if (zza != null) {
            View button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(zzk);
            linearLayout.addView(button);
            button.setOnClickListener(new zzf(context, zza));
        }
    }

    protected abstract void zza(zzo<T> com_google_android_gms_dynamic_zzo_T);

    public final T zzapk() {
        return this.zzgtj;
    }
}
