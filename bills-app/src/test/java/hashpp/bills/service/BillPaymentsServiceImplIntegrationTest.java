package hashpp.bills.service;

import hashpp.bills.model.BillPayments;
import hashpp.bills.model.Payee;
import hashpp.bills.repository.BillPaymentsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class BillPaymentsServiceImplIntegrationTest {

    @TestConfiguration
    static class BillPaymentsServiceImplIntegrationTestContextConfiguration {

        @Bean
        public BillPaymentsService billPaymentsService() {

            return new BillPaymentsServiceImpl();

        }

    }

    @Autowired
    private BillPaymentsService billPaymentsService;

    @MockBean
    private BillPaymentsRepository billPaymentsRepository;

    @Before
    public void setUp() {

        BillPayments billPayments = new BillPayments();
        billPayments.setPmtId(1);
        Payee payee = new Payee();
        payee.setName("ServiceTest1");
        billPayments.setPayee(payee);

        Mockito.when(billPaymentsRepository.findById(1)).thenReturn(java.util.Optional.of(billPayments));

    }

    @Test
    public void whenValidBillPayment_thenPayeeShouldBeFound() {
        String name = "ServiceTest1";
        Optional<hashpp.bills.dto.BillPayments> found = billPaymentsService.getBillPaymentById(1);

         assertThat(found.get().getName()).isEqualTo(name);
     }
}
