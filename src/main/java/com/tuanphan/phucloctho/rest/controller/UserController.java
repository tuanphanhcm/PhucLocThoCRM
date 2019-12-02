package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.dto.UserDto;
import com.tuanphan.phucloctho.model.User;
import com.tuanphan.phucloctho.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public Object getAllUsers(){
        List<User> userList = userService.getAllUsers();
        if(userList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @PostMapping("")
    public Object addUser(
            @Valid @RequestBody UserDto userDto,
            BindingResult errors
        ){
        if(errors.hasErrors())
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.BAD_GATEWAY);
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "userDto", "Nhập lại mật khẩu không trùng khớp");
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        User user = parseUser(userDto);
        if(userService.addUser(user) == null)
            return new ResponseEntity<>("Invalid user",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    private User parseUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setRoleId(userDto.getRoleId());
        return user;
    }
}
