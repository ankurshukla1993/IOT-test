package com.cooey.maya;

import chatkit.Content;
import chatkit.MayaIntent;
import java.util.List;

public class MayaResponse {
    private List<Content> contentList;
    private String contextId;
    private String dialog;
    private MayaIntent expectedIntent;
    private String intentId;

    public String getDialog() {
        return this.dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public MayaIntent getExpectedIntent() {
        return this.expectedIntent;
    }

    public void setExpectedIntent(MayaIntent expectedIntent) {
        this.expectedIntent = expectedIntent;
    }

    public String getContextId() {
        return this.contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getIntentId() {
        return this.intentId;
    }

    public void setIntentId(String intentId) {
        this.intentId = intentId;
    }

    public List<Content> getContentList() {
        return this.contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }
}
