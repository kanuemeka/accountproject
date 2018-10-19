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
    public void shouldReturnResponseWithListOfNumberedAccounts(){
        //GIVEN
        int numOfAccounts=4;
        when(accountRepository.getAllAccounts()).thenReturn(buildList(numOfAccounts));

        // WHEN
        Response actualResponse = accountResource.getAllAccounts();

        //THEN
        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getEntity());

        List<Account> returnedAccounts = (List<Account>) actualResponse.getEntity();

        assertEquals(numOfAccounts, returnedAccounts.size());
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
