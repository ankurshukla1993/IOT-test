package android.databinding;

import android.view.View;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.databinding.ActionItemWidgetBinding;
import com.biz.health.cooey_app.databinding.ActivityLoginMainBinding;
import com.biz.health.cooey_app.databinding.ActivityMainBinding;
import com.biz.health.cooey_app.databinding.ActivitySummaryBinding;
import com.biz.health.cooey_app.databinding.CareplanWidgetBinding;
import com.biz.health.cooey_app.databinding.FragmentGraphBinding;
import com.biz.health.cooey_app.databinding.FragmentHistoryBinding;
import com.biz.health.cooey_app.databinding.FragmentLoginGuardianBinding;
import com.biz.health.cooey_app.databinding.FragmentLoginPageOneBinding;
import com.biz.health.cooey_app.databinding.FragmentLoginPageThreeBinding;
import com.biz.health.cooey_app.databinding.FragmentLoginPageTwoBinding;
import com.biz.health.cooey_app.databinding.FragmentWidgetBinding;
import com.biz.health.cooey_app.databinding.ItemLayoutNotesBinding;
import com.biz.health.cooey_app.databinding.ItemLayoutSummaryInfoBinding;
import com.biz.health.cooey_app.databinding.ItemLayoutVitalsBinding;
import com.biz.health.cooey_app.databinding.ItemLayoutVitalsGraphBinding;
import com.biz.health.cooey_app.databinding.ItemSummaryMedicinesBinding;
import com.biz.health.cooey_app.databinding.SummaryWidgetBinding;
import com.biz.health.cooey_app.databinding.TipWidgetBinding;
import com.biz.health.cooey_app.databinding.VitalsDashboardWidgetBinding;
import com.cooey.android.medical_reports.databinding.ActivityMedicalReportBinding;
import com.cooey.android.medical_reports.databinding.LayoutMedicalReportItemViewBinding;
import com.cooey.android.users.databinding.LayoutPeopleItemViewBinding;
import com.cooey.android.users.databinding.LayoutPeopleSelectorViewBinding;
import com.cooey.android.video_call.databinding.ActivityCallRingBinding;
import com.cooey.common.databinding.LayoutMayaInputBinding;
import com.cooey.common.databinding.LayoutMessageBinding;
import com.cooey.common.databinding.LayoutVitalIndividualViewBinding;
import com.cooey.common.databinding.LayoutVitalViewBinding;
import com.cooey.devices.databinding.ActivityBodyAnalyzerDeviceBinding;
import com.cooey.devices.databinding.ActivityBodyAnalyzerDeviceInputBinding;
import com.cooey.devices.databinding.ActivityBpdeviceBinding;
import com.cooey.devices.databinding.ActivityBpdeviceInputActvityBinding;
import com.cooey.devices.databinding.ActivityDeviceSelectBinding;
import com.cooey.devices.databinding.ActivityDevicesBinding;
import com.cooey.devices.databinding.DeviceItemBinding;
import com.cooey.devices.databinding.FragmentBloodPressureValueBinding;
import com.cooey.devices.databinding.FragmentDeviceHelpBinding;
import com.google.android.gms.measurement.AppMeasurement$Param;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.ihealth.communication.control.AmProfile;

class DataBinderMapper {
    static final int TARGET_MIN_SDK = 18;

    private static class InnerBrLookup {
        static String[] sKeys = new String[]{"_all", AmProfile.GET_USER_AGE_AM, "battery", "bloodGlucoseText", "bodyAnalyzerDeviceViewModel", "bpDeviceInputViewModel", "bpDeviceViewModel", "bpText", "callRingViewModel", "careplanWidgetModel", "dashboardViewModel", "deviceHelpViewModel", "deviceSelectViewModel", "email", "graphsViewModel", "historyViewModel", "historyViewPagerAdapter", "inProgress", Param.LOCATION, "loginActivityViewModel", "loginPageOneModel", "loginPageThreeModel", "loginPageTwoModel", "loginViewModel", "loginViewPagerAdapter", "mayaInputViewModel", "mayaRecyclerAdapter", "medicalReport", "medicalReportActivityViewModel", "medicalReportItemViewModel", "medicalReportRecyclerViewAdapter", "medicinesInfoViewModel", "message", "messageViewModel", "name", "parameters", "password", "peopleItemViewModel", "peopleSelectorViewModel", "progess", "recyclerViewAdapter", "summaryActivityViewModel", "summaryInfoViewModel", "summaryPatientNotesViewModel", "summaryRecylerAdapter", "summaryViewModel", "summaryVitalsInfoViewModel", "temperature", "timeString", AppMeasurement$Param.TIMESTAMP, "tipWdigetModel", "user", "users", "usersDataProgress", "viewPagerAdapter", "vitalList", "widgetsViewModel"};

