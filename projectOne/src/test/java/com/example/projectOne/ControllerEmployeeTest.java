package com.example.projectOne;

import com.example.projectOne.models.ReimbForm;
import com.example.projectOne.services.ReimbFormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ProjectOneApplication.class})
public class ControllerEmployeeTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReimbFormService reimbFormService;

//    @Before
//    public void setUp() {
//        this.mockMvc = webAppContextSetup(webApplicationContext).build();
//    }

//    @Test
//    public void shouldCreateReimbForm() throws Exception {
//        ReimbForm reimbForm = new ReimbForm(5,5,"Olivia Miuki","2022/02/16",
//                "none for now",588,"2022/02/16");
//        when(reimbFormService.save(any(ReimbForm.class))).thenReturn(reimbForm);
//
//        ResultActions response = mockMvc.perform(post("/employee/new")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(reimbForm)));
//
//        response.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.employeeId").value(reimbForm.getEmployeeId()))
//                .andExpect(jsonPath("$.employeeName").value(reimbForm.getEmployeeName()))
//                .andExpect(jsonPath("$.expenseDate").value(reimbForm.getExpenseDate()))
//                .andExpect(jsonPath("$.description").value(reimbForm.getDescription()))
//                .andExpect(jsonPath("$.amount").value(reimbForm.getAmount()))
//                .andExpect(jsonPath("$.requestDate").value(reimbForm.getRequestDate()));
//    }
//
//    @Test
//    public void shouldGetAllByEmployeeId() throws Exception{
//        Integer employeeId = 5;
//        List<ReimbForm> reimbFormList = new ArrayList<>();
//
//        ReimbForm reimbForm = new ReimbForm(5,5,"Olivia Miuki","2022/02/16",
//                "none for now",588,"2022/02/16");
//        reimbFormList.add(reimbForm);
//
//        given(reimbFormService.getAllById(reimbFormList.get(0).getEmployeeId())).willReturn(reimbFormList);
//
//        ResultActions response = mockMvc.perform(get("/employee/view/{employeeId}", employeeId));
//
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.size()", is(reimbFormList.size())));
//    }

}


