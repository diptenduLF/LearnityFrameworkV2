-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: lkeframework
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
-- Table structure for table `add_action_param`
--

DROP TABLE IF EXISTS `add_action_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_action_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `action_sequence` varchar(10) DEFAULT NULL,
  `action_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`),
  KEY `add_action_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `add_param`
--

DROP TABLE IF EXISTS `add_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `query_id` varchar(100) NOT NULL DEFAULT '',
  `add_param_value` blob,
  `add_parameter` blob,
  PRIMARY KEY (`interface_id`,`part_id`,`query_id`),
  KEY `add_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `add_validation_param`
--

DROP TABLE IF EXISTS `add_validation_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_validation_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `query_id` varchar(100) NOT NULL DEFAULT '',
  `add_param_value` blob,
  `add_parameter` blob,
  `message` varchar(200) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `function_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`,`query_id`),
  KEY `add_validation_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_user`
--

DROP TABLE IF EXISTS `admin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_user` (
  `user_id` varchar(25) NOT NULL,
  `password` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `administrator_creation_details`
--

DROP TABLE IF EXISTS `administrator_creation_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator_creation_details` (
  `user_id` varchar(25) NOT NULL,
  `date_user_created` datetime DEFAULT NULL,
  `user_created_by` varchar(25) DEFAULT NULL,
  `last_modification_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `administrator_details`
--

