/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.43 : Database - child_programming
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`child_programming` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `child_programming`;

/*Table structure for table `class` */

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classroom_id` int(11) DEFAULT NULL COMMENT '教室id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `name` varchar(255) DEFAULT NULL COMMENT '班级名称',
  `teacher_id` int(11) DEFAULT NULL COMMENT '老师id，只允许一个',
  `max_capacity` int(11) DEFAULT NULL COMMENT '最大容量',
  `description` varchar(255) DEFAULT NULL COMMENT '班级描述',
  `current_period` int(11) DEFAULT NULL COMMENT '当前所上课时#',
  `period_history` varchar(255) DEFAULT NULL COMMENT '所上课时历史，以逗号分隔',
  `start_date` datetime DEFAULT NULL COMMENT '占用开始时间，包括日月年，必须以周一日期开始',
  `end_date` datetime DEFAULT NULL COMMENT '占用结束时间，包括日月年，必须以周日日期结束',
  `weekends_schedule` varchar(255) DEFAULT NULL COMMENT ' 一个星期内的时间安排，json格式字符串，属性为day, start_hour, end_hour',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级';

/*Data for the table `class` */

/*Table structure for table `classroom` */

DROP TABLE IF EXISTS `classroom`;

CREATE TABLE `classroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_id` int(11) DEFAULT NULL COMMENT '校区id',
  `code` int(11) DEFAULT NULL COMMENT '教室编号',
  `max_capacity` int(11) DEFAULT NULL COMMENT '最大容量',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='教室';

/*Data for the table `classroom` */

insert  into `classroom`(`id`,`school_id`,`code`,`max_capacity`,`comment`,`status`,`create_id`,`create_time`,`last_update_id`,`last_update_time`) values (1,2,100,2,'10000000000000',0,1,'2019-03-25 16:36:14',1,'2019-03-25 16:51:11'),(2,1,105,11,'101000001',0,1,'2019-03-25 16:51:24',1,'2019-03-26 21:39:17'),(3,2,103,10,'1010000',1,1,'2019-03-26 21:39:27',1,'2019-03-26 21:39:42'),(4,1,100,10,'10000',1,1,'2019-03-26 21:44:45',NULL,NULL),(5,6,100,12,'12121212',0,1,'2019-03-27 10:47:19',1,'2019-03-27 10:48:16'),(6,6,100,12,'1201222',0,1,'2019-03-27 10:47:38',1,'2019-03-27 10:48:19');

/*Table structure for table `collection_work` */

DROP TABLE IF EXISTS `collection_work`;

CREATE TABLE `collection_work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `student_work_id` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏作品';

/*Data for the table `collection_work` */

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '课程名称',
  `period_count` int(11) DEFAULT NULL COMMENT '课时数量',
  `code` varchar(255) DEFAULT NULL COMMENT '课程编码',
  `max_capacity` int(11) DEFAULT NULL COMMENT '最大人数容量',
  `money` double DEFAULT NULL COMMENT '价格',
  `telephone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `photo_url` varchar(255) DEFAULT NULL COMMENT '图片描述地址',
  `status` int(11) DEFAULT NULL COMMENT '状态 0停用，1报名，2开课，3结课',
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程';

/*Data for the table `course` */

/*Table structure for table `experience_course` */

DROP TABLE IF EXISTS `experience_course`;

CREATE TABLE `experience_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `money` double DEFAULT NULL COMMENT '价格#',
  `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `photo_url` varchar(255) DEFAULT NULL COMMENT '图片描述地址',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `sign_up_end_date` datetime DEFAULT NULL COMMENT '报名截止时间',
  `telephone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `discount_strategy` varchar(255) DEFAULT NULL COMMENT '打折策略',
  `status` varchar(255) DEFAULT NULL COMMENT '状态 0停用，1报名，2开课，3结课',
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='体验课';

