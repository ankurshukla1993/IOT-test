package com.cooey.android.users.old.viewholders;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.InterventionHeader;
import com.cooey.android.users.old.PatientCareplanHeader;
import com.cooey.android.users.old.PatientInterventionHeader;
import com.cooey.common.vo.InterventionBlueprintHeader;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class InterventionHeaderViewHolder extends GroupViewHolder {
    private ImageView arrow;
    private TextView genreName;

    public InterventionHeaderViewHolder(View itemView) {
        super(itemView);
        this.genreName = (TextView) itemView.findViewById(C0757R.id.list_item_genre_name);
        this.arrow = (ImageView) itemView.findViewById(C0757R.id.list_item_genre_arrow);
    }

    public void setInterventionHeaderTitle(ExpandableGroup genre) {
        if (genre instanceof InterventionBlueprintHeader) {
            this.genreName.setText(((InterventionBlueprintHeader) genre).getInterventionType());
        }
        if (genre instanceof InterventionHeader) {
            this.genreName.setText(((InterventionHeader) genre).getInterventionType());
        }
        if (genre instanceof PatientCareplanHeader) {
            this.genreName.setText(((PatientCareplanHeader) genre).getTitle());
        }
        if (genre instanceof PatientInterventionHeader) {
            this.genreName.setText(((PatientInterventionHeader) genre).getTitle());
        }
    }

    public void expand() {
        animateExpand();
    }

    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate = new RotateAnimation(360.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        this.arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate = new RotateAnimation(180.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        this.arrow.setAnimation(rotate);
    }
}
