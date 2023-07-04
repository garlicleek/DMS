-- MySQL dump 10.13  Distrib 5.7.35, for Win64 (x86_64)
--
-- Host: localhost    Database: archive_manage
-- ------------------------------------------------------
-- Server version	5.7.35

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
-- Table structure for table `archive`
--

DROP TABLE IF EXISTS `archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bianhao` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mingcheng` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lujing` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `leixing_id` int(11) DEFAULT NULL,
  `laiyuan_id` int(11) DEFAULT NULL,
  `miji_id` int(11) DEFAULT NULL,
  `zhuangtai` int(11) DEFAULT NULL,
  `danganjia` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive`
--

LOCK TABLES `archive` WRITE;
/*!40000 ALTER TABLE `archive` DISABLE KEYS */;
INSERT INTO `archive` VALUES (1,'111','111','/file/1688135808065.jpg',2,2,0,11,NULL),(2,'1','1','/file/1688289312580.txt',2,2,1,11,'1'),(3,'1','1','/file/1688289394614.jpg',2,2,1,6,'1'),(7,'1','1','/file/1688368345476.png',2,2,1,2,NULL);
/*!40000 ALTER TABLE `archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_history`
--

DROP TABLE IF EXISTS `archive_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `archive_id` int(11) DEFAULT NULL,
  `caozuo_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  `beizhu` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_history`
--

LOCK TABLES `archive_history` WRITE;
/*!40000 ALTER TABLE `archive_history` DISABLE KEYS */;
INSERT INTO `archive_history` VALUES (1,6,1,'2023-07-03 15:21:24','111',1),(2,7,2,'2023-07-03 15:23:16','111',1),(3,6,3,'2023-07-03 15:31:23','111',1),(4,6,4,'2023-07-03 15:31:38','111',1),(6,6,5,'2023-07-03 15:36:01','111',1),(7,6,6,'2023-07-03 15:42:58','111',1),(8,6,14,'2023-07-03 15:50:34','111',1),(9,6,15,'2023-07-03 15:54:27','111',1),(10,6,8,'2023-07-03 16:12:55','111',1),(11,6,9,'2023-07-03 16:13:53','111',1),(12,6,11,'2023-07-03 16:14:01','111',1),(13,6,12,'2023-07-03 16:24:33','111',1);
/*!40000 ALTER TABLE `archive_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_jieyue`
--

DROP TABLE IF EXISTS `archive_jieyue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_jieyue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `archive_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT '0' COMMENT '0申请中1通过2拒绝',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_jieyue`
--

LOCK TABLES `archive_jieyue` WRITE;
/*!40000 ALTER TABLE `archive_jieyue` DISABLE KEYS */;
INSERT INTO `archive_jieyue` VALUES (1,2,1,'2023-07-02 19:39:49',1),(2,4,1,'2023-07-02 21:08:47',1),(3,5,1,'2023-07-02 21:12:27',1),(4,5,1,'2023-07-02 21:12:40',1),(5,3,5,'2023-07-02 22:26:22',0),(6,6,1,'2023-07-03 15:50:34',1);
/*!40000 ALTER TABLE `archive_jieyue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_laiyuan`
--

DROP TABLE IF EXISTS `archive_laiyuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_laiyuan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mingcheng` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_laiyuan`
--

LOCK TABLES `archive_laiyuan` WRITE;
/*!40000 ALTER TABLE `archive_laiyuan` DISABLE KEYS */;
INSERT INTO `archive_laiyuan` VALUES (2,'测试');
/*!40000 ALTER TABLE `archive_laiyuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_leixing`
--

DROP TABLE IF EXISTS `archive_leixing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_leixing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mingcheng` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_leixing`
--

LOCK TABLES `archive_leixing` WRITE;
/*!40000 ALTER TABLE `archive_leixing` DISABLE KEYS */;
INSERT INTO `archive_leixing` VALUES (2,'测试');
/*!40000 ALTER TABLE `archive_leixing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bumen`
--

DROP TABLE IF EXISTS `bumen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bumen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mingcheng` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dengji` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `shangji_id` int(11) DEFAULT NULL COMMENT '1级部门上级默认0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bumen`
--

LOCK TABLES `bumen` WRITE;
/*!40000 ALTER TABLE `bumen` DISABLE KEYS */;
INSERT INTO `bumen` VALUES (1,'档案管理部','1',0),(2,'档案室','2',1),(3,'档案整理员','3',2),(15,'额','3',2),(16,'保密室','2',1),(17,'档案员','3',2),(18,'档案借阅管理员','3',2),(19,'档案保密员','3',16);
/*!40000 ALTER TABLE `bumen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bumen_quanxian`
--

DROP TABLE IF EXISTS `bumen_quanxian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bumen_quanxian` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bumen_id` int(11) DEFAULT NULL,
  `quanxian_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bumen_quanxian`
--

LOCK TABLES `bumen_quanxian` WRITE;
/*!40000 ALTER TABLE `bumen_quanxian` DISABLE KEYS */;
INSERT INTO `bumen_quanxian` VALUES (2,3,2),(3,3,3),(4,3,5);
/*!40000 ALTER TABLE `bumen_quanxian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quanxian`
--

DROP TABLE IF EXISTS `quanxian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quanxian` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mingcheng` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `jiekou` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quanxian`
--

LOCK TABLES `quanxian` WRITE;
/*!40000 ALTER TABLE `quanxian` DISABLE KEYS */;
INSERT INTO `quanxian` VALUES (2,'查看档案','/dangan/selDanganPage'),(3,'立卷','/dangan/insDangan'),(4,'查看档案详情','/dangan/selDanganInfo'),(5,'修改档案','/dangan/updDangan'),(6,'删除档案','/dangan/delDangan'),(7,'审批通过','/dangan/updSp1'),(8,'审批不通过','/dangan/updSp2'),(9,'交接','/dangan/updJj'),(10,'鉴定','/dangan/updJd'),(11,'归档','/dangan/updGd'),(12,'保管','/dangan/updBaoguan'),(13,'借阅','/dangan/updJy'),(14,'定期鉴定','/dangan/updDqjd'),(15,'通过销毁申请','/dangan/updXh1'),(16,'拒绝销毁申请','/dangan/updXh2'),(17,'销毁','/dangan/updXh');
/*!40000 ALTER TABLE `quanxian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` int(11) DEFAULT NULL COMMENT '0管理员1员工2用户',
  `mingcheng` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bumen_id` int(11) DEFAULT '3' COMMENT '0为管理员1一级2二级3三级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin',0,'张三',0),(2,'lisi','lisi',1,'李四',3),(3,'2','2',1,'2',17),(5,'wangwu','wangwu',2,'王五',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'archive_manage'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-03 22:03:14
