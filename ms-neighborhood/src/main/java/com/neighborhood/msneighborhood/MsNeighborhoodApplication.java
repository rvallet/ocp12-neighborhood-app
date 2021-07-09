package com.neighborhood.msneighborhood;

import com.neighborhood.msneighborhood.config.ApplicationPropertiesConfig;
import com.neighborhood.msneighborhood.entities.Address;
import com.neighborhood.msneighborhood.entities.NeighborGroup;
import com.neighborhood.msneighborhood.entities.User;
import com.neighborhood.msneighborhood.security.WebSecurityConfig;
import com.neighborhood.msneighborhood.service.NeighborGroupService;
import com.neighborhood.msneighborhood.service.UserService;
import com.neighborhood.msneighborhood.utils.RandomTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.util.CollectionUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(scanBasePackages="com.neighborhood")
@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableFeignClients("com.neighborhood.msneighborhood")
@Profile("!test")
public class MsNeighborhoodApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MsNeighborhoodApplication.class, args);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MsNeighborhoodApplication.class);

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    private ApplicationPropertiesConfig appConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private NeighborGroupService neighborGroupService;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

        /* Initialize BDD with sample test */
        LOGGER.info("Recherche de l'existance des utilisateurs en BDD");

        boolean isBddInit = false;
        List<User> allUsers = userService.findAll();

        if (CollectionUtils.isEmpty(allUsers)) {
            LOGGER.info("Création d'un jeu de données utilisateur de test (table 'user')");
            isBddInit=true;

            List<User> userList = Arrays.asList(
                    new User(
                            "email@user1.fr",
                            "user1_lastName",
                            "user1_firstName",
                            webSecurityConfig.passwordEncoder().encode("passwordUser1"),
                            "user"
                    ),
                    new User(
                            "email@user2.fr",
                            "user2_lastName",
                            "user2_firstName",
                            webSecurityConfig.passwordEncoder().encode("passwordUser2"),
                            "user"
                    ),
                    new User(
                            "email@user3.fr",
                            "user3_lastName",
                            "user3_firstName",
                            webSecurityConfig.passwordEncoder().encode("passwordUser3"),
                            "user"
                    ),
                    new User(
                            "email@user4.fr",
                            "user4_lastName",
                            "user4_firstName",
                            webSecurityConfig.passwordEncoder().encode("passwordUser4"),
                            "user"
                    ),
                    new User(
                            "email@member1.fr",
                            "member1_lastName",
                            "member1_firstName",
                            webSecurityConfig.passwordEncoder().encode("passwordMember1"),
                            "member"
                    ),
                    new User(
                            "email@member2.fr",
                            "member2_lastName",
                            "member2_firstName",
                            webSecurityConfig.passwordEncoder().encode("passwordMember2"),
                            "member"
                    ),
                    new User(
                            "email@admin1.fr",
                            "admin1_lastName",
                            "admin1_firstName",
                            webSecurityConfig.passwordEncoder().encode("passwordAdmin1"),
                            "admin"
                    ),
                    new User(
                            "email@admin2.fr",
                            "admin2_lastName",
                            "admin2_firstName",
                            webSecurityConfig.passwordEncoder().encode("passwordAdmin2"),
                            "admin"
                    )
            );

            initUserAdresse(userList);
            initNeighbors(userList);

            userService.saveAll(userList);
            LOGGER.info("Ajout de {} Utilisateurs", userList.size());
        } else {
            LOGGER.info("Il existe {} utilisateurs en BDD", allUsers.size());
        }

        if (isBddInit) {

        }

    }

    private void initNeighbors(List<User> users){
        LOGGER.info("Création d'un jeu de données groupe de voisins (table 'neighborgroup')");
        NeighborGroup neighborGroup = new NeighborGroup("GroupTest", users);
        neighborGroupService.save(neighborGroup);
        users.forEach(u -> u.setNeighborGroup(neighborGroup));
        NeighborGroup neighborGroupEmpty = new NeighborGroup("GroupTestEmpty");
        neighborGroupService.save(neighborGroupEmpty);
    }

    private void initUserAdresse(List<User> users) {
        LOGGER.info("Création des Adresses (table 'adresse')");
        users.forEach(u -> {
            try {
                u.setAddress(new Address(String.valueOf(RandomTools.randomNum(100,200)), "rue Brancourt", "59000", "Lille"));
            } catch (NoSuchAlgorithmException e) {
                LOGGER.warn("Echec lors de la création des Adresses :\n{}"+ e.getMessage());
            }
        });
    }


}
