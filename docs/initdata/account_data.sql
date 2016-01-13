
INSERT INTO `tli_bank` VALUES 
(1,'ABC','中国农业银行','1','1','1','01'),
(2,'BJBANK','北京银行','2','1','1','01'),
(3,'BOC','中国银行','1','1','1','01'),
(4,'CCB','中国建设银行','1','1','1','01'),
(5,'CEB','中国光大银行','1','1','1','01'),
(6,'CGB','广发银行','1','1','1','01'),
(7,'CITIC','中信银行','1','1','1','01'),
(8,'CMB','招商银行','1','1','1','01'),
(9,'CMBC','中国民生银行','1','1','1','01'),
(10,'COMM','中国交通银行','1','1','1','01'),
(11,'ICBC','中国工商银行','1','1','1','01'),
(12,'PSBC','中国邮政','1','1','1','01'),
(101,'ABC','中国农业银行','1','1','1','13'),
(102,'BJBANK','北京银行','2','1','1','13'),
(103,'BOC','中国银行','1','1','1','13'),
(104,'CCB','中国建设银行','1','1','1','13'),
(105,'CEB','中国光大银行','1','1','1','13'),
(106,'CGB','广发银行','1','1','1','13'),
(107,'CITIC','中信银行','1','1','1','13'),
(108,'CMB','招商银行','1','1','1','13'),
(109,'CMBC','中国民生银行','1','1','1','13'),
(110,'COMM','中国交通银行','1','1','1','13'),
(111,'ICBC','中国工商银行','1','1','1','13'),
(112,'PSBC','中国邮政','1','1','1','13');

INSERT INTO `tli_channel` VALUES 
('01','线下柜台','1','银行柜台等线下充值渠道',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('13','线上易宝','1','通过易宝渠道',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('1', '0', '用户状态', 'user_status', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('2', '1', 'user_status', '0', '正常', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('3', '1', 'user_status', '1', '冻结', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('4', '0', '是否启用', 'is_enable', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('5', '4', 'is_enable', '0', '关闭', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('6', '4', 'is_enable', '1', '启用', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('7', '0', '订单状态', 'order_status', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('8', '7', 'order_status', '1', '待审批', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('9', '7', 'order_status', '2', '审批不通过', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('10', '7', 'order_status', '3', '审批通过-待支付', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('11', '7', 'order_status', '4', '不用审批-待支付', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('12', '7', 'order_status', '5', '支付失败-待对账', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('13', '7', 'order_status', '6', '支付成功-待对账', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('14', '7', 'order_status', '7', '已对账', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('15', '0', '账户操作方向', 'account_direction', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('16', '15', 'account_direction', '1', '加钱', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('17', '15', 'account_direction', '0', '减钱', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('18', '0', '对方系统', 'op_system', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('19', '18', 'op_system', '1', 'P2P', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('20', '18', 'op_system', '2', 'CPZC', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('21', '0', '角色等级', 'r_level', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('22', '21', 'r_level', '1', '管理员角色', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('23', '21', 'r_level', '2', '财务角色', now(),'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('24', '21', 'r_level', '3', '运维角色', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('25', '21', 'r_level', '4', '客服角色', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('26', '0', '用户类别', 'user_type', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('27', '26', 'user_type', '1001', '普通用户', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('28', '26', 'user_type', '2001', '法人', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('29', '0', '验证码状态', 'sms_status', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('30', '29', 'sms_status', '0', '待发送', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('31', '29', 'sms_status', '1', '发送成功', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('32', '29', 'sms_status', '2', '发送失败', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('33', '29', 'sms_status', '3', '已验证', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('34', '0', '验证码业务类型', 'sms_type', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('35', '34', 'sms_type', '1', '平台主账户-注册', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('36', '34', 'sms_type', '2', '平台主账户-找回登录密码', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('37', '34', 'sms_type', '3', '平台主账户-重置登录密码', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('38', '34', 'sms_type', '4', '平台主账户-设置交易密码', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('39', '34', 'sms_type', '5', '平台主账户-找回交易密码', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('40', '34', 'sms_type', '6', '平台主账户-重置交易密码', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('41', '34', 'sms_type', '7', '平台主账户-修改手机号码', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('42', '0', '证件类型', 'id_kind', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('43', '42', 'id_kind', '1', '身份证', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('44', '0', '登录是否成功', 'login_status', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('45', '44', 'login_status', '0', '失败', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('46', '44', 'login_status', '1', '成功', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('47', '44', 'login_status', '2', '注册成功', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('48', '0', '支付渠道', 'channel', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('49', '48', 'channel', '11', '线上-联动优势', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('50', '48', 'channel', '12', '线上-通联支付', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('51', '48', 'channel', '13', '线上-易宝支付', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('52', '48', 'channel', '01', '线下', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('53', '0', '账户状态', 'account_status', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('54', '53', 'account_status', '0', '正常', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('55', '53', 'account_status', '1', '程序锁定', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('56', '53', 'account_status', '2', '人工锁定', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('57', '0', '是否成功', 'user_isSuccess', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('58', '57', 'user_isSuccess', '1', '成功', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('59', '57', 'user_isSuccess', '0', '失败', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('60', '0', '锁的方向', 'lock_direction', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('61', '60', 'lock_direction', '1', '用锁', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('62', '60', 'lock_direction', '0', '解锁', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('63', '0', '红冲蓝补状态', 'rb_order_status', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('64', '63', 'rb_order_status', '1', '待审批', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('65', '63', 'rb_order_status', '2', '审批不通过', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('66', '63', 'rb_order_status', '3', '审批通过', now(), 'admin');

INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('67', '0', '业务类型', 'biz_type', '', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('68', '67', 'biz_type', '10', '虚拟币兑换', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('69', '67', 'biz_type', '11', '充值', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('70', '67', 'biz_type', '-11', '取现', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('71', '67', 'biz_type', '12', '转入', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('72', '67', 'biz_type', '-12', '转出', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('73', '67', 'biz_type', '19', '蓝补', now(), 'admin');
INSERT INTO `tsys_dict` (`id`, `p_id`, `dkey`, `dvalue`, `remark`, `create_datetime`, `creator`) VALUES ('74', '67', 'biz_type', '-19', '红冲', now(), 'admin');