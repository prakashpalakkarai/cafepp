package hashpp.bills.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BillPayments implements java.io.Serializable {

     private Integer pmtId;
     private Integer payeeId;
     private String pmtMethodId;
     private String billSchId;
     private String name;
     private String type;
     private BigDecimal dueAmount;
     private BigDecimal totalBalance;
     private BigDecimal interest;
     private Date promotionExpireDt;
     private Integer accountNum;
     private Date pmtScheduleDt;
     private BigDecimal amount;
     private String pmtStatus;

    public BillPayments() {
    }

    public Integer getPmtId() {
        return pmtId;
    }

    public void setPmtId(Integer pmtId) {
        this.pmtId = pmtId;
    }

    public Integer getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Integer payeeId) {
        this.payeeId = payeeId;
    }

    public String getPmtMethodId() {
        return pmtMethodId;
    }

    public void setPmtMethodId(String pmtMethodId) {
        this.pmtMethodId = pmtMethodId;
    }

    public String getBillSchId() {
        return billSchId;
    }

    public void setBillSchId(String billSchId) {
        this.billSchId = billSchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Date getPromotionExpireDt() {
        return promotionExpireDt;
    }

    public void setPromotionExpireDt(Date promotionExpireDt) {
        this.promotionExpireDt = promotionExpireDt;
    }

    public Integer getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Integer accountNum) {
        this.accountNum = accountNum;
    }

    public Date getPmtScheduleDt() {
        return pmtScheduleDt;
    }

    public void setPmtScheduleDt(Date pmtScheduleDt) {
        this.pmtScheduleDt = pmtScheduleDt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPmtStatus() {
        return pmtStatus;
    }

    public void setPmtStatus(String pmtStatus) {
        this.pmtStatus = pmtStatus;
    }
}
