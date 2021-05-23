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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NeighborController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeighborController.class);

    @Autowired
    MicroServiceNeighborhoodProxy microServiceNeighborhoodProxy;

    @GetMapping(path= {"/my-neighbors"})
    public String mesVoisins (Model model) {
        UserBean u = microServiceNeighborhoodProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        LOGGER.info("Accès à la page mes voisins");

        List<UserBean> neighborsList = new ArrayList<>();
        if (u!=null && u.getNeighborGroup() !=null) {
            neighborsList = microServiceNeighborhoodProxy.getNeighborsByGroupId(u.getNeighborGroup().getId());
            neighborsList  = neighborsList.stream()
                    .filter(o -> !o.getEmail().equals(u.getEmail()))
                    .collect(Collectors.toList());
        }
        model.addAttribute("user", u);
        model.addAttribute("neighborsList" , neighborsList);

        return "my-neighbors";
    }

}
