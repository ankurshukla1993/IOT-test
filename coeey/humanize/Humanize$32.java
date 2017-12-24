package humanize;

import humanize.text.util.Replacer;

class Humanize$32 implements Replacer {
    Humanize$32() {
    }

    public String replace(String in) {
        StringBuilder uc = new StringBuilder();
        for (char c : in.toCharArray()) {
            uc.append("\\\\u");
            uc.append(Integer.toHexString(c).toUpperCase());
        }
        return uc.toString();
    }
}
