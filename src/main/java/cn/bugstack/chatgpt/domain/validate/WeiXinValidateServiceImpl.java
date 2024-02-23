package cn.bugstack.chatgpt.domain.validate;

import cn.bugstack.chatgpt.application.IWeiXinValidateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import cn.bugstack.chatgpt.infrastructure.util.sdk.SignatureUtil;

/**
 * @description:
 * @author：cheese，微信：
 * @date: 2024-02-22
 * @Copyright： 公众号：
 */
@Service
public class WeiXinValidateServiceImpl implements IWeiXinValidateService {
    @Value("${wx.config.token}")
    private String token;

    @Override
    public boolean checkSign(String signature, String timestamp, String nonce){
        return SignatureUtil.check(token,signature,timestamp,nonce);
    }
}
