package com.example.projectOne.DAO;

import com.example.projectOne.models.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface LoginRepository extends JpaRepository<UserLogin, Integer> {
    Optional<UserLogin> findByUsername(String username);
}
