package com.tuanphan.phucloctho.security.filter;

import com.tuanphan.phucloctho.security.SecurityUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private UserDetailsService _userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        _userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenBearer = request.getHeader("Authorization");
        if(tokenBearer != null || tokenBearer.startsWith("Bearer ")){
            String token = tokenBearer.replace("Bearer ","");
            String userNameDecodedFromToken = Jwts.parser()
                    .setSigningKey(SecurityUtils.secretKey)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
            UserDetails userDetailsGotFromDatabase = _userDetailsService.loadUserByUsername(userNameDecodedFromToken);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetailsGotFromDatabase,
                    null,
                    userDetailsGotFromDatabase.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
