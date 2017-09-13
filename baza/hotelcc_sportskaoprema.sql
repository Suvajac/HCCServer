-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: hotelcc
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sportskaoprema`
--

DROP TABLE IF EXISTS `sportskaoprema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sportskaoprema` (
  `IdSportskeOpreme` int(11) NOT NULL,
  `Naziv` varchar(20) NOT NULL,
  `Cijena` decimal(5,2) NOT NULL,
  `Velicina` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IdSportskeOpreme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sportskaoprema`
--

LOCK TABLES `sportskaoprema` WRITE;
/*!40000 ALTER TABLE `sportskaoprema` DISABLE KEYS */;
INSERT INTO `sportskaoprema` VALUES (1,'Patike DIADORA muske',15.00,'44'),(2,'Patike DIADORA muske',15.00,'45'),(3,'Patike DIADORA muske',15.00,'46'),(4,'Patike DIADORA muske',15.00,'43'),(5,'Patike DIADORA muske',15.00,'42'),(6,'Head reket',25.00,'44'),(7,'majica bijela Nike',5.00,'L'),(8,'majica bijela Nike',5.00,'XL'),(9,'majica plava Nike',5.00,'XXL'),(10,'majica bijela Nike',5.00,'M'),(11,'majica crna Nike',5.00,'S');
/*!40000 ALTER TABLE `sportskaoprema` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-13 10:55:06
