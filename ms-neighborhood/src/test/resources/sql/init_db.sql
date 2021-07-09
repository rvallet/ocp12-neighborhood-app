INSERT INTO `neighborgroup` (`id_neighborgroup`, `name`) VALUES
(1,'GroupTest'),
(2,'GroupTestEmpty');

INSERT INTO `user` (`id_user`, `creation_date`,`email`,`first_name`,`last_name`,`password`,`reset_token`,`role`,`id_neighborgroup`) VALUES
(1,'2021-06-30 09:53:44','email@user1.fr','user1_firstName','user1_lastName','$2a$10$QDZgQz15ye9WTnv1tENUyeSkYE7B5ETPrVo0rpwOsr05rKv6naXKa',NULL,'user',1),
(2,'2021-06-30 09:53:44','email@user2.fr','user2_firstName','user2_lastName','$2a$10$DTUCZtIUXEx0Ep8ZgzPT7elpLH3UAeqVXiwyHEXMSQkRzRmoMCDIi',NULL,'user',1),
(3,'2021-06-30 09:53:44','email@user3.fr','user3_firstName','user3_lastName','$2a$10$QDqe96XLy28.bBRoyirvGee0D5L1ZdZX60S8REOK8symuTRk.rNMG',NULL,'user',1),
(4,'2021-06-30 09:53:45','email@user4.fr','user4_firstName','user4_lastName','$2a$10$2H0NT8Xt0AxaJJMMHTUlduv4yA5OYvu.nmnEmNroOoKdUcXI4lrlC',NULL,'user',1),
(5,'2021-06-30 09:53:45','email@member1.fr','member1_firstName','member1_lastName','$2a$10$DTfTzAemAEILbGPPENvWq.Kt4iE2o9fXFL7vAEUCuKcDawH5QuFmS',NULL,'member',1),
(6,'2021-06-30 09:53:45','email@member2.fr','member2_firstName','member2_lastName','$2a$10$LtZp0mfKgUZhe4XGXLh6c.xHl/OVSB2NDXPDStRfvwn76Xe52ylJi',NULL,'member',1),
(7,'2021-06-30 09:53:45','email@admin1.fr','admin1_firstName','admin1_lastName','$2a$10$xq5HgfW2jzGJ5PzTlqMLGOZRJLioAzCAcRywJ2dFxDBCowJafJ4h6',NULL,'admin',1),
(8,'2021-06-30 09:53:45','email@admin2.fr','admin2_firstName','admin2_lastName','$2a$10$ah5BGzBU/ZjWQXNt9i/J/uUsZEtSBouPCACI4FojBW.Rb1HR3h3hW',NULL,'admin',1);

INSERT INTO `request` (`id_request`, `author`, `closing_date`, `creation_date`, `description`, `helper`, `img_path_th_attribute`, `owner_id`, `request_status`, `request_type`, `id_user`) VALUES
(1, 'member1_firstName member1_lastName', '2021-06-30 10:43:38', '2021-06-30 10:03:31', 'J''aurais besoin d''un tournevis vis TorX 8 svp, retour rapide assuré :)', 'user1_firstName user1_lastName', '/img/upload/service_1625047411390_tournevis-torx-t8.jpg', 5, 'Terminé', 'Emprunt d''outils', 5),
(2, 'member1_firstName member1_lastName', NULL, '2021-06-30 10:05:23', 'Je vous propose d''acheter un lot d''autocollant pour les voitures mal garées sur notre parking qui est intéressé ?', NULL, '/img/upload/service_1625047521520_autocollants_auto.gif', 5, 'En cours', 'Achat groupé', 5),
(3, 'member1_firstName member1_lastName', '2021-06-30 10:45:03', '2021-06-30 10:06:58', 'J''ai trouvé des caméras pour un budget de 100€ pour la surveillance de nos jardins. ', 'user1_firstName user1_lastName', '/img/upload/service_1625047618006_pb30.jpg', 5, 'Terminé', 'Achat groupé', 5),
(4, 'member1_firstName member1_lastName', NULL, '2021-06-30 10:08:39', 'Je serais absent ce week-end, qui pourrais venir nourrir mon chat samedi soir ?', NULL, NULL, 5, 'En cours', 'Service divers', 5),
(5, 'user1_firstName user1_lastName', NULL, '2021-06-30 10:43:22', 'Je souhaiterais emprunter une échelle de 8 mètres pour ce week-end', NULL, NULL, 1, 'En cours', 'Emprunt d''outils', 1);

INSERT INTO `loan` (`id_loan`, `end_loan`, `loan_status`, `owner_full_name`, `owner_id`, `return_loan`, `start_loan`, `title`, `user_id`, `id_user`) VALUES
(1, '2021-06-30 10:43:38', 'En cours', 'user1_firstName user1_lastName', 1, NULL, '2021-06-30 10:43:38', 'J''aurais besoin d''un tournevis vis TorX 8 svp, retour rapide assuré :)', 5, 5);

INSERT INTO `groupbuying` (`id_groupbuying`, `creation_date`, `group_buying_status`, `img_path_th_attribute`, `neighbor_group_id`, `owner_full_name`, `owner_id`, `purchase_date`, `title`) VALUES
(1, '2021-06-30 10:45:03', 'Terminé', '/img/upload/service_1625047618006_pb30.jpg', 1, 'member1_firstName member1_lastName', 5, '2021-06-30 10:45:55', 'J''ai trouvé des caméras pour un budget de 100€ pour la surveillance de nos jardins. ');
