package com.rocktech.accountjdbc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(value = {"id"})
public class Account {
    int id;
    private String name;
    private String accountNumber;
    private String date;
    private String status;

    public Account(int id, String name, String accountNumber, String date, String status) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.date = date;
        this.status = status;
    }

    public Account(String name, String accountNumber, String date, String status) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.date = date;
        this.status = status;
    }

    public Account(String name, String accountNumber, String date) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.date = date;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
