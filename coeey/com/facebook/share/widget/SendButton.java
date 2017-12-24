package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.C1100R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;

public final class SendButton extends ShareButtonBase {
    private static final int DEFAULT_REQUEST_CODE = RequestCodeOffset.Message.toRequestCode();

    class C14191 implements OnClickListener {
        C14191() {
        }

        public void onClick(View v) {
            MessageDialog dialog;
            if (SendButton.this.getFragment() != null) {
                dialog = new MessageDialog(SendButton.this.getFragment(), SendButton.this.getRequestCode());
            } else {
                dialog = new MessageDialog(SendButton.this.getActivity(), SendButton.this.getRequestCode());
            }
            dialog.show(SendButton.this.getShareContent());
            SendButton.this.callExternalOnClickListener(v);
        }
    }

    public SendButton(Context context) {
        super(context, null, 0, AnalyticsEvents.EVENT_SEND_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    public SendButton(Context context, AttributeSet attrs) {
        super(context, attrs, 0, AnalyticsEvents.EVENT_SEND_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    public SendButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, AnalyticsEvents.EVENT_SEND_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    protected int getDefaultStyleResource() {
        return C1100R.style.com_facebook_button_send;
    }

    protected OnClickListener getShareOnClickListener() {
        return new C14191();
    }
}
