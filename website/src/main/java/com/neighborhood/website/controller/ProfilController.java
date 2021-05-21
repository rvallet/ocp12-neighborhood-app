package com.neighborhood.website.controller;

import com.neighborhood.website.beans.LoanBean;
import com.neighborhood.website.beans.UserBean;
import com.neighborhood.website.proxies.MicroServiceNeighborhoodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfilController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilController.class);

    @Autowired
    MicroServiceNeighborhoodProxy microServiceNeighborhoodProxy;

    @GetMapping("/user/profil")
    public String userProfil(Model model) {

        UserBean u = microServiceNeighborhoodProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user" , u );

        List<LoanBean> loanList = microServiceNeighborhoodProxy.getLoansByUserId(u.getId().toString());
        model.addAttribute("loanList" , loanList);

        LOGGER.info(
                "Récupération d'une liste de {} emprunts pour l'utilisateur id {}",
                loanList.size(),
                u.getId()
        );

        return "user/profil";
    }
}
