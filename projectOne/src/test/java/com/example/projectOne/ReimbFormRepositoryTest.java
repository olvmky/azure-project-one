package com.example.projectOne;

import com.example.projectOne.DAO.ReimbFormRepository;
import com.example.projectOne.models.ReimbForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ReimbFormRepositoryTest {

    @Mock
    private ReimbFormRepository reimbFormRepository;

    @Test
    void shouldReturnAllByEmployeeId(){
        ReimbForm reimbForm = new ReimbForm(5,5,"Olivia Miuki","2022/02/16",
                "none for now",588,"2022/02/16");

        reimbFormRepository.save(reimbForm);

        List<ReimbForm> reimbForms = reimbFormRepository.getAllByEmployeeId(reimbForm.getEmployeeId());
        Assertions.assertThat(reimbForms).isNotNull();
    }
}
