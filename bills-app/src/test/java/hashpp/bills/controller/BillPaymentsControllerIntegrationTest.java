package hashpp.bills.controller;

import hashpp.bills.dto.BillPayments;
import hashpp.bills.service.BillPaymentsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(BillPaymentsController.class)
public class BillPaymentsControllerIntegrationTest {

      @Autowired
      private MockMvc mvc;

      @MockBean
      private BillPaymentsService billPaymentsService;

     @WithMockUser(value = "spring")
     @Test
     public void givenBillPayments_whenGetBillPayments_thenReturnJsonArray()
         throws Exception {

         BillPayments billPayments = new BillPayments();
         billPayments.setPmtId(1);
         billPayments.setName("ControllerTest1");

         List<BillPayments> allBillPayments = Arrays.asList(billPayments);

         given(billPaymentsService.getAllBillPayments()).willReturn(allBillPayments);

         mvc.perform(get("/api/billpayments")
           .contentType(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(jsonPath("$", hasSize(1)))
           .andExpect(jsonPath("$[0].name", is(billPayments.getName())));
     }
}
