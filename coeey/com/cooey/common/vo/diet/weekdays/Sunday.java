package com.cooey.common.vo.diet.weekdays;

import com.cooey.common.vo.diet.time.BreakFast;
import com.cooey.common.vo.diet.time.Dinner;
import com.cooey.common.vo.diet.time.Lunch;
import java.io.Serializable;

public class Sunday implements Serializable {
    private BreakFast breakFast;
    private Dinner dinner;
    private Lunch lunch;

    public Sunday(BreakFast breakFast, Lunch lunch, Dinner dinner) {
        this.breakFast = breakFast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public BreakFast getBreakFast() {
        return this.breakFast;
    }

    public void setBreakFast(BreakFast breakFast) {
        this.breakFast = breakFast;
    }

    public Lunch getLunch() {
        return this.lunch;
    }

    public void setLunch(Lunch lunch) {
        this.lunch = lunch;
    }

    public Dinner getDinner() {
        return this.dinner;
    }

    public void setDinner(Dinner dinner) {
        this.dinner = dinner;
    }
}
