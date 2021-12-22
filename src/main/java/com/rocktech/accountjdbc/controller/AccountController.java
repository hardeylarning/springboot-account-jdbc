package com.rocktech.accountjdbc.controller;

import com.rocktech.accountjdbc.model.Database;
import com.rocktech.accountjdbc.pojo.Account;
import com.rocktech.accountjdbc.response.Response;
import com.rocktech.accountjdbc.static_value.StaticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    Database database;

    @RequestMapping("/create")
    Response create(@RequestBody Account account){

        return database.create(account);
    }

    @GetMapping("/accounts")
    List<Account> getAll () {
        return database.getAllAccount();
    }

    @RequestMapping("/block")
    Response block(@RequestBody Account accountNumber){
        Response response = new Response(
                StaticData.BLOCK_CODE,
                StaticData.BLOCK_MESSAGE);
        return database.getResponse(accountNumber, "blocked", response);
    }

    @RequestMapping("/unblock")
    Response unblock(@RequestBody Account accountNumber){
        Response response = new Response(
                StaticData.ACTIVE_CODE,
                StaticData.ACTIVE_MESSAGE);
        return database.getResponse(accountNumber, "Active", response);
    }



}
