package com.cooey.android.vitals;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.cooey.android.vitals.converters.Converter;
import com.cooey.android.vitals.dao.VitalBlueprintDao;
import com.cooey.android.vitals.dao.VitalDao;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Database(entities = {VitalBlueprint.class, Vital.class}, version = 1)
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lcom/cooey/android/vitals/VitalsDatabase;", "Landroid/arch/persistence/room/RoomDatabase;", "()V", "VitalBlueprintDao", "Lcom/cooey/android/vitals/dao/VitalBlueprintDao;", "VitalDao", "Lcom/cooey/android/vitals/dao/VitalDao;", "Companion", "vitals_release"}, k = 1, mv = {1, 1, 7})
@TypeConverters({Converter.class})
/* compiled from: VitalsDatabase.kt */
public abstract class VitalsDatabase extends RoomDatabase {
    public static final Companion Companion = new Companion();
    @Nullable
    private static VitalsDatabase vitalsDatabase;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/cooey/android/vitals/VitalsDatabase$Companion;", "", "()V", "vitalsDatabase", "Lcom/cooey/android/vitals/VitalsDatabase;", "getVitalsDatabase", "()Lcom/cooey/android/vitals/VitalsDatabase;", "setVitalsDatabase", "(Lcom/cooey/android/vitals/VitalsDatabase;)V", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalsDatabase.kt */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final VitalsDatabase getVitalsDatabase() {
            return VitalsDatabase.vitalsDatabase;
        }

        public final void setVitalsDatabase(@Nullable VitalsDatabase <set-?>) {
            VitalsDatabase.vitalsDatabase = <set-?>;
        }
    }

    @NotNull
    public abstract VitalBlueprintDao VitalBlueprintDao();

    @NotNull
    public abstract VitalDao VitalDao();
}
