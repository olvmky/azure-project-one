package com.example.projectOne.Controller;

import com.example.projectOne.Exception.IdNotFoundException;
import com.example.projectOne.models.ReimbForm;
import com.example.projectOne.models.Status;
import com.example.projectOne.services.ReimbFormService;
import com.example.projectOne.services.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EmployeeController.java
 * Employee can view all of their reimbursement request by their employee id
 *   Can create new reimbursement request
 *   Can edit their reimbursement request
 *   Can also view all of their status post by their employee id
 *
 * @author Jia ying Li
 */
@RestController
@RequestMapping("employee")
//@PreAuthorize("isAuthenticated()")
public class EmployeeController {
    @Value("${server.port}")
    int port;

    private final ReimbFormService reimbFormService;

    private final StatusService statusService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(ReimbFormService reimbFormService, StatusService statusService) {
        this.reimbFormService = reimbFormService;
        this.statusService = statusService;
    }

    /**
     * For employee view all of their reimbursement request by their employee id
     *
     * @param employeeId employees' id
     * @return all the reimbursement post the employee have requested
     */
    @GetMapping(value = "/view/{employeeId}")
    public ResponseEntity<List<ReimbForm>> getAllByEmployeeId(@PathVariable(value = "employeeId") Integer employeeId) {
        LOGGER.info("Viewing All Reimbursement By employee Id");
        List<ReimbForm> reimbForm = reimbFormService.getAllById(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(reimbForm);
    }

    /**
     * Employee can create new reimbursement request
     *
     * @param reimbForm the reimbursement request form
     * @return the reimbursement request form the employee have submitted
     */
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReimbForm createNewReimbForm(@RequestBody ReimbForm reimbForm) {
        LOGGER.info("Creating new reimbursement request.");
        return reimbFormService.save(reimbForm);
    }

    /**
     * Employees are able to edit their request
     *
     * @param updateReimbForm the updated reimbursement request form
     * @return the updated reimbursement request form the employee have edited
     */
    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateReimbForm(@RequestBody ReimbForm updateReimbForm) throws IdNotFoundException {
        LOGGER.info("Editing reimbursement post.");
        reimbFormService.update(updateReimbForm);
        return ResponseEntity.status(HttpStatus.OK).body(updateReimbForm);
    }

    /**
     * Employee can view all of their own status post by their employee id
     *
     * @param requestId employee's id
     * @return all the repsonse posts for employee's reimbursement request
     */
    @GetMapping(value = "/view/status/{requestId}")
    public ResponseEntity<List<Status>> getAllByRequesterId(@PathVariable(value = "requestId")
                                                                             Integer requestId) {
        LOGGER.info("Viewing all of my status post for my reimbursement.");
        List<Status> status = statusService.getAllById(requestId);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }


}
