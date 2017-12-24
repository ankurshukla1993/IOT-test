package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.Note;
import com.cooey.common.vo.ProtoRealm;
import com.facebook.share.internal.ShareConstants;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.util.List;

public class NoteStore {
    Realm realm;

    public NoteStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static NoteStore getInstance(Context context) {
        return new NoteStore(context);
    }

    public void writeToNote(final Note note) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(note);
            }
        });
    }

    public void writeToNotes(final List<Note> notes) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(notes);
            }
        });
    }

    public Note getNote(String id) {
        return (Note) this.realm.where(Note.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
    }

    public List<Note> getNotes() {
        return this.realm.where(Note.class).findAll();
    }
}
