package org.launchcode.britaneygroupa.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class GoogleSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers(new String[]{"/", "/index", "/about", "/features", "/faq", "/register", "/login", "/contact"}).permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }
}
