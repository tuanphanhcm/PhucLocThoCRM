package com.tuanphan.phucloctho.service;

import com.tuanphan.phucloctho.dto.UserDto;
import com.tuanphan.phucloctho.model.User;
import com.tuanphan.phucloctho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository){
        this.userRepository = repository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(UserDto userDto){
        User user = parseUser(userDto);
        return userRepository.save(user);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
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

    public Boolean isExisting(int id){
        return userRepository.existsById(id);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    public List<User> findByRoleId(int roleId){
        return userRepository.findByRoleId(roleId);
    }

}
