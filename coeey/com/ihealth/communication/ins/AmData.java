package com.ihealth.communication.ins;

import java.io.Serializable;
import java.util.List;

public class AmData implements Serializable {
    private List f1720a;

    public AmData(List mDatas) {
        this.f1720a = mDatas;
    }

    public List getmDatas() {
        return this.f1720a;
    }
}
