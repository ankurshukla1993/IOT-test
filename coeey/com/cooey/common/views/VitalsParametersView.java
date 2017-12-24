package com.cooey.common.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.C0842R;
import com.cooey.common.vo.Vital;
import java.util.Map;

public class VitalsParametersView extends FrameLayout {
    private ImageView foodContextImageView;
    private ImageView handImageView;
    private LinearLayout mContentView;
    private ImageView moodImageView;
    private Vital vital;

    public VitalsParametersView(Context context) {
        this(context, null);
    }

    public VitalsParametersView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VitalsParametersView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(C0842R.layout.layout_vitals_parameters, this);
        this.mContentView = (LinearLayout) findViewById(C0842R.id.content);
        this.moodImageView = (ImageView) this.mContentView.findViewById(C0842R.id.moodImage);
        this.foodContextImageView = (ImageView) this.mContentView.findViewById(C0842R.id.foodContextImage);
        this.handImageView = (ImageView) this.mContentView.findViewById(C0842R.id.handImage);
    }

    public void setParameters(String parameters) {
        String paramters = "";
    }

    public void addView(View child, int index, LayoutParams params) {
        if (this.mContentView == null) {
            super.addView(child, index, params);
        } else {
            this.mContentView.addView(child, index, params);
        }
    }

    public void setVital(Vital vital) {
        this.vital = vital;
        if (vital != null) {
            Map<String, String> parameterMap = vital.getParameterMap();
            if (parameterMap != null) {
                String mood = (String) parameterMap.get("mood");
                if (mood != null && mood.contentEquals("Happy")) {
                    this.moodImageView.setImageResource(C0842R.drawable.happy_green_180);
                }
                if (mood != null && mood.contentEquals("Sad")) {
                    this.moodImageView.setImageResource(C0842R.drawable.sad_green_180);
                }
                String context = (String) parameterMap.get("context");
                if (!(context == null || vital.getVitalType() == null || !vital.getVitalType().contentEquals("BLOOD_PRESSURE"))) {
                    this.foodContextImageView.setVisibility(8);
                    if (context.contentEquals(CTConstants.LEFTHAND)) {
                        this.handImageView.setImageResource(C0842R.drawable.lefthand_green_180);
                    }
                    if (context.contentEquals(CTConstants.RIGHTHAND)) {
                        this.handImageView.setImageResource(C0842R.drawable.right_hand_green_180);
                    }
                }
                if (context != null && vital.getVitalType() != null && vital.getVitalType().contentEquals("BLOOD_GLUCOSE")) {
                    this.handImageView.setVisibility(8);
                    if (context.contentEquals("FBS")) {
                        this.foodContextImageView.setImageResource(C0842R.drawable.fasting_green_180);
                    }
                    if (context.contentEquals("PPDB")) {
                        this.foodContextImageView.setImageResource(C0842R.drawable.after_meal_green_180);
                    }
                    if (context.contentEquals("Random")) {
                        this.foodContextImageView.setImageResource(C0842R.drawable.bolus_green_180);
                    }
                }
            }
        }
    }
}
