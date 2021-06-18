package com.neighborhood.website.controller;

import com.neighborhood.website.beans.GroupBuyingBean;
import com.neighborhood.website.beans.ServiceRequestBean;
import com.neighborhood.website.beans.UserBean;
import com.neighborhood.website.proxies.MicroServiceNeighborhoodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ServicesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicesController.class);

    @Autowired
    MicroServiceNeighborhoodProxy microServiceNeighborhoodProxy;

    /* Services */
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
            @RequestParam(name = "type") String type,
            @RequestParam(name = "desc") String desc,
            @RequestParam(name = "file", required = false) MultipartFile file,
            Model model) {
        LOGGER.info("Création d'une demande de service");
        UserBean user = microServiceNeighborhoodProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        ServiceRequestBean serviceRequest = new ServiceRequestBean();
        serviceRequest.setUser(user);
        serviceRequest.setRequestType(type);
        serviceRequest.setDescription(desc);

        if(!file.isEmpty()) {
            // FileName normalize and store
            final String UPLOAD_DIR = "website/src/main/resources/static/img/upload/";
            final String TH_IMG_ROOT_PATH = "/img/upload/";
            String fileName = "service_"+System.currentTimeMillis()+"_"+ StringUtils.cleanPath(file.getOriginalFilename());
            try {
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                serviceRequest.setImgPathThAttribute(TH_IMG_ROOT_PATH+fileName);
            } catch (IOException e) {
                LOGGER.debug("SERVICE -> {} upload failed copy into {}", fileName, UPLOAD_DIR);
                LOGGER.warn(e.getMessage());
            }
        }
        LOGGER.info("L'utilisateur {} à créer une demande de service (IMG : {})", user.getEmail(), serviceRequest.getImgPathThAttribute());
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

    /* Loans */

    @GetMapping(path = {"/user/close-loan"})
    public String closeLoan (
            @RequestParam(name="id_loan") Long loanId)
            {
        LOGGER.info("Envoie d'une demande de fermeture de l'emprunt loanId {}", loanId);
        microServiceNeighborhoodProxy.closeLoan(loanId);

        return "redirect:/user/profil#nav-loan";
    }

    /* GroupBuying */

    @GetMapping(path= {"/group-buying"})
    public String groupBuying (Model model) {
        LOGGER.info("Accès à la page des achats groupés");
        UserBean u = microServiceNeighborhoodProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        List<GroupBuyingBean> groupBuyingList = new ArrayList<>();
        Long neighborgroupId = u.getNeighborGroup().getId();
        List<Boolean> isAlreadyParticipate = new ArrayList<>();

        if (neighborgroupId !=null) {
            groupBuyingList = microServiceNeighborhoodProxy.getGroupBuyingsList();
            groupBuyingList.stream()
                    .filter(e -> e.getNeighborGroupId() == neighborgroupId)
                    .filter(e -> e.getGroupBuyingStatus().equalsIgnoreCase("En cours"))
                    .collect(Collectors.toList());
            groupBuyingList.forEach(e -> isAlreadyParticipate.add(e.getUserList().stream().anyMatch(user -> user.getId() == u.getId())));
        }

        model.addAttribute("groupBuyingList", groupBuyingList);
        model.addAttribute("user", u);
        model.addAttribute("isAlreadyParticipate", isAlreadyParticipate);
        return "group-buying";
    }

    @GetMapping(path= {"/group-buying/add-user"})
    public String groupBuyingAddUser (
            @RequestParam(name = "id_groupBuy") Long groupBuyId,
            @RequestParam(name = "id_user") Long userId,
            Model model) {
        LOGGER.info("Participation de l'utilsateur id {} à l'achat groué Id {}", userId, groupBuyId);
        microServiceNeighborhoodProxy.updateGroupBuying(groupBuyId, userId);
        return "redirect:/group-buying";
    }

    @GetMapping(path= {"/group-buying/close"})
    public String closeGroupBuying (
            @RequestParam(name = "id_groupBuy") Long groupBuyId,
            Model model) {
        LOGGER.info("Clôture de l'achat groupé id {}", groupBuyId);
        microServiceNeighborhoodProxy.closeGroupBuying(groupBuyId);
        return "redirect:/group-buying";
    }

}
