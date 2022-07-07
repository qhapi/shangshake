/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.5-10.6.5-MariaDB-1:10.6.5+maria~focal : Database - shangshake
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shangshake` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `shangshake`;

/*Table structure for table `appraise` */

DROP TABLE IF EXISTS `appraise`;

CREATE TABLE `appraise` (
  `ano` int(11) NOT NULL AUTO_INCREMENT,
  `acontent` varchar(255) DEFAULT NULL,
  `astar` float NOT NULL,
  `atime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `isanonymous` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ano`)
) ENGINE=InnoDB AUTO_INCREMENT=20014 DEFAULT CHARSET=utf8mb4;

/*Data for the table `appraise` */

insert  into `appraise`(`ano`,`acontent`,`astar`,`atime`,`isanonymous`) values (20001,'课程/教师真心不错',9.5,'2022-07-07 00:20:20',0),(20002,'jj',1.2,'2022-07-07 10:22:19',0),(20003,'jjj',1.2,'2022-07-07 10:30:26',0),(20004,NULL,2,'2022-07-07 10:44:55',1),(20005,NULL,2,'2022-07-07 10:46:05',1),(20006,'try0abc',2,'2022-07-07 10:53:49',1),(20007,'try0abcd',3,'2022-07-07 11:58:06',1),(20008,'try0abcd',4,'2022-07-07 12:03:25',1),(20009,'e',5,'2022-07-07 16:58:32',1),(20010,'12',1,'2022-07-07 18:25:34',0),(20011,'123',2,'2022-07-07 18:25:51',0),(20012,'llll',10,'2022-07-07 20:38:34',0),(20013,'allll',10,'2022-07-07 20:40:15',0);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `cno` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) NOT NULL,
  `averagestar` float DEFAULT NULL,
  `credit` float NOT NULL,
  `cintroduction` varchar(255) DEFAULT NULL,
  `cpicture` varchar(255) DEFAULT NULL,
  `kno` int(11) DEFAULT NULL,
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4;

/*Data for the table `course` */

insert  into `course`(`cno`,`cname`,`averagestar`,`credit`,`cintroduction`,`cpicture`,`kno`) values (10001,'C语言程序设计',9.5,3.5,'C语言是最基础的高级程序设计语言...','001.png',1);

/*Table structure for table `ct` */

DROP TABLE IF EXISTS `ct`;

CREATE TABLE `ct` (
  `cno` int(11) NOT NULL,
  `tno` int(11) NOT NULL,
  `cbeginweek` int(11) DEFAULT NULL,
  `cendweek` int(11) DEFAULT NULL,
  `week` int(11) DEFAULT NULL,
  `section` int(11) DEFAULT NULL,
  `testmethod` varchar(50) DEFAULT NULL,
  `teachplace` varchar(50) DEFAULT NULL,
  `teachmethod` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`cno`,`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `ct` */

insert  into `ct`(`cno`,`tno`,`cbeginweek`,`cendweek`,`week`,`section`,`testmethod`,`teachplace`,`teachmethod`,`remark`) values (10001,2005001,1,3,2,1,NULL,NULL,NULL,NULL);

/*Table structure for table `kind` */

DROP TABLE IF EXISTS `kind`;

CREATE TABLE `kind` (
  `kno` int(11) NOT NULL AUTO_INCREMENT,
  `kname` varchar(255) NOT NULL,
  PRIMARY KEY (`kno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kind` */

insert  into `kind`(`kno`,`kname`) values (1,'必修');

/*Table structure for table `sca` */

DROP TABLE IF EXISTS `sca`;

CREATE TABLE `sca` (
  `sno` int(11) NOT NULL,
  `cno` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  PRIMARY KEY (`sno`,`cno`,`ano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sca` */

insert  into `sca`(`sno`,`cno`,`ano`) values (2019001,10001,20001),(2019001,10001,20009);

/*Table structure for table `sct` */

DROP TABLE IF EXISTS `sct`;

CREATE TABLE `sct` (
  `sno` int(11) NOT NULL,
  `cno` int(11) NOT NULL,
  `tno` int(11) NOT NULL,
  `passed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`sno`,`cno`,`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sct` */

/*Table structure for table `sctcurrent` */

DROP TABLE IF EXISTS `sctcurrent`;

CREATE TABLE `sctcurrent` (
  `sno` int(11) NOT NULL,
  `cno` int(11) NOT NULL,
  `tno` int(11) NOT NULL,
  PRIMARY KEY (`sno`,`cno`,`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sctcurrent` */

/*Table structure for table `spc` */

DROP TABLE IF EXISTS `spc`;

CREATE TABLE `spc` (
  `spno` int(11) NOT NULL,
  `cno` int(11) DEFAULT NULL,
  PRIMARY KEY (`spno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `spc` */

/*Table structure for table `specialty` */

DROP TABLE IF EXISTS `specialty`;

CREATE TABLE `specialty` (
  `spno` int(11) NOT NULL,
  `spname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`spno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `specialty` */

/*Table structure for table `spkcredit` */

DROP TABLE IF EXISTS `spkcredit`;

CREATE TABLE `spkcredit` (
  `spno` int(11) NOT NULL,
  `kno` int(11) NOT NULL,
  `credit` float DEFAULT NULL,
  PRIMARY KEY (`spno`,`kno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `spkcredit` */

/*Table structure for table `sta` */

DROP TABLE IF EXISTS `sta`;

CREATE TABLE `sta` (
  `sno` int(11) NOT NULL,
  `tno` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  PRIMARY KEY (`sno`,`tno`,`ano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sta` */

insert  into `sta`(`sno`,`tno`,`ano`) values (2009001,2005001,20010),(2019001,2005001,20011),(2019001,2005001,20012),(2019001,2005001,20013);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `spno` int(11) NOT NULL COMMENT '专业号',
  `sname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `spicture` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `grade` varchar(255) NOT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=2147483648 DEFAULT CHARSET=utf8mb4;

/*Data for the table `student` */

insert  into `student`(`sno`,`spno`,`sname`,`username`,`password`,`phonenumber`,`spicture`,`sex`,`grade`) values (2019001,1,'张三','三德子','123456','19155556666','002.png','男','2019'),(2147483647,1,'李四','inters','123456','18355555555','001.png','男','2019');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `tno` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(255) NOT NULL,
  `duty` varchar(255) NOT NULL,
  `t_edu_rec` varchar(255) DEFAULT NULL COMMENT '教师学历',
  `tpicture` varchar(255) DEFAULT NULL,
  `tintroduction` varchar(255) NOT NULL,
  `contact` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tno`)
) ENGINE=InnoDB AUTO_INCREMENT=2005002 DEFAULT CHARSET=utf8mb4;

/*Data for the table `teacher` */

insert  into `teacher`(`tno`,`tname`,`duty`,`t_edu_rec`,`tpicture`,`tintroduction`,`contact`) values (2005001,'王老师','副教授','本科就读于...，硕士毕业于...，曾于...访学','003.png','王老师，曾就职于...，现就职于...,研究方向为...，著有...文章','wanhglaoshi@email.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
