package domain.home.service;

import domain.home.entity.TeacherEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

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

    /**
     * 名师风采修改
     * @param teacherEntity 修改实体
     * @return Boolean
     */
    Boolean teacherEdit(TeacherEntity teacherEntity);

    /**
     * 名师风采杀出
     * @param id id
     * @param loginId 当前登录
     * @return Boolean
     */
    Boolean teacherDelete(Long id, Long loginId);

    /**
     * 名师风采接口
     * @param teacherEntity 查询实体
     * @return List<TeacherEntity>
     */
    List<TeacherEntity> teacherAllList(TeacherEntity teacherEntity);

    TeacherEntity teacherDetails(Long id);
}
