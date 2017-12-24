package com.thefinestartist.utils.log;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;
import com.thefinestartist.enums.LogLevel;
import com.thefinestartist.utils.content.ResourcesUtil;
import com.thefinestartist.utils.etc.APILevel;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONObject;

public class LogHelper {
    private static final int INDENT_SPACES = 4;
    protected Settings settings = new Settings(LogHelper.class.getSimpleName());

    public LogHelper(String tag) {
        this.settings.setTag(tag);
    }

    public LogHelper(@StringRes int tagRes) {
        this.settings.setTag(ResourcesUtil.getString(tagRes));
    }

    public LogHelper(Class clazz) {
        this.settings.setTag(clazz.getSimpleName());
    }

    public LogHelper tag(String tag) {
        this.settings.setTag(tag);
        return this;
    }

    public LogHelper tag(@StringRes int tagRes) {
        this.settings.setTag(tagRes);
        return this;
    }

    public LogHelper tag(Class clazz) {
        this.settings.setTag(clazz);
        return this;
    }

    public LogHelper showThreadInfo(boolean showThreadInfo) {
        this.settings.setShowThreadInfo(showThreadInfo);
        return this;
    }

    public LogHelper stackTraceCount(int stackTraceCount) {
        this.settings.setStackTraceCount(stackTraceCount);
        return this;
    }

    public LogHelper logLevel(LogLevel logLevel) {
        this.settings.setLogLevel(logLevel);
        return this;
    }

