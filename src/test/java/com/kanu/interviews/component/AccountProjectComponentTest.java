package com.kanu.interviews.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanu.interviews.Application;
import com.kanu.interviews.model.Account;
import com.kanu.interviews.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class AccountProjectComponentTest {

    static final String ACCOUNT_ADDED_SUCCESS_MSG = "{\"message\":\"account successfully added\"}";
    static final String ACCOUNT_DEL_SUCCESS_MSG = "{\"message\":\"account successfully deleted\"}";

    @LocalServerPort
    protected int randomServerPort;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    final ObjectMapper objectMapper = new ObjectMapper();

    final String localHostDomainUri = "http://localhost:";

    final String accountServiceName = "/accountproject";

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setup(){
        //Clear accounts
        accountRepository.deleteAllAccounts();
    }

    @Test
    public void saveClubShouldReturn200ResponseWithPositiveMessage(){
        //GIVEN
        final URI uri = URI.create(localHostDomainUri+randomServerPort+accountServiceName + "/rest/account/json");
        String request = "{\"firstName\": \"Steven\", \"secondName\": \"Doe\", \"accountNumber\": \"12345\"}";

        //WHEN
        HttpEntity<String> entity = wrapRequestInHttpEntity(request);
        final ResponseEntity<String> response = testRestTemplate.postForEntity(uri, entity, String.class);

        //THEN
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals("Expected Response Not Received", ACCOUNT_ADDED_SUCCESS_MSG, response.getBody());
    }

    @Test
    public void getAllAccountsShouldReturn200ResponseWithListOfAccounts()throws Exception{
        //GIVEN
        int numberOfAccounts = 4;
        preloadAccountsInDB(numberOfAccounts);
        final URI uri = URI.create(localHostDomainUri+randomServerPort+accountServiceName + "/rest/account/json");

        //WHEN
        final ResponseEntity<String> response = testRestTemplate.getForEntity(uri, String.class);

        //THEN
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        ArrayList listOfAccounts = objectMapper.readValue(response.getBody(), ArrayList.class);
        assertEquals(numberOfAccounts, listOfAccounts.size());
    }

    @Test
    public void shouldDeleteAnAccountSuccessfullyAndGetANewListOfAccounts()throws Exception{
        //GIVEN
        int numberOfAccounts = 4;
        preloadAccountsInDB(numberOfAccounts);
        final URI uri = URI.create(localHostDomainUri+randomServerPort+accountServiceName + "/rest/account/json/1");

        //WHEN
        testRestTemplate.delete(uri);
        final URI getUri = URI.create(localHostDomainUri+randomServerPort+accountServiceName + "/rest/account/json");
        final ResponseEntity<String> response = testRestTemplate.getForEntity(getUri, String.class);

        //THEN
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        ArrayList listOfAccounts = objectMapper.readValue(response.getBody(), ArrayList.class);
        assertEquals(numberOfAccounts-1, listOfAccounts.size());

    }

    private HttpEntity<String> wrapRequestInHttpEntity(String request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(request, headers);
    }

    private HttpEntity<String> wrapRequestInHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(headers);
    }

    private void preloadAccountsInDB(int numberOfAccounts){
        List<Account>accounts = buildList(numberOfAccounts);

        accounts.forEach(item -> accountRepository.saveAccount(item));
    }

    private List<Account> buildList(int numberOfItems){
        List<Account> builtAccounts = new ArrayList<Account>();
        for(int i =0; i<numberOfItems; i++){
            builtAccounts.add(
                    Account.builder()
                            .id(i)
                            .firstName("firstname")
                            .secondName("secondName")
                            .accountNumber("accountNumber"+i)
                            .build()
            );
        }

        return builtAccounts;
    }

}
