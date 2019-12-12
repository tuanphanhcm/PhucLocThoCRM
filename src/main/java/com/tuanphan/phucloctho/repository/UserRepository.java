package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(@Param("username")String username);

    List<User> findByRoleId(@Param("roleId")int roleId);

}
