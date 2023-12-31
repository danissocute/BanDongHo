package com.example.demo.config;

import com.example.demo.entity.Accounts;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;
    @Autowired
    BCryptPasswordEncoder pe;

    //    Co che ma hoa mat khau
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                Accounts user = accountService.findById(username);
                String password = pe.encode(user.getPassword());
                String[] roles = user.getAuthorities().stream()
                        .map(er -> er.getRoles().getName())
                        .collect(Collectors.toList())
                        .toArray(new String[0]);
                return User.withUsername(username).password(password).roles(roles).build();
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException("username" + username);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/rest/authorities").hasAnyRole("ADMIN")
                .anyRequest().permitAll();
        http.formLogin()
                .loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success", false)
                .failureUrl("/security/login/form?error=true");
//        Neu remember me thi luu trong 24h
        http.rememberMe().tokenValiditySeconds(86400);
        http.exceptionHandling().accessDeniedPage("/security/unauthoried");
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/security/login/form");
        http.oauth2Login().loginPage("/security/login/form")
                .defaultSuccessUrl("/oauth2/login/success",true)
                .failureUrl("/security/login/form?error=true")
                .authorizationEndpoint()
//
                .baseUri("/oauth2/authorization");
    }

    //    Cho phep truy xuat Rest Api tu ben ngoai
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
