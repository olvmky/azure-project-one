package com.example.projectOne.DAO;

import com.example.projectOne.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StatusRepository extends JpaRepository<Status, Integer> {
    List<Status> getAllByRequestId(Integer requesterId);
    void deleteByStatusId(Integer statusId);
}
