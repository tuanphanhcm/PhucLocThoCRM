package com.tuanphan.phucloctho.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //http.authorizeRequests().antMatchers("/").permitAll();
        http.cors();
        http.csrf().disable()
                .antMatcher("/api/admin/**")
                .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()
                .antMatchers("/api/admin/**")
                .hasAnyRole("ADMIN","MANAGER")
                .anyRequest()
                .authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
    }
}
