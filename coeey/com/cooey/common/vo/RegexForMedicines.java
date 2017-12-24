package com.cooey.common.vo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class RegexForMedicines {
    private HashMap<String, String> splitKeyAndValue(String medicines) {
        HashMap<String, String> medicineTimingWithDays = new HashMap();
        String[] medidineString = medicines.split(Pattern.quote("|"));
        if (medidineString.length == 2) {
            medicineTimingWithDays.put(medidineString[1], medidineString[0]);
        }
        return medicineTimingWithDays;
    }

    private String getTimingFromMedicine(String medicine) {
        String[] medidineString = medicine.split(Pattern.quote("|"));
        if (medidineString == null || medidineString.length <= 0 || medidineString.length != 2) {
            return null;
        }
        return medidineString[1];
    }

    private List<String> getDaysFromMedicine(String medicine) {
        String[] medidineString = medicine.split(Pattern.quote("|"));
        if (medidineString == null || medidineString.length <= 0 || medidineString.length != 2) {
            return null;
        }
        return Arrays.asList(medidineString[0].split(Pattern.quote(",")));
    }
}
