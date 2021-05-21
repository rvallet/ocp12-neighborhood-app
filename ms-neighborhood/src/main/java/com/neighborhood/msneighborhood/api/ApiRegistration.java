package com.neighborhood.msneighborhood.api;

public interface ApiRegistration {

    /* Service (Commons) */
    String SERVICE_ID = "NEIGHBORHOOD";
    String REST_PREFIX = "/ms-neighborhood";
    String REST_PAGINATION = "/page";

    /* Users */
    String REST_USERS = "/users";
    String REST_NEIGHBORS = "/neighbors";
    String REST_GET_USER_BY_EMAIL = "/getUserByEmail";
    String REST_GET_USER_BY_ID = "/getUserById";
    String REST_SAVE_USER = "/saveUser";
    String REST_GET_ROLE_LIST ="/getRoleList";

    /* Loans */
    String REST_LOANS_LIST_BY_USER_ID = "/findLoansListByUserId";
}
