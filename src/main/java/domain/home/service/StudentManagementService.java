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
}
