package com.example.demo.service;

import com.example.demo.entity.Accounts;

import java.util.List;

public interface AccountService {
    Accounts findById(String username);

    List<Accounts> getAdministrators();

    List<Accounts> findAll();
}
