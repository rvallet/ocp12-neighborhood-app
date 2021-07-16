package com.neighborhood.msneighborhood.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighborhood.msneighborhood.api.ApiRegistration;
import com.neighborhood.msneighborhood.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    LoanService loanService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/init_db.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    void findLoansListByUserId() throws Exception {
        long userId = 5L;
        // @formatter:off
        mockMvc.perform(MockMvcRequestBuilders
                .get(ApiRegistration.REST_GET_LOANS_LIST_BY_USER_ID + "/" + userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(userId));
        // @formatter:on
    }

    void getLoansList() {

    }

    void closeLoan() {

    }

}
