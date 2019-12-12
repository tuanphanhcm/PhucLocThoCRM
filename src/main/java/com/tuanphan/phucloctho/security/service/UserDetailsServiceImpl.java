package com.tuanphan.phucloctho.security.service;

import com.tuanphan.phucloctho.dto.CustomerUserDetail;
import com.tuanphan.phucloctho.model.User;
import com.tuanphan.phucloctho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository){
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("Không tìm thấy tài khoản người dùng.");
        List<GrantedAuthority> authorities = new ArrayList<>();
        String rolename = user.getRole().getName();
        authorities.add(new SimpleGrantedAuthority(rolename));
        return new CustomerUserDetail(user.getUsername(),user.getPassword(),authorities);
    }
}
