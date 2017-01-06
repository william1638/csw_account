/*
-- Query: SELECT * FROM std_account.tstd_account where user_id = 'CD-CZH000001'
-- Date: 2016-12-29 15:08
*/
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000000','SYS_ACCOUNT',NULL,'P','0','CNY',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000001','SYS_ACCOUNT',NULL,'P','0','FRB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000002','SYS_ACCOUNT',NULL,'P','0','GXB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000003','SYS_ACCOUNT',NULL,'P','0','QBB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000004','SYS_ACCOUNT',NULL,'P','0','GWB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000005','SYS_ACCOUNT',NULL,'P','0','HBB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000006','SYS_ACCOUNT',NULL,'P','0','HBYJ',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');

/*
-- Query: SELECT * FROM std_account.tstd_company_channel
-- Date: 2017-01-06 12:02
*/
INSERT INTO `tstd_company_channel` (`company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code`) VALUES ('CD-CZH000001','正汇科技','36','1','1300851701','r2jgDFSdiikklwlllejlwjio3242342n','wx3eb3d4d796093674',NULL,NULL,NULL,NULL,NULL,'http://121.43.101.148:5602/std-mall/storepurchase/wechat/app/callback',NULL,'微信APP支付','CD-CZH000001');
INSERT INTO `tstd_company_channel` (`company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code`) VALUES ('CD-CQQ000004','骑来骑去','35','1','1400666002','r2jgDFSdiikklwlllejlwjio3242342n','wx8bc03dd744895352','44ebf0ef908dc54656573625a579ea82',NULL,NULL,NULL,NULL,'http://121.43.101.148:5702/cd-qlqq/activity/wechat/h5/callback',NULL,'微信公众号支付','CD-CQQ000004');