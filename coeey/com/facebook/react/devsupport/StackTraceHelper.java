package com.facebook.react.devsupport;

import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.io.File;
import javax.annotation.Nullable;

public class StackTraceHelper {

    public static class StackFrame {
        private final int mColumn;
        private final String mFile;
        private final String mFileName;
        private final int mLine;
        private final String mMethod;

        private StackFrame(String file, String method, int line, int column) {
            this.mFile = file;
            this.mMethod = method;
            this.mLine = line;
            this.mColumn = column;
            this.mFileName = new File(file).getName();
        }

        private StackFrame(String file, String fileName, String method, int line, int column) {
            this.mFile = file;
            this.mFileName = fileName;
            this.mMethod = method;
            this.mLine = line;
            this.mColumn = column;
        }

        public String getFile() {
            return this.mFile;
        }

        public String getMethod() {
            return this.mMethod;
        }

        public int getLine() {
            return this.mLine;
        }

        public int getColumn() {
            return this.mColumn;
        }

        public String getFileName() {
            return this.mFileName;
        }
    }

    public static StackFrame[] convertJsStackTrace(@Nullable ReadableArray stack) {
        int size = stack != null ? stack.size() : 0;
        StackFrame[] result = new StackFrame[size];
        for (int i = 0; i < size; i++) {
            ReadableMap frame = stack.getMap(i);
            String methodName = frame.getString("methodName");
            String fileName = frame.getString(UriUtil.LOCAL_FILE_SCHEME);
            int lineNumber = frame.getInt("lineNumber");
            int columnNumber = -1;
            if (frame.hasKey("column") && !frame.isNull("column")) {
                columnNumber = frame.getInt("column");
            }
            result[i] = new StackFrame(fileName, methodName, lineNumber, columnNumber);
        }
        return result;
    }

    public static StackFrame[] convertJavaStackTrace(Throwable exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        StackFrame[] result = new StackFrame[stackTrace.length];
        for (int i = 0; i < stackTrace.length; i++) {
            result[i] = new StackFrame(stackTrace[i].getClassName(), stackTrace[i].getFileName(), stackTrace[i].getMethodName(), stackTrace[i].getLineNumber(), -1);
        }
        return result;
    }

    public static String formatFrameSource(StackFrame frame) {
        String lineInfo = "";
        int column = frame.getColumn();
        return lineInfo + frame.getFileName() + ":" + frame.getLine() + (column <= 0 ? "" : ":" + column);
    }

    public static String formatStackTrace(String title, StackFrame[] stack) {
        StringBuilder stackTrace = new StringBuilder();
        stackTrace.append(title).append("\n");
        for (StackFrame frame : stack) {
            stackTrace.append(frame.getMethod()).append("\n").append("    ").append(formatFrameSource(frame)).append("\n");
        }
        return stackTrace.toString();
    }
}
