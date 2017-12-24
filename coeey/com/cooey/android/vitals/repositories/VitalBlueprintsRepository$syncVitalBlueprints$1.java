package com.cooey.android.vitals.repositories;

import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.dao.VitalBlueprintDao;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J4\u0010\u000b\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\b2\u0014\u0010\f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\rH\u0016¨\u0006\u000e"}, d2 = {"com/cooey/android/vitals/repositories/VitalBlueprintsRepository$syncVitalBlueprints$1", "Lretrofit2/Callback;", "", "Lcom/cooey/android/vitals/VitalBlueprint;", "(Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalBlueprintsRepository.kt */
public final class VitalBlueprintsRepository$syncVitalBlueprints$1 implements Callback<List<? extends VitalBlueprint>> {
    final /* synthetic */ VitalBlueprintsRepository this$0;

    VitalBlueprintsRepository$syncVitalBlueprints$1(VitalBlueprintsRepository $outer) {
        this.this$0 = $outer;
    }

    public void onFailure(@Nullable Call<List<VitalBlueprint>> call, @Nullable Throwable t) {
    }

    public void onResponse(@Nullable Call<List<VitalBlueprint>> call, @Nullable Response<List<VitalBlueprint>> response) {
        List blueprints = response != null ? (List) response.body() : null;
        if (blueprints != null) {
            VitalBlueprintDao vitalBlueprintDao = this.this$0.getVitalBlueprintDao();
            if (vitalBlueprintDao != null) {
                vitalBlueprintDao.insert(blueprints);
            }
        }
    }
}
