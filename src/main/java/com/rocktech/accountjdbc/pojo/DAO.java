package com.rocktech.accountjdbc.pojo;

import com.rocktech.accountjdbc.response.Response;

import java.util.List;

public interface DAO <Account> {
    List<Account> getAllAccount ();

    Response create(Account account);

    String statusChecker(Account accountNumber);

    Boolean checkAccount(Account account);

    Response updateStatus( Account accountNumber, String status, Response response);
}
