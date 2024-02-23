package cn.bugstack.chatgpt.application;

/**
 * @description:微信公众号验签服务
 * @author：cheese，微信：
 * @date: 2024-02-22
 * @Copyright： 公众号：
 */
public interface IWeiXinValidateService {

    boolean checkSign(String signature, String timestamp, String nonce);
}