/*Data for the table `experience_course` */

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classroom_id` int(11) DEFAULT NULL COMMENT '教室id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `name` varchar(255) DEFAULT NULL COMMENT '班级名称',
  `teacher_id` int(11) DEFAULT NULL COMMENT '老师id，只允许一个',
  `max_capacity` int(11) DEFAULT NULL COMMENT '最大容量',
  `description` varchar(255) DEFAULT NULL COMMENT '班级描述',
  `current_period` int(11) DEFAULT NULL COMMENT '当前所上课时#',
  `period_history` varchar(255) DEFAULT NULL COMMENT '所上课时历史，以逗号分隔',
  `start_date` datetime DEFAULT NULL COMMENT '占用开始时间，包括日月年，必须以周一日期开始',
  `end_date` datetime DEFAULT NULL COMMENT '占用结束时间，包括日月年，必须以周日日期结束',
  `weekends_schedule` varchar(255) DEFAULT NULL COMMENT ' 一个星期内的时间安排，json格式字符串，属性为day, start_hour, end_hour',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级';

/*Data for the table `grade` */

/*Table structure for table `material` */

DROP TABLE IF EXISTS `material`;

CREATE TABLE `material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_type_id` int(11) DEFAULT NULL COMMENT '资料类别id',
  `type` varchar(255) DEFAULT NULL COMMENT '资料类别，如文档、视频等',
  `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `file_url` varchar(255) DEFAULT NULL COMMENT '资料地址',
  `download_number` int(11) DEFAULT NULL COMMENT '下载次数',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `origin_name` varchar(255) DEFAULT NULL COMMENT '资料原始名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资料';

/*Data for the table `material` */

/*Table structure for table `material_type` */

DROP TABLE IF EXISTS `material_type`;

CREATE TABLE `material_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '类别名称',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='资料类别';

/*Data for the table `material_type` */

insert  into `material_type`(`id`,`name`,`comment`,`status`,`create_id`,`create_time`,`last_update_id`,`last_update_time`) values (1,'文档','加密',1,1,'2019-04-11 18:40:06',NULL,NULL);

/*Table structure for table `payment_record` */

DROP TABLE IF EXISTS `payment_record`;

CREATE TABLE `payment_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `purchase_period` int(11) DEFAULT NULL COMMENT '购买课时数量',
  `remain_period` int(11) DEFAULT NULL COMMENT '剩余课时',
  `pay_money` double DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL COMMENT '付费时间',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='付费记录';

/*Data for the table `payment_record` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `role` */

/*Table structure for table `school` */

DROP TABLE IF EXISTS `school`;

CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '校园名称',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `charge_user_name` varchar(255) DEFAULT NULL COMMENT '负责人姓名',
  `charge_user_phone` varchar(255) DEFAULT NULL COMMENT '负责人电话',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='校区';

/*Data for the table `school` */

insert  into `school`(`id`,`name`,`address`,`introduction`,`charge_user_name`,`charge_user_phone`,`status`,`create_id`,`create_time`,`last_update_id`,`last_update_time`) values (1,'左学彬擦出租车在出租车在出租车在操场','12阿达ad大阿达阿达的阿达ad阿达','这个小区真的不错，不是假的不错，是正的不错暂住证的发撒沙发是v是v是是是v是是省市是  是是是是是是是是 是是是是  是s','121','15833655291',0,1,'2019-03-25 15:06:47',1,'2019-03-26 21:45:12'),(2,'石家庄南校区','河北省石家庄市雨花区珠江大街266号','这个小区凉席五年堵管','张戴鹏','15833655291',0,1,'2019-03-25 16:32:41',1,'2019-03-26 21:44:18'),(3,'左学彬2','102','10000002','102','102',0,1,'2019-03-26 21:42:39',1,'2019-03-26 21:44:09'),(4,'青少年技能培训1','1001','1000001','101','101',0,1,'2019-03-26 21:44:58',1,'2019-03-27 10:46:52'),(5,'左学彬','121','12121212','121','12',0,1,'2019-03-27 10:46:44',1,'2019-03-27 10:47:07'),(6,'121','12','1212212','21','12',1,1,'2019-03-27 10:47:01',NULL,NULL);

/*Table structure for table `share_circle` */

DROP TABLE IF EXISTS `share_circle`;

CREATE TABLE `share_circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `eclusive_code` varchar(255) DEFAULT NULL COMMENT '专属码',
  `share_counts` int(11) DEFAULT NULL COMMENT '分享次数',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `share_circle` */

/*Table structure for table `sign_up_experience_course` */

DROP TABLE IF EXISTS `sign_up_experience_course`;

CREATE TABLE `sign_up_experience_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `experience_course_id` int(11) DEFAULT NULL COMMENT '体验课id',
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `sign_up_time` datetime DEFAULT NULL COMMENT '报名时间',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报名体验课';

