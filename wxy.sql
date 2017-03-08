# Host: 115.28.61.167  (Version 5.5.50-0ubuntu0.14.04.1)
# Date: 2016-08-18 15:19:22
# Generator: MySQL-Front 5.3  (Build 6.26)

/*!40101 SET NAMES gb2312 */;

#
# Structure for table "activity"
#

DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `activityId` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `simpleDes` varchar(255) DEFAULT NULL,
  `complexDes` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `expectNum` int(11) DEFAULT NULL,
  `deadLine` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `activitypic` varchar(255) DEFAULT NULL,
  `showit` int(11) DEFAULT '0',
  PRIMARY KEY (`activityId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "activity"
#

INSERT INTO `activity` VALUES (7,'Wed May 25 04:37:04 CST 2016',NULL,'adwadwadw','4111111111111111111',0,'er',10,'2016-06-04','17854160637',NULL,0),(8,'Wed May 25 04:39:38 CST 2016',NULL,'adwadwadw','4111111111111111111',0,'er',10,'2016-07-23','17854160637','17854160637Wed_May_25_04-37-01_CST_2016',0);

#
# Structure for table "person"
#

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `tel` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `myDrawWish` varchar(255) DEFAULT NULL,
  `myDrawAct` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "person"
#

INSERT INTO `person` VALUES ('17854160637','mLWXt1jdjcxRzl5wOT5joA==','男','2016-05-01','1014804721@qq.com','Sun May 15 20:08:42 CST 2016','fffff',',',',3,');

#
# Structure for table "team"
#

DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `tel` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) DEFAULT NULL,
  `teamName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `myDrawWish` varchar(255) DEFAULT ',',
  `myDrawAct` varchar(255) DEFAULT ',',
  PRIMARY KEY (`tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "team"
#

INSERT INTO `team` VALUES ('17854160637','DiiDvC23C9p+rJV3xlR97w==','er','1014804721@qq.com','ww','Sun May 15 18:56:05 CST 2016',',25,',','),('17865169935','2JNaNT4KDB6dqq45+pZ99A==','SOS','123@qq.com','大阪','Mon Jun 06 20:08:00 CST 2016',',',',');

#
# Structure for table "wish"
#

DROP TABLE IF EXISTS `wish`;
CREATE TABLE `wish` (
  `wishId` int(11) NOT NULL AUTO_INCREMENT,
  `simpleDes` varchar(255) DEFAULT NULL,
  `complexDes` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `expectTime` varchar(255) DEFAULT NULL,
  `expectNum` int(11) DEFAULT NULL,
  `deadLine` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `wishpic` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `showit` varchar(11) DEFAULT '0',
  PRIMARY KEY (`wishId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

#
# Data for table "wish"
#

INSERT INTO `wish` VALUES (30,'adwadw','54555555555555555555','0','222','Wed May 25 07:47:05 CST 2016','17854160637','2016-05-28',20,NULL,'男','wishpic/17854160637Wed_May_25_07-47-03_CST_2016',0,'2'),(31,'阿达瓦达瓦','盛大的挖的挖的挖','0','222','Wed May 25 07:51:59 CST 2016','17854160637','2016-08-26',10,NULL,'男','wishpic/17854160637Wed_May_25_07-51-54_CST_2016',0,'0');
