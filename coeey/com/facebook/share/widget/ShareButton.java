package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.C1100R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;

public final class ShareButton extends ShareButtonBase {
    private static final int DEFAULT_REQUEST_CODE = RequestCodeOffset.Share.toRequestCode();

    class C14201 implements OnClickListener {
        C14201() {
        }

        public void onClick(View v) {
            ShareDialog dialog;
            if (ShareButton.this.getFragment() != null) {
                dialog = new ShareDialog(ShareButton.this.getFragment(), ShareButton.this.getRequestCode());
            } else {
                dialog = new ShareDialog(ShareButton.this.getActivity(), ShareButton.this.getRequestCode());
            }
            dialog.show(ShareButton.this.getShareContent());
            ShareButton.this.callExternalOnClickListener(v);
        }
    }

    public ShareButton(Context context) {
        super(context, null, 0, AnalyticsEvents.EVENT_SHARE_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    public ShareButton(Context context, AttributeSet attrs) {
        super(context, attrs, 0, AnalyticsEvents.EVENT_SHARE_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    public ShareButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, AnalyticsEvents.EVENT_SHARE_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    protected int getDefaultStyleResource() {
        return C1100R.style.com_facebook_button_share;
    }

    protected OnClickListener getShareOnClickListener() {
        return new C14201();
    }
}
