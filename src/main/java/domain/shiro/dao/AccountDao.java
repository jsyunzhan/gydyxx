package domain.shiro.dao;

import domain.shiro.entity.AccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao {

    /**
     * 根据用户名获取用户信息
     * @param loginName 用户信息
     * @return AccountEntity
     */
    AccountEntity accoutInfo(String loginName);
}
