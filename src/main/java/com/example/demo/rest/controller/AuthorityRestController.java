package com.example.demo.rest.controller;

import com.example.demo.entity.Authorities;
import com.example.demo.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {
    @Autowired
    AuthorityService authorityService;

    @GetMapping
    public List<Authorities> findAll(@RequestParam("admin") Optional<Boolean> admin) {
        if (admin.orElse(false)) {
            return authorityService.findAuthoritiesOfAdminitrators();
        }
        return authorityService.findAll();
    }

    @PostMapping
    public Authorities post(@RequestBody Authorities authorities) {
        return authorityService.create(authorities);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        authorityService.deleteById(id);
    }
}
