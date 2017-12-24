package com.biz.health.cooey_app;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import com.biz.health.cooey_app.dashboard.AboutFragment;
import com.biz.health.cooey_app.dashboard.InterventionFragment;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.realm_store.CarePlanStore;
import com.cooey.common.realm_store.InterventionStore;
import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.User;
import com.cooey.common.vo.careplan.Careplan;
import com.cooey.common.vo.careplan.Intervention;
import humanize.util.Constants;
import java.util.ArrayList;
import java.util.List;

public class ActivityCareplanSummary extends AppCompatActivity {
    private ApiClient apiClient;
    private Careplan careplan;
    Context context;
    private Session session;
    private TabLayout tabLayout;
    private TextView textNumofDays;
    private TextView textViewCareplanDesc;
    private TextView textViewCareplanName;
    private TextView textViewPatientEmail;
    private TextView textViewPatientName;
    private Toolbar toolbar;
    private User user;
    private ViewPager viewPager;

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public Fragment getItem(int position) {
            return (Fragment) this.mFragmentList.get(position);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return (CharSequence) this.mFragmentTitleList.get(position);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0644R.layout.activity_careplan_summary);
        this.context = this;
        initialize();
        setupViewPager(this.viewPager);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.textViewPatientName.setText(this.user.getFirstName() + Constants.SPACE + this.user.getLastName());
        this.textViewPatientEmail.setText(this.user.getEmail());
    }

    private void initialize() {
        this.toolbar = (Toolbar) findViewById(C0644R.id.toolbar);
        this.toolbar.setTitle((CharSequence) "Care Plan");
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.viewPager = (ViewPager) findViewById(C0644R.id.viewpager);
        this.textViewPatientName = (TextView) findViewById(C0644R.id.txt_patient_name);
        this.textViewCareplanName = (TextView) findViewById(C0644R.id.txt_careplan_name);
        this.textViewCareplanDesc = (TextView) findViewById(C0644R.id.txt_careplan_description);
        this.textViewPatientEmail = (TextView) findViewById(C0644R.id.txt_patient_email);
        this.textNumofDays = (TextView) findViewById(C0644R.id.txt_num_of_days);
        this.careplan = getCarePlan();
        this.user = new PreferenceStore().getUser(this.context);
        this.apiClient = new ApiClient(this.context, "http://api.cooey.co.in/ehealth/");
        this.session = new PreferenceStore().getSession(this);
        this.tabLayout = (TabLayout) findViewById(C0644R.id.tabs);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        InterventionFragment interventionFragment = new InterventionFragment();
        AboutFragment aboutFragment = new AboutFragment();
        if (this.careplan != null) {
            interventionFragment.interventionList = getCarePlanInterventions(this.careplan.getId());
            aboutFragment.careplan = this.careplan;
            this.textViewCareplanName.setText(this.careplan.getName());
            this.textViewCareplanDesc.setText(this.careplan.getDescription());
            if (this.careplan.getNumOfDays() > 0) {
                this.textNumofDays.setText(this.careplan.getNumOfDays() + "");
            } else {
                this.textNumofDays.setText("-");
            }
        }
        adapter.addFragment(interventionFragment, CTConstants.INTERVENTION);
        adapter.addFragment(aboutFragment, CTConstants.ABOUT);
        viewPager.setAdapter(adapter);
    }

    public Careplan getCarePlan() {
        List<Careplan> careplan = CarePlanStore.getInstance(this).getCareplan();
        if (careplan == null || careplan.size() <= 0) {
            return null;
        }
        return (Careplan) careplan.get(careplan.size() - 1);
    }

    public List<Intervention> getCarePlanInterventions(String ownerid) {
        return InterventionStore.getInstance(this).getInterventions(ownerid);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
