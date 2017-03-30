INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'bankcard_status','银行卡状态','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','bankcard_status','0','弃用','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','bankcard_status','1','启用','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'currency','货币','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency','CNY','人民币','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency','FRB','分润','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency','GXJL','贡献值','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency','QBB','钱包币','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency','GWB','购物币','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency','HBB','红包币','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency','HBYJ','红包业绩','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'account_type','账户类型','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_type','C','C端用户','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_type','B','B端用户','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_type','P','平台用户','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'account_status','账户状态','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_status','0','正常','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_status','1','程序锁定','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_status','2','人工锁定','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'jour_status','流水状态','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','0','刚生成待回调','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','1','已回调通过待对账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','2','回调不通过','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','3','已对账且账不平','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','4','账不平待调账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','5','已调账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','9','无需对账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','6','待审批','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','7','审批通过','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','8','审批不通过','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'channel_type','渠道类型','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','01','线下_橙账本','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','0','内部账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','9','调账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','10','轧账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','1','币种兑换','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','30','支付宝APP支付','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','35','微信公众号支付','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','36','微信APP支付','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','40','网银代付','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'biz_type','业务类型','admin',now(),'','CD-CZH000001');         
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','biz_type','11','充值','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','biz_type','-11','取现','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','biz_type','19','蓝补','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','biz_type','-19','红冲','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','biz_type','-30','购物','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','biz_type','30','购物退款','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','biz_type','-31','正汇O2O支付','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','biz_type','42','确认收货，商户收钱','admin',now(),'','CD-CZH000001');

/*
-- Query: SELECT * FROM std_account.tstd_account where user_id = 'CD-CZH000001'
-- Date: 2016-12-29 15:08
*/
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000000','SYS_ACCOUNT','平台','P','0','CNY',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000001','SYS_ACCOUNT','平台','P','0','FRB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000002','SYS_ACCOUNT','平台','P','0','GXJL',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000003','SYS_ACCOUNT','平台','P','0','QBB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000004','SYS_ACCOUNT','平台','P','0','GWB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000005','SYS_ACCOUNT','平台','P','0','HBB',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`create_datetime`,`last_order`,`system_code`) VALUES ('A2016100000000000006','SYS_ACCOUNT','平台','P','0','HBYJ',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',now(),NULL,'CD-CZH000001');

