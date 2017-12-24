package com.thefinestartist.finestwebview.helpers;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlParser {
    public static String getHost(String url) {
        try {
            url = new URL(url).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
