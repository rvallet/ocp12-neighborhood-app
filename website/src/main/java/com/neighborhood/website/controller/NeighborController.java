package com.neighborhood.website.controller;

import com.neighborhood.website.proxies.MicroServiceNeighborhoodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NeighborController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeighborController.class);

    @Autowired
    MicroServiceNeighborhoodProxy microServiceNeighborhoodProxy;

    @GetMapping(path= {"/my-neighbors"})
    public String mesVoisins () {
        LOGGER.info("Accès à la page mes voisins");
        return "my-neighbors";
    }

}
