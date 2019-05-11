/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : school

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 12/05/2019 00:14:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (118);

-- ----------------------------
-- Table structure for s_menu
-- ----------------------------
DROP TABLE IF EXISTS `s_menu`;
CREATE TABLE `s_menu`  (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `permission` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK2ki9gmfd5n76g8p7ca0oadhcr`(`menu_id`) USING BTREE,
  CONSTRAINT `FK2ki9gmfd5n76g8p7ca0oadhcr` FOREIGN KEY (`menu_id`) REFERENCES `s_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_menu
-- ----------------------------
INSERT INTO `s_menu` VALUES (100, '系统管理', '', '1', '', NULL);
INSERT INTO `s_menu` VALUES (101, '学校管理', '', '1', '', NULL);
INSERT INTO `s_menu` VALUES (103, '教师信息', '', '1', '', NULL);
INSERT INTO `s_menu` VALUES (104, '学生信息', '', '1', '', NULL);
INSERT INTO `s_menu` VALUES (105, '管理员管理', '', '1', '/user', 100);
INSERT INTO `s_menu` VALUES (106, '角色管理', '', '1', '/role', 100);
INSERT INTO `s_menu` VALUES (107, '菜单管理', '', '1', '/menu', 100);
INSERT INTO `s_menu` VALUES (108, '学年管理', '', '1', '/year', 101);
INSERT INTO `s_menu` VALUES (109, '课程管理', '', '1', '/course', 101);
INSERT INTO `s_menu` VALUES (110, '班级管理', '', '1', '/class', 101);
INSERT INTO `s_menu` VALUES (111, '教师管理', '', '1', '/teacher', 101);
INSERT INTO `s_menu` VALUES (112, '学生管理', '', '1', '/student', 101);
INSERT INTO `s_menu` VALUES (113, '教师改分', '', '1', '/score', 103);
INSERT INTO `s_menu` VALUES (114, '我的课程', '', '1', '/myCourse', 104);
INSERT INTO `s_menu` VALUES (115, '选课', '', '1', '/selectCourse', 104);

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role`  (
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('admin', 'admin role');

-- ----------------------------
-- Table structure for s_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `s_role_menu`;
CREATE TABLE `s_role_menu`  (
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_name`, `menu_id`) USING BTREE,
  INDEX `id`(`menu_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_role_menu
-- ----------------------------
INSERT INTO `s_role_menu` VALUES ('admin', 32);
INSERT INTO `s_role_menu` VALUES ('admin', 33);
INSERT INTO `s_role_menu` VALUES ('admin', 41);

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fail_count` int(11) NULL DEFAULT NULL,
  `last_login_ip` varchar(17) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_login_time` datetime(0) NULL DEFAULT NULL,
  `password` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remember` int(11) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('admin', 0, NULL, NULL, '123456', 0, 'admin');

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`username`, `role_name`) USING BTREE,
  INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('admin', 'admin');

