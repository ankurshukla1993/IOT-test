package com.thefinestartist.utils.log;

import android.support.annotation.StringRes;
import com.thefinestartist.enums.LogLevel;
import org.json.JSONArray;
import org.json.JSONObject;

public class LogUtil {
    private static Settings defaultSettings = new Settings(LogUtil.class.getSimpleName());
    private static volatile LogHelper logHelper = new LogHelper().tag(defaultSettings.getTag()).showThreadInfo(defaultSettings.getShowThreadInfo()).stackTraceCount(defaultSettings.getStackTraceCount()).logLevel(defaultSettings.getLogLevel());

    protected LogUtil() {
    }

    public static Settings getDefaultSettings() {
        return defaultSettings;
    }

    public static LogHelper getInstance() {
        return logHelper;
    }

    public static LogHelper tag(String tag) {
        return logHelper.tag(tag);
    }

    public static LogHelper tag(@StringRes int tagRes) {
        return logHelper.tag(tagRes);
    }

    public static LogHelper tag(Class clazz) {
        return logHelper.tag(clazz);
    }

    public static LogHelper showThreadInfo(boolean showThreadInfo) {
        return logHelper.showThreadInfo(showThreadInfo);
    }

    public static LogHelper stackTraceCount(int stackTraceCount) {
        return logHelper.stackTraceCount(stackTraceCount);
    }

    public static LogHelper logLevel(LogLevel logLevel) {
        return logHelper.logLevel(logLevel);
    }

    public static void m2015v(byte message) {
        logHelper.m2080v(message);
    }

    public static void m2016v(char message) {
        logHelper.m2081v(message);
    }

    public static void m2026v(short message) {
        logHelper.m2091v(message);
    }

    public static void m2019v(int message) {
        logHelper.m2084v(message);
    }

    public static void m2020v(long message) {
        logHelper.m2085v(message);
    }

    public static void m2018v(float message) {
        logHelper.m2083v(message);
    }

    public static void m2017v(double message) {
        logHelper.m2082v(message);
    }

    public static void m2027v(boolean message) {
        logHelper.m2092v(message);
    }

    public static void m2023v(String message) {
        logHelper.m2088v(message);
    }

    public static void m2025v(JSONObject message) {
        logHelper.m2090v(message);
    }

    public static void m2024v(JSONArray message) {
        logHelper.m2089v(message);
    }

    public static void m2021v(Exception message) {
        logHelper.m2086v(message);
    }

    public static void m2022v(Object message) {
        logHelper.m2087v(message);
    }

    public static void m1976d(byte message) {
        logHelper.m2041d(message);
    }

    public static void m1977d(char message) {
        logHelper.m2042d(message);
    }

    public static void m1987d(short message) {
        logHelper.m2052d(message);
    }

    public static void m1980d(int message) {
        logHelper.m2045d(message);
    }

    public static void m1981d(long message) {
        logHelper.m2046d(message);
    }

    public static void m1979d(float message) {
        logHelper.m2044d(message);
    }

    public static void m1978d(double message) {
        logHelper.m2043d(message);
    }

    public static void m1988d(boolean message) {
        logHelper.m2053d(message);
    }

    public static void m1984d(String message) {
        logHelper.m2049d(message);
    }

    public static void m1986d(JSONObject message) {
        logHelper.m2051d(message);
    }

    public static void m1985d(JSONArray message) {
        logHelper.m2050d(message);
    }

    public static void m1982d(Exception message) {
        logHelper.m2047d(message);
    }

    public static void m1983d(Object message) {
        logHelper.m2048d(message);
    }

    public static void m2002i(byte message) {
        logHelper.m2067i(message);
    }

    public static void m2003i(char message) {
        logHelper.m2068i(message);
    }

    public static void m2013i(short message) {
        logHelper.m2078i(message);
    }

    public static void m2006i(int message) {
        logHelper.m2071i(message);
    }

    public static void m2007i(long message) {
        logHelper.m2072i(message);
    }

    public static void m2005i(float message) {
        logHelper.m2070i(message);
    }

