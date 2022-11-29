package com.works;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.works.entities.Customer;
import com.works.props.ProductData;
import com.works.xml.Currency;
import com.works.xml.XmlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class CustomerRestTest {

    @Autowired
    XmlService xmlService;

    @Autowired
    ObjectMapper objectMapper;

    RestTemplate restTemplate;

    @BeforeAll
    public void beforeAll() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void addTest() throws JsonProcessingException {
        String url = "http://localhost:8090/add";
        Customer customer = new Customer();
        customer.setTitle("Bisiklet" + Math.random());
        customer.setPrice(6000);
        String stCustomer = objectMapper.writeValueAsString(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity httpEntity = new HttpEntity(stCustomer,headers );

        ResponseEntity<Customer> res = restTemplate.postForEntity(url, httpEntity, Customer.class);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(res.getBody()),
                () -> Assertions.assertEquals(200, res.getStatusCode().value() )
        );
    }

    @Test
    public void listTest() {
        String url = "http://localhost:8090/list";
        ResponseEntity<List> res = restTemplate.getForEntity(url, List.class);
        List<Customer> customers = res.getBody();
        Assertions.assertTrue(customers.size() > 0);
    }

    @Test
    public void pageTest() throws IOException {
        String url = "http://localhost:8090/page/a/0";
        ResponseEntity res = restTemplate.getForEntity(url, Object.class);
        String stData = objectMapper.writeValueAsString(res.getBody());
        //JsonParser jsonParser = objectMapper.createParser(stData);

        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(stData, JsonObject.class);
        int total = obj.get("totalElements").getAsInt();
        Assertions.assertNotEquals(0, total);
    }

    @Test
    public void productTest() {
        String url = "https://dummyjson.com/products";
        ProductData data = restTemplate.getForObject(url, ProductData.class);
        int size = data.getProducts().size();
        Assertions.assertNotEquals(0, size);
    }

    @Test
    public void xmlTest() {
        List<Currency> currencies = xmlService.data();
        double dollar = Double.parseDouble( currencies.get(0).getForexBuying() );
        Assertions.assertAll(
                () -> Assertions.assertTrue(currencies.size() > 0),
                () -> Assertions.assertTrue(dollar > 18.00)
        );
    }

}
