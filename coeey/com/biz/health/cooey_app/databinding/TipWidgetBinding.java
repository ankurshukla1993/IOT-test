package com.biz.health.cooey_app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.dashboard.widgets.holders.TipWidgetViewModel;
import com.cooey.common.BindingAdapters;

public class TipWidgetBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView feedsText;
    @NonNull
    public final ImageView imgvwreclike;
    @NonNull
    public final ImageView imgvwrecshare;
    @NonNull
    public final ImageView learningVideo;
    @NonNull
    public final TextView learningVideoTitle;
    @NonNull
    public final LinearLayout linearContent;
    @NonNull
    public final ImageView listDashboard;
    @NonNull
    public final TextView loadingtext;
    private long mDirtyFlags = -1;
    @Nullable
    private TipWidgetViewModel mTipWdigetModel;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    public final CardView tipCard;
    @NonNull
    public final TextView txtrectext;
    @NonNull
    public final TextView viewAll;

    static {
        sViewsWithIds.put(C0644R.id.tipCard, 2);
        sViewsWithIds.put(C0644R.id.linear_content, 3);
        sViewsWithIds.put(C0644R.id.list_dashboard, 4);
        sViewsWithIds.put(C0644R.id.txtrectext, 5);
        sViewsWithIds.put(C0644R.id.learningVideo, 6);
        sViewsWithIds.put(C0644R.id.learningVideoTitle, 7);
        sViewsWithIds.put(C0644R.id.loadingtext, 8);
        sViewsWithIds.put(C0644R.id.imgvwrecshare, 9);
        sViewsWithIds.put(C0644R.id.imgvwreclike, 10);
        sViewsWithIds.put(C0644R.id.viewAll, 11);
    }

    public TipWidgetBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.feedsText = (TextView) bindings[1];
        this.feedsText.setTag(null);
        this.imgvwreclike = (ImageView) bindings[10];
        this.imgvwrecshare = (ImageView) bindings[9];
        this.learningVideo = (ImageView) bindings[6];
        this.learningVideoTitle = (TextView) bindings[7];
        this.linearContent = (LinearLayout) bindings[3];
        this.listDashboard = (ImageView) bindings[4];
        this.loadingtext = (TextView) bindings[8];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tipCard = (CardView) bindings[2];
        this.txtrectext = (TextView) bindings[5];
        this.viewAll = (TextView) bindings[11];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (50 != variableId) {
            return false;
        }
        setTipWdigetModel((TipWidgetViewModel) variable);
        return true;
    }

    public void setTipWdigetModel(@Nullable TipWidgetViewModel TipWdigetModel) {
        this.mTipWdigetModel = TipWdigetModel;
    }

    @Nullable
    public TipWidgetViewModel getTipWdigetModel() {
        return this.mTipWdigetModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        if ((2 & dirtyFlags) != 0) {
            BindingAdapters.onSetFont(this.feedsText, "Lato-Bold");
        }
    }

    @NonNull
    public static TipWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TipWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (TipWidgetBinding) DataBindingUtil.inflate(inflater, C0644R.layout.tip_widget, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static TipWidgetBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TipWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.tip_widget, null, false), bindingComponent);
    }

    @NonNull
    public static TipWidgetBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TipWidgetBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/tip_widget_0".equals(view.getTag())) {
            return new TipWidgetBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
