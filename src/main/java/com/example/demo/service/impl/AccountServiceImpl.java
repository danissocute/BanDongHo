package com.example.demo.service.impl;

import com.example.demo.dao.AccountDAO;
import com.example.demo.entity.Accounts;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;

    @Override
    public Accounts findById(String username) {
        return accountDAO.findById(username).get();
    }

    @Override
    public List<Accounts> getAdministrators() {
        return accountDAO.getAdministrators();
    }

    @Override
    public List<Accounts> findAll() {
        return accountDAO.findAll();
    }
}
