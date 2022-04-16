package com.example.projectOne.services;

import com.example.projectOne.DAO.StatusRepository;
import com.example.projectOne.Exception.IdNotFoundException;
import com.example.projectOne.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * StatusService.java
 * Class for manager's response to employees' reimbursement request
 *
 * @author Jia ying Li
 */
@Service
public class StatusService {
    private static StatusRepository statusRepository;

    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    /**
     * save the response post for the employee request to database
     *
     * @param status the response post the manager created
     * @return the response post manager created
     */
    public Status save(Status status){
        return statusRepository.save(status);
    }

    /**
     * Getting all status post for employees' reimbursement request
     *
     * @return all response post manager created
     */
    public List<Status> getAll() { return statusRepository.findAll();}

    /**
     * Getting all status post by employees' id
     *
     * @param employeeId employees' id
     * @return all response post by employees' id
     */
    public List<Status> getAllById(Integer employeeId) {
        return  statusRepository.getAllByRequestId(employeeId);
    }

    /**
     * update/edit the response post
     *
     * @param updateStatus the updated response post manager filled out
     * @return the updated response post
     * @throws IdNotFoundException throws an exception if cannot find by id
     */
    public Status update(Status updateStatus) throws IdNotFoundException {

        Status status = statusRepository.findById(updateStatus.getStatusId()).orElseThrow(() ->
                new IdNotFoundException("Reimbursement id " + updateStatus.getStatusId() + " is not found"));
        status.setRequestId(updateStatus.getRequestId());
        status.setRequester(updateStatus.getRequester());
        status.setAmount(updateStatus.getAmount());
        status.setStatus(updateStatus.getStatus());
        status.setManagerComment(updateStatus.getManagerComment());
        status.setManagerName(updateStatus.getManagerName());
        status.setDecisionDate(updateStatus.getDecisionDate());
        statusRepository.save(status);
        return status;
    }

    public void deleteByStatusId(Integer statusId) {
        statusRepository.deleteByStatusId(statusId);
    }
}
