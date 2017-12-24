package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("discountApplied")
    private Double discountApplied = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("quantity")
    private Integer quantity = null;
    @SerializedName("serviceable")
    private Boolean serviceable = Boolean.valueOf(false);
    @SerializedName("taxApplied")
    private List<Tax> taxApplied = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("totalAmount")
    private Double totalAmount = null;
    @SerializedName("totalTax")
    private Double totalTax = null;
    @SerializedName("unitPrice")
    private Integer unitPrice = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public Product createdOn(Long createdOn) {
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

    public Product updatedOn(Long updatedOn) {
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

    public Product tenantId(String tenantId) {
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

    public Product applicationId(String applicationId) {
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

    public Product id(String id) {
        this.id = id;
        return this;
    }

    @ApiModelProperty("")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product name(String name) {
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

    public Product description(String description) {
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

    public Product taxApplied(List<Tax> taxApplied) {
        this.taxApplied = taxApplied;
        return this;
    }

    public Product addTaxAppliedItem(Tax taxAppliedItem) {
        if (this.taxApplied == null) {
            this.taxApplied = new ArrayList();
        }
        this.taxApplied.add(taxAppliedItem);
        return this;
    }

    @ApiModelProperty("")
    public List<Tax> getTaxApplied() {
        return this.taxApplied;
    }

    public void setTaxApplied(List<Tax> taxApplied) {
        this.taxApplied = taxApplied;
    }

    public Product discountApplied(Double discountApplied) {
        this.discountApplied = discountApplied;
        return this;
    }

    @ApiModelProperty("")
    public Double getDiscountApplied() {
        return this.discountApplied;
    }

    public void setDiscountApplied(Double discountApplied) {
        this.discountApplied = discountApplied;
    }

    public Product quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @ApiModelProperty("")
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product totalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    @ApiModelProperty("")
    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Product unitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    @ApiModelProperty("")
    public Integer getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product totalTax(Double totalTax) {
        this.totalTax = totalTax;
        return this;
    }

    @ApiModelProperty("")
    public Double getTotalTax() {
        return this.totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Product serviceable(Boolean serviceable) {
        this.serviceable = serviceable;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getServiceable() {
        return this.serviceable;
    }

    public void setServiceable(Boolean serviceable) {
        this.serviceable = serviceable;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        if (Objects.equals(this.createdOn, product.createdOn) && Objects.equals(this.updatedOn, product.updatedOn) && Objects.equals(this.tenantId, product.tenantId) && Objects.equals(this.applicationId, product.applicationId) && Objects.equals(this.id, product.id) && Objects.equals(this.name, product.name) && Objects.equals(this.description, product.description) && Objects.equals(this.taxApplied, product.taxApplied) && Objects.equals(this.discountApplied, product.discountApplied) && Objects.equals(this.quantity, product.quantity) && Objects.equals(this.totalAmount, product.totalAmount) && Objects.equals(this.unitPrice, product.unitPrice) && Objects.equals(this.totalTax, product.totalTax) && Objects.equals(this.serviceable, product.serviceable)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.name, this.description, this.taxApplied, this.discountApplied, this.quantity, this.totalAmount, this.unitPrice, this.totalTax, this.serviceable});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Product {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    taxApplied: ").append(toIndentedString(this.taxApplied)).append("\n");
        sb.append("    discountApplied: ").append(toIndentedString(this.discountApplied)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(this.quantity)).append("\n");
        sb.append("    totalAmount: ").append(toIndentedString(this.totalAmount)).append("\n");
        sb.append("    unitPrice: ").append(toIndentedString(this.unitPrice)).append("\n");
        sb.append("    totalTax: ").append(toIndentedString(this.totalTax)).append("\n");
        sb.append("    serviceable: ").append(toIndentedString(this.serviceable)).append("\n");
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