        private InnerBrLookup() {
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent bindingComponent, View view, int layoutId) {
        switch (layoutId) {
            case C0644R.layout.action_item_widget:
                return ActionItemWidgetBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_body_analyzer_device:
                return ActivityBodyAnalyzerDeviceBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_body_analyzer_device_input:
                return ActivityBodyAnalyzerDeviceInputBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_bpdevice:
                return ActivityBpdeviceBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_bpdevice_input_actvity:
                return ActivityBpdeviceInputActvityBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_call_ring:
                return ActivityCallRingBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_device_select:
                return ActivityDeviceSelectBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_devices:
                return ActivityDevicesBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_login_main:
                return ActivityLoginMainBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_main:
                return ActivityMainBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_medical_report:
                return ActivityMedicalReportBinding.bind(view, bindingComponent);
            case C0644R.layout.activity_summary:
                return ActivitySummaryBinding.bind(view, bindingComponent);
            case C0644R.layout.careplan_widget:
                return CareplanWidgetBinding.bind(view, bindingComponent);
            case C0644R.layout.device_item:
                return DeviceItemBinding.bind(view, bindingComponent);
            case C0644R.layout.fragment_blood_pressure_value:
                return FragmentBloodPressureValueBinding.bind(view, bindingComponent);
            case C0644R.layout.fragment_device_help:
                return FragmentDeviceHelpBinding.bind(view, bindingComponent);
            case C0644R.layout.fragment_graph:
                return FragmentGraphBinding.bind(view, bindingComponent);
            case C0644R.layout.fragment_history:
                return FragmentHistoryBinding.bind(view, bindingComponent);
            case C0644R.layout.fragment_login_guardian:
                return FragmentLoginGuardianBinding.bind(view, bindingComponent);
            case C0644R.layout.fragment_login_page_one:
                return FragmentLoginPageOneBinding.bind(view, bindingComponent);
            case C0644R.layout.fragment_login_page_three:
                return FragmentLoginPageThreeBinding.bind(view, bindingComponent);
            case C0644R.layout.fragment_login_page_two:
                return FragmentLoginPageTwoBinding.bind(view, bindingComponent);
            case C0644R.layout.fragment_widget:
                return FragmentWidgetBinding.bind(view, bindingComponent);
            case C0644R.layout.item_layout_notes:
                return ItemLayoutNotesBinding.bind(view, bindingComponent);
            case C0644R.layout.item_layout_summary_info:
                return ItemLayoutSummaryInfoBinding.bind(view, bindingComponent);
            case C0644R.layout.item_layout_vitals:
                return ItemLayoutVitalsBinding.bind(view, bindingComponent);
            case C0644R.layout.item_layout_vitals_graph:
                return ItemLayoutVitalsGraphBinding.bind(view, bindingComponent);
            case C0644R.layout.item_summary_medicines:
                return ItemSummaryMedicinesBinding.bind(view, bindingComponent);
            case C0644R.layout.layout_maya_input:
                return LayoutMayaInputBinding.bind(view, bindingComponent);
            case C0644R.layout.layout_medical_report_item_view:
                return LayoutMedicalReportItemViewBinding.bind(view, bindingComponent);
            case C0644R.layout.layout_message:
                return LayoutMessageBinding.bind(view, bindingComponent);
            case C0644R.layout.layout_people_item_view:
                return LayoutPeopleItemViewBinding.bind(view, bindingComponent);
            case C0644R.layout.layout_people_selector_view:
                return LayoutPeopleSelectorViewBinding.bind(view, bindingComponent);
            case C0644R.layout.layout_vital_individual_view:
                return LayoutVitalIndividualViewBinding.bind(view, bindingComponent);
            case C0644R.layout.layout_vital_view:
                return LayoutVitalViewBinding.bind(view, bindingComponent);
            case C0644R.layout.summary_widget:
                return SummaryWidgetBinding.bind(view, bindingComponent);
            case C0644R.layout.tip_widget:
                return TipWidgetBinding.bind(view, bindingComponent);
            case C0644R.layout.vitals_dashboard_widget:
                return VitalsDashboardWidgetBinding.bind(view, bindingComponent);
            default:
                return null;
        }
    }

