package com.kanu.interviews.resource;

import com.kanu.interviews.model.Account;
import com.kanu.interviews.repository.AccountRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class AccountResourceTest {

    AccountResource accountResource;

    AccountRepository accountRepository;

    @Before
    public void setup(){
        accountRepository = mock(AccountRepository.class);
        accountResource = new AccountResource(accountRepository);
    }

    @After
    public void tearup(){
        accountResource = null;
        accountRepository = null;
    }

    @Test
    public void getAllAccountsShouldReturnResponseWithListOfNumberedAccounts(){
        //GIVEN
        int numOfAccounts=4;
        when(accountRepository.getAllAccounts()).thenReturn(buildList(numOfAccounts));

        // WHEN
        Response actualResponse = accountResource.getAllAccounts();

        //THEN
        assertNotNull(actualResponse);
        assertEquals(200, actualResponse.getStatus());
        assertNotNull(actualResponse.getEntity());

        List<Account> returnedAccounts = (List<Account>) actualResponse.getEntity();

        assertEquals(numOfAccounts, returnedAccounts.size());
    }

    @Test
    public void postAccountShouldReturnSuccessMessage(){
        // GIVEN
        Account testAccount
                = Account.builder().firstName("firstname").secondName("secondName")
                .accountNumber("1234").build();

        //WHEN
        Response actualResponse = accountResource.saveAccount(testAccount);

        // THEN
        assertNotNull("Null Response Received", actualResponse);
        assertEquals(200, actualResponse.getStatus());
        assertNotNull(actualResponse.getEntity());
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
