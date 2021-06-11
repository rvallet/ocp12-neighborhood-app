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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<ServiceRequestBean> serviceList = new ArrayList<>();
        Long neighborgroupId = u.getNeighborGroup().getId();

        if (neighborgroupId !=null) {
            serviceList = microServiceNeighborhoodProxy.getServiceRequestListByNeighborgroupId(neighborgroupId);
        }

        model.addAttribute("serviceList", serviceList);
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
        serviceRequest.setRequestType(type);
        serviceRequest.setDescription(desc);
        microServiceNeighborhoodProxy.createServiceRequest(serviceRequest, user.getId());

        return "redirect:/services";
    }

    @GetMapping(path = {"/validatedService"})
    public String validatedService (
            @RequestParam(name="id_service") Long serviceId,
            @RequestParam(name="id_user") Long userId,
            Model model
    ) {
        LOGGER.info("Envoie d'une demande de mise à jour du serviceId {} par l'utilisateurId {}", serviceId, userId);
        microServiceNeighborhoodProxy.processServiceResponse(serviceId, userId);

        return "redirect:/services";
    }

    @GetMapping(path = {"/user/close-loan"})
    public String closeLoan (
            @RequestParam(name="id_loan") Long loanId)
            {
        LOGGER.info("Envoie d'une demùande de fermeture de l'emprunt loanId {}", loanId);
        microServiceNeighborhoodProxy.closeLoan(loanId);

        return "redirect:/user/profil#nav-loan";
    }
}