/*
-- Query: SELECT `company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code` FROM std_account.tstd_company_channel where company_code = 'CD-CZH000001'
LIMIT 0, 50000

-- Date: 2017-01-15 20:51
*/
INSERT INTO `tstd_company_channel` (`company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code`) VALUES ('CD-CZH000001','正汇钱包','36','1','1360736402','r2jgDFSdiikklwlllejlwjio3242342n','wx9324d86fb16e8af0',NULL,NULL,NULL,NULL,NULL,'http://139.224.200.54:5602/xn-zhpay/zhpay/app/callback',NULL,NULL,'CD-CZH000001');
INSERT INTO `tstd_company_channel` (`company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code`) VALUES ('CD-CZH000001','正汇钱包','30','1','2088102169104921','MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC6UXrwPBdI6vFDr8NISu25/OMrrSu+/dh73PL9qSQdBWwJhisQ4tD7VHgd5AmgnADdX5by2nhM0FnN0WxZzUUIactl0HOudnF1ec3samu77Nqp2TxFr3oCrdtsK3TRJgGLeJg3T7Au4TqZOfmDfEWlESosZkMedeZYHvuOHnfQ8Sj1256+hwfJpLjADrwE0UwkkGEYtsX0LusDbQPPk2ICgiB3Z6l+1XKcafYicpr/HGUTo3MzIYE0mIo2rwzdQTjy5LTiQKJSku6Q3k+UuucbAGk3FAMwWRU8xNDSFeDXNGvt0H8weWqNrGaJpqUqrzBOtcasCbtA/EO7PeRDc6KPAgMBAAECggEADkHj7XQ7t+m6kpplkCejr2LJo3L58QCNy1SqB30k9U+P596rdvxUT5c0Jd1oWzD04/vHmkfbgs1CwfwvlWOez7kAxobAA26u2fV7K4ctPVEvnmWwNgjqhj/1lX5xzvtgjOKqizj+22eCy2zjSgt9bNc2afVdV3xgJwgf6c0tEADyxxSs/4cvfugkdnUYEFa0dMLZz9wP4jwKO+OCdRrdwg5tohiZ4xsdUiw3ihikwJwFuOczFsbls8hm9H0BMrZG1BF+7gaQXNia2nx8Mcpg2GW7jKaBgyl+J1Gq2Si+9lA8cPaD4+w+0dSvUKBXDYkSo3DQxFXFgzIh+UnNd77CqQKBgQDqGW5huM9HOA6SOGx1mdx7dp3G0CiPMZIVOvXDSgLQ22i6T1JKWnQLn7JvJDIHVsfODoJsk6hD4v/Vfad3xAByvt25PGKC4jsynhXjW9aacUjP8pJnCrUY/71E3jOb8PtBj44WFrcI7rEIGo9CeQLjjolaEzt+pmY6XUqqva8ePQKBgQDLv7cZYZe+n8vIKiN/xbq7+1GseIvG2bCQ+b04OIS7KxJEsbGgfz5p1MVTHRNBQUc/8BoIaSkSTXTVWOkSiQV7MqHpCk18u0f53RIZgyK/lLUZoYLGlXvlvICsHvz14DCNFSmb+NPAGtjNfvwKcLwqoAq5AaDdJdwqk7a/Ip98uwKBgC842g9UXFudbzMHtrkyREuTbdhA/S2FblZiAllWgKLo34NVpN/ixy3hFPleb8kTNFLYkm6cioDHNZw3MKT0ILME1W0cRTLpt89KHFg0mmGlIG7yG541hrp4PBG8UOTQA58B+kNyaZGftI5R7TwXdQG6KvLElmfB4TU8xGcUxFJxAoGAMipZ4p3sxrqBah817le4yl/ulBB6GdOvbYKP3C/OPmc0cEVv/pxVuajxjK0z+HUiHdP4FOoPeroncgAaIr4p41NsGfy3Z2aVKigMNMvc2l662Dyz/kD/6GipJbfbgI5aZeI4BHcXa80wZChH1io7qiMNgM+t08Vml/UWoajcq9ECgYAOEGZAhINFGchLkxZR2JvmzC8TGwMYjtCFaQjjlWz1krEohe5u3g/HgqG05JuFG1pI5G11Z4+zzzLDw+tp93vAwfbwP0HFrvDOrNLfLBjPauJyzwNRU8ZTbLcATI7s/FDUY7zTufQcgynwPaLPoLWnK71b/lLNTUpIxxrREoqSpg==','MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1kT/LTc3iEJDlRuMPmztYBS9hTo7kzwg9lrQjfrrGPnn+R+AEjUsphWMRgvAW+Y0pq+RdKaIrohKsWP5rdxSXEdR5fdxNFw2PgqUuA0f7ckdll0IkJD8NQe1mjyuoQ7LXkVBaV3lUD2t629bslsd1DFv+uOod3yfzt2nd59k72fMUNPMXcHwfsNj/r5HMeZahWoAgPuDBNJ3DQtzzMgqR2GSmhEPlHgvgmE8rckuaTbQFf6Be2y6UIk90pmHpcOXHOmt/OAkmEeDGVRgIDdl2Ek9HL1KHLneKbpZPAL0ul+cV7EPBUtWdTVzg13QedXxnzkEeZoclpCkmKP10RcH7wIDAQAB','2016073000123146',NULL,NULL,NULL,NULL,'http://139.224.200.54:5602/xn-zhpay/zhpay/app/callback',NULL,NULL,'CD-CZH000001');
INSERT INTO `tstd_company_channel` (`company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code`) VALUES ('CD-CZH000001','正汇钱包','37','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CD-CZH000001');

/*
-- Query: SELECT bank_code,bank_name,channel_type,status,channel_bank,max_order,order_amount,day_amount,month_amount,remark FROM std_account.tstd_channel_bank
LIMIT 0, 50000

-- Date: 2017-01-15 20:50
*/
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('ICBC','中国工商银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('ABC','中国农业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CCB','中国建设银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('BOC','中国银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('BCM','中国交通银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CITIC','中信银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CEB','中国光大银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('PAB','平安银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('PSBC','中国邮政储蓄银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('SHB','上海银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('SPDB','浦东发展银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tstd_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
