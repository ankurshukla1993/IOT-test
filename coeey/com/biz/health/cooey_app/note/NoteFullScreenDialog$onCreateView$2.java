package com.biz.health.cooey_app.note;

import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: NoteFullScreenDialog.kt */
final class NoteFullScreenDialog$onCreateView$2 implements OnClickListener {
    final /* synthetic */ NoteFullScreenDialog this$0;

    NoteFullScreenDialog$onCreateView$2(NoteFullScreenDialog noteFullScreenDialog) {
        this.this$0 = noteFullScreenDialog;
    }

    public final void onClick(View it) {
        this.this$0.getDialog().dismiss();
    }
}
