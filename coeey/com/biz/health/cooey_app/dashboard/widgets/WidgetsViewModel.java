package com.biz.health.cooey_app.dashboard.widgets;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.cooey.common.vo.User;
import com.cooey.common.vo.Vital;
import java.util.List;

public class WidgetsViewModel extends BaseObservable {
    private final Context context;
    private final User user;
    private List<Vital> vitalList;
    private WidgetRecyclerAdapter widgetRecyclerAdapter;

    @Bindable
    public List<Vital> getVitalList() {
        return this.vitalList;
    }

    public void setVitalList(List<Vital> vitalList) {
        this.vitalList = vitalList;
        notifyPropertyChanged(55);
    }

    public WidgetsViewModel(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public void setWidgetRecyclerAdapter(WidgetRecyclerAdapter widgetRecyclerAdapter) {
        this.widgetRecyclerAdapter = widgetRecyclerAdapter;
    }

    public WidgetRecyclerAdapter getWidgetRecyclerAdapter() {
        return this.widgetRecyclerAdapter;
    }
}
