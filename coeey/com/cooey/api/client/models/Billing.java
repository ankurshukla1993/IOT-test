package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Billing {
    @SerializedName("address")
    private String address = null;
    @SerializedName("amountPaid")
    private Double amountPaid = null;
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("balanceAmount")
    private Double balanceAmount = null;
    @SerializedName("billDate")
    private Long billDate = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("email")
    private String email = null;
    @SerializedName("grossTotalPrice")
    private Double grossTotalPrice = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("invoiceNo")
    private String invoiceNo = null;
    @SerializedName("mobile")
    private String mobile = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("productList")
    private List<Product> productList = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("totalAmount")
    private Double totalAmount = null;
    @SerializedName("totalTax")
    private Double totalTax = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("userId")
    private String userId = null;

    public Billing createdOn(Long createdOn) {
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

    public Billing updatedOn(Long updatedOn) {
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

    public Billing tenantId(String tenantId) {
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

    public Billing applicationId(String applicationId) {
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

    public Billing id(String id) {
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

    public Billing name(String name) {
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

    public Billing userId(String userId) {
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

    public Billing email(String email) {
        this.email = email;
        return this;
    }

    @ApiModelProperty("")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Billing address(String address) {
        this.address = address;
        return this;
    }

    @ApiModelProperty("")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Billing mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @ApiModelProperty("")
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Billing productList(List<Product> productList) {
        this.productList = productList;
        return this;
    }

    public Billing addProductListItem(Product productListItem) {
        if (this.productList == null) {
            this.productList = new ArrayList();
        }
        this.productList.add(productListItem);
        return this;
    }

    @ApiModelProperty("")
    public List<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Billing grossTotalPrice(Double grossTotalPrice) {
        this.grossTotalPrice = grossTotalPrice;
        return this;
    }

    @ApiModelProperty("")
    public Double getGrossTotalPrice() {
        return this.grossTotalPrice;
    }

    public void setGrossTotalPrice(Double grossTotalPrice) {
        this.grossTotalPrice = grossTotalPrice;
    }

    public Billing invoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
        return this;
    }

    @ApiModelProperty("")
    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Billing totalTax(Double totalTax) {
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

    public Billing totalAmount(Double totalAmount) {
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

    public Billing balanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
        return this;
    }

    @ApiModelProperty("")
    public Double getBalanceAmount() {
        return this.balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Billing amountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    @ApiModelProperty("")
    public Double getAmountPaid() {
        return this.amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Billing billDate(Long billDate) {
        this.billDate = billDate;
        return this;
    }

    @ApiModelProperty("")
    public Long getBillDate() {
        return this.billDate;
    }

    public void setBillDate(Long billDate) {
        this.billDate = billDate;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Billing billing = (Billing) o;
        if (Objects.equals(this.createdOn, billing.createdOn) && Objects.equals(this.updatedOn, billing.updatedOn) && Objects.equals(this.tenantId, billing.tenantId) && Objects.equals(this.applicationId, billing.applicationId) && Objects.equals(this.id, billing.id) && Objects.equals(this.name, billing.name) && Objects.equals(this.userId, billing.userId) && Objects.equals(this.email, billing.email) && Objects.equals(this.address, billing.address) && Objects.equals(this.mobile, billing.mobile) && Objects.equals(this.productList, billing.productList) && Objects.equals(this.grossTotalPrice, billing.grossTotalPrice) && Objects.equals(this.invoiceNo, billing.invoiceNo) && Objects.equals(this.totalTax, billing.totalTax) && Objects.equals(this.totalAmount, billing.totalAmount) && Objects.equals(this.balanceAmount, billing.balanceAmount) && Objects.equals(this.amountPaid, billing.amountPaid) && Objects.equals(this.billDate, billing.billDate)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.name, this.userId, this.email, this.address, this.mobile, this.productList, this.grossTotalPrice, this.invoiceNo, this.totalTax, this.totalAmount, this.balanceAmount, this.amountPaid, this.billDate});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Billing {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    userId: ").append(toIndentedString(this.userId)).append("\n");
        sb.append("    email: ").append(toIndentedString(this.email)).append("\n");
        sb.append("    address: ").append(toIndentedString(this.address)).append("\n");
        sb.append("    mobile: ").append(toIndentedString(this.mobile)).append("\n");
        sb.append("    productList: ").append(toIndentedString(this.productList)).append("\n");
        sb.append("    grossTotalPrice: ").append(toIndentedString(this.grossTotalPrice)).append("\n");
        sb.append("    invoiceNo: ").append(toIndentedString(this.invoiceNo)).append("\n");
        sb.append("    totalTax: ").append(toIndentedString(this.totalTax)).append("\n");
        sb.append("    totalAmount: ").append(toIndentedString(this.totalAmount)).append("\n");
        sb.append("    balanceAmount: ").append(toIndentedString(this.balanceAmount)).append("\n");
        sb.append("    amountPaid: ").append(toIndentedString(this.amountPaid)).append("\n");
        sb.append("    billDate: ").append(toIndentedString(this.billDate)).append("\n");
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
