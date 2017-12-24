package org.apache.commons.cli;

import humanize.util.Constants;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OptionGroup implements Serializable {
    private static final long serialVersionUID = 1;
    private Map optionMap = new HashMap();
    private boolean required;
    private String selected;

    public OptionGroup addOption(Option option) {
        this.optionMap.put(option.getKey(), option);
        return this;
    }

    public Collection getNames() {
        return this.optionMap.keySet();
    }

    public Collection getOptions() {
        return this.optionMap.values();
    }

    public void setSelected(Option option) throws AlreadySelectedException {
        if (this.selected == null || this.selected.equals(option.getOpt())) {
            this.selected = option.getOpt();
            return;
        }
        throw new AlreadySelectedException(this, option);
    }

    public String getSelected() {
        return this.selected;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return this.required;
    }

    public String toString() {
        StringBuffer buff = new StringBuffer();
        Iterator iter = getOptions().iterator();
        buff.append("[");
        while (iter.hasNext()) {
            Option option = (Option) iter.next();
            if (option.getOpt() != null) {
                buff.append("-");
                buff.append(option.getOpt());
            } else {
                buff.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                buff.append(option.getLongOpt());
            }
            buff.append(Constants.SPACE);
            buff.append(option.getDescription());
            if (iter.hasNext()) {
                buff.append(", ");
            }
        }
        buff.append("]");
        return buff.toString();
    }
}
