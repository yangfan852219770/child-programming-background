/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : child_programming

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-04-19 20:18:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
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

-- ----------------------------
-- Records of class
-- ----------------------------

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='教室';

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES ('1', '2', '100', '2', '10000000000000', '0', '1', '2019-03-25 16:36:14', '1', '2019-03-25 16:51:11');
INSERT INTO `classroom` VALUES ('2', '1', '105', '11', '101000001', '0', '1', '2019-03-25 16:51:24', '1', '2019-03-26 21:39:17');
INSERT INTO `classroom` VALUES ('3', '2', '103', '10', '1010000', '1', '1', '2019-03-26 21:39:27', '1', '2019-03-26 21:39:42');
INSERT INTO `classroom` VALUES ('4', '1', '100', '10', '10000', '1', '1', '2019-03-26 21:44:45', null, null);
INSERT INTO `classroom` VALUES ('5', '6', '100', '12', '12121212', '0', '1', '2019-03-27 10:47:19', '1', '2019-03-27 10:48:16');
INSERT INTO `classroom` VALUES ('6', '6', '100', '12', '1201222', '0', '1', '2019-03-27 10:47:38', '1', '2019-03-27 10:48:19');
INSERT INTO `classroom` VALUES ('7', '7', '101', '20', '123123123', '1', '1', '2019-04-18 16:34:34', null, null);
INSERT INTO `classroom` VALUES ('8', '7', '102', '30', '134564', '1', '1', '2019-04-18 16:35:28', '1', '2019-04-18 18:59:38');
INSERT INTO `classroom` VALUES ('9', '6', '101', '20', '12312312312', '1', '1', '2019-04-18 18:59:53', '1', '2019-04-18 19:00:06');

-- ----------------------------
-- Table structure for collection_work
-- ----------------------------
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

-- ----------------------------
-- Records of collection_work
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='课程';

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'Java', '2', '191064', '11', '20', '1234567890', '123123132', '', '1', '1', '2019-04-18 16:52:18', '1', '2019-04-19 18:24:02');

-- ----------------------------
-- Table structure for experience_course
-- ----------------------------
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

-- ----------------------------
-- Records of experience_course
-- ----------------------------

