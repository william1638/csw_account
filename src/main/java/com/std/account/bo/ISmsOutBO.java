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

    /**
     * 验证短信码
     * @param mobile
     * @param smsCaptcha
     * @param bizType 
     * @create: 2016年1月14日 下午3:49:54 myb858
     * @history:
     */
    public void checkSmsCaptcha(String mobile, String smsCaptcha, String bizType);
}
