package com.tugab.jobsearchplus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .csrfTokenRepository(this.csrfTokenRepository())
            .and()
                .authorizeRequests()
                .antMatchers("/user/login").anonymous()
                .antMatchers("/user/list", "/user/register").hasAuthority("ADMIN")
                .antMatchers("/", "/job/list").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("facultyNumber")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
            .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/user/login");
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository =
                new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}