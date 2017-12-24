package com.cooey.common;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.cooey.common.dao.ActionItemDao;
import com.cooey.common.dao.TimelineItemDao;
import com.cooey.common.vo.ActionItem;
import com.cooey.common.vo.timeline.TimelineItem;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lcom/cooey/common/CommonDatabase;", "Landroid/arch/persistence/room/RoomDatabase;", "()V", "ActionItemDao", "Lcom/cooey/common/dao/ActionItemDao;", "TimelineItemDao", "Lcom/cooey/common/dao/TimelineItemDao;", "Companion", "common_release"}, k = 1, mv = {1, 1, 7})
@Database(entities = {TimelineItem.class, ActionItem.class}, version = 1)
/* compiled from: CommonDatabase.kt */
public abstract class CommonDatabase extends RoomDatabase {
    public static final Companion Companion = new Companion();
    @Nullable
    private static CommonDatabase commonDatabase;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/cooey/common/CommonDatabase$Companion;", "", "()V", "commonDatabase", "Lcom/cooey/common/CommonDatabase;", "getCommonDatabase", "()Lcom/cooey/common/CommonDatabase;", "setCommonDatabase", "(Lcom/cooey/common/CommonDatabase;)V", "common_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: CommonDatabase.kt */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final CommonDatabase getCommonDatabase() {
            return CommonDatabase.commonDatabase;
        }

        public final void setCommonDatabase(@Nullable CommonDatabase <set-?>) {
            CommonDatabase.commonDatabase = <set-?>;
        }
    }

    @NotNull
    public abstract ActionItemDao ActionItemDao();

    @NotNull
    public abstract TimelineItemDao TimelineItemDao();
}
