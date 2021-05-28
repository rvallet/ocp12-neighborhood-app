package com.neighborhood.website.controller;

import com.neighborhood.website.beans.UserBean;
import com.neighborhood.website.proxies.MicroServiceNeighborhoodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicesController.class);

    @Autowired
    MicroServiceNeighborhoodProxy microServiceNeighborhoodProxy;

    @GetMapping(path= {"/services"})
    public String lesServices (Model model) {
        LOGGER.info("Accès à la page des services");
        UserBean u = microServiceNeighborhoodProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("user", u);
        return "services";
    }
}
