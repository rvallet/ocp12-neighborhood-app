package com.neighborhood.msneighborhood.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighborhood.msneighborhood.api.ApiRegistration;
import com.neighborhood.msneighborhood.entities.ServiceRequest;
import com.neighborhood.msneighborhood.enumerated.ServiceRequestTypeEnum;
import com.neighborhood.msneighborhood.service.ServiceRequestService;
import com.neighborhood.msneighborhood.service.UserService;
import org.junit.jupiter.api.Assertions;
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

import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static com.neighborhood.msneighborhood.mock.ServiceRequestMock.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ServiceRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ServiceRequestService serviceRequestService;

    @Autowired
    UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/init_db.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    void findServiceRequestListByUserId() throws Exception {
        final long userId = 5L;
        // @formatter:off
        mockMvc.perform(MockMvcRequestBuilders
                .get(ApiRegistration.REST_REQUEST_SERVICE_LIST_BY_USER_ID + "/" + userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(4)));
        // @formatter:on
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/init_db.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    void createServiceRequest() throws Exception {
        final long userId = 2L;
        ServiceRequest serviceRequestInitial = getServiceRequestMock(ServiceRequestTypeEnum.MISCELLANEOUS_SERVICE);
        serviceRequestInitial.setDescription("Description");
        serviceRequestInitial.setImgPathThAttribute("ImgPath");

        // @formatter:off
        mockMvc.perform(MockMvcRequestBuilders
                .post(ApiRegistration.REST_CREATE_SERVICE_REQUEST + "/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(serviceRequestInitial))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // @formatter:on

        List<ServiceRequest> serviceRequestList = serviceRequestService.findServiceRequestsByUserId(userId);

        Assertions.assertEquals(
                1,
                serviceRequestList.size(),
                "Ajout d'une entrée pour l'utilsateur id" + userId
        );

        Assertions.assertEquals(
                userId,
                serviceRequestList.get(0).getUser().getId(),
                "Ajout d'une entrée pour l'utilsateur id" + userId
        );


    }

    @Test
    void getServiceRequestTypeList() throws Exception {
        int size = ServiceRequestTypeEnum.values().length;

        // @formatter:off
        mockMvc.perform(MockMvcRequestBuilders
                .get(ApiRegistration.REST_GET_SERVICE_REQUEST_TYPE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(size)));
        // @formatter:on


    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:sql/init_db.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:sql/clean_db.sql")
    void findServiceRequestListByNeighborGroupId() throws Exception {
        final long neighborGroupId = 1L;
        // @formatter:off
        mockMvc.perform(MockMvcRequestBuilders
                .get(ApiRegistration.REST_GET_SERVICE_REQUEST_LIST_BY_GROUP_ID + "/" + neighborGroupId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(3))); // 3 entrées status actif
        // @formatter:on
    }

}
