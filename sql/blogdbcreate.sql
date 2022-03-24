/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.34-log : Database - blog_reference
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog_reference` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog_reference`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `uid` bigint(20) NOT NULL,
  `content` longtext COMMENT '内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `first_picture` varchar(255) NOT NULL DEFAULT 'https://unsplash.it/800/450?image=271' COMMENT '首图',
  `flag` varchar(255) DEFAULT NULL COMMENT '完成状态',
  `thumbs` int(10) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `published` tinyint(1) NOT NULL COMMENT '发布状态',
  `recommend` tinyint(1) NOT NULL COMMENT '推荐状态',
  `share_statement` varchar(10) NOT NULL COMMENT '版权状态',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `views` int(10) DEFAULT '0' COMMENT '浏览次数',
  `type_id` bigint(20) NOT NULL,
  `description` longtext NOT NULL,
  `appreciation` tinyint(1) NOT NULL COMMENT '赞赏状态',
  `commentabled` tinyint(1) DEFAULT NULL COMMENT '评论状态',
  PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `blog_tag` */

DROP TABLE IF EXISTS `blog_tag`;

CREATE TABLE `blog_tag` (
  `blog_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`blog_id`,`tag_id`),
  KEY `tag_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `chat_log` */

DROP TABLE IF EXISTS `chat_log`;

CREATE TABLE `chat_log` (
  `msg_id` bigint(20) NOT NULL COMMENT '''消息id''',
  `sender` bigint(20) NOT NULL,
  `receiver` bigint(20) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '''发送消息的时间''',
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '''消息内容''',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` bigint(20) NOT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `crawled_blog` */

DROP TABLE IF EXISTS `crawled_blog`;

CREATE TABLE `crawled_blog` (
  `blog_id` bigint(20) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `first_picture` varchar(255) DEFAULT NULL,
  `thumbs` int(10) DEFAULT NULL,
  `views` int(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT '这是一篇从CSDN上爬取下来的博客，仅供学习使用，如侵权请发邮件到1626680964@qq.com，联系本人立马删除，谢谢！',
  `avatar` varchar(255) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `friends` */

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `uid` bigint(20) NOT NULL COMMENT '''用户本人的id''',
  `friend_id` bigint(20) NOT NULL COMMENT '''拥有的朋友的id''',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '''添加好友的时间''',
  PRIMARY KEY (`uid`,`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `group_chat` */

DROP TABLE IF EXISTS `group_chat`;

CREATE TABLE `group_chat` (
  `msg_id` bigint(20) NOT NULL COMMENT '消息id',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送消息的时间',
  `room_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '群聊id',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `link` */

DROP TABLE IF EXISTS `link`;

CREATE TABLE `link` (
  `link_id` bigint(20) NOT NULL COMMENT '友链id',
  `link_name` varchar(50) NOT NULL COMMENT '友链名称',
  `avatar_link` varchar(255) NOT NULL COMMENT '友链头像地址',
  `blog_link` varchar(255) NOT NULL COMMENT '友链地址',
  `description` varchar(255) NOT NULL COMMENT '友链博客描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `data_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '友链展示状态',
  PRIMARY KEY (`link_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `mid` bigint(20) NOT NULL COMMENT '留言id',
  `time` varchar(255) NOT NULL COMMENT '弹幕过屏时间',
  `message_content` varchar(255) NOT NULL COMMENT '留言内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `avatar` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `tag_id` bigint(20) NOT NULL,
  `tag_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `thumbs_up` */

DROP TABLE IF EXISTS `thumbs_up`;

CREATE TABLE `thumbs_up` (
  `blog_id` bigint(20) NOT NULL,
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`blog_id`,`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `type_id` bigint(20) NOT NULL,
  `type_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `last_ip` varchar(16) DEFAULT NULL COMMENT '上次登录的ip',
  `data_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '禁用状态',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `views` */

DROP TABLE IF EXISTS `views`;

CREATE TABLE `views` (
  `blog_id` bigint(20) NOT NULL COMMENT '访问的博客id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '访问的时间',
  `ip` varchar(50) NOT NULL COMMENT '访问者的ip',
  PRIMARY KEY (`blog_id`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Trigger structure for table `thumbs_up` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `thumbs_up_insert` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `thumbs_up_insert` AFTER INSERT ON `thumbs_up` FOR EACH ROW BEGIN
UPDATE blog as b SET b.thumbs =b.thumbs+1   WHERE b.blog_id= new.blog_id ;
END */$$


DELIMITER ;

/* Trigger structure for table `thumbs_up` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `thumbs_up_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `thumbs_up_delete` AFTER DELETE ON `thumbs_up` FOR EACH ROW BEGIN
UPDATE blog as b SET b.thumbs = b.thumbs - 1   WHERE b.blog_id= old.blog_id  ;
END */$$


DELIMITER ;

/*Table structure for table `blog_views` */

DROP TABLE IF EXISTS `blog_views`;

/*!50001 DROP VIEW IF EXISTS `blog_views` */;
/*!50001 DROP TABLE IF EXISTS `blog_views` */;

/*!50001 CREATE TABLE  `blog_views`(
 `blog_id` bigint(20) ,
 `title` varchar(100) ,
 `create_time` timestamp ,
 `ip` varchar(50) 
)*/;

/*Table structure for table `comments_statistics` */

DROP TABLE IF EXISTS `comments_statistics`;

/*!50001 DROP VIEW IF EXISTS `comments_statistics` */;
/*!50001 DROP TABLE IF EXISTS `comments_statistics` */;

/*!50001 CREATE TABLE  `comments_statistics`(
 `blog_id` bigint(20) ,
 `title` varchar(100) ,
 `uid` bigint(20) ,
 `seven_day` bigint(21) ,
 `six_day` bigint(21) ,
 `five_day` bigint(21) ,
 `four_day` bigint(21) ,
 `three_day` bigint(21) ,
 `two_day` bigint(21) ,
 `one_day` bigint(21) 
)*/;

/*Table structure for table `report` */

DROP TABLE IF EXISTS `report`;

/*!50001 DROP VIEW IF EXISTS `report` */;
/*!50001 DROP TABLE IF EXISTS `report` */;

/*!50001 CREATE TABLE  `report`(
 `c_seven_day` decimal(42,0) ,
 `c_six_day` decimal(42,0) ,
 `c_five_day` decimal(42,0) ,
 `c_four_day` decimal(42,0) ,
 `c_three_day` decimal(42,0) ,
 `c_two_day` decimal(42,0) ,
 `c_one_day` decimal(42,0) ,
 `v_seven_day` decimal(42,0) ,
 `v_six_day` decimal(42,0) ,
 `v_five_day` decimal(42,0) ,
 `v_four_day` decimal(42,0) ,
 `v_three_day` decimal(42,0) ,
 `v_two_day` decimal(42,0) ,
 `v_one_day` decimal(42,0) 
)*/;

/*Table structure for table `thumbs_up_statistics` */

DROP TABLE IF EXISTS `thumbs_up_statistics`;

/*!50001 DROP VIEW IF EXISTS `thumbs_up_statistics` */;
/*!50001 DROP TABLE IF EXISTS `thumbs_up_statistics` */;

/*!50001 CREATE TABLE  `thumbs_up_statistics`(
 `blog_id` bigint(20) ,
 `title` varchar(100) ,
 `uid` bigint(20) ,
 `seven_day` bigint(21) ,
 `six_day` bigint(21) ,
 `five_day` bigint(21) ,
 `four_day` bigint(21) ,
 `three_day` bigint(21) ,
 `two_day` bigint(21) ,
 `one_day` bigint(21) 
)*/;

/*Table structure for table `views_statistics` */

DROP TABLE IF EXISTS `views_statistics`;

/*!50001 DROP VIEW IF EXISTS `views_statistics` */;
/*!50001 DROP TABLE IF EXISTS `views_statistics` */;

/*!50001 CREATE TABLE  `views_statistics`(
 `blog_id` bigint(20) ,
 `title` varchar(100) ,
 `uid` bigint(20) ,
 `seven_day` bigint(21) ,
 `six_day` bigint(21) ,
 `five_day` bigint(21) ,
 `four_day` bigint(21) ,
 `three_day` bigint(21) ,
 `two_day` bigint(21) ,
 `one_day` bigint(21) 
)*/;

/*View structure for view blog_views */

/*!50001 DROP TABLE IF EXISTS `blog_views` */;
/*!50001 DROP VIEW IF EXISTS `blog_views` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `blog_views` AS select `v`.`blog_id` AS `blog_id`,`b`.`title` AS `title`,`v`.`create_time` AS `create_time`,`v`.`ip` AS `ip` from (`views` `v` join `blog` `b`) where (`b`.`blog_id` = `v`.`blog_id`) */;

/*View structure for view comments_statistics */

/*!50001 DROP TABLE IF EXISTS `comments_statistics` */;
/*!50001 DROP VIEW IF EXISTS `comments_statistics` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `comments_statistics` AS select `cp`.`blog_id` AS `blog_id`,`b`.`title` AS `title`,`b`.`uid` AS `uid`,ifnull((select count(0) from `comment` `c` where ((date_format(`c`.`create_time`,'%y-%m-%d') = (curdate() - interval 6 day)) and (`c`.`blog_id` = `cp`.`blog_id`))),0) AS `seven_day`,ifnull((select count(0) from `comment` `c` where ((date_format(`c`.`create_time`,'%y-%m-%d') = (curdate() - interval 5 day)) and (`c`.`blog_id` = `cp`.`blog_id`))),0) AS `six_day`,ifnull((select count(0) from `comment` `c` where ((date_format(`c`.`create_time`,'%y-%m-%d') = (curdate() - interval 4 day)) and (`c`.`blog_id` = `cp`.`blog_id`))),0) AS `five_day`,ifnull((select count(0) from `comment` `c` where ((date_format(`c`.`create_time`,'%y-%m-%d') = (curdate() - interval 3 day)) and (`c`.`blog_id` = `cp`.`blog_id`))),0) AS `four_day`,ifnull((select count(0) from `comment` `c` where ((date_format(`c`.`create_time`,'%y-%m-%d') = (curdate() - interval 2 day)) and (`c`.`blog_id` = `cp`.`blog_id`))),0) AS `three_day`,ifnull((select count(0) from `comment` `c` where ((date_format(`c`.`create_time`,'%y-%m-%d') = (curdate() - interval 1 day)) and (`c`.`blog_id` = `cp`.`blog_id`))),0) AS `two_day`,ifnull((select count(0) from `comment` `c` where ((date_format(`c`.`create_time`,'%y-%m-%d') > (curdate() - interval 1 day)) and (`c`.`blog_id` = `cp`.`blog_id`))),0) AS `one_day` from (`comment` `cp` join `blog` `b`) where (`b`.`blog_id` = `cp`.`blog_id`) group by `cp`.`blog_id` */;

/*View structure for view report */

/*!50001 DROP TABLE IF EXISTS `report` */;
/*!50001 DROP VIEW IF EXISTS `report` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `report` AS select sum(`c`.`seven_day`) AS `c_seven_day`,sum(`c`.`six_day`) AS `c_six_day`,sum(`c`.`five_day`) AS `c_five_day`,sum(`c`.`four_day`) AS `c_four_day`,sum(`c`.`three_day`) AS `c_three_day`,sum(`c`.`two_day`) AS `c_two_day`,sum(`c`.`one_day`) AS `c_one_day`,sum(`v`.`seven_day`) AS `v_seven_day`,sum(`v`.`six_day`) AS `v_six_day`,sum(`v`.`five_day`) AS `v_five_day`,sum(`v`.`four_day`) AS `v_four_day`,sum(`v`.`three_day`) AS `v_three_day`,sum(`v`.`two_day`) AS `v_two_day`,sum(`v`.`one_day`) AS `v_one_day` from (`comments_statistics` `c` join `views_statistics` `v`) */;

/*View structure for view thumbs_up_statistics */

/*!50001 DROP TABLE IF EXISTS `thumbs_up_statistics` */;
/*!50001 DROP VIEW IF EXISTS `thumbs_up_statistics` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `thumbs_up_statistics` AS select `tp`.`blog_id` AS `blog_id`,`b`.`title` AS `title`,`b`.`uid` AS `uid`,ifnull((select count(0) from `thumbs_up` `t` where ((date_format(`t`.`create_time`,'%y-%m-%d') = (curdate() - interval 6 day)) and (`t`.`blog_id` = `tp`.`blog_id`))),0) AS `seven_day`,ifnull((select count(0) from `thumbs_up` `t` where ((date_format(`t`.`create_time`,'%y-%m-%d') = (curdate() - interval 5 day)) and (`t`.`blog_id` = `tp`.`blog_id`))),0) AS `six_day`,ifnull((select count(0) from `thumbs_up` `t` where ((date_format(`t`.`create_time`,'%y-%m-%d') = (curdate() - interval 4 day)) and (`t`.`blog_id` = `tp`.`blog_id`))),0) AS `five_day`,ifnull((select count(0) from `thumbs_up` `t` where ((date_format(`t`.`create_time`,'%y-%m-%d') = (curdate() - interval 3 day)) and (`t`.`blog_id` = `tp`.`blog_id`))),0) AS `four_day`,ifnull((select count(0) from `thumbs_up` `t` where ((date_format(`t`.`create_time`,'%y-%m-%d') = (curdate() - interval 2 day)) and (`t`.`blog_id` = `tp`.`blog_id`))),0) AS `three_day`,ifnull((select count(0) from `thumbs_up` `t` where ((date_format(`t`.`create_time`,'%y-%m-%d') = (curdate() - interval 1 day)) and (`t`.`blog_id` = `tp`.`blog_id`))),0) AS `two_day`,ifnull((select count(0) from `thumbs_up` `t` where ((date_format(`t`.`create_time`,'%y-%m-%d') > (curdate() - interval 1 day)) and (`t`.`blog_id` = `tp`.`blog_id`))),0) AS `one_day` from (`thumbs_up` `tp` join `blog` `b`) where (`b`.`blog_id` = `tp`.`blog_id`) group by `tp`.`blog_id` */;

/*View structure for view views_statistics */

/*!50001 DROP TABLE IF EXISTS `views_statistics` */;
/*!50001 DROP VIEW IF EXISTS `views_statistics` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `views_statistics` AS select `vp`.`blog_id` AS `blog_id`,`b`.`title` AS `title`,`b`.`uid` AS `uid`,ifnull((select count(0) from `views` `v` where ((date_format(`v`.`create_time`,'%y-%m-%d') = (curdate() - interval 6 day)) and (`v`.`blog_id` = `vp`.`blog_id`))),0) AS `seven_day`,ifnull((select count(0) from `views` `v` where ((date_format(`v`.`create_time`,'%y-%m-%d') = (curdate() - interval 5 day)) and (`v`.`blog_id` = `vp`.`blog_id`))),0) AS `six_day`,ifnull((select count(0) from `views` `v` where ((date_format(`v`.`create_time`,'%y-%m-%d') = (curdate() - interval 4 day)) and (`v`.`blog_id` = `vp`.`blog_id`))),0) AS `five_day`,ifnull((select count(0) from `views` `v` where ((date_format(`v`.`create_time`,'%y-%m-%d') = (curdate() - interval 3 day)) and (`v`.`blog_id` = `vp`.`blog_id`))),0) AS `four_day`,ifnull((select count(0) from `views` `v` where ((date_format(`v`.`create_time`,'%y-%m-%d') = (curdate() - interval 2 day)) and (`v`.`blog_id` = `vp`.`blog_id`))),0) AS `three_day`,ifnull((select count(0) from `views` `v` where ((date_format(`v`.`create_time`,'%y-%m-%d') = (curdate() - interval 1 day)) and (`v`.`blog_id` = `vp`.`blog_id`))),0) AS `two_day`,ifnull((select count(0) from `views` `v` where ((date_format(`v`.`create_time`,'%y-%m-%d') > (curdate() - interval 1 day)) and (`v`.`blog_id` = `vp`.`blog_id`))),0) AS `one_day` from (`views` `vp` join `blog` `b`) where (`b`.`blog_id` = `vp`.`blog_id`) group by `vp`.`blog_id` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