-- ----------------------------
-- Table structure for grade
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='班级';

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', '7', '1', 'javaA班', '1', '5', '23123123', null, null, '2019-04-22 00:00:00', '2019-04-28 00:00:00', '[{\"day\":[\"日\",\"二\"],\"startHour\":\"16:47:41\",\"endHour\":\"17:47:41\"}]', '1', '1', '2019-04-18 16:36:17', '1', '2019-04-19 18:24:02');
INSERT INTO `grade` VALUES ('2', '7', '1', 'java-B班', '2', '6', '2312312', null, null, '2019-04-22 00:00:00', '2019-04-28 00:00:00', '[{\"day\":[\"一\"],\"startHour\":\"16:47:41\",\"endHour\":\"17:47:41\"},{\"day\":[\"五\"],\"startHour\":\"19:26:58\",\"endHour\":\"20:26:58\"}]', '1', '1', '2019-04-18 16:36:53', '1', '2019-04-19 18:24:02');

-- ----------------------------
-- Table structure for mateial_type
-- ----------------------------
DROP TABLE IF EXISTS `mateial_type`;
CREATE TABLE `mateial_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '类别名称',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资料类别';

-- ----------------------------
-- Records of mateial_type
-- ----------------------------

-- ----------------------------
-- Table structure for material
-- ----------------------------
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

-- ----------------------------
-- Records of material
-- ----------------------------

-- ----------------------------
-- Table structure for material_type
-- ----------------------------
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

-- ----------------------------
-- Records of material_type
-- ----------------------------
INSERT INTO `material_type` VALUES ('1', '文档', '加密', '1', '1', '2019-04-11 18:40:06', null, null);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL COMMENT '菜单Url',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `locale` varchar(255) DEFAULT NULL COMMENT '菜单国际化',
  `exact` tinyint(1) DEFAULT '1' COMMENT '菜单子项确认',
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `authority` varchar(255) DEFAULT 'admin' COMMENT '权限',
  `create_id` int(11) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/dashboard', 'Dashboard', 'dashboard', 'menu.dashboard', '0', '0', 'admin', '1', '2019-04-17 09:46:56', '1', '2019-04-17 09:47:00', '1');
INSERT INTO `menu` VALUES ('2', '/dashboard/workplace', '工作台', null, 'menu.dashboard.workplace', '1', '1', 'admin', '1', '2019-04-17 09:50:14', '1', '2019-04-17 09:50:16', '1');
INSERT INTO `menu` VALUES ('3', '/webAdmin', '门户网站管理', 'table', 'menu.portalManagement', '0', '0', 'admin', '1', '2019-04-17 09:55:17', '1', '2019-04-17 09:55:27', '1');
INSERT INTO `menu` VALUES ('4', '/webAdmin/worksManagement', '作品管理', null, 'menu.portalManagement.worksManagement', '1', '3', 'admin', '1', '2019-04-17 09:55:20', '1', '2019-04-17 09:55:28', '1');
INSERT INTO `menu` VALUES ('5', '/webAdmin/materialManagement', '资料管理', null, 'menu.portalManagement.materialManagement', '0', '3', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('6', '/webAdmin/paymentRecord', '缴费记录管理', null, 'menu.portalManagement.paymentRecord', '1', '3', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('7', '/webAdmin/materialManagement/materialTypeManagement', '资料类别管理', null, 'menu.portalManagement.materialManagement.materialTypeManagement', '1', '5', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('8', '/webAdmin/materialManagement/materialContentManagement', '资料内容管理', null, 'menu.portalManagement.materialManagement.materialContentManagement', '1', '5', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('10', '/educationalAdministration', '教务管理', 'table', 'menu.educationalAdministration', '0', '0', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('11', '/educationalAdministration/schoolManagement', '校区管理', null, 'menu.educationalAdministration.schoolManagement', '1', '10', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('12', '/educationalAdministration/classroomManagement', '教室管理', null, 'menu.educationalAdministration.classroomManagement', '1', '10', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('13', '/educationalAdministration/studentsManagement', '学生信息管理', null, 'menu.educationalAdministration.studentsManagement', '1', '10', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('14', '/educationalAdministration/teacherManagement', '教师信息管理', null, 'menu.educationalAdministration.teacherManagement', '1', '10', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('15', '/educationalAdministration/courseManagement', '课程管理', null, 'menu.educationalAdministration.courseManagement', '0', '10', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('16', '/educationalAdministration/courseManagement/formal', '正式课管理', null, 'menu.educationalAdministration.courseManagement.formalCourseManagement', '1', '15', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('17', '/educationalAdministration/gradeManagement', '班级管理', null, 'menu.educationalAdministration.gradeManagement', '1', '10', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('18', '/educationalAdministration/signManagement', '签到管理', null, 'menu.educationalAdministration.signManagement', '1', '10', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('19', '/account', '个人页', 'user', 'menu.account', '0', '0', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('20', '/account/center', '个人中心', null, 'menu.account.center', '1', '19', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('21', '/account/settings', '个人设置', null, 'menu.account.settings', '1', '19', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('22', '/authorityManagement', '权限管理', 'table', 'menu.authorityManagement', '0', '0', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('23', '/authorityManagement/roleManagement', '角色管理', null, 'menu.authorityManagement.roleManagement', '1', '22', 'admin', '1', null, null, null, '1');
INSERT INTO `menu` VALUES ('24', '/educationalAdministration/courseManagement/experience', '体验课管理', null, 'menu.educationalAdministration.courseManagement.experienceCourseManagement', '1', '15', 'admin', '1', null, null, null, '1');

-- ----------------------------
-- Table structure for payment_record
-- ----------------------------
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

-- ----------------------------
-- Records of payment_record
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
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
  `role_token` varchar(255) DEFAULT NULL COMMENT '菜单json',
  `menu_ids` varchar(255) DEFAULT NULL COMMENT 'menuId集合',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '拥有所有权限', '1', '1', '2019-04-17 14:36:46', '1', '2019-04-17 14:36:53', 'admin', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24');
INSERT INTO `role` VALUES ('2', '教务管理员', '负责教务管理', '1', '1', '2019-04-18 09:57:10', '1', '2019-04-18 22:11:36', 'education', '7');

-- ----------------------------
-- Table structure for school
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='校区';

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('1', '左学彬擦出租车在出租车在出租车在操场', '12阿达ad大阿达阿达的阿达ad阿达', '这个小区真的不错，不是假的不错，是正的不错暂住证的发撒沙发是v是v是是是v是是省市是  是是是是是是是是 是是是是  是s', '121', '15833655291', '0', '1', '2019-03-25 15:06:47', '1', '2019-03-26 21:45:12');
INSERT INTO `school` VALUES ('2', '石家庄南校区', '河北省石家庄市雨花区珠江大街266号', '这个小区凉席五年堵管', '张戴鹏', '15833655291', '0', '1', '2019-03-25 16:32:41', '1', '2019-03-26 21:44:18');
INSERT INTO `school` VALUES ('3', '左学彬2', '102', '10000002', '102', '102', '0', '1', '2019-03-26 21:42:39', '1', '2019-03-26 21:44:09');
INSERT INTO `school` VALUES ('4', '青少年技能培训1', '1001', '1000001', '101', '101', '0', '1', '2019-03-26 21:44:58', '1', '2019-03-27 10:46:52');
INSERT INTO `school` VALUES ('5', '左学彬', '121', '12121212', '121', '12', '0', '1', '2019-03-27 10:46:44', '1', '2019-03-27 10:47:07');
INSERT INTO `school` VALUES ('6', '121', '12', '1212212', '21', '12', '1', '1', '2019-03-27 10:47:01', null, null);
INSERT INTO `school` VALUES ('7', '石家庄学院', '裕华区', '2132312', '张三', '123456789', '1', '1', '2019-04-18 16:34:17', null, null);

-- ----------------------------
-- Table structure for share_circle
-- ----------------------------
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

-- ----------------------------
-- Records of share_circle
-- ----------------------------

-- ----------------------------
-- Table structure for sign_up_experience_course
-- ----------------------------
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
  `is_payment` tinyint(4) DEFAULT NULL COMMENT '是否付费',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报名体验课';

-- ----------------------------
-- Records of sign_up_experience_course
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
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

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '1', '1', '1', '2', '1', '1', '1', '1', '1', '1', '0', '1', '2019-03-21 11:12:30', '1', '2019-03-28 11:28:41');
INSERT INTO `student` VALUES ('2', '李四', '15833655555', '张三', '1', '10', '河北石家庄', '545226478@qq.com', 'student/2019-03-28/1e5a24ca-6117-4251-9cca-cf04cfc47e6c.jpg', null, null, '1', '1', '2019-03-28 11:18:43', '1', '2019-03-28 21:10:47');
INSERT INTO `student` VALUES ('3', '1', '1', '左学彬', '1', '10', '1', '1', 'student/2019-03-28/66950f8a-b926-4f08-bcb5-e89989cc9aeb.jpg', null, null, '1', '1', '2019-03-28 21:18:49', '1', '2019-03-28 21:28:09');

-- ----------------------------
-- Table structure for student_course_schedule
-- ----------------------------
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

-- ----------------------------
-- Records of student_course_schedule
-- ----------------------------

-- ----------------------------
-- Table structure for student_sign_up
-- ----------------------------
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

-- ----------------------------
-- Records of student_sign_up
-- ----------------------------

-- ----------------------------
-- Table structure for student_work
-- ----------------------------
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
  `status` tinyint(4) DEFAULT NULL COMMENT '0 删除 1正常 2推送',
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='学生作品';

-- ----------------------------
-- Records of student_work
-- ----------------------------
INSERT INTO `student_work` VALUES ('7', null, null, 'scratch/2019-04-07/f70a3e72-1f42-4e29-9f69-606c553fe706.sb3', '1', '这个作品不错', '1', null, null, '1', '1', '2019-04-16 09:27:54', '1', '2019-04-16 10:39:14');
INSERT INTO `student_work` VALUES ('8', null, null, 'scratch/27.sb3', '1', '这个作品不错', '1', null, null, '1', '1', '2019-04-16 09:27:56', '1', '2019-04-16 11:43:45');
INSERT INTO `student_work` VALUES ('9', null, null, 'scratch/2019-04-07/f2bbf353-c9ee-4090-a844-ed62f7a0d1f7.sb3', '1', '这个作品不错', '1', null, null, '1', '1', '2019-05-02 09:27:58', '1', '2019-04-16 10:36:28');
INSERT INTO `student_work` VALUES ('10', null, '1', 'scratch/2019-04-07/f2bbf353-c9ee-4090-a844-ed62f7a0d1f7.sb3', '1', '这个作品不错', '1', null, null, '1', '1', '2019-04-16 09:28:01', '1', '2019-04-16 11:43:42');
INSERT INTO `student_work` VALUES ('11', null, null, null, null, null, null, null, null, null, '1', null, null, null);

-- ----------------------------
-- Table structure for suggestion
-- ----------------------------
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

-- ----------------------------
-- Records of suggestion
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
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
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='老师';

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '吴翰清', '15833655291', 'teacher/2019-04-04/b5a82c98-7957-42a5-982a-692c993f0678.jpg', '网络安全工程师', null, '1', '1', '2019-04-04 17:54:10', null, null, '1');
INSERT INTO `teacher` VALUES ('2', 'admin2', 'e10adc3949ba59abbe56e057f20f883e', '张喆翰', '15833655291', 'teacher/2019-04-04/24f59257-1413-4fd4-a6b1-47c5881b81cd.jpg', '未来战士工程师', null, '1', '1', '2019-04-04 17:55:27', null, null, null);
INSERT INTO `teacher` VALUES ('5', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `teacher` VALUES ('6', 'admin3', 'e10adc3949ba59abbe56e057f20f883e', '左学彬', '15833655291', 'teacher/2019-04-17/fca018aa-80a8-4b73-8ef4-c8329472aa4c.png', '的士速递收到', null, '0', '1', '2019-04-17 16:30:04', '1', '2019-04-17 16:33:26', null);
INSERT INTO `teacher` VALUES ('7', 'admin4', 'e10adc3949ba59abbe56e057f20f883e', '左学彬2', '15833655291', 'teacher/2019-04-17/6a80c32f-a74c-45a6-9318-f2f3b6170a76.png', '收到收到收到但是', null, '1', '1', '2019-04-17 16:32:55', '1', '2019-04-19 18:42:29', '1');

-- ----------------------------
-- Table structure for teacher_course_schedule
-- ----------------------------
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

-- ----------------------------
-- Records of teacher_course_schedule
-- ----------------------------
