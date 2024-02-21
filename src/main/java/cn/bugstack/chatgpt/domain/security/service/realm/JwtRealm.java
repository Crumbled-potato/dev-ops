package cn.bugstack.chatgpt.domain.security.service.realm;

import cn.bugstack.chatgpt.domain.security.model.vo.JwtToken;
import cn.bugstack.chatgpt.domain.security.service.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:自定义 Realm
 * @author：cheese，微信：
 * @date: 2024-02-21
 * @Copyright： 公众号：
 */
public class JwtRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(JwtRealm.class);

    private static JwtUtil jwtUtil = new JwtUtil();

    // 覆盖了 supports 方法，用于判断传入的 AuthenticationToken 是否为 JwtToken 类型。
    // 这是为了确保仅处理 JwtToken 类型的身份验证请求
    @Override
    public boolean supports(AuthenticationToken token){

        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        // 暂时不需要实现
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
        String jwt = (String) token.getPrincipal();
        if (jwt == null) {
            throw new NullPointerException("jwtToken 不允许为空");
        }
        // 判断
        if (!jwtUtil.isVerify(jwt)) {
            throw new UnknownAccountException();
        }
        // 可以获取username信息，并做一些处理
        String username = (String) jwtUtil.decode(jwt).get("username");
        logger.info("鉴权用户 username：{}", username);
        return new SimpleAuthenticationInfo(jwt,jwt,"JwtRealm");
    }

}
