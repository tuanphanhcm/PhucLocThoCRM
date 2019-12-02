package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.model.Role;
import com.tuanphan.phucloctho.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("")
    public Object getAllRoles(){
        List<Role> roleList = roleService.getAllRoles();
        if(roleList.isEmpty())
            return new ResponseEntity<>("Role list is empty",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(roleList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object insertNewRole(@RequestBody Role role){
        if(roleService.addRole(role) == null)
            return new ResponseEntity<>("Invalid role",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(role,HttpStatus.OK);
    }

}
