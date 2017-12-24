package com.cooey.android.vitals;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.cooey.android.vitals.repositories.VitalRepository;
import com.cooey.common.CommonDatabase;
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

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 32\u00020\u0001:\u000234B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020*H\u0016J\u0012\u0010+\u001a\u00020!2\b\u0010,\u001a\u0004\u0018\u00010#H\u0014J\b\u0010-\u001a\u00020!H\u0002J\u0006\u0010.\u001a\u00020!J\u0012\u0010/\u001a\u00020!2\b\u00100\u001a\u0004\u0018\u000101H\u0002J\b\u00102\u001a\u00020%H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010¨\u00065"}, d2 = {"Lcom/cooey/android/vitals/VitalInputActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "HAPPY", "", "getHAPPY", "()Ljava/lang/String;", "INDIFFERENT", "getINDIFFERENT", "SAD", "getSAD", "happy", "Landroid/widget/CheckBox;", "getHappy", "()Landroid/widget/CheckBox;", "setHappy", "(Landroid/widget/CheckBox;)V", "indifferent", "getIndifferent", "setIndifferent", "layout", "Landroid/widget/LinearLayout;", "getLayout", "()Landroid/widget/LinearLayout;", "setLayout", "(Landroid/widget/LinearLayout;)V", "notesEditText", "Landroid/widget/EditText;", "sad", "getSad", "setSad", "checkMood", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onSaveInstanceState", "outState", "saveVital", "setupToolbar", "setupVitalFields", "container", "Landroid/view/ViewGroup;", "validateValues", "Companion", "SaveCallback", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalInputActivity.kt */
public final class VitalInputActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion();
    @Nullable
    private static CommonDatabase commonDatabase;
    @Nullable
    private static String contextId;
    @Nullable
    private static String contextType;
    @Nullable
    private static String deviceId;
    @Nullable
    private static String deviceType;
    private static boolean isDeviceRading;
    @Nullable
    private static String platform;
    @Nullable
    private static String postAction;
    @Nullable
    private static com.cooey.android.vitals.views.VitalInputDialogFragment.SaveCallback saveCallback;
    @Nullable
    private static Session session;
    @Nullable
    private static String userId;
    @Nullable
    private static Map<String, String> values;
    @Nullable
    private static VitalBlueprint vitalBlueprint;
    @Nullable
    private static VitalRepository vitalRepository;
    @NotNull
    private final String HAPPY = "Happy";
    @NotNull
    private final String INDIFFERENT = "Indifferent";
    @NotNull
    private final String SAD = "Sad";
    private HashMap _$_findViewCache;
    @Nullable
    private CheckBox happy;
    @Nullable
    private CheckBox indifferent;
    @Nullable
    private LinearLayout layout;
    private EditText notesEditText;
    @Nullable
    private CheckBox sad;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\f\"\u0004\b\u001f\u0010\u000eR\u001c\u0010 \u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\f\"\u0004\b\"\u0010\u000eR\u001c\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010/\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\f\"\u0004\b1\u0010\u000eR(\u00102\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u000103X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001c\u00108\u001a\u0004\u0018\u000109X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001c\u0010>\u001a\u0004\u0018\u00010?X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010C¨\u0006D"}, d2 = {"Lcom/cooey/android/vitals/VitalInputActivity$Companion;", "", "()V", "commonDatabase", "Lcom/cooey/common/CommonDatabase;", "getCommonDatabase", "()Lcom/cooey/common/CommonDatabase;", "setCommonDatabase", "(Lcom/cooey/common/CommonDatabase;)V", "contextId", "", "getContextId", "()Ljava/lang/String;", "setContextId", "(Ljava/lang/String;)V", "contextType", "getContextType", "setContextType", "deviceId", "getDeviceId", "setDeviceId", "deviceType", "getDeviceType", "setDeviceType", "isDeviceRading", "", "()Z", "setDeviceRading", "(Z)V", "platform", "getPlatform", "setPlatform", "postAction", "getPostAction", "setPostAction", "saveCallback", "Lcom/cooey/android/vitals/views/VitalInputDialogFragment$SaveCallback;", "getSaveCallback", "()Lcom/cooey/android/vitals/views/VitalInputDialogFragment$SaveCallback;", "setSaveCallback", "(Lcom/cooey/android/vitals/views/VitalInputDialogFragment$SaveCallback;)V", "session", "Lcom/cooey/common/vo/Session;", "getSession", "()Lcom/cooey/common/vo/Session;", "setSession", "(Lcom/cooey/common/vo/Session;)V", "userId", "getUserId", "setUserId", "values", "", "getValues", "()Ljava/util/Map;", "setValues", "(Ljava/util/Map;)V", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "getVitalBlueprint", "()Lcom/cooey/android/vitals/VitalBlueprint;", "setVitalBlueprint", "(Lcom/cooey/android/vitals/VitalBlueprint;)V", "vitalRepository", "Lcom/cooey/android/vitals/repositories/VitalRepository;", "getVitalRepository", "()Lcom/cooey/android/vitals/repositories/VitalRepository;", "setVitalRepository", "(Lcom/cooey/android/vitals/repositories/VitalRepository;)V", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalInputActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final VitalBlueprint getVitalBlueprint() {
            return VitalInputActivity.vitalBlueprint;
        }

        public final void setVitalBlueprint(@Nullable VitalBlueprint <set-?>) {
            VitalInputActivity.vitalBlueprint = <set-?>;
        }

        @Nullable
        public final String getUserId() {
            return VitalInputActivity.userId;
        }

        public final void setUserId(@Nullable String <set-?>) {
            VitalInputActivity.userId = <set-?>;
        }

        @Nullable
        public final Session getSession() {
            return VitalInputActivity.session;
        }

        public final void setSession(@Nullable Session <set-?>) {
            VitalInputActivity.session = <set-?>;
        }

        @Nullable
        public final com.cooey.android.vitals.views.VitalInputDialogFragment.SaveCallback getSaveCallback() {
            return VitalInputActivity.saveCallback;
        }

        public final void setSaveCallback(@Nullable com.cooey.android.vitals.views.VitalInputDialogFragment.SaveCallback <set-?>) {
            VitalInputActivity.saveCallback = <set-?>;
        }

        @Nullable
        public final VitalRepository getVitalRepository() {
            return VitalInputActivity.vitalRepository;
        }

        public final void setVitalRepository(@Nullable VitalRepository <set-?>) {
            VitalInputActivity.vitalRepository = <set-?>;
        }

        @Nullable
        public final CommonDatabase getCommonDatabase() {
            return VitalInputActivity.commonDatabase;
        }

        public final void setCommonDatabase(@Nullable CommonDatabase <set-?>) {
            VitalInputActivity.commonDatabase = <set-?>;
        }

        @Nullable
        public final Map<String, String> getValues() {
            return VitalInputActivity.values;
        }

        public final void setValues(@Nullable Map<String, String> <set-?>) {
            VitalInputActivity.values = <set-?>;
        }

        @Nullable
        public final String getDeviceId() {
            return VitalInputActivity.deviceId;
        }

        public final void setDeviceId(@Nullable String <set-?>) {
            VitalInputActivity.deviceId = <set-?>;
        }

        @Nullable
        public final String getDeviceType() {
            return VitalInputActivity.deviceType;
        }

        public final void setDeviceType(@Nullable String <set-?>) {
            VitalInputActivity.deviceType = <set-?>;
        }

        @Nullable
        public final String getPostAction() {
            return VitalInputActivity.postAction;
        }

        public final void setPostAction(@Nullable String <set-?>) {
            VitalInputActivity.postAction = <set-?>;
        }

        @Nullable
        public final String getPlatform() {
            return VitalInputActivity.platform;
        }

        public final void setPlatform(@Nullable String <set-?>) {
            VitalInputActivity.platform = <set-?>;
        }

        @Nullable
        public final String getContextType() {
            return VitalInputActivity.contextType;
        }

        public final void setContextType(@Nullable String <set-?>) {
            VitalInputActivity.contextType = <set-?>;
        }

        @Nullable
        public final String getContextId() {
            return VitalInputActivity.contextId;
        }

        public final void setContextId(@Nullable String <set-?>) {
            VitalInputActivity.contextId = <set-?>;
        }

        public final boolean isDeviceRading() {
            return VitalInputActivity.isDeviceRading;
        }

        public final void setDeviceRading(boolean <set-?>) {
            VitalInputActivity.isDeviceRading = <set-?>;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/cooey/android/vitals/VitalInputActivity$SaveCallback;", "", "onSave", "", "vital", "Lcom/cooey/android/vitals/Vital;", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: VitalInputActivity.kt */
    public interface SaveCallback {
        void onSave(@NotNull Vital vital);
    }

    private final boolean validateValues() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.cooey.android.vitals.VitalInputActivity.validateValues():boolean
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
        r5 = 0;
        r4 = Companion;
        r4 = r4.getVitalBlueprint();
        if (r4 == 0) goto L_0x007a;
    L_0x0009:
        r0 = r4.getFields();
        if (r0 == 0) goto L_0x007a;
    L_0x000f:
        r0 = (java.lang.Iterable) r0;
        r6 = r0.iterator();
        r4 = r6.hasNext();
        if (r4 == 0) goto L_0x007a;
    L_0x001b:
        r2 = r6.next();
        r3 = r2;
        r3 = (com.cooey.android.vitals.Field) r3;
        r4 = r8.layout;
        if (r4 == 0) goto L_0x0071;
    L_0x0026:
        r7 = r3.getKey();
        r4 = r4.findViewWithTag(r7);
        r4 = (android.widget.EditText) r4;
        r1 = r4;
        if (r1 == 0) goto L_0x0073;
    L_0x0033:
        r4 = r1.getText();
        r4 = java.lang.String.valueOf(r4);
        if (r4 == 0) goto L_0x004d;
    L_0x003d:
        if (r1 == 0) goto L_0x0075;
    L_0x003f:
        r4 = r1.getText();
        r4 = java.lang.String.valueOf(r4);
        r4 = r4.length();
        if (r4 > 0) goto L_0x0077;
    L_0x004d:
        r4 = com.cooey.android.vitals.Utility.Companion;
        r8 = (android.content.Context) r8;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r3.getKey();
        r6 = kotlin.text.StringsKt__StringsJVMKt.capitalize(r6);
        r5 = r5.append(r6);
        r6 = " is empty.";
        r5 = r5.append(r6);
        r5 = r5.toString();
        r4.showErrorAlert(r8, r5);
        r4 = 0;
        return r4;
    L_0x0071:
        r1 = r5;
        goto L_0x0031;
    L_0x0073:
        r4 = r5;
        goto L_0x0037;
    L_0x0075:
        r4 = r5;
        goto L_0x0043;
        goto L_0x0015;
    L_0x007a:
        r4 = 1;
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.vitals.VitalInputActivity.validateValues():boolean");
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
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
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
    public final LinearLayout getLayout() {
        return this.layout;
    }

    public final void setLayout(@Nullable LinearLayout <set-?>) {
        this.layout = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dialog_vital_input);
        this.layout = (LinearLayout) findViewById(R.id.fields_container);
        setupToolbar();
        setupVitalFields(this.layout);
        this.notesEditText = (EditText) findViewById(R.id.notes_text);
    }

    public final void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StringBuilder append = new StringBuilder().append("Add ");
        VitalBlueprint vitalBlueprint = Companion.getVitalBlueprint();
        toolbar.setTitle((CharSequence) append.append(vitalBlueprint != null ? vitalBlueprint.getName() : null).toString());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(17301560);
        }
    }

    private final void setupVitalFields(ViewGroup container) {
        LinearLayout vitalInputContainer = (LinearLayout) findViewById(R.id.vital_input_container);
        VitalBlueprint vitalBlueprint = Companion.getVitalBlueprint();
        if (vitalBlueprint != null) {
            Iterable<Field> $receiver$iv = vitalBlueprint.getFields();
            if ($receiver$iv != null) {
                for (Field field : $receiver$iv) {
                    if (field.getKey() == null) {
                        field.setKey(field.getLabel());
                    }
                    TextInputLayout textInputLayout = LayoutInflater.from(this).inflate(R.layout.layout_vital_input_field, container, false);
                    if (textInputLayout == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.support.design.widget.TextInputLayout");
                    }
                    textInputLayout = textInputLayout;
                    EditText editText = (EditText) textInputLayout.findViewById(R.id.vital_input_edit_text);
                    Map values = Companion.getValues();
                    String value = values != null ? (String) values.get(field.getKey()) : null;
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

    public boolean onCreateOptionsMenu(@NotNull Menu menu) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        getMenuInflater().inflate(R.menu.menu_input_dialog, menu);
        return true;
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
            finish();
            return true;
        }
    }

    private final void saveVital() {
        if (validateValues()) {
            LinkedHashMap parameterMap = new LinkedHashMap();
            VitalBlueprint vitalBlueprint = Companion.getVitalBlueprint();
            if (vitalBlueprint != null) {
                Iterable<Field> $receiver$iv = vitalBlueprint.getFields();
                if ($receiver$iv != null) {
                    for (Field field : $receiver$iv) {
                        LinearLayout linearLayout = this.layout;
                        EditText editText = linearLayout != null ? (EditText) linearLayout.findViewWithTag(field.getKey()) : null;
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
            VitalBlueprint vitalBlueprint2 = Companion.getVitalBlueprint();
            String type = vitalBlueprint2 != null ? vitalBlueprint2.getType() : null;
            if (type == null) {
                Intrinsics.throwNpe();
            }
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            String userId = Companion.getUserId();
            if (userId == null) {
                Intrinsics.throwNpe();
            }
            Session session = Companion.getSession();
            Vital vital = new Vital(uuid, type, timeInMillis, parameters, userId, session != null ? session.getUserId() : null, Companion.getPostAction(), Companion.getDeviceId(), Companion.getDeviceType(), Companion.getPlatform(), Companion.getContextType(), Companion.getContextId(), Boolean.valueOf(Companion.isDeviceRading()));
            VitalRepository vitalRepository = Companion.getVitalRepository();
            if (vitalRepository != null) {
                vitalRepository.insertVital(vital);
            }
            com.cooey.android.vitals.views.VitalInputDialogFragment.SaveCallback saveCallback = Companion.getSaveCallback();
            if (saveCallback != null) {
                saveCallback.onSave(vital);
            }
            finish();
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

    protected void onSaveInstanceState(@Nullable Bundle outState) {
    }
}