/*Data for the table `sign_up_experience_course` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guardian_name` varchar(255) DEFAULT NULL COMMENT '监护人姓名',
  `guardian_phone` varchar(255) DEFAULT NULL COMMENT '监护人电话',
  `name` varchar(255) DEFAULT NULL COMMENT '学生姓名',
  `sex` tinyint(4) DEFAULT NULL COMMENT '学生性别 1男 2女',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `photo_url` varchar(255) DEFAULT NULL COMMENT '照片',
  `openid` varchar(255) DEFAULT NULL COMMENT '微信标识',
  `accent_token` varchar(255) DEFAULT NULL COMMENT '微信凭证',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='学生';

/*Data for the table `student` */

insert  into `student`(`id`,`guardian_name`,`guardian_phone`,`name`,`sex`,`age`,`address`,`email`,`photo_url`,`openid`,`accent_token`,`status`,`create_id`,`create_time`,`last_update_id`,`last_update_time`) values (1,'1','1','1',2,1,'1','1','1','1','1',0,1,'2019-03-21 11:12:30',1,'2019-03-28 11:28:41'),(2,'李四','15833655555','张三',1,10,'河北石家庄','545226478@qq.com','student/2019-03-28/1e5a24ca-6117-4251-9cca-cf04cfc47e6c.jpg',NULL,NULL,1,1,'2019-03-28 11:18:43',1,'2019-03-28 21:10:47'),(3,'1','1','左学彬',1,10,'1','1','student/2019-03-28/66950f8a-b926-4f08-bcb5-e89989cc9aeb.jpg',NULL,NULL,1,1,'2019-03-28 21:18:49',1,'2019-03-28 21:28:09');

/*Table structure for table `student_course_schedule` */

DROP TABLE IF EXISTS `student_course_schedule`;

CREATE TABLE `student_course_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `temp_class_id` int(11) DEFAULT NULL COMMENT '临时调课班级id',
  `start_time` datetime DEFAULT NULL COMMENT '上课开始时间，年月日，时分',
  `end_time` datetime DEFAULT NULL COMMENT '上课结束时间，年月日，时分',
  `period` int(11) DEFAULT NULL COMMENT '对应课时',
  `is_sign_in` tinyint(4) DEFAULT NULL COMMENT '是否签到',
  `is_leave` tinyint(4) DEFAULT NULL COMMENT '是否请假',
  `is_adjust` tinyint(4) DEFAULT NULL COMMENT '是否调课',
  `is_evaluate` tinyint(4) DEFAULT NULL COMMENT '是否评价',
  `is_retroactive` tinyint(4) DEFAULT NULL COMMENT '是否为补签',
  `is_suspend` tinyint(4) DEFAULT NULL COMMENT '是否停课',
  `is_add_class` tinyint(4) DEFAULT NULL COMMENT '是否为加课',
  `date_for_absence` datetime DEFAULT NULL COMMENT '请假时间',
  `change_class_time` datetime DEFAULT NULL COMMENT '调课时间',
  `evaluation_time` datetime DEFAULT NULL COMMENT '评价时间',
  `sign_in_time` datetime DEFAULT NULL COMMENT '签到时间',
  `evaluate_text` varchar(255) DEFAULT NULL COMMENT '评价文字',
  `evaluate_voice_url` varchar(255) DEFAULT NULL COMMENT '评价语音',
  `evaluate_video_url` varchar(255) DEFAULT NULL COMMENT '评价视频',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生课程表';

/*Data for the table `student_course_schedule` */

/*Table structure for table `student_sign_up` */

DROP TABLE IF EXISTS `student_sign_up`;

CREATE TABLE `student_sign_up` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `sign_up_time` datetime DEFAULT NULL COMMENT '报名时间',
  `is_payment` tinyint(4) DEFAULT NULL COMMENT '是否缴费',
  `is_halfway` tinyint(4) DEFAULT NULL COMMENT '是否中途报名',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生正式课报名情况';

/*Data for the table `student_sign_up` */

/*Table structure for table `student_work` */

DROP TABLE IF EXISTS `student_work`;

CREATE TABLE `student_work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `teacher_id` int(11) DEFAULT NULL COMMENT '老师id',
  `work_url` varchar(255) DEFAULT NULL COMMENT '作品url',
  `work_name` varchar(255) DEFAULT NULL COMMENT '作品名称',
  `description` varchar(255) DEFAULT NULL COMMENT '作品描述',
  `page_view` varchar(255) DEFAULT NULL COMMENT '阅读次数',
  `collection_number` int(11) DEFAULT NULL COMMENT '收藏数量',
  `like_count` varchar(255) DEFAULT NULL COMMENT '点赞数量',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='学生作品';

