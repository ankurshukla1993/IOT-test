package com.facebook.share.internal;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.C1100R;
import com.facebook.FacebookButtonBase;
import com.facebook.internal.AnalyticsEvents;

public class LikeButton extends FacebookButtonBase {
    public LikeButton(Context context, boolean isLiked) {
        super(context, null, 0, 0, AnalyticsEvents.EVENT_LIKE_BUTTON_CREATE, 0);
        setSelected(isLiked);
    }

    public void setSelected(boolean selected) {
        super.setSelected(selected);
        updateForLikeStatus();
    }

    protected void configureButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super.configureButton(context, attrs, defStyleAttr, defStyleRes);
        updateForLikeStatus();
    }

    protected int getDefaultStyleResource() {
        return C1100R.style.com_facebook_button_like;
    }

    private void updateForLikeStatus() {
        if (isSelected()) {
            setCompoundDrawablesWithIntrinsicBounds(C1100R.drawable.com_facebook_button_like_icon_selected, 0, 0, 0);
            setText(getResources().getString(C1100R.string.com_facebook_like_button_liked));
            return;
        }
        setCompoundDrawablesWithIntrinsicBounds(C1100R.drawable.com_facebook_button_icon, 0, 0, 0);
        setText(getResources().getString(C1100R.string.com_facebook_like_button_not_liked));
    }
}
