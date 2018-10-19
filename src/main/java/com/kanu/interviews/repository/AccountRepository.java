package com.kanu.interviews.repository;

import com.kanu.interviews.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepository {

    private List<Account>accountsList;

    public AccountRepository() {
        accountsList = new ArrayList<Account>();
    }

    public List<Account> getAllAccounts(){

        List<Account> newList = new ArrayList<Account>();
        newList.addAll(accountsList);

        return newList;
    }
}
