/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.111_3306
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 192.168.0.111:3306
 Source Schema         : d_shiro

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 01/11/2020 21:28:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_perm`;
CREATE TABLE `sys_perm`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_` int(0) NULL DEFAULT NULL COMMENT '0-module;1-menu;2-button',
  `rule_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限规则，如：user:save:*，user:*',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_perm
-- ----------------------------
INSERT INTO `sys_perm` VALUES (1, '超级管理员', NULL, NULL, '*');
INSERT INTO `sys_perm` VALUES (2, '用户管理', '/user**', NULL, 'user:*');
INSERT INTO `sys_perm` VALUES (3, '部门管理', '/dept**', NULL, 'dept:*');
INSERT INTO `sys_perm` VALUES (4, '角色管理', '/role**', NULL, 'role:*');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key_` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'superAdmin');
INSERT INTO `sys_role` VALUES (2, '系统管理员', 'admin');
INSERT INTO `sys_role` VALUES (3, '用户管理员', 'userAdmin');
INSERT INTO `sys_role` VALUES (4, '角色管理员', 'roleAdmin');
INSERT INTO `sys_role` VALUES (5, '权限管理员', 'permAdmin');
INSERT INTO `sys_role` VALUES (6, '部门管理员', 'deptAdmin');
INSERT INTO `sys_role` VALUES (7, '访客', 'guest');

-- ----------------------------
-- Table structure for sys_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_perm`;
CREATE TABLE `sys_role_perm`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NULL DEFAULT NULL,
  `perm_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_perm
-- ----------------------------
INSERT INTO `sys_role_perm` VALUES (1, 1, 1);
INSERT INTO `sys_role_perm` VALUES (2, 2, 2);
INSERT INTO `sys_role_perm` VALUES (3, 2, 3);
INSERT INTO `sys_role_perm` VALUES (4, 2, 4);
INSERT INTO `sys_role_perm` VALUES (5, 3, 2);
INSERT INTO `sys_role_perm` VALUES (6, 4, 4);
INSERT INTO `sys_role_perm` VALUES (7, 6, 3);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username_cn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `salt` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (7, 'ekozhan', 'a3bbf11fbfa7dfd26612e2d01f810123', '展昭', NULL, NULL, NULL, NULL, NULL, NULL, '8b32');
INSERT INTO `sys_user` VALUES (11, 'ekoz', '785b5556e9a02d2e1481467b0c2cbd57', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '9f4a');
INSERT INTO `sys_user` VALUES (12, '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (13, '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 7, 1);

SET FOREIGN_KEY_CHECKS = 1;