    public void m2080v(byte message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2081v(char message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2091v(short message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2084v(int message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2085v(long message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2083v(float message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2082v(double message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2092v(boolean message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2088v(String message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2090v(JSONObject message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2089v(JSONArray message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2086v(Exception message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2087v(Object message) {
        log(LogLevel.VERBOSE, message);
    }

    public void m2041d(byte message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2042d(char message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2052d(short message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2045d(int message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2046d(long message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2044d(float message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2043d(double message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2053d(boolean message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2049d(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2051d(JSONObject message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2050d(JSONArray message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2047d(Exception message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2048d(Object message) {
        log(LogLevel.DEBUG, message);
    }

    public void m2067i(byte message) {
        log(LogLevel.INFO, message);
    }

    public void m2068i(char message) {
        log(LogLevel.INFO, message);
    }

    public void m2078i(short message) {
        log(LogLevel.INFO, message);
    }

    public void m2071i(int message) {
        log(LogLevel.INFO, message);
    }

    public void m2072i(long message) {
        log(LogLevel.INFO, message);
    }

    public void m2070i(float message) {
        log(LogLevel.INFO, message);
    }

    public void m2069i(double message) {
        log(LogLevel.INFO, message);
    }

    public void m2079i(boolean message) {
        log(LogLevel.INFO, message);
    }

    public void m2075i(String message) {
        log(LogLevel.INFO, message);
    }

    public void m2077i(JSONObject message) {
        log(LogLevel.INFO, message);
    }

    public void m2076i(JSONArray message) {
        log(LogLevel.INFO, message);
    }

    public void m2073i(Exception message) {
        log(LogLevel.INFO, message);
    }

    public void m2074i(Object message) {
        log(LogLevel.INFO, message);
    }

    public void m2093w(byte message) {
        log(LogLevel.WARN, message);
    }

    public void m2094w(char message) {
        log(LogLevel.WARN, message);
    }

    public void m2104w(short message) {
        log(LogLevel.WARN, message);
    }

    public void m2097w(int message) {
        log(LogLevel.WARN, message);
    }

    public void m2098w(long message) {
        log(LogLevel.WARN, message);
    }

    public void m2096w(float message) {
        log(LogLevel.WARN, message);
    }

    public void m2095w(double message) {
        log(LogLevel.WARN, message);
    }

    public void m2105w(boolean message) {
        log(LogLevel.WARN, message);
    }

    public void m2101w(String message) {
        log(LogLevel.WARN, message);
    }

    public void m2103w(JSONObject message) {
        log(LogLevel.WARN, message);
    }

    public void m2102w(JSONArray message) {
        log(LogLevel.WARN, message);
    }

    public void m2099w(Exception message) {
        log(LogLevel.WARN, message);
    }

    public void m2100w(Object message) {
        log(LogLevel.WARN, message);
    }

    public void m2054e(byte message) {
        log(LogLevel.ERROR, message);
    }

    public void m2055e(char message) {
        log(LogLevel.ERROR, message);
    }

    public void m2065e(short message) {
        log(LogLevel.ERROR, message);
    }

    public void m2058e(int message) {
        log(LogLevel.ERROR, message);
    }

    public void m2059e(long message) {
        log(LogLevel.ERROR, message);
    }

    public void m2057e(float message) {
        log(LogLevel.ERROR, message);
    }

    public void m2056e(double message) {
        log(LogLevel.ERROR, message);
    }

    public void m2066e(boolean message) {
        log(LogLevel.ERROR, message);
    }

    public void m2062e(String message) {
        log(LogLevel.ERROR, message);
    }

    public void m2064e(JSONObject message) {
        log(LogLevel.ERROR, message);
    }

    public void m2063e(JSONArray message) {
        log(LogLevel.ERROR, message);
    }

    public void m2060e(Exception message) {
        log(LogLevel.ERROR, message);
    }

    public void m2061e(Object message) {
        log(LogLevel.ERROR, message);
    }

    public void wtf(byte message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(char message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(short message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(int message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(long message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(float message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(double message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(boolean message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(String message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(JSONObject message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(JSONArray message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(Exception message) {
        log(LogLevel.ASSERT, message);
    }

    public void wtf(Object message) {
        log(LogLevel.ASSERT, message);
    }

    public void json(String jsonString) {
        json(LogLevel.DEBUG, jsonString);
    }

    public void json(LogLevel logLevel, String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            log(logLevel, "Json string is empty.");
            return;
        }
        jsonString = jsonString.trim();
        try {
            if (jsonString.startsWith("{")) {
                log(logLevel, new JSONObject(jsonString).toString(4));
            } else if (jsonString.startsWith("[")) {
                log(logLevel, new JSONArray(jsonString).toString(4));
            }
        } catch (Exception e) {
            log(logLevel, e);
        }
    }

    public void xml(String xmlString) {
        xml(LogLevel.DEBUG, xmlString);
    }

    public void xml(LogLevel logLevel, String xmlString) {
        if (TextUtils.isEmpty(xmlString)) {
            log(logLevel, "Xml string is empty.");
        } else if (APILevel.require(8)) {
            try {
                Source xmlInput = new StreamSource(new StringReader(xmlString));
                StreamResult xmlOutput = new StreamResult(new StringWriter());
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty("indent", "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(xmlInput, xmlOutput);
                log(logLevel, xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
            } catch (Exception e) {
                log(logLevel, e);
            }
        } else {
            log(logLevel, xmlString);
        }
    }

    private void log(LogLevel logLevel, byte message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            printLog(logLevel, String.valueOf(message));
        }
    }

    private void log(LogLevel logLevel, char message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            printLog(logLevel, String.valueOf(message));
        }
    }

    private void log(LogLevel logLevel, short message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            printLog(logLevel, String.valueOf(message));
        }
    }

    private void log(LogLevel logLevel, int message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            printLog(logLevel, String.valueOf(message));
        }
    }

    private void log(LogLevel logLevel, long message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            printLog(logLevel, String.valueOf(message));
        }
    }

    private void log(LogLevel logLevel, float message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            printLog(logLevel, String.valueOf(message));
        }
    }

    private void log(LogLevel logLevel, double message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            printLog(logLevel, String.valueOf(message));
        }
    }

    private void log(LogLevel logLevel, boolean message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            printLog(logLevel, String.valueOf(message));
        }
    }

    private void log(LogLevel logLevel, String message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            printLog(logLevel, message);
        }
    }

    private void log(LogLevel logLevel, JSONObject message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            try {
                printLog(logLevel, message.toString(4));
            } catch (Exception e) {
                log(logLevel, e);
            }
        }
    }

    private void log(LogLevel logLevel, JSONArray message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            try {
                printLog(logLevel, message.toString(4));
            } catch (Exception e) {
                log(logLevel, e);
            }
        }
    }

    private void log(LogLevel logLevel, Exception message) {
        if (logLevel.ordinal() >= this.settings.getLogLevel().ordinal()) {
            StringBuilder builder = new StringBuilder();
            builder.append(String.valueOf(message));
            builder.append("\n");
            for (StackTraceElement trace : message.getStackTrace()) {
                builder.append("    at ").append(trace.getClassName()).append(".").append(trace.getMethodName()).append("(").append(trace.getFileName()).append(":").append(trace.getLineNumber()).append(")").append("\n");
            }
            printLog(logLevel, builder.toString(), true);
        }
    }

    private void log(LogLevel logLevel, Object message) {
        if (logLevel.ordinal() >= logLevel.ordinal()) {
            String log;
            if (message instanceof byte[]) {
                log = Arrays.toString((byte[]) message);
            } else if (message instanceof char[]) {
                log = Arrays.toString((char[]) message);
            } else if (message instanceof short[]) {
                log = Arrays.toString((short[]) message);
            } else if (message instanceof int[]) {
                log = Arrays.toString((int[]) message);
            } else if (message instanceof long[]) {
                log = Arrays.toString((long[]) message);
            } else if (message instanceof float[]) {
                log = Arrays.toString((float[]) message);
            } else if (message instanceof double[]) {
                log = Arrays.toString((double[]) message);
            } else if (message instanceof boolean[]) {
                log = Arrays.toString((boolean[]) message);
            } else if (message instanceof Object[]) {
                log = Arrays.toString((Object[]) message);
            } else {
                log = String.valueOf(message);
            }
            printLog(logLevel, log);
        }
    }

    private void printLog(LogLevel logLevel, String message) {
        printLog(logLevel, message, false);
    }

    private synchronized void printLog(LogLevel logLevel, String message, boolean fromException) {
        String TAG = this.settings.getTag();
        if (this.settings.getShowThreadInfo()) {
            TAG = TAG + "(" + Thread.currentThread().getName() + ")";
        }
        for (String line : message.split(System.getProperty("line.separator"))) {
            printLine(logLevel, TAG, line);
        }
        if (this.settings.getStackTraceCount() > 0 && fromException) {
            printLine(logLevel, TAG, "Exception is occurred");
        }
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        int startIndex = 2;
        while (true) {
            if (!LogUtil.class.getCanonicalName().equals(traces[startIndex].getClassName()) && !LogHelper.class.getCanonicalName().equals(traces[startIndex].getClassName())) {
                break;
            }
            startIndex++;
        }
        for (int i = startIndex; i < Math.min(traces.length, this.settings.getStackTraceCount() + startIndex); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append("    at ").append(traces[i].getClassName()).append(".").append(traces[i].getMethodName()).append("(").append(traces[i].getFileName()).append(":").append(traces[i].getLineNumber()).append(")");
            printLine(logLevel, TAG, builder.toString());
        }
        int leftTraceCount = (traces.length - startIndex) - this.settings.getStackTraceCount();
        if (this.settings.getStackTraceCount() > 0 && leftTraceCount > 1) {
            printLine(logLevel, TAG, "    at " + leftTraceCount + " more stack traces...");
        }
        if (this.settings.getStackTraceCount() > 0 && leftTraceCount == 1) {
            printLine(logLevel, TAG, "    at 1 more stack trace...");
        }
        if (this == LogUtil.getInstance()) {
            setToDefault();
        }
    }

    protected void setToDefault() {
        this.settings.setTag(LogUtil.getDefaultSettings().getTag());
        this.settings.setShowThreadInfo(LogUtil.getDefaultSettings().getShowThreadInfo());
        this.settings.setStackTraceCount(LogUtil.getDefaultSettings().getStackTraceCount());
        this.settings.setLogLevel(LogUtil.getDefaultSettings().getLogLevel());
    }

    private void printLine(LogLevel logLevel, String TAG, String message) {
        switch (logLevel) {
            case FULL:
            case VERBOSE:
                Log.v(TAG, message);
                return;
            case DEBUG:
                Log.d(TAG, message);
                return;
            case INFO:
                Log.i(TAG, message);
                return;
            case WARN:
                Log.w(TAG, message);
                return;
            case ERROR:
                Log.e(TAG, message);
                return;
            case ASSERT:
                if (APILevel.require(8)) {
                    Log.wtf(TAG, message);
                    return;
                } else {
                    Log.e(TAG, message);
                    return;
                }
            default:
                return;
        }
    }
}
