package com.neighborhood.website.controller;

import com.neighborhood.website.beans.GroupBuyingBean;
import com.neighborhood.website.beans.LoanBean;
import com.neighborhood.website.beans.NeighborGroupBean;
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
public class ProfilController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilController.class);

    @Autowired
    MicroServiceNeighborhoodProxy microServiceNeighborhoodProxy;

    @GetMapping("/user/profil")
    public String userProfil(Model model) {

        UserBean u = microServiceNeighborhoodProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user" , u );

        List<LoanBean> loanList = microServiceNeighborhoodProxy.getLoansByUserId(u.getId());
        model.addAttribute("loanList" , loanList);

        List<LoanBean> filtredLoanList = loanList.stream().filter(e -> e.getLoanStatus().equalsIgnoreCase("En cours")).collect(Collectors.toList());
        model.addAttribute("filtredLoanList", filtredLoanList);

        List<ServiceRequestBean> serviceRequestList = (List<ServiceRequestBean>) u.getServiceRequestList();
        model.addAttribute("serviceRequestList" , serviceRequestList);

        List<ServiceRequestBean> filtredServiceList = serviceRequestList.stream().filter(e -> e.getRequestStatus().equalsIgnoreCase("En cours")).collect(Collectors.toList());
        model.addAttribute("filtredServiceList", filtredServiceList);

        List<GroupBuyingBean> groupBuyingList = microServiceNeighborhoodProxy.getCurrentGroupBuyingsList();

        List<GroupBuyingBean> filtredGroupBuyingList = new ArrayList<>();
        groupBuyingList.forEach(e -> {
            if (e.getUserList().stream().anyMatch(user -> user.getId() == u.getId())) filtredGroupBuyingList.add(e);
        });

        model.addAttribute("filtredGroupBuyingList", filtredGroupBuyingList);

        LOGGER.info(
                "Récupération d'une liste de {} emprunts pour l'utilisateur id {}",
                loanList.size(),
                u.getId()
        );

        return "user/profil";
    }

    @GetMapping("/member/profil")
    public String memberProfil(Model model) {

        UserBean u = microServiceNeighborhoodProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user" , u );

        List<UserBean> usersList = microServiceNeighborhoodProxy.getUsers();
        model.addAttribute("usersList" , usersList);

        List<UserBean> filtredUsersList = usersList.stream()
                .filter(e -> (e.getNeighborGroup() !=null && e.getNeighborGroup().getId() == u.getNeighborGroup().getId()))
                .collect(Collectors.toList());
        model.addAttribute("filtredUsersList", filtredUsersList);

        LOGGER.info(
                "Récupération d'une liste de {} utilisateurs pour l'utilisateur id {}",
                usersList.size(),
                u.getId()
        );

        return "member/profil";
    }

    @GetMapping("/member/edit-user")
    public String memberEditUser (
            @RequestParam(name="id", required = false) Long userId,
            Model model
    ) {
        LOGGER.info("Chargement de la page de modification Utilisateur {}", userId);
        UserBean user = microServiceNeighborhoodProxy.getUserById(userId);
        model.addAttribute("user", user);

        List<String> roleList = microServiceNeighborhoodProxy.getRoleList();
        model.addAttribute("roleList", roleList);

        List<NeighborGroupBean> neighborGroupList = microServiceNeighborhoodProxy.getNeighborGroupList();
        model.addAttribute("neighborGroupList", neighborGroupList);

        return "/member/edit-user";
    }

    @PostMapping("/member/update-user")
    public String memberUpdateUser (
            @RequestParam(name="id") Long userId,
            @RequestParam(name="role", required = false) String role,
            @RequestParam(name="neighborGroupId", required = false) Long neighborGroupId
    ) {
        UserBean user = microServiceNeighborhoodProxy.getUserById(userId);

        /* UpDate Role */
        if (role!=null) {
            LOGGER.info(
                    "Mise à jour de l'utilisateur id {} :\n Role : {} ==> {}",
                    userId,
                    user.getRole(),
                    role);
            user.setRole(role);
        }

        /* UpDate neighborGroup */
        if (neighborGroupId!=null) {
            List<NeighborGroupBean> neighborGroup = microServiceNeighborhoodProxy.getNeighborGroupList().stream()
                    .filter(e -> e.getId() == neighborGroupId)
                    .collect(Collectors.toList());
            if (!neighborGroup.isEmpty()) {
                user.setNeighborGroup(neighborGroup.get(0));
            }
        }

        microServiceNeighborhoodProxy.saveUser(user);
        return "redirect:/member/profil#nav-users";
    }

}
