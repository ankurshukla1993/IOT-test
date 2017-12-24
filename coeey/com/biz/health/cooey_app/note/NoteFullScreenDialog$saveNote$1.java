package com.biz.health.cooey_app.note;

import android.support.v4.app.NotificationCompat;
import com.cooey.common.dao.TimelineItemDao;
import com.cooey.common.realm_store.NoteStore;
import com.cooey.common.services.ApiClient;
import com.cooey.common.services.NoteService;
import com.cooey.common.vo.Note;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.timeline.TimelineItem;
import com.cooey.common.vo.timeline.TimelineItemType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 7})
/* compiled from: NoteFullScreenDialog.kt */
final class NoteFullScreenDialog$saveNote$1 implements Runnable {
    final /* synthetic */ Note $note;
    final /* synthetic */ NoteFullScreenDialog this$0;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J$\u0010\n\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/biz/health/cooey_app/note/NoteFullScreenDialog$saveNote$1$1", "Lretrofit2/Callback;", "Lokhttp3/ResponseBody;", "()V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "app_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: NoteFullScreenDialog.kt */
    public static final class C06491 implements Callback<ResponseBody> {
        C06491() {
        }

        public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
            Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
            Intrinsics.checkParameterIsNotNull(response, "response");
        }

        public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
            Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
            Intrinsics.checkParameterIsNotNull(t, "t");
        }
    }

    NoteFullScreenDialog$saveNote$1(NoteFullScreenDialog noteFullScreenDialog, Note note) {
        this.this$0 = noteFullScreenDialog;
        this.$note = note;
    }

    public final void run() {
        String str = null;
        NoteStore.getInstance(this.this$0.getContext()).writeToNote(this.$note);
        String id = this.$note.getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "note.id");
        int note = TimelineItemType.INSTANCE.getNOTE();
        long createdOn = this.$note.getCreatedOn();
        String patientId$app_release = this.this$0.getPatientId$app_release();
        if (patientId$app_release == null) {
            Intrinsics.throwNpe();
        }
        TimelineItem timelineItem = new TimelineItem(id, note, createdOn, patientId$app_release, null, 16, null);
        TimelineItemDao timelineDao = this.this$0.getTimelineDao();
        if (timelineDao != null) {
            timelineDao.insert(timelineItem);
        }
        NoteService noteService = new ApiClient(this.this$0.getDialog().getContext(), "http://api.cooey.co.in/ehealth/").getNoteService();
        Session session$app_release = this.this$0.getSession$app_release();
        if (session$app_release != null) {
            str = session$app_release.getId();
        }
        noteService.postNote(str, this.$note).enqueue(new C06491());
    }
}
