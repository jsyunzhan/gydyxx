package domain.home.service;

import domain.home.entity.TeachingEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface TeachingManagementService {
    /**
     * 教学资源分页
     * @param teachingEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult teachingList(TeachingEntity teachingEntity);

    /**
     * 教学资源新增
     * @param teachingEntity 新增实体
     * @return Boolean
     */
    Boolean teachingAdd(TeachingEntity teachingEntity);

    /**
     * 教学资源修改
     * @param teachingEntity 修改实体
     * @return Boolean
     */
    Boolean teachingEdit(TeachingEntity teachingEntity);

    /**
     * 教学资源删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean teachingDelete(Long id, Long loginId);

    List<TeachingEntity> teachingAllList(TeachingEntity teachingEntity);

    TeachingEntity teacheringDetails(Long id);
}
