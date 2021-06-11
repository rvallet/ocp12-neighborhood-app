package com.neighborhood.website.controller;

import com.neighborhood.website.beans.UserBean;
import com.neighborhood.website.dto.MailDto;
import com.neighborhood.website.dto.UserRegistrationDto;
import com.neighborhood.website.proxies.MicroServiceBatchProxy;
import com.neighborhood.website.proxies.MicroServiceNeighborhoodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NeighborController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeighborController.class);

    @Autowired
    MicroServiceNeighborhoodProxy microServiceNeighborhoodProxy;

    @Autowired
    MicroServiceBatchProxy microServiceBatchProxy;

    @ModelAttribute("email")
    public MailDto mailDto() {
        return new MailDto();
    }

    @GetMapping(path="/my-neighbors")
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

    @GetMapping(path= {"/contact-neighbor"})
    public String contactNeighbor(
            @RequestParam(name="id_neighbor") Long neighborId,
            @RequestParam(name="id_user") Long userId,
            Model model
    ) {
        UserBean user = microServiceNeighborhoodProxy.getUserById(userId);
        UserBean neighbor = microServiceNeighborhoodProxy.getUserById(neighborId);

        model.addAttribute("user", user);
        model.addAttribute("neighbor" , neighbor);
        model.addAttribute("email" , new MailDto());
        return "contact-neighbor";
    }

    @PostMapping(path= {"/send-mail"})
    public String sendEmail(
            @ModelAttribute("email") @Valid MailDto email,
            @RequestParam(name="from") String from,
            @RequestParam(name="to") String to,
            @RequestParam(name="id_neighbor") Long neighborId,
            @RequestParam(name="id_user") Long userId,
            BindingResult result,
            Model model
    ) {

        if(result.hasErrors()){
            LOGGER.debug("form has {} error(s) - First {}", result.getErrorCount(), result.getFieldError());
            model.addAttribute("id_neighbor",neighborId);
            model.addAttribute("id_user",userId);
            return "contact-neighbor";
        }

        email.setFrom(from);
        email.setTo(to);

        LOGGER.info(
                "Envoie d'un mail :\nFrom {} To {}\nObjet : {} \nContent {}",
                email.getFrom(),
                email.getTo(),
                email.getObjet(),
                email.getContent()
        );
        microServiceBatchProxy.sendMail(email);
        return "redirect:/my-neighbors";
    }

}
