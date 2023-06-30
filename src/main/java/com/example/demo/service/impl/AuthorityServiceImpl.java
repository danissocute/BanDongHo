package com.example.demo.service.impl;

import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.AuthoritieDAO;
import com.example.demo.entity.Accounts;
import com.example.demo.entity.Authorities;
import com.example.demo.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthoritieDAO authoritieDAO;
    @Autowired
    AccountDAO accountDAO;

    @Override
    public List<Authorities> findAuthoritiesOfAdminitrators() {
        List<Accounts> accounts = accountDAO.getAdministrators();
        return authoritieDAO.authoritiesOf(accounts);
    }

    @Override
    public List<Authorities> findAll() {
        return authoritieDAO.findAll();
    }

    @Override
    public Authorities create(Authorities authorities) {
        return authoritieDAO.save(authorities);
    }

    @Override
    public void deleteById(Integer id) {
        authoritieDAO.deleteById(id);
    }
}
