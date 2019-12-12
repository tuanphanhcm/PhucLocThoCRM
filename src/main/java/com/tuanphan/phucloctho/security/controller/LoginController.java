package com.tuanphan.phucloctho.security.controller;

import com.tuanphan.phucloctho.dto.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {
    private AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(AuthenticationManager manager){
        this.authenticationManager = manager;
    }

    @PostMapping("")
    public Object login(@RequestBody UserLogin userLogin){
        Authentication authentication;

        try{
            authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(userLogin.getUsername(),userLogin.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = generateToken(authentication);
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Username or password is not correct.",HttpStatus.BAD_REQUEST);
    }

    private String generateToken(Authentication authentication) {
        final String secretKey = "thisIsTheSecretKey";
        final long expirationDuration = 864000000L;

        Date currentDate = new Date();
        Date expiryDate =  new Date(currentDate.getTime() + expirationDuration);

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
    }
}
