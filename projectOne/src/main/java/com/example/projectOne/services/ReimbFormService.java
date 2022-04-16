package com.example.projectOne.services;

import com.example.projectOne.DAO.ReimbFormRepository;
import com.example.projectOne.Exception.IdNotFoundException;
import com.example.projectOne.models.ReimbForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ReimbFormService.java
 * Class for employee reimbursement request service
 *
 * @author Jia ying Li
 */
@Service
public class ReimbFormService {

    private ReimbFormRepository reimbFormRepository;

    @Autowired
    public void setReimbFormRepository(ReimbFormRepository reimbFormRepository) {
        this.reimbFormRepository = reimbFormRepository;
    }

    /**
     * save the reimbursement post the employee request to database
     *
     * @param reimbForm the reimbursement request the employee filled out
     * @return the reimbursement request employee had filled out
     */
    public ReimbForm save(ReimbForm reimbForm){
        return reimbFormRepository.save(reimbForm);
    }

    /**
     * Getting all reimbursement posts the employee had requested
     *
     * @return all reimbursement posts employee had requested
     */
    public List<ReimbForm> getAll() {
        return reimbFormRepository.findAll();
    }

    /**
     * Getting all reimbursement post for employee by their id
     *
     * @param employeeId employees' id
     * @return all reimbursement post for employee by their id
     */
    public List<ReimbForm> getAllById(Integer employeeId) {
            return  reimbFormRepository.getAllByEmployeeId(employeeId);
    }

    /**
     * Update/editing reimbursement post
     *
     * @param updateReimbForm the new reimbursement post the employee had edited
     * @return the updated reimbursement post
     * @throws IdNotFoundException if cannot find will throw an exception
     */
    public ReimbForm update(ReimbForm updateReimbForm) throws IdNotFoundException {
        ReimbForm reimbForm = reimbFormRepository.findById(updateReimbForm.getReimbId()).orElseThrow(() ->
                new IdNotFoundException("Reimbursement id " + updateReimbForm.getReimbId() + " is not found"));

        reimbForm.setEmployeeId(updateReimbForm.getEmployeeId());
        reimbForm.setEmployeeName(updateReimbForm.getEmployeeName());
        reimbForm.setExpenseDate(updateReimbForm.getExpenseDate());
        reimbForm.setDescription(updateReimbForm.getDescription());
        reimbForm.setAmount(updateReimbForm.getAmount());
        reimbForm.setRequestDate(updateReimbForm.getRequestDate());
        reimbFormRepository.save(reimbForm);

        return reimbForm;
    }

}

