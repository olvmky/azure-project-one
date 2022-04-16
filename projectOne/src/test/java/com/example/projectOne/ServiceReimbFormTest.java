package com.example.projectOne;

import com.example.projectOne.DAO.ReimbFormRepository;
import com.example.projectOne.models.ReimbForm;
import com.example.projectOne.services.ReimbFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServiceReimbFormTest {

    @Mock
    private ReimbFormRepository reimbFormRepository;

    @InjectMocks
    private ReimbFormService reimbFormService;

    @BeforeEach
    public void initBeforeTest() {
        reimbFormRepository = mock(ReimbFormRepository.class);
        reimbFormService = new ReimbFormService();
        reimbFormService.setReimbFormRepository(reimbFormRepository);
    }

    @Test
    void shouldReturnAllModels() {
        when(reimbFormRepository.findAll()).thenReturn(Collections.emptyList());
        List<ReimbForm> reimbForms = reimbFormService.getAll();
        assertTrue(reimbForms.isEmpty());
    }

    @Test
    void shouldReturnAllModelsbyId() {
        ReimbForm reimbForm = new ReimbForm(5,5,"Olivia Miuki","2022/02/16",
                "none for now",588,"2022/02/16");
        when(reimbFormRepository.getAllByEmployeeId(reimbForm.getEmployeeId())).thenReturn(Collections.emptyList());
        List<ReimbForm> reimbForms = reimbFormService.getAllById(reimbForm.getEmployeeId());
        assertTrue(reimbForms.isEmpty());
    }

    @Test
    void shouldReturnSavedModel() {
        ReimbForm reimbForm = new ReimbForm(5,5,"Olivia Miuki","2022/02/16",
                "none for now",588,"2022/02/16");

        given(reimbFormRepository.save(reimbForm)).willReturn(reimbForm);

        ReimbForm savedReimbForm = reimbFormService.save(reimbForm);
        org.assertj.core.api.Assertions.assertThat(savedReimbForm).isNotNull();
    }
}