DROP TABLE IF EXISTS `administrator_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator_details` (
  `user_id` varchar(25) NOT NULL,
  `user_first_name` varchar(50) NOT NULL,
  `user_middle_name` varchar(50) DEFAULT NULL,
  `user_last_name` varchar(50) NOT NULL,
  `gender` varchar(8) DEFAULT NULL,
  `email_id` varchar(25) DEFAULT NULL,
  `department` varchar(25) DEFAULT NULL,
  `educational_qualification` varchar(25) DEFAULT NULL,
  `age` varchar(2) DEFAULT NULL,
  `account_status` varchar(25) DEFAULT NULL,
  `experience` varchar(10) DEFAULT NULL,
  `access_priviledge` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `administrator_logged_in_at`
--

DROP TABLE IF EXISTS `administrator_logged_in_at`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator_logged_in_at` (
  `user_id` varchar(25) NOT NULL,
  `logged_in_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `administrator_login_time`
--

DROP TABLE IF EXISTS `administrator_login_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator_login_time` (
  `user_id` varchar(25) NOT NULL,
  `login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `administrator_previlege_management`
--

DROP TABLE IF EXISTS `administrator_previlege_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator_previlege_management` (
  `user_id` varchar(25) NOT NULL,
  `previledge` varchar(35) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `application_template_asset`
--

DROP TABLE IF EXISTS `application_template_asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_template_asset` (
  `application_template_id` int NOT NULL DEFAULT '0',
  `delivery_mode` varchar(300) DEFAULT NULL,
  `asset_type` varchar(300) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `sequence_no` int NOT NULL DEFAULT '0',
  `file_name` varchar(300) DEFAULT NULL,
  `asset_path` varchar(300) DEFAULT NULL,
  `template_asset_file` longblob,
  PRIMARY KEY (`sequence_no`,`application_template_id`),
  KEY `application_template_asset1` (`sequence_no`,`application_template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `application_template_default`
--

DROP TABLE IF EXISTS `application_template_default`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_template_default` (
  `application_template_id` int NOT NULL DEFAULT '0',
  `section_name` varchar(100) NOT NULL DEFAULT '',
  `class_name` varchar(100) NOT NULL DEFAULT '',
  `attribute_name` varchar(100) NOT NULL DEFAULT '',
  `default_value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`application_template_id`,`section_name`,`class_name`,`attribute_name`),
  KEY `application_template_default1` (`application_template_id`,`section_name`,`class_name`,`attribute_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `application_template_master`
--

DROP TABLE IF EXISTS `application_template_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_template_master` (
  `application_template_id` mediumint NOT NULL AUTO_INCREMENT,
  `application_template_title` varchar(300) DEFAULT NULL,
  `templatecomment` varchar(300) DEFAULT NULL,
  `upload_on` datetime NOT NULL,
  `default_value` varchar(100) DEFAULT NULL,
  `ui_framework` varchar(100) NOT NULL DEFAULT 'jQueryUI',
  `block_ui_timeout` int NOT NULL DEFAULT '2000',
  `applivation_xml_value` blob,
  PRIMARY KEY (`application_template_id`),
  UNIQUE KEY `application_template_title` (`application_template_title`),
  KEY `application_template_master1` (`application_template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `behaviour`
--

DROP TABLE IF EXISTS `behaviour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `behaviour` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `behaviour_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `value` varchar(100) DEFAULT NULL,
  `valuetype` varchar(100) DEFAULT NULL,
  `target` varchar(100) DEFAULT NULL,
  `behaviourevent` varchar(100) DEFAULT NULL,
  `parameter` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `callback` varchar(100) DEFAULT NULL,
  `resourceid` varchar(100) DEFAULT NULL,
  `javaclass` varchar(100) DEFAULT NULL,
  `package` varchar(100) DEFAULT NULL,
  `resource_location` varchar(100) DEFAULT NULL,
  `dom_ready` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`interface_id`,`behaviour_id`,`part_id`),
  KEY `behaviour1` (`interface_id`,`behaviour_id`,`part_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13324 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cache_definition`
--

DROP TABLE IF EXISTS `cache_definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cache_definition` (
  `cache_id` mediumint NOT NULL AUTO_INCREMENT,
  `cache_name` varchar(100) NOT NULL DEFAULT '',
  `cache_type` varchar(20) DEFAULT NULL,
  `max_element` int NOT NULL,
  `overflowtodisk` tinyint(1) NOT NULL DEFAULT '1',
  `timetoliveseconds` int NOT NULL DEFAULT '0',
  `timetoidleseconds` int NOT NULL DEFAULT '0',
  `diskpersistent` tinyint(1) NOT NULL DEFAULT '0',
  `eternal` tinyint(1) NOT NULL DEFAULT '0',
  `diskexpirythreadintervalseconds` int NOT NULL DEFAULT '0',
  `memorystoreevictionpolicy` varchar(10) NOT NULL DEFAULT 'LFU',
  `diskstorepath` varchar(250) DEFAULT NULL,
  `default_cache` varchar(10) NOT NULL DEFAULT 'No',
  PRIMARY KEY (`cache_id`),
  UNIQUE KEY `cache_name` (`cache_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `columnmodel`
--

DROP TABLE IF EXISTS `columnmodel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `columnmodel` (
  `interface_id` varchar(100) NOT NULL,
  `part_id` varchar(100) NOT NULL,
  `col_head` varchar(100) DEFAULT NULL,
  `col_name` varchar(100) NOT NULL,
  `col_index` int DEFAULT NULL,
  `col_width` varchar(100) DEFAULT NULL,
  `col_editable` varchar(100) DEFAULT NULL,
  `col_hidden` varchar(100) DEFAULT NULL,
  `key_value` varchar(100) DEFAULT NULL,
  `required` varchar(100) DEFAULT NULL,
  `minval` varchar(100) DEFAULT NULL,
  `maxval` varchar(100) DEFAULT NULL,
  `dbcolumnname` varchar(200) DEFAULT NULL,
  `edithidden` varchar(200) DEFAULT NULL,
  `influence` varchar(100) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  `number_check` varchar(300) DEFAULT NULL,
  `custom` varchar(300) DEFAULT NULL,
  `custom_func` varchar(300) DEFAULT NULL,
  `default_type` varchar(200) DEFAULT NULL,
  `default_value` varchar(200) DEFAULT NULL,
  `formatter` varchar(100) DEFAULT NULL,
  `searchoptions` varchar(150) NOT NULL DEFAULT '',
  PRIMARY KEY (`interface_id`,`part_id`,`col_name`),
  KEY `columnmodel1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `configuration_item`
--

DROP TABLE IF EXISTS `configuration_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuration_item` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `createsession` varchar(100) DEFAULT NULL,
  `checkrole` varchar(100) DEFAULT NULL,
  `contenttype` varchar(100) DEFAULT NULL,
  `doctypepublic` varchar(100) DEFAULT NULL,
  `doctypesystem` varchar(100) DEFAULT NULL,
  `cachecontrol` varchar(300) DEFAULT NULL,
  `expires` varchar(300) DEFAULT NULL,
  `lastmodify` varchar(300) DEFAULT NULL,
  `template` varchar(300) DEFAULT NULL,
  `themes_id` varchar(200) DEFAULT NULL,
  `enable_caching` varchar(10) NOT NULL DEFAULT ' ',
  `cache_name` varchar(200) DEFAULT NULL,
  `cache_dynamic_js` varchar(10) NOT NULL DEFAULT ' ',
  `cache_dynamic_css` varchar(10) NOT NULL DEFAULT ' ',
  `cache_dynamic_image` varchar(10) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`interface_id`),
  KEY `configuration_item1` (`interface_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `content` (
  `menu_item_id` mediumint NOT NULL AUTO_INCREMENT,
  `interface_id` varchar(100) NOT NULL,
  `content_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `value` longblob,
  `contenttype` varchar(100) DEFAULT NULL,
  `active_value` longblob,
  `contentlocation` varchar(100) DEFAULT NULL,
  `show_choose_one` varchar(100) DEFAULT NULL,
  `choose_one_label` varchar(200) DEFAULT NULL,
  `choose_one_value` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`menu_item_id`,`interface_id`,`content_id`,`part_id`),
  KEY `content1` (`interface_id`,`content_id`,`part_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17668 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `delete_action_param`
--

DROP TABLE IF EXISTS `delete_action_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delete_action_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `action_sequence` varchar(10) DEFAULT NULL,
  `action_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`),
  KEY `delete_action_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `delete_param`
--

DROP TABLE IF EXISTS `delete_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delete_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `query_id` varchar(100) NOT NULL DEFAULT '',
  `delete_param_value` blob,
  `delete_parameter` blob,
  PRIMARY KEY (`interface_id`,`part_id`,`query_id`),
  KEY `delete_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `delete_validation_param`
--

DROP TABLE IF EXISTS `delete_validation_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delete_validation_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `query_id` varchar(100) NOT NULL DEFAULT '',
  `delete_param_value` blob,
  `delete_parameter` blob,
  `message` varchar(200) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `function_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`,`query_id`),
  KEY `delete_validation_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dropdownmenu`
--

DROP TABLE IF EXISTS `dropdownmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dropdownmenu` (
  `menu_item_id` mediumint NOT NULL AUTO_INCREMENT,
  `interface_id` varchar(100) DEFAULT NULL,
  `part_id` varchar(100) NOT NULL,
  `name` varchar(300) DEFAULT NULL,
  `value` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`menu_item_id`),
  KEY `dropdownmenu1` (`interface_id`,`part_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1069 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `edit_type`
--

DROP TABLE IF EXISTS `edit_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edit_type` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `col_name` varchar(100) NOT NULL DEFAULT '',
  `type` varchar(100) DEFAULT NULL,
  `size` varchar(100) DEFAULT NULL,
  `textarea_rows` varchar(100) DEFAULT NULL,
  `textarea_cols` varchar(100) DEFAULT NULL,
  `editdomaintype` varchar(100) DEFAULT NULL,
  `value` blob,
  `active_value` blob,
  `multiple` varchar(200) DEFAULT NULL,
  `imageAccessURL` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`,`col_name`),
  KEY `edit_type1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flash_parameter`
--

DROP TABLE IF EXISTS `flash_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flash_parameter` (
  `interface_flash_parameter_id` mediumint NOT NULL AUTO_INCREMENT,
  `interface_id` varchar(100) NOT NULL,
  `content_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `parameter_name` varchar(200) DEFAULT NULL,
  `parameter_value` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`interface_flash_parameter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `form_add_param`
--

DROP TABLE IF EXISTS `form_add_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_add_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `query_id` varchar(100) NOT NULL DEFAULT '',
  `add_param_value` blob,
  `add_parameter` blob,
  PRIMARY KEY (`interface_id`,`part_id`,`query_id`),
  KEY `form_add_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `form_delete_param`
--

DROP TABLE IF EXISTS `form_delete_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_delete_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `query_id` varchar(100) NOT NULL DEFAULT '',
  `delete_param_value` blob,
  `delete_parameter` blob,
  PRIMARY KEY (`interface_id`,`part_id`,`query_id`),
  KEY `form_delete_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `form_element`
--

DROP TABLE IF EXISTS `form_element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_element` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `element_id` varchar(100) NOT NULL DEFAULT '',
  `element_type` varchar(100) DEFAULT NULL,
  `element_key` varchar(100) DEFAULT NULL,
  `selectindex` int DEFAULT '0',
  `modifyindex` int DEFAULT '0',
  `insertindex` int DEFAULT '0',
  `forlabel` varchar(100) DEFAULT NULL,
  `required` varchar(100) DEFAULT NULL,
  `minlength` varchar(50) DEFAULT NULL,
  `maxlength` varchar(50) DEFAULT NULL,
  `equalto` varchar(50) DEFAULT NULL,
  `numbercheck` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `requiredmess` varchar(200) DEFAULT NULL,
  `minlengthmess` varchar(200) DEFAULT NULL,
  `maxlengthmess` varchar(200) DEFAULT NULL,
  `equaltomess` varchar(200) DEFAULT NULL,
  `numbercheckmess` varchar(200) DEFAULT NULL,
  `emailmess` varchar(200) DEFAULT NULL,
  `placeholder` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`,`element_id`),
  KEY `form_element1` (`interface_id`,`part_id`,`element_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `form_modify_param`
--

DROP TABLE IF EXISTS `form_modify_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_modify_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `query_id` varchar(100) NOT NULL DEFAULT '',
  `modify_param_value` blob,
  `modify_parameter` blob,
  PRIMARY KEY (`interface_id`,`part_id`,`query_id`),
  KEY `form_modify_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `form_select_param`
--

DROP TABLE IF EXISTS `form_select_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_select_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `query_id` varchar(100) NOT NULL DEFAULT '',
  `select_param_value` blob,
  `select_parameter` blob,
  PRIMARY KEY (`interface_id`,`part_id`,`query_id`),
  KEY `form_select_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formelement`
--

DROP TABLE IF EXISTS `formelement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formelement` (
  `interface_id` varchar(100) NOT NULL,
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `formmethod` varchar(100) DEFAULT NULL,
  `formaction` varchar(100) DEFAULT NULL,
  `jscontrol` varchar(100) DEFAULT NULL,
  `success_method` varchar(200) DEFAULT NULL,
  `failure_method` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`),
  KEY `formelement1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `framework_asset`
--

DROP TABLE IF EXISTS `framework_asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `framework_asset` (
  `asset_id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(200) DEFAULT NULL,
  `upload_on` datetime DEFAULT NULL,
  `asset_type` varchar(300) DEFAULT NULL,
  `value` blob,
  PRIMARY KEY (`asset_id`),
  KEY `framework_asset1` (`asset_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `framework_file`
--

DROP TABLE IF EXISTS `framework_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `framework_file` (
  `framework_file_id` varchar(100) NOT NULL,
  `framework_file_title` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `filesize` varchar(100) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  `inlinecss` varchar(100) DEFAULT NULL,
  `inlinejs` varchar(100) DEFAULT NULL,
  `imagepath` varchar(100) DEFAULT NULL,
  `value` longblob,
  `last_updated` datetime DEFAULT NULL,
  PRIMARY KEY (`framework_file_id`),
  KEY `framework_file1` (`framework_file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grid_column_snipet`
--

DROP TABLE IF EXISTS `grid_column_snipet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grid_column_snipet` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `column_name` varchar(100) NOT NULL DEFAULT '',
  `grid_snipet` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`,`column_name`),
  KEY `grid_column_snipet1` (`interface_id`,`part_id`,`column_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grid_structure`
--

DROP TABLE IF EXISTS `grid_structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grid_structure` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `altRows` varchar(1) DEFAULT NULL,
  `autowidth` varchar(1) DEFAULT NULL,
  `ignoreCase` varchar(1) DEFAULT NULL,
  `rowNumbers` varchar(1) DEFAULT NULL,
  `altClass` varchar(100) DEFAULT NULL,
  `searchonEnter` varchar(1) DEFAULT NULL,
  `columnChooser` varchar(1) DEFAULT NULL,
  `toolbarSearch` varchar(1) DEFAULT NULL,
  `shrinkToFit` varchar(1) DEFAULT NULL,
  `onSelectRow` varchar(100) DEFAULT NULL,
  `searchOperators` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`),
  KEY `grid_structure1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gridquery`
--

DROP TABLE IF EXISTS `gridquery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gridquery` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `grid_query` blob,
  `active_query` blob,
  `load_parameter` blob,
  `server_cache_grid` varchar(10) NOT NULL DEFAULT ' ',
  `cachekey_includeuserid` varchar(10) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`interface_id`,`part_id`),
  KEY `gridquery1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interface`
--

DROP TABLE IF EXISTS `interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interface` (
  `interface_id` varchar(100) NOT NULL,
  `interface_title` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `filesize` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`interface_id`),
  KEY `interface1` (`interface_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interface_chart_content`
--

DROP TABLE IF EXISTS `interface_chart_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interface_chart_content` (
  `interface_chart_item_id` mediumint NOT NULL AUTO_INCREMENT,
  `interface_id` varchar(100) NOT NULL,
  `content_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `metric_title` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `X_axis_query` blob,
  `series1_query` blob,
  `series2_query` blob,
  `series3_query` blob,
  `legenddata1` varchar(50) DEFAULT NULL,
  `legenddata2` varchar(50) DEFAULT NULL,
  `legenddata3` varchar(50) DEFAULT NULL,
  `chart_type` varchar(50) DEFAULT NULL,
  `subtype` varchar(50) DEFAULT NULL,
  `width` int NOT NULL DEFAULT '0',
  `height` int NOT NULL DEFAULT '0',
  `yaxis_label` varchar(100) DEFAULT NULL,
  `xaxis_label` varchar(100) DEFAULT NULL,
  `color` int NOT NULL DEFAULT '0',
  `transpose` varchar(5) DEFAULT NULL,
  `stacked` varchar(5) DEFAULT NULL,
  `chart_dimension` varchar(40) NOT NULL DEFAULT 'TwoDimensional',
  PRIMARY KEY (`interface_chart_item_id`,`interface_id`,`content_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interface_report_content`
--

DROP TABLE IF EXISTS `interface_report_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interface_report_content` (
  `interface_report_item_id` mediumint NOT NULL AUTO_INCREMENT,
  `interface_id` varchar(100) NOT NULL,
  `content_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `rptdesign_file_name` varchar(100) DEFAULT NULL,
  `viewer_type` varchar(100) DEFAULT NULL,
  `report_chooser` varchar(100) DEFAULT NULL,
  `report_executer` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`interface_report_item_id`,`interface_id`,`content_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interfaceenginecalling`
--

DROP TABLE IF EXISTS `interfaceenginecalling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interfaceenginecalling` (
  `interface_id` varchar(100) NOT NULL,
  `valueblob` longblob,
  `layout_id` varchar(100) NOT NULL DEFAULT '',
  `content_id` varchar(100) NOT NULL DEFAULT '',
  `behaviour_id` varchar(100) NOT NULL DEFAULT '',
  `style_id` varchar(100) NOT NULL DEFAULT '',
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`interface_id`,`layout_id`,`content_id`,`behaviour_id`,`style_id`),
  KEY `interfaceenginecalling1` (`interface_id`,`layout_id`,`content_id`,`behaviour_id`,`style_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `keycolumn`
--

DROP TABLE IF EXISTS `keycolumn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `keycolumn` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `keycolumn_value` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`),
  KEY `keycolumn1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `layout`
--

DROP TABLE IF EXISTS `layout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `layout` (
  `layout_interface_id` int NOT NULL AUTO_INCREMENT,
  `interface_id` varchar(100) NOT NULL,
  `layout_id` varchar(100) DEFAULT NULL,
  `part_id` varchar(100) DEFAULT NULL,
  `height` varchar(100) DEFAULT NULL,
  `width` varchar(100) NOT NULL,
  `x` varchar(100) DEFAULT NULL,
  `y` varchar(100) NOT NULL,
  `position` varchar(100) DEFAULT NULL,
  `parent_id` varchar(100) NOT NULL,
  PRIMARY KEY (`layout_interface_id`),
  KEY `layout1` (`interface_id`,`layout_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32053 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `manifestinterfaceassociation`
--

DROP TABLE IF EXISTS `manifestinterfaceassociation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manifestinterfaceassociation` (
  `manifest_id` varchar(100) NOT NULL DEFAULT '',
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`manifest_id`,`interface_id`),
  KEY `manifestinterfaceassociation1` (`interface_id`,`manifest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `modify_action_param`
--

DROP TABLE IF EXISTS `modify_action_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modify_action_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `action_sequence` varchar(10) DEFAULT NULL,
  `action_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`),
  KEY `modify_action_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `modify_param`
--

DROP TABLE IF EXISTS `modify_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modify_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `query_id` varchar(100) NOT NULL DEFAULT '',
  `modify_param_value` blob,
  `modify_parameter` blob,
  PRIMARY KEY (`interface_id`,`part_id`,`query_id`),
  KEY `modify_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `modify_validation_param`
--

DROP TABLE IF EXISTS `modify_validation_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modify_validation_param` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `query_id` varchar(100) NOT NULL DEFAULT '',
  `modify_param_value` blob,
  `modify_parameter` blob,
  `message` varchar(200) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `function_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`,`query_id`),
  KEY `modify_validation_param1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `optionmenu`
--

DROP TABLE IF EXISTS `optionmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `optionmenu` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL,
  `option_id` varchar(100) NOT NULL,
  `labelname` varchar(100) DEFAULT NULL,
  `labelvalue` varchar(100) DEFAULT NULL,
  `eventname` varchar(30) DEFAULT NULL,
  `eventvalue` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`,`option_id`),
  KEY `optionmenu1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `report_parameter`
--

DROP TABLE IF EXISTS `report_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_parameter` (
  `interface_report_parameter_id` mediumint NOT NULL AUTO_INCREMENT,
  `interface_id` varchar(100) NOT NULL,
  `content_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `parameter_name` varchar(200) NOT NULL DEFAULT '',
  `parameter_value` varchar(300) DEFAULT NULL,
  `parameter_value_type` varchar(300) DEFAULT NULL,
  `value_source_item_name` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`interface_report_parameter_id`,`interface_id`,`content_id`,`part_id`,`parameter_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource` (
  `interface_id` varchar(100) NOT NULL,
  `resource_id` varchar(100) NOT NULL DEFAULT '',
  `value` longblob,
  `href` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `keyvalue` varchar(100) DEFAULT NULL,
  `resource_location` varchar(100) DEFAULT NULL,
  `size` varchar(100) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uploaded_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`resource_id`),
  KEY `resource1` (`interface_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_type`
--

DROP TABLE IF EXISTS `resource_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource_type` (
  `resource_type_id` varchar(100) NOT NULL,
  `resource_type_title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`resource_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `role1` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roleassociation`
--

DROP TABLE IF EXISTS `roleassociation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roleassociation` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `role_id` varchar(100) NOT NULL,
  `layout_id` varchar(100) DEFAULT NULL,
  `style_id` varchar(100) DEFAULT NULL,
  `content_id` varchar(100) DEFAULT NULL,
  `behaviour_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`interface_id`),
  KEY `roleassociation1` (`interface_id`,`role_id`,`layout_id`,`style_id`,`content_id`,`behaviour_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `selector`
--

DROP TABLE IF EXISTS `selector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selector` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `selector_id` varchar(100) NOT NULL DEFAULT '',
  `gridrefresh` varchar(100) DEFAULT NULL,
  `influence` varchar(100) DEFAULT NULL,
  `influencegridcolumn` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`selector_id`,`part_id`),
  KEY `selector1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `snippet`
--

DROP TABLE IF EXISTS `snippet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `snippet` (
  `interface_id` varchar(100) NOT NULL,
  `snippet_id` varchar(100) NOT NULL DEFAULT '',
  `value` longblob,
  PRIMARY KEY (`interface_id`,`snippet_id`),
  KEY `snippet1` (`interface_id`,`snippet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `structure`
--

DROP TABLE IF EXISTS `structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `structure` (
  `interface_id` varchar(100) NOT NULL,
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `part_class` varchar(100) NOT NULL,
  `resize` varchar(100) NOT NULL DEFAULT ' ',
  `border` varchar(100) NOT NULL DEFAULT ' ',
  `textarea_cols` varchar(100) NOT NULL DEFAULT ' ',
  `textarea_rows` varchar(100) NOT NULL DEFAULT ' ',
  `scrolling` varchar(100) NOT NULL DEFAULT ' ',
  `spacing` varchar(100) NOT NULL DEFAULT ' ',
  `colspan` varchar(100) NOT NULL DEFAULT ' ',
  `length` varchar(100) NOT NULL DEFAULT ' ',
  `size` varchar(100) NOT NULL DEFAULT ' ',
  `tabindex` varchar(100) NOT NULL DEFAULT ' ',
  `archieve` varchar(100) NOT NULL DEFAULT ' ',
  `codebase` varchar(100) NOT NULL DEFAULT ' ',
  `mayscript` varchar(100) NOT NULL DEFAULT ' ',
  `loadurl` varchar(200) NOT NULL DEFAULT ' ',
  `editurl` varchar(200) NOT NULL DEFAULT ' ',
  `caption` varchar(100) NOT NULL DEFAULT ' ',
  `sortname` varchar(300) NOT NULL DEFAULT ' ',
  `sortorder` varchar(100) NOT NULL DEFAULT ' ',
  `gridhidden` varchar(100) NOT NULL DEFAULT ' ',
  `gridnavbar` varchar(100) NOT NULL DEFAULT ' ',
  `multiselect` varchar(100) DEFAULT NULL,
  `multiboxonly` varchar(100) DEFAULT NULL,
  `gridrowNum` varchar(100) DEFAULT NULL,
  `gridrowList` varchar(100) DEFAULT NULL,
  `dateformat` varchar(200) DEFAULT NULL,
  `resetSearchOnClose` varchar(100) DEFAULT NULL,
  `multiplesearch` varchar(100) DEFAULT NULL,
  `customeditbutton` varchar(100) DEFAULT NULL,
  `griddata` varchar(100) DEFAULT NULL,
  `griddatatype` varchar(100) DEFAULT NULL,
  `autoencode` varchar(10) DEFAULT NULL,
  `subgrid` varchar(20) DEFAULT NULL,
  `parentgrid_id` varchar(100) DEFAULT NULL,
  `generateExcel` varchar(20) DEFAULT NULL,
  `autocomplete` varchar(20) DEFAULT NULL,
  `multiple` varchar(20) DEFAULT NULL,
  `showsearchboxonload` varchar(20) DEFAULT NULL,
  `searchboxtop` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`),
  KEY `structure1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `style`
--

DROP TABLE IF EXISTS `style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `style` (
  `interface_id` varchar(100) NOT NULL,
  `style_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `value` blob,
  `styletype` varchar(100) NOT NULL,
  `resource_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`interface_id`,`part_id`,`style_id`,`styletype`),
  KEY `style1` (`interface_id`,`style_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `themes`
--

DROP TABLE IF EXISTS `themes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `themes` (
  `themes_id` varchar(200) NOT NULL DEFAULT '',
  `default_value` varchar(100) DEFAULT NULL,
  `xml_value` blob,
  `upload_on` datetime NOT NULL,
  PRIMARY KEY (`themes_id`),
  KEY `themes1` (`themes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `themes_css`
--

DROP TABLE IF EXISTS `themes_css`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `themes_css` (
  `themes_css_id` int NOT NULL AUTO_INCREMENT,
  `themes_id` varchar(200) DEFAULT NULL,
  `css_value` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`themes_css_id`),
  KEY `themes_css1` (`themes_css_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `themes_definition`
--

DROP TABLE IF EXISTS `themes_definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `themes_definition` (
  `themes_id` varchar(200) DEFAULT NULL,
  `themes_element_id` int NOT NULL AUTO_INCREMENT,
  `class_type` varchar(200) DEFAULT NULL,
  `prop_type` varchar(200) DEFAULT NULL,
  `css_classes` varchar(200) DEFAULT NULL,
  `properties` blob,
  `property_application` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`themes_element_id`),
  KEY `themes_definition1` (`themes_element_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tree_structure`
--

DROP TABLE IF EXISTS `tree_structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tree_structure` (
  `interface_id` varchar(100) NOT NULL DEFAULT '',
  `part_id` varchar(100) NOT NULL DEFAULT '',
  `treedataremotefunction` varchar(100) DEFAULT NULL,
  `onselectremotefunction` varchar(100) DEFAULT NULL,
  `onpostinitfunction` varchar(100) DEFAULT NULL,
  `autocollapse` varchar(100) DEFAULT NULL,
  `initialiseonload` varchar(100) DEFAULT NULL,
  `islazynode` varchar(100) DEFAULT NULL,
  `tooltip` varchar(100) DEFAULT NULL,
  `nodestructure` blob,
  `parentsql` blob,
  `childnodesql` blob,
  `tree_parameter` blob,
  PRIMARY KEY (`interface_id`,`part_id`),
  KEY `tree_structure1` (`interface_id`,`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-13 20:34:13
