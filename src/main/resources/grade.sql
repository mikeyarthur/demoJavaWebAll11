/*
Navicat MySQL Data Transfer

Source Server         : mysql8
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : yhp

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2020-12-10 16:13:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `gradeid` int NOT NULL AUTO_INCREMENT,
  `gradename` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`gradeid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', '一年级');
INSERT INTO `grade` VALUES ('2', '二年级');
INSERT INTO `grade` VALUES ('3', '三年级');
INSERT INTO `grade` VALUES ('4', '四年级');
INSERT INTO `grade` VALUES ('5', '五年级');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuid` int NOT NULL AUTO_INCREMENT,
  `menuname` varchar(255) DEFAULT NULL,
  `upmenuid` int DEFAULT NULL,
  `state` int DEFAULT NULL,
  `desc` varchar(50) DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '教务中心', '0', '1', null, null);
INSERT INTO `menu` VALUES ('2', '权限管理', '0', '1', null, null);
INSERT INTO `menu` VALUES ('3', '学生管理', '1', '1', null, '/Educational/student/studentServlet');
INSERT INTO `menu` VALUES ('4', '用户管理', '2', '1', null, '/power/user/users?method=select');
INSERT INTO `menu` VALUES ('5', '角色管理', '2', '1', null, '/power/role/roles?method=select');
INSERT INTO `menu` VALUES ('6', '菜单管理', '2', '1', null, '#');

-- ----------------------------
-- Table structure for `middle`
-- ----------------------------
DROP TABLE IF EXISTS `middle`;
CREATE TABLE `middle` (
  `middleid` int NOT NULL AUTO_INCREMENT,
  `roleid` int DEFAULT NULL,
  `menuid` int DEFAULT NULL,
  PRIMARY KEY (`middleid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of middle
-- ----------------------------
INSERT INTO `middle` VALUES ('1', '1', '1');
INSERT INTO `middle` VALUES ('2', '1', '2');
INSERT INTO `middle` VALUES ('3', '1', '3');
INSERT INTO `middle` VALUES ('4', '1', '4');
INSERT INTO `middle` VALUES ('5', '1', '5');
INSERT INTO `middle` VALUES ('6', '1', '6');
INSERT INTO `middle` VALUES ('7', '2', '1');
INSERT INTO `middle` VALUES ('8', '2', '3');
INSERT INTO `middle` VALUES ('13', '14', '1');
INSERT INTO `middle` VALUES ('14', '14', '3');
INSERT INTO `middle` VALUES ('15', '14', '2');
INSERT INTO `middle` VALUES ('16', '14', '4');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` int NOT NULL AUTO_INCREMENT,
  `rolename` varchar(10) DEFAULT NULL,
  `rolestate` int DEFAULT NULL COMMENT '0 禁用 1 启用',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '1');
INSERT INTO `role` VALUES ('2', '班主任', '1');
INSERT INTO `role` VALUES ('3', '学生', '1');
INSERT INTO `role` VALUES ('4', '老师', '0');
INSERT INTO `role` VALUES ('13', '管理员1', '1');
INSERT INTO `role` VALUES ('14', '管理员2', '1');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stuid` int NOT NULL AUTO_INCREMENT,
  `stuname` varchar(5) DEFAULT NULL,
  `stuno` varchar(10) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(15) DEFAULT NULL,
  `registered` varchar(10) DEFAULT NULL,
  `address` varchar(10) DEFAULT NULL,
  `profession` varchar(10) DEFAULT NULL,
  `idnumber` varchar(20) DEFAULT NULL,
  `politics` varchar(10) DEFAULT NULL,
  `regdate` date DEFAULT NULL,
  `state` int DEFAULT NULL,
  `introduction` varchar(50) DEFAULT NULL,
  `gid` int DEFAULT NULL,
  PRIMARY KEY (`stuid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '张三1', 'stu1001', '0', '13719203921', '1233253@qq.com', '北京', '昌平区XXX镇', '物理', '11091211029121212', '党员', '2020-06-12', '4', '优秀学员1', '1');
INSERT INTO `student` VALUES ('2', '李四', 'stu1002', '1', '13719203421', '121253@qq.com', '上海', 'XXX区', '化学', '45691211029121212', '党员', '2020-05-03', '4', '优秀学员2', '1');
INSERT INTO `student` VALUES ('3', '王五22', 'stu1003', '1', '13715203921', '2321253@qq.com', '杭州', 'XXX区', '生物', '32291211029121212', '党员', '2020-04-04', '1', '优秀学员3', '2');
INSERT INTO `student` VALUES ('4', '赵六', 'stu1004', '1', '13716203921', '35463253@qq.com', '四川', 'XXX区', '英语', '33391211029121212', '群众', '2020-03-10', '1', '优秀学员4', '2');
INSERT INTO `student` VALUES ('5', '李磊', 'stu1005', '0', '18719203921', '65765553@qq.com', '湖南', 'XXX区', '医学', '222291211029121212', '群众', '2020-02-18', '1', '优秀学员5', '3');
INSERT INTO `student` VALUES ('6', '韩梅梅', 'stu1006', '0', '13719673921', '54654653@qq.com', '武汉', 'XXX区', '政治', '242291211029121212', '群众', '2020-01-17', '4', '优秀学员6', '3');
INSERT INTO `student` VALUES ('7', '斯蒂文', 'stu1007', '0', '13743203921', '43532253@qq.com', '天津', 'XXX区', '计算机', '22091211029121212', '群众', '2020-07-18', '4', '优秀学员7', '3');
INSERT INTO `student` VALUES ('11', '杨11', 's1010', '1', '13333333333', '1332@126.com', '北京', '朝阳', 'java', '11011111', '党员', '2020-11-29', '1', '一个新开辟领域的探讨，探讨摸索', '1');
INSERT INTO `student` VALUES ('12', '杨XX', 's1020', '1', '13333333333', '1332@126.com', '北京', '朝阳', 'java', '11011111111', '党员', '2020-11-30', '1', '一个新开辟领域的探讨，探讨摸索', '1');
INSERT INTO `student` VALUES ('13', '杨XX', 's1020', '1', '13333333333', '1332@126.com', '北京', '朝阳', 'java', '1101111111', '党员', '2020-11-30', '1', '一个新开辟领域的探讨，探讨摸索', '1');
INSERT INTO `student` VALUES ('14', 'abc', 's1021', '1', '13333333333', '1332@126.com', '北京', '朝阳', 'java', '1101111', '党员', '2020-11-30', '1', '一个新开辟领域的探讨，探讨摸索', '1');
INSERT INTO `student` VALUES ('15', '杨Xa', 's1111', '1', '13333333333', '1332@126.com', '北京', '朝阳', 'java', '11011111111', '党员', '2020-11-30', '1', '一个新开辟领域的探讨，探讨摸索', '1');
INSERT INTO `student` VALUES ('16', '杨aa', 's1111', '1', '13333333333', '1332@126.com', '北京', '朝阳', 'java', '110111111', '党员', '2020-11-30', '1', '一个新开辟领域的探讨，探讨摸索', '1');
INSERT INTO `student` VALUES ('17', '杨q', 's1113', '1', '13333333333', '1332@126.com', '北京1', '朝阳', 'java', '11011111', '党员', '2020-11-30', '4', '一个新开辟领域的探讨，探讨摸索', '1');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `loginname` varchar(10) DEFAULT NULL,
  `password` varchar(12) DEFAULT NULL,
  `realname` varchar(10) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `cardid` varchar(20) DEFAULT NULL,
  `desc` varchar(50) DEFAULT NULL,
  `roleid` int DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', '123456', '张三', '1', null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('2', 'user1', '123456', '李四', '1', null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('3', 'user2', '123456', '王五', '0', null, null, null, null, null, '2');
INSERT INTO `users` VALUES ('4', 'aaa', '111', 'a', null, null, null, null, null, null, '3');
INSERT INTO `users` VALUES ('5', 'bb', '111', 'b', null, null, null, null, null, null, '3');
INSERT INTO `users` VALUES ('6', 'cc', '111', 'c', null, null, null, null, null, null, '3');