-- ----------------------------
-- Table structure for school_class
-- ----------------------------
DROP TABLE IF EXISTS `school_class`;
CREATE TABLE `school_class`  (
  `id` int(11) NOT NULL,
  `name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `school_year_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_ec4vqaetwr6n8b48hp14t8e52`(`name`) USING BTREE,
  INDEX `FKpex21eh7ky7mnf1trfguw9ju3`(`school_year_id`) USING BTREE,
  CONSTRAINT `FKpex21eh7ky7mnf1trfguw9ju3` FOREIGN KEY (`school_year_id`) REFERENCES `school_year` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_class
-- ----------------------------
INSERT INTO `school_class` VALUES (83, '英语001', 3);
INSERT INTO `school_class` VALUES (85, '英语002', 3);

-- ----------------------------
-- Table structure for school_class_course
-- ----------------------------
DROP TABLE IF EXISTS `school_class_course`;
CREATE TABLE `school_class_course`  (
  `class_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`class_id`, `course_id`) USING BTREE,
  INDEX `FKtokerrfbsdjp9smncwa70rqgs`(`course_id`) USING BTREE,
  CONSTRAINT `FKoutxofh16x9sr8fqhepehyh17` FOREIGN KEY (`class_id`) REFERENCES `school_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtokerrfbsdjp9smncwa70rqgs` FOREIGN KEY (`course_id`) REFERENCES `school_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_class_course
-- ----------------------------
INSERT INTO `school_class_course` VALUES (83, 4);
INSERT INTO `school_class_course` VALUES (85, 4);

-- ----------------------------
-- Table structure for school_class_course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `school_class_course_teacher`;
CREATE TABLE `school_class_course_teacher`  (
  `id` int(11) NOT NULL,
  `school_class_id` int(11) NULL DEFAULT NULL,
  `school_course_id` int(11) NULL DEFAULT NULL,
  `school_teacher_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKsmhmxoyps91lg7k1c4j2rwdi8`(`school_class_id`) USING BTREE,
  INDEX `FKa24vs6mkm97a42im8hl2fe2vd`(`school_course_id`) USING BTREE,
  INDEX `FK3in11uch4x1n28mm882xmpwrv`(`school_teacher_id`) USING BTREE,
  CONSTRAINT `FK3in11uch4x1n28mm882xmpwrv` FOREIGN KEY (`school_teacher_id`) REFERENCES `school_teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKa24vs6mkm97a42im8hl2fe2vd` FOREIGN KEY (`school_course_id`) REFERENCES `school_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKsmhmxoyps91lg7k1c4j2rwdi8` FOREIGN KEY (`school_class_id`) REFERENCES `school_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_class_course_teacher
-- ----------------------------
INSERT INTO `school_class_course_teacher` VALUES (84, 83, 4, 80);
INSERT INTO `school_class_course_teacher` VALUES (86, 85, 4, 81);

