package com.biz.health.cooey_app.note;

import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;
import android.widget.EditText;
import com.cooey.common.vo.Note;
import com.cooey.common.vo.Session;
import java.util.Calendar;
import java.util.UUID;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/MenuItem;", "kotlin.jvm.PlatformType", "onMenuItemClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: NoteFullScreenDialog.kt */
final class NoteFullScreenDialog$onCreateView$1 implements OnMenuItemClickListener {
    final /* synthetic */ EditText $noteTxt;
    final /* synthetic */ NoteFullScreenDialog this$0;

    NoteFullScreenDialog$onCreateView$1(NoteFullScreenDialog noteFullScreenDialog, EditText editText) {
        this.this$0 = noteFullScreenDialog;
        this.$noteTxt = editText;
    }

    public final boolean onMenuItemClick(MenuItem it) {
        if (this.$noteTxt.getText().toString().length() > 0) {
            Note note = new Note();
            note.setId(UUID.randomUUID().toString());
            Session session$app_release = this.this$0.getSession$app_release();
            note.setTenantId(session$app_release != null ? session$app_release.getTenantId() : null);
            note.setUserId(this.this$0.getPatientId$app_release());
            note.setType("NOTES");
            note.setCreatedOn(Calendar.getInstance().getTimeInMillis());
            note.setContents(this.$noteTxt.getText().toString());
            this.this$0.saveNote(note);
            this.this$0.getDialog().dismiss();
        }
        return false;
    }
}
