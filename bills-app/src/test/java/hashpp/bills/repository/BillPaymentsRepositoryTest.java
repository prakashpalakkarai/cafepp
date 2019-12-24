package hashpp.bills.repository;

import hashpp.bills.model.BillPayments;
import hashpp.bills.model.BillSchedules;
import hashpp.bills.model.Payee;
import hashpp.bills.model.PaymentMethods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(value = "classpath:unit-application.properties")
public class BillPaymentsRepositoryTest {

    @Autowired
    BillPaymentsRepository billPaymentsRepository;

    @Autowired
    PayeeRepository payeeRepository;


    @Test
    public void crudTest() {
        // create
        BillPayments bp = new BillPayments();
        //bp.setPmtId(999); // Auto generated
        bp.setPmtStatus("Scheduled");
        bp.setPmtScheduleDt(new Date());
        Payee payee = new Payee();
        payee.setAccountNum(1000);
        payee.setDueAmount(new BigDecimal(100));
        payee.setInterest(new BigDecimal(1.9));
        payee.setName("crudTest1");
        //payee.setPayeeId(100); // Auto generated
        payee.setType("financing");
        payee.setTotalBalance(new BigDecimal(5000));
        PaymentMethods paymentMethods = new PaymentMethods();
        paymentMethods.setMethodName("Automatic Payment from Checking");
        paymentMethods.setPmtMethodId("ACK");
        payee.setPaymentMethods(paymentMethods);
        BillSchedules billSchedules = new BillSchedules();
        billSchedules.setBillSchId("MLY");
        billSchedules.setScheduleName("Monthly");
        billSchedules.setFrequency("1 month");
        payee.setBillSchedules(billSchedules);
        bp.setPayee(payee);
        payeeRepository.save(payee);
        Payee foundPayee = payeeRepository.findByName("crudTest1");
        assertThat(foundPayee).isNotNull();
        assertThat(foundPayee.getDueAmount()).isEqualTo(payee.getDueAmount());

        bp.setAmount(new BigDecimal(200));
        billPaymentsRepository.save(bp);

        // read
        List<BillPayments> billPaymentsList = billPaymentsRepository.findAll();
        assertThat(billPaymentsList.size() > 0);
        assertThat(billPaymentsList.get(0).getPayee().getName()).isEqualTo("crudTest1");

        // update
        BillPayments bpUpdate = billPaymentsList.get(0);
        bpUpdate.setAmount(new BigDecimal(300));
        billPaymentsRepository.save(bpUpdate);
        Optional<BillPayments> bpNew = billPaymentsRepository.findById(bpUpdate.getPmtId());
        assertThat(bpNew).isNotNull();
        assertThat(bpNew.get().getAmount()).isEqualTo(new BigDecimal(300));

        // delete
        billPaymentsRepository.delete(bpNew.get());
        Optional<BillPayments> bpThere = billPaymentsRepository.findById(bpUpdate.getPmtId());
        assertThat(bpThere).isEmpty();

    }
}
