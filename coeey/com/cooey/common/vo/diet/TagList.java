package com.cooey.common.vo.diet;

import java.io.Serializable;
import java.util.Map;

public class TagList implements Serializable {
    private Map<String, String> tagListValue;

    public TagList(Map<String, String> tagListValue) {
        this.tagListValue = tagListValue;
    }

    public Map<String, String> getTagListValue() {
        return this.tagListValue;
    }

    public void setTagListValue(Map<String, String> tagListValue) {
        this.tagListValue = tagListValue;
    }
}
