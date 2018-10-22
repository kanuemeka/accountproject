package com.kanu.interviews.resource;

import com.kanu.interviews.model.Account;
import com.kanu.interviews.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Component
public class AccountResource {

    private AccountRepository accountRepository;

    @Autowired
    public AccountResource(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Response getAllAccounts(){
        return Response.ok(accountRepository.getAllAccounts()).build();
    }

    public Response saveAccount(Account testAccount) {
        return null;
    }
}
