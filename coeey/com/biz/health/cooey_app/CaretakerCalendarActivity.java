package com.biz.health.cooey_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions.Picker;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import com.biz.health.cooey_app.calendar_events.BlockedDatesAdapter;
import com.biz.health.cooey_app.calendar_events.EventDate;
import com.biz.health.cooey_app.vitals.SublimePickerFragment;
import com.cooey.common.services.ActivitiesService;
import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Activity;
import com.cooey.common.vo.FreeSlot;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.ihealth.communication.control.AmProfile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u00020\u00052\u00020\u00062\u00020\u0007B\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020`J\u0016\u0010a\u001a\u00020b2\u0006\u0010E\u001a\u00020@2\u0006\u0010c\u001a\u00020\u0004J\u000e\u0010d\u001a\u00020^2\u0006\u0010e\u001a\u00020fJ\u0006\u0010g\u001a\u00020^J!\u0010h\u001a\u000e\u0012\u0004\u0012\u00020j\u0012\u0004\u0012\u00020k0i2\u0006\u0010_\u001a\u00020`H\u0000¢\u0006\u0002\blJ\u000e\u0010m\u001a\u00020^2\u0006\u0010n\u001a\u00020jJ\u0006\u0010o\u001a\u00020^J\b\u0010p\u001a\u00020^H\u0016J\b\u0010q\u001a\u00020^H\u0016J\u0012\u0010r\u001a\u00020^2\b\u0010s\u001a\u0004\u0018\u00010tH\u0016J\u0012\u0010u\u001a\u00020^2\b\u0010v\u001a\u0004\u0018\u00010wH\u0014JT\u0010x\u001a\u0010\u0012\u0004\u0012\u00020j\u0012\u0004\u0012\u00020k\u0018\u00010i2\u0006\u0010y\u001a\u00020z2\u0006\u0010{\u001a\u00020z2\u0006\u0010|\u001a\u00020z2\u0006\u0010}\u001a\u00020z2\u0006\u0010~\u001a\u00020z2\t\u0010\u001a\u0005\u0018\u00010\u00012\t\u0010\u0001\u001a\u0004\u0018\u00010\u0016H\u0016J\u0014\u0010\u0001\u001a\u00020^2\t\u0010\u0001\u001a\u0004\u0018\u00010fH\u0016J-\u0010\u0001\u001a\u00020^2\u0016\u0010\u0001\u001a\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u00012\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0016J\u0014\u0010\u0001\u001a\u00020^2\t\u0010\u0001\u001a\u0004\u0018\u00010fH\u0016J\u0013\u0010\u0001\u001a\u00020j2\b\u0010\u0001\u001a\u00030\u0001H\u0016J9\u0010\u0001\u001a\u00020^2\u0016\u0010\u0001\u001a\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u00012\u0016\u0010\u0001\u001a\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0001H\u0016J\u0012\u0010\u0001\u001a\u00020^2\u0007\u0010\u0001\u001a\u00020@H\u0002J\u0007\u0010\u0001\u001a\u00020^R\u001a\u0010\t\u001a\u00020\nX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R \u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001a\u0010(\u001a\u00020)X.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020)X.¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R\u0010\u00101\u001a\u0004\u0018\u000102X\u000e¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u000204X.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020:X.¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001a\u0010?\u001a\u00020@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001c\u0010E\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0018\"\u0004\bG\u0010\u001aR\u001a\u0010H\u001a\u00020\u0016X.¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0018\"\u0004\bJ\u0010\u001aR\u001a\u0010K\u001a\u00020LX.¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001a\u0010Q\u001a\u00020RX.¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001a\u0010W\u001a\u00020RX.¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010T\"\u0004\bY\u0010VR\u001a\u0010Z\u001a\u00020RX.¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010T\"\u0004\b\\\u0010V¨\u0006\u0001"}, d2 = {"Lcom/biz/health/cooey_app/CaretakerCalendarActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Lretrofit2/Callback;", "", "Lcom/cooey/common/vo/Activity;", "Lcom/github/sundeepk/compactcalendarview/CompactCalendarView$CompactCalendarViewListener;", "Landroid/view/View$OnClickListener;", "Lcom/biz/health/cooey_app/vitals/SublimePickerFragment$Callback;", "()V", "blockedDateAdapter", "Lcom/biz/health/cooey_app/calendar_events/BlockedDatesAdapter;", "getBlockedDateAdapter", "()Lcom/biz/health/cooey_app/calendar_events/BlockedDatesAdapter;", "setBlockedDateAdapter", "(Lcom/biz/health/cooey_app/calendar_events/BlockedDatesAdapter;)V", "blockedDateRecylerView", "Landroid/support/v7/widget/RecyclerView;", "getBlockedDateRecylerView", "()Landroid/support/v7/widget/RecyclerView;", "setBlockedDateRecylerView", "(Landroid/support/v7/widget/RecyclerView;)V", "careTakerId", "", "getCareTakerId", "()Ljava/lang/String;", "setCareTakerId", "(Ljava/lang/String;)V", "endDate", "getEndDate", "setEndDate", "eventsList", "Lcom/github/sundeepk/compactcalendarview/domain/Event;", "getEventsList", "()Ljava/util/List;", "setEventsList", "(Ljava/util/List;)V", "freeSlotList", "Lcom/cooey/common/vo/FreeSlot;", "getFreeSlotList", "setFreeSlotList", "imgNextMonth", "Landroid/widget/ImageView;", "getImgNextMonth", "()Landroid/widget/ImageView;", "setImgNextMonth", "(Landroid/widget/ImageView;)V", "imgPrevMonth", "getImgPrevMonth", "setImgPrevMonth", "mCalendarView", "Lcom/github/sundeepk/compactcalendarview/CompactCalendarView;", "pbFreeSlots", "Landroid/widget/ProgressBar;", "getPbFreeSlots", "()Landroid/widget/ProgressBar;", "setPbFreeSlots", "(Landroid/widget/ProgressBar;)V", "progressDialog", "Landroid/app/ProgressDialog;", "getProgressDialog", "()Landroid/app/ProgressDialog;", "setProgressDialog", "(Landroid/app/ProgressDialog;)V", "selectedDayInMills", "", "getSelectedDayInMills", "()J", "setSelectedDayInMills", "(J)V", "startDate", "getStartDate", "setStartDate", "token", "getToken", "setToken", "toolBar", "Landroid/support/v7/widget/Toolbar;", "getToolBar", "()Landroid/support/v7/widget/Toolbar;", "setToolBar", "(Landroid/support/v7/widget/Toolbar;)V", "txtError", "Landroid/widget/TextView;", "getTxtError", "()Landroid/widget/TextView;", "setTxtError", "(Landroid/widget/TextView;)V", "txtFreeTimeSlots", "getTxtFreeTimeSlots", "setTxtFreeTimeSlots", "txtMonthName", "getTxtMonthName", "setTxtMonthName", "displayCalendarView", "", "calendar", "Ljava/util/Calendar;", "getCalendarEvent", "Lcom/biz/health/cooey_app/calendar_events/EventDate;", "activity", "getEventOfDay", "date", "Ljava/util/Date;", "getEvents", "getOptions", "Landroid/support/v4/util/Pair;", "", "Lcom/appeaser/sublimepickerlibrary/helpers/SublimeOptions;", "getOptions$app_release", "hideOrVisible", "boolean", "initValues", "onBackPressed", "onCancelled", "onClick", "p0", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDateTimeRecurrenceSet", "year", "", "monthOfYear", "dayOfMonth", "hourOfDay", "minute", "recurrenceOption", "Lcom/appeaser/sublimepickerlibrary/recurrencepicker/SublimeRecurrencePicker$RecurrenceOption;", "recurrenceRule", "onDayClick", "dateClicked", "onFailure", "call", "Lretrofit2/Call;", "t", "", "onMonthScroll", "firstDayOfNewMonth", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResponse", "response", "Lretrofit2/Response;", "processDate", "changeDate", "setUI", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: CaretakerCalendarActivity.kt */
public final class CaretakerCalendarActivity extends AppCompatActivity implements Callback<List<Activity>>, CompactCalendarViewListener, OnClickListener, SublimePickerFragment.Callback {
    private HashMap _$_findViewCache;
    @NotNull
    public BlockedDatesAdapter blockedDateAdapter;
    @NotNull
    public RecyclerView blockedDateRecylerView;
    @Nullable
    private String careTakerId;
    @Nullable
    private String endDate;
    @NotNull
    public List<Event> eventsList;
    @NotNull
    public List<FreeSlot> freeSlotList;
    @NotNull
    public ImageView imgNextMonth;
    @NotNull
    public ImageView imgPrevMonth;
    private CompactCalendarView mCalendarView;
    @NotNull
    public ProgressBar pbFreeSlots;
    @NotNull
    public ProgressDialog progressDialog;
    private long selectedDayInMills;
    @Nullable
    private String startDate;
    @NotNull
    public String token;
    @NotNull
    public Toolbar toolBar;
    @NotNull
    public TextView txtError;
    @NotNull
    public TextView txtFreeTimeSlots;
    @NotNull
    public TextView txtMonthName;

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
    public final String getCareTakerId() {
        return this.careTakerId;
    }

    public final void setCareTakerId(@Nullable String <set-?>) {
        this.careTakerId = <set-?>;
    }

    @Nullable
    public final String getStartDate() {
        return this.startDate;
    }

    public final void setStartDate(@Nullable String <set-?>) {
        this.startDate = <set-?>;
    }

    @Nullable
    public final String getEndDate() {
        return this.endDate;
    }

    public final void setEndDate(@Nullable String <set-?>) {
        this.endDate = <set-?>;
    }

    public final long getSelectedDayInMills() {
        return this.selectedDayInMills;
    }

    public final void setSelectedDayInMills(long <set-?>) {
        this.selectedDayInMills = <set-?>;
    }

    @NotNull
    public final TextView getTxtMonthName() {
        TextView textView = this.txtMonthName;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtMonthName");
        }
        return textView;
    }

    public final void setTxtMonthName(@NotNull TextView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.txtMonthName = <set-?>;
    }

    @NotNull
    public final ImageView getImgPrevMonth() {
        ImageView imageView = this.imgPrevMonth;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgPrevMonth");
        }
        return imageView;
    }

    public final void setImgPrevMonth(@NotNull ImageView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.imgPrevMonth = <set-?>;
    }

    @NotNull
    public final ImageView getImgNextMonth() {
        ImageView imageView = this.imgNextMonth;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgNextMonth");
        }
        return imageView;
    }

    public final void setImgNextMonth(@NotNull ImageView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.imgNextMonth = <set-?>;
    }

    @NotNull
    public final List<Event> getEventsList() {
        List<Event> list = this.eventsList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventsList");
        }
        return list;
    }

    public final void setEventsList(@NotNull List<Event> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.eventsList = <set-?>;
    }

    @NotNull
    public final Toolbar getToolBar() {
        Toolbar toolbar = this.toolBar;
        if (toolbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolBar");
        }
        return toolbar;
    }

    public final void setToolBar(@NotNull Toolbar <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.toolBar = <set-?>;
    }

    @NotNull
    public final ProgressDialog getProgressDialog() {
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        return progressDialog;
    }

    public final void setProgressDialog(@NotNull ProgressDialog <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.progressDialog = <set-?>;
    }

    @NotNull
    public final RecyclerView getBlockedDateRecylerView() {
        RecyclerView recyclerView = this.blockedDateRecylerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockedDateRecylerView");
        }
        return recyclerView;
    }

    public final void setBlockedDateRecylerView(@NotNull RecyclerView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.blockedDateRecylerView = <set-?>;
    }

    @NotNull
    public final String getToken() {
        String str = this.token;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("token");
        }
        return str;
    }

    public final void setToken(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.token = <set-?>;
    }

    @NotNull
    public final TextView getTxtError() {
        TextView textView = this.txtError;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtError");
        }
        return textView;
    }

    public final void setTxtError(@NotNull TextView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.txtError = <set-?>;
    }

    @NotNull
    public final ProgressBar getPbFreeSlots() {
        ProgressBar progressBar = this.pbFreeSlots;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pbFreeSlots");
        }
        return progressBar;
    }

    public final void setPbFreeSlots(@NotNull ProgressBar <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.pbFreeSlots = <set-?>;
    }

    @NotNull
    public final TextView getTxtFreeTimeSlots() {
        TextView textView = this.txtFreeTimeSlots;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtFreeTimeSlots");
        }
        return textView;
    }

    public final void setTxtFreeTimeSlots(@NotNull TextView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.txtFreeTimeSlots = <set-?>;
    }

    @NotNull
    public final BlockedDatesAdapter getBlockedDateAdapter() {
        BlockedDatesAdapter blockedDatesAdapter = this.blockedDateAdapter;
        if (blockedDatesAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockedDateAdapter");
        }
        return blockedDatesAdapter;
    }

    public final void setBlockedDateAdapter(@NotNull BlockedDatesAdapter <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.blockedDateAdapter = <set-?>;
    }

    @NotNull
    public final List<FreeSlot> getFreeSlotList() {
        List<FreeSlot> list = this.freeSlotList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("freeSlotList");
        }
        return list;
    }

    public final void setFreeSlotList(@NotNull List<FreeSlot> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.freeSlotList = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0644R.layout.activity_caretaker_calendar);
        this.careTakerId = getIntent().getStringExtra("caretakerId");
        setUI();
        initValues();
        processDate(Calendar.getInstance().getTime().getTime());
        getEvents();
        Date time = Calendar.getInstance().getTime();
        Intrinsics.checkExpressionValueIsNotNull(time, "Calendar.getInstance().time");
        getEventOfDay(time);
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public final void setUI() {
        this.mCalendarView = (CompactCalendarView) findViewById(C0644R.id.calendarView);
        View findViewById = findViewById(C0644R.id.txt_month_name);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.txt_month_name)");
        this.txtMonthName = (TextView) findViewById;
        findViewById = findViewById(C0644R.id.img_left_arrow);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.img_left_arrow)");
        this.imgPrevMonth = (ImageView) findViewById;
        findViewById = findViewById(C0644R.id.img_right_arrow);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.img_right_arrow)");
        this.imgNextMonth = (ImageView) findViewById;
        findViewById = findViewById(C0644R.id.toolbar);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.toolbar)");
        this.toolBar = (Toolbar) findViewById;
        findViewById = findViewById(C0644R.id.rev_free_slots);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.rev_free_slots)");
        this.blockedDateRecylerView = (RecyclerView) findViewById;
        findViewById = findViewById(C0644R.id.pb_free_slots);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.pb_free_slots)");
        this.pbFreeSlots = (ProgressBar) findViewById;
        findViewById = findViewById(C0644R.id.txt_no_schedules);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.txt_no_schedules)");
        this.txtError = (TextView) findViewById;
        TextView textView = this.txtError;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtError");
        }
        textView.setVisibility(8);
        findViewById = findViewById(C0644R.id.txt_free_time_slots);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.txt_free_time_slots)");
        this.txtFreeTimeSlots = (TextView) findViewById;
    }

    public final void initValues() {
        this.eventsList = new ArrayList();
        this.freeSlotList = new ArrayList();
        CompactCalendarView compactCalendarView = this.mCalendarView;
        if (compactCalendarView != null) {
            compactCalendarView.setUseThreeLetterAbbreviation(true);
        }
        compactCalendarView = this.mCalendarView;
        if (compactCalendarView != null) {
            compactCalendarView.setShouldDrawDaysHeader(true);
        }
        setSupportActionBar((Toolbar) _$_findCachedViewById(C0644R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
        supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM-yyyy", Locale.getDefault());
        TextView textView = this.txtMonthName;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtMonthName");
        }
        textView.setText(dateFormatter.format(Calendar.getInstance(Locale.getDefault()).getTime()));
        CompactCalendarView compactCalendarView2 = this.mCalendarView;
        if (compactCalendarView2 != null) {
            compactCalendarView2.setListener(this);
        }
        ImageView imageView = this.imgNextMonth;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgNextMonth");
        }
        imageView.setOnClickListener(this);
        imageView = this.imgPrevMonth;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgPrevMonth");
        }
        imageView.setOnClickListener(this);
        RecyclerView recyclerView = this.blockedDateRecylerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockedDateRecylerView");
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public final void getEvents() {
        this.progressDialog = new ProgressDialog(this);
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        progressDialog.setCanceledOnTouchOutside(false);
        ProgressDialog progressDialog2 = this.progressDialog;
        if (progressDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        progressDialog2.setMessage("Loading the events. Please wait ...");
        progressDialog = this.progressDialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        progressDialog.show();
        String id = new PreferenceStore().getSession(this).getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "PreferenceStore().getSession(this).id");
        this.token = id;
        List list = this.eventsList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventsList");
        }
        list.clear();
        if (this.careTakerId != null) {
            Boolean valueOf;
            id = this.careTakerId;
            if (id != null) {
                boolean z;
                if (id.length() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                valueOf = Boolean.valueOf(z);
            } else {
                valueOf = null;
            }
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (!valueOf.booleanValue()) {
                ActivitiesService activitiesService = new ApiClient(this, "http://api.cooey.co.in/ehealth/").getActivitiesService();
                String str = this.token;
                if (str == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("token");
                }
                activitiesService.getCalendarEvents(str, this.careTakerId, this.startDate, this.endDate).enqueue(this);
                return;
            }
        }
        Toast.makeText(this, "Caretaker is not valid", 1).show();
    }

    private final void processDate(long changeDate) {
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(changeDate);
        start.set(5, start.getActualMinimum(5));
        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(changeDate);
        end.set(5, start.getActualMaximum(5));
        this.startDate = String.valueOf(start.getTimeInMillis());
        this.endDate = String.valueOf(end.getTimeInMillis());
    }

    public void onFailure(@Nullable Call<List<Activity>> call, @Nullable Throwable t) {
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        progressDialog.dismiss();
        if (t != null) {
            t.printStackTrace();
        }
    }

    public void onResponse(@Nullable Call<List<Activity>> call, @Nullable Response<List<Activity>> response) {
        ProgressDialog progressDialog;
        if (response != null) {
            List list = (List) response.body();
            Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (valueOf.intValue() > 0) {
                Object body = response.body();
                if (body == null) {
                    Intrinsics.throwNpe();
                }
                for (Activity acivity : (List) body) {
                    if (acivity.getStart() != null) {
                        if ((((CharSequence) acivity.getStart()).length() == 0 ? 1 : null) == null) {
                            list = this.eventsList;
                            if (list == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("eventsList");
                            }
                            list.add(getCalendarEvent(Long.parseLong(acivity.getStart()), acivity));
                        }
                    }
                }
                CompactCalendarView compactCalendarView = this.mCalendarView;
                if (compactCalendarView != null) {
                    List list2 = this.eventsList;
                    if (list2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("eventsList");
                    }
                    compactCalendarView.addEvents(list2);
                }
                progressDialog = this.progressDialog;
                if (progressDialog == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
                }
                progressDialog.dismiss();
            }
        }
        progressDialog = this.progressDialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        progressDialog.dismiss();
    }

    @NotNull
    public final EventDate getCalendarEvent(long startDate, @NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        Calendar.getInstance(Locale.getDefault()).setTime(new Date(startDate));
        EventDate eventDate = new EventDate(C0644R.color.black, startDate, activity);
        eventDate.setEndTime(Long.parseLong(activity.getEnd()));
        return eventDate;
    }

    public final void getEventOfDay(@NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        ActivitiesService activitiesService = new ApiClient(this, "http://api.cooey.co.in/ehealth/").getActivitiesService();
        String str = this.token;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("token");
        }
        activitiesService.freeSlotsOfCaretaker(str, this.careTakerId, GuardianUtility.getStartOfDay(date), GuardianUtility.getEndOfDay(date)).enqueue(new CaretakerCalendarActivity$getEventOfDay$1(this));
    }

    public void onDayClick(@Nullable Date dateClicked) {
        ProgressBar progressBar = this.pbFreeSlots;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pbFreeSlots");
        }
        progressBar.setVisibility(0);
        Long valueOf = dateClicked != null ? Long.valueOf(dateClicked.getTime()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        this.selectedDayInMills = valueOf.longValue();
        List list = this.freeSlotList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("freeSlotList");
        }
        list.clear();
        getEventOfDay(dateClicked);
    }

    public final void hideOrVisible(boolean boolean_) {
        RecyclerView recyclerView;
        TextView textView;
        if (boolean_) {
            recyclerView = this.blockedDateRecylerView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("blockedDateRecylerView");
            }
            if (recyclerView.getVisibility() == 8) {
                recyclerView = this.blockedDateRecylerView;
                if (recyclerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("blockedDateRecylerView");
                }
                recyclerView.setVisibility(0);
            }
            textView = this.txtFreeTimeSlots;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("txtFreeTimeSlots");
            }
            if (textView.getVisibility() == 8) {
                textView = this.txtFreeTimeSlots;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("txtFreeTimeSlots");
                }
                textView.setVisibility(0);
            }
            textView = this.txtError;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("txtError");
            }
            if (textView.getVisibility() == 0) {
                textView = this.txtError;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("txtError");
                }
                textView.setVisibility(8);
                return;
            }
            return;
        }
        recyclerView = this.blockedDateRecylerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockedDateRecylerView");
        }
        if (recyclerView.getVisibility() == 0) {
            recyclerView = this.blockedDateRecylerView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("blockedDateRecylerView");
            }
            recyclerView.setVisibility(8);
        }
        textView = this.txtFreeTimeSlots;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtFreeTimeSlots");
        }
        if (textView.getVisibility() == 0) {
            textView = this.txtFreeTimeSlots;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("txtFreeTimeSlots");
            }
            textView.setVisibility(8);
        }
        textView = this.txtError;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtError");
        }
        if (textView.getVisibility() == 8) {
            textView = this.txtError;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("txtError");
            }
            textView.setVisibility(0);
        }
    }

    public void onMonthScroll(@Nullable Date firstDayOfNewMonth) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM-yyyy", Locale.getDefault());
        TextView textView = this.txtMonthName;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtMonthName");
        }
        textView.setText(dateFormatter.format(firstDayOfNewMonth));
        Long valueOf = firstDayOfNewMonth != null ? Long.valueOf(firstDayOfNewMonth.getTime()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        processDate(valueOf.longValue());
        List list = this.freeSlotList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("freeSlotList");
        }
        list.clear();
        BlockedDatesAdapter blockedDatesAdapter = this.blockedDateAdapter;
        if (blockedDatesAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockedDateAdapter");
        }
        blockedDatesAdapter.notifyDataSetChanged();
        getEvents();
    }

    public void onClick(@Nullable View p0) {
        Integer valueOf = p0 != null ? Integer.valueOf(p0.getId()) : null;
        CompactCalendarView compactCalendarView;
        if (valueOf != null && valueOf.intValue() == C0644R.id.img_left_arrow) {
            compactCalendarView = this.mCalendarView;
            if (compactCalendarView != null) {
                compactCalendarView.showPreviousMonth();
            }
        } else if (valueOf != null && valueOf.intValue() == C0644R.id.img_right_arrow) {
            compactCalendarView = this.mCalendarView;
            if (compactCalendarView != null) {
                compactCalendarView.showNextMonth();
            }
        }
    }

    public final void displayCalendarView(@NotNull Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(this);
        Pair optionsPair = getOptions$app_release(calendar);
        if (((Boolean) optionsPair.first).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("SUBLIME_OPTIONS", (Parcelable) optionsPair.second);
            pickerFrag.setArguments(bundle);
            pickerFrag.setStyle(1, 0);
            if (this == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
            }
            pickerFrag.show(getSupportFragmentManager(), "SUBLIME_PICKER");
            return;
        }
        Toast.makeText(this, "No pickers activated", 0).show();
    }

    @NotNull
    public final Pair<Boolean, SublimeOptions> getOptions$app_release(@NotNull Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        SublimeOptions options = new SublimeOptions();
        int displayOptions = (0 | 1) | 2;
        options.setDisplayOptions(displayOptions);
        options.setPickerToShow(Picker.TIME_PICKER);
        options.setTimeParams(calendar.get(11), calendar.get(12), true);
        return new Pair(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }

    public void onCancelled() {
    }

    @Nullable
    public Pair<Boolean, SublimeOptions> onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, @Nullable RecurrenceOption recurrenceOption, @Nullable String recurrenceRule) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance(Locale.getDefault())");
        calendar.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
        Intent intent = new Intent();
        intent.putExtra("timeInMills", calendar.getTime().getTime());
        setResult(-1, intent);
        finish();
        return null;
    }

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, RescheduleEventActivity.class));
        finish();
    }
}
