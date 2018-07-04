package domain.home.service;

import domain.home.entity.StudentEntity;
import domain.shiro.entity.PageQueryResult;

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
}
