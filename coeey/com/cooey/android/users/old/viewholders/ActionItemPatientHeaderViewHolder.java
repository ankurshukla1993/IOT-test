package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.utils.CTUtility;
import com.cooey.android.users.old.utils.animation.CircleTransform;
import com.cooey.common.vo.ActionItemPatientHeader;
import com.cooey.common.vo.ListItem;
import com.cooey.common.vo.User;
import java.util.Date;

public class ActionItemPatientHeaderViewHolder extends TodoViewHolder {
    ImageView imgPatientImage;
    private Context mContext;
    TextView txtActionHeader;
    User user;

    public ActionItemPatientHeaderViewHolder(Context context, View itemView, User user) {
        super(itemView);
        this.mContext = context;
        this.txtActionHeader = (TextView) itemView.findViewById(C0757R.id.txt_view_action_header);
        this.imgPatientImage = (ImageView) itemView.findViewById(C0757R.id.imv_patient_image);
        this.user = user;
    }

    public void bindType(ListItem listItem, int position) {
        super.bindType(listItem, position);
        String gender = null;
        ActionItemPatientHeader actionItemTimeHeader = (ActionItemPatientHeader) listItem;
        boolean isPicPresent = false;
        if (this.user == null || this.user.getDateOfBirth() == null) {
            this.txtActionHeader.setText("");
        } else {
            String dateOfBirth = "" + CTUtility.getAge(new Date(Long.parseLong(this.user.getDateOfBirth())));
            gender = this.user.getGender();
            this.txtActionHeader.setText(String.format("%s %s (%s)", new Object[]{this.user.getFirstName(), this.user.getLastName(), dateOfBirth}));
        }
        String profilePhoto = null;
        if (this.user != null) {
            if (this.user.getProfilePhotoId() == null) {
                profilePhoto = "null";
            } else {
                profilePhoto = this.user.getProfilePhotoId();
            }
        }
        if (!(profilePhoto == null || profilePhoto.contentEquals("null"))) {
            isPicPresent = true;
            Glide.with(this.mContext).load(Base64.decode(profilePhoto, 0)).asBitmap().transform(new BitmapTransformation[]{new CircleTransform(this.mContext)}).into(this.imgPatientImage);
        }
        if (gender == null || !gender.equalsIgnoreCase(CTConstants.MALE)) {
            if (gender == null || !gender.equalsIgnoreCase(CTConstants.FEMALE)) {
                if (!isPicPresent) {
                    this.imgPatientImage.setImageResource(C0757R.drawable.man);
                }
            } else if (!isPicPresent) {
                this.imgPatientImage.setImageResource(C0757R.drawable.woman);
            }
        } else if (!isPicPresent) {
            this.imgPatientImage.setImageResource(C0757R.drawable.man);
        }
    }
}