    public static void m2004i(double message) {
        logHelper.m2069i(message);
    }

    public static void m2014i(boolean message) {
        logHelper.m2079i(message);
    }

    public static void m2010i(String message) {
        logHelper.m2075i(message);
    }

    public static void m2012i(JSONObject message) {
        logHelper.m2077i(message);
    }

    public static void m2011i(JSONArray message) {
        logHelper.m2076i(message);
    }

    public static void m2008i(Exception message) {
        logHelper.m2073i(message);
    }

    public static void m2009i(Object message) {
        logHelper.m2074i(message);
    }

    public static void m2028w(byte message) {
        logHelper.m2093w(message);
    }

    public static void m2029w(char message) {
        logHelper.m2094w(message);
    }

    public static void m2039w(short message) {
        logHelper.m2104w(message);
    }

    public static void m2032w(int message) {
        logHelper.m2097w(message);
    }

    public static void m2033w(long message) {
        logHelper.m2098w(message);
    }

    public static void m2031w(float message) {
        logHelper.m2096w(message);
    }

    public static void m2030w(double message) {
        logHelper.m2095w(message);
    }

    public static void m2040w(boolean message) {
        logHelper.m2105w(message);
    }

    public static void m2036w(String message) {
        logHelper.m2101w(message);
    }

    public static void m2038w(JSONObject message) {
        logHelper.m2103w(message);
    }

    public static void m2037w(JSONArray message) {
        logHelper.m2102w(message);
    }

    public static void m2034w(Exception message) {
        logHelper.m2099w(message);
    }

    public static void m2035w(Object message) {
        logHelper.m2100w(message);
    }

    public static void m1989e(byte message) {
        logHelper.m2054e(message);
    }

    public static void m1990e(char message) {
        logHelper.m2055e(message);
    }

    public static void m2000e(short message) {
        logHelper.m2065e(message);
    }

    public static void m1993e(int message) {
        logHelper.m2058e(message);
    }

    public static void m1994e(long message) {
        logHelper.m2059e(message);
    }

    public static void m1992e(float message) {
        logHelper.m2057e(message);
    }

    public static void m1991e(double message) {
        logHelper.m2056e(message);
    }

    public static void m2001e(boolean message) {
        logHelper.m2066e(message);
    }

    public static void m1997e(String message) {
        logHelper.m2062e(message);
    }

    public static void m1999e(JSONObject message) {
        logHelper.m2064e(message);
    }

    public static void m1998e(JSONArray message) {
        logHelper.m2063e(message);
    }

    public static void m1995e(Exception message) {
        logHelper.m2060e(message);
    }

    public static void m1996e(Object message) {
        logHelper.m2061e(message);
    }

    public static void wtf(byte message) {
        logHelper.wtf(message);
    }

    public static void wtf(char message) {
        logHelper.wtf(message);
    }

    public static void wtf(short message) {
        logHelper.wtf(message);
    }

    public static void wtf(int message) {
        logHelper.wtf(message);
    }

    public static void wtf(long message) {
        logHelper.wtf(message);
    }

    public static void wtf(float message) {
        logHelper.wtf(message);
    }

    public static void wtf(double message) {
        logHelper.wtf(message);
    }

    public static void wtf(boolean message) {
        logHelper.wtf(message);
    }

    public static void wtf(String message) {
        logHelper.wtf(message);
    }

    public static void wtf(JSONObject message) {
        logHelper.wtf(message);
    }

    public static void wtf(JSONArray message) {
        logHelper.wtf(message);
    }

    public static void wtf(Exception message) {
        logHelper.wtf(message);
    }

    public static void wtf(Object message) {
        logHelper.wtf(message);
    }

    public static void json(String jsonString) {
        json(LogLevel.DEBUG, jsonString);
    }

    public static void json(LogLevel logLevel, String jsonString) {
        logHelper.json(logLevel, jsonString);
    }

    public static void xml(String xmlString) {
        xml(LogLevel.DEBUG, xmlString);
    }

    public static void xml(LogLevel logLevel, String jsonString) {
        logHelper.xml(logLevel, jsonString);
    }
}
