/*
-- Query: SELECT `account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,0 as `amount`,`frozen_amount`,`md5`,now() as `create_datetime`,'' as `last_order`,`system_code` FROM std_account.tstd_account where user_id = 'SYS_ACCOUNT' and system_code = 'CD-CGD000006'
LIMIT 0, 10000

-- Date: 2017-03-14 15:36
*/
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('CGD2016100000000000000','SYS_USER_PIPE','平台','P','0','CNY',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c','2017-03-14 15:36:28','','CD-CGD000006');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('CGD2016100000000000001','SYS_USER_PIPE','平台','P','0','XNB',0,0,'776107f5a3b950391e92f668137b2b70','2017-03-14 15:36:28','','CD-CGD000006');

/*
-- Query: SELECT `company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code` FROM std_account.tstd_company_channel where system_code = 'CD-CGD000006'
LIMIT 0, 10000

-- Date: 2017-02-17 16:49
*/