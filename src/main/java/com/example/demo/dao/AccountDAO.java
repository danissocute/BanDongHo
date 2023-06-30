package com.example.demo.dao;

import com.example.demo.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDAO extends JpaRepository<Accounts, String> {
    @Query("select distinct p.accounts from Authorities p where p.roles.name ='ADMIN' or p.roles.name='USER'")
    List<Accounts> getAdministrators();
}
