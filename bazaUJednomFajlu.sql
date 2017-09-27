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
-- Table structure for table `gost`
--

DROP TABLE IF EXISTS `gost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gost` (
  `Username` varchar(50) NOT NULL,
  `IdRacuna` int(11) NOT NULL,
  PRIMARY KEY (`Username`),
  KEY `R_13` (`IdRacuna`),
  CONSTRAINT `gost_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `korisnik` (`Username`) ON DELETE CASCADE,
  CONSTRAINT `gost_ibfk_2` FOREIGN KEY (`IdRacuna`) REFERENCES `racun` (`IdRacuna`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gost`
--

LOCK TABLES `gost` WRITE;
/*!40000 ALTER TABLE `gost` DISABLE KEYS */;
INSERT INTO `gost` VALUES ('draganbunic',1),('bojansuvajac',2),('ljubisamilincic',3),('danielcrnovcic',4),('svetozarvukovic',5);
/*!40000 ALTER TABLE `gost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnik` (
  `Username` varchar(50) NOT NULL,
  `Ime` varchar(20) DEFAULT NULL,
  `Prezime` varchar(20) DEFAULT NULL,
  `BrojTelefona` varchar(30) DEFAULT NULL,
  `LozinkaHash` varchar(512) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES ('aleksandarlekic','Aleksandar','Lekic','065/333-444','913949030'),('bojansuvajac','Bojan','Suvajac','065/111-222','-1861353340'),('danielcrnovcic','Daniel','Crnovcic','065/999-000','1692808598'),('draganbunic','Dragan','Bunic','065/555-666','-1205948140'),('ljubisamilincic','Ljubisa','Milincic','065/777-888','177715508'),('svetozarvukovic','Svetozar','Vukovic','066/123-456','-1439170465');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obavjestenje`
--

DROP TABLE IF EXISTS `obavjestenje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obavjestenje` (
  `IdObavjestenja` int(11) NOT NULL AUTO_INCREMENT,
  `Tekst` varchar(1500) DEFAULT NULL,
  `Datum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Procitano` tinyint(1) NOT NULL,
  PRIMARY KEY (`IdObavjestenja`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obavjestenje`
--

LOCK TABLES `obavjestenje` WRITE;
/*!40000 ALTER TABLE `obavjestenje` DISABLE KEYS */;
INSERT INTO `obavjestenje` VALUES (3,'Korisnik ljubisamilincic je narucio Usluga restorana.','2017-09-14 22:32:02',1),(4,'Korisnik ljubisamilincic je narucio Usluga restorana.','2017-09-14 22:34:11',0),(5,'Korisnik bojansuvajac je narucio Usluga restorana.','2017-09-15 08:10:23',1);
/*!40000 ALTER TABLE `obavjestenje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oglas`
--

DROP TABLE IF EXISTS `oglas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oglas` (
  `IdOglasa` int(11) NOT NULL AUTO_INCREMENT,
  `Datum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Poruka` varchar(256) NOT NULL,
  PRIMARY KEY (`IdOglasa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oglas`
--

LOCK TABLES `oglas` WRITE;
/*!40000 ALTER TABLE `oglas` DISABLE KEYS */;
INSERT INTO `oglas` VALUES (1,'2004-09-20 00:00:00','Upozoravaju se gosti da pripaze prilikom napustanja objekta iz razloga sto se vrse radovi na obliznjem objektu.'),(2,'2013-01-20 00:00:00','Popravka internet odasiljaca u trajanju od 2h.');
/*!40000 ALTER TABLE `oglas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opremasportusluga`
--

DROP TABLE IF EXISTS `opremasportusluga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opremasportusluga` (
  `IdSportskeOpreme` int(11) NOT NULL,
  `IdUsluge` int(11) NOT NULL,
  `Kolicina` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`IdSportskeOpreme`,`IdUsluge`),
  KEY `fk_opremasportusluga_1_idx` (`IdUsluge`),
  CONSTRAINT `fk_opremasportusluga_1` FOREIGN KEY (`IdUsluge`) REFERENCES `sportusluga` (`IdUsluge`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_opremasportusluga_2` FOREIGN KEY (`IdSportskeOpreme`) REFERENCES `sportskaoprema` (`IdSportskeOpreme`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opremasportusluga`
--

LOCK TABLES `opremasportusluga` WRITE;
/*!40000 ALTER TABLE `opremasportusluga` DISABLE KEYS */;
/*!40000 ALTER TABLE `opremasportusluga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `popust`
--

DROP TABLE IF EXISTS `popust`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `popust` (
  `KodPopusta` int(11) NOT NULL,
  `Procenat` decimal(5,2) NOT NULL,
  `Aktivan` tinyint(1) NOT NULL,
  PRIMARY KEY (`KodPopusta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `popust`
--

LOCK TABLES `popust` WRITE;
/*!40000 ALTER TABLE `popust` DISABLE KEYS */;
INSERT INTO `popust` VALUES (1,20.00,1),(2,11.00,1),(3,10.00,0),(4,15.00,1),(5,35.00,1);
/*!40000 ALTER TABLE `popust` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proizvod`
--

DROP TABLE IF EXISTS `proizvod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proizvod` (
  `IdProizvoda` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(150) NOT NULL,
  `Cijena` double DEFAULT NULL,
  `Tip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IdProizvoda`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proizvod`
--

LOCK TABLES `proizvod` WRITE;
/*!40000 ALTER TABLE `proizvod` DISABLE KEYS */;
INSERT INTO `proizvod` VALUES (1,'Coca Cola',2.3,'Pice'),(2,'Fanta',2.3,'Pice'),(3,'Hamburger',4.5,'Hrana'),(4,'Pizza',6.99,'Hrana'),(5,'Dalmatinski pršut',34.99,'Hrana'),(6,'Salata plodovi mora',36.49,'Hrana'),(7,'Goveđa juha',24.49,'Hrana'),(8,'Juha od povrća',13.99,'Hrana'),(9,'Crni rižoto',21.49,'Hrana'),(10,'Piletina sa pomfritom',23.49,'Hrana'),(11,'Biftek na žaru',44.49,'Hrana'),(12,'Biftek sa pomfritom',54.49,'Hrana'),(14,'Supa',4.99,'Hrana'),(15,'Becka snicla',19.99,'Hrana');
/*!40000 ALTER TABLE `proizvod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proizvodirestoran`
--

DROP TABLE IF EXISTS `proizvodirestoran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proizvodirestoran` (
  `IdUslugaRestorana` int(11) NOT NULL,
  `IdProizvoda` int(11) NOT NULL,
  `Kolicina` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`IdUslugaRestorana`,`IdProizvoda`),
  KEY `fk_proizvodirestoran_1_idx` (`IdProizvoda`),
  CONSTRAINT `fk_proizvodirestoran_1` FOREIGN KEY (`IdProizvoda`) REFERENCES `proizvod` (`IdProizvoda`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_proizvodirestoran_2` FOREIGN KEY (`IdUslugaRestorana`) REFERENCES `uslugarestorana` (`IdUsluge`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proizvodirestoran`
--

LOCK TABLES `proizvodirestoran` WRITE;
/*!40000 ALTER TABLE `proizvodirestoran` DISABLE KEYS */;
/*!40000 ALTER TABLE `proizvodirestoran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proizvodisoba`
--

DROP TABLE IF EXISTS `proizvodisoba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proizvodisoba` (
  `IdSobneUsluge` int(11) NOT NULL,
  `IdProizvoda` int(11) NOT NULL,
  `Kolicina` int(11) NOT NULL DEFAULT '1',
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

--
-- Table structure for table `racun`
--

DROP TABLE IF EXISTS `racun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `racun` (
  `IdRacuna` int(11) NOT NULL AUTO_INCREMENT,
  `Placen` tinyint(1) NOT NULL,
  `IdPopusta` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdRacuna`),
  KEY `racun_ibfk_1_idx` (`IdPopusta`),
  CONSTRAINT `racun_ibfk_1` FOREIGN KEY (`IdPopusta`) REFERENCES `popust` (`KodPopusta`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `racun`
--

LOCK TABLES `racun` WRITE;
/*!40000 ALTER TABLE `racun` DISABLE KEYS */;
INSERT INTO `racun` VALUES (1,0,1),(2,0,3),(3,0,1),(4,0,4),(5,1,NULL);
/*!40000 ALTER TABLE `racun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recepcionar`
--

DROP TABLE IF EXISTS `recepcionar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recepcionar` (
  `Username` varchar(50) NOT NULL,
  PRIMARY KEY (`Username`),
  CONSTRAINT `recepcionar_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `korisnik` (`Username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recepcionar`
--

LOCK TABLES `recepcionar` WRITE;
/*!40000 ALTER TABLE `recepcionar` DISABLE KEYS */;
INSERT INTO `recepcionar` VALUES ('aleksandarlekic');
/*!40000 ALTER TABLE `recepcionar` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`Username`,`BrSobe`,`DatumOd`,`DatumDo`),
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
INSERT INTO `registracija` VALUES ('draganbunic','2006-09-20','2020-09-20',101),('ljubisamilincic','2005-09-20','2021-09-20',105),('svetozarvukovic','2010-09-20','2006-09-20',202),('danielcrnovcic','2011-09-20','2030-09-20',206),('bojansuvajac','2008-09-20','2024-09-20',209);
/*!40000 ALTER TABLE `registracija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soba`
--

DROP TABLE IF EXISTS `soba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soba` (
  `BrSobe` int(11) NOT NULL,
  `BrKreveta` int(11) NOT NULL,
  `BrSprata` int(11) NOT NULL,
  `CijenaPoDanu` decimal(6,2) NOT NULL,
  PRIMARY KEY (`BrSobe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soba`
--

LOCK TABLES `soba` WRITE;
/*!40000 ALTER TABLE `soba` DISABLE KEYS */;
INSERT INTO `soba` VALUES (101,3,1,45.00),(102,3,1,45.00),(103,2,1,40.00),(104,2,1,40.00),(105,2,1,50.00),(106,1,1,30.00),(107,1,1,30.00),(108,1,1,40.00),(109,3,1,75.00),(201,3,2,45.00),(202,3,2,45.00),(203,2,2,40.00),(204,2,2,40.00),(205,2,2,50.00),(206,1,2,30.00),(207,1,2,30.00),(208,1,2,40.00),(209,3,2,75.00);
/*!40000 ALTER TABLE `soba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sobnausluga`
--

DROP TABLE IF EXISTS `sobnausluga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sobnausluga` (
  `IdUsluge` int(11) NOT NULL AUTO_INCREMENT,
  `Tip` varchar(20) NOT NULL,
  PRIMARY KEY (`IdUsluge`),
  CONSTRAINT `sobnausluga_ibfk_1` FOREIGN KEY (`IdUsluge`) REFERENCES `usluga` (`IdUsluge`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sobnausluga`
--

LOCK TABLES `sobnausluga` WRITE;
/*!40000 ALTER TABLE `sobnausluga` DISABLE KEYS */;
/*!40000 ALTER TABLE `sobnausluga` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `sporttermin`
--

DROP TABLE IF EXISTS `sporttermin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sporttermin` (
  `IdTermina` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IdTermina`),
  CONSTRAINT `sporttermin_ibfk_1` FOREIGN KEY (`IdTermina`) REFERENCES `termin` (`IdTermina`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sporttermin`
--

LOCK TABLES `sporttermin` WRITE;
/*!40000 ALTER TABLE `sporttermin` DISABLE KEYS */;
/*!40000 ALTER TABLE `sporttermin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sportusluga`
--

DROP TABLE IF EXISTS `sportusluga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sportusluga` (
  `IdUsluge` int(11) NOT NULL AUTO_INCREMENT,
  `IdTermina` int(11) NOT NULL,
  PRIMARY KEY (`IdUsluge`),
  KEY `R_57` (`IdTermina`),
  CONSTRAINT `sportusluga_ibfk_1` FOREIGN KEY (`IdUsluge`) REFERENCES `usluga` (`IdUsluge`) ON DELETE CASCADE,
  CONSTRAINT `sportusluga_ibfk_2` FOREIGN KEY (`IdTermina`) REFERENCES `sporttermin` (`IdTermina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sportusluga`
--

LOCK TABLES `sportusluga` WRITE;
/*!40000 ALTER TABLE `sportusluga` DISABLE KEYS */;
/*!40000 ALTER TABLE `sportusluga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stavka`
--

DROP TABLE IF EXISTS `stavka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stavka` (
  `IdStavke` int(11) NOT NULL AUTO_INCREMENT,
  `Datum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IdRacuna` int(11) NOT NULL,
  `IdUsluge` int(11) NOT NULL,
  PRIMARY KEY (`IdStavke`,`IdRacuna`),
  KEY `R_17` (`IdRacuna`),
  KEY `R_42` (`IdUsluge`),
  CONSTRAINT `stavka_ibfk_1` FOREIGN KEY (`IdRacuna`) REFERENCES `racun` (`IdRacuna`),
  CONSTRAINT `stavka_ibfk_2` FOREIGN KEY (`IdUsluge`) REFERENCES `usluga` (`IdUsluge`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stavka`
--

LOCK TABLES `stavka` WRITE;
/*!40000 ALTER TABLE `stavka` DISABLE KEYS */;
/*!40000 ALTER TABLE `stavka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `termin`
--

DROP TABLE IF EXISTS `termin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `termin` (
  `Datum` date NOT NULL,
  `IdTermina` int(11) NOT NULL AUTO_INCREMENT,
  `Vrijeme` time NOT NULL,
  PRIMARY KEY (`IdTermina`),
  KEY `R_71` (`IdTermina`),
  KEY `termin_ibfk_1` (`Vrijeme`),
  CONSTRAINT `termin_ibfk_1` FOREIGN KEY (`Vrijeme`) REFERENCES `vrijemetermina` (`Vrijeme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `termin`
--

LOCK TABLES `termin` WRITE;
/*!40000 ALTER TABLE `termin` DISABLE KEYS */;
/*!40000 ALTER TABLE `termin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usluga`
--

DROP TABLE IF EXISTS `usluga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usluga` (
  `IdUsluge` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(20) NOT NULL,
  `Cijena` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`IdUsluge`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usluga`
--

LOCK TABLES `usluga` WRITE;
/*!40000 ALTER TABLE `usluga` DISABLE KEYS */;
/*!40000 ALTER TABLE `usluga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uslugarestorana`
--

DROP TABLE IF EXISTS `uslugarestorana`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uslugarestorana` (
  `BrojStolica` int(11) NOT NULL,
  `IdUsluge` int(11) NOT NULL AUTO_INCREMENT,
  `Vrijeme` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IdUsluge`),
  CONSTRAINT `uslugarestorana_ibfk_2` FOREIGN KEY (`IdUsluge`) REFERENCES `usluga` (`IdUsluge`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uslugarestorana`
--

LOCK TABLES `uslugarestorana` WRITE;
/*!40000 ALTER TABLE `uslugarestorana` DISABLE KEYS */;
/*!40000 ALTER TABLE `uslugarestorana` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utisak`
--

DROP TABLE IF EXISTS `utisak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utisak` (
  `IdUtiska` int(11) NOT NULL AUTO_INCREMENT,
  `Tekst` varchar(1024) NOT NULL,
  `Datum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Username` varchar(50) NOT NULL,
  PRIMARY KEY (`IdUtiska`,`Username`),
  KEY `R_4` (`Username`),
  CONSTRAINT `utisak_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `korisnik` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utisak`
--

LOCK TABLES `utisak` WRITE;
/*!40000 ALTER TABLE `utisak` DISABLE KEYS */;
INSERT INTO `utisak` VALUES (2,'Prvi utisak','2017-09-12 13:26:30','bojansuvajac');
/*!40000 ALTER TABLE `utisak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vrijemetermina`
--

DROP TABLE IF EXISTS `vrijemetermina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vrijemetermina` (
  `Vrijeme` time NOT NULL,
  PRIMARY KEY (`Vrijeme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vrijemetermina`
--

LOCK TABLES `vrijemetermina` WRITE;
/*!40000 ALTER TABLE `vrijemetermina` DISABLE KEYS */;
INSERT INTO `vrijemetermina` VALUES ('09:00:00'),('10:00:00'),('11:00:00'),('12:00:00'),('13:00:00'),('14:00:00'),('15:00:00'),('16:00:00'),('17:00:00'),('18:00:00'),('19:00:00'),('20:00:00'),('21:00:00'),('22:00:00'),('23:00:00');
/*!40000 ALTER TABLE `vrijemetermina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wellnesstermin`
--

DROP TABLE IF EXISTS `wellnesstermin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wellnesstermin` (
  `IdTermina` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IdTermina`),
  CONSTRAINT `wellnesstermin_ibfk_1` FOREIGN KEY (`IdTermina`) REFERENCES `termin` (`IdTermina`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wellnesstermin`
--

LOCK TABLES `wellnesstermin` WRITE;
/*!40000 ALTER TABLE `wellnesstermin` DISABLE KEYS */;
/*!40000 ALTER TABLE `wellnesstermin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wellnessusluga`
--

DROP TABLE IF EXISTS `wellnessusluga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wellnessusluga` (
  `IdUsluge` int(11) NOT NULL AUTO_INCREMENT,
  `IdTermina` int(11) NOT NULL,
  PRIMARY KEY (`IdUsluge`),
  KEY `R_56` (`IdTermina`),
  CONSTRAINT `wellnessusluga_ibfk_1` FOREIGN KEY (`IdUsluge`) REFERENCES `usluga` (`IdUsluge`) ON DELETE CASCADE,
  CONSTRAINT `wellnessusluga_ibfk_2` FOREIGN KEY (`IdTermina`) REFERENCES `wellnesstermin` (`IdTermina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wellnessusluga`
--

LOCK TABLES `wellnessusluga` WRITE;
/*!40000 ALTER TABLE `wellnessusluga` DISABLE KEYS */;
/*!40000 ALTER TABLE `wellnessusluga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'hotelcc'
--

--
-- Dumping routines for database 'hotelcc'
--
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_gost` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_gost`(
	in varUser varchar(50),
	in varIme varchar(20),
    in varPrezime varchar(20),
    in varBrTel varchar(30),
    in varLozinka varchar(512),
    in varIdRac int(11))
begin
	insert into korisnik(Username,Ime,Prezime,
		BrojTelefona,LozinkaHash) values(varUser,varIme,varPrezime,varBrTel,varLozinka);
	insert into gost(Username,IdRacuna) values(varUser,varIdRac);
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_obavjestenje` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_obavjestenje`(
	in varid int(11),
    in varTekst varchar(500),
	in varDatum timestamp,
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into obavjestenje(IdObavjestenja,Tekst,Datum,Procitano) values(varid,varTekst,varDatum,0);
		set rez=varid;
	else
		insert into obavjestenje(Tekst,Datum,Procitano) values(varTekst,varDatum,0);
		select max(IdObavjestenja) into a from obavjestenje;
		set rez=a;
    end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_oglas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_oglas`(
	in varid int(11),
    in varTime timestamp,
	in varPoruka varchar(256),
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into oglas(IdOglasa,Datum,Poruka) values(varid,varTime,varPoruka);
		set rez=varid;
	else
		insert into oglas(Datum,Poruka) values(varTime,varPoruka);
		select max(IdOglasa) into a from oglas;
		set rez=a;
    end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_opremasportusluga` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_opremasportusluga`(
	in varPro int(11),
    in varUsl int(11)
	)
begin
	if (exists (SELECT * FROM opremasportusluga where IdSportskeOpreme=varPro and IdUsluge=varUsl)) 
		then
			update opremasportusluga set Kolicina=Kolicina+1 where IdSportskeOpreme=varPro and IdUsluge=varUsl;
		else	
			insert into opremasportusluga(IdSportskeOpreme,IdUsluge) values (varPro,varUsl);
        end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_proizvod` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_proizvod`(
	in varid int(11),
    in varNaziv varchar(150),
	in varCijena double,
    in varTip varchar(20),
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into proizvod(IdProizvoda,Naziv,Cijena,Tip) values(varid,varNaziv,varCijena,varTip);
		set rez=varid;
	else
		insert into proizvod(Naziv,Cijena,Tip) values(varNaziv,varCijena,varTip);
		select max(IdProizvoda) into a from proizvod;
		set rez=a;
    end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_proizvodirestoran` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_proizvodirestoran`(
	in varPro int(11),
    in varUsl int(11)
	)
begin
	if (exists (SELECT * FROM proizvodirestoran where IdProizvoda=varPro and IdUslugaRestorana=varUsl)) 
		then
			update proizvodirestoran set Kolicina=Kolicina+1 where IdProizvoda=varPro and IdUslugaRestorana=varUsl;
		else	
			insert into proizvodirestoran(IdProizvoda,IdUslugaRestorana) values (varPro,varUsl);
        end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_proizvodisoba` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_proizvodisoba`(
	in varPro int(11),
    in varUsl int(11)
	)
begin
	if (exists (SELECT * FROM proizvodisoba where IdProizvoda=varPro and IdSobneUsluge=varUsl)) 
		then
			update proizvodisoba set Kolicina=Kolicina+1 where IdProizvoda=varPro and IdSobneUsluge=varUsl;
		else	
			insert into proizvodisoba(IdProizvoda,IdSobneUsluge) values (varPro,varUsl);
        end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_racun` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_racun`(
	out rez int(11) )
begin
    insert into racun(Placen) values (0);
	select max(IdRacuna) into rez from racun;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_recepcionar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_recepcionar`(
	in varUser varchar(50),
	in varIme varchar(20),
    in varPrezime varchar(20),
    in varBrTel varchar(30),
    in varLozinka varchar(512)
	)
begin
	
	insert into korisnik(Username,Ime,Prezime,
		BrojTelefona,LozinkaHash) values(varUser,varIme,varPrezime,varBrTel,varLozinka);
	insert into recepcionar(Username) values(varUser);
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_sobnausluga` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_sobnausluga`(
	in varid int(11),
	in varNaziv varchar(20),
    in varCijena decimal(8,2),
    in varTip varchar(20),
	out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into usluga(IdUsluge,Naziv,Cijena) values(varid,varNaziv,varCijena);
		insert into sobnausluga(IdUsluge,Tip) values(varid,varTip);
        set rez=varid;
	else
		insert into usluga(Naziv,Cijena) values(varNaziv,varCijena);
		select max(IdUsluge) into a from usluga;
		insert into sobnausluga(IdUsluge,Tip) values(a,varTip);
        set rez=a;
    end if;
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_sporttermin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_sporttermin`(
	in varid int(11),
	in varDate date,
    in varVrijeme time,
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into termin(IdTermina,Datum,Vrijeme) values(varid,varDate,varVrijeme);
		insert into sporttermin(IdTermina) values(varid);
        set rez=varid;
	else
		insert into termin(Datum,Vrijeme) values(varDate,varVrijeme);
        select max(IdTermina) into a from termin;
		insert into sporttermin(IdTermina) values(a);
        set rez=a;
    end if;
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_sportusluga` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_sportusluga`(
	in varid int(11),
	in varNaziv varchar(20),
    in varCijena decimal(8,2),
    in varIdTermina int(11),
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into usluga(IdUsluge,Naziv,Cijena) values(varid,varNaziv,varCijena);
		insert into sportusluga(IdUsluge,IdTermina) values(varid,varIdTermina);
        set rez=varid;
	else
		insert into usluga(Naziv,Cijena) values(varNaziv,varCijena);
		select max(IdUsluge) into a from usluga;
		insert into sportusluga(IdUsluge,IdTermina) values(a,varIdTermina);
        set rez=a;
    end if;
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_usluga` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_usluga`(
	in varNaziv varchar(20),
    in varCijena decimal(6,2),
	out rez int(11) )
begin
    insert into usluga(Naziv,Cijena) values (varNaziv,varCijena);
	select max(IdUsluge) into rez from usluga;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_uslugarestorana` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_uslugarestorana`(
	in varid int(11),
	in varNaziv varchar(20),
    in varCijena decimal(8,2),
    in varIdStola int(11),
    in varVrijeme varchar(20),
	out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into usluga(IdUsluge,Naziv,Cijena) values(varid,varNaziv,varCijena);
		insert into uslugarestorana(IdUsluge,BrojStolica,Vrijeme) values(varid,varIdStola,varVrijeme);
        set rez=varid;
	else
		insert into usluga(Naziv,Cijena) values(varNaziv,varCijena);
		select max(IdUsluge) into a from usluga;
		insert into uslugarestorana(IdUsluge,BrojStolica,Vrijeme) values(a,varIdStola,varVrijeme);
        set rez=a;
    end if;
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_wellnesstermin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_wellnesstermin`(
	in varid int(11),
	in varDate date,
    in varVrijeme time,
    out rez int(11)
	)
begin
	declare a int(11) default 0;
    
	if(varid >0) then
		insert into termin(IdTermina,Datum,Vrijeme) values(varid,varDate,varVrijeme);
		insert into wellnesstermin(IdTermina) values(varid);
		set rez=varid;
    else
		insert into termin(Datum,Vrijeme) values(varDate,varVrijeme);
        select max(IdTermina) into a from termin;
		insert into wellnesstermin(IdTermina) values(a);
        set rez=a;
    end if;
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_into_wellnessusluga` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_into_wellnessusluga`(
	in varid int(11),
	in varNaziv varchar(20),
    in varCijena decimal(8,2),
    in varIdTermina int(11),
    out rez int(11)
	)
begin
	declare a int(11) default 0;
	if(varid >0) then
		insert into usluga(IdUsluge,Naziv,Cijena) values(varid,varNaziv,varCijena);
		insert into wellnessusluga(IdUsluge,IdTermina) values(varid,varIdTermina);
        set rez=varid;
	else
		insert into usluga(Naziv,Cijena) values(varNaziv,varCijena);
		select max(IdUsluge) into a from usluga;
		insert into wellnessusluga(IdUsluge,IdTermina) values(a,varIdTermina);
        set rez=a;
    end if;
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-27 10:46:05
