package com.cooey.common.maya;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.cooey.common.BR;

public class MayaViewModel extends BaseObservable {
    private MayaRecyclerAdapter mayaRecyclerAdapter;
    private String message;

    @Bindable
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public MayaRecyclerAdapter getMayaRecyclerAdapter() {
        return this.mayaRecyclerAdapter;
    }

    public void setMayaRecyclerAdapter(MayaRecyclerAdapter mayaRecyclerAdapter) {
        this.mayaRecyclerAdapter = mayaRecyclerAdapter;
        notifyPropertyChanged(BR.mayaRecyclerAdapter);
    }
}
