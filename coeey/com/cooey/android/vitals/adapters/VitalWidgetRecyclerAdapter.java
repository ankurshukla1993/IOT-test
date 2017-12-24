package com.cooey.android.vitals.adapters;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cooey.android.vitals.R;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.repositories.VitalBlueprintsRepository;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.CommonDatabase;
import com.google.gson.Gson;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u00019B;\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\b\u0010/\u001a\u000200H\u0016J\u001e\u00101\u001a\u0002022\f\u00103\u001a\b\u0018\u00010\u0002R\u00020\u00002\u0006\u00104\u001a\u000200H\u0016J\u001e\u00105\u001a\u00060\u0002R\u00020\u00002\b\u00106\u001a\u0004\u0018\u0001072\u0006\u00108\u001a\u000200H\u0016R\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u0006:"}, d2 = {"Lcom/cooey/android/vitals/adapters/VitalWidgetRecyclerAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/cooey/android/vitals/adapters/VitalWidgetRecyclerAdapter$VitalWidgetItemViewHolder;", "context", "Landroid/content/Context;", "userId", "", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "vitalBlueprintsRepository", "Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "commonDatabase", "Lcom/cooey/common/CommonDatabase;", "(Landroid/content/Context;Ljava/lang/String;Lcom/cooey/android/vitals/repositories/VitalRepository;Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;Landroid/arch/lifecycle/LifecycleOwner;Lcom/cooey/common/CommonDatabase;)V", "getCommonDatabase", "()Lcom/cooey/common/CommonDatabase;", "setCommonDatabase", "(Lcom/cooey/common/CommonDatabase;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getLifecycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroid/arch/lifecycle/LifecycleOwner;)V", "getUserId", "()Ljava/lang/String;", "setUserId", "(Ljava/lang/String;)V", "vitalBlueprints", "", "Lcom/cooey/android/vitals/VitalBlueprint;", "getVitalBlueprints", "()Ljava/util/List;", "setVitalBlueprints", "(Ljava/util/List;)V", "getVitalBlueprintsRepository", "()Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;", "setVitalBlueprintsRepository", "(Lcom/cooey/android/vitals/repositories/VitalBlueprintsRepository;)V", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "VitalWidgetItemViewHolder", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalWidgetRecyclerAdapter.kt */
public final class VitalWidgetRecyclerAdapter extends Adapter<VitalWidgetItemViewHolder> {
    @NotNull
    private CommonDatabase commonDatabase;
    @NotNull
    private Context context;
    @NotNull
    private LifecycleOwner lifecycleOwner;
    @Nullable
    private String userId;
    @Nullable
    private List<VitalBlueprint> vitalBlueprints;
    @Nullable
    private VitalBlueprintsRepository vitalBlueprintsRepository;
    @Nullable
    private VitalRepository vitalRepository;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u001c\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J(\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017J(\u0010\u0018\u001a\u0004\u0018\u00010\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J*\u0010\u001b\u001a\n \u001d*\u0004\u0018\u0001H\u001cH\u001c\"\u0006\b\u0000\u0010\u001c\u0018\u0001*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000fH\b¢\u0006\u0002\u0010 R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006!"}, d2 = {"Lcom/cooey/android/vitals/adapters/VitalWidgetRecyclerAdapter$VitalWidgetItemViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/cooey/android/vitals/adapters/VitalWidgetRecyclerAdapter;Landroid/view/View;)V", "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "addEmptyField", "", "fieldValueContainer", "Landroid/widget/LinearLayout;", "addFields", "parameters", "", "bind", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "userId", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "generateParameterView", "key", "value", "fromJson", "T", "kotlin.jvm.PlatformType", "Lcom/google/gson/Gson;", "json", "(Lcom/google/gson/Gson;Ljava/lang/String;)Ljava/lang/Object;", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalWidgetRecyclerAdapter.kt */
    public final class VitalWidgetItemViewHolder extends ViewHolder {
        final /* synthetic */ VitalWidgetRecyclerAdapter this$0;
        @NotNull
        private View view;

        private final void addFields(java.lang.String r1, android.widget.LinearLayout r2) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.cooey.android.vitals.adapters.VitalWidgetRecyclerAdapter.VitalWidgetItemViewHolder.addFields(java.lang.String, android.widget.LinearLayout):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:256)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.addJump(MethodNode.java:370)
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:356)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 8 more
*/
            /*
            r0 = this;
            if (r11 == 0) goto L_0x0005;
        L_0x0002:
            r11.removeAllViewsInLayout();
        L_0x0005:
            if (r10 != 0) goto L_0x0008;
        L_0x0007:
            return;
        L_0x0008:
            r7 = new com.google.gson.Gson;
            r7.<init>();
            r2 = r10;
            r8 = new com.cooey.android.vitals.adapters.VitalWidgetRecyclerAdapter$VitalWidgetItemViewHolder$addFields$$inlined$fromJson$1;
            r8.<init>();
            r8 = r8.getType();
            r4 = r7.fromJson(r2, r8);
            r4 = (java.util.Map) r4;
            r0 = r4.keySet();
            r0 = (java.lang.Iterable) r0;
            r8 = r0.iterator();
            r7 = r8.hasNext();
            if (r7 == 0) goto L_0x0087;
        L_0x002d:
            r1 = r8.next();
            r3 = r1;
            r3 = (java.lang.String) r3;
            r7 = "notes";
            r7 = (java.lang.CharSequence) r7;
            if (r3 != 0) goto L_0x0042;
            r7 = new kotlin.TypeCastException;
            r8 = "null cannot be cast to non-null type java.lang.String";
            r7.<init>(r8);
            throw r7;
            r7 = r3.contentEquals(r7);
            if (r7 != 0) goto L_0x0084;
            r7 = "mood";
            r7 = (java.lang.CharSequence) r7;
            if (r3 != 0) goto L_0x0056;
            r7 = new kotlin.TypeCastException;
            r8 = "null cannot be cast to non-null type java.lang.String";
            r7.<init>(r8);
            throw r7;
            r7 = r3.contentEquals(r7);
            if (r7 != 0) goto L_0x0084;
            r7 = "context";
            r7 = (java.lang.CharSequence) r7;
            if (r3 != 0) goto L_0x006a;
            r7 = new kotlin.TypeCastException;
            r8 = "null cannot be cast to non-null type java.lang.String";
            r7.<init>(r8);
            throw r7;
            r7 = r3.contentEquals(r7);
            if (r7 != 0) goto L_0x0084;
            r6 = r4.get(r3);
            r6 = (java.lang.String) r6;
            r5 = r9.generateParameterView(r3, r6, r11);
            if (r11 == 0) goto L_0x007f;
            r11.addView(r5);
            if (r11 == 0) goto L_0x0084;
            r11.invalidate();
            goto L_0x0027;
            goto L_0x0007;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.vitals.adapters.VitalWidgetRecyclerAdapter.VitalWidgetItemViewHolder.addFields(java.lang.String, android.widget.LinearLayout):void");
        }

        public VitalWidgetItemViewHolder(@NotNull VitalWidgetRecyclerAdapter $outer, View view) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.this$0 = $outer;
            super(view);
            this.view = view;
        }

        @NotNull
        public final View getView() {
            return this.view;
        }

        public final void setView(@NotNull View <set-?>) {
            Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
            this.view = <set-?>;
        }

        private final <T> T fromJson(@NotNull Gson $receiver, String json) {
            Intrinsics.needClassReification();
            return $receiver.fromJson(json, new VitalWidgetRecyclerAdapter$VitalWidgetItemViewHolder$fromJson$1().getType());
        }

        public final void bind(@NotNull VitalBlueprint vitalBlueprint, @NotNull String userId, @Nullable VitalRepository vitalRepository, @NotNull LifecycleOwner lifecycleOwner) {
            Intrinsics.checkParameterIsNotNull(vitalBlueprint, "vitalBlueprint");
            Intrinsics.checkParameterIsNotNull(userId, "userId");
            Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
            this.view.setOnClickListener(new VitalWidgetRecyclerAdapter$VitalWidgetItemViewHolder$bind$1(this, userId, vitalBlueprint, vitalRepository));
            ((TextView) this.view.findViewById(R.id.vital_title)).setText(vitalBlueprint.getName());
            ObjectRef objectRef = new ObjectRef();
            objectRef.element = (LinearLayout) this.view.findViewById(R.id.field_values_container);
            LiveData liveVital = vitalRepository != null ? vitalRepository.getLatestVitalForType(vitalBlueprint.getType(), userId) : null;
            if (liveVital != null) {
                liveVital.observe(lifecycleOwner, new VitalWidgetRecyclerAdapter$VitalWidgetItemViewHolder$bind$2(this, objectRef));
            }
        }

        private final void addEmptyField(LinearLayout fieldValueContainer) {
            if (fieldValueContainer != null) {
                fieldValueContainer.removeAllViewsInLayout();
            }
            View view = generateParameterView("", "NA", fieldValueContainer);
            if (fieldValueContainer != null) {
                fieldValueContainer.addView(view);
            }
            if (fieldValueContainer != null) {
                fieldValueContainer.invalidate();
            }
        }

        private final View generateParameterView(String key, String value, LinearLayout fieldValueContainer) {
            View view = this.view;
            View view2 = LayoutInflater.from(view != null ? view.getContext() : null).inflate(R.layout.layout_vital_parameter, fieldValueContainer, false);
            TextView parameterValue = (TextView) view2.findViewById(R.id.parameter_value);
            ((TextView) view2.findViewById(R.id.parameter_key)).setText(key);
            parameterValue.setText(value);
            return view2;
        }
    }

    public VitalWidgetRecyclerAdapter(@NotNull Context context, @Nullable String userId, @Nullable VitalRepository vitalRepository, @Nullable VitalBlueprintsRepository vitalBlueprintsRepository, @NotNull LifecycleOwner lifecycleOwner, @NotNull CommonDatabase commonDatabase) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkParameterIsNotNull(commonDatabase, "commonDatabase");
        this.context = context;
        this.userId = userId;
        this.vitalRepository = vitalRepository;
        this.vitalBlueprintsRepository = vitalBlueprintsRepository;
        this.lifecycleOwner = lifecycleOwner;
        this.commonDatabase = commonDatabase;
    }

    @NotNull
    public final CommonDatabase getCommonDatabase() {
        return this.commonDatabase;
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    @Nullable
    public final String getUserId() {
        return this.userId;
    }

    @Nullable
    public final VitalBlueprintsRepository getVitalBlueprintsRepository() {
        return this.vitalBlueprintsRepository;
    }

    @Nullable
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setCommonDatabase(@NotNull CommonDatabase <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.commonDatabase = <set-?>;
    }

    public final void setContext(@NotNull Context <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.context = <set-?>;
    }

    public final void setLifecycleOwner(@NotNull LifecycleOwner <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.lifecycleOwner = <set-?>;
    }

    public final void setUserId(@Nullable String <set-?>) {
        this.userId = <set-?>;
    }

    public final void setVitalBlueprintsRepository(@Nullable VitalBlueprintsRepository <set-?>) {
        this.vitalBlueprintsRepository = <set-?>;
    }

    public final void setVitalRepository(@Nullable VitalRepository <set-?>) {
        this.vitalRepository = <set-?>;
    }

    @Nullable
    public final List<VitalBlueprint> getVitalBlueprints() {
        return this.vitalBlueprints;
    }

    public final void setVitalBlueprints(@Nullable List<VitalBlueprint> <set-?>) {
        this.vitalBlueprints = <set-?>;
    }

    public int getItemCount() {
        if (this.vitalBlueprints == null) {
            return 0;
        }
        List list = this.vitalBlueprints;
        Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        return valueOf.intValue();
    }

    @NotNull
    public VitalWidgetItemViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.layout_vital_widget_item, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        return new VitalWidgetItemViewHolder(this, view);
    }

    public void onBindViewHolder(@Nullable VitalWidgetItemViewHolder holder, int position) {
        if (holder != null) {
            List list = this.vitalBlueprints;
            VitalBlueprint vitalBlueprint = list != null ? (VitalBlueprint) list.get(position) : null;
            if (vitalBlueprint == null) {
                Intrinsics.throwNpe();
            }
            String str = this.userId;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            holder.bind(vitalBlueprint, str, this.vitalRepository, this.lifecycleOwner);
        }
    }
}
