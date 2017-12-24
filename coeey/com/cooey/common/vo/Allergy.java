package com.cooey.common.vo;

import com.google.gson.annotations.SerializedName;
import io.realm.AllergyRealmProxyInterface;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import java.util.List;

public class Allergy extends RealmObject implements AllergyRealmProxyInterface {
    private transient AllergyLevelEnum allergyLevelEnum;
    @SerializedName("level")
    private String levelValue;
    private String name;
    private RealmList<RealmString> symptoms;

    public enum AllergyLevelEnum {
        LOW("LOW"),
        NORMAL("HIGH"),
        HIGH("HIGH"),
        SEVERE("SEVERE");
        
        private String value;

        private AllergyLevelEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }
    }

    public String realmGet$levelValue() {
        return this.levelValue;
    }

    public String realmGet$name() {
        return this.name;
    }

    public RealmList realmGet$symptoms() {
        return this.symptoms;
    }

    public void realmSet$levelValue(String str) {
        this.levelValue = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$symptoms(RealmList realmList) {
        this.symptoms = realmList;
    }

    public Allergy() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getAllergyLevelEnum() {
        return this.allergyLevelEnum.value;
    }

    public void setAllergyLevel(String allergyLevel) {
        realmSet$levelValue(allergyLevel);
    }

    public String getAllergyLevel() {
        return realmGet$levelValue();
    }

    public void setAllergyLevelEnum(AllergyLevelEnum allergyLevelEnum) {
        setAllergyLevel(allergyLevelEnum.value);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public void setAllergyLevel(AllergyLevelEnum allergyLevel) {
        setLevelValue(allergyLevel.toString());
    }

    public String getLevelValue() {
        return realmGet$levelValue();
    }

    public void setLevelValue(String levelValue) {
        realmSet$levelValue(levelValue);
    }

    public List<RealmString> getSymptoms() {
        return realmGet$symptoms();
    }

    public void setSymptoms(RealmList<RealmString> symptoms) {
        realmSet$symptoms(symptoms);
    }
}
