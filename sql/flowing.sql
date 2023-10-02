/*
Navicat MySQL Data Transfer

Source Server         : wcg
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : flowing-project

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2023-10-02
*/


/*创建表实例*/
-- -------------------------------------
-- Table structure for fp_example_table
-- -------------------------------------
/*DROP TABLE IF EXISTS `fp_example_table`;
CREATE TABLE `fp_example_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `concent` text,
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实例表';*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------------
-- Table structure for fp_bank_account
-- 账号,所属银行,所属人/公司,账号状态,账户余额,备注,公/私,开户行
-- ----------------------------------
DROP TABLE IF EXISTS `fp_bank_account`;
CREATE TABLE `fp_bank_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
	`account_number` varchar(255) DEFAULT NULL COMMENT '卡号',
	`account_belong` varchar(255) DEFAULT NULL COMMENT '所属人/公司',
	`bank` varchar(64) DEFAULT NULL COMMENT '所属银行',
	`bank_belong` varchar(64) DEFAULT NULL COMMENT '开户行',
	`balance` bigint NULL DEFAULT NULL COMMENT '账户余额',
	`status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
	`main_body` int(1) DEFAULT '1' COMMENT '公/私：1->公户；2->个人账户',
	`remark` varchar(500) DEFAULT NULL COMMENT '备注',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行账户表';


-- -------------------------------------
-- Table structure for fp_contract
-- -------------------------------------
DROP TABLE IF EXISTS `fp_contract`;
CREATE TABLE `fp_contract` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pro_id` bigint(20) DEFAULT NULL COMMENT '关联项目表ID',
  `name` varchar(255) DEFAULT NULL COMMENT '合同名称',
  `party_a` varchar(255) DEFAULT NULL COMMENT '甲方',
  `party_b` varchar(255) DEFAULT NULL COMMENT '乙方',
  `money`  bigint NULL DEFAULT NULL COMMENT '金额',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同表';


-- -------------------------------------
-- Table structure for fp_project
--一个项目多个合同
-- -------------------------------------
DROP TABLE IF EXISTS `fp_project`;
CREATE TABLE `fp_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `money` bigint NULL DEFAULT NULL COMMENT '金额',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目表';


-- -------------------------------------
-- Table structure for fp_flowing
-- 所属账号;所属合同;所属项目;金额;状态;资金类型(投资/回款/费用/其他);费用类型;
-- -------------------------------------
DROP TABLE IF EXISTS `fp_flowing`;
CREATE TABLE `fp_flowing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `flowing_number` bigint NULL DEFAULT NULL COMMENT '流水号',
  `acc_id` bigint(20) DEFAULT NULL COMMENT '所属账户',
  `pro_id` bigint(20) DEFAULT NULL COMMENT '关联项目表ID',
  `con_id` bigint(20) DEFAULT NULL COMMENT '关联合同表ID',
  `money` bigint NULL DEFAULT NULL COMMENT '金额',
  `money_type` int(1) DEFAULT '9' COMMENT '资金类型: 1->投资;2->回款;3->费用;9->其他',
  `cost_type` int(1) DEFAULT '9' COMMENT '费用类型: 1->费用1;2->费用2;3->费用3;9->其他', 
  `status` int(1) DEFAULT '1' COMMENT '状态：0->无效；1->有效',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流水明细表';



回款10万,费用3万,只回了7万,如何入账