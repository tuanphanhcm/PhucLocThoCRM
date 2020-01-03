package com.tuanphan.phucloctho.rest.controller;

import com.tuanphan.phucloctho.dto.UserDto;
import com.tuanphan.phucloctho.model.User;
import com.tuanphan.phucloctho.service.RoleService;
import com.tuanphan.phucloctho.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

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
            BindingResult errors){
        if(errors.hasErrors())
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        User existingUsers = userService.findByUsername(userDto.getUsername());
        if(existingUsers == null){
            errors.rejectValue("username","userDto.existed","Username đã tồn tại!");
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "userDto", "Nhập lại mật khẩu không trùng khớp");
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        if(!roleService.existsById(userDto.getRoleId())){
            errors.rejectValue("roleId","userDto","Quyền truy cập không tồn tại!");
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        User user = userService.addUser(userDto);
        if(user == null)
            return new ResponseEntity<>("Invalid user",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @GetMapping("/find/username/{username}")
    public Object findByUsername(@PathVariable String username){
        if(username.isEmpty())
            return new ResponseEntity<>("Vui lòng nhập username", HttpStatus.BAD_GATEWAY);

        User userList = userService.findByUsername(username);

        if(userList == null)
            return new ResponseEntity<>("Username không tồn tại",HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @PutMapping("")
    public Object updateUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);

        if(!userService.isExisting(user.getId())){
            bindingResult.rejectValue("id","user","ID không tồn tại");
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }

        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable int id){
        if(userService.isExisting(id)){
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("User không tồn tại!",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/find/roleId/{roleId}")
    public Object findByRoleId(@PathVariable int roleId){
        if(!roleService.existsById(roleId))
            return new ResponseEntity<>("Quyền truy cập không tồn tại",HttpStatus.BAD_REQUEST);

        List<User> userList = userService.findByRoleId(roleId);
        if(userList.isEmpty())
            return new ResponseEntity<>("Không tìm thấy bất kỳ user nào!",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }
}
