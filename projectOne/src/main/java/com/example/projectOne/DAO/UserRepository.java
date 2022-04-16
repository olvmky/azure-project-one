package com.example.projectOne.DAO;

import com.example.projectOne.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {
    User getAllByEmployeeName(String employeeName);
}
