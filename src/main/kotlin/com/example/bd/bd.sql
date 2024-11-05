-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: clothes
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assortment`
--

DROP TABLE IF EXISTS `assortment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assortment` (
  `id_assortment` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_assortment`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assortment`
--

LOCK TABLES `assortment` WRITE;
/*!40000 ALTER TABLE `assortment` DISABLE KEYS */;
INSERT INTO `assortment` VALUES (1,'T-shirt','short'),(2,'T-shirt','long'),(3,'pants','long'),(4,'skirt','short'),(5,'skirt','wide'),(6,'shorts','wide'),(7,'blouse','long'),(8,'pants','wide'),(9,'dress','long'),(10,'shorts','long');
/*!40000 ALTER TABLE `assortment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id_client` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `patronymic` varchar(20) DEFAULT NULL,
  `orde` varchar(20) NOT NULL,
  `result` varchar(20) NOT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Sergey','Sergeev','Sergeevich','T-shirt','short'),(2,'Valera','Valeriev','Valerievich','T-shirt','long'),(3,'Timofey','Demidov','Dmitrievich','pants','narrowed'),(4,'Alice','Vasilyeva','Ivanovna','skirt','long'),(5,'Marcel','Belov','Romanovich','shirt','long'),(6,'Pearl','Newton',NULL,'shorts','wide'),(7,'Daniel','Harris',NULL,'blouse','long'),(8,'Francisco','Parker',NULL,'pants','wide'),(9,'Teresa','Turner',NULL,'dress','long'),(10,'Leonard','Castillo',NULL,'shorts','wide');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id_employee` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `patronymic` varchar(20) DEFAULT NULL,
  `phone_number` varchar(20) NOT NULL,
  PRIMARY KEY (`id_employee`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Elizaveta','Sergeeva','Ivanovna','79154435784'),(2,'Victor','Krasnov','Alexandrovich','79123451223'),(3,'Alice','Fedorova','Stepanovna','79157895784'),(4,'Arina','Nazarova',NULL,'79123457923'),(5,'Roman','Gusev','Arturovich','79123457954'),(6,'Artem','Pakhomov',NULL,'79167894356'),(7,'Lev','Yashin','Matveyevich','79167356754'),(8,'Anton','Platonov',NULL,'79168960905'),(9,'Stepan','Krylov','Ilyich','79160450605'),(10,'Yaroslav','Zaitsev','Kirillovich','79160303043');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `id_equipment` int NOT NULL AUTO_INCREMENT,
  `purchase_dt` date DEFAULT NULL,
  `service_life` date NOT NULL,
  `model_number` int DEFAULT NULL,
  PRIMARY KEY (`id_equipment`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'2019-01-01','2026-03-23',345),(2,NULL,'2027-05-12',333),(3,NULL,'2028-09-17',213);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orde`
--

DROP TABLE IF EXISTS `orde`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orde` (
  `id_orde` int NOT NULL AUTO_INCREMENT,
  `start_dt` date DEFAULT NULL,
  `end_dt` date DEFAULT NULL,
  `id_client` int NOT NULL,
  PRIMARY KEY (`id_orde`),
  KEY `id_client` (`id_client`),
  CONSTRAINT `orde_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orde`
--

LOCK TABLES `orde` WRITE;
/*!40000 ALTER TABLE `orde` DISABLE KEYS */;
INSERT INTO `orde` VALUES (1,'2019-03-01','2019-03-05',1),(2,'2019-06-22','2019-06-26',2),(3,'2020-12-12','2020-12-16',3),(4,'2021-03-01','2021-03-05',4),(5,'2021-06-22','2021-06-26',5),(6,'2021-12-12','2021-12-16',6),(7,'2022-01-07','2022-01-18',7),(8,'2024-01-09','2024-01-14',8),(9,'2024-02-15','2024-02-18',9),(10,'2024-03-01','2024-03-05',10);
/*!40000 ALTER TABLE `orde` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orde_work`
--

DROP TABLE IF EXISTS `orde_work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orde_work` (
  `count` int NOT NULL,
  `orde_status` varchar(20) NOT NULL,
  `id_orde` int NOT NULL,
  `id_work` int NOT NULL,
  `id_equipment` int NOT NULL,
  `id_employee` int NOT NULL,
  `id_assortment` int NOT NULL,
  `price` int DEFAULT NULL,
  `id_prese` int DEFAULT NULL,
  KEY `id_orde` (`id_orde`),
  KEY `id_work` (`id_work`),
  KEY `id_equipment` (`id_equipment`),
  KEY `id_employee` (`id_employee`),
  KEY `id_assortment` (`id_assortment`),
  KEY `id_prese` (`id_prese`),
  CONSTRAINT `orde_work_ibfk_1` FOREIGN KEY (`id_orde`) REFERENCES `orde` (`id_orde`),
  CONSTRAINT `orde_work_ibfk_2` FOREIGN KEY (`id_work`) REFERENCES `work` (`id_work`),
  CONSTRAINT `orde_work_ibfk_3` FOREIGN KEY (`id_equipment`) REFERENCES `equipment` (`id_equipment`),
  CONSTRAINT `orde_work_ibfk_4` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`),
  CONSTRAINT `orde_work_ibfk_5` FOREIGN KEY (`id_assortment`) REFERENCES `assortment` (`id_assortment`),
  CONSTRAINT `orde_work_ibfk_6` FOREIGN KEY (`id_prese`) REFERENCES `prese` (`id_prese`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orde_work`
--

LOCK TABLES `orde_work` WRITE;
/*!40000 ALTER TABLE `orde_work` DISABLE KEYS */;
INSERT INTO `orde_work` VALUES (180,'ready',1,1,1,3,1,1000,1),(90,'ready',2,2,1,1,2,1000,1),(70,'ready',3,3,2,5,3,1000,1),(50,'ready',4,4,3,6,4,1000,1),(100,'ready',5,5,1,2,4,1000,1),(50,'ready',6,6,3,8,6,1000,1),(80,'ready',7,7,2,1,7,1000,1),(20,'ready',8,8,2,9,8,1000,1),(30,'ready',9,9,3,10,9,1000,1),(55,'ready',10,10,1,3,6,1000,1),(1,'ready',1,3,1,1,1,NULL,NULL),(180,'ready',1,1,1,3,1,NULL,NULL);
/*!40000 ALTER TABLE `orde_work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orde_work_log`
--

DROP TABLE IF EXISTS `orde_work_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orde_work_log` (
  `count` int DEFAULT NULL,
  `orde_status` varchar(20) DEFAULT NULL,
  `id_orde` int DEFAULT NULL,
  `id_work` int DEFAULT NULL,
  `id_equipment` int DEFAULT NULL,
  `id_employee` int DEFAULT NULL,
  `id_assortment` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orde_work_log`
--

LOCK TABLES `orde_work_log` WRITE;
/*!40000 ALTER TABLE `orde_work_log` DISABLE KEYS */;
INSERT INTO `orde_work_log` VALUES (90,'ready',1,1,1,3,1),(90,'ready',2,2,1,1,2);
/*!40000 ALTER TABLE `orde_work_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prese`
--

DROP TABLE IF EXISTS `prese`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prese` (
  `id_prese` int NOT NULL AUTO_INCREMENT,
  `min` int NOT NULL,
  `max` int NOT NULL,
  `chena` int NOT NULL,
  PRIMARY KEY (`id_prese`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prese`
--

LOCK TABLES `prese` WRITE;
/*!40000 ALTER TABLE `prese` DISABLE KEYS */;
INSERT INTO `prese` VALUES (1,1,150,1000),(2,151,500,3000),(3,501,1000,5000);
/*!40000 ALTER TABLE `prese` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work`
--

DROP TABLE IF EXISTS `work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work` (
  `id_work` int NOT NULL AUTO_INCREMENT,
  `start_dt` date DEFAULT NULL,
  `end_dt` date DEFAULT NULL,
  PRIMARY KEY (`id_work`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work`
--

LOCK TABLES `work` WRITE;
/*!40000 ALTER TABLE `work` DISABLE KEYS */;
INSERT INTO `work` VALUES (1,'2019-03-02','2019-03-04'),(2,'2019-06-23','2019-06-25'),(3,'2020-12-12','2020-12-15'),(4,'2021-03-02','2021-03-04'),(5,'2021-06-23','2021-06-26'),(6,'2021-12-13','2021-12-15'),(7,'2022-01-08','2022-01-17'),(8,'2024-01-10','2024-01-14'),(9,'2024-02-15','2024-02-17'),(10,'2024-03-02','2024-03-04'),(11,'2024-05-04','2024-05-10');
/*!40000 ALTER TABLE `work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_log`
--

DROP TABLE IF EXISTS `work_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_log` (
  `id` int DEFAULT NULL,
  `start_dt_log` date DEFAULT NULL,
  `end_dt_log` date DEFAULT NULL,
  `log` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_log`
--

LOCK TABLES `work_log` WRITE;
/*!40000 ALTER TABLE `work_log` DISABLE KEYS */;
INSERT INTO `work_log` VALUES (11,'2024-05-04','2024-05-10','add');
/*!40000 ALTER TABLE `work_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-05 11:33:51
