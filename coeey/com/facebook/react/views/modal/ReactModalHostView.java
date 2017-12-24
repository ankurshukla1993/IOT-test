package com.facebook.react.views.modal;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnShowListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.annotations.VisibleForTesting;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class ReactModalHostView extends ViewGroup implements LifecycleEventListener {
    private String mAnimationType;
    @Nullable
    private Dialog mDialog;
    private boolean mHardwareAccelerated;
    private DialogRootViewGroup mHostView;
    @Nullable
    private OnRequestCloseListener mOnRequestCloseListener;
    @Nullable
    private OnShowListener mOnShowListener;
    private boolean mPropertyRequiresNewDialog;
    private boolean mTransparent;

    public ReactModalHostView(Context context) {
        super(context);
        ((ReactContext) context).addLifecycleEventListener(this);
        this.mHostView = new DialogRootViewGroup(context);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    public void addView(View child, int index) {
        this.mHostView.addView(child, index);
    }

    public int getChildCount() {
        return this.mHostView.getChildCount();
    }

    public View getChildAt(int index) {
        return this.mHostView.getChildAt(index);
    }

    public void removeView(View child) {
        this.mHostView.removeView(child);
    }

    public void removeViewAt(int index) {
        this.mHostView.removeView(getChildAt(index));
    }

    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return false;
    }

    public void onDropInstance() {
        ((ReactContext) getContext()).removeLifecycleEventListener(this);
        dismiss();
    }

    private void dismiss() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
            this.mDialog = null;
            ((ViewGroup) this.mHostView.getParent()).removeViewAt(0);
        }
    }

    protected void setOnRequestCloseListener(OnRequestCloseListener listener) {
        this.mOnRequestCloseListener = listener;
    }

    protected void setOnShowListener(OnShowListener listener) {
        this.mOnShowListener = listener;
    }

    protected void setTransparent(boolean transparent) {
        this.mTransparent = transparent;
    }

    protected void setAnimationType(String animationType) {
        this.mAnimationType = animationType;
        this.mPropertyRequiresNewDialog = true;
    }

    protected void setHardwareAccelerated(boolean hardwareAccelerated) {
        this.mHardwareAccelerated = hardwareAccelerated;
        this.mPropertyRequiresNewDialog = true;
    }

    public void onHostResume() {
        showOrUpdate();
    }

    public void onHostPause() {
        dismiss();
    }

    public void onHostDestroy() {
        onDropInstance();
    }

    @VisibleForTesting
    @Nullable
    public Dialog getDialog() {
        return this.mDialog;
    }

    protected void showOrUpdate() {
        if (this.mDialog != null) {
            if (this.mPropertyRequiresNewDialog) {
                dismiss();
            } else {
                updateProperties();
                return;
            }
        }
        this.mPropertyRequiresNewDialog = false;
        int theme = R.style.Theme_FullScreenDialog;
        if (this.mAnimationType.equals("fade")) {
            theme = R.style.Theme_FullScreenDialogAnimatedFade;
        } else if (this.mAnimationType.equals("slide")) {
            theme = R.style.Theme_FullScreenDialogAnimatedSlide;
        }
        this.mDialog = new Dialog(getContext(), theme);
        this.mDialog.setContentView(getContentView());
        updateProperties();
        this.mDialog.setOnShowListener(this.mOnShowListener);
        this.mDialog.setOnKeyListener(new 1(this));
        this.mDialog.getWindow().setSoftInputMode(16);
        if (this.mHardwareAccelerated) {
            this.mDialog.getWindow().addFlags(16777216);
        }
        this.mDialog.show();
    }

    private View getContentView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.mHostView);
        frameLayout.setFitsSystemWindows(true);
        return frameLayout;
    }

    private void updateProperties() {
        Assertions.assertNotNull(this.mDialog, "mDialog must exist when we call updateProperties");
        if (this.mTransparent) {
            this.mDialog.getWindow().clearFlags(2);
            return;
        }
        this.mDialog.getWindow().setDimAmount(0.5f);
        this.mDialog.getWindow().setFlags(2, 2);
    }
}
