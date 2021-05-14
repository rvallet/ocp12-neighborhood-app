package com.neighborhood.website.service.impl;

import com.neighborhood.website.beans.UserBean;
import com.neighborhood.website.proxies.MicroServiceNeighborhoodProxy;
import com.neighborhood.website.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    MicroServiceNeighborhoodProxy microServiceNeighborhoodProxy;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername START with email = {}", email);
        UserBean user = microServiceNeighborhoodProxy.getUserByEmail(email);

        if (user == null){
            LOGGER.warn("loadUserByUsername FAILED");
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        LOGGER.info("Found - loadUserByUsername with {} {} {}",user.getEmail(),user.getPassword(),user.getRole());
        org.springframework.security.core.userdetails.User uControl = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                convertRoleEnumToAuthorities(user.getRole()));
        LOGGER.info("Found - loadUserByUsername with {}", uControl);
        return uControl;
    }

    private Collection<? extends GrantedAuthority> convertRoleEnumToAuthorities(String role){
        return AuthorityUtils.createAuthorityList(role.toUpperCase());
    }

}
