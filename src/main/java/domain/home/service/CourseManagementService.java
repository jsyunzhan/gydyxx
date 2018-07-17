package domain.home.service;

import domain.home.entity.CourseEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface CourseManagementService {
    /**
     * 班本课程分页
     * @param courseEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult courseList(CourseEntity courseEntity);

    /**
     * 班本课程新增
     * @param courseEntity 新增实体
     * @return Boolean
     */
    Boolean courseAdd(CourseEntity courseEntity);

    /**
     * 班本课程修改
     * @param courseEntity 修改实体
     * @return Boolean
     */
    Boolean courseEdit(CourseEntity courseEntity);

    /**
     * 班本课程删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean courseDelete(Long id, Long loginId);

    List<CourseEntity> courseAllList(CourseEntity courseEntity);
}
