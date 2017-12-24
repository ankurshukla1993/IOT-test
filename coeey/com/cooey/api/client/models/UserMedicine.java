package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserMedicine {
    @SerializedName("addedOn")
    private Long addedOn = null;
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("color")
    private String color = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("medicineId")
    private String medicineId = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("notes")
    private String notes = null;
    @SerializedName("reminders")
    private List<Reminder> reminders = null;
    @SerializedName("shape")
    private String shape = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("toBeTakenTill")
    private Long toBeTakenTill = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("userId")
    private String userId = null;

    public UserMedicine createdOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public UserMedicine updatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public UserMedicine tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @ApiModelProperty("")
    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public UserMedicine applicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    @ApiModelProperty("")
    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public UserMedicine name(String name) {
        this.name = name;
        return this;
    }

    @ApiModelProperty("")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserMedicine medicineId(String medicineId) {
        this.medicineId = medicineId;
        return this;
    }

    @ApiModelProperty("")
    public String getMedicineId() {
        return this.medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public UserMedicine addedOn(Long addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getAddedOn() {
        return this.addedOn;
    }

    public void setAddedOn(Long addedOn) {
        this.addedOn = addedOn;
    }

    public UserMedicine toBeTakenTill(Long toBeTakenTill) {
        this.toBeTakenTill = toBeTakenTill;
        return this;
    }

    @ApiModelProperty("")
    public Long getToBeTakenTill() {
        return this.toBeTakenTill;
    }

    public void setToBeTakenTill(Long toBeTakenTill) {
        this.toBeTakenTill = toBeTakenTill;
    }

    public UserMedicine reminders(List<Reminder> reminders) {
        this.reminders = reminders;
        return this;
    }

    public UserMedicine addRemindersItem(Reminder remindersItem) {
        if (this.reminders == null) {
            this.reminders = new ArrayList();
        }
        this.reminders.add(remindersItem);
        return this;
    }

    @ApiModelProperty("")
    public List<Reminder> getReminders() {
        return this.reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public UserMedicine description(String description) {
        this.description = description;
        return this;
    }

    @ApiModelProperty("")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserMedicine userId(String userId) {
        this.userId = userId;
        return this;
    }

    @ApiModelProperty("")
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserMedicine notes(String notes) {
        this.notes = notes;
        return this;
    }

    @ApiModelProperty("")
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UserMedicine color(String color) {
        this.color = color;
        return this;
    }

    @ApiModelProperty("")
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UserMedicine shape(String shape) {
        this.shape = shape;
        return this;
    }

    @ApiModelProperty("")
    public String getShape() {
        return this.shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserMedicine userMedicine = (UserMedicine) o;
        if (Objects.equals(this.createdOn, userMedicine.createdOn) && Objects.equals(this.updatedOn, userMedicine.updatedOn) && Objects.equals(this.tenantId, userMedicine.tenantId) && Objects.equals(this.applicationId, userMedicine.applicationId) && Objects.equals(this.name, userMedicine.name) && Objects.equals(this.medicineId, userMedicine.medicineId) && Objects.equals(this.addedOn, userMedicine.addedOn) && Objects.equals(this.toBeTakenTill, userMedicine.toBeTakenTill) && Objects.equals(this.reminders, userMedicine.reminders) && Objects.equals(this.description, userMedicine.description) && Objects.equals(this.userId, userMedicine.userId) && Objects.equals(this.notes, userMedicine.notes) && Objects.equals(this.color, userMedicine.color) && Objects.equals(this.shape, userMedicine.shape)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.name, this.medicineId, this.addedOn, this.toBeTakenTill, this.reminders, this.description, this.userId, this.notes, this.color, this.shape});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserMedicine {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    medicineId: ").append(toIndentedString(this.medicineId)).append("\n");
        sb.append("    addedOn: ").append(toIndentedString(this.addedOn)).append("\n");
        sb.append("    toBeTakenTill: ").append(toIndentedString(this.toBeTakenTill)).append("\n");
        sb.append("    reminders: ").append(toIndentedString(this.reminders)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    userId: ").append(toIndentedString(this.userId)).append("\n");
        sb.append("    notes: ").append(toIndentedString(this.notes)).append("\n");
        sb.append("    color: ").append(toIndentedString(this.color)).append("\n");
        sb.append("    shape: ").append(toIndentedString(this.shape)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
