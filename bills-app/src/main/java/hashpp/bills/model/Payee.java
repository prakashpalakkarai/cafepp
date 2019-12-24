package hashpp.bills.model;
// Generated Dec 2, 2019 12:32:32 PM by Hibernate Tools 3.2.2.GA


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Payee generated by hbm2java
 */
@Entity
@Table(name="payee"
    ,catalog="billpay"
)
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Payee  implements java.io.Serializable {


     private Integer payeeId;
     private PaymentMethods paymentMethods;
     private BillSchedules billSchedules;
     private String name;
     private String type;
     private BigDecimal dueAmount;
     private BigDecimal totalBalance;
     private BigDecimal interest;
     private Date promotionExpireDt;
     private Integer accountNum;
     private Set<BillPayments> billPaymentses = new HashSet<BillPayments>(0);

    public Payee() {
    }

	
    public Payee(String name, String type, BigDecimal dueAmount, BigDecimal totalBalance, BigDecimal interest) {
        this.name = name;
        this.type = type;
        this.dueAmount = dueAmount;
        this.totalBalance = totalBalance;
        this.interest = interest;
    }
    public Payee(PaymentMethods paymentMethods, BillSchedules billSchedules, String name, String type, BigDecimal dueAmount, BigDecimal totalBalance, BigDecimal interest, Date promotionExpireDt, Integer accountNum, Set<BillPayments> billPaymentses) {
       this.paymentMethods = paymentMethods;
       this.billSchedules = billSchedules;
       this.name = name;
       this.type = type;
       this.dueAmount = dueAmount;
       this.totalBalance = totalBalance;
       this.interest = interest;
       this.promotionExpireDt = promotionExpireDt;
       this.accountNum = accountNum;
       this.billPaymentses = billPaymentses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="payee_id", unique=true, nullable=false)
    public Integer getPayeeId() {
        return this.payeeId;
    }
    
    public void setPayeeId(Integer payeeId) {
        this.payeeId = payeeId;
    }
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pmt_method_id")
    public PaymentMethods getPaymentMethods() {
        return this.paymentMethods;
    }
    
    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bill_sch_id")
    public BillSchedules getBillSchedules() {
        return this.billSchedules;
    }
    
    public void setBillSchedules(BillSchedules billSchedules) {
        this.billSchedules = billSchedules;
    }
    
    @Column(name="name", nullable=false, length=40)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="type", nullable=false, length=40)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="due_amount", nullable=false, precision=7)
    public BigDecimal getDueAmount() {
        return this.dueAmount;
    }
    
    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }
    
    @Column(name="total_balance", nullable=false, precision=7)
    public BigDecimal getTotalBalance() {
        return this.totalBalance;
    }
    
    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }
    
    @Column(name="interest", nullable=false, precision=7)
    public BigDecimal getInterest() {
        return this.interest;
    }
    
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="promotion_expire_dt", length=10)
    public Date getPromotionExpireDt() {
        return this.promotionExpireDt;
    }
    
    public void setPromotionExpireDt(Date promotionExpireDt) {
        this.promotionExpireDt = promotionExpireDt;
    }
    
    @Column(name="account_num")
    public Integer getAccountNum() {
        return this.accountNum;
    }
    
    public void setAccountNum(Integer accountNum) {
        this.accountNum = accountNum;
    }
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="payee")
    public Set<BillPayments> getBillPaymentses() {
        return this.billPaymentses;
    }

    public void setBillPaymentses(Set<BillPayments> billPaymentses) {
        this.billPaymentses = billPaymentses;
    }

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;


}

