package com.cooey.common.vo;

public class ActionItemTimeHeader implements ListItem {
    private String actionItemHeader;

    public ActionItemTimeHeader(String actionItemHeader) {
        this.actionItemHeader = actionItemHeader;
    }

    public String getActionItemHeader() {
        return this.actionItemHeader;
    }

    public void setActionItemHeader(String actionItemHeader) {
        this.actionItemHeader = actionItemHeader;
    }

    public int getListItemType() {
        return 0;
    }
}
