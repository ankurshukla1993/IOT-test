package com.facebook.react.devsupport;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.devsupport.RedBoxHandler.ReportCompletedListener;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;
import javax.annotation.Nullable;

class RedBoxDialog extends Dialog implements OnItemClickListener {
    private boolean isReporting = false;
    private Button mCopyToClipboardButton;
    private final DevSupportManager mDevSupportManager;
    private Button mDismissButton;
    private final DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    @Nullable
    private View mLineSeparator;
    @Nullable
    private ProgressBar mLoadingIndicator;
    @Nullable
    private final RedBoxHandler mRedBoxHandler;
    private Button mReloadJsButton;
    @Nullable
    private Button mReportButton;
    private OnClickListener mReportButtonOnClickListener = new 2(this);
    private ReportCompletedListener mReportCompletedListener = new 1(this);
    @Nullable
    private TextView mReportTextView;
    private ListView mStackView;

    protected RedBoxDialog(Context context, DevSupportManager devSupportManager, @Nullable RedBoxHandler redBoxHandler) {
        super(context, R.style.Theme_Catalyst_RedBox);
        requestWindowFeature(1);
        setContentView(R.layout.redbox_view);
        this.mDevSupportManager = devSupportManager;
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mRedBoxHandler = redBoxHandler;
        this.mStackView = (ListView) findViewById(R.id.rn_redbox_stack);
        this.mStackView.setOnItemClickListener(this);
        this.mReloadJsButton = (Button) findViewById(R.id.rn_redbox_reload_button);
        this.mReloadJsButton.setOnClickListener(new 3(this));
        this.mDismissButton = (Button) findViewById(R.id.rn_redbox_dismiss_button);
        this.mDismissButton.setOnClickListener(new 4(this));
        this.mCopyToClipboardButton = (Button) findViewById(R.id.rn_redbox_copy_button);
        this.mCopyToClipboardButton.setOnClickListener(new 5(this));
        if (this.mRedBoxHandler != null && this.mRedBoxHandler.isReportEnabled()) {
            this.mLoadingIndicator = (ProgressBar) findViewById(R.id.rn_redbox_loading_indicator);
            this.mLineSeparator = findViewById(R.id.rn_redbox_line_separator);
            this.mReportTextView = (TextView) findViewById(R.id.rn_redbox_report_label);
            this.mReportTextView.setMovementMethod(LinkMovementMethod.getInstance());
            this.mReportTextView.setHighlightColor(0);
            this.mReportButton = (Button) findViewById(R.id.rn_redbox_report_button);
            this.mReportButton.setOnClickListener(this.mReportButtonOnClickListener);
        }
    }

    public void setExceptionDetails(String title, StackFrame[] stack) {
        this.mStackView.setAdapter(new StackAdapter(title, stack));
    }

    public void resetReporting(boolean enabled) {
        int i = 0;
        if (this.mRedBoxHandler != null && this.mRedBoxHandler.isReportEnabled()) {
            this.isReporting = false;
            ((TextView) Assertions.assertNotNull(this.mReportTextView)).setVisibility(8);
            ((ProgressBar) Assertions.assertNotNull(this.mLoadingIndicator)).setVisibility(8);
            ((View) Assertions.assertNotNull(this.mLineSeparator)).setVisibility(8);
            Button button = (Button) Assertions.assertNotNull(this.mReportButton);
            if (!enabled) {
                i = 8;
            }
            button.setVisibility(i);
            ((Button) Assertions.assertNotNull(this.mReportButton)).setEnabled(true);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        new OpenStackFrameTask(this.mDevSupportManager, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new StackFrame[]{(StackFrame) this.mStackView.getAdapter().getItem(position)});
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 82) {
            this.mDevSupportManager.showDevOptionsDialog();
            return true;
        }
        if (this.mDoubleTapReloadRecognizer.didDoubleTapR(keyCode, getCurrentFocus())) {
            this.mDevSupportManager.handleReloadJS();
        }
        return super.onKeyUp(keyCode, event);
    }
}
