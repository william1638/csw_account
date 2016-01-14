
DROP TABLE IF EXISTS  `tstd_user`;
CREATE TABLE `tstd_user` (
  `user_id` varchar(32) NOT NULL,
  `mobile` varchar(16) NOT NULL  COMMENT '手机号',
  `login_pwd` varchar(32) NOT NULL  COMMENT '登陆密码',
  `login_pwd_strength` char(1) NOT NULL  COMMENT '登陆密码强度',
  `user_kind` varchar(4) NOT NULL  COMMENT '身份标识',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `trade_pwd` varchar(32) DEFAULT NULL  COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL  COMMENT '安全密码强度',
  `create_datetime` datetime DEFAULT NULL  COMMENT '创建时间',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `serveList` varchar(32) DEFAULT NULL COMMENT '拥有的服务list',
  `quoteList` varchar(32) DEFAULT NULL COMMENT '拥有的报价list',
  `level` varchar(32) DEFAULT NULL COMMENT '用户等级',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_company`;
CREATE TABLE `tstd_company` (
  `company_id` varchar(32) NOT NULL COMMENT '编号',
  `company_name` varchar(64) NOT NULL COMMENT '公司名字',
  `licence_no` varchar(255) DEFAULT NULL COMMENT '工商营业执照号',
  `id_kind` char(1) DEFAULT NULL COMMENT '法人证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '法人证件号码',
  `real_name` varchar(16) DEFAULT NULL COMMENT '法人真实姓名',
  `capital` bigint(32) DEFAULT NULL COMMENT '注册资金',
  `province` varchar(16) NOT NULL COMMENT '省',
  `city` varchar(16) NOT NULL COMMENT '市',
  `address` varchar(255) NOT NULL COMMENT '地址',
  `gsyyzz_picture` varchar(255) DEFAULT NULL COMMENT '工商营业执照',
  `zzjgdmz_picture` varchar(255) DEFAULT NULL COMMENT '组织机构代码证',
  `swdjz_picture` varchar(255) DEFAULT NULL COMMENT '税务登记证',
  `dzz_picture` varchar(128) DEFAULT NULL COMMENT '电子章',
  `sqgh_picture` varchar(255) DEFAULT NULL COMMENT '申请公函',
  `fr_picture` varchar(255) DEFAULT NULL COMMENT '法人身份证照片',
  `other_picture` varchar(255) DEFAULT NULL COMMENT '其他照片',
  `apply_datetime` datetime NOT NULL COMMENT '申请时间(重新提交也要更新)',
  `kyc_datetime` datetime NOT NULL COMMENT 'KYC（通过）时间',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_user_company`;
CREATE TABLE `tstd_company` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `company_id` varchar(32) NOT NULL COMMENT '公司编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS  `tpz_sms_captcha`;
CREATE TABLE `tpz_sms_captcha` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(16) DEFAULT NULL,
  `biz_type` varchar(2) DEFAULT NULL,
  `sms_captcha` varchar(8) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `create_datetime` datetime NOT NULL,
  `sent_datetime` datetime  DEFAULT NULL,
  `invalid_datetime` datetime  DEFAULT NULL,
  `check_datetime` datetime  DEFAULT NULL,
  `check_times` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS  `tpz_user_ext`;
CREATE TABLE `tpz_user_ext` (
  `user_id` varchar(32) NOT NULL,
  `photo` varchar(256) DEFAULT NULL,
  `comefrom` varchar(64) DEFAULT NULL,
  `birthday` varchar(8) DEFAULT NULL,
  `qq` varchar(16) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `occupation` varchar(8) DEFAULT NULL,
  `education` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_user_identify_log`;
CREATE TABLE `tpz_user_identify_log` (
  `user_id` varchar(32) DEFAULT NULL,
  `real_name` varchar(16) DEFAULT NULL,
  `id_kind` varchar(1) DEFAULT NULL,
  `id_no` varchar(32) DEFAULT NULL,
  `error_code` varchar(1) DEFAULT NULL,
  `error_info` varchar(256) DEFAULT NULL,
  `create_datetime` datetime NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_user_lock`;
CREATE TABLE `tpz_user_lock` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL,
  `lock_direction` char(1) NOT NULL,
  `remark` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_user_login_log`;
CREATE TABLE `tpz_user_login_log` (
  `user_id` varchar(32) NOT NULL,
  `login_datetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `login_ip` varchar(64) NOT NULL,
  `is_success` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tpz_user_picture`;
CREATE TABLE `tpz_user_picture` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `id_pic1` varchar(255) DEFAULT NULL,
  `id_pic2` varchar(255) DEFAULT NULL,
  `id_user_pic` varchar(255) DEFAULT NULL,
  `real_name` varchar(16) DEFAULT NULL,
  `id_kind` varchar(1) DEFAULT NULL,
  `id_no` varchar(32) DEFAULT NULL,
  `create_datetime` timestamp NULL DEFAULT NULL,
  `verify_datetime` timestamp NULL DEFAULT NULL,
  `verify_status` varchar(1) DEFAULT NULL,
  `verify_user` varchar(32) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS  `tstd_bankcard`;
CREATE TABLE `tstd_bankcard` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `type` char(1) NOT NULL COMMENT '类型(对公对私)',
  `bank_code` varchar(8) DEFAULT NULL COMMENT '银行行号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `bankcard_no` varchar(64) DEFAULT NULL  COMMENT '银行卡编号',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '银行卡绑定手机号',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime NOT NULL,
  `update_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS  `tstd_account`;
CREATE TABLE `tstd_account` (
  `account_number` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `amount` bigint(32) NOT NULL,
  `frozen_amount` bigint(32) NOT NULL,
  `currency` varchar(8) NOT NULL,
  `md5` varchar(32) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_account_frozen_jour`;
CREATE TABLE `tstd_account_frozen_jour` (
  `afj_no` bigint(32) NOT NULL AUTO_INCREMENT,
  `biz_type` varchar(4) NOT NULL,
  `ref_no` varchar(32) NOT NULL,
  `trans_amount` bigint(20) NOT NULL,
  `pre_amount` bigint(20) NOT NULL,
  `post_amount` bigint(20) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`afj_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_account_jour`;
CREATE TABLE `tstd_account_jour` (
  `aj_no` bigint(32) NOT NULL AUTO_INCREMENT,
  `status` char(1) NOT NULL,
  `biz_type` varchar(4) NOT NULL,
  `ref_no` varchar(32) NOT NULL,
  `trans_amount` bigint(20) NOT NULL,
  `pre_amount` bigint(20) NOT NULL,
  `post_amount` bigint(20) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_datetime` datetime NOT NULL,
  `work_date` varchar(8) NOT NULL,
  `check_user` varchar(32) DEFAULT NULL,
  `check_datetime` datetime DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`aj_no`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_bank`;
CREATE TABLE `tstd_bank` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `bank_no` varchar(8) NOT NULL,
  `bank_name` varchar(64) NOT NULL,
  `bank_type` varchar(4) DEFAULT NULL,
  `quick_type` char(1) DEFAULT NULL,
  `is_enable` char(1) DEFAULT NULL,
  `channel_no` char(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_channel`;
CREATE TABLE `tstd_channel` (
  `channel_no` varchar(4) NOT NULL,
  `channel_name` varchar(16) NOT NULL,
  `channel_status` char(1) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `merchant_id` varchar(8) DEFAULT NULL,
  `sign_type` varchar(8) DEFAULT NULL,
  `sign_key` varchar(8) DEFAULT NULL,
  `cer_path` varchar(8) DEFAULT NULL,
  `poundage_type` varchar(8) DEFAULT NULL,
  `channel_version` varchar(8) DEFAULT NULL,
  `business_web_gateway` varchar(8) DEFAULT NULL,
  `business_wap_gateway` varchar(8) DEFAULT NULL,
  `business_file_gateway` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`channel_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_cqorder`;
CREATE TABLE `tstd_cqorder` (
  `cq_no` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `direction` char(1) NOT NULL,
  `channel` varchar(2) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `bank_code` varchar(12) DEFAULT NULL,
  `bankcard_no` varchar(32) DEFAULT NULL,
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `create_datetime` datetime NOT NULL,
  `approve_user` varchar(32) DEFAULT NULL,
  `approve_datetime` datetime DEFAULT NULL,
  `pay_user` varchar(32) DEFAULT NULL,
  `pay_datetime` datetime DEFAULT NULL,
  `pay_no` varchar(32) DEFAULT NULL,
  `pay_fee` bigint(20) DEFAULT NULL,
  `work_date` varchar(8) DEFAULT NULL,
  `check_user` varchar(32) DEFAULT NULL,
  `check_datetime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`cq_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_hlorder`;
CREATE TABLE `tstd_hlorder` (
  `hl_no` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `direction` char(1) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `apply_user` varchar(32) NOT NULL,
  `apply_note` varchar(255) NOT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `approve_user` varchar(32) DEFAULT NULL,
  `approve_datetime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`hl_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_xnborder`;
CREATE TABLE `tstd_xnborder` (
  `xnb_no` varchar(20) NOT NULL,
  `status` char(1) NOT NULL,
  `type` char(1) NOT NULL,
  `score` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_datetime` datetime NOT NULL,
  `approve_user` varchar(32) DEFAULT NULL,
  `approve_datetime` datetime DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`xnb_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_ytorder`;
CREATE TABLE `tstd_ytorder` (
  `yt_no` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `biz_type` varchar(2) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `approve_user` varchar(32) DEFAULT NULL,
  `approve_datetime` datetime DEFAULT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`yt_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `tstd_zzorder`;
CREATE TABLE `tstd_zzorder` (
  `zz_no` varchar(32) NOT NULL,
  `status` char(1) NOT NULL,
  `direction` char(1) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `create_datetime` datetime NOT NULL,
  `opposite_system` char(2) NOT NULL,
  `opposite_account` varchar(32) DEFAULT NULL,
  `remark` varchar(255) NOT NULL,
  `account_number` varchar(32) NOT NULL,
  PRIMARY KEY (`zz_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



SET FOREIGN_KEY_CHECKS = 1;

