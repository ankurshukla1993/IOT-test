package com.cooey.maya;

import chatkit.MayaIntent;

public class MayaRequest {
    private String contentId;
    private String contextId;
    private MayaIntent expectedIntent;
    private String query;

    public MayaIntent getExpectedIntent() {
        return this.expectedIntent;
    }

    public void setExpectedIntent(MayaIntent expectedIntent) {
        this.expectedIntent = expectedIntent;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getContextId() {
        return this.contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
