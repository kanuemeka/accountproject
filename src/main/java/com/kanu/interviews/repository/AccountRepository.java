package com.kanu.interviews.repository;

import com.kanu.interviews.model.Account;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class AccountRepository {

    public static Predicate<Account> isMatchingId(Integer id)
    {
        return p -> p.getId() > id;
    }

    private List<Account>accountsList;

    public AccountRepository() {
        accountsList = new ArrayList<Account>();
    }

    public List<Account> getAllAccounts(){

        return new ArrayList<Account>(accountsList);
    }

    public void saveAccount(final Account account) {
        int maxId = 0;

        Optional<Account> maxIdAccountOpt =
                accountsList.stream()
                        .collect(Collectors.maxBy(Comparator.comparing(Account::getId)));

        if(maxIdAccountOpt.isPresent())
            maxId = maxIdAccountOpt.get().getId();
        int newId = maxId+1;

        account.setId(newId);
        accountsList.add(account);

    }

    public void deleteAccount(final int accountId){

        accountsList.removeIf(isMatchingId(accountId));
    }

    /**
     * Convenience method to halp testing
     */
    public void deleteAllAccounts(){
        accountsList.removeAll(accountsList);
    }
}
