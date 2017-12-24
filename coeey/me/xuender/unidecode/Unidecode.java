package me.xuender.unidecode;

import humanize.util.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Unidecode {
    private static final String[][] cache = new String[256][];

    public static String decode(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int codepoint = str.codePointAt(i);
            if (codepoint < 128) {
                sb.append(c);
            } else if (codepoint <= 65535) {
                int position = codepoint % 256;
                String[] table = getCache(codepoint >> 8);
                if (table != null && table.length > position) {
                    sb.append(table[position]);
                }
            }
        }
        return sb.toString().trim();
    }

    private static String[] getCache(int section) {
        String[] ret = cache[section];
        if (ret == null) {
            InputStream inStream = null;
            try {
                inStream = Unidecode.class.getResourceAsStream(String.format("/data/X%03x", new Object[]{Integer.valueOf(section)}));
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                ret = new String[256];
                int i = 0;
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    ret[i] = line;
                    i++;
                }
                cache[section] = ret;
                if (inStream != null) {
                    try {
                        inStream.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Exception e2) {
                cache[section] = new String[0];
                if (inStream != null) {
                    try {
                        inStream.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (Throwable th) {
                if (inStream != null) {
                    try {
                        inStream.close();
                    } catch (IOException e4) {
                    }
                }
            }
        } else if (ret.length == 0) {
            return null;
        }
        return ret;
    }

    public static String initials(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Matcher m = Pattern.compile("^\\w|\\s+\\w").matcher(decode(str));
        while (m.find()) {
            sb.append(m.group().replaceAll(Constants.SPACE, ""));
        }
        return sb.toString();
    }

    private Unidecode() {
    }
}
