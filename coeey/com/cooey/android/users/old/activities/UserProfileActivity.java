package com.cooey.android.users.old.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.adapters.CustomFragmentPagerAdapter;
import com.cooey.android.users.old.careplan.CareplanDashBoardFragment;
import com.cooey.android.users.old.fragments.AboutFragment;
import com.cooey.android.users.old.fragments.InterventionFragment;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.utils.CTUtility;
import com.cooey.android.users.old.utils.animation.CircleTransform;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.User;
import humanize.util.Constants;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {
    private CustomFragmentPagerAdapter fragmentPagerAdapter;
    private ImageView imgProfile;
    private ImageView imvGender;
    private String patientId;
    private String serverUrl;
    private String sessionId;
    private TabLayout tabLayout;
    private String tenantId;
    private TextView txtPatientAge;
    private TextView txtPatientContact;
    private TextView txtPatientName;
    private TextView txtPatientRoomNumber;
    private User user = new User();
    private ViewPager viewPager;

    class C07701 implements Callback<User> {
        C07701() {
        }

        public void onResponse(Call<User> call, Response<User> response) {
            if (response.body() != null && response.isSuccessful()) {
                UserProfileActivity.this.user = (User) response.body();
            }
            UserProfileActivity.this.setProfileView();
        }

        public void onFailure(Call<User> call, Throwable t) {
            UserProfileActivity.this.user = null;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0757R.layout.activity_user_profile_user);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            if (intent.getStringExtra("patientId") != null) {
                this.patientId = intent.getStringExtra("patientId");
            }
            if (intent.getStringExtra("sessionId") != null) {
                this.sessionId = intent.getStringExtra("sessionId");
            }
            if (intent.getStringExtra("serverurl") != null) {
                this.serverUrl = intent.getStringExtra("serverurl");
            }
            if (intent.getStringExtra("tenantId") != null) {
                this.tenantId = intent.getStringExtra("tenantId");
            }
        }
        initViews();
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.user = getUserInfo();
    }

    private void initViews() {
        this.tabLayout = (TabLayout) findViewById(C0757R.id.tabs);
        this.viewPager = (ViewPager) findViewById(C0757R.id.viewpager);
        this.imgProfile = (ImageView) findViewById(C0757R.id.profilePic);
        this.imvGender = (ImageView) findViewById(C0757R.id.imv_gender);
        this.txtPatientRoomNumber = (TextView) findViewById(C0757R.id.txt_view_room_number);
        this.txtPatientName = (TextView) findViewById(C0757R.id.txt_patient_name);
        this.txtPatientAge = (TextView) findViewById(C0757R.id.txt_patient_age);
        this.txtPatientContact = (TextView) findViewById(C0757R.id.txt_patient_mob);
    }

    private void setProfileView() {
        String profilePhoto;
        boolean isPicPresent = false;
        this.txtPatientName.setText(this.user.getFirstName() + Constants.SPACE + this.user.getLastName());
        String dob = this.user.getDateOfBirth();
        if (dob != null) {
            this.txtPatientAge.setText(CTUtility.getAge(new Date(Long.parseLong(dob))));
        }
        this.txtPatientContact.setText(this.user.getMobile());
        if (this.user.getRoom() == null) {
            this.txtPatientRoomNumber.setVisibility(8);
        }
        this.txtPatientRoomNumber.setText(this.user.getRoom());
        String gender = this.user.getGender();
        if (this.user.getProfilePhotoId() == null) {
            profilePhoto = "null";
        } else {
            profilePhoto = this.user.getProfilePhotoId();
        }
        if (!profilePhoto.contentEquals("null")) {
            isPicPresent = true;
            Glide.with(this).load(Base64.decode(profilePhoto, 0)).asBitmap().transform(new BitmapTransformation[]{new CircleTransform(this)}).into(this.imgProfile);
        }
        if (gender != null && gender.equalsIgnoreCase("male")) {
            if (!isPicPresent) {
                this.imgProfile.setImageDrawable(ContextCompat.getDrawable(this, C0757R.drawable.man));
            }
            this.imvGender.setImageDrawable(ContextCompat.getDrawable(this, C0757R.drawable.ic_action_male));
        } else if (gender != null && gender.equalsIgnoreCase("female")) {
            if (!isPicPresent) {
                this.imgProfile.setImageDrawable(ContextCompat.getDrawable(this, C0757R.drawable.woman));
            }
            this.imvGender.setImageDrawable(ContextCompat.getDrawable(this, C0757R.drawable.ic_action_female));
        } else if (!isPicPresent) {
            this.imgProfile.setImageDrawable(ContextCompat.getDrawable(this, C0757R.drawable.man));
        }
        setupViewPager(this.viewPager);
    }

    public User getUserInfo() {
        new ApiClient(this, this.serverUrl).getUsersService().get_0(this.patientId, this.sessionId).enqueue(new C07701());
        return this.user;
    }

    public void setupViewPager(ViewPager viewPager) {
        AboutFragment aboutFragment = new AboutFragment();
        aboutFragment.sessionId = this.sessionId;
        aboutFragment.user = this.user;
        aboutFragment.serverUrl = this.serverUrl;
        if (this.tenantId != null) {
            aboutFragment.tenantId = this.tenantId;
        }
        InterventionFragment interventionFragment = new InterventionFragment();
        interventionFragment.sessionId = this.sessionId;
        interventionFragment.user = this.user;
        interventionFragment.serverUrl = this.serverUrl;
        CareplanDashBoardFragment careplanDashBoardFragment = new CareplanDashBoardFragment();
        careplanDashBoardFragment.token = this.sessionId;
        careplanDashBoardFragment.user = this.user;
        careplanDashBoardFragment.userId = this.user.getId();
        careplanDashBoardFragment.serverUrl = this.serverUrl;
        this.fragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
        this.fragmentPagerAdapter.addFragment(interventionFragment, CTConstants.INTERVENTION);
        this.fragmentPagerAdapter.addFragment(aboutFragment, CTConstants.ABOUT);
        this.fragmentPagerAdapter.addFragment(careplanDashBoardFragment, "Action Items");
        viewPager.setAdapter(this.fragmentPagerAdapter);
    }
}
