package com.cooey.common.vo;

public interface ListItem {
    public static final int ACTION_ITEM = 2;
    public static final int ACTION_ITEM_HEADER_PATIENT = 1;
    public static final int ACTION_ITEM_HEADER_TIME = 0;
    public static final int ACTION_ITEM_PATIENT = 3;

    int getListItemType();
}
