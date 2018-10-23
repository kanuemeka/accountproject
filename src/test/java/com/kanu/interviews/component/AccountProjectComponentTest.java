package com.kanu.interviews.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanu.interviews.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountProjectComponentTest {

    @LocalServerPort
    protected int randomServerPort;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    final ObjectMapper objectMapper = new ObjectMapper();

    final String localHostDomainUri = "http://localhost:";

    final String accountServiceName = "/accountproject";

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void saveClubShouldReturn200ResponseWithPositiveMessage()throws Exception{
        //GIVEN
        final URI uri = URI.create(localHostDomainUri+randomServerPort+accountServiceName + "/rest/account/json");
        String request = "{\"firstName\": \"Steven\", \"secondName\": \"Doe\", \"accountNumber\": \"12345\"}";

        //WHEN
        final ResponseEntity<String> response = testRestTemplate.getRestTemplate().postForEntity(uri, request, String.class);

        //THEN
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

}
