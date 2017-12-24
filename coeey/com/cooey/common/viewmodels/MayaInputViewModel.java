package com.cooey.common.viewmodels;

import android.databinding.BaseObservable;

public class MayaInputViewModel extends BaseObservable {
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
