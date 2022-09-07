/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.104
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 192.168.1.104:3306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 07/09/2022 11:52:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'gateway', 'DEFAULT_GROUP', '#token超时时间设置，单位为秒\ntoken-overtime: 3600\n#uri白名单\npass-require: \n  path:\n    - ums/hello\n    - ums/login\n\nserver:\n  port: 9000\n  max-http-header-size: 102400\n\nspring:\n  redis:\n    host: localhost\n    port: 6379\n    password:\n    connect-timeout: 30000\n  cloud:\n    nacos:\n      discovery:\n        server-addr: localhost:8848\n        #自动刷新配置\n        refresh-enabled: true\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n      routes:\n        - id: ums\n          #nacos 配置管理——》配置列表的Data Id\n          uri: lb://ums\n          predicates:\n            #路径配置\n            - Path=/ums/**\n        \n        - id: rabbitmq\n          uri: lb://rabbitmq\n          predicates:\n            - Path=/rabbitmq/**', 'efc1068a9a9f98b2bd26e4b44b56b433', '2022-09-07 03:50:48', '2022-09-07 03:50:54', 'nacos', '192.168.1.101', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (2, 'ums', 'DEFAULT_GROUP', '#token过期时间\ntoken-overtime: 3600\n\nserver:\n  port: 9001\n\nspring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: localhost:8848\n  #jackson\n  jackson:\n    date-format: yyyy-MM-dd hh:mm:ss\n    time-zone: GMT+8\n  redis:\n    host: localhost\n    port: 6379\n    password: \n    connect-timeout: 30000\n\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: 123456\n    #url中database为对应的数据库名称\n    url: jdbc:mysql://localhost:3306/ums?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai\n\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: com.jw.ums.entity\n\nlogging:\n  level:\n    com.jw.ums.dao: debug\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'', '6a4883a5e16192b4fc7e67936398988a', '2022-09-07 03:50:48', '2022-09-07 03:51:01', 'nacos', '192.168.1.101', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (3, 'rabbitmq', 'DEFAULT_GROUP', 'server:\n  port: 9002\n\nspring:\n  rabbitmq:\n    host: 192.168.1.111\n    port: 5672\n    username: root\n    password: 123456\n    # 默认是禁用发布确认模式none，发布消息成功到交换机后厨触发回调方法correlated\n    publisher-confirm-type: correlated\n    #回退queue队列消息给生产者\n    publisher-returns: true\n', '25731a7e451a476afb39fbcae4e97904', '2022-09-07 03:50:48', '2022-09-07 03:51:08', 'nacos', '192.168.1.101', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (4, 'redis_config', 'DEFAULT_GROUP', 'spring:\n  redis:\n    host: localhost\n    port: 6379\n    password: \n    connect-timeout: 30000', '30d79cb7484fa08af438ec6511cc1b61', '2022-09-07 03:50:48', '2022-09-07 03:51:16', 'nacos', '192.168.1.101', '', '', '', '', '', 'yaml', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC, `datum_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id` ASC, `group_id` ASC, `tenant_id` ASC, `tag_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id` ASC, `tag_name` ASC, `tag_type` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint UNSIGNED NOT NULL,
  `nid` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create` ASC) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified` ASC) USING BTREE,
  INDEX `idx_did`(`data_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'gateway', 'DEFAULT_GROUP', '', '#token超时时间设置，单位为秒\ntoken-overtime: 3600\n#uri白名单\npass-require: \n  path:\n    - ums/hello\n    - ums/login\n\nserver:\n  port: 9000\n  max-http-header-size: 102400\n\nspring:\n  redis:\n    host: localhost\n    port: 6379\n    password:\n    connect-timeout: 30000\n  cloud:\n    nacos:\n      discovery:\n        server-addr: localhost:8848\n        #自动刷新配置\n        refresh-enabled: true\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n      routes:\n        - id: ums\n          #nacos 配置管理——》配置列表的Data Id\n          uri: lb://ums\n          predicates:\n            #路径配置\n            - Path=/ums/**\n        \n        - id: rabbitmq\n          uri: lb://rabbitmq\n          predicates:\n            - Path=/rabbitmq/**', 'efc1068a9a9f98b2bd26e4b44b56b433', '2022-09-07 03:50:47', '2022-09-07 03:50:48', NULL, '192.168.1.101', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 2, 'ums', 'DEFAULT_GROUP', '', '#token过期时间\ntoken-overtime: 3600\n\nserver:\n  port: 9001\n\nspring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: localhost:8848\n  #jackson\n  jackson:\n    date-format: yyyy-MM-dd hh:mm:ss\n    time-zone: GMT+8\n  redis:\n    host: localhost\n    port: 6379\n    password: \n    connect-timeout: 30000\n\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: 123456\n    #url中database为对应的数据库名称\n    url: jdbc:mysql://localhost:3306/ums?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai\n\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: com.jw.ums.entity\n\nlogging:\n  level:\n    com.jw.ums.dao: debug\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'', '6a4883a5e16192b4fc7e67936398988a', '2022-09-07 03:50:47', '2022-09-07 03:50:48', NULL, '192.168.1.101', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 3, 'rabbitmq', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9002\r\n\r\nspring:\r\n  rabbitmq:\r\n    host: 192.168.1.111\r\n    port: 5672\r\n    username: root\r\n    password: 123456\r\n    # 默认是禁用发布确认模式none，发布消息成功到交换机后厨触发回调方法correlated\r\n    publisher-confirm-type: correlated\r\n    #回退queue队列消息给生产者\r\n    publisher-returns: true\r\n', '8d6e2b969b30d4c878ac9c62f76e0a58', '2022-09-07 03:50:47', '2022-09-07 03:50:48', NULL, '192.168.1.101', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 4, 'redis_config', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: localhost\n    port: 6379\n    password: \n    connect-timeout: 30000', '30d79cb7484fa08af438ec6511cc1b61', '2022-09-07 03:50:47', '2022-09-07 03:50:48', NULL, '192.168.1.101', 'I', '');
INSERT INTO `his_config_info` VALUES (1, 5, 'gateway', 'DEFAULT_GROUP', '', '#token超时时间设置，单位为秒\ntoken-overtime: 3600\n#uri白名单\npass-require: \n  path:\n    - ums/hello\n    - ums/login\n\nserver:\n  port: 9000\n  max-http-header-size: 102400\n\nspring:\n  redis:\n    host: localhost\n    port: 6379\n    password:\n    connect-timeout: 30000\n  cloud:\n    nacos:\n      discovery:\n        server-addr: localhost:8848\n        #自动刷新配置\n        refresh-enabled: true\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n      routes:\n        - id: ums\n          #nacos 配置管理——》配置列表的Data Id\n          uri: lb://ums\n          predicates:\n            #路径配置\n            - Path=/ums/**\n        \n        - id: rabbitmq\n          uri: lb://rabbitmq\n          predicates:\n            - Path=/rabbitmq/**', 'efc1068a9a9f98b2bd26e4b44b56b433', '2022-09-07 03:50:54', '2022-09-07 03:50:54', 'nacos', '192.168.1.101', 'U', '');
INSERT INTO `his_config_info` VALUES (2, 6, 'ums', 'DEFAULT_GROUP', '', '#token过期时间\ntoken-overtime: 3600\n\nserver:\n  port: 9001\n\nspring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: localhost:8848\n  #jackson\n  jackson:\n    date-format: yyyy-MM-dd hh:mm:ss\n    time-zone: GMT+8\n  redis:\n    host: localhost\n    port: 6379\n    password: \n    connect-timeout: 30000\n\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: 123456\n    #url中database为对应的数据库名称\n    url: jdbc:mysql://localhost:3306/ums?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai\n\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: com.jw.ums.entity\n\nlogging:\n  level:\n    com.jw.ums.dao: debug\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'', '6a4883a5e16192b4fc7e67936398988a', '2022-09-07 03:51:01', '2022-09-07 03:51:01', 'nacos', '192.168.1.101', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 7, 'rabbitmq', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9002\r\n\r\nspring:\r\n  rabbitmq:\r\n    host: 192.168.1.111\r\n    port: 5672\r\n    username: root\r\n    password: 123456\r\n    # 默认是禁用发布确认模式none，发布消息成功到交换机后厨触发回调方法correlated\r\n    publisher-confirm-type: correlated\r\n    #回退queue队列消息给生产者\r\n    publisher-returns: true\r\n', '8d6e2b969b30d4c878ac9c62f76e0a58', '2022-09-07 03:51:07', '2022-09-07 03:51:08', 'nacos', '192.168.1.101', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 8, 'redis_config', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: localhost\n    port: 6379\n    password: \n    connect-timeout: 30000', '30d79cb7484fa08af438ec6511cc1b61', '2022-09-07 03:51:15', '2022-09-07 03:51:16', 'nacos', '192.168.1.101', 'U', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role` ASC, `resource` ASC, `action` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username` ASC, `role` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp` ASC, `tenant_id` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
