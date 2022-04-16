package com.example.projectOne.services;

import com.example.projectOne.DAO.UserRepository;
import com.example.projectOne.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * EmailService.java
 * Class for email service
 *
 * @author Jia ying Li
 */
@Service
public class EmailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
    }

    /**
     * Getting the employee name, email address and the status of their reimbursement post
     *   to a string list.
     *
     * @param status response post the manager created
     * @return a string list of employees name, email address and their reimbursement status
     */
    public List<String> emailFormat(Status status) {

        List<String> list = Arrays.asList(status.getRequester(),
                userRepository.getAllByEmployeeName(status.getRequester()).getEmail(),
                status.getStatus());
        return list;
    }

}
