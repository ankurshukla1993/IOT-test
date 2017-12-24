package com.cooey.android.users.old.careplan;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.cooey.android.users.C0757R;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.ActionItem;
import com.cooey.common.vo.ActionItemPatient;
import com.cooey.common.vo.ActionItemPatientHeader;
import com.cooey.common.vo.ActionItemTimeHeader;
import com.cooey.common.vo.ListItem;
import com.cooey.common.vo.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CareplanDashBoardFragment extends Fragment {
    static final int LOCATION_SETTINGS_REQUEST = 1;
    ActionItemAdapter actionItemAdapter;
    List<ActionItem> actionItemList;
    AlertDialog alertDialog1;
    Button btnRetry;
    int checkedItem = 0;
    LinearLayout errorLayout;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    public String serverUrl;
    public String token;
    TextView txtNoActionItems;
    public User user;
    public String userId;
    CharSequence[] values = new CharSequence[]{"Patients", "Time"};

    class C07801 implements OnClickListener {
        C07801() {
        }

        public void onClick(DialogInterface dialog, int item) {
            switch (item) {
                case 0:
                    CareplanDashBoardFragment.this.changeAdapter("Patients");
                    CareplanDashBoardFragment.this.saveSelectedFilterAction(0);
                    break;
                case 1:
                    CareplanDashBoardFragment.this.changeAdapter(CareplanDashBoardFragment.this.getString(C0757R.string.time));
                    CareplanDashBoardFragment.this.saveSelectedFilterAction(1);
                    break;
            }
            CareplanDashBoardFragment.this.alertDialog1.dismiss();
        }
    }

    class C07822 implements Callback<List<ActionItem>> {

        class C07811 implements View.OnClickListener {
            C07811() {
            }

            public void onClick(View v) {
                CareplanDashBoardFragment.this.errorLayout.setVisibility(4);
                CareplanDashBoardFragment.this.getActionItems();
            }
        }

        C07822() {
        }

        public void onResponse(Call<List<ActionItem>> call, Response<List<ActionItem>> response) {
            if (response.body() != null && ((List) response.body()).size() > 0) {
                CareplanDashBoardFragment.this.actionItemList = (List) response.body();
                CareplanDashBoardFragment.this.recyclerView.setLayoutManager(new LinearLayoutManager(CareplanDashBoardFragment.this.getContext()));
                CareplanDashBoardFragment.this.actionItemAdapter = new ActionItemAdapter(CareplanDashBoardFragment.this.sortedActionItemsOnPatients(CareplanDashBoardFragment.this.actionItemList, true), CareplanDashBoardFragment.this.getContext(), CareplanDashBoardFragment.this.user, CareplanDashBoardFragment.this.serverUrl);
                CareplanDashBoardFragment.this.recyclerView.setAdapter(CareplanDashBoardFragment.this.actionItemAdapter);
            } else if (CareplanDashBoardFragment.this.txtNoActionItems.getVisibility() == 4) {
                CareplanDashBoardFragment.this.txtNoActionItems.setVisibility(0);
                CareplanDashBoardFragment.this.txtNoActionItems.setText("No Action items for the day");
            }
            CareplanDashBoardFragment.this.hideDialog();
        }

        public void onFailure(Call<List<ActionItem>> call, Throwable t) {
            t.printStackTrace();
            CareplanDashBoardFragment.this.hideDialog();
            if (CareplanDashBoardFragment.this.errorLayout.getVisibility() == 4) {
                CareplanDashBoardFragment.this.errorLayout.setVisibility(0);
                CareplanDashBoardFragment.this.btnRetry.setOnClickListener(new C07811());
            }
        }
    }

    class C07833 implements Comparator<ActionItem> {
        C07833() {
        }

        public int compare(ActionItem m1, ActionItem m2) {
            return new Date(m1.getScheduledOn()).compareTo(new Date(m2.getScheduledOn()));
        }
    }

    class C07844 implements Comparator<ActionItem> {
        C07844() {
        }

        public int compare(ActionItem m1, ActionItem m2) {
            return new Date(m1.getScheduledOn()).compareTo(new Date(m2.getScheduledOn()));
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(C0757R.layout.fragment_careplan_dashboard, container, false);
        ButterKnife.bind(this, view);
        this.recyclerView = (RecyclerView) view.findViewById(C0757R.id.rev_action_items);
        this.errorLayout = (LinearLayout) view.findViewById(C0757R.id.error_layout);
        this.btnRetry = (Button) view.findViewById(C0757R.id.error_btn_retry);
        this.progressBar = (ProgressBar) view.findViewById(C0757R.id.pb_careplan_list);
        this.txtNoActionItems = (TextView) view.findViewById(C0757R.id.txt_no_action_items);
        getActionItems();
        return view;
    }

    public void createAlertDialogWithRadioButtonGroup() {
        Builder builder = new Builder(getActivity());
        builder.setTitle("Filter by");
        this.checkedItem = loadSelectedFilterAction();
        builder.setSingleChoiceItems(this.values, this.checkedItem, new C07801());
        this.alertDialog1 = builder.create();
        this.alertDialog1.show();
    }

    private void changeAdapter(String timeOrPatient) {
        if (this.actionItemList != null && this.actionItemList.size() > 0) {
            List<ListItem> sortedListTem;
            if (timeOrPatient.equalsIgnoreCase("time")) {
                sortedListTem = sortedActions();
            } else {
                sortedListTem = sortedActionItemsOnPatients(this.actionItemList, true);
            }
            this.actionItemAdapter.filterActionItems(sortedListTem);
        }
    }

    private void getActionItems() {
        showDialog();
        new ApiClient(getContext(), this.serverUrl).getActionItemService().getActionItemsForPatients(this.token, this.userId, getStartOfDay(Calendar.getInstance().getTime()), getEndOfDay(Calendar.getInstance().getTime())).enqueue(new C07822());
    }

    public void saveSelectedFilterAction(int value) {
    }

    public int loadSelectedFilterAction() {
        return 0;
    }

    private List<ListItem> sortedActions() {
        List<ListItem> listItemList = new ArrayList();
        Collections.sort(this.actionItemList, new C07833());
        if (this.actionItemList != null && this.actionItemList.size() > 0) {
            Date startDate = toNearestWholeHour(new Date(((ActionItem) this.actionItemList.get(0)).getScheduledOn()));
            Date endDate = toNextNearestWholeHour(new Date(((ActionItem) this.actionItemList.get(this.actionItemList.size() - 1)).getScheduledOn()));
            Map<Long, List<ActionItem>> longListMap = new HashMap();
            for (long dif = startDate.getTime(); dif < endDate.getTime(); dif += 3600000) {
                longListMap.put(Long.valueOf(toNearestWholeHour(new Date(dif)).getTime()), new ArrayList());
            }
            for (ActionItem actionItem : this.actionItemList) {
                long key = toNearestWholeHour(new Date(actionItem.getScheduledOn())).getTime();
                if (longListMap.containsKey(Long.valueOf(key))) {
                    ((List) longListMap.get(Long.valueOf(key))).add(actionItem);
                }
            }
            for (Entry<Long, List<ActionItem>> entry : new TreeMap(longListMap).entrySet()) {
                if (entry.getValue() != null && ((List) entry.getValue()).size() > 0) {
                    listItemList.add(new ActionItemTimeHeader("" + ((Long) entry.getKey())));
                    listItemList.addAll(sortedActionItemsOnPatients((List) entry.getValue(), false));
                }
            }
        }
        return listItemList;
    }

    private List<ListItem> sortedActionItemsOnPatients(List<ActionItem> actionItemList, boolean isByPatient) {
        List<ListItem> listItemList = new ArrayList();
        if (actionItemList != null && actionItemList.size() > 0) {
            Collections.sort(actionItemList, new C07844());
            Set<String> uniquePatientId = new HashSet();
            if (actionItemList.size() > 0) {
                String patientId;
                for (ActionItem actionItem : actionItemList) {
                    uniquePatientId.add(actionItem.getPatientId());
                }
                Map<String, List<ActionItem>> longListMap = new HashMap();
                for (String patientId2 : uniquePatientId) {
                    longListMap.put(patientId2, new ArrayList());
                }
                for (ActionItem actionItem2 : actionItemList) {
                    patientId2 = actionItem2.getPatientId();
                    if (longListMap.containsKey(patientId2)) {
                        ((List) longListMap.get(patientId2)).add(actionItem2);
                    }
                }
                for (Entry<String, List<ActionItem>> entry : longListMap.entrySet()) {
                    if (entry.getValue() != null && ((List) entry.getValue()).size() > 0) {
                        if (isByPatient) {
                            listItemList.add(new ActionItemPatientHeader((String) entry.getKey()));
                            listItemList.addAll((Collection) entry.getValue());
                        } else {
                            listItemList.add(new ActionItemPatient((String) entry.getKey()));
                            listItemList.addAll((Collection) entry.getValue());
                        }
                    }
                }
            }
        }
        return listItemList;
    }

    static Date toNearestWholeHour(Date d) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime();
    }

    static Date toNextNearestWholeHour(Date d) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        c.set(12, 59);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime();
    }

    public static long getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime().getTime();
    }

    public static long getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }

    public void showDialog() {
        if (this.progressBar.getVisibility() == 4) {
            this.progressBar.setVisibility(0);
        }
    }

    public void hideDialog() {
        if (this.progressBar.getVisibility() == 0) {
            this.progressBar.setVisibility(4);
        }
    }
}
