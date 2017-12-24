package com.biz.health.cooey_app.medicine;

public class MedicineReminderSchedule {
    private String medicineReminder;

    public MedicineReminderSchedule(String medicineReminder, String medicineNotes) {
        this.medicineReminder = medicineReminder;
    }

    public String getMedicineReminder() {
        return this.medicineReminder;
    }

    public void setMedicineReminder(String medicineReminder) {
        this.medicineReminder = medicineReminder;
    }
}
