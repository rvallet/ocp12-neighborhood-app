# Aidez la communauté en tant que développeur d'application Java [OCP12]

Menez un projet de développement de la forme qui vous semblera la plus adaptée pour répondre à un besoin autour de vous.

## Application web de bon voisinage
Fichiers de configuration : voir dépôt [NeighborhoodConfig](https://github.com/rvallet/neighborhood-config)

### Objet :


### Contexte :


#### Travail demandé :


### Livrables attendus :
* Une note d’intention expliquant les objectifs du projet (quel que soit le sujet) : 1 page maximum, format PDF.
* Un bilan qui décrit et évalue rétrospectivement votre expérience.
* Le code source développé
* Selon le contexte, tous les éléments de création du projet auxquels vous avez contribué

## Installation & Déploiement

### Installation

- Cloner les dépôts https://github.com/rvallet/ocp12-neighborhood-app & https://github.com/rvallet/neighborhood-config
- Paramétrer une base de donnée MySQL en local dans les fichiers properties de neighborhood-config : ms-neighborhood.properties et website.properties
  (port local, username, password)

spring.datasource.url=jdbc:mysql://localhost:3306/neighborhood_bdd?useSSL=false&autoReconnect=true&verifyServerCertificate=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=admin
- Lancer un serveur local de connexion à la BDD MySQL (ex: WampServer)
- Importer le projet complet ocp12-neighborhood-app dans votre IDE Java (nécessite la présence d'un module Sprint Boot, présente par défaut dans IntelliJ ou Eclipse Sprint Tools Suite 4 "STS4")
- Démarrer le service cloud-config (port 8888)
- Démarrer le service eureka-server (port 9102)
- Lancer les microservices ms-neighborhood (ports 9090 & 9092).
  Plusieurs instances possibles, ex : ajouter 'VM options = -Dserver.port=XXXX')
- Au premier démarrage les microservice vont créer les table de la BDD 'neighborhood_bdd' ainsi qu'un jeu de données de tests sur la table 'utilisateur' (supprimer les tables pour régénérer le jeu de données)
- Lancer le website

####Notes


### Déploiement

- Générer les wars des modules avec "mvn package"
- Récupérer les war créés dans le repertoire 'target' les copier dans le dossier 'webapps' de votre Server Apache Tomcat
- Démarrer le serveur

## Réalisé avec

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE (JDK11)
* [Maven](https://maven.apache.org/) - Pour la gestion des dépendances du projet
* [SpringBoot v2.3.2](https://spring.io/projects/spring-boot) - Framework (+Spring DATA/JPA/HIBERNATE & Spring SECURITY)
* [Thymeleaf](https://www.thymeleaf.org/) - Moteur de templating Java
* [Bootstrap 4](https://getbootstrap.com/) - framework de design responsive (Librairies HTML, CSS et JS)
* [MySQL WorkBench](https://www.mysql.com/) - SGB MySQL, pour la conception du Modèle Physique de Donnée
* [DBeaver](https://dbeaver.io/) - SGBD universelle, pour l'écriture des scripts SQL et des tests MySQL
* [WampServer](http://www.wampserver.com/) - Gestion de serveurs Apache, PHP, MySQL (+PHP MyAdmin)

## Auteurs

* **Rémy VALLET** - *Initial work* - [rvallet](https://github.com/rvallet)

<!-- Voir également la liste des [contributeurs](https://github.com/rvallet/ocp12-neighborhood-app/graphs/contributors) qui ont participé au projet. -->

## License
This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/rvallet/ocp12-neighborhood-app/blob/main/LICENSE) file for details
