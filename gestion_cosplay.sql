-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 22 fév. 2024 à 00:29
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_cosplay`
--

-- --------------------------------------------------------

--
-- Structure de la table `cosplay`
--

CREATE TABLE `cosplay` (
  `idCp` int(11) NOT NULL,
  `nomCp` varchar(300) NOT NULL,
  `descriptionCp` varchar(300) NOT NULL,
  `personnage` varchar(255) NOT NULL,
  `imageCp` varchar(300) NOT NULL,
  `dateCreation` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `idmateriaux` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `cosplay`
--

INSERT INTO `cosplay` (`idCp`, `nomCp`, `descriptionCp`, `personnage`, `imageCp`, `dateCreation`, `idmateriaux`) VALUES
(1, 'naruto', 'de couleur orange ..', 'naruto', 'naruto.jpg', '2024-02-20 23:00:00', 3),
(4, 'one piece ', 'round', 'sasusaku', 'hat.jpg', '2024-02-21 23:00:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `materiaux`
--

CREATE TABLE `materiaux` (
  `idMa` int(11) NOT NULL,
  `nomMa` varchar(255) NOT NULL,
  `typeMa` varchar(255) NOT NULL,
  `disponibilite` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `materiaux`
--

INSERT INTO `materiaux` (`idMa`, `nomMa`, `typeMa`, `disponibilite`) VALUES
(1, 'tissu de coton', 'textile', 'dispo a sfax'),
(3, 'hikuji', 'coton', 'dispo');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `cosplay`
--
ALTER TABLE `cosplay`
  ADD PRIMARY KEY (`idCp`),
  ADD KEY `idmateriaux` (`idmateriaux`);

--
-- Index pour la table `materiaux`
--
ALTER TABLE `materiaux`
  ADD PRIMARY KEY (`idMa`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `cosplay`
--
ALTER TABLE `cosplay`
  MODIFY `idCp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `materiaux`
--
ALTER TABLE `materiaux`
  MODIFY `idMa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cosplay`
--
ALTER TABLE `cosplay`
  ADD CONSTRAINT `cosplay_ibfk_1` FOREIGN KEY (`idmateriaux`) REFERENCES `materiaux` (`idMa`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
