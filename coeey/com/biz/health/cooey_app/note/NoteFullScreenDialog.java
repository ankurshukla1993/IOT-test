package com.biz.health.cooey_app.note;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.dao.TimelineItemDao;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Note;
import com.cooey.common.vo.Session;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J(\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u000e\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lcom/biz/health/cooey_app/note/NoteFullScreenDialog;", "Landroid/support/v4/app/DialogFragment;", "()V", "patientId", "", "getPatientId$app_release", "()Ljava/lang/String;", "setPatientId$app_release", "(Ljava/lang/String;)V", "session", "Lcom/cooey/common/vo/Session;", "getSession$app_release", "()Lcom/cooey/common/vo/Session;", "setSession$app_release", "(Lcom/cooey/common/vo/Session;)V", "timelineDao", "Lcom/cooey/common/dao/TimelineItemDao;", "getTimelineDao", "()Lcom/cooey/common/dao/TimelineItemDao;", "setTimelineDao", "(Lcom/cooey/common/dao/TimelineItemDao;)V", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "saveNote", "note", "Lcom/cooey/common/vo/Note;", "Companion", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: NoteFullScreenDialog.kt */
public final class NoteFullScreenDialog extends DialogFragment {
    public static final Companion Companion = new Companion();
    private HashMap _$_findViewCache;
    @Nullable
    private String patientId;
    @Nullable
    private Session session;
    @Nullable
    private TimelineItemDao timelineDao;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/biz/health/cooey_app/note/NoteFullScreenDialog$Companion;", "", "()V", "newInstance", "Lcom/biz/health/cooey_app/note/NoteFullScreenDialog;", "patiendId", "", "app_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: NoteFullScreenDialog.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final NoteFullScreenDialog newInstance(@NotNull String patiendId) {
            Intrinsics.checkParameterIsNotNull(patiendId, "patiendId");
            NoteFullScreenDialog noteFullScreenDialog = new NoteFullScreenDialog();
            Bundle args = new Bundle();
            args.putString("patientId", patiendId);
            noteFullScreenDialog.setArguments(args);
            noteFullScreenDialog.setStyle(0, C0644R.style.FullScreenDialogStyle);
            return noteFullScreenDialog;
        }
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
    public final String getPatientId$app_release() {
        return this.patientId;
    }

    public final void setPatientId$app_release(@Nullable String <set-?>) {
        this.patientId = <set-?>;
    }

    @Nullable
    public final Session getSession$app_release() {
        return this.session;
    }

    public final void setSession$app_release(@Nullable Session <set-?>) {
        this.session = <set-?>;
    }

    @Nullable
    public final TimelineItemDao getTimelineDao() {
        return this.timelineDao;
    }

    public final void setTimelineDao(@Nullable TimelineItemDao <set-?>) {
        this.timelineDao = <set-?>;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.patientId = getArguments().getString("patientId");
        setHasOptionsMenu(true);
    }

    @Nullable
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) {
            Intrinsics.throwNpe();
        }
        View view = inflater.inflate(C0644R.layout.custom_dialog_note, container, false);
        this.session = new PreferenceStore().getSession(getDialog().getContext());
        EditText noteTxt = view.findViewById(C0644R.id.note);
        if (noteTxt == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        noteTxt = noteTxt;
        Toolbar toolbar = view.findViewById(C0644R.id.toolbar);
        if (toolbar == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.Toolbar");
        }
        toolbar = toolbar;
        toolbar.setTitle((CharSequence) "Add Note");
        toolbar.inflateMenu(C0644R.menu.menu_notes_save);
        toolbar.setOnMenuItemClickListener(new NoteFullScreenDialog$onCreateView$1(this, noteTxt));
        toolbar.setNavigationIcon((int) C0644R.drawable.close);
        toolbar.setNavigationOnClickListener(new NoteFullScreenDialog$onCreateView$2(this));
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            if (window == null) {
                Intrinsics.throwNpe();
            }
            window.setLayout(-1, -1);
        }
    }

    public final void saveNote(@NotNull Note note) {
        Intrinsics.checkParameterIsNotNull(note, "note");
        new Handler().post(new NoteFullScreenDialog$saveNote$1(this, note));
        getDialog().dismiss();
    }
}
