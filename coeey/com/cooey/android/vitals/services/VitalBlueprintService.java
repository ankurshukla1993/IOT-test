package com.cooey.android.vitals.services;

import com.cooey.android.vitals.VitalBlueprint;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0001\u0010\b\u001a\u00020\u0007H'¨\u0006\t"}, d2 = {"Lcom/cooey/android/vitals/services/VitalBlueprintService;", "", "getVitalBlueprintsForTenant", "Lretrofit2/Call;", "", "Lcom/cooey/android/vitals/VitalBlueprint;", "token", "", "tenantId", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalBlueprintService.kt */
public interface VitalBlueprintService {
    @NotNull
    @GET("v2/vitals/blueprints/tenants/{tenantId}")
    Call<List<VitalBlueprint>> getVitalBlueprintsForTenant(@Nullable @Header("X-Auth-Token") String str, @NotNull @Path("tenantId") String str2);
}
