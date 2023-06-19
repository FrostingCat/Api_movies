package com.example.api_autho.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String GET_MOVIES_ENDPOINT = "/api/movies";
    private static final String GET_SHOWTIMES_ENDPOINT = "/api/showtimes";
    private static final String POST_TICKETS_ENDPOINT = "/api/tickets";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(GET_MOVIES_ENDPOINT, GET_SHOWTIMES_ENDPOINT, POST_TICKETS_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and();
    }
}
