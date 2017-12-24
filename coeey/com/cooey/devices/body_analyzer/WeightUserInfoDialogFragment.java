package com.cooey.devices.body_analyzer;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import com.cooey.devices.C0910R;
import com.lifesense.ble.bean.SexType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WeightUserInfoDialogFragment extends DialogFragment implements OnItemSelectedListener, OnClickListener {
    private static int age;
    static TextInputEditText txtDateOfBirth;
    static TextInputEditText txtHeight;
    AppCompatSpinner appCompatSpinnerGender;
    ArrayAdapter<String> genderAdapter;
    private float height;
    private SexType sexType;
    private UserInfoDialogListner userInfoDialogListner;

    class C09241 implements OnClickListener {
        C09241() {
        }

        public void onClick(View v) {
            WeightUserInfoDialogFragment.this.buildCloseDialog(WeightUserInfoDialogFragment.this.getActivity(), "Are you sure you want to exit");
        }
    }

    class C09252 implements OnMenuItemClickListener {
        C09252() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == C0910R.id.save && WeightUserInfoDialogFragment.this.validFields()) {
                WeightUserInfoDialogFragment.this.saveUserDetails(WeightUserInfoDialogFragment.age, WeightUserInfoDialogFragment.this.height, WeightUserInfoDialogFragment.this.sexType);
            }
            return true;
        }
    }

    class C09263 implements OnClickListener {
        C09263() {
        }

        public void onClick(View v) {
            WeightUserInfoDialogFragment.this.dismiss();
        }
    }

    static class C09274 implements DialogInterface.OnClickListener {
        C09274() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    class C09285 implements DialogInterface.OnClickListener {
        C09285() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            if (WeightUserInfoDialogFragment.this.isVisible()) {
                WeightUserInfoDialogFragment.this.dismiss();
            }
        }
    }

    class C09296 implements DialogInterface.OnClickListener {
        C09296() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    public static class DatePickerFragment extends DialogFragment implements OnDateSetListener {
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            return new DatePickerDialog(getActivity(), this, c.get(1), c.get(2), c.get(5));
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(1, year);
            c.set(2, month);
            c.set(5, day);
            WeightUserInfoDialogFragment.txtDateOfBirth.setText(WeightUserInfoDialogFragment.getStrinFromDate(c.getTime()));
            WeightUserInfoDialogFragment.age = WeightUserInfoDialogFragment.getAgeFromDOB(WeightUserInfoDialogFragment.getStrinFromDate(c.getTime()));
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public WeightUserInfoDialogFragment newInstance() {
        WeightUserInfoDialogFragment weightUserInfoDialogFragment = new WeightUserInfoDialogFragment();
        weightUserInfoDialogFragment.setStyle(0, C0910R.style.FullScreenDialogStyle);
        return weightUserInfoDialogFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.userInfoDialogListner = (UserInfoDialogListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement EditNameDialogListener");
        }
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(C0910R.layout.fragment_weight_user_info, container, false);
        initToolBar(view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getDialog() != null) {
            getDialog().getWindow().setLayout(-1, -1);
        }
    }

    private void initToolBar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(C0910R.id.toolbar);
        txtHeight = (TextInputEditText) view.findViewById(C0910R.id.txt_height);
        txtDateOfBirth = (TextInputEditText) view.findViewById(C0910R.id.txt_dob);
        this.appCompatSpinnerGender = (AppCompatSpinner) view.findViewById(C0910R.id.spin_gender);
        this.genderAdapter = new ArrayAdapter(getActivity(), 17367043, getResources().getStringArray(C0910R.array.genders));
        this.appCompatSpinnerGender.setAdapter(this.genderAdapter);
        this.appCompatSpinnerGender.setOnItemSelectedListener(this);
        this.appCompatSpinnerGender.setPrompt("Select gender");
        txtDateOfBirth.setOnClickListener(this);
        toolbar.inflateMenu(C0910R.menu.menu_save);
        toolbar.setNavigationIcon(C0910R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new C09241());
        toolbar.setOnMenuItemClickListener(new C09252());
        toolbar.setNavigationOnClickListener(new C09263());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                buildCloseDialog(getActivity(), "Are you sure you want to exit");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validFields() {
        if (age == 0) {
            buildErrorDialog(getActivity(), "Enter age");
            return false;
        }
        this.height = Float.parseFloat(txtHeight.getText().toString());
        if (this.height == 0.0f) {
            buildErrorDialog(getActivity(), "Enter Height");
            return false;
        }
        try {
            if (this.sexType.equals(SexType.FEMALE) || this.sexType.equals(SexType.MALE)) {
                return true;
            }
            return true;
        } catch (Exception e) {
            buildErrorDialog(getActivity(), "Select gender");
            return false;
        }
    }

    private void saveUserDetails(int age, float height, SexType sexType) {
        this.userInfoDialogListner.saveUserInfo(age, height, sexType);
        if (isVisible()) {
            dismiss();
        }
    }

    public static void buildErrorDialog(Context context, String message) {
        Builder alertBuilder = new Builder(context);
        alertBuilder.setTitle(context.getResources().getString(C0910R.string.error_dialog_title));
        alertBuilder.setMessage(message);
        alertBuilder.setPositiveButton(context.getResources().getString(C0910R.string.error_dialog_btn), new C09274());
        alertBuilder.show();
    }

    public void buildCloseDialog(Context context, String message) {
        Builder alertBuilder = new Builder(context);
        alertBuilder.setTitle(context.getResources().getString(C0910R.string.error_dialog_title));
        alertBuilder.setMessage(message);
        alertBuilder.setPositiveButton(context.getResources().getString(C0910R.string.error_dialog_btn), new C09285());
        alertBuilder.setNegativeButton(context.getResources().getString(C0910R.string.cancel), new C09296());
        alertBuilder.show();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sex = parent.getItemAtPosition(position).toString();
        if (position > 0) {
            this.sexType = SexType.valueOf(sex);
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public static String getStrinFromDate(Date date) {
        try {
            return new SimpleDateFormat("d MMM yyyy").format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static int getAgeFromDOB(String dob) {
        try {
            Date birthDate = new SimpleDateFormat("d MMM yyyy").parse(dob);
            if (birthDate != null) {
                return getDiffYears(birthDate, new Date());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int getDiffYears(Date first, Date last) {
        try {
            Calendar a = getCalendar(first);
            Calendar b = getCalendar(last);
            int diff = b.get(1) - a.get(1);
            if (a.get(2) > b.get(2) || (a.get(2) == b.get(2) && a.get(5) > b.get(5))) {
                return diff - 1;
            }
            return diff;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    public void onClick(View v) {
        if (v.getId() == C0910R.id.txt_dob) {
            new DatePickerFragment().show(getActivity().getSupportFragmentManager(), "datePicker");
        }
    }
}
