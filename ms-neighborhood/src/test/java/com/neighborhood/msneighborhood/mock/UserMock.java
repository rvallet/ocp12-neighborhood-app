package com.neighborhood.msneighborhood.mock;

import com.neighborhood.msneighborhood.entities.Address;
import com.neighborhood.msneighborhood.entities.NeighborGroup;
import com.neighborhood.msneighborhood.entities.User;
import com.neighborhood.msneighborhood.enumerated.UserRoleEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMock {

    public static User getMockUser(){
        User user = new User(
                "user1@email.com",
                "lastName",
                "firstName",
                "password",
                UserRoleEnum.USER.toString()
        );
        user.setId(0L);
        user.setAddress(getMockAdress());
        return user;
    }

    public static List<User> getMockUserList(){
        return Arrays.asList(getMockUser(), getMockUser());
    }

    public static NeighborGroup getMockNeighborGroup(String groupName){
        NeighborGroup result = new NeighborGroup(groupName, getMockUserList());
        result.setId(0L);
        return result;
    }

    public static NeighborGroup getMockNeighborGroup(){
        NeighborGroup result = new NeighborGroup("groupName", getMockUserList());
        result.setId(0L);
        return result;
    }

    public static List<NeighborGroup> getMockNeighborGroupList(int size){
        List<NeighborGroup> result = new ArrayList<>();

        for (int i=1; i<size+1; i++) {
            result.add(getMockNeighborGroup("groupName #"+i));
        }

        return result;
    }

    public static Address getMockAdress(){
        return new Address("num","nomVoie","codePostal","Ville");
    }

}
