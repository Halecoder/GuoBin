/*
 Navicat Premium Data Transfer

 Source Server         : HL
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : guobin2023

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 22/06/2023 15:17:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `addressName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lng` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES (1, '深圳市世界之窗', '113.97939921156106', '22.540746033109636');
INSERT INTO `t_address` VALUES (2, '深圳市宝安区深圳宝安国际机场-T3航站楼', '113.819598', '22.631062');
INSERT INTO `t_address` VALUES (3, '深圳市北站', '114.03552911268908', '22.615107645742687');
INSERT INTO `t_address` VALUES (4, '深圳市光明区政府', '113.94256006193123', '22.754465883635367');
INSERT INTO `t_address` VALUES (5, '深圳市莲花山公园', '114.06519268773812', '22.559256676825059');
INSERT INTO `t_address` VALUES (6, '深圳市宝安区羊台山森林公园', '113.97127', '22.657618');
INSERT INTO `t_address` VALUES (7, '深圳市宝安区宝安公园', '113.909263', '22.592267');
INSERT INTO `t_address` VALUES (8, '深圳市盐田区大梅沙海滨公园', '114.314011', '22.598196');
INSERT INTO `t_address` VALUES (10, '株洲市天元区湖南工业大学新校区', '113.11414864971958', '27.82386622437292');

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `fileNumber` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idCard` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `regTime` date NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES (1, NULL, '小明', '1', '522601198106230053', '18544278888', '2023-02-08', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (2, NULL, '美丽', '2', '63260119920123005X', '13412345678', '2023-02-11', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (3, NULL, '小星星', '2', '422601199010280033', '18343569999', '2023-02-13', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (4, NULL, '小强', '1', '532601198911230630', '18343569988', '2023-02-26', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (5, NULL, 'HL', '1', '522401200208290157', '18485730290', '2023-06-13', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (6, NULL, '赵燕', '0', '32412453412321345X', '13412345678', '2023-04-11', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (7, NULL, '小强', '1', '124345341232134561', '18343569942', '2023-04-13', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (8, NULL, '小辉', '1', '124345341232134561', '18344249941', '2023-04-01', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (9, NULL, '章华', '1', '124345341232134561', '18544279942', '2023-02-08', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (10, NULL, '小梅', '0', '32412453412321345X', '13412345678', '2023-02-11', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (11, NULL, '海星', '0', '124345341232134561', '18343569942', '2023-02-13', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (12, NULL, '大疆', '1', '124345341232134561', '18344249948', '2023-02-06', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (13, NULL, '刘旭', '1', '124345341232134561', '18544279942', '2023-02-08', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (14, NULL, '王娅', '0', '32412453412321345X', '13412345678', '2023-02-11', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (15, NULL, '戴强', '1', '124345341232134561', '18343569943', '2023-02-13', NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (18, NULL, '用户18344249942', NULL, NULL, '18344249942', '2023-06-19', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `linkUrl` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` int NULL DEFAULT NULL,
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentMenuId` int NULL DEFAULT NULL,
  `level` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_13`(`parentMenuId` ASC) USING BTREE,
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`parentMenuId`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '会员管理', NULL, '2', 1, 'fa-user-md', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (2, '会员档案', 'member.html', '/2-1', 1, NULL, NULL, 1, 2);
INSERT INTO `t_menu` VALUES (3, '会员信息上传', NULL, '/2-2', 2, NULL, NULL, 1, 2);
INSERT INTO `t_menu` VALUES (4, '会员统计', NULL, '/2-3', 3, NULL, NULL, 1, 2);
INSERT INTO `t_menu` VALUES (5, '预约管理', NULL, '3', 2, 'fa-tty', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (6, '预约列表', 'ordersettinglist.html', '/3-1', 1, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (7, '预约设置', 'ordersetting.html', '/3-2', 2, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (8, '套餐管理', 'setmeal.html', '/3-3', 3, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (9, '跟团游管理', 'checkgroup.html', '/3-4', 4, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (10, '自由行管理', 'checkitem.html', '/3-5', 5, NULL, NULL, 5, 2);
INSERT INTO `t_menu` VALUES (11, '旅游评估', NULL, '4', 3, 'fa-stethoscope', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (12, '大家一起游', NULL, '/4-1', 1, NULL, NULL, 11, 2);
INSERT INTO `t_menu` VALUES (13, '统计分析', NULL, '5', 4, 'fa-heartbeat', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (14, '会员数量', 'report_member.html', '/5-1', 1, NULL, NULL, 13, 2);
INSERT INTO `t_menu` VALUES (15, '系统设置', NULL, '6', 5, 'fa-users', NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (16, '菜单管理', 'menu.html', '/6-1', 1, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (17, '权限管理', 'permission.html', '/6-2', 2, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (18, '角色管理', 'role.html', '/6-3', 3, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (19, '用户管理', 'user.html', '/6-4', 4, NULL, NULL, 15, 2);
INSERT INTO `t_menu` VALUES (20, '套餐占比', 'report_setmeal.html', '/5-2', 2, NULL, NULL, 13, 2);
INSERT INTO `t_menu` VALUES (21, '运营数据', 'report_business.html', '/5-3', 3, NULL, NULL, 13, 2);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NULL DEFAULT NULL COMMENT '员会id',
  `orderDate` date NULL DEFAULT NULL COMMENT '约预日期',
  `orderType` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '约预类型 电话预约/微信预约',
  `orderStatus` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约状态（是否旅游）',
  `setmeal_id` int NULL DEFAULT NULL COMMENT '餐套id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_member_id`(`member_id` ASC) USING BTREE,
  INDEX `key_setmeal_id`(`setmeal_id` ASC) USING BTREE,
  CONSTRAINT `key_member_id` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `key_setmeal_id` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, 1, '2022-02-08', '微信预约', '已出游', 3);
INSERT INTO `t_order` VALUES (2, 2, '2022-02-11', '微信预约', '已出游', 3);
INSERT INTO `t_order` VALUES (3, 3, '2022-02-13', '微信预约', '已出游', 3);
INSERT INTO `t_order` VALUES (4, 4, '2022-02-26', '微信预约', '已出游', 4);
INSERT INTO `t_order` VALUES (5, 5, '2023-06-21', '微信预约', '未出游', 1);
INSERT INTO `t_order` VALUES (6, 5, '2023-06-14', '微信预约', '未出游', 1);
INSERT INTO `t_order` VALUES (7, 5, '2023-06-22', '微信预约', '未出游', 1);
INSERT INTO `t_order` VALUES (8, 5, '2023-06-23', '微信预约', '未出游', 1);

-- ----------------------------
-- Table structure for t_ordersetting
-- ----------------------------
DROP TABLE IF EXISTS `t_ordersetting`;
CREATE TABLE `t_ordersetting`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderDate` date NULL DEFAULT NULL COMMENT '约预日期',
  `number` int NULL DEFAULT NULL COMMENT '可预约人数',
  `reservations` int NULL DEFAULT NULL COMMENT '已预约人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ordersetting
-- ----------------------------
INSERT INTO `t_ordersetting` VALUES (1, '2022-12-01', 100, 0);
INSERT INTO `t_ordersetting` VALUES (2, '2022-12-02', 100, 0);
INSERT INTO `t_ordersetting` VALUES (3, '2022-12-03', 100, 0);
INSERT INTO `t_ordersetting` VALUES (4, '2022-12-04', 100, 0);
INSERT INTO `t_ordersetting` VALUES (5, '2022-12-05', 100, 0);
INSERT INTO `t_ordersetting` VALUES (6, '2022-12-06', 100, 0);
INSERT INTO `t_ordersetting` VALUES (7, '2022-12-07', 100, 0);
INSERT INTO `t_ordersetting` VALUES (8, '2022-12-08', 100, 0);
INSERT INTO `t_ordersetting` VALUES (9, '2022-12-09', 100, 0);
INSERT INTO `t_ordersetting` VALUES (10, '2022-12-10', 100, 0);
INSERT INTO `t_ordersetting` VALUES (11, '2022-12-11', 100, 0);
INSERT INTO `t_ordersetting` VALUES (12, '2022-12-12', 100, 0);
INSERT INTO `t_ordersetting` VALUES (13, '2022-12-13', 100, 0);
INSERT INTO `t_ordersetting` VALUES (14, '2022-12-14', 100, 0);
INSERT INTO `t_ordersetting` VALUES (15, '2022-12-15', 100, 0);
INSERT INTO `t_ordersetting` VALUES (16, '2022-12-16', 100, 0);
INSERT INTO `t_ordersetting` VALUES (17, '2022-12-17', 100, 0);
INSERT INTO `t_ordersetting` VALUES (18, '2022-12-18', 100, 0);
INSERT INTO `t_ordersetting` VALUES (19, '2022-12-19', 300, 0);
INSERT INTO `t_ordersetting` VALUES (20, '2022-12-20', 200, 0);
INSERT INTO `t_ordersetting` VALUES (21, '2022-12-21', 100, 0);
INSERT INTO `t_ordersetting` VALUES (22, '2022-12-22', 100, 0);
INSERT INTO `t_ordersetting` VALUES (23, '2022-12-23', 100, 0);
INSERT INTO `t_ordersetting` VALUES (24, '2022-12-24', 100, 0);
INSERT INTO `t_ordersetting` VALUES (25, '2022-12-25', 100, 0);
INSERT INTO `t_ordersetting` VALUES (26, '2022-12-26', 100, 0);
INSERT INTO `t_ordersetting` VALUES (27, '2022-12-27', 100, 0);
INSERT INTO `t_ordersetting` VALUES (28, '2022-12-28', 100, 0);
INSERT INTO `t_ordersetting` VALUES (29, '2022-12-29', 100, 0);
INSERT INTO `t_ordersetting` VALUES (30, '2022-12-30', 100, 0);
INSERT INTO `t_ordersetting` VALUES (31, '2023-06-01', 100, 0);
INSERT INTO `t_ordersetting` VALUES (32, '2023-06-02', 100, 0);
INSERT INTO `t_ordersetting` VALUES (33, '2023-06-03', 100, 0);
INSERT INTO `t_ordersetting` VALUES (34, '2023-06-04', 100, 0);
INSERT INTO `t_ordersetting` VALUES (35, '2023-06-05', 100, 0);
INSERT INTO `t_ordersetting` VALUES (36, '2023-06-06', 100, 0);
INSERT INTO `t_ordersetting` VALUES (37, '2023-06-07', 100, 0);
INSERT INTO `t_ordersetting` VALUES (38, '2023-06-08', 100, 0);
INSERT INTO `t_ordersetting` VALUES (39, '2023-06-09', 100, 0);
INSERT INTO `t_ordersetting` VALUES (40, '2023-06-10', 100, 0);
INSERT INTO `t_ordersetting` VALUES (41, '2023-06-11', 100, 0);
INSERT INTO `t_ordersetting` VALUES (42, '2023-06-12', 100, 0);
INSERT INTO `t_ordersetting` VALUES (43, '2023-06-13', 100, 0);
INSERT INTO `t_ordersetting` VALUES (44, '2023-06-14', 100, 1);
INSERT INTO `t_ordersetting` VALUES (45, '2023-06-15', 100, 0);
INSERT INTO `t_ordersetting` VALUES (46, '2023-06-16', 100, 0);
INSERT INTO `t_ordersetting` VALUES (47, '2023-06-17', 100, 0);
INSERT INTO `t_ordersetting` VALUES (48, '2023-06-18', 100, 0);
INSERT INTO `t_ordersetting` VALUES (49, '2023-06-19', 300, 1);
INSERT INTO `t_ordersetting` VALUES (50, '2023-06-20', 200, 0);
INSERT INTO `t_ordersetting` VALUES (51, '2023-06-21', 100, 1);
INSERT INTO `t_ordersetting` VALUES (52, '2023-06-22', 56, 1);
INSERT INTO `t_ordersetting` VALUES (53, '2023-06-23', 34, 1);
INSERT INTO `t_ordersetting` VALUES (54, '2023-06-24', 100, 0);
INSERT INTO `t_ordersetting` VALUES (55, '2023-06-25', 100, 0);
INSERT INTO `t_ordersetting` VALUES (56, '2023-06-26', 100, 0);
INSERT INTO `t_ordersetting` VALUES (57, '2023-06-27', 100, 0);
INSERT INTO `t_ordersetting` VALUES (58, '2023-06-28', 100, 0);
INSERT INTO `t_ordersetting` VALUES (59, '2023-06-29', 100, 0);
INSERT INTO `t_ordersetting` VALUES (60, '2023-06-30', 100, 0);
INSERT INTO `t_ordersetting` VALUES (61, '2023-07-01', 100, 0);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keyword` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '新增自由行', 'TRAVELITEM_ADD', NULL);
INSERT INTO `t_permission` VALUES (2, '删除自由行', 'TRAVELITEM_DELETE', NULL);
INSERT INTO `t_permission` VALUES (3, '编辑自由行', 'TRAVELITEM_EDIT', NULL);
INSERT INTO `t_permission` VALUES (4, '查询自由行', 'TRAVELITEM_QUERY', NULL);
INSERT INTO `t_permission` VALUES (5, '新增跟团游', 'TRAVELGROUP_ADD', NULL);
INSERT INTO `t_permission` VALUES (6, '删除跟团游', 'TRAVELGROUP_DELETE', NULL);
INSERT INTO `t_permission` VALUES (7, '编辑跟团游', 'TRAVELGROUP_EDIT', NULL);
INSERT INTO `t_permission` VALUES (8, '查询跟团游', 'TRAVELGROUP_QUERY', NULL);
INSERT INTO `t_permission` VALUES (9, '新增套餐', 'SETMEAL_ADD', NULL);
INSERT INTO `t_permission` VALUES (10, '删除套餐', 'SETMEAL_DELETE', NULL);
INSERT INTO `t_permission` VALUES (11, '编辑套餐', 'SETMEAL_EDIT', NULL);
INSERT INTO `t_permission` VALUES (12, '查询套餐', 'SETMEAL_QUERY', NULL);
INSERT INTO `t_permission` VALUES (13, '预约设置', 'ORDERSETTING', NULL);
INSERT INTO `t_permission` VALUES (14, '查看统计报表', 'REPORT_VIEW', NULL);
INSERT INTO `t_permission` VALUES (15, '新增菜单', 'MENU_ADD', NULL);
INSERT INTO `t_permission` VALUES (16, '删除菜单', 'MENU_DELETE', NULL);
INSERT INTO `t_permission` VALUES (17, '编辑菜单', 'MENU_EDIT', NULL);
INSERT INTO `t_permission` VALUES (18, '查询菜单', 'MENU_QUERY', NULL);
INSERT INTO `t_permission` VALUES (19, '新增角色', 'ROLE_ADD', NULL);
INSERT INTO `t_permission` VALUES (20, '删除角色', 'ROLE_DELETE', NULL);
INSERT INTO `t_permission` VALUES (21, '编辑角色', 'ROLE_EDIT', NULL);
INSERT INTO `t_permission` VALUES (22, '查询角色', 'ROLE_QUERY', NULL);
INSERT INTO `t_permission` VALUES (23, '新增用户', 'USER_ADD', NULL);
INSERT INTO `t_permission` VALUES (24, '删除用户', 'USER_DELETE', NULL);
INSERT INTO `t_permission` VALUES (25, '编辑用户', 'USER_EDIT', NULL);
INSERT INTO `t_permission` VALUES (26, '查询用户', 'USER_QUERY', NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keyword` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '系统管理员', 'ROLE_ADMIN', NULL);
INSERT INTO `t_role` VALUES (2, '导游', 'ROLE_HEALTH_MANAGER', NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `role_id` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `FK_Reference_10`(`menu_id` ASC) USING BTREE,
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1);
INSERT INTO `t_role_menu` VALUES (2, 1);
INSERT INTO `t_role_menu` VALUES (1, 2);
INSERT INTO `t_role_menu` VALUES (2, 2);
INSERT INTO `t_role_menu` VALUES (1, 3);
INSERT INTO `t_role_menu` VALUES (2, 3);
INSERT INTO `t_role_menu` VALUES (1, 4);
INSERT INTO `t_role_menu` VALUES (2, 4);
INSERT INTO `t_role_menu` VALUES (1, 5);
INSERT INTO `t_role_menu` VALUES (1, 6);
INSERT INTO `t_role_menu` VALUES (1, 7);
INSERT INTO `t_role_menu` VALUES (1, 8);
INSERT INTO `t_role_menu` VALUES (1, 9);
INSERT INTO `t_role_menu` VALUES (1, 10);
INSERT INTO `t_role_menu` VALUES (1, 11);
INSERT INTO `t_role_menu` VALUES (1, 12);
INSERT INTO `t_role_menu` VALUES (1, 13);
INSERT INTO `t_role_menu` VALUES (1, 14);
INSERT INTO `t_role_menu` VALUES (1, 15);
INSERT INTO `t_role_menu` VALUES (1, 16);
INSERT INTO `t_role_menu` VALUES (1, 17);
INSERT INTO `t_role_menu` VALUES (1, 18);
INSERT INTO `t_role_menu` VALUES (1, 19);
INSERT INTO `t_role_menu` VALUES (1, 20);
INSERT INTO `t_role_menu` VALUES (1, 21);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `FK_Reference_12`(`permission_id` ASC) USING BTREE,
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1);
INSERT INTO `t_role_permission` VALUES (1, 2);
INSERT INTO `t_role_permission` VALUES (2, 2);
INSERT INTO `t_role_permission` VALUES (1, 3);
INSERT INTO `t_role_permission` VALUES (2, 3);
INSERT INTO `t_role_permission` VALUES (1, 4);
INSERT INTO `t_role_permission` VALUES (2, 4);
INSERT INTO `t_role_permission` VALUES (1, 5);
INSERT INTO `t_role_permission` VALUES (2, 5);
INSERT INTO `t_role_permission` VALUES (1, 6);
INSERT INTO `t_role_permission` VALUES (2, 6);
INSERT INTO `t_role_permission` VALUES (1, 7);
INSERT INTO `t_role_permission` VALUES (2, 7);
INSERT INTO `t_role_permission` VALUES (1, 8);
INSERT INTO `t_role_permission` VALUES (2, 8);
INSERT INTO `t_role_permission` VALUES (1, 9);
INSERT INTO `t_role_permission` VALUES (2, 9);
INSERT INTO `t_role_permission` VALUES (1, 10);
INSERT INTO `t_role_permission` VALUES (2, 10);
INSERT INTO `t_role_permission` VALUES (1, 11);
INSERT INTO `t_role_permission` VALUES (2, 11);
INSERT INTO `t_role_permission` VALUES (1, 12);
INSERT INTO `t_role_permission` VALUES (2, 12);
INSERT INTO `t_role_permission` VALUES (1, 13);
INSERT INTO `t_role_permission` VALUES (2, 13);
INSERT INTO `t_role_permission` VALUES (1, 14);
INSERT INTO `t_role_permission` VALUES (2, 14);
INSERT INTO `t_role_permission` VALUES (1, 15);
INSERT INTO `t_role_permission` VALUES (1, 16);
INSERT INTO `t_role_permission` VALUES (1, 17);
INSERT INTO `t_role_permission` VALUES (1, 18);
INSERT INTO `t_role_permission` VALUES (1, 19);
INSERT INTO `t_role_permission` VALUES (1, 20);
INSERT INTO `t_role_permission` VALUES (1, 21);
INSERT INTO `t_role_permission` VALUES (1, 22);
INSERT INTO `t_role_permission` VALUES (1, 23);
INSERT INTO `t_role_permission` VALUES (1, 24);
INSERT INTO `t_role_permission` VALUES (1, 25);
INSERT INTO `t_role_permission` VALUES (1, 26);

-- ----------------------------
-- Table structure for t_setmeal
-- ----------------------------
DROP TABLE IF EXISTS `t_setmeal`;
CREATE TABLE `t_setmeal`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `helpCode` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `remark` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attention` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_setmeal
-- ----------------------------
INSERT INTO `t_setmeal` VALUES (1, '公司年度旅游套餐（男女通用）', '0001', 'RZTJ', '0', '18-60', 300, '公司年度旅游套餐', NULL, '07b70852-d515-46f7-957a-df161f254acf.jpg');
INSERT INTO `t_setmeal` VALUES (2, '广州长隆酒店+珠海长隆企鹅酒店双飞5日自由行套餐', '0002', 'FHZA', '2', '18-60', 1200, '长隆酒店(广州长隆野生动物世界店)位于广州长隆旅游度假区中心地段，毗邻长隆欢乐世界、长隆水上乐园、长隆野生动物世界、长隆飞鸟乐园和长隆国际大马戏等主题乐园。交通便捷，多条园区穿梭巴往返长隆酒店和各大园区之间。珠海园区、机场快线、香港直通巴汇集其中，广州地铁3号线/7号线让您的度假娱乐更快捷方便。 长隆酒店作为大型的生态主题酒店，主要以热带区域的人文文化风情为主基调。客房的摆设装修以客为主，温馨舒适，并有多种客房和套房类型选择。酒店绿植围绕，种类繁多，动物岛置身于酒店之中，珍稀动物随处可见。 酒店内配套多间风味各异的餐厅，让你足不出户品尝环球美食。酒店配套室外泳池、室内四季恒温泳池、健身房、童趣乐园、康体中心等，都是您商旅或者度假的上佳消遣地方。 国际会展中心更有可容纳3,000人的6,000平方米宴会厅，拥有12米无柱高楼顶，配备LED屏幕，及大型内置8米升降舞台、先进的多媒体视听、通讯系统等商务会议设施。39个不同规格的豪华多功能会议厅。专业的销售、服务团队和完善的会议设施设备随时准备为您提供一流的会议、展览和宴会服务，打造一流的商务会议品牌。', NULL, 'e9bcc509-bc05-460c-a211-a2e7e048b5cb.jpg');
INSERT INTO `t_setmeal` VALUES (3, '厦门+鼓浪屿双飞5日自由行套餐', '0003', 'YGBM', '0', '55-100', 1400, '鼓浪屿的生活，是慵懒而优雅的，像极了欧洲某个古老的城市，不张扬，却有着致命的吸引力——温柔的阳光，蔚蓝的大海，美妙的琴声，静静矗立的老建筑，悠然自得的猫咪，所有的一切，总让人忍不住想好好的，再谈一场恋爱。 　　所以做了这样的主题，就是想把所谓浪漫，狠狠地，彻底地，进行下去。要华丽，因为青春理应肆意和张扬；要低调，因为在心底，总有一个最柔软最隐秘的角落，是只属于你的。最重要的，是要浪漫。在流淌着音乐的房间里，看点点烛光摇曳，撒在床上的玫瑰花瓣香味隐约扑来，举起手中的红酒杯，两个人，相视无语。cheers. 感谢你们，与我们分享这美好。也但愿我能，一直见证你们的幸福。', NULL, '84354b57-af0c-4bce-a982-5b02bd5dabf0.jpg');
INSERT INTO `t_setmeal` VALUES (4, '云南-昆明-大理-丽江-香格里拉双飞8日游套餐', '0004', 'ZAGD', '0', '14-20', 2400, '今日行程无导游陪同，如您当日抵昆时间较早，可自行将行李寄存在入住酒店（贵重物品请自行保管好）；随后，自由活动（如抵达昆明时间尚早，可自行前往云南师范大学（西南联合大学旧址）、昆明金马碧鸡坊、南屏街、、陆军讲武堂等景点游览、（外出酒店时贵重物品请自行保管好，请到酒店前台带上酒店名片，方便打车回酒店）', NULL, '68c7c13f-8fc2-46c3-b5d6-f7ec7992dc6e1.jpg');
INSERT INTO `t_setmeal` VALUES (5, '澳门威尼斯人商圈酒店双飞3-5日自由行套餐', '0005', 'AMWN', '0', '18-60', 300, '澳门巴黎人是全澳门乃至亚洲的一颗闪耀新星，您可以在此感受“光之城”巴黎的独特艺术气息与迷人魅力。这里有约2千余间法式客房及套房供您选择，还有依照巴黎埃菲尔铁塔1/2比例建造的巴黎铁塔，让您体验独特的浪漫风情。您还可以于170家精品名店享受购物乐趣，或是品尝经典法式美食，欣赏精彩的娱乐表演，畅游水世界、儿童王国等各种娱乐项目，像巴黎人一样体验无处不在的浪漫与惊喜！', NULL, 'a5e8e729-74ce-4939-bf36-9cdc02fb2ae51.jpg');
INSERT INTO `t_setmeal` VALUES (6, '香港九龙尖沙咀商圈双飞3-8日自由行套餐', '0006', 'XGJL', '0', '18-60', 300, '香港九龙珀丽酒店(Rosedale Hotel Kowloon)位于市中心繁华闹市，地处九龙中心地带，毗邻多个购物、娱乐中心；酒店提供班车来往旺角、尖沙嘴和“圆方”购物区，方便宾客出行。 香港九龙珀丽酒店(Rosedale Hotel Kowloon) 设计精巧、新颖独特，拥有精致、高雅的各式客房。酒店客房均配有LED智能电视、iPod/ iPhone 底座，让您尽享便捷、舒适生活。', NULL, 'a5e8e729-74ce-4939-bf36-9cdc02fb2ae51.jpg');
INSERT INTO `t_setmeal` VALUES (7, '海南-亚龙湾-三亚双飞7日游', '0007', 'HNYL', '0', '18-60', 300, '全程由旅游局指定餐厅用餐，严格把控餐饮质量，让您安心享用每一顿饮食，尽情尝特色美食，让味蕾绽放在路上，品味舌尖上的海南 。', NULL, 'a5e8e729-74ce-4939-bf36-9cdc02fb2ae51.jpg');
INSERT INTO `t_setmeal` VALUES (8, '丽江-大理-香格里拉双飞6日游', '0008', 'LJDL', '0', '18-60', 300, '云南地处高原，老年人体温调节功能较差，易受凉感冒，所以衣服要带得够，以便随时增减，行走出汗时，不要马上脱衣敞怀。高原地区昼夜温差大，睡前要盖好被毯，夜间风起雨来时要关好门窗。', NULL, 'a5e8e729-74ce-4939-bf36-9cdc02fb2ae51.jpg');
INSERT INTO `t_setmeal` VALUES (9, '海南-亚龙湾-三亚双飞8日游', '0009', 'HNSY', '0', '18-60', 300, '旅游中要有充足的休息和睡眠，若感到体力不支，可略着休息或减缓旅行。在长时间步行游览时应随时坐下小憩。', NULL, 'a5e8e729-74ce-4939-bf36-9cdc02fb2ae51.jpg');
INSERT INTO `t_setmeal` VALUES (20, '434', '23', 'fsfs', '0', '5656', 45, NULL, NULL, NULL);
INSERT INTO `t_setmeal` VALUES (21, '324324', '0002', NULL, NULL, NULL, NULL, NULL, NULL, 'de7386ed-cc4b-4d4a-93be-0ec5f41680f7.jpg');
INSERT INTO `t_setmeal` VALUES (22, '1111', 'wewd', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_setmeal` VALUES (23, '1112', 'dscd', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_setmeal` VALUES (24, '1113', 'dffd', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_setmeal` VALUES (25, '1114', 'fvvb', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_setmeal` VALUES (26, '1115', 'effg', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_setmeal` VALUES (27, '1116', 'dcdv', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_setmeal` VALUES (28, '1117', 'dfsd', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_setmeal` VALUES (29, '1118', 'vfbb', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_setmeal` VALUES (30, '3232', 'dfdf', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_setmeal_travelgroup
-- ----------------------------
DROP TABLE IF EXISTS `t_setmeal_travelgroup`;
CREATE TABLE `t_setmeal_travelgroup`  (
  `setmeal_id` int NOT NULL DEFAULT 0,
  `travelgroup_id` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`setmeal_id`, `travelgroup_id`) USING BTREE,
  INDEX `travelgroup_key`(`travelgroup_id` ASC) USING BTREE,
  CONSTRAINT `setmeal_key` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `travelgroup_key` FOREIGN KEY (`travelgroup_id`) REFERENCES `t_travelgroup` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_setmeal_travelgroup
-- ----------------------------
INSERT INTO `t_setmeal_travelgroup` VALUES (1, 3);
INSERT INTO `t_setmeal_travelgroup` VALUES (1, 4);
INSERT INTO `t_setmeal_travelgroup` VALUES (20, 4);
INSERT INTO `t_setmeal_travelgroup` VALUES (20, 6);

-- ----------------------------
-- Table structure for t_travelgroup
-- ----------------------------
DROP TABLE IF EXISTS `t_travelgroup`;
CREATE TABLE `t_travelgroup`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `helpCode` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attention` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_travelgroup
-- ----------------------------
INSERT INTO `t_travelgroup` VALUES (3, '0003', '重庆、武隆仙女山、天坑三硚、龙水峡地缝、洪崖洞、磁器口古镇双飞5日经典游', 'XCG', '0', '春节南宁-越南-下龙湾-北海双飞7日游>0购0自费/境外国际4星/1晚希尔顿/168元海鲜自助/奥巴马米粉/赠越式按摩/可升涠洲岛/全程领队/双口岸进出', NULL);
INSERT INTO `t_travelgroup` VALUES (4, '0004', '印度尼西亚巴厘岛7日5晚半自助跟团游', 'NCG', '0', '春节海南三亚双飞5日游17万人游玩0购物，180度海景房(华美达/明申/维景/唐拉雅秀)，赠全海景玻璃栈道+蟹鲍海鲜餐，20年出游去南山看108米海上观音', NULL);
INSERT INTO `t_travelgroup` VALUES (5, '0005', '厦门-鼓浪屿-云水谣土楼双飞5日4晚跟团游', 'GGSX', '0', '厦门-鼓浪屿双飞5日游臻品9人团0购0自，全程五星华邑/万豪海景房，高标佳丽自助/地标全海景餐厅，人气日光岩植物园鹭江夜游，接送0等待', NULL);
INSERT INTO `t_travelgroup` VALUES (6, '0006', '越南芽庄6日5晚自由行', 'NGSX', '0', '土耳其10日游超6000人选择牛人专线/五星航空直飞内陆双飞/费特希耶/网红景点齐打卡/五星酒店升1晚真洞穴酒店/升级4特色餐/2顿中餐', NULL);
INSERT INTO `t_travelgroup` VALUES (7, '0007', '印度尼西亚巴厘岛7日5晚半自助跟团游', 'XZSX', '0', '春节埃及-迪拜-阿布扎比10日游A380或波音787/埃及全程五星/红海两晚/宿卢克索/吉萨金字塔群/埃及博物馆/马车巡游/风帆船', NULL);
INSERT INTO `t_travelgroup` VALUES (8, '0008', '泰国普吉岛7日5晚跟团游', 'XJMSX', '0', '春节埃及+土耳其11-14日游埃及卢克索/亚历山大/红海,土耳其伊斯坦布尔/圣索菲亚大教堂,内陆4段飞,含卡帕和棉花堡,TK', NULL);
INSERT INTO `t_travelgroup` VALUES (9, '0009', '悉尼-黄金海岸 汉密尔顿8日6晚品质游', 'JGSX', '0', '南美印象5国+巴西+阿根廷+秘鲁+智利+乌拉圭16-20日游>纯玩无购物/雨林/伊瓜苏瀑布/纳斯卡地画/四到五星酒店/部分增游马丘比丘', NULL);
INSERT INTO `t_travelgroup` VALUES (10, '0010', '柬埔寨吴哥6日4晚跟团游', 'ZGFJCC', '2', '春节美国东西海岸11-13日游游多款产品可选/迪士尼一日双园过大年/环球影城/峡谷/羚羊彩穴/马蹄湾/部分团期升级纯玩团/保证拼住', NULL);
INSERT INTO `t_travelgroup` VALUES (11, '0011', '张家界、玻璃桥、天门山、芙蓉镇、凤凰古城双飞5日跟团游', 'DHSSX', '0', '美国西雅图-波特兰6晚8日自由行西雅图进波特兰出，精选全程6晚酒店，行程天数可随意增减', NULL);

-- ----------------------------
-- Table structure for t_travelgroup_travelitem
-- ----------------------------
DROP TABLE IF EXISTS `t_travelgroup_travelitem`;
CREATE TABLE `t_travelgroup_travelitem`  (
  `travelgroup_id` int NOT NULL DEFAULT 0,
  `travelitem_id` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`travelgroup_id`, `travelitem_id`) USING BTREE,
  INDEX `item_id`(`travelitem_id` ASC) USING BTREE,
  CONSTRAINT `group_id` FOREIGN KEY (`travelgroup_id`) REFERENCES `t_travelgroup` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `item_id` FOREIGN KEY (`travelitem_id`) REFERENCES `t_travelitem` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_travelgroup_travelitem
-- ----------------------------
INSERT INTO `t_travelgroup_travelitem` VALUES (3, 2);
INSERT INTO `t_travelgroup_travelitem` VALUES (3, 3);

-- ----------------------------
-- Table structure for t_travelitem
-- ----------------------------
DROP TABLE IF EXISTS `t_travelitem`;
CREATE TABLE `t_travelitem`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自由行类型,分为自由和跟团两种',
  `attention` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_travelitem
-- ----------------------------
INSERT INTO `t_travelitem` VALUES (2, '0001', '5天4晚，海南三亚自由行玩法', '0', '0-199', 5, '1', '无', '5天4晚，海南三亚自由行玩法');
INSERT INTO `t_travelitem` VALUES (3, '0003', '5天4晚，三亚玩法，带着爸妈去旅行', '0', '0-100', 5, '1', '无', '5天4晚，三亚玩法，带着爸妈去旅行');
INSERT INTO `t_travelitem` VALUES (4, '0004', '三亚大东海风景迷人5日家庭游', '0', '0-100', 5, '1', '无', '三亚大东海风景迷人5日家庭游');
INSERT INTO `t_travelitem` VALUES (5, '0005', '4天3晚厦门玩法，热恋在土楼与都市沿岸', '0', '0-100', 5, '1', '无', '4天3晚厦门玩法，热恋在土楼与都市沿岸');
INSERT INTO `t_travelitem` VALUES (6, '0006', '4日3晚，厦门亲子玩法', '0', '0-100', 5, '1', '无', '4日3晚，厦门亲子玩法');
INSERT INTO `t_travelitem` VALUES (7, '0007', '厦门风灌醉鼓浪屿的海4日浪漫游', '0', '0-100', 5, '1', '无', '厦门风灌醉鼓浪屿的海4日浪漫游');
INSERT INTO `t_travelitem` VALUES (8, '0008', '4天3晚，海上花园城市，厦门玩法', '0', '0-100', 5, '1', '无', '4天3晚，海上花园城市，厦门玩法');
INSERT INTO `t_travelitem` VALUES (9, '0009', '香港迪士尼里追童梦5日亲子游', '0', '0-100', 5, '1', '无', '香港迪士尼里追童梦5日亲子游');
INSERT INTO `t_travelitem` VALUES (10, '0010', '香港铜锣湾处处风情5日休闲游', '0', '0-100', 5, '1', '无', '香港铜锣湾处处风情5日休闲游');
INSERT INTO `t_travelitem` VALUES (11, '0011', '5天4晚寻味香港玩法，回忆记忆中的“港味儿”', '0', '0-100', 10, '2', '无', '5天4晚寻味香港玩法，回忆记忆中的“港味儿”');
INSERT INTO `t_travelitem` VALUES (12, '0012', '版纳傣族园丽江古城8日三地连线游', '0', '0-100', 10, '2', NULL, '版纳傣族园丽江古城 8日三地连线游');
INSERT INTO `t_travelitem` VALUES (13, '0013', '6天5晚，丽江香格里拉蜜月玩法，意外的遇见，刚刚好！', '0', '0-100', 10, '2', NULL, '6天5晚，丽江香格里拉蜜月玩法，意外的遇见，刚刚好！');
INSERT INTO `t_travelitem` VALUES (14, '0014', '4天3晚，云南丽江大众玩法', '0', '0-100', 10, '2', NULL, '4天3晚，云南丽江大众玩法');
INSERT INTO `t_travelitem` VALUES (15, '0015', '丽江古城佳肴诱惑5日美食游', '0', '0-100', 10, '2', NULL, '丽江古城佳肴诱惑5日美食游');
INSERT INTO `t_travelitem` VALUES (16, '0016', '成都九寨沟五彩天堂7日深度游', '0', '0-100', 10, '2', NULL, '成都九寨沟五彩天堂7日深度游');
INSERT INTO `t_travelitem` VALUES (17, '0017', '成都九寨沟黄龙远离喧嚣6日风光游', '0', '0-100', 10, '2', NULL, '成都九寨沟黄龙远离喧嚣6日风光游');
INSERT INTO `t_travelitem` VALUES (18, '0018', '7天6晚，四川玩法', '0', '0-100', 10, '2', NULL, '7天6晚，四川玩法');
INSERT INTO `t_travelitem` VALUES (19, '0019', '4天3晚，大连玩法，阳光明媚去看海', '0', '0-100', 10, '2', NULL, '4天3晚，大连玩法，阳光明媚去看海');
INSERT INTO `t_travelitem` VALUES (20, '0020', '4天3晚，浪漫之都大连玩法', '0', '0-100', 10, '2', NULL, '4天3晚，浪漫之都大连玩法');
INSERT INTO `t_travelitem` VALUES (21, '0021', '4天3晚大连海滨玩法', '0', '0-100', 10, '2', NULL, '4天3晚大连海滨玩法');
INSERT INTO `t_travelitem` VALUES (22, '0022', '大连美丽不过滨海路 4日经典游', '0', '0-100', 10, '2', NULL, '大连美丽不过滨海路4日经典游');
INSERT INTO `t_travelitem` VALUES (23, '0023', '1天，尽览东方巴黎风采，哈尔滨玩法', '0', '0-100', 10, '2', NULL, '1天，尽览东方巴黎风采，哈尔滨玩法');
INSERT INTO `t_travelitem` VALUES (24, '0024', '5天4晚哈尔滨+吉林双城玩法', '0', '0-100', 10, '2', NULL, '5天4晚哈尔滨+吉林双城玩法');
INSERT INTO `t_travelitem` VALUES (25, '0025', '哈尔滨老道外风情建筑 5日魅力游', '0', '0-100', 10, '2', NULL, '哈尔滨老道外风情建筑5日魅力游');
INSERT INTO `t_travelitem` VALUES (26, '0026', '哈尔滨雪博会一头栽雪堆 5日狂欢游', '0', '0-100', 10, '2', NULL, '哈尔滨雪博会一头栽雪堆5日狂欢游');
INSERT INTO `t_travelitem` VALUES (27, '0027', '大阪东京夜景艾特你了6日连线游', '0', '0-100', 10, '2', NULL, '大阪东京夜景艾特你了6日连线游');
INSERT INTO `t_travelitem` VALUES (28, '0028', '东京大阪一秒钟嗨翻乐园 6日亲子游', '0', '0-100', 10, '2', NULL, '东京大阪一秒钟嗨翻乐园6日亲子游');
INSERT INTO `t_travelitem` VALUES (29, '0029', '东京扫货扫到手抽筋5日购物游', '0', '0-100', 10, '2', NULL, '东京扫货扫到手抽筋5日购物游');
INSERT INTO `t_travelitem` VALUES (30, '0030', '东京富士山温泉5日泡汤游', '0', '0-100', 10, '2', NULL, '东京富士山温泉 5日泡汤游');
INSERT INTO `t_travelitem` VALUES (31, '0031', '巴厘岛梦幻海滩分享甜蜜 5日蜜月游', '0', '0-100', 10, '2', NULL, '巴厘岛梦幻海滩分享甜蜜5日蜜月游');
INSERT INTO `t_travelitem` VALUES (32, '0032', '6天5晚千岛之国，万般风情，印尼海岛玩法。', '0', '0-100', 10, '2', NULL, '6天5晚千岛之国，万般风情，印尼海岛玩法。');
INSERT INTO `t_travelitem` VALUES (33, '0033', '5天4晚巴厘岛玩法，拥抱理想国的阳光', '0', '0-100', 10, '2', NULL, '5天4晚巴厘岛玩法，拥抱理想国的阳光');
INSERT INTO `t_travelitem` VALUES (34, '0034', '巴厘岛金巴兰海滩享SPA6日悠闲游', '0', '0-100', 10, '2', NULL, '巴厘岛金巴兰海滩享SPA6日悠闲游');
INSERT INTO `t_travelitem` VALUES (35, '0035', '巴厘岛乌布皇宫如梦如幻 5日蜜月游', '0', '0-100', 10, '2', NULL, '巴厘岛乌布皇宫如梦如幻 5日蜜月游');
INSERT INTO `t_travelitem` VALUES (36, '0036', '巴厘岛海神庙全家出行8日亲子游', '0', '0-100', 10, '2', NULL, '巴厘岛海神庙全家出行8日亲子游');
INSERT INTO `t_travelitem` VALUES (37, '0037', '巴厘岛金银岛悠然自得6日经典游', '0', '0-100', 10, '2', NULL, '巴厘岛金银岛悠然自得6日经典游');
INSERT INTO `t_travelitem` VALUES (38, '0038', '巴厘岛蓝点教堂SPA专享7日休闲游', '0', '0-100', 10, '2', NULL, '巴厘岛蓝点教堂SPA专享7日休闲游');
INSERT INTO `t_travelitem` VALUES (39, '0039', '大阪东京夜景艾特你了6日连线游', '0', '0-100', 10, '2', NULL, '大阪东京夜景艾特你了6日连线游');
INSERT INTO `t_travelitem` VALUES (40, '0040', '东京大阪一秒钟嗨翻乐园6日亲子游', '0', '0-100', 10, '2', NULL, '东京大阪一秒钟嗨翻乐园6日亲子游');
INSERT INTO `t_travelitem` VALUES (41, '0041', '6天5晚最牛的日本本州玩法', '0', '0-100', 10, '2', NULL, '6天5晚最牛的日本本州玩法');
INSERT INTO `t_travelitem` VALUES (42, '0042', '日本京都箱根东瀛和风7日风情游', '0', '0-100', 10, '2', NULL, '日本京都箱根东瀛和风7日风情游');
INSERT INTO `t_travelitem` VALUES (43, '0043', '日本箱根大涌谷6日关东关西游', '0', '0-100', 10, '2', NULL, '日本箱根大涌谷6日关东关西游');
INSERT INTO `t_travelitem` VALUES (44, '0044', '日本伏见稻荷朱红色6日古都游', '0', '0-100', 10, '2', NULL, '日本伏见稻荷朱红色6日古都游');
INSERT INTO `t_travelitem` VALUES (45, '0045', '大阪环球影城一键收藏5日亲子游', '0', '0-100', 10, '2', NULL, '大阪环球影城一键收藏5日亲子游');
INSERT INTO `t_travelitem` VALUES (46, '0046', '大阪黑门市场吃海鲜5日美食游', '0', '0-100', 10, '2', NULL, '大阪黑门市场吃海鲜5日美食游');
INSERT INTO `t_travelitem` VALUES (47, '0047', '大阪城公园樱花美成画5日经典游', '0', '0-100', 10, '2', NULL, '大阪城公园樱花美成画5日经典游');
INSERT INTO `t_travelitem` VALUES (48, '0048', '大阪海游馆亲密接触5日休闲游', '0', '0-100', 10, '2', NULL, '大阪海游馆亲密接触5日休闲游');
INSERT INTO `t_travelitem` VALUES (49, '0049', '日本奈良公园小鹿乱撞7日古都游', '0', '0-100', 10, '2', NULL, '日本奈良公园小鹿乱撞7日古都游');
INSERT INTO `t_travelitem` VALUES (50, '0050', '大阪道顿堀小吃嘴不停6日美食游', '0', '0-100', 10, '2', NULL, '大阪道顿堀小吃嘴不停6日美食游');
INSERT INTO `t_travelitem` VALUES (51, '0051', '大阪梅田空中庭院漫步6日休闲游', '0', '0-100', 10, '2', NULL, '大阪梅田空中庭院漫步6日休闲游');
INSERT INTO `t_travelitem` VALUES (52, '0052', '大阪奈良赴一场盛宴6日深度游', '0', '0-100', 10, '2', NULL, '大阪奈良赴一场盛宴6日深度游');
INSERT INTO `t_travelitem` VALUES (53, '0053', '日本繁华都市闲逛6日深度游', '0', '0-100', 10, '2', NULL, '日本繁华都市闲逛6日深度游');
INSERT INTO `t_travelitem` VALUES (54, '0054', '毛里求斯鹿岛享沙滩阳光6日蜜月游', '0', '0-100', 10, '2', NULL, '毛里求斯鹿岛享沙滩阳光6日蜜月游');
INSERT INTO `t_travelitem` VALUES (55, '0055', '7天6晚毛里求斯360度多情与激情玩法', '0', '0-100', 10, '2', NULL, '7天6晚毛里求斯360度多情与激情玩法');
INSERT INTO `t_travelitem` VALUES (56, '0056', '毛里求斯迪拜享浪漫10日蜜月游', '0', '0-100', 10, '2', NULL, '毛里求斯迪拜享浪漫10日蜜月游');
INSERT INTO `t_travelitem` VALUES (57, '0057', '毛里求斯迪拜两国畅玩9日经典游', '0', '0-100', 10, '2', NULL, '毛里求斯迪拜两国畅玩9日经典游');
INSERT INTO `t_travelitem` VALUES (58, '0058', '毛里求斯蓝湾纯净浪漫8日蜜月游', '0', '0-100', 10, '2', NULL, '毛里求斯蓝湾纯净浪漫8日蜜月游');
INSERT INTO `t_travelitem` VALUES (59, '0059', '毛里求斯七色土绚烂多姿8日蜜月游', '0', '0-100', 10, '2', NULL, '毛里求斯七色土绚烂多姿8日蜜月游');
INSERT INTO `t_travelitem` VALUES (61, '0060', '5天4晚四川成都大众玩法', '0', '0-100', 10, '2', NULL, '5天4晚四川成都大众玩法');
INSERT INTO `t_travelitem` VALUES (62, '0061', '成都杜甫草堂历史悠久5日访古游', '2', '0-100', 10, '2', NULL, '成都杜甫草堂历史悠久5日访古游');
INSERT INTO `t_travelitem` VALUES (63, '0062', '成都人民公园享欢乐时光5日亲子游', '2', '0-100', 10, '2', NULL, '成都人民公园享欢乐时光5日亲子游');
INSERT INTO `t_travelitem` VALUES (64, '0063', '成都锦里小吃很地道4日美食游', '0', '0-100', 10, '2', NULL, '成都锦里小吃很地道4日美食游');
INSERT INTO `t_travelitem` VALUES (65, '0064', '成都锦里感受慢生活4日经典游', '0', '0-100', 10, '2', NULL, '成都锦里感受慢生活4日经典游');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `birthday` date NULL DEFAULT NULL,
  `gender` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `station` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, NULL, NULL, 'admin', '$2a$10$U5wsyaGZxjPmsLZwXPgeR./QgnO2k2e9tnKc1DlyIDkh0EULK4NUe', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (2, NULL, NULL, 'xiaoming', '$2a$10$U5wsyaGZxjPmsLZwXPgeR./QgnO2k2e9tnKc1DlyIDkh0EULK4NUe', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (3, NULL, NULL, 'test', '$2a$10$zYJRscVUgHX1wqwu90WereuTmIg6h/JGirGG4SWBsZ60wVPCgtF8W', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FK_Reference_8`(`role_id` ASC) USING BTREE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1);
INSERT INTO `t_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
