package domain.home.service;

import domain.home.entity.EducationEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface EducationManagementService {
    /**
     * 教育科研分页
     * @param educationEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult educationList(EducationEntity educationEntity);

    /**
     * 教育科研新增
     * @param educationEntity 新增实体
     * @return Boolean
     */
    Boolean educationAdd(EducationEntity educationEntity);

    /**
     * 教育科研修改
     * @param educationEntity 修改实体
     * @return Boolean
     */
    Boolean educationEdit(EducationEntity educationEntity);

    /**
     * 教育科研删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean educationDelete(Long id, Long loginId);

    List<EducationEntity> educationAllList(EducationEntity educationEntity);

    EducationEntity educationDetails(Long id);
}
