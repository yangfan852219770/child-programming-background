/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : child_programming

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2019-03-12 20:31:41
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教室';

-- ----------------------------
-- Records of classroom
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程';

-- ----------------------------
-- Records of course
-- ----------------------------

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资料';

-- ----------------------------
-- Records of material
-- ----------------------------

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='校区';

-- ----------------------------
-- Records of school
-- ----------------------------

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
  `sex` tinyint(4) DEFAULT NULL COMMENT '学生性别',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生';

-- ----------------------------
-- Records of student
-- ----------------------------

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
  `status` tinyint(4) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生作品';

-- ----------------------------
-- Records of student_work
-- ----------------------------

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='老师';

-- ----------------------------
-- Records of teacher
-- ----------------------------

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
