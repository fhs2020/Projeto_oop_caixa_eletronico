-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: new_bank_fhs
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `conta`
--

DROP TABLE IF EXISTS `conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conta` (
  `saldo` float DEFAULT NULL,
  `ContaId` int(11) NOT NULL,
  `NumeroConta` int(11) DEFAULT NULL,
  `DataAbertura` date DEFAULT NULL,
  `transacaoAnterior` float DEFAULT NULL,
  `senha` varchar(6) DEFAULT NULL,
  `tipoConta` varchar(100) DEFAULT NULL,
  `NomeCliente` varchar(100) DEFAULT NULL,
  `clienteId` int(11) DEFAULT NULL,
  `dataDeposito` date DEFAULT NULL,
  `dataSaque` date DEFAULT NULL,
  `dataTransacao` date DEFAULT NULL,
  `tipoTransacao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ContaId`),
  KEY `Conta_tipoConta_idx` (`tipoConta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conta`
--

LOCK TABLES `conta` WRITE;
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
INSERT INTO `conta` VALUES (1000520,1,1,NULL,0,'123456','CC','Peyton Maning',1,NULL,NULL,NULL,NULL),(5003330,7,7,NULL,0,'1234','CC','Lamar',7,NULL,NULL,NULL,NULL),(98652,8,8,NULL,0,'008','Checking','Flavio H Sousa',8,'2017-10-31',NULL,'2017-10-31','Deposito'),(55950800,51,51,NULL,0,'123456','Checking','Aron Rodgers',51,'2017-10-31',NULL,'2017-10-31','saque'),(850000000,55,55,NULL,0,'1234','CC','Ailton Pessoa',55,NULL,NULL,NULL,NULL),(552112000,105,105,NULL,0,'123456','cc','Flavio',105,NULL,NULL,NULL,NULL),(85,106,106,NULL,0,'123456','CC','John Ray',106,NULL,NULL,NULL,NULL),(5000,121,NULL,'0000-00-00',1212,'1255','cc','JP',1211,NULL,NULL,NULL,NULL),(98000,125,125,NULL,0,'123456','Checking Account','Uncle Joe',125,'2017-10-31',NULL,'2017-10-31','Criação de conta bancaria, e deposito inicial'),(21455,214,214,NULL,0,'0214','0214','Frank Gore',214,'2017-10-31',NULL,'2017-10-31','Criação de conta bancaria, e deposito inicial'),(214000000,215,215,NULL,0,'215','Checking','Frank Gore',215,'2017-10-31',NULL,'2017-10-31','Criação de conta bancaria, e deposito inicial'),(215000000,216,216,NULL,0,'216','Checking','Frank Gore 21',216,'2017-10-31',NULL,'2017-10-31','Criação de conta bancaria, e deposito inicial'),(28000000,217,217,NULL,0,'217','Checking','J Z',217,'2017-10-31',NULL,'2017-10-31','Criação de conta bancaria, e deposito inicial'),(256000,218,218,NULL,0,'218','checking','Frank Gore',218,'2017-10-31',NULL,'2017-10-31','Criação de conta bancaria, e deposito inicial'),(95000000,275,275,NULL,0,'275','Checking','AJ AJAY',275,'2017-10-31',NULL,'2017-10-31','saque'),(874545000,454,454,NULL,0,'8055','Checking','Marcus',454,NULL,NULL,NULL,NULL),(45554000,1212,1212,NULL,0,'1212','CC','Cam Newton',1212,NULL,NULL,NULL,NULL),(21610000,2610,2610,NULL,0,'2610','Checking','Frank Gore',2610,'2017-10-31',NULL,'2017-10-31','Criação de conta bancaria, e deposito inicial'),(850,8055,8055,NULL,0,'8055','CC','Mr John Smith',8055,NULL,NULL,NULL,NULL),(8377370000,8888,8888,NULL,0,'','CC','',8888,NULL,NULL,NULL,NULL),(83838400,1211212,1211212,NULL,0,'8055','CC','Dalvin Cook',1211212,NULL,NULL,NULL,NULL),(454554,4544545,4544545,NULL,0,'1212','cc','ddddd',4544545,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico`
--

DROP TABLE IF EXISTS `historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico` (
  `conta` int(11) DEFAULT NULL,
  `dataTransacao` date DEFAULT NULL,
  `tipoTransacao` varchar(55) DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `dataAbertura` date DEFAULT NULL,
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `valorTransacao` float DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico`
--

LOCK TABLES `historico` WRITE;
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
INSERT INTO `historico` VALUES (2610,'2017-10-31','Criação de conta bancaria, e deposito inicial',21610000,'2017-10-31',1,NULL),(275,'2017-10-31','Criação de conta bancaria, e deposito inicial',95000000,'2017-10-31',2,NULL),(275,'2017-10-31','Deposito',95000100,NULL,3,105),(275,'2017-10-31','saque',95000000,NULL,4,55),(275,'2017-10-31','saque',95000000,NULL,5,40);
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'new_bank_fhs'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-01 10:52:27
