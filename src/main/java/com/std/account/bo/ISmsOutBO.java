package com.std.account.bo;

public interface ISmsOutBO {
    /**
     * 发送下行短信
     * @param mobile
     * @param content
     * @param bizType 业务类型，直接决定短信模板的选用
     * @param remark 建议填写备注
     * @create: 2015年9月19日 上午10:25:52 myb858
     * @history:
     */
    public boolean sendSmsOut(String mobile, String content, String bizType,
            String remark);
}
