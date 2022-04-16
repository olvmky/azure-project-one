package com.example.projectOne.DAO;

import com.example.projectOne.models.ReimbForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ReimbFormRepository extends JpaRepository<ReimbForm, Integer> {
    List<ReimbForm> getAllByEmployeeId(Integer employeeId);
}
