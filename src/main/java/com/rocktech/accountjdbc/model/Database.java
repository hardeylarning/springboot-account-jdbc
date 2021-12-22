package com.rocktech.accountjdbc.model;

import com.rocktech.accountjdbc.pojo.Account;
import com.rocktech.accountjdbc.pojo.DAO;
import com.rocktech.accountjdbc.response.Response;
import com.rocktech.accountjdbc.static_value.StaticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Database implements DAO<Account> {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Account> getAllAccount() {
        RowMapper<Account> rowMapper = (res, rowNum)  -> {
            Account account = new Account();
            account.setName(res.getString("name"));
            account.setAccountNumber(res.getString("accountNumber"));
            account.setDate(res.getString("dob"));
            account.setStatus(res.getString("status"));
            return account;
        };

        String sql = "select name, accountNumber, dob, status from accounts";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Response create(Account account) {
        if (checkAccount(account)) {
            return new Response(StaticData.EXISTING_CODE, StaticData.EXISTING_MESSAGE);
        }
        String sql = "insert into accounts(name, accountNumber, dob) values(?,?,?)";
        int insert = jdbcTemplate.update(sql,
                account.getName(),
                account.getAccountNumber(),
                account.getDate());

        if (insert == 1) {
            return new Response(StaticData.SUCCESS_CODE, StaticData.SUCCESS_MESSAGE);
        }
        else {
            return new Response(StaticData.FAILED_CODE, StaticData.FAILED_MESSAGE);
        }
    }

    @Override
    public String statusChecker(Account accountNumber) {
        String sql = "select status from accounts where accountNumber=?";

        RowMapper<String> rowMapper = (res, rowNum)  -> res.getString("status");
        String result = jdbcTemplate.queryForObject(
                sql, rowMapper, accountNumber.getAccountNumber());

        assert result != null;
        if (!result.isEmpty()) {
            return result;
        }
        else {
            return null;
        }
    }

    @Override
    public Response updateStatus(Account accountNumber, String status, Response response) {
        String sql = "update accounts set status = ? where accountNumber = ?";
        int update = jdbcTemplate.update(sql,
                status,
                accountNumber.getAccountNumber());

        if (update == 1)
            return new Response(response.getCode(),
                    response.getMessage()+" changed to "+status);

        else
            return null;
    }

    @Override
    public Boolean checkAccount(Account account) {
        String sql = "select COUNT(accountNumber) from accounts where accountNumber=?";

        int result = jdbcTemplate.queryForObject(sql, Integer.class,
                account.getAccountNumber());

        return result > 0;
    }

    public Response getResponse(Account accountNumber, String compareStatus, Response response) {
        String status = statusChecker(accountNumber);
        if (status == null){
            return new Response(StaticData.FAILED_CODE, StaticData.FAILED_MESSAGE);
        }
        else if (status.equalsIgnoreCase(compareStatus)) {
            return new Response(response.getCode(), response.getMessage());
        }
        else {
            Response responseIn = new Response(
                    StaticData.SUCCESS_CODE,
                    StaticData.SUCCESS_MESSAGE);
            return updateStatus(accountNumber, compareStatus, responseIn);
        }
    }
}
