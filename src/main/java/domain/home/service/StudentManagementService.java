package domain.home.service;

import domain.home.entity.StudentEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface StudentManagementService {
    /**
     * 学子风采分页
     * @param studentEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult studentList(StudentEntity studentEntity);

    /**
     * 学子风采新增
     * @param studentEntity 新增实体
     * @return Boolean
     */
    Boolean studentAdd(StudentEntity studentEntity);

    /**
     * 学子风采修改
     * @param studentEntity 修改实体
     * @return Boolean
     */
    Boolean studentEdit(StudentEntity studentEntity);

    /**
     * 学子风采删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean studentDelete(Long id, Long loginId);

    /**
     * 学子风采接口
     * @param studentEntity 查询实体
     * @return List<StudentEntity>
     */
    List<StudentEntity> studentAllList(StudentEntity studentEntity);

    StudentEntity studentDetails(Long id);
}
