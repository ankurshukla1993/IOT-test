package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.List;

public class GnuParser extends Parser {
    protected String[] flatten(Options options, String[] arguments, boolean stopAtNonOption) {
        List tokens = new ArrayList();
        boolean eatTheRest = false;
        int i = 0;
        while (i < arguments.length) {
            String arg = arguments[i];
            if (HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(arg)) {
                eatTheRest = true;
                tokens.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            } else if ("-".equals(arg)) {
                tokens.add("-");
            } else if (arg.startsWith("-")) {
                String opt = Util.stripLeadingHyphens(arg);
                if (options.hasOption(opt)) {
                    tokens.add(arg);
                } else if (opt.indexOf(61) != -1 && options.hasOption(opt.substring(0, opt.indexOf(61)))) {
                    tokens.add(arg.substring(0, arg.indexOf(61)));
                    tokens.add(arg.substring(arg.indexOf(61) + 1));
                } else if (options.hasOption(arg.substring(0, 2))) {
                    tokens.add(arg.substring(0, 2));
                    tokens.add(arg.substring(2));
                } else {
                    eatTheRest = stopAtNonOption;
                    tokens.add(arg);
                }
            } else {
                tokens.add(arg);
            }
            if (eatTheRest) {
                i++;
                while (i < arguments.length) {
                    tokens.add(arguments[i]);
                    i++;
                }
            }
            i++;
        }
        return (String[]) tokens.toArray(new String[tokens.size()]);
    }
}
