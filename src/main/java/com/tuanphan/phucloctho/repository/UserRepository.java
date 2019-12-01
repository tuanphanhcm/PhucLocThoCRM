package com.tuanphan.phucloctho.repository;

import com.tuanphan.phucloctho.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
