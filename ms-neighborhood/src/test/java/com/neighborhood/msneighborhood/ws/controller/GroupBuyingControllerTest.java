package com.neighborhood.msneighborhood.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighborhood.msneighborhood.service.ServiceRequestService;
import com.neighborhood.msneighborhood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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


}