-- ----------------------------
-- Table structure for school_class_student
-- ----------------------------
DROP TABLE IF EXISTS `school_class_student`;
CREATE TABLE `school_class_student`  (
  `class_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  PRIMARY KEY (`class_id`, `student_id`) USING BTREE,
  INDEX `FKpg3i1soelwf9old3j4uns8tth`(`student_id`) USING BTREE,
  CONSTRAINT `FK1ypdk5udwbcd2d97luruihj1i` FOREIGN KEY (`class_id`) REFERENCES `school_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKpg3i1soelwf9old3j4uns8tth` FOREIGN KEY (`student_id`) REFERENCES `school_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for school_course
-- ----------------------------
DROP TABLE IF EXISTS `school_course`;
CREATE TABLE `school_course`  (
  `id` int(11) NOT NULL,
  `name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `hour` int(11) NULL DEFAULT NULL,
  `school_year_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKhhd2pg88ndyywjouh4raj4bvw`(`school_year_id`) USING BTREE,
  CONSTRAINT `FKhhd2pg88ndyywjouh4raj4bvw` FOREIGN KEY (`school_year_id`) REFERENCES `school_year` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_course
-- ----------------------------
INSERT INTO `school_course` VALUES (4, '英语', 5, 10, 3);
INSERT INTO `school_course` VALUES (5, '数学1', 5, 10, 3);
INSERT INTO `school_course` VALUES (71, '语文', 5, 10, 3);
INSERT INTO `school_course` VALUES (72, '社会主义', 5, 10, 3);
INSERT INTO `school_course` VALUES (73, '音乐鉴赏', 5, 10, 3);
INSERT INTO `school_course` VALUES (74, '计算机基础', 5, 10, 3);
INSERT INTO `school_course` VALUES (75, '初级日语', 5, 10, 3);
INSERT INTO `school_course` VALUES (76, '初级德语', 5, 10, 3);
INSERT INTO `school_course` VALUES (77, '初级法语', 5, 10, 3);
INSERT INTO `school_course` VALUES (78, '初级韩语', 5, 10, 3);
INSERT INTO `school_course` VALUES (79, '历史解读', 5, 10, 3);

-- ----------------------------
-- Table structure for school_course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `school_course_teacher`;
CREATE TABLE `school_course_teacher`  (
  `course_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`course_id`, `teacher_id`) USING BTREE,
  INDEX `FKb5qob99qxkletholbgh5i2jp9`(`teacher_id`) USING BTREE,
  CONSTRAINT `FKb5qob99qxkletholbgh5i2jp9` FOREIGN KEY (`teacher_id`) REFERENCES `school_teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhgy65gyajw58bknc24v0tiqnu` FOREIGN KEY (`course_id`) REFERENCES `school_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_course_teacher
-- ----------------------------
INSERT INTO `school_course_teacher` VALUES (4, 80);
INSERT INTO `school_course_teacher` VALUES (4, 81);
INSERT INTO `school_course_teacher` VALUES (4, 82);

-- ----------------------------
-- Table structure for school_student
-- ----------------------------
DROP TABLE IF EXISTS `school_student`;
CREATE TABLE `school_student`  (
  `id` int(11) NOT NULL,
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `school_no` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `school_year_id` int(11) NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `school_class_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_iadika79knvg1rb9pu52a7ent`(`id_card`) USING BTREE,
  UNIQUE INDEX `UK_k76mo8s4sxph1dd8jmpn1pyc9`(`school_no`) USING BTREE,
  INDEX `FKj9htsau989bwo32kgy6725l8t`(`school_year_id`) USING BTREE,
  INDEX `FK7fo9datvys3es8vhxhuqryleq`(`school_class_id`) USING BTREE,
  CONSTRAINT `FK7fo9datvys3es8vhxhuqryleq` FOREIGN KEY (`school_class_id`) REFERENCES `school_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKj9htsau989bwo32kgy6725l8t` FOREIGN KEY (`school_year_id`) REFERENCES `school_year` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_student
-- ----------------------------
INSERT INTO `school_student` VALUES (87, '001', '学生1', '001', 3, '0011', 83);
INSERT INTO `school_student` VALUES (89, '002', '学生2', '002', 3, '002', 83);
INSERT INTO `school_student` VALUES (91, '003', '学生3', '003', 3, '003', 83);

-- ----------------------------
-- Table structure for school_student_course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `school_student_course_teacher`;
CREATE TABLE `school_student_course_teacher`  (
  `id` int(11) NOT NULL,
  `school_course_id` int(11) NULL DEFAULT NULL,
  `school_student_id` int(11) NULL DEFAULT NULL,
  `school_teacher_id` int(11) NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKhsm8soqv3n89hc1vmxly8n4q7`(`school_course_id`) USING BTREE,
  INDEX `FK57rlptknyixxaxw3yx1cwrrwl`(`school_student_id`) USING BTREE,
  INDEX `FKnvi5lnatiux5n32irl1k5t3dq`(`school_teacher_id`) USING BTREE,
  CONSTRAINT `FK57rlptknyixxaxw3yx1cwrrwl` FOREIGN KEY (`school_student_id`) REFERENCES `school_student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhsm8soqv3n89hc1vmxly8n4q7` FOREIGN KEY (`school_course_id`) REFERENCES `school_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKnvi5lnatiux5n32irl1k5t3dq` FOREIGN KEY (`school_teacher_id`) REFERENCES `school_teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_student_course_teacher
-- ----------------------------
INSERT INTO `school_student_course_teacher` VALUES (88, 4, 87, 80, 8);
INSERT INTO `school_student_course_teacher` VALUES (90, 4, 89, 80, 4);
INSERT INTO `school_student_course_teacher` VALUES (92, 4, 91, 80, 3);

-- ----------------------------
-- Table structure for school_teacher
-- ----------------------------
DROP TABLE IF EXISTS `school_teacher`;
CREATE TABLE `school_teacher`  (
  `id` int(11) NOT NULL,
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `work_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_obx5xhw60fem492ggfo9n80b1`(`id_card`) USING BTREE,
  UNIQUE INDEX `UK_hvfde5kqj67j4nime8jqto4q`(`work_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_teacher
-- ----------------------------
INSERT INTO `school_teacher` VALUES (80, '123', '张三', '1234', '123');
INSERT INTO `school_teacher` VALUES (81, '111', '李四', '111', '111');
INSERT INTO `school_teacher` VALUES (82, '112', '王五', '112', '112');

-- ----------------------------
-- Table structure for school_year
-- ----------------------------
DROP TABLE IF EXISTS `school_year`;
CREATE TABLE `school_year`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_5o9484o8imfrlh5lomcsxlkb1`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_year
-- ----------------------------
INSERT INTO `school_year` VALUES (42, '2010');
INSERT INTO `school_year` VALUES (3, '2019');

SET FOREIGN_KEY_CHECKS = 1;
