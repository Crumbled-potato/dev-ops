package cn.bugstack.chatgpt.domain.security.model.vo;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @description:
 * @author：cheese，微信：
 * @date: 2024-02-21
 * @Copyright： 公众号：
 */
public class JwtToken implements AuthenticationToken {

    private String jwt;

    public JwtToken(String jwt) {
        this.jwt = jwt;
    }

    /**
     * 等同于账户
     */
    @Override
    public Object getPrincipal() {
        return jwt;
    }

    /**
     * 等同于密码
     */
    @Override
    public Object getCredentials() {
        return jwt;
    }
}
