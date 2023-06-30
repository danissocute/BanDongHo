package com.example.demo.service;

import com.example.demo.entity.Authorities;

import java.util.List;

public interface AuthorityService {
    List<Authorities> findAuthoritiesOfAdminitrators();

    List<Authorities> findAll();

    Authorities create(Authorities authorities);

    void deleteById(Integer id);
}
