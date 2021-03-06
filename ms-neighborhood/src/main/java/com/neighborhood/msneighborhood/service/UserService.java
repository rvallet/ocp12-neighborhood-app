package com.neighborhood.msneighborhood.service;

import com.neighborhood.msneighborhood.entities.NeighborGroup;
import com.neighborhood.msneighborhood.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findUserById (Long id);
    List<User> findUsersByNeighborGroupId (Long groupId);
    User findUserByEmail (String email);
    User saveUser(User user);
    List<User> saveAll(List<User> userList);
    Page<User> findPaginatedUsers(int pageNumber, int pageSize);
    List<String> getRoleList ();
    List<NeighborGroup> getNeighborGroupList ();

}
