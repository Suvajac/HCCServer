CREATE DATABASE  IF NOT EXISTS `hotelcc` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hotelcc`;
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
-- Table structure for table `proizvodisoba`
--

DROP TABLE IF EXISTS `proizvodisoba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proizvodisoba` (
  `IdSobneUsluge` int(11) NOT NULL,
  `IdProizvoda` int(11) NOT NULL,
  PRIMARY KEY (`IdSobneUsluge`,`IdProizvoda`),
  KEY `fk_proizvodisoba_1_idx` (`IdProizvoda`),
  CONSTRAINT `fk_proizvodisoba_1` FOREIGN KEY (`IdProizvoda`) REFERENCES `proizvod` (`IdProizvoda`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_proizvodisoba_2` FOREIGN KEY (`IdSobneUsluge`) REFERENCES `sobnausluga` (`IdUsluge`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proizvodisoba`
--

LOCK TABLES `proizvodisoba` WRITE;
/*!40000 ALTER TABLE `proizvodisoba` DISABLE KEYS */;
/*!40000 ALTER TABLE `proizvodisoba` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-12 17:09:44
