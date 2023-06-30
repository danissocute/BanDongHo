package com.example.demo.service.impl;

import com.example.demo.dao.RoleDAO;
import com.example.demo.entity.Roles;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;

    @Override
    public List<Roles> findAll() {
        return roleDAO.findAll();
    }
}
