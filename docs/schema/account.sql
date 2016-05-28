

DROP TABLE IF EXISTS `tstd_account`;
CREATE TABLE `tstd_account` (
  `account_number` varchar(32) NOT NULL COMMENT '账号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `currency` varchar(8) NOT NULL COMMENT '币种',
  `amount` bigint(32) NOT NULL COMMENT '余额',
  `frozen_amount` bigint(32) NOT NULL COMMENT '冻结金额',
  `md5` varchar(32) NOT NULL COMMENT 'md5',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tstd_account_frozen_jour`;
CREATE TABLE `tstd_account_frozen_jour` (
  `afj_no` bigint(32) NOT NULL AUTO_INCREMENT,
  `biz_type` varchar(4) NOT NULL,
  `ref_no` varchar(32) NOT NULL,
  `trans_amount` bigint(32) NOT NULL,
  `pre_amount` bigint(32) NOT NULL,
  `post_amount` bigint(32) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`afj_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tstd_account_jour`;
CREATE TABLE `tstd_account_jour` (
  `aj_no` bigint(32) NOT NULL AUTO_INCREMENT,
  `biz_type` varchar(4) NOT NULL,
  `ref_no` varchar(32) NOT NULL,
  `trans_amount` bigint(32) NOT NULL,
  `pre_amount` bigint(32) NOT NULL,
  `post_amount` bigint(32) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_datetime` datetime NOT NULL,
  `account_number` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `work_date` varchar(8) NOT NULL,
  `check_user` varchar(32) DEFAULT NULL,
  `check_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`aj_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tstd_charge`;
CREATE TABLE `tstd_charge` (
  `code` varchar(32) NOT NULL,
  `from_type` varchar(32) NOT NULL COMMENT '来方类型：银行卡/三方账户',
  `from_code` varchar(32) NOT NULL COMMENT '来方账号：银行卡号/支付宝号',
  `channel` varchar(2) NOT NULL COMMENT '渠道:线下/三方支付公司/银联',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '渠道对应订单号',
  `amount` bigint(32) NOT NULL COMMENT '充值金额',
  `fee` bigint(32) DEFAULT NULL COMMENT '手续费',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审批时间',
  `account_number` varchar(32) NOT NULL COMMENT '账号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tstd_withdraw`;
CREATE TABLE `tstd_withdraw` (
  `code` varchar(32) NOT NULL,
  `to_type` varchar(32) NOT NULL COMMENT '去方类型：银行卡/三方账户',
  `to_code` varchar(32) NOT NULL COMMENT '去方账号：银行卡号/支付宝号',
  `channel` varchar(2) NOT NULL COMMENT '渠道:线下/三方支付公司/银联',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '渠道对应订单号',
  `amount` bigint(32) NOT NULL COMMENT '取现金额',
  `fee` bigint(32) DEFAULT NULL COMMENT '手续费',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审批时间',
  `pay_user` varchar(32) DEFAULT NULL COMMENT '支付人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付说明',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `account_number` varchar(32) NOT NULL COMMENT '账号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tstd_zzorder`;
CREATE TABLE `tstd_zzorder` (
  `code` varchar(32) NOT NULL,
  `direction` char(1) NOT NULL COMMENT '方向',
  `amount` bigint(32) NOT NULL COMMENT '金额',
  `fee` bigint(20) DEFAULT NULL COMMENT '手续费',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `account_number` varchar(32) NOT NULL COMMENT '账号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `tstd_hlorder`;
CREATE TABLE `tstd_hlorder` (
  `code` varchar(32) NOT NULL,
  `direction` char(1) NOT NULL COMMENT '方向',
  `amount` bigint(32) NOT NULL COMMENT '金额',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) NOT NULL COMMENT '申请人',
  `apply_note` varchar(255) NOT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审批时间',
  `account_number` varchar(32) NOT NULL COMMENT '账号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;







