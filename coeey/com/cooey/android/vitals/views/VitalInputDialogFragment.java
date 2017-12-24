package com.cooey.android.vitals.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.cooey.android.vitals.DataType;
import com.cooey.android.vitals.Field;
import com.cooey.android.vitals.R;
import com.cooey.android.vitals.Vital;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.vo.Session;
import com.google.gson.GsonBuilder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 p2\u00020\u0001:\u0002pqB\u0005¢\u0006\u0002\u0010\u0002J\n\u0010V\u001a\u0004\u0018\u00010\u0004H\u0002J\u001c\u0010W\u001a\u00020X2\u0014\u0010Y\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010EJ\u0012\u0010Z\u001a\u00020X2\b\u0010[\u001a\u0004\u0018\u00010\\H\u0016J\u0012\u0010]\u001a\u00020^2\b\u0010[\u001a\u0004\u0018\u00010\\H\u0016J\u0018\u0010_\u001a\u00020X2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020cH\u0016J(\u0010d\u001a\u0004\u0018\u00010e2\b\u0010b\u001a\u0004\u0018\u00010f2\b\u0010g\u001a\u0004\u0018\u00010h2\b\u0010[\u001a\u0004\u0018\u00010\\H\u0016J\u0010\u0010i\u001a\u00020#2\u0006\u0010j\u001a\u00020kH\u0016J\b\u0010l\u001a\u00020XH\u0002J\u0010\u0010m\u001a\u00020X2\u0006\u0010n\u001a\u00020eH\u0002J\u001a\u0010o\u001a\u00020X2\u0006\u0010n\u001a\u00020e2\b\u0010g\u001a\u0004\u0018\u00010hH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u000e\u0010\u000b\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\u000fR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\u000fR\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\u000fR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u001a\u0010\"\u001a\u00020#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010$\"\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010)\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0006\"\u0004\b+\u0010\u000fR\u001c\u0010,\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0006\"\u0004\b.\u0010\u000fR\u001c\u0010/\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0006\"\u0004\b1\u0010\u000fR\u001c\u00102\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001c\"\u0004\b4\u0010\u001eR\u001c\u00105\u001a\u0004\u0018\u000106X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001c\u0010;\u001a\u0004\u0018\u00010<X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001c\u0010A\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u0006\"\u0004\bC\u0010\u000fR(\u0010D\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010EX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001c\u0010J\u001a\u0004\u0018\u00010KX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001c\u0010P\u001a\u0004\u0018\u00010QX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010U¨\u0006r"}, d2 = {"Lcom/cooey/android/vitals/views/VitalInputDialogFragment;", "Landroid/support/v4/app/DialogFragment;", "()V", "HAPPY", "", "getHAPPY", "()Ljava/lang/String;", "INDIFFERENT", "getINDIFFERENT", "SAD", "getSAD", "TAG", "contextId", "getContextId", "setContextId", "(Ljava/lang/String;)V", "contextType", "getContextType", "setContextType", "deviceId", "getDeviceId", "setDeviceId", "deviceType", "getDeviceType", "setDeviceType", "happy", "Landroid/widget/CheckBox;", "getHappy", "()Landroid/widget/CheckBox;", "setHappy", "(Landroid/widget/CheckBox;)V", "indifferent", "getIndifferent", "setIndifferent", "isDeviceRading", "", "()Z", "setDeviceRading", "(Z)V", "notesEditText", "Landroid/widget/EditText;", "platform", "getPlatform", "setPlatform", "postAction", "getPostAction", "setPostAction", "resolutionId", "getResolutionId", "setResolutionId", "sad", "getSad", "setSad", "saveCallback", "Lcom/cooey/android/vitals/views/VitalInputDialogFragment$SaveCallback;", "getSaveCallback", "()Lcom/cooey/android/vitals/views/VitalInputDialogFragment$SaveCallback;", "setSaveCallback", "(Lcom/cooey/android/vitals/views/VitalInputDialogFragment$SaveCallback;)V", "session", "Lcom/cooey/common/vo/Session;", "getSession", "()Lcom/cooey/common/vo/Session;", "setSession", "(Lcom/cooey/common/vo/Session;)V", "userId", "getUserId", "setUserId", "values", "", "getValues", "()Ljava/util/Map;", "setValues", "(Ljava/util/Map;)V", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "getVitalBlueprint", "()Lcom/cooey/android/vitals/VitalBlueprint;", "setVitalBlueprint", "(Lcom/cooey/android/vitals/VitalBlueprint;)V", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "checkMood", "loadValues", "", "valuesMap", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "saveVital", "setupToolbar", "rootView", "setupVitalFields", "Companion", "SaveCallback", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalInputDialogFragment.kt */
public final class VitalInputDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion();
    @NotNull
    private final String HAPPY = "Happy";
    @NotNull
    private final String INDIFFERENT = "Indifferent";
    @NotNull
    private final String SAD = "Sad";
    private final String TAG = "VitalInputDialogFragment";
    private HashMap _$_findViewCache;
    @Nullable
    private String contextId;
    @Nullable
    private String contextType;
    @Nullable
    private String deviceId;
    @Nullable
    private String deviceType;
    @Nullable
    private CheckBox happy;
    @Nullable
    private CheckBox indifferent;
    private boolean isDeviceRading;
    private EditText notesEditText;
    @Nullable
    private String platform;
    @Nullable
    private String postAction;
    @Nullable
    private String resolutionId;
    @Nullable
    private CheckBox sad;
    @Nullable
    private SaveCallback saveCallback;
    @Nullable
    private Session session;
    @Nullable
    private String userId;
    @Nullable
    private Map<String, String> values;
    @Nullable
    private VitalBlueprint vitalBlueprint;
    @Nullable
    private VitalRepository vitalRepository;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/cooey/android/vitals/views/VitalInputDialogFragment$Companion;", "", "()V", "newIntance", "Lcom/cooey/android/vitals/views/VitalInputDialogFragment;", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalInputDialogFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final VitalInputDialogFragment newIntance() {
            VitalInputDialogFragment vitalInputDialogFragment = new VitalInputDialogFragment();
            vitalInputDialogFragment.setStyle(0, R.style.FullScreenDialogStyle);
            return vitalInputDialogFragment;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/cooey/android/vitals/views/VitalInputDialogFragment$SaveCallback;", "", "onSave", "", "vital", "Lcom/cooey/android/vitals/Vital;", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalInputDialogFragment.kt */
    public interface SaveCallback {
        void onSave(@NotNull Vital vital);
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

    public final void loadValues(@org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, java.lang.String> r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.cooey.android.vitals.views.VitalInputDialogFragment.loadValues(java.util.Map):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
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
	... 7 more
*/
        /*
        r0 = this;
        if (r7 == 0) goto L_0x003d;
    L_0x0002:
        r0 = r7.keySet();
        if (r0 == 0) goto L_0x003d;
    L_0x0008:
        r0 = (java.lang.Iterable) r0;
        r5 = r0.iterator();
        r4 = r5.hasNext();
        if (r4 == 0) goto L_0x003d;
    L_0x0014:
        r2 = r5.next();
        r3 = r2;
        r3 = (java.lang.String) r3;
        r4 = r6.getView();
        if (r4 == 0) goto L_0x003b;
    L_0x0021:
        r4 = r4.findViewWithTag(r3);
        r4 = (android.widget.EditText) r4;
        r1 = r4;
        if (r1 == 0) goto L_0x0038;
    L_0x002a:
        r4 = r7.get(r3);
        if (r4 != 0) goto L_0x0033;
        kotlin.jvm.internal.Intrinsics.throwNpe();
        r4 = (java.lang.CharSequence) r4;
        r1.setText(r4);
        goto L_0x000e;
    L_0x003b:
        r1 = 0;
        goto L_0x0028;
    L_0x003d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.vitals.views.VitalInputDialogFragment.loadValues(java.util.Map):void");
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Nullable
    public final VitalBlueprint getVitalBlueprint() {
        return this.vitalBlueprint;
    }

    public final void setVitalBlueprint(@Nullable VitalBlueprint <set-?>) {
        this.vitalBlueprint = <set-?>;
    }

    @Nullable
    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(@Nullable String <set-?>) {
        this.userId = <set-?>;
    }

    @Nullable
    public final Session getSession() {
        return this.session;
    }

    public final void setSession(@Nullable Session <set-?>) {
        this.session = <set-?>;
    }

    @Nullable
    public final String getDeviceId() {
        return this.deviceId;
    }

    public final void setDeviceId(@Nullable String <set-?>) {
        this.deviceId = <set-?>;
    }

    @Nullable
    public final String getDeviceType() {
        return this.deviceType;
    }

    public final void setDeviceType(@Nullable String <set-?>) {
        this.deviceType = <set-?>;
    }

    @Nullable
    public final String getPostAction() {
        return this.postAction;
    }

    public final void setPostAction(@Nullable String <set-?>) {
        this.postAction = <set-?>;
    }

    @Nullable
    public final String getPlatform() {
        return this.platform;
    }

    public final void setPlatform(@Nullable String <set-?>) {
        this.platform = <set-?>;
    }

    @Nullable
    public final String getContextType() {
        return this.contextType;
    }

    public final void setContextType(@Nullable String <set-?>) {
        this.contextType = <set-?>;
    }

    @Nullable
    public final String getContextId() {
        return this.contextId;
    }

    public final void setContextId(@Nullable String <set-?>) {
        this.contextId = <set-?>;
    }

    public final boolean isDeviceRading() {
        return this.isDeviceRading;
    }

    public final void setDeviceRading(boolean <set-?>) {
        this.isDeviceRading = <set-?>;
    }

    @Nullable
    public final VitalRepository getVitalRepository() {
        return this.vitalRepository;
    }

    public final void setVitalRepository(@Nullable VitalRepository <set-?>) {
        this.vitalRepository = <set-?>;
    }

    @Nullable
    public final SaveCallback getSaveCallback() {
        return this.saveCallback;
    }

    public final void setSaveCallback(@Nullable SaveCallback <set-?>) {
        this.saveCallback = <set-?>;
    }

    @Nullable
    public final Map<String, String> getValues() {
        return this.values;
    }

    public final void setValues(@Nullable Map<String, String> <set-?>) {
        this.values = <set-?>;
    }

    @Nullable
    public final CheckBox getHappy() {
        return this.happy;
    }

    public final void setHappy(@Nullable CheckBox <set-?>) {
        this.happy = <set-?>;
    }

    @Nullable
    public final CheckBox getSad() {
        return this.sad;
    }

    public final void setSad(@Nullable CheckBox <set-?>) {
        this.sad = <set-?>;
    }

    @Nullable
    public final CheckBox getIndifferent() {
        return this.indifferent;
    }

    public final void setIndifferent(@Nullable CheckBox <set-?>) {
        this.indifferent = <set-?>;
    }

    @Nullable
    public final String getResolutionId() {
        return this.resolutionId;
    }

    public final void setResolutionId(@Nullable String <set-?>) {
        this.resolutionId = <set-?>;
    }

    @Nullable
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) {
            Intrinsics.throwNpe();
        }
        View rootView = inflater.inflate(R.layout.fragment_dialog_vital_input, container, false);
        Intrinsics.checkExpressionValueIsNotNull(rootView, "rootView");
        setupToolbar(rootView);
        setHasOptionsMenu(true);
        setupVitalFields(rootView, container);
        this.notesEditText = (EditText) rootView.findViewById(R.id.notes_text);
        return rootView;
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

    private final void setupVitalFields(View rootView, ViewGroup container) {
        LinearLayout vitalInputContainer = (LinearLayout) rootView.findViewById(R.id.vital_input_container);
        VitalBlueprint vitalBlueprint = this.vitalBlueprint;
        if (vitalBlueprint != null) {
            Iterable<Field> $receiver$iv = vitalBlueprint.getFields();
            if ($receiver$iv != null) {
                for (Field field : $receiver$iv) {
                    if (field.getKey() == null) {
                        field.setKey(field.getLabel());
                    }
                    TextInputLayout textInputLayout = LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_vital_input_field, container, false);
                    if (textInputLayout == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.support.design.widget.TextInputLayout");
                    }
                    textInputLayout = textInputLayout;
                    EditText editText = (EditText) textInputLayout.findViewById(R.id.vital_input_edit_text);
                    Map map = this.values;
                    String value = map != null ? (String) map.get(field.getKey()) : null;
                    if (value != null) {
                        editText.setText(value);
                    }
                    if (editText != null) {
                        editText.setTag(field.getKey());
                    }
                    textInputLayout.setHint(field.getLabel());
                    if (Intrinsics.areEqual(field.getDataType(), DataType.INTEGER)) {
                        editText.setInputType(2);
                    } else if (Intrinsics.areEqual(field.getDataType(), DataType.DECIMAL)) {
                        editText.setInputType(8194);
                    }
                    vitalInputContainer.addView(textInputLayout);
                }
            }
        }
    }

    private final void setupToolbar(View rootView) {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        StringBuilder append = new StringBuilder().append("Add ");
        VitalBlueprint vitalBlueprint = this.vitalBlueprint;
        toolbar.setTitle((CharSequence) append.append(vitalBlueprint != null ? vitalBlueprint.getName() : null).toString());
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
        }
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);
        activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
        }
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(17301560);
        }
    }

    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setFlags(1024, 1024);
        Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
        return dialog;
    }

    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        menu.clear();
        inflater.inflate(R.menu.menu_input_dialog, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        int id = item.getItemId();
        if (id == R.id.save_menu_item) {
            saveVital();
            return true;
        } else if (id != 16908332) {
            return super.onOptionsItemSelected(item);
        } else {
            dismiss();
            return true;
        }
    }

    private final void saveVital() {
        LinkedHashMap parameterMap = new LinkedHashMap();
        VitalBlueprint vitalBlueprint = this.vitalBlueprint;
        if (vitalBlueprint != null) {
            Iterable<Field> $receiver$iv = vitalBlueprint.getFields();
            if ($receiver$iv != null) {
                for (Field field : $receiver$iv) {
                    View view = getView();
                    EditText editText = view != null ? (EditText) view.findViewWithTag(field.getKey()) : null;
                    parameterMap.put(field.getKey(), String.valueOf(editText != null ? editText.getText() : null));
                }
            }
        }
        EditText editText2 = this.notesEditText;
        parameterMap.put("notes", String.valueOf(editText2 != null ? editText2.getText() : null));
        String mood = checkMood();
        if (mood != null) {
            parameterMap.put("mood", mood);
        }
        String parameters = new GsonBuilder().create().toJson(parameterMap);
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        VitalBlueprint vitalBlueprint2 = this.vitalBlueprint;
        String type = vitalBlueprint2 != null ? vitalBlueprint2.getType() : null;
        if (type == null) {
            Intrinsics.throwNpe();
        }
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        String str = this.userId;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        Session session = this.session;
        Vital vital = new Vital(uuid, type, timeInMillis, parameters, str, session != null ? session.getUserId() : null, this.postAction, this.deviceId, this.deviceType, this.platform, this.contextType, this.contextId, Boolean.valueOf(this.isDeviceRading));
        VitalRepository vitalRepository = this.vitalRepository;
        if (vitalRepository != null) {
            vitalRepository.insertVital(vital);
        }
        dismiss();
        SaveCallback saveCallback = this.saveCallback;
        if (saveCallback != null) {
            saveCallback.onSave(vital);
        }
    }

    private final String checkMood() {
        try {
            Boolean valueOf;
            String mood = null;
            CheckBox checkBox = this.happy;
            if (checkBox != null) {
                valueOf = Boolean.valueOf(checkBox.isChecked());
            } else {
                valueOf = null;
            }
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (valueOf.booleanValue()) {
                return this.HAPPY;
            }
            checkBox = this.sad;
            if (checkBox != null) {
                valueOf = Boolean.valueOf(checkBox.isChecked());
            } else {
                valueOf = null;
            }
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (valueOf.booleanValue()) {
                return this.SAD;
            }
            checkBox = this.indifferent;
            if (checkBox != null) {
                valueOf = Boolean.valueOf(checkBox.isChecked());
            } else {
                valueOf = null;
            }
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (valueOf.booleanValue()) {
                return this.INDIFFERENT;
            }
            return mood;
        } catch (Exception e) {
            return null;
        }
    }

    @NotNull
    public final String getHAPPY() {
        return this.HAPPY;
    }

    @NotNull
    public final String getSAD() {
        return this.SAD;
    }

    @NotNull
    public final String getINDIFFERENT() {
        return this.INDIFFERENT;
    }
}
