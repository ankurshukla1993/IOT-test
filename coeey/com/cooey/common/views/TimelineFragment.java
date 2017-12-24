package com.cooey.common.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cooey.common.R;
import com.cooey.common.adapters.TimelineRecyclerAdapter;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0002\"#B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J(\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0012H\u0016J\u000e\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u0010R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/cooey/common/views/TimelineFragment;", "Landroid/support/v4/app/Fragment;", "()V", "mListener", "Lcom/cooey/common/views/TimelineFragment$OnFragmentInteractionListener;", "textViewNoTimeLineItems", "Landroid/widget/TextView;", "timelineRecyclerAdapter", "Lcom/cooey/common/adapters/TimelineRecyclerAdapter;", "getTimelineRecyclerAdapter", "()Lcom/cooey/common/adapters/TimelineRecyclerAdapter;", "setTimelineRecyclerAdapter", "(Lcom/cooey/common/adapters/TimelineRecyclerAdapter;)V", "timelineRecyclerView", "Landroid/support/v7/widget/RecyclerView;", "userId", "", "hideEmptyMessage", "", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDetach", "showEmptyMessage", "emptyMessage", "Companion", "OnFragmentInteractionListener", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: TimelineFragment.kt */
public final class TimelineFragment extends Fragment {
    public static final Companion Companion = new Companion();
    private static final String USER_ID = USER_ID;
    private HashMap _$_findViewCache;
    private OnFragmentInteractionListener mListener;
    private TextView textViewNoTimeLineItems;
    @Nullable
    private TimelineRecyclerAdapter timelineRecyclerAdapter;
    private RecyclerView timelineRecyclerView;
    private String userId;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/cooey/common/views/TimelineFragment$Companion;", "", "()V", "USER_ID", "", "getUSER_ID", "()Ljava/lang/String;", "newInstance", "Lcom/cooey/common/views/TimelineFragment;", "userId", "common_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: TimelineFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        private final String getUSER_ID() {
            return TimelineFragment.USER_ID;
        }

        @NotNull
        public final TimelineFragment newInstance(@NotNull String userId) {
            Intrinsics.checkParameterIsNotNull(userId, "userId");
            TimelineFragment fragment = new TimelineFragment();
            Bundle args = new Bundle();
            args.putString(getUSER_ID(), userId);
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/cooey/common/views/TimelineFragment$OnFragmentInteractionListener;", "", "onFragmentInteraction", "", "uri", "Landroid/net/Uri;", "common_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: TimelineFragment.kt */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(@NotNull Uri uri);
    }

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
        view = getView();
        if (view == null) {
            return null;
        }
        view = view.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Nullable
    public final TimelineRecyclerAdapter getTimelineRecyclerAdapter() {
        return this.timelineRecyclerAdapter;
    }

    public final void setTimelineRecyclerAdapter(@Nullable TimelineRecyclerAdapter <set-?>) {
        this.timelineRecyclerAdapter = <set-?>;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.userId = getArguments().getString(Companion.getUSER_ID());
        }
    }

    @Nullable
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView;
        if (inflater == null) {
            Intrinsics.throwNpe();
        }
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        this.timelineRecyclerView = view != null ? (RecyclerView) view.findViewById(R.id.timeline_item_recycler_view) : null;
        if (view != null) {
            textView = (TextView) view.findViewById(R.id.txt_no_timeline_items);
        } else {
            textView = null;
        }
        this.textViewNoTimeLineItems = textView;
        RecyclerView recyclerView = this.timelineRecyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(this.timelineRecyclerAdapter);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView2 = this.timelineRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setLayoutManager(linearLayoutManager);
        }
        return view;
    }

    public void onAttach(@Nullable Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) context;
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = (OnFragmentInteractionListener) null;
    }

    public final void showEmptyMessage(@NotNull String emptyMessage) {
        Intrinsics.checkParameterIsNotNull(emptyMessage, "emptyMessage");
        if (((TextView) _$_findCachedViewById(R.id.txt_no_timeline_items)).getVisibility() == 4) {
            if (((TextView) _$_findCachedViewById(R.id.txt_no_timeline_items)).getVisibility() == 0) {
                ((TextView) _$_findCachedViewById(R.id.txt_no_timeline_items)).setText(emptyMessage);
            } else {
                ((TextView) _$_findCachedViewById(R.id.txt_no_timeline_items)).setText(emptyMessage);
            }
        }
    }

    public final void hideEmptyMessage() {
        if (((TextView) _$_findCachedViewById(R.id.txt_no_timeline_items)).getVisibility() == 0) {
            if (((TextView) _$_findCachedViewById(R.id.txt_no_timeline_items)).getVisibility() == 4) {
                ((TextView) _$_findCachedViewById(R.id.txt_no_timeline_items)).setText("");
            } else {
                ((TextView) _$_findCachedViewById(R.id.txt_no_timeline_items)).setText("");
            }
        }
    }
}
