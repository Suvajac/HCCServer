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
-- Table structure for table `registracija`
--

DROP TABLE IF EXISTS `registracija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registracija` (
  `Username` varchar(50) NOT NULL,
  `DatumOd` date NOT NULL,
  `DatumDo` date NOT NULL,
  `BrSobe` int(11) NOT NULL,
  PRIMARY KEY (`Username`,`BrSobe`),
  KEY `R_7` (`BrSobe`),
  CONSTRAINT `registracija_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `gost` (`Username`),
  CONSTRAINT `registracija_ibfk_2` FOREIGN KEY (`BrSobe`) REFERENCES `soba` (`BrSobe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registracija`
--

LOCK TABLES `registracija` WRITE;
/*!40000 ALTER TABLE `registracija` DISABLE KEYS */;
INSERT INTO `registracija` VALUES ('bojansuvajac','2008-09-20','2024-09-20',209),('danielcrnovcic','2011-09-20','2030-09-20',206),('draganbunic','2006-09-20','2020-09-20',101),('ljubisamilincic','2005-09-20','2021-09-20',105),('svetozarvukovic','2010-09-20','2026-09-20',202);
/*!40000 ALTER TABLE `registracija` ENABLE KEYS */;
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
