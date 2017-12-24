package com.cooey.common.vo.diet.weekdays;

import com.cooey.common.vo.diet.time.BreakFast;
import com.cooey.common.vo.diet.time.Dinner;
import com.cooey.common.vo.diet.time.Lunch;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.SaturdayRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Saturday extends RealmObject implements Serializable, SaturdayRealmProxyInterface {
    @SerializedName("breakfast")
    private BreakFast breakFast;
    @SerializedName("dinner")
    private Dinner dinner;
    @SerializedName("lunch")
    private Lunch lunch;

    public BreakFast realmGet$breakFast() {
        return this.breakFast;
    }

    public Dinner realmGet$dinner() {
        return this.dinner;
    }

    public Lunch realmGet$lunch() {
        return this.lunch;
    }

    public void realmSet$breakFast(BreakFast breakFast) {
        this.breakFast = breakFast;
    }

    public void realmSet$dinner(Dinner dinner) {
        this.dinner = dinner;
    }

    public void realmSet$lunch(Lunch lunch) {
        this.lunch = lunch;
    }

    public Saturday(BreakFast breakFast, Lunch lunch, Dinner dinner) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$breakFast(breakFast);
        realmSet$lunch(lunch);
        realmSet$dinner(dinner);
    }

    public Saturday() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public BreakFast getBreakFast() {
        return realmGet$breakFast();
    }

    public void setBreakFast(BreakFast breakFast) {
        realmSet$breakFast(breakFast);
    }

    public Lunch getLunch() {
        return realmGet$lunch();
    }

    public void setLunch(Lunch lunch) {
        realmSet$lunch(lunch);
    }

    public Dinner getDinner() {
        return realmGet$dinner();
    }

    public void setDinner(Dinner dinner) {
        realmSet$dinner(dinner);
    }
}
