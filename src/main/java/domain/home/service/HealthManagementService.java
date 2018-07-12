package domain.home.service;

import domain.home.entity.HealthEntity;
import domain.shiro.entity.PageQueryResult;

public interface HealthManagementService {
    /**
     * 健康管理分页
     * @param healthEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult healthList(HealthEntity healthEntity);

    /**
     * 健康教育新增
     * @param healthEntity 新增实体
     * @return Boolean
     */
    Boolean healthAdd(HealthEntity healthEntity);

    /**
     * 健康教育修改
     * @param healthEntity 修改实体
     * @return Boolean
     */
    Boolean healthEdit(HealthEntity healthEntity);

    /**
     * 健康教育删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean healthDelete(Long id, Long loginId);
}
