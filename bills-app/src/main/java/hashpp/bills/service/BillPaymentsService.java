package hashpp.bills.service;

import hashpp.bills.dto.BillPayments;

import java.util.List;
import java.util.Optional;

public interface BillPaymentsService {

    public List<BillPayments> getAllBillPayments();
    public BillPayments createBillPayment(BillPayments billPaymentsDto);
    Optional<BillPayments> getBillPaymentById(Integer billPaymentId);
    public BillPayments updateBillPayments(BillPayments billPayments);
    public void deleteBillPayments(Integer billPaymentId);

}
