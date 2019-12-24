package hashpp.bills;

import com.fasterxml.jackson.databind.ObjectMapper;
import hashpp.bills.dto.BillPayments;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("boottest")
@TestPropertySource(
  locations = "classpath:unit-application.properties")
public class BillPaymentsTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void givenBillPayments_whenGetBillPayments_thenStatus200()
      throws Exception {

        // create BillPayment
        BillPayments billPayments = new BillPayments();
        billPayments.setName("BootTest1");
        billPayments.setPmtId(1);
        billPayments.setType("financing");
        billPayments.setTotalBalance(new BigDecimal(20000));
        billPayments.setAccountNum(1001);
        billPayments.setAmount(new BigDecimal(10000));
        billPayments.setBillSchId("MLY");
        billPayments.setDueAmount(new BigDecimal(100));
        billPayments.setInterest(new BigDecimal(1.99));
        billPayments.setPayeeId(1);
        billPayments.setPmtMethodId("ACK");
        billPayments.setPmtStatus("Scheduled");
        billPayments.setPmtScheduleDt(new Date());
        LocalDateTime promoExpireDt = LocalDateTime.of(2020,10, 01,
                                                    0,0,0);
        billPayments.setPromotionExpireDt(Date.from(
                                                promoExpireDt.atZone(
                                                        ZoneId.systemDefault()
                                                        ).toInstant()
                                                )
                                            );

       mvc.perform( MockMvcRequestBuilders
            .post("/api/billpayments")
            .content(asJsonString(billPayments))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.pmtId").exists());

        mvc.perform(get("/api/billpayments")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content()
          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].name", is("BootTest1")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
