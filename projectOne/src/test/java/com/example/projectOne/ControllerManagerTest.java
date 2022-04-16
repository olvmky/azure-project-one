package com.example.projectOne;

import com.example.projectOne.models.Status;
import com.example.projectOne.services.StatusService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ProjectOneApplication.class})
public class ControllerManagerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private StatusService statusService;

//    @Before
//    public void setUp() {
//        this.mockMvc = webAppContextSetup(webApplicationContext).build();
//    }

//    @Test
//    public void shouldReturnAllStatusPost() throws Exception {
//        List<Status> statusList = new ArrayList<>();
//        Status statues = new Status(3,5,"Olivia Miuki", 588,"approved",
//                "approved with no comment", "August Duet", "2022/02/15");
//        statusList.add(statues);
//
//        given(statusService.getAll()).willReturn(statusList);
//        ResultActions response = mockMvc.perform(get("/manager/status/getall"));
//
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.size()",is(statusList.size())));
//    }
//
//    @Test
//    public void shouldDeleteStatusById() throws Exception {
//        Integer statusId = 3;
//        willDoNothing().given(statusService).deleteByStatusId(statusId);
//
//        ResultActions response = mockMvc.perform(delete("/manager/delete/{id}", statusId));
//
//        response.andExpect(status().isOk()).andDo(print());
//    }
}
