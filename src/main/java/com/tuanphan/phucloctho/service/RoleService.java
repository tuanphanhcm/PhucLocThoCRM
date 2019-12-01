package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.model.Role;
import com.tuanphan.phucloctho.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Role addRole(Role role){
        return roleRepository.save(role);
    }
}
