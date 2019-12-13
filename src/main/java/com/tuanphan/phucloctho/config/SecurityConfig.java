package com.tuanphan.phucloctho.config;

import com.tuanphan.phucloctho.repository.UserRepository;
import com.tuanphan.phucloctho.security.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.tuanphan.phucloctho.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserRepository _userRepository;
    private UserDetailsService _userDetailsService;

    @Autowired
    public SecurityConfig(UserRepository userRepository,
                          @Qualifier("userDetailsServiceImpl")
                          UserDetailsService userDetailsService
        ){
        this._userRepository = userRepository;
        this._userDetailsService = userDetailsService;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable()
                .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()
                .antMatchers("/login")
                .permitAll()
                //.antMatchers("/api/**")
                //.hasAnyRole("ADMIN","MANAGER","ACCOUNTANT")
                .anyRequest()
                .authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(),_userDetailsService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(_userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
