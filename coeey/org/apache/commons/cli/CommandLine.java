package org.apache.commons.cli;

import com.facebook.internal.ServerProtocol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class CommandLine implements Serializable {
    private static final long serialVersionUID = 1;
    private List args = new LinkedList();
    private List options = new ArrayList();

    CommandLine() {
    }

    public boolean hasOption(String opt) {
        return this.options.contains(resolveOption(opt));
    }

    public boolean hasOption(char opt) {
        return hasOption(String.valueOf(opt));
    }

    public Object getOptionObject(String opt) {
        try {
            return getParsedOptionValue(opt);
        } catch (ParseException pe) {
            System.err.println(new StringBuffer().append("Exception found converting ").append(opt).append(" to desired type: ").append(pe.getMessage()).toString());
            return null;
        }
    }

    public Object getParsedOptionValue(String opt) throws ParseException {
        String res = getOptionValue(opt);
        Option option = resolveOption(opt);
        if (option == null) {
            return null;
        }
        Object type = option.getType();
        if (res != null) {
            return TypeHandler.createValue(res, type);
        }
        return null;
    }

    public Object getOptionObject(char opt) {
        return getOptionObject(String.valueOf(opt));
    }

    public String getOptionValue(String opt) {
        String[] values = getOptionValues(opt);
        return values == null ? null : values[0];
    }

    public String getOptionValue(char opt) {
        return getOptionValue(String.valueOf(opt));
    }

    public String[] getOptionValues(String opt) {
        List values = new ArrayList();
        for (Option option : this.options) {
            if (opt.equals(option.getOpt()) || opt.equals(option.getLongOpt())) {
                values.addAll(option.getValuesList());
            }
        }
        return values.isEmpty() ? null : (String[]) values.toArray(new String[values.size()]);
    }

    private Option resolveOption(String opt) {
        opt = Util.stripLeadingHyphens(opt);
        for (Option option : this.options) {
            if (opt.equals(option.getOpt())) {
                return option;
            }
            if (opt.equals(option.getLongOpt())) {
                return option;
            }
        }
        return null;
    }

    public String[] getOptionValues(char opt) {
        return getOptionValues(String.valueOf(opt));
    }

    public String getOptionValue(String opt, String defaultValue) {
        String answer = getOptionValue(opt);
        return answer != null ? answer : defaultValue;
    }

    public String getOptionValue(char opt, String defaultValue) {
        return getOptionValue(String.valueOf(opt), defaultValue);
    }

    public Properties getOptionProperties(String opt) {
        Properties props = new Properties();
        for (Option option : this.options) {
            if (opt.equals(option.getOpt()) || opt.equals(option.getLongOpt())) {
                List values = option.getValuesList();
                if (values.size() >= 2) {
                    props.put(values.get(0), values.get(1));
                } else if (values.size() == 1) {
                    props.put(values.get(0), ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                }
            }
        }
        return props;
    }

    public String[] getArgs() {
        String[] answer = new String[this.args.size()];
        this.args.toArray(answer);
        return answer;
    }

    public List getArgList() {
        return this.args;
    }

    void addArg(String arg) {
        this.args.add(arg);
    }

    void addOption(Option opt) {
        this.options.add(opt);
    }

    public Iterator iterator() {
        return this.options.iterator();
    }

    public Option[] getOptions() {
        Collection processed = this.options;
        return (Option[]) processed.toArray(new Option[processed.size()]);
    }
}
