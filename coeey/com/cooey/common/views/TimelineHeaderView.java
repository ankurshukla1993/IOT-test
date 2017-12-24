package com.cooey.common.views;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.cooey.common.R;
import com.facebook.internal.NativeProtocol;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0006\u0010\u001b\u001a\u00020\u0014R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Lcom/cooey/common/views/TimelineHeaderView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "content", "getContent", "()Landroid/widget/FrameLayout;", "setContent", "(Landroid/widget/FrameLayout;)V", "fabButton", "Landroid/support/design/widget/FloatingActionButton;", "getFabButton", "()Landroid/support/design/widget/FloatingActionButton;", "setFabButton", "(Landroid/support/design/widget/FloatingActionButton;)V", "addView", "", "child", "Landroid/view/View;", "index", "", "params", "Landroid/view/ViewGroup$LayoutParams;", "initialize", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: TimelineHeaderView.kt */
public final class TimelineHeaderView extends FrameLayout {
    private HashMap _$_findViewCache;
    @Nullable
    private FrameLayout content;
    @Nullable
    private FloatingActionButton fabButton;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public TimelineHeaderView(@Nullable Context context, @NotNull AttributeSet attrs) {
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        super(context, attrs);
        initialize();
    }

    public TimelineHeaderView(@Nullable Context context) {
        super(context);
        initialize();
    }

    @Nullable
    public final FrameLayout getContent() {
        return this.content;
    }

    public final void setContent(@Nullable FrameLayout <set-?>) {
        this.content = <set-?>;
    }

    @Nullable
    public final FloatingActionButton getFabButton() {
        return this.fabButton;
    }

    public final void setFabButton(@Nullable FloatingActionButton <set-?>) {
        this.fabButton = <set-?>;
    }

    public final void initialize() {
        View.inflate(getContext(), R.layout.layout_timeline_header, this);
        View findViewById = findViewById(R.id.content);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout");
        }
        this.content = (FrameLayout) findViewById;
        this.fabButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
    }

    public void addView(@NotNull View child, int index, @NotNull LayoutParams params) {
        Intrinsics.checkParameterIsNotNull(child, "child");
        Intrinsics.checkParameterIsNotNull(params, NativeProtocol.WEB_DIALOG_PARAMS);
        if (this.content == null) {
            super.addView(child, index, params);
            return;
        }
        FrameLayout frameLayout = this.content;
        if (frameLayout != null) {
            frameLayout.addView(child, index, params);
        }
    }
}
