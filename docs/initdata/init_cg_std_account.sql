/*
-- Query: SELECT * FROM std_account.tstd_account where user_id = 'CD-CCG000007'
-- Date: 2016-12-29 15:08
*/
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('CGA2016100000000000000','SYS_USER_CAIGO','平台','P','0','CNY',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CCG000007');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('CGA2016100000000000001','SYS_USER_CAIGO','平台','P','0','XNB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CCG000007');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('CGA2016100000000000002','SYS_USER_CAIGO','平台','P','0','CGB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CCG000007');

/*
-- Query: SELECT `company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code` FROM std_account.tstd_company_channel where company_code = 'CD-CCG000007'
LIMIT 0, 50000

-- Date: 2017-01-15 20:51
*/
INSERT INTO `tstd_company_channel` (`company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code`) VALUES ('CD-CCG000007','菜狗商城','35','1','1400666002','r2jgDFSdiikklwlllejlwjio3242342n','wx8bc03dd744895352','44ebf0ef908dc54656573625a579ea82',NULL,NULL,NULL,NULL,'http://121.43.101.148:5602/xn-zhpay/zhpay/h5/callback',NULL,NULL,'CD-CCG000007');

/*
-- Query: SELECT `type`,`parent_key`,`dkey`,`dvalue`,`updater`,now() as `update_datetime`,`remark`,`system_code` FROM std_account.tsys_dict
LIMIT 0, 10000

-- Date: 2017-03-25 21:53
*/
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'bankcard_status','银行卡状态','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','bankcard_status','0','弃用','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','bankcard_status','1','启用','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'currency','货币','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency','CNY','人民币','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency','XNB','积分','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency','CGB','菜狗币','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'account_type','账户类型','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_type','C','C端用户','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_type','B','B端用户','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_type','P','平台用户','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'account_status','账户状态','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_status','0','正常','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_status','1','程序锁定','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_status','2','人工锁定','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'jour_status','流水状态','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','0','刚生成待回调','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','1','已回调通过待对账','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','2','回调不通过','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','3','已对账且账不平','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','4','账不平待调账','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','5','已调账','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','9','无需对账','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','6','待审批','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','7','审批通过','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','8','审批不通过','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'channel_type','渠道类型','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','01','线下_橙账本','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','0','内部账','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','9','调账','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','10','轧账','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','1','币种兑换','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','30','支付宝APP支付','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','35','微信公众号支付','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','36','微信APP支付','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','40','网银代付','admin',now(),'','CD-CCG000007');
