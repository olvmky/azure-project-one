package com.example.projectOne.Controller;

import com.example.projectOne.DAO.ReimbFormRepository;
import com.example.projectOne.Exception.IdNotFoundException;
import com.example.projectOne.models.ReimbForm;
import com.example.projectOne.models.Status;
import com.example.projectOne.services.EmailService;
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
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * ManagerController.java
 * Manger creates their responses to employees reimbursement request
 *   can also viewing all the reimbursement post from employees and response post
 *
 * @author Jia ying Li
 *
 */
@RestController
@RequestMapping("manager")
//@PreAuthorize("hasAuthority('MANAGER')")
public class ManagerController {
    //api.config.mail-url will become the one spring tries to wire
    //http://localhost:8088/mail will become the default value
    @Value("${api.config.mail-url:http://20.118.4.179:8088/mail}")
    String mailUrl;

    RestTemplate restTemplate;

    private final ReimbFormService reimbFormService;

    private final StatusService statusService;

    private final EmailService emailService;

    private final ReimbFormRepository reimbFormRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    public ManagerController(ReimbFormService reimbFormService, StatusService statusService,
                             RestTemplate restTemplate, EmailService emailService,
                             ReimbFormRepository reimbFormRepository) {
        this.reimbFormService = reimbFormService;
        this.statusService = statusService;
        this.restTemplate = restTemplate;
        this.emailService = emailService;
        this.reimbFormRepository = reimbFormRepository;
    }

    /**
     * Manager can view all reimbursements
     *
     * @return all the reimbursement post the employee have requested
     */
    @GetMapping("/getall")
    public ResponseEntity<List<ReimbForm>> getAllReimbForm() {
        LOGGER.info("Manager is viewing all reimbursement requests.");
        return new ResponseEntity<>(reimbFormService.getAll(), HttpStatus.OK);
    }

    /**
     * Employee can view all of their own status post by their employee id
     *
     * @param status response post for reimbursement request
     * @return saved response post the manager created, will also send out an email notification to the employee
     */
    //Manager can create new response for the form & will send out an email notification to the user
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewStatus(@RequestBody Status status) {
        LOGGER.info("Created response post for the reimbursement request & sending request for email notification");

        List<String> list = emailService.emailFormat(status);
        ResponseEntity<Object> resp = restTemplate.postForEntity(mailUrl, list, null);

        if(resp.getStatusCode().is5xxServerError()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(statusService.save(status));
    }

    /**
     * Manager can view all status post
     *
     * @return all the status post Manager responded
     */
    //Manager can view all status post
    @GetMapping("/status/getall")
    public ResponseEntity<List<Status>> getAllStatus() {
        LOGGER.info("Viewing All Status post.");
        return ResponseEntity.status(HttpStatus.OK).body(statusService.getAll());
    }

    /**
     * Manager can edit the status post such that reassign
     *
     * @param updateStatus  updated response post for reimbursement request
     * @return updated response post and will send out an email notification to the employee
     */
    @PutMapping(value = "/reassign", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStatus(@RequestBody Status updateStatus) throws IdNotFoundException {
        LOGGER.info("Reassign manager is editing the status post.");
        Status status = statusService.update(updateStatus);

        List<String> list = emailService.emailFormat(status);
        ResponseEntity<Object> resp = restTemplate.postForEntity(mailUrl, list, null);

        if(resp.getStatusCode().is5xxServerError()) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
    }

    /**
     * Manager can delete the status post by given status id
     *
     * @param statusId  the id for the status post that need to be delete
     * @return a string telling the status post for given status id have been deleted.
     */
    @DeleteMapping(value = "/delete/{statusId}")
    public ResponseEntity<String> deleteByStatusId(@PathVariable(value = "statusId") Integer statusId) {
        LOGGER.info("Deleting status post by given id");
        statusService.deleteByStatusId(statusId);
        return new ResponseEntity<String>(
                "status id " + statusId + " have been successfully deleted!", HttpStatus.OK);
    }
}
