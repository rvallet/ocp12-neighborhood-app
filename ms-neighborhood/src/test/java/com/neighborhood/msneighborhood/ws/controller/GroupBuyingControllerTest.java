package com.neighborhood.msneighborhood.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighborhood.msneighborhood.api.ApiRegistration;
import com.neighborhood.msneighborhood.enumerated.GroupBuyingStatusEnum;
import com.neighborhood.msneighborhood.service.UserService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GroupBuyingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    GroupBuyingController groupBuyingController;

    @Autowired
    UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/init_db.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    void getGroupBuyingsList() throws Exception {
        // @formatter:off
        mockMvc.perform(MockMvcRequestBuilders
                .get(ApiRegistration.REST_GET_GROUP_BUYINGS_LIST)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(1))); // Un seul achat groupé en cours
        // @formatter:on
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/init_db.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    void getCurrentGroupBuyingsList() throws Exception {
        // @formatter:off
        mockMvc.perform(MockMvcRequestBuilders
                .get(ApiRegistration.REST_GET_CURRENT_GROUP_BUYINGS_LIST)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(2))); // 2 achats groupés en tout
        // @formatter:on

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/init_db.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    void closeGroupBuying() throws Exception {
        final long groupBuyingId = 2L;
        // @formatter:off
        mockMvc.perform(MockMvcRequestBuilders
                .get(ApiRegistration.REST_GET_CLOSE_GROUP_BUYING + "/" + groupBuyingId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.groupBuyingStatus").value(GroupBuyingStatusEnum.CLOSED.toString()));
        // @formatter:on

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/init_db.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    void archiveGroupBuying() throws Exception {
        final long groupBuyingId = 2L;
        // @formatter:off
        mockMvc.perform(MockMvcRequestBuilders
                .get(ApiRegistration.REST_GET_ARCHIVE_GROUP_BUYING + "/" + groupBuyingId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.groupBuyingStatus").value(GroupBuyingStatusEnum.OFFLINE.toString()));
        // @formatter:on
    }

    void updateGroupBuying(){

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/init_db.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    void getGroupBuyingById() throws Exception {
        final long groupBuyingId = 2L;
        // @formatter:off
        mockMvc.perform(MockMvcRequestBuilders
                .get(ApiRegistration.REST_GET_GROUP_BUYINGS_BY_ID + "/" + groupBuyingId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(groupBuyingId));
        // @formatter:on

    }
}
