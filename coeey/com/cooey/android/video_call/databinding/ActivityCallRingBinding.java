package com.cooey.android.video_call.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.cooey.android.video_call.CallRingViewModel;

public class ActivityCallRingBinding extends ViewDataBinding implements Listener {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final Guideline guideline4;
    @NonNull
    public final ImageView imageButton2;
    @NonNull
    public final ImageView imageButton3;
    @Nullable
    private CallRingViewModel mCallRingViewModel;
    @Nullable
    private final OnClickListener mCallback1;
    @Nullable
    private final OnClickListener mCallback2;
    private long mDirtyFlags = -1;
    @NonNull
    private final ConstraintLayout mboundView0;
    @NonNull
    public final TextView textView;
    @NonNull
    public final TextView textView2;

    static {
        sViewsWithIds.put(C0644R.id.guideline4, 4);
        sViewsWithIds.put(C0644R.id.textView2, 5);
    }

    public ActivityCallRingBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.guideline4 = (Guideline) bindings[4];
        this.imageButton2 = (ImageView) bindings[1];
        this.imageButton2.setTag(null);
        this.imageButton3 = (ImageView) bindings[2];
        this.imageButton3.setTag(null);
        this.mboundView0 = (ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.textView = (TextView) bindings[3];
        this.textView.setTag(null);
        this.textView2 = (TextView) bindings[5];
        setRootTag(root);
        this.mCallback2 = new android.databinding.generated.callback.OnClickListener(this, 2);
        this.mCallback1 = new android.databinding.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (8 != variableId) {
            return false;
        }
        setCallRingViewModel((CallRingViewModel) variable);
        return true;
    }

    public void setCallRingViewModel(@Nullable CallRingViewModel CallRingViewModel) {
        updateRegistration(0, CallRingViewModel);
        this.mCallRingViewModel = CallRingViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(8);
        super.requestRebind();
    }

    @Nullable
    public CallRingViewModel getCallRingViewModel() {
        return this.mCallRingViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeCallRingViewModel((CallRingViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeCallRingViewModel(CallRingViewModel CallRingViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId != 34) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        CallRingViewModel callRingViewModel = this.mCallRingViewModel;
        String callRingViewModelName = null;
        if (!((dirtyFlags & 7) == 0 || callRingViewModel == null)) {
            callRingViewModelName = callRingViewModel.getName();
        }
        if ((4 & dirtyFlags) != 0) {
            this.imageButton2.setOnClickListener(this.mCallback1);
            this.imageButton3.setOnClickListener(this.mCallback2);
        }
        if ((dirtyFlags & 7) != 0) {
            TextViewBindingAdapter.setText(this.textView, callRingViewModelName);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        CallRingViewModel callRingViewModel;
        boolean callRingViewModelJavaLangObjectNull;
        switch (sourceId) {
            case 1:
                callRingViewModel = this.mCallRingViewModel;
                if (callRingViewModel != null) {
                    callRingViewModelJavaLangObjectNull = true;
                } else {
                    callRingViewModelJavaLangObjectNull = false;
                }
                if (callRingViewModelJavaLangObjectNull) {
                    callRingViewModel.dismissCall();
                    return;
                }
                return;
            case 2:
                callRingViewModel = this.mCallRingViewModel;
                if (callRingViewModel != null) {
                    callRingViewModelJavaLangObjectNull = true;
                } else {
                    callRingViewModelJavaLangObjectNull = false;
                }
                if (callRingViewModelJavaLangObjectNull) {
                    callRingViewModel.answerCall();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @NonNull
    public static ActivityCallRingBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityCallRingBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityCallRingBinding) DataBindingUtil.inflate(inflater, C0644R.layout.activity_call_ring, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityCallRingBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityCallRingBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.activity_call_ring, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityCallRingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityCallRingBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_call_ring_0".equals(view.getTag())) {
            return new ActivityCallRingBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
