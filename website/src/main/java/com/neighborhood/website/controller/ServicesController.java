package com.neighborhood.website.controller;

import com.neighborhood.website.beans.ServiceRequestBean;
import com.neighborhood.website.beans.UserBean;
import com.neighborhood.website.proxies.MicroServiceNeighborhoodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ServicesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicesController.class);

    @Autowired
    MicroServiceNeighborhoodProxy microServiceNeighborhoodProxy;

    @GetMapping(path= {"/services"})
    public String lesServices (Model model) {
        LOGGER.info("Accès à la page des services");
        UserBean u = microServiceNeighborhoodProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<String> typeList = microServiceNeighborhoodProxy.getServiceRequestTypeList();
        model.addAttribute("user", u);
        model.addAttribute("typeList", typeList);
        return "services";
    }

    @PostMapping(path= {"/services/create-serviceRequest"})
    public String createServiceRequest (
            @RequestParam(name="type") String type,
            @RequestParam(name="desc") String desc,
            Model model) {
        LOGGER.info("Création d'une demande de service");
        UserBean user = microServiceNeighborhoodProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        ServiceRequestBean serviceRequest = new ServiceRequestBean();
        serviceRequest.setUser(user);
        //TODO: Alimenter avec les champs renseignés
        serviceRequest.setRequestType(type);
        serviceRequest.setDescription(desc);
        microServiceNeighborhoodProxy.createServiceRequest(serviceRequest, user.getId());

        return "redirect:/services";
    }
}
