-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 13 mai 2022 à 13:03
-- Version du serveur :  5.7.31
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_compte`
--

-- --------------------------------------------------------

--
-- Structure de la table `commercant`
--

DROP TABLE IF EXISTS `commercant`;
CREATE TABLE IF NOT EXISTS `commercant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addresse` varchar(255) COLLATE utf8_bin NOT NULL,
  `created_at` date NOT NULL,
  `departement` varchar(255) COLLATE utf8_bin NOT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `ville` varchar(255) COLLATE utf8_bin NOT NULL,
  `category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp76ij232kjwq0gpmodvtbevnv` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `commercant`
--

INSERT INTO `commercant` (`id`, `addresse`, `created_at`, `departement`, `description`, `name`, `ville`, `category_id`) VALUES
(1, 'Route de Joué', '2022-05-13', '37', 'C\'est le fulunushu', 'Flunch Chambray-lès-Tours', 'Chambray-lès-Tours', 2),
(2, '85 Avenue du Grand Sud', '2022-05-13', '37', 'Boulangerie Sandwicherie Tarterie situé à Chambray-lès-Tours', 'Marie Blachère Chambray-lès-Tours', 'Chambray-lès-Tours', 3),
(3, 'Route de Joué', '2022-05-13', '37', 'Magasin Auchan situé dans le centre commercial de Chambray-lès-Tours', 'Auchan Chambray-lès-Tours', 'Chambray-lès-Tours', 1),
(4, '59 Avenue Marcel Mérieux', '2022-05-13', '37', 'Diner américain situé dans la galerie commerçante de l\'Heure Tranquille', 'Holly\'s Diner L\'Heure Tranquille', 'Tours', 4);

-- --------------------------------------------------------

--
-- Structure de la table `commercant_category`
--

DROP TABLE IF EXISTS `commercant_category`;
CREATE TABLE IF NOT EXISTS `commercant_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `commercant_category`
--

INSERT INTO `commercant_category` (`id`, `name`) VALUES
(1, 'Grande surface'),
(2, 'Restauration rapide'),
(3, 'Boulangerie'),
(4, 'Restaurant');

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` date NOT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `valid_until` date NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `commercant_id` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `prix` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfxts201uq5tc7cglv9wub0jei` (`order_id`),
  KEY `FKpq8d0ok5icxyc40wcd7xrs6td` (`category_id`),
  KEY `FKs6fm7c40ohpxdobmgmlsc33ly` (`commercant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`id`, `created_at`, `description`, `valid_until`, `category_id`, `commercant_id`, `order_id`, `prix`) VALUES
(28, '2022-05-13', '1 côte de bœuf\r\n3 steaks hachés\r\n2 rôtis', '2022-07-20', 2, 3, NULL, '12.50'),
(22, '2022-05-13', '1 entrée au choix (salade composée, chèvre chaud)\r\n1 Burger au choix + frites\r\n3 viennoiseries ou churros', '2022-06-20', 5, 4, NULL, '8.00'),
(23, '2022-05-13', '1 entrée au choix (salade composée, chèvre chaud)\r\n1 Burger au choix + frites\r\n3 viennoiseries ou churros', '2022-05-01', 5, 4, NULL, '8.00'),
(24, '2022-05-13', '1 entrée au choix (salade composée, chèvre chaud)\r\n1 Burger au choix + frites\r\n3 viennoiseries ou churros', '2022-06-20', 5, 4, NULL, '8.00'),
(29, '2022-05-13', '2 côte de bœuf\r\n2 steaks hachés', '2022-07-20', 2, 3, NULL, '9.50'),
(30, '2022-05-13', '250g de crevettes\r\n1 poisson au choix', '2022-06-25', 4, 3, NULL, '6.50'),
(31, '2022-05-13', '1 poisson pané\r\nLégumes à volonté', '2022-05-10', 1, 1, NULL, '5.50'),
(32, '2022-05-13', '1 Rosti burger\r\nLégumes à volonté', '2022-06-28', 1, 1, NULL, '7.50'),
(33, '2022-05-13', '2 baguettes traditions\r\n1 sachet de 5 viennoiserie au choix', '2022-07-21', 6, 2, NULL, '4.00'),
(34, '2022-05-13', 'Pané à l\'épeautre\r\nLégumes à volonté', '2022-06-19', 3, 1, NULL, '4.00'),
(35, '2022-05-13', '1 pané à l\'épeautre\r\nLégumes à volonté', '2022-05-12', 3, 1, NULL, '6.50');

-- --------------------------------------------------------

--
-- Structure de la table `panier_category`
--

DROP TABLE IF EXISTS `panier_category`;
CREATE TABLE IF NOT EXISTS `panier_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `panier_category`
--

INSERT INTO `panier_category` (`id`, `name`) VALUES
(1, 'Plat préparé'),
(2, 'Viande'),
(3, 'Légume'),
(4, 'Poisson'),
(5, 'Mixte'),
(6, 'Pains & boulangerie');

-- --------------------------------------------------------

--
-- Structure de la table `panier_order`
--

DROP TABLE IF EXISTS `panier_order`;
CREATE TABLE IF NOT EXISTS `panier_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` date NOT NULL,
  `status` varchar(255) COLLATE utf8_bin NOT NULL,
  `panier_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5u7iim1femb599nl1l8mykfsr` (`panier_id`),
  KEY `FK9gfjacrcvjc36gh52vjng3pfu` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `panier_order`
--

INSERT INTO `panier_order` (`id`, `created_at`, `status`, `panier_id`, `user_id`) VALUES
(20, '2022-05-13', 'DELIVERED', 22, 2),
(21, '2022-05-13', 'EXPIRED', 35, 2),
(22, '2022-05-13', 'DELIVERED', 23, 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'USER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `firstname` varchar(255) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `created_at` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `firstname`, `lastname`, `password`, `role_id`, `created_at`) VALUES
(2, 'd.f@gmail.com', 'Didier', 'Franck', '$2a$10$w9G5wrSWXC/P0wRh5qU.aucZJvTdBjlSk.FhOfvJcnTQltmumIFS2', 1, '2022-05-12'),
(7, 'admin@example.com', 'Admin', 'Admin', '$2a$10$zncTwBG.FbnhP8Kzb8S4FurcQyw8EeC15GcWnymQoSNp7aJBSvBna', 2, '2022-05-13');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
