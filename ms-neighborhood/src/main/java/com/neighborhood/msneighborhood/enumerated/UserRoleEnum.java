package com.neighborhood.msneighborhood.enumerated;

public enum UserRoleEnum {

    USER ("user"),
    MEMBER("member"),
    ADMIN ("admin");

    private String userRole;

    UserRoleEnum(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return userRole;
    }

}
