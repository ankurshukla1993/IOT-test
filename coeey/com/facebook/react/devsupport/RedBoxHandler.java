package com.facebook.react.devsupport;

import android.text.SpannedString;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;

public interface RedBoxHandler {

    public interface ReportCompletedListener {
        void onReportError(SpannedString spannedString);

        void onReportSuccess(SpannedString spannedString);
    }

    public enum ErrorType {
        JS("JS"),
        NATIVE("Native");
        
        private final String name;

        private ErrorType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    void handleRedbox(String str, StackFrame[] stackFrameArr, ErrorType errorType);

    boolean isReportEnabled();

    void reportRedbox(String str, StackFrame[] stackFrameArr, String str2, ReportCompletedListener reportCompletedListener);
}
