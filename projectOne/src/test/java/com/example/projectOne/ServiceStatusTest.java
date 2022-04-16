package com.example.projectOne;

import com.example.projectOne.DAO.StatusRepository;
import com.example.projectOne.models.Status;
import com.example.projectOne.services.StatusService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceStatusTest {

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private StatusService statusService;

    private Status status = new Status(3,5,"Olivia Miuki", 588,"approved",
            "approved with no comment", "August Duet", "2022/02/15");

    @BeforeEach
    public void initBeforeTest() {
        statusRepository = mock(StatusRepository.class);
        statusService = new StatusService();
        statusService.setStatusRepository(statusRepository);
    }


    @Test
    void shouldReturnAllModels() {
        when(statusRepository.findAll()).thenReturn(Collections.emptyList());
        List<Status> statuses = statusService.getAll();
        assertTrue(statuses.isEmpty());
    }

    @Test
    void shouldReturnAllModelsbyRequestId() {
        status = new Status(3,5,"Olivia Miuki", 588,"approved",
                "approved with no comment", "August Duet", "2022/02/15");
        when(statusRepository.getAllByRequestId(status.getRequestId())).thenReturn(Collections.emptyList());
        List<Status> savedStatus = statusService.getAllById(status.getRequestId());
        assertTrue(savedStatus.isEmpty());
    }

    @Test
    void shouldReturnSavedModel() {
        given(statusRepository.save(status)).willReturn(status);
        Status savedStatus = statusService.save(status);
        Assertions.assertThat(savedStatus).isNotNull();
    }

}
