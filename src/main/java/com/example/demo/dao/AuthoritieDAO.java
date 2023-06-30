package com.example.demo.dao;

import com.example.demo.entity.Accounts;
import com.example.demo.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthoritieDAO extends JpaRepository<Authorities, Integer> {
    @Query("select distinct p from Authorities p where p.accounts in ?1")
    List<Authorities> authoritiesOf(List<Accounts> accounts);
}
