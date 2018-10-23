package com.kanu.interviews.resource;

import com.kanu.interviews.model.Account;
import com.kanu.interviews.model.ResponseMessage;
import com.kanu.interviews.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/rest/account/json")
public class AccountResource {

    private AccountRepository accountRepository;

    @Autowired
    public AccountResource(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts(){
        return Response.ok(accountRepository.getAllAccounts()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAccount(Account account) {

        accountRepository.saveAccount(account);
        return Response.ok(ResponseMessage.ADD_SUCCESS).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAccount(@PathParam("id") Integer accountId) {

        accountRepository.deleteAccount(accountId);
        return Response.ok(ResponseMessage.DELETE_SUCCESS).build();
    }
}
