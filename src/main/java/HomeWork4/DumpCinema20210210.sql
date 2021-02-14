-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema_db
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `movies_tbl`
--

DROP TABLE IF EXISTS `movies_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies_tbl` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `movie_name_fld` varchar(101) NOT NULL,
  `movie_length_fld` time NOT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies_tbl`
--

LOCK TABLES `movies_tbl` WRITE;
/*!40000 ALTER TABLE `movies_tbl` DISABLE KEYS */;
INSERT INTO `movies_tbl` VALUES (1,'Best Movie','01:30:00'),(2,'Worst Movie','02:00:00'),(3,'Movie 63','01:30:00'),(4,'Blue Movie','01:00:00'),(5,'Black Movie','01:00:00'),(6,'White Movie','02:00:00'),(7,'Black and White Movie','02:00:00'),(8,'Terminator 2','02:00:00'),(9,'Avatar Light','01:30:00');
/*!40000 ALTER TABLE `movies_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_running_tbl`
--

DROP TABLE IF EXISTS `show_running_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show_running_tbl` (
  `show_id` int NOT NULL AUTO_INCREMENT,
  `show_movie_id` int NOT NULL,
  `show_date_fld` date NOT NULL,
  `show_starting_time_fld` time NOT NULL,
  PRIMARY KEY (`show_id`),
  KEY `fk_movie_id_idx` (`show_movie_id`),
  CONSTRAINT `fk_movie_id` FOREIGN KEY (`show_movie_id`) REFERENCES `movies_tbl` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_running_tbl`
--

LOCK TABLES `show_running_tbl` WRITE;
/*!40000 ALTER TABLE `show_running_tbl` DISABLE KEYS */;
INSERT INTO `show_running_tbl` VALUES (1,2,'2020-01-03','10:30:00'),(2,2,'2020-01-03','14:00:00'),(3,2,'2020-01-03','17:30:00'),(4,2,'2020-01-03','18:45:00'),(5,2,'2020-01-03','20:00:00'),(6,2,'2020-01-03','23:15:00'),(7,3,'2020-01-04','10:30:00'),(8,4,'2020-01-04','14:00:00'),(9,5,'2020-01-04','17:30:00'),(10,6,'2020-01-04','18:45:00'),(11,1,'2020-01-04','20:00:00'),(12,7,'2020-01-04','23:15:00'),(13,9,'2020-01-05','10:30:00'),(14,8,'2020-01-05','14:00:00'),(15,1,'2020-01-05','17:30:00'),(16,2,'2020-01-05','18:45:00'),(17,1,'2020-01-05','20:00:00'),(18,1,'2020-01-05','23:15:00');
/*!40000 ALTER TABLE `show_running_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets_tbl`
--

DROP TABLE IF EXISTS `tickets_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets_tbl` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `show_id` int NOT NULL,
  `cost_fld` decimal(10,0) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `fk_show_id_idx` (`show_id`),
  CONSTRAINT `fk_show_id` FOREIGN KEY (`show_id`) REFERENCES `show_running_tbl` (`show_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets_tbl`
--

LOCK TABLES `tickets_tbl` WRITE;
/*!40000 ALTER TABLE `tickets_tbl` DISABLE KEYS */;
INSERT INTO `tickets_tbl` VALUES (1,1,80),(2,1,80),(3,1,80),(4,1,80),(5,1,80),(6,1,80),(7,1,80),(8,1,80),(9,2,250),(10,2,250),(11,3,250),(12,3,250),(13,4,350),(14,4,350),(15,5,400),(16,5,400),(17,6,600),(18,6,600),(19,6,600),(20,6,600),(21,6,600),(22,7,90),(23,8,260),(24,9,260),(25,10,360),(26,11,400),(27,12,600),(28,13,100),(29,13,100),(30,13,100),(31,13,100),(32,16,380),(33,16,380),(34,16,380),(35,17,390),(36,17,390),(37,17,390),(38,17,390);
/*!40000 ALTER TABLE `tickets_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-11 13:41:33
