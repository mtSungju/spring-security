package com.example.securitytest.repository;

import com.example.securitytest.entity.UserEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntitiy,Integer> {

    boolean existsByUsername(String username);

    UserEntitiy findByUsername(String username);
}
