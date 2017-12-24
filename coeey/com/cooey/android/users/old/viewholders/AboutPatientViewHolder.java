package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.Allergy;
import com.cooey.common.vo.Guardian;
import com.cooey.common.vo.MedicalContact;
import com.cooey.common.vo.RealmString;
import com.cooey.common.vo.User;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import humanize.util.Constants;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutPatientViewHolder extends ChildViewHolder {
    private Context context;
    List<Guardian> guardianList = new ArrayList();
    List<MedicalContact> medicalContacts = new ArrayList();
    private String serverUrl;
    private String sessionId;
    private TextView txtAllergy1;
    private TextView txtAllergy2;
    private TextView txtGuardianName1;
    private TextView txtGuardianName2;
    private TextView txtGuardianNumber1;
    private TextView txtGuardianNumber2;
    private TextView txtHeight;
    private TextView txtHip;
    private TextView txtMedicalContactName1;
    private TextView txtMedicalContactName2;
    private TextView txtMedicalContactNumber1;
    private TextView txtMedicalContactNumber2;
    private TextView txtMoreAllergies;
    private TextView txtNoGuardians;
    private TextView txtNoMedContacts;
    private TextView txtWaist;
    private User user;

    class C08051 implements Callback<List<Guardian>> {
        C08051() {
        }

        public void onResponse(Call<List<Guardian>> call, Response<List<Guardian>> response) {
            if (response.isSuccessful() && response.body() != null && ((List) response.body()).size() > 0) {
                for (int i = 0; i < ((List) response.body()).size(); i++) {
                    AboutPatientViewHolder.this.guardianList.add(((List) response.body()).get(i));
                }
            }
            AboutPatientViewHolder.this.updateGuardianList();
        }

        public void onFailure(Call<List<Guardian>> call, Throwable t) {
            AboutPatientViewHolder.this.updateGuardianList();
        }
    }

    class C08062 implements Callback<List<MedicalContact>> {
        C08062() {
        }

        public void onResponse(Call<List<MedicalContact>> call, Response<List<MedicalContact>> response) {
            if (response.isSuccessful() && response.body() != null && ((List) response.body()).size() > 0) {
                for (int i = 0; i < ((List) response.body()).size(); i++) {
                    AboutPatientViewHolder.this.medicalContacts.add(((List) response.body()).get(i));
                }
            }
            AboutPatientViewHolder.this.updateMedicalContacts();
        }

        public void onFailure(Call<List<MedicalContact>> call, Throwable t) {
            AboutPatientViewHolder.this.updateMedicalContacts();
        }
    }

    public AboutPatientViewHolder(Context context, View itemView, User user, String sessionId, String serverUrl) {
        super(itemView);
        this.context = context;
        this.user = user;
        this.sessionId = sessionId;
        this.serverUrl = serverUrl;
        this.txtNoMedContacts = (TextView) itemView.findViewById(C0757R.id.txt_no_medical_contacts);
        this.txtNoGuardians = (TextView) itemView.findViewById(C0757R.id.txt_no_guardians);
        this.txtMoreAllergies = (TextView) itemView.findViewById(C0757R.id.txt_more_allergies);
        this.txtAllergy2 = (TextView) itemView.findViewById(C0757R.id.txt_allergy_2);
        this.txtAllergy1 = (TextView) itemView.findViewById(C0757R.id.txt_allergy_1);
        this.txtMedicalContactNumber2 = (TextView) itemView.findViewById(C0757R.id.txt_medical_contact_number2);
        this.txtMedicalContactNumber1 = (TextView) itemView.findViewById(C0757R.id.txt_medical_contact_number);
        this.txtMedicalContactName2 = (TextView) itemView.findViewById(C0757R.id.txt_medical_contact);
        this.txtMedicalContactName1 = (TextView) itemView.findViewById(C0757R.id.txt_medical_contact_name);
        this.txtGuardianNumber2 = (TextView) itemView.findViewById(C0757R.id.guardian_number_2);
        this.txtGuardianNumber1 = (TextView) itemView.findViewById(C0757R.id.guardian_number_1);
        this.txtGuardianName2 = (TextView) itemView.findViewById(C0757R.id.guardian_name_2);
        this.txtGuardianName1 = (TextView) itemView.findViewById(C0757R.id.guardian_name_1);
        this.txtHip = (TextView) itemView.findViewById(C0757R.id.txt_hip);
        this.txtWaist = (TextView) itemView.findViewById(C0757R.id.txt_view_waist);
        this.txtHeight = (TextView) itemView.findViewById(C0757R.id.textView5);
    }

    public void setPatientDetails(String patientId) {
        this.guardianList.clear();
        this.medicalContacts.clear();
        new ApiClient(this.context, this.serverUrl).getGuardianService().getGuardiansForPatient(this.sessionId, patientId, 20, 0).enqueue(new C08051());
        new ApiClient(this.context, this.serverUrl).getUsersService().getMedicalContactsForUser(this.sessionId, patientId, "MEDICAL").enqueue(new C08062());
        updateView();
    }

    public void updateGuardianList() {
        if (this.guardianList == null || this.guardianList.size() <= 0) {
            this.txtNoGuardians.setVisibility(0);
            return;
        }
        switch (this.guardianList.size()) {
            case 1:
                if (((Guardian) this.guardianList.get(0)).getFirstName() != null) {
                    this.txtGuardianName1.setText(((Guardian) this.guardianList.get(0)).getFirstName() + Constants.SPACE + ((Guardian) this.guardianList.get(0)).getLastName());
                }
                if (((Guardian) this.guardianList.get(0)).getMobile() != null) {
                    this.txtGuardianNumber1.setText(((Guardian) this.guardianList.get(0)).getMobile());
                    return;
                }
                return;
            case 2:
                if (((Guardian) this.guardianList.get(0)).getFirstName() != null) {
                    this.txtGuardianName1.setText(((Guardian) this.guardianList.get(0)).getFirstName() + Constants.SPACE + ((Guardian) this.guardianList.get(0)).getLastName());
                }
                if (((Guardian) this.guardianList.get(0)).getMobile() != null) {
                    this.txtGuardianNumber1.setText(((Guardian) this.guardianList.get(0)).getMobile());
                }
                if (((Guardian) this.guardianList.get(1)).getFirstName() != null) {
                    this.txtGuardianName1.setText(((Guardian) this.guardianList.get(1)).getFirstName() + Constants.SPACE + ((Guardian) this.guardianList.get(1)).getLastName());
                }
                if (((Guardian) this.guardianList.get(1)).getMobile() != null) {
                    this.txtGuardianNumber2.setText(((Guardian) this.guardianList.get(1)).getMobile());
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void updateMedicalContacts() {
        if (this.medicalContacts == null || this.medicalContacts.size() <= 0) {
            this.txtNoMedContacts.setVisibility(0);
            return;
        }
        switch (this.medicalContacts.size()) {
            case 1:
                if (((MedicalContact) this.medicalContacts.get(0)).getName() != null) {
                    this.txtMedicalContactName1.setText(((MedicalContact) this.medicalContacts.get(0)).getName());
                }
                if (((MedicalContact) this.medicalContacts.get(0)).getMobileNumbers() != null && ((MedicalContact) this.medicalContacts.get(0)).getMobileNumbers().size() > 0 && ((RealmString) ((MedicalContact) this.medicalContacts.get(0)).getMobileNumbers().get(0)).getValue() != null) {
                    this.txtMedicalContactNumber1.setText(((RealmString) ((MedicalContact) this.medicalContacts.get(0)).getMobileNumbers().get(0)).getValue());
                    return;
                }
                return;
            case 2:
                if (((MedicalContact) this.medicalContacts.get(0)).getName() != null) {
                    this.txtMedicalContactName1.setText(((MedicalContact) this.medicalContacts.get(0)).getName());
                }
                if (!(((MedicalContact) this.medicalContacts.get(1)).getMobileNumbers() == null || ((MedicalContact) this.medicalContacts.get(1)).getMobileNumbers().size() <= 0 || ((RealmString) ((MedicalContact) this.medicalContacts.get(1)).getMobileNumbers().get(0)).getValue() == null)) {
                    this.txtMedicalContactNumber1.setText(((RealmString) ((MedicalContact) this.medicalContacts.get(1)).getMobileNumbers().get(0)).getValue());
                }
                if (((MedicalContact) this.medicalContacts.get(1)).getName() != null) {
                    this.txtMedicalContactName2.setText(((MedicalContact) this.medicalContacts.get(1)).getName());
                }
                if (((MedicalContact) this.medicalContacts.get(1)).getMobileNumbers() != null && ((MedicalContact) this.medicalContacts.get(1)).getMobileNumbers().size() > 0 && ((RealmString) ((MedicalContact) this.medicalContacts.get(1)).getMobileNumbers().get(0)).getValue() != null) {
                    this.txtMedicalContactNumber2.setText(((RealmString) ((MedicalContact) this.medicalContacts.get(1)).getMobileNumbers().get(0)).getValue());
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void updateView() {
        if (this.user != null && this.user.getAllergires() != null && this.user.getAllergires().size() > 0) {
            if (this.user.getAllergires().size() > 2) {
                this.txtMoreAllergies.setVisibility(0);
            }
            switch (this.user.getAllergires().size()) {
                case 1:
                    if (((Allergy) this.user.getAllergires().get(0)).getName() != null) {
                        this.txtAllergy1.setText(((Allergy) this.user.getAllergires().get(0)).getName());
                        this.txtAllergy1.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        break;
                    }
                    break;
                case 2:
                    if (((Allergy) this.user.getAllergires().get(0)).getName() != null) {
                        this.txtAllergy1.setText(((Allergy) this.user.getAllergires().get(0)).getName());
                    }
                    if (((Allergy) this.user.getAllergires().get(1)).getName() != null) {
                        this.txtAllergy2.setText(((Allergy) this.user.getAllergires().get(1)).getName());
                        break;
                    }
                    break;
            }
        }
        this.txtAllergy1.setText("No Allergies");
        this.txtAllergy1.setTextAlignment(4);
        if (!(this.user.getHeight() == null || this.user.getHeight().getValue() == null)) {
            this.txtHeight.setText(this.user.getHeight().getValue() + Constants.SPACE + this.user.getHeight().getUnit());
        }
        if (!(this.user.getHipSize() == null || this.user.getHipSize().getValue() == null)) {
            this.txtHip.setText(this.user.getHipSize().getValue() + Constants.SPACE + this.user.getHipSize().getUnit());
        }
        if (this.user.getWaistSize() != null && this.user.getWaistSize().getValue() != null) {
            this.txtWaist.setText(this.user.getWaistSize().getValue() + Constants.SPACE + this.user.getWaistSize().getUnit());
        }
    }
}
