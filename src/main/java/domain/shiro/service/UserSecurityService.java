package domain.shiro.service;

import domain.shiro.entity.AccountEntity;

public interface UserSecurityService {

    /**
     * 根据用户名获取用户信息
     * @param loginName 用户名
     * @return AccountEntity
     */
    AccountEntity accoutInfo(String loginName);
}