/*Data for the table `student_work` */

insert  into `student_work`(`id`,`student_id`,`teacher_id`,`work_url`,`work_name`,`description`,`page_view`,`collection_number`,`like_count`,`status`,`create_id`,`create_time`,`last_update_id`,`last_update_time`) values (7,NULL,NULL,'scratch/2019-04-07/f70a3e72-1f42-4e29-9f69-606c553fe706.sb3',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(8,NULL,NULL,'scratch/2019-04-07/f70a3e72-1f42-4e29-9f69-606c553fe706.sb3',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(9,NULL,NULL,'scratch/2019-04-07/f2bbf353-c9ee-4090-a844-ed62f7a0d1f7.sb3',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(10,NULL,NULL,'scratch/2019-04-07/f2bbf353-c9ee-4090-a844-ed62f7a0d1f7.sb3',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL);

/*Table structure for table `suggestion` */

DROP TABLE IF EXISTS `suggestion`;

CREATE TABLE `suggestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_text` varchar(255) DEFAULT NULL COMMENT '文本',
  `voice_url` varchar(255) DEFAULT NULL COMMENT '语音',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见反馈';

/*Data for the table `suggestion` */

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(255) DEFAULT NULL COMMENT '登陆账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `photo_url` varchar(255) DEFAULT NULL COMMENT '照片',
  `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `certificate` varchar(255) DEFAULT NULL COMMENT '证书',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='老师';

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`login_id`,`password`,`name`,`phone`,`photo_url`,`introduction`,`certificate`,`status`,`create_id`,`create_time`,`last_update_id`,`last_update_time`) values (1,'10','e10adc3949ba59abbe56e057f20f883e','10','10','teacher/2019-03-28/b7591e0d-1c07-4975-a908-c842eb9de1d5.jpg','100000',NULL,0,1,'2019-03-28 21:10:16',1,'2019-04-04 17:51:44'),(2,'1212','e10adc3949ba59abbe56e057f20f883e','12','12','teacher/2019-03-28/ca58f2aa-71eb-4e6b-9655-51d5e1117b57.jpg','121121221',NULL,0,1,'2019-03-28 21:19:29',1,'2019-04-04 17:51:46'),(3,'admin','e10adc3949ba59abbe56e057f20f883e','吴翰清','15833655291','teacher/2019-04-04/b5a82c98-7957-42a5-982a-692c993f0678.jpg','网络安全工程师',NULL,1,1,'2019-04-04 17:54:10',NULL,NULL),(4,'admin2','e10adc3949ba59abbe56e057f20f883e','张喆翰','15833655291','teacher/2019-04-04/24f59257-1413-4fd4-a6b1-47c5881b81cd.jpg','未来战士工程师',NULL,1,1,'2019-04-04 17:55:27',NULL,NULL);

/*Table structure for table `teacher_course_schedule` */

DROP TABLE IF EXISTS `teacher_course_schedule`;

CREATE TABLE `teacher_course_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `start_time` datetime DEFAULT NULL COMMENT '上课开始时间，年月日，时分',
  `period` varchar(255) DEFAULT NULL COMMENT '对应课时',
  `end_time` datetime DEFAULT NULL COMMENT '上课结束时间，年月日，时分',
  `is_sign_in` tinyint(4) DEFAULT NULL COMMENT '是否签到',
  `is_suspend` tinyint(4) DEFAULT NULL COMMENT '是否停课',
  `is_add_class` tinyint(4) DEFAULT NULL COMMENT '是否为加课',
  `sign_in_time` datetime DEFAULT NULL COMMENT '签到时间',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='老师课程表';

/*Data for the table `teacher_course_schedule` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
