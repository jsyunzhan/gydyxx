package domain.shiro.controller;

import domain.shiro.entity.WebSessionObject;
import org.apache.shiro.subject.Subject;

import static domain.shiro.entity.SystemConfig.LOGIN_SESSION;
import static org.apache.shiro.SecurityUtils.getSubject;

public class AbstractActionController {

    //获取登录Session
    private WebSessionObject getLoginSession(){
        final Subject securitySubject = getSubject();
        return (WebSessionObject) securitySubject.getSession().getAttribute(LOGIN_SESSION);
    }

    //获取当前登录id
    protected final Long getLoginId(){
        return getLoginSession().getId();
    }

    //获取当前用户名称
    protected final String getUserName(){
        return getLoginSession().getUserName();
    }

    //获取角色id
    protected final Long getRoleId(){
        return getLoginSession().getRoleId();
    }

    //获取角色名称
    protected final String getRoleName(){
        return getLoginSession().getRoleName();
    }
}
