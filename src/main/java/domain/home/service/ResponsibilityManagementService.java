package domain.home.service;

import domain.home.entity.ResponsibilityEntity;
import domain.shiro.entity.PageQueryResult;

public interface ResponsibilityManagementService {
    /**
     * 责任督学份分页
     * @param responsibilityEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult responsibilityList(ResponsibilityEntity responsibilityEntity);

    /**
     * 责任督学新增
     * @param responsibilityEntity 新增实体
     * @return Boolean
     */
    Boolean responsibilityAdd(ResponsibilityEntity responsibilityEntity);

    /**
     * 责任督学修改
     * @param responsibilityEntity 修改实体
     * @return Boolean
     */
    Boolean responsibilityEdit(ResponsibilityEntity responsibilityEntity);

    /**
     * 责任督学删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean responsibilityDelete(Long id, Long loginId);
}
