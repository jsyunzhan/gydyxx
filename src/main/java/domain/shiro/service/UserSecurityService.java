package domain.shiro.service;

import domain.shiro.entity.AccountEntity;
import domain.shiro.entity.ResourceEntity;

import java.util.List;

public interface UserSecurityService {

    /**
     * 根据用户名获取用户信息
     * @param loginName 用户名
     * @return AccountEntity
     */
    AccountEntity accoutInfo(String loginName);

    /**
     * 获取资源
     * @param roleId 角色id
     * @return List<ResourceEntity> 资源信息
     */
    List<ResourceEntity> getResourceInfoByRoleId(Long roleId);
}
