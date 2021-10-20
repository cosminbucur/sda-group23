package com.sda.spring.security.memory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // use in memory auth
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password(passwordEncoder().encode("pass"))
            .roles("ADMIN")
        .and()
            .withUser("user")
            .password(passwordEncoder().encode("pass"))
            .roles("USER");
    }

    // authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // use basic authentication
        http.httpBasic()
                .and().authorizeRequests()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/all/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