    ViewDataBinding getDataBinder(DataBindingComponent bindingComponent, View[] views, int layoutId) {
        return null;
    }

    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        switch (tag.hashCode()) {
            case -2114781474:
                if (tag.equals("layout/tip_widget_0")) {
                    return C0644R.layout.tip_widget;
                }
                return 0;
            case -2071302680:
                if (tag.equals("layout/device_item_0")) {
                    return C0644R.layout.device_item;
                }
                return 0;
            case -2045391329:
                if (tag.equals("layout/fragment_login_page_three_0")) {
                    return C0644R.layout.fragment_login_page_three;
                }
                return 0;
            case -1952572512:
                if (tag.equals("layout/activity_bpdevice_0")) {
                    return C0644R.layout.activity_bpdevice;
                }
                return 0;
            case -1615934923:
                if (tag.equals("layout/layout_medical_report_item_view_0")) {
                    return C0644R.layout.layout_medical_report_item_view;
                }
                return 0;
            case -1408939092:
                if (tag.equals("layout/activity_summary_0")) {
                    return C0644R.layout.activity_summary;
                }
                return 0;
            case -1206093511:
                if (tag.equals("layout/layout_vital_view_0")) {
                    return C0644R.layout.layout_vital_view;
                }
                return 0;
            case -1048433963:
                if (tag.equals("layout/fragment_graph_0")) {
                    return C0644R.layout.fragment_graph;
                }
                return 0;
            case -1017813301:
                if (tag.equals("layout/activity_login_main_0")) {
                    return C0644R.layout.activity_login_main;
                }
                return 0;
            case -942494046:
                if (tag.equals("layout/layout_people_item_view_0")) {
                    return C0644R.layout.layout_people_item_view;
                }
                return 0;
            case -183733005:
                if (tag.equals("layout/vitals_dashboard_widget_0")) {
                    return C0644R.layout.vitals_dashboard_widget;
                }
                return 0;
            case -183685257:
                if (tag.equals("layout/activity_call_ring_0")) {
                    return C0644R.layout.activity_call_ring;
                }
                return 0;
            case -62786247:
                if (tag.equals("layout/activity_body_analyzer_device_input_0")) {
                    return C0644R.layout.activity_body_analyzer_device_input;
                }
                return 0;
            case -6579993:
                if (tag.equals("layout/fragment_login_page_one_0")) {
                    return C0644R.layout.fragment_login_page_one;
                }
                return 0;
            case -1684659:
                if (tag.equals("layout/fragment_login_page_two_0")) {
                    return C0644R.layout.fragment_login_page_two;
                }
                return 0;
            case 46443498:
                if (tag.equals("layout/item_summary_medicines_0")) {
                    return C0644R.layout.item_summary_medicines;
                }
                return 0;
            case 118416110:
                if (tag.equals("layout/activity_body_analyzer_device_0")) {
                    return C0644R.layout.activity_body_analyzer_device;
                }
                return 0;
            case 152889779:
                if (tag.equals("layout/summary_widget_0")) {
                    return C0644R.layout.summary_widget;
                }
                return 0;
            case 194508469:
                if (tag.equals("layout/item_layout_vitals_graph_0")) {
                    return C0644R.layout.item_layout_vitals_graph;
                }
                return 0;
            case 205681017:
                if (tag.equals("layout/layout_vital_individual_view_0")) {
                    return C0644R.layout.layout_vital_individual_view;
                }
                return 0;
            case 345283125:
                if (tag.equals("layout/careplan_widget_0")) {
                    return C0644R.layout.careplan_widget;
                }
                return 0;
            case 358389343:
                if (tag.equals("layout/fragment_widget_0")) {
                    return C0644R.layout.fragment_widget;
                }
                return 0;
            case 376215323:
                if (tag.equals("layout/fragment_history_0")) {
                    return C0644R.layout.fragment_history;
                }
                return 0;
            case 379361886:
                if (tag.equals("layout/activity_medical_report_0")) {
                    return C0644R.layout.activity_medical_report;
                }
                return 0;
            case 423753077:
                if (tag.equals("layout/activity_main_0")) {
                    return C0644R.layout.activity_main;
                }
                return 0;
            case 442009686:
                if (tag.equals("layout/layout_people_selector_view_0")) {
                    return C0644R.layout.layout_people_selector_view;
                }
                return 0;
            case 479678246:
                if (tag.equals("layout/item_layout_summary_info_0")) {
                    return C0644R.layout.item_layout_summary_info;
                }
                return 0;
            case 837542289:
                if (tag.equals("layout/fragment_device_help_0")) {
                    return C0644R.layout.fragment_device_help;
                }
                return 0;
            case 919878539:
                if (tag.equals("layout/activity_device_select_0")) {
                    return C0644R.layout.activity_device_select;
                }
                return 0;
            case 952627048:
                if (tag.equals("layout/layout_message_0")) {
                    return C0644R.layout.layout_message;
                }
                return 0;
            case 1011527268:
                if (tag.equals("layout/item_layout_notes_0")) {
                    return C0644R.layout.item_layout_notes;
                }
                return 0;
            case 1123168285:
                if (tag.equals("layout/action_item_widget_0")) {
                    return C0644R.layout.action_item_widget;
                }
                return 0;
            case 1190602498:
                if (tag.equals("layout/fragment_login_guardian_0")) {
                    return C0644R.layout.fragment_login_guardian;
                }
                return 0;
            case 1316218342:
                if (tag.equals("layout/item_layout_vitals_0")) {
                    return C0644R.layout.item_layout_vitals;
                }
                return 0;
            case 1316580328:
                if (tag.equals("layout/layout_maya_input_0")) {
                    return C0644R.layout.layout_maya_input;
                }
                return 0;
            case 1386589303:
                if (tag.equals("layout/fragment_blood_pressure_value_0")) {
                    return C0644R.layout.fragment_blood_pressure_value;
                }
                return 0;
            case 1397415715:
                if (tag.equals("layout/activity_devices_0")) {
                    return C0644R.layout.activity_devices;
                }
                return 0;
            case 1504789110:
                if (tag.equals("layout/activity_bpdevice_input_actvity_0")) {
                    return C0644R.layout.activity_bpdevice_input_actvity;
                }
                return 0;
            default:
                return 0;
        }
    }

    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
}
