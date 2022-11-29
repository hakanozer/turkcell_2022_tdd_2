package com.works;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Customer;
import com.works.restcontrollers.CustomerRestController;
import com.works.services.CustomerService;
import com.works.utils.Action;
import com.works.xml.Currency;
import com.works.xml.XmlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(controllers = {CustomerRestController.class})
public class CustomerMockRestTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    XmlService xmlService;

    @Test
    public void addTest() throws Exception {

        Customer customer = new Customer();
        customer.setTitle("Macbook");
        customer.setPrice(50000);
        String customerString = objectMapper.writeValueAsString(customer);

        /*
        mockMvc.perform(
                MockMvcRequestBuilders.post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerString)
        ).andExpect(MockMvcResultMatchers.status().isOk());
         */

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerString)
        ).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus() );
    }

    @Test
    public void xmlTest() {
        List<Currency> currencies = xmlService.data();
        System.out.println(currencies);
    }

    @Test
    public void actionTest() {
        Action action = new Action();
        int i = action.sum(50,78);
        System.out.println("sum : " + i);
    }

    @Test
    public void list() throws Exception {
        MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders.get("/list") )
                                        .andReturn();
        String lists = mvcResult.getResponse().getContentAsString();
        System.out.println(lists);
    }

}
