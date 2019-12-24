package hashpp.bills.controller;

import hashpp.bills.exception.ResourceNotFoundException;
import hashpp.bills.dto.BillPayments;
import hashpp.bills.service.BillPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BillPaymentsController {

    @Autowired
    BillPaymentsService billPaymentsService;

    @GetMapping("/billpayments")
    public List<BillPayments> getAllBillPayments() {
        return billPaymentsService.getAllBillPayments();
    }

    @PostMapping("/billpayments")
    public ResponseEntity<BillPayments> createBillPayments(@Valid @RequestBody BillPayments billPayments) {
        billPaymentsService.createBillPayment(billPayments);
        return new ResponseEntity(billPayments, HttpStatus.CREATED);
    }

    @GetMapping("/billpayments/{id}")
    public BillPayments getBillPaymentById(@PathVariable(value = "id") Integer billPaymentId) {
        Optional<BillPayments> billPaymentsOptional = billPaymentsService.getBillPaymentById(billPaymentId);
        billPaymentsOptional.orElseThrow(() -> new ResourceNotFoundException("BillPayments", "id", billPaymentId));
        return billPaymentsOptional.get();
    }

    @PutMapping("/billpayments/{id}")
    public BillPayments updateBillPayments(@PathVariable(value = "id") Integer billPaymentId,
                                            @Valid @RequestBody BillPayments billPayments) {

        BillPayments billPayment = billPaymentsService.getBillPaymentById(billPaymentId)
                .orElseThrow(() -> new ResourceNotFoundException("BillPayments", "id", billPaymentId));

        billPaymentsService.updateBillPayments(billPayment);
        return billPayment;
    }

    @DeleteMapping("/billpayments/{id}")
    public ResponseEntity<?> deleteBillPayments(@PathVariable(value = "id") Integer billPaymentId) {
        BillPayments billPayments = billPaymentsService.getBillPaymentById(billPaymentId)
                .orElseThrow(() -> new ResourceNotFoundException("BillPayments", "id", billPaymentId));

        billPaymentsService.deleteBillPayments(billPaymentId);

        return ResponseEntity.ok().build();
    }
}
