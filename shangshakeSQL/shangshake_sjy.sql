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
  `atime` time NOT NULL,
  `isanonymous` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ano`)
) ENGINE=InnoDB AUTO_INCREMENT=20002 DEFAULT CHARSET=utf8mb4;

/*Data for the table `appraise` */

insert  into `appraise`(`ano`,`acontent`,`astar`,`atime`,`isanonymous`) values (20001,'课程/教师真心不错',9.5,'00:20:20',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8mb4;

/*Data for the table `course` */

insert  into `course`(`cno`,`cname`,`averagestar`,`credit`,`cintroduction`,`cpicture`,`kno`) values (10001,'C语言程序设计',9.5,3.5,'C语言是最基础的高级程序设计语言...','001.png',1),(10002,'数据库课程设计',9.3,3,'123','002.png',1),(10003,'操作系统',9.4,4,'234','003.png',1),(10004,'Java程序设计',9.2,3.5,'333','004.png',7);

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

/*Table structure for table `kind` */

DROP TABLE IF EXISTS `kind`;

CREATE TABLE `kind` (
  `kno` int(11) NOT NULL AUTO_INCREMENT,
  `kname` varchar(255) NOT NULL,
  PRIMARY KEY (`kno`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kind` */

insert  into `kind`(`kno`,`kname`) values (1,'学科基础平台课程（必修）'),(2,'通识教育选修课（公选）'),(3,'通识教育核心课(公选)'),(4,'通识教育必修课(必修)'),(5,'专业必修课程(必修)'),(6,'实践环节(必修)'),(7,'专业选修课程(限选)');

/*Table structure for table `sca` */

DROP TABLE IF EXISTS `sca`;

CREATE TABLE `sca` (
  `sno` int(11) NOT NULL,
  `cno` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  PRIMARY KEY (`sno`,`cno`,`ano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sca` */

insert  into `sca`(`sno`,`cno`,`ano`) values (2019001,10001,20001);

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

insert  into `sct`(`sno`,`cno`,`tno`,`passed`) values (2019001,10001,2005001,1),(2019001,10002,2005001,1),(2019501,10001,2005001,1),(2019501,10004,2005001,1);

/*Table structure for table `sctcurrent` */

DROP TABLE IF EXISTS `sctcurrent`;

CREATE TABLE `sctcurrent` (
  `sno` int(11) NOT NULL,
  `cno` int(11) NOT NULL,
  `tno` int(11) NOT NULL,
  PRIMARY KEY (`sno`,`cno`,`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sctcurrent` */

insert  into `sctcurrent`(`sno`,`cno`,`tno`) values (2019001,10003,2005001);

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

insert  into `specialty`(`spno`,`spname`) values (1,'计算机技术'),(2,'软件工程'),(3,'网络工程'),(4,'信息安全'),(5,'自动化'),(6,'材料工程'),(7,'有机化学'),(8,'材料工程'),(9,'物理');

/*Table structure for table `spkcredit` */

DROP TABLE IF EXISTS `spkcredit`;

CREATE TABLE `spkcredit` (
  `spno` int(11) NOT NULL,
  `kno` int(11) NOT NULL,
  `credit` float DEFAULT NULL,
  PRIMARY KEY (`spno`,`kno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `spkcredit` */

insert  into `spkcredit`(`spno`,`kno`,`credit`) values (1,1,30),(1,2,20),(1,3,45.5),(1,4,30),(1,5,20),(1,6,20),(1,7,25.5),(2,1,31.5),(2,2,22),(2,3,40),(2,4,27),(2,5,15),(2,6,19),(2,7,20);

/*Table structure for table `sta` */

DROP TABLE IF EXISTS `sta`;

CREATE TABLE `sta` (
  `sno` int(11) NOT NULL,
  `tno` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  PRIMARY KEY (`sno`,`tno`,`ano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sta` */

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

insert  into `student`(`sno`,`spno`,`sname`,`username`,`password`,`phonenumber`,`spicture`,`sex`,`grade`) values (2019001,1,'张三','三德子','123456','19155556666','002.png','男','2019'),(2019501,2,'李华','lihua','234567','1884455667','9501.png','女','2019'),(2147483647,1,'李四','inters','123456','18355555555','001.png','男','2019');

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
