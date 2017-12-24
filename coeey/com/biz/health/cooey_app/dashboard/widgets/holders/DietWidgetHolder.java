package com.biz.health.cooey_app.dashboard.widgets.holders;

import android.databinding.ViewDataBinding;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.vo.User;
import java.util.HashMap;

public class DietWidgetHolder extends WidgetViewHolder {
    public DietWidgetHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding);
    }

    public void bind(User user) {
        HashMap<Integer, Integer> map = new HashMap();
        map.put(Integer.valueOf(1), Integer.valueOf(C0644R.drawable.diet2));
        map.put(Integer.valueOf(2), Integer.valueOf(C0644R.drawable.diet3));
        map.put(Integer.valueOf(3), Integer.valueOf(C0644R.drawable.diet4));
        int imgResId = ((Integer) map.get(Integer.valueOf(((int) (Math.random() * 2.0d)) + 1))).intValue();
    }
}
