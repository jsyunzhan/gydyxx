package domain.home.service;

import domain.home.entity.TeacherEntity;
import domain.shiro.entity.PageQueryResult;

public interface TeacherManagementService {
    /**
     * 名师风采分页
     * @param teacherEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult teacherList(TeacherEntity teacherEntity);

    /**
     * 名师风采新增
     * @param teacherEntity 新增实体
     * @return Boolean
     */
    Boolean teacherAdd(TeacherEntity teacherEntity);
}
