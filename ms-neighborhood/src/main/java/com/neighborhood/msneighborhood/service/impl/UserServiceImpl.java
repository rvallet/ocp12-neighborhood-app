package com.neighborhood.msneighborhood.service.impl;

import com.neighborhood.msneighborhood.entities.User;
import com.neighborhood.msneighborhood.enumerated.UserRoleEnum;
import com.neighborhood.msneighborhood.repository.UserRepository;
import com.neighborhood.msneighborhood.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        LOGGER.info("Utilisateur {} enregistré", user.getEmail());
        return user;
    }

    @Override
    public List<User> saveAll(List<User> userList) {
        return userRepository.saveAll(userList);
    }

    @Override
    public Page<User> findPaginatedUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public List<String> getRoleList() {
        List<String> result = new ArrayList<>();
        for (UserRoleEnum role : UserRoleEnum.values()) {
            result.add(role.toString());
        }
        LOGGER.info("Envoi d'une liste de {} rôles utilisateurs", result.size());
        return result;
    }

}
