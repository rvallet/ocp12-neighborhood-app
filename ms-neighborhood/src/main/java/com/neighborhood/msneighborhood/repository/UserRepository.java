package com.neighborhood.msneighborhood.repository;

import com.neighborhood.msneighborhood.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

    List<User> findAll();
    User findUserById (Long id);
    User findUserByEmail (String email);
    User findUserByLastName (String lastName);

}
