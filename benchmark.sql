-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: microservice
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pointaccount`
--

DROP TABLE IF EXISTS `pointaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pointaccount` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '积分id',
  `point_activity` bigint unsigned NOT NULL COMMENT '积分所属活动',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL COMMENT '修改时间',
  `point_number` bigint unsigned NOT NULL COMMENT '积分数量',
  `user_id` bigint unsigned NOT NULL COMMENT '积分所属用户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pointaccount_un` (`id`,`point_activity`),
  KEY `pointaccount_fk` (`user_id`),
  CONSTRAINT `pointaccount_fk` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pointaccount`
--

LOCK TABLES `pointaccount` WRITE;
/*!40000 ALTER TABLE `pointaccount` DISABLE KEYS */;
INSERT INTO `pointaccount` VALUES (1,1,'2021-08-24 03:35:57','2021-08-24 03:35:57',0,1);
/*!40000 ALTER TABLE `pointaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pointdetail`
--

DROP TABLE IF EXISTS `pointdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pointdetail` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `point_change` bigint NOT NULL COMMENT '积分变化值',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL COMMENT '修改时间',
  `point_id` bigint unsigned NOT NULL COMMENT '所属积分账号',
  `change_reason` varchar(100) DEFAULT NULL COMMENT '积分变更理由',
  `user_id` bigint unsigned NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `pointdetail_fk_1` (`user_id`),
  KEY `pointdetail_fk` (`point_id`),
  CONSTRAINT `pointdetail_fk` FOREIGN KEY (`point_id`) REFERENCES `pointaccount` (`id`),
  CONSTRAINT `pointdetail_fk_1` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pointdetail`
--

LOCK TABLES `pointdetail` WRITE;
/*!40000 ALTER TABLE `pointdetail` DISABLE KEYS */;
INSERT INTO `pointdetail` VALUES (4,10,'2021-08-24 03:39:42','2021-08-24 03:39:42',1,'增加积分10',1),(5,-6,'2021-08-24 03:39:42','2021-08-24 03:39:42',1,'减少积分6',1);
/*!40000 ALTER TABLE `pointdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prize_content`
--

DROP TABLE IF EXISTS `prize_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prize_content` (
  `prize_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '奖品id',
  `prize_type` bigint unsigned NOT NULL COMMENT '奖品类型',
  `gmt_create` datetime NOT NULL COMMENT '奖品创建时间',
  `prize_num` bigint unsigned NOT NULL COMMENT '奖品数量',
  `activity_id` bigint unsigned NOT NULL COMMENT '所属活动id',
  `prize_money` bigint unsigned NOT NULL COMMENT '奖品金额',
  PRIMARY KEY (`prize_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prize_content`
--

LOCK TABLES `prize_content` WRITE;
/*!40000 ALTER TABLE `prize_content` DISABLE KEYS */;
INSERT INTO `prize_content` VALUES (1,1,'2021-08-30 12:48:00',21474,1,3),(2,1,'2021-08-30 12:48:02',83647,1,5),(3,1,'2021-08-30 12:48:09',65536,1,10),(4,2,'2021-08-30 12:48:10',12138,1,3),(5,2,'2021-08-30 12:48:12',12345,1,5),(6,2,'2021-08-30 12:48:20',67890,1,10),(7,0,'2021-08-30 12:48:30',2147483647,1,0);
/*!40000 ALTER TABLE `prize_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prize_detail`
--

DROP TABLE IF EXISTS `prize_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prize_detail` (
  `detail_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '奖品流水id',
  `detail_content` varchar(100) DEFAULT NULL COMMENT '奖品流水详情',
  `gmt_create` datetime NOT NULL COMMENT '奖品发放时间',
  `prize_id` bigint unsigned NOT NULL COMMENT '奖品id',
  `user_id` bigint unsigned NOT NULL COMMENT '获得奖品的用户id',
  `activity_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `prize_detail_fk` (`user_id`),
  KEY `prize_detail_fk_1` (`prize_id`),
  CONSTRAINT `prize_detail_fk` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`user_id`),
  CONSTRAINT `prize_detail_fk_1` FOREIGN KEY (`prize_id`) REFERENCES `prize_content` (`prize_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prize_detail`
--

LOCK TABLES `prize_detail` WRITE;
/*!40000 ALTER TABLE `prize_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `prize_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taskcontent`
--

DROP TABLE IF EXISTS `taskcontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taskcontent` (
  `task_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '任务id号',
  `task_name` varchar(100) NOT NULL COMMENT '任务名称',
  `gmt_create` datetime NOT NULL COMMENT '任务上线时间',
  `activity_id` bigint unsigned NOT NULL COMMENT '所属活动id',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taskcontent`
--

LOCK TABLES `taskcontent` WRITE;
/*!40000 ALTER TABLE `taskcontent` DISABLE KEYS */;
INSERT INTO `taskcontent` VALUES (1,'添加到我的关注','2021-08-24 09:54:40',1),(2,'添加到首页','2021-08-24 09:55:28',1),(3,'浏览商品15秒','2021-08-30 12:47:00',1),(4,'购买一次商品','2021-08-30 12:48:00',1),(5,'分享链接给好友','2021-08-30 12:49:00',1),(6,'增加活动提醒','2021-08-30 12:50:00',1),(7,'测试任务','2021-08-30 07:48:10',1),(8,'测试任务','2021-08-30 07:48:22',1);
/*!40000 ALTER TABLE `taskcontent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taskdetail`
--

DROP TABLE IF EXISTS `taskdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taskdetail` (
  `detail_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '任务流水id',
  `detail_content` varchar(100) DEFAULT NULL COMMENT '完成任务内容',
  `gmt_create` datetime NOT NULL COMMENT '任务流水形成时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '任务流水修改时间',
  `task_id` bigint unsigned NOT NULL,
  `user_id` bigint unsigned NOT NULL,
  `acitivity_id` bigint unsigned NOT NULL COMMENT '所属活动id',
  PRIMARY KEY (`detail_id`),
  KEY `taskdetail_fk` (`user_id`),
  KEY `taskdetail_fk_1` (`task_id`),
  CONSTRAINT `taskdetail_fk` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`user_id`),
  CONSTRAINT `taskdetail_fk_1` FOREIGN KEY (`task_id`) REFERENCES `taskcontent` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taskdetail`
--

LOCK TABLES `taskdetail` WRITE;
/*!40000 ALTER TABLE `taskdetail` DISABLE KEYS */;
INSERT INTO `taskdetail` VALUES (1,NULL,'2021-08-24 09:55:28',NULL,1,1,1),(2,NULL,'2021-08-30 07:48:22',NULL,1,1,1);
/*!40000 ALTER TABLE `taskdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo` (
  `user_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改信息时间',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'wgq','1999-12-31 23:59:59',NULL,'gif1'),(2,'test','2021-08-20 11:37:00',NULL,'gif2'),(3,'ls','2021-08-30 11:37:00',NULL,'gif3'),(4,'syq','2021-08-20 12:37:00',NULL,'gif4'),(5,'jc','2021-08-20 12:47:00',NULL,'gif5'),(6,'zjq','2021-08-20 12:57:00',NULL,'gif6'),(7,'syw','2021-08-20 12:58:00',NULL,'gif7');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'microservice'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-30 17:03:45
