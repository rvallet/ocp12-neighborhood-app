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
    String REST_GET_NEIGHBOR_GROUP_LIST ="/neighbors/getNeighborGroupList";

    /* Loans */
    String REST_GET_LOANS_LIST_BY_USER_ID = "/findLoansListByUserId";
    String REST_GET_LOANS_LIST = "/getLoansList";
    String REST_GET_CLOSE_LOAN = "/closeLoan";

    /* Services */
    String REST_SERVICES = "/services";
    String REST_REQUEST_SERVICE_LIST_BY_USER_ID = "/findServiceRequestsByUserId";
    String REST_CREATE_SERVICE_REQUEST = "/createServiceRequest";
    String REST_GET_SERVICE_REQUEST_TYPE = "/getServiceRequestTypeList";
    String REST_GET_SERVICE_REQUEST_LIST_BY_GROUP_ID = "/getServiceRequestListByNeighborgroupId";
    String REST_GET_PROCESS_SERVICE_RESPONSE = "/processServiceResponse";

    /* GroupBuyings */
    String REST_UPDATE_GROUP_BUYING = "/updateGroupBuying";
    String REST_GET_GROUP_BUYINGS_LIST = "/getGroupBuyingsList";
    String REST_GET_CURRENT_GROUP_BUYINGS_LIST = "/getCurrentGroupBuyingsList";
    String REST_GET_CLOSE_GROUP_BUYING = "/closeGroupBuying";
    String REST_GET_ARCHIVE_GROUP_BUYING = "/archiveGroupBuying";
    String REST_GET_GROUP_BUYINGS_BY_ID = "/getGroupBuyingById";
}
