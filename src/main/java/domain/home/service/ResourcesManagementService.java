package domain.home.service;

import domain.home.entity.ResourcesEntity;
import domain.shiro.entity.PageQueryResult;

public interface ResourcesManagementService {
    /**
     * 家校资源分页
     * @param resourcesEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult resourcesList(ResourcesEntity resourcesEntity);

    /**
     * 家校资源新增
     * @param resourcesEntity 新增实体
     * @return Boolean
     */
    Boolean resourcesAdd(ResourcesEntity resourcesEntity);

    /**
     * 家校资源修改
     * @param resourcesEntity 修改实体
     * @return Boolean
     */
    Boolean resourcesEdit(ResourcesEntity resourcesEntity);

    /**
     * 家校资源删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean resourcesDelete(Long id, Long loginId);
}
